package ar.com.santanderrio.obp.servicios.crm;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.generated.webservices.crm.ResponseGrupal;
import ar.com.santanderrio.obp.servicios.crm.AlertasCRMTest.AlertasCRMTestConfiguration;
import ar.com.santanderrio.obp.servicios.crm.dao.AlertasCRMDAO;


/**
 * The Class AlertasCRMTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AlertasCRMTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(value = Profiles.TEST)
@TestPropertySource(properties = { "CRM.POOL.ACTIVO=false",
        "CRM.URL=http://webdesa5:8038/alertasporcanal.asmx",
        "CRM.TIMEOUT=10",
        "CRM.POOL.SIZE=30",
        "CRM.POOL.MAXWAIT=5"})
public class AlertasCRMTest { 
	
	/** The alertas CRMDAO. */
	@Autowired
	private AlertasCRMDAO alertasCRMDAO;

	/**
     * The Class OnDemandClientTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.crm.dao", 
    		"ar.com.santanderrio.obp.base.webservice"})
    public static class AlertasCRMTestConfiguration {

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
        
        /**
         * Key store factory.
         *
         * @return the key store factory
         */
        @Bean
        public KeyStoreFactory keyStoreFactory() {
            return Mockito.mock(KeyStoreFactory.class);
        }
        
        /**
         * System routing data source.
         *
         * @return the system routing data source
         */
        @Bean
        public SystemRoutingDataSource SystemRoutingDataSource() {
        	return Mockito.mock(SystemRoutingDataSource.class);
        }

    }
    
    /**
     * Probar CRM.
     *
     * @throws DAOException the DAO exception
     */
    @Ignore
    @Test
    public void probarCRM() throws DAOException  {
    	ResponseGrupal resultado = alertasCRMDAO.consultarCRM("02615492");
        Assert.assertNotNull(resultado);
    }
}
