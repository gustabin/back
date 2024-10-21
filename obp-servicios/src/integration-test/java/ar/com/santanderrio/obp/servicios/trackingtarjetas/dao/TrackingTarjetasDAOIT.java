package ar.com.santanderrio.obp.servicios.trackingtarjetas.dao;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.solicitudes.andreani.dao.TrackingTarjetasGestionarWSImpl;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.impl.TrackingTarjetasDAOImpl;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.mock.TrackingTarjetasObjectsMock;

/**
 * Clase test TrackingTarjetasDAOIT
 * @author Silvina_Luque 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.TrackingTarjetasDAOIT.TrackingTarjetasDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = {
        "APP.ENCODING = UTF-8",
        "ANDREANI.POOL.ACTIVO = false",
        "ANDREANI.URL = http://wasdesainet01.ar.bsch:9084/WSDELTARJ/services/WSDelTarjSOAP",
        "ANDREANI.TIMEOUT = 10000",
        "ANDREANI.JKS = VISAAMEX",
        "ANDREANI.POOL.SIZE = 30",
        "ANDREANI.POOL.MAXWAIT = 5000"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TrackingTarjetasDAOIT {
    
    @Autowired
    private TrackingTarjetasDAO trackingTarjetasDAO;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class GestionWSTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { TrackingTarjetasGestionarWSImpl.class, TrackingTarjetasDAOImpl.class, ContextHolder.class })
    public static class TrackingTarjetasDAOITConfiguration {
        
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
                    keyStore.setKeystoreAlias("bcorio");
                    keyStore.setKeystorePassword("hbpassword");
                    return keyStore;
                }

            });
        }

        @Bean
        public CredentialSecurityFactory credentialSecurityFactory() {
            return Mockito.mock(CredentialSecurityFactory.class);
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
        public static   IatxComm iatxComm() {
            return Mockito.mock(IatxComm.class);
        }

        
    }
    
    /**
     * 
     * @throws consultarTrazaOKTest
     */
    @Test//(expected = Exception.class) El servicio no esta funcionando en el ambiente de desarrollo
    public void consultarTrazaOKTest() throws DAOException {
        TrackingTarjetasIn inEntity =TrackingTarjetasObjectsMock.obtenerTrackingTarjetasIn();
        TrackingTarjetasOut respuesta = trackingTarjetasDAO.consultarTraza(inEntity);
        Assert.assertNotNull(respuesta);

    }
 
    
 
}
