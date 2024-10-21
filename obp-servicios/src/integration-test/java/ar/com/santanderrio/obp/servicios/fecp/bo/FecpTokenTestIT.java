package ar.com.santanderrio.obp.servicios.fecp.bo;

import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.fecp.entitiy.DatosIn;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        FecpTokenTestIT.FecpBOTestConfiguration.class, SecurityProviderConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { 
        "CANJEPUNTOS.URL = http://wasfecpdesa01.ar.bsch:9081/mfecp-webapp/angular/#!/superclub?token=",
        "CANJEPUNTOS.REGISTRAR.URL = http://wasfecpdesa01.ar.bsch:9091/mfecp-servicios/login/registroToken",
        "CANJEPUNTOS.REGISTRAR.TIMEOUT=10",
        "APP.ENCODING=UTF-8" })

@ActiveProfiles(value = Profiles.TEST)
public class FecpTokenTestIT {
    /** The agenda manager. */
    @Autowired
    private FecpToken fecpToken;
    
    /** The agenda destinatarios manager. */
    @Autowired
    private SesionParametros sesionParametros;
    
    @Value("${CANJEPUNTOS.URL}")
    private String urlFecp;
    
    /**
     * The Class SeguroBOTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { FecpToken.class, Sign.class, KeyStoreHelperImpl.class, RespuestaFactory.class, RestWebClient.class },
                 excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {KeyStoreFactory.class }))
    public static class FecpBOTestConfiguration {

        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
        
        /**
         * Key store factory.
         *
         * @return the key store factory
         */
        @Bean
        public KeyStoreFactory keyStoreFactory() {
            return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

                /*
                 * (non-Javadoc)
                 * 
                 * @see
                 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
                 * InvocationOnMock)
                 */
                @Override
                public KeyStore answer(InvocationOnMock invocation) throws Throwable {
                    KeyStore keyStore = new KeyStore();
                    keyStore.setKeystoreType("JKS");
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("obp");
                    keyStore.setKeystorePassword("hbpassword");
                    return keyStore;
                }

            });
        }

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
        
        @Bean
        public SesionParametros sesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }

    }
    
    @Test
    public void armarToken() throws SQLException {

        SesionCliente sesionCliente = new SesionCliente();
        Cliente cliente = new Cliente();

        cliente.setTipoDocumento("96");
        cliente.setDni("11111111");
        cliente.setFechaNacimiento("20011010");
        cliente.setNup("123");
        cliente.setValorSinonimo("S");
        cliente.setTipoPersona("F");
        cliente.setUsuarioRacf("asdf");
        cliente.setPasswordRacf("ssdfds");
        cliente.setNombre("Juan");
        cliente.setApellido1("Perez");
        cliente.setApellido2("Rodriguez");

        sesionCliente.setCliente(cliente);
        
        EventosComercialesDTO dto = new EventosComercialesDTO();
        dto.setJsessionId("");
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(dto );

        Respuesta<TokenDTO> respuesta = fecpToken.obtenerHash(sesionCliente);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertNotEquals(0, respuesta.getRespuesta().getTokenFirmado().length());
        Assert.assertEquals(urlFecp, respuesta.getRespuesta().getUrl());

    }
    
    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
      throws IOException {
      
        DatosIn user = new DatosIn("John");
     
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
        om.getSerializationConfig().isEnabled(SerializationConfig.Feature.INDENT_OUTPUT);
        om.getSerializationConfig().isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
        String result = om.writeValueAsString(user);
        
        Assert.assertTrue(result.contains("token"));
        Assert.assertTrue(result.contains("datos"));
//        assertThat(result, containsString("user"));
    }
}
