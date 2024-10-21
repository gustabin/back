package ar.com.santanderrio.obp.servicios.seguros.bo;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.seguros.dao.SeguroTenenciasDAO;
import ar.com.santanderrio.obp.servicios.token.externos.TokenExternoDTO;
import ar.com.santanderrio.obp.servicios.token.externos.dto.TokenDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SeguroTokenTestIT.SeguroBOTestConfiguration.class, SecurityProviderConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "SEGUROS.RAMO=0", "KEYSTORE.SEGUROS.IDSEGURIDAD=20104", "SEGUROS.USUARIO=HMBK",
        "SEGUROS.PASSWORD=HK2453690", "SEGUROS.CANAL=04",
        "SEGUROS.URL = http://wasdesainet07:9092/segurosServiciosWeb/#!/tenencias?type={0}&jwt=",
        "APP.ENCODING=UTF-8" })

@ActiveProfiles(value = Profiles.TEST)
public class SeguroTokenTestIT {

    /** The agenda manager. */
    @Autowired
    private SeguroToken seguroToken;

    @Value("${SEGUROS.URL}")
    private String urlSeguros;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class SeguroBOTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { SeguroToken.class, Sign.class, KeyStoreHelperImpl.class,
            RespuestaFactory.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                    KeyStoreFactory.class }))
    public static class SeguroBOTestConfiguration {

        @Bean
        public SeguroTenenciasDAO seguroTenenciasDAO() {
            return Mockito.mock(SeguroTenenciasDAO.class);
        }

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

        Cliente cliente = new Cliente();

        cliente.setTipoDocumento("96");
        cliente.setDni("11111111");
        cliente.setFechaNacimiento("20011010");
        cliente.setNup("123");

        TokenExternoDTO tokenExternoDTO = new TokenExternoDTO(cliente);
        Respuesta<TokenDTO> respuesta = seguroToken.armarToken(null, tokenExternoDTO);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertNotEquals(respuesta.getRespuesta().getTokenFirmado().length(), 0);
        Assert.assertEquals(respuesta.getRespuesta().getUrl(), urlSeguros);

    }
}
