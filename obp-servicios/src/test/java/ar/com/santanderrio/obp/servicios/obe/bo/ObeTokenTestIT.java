package ar.com.santanderrio.obp.servicios.obe.bo;

import java.sql.SQLException;

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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ObeTokenTestIT.ObeBOTestConfiguration.class, SecurityProviderConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "OBE.LOGIN.SWITCH.URL=http://wasdesaobe01.ar.bsch:9085/nb/principalOBP.jsp",
        "APP.ENCODING=UTF-8" })
@ActiveProfiles(value = Profiles.TEST)
public class ObeTokenTestIT {

    /** The agenda manager. */
    @Autowired
    private ObeToken obeToken;

    @Value("${OBE.LOGIN.SWITCH.URL}")
    private String urlObe;

    @Configuration
    @ComponentScan(basePackageClasses = { Sign.class, KeyStoreHelperImpl.class, RespuestaFactory.class,
            ObeToken.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class ObeBOTestConfiguration {

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
    }

    @Test
    public void armarToken() throws SQLException {

        SesionCliente sesionCliente = new SesionCliente();
        Cliente cliente = new Cliente();

        cliente.setUsuarioRacf("asdf");
        cliente.setPasswordRacf("ssdfds");
        cliente.setNup("123");
        cliente.setDni("11111111");
        cliente.setFechaNacimiento("20011010");
        cliente.setTipoPersona("F");
        cliente.setTipoDocumento("96");
        cliente.setTipoClave("c");
        cliente.setMarcaANPH("m");

        sesionCliente.setCliente(cliente);
        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(sesionCliente.getCliente());
        Respuesta<TokenDTO> respuesta = obeToken.armarToken(null, tokenExternoDTO);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertNotEquals(respuesta.getRespuesta().getTokenFirmado().length(), 0);
        Assert.assertEquals(respuesta.getRespuesta().getUrl(), urlObe);

    }

}
