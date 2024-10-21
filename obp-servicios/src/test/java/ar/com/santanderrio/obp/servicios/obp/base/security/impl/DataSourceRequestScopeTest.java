package ar.com.santanderrio.obp.servicios.obp.base.security.impl;

import java.util.HashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.datasource.SystemDataSources;
import ar.com.santanderrio.obp.base.security.datasource.SystemRequested;
import ar.com.santanderrio.obp.servicios.obp.base.security.impl.DataSourceRequestScopeTest.TestConfiguration;

/**
 * The Class DataSourceRequestScopeTest.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(Profiles.TEST)
public class DataSourceRequestScopeTest extends AbstractJndiTest {

    /**
     * The Class TestConfiguration.
     */
    @Configurable
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.security", "ar.com.santanderrio.base.context",
            "ar.com.santanderrio.obp.base.dao" })
    public static class TestConfiguration {

        /**
         * Ds.
         *
         * @return the data source
         */
        @Bean(name = "defaultDataSource")
        public DataSource ds() {
            DriverManagerDataSource ds = new org.springframework.jdbc.datasource.DriverManagerDataSource();
            ds.setDriverClassName("oracle.jdbc.OracleDriver");
            ds.setUrl("jdbc:oracle:thin:@//ITR-WKS-0229.intranet.itresources:1521");
            ds.setUsername("user2");
            ds.setPassword("user2");
            return ds;
        }

        /**
         * Custom scope configurer.
         *
         * @return the custom scope configurer
         */
        @Bean
        public CustomScopeConfigurer customScopeConfigurer() {
            CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();

            HashMap<String, Object> scopes = new HashMap<String, Object>();
            scopes.put(WebApplicationContext.SCOPE_REQUEST, new SimpleThreadScope());
            scopes.put(WebApplicationContext.SCOPE_SESSION, new SimpleThreadScope());
            scopeConfigurer.setScopes(scopes);

            return scopeConfigurer;
        }
    }

    /**
     * Sets the up class.
     *
     * @throws Exception
     *             the exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", Profiles.TEST);
    }

    /** The system requested. */
    @Autowired
    private SystemRequested systemRequested;

    /** The system data sources. */
    @Autowired
    private SystemDataSources systemDataSources;

    /**
     * Aspect test.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * TODO Reactivar
     */
    @Ignore
    @Test
    public void aspectTest() throws Exception {
    }

}
