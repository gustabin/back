/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencia;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
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

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.seguros.entities.TenenciaLogin;
import ar.com.santanderrio.obp.servicios.seguros.entities.TenenciaPoliza;
import ar.com.santanderrio.obp.servicios.seguros.entities.TenenciaPolizaResponse;
import ar.com.santanderrio.obp.servicios.seguros.entities.TenenciaResponse;
import ar.com.santanderrio.obp.servicios.tenencia.TenenciaDAOIT.TenenciaDAOITConfiguration;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TenenciaDAOITConfiguration.class})
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { 
        "TENENCIA.URL=https://wasrioseghomo.ar.bsch:9446/segurosServicios/api/olb",
        "TENENCIA.TIMEOUT=10000",
        "TENENCIA.PALABRAS.SENSIBLES.IN=user,password,nup,numeroDocumento",
        "TENENCIA.PALABRAS.SENSIBLES.OUT=",
        "KEYSTORE.TENENCIA.TYPE=JKS",
        "KEYSTORE.TENENCIA.PATH=<mockeado>",
        "KEYSTORE.TENENCIA.IDSEGURIDAD=20104",
        "TENENCIA.JKS=TENENCIA",
        "APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TenenciaDAOIT {
    
    @Autowired
    private RestWebClient restWebClient;
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class GestionWSTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = {RestWebClientImpl.class,  Sign.class, KeyStoreHelperImpl.class, ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class TenenciaDAOITConfiguration {
        
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
//                	"KEYSTORE.TENENCIA.PATH=",
//                    "KEYSTORE.TENENCIA.IDSEGURIDAD=20001",
                    KeyStore keyStore = new KeyStore();
                    keyStore.setKeystoreType("JKS");
                    keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
                    keyStore.setKeystoreAlias("seguros");
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
    public void consultarWsTenenciaLoginNombreWS() throws DAOException {
    	WebClient clientLogin = restWebClient.obtenerClienteRest("TENENCIA");
        TenenciaResponse token = obtenerToken(clientLogin);
        Assert.assertEquals("0", token.getCodigoRespuesta());
        Assert.assertNotNull(token.getRespuesta().getToken());
        
        WebClient clientPoliza = restWebClient.obtenerClienteRest("TENENCIA");
        TenenciaPolizaResponse tenencias = obtenerPolizas(clientPoliza, token.getRespuesta().getToken());
        
        Assert.assertNotNull(tenencias.getRespuesta());
    }

    private TenenciaResponse obtenerToken(WebClient clientLogin) {
        clientLogin.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        clientLogin.path("/login");
        TenenciaLogin tenenciaLogin = new TenenciaLogin();
        tenenciaLogin.setUser("default");
        tenenciaLogin.setPassword("default");
        return clientLogin.post(tenenciaLogin, TenenciaResponse.class);
    }
    
    private TenenciaPolizaResponse obtenerPolizas(WebClient clientPoliza, String token) {
    	TenenciaPoliza poliza = new TenenciaPoliza();
        poliza.setNup("00011903");
        poliza.setFechaNacimiento("19640714");
        poliza.setNumeroDocumento("00016940691");
        poliza.setTipoDocumento("N");
        
    	clientPoliza.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
        clientPoliza.header("token", token);
        clientPoliza.path("/polizas/tenencias");
        return clientPoliza.post(poliza, TenenciaPolizaResponse.class);
    }

}
