/**
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
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
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ResultadoAltaWS;
import ar.com.santanderrio.obp.servicios.producto.dao.SolicitudProductoDAOIT.SolicitudProductoDAOITConfiguration;

/**
 * The Class AliasCbuDAOIT.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SolicitudProductoDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "SOLICITUDES.POOL.ACTIVO=false", 
        "SOLICITUDES.URL=http://wasdesafront04.ar.bsch:9080/gec/services/WS_GC",
        "SOLICITUDES.TIMEOUT=100", 
        "SOLICITUDES.POOL.SIZE=30", 
        "SOLICITUDES.POOL.MAXWAIT=50",  
        "APP.ENCODING = UTF-8"})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SolicitudProductoDAOIT {

    /** The alias DAO. */
    @Autowired
    private SolicitudProductoDAO solicitudProductoDAO;
    
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
    @ComponentScan(basePackageClasses = { SolicitudProductoGestionarWSImpl.class, SolicitudProductoDAOImpl.class, ContextHolder.class })
    public static class SolicitudProductoDAOITConfiguration {
        
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

        /**
         * Credential security factory.
         *
         * @return the credential security factory
         */
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

    }

    /**
     * Test alta gestion.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testAltaGestion() throws DAOException {
        Integer codAsociacion = 76;
        String tipoPersona = "F";
        Integer nup = 276937;
        String codSector = "NEIT";
        String codUser = "HBAN0002";
        Integer medioIngreso = 35;
        String comentario = "VISA RECARGABLE;581743420;4297510125700650";
        ArrayOf158770343432493182NillableInfoRequeridaWS array = new ArrayOf158770343432493182NillableInfoRequeridaWS();

        ResultadoAltaWS rta = this.solicitudProductoDAO.altaGestion(codAsociacion, tipoPersona, nup, codSector, codUser, medioIngreso, comentario, array);
        
        Assert.assertNotNull( rta.getCodRetorno() );
        
        Assert.assertNotNull(rta.getDescRetorno());
    }
    
}
