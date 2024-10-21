package ar.com.santanderrio.obp.servicios.obp.base.security.impl;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.datasource.SystemDataSources;
import ar.com.santanderrio.obp.base.security.datasource.SystemRequested;
import ar.com.santanderrio.obp.servicios.obp.base.security.impl.DataSourceIntegrationTest.TestConfiguration;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * The Class DataSourceIntegrationTest.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(Profiles.TEST)
public class DataSourceIntegrationTest extends AbstractJndiTest {

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

    }

    /**
     * Sets the up class.
     *
     * @throws Exception
     *             the exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        String seguridadds = "jdbc/seguridadbd";
        String premiosds = "jdbc/premiosbd";
        String estadisticasds = "jdbc/fecpestad";
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            InitialContext ic = new InitialContext();

            try {
	             ic.createSubcontext("java:");
	             ic.createSubcontext("java:/comp");
	             ic.createSubcontext("java:/comp/env");
	             ic.createSubcontext("java:/comp/env/jdbc");
            } catch (NameAlreadyBoundException e) {
           	 System.out.println("Ya fueron definidos estos nombres en el contexto");
            }

            // Construct DataSource
            OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
            ds.setURL("jdbc:oracle:thin:@//ITR-WKS-0229.intranet.itresources:1521");
            ds.setUser("user2");
            ds.setPassword("user2");
            try {
            	ic.bind("java:/comp/env/" + seguridadds, ds);
            } catch (NameAlreadyBoundException e) {
            	 System.out.println("Ya fueron definidos estos nombres en el contexto");
           }
           try {
            	ic.bind("java:/comp/env/" + premiosds, ds);
           } catch (NameAlreadyBoundException e) {
           	 System.out.println("Ya fueron definidos estos nombres en el contexto");
          }
          try {
            	ic.bind("java:/comp/env/" + estadisticasds, ds);
			} catch (NameAlreadyBoundException e) {
				System.out.println("Ya fueron definidos estos nombres en el contexto");
			}

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", Profiles.TEST);
    }

    /** The system requested. */
    @Autowired
    private SystemRequested systemRequested;

    /** The system data sources. */
    private SystemDataSources systemDataSources;

    /**
     * Test request bad credentials.
     */
    /*
     * TODO Reactivar
     */
    @Ignore
    @Test
    public void testRequestBadCredentials() {
    }
}
