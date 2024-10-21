package ar.com.santanderrio.obp.servicios.segmento.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.segmento.dao.SegmentoClienteDAOIT.SegmentoClienteDAOITConfiguration;

/**
 * The Class SegmentoClienteDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SegmentoClienteDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "SEGMENTO.POOL.ACTIVO=false", 
        "SEGMENTO.URL=http://180.166.45.250:9550/ClientData.svc",
        "SEGMENTO.TIMEOUT=1", "SEGMENTO.POOL.SIZE=3", "SEGMENTO.POOL.MAXWAIT=2",  "APP.ENCODING = UTF-8"  })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Ignore
public class SegmentoClienteDAOIT {
	
	/** The segmento cliente DAO. */
	@Autowired
	private SegmentoClienteDAO segmentoClienteDAO;

	/**
     * The Class GestionWSTestConfiguration.
     */
    @Configuration
    @ComponentScan(
    		basePackageClasses = { SegmentoClienteDAO.class,  Sign.class, KeyStoreHelperImpl.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
            KeyStoreFactory.class }))
    public static class SegmentoClienteDAOITConfiguration {
        
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
    
    
    /**
     * Segmento test.
     *
     * @throws DAOException the DAO exception
     */
    @Test()
    public void segmentoTest() throws DAOException  {
    	String nup = "03003824";
    	String channel = "70";
    	String subchannel = "00";
    	segmentoClienteDAO.getClienteSegmento(nup, channel, subchannel, "FREEUSER", "FREEUSER");
		
    	
    }
    
    /**
     * Segmento test.
     *
     * @throws DAOException the DAO exception
     */
    @Test()
    public void getClientSelectSegmentTest() throws DAOException  {
    	String nup = "03003824";
    	String channel = "70";
    	String subchannel = "00";
    	segmentoClienteDAO.getClientSelectSegment(nup, channel, subchannel, "FREEUSER", "FREEUSER");
		
    	
    }
}
