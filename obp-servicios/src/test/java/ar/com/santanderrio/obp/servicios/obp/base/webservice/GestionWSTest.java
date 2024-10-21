/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.base.webservice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreException;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.PlanVService;
import ar.com.santanderrio.obp.servicios.obp.base.webservice.GestionWSTest.GestionWSTestConfiguration;

/**
 * Mover a IT.
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { GestionWSTestConfiguration.class })
@ActiveProfiles(Profiles.TEST)
@Ignore
public class GestionWSTest {

    /** The ws soap. */
    @InjectMocks
    @Autowired
    private GestionarWS<PlanVService> wsSoap;

    /** The key store factory. */
    @Autowired
    private KeyStoreFactory keyStoreFactory;

    /**
     * The Class GestionWSTestConfiguration.
     */
    @Configurable
    @ComponentScan(basePackages = { "ar.com.santanderrio.base.webservice", "ar.com.santanderrio.obp.base.webservice" })
    public static class GestionWSTestConfiguration {

        /**
         * Key store factory.
         *
         * @return the key store factory
         */
        @Bean
        public KeyStoreFactory keyStoreFactory() {
            return Mockito.mock(KeyStoreFactory.class);
        }
    }

    /**
     * Inits the.
     *
     * @throws KeyStoreException
     *             the key store exception
     */
    @Before
    public void init() throws KeyStoreException {
        MockitoAnnotations.initMocks(this);
        KeyStore ks = new KeyStore();
        ks.setKeystoreType("jks");
        ks.setKeystorePath("C:/isbanCertificados/hbkey.jks");
        ks.setKeystoreAlias("bcorio");
        ks.setKeystorePassword("hbpassword");
        Mockito.when(keyStoreFactory.createKeyStore(Matchers.anyString())).thenReturn(ks);
    }

    /**
     * Invocar analyze response not null.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void invocarAnalyzeResponseNotNull() throws Exception {
        PlanVService cliente = null;
        InformacionPlanV response = null;
        try {
            cliente = wsSoap.obtenerPort();
            response = cliente.getInformacionPlanV("");
        } finally {
            wsSoap.liberarPort(cliente);
        }
        Assert.assertNull(response);
    }

}
