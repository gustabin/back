package ar.com.santanderrio.obp.servicios.obp.base.security.impl;

import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.datasource.SystemDataSources;
import ar.com.santanderrio.obp.base.security.datasource.SystemRequested;
import ar.com.santanderrio.obp.servicios.obp.base.security.impl.MultiThreadDataSourceTest.TestConfiguration;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * The Class MultiThreadDataSourceTest.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(Profiles.TEST)
public class MultiThreadDataSourceTest extends AbstractJndiTest {

    /**
     * The Class TestConfiguration.
     */
    @Configurable
    @Configuration
    @EnableAsync
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.security", "ar.com.santanderrio.base.context",
            "ar.com.santanderrio.obp.base.dao" })
    public static class TestConfiguration implements AsyncConfigurer {

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.scheduling.annotation.AsyncConfigurer#
         * getAsyncExecutor()
         */
        @Override
        public Executor getAsyncExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(4);
            executor.setMaxPoolSize(5);
            executor.setQueueCapacity(50);
            executor.setThreadNamePrefix("MyExecutor-");
            executor.initialize();
            return executor;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.scheduling.annotation.AsyncConfigurer#
         * getAsyncUncaughtExceptionHandler()
         */
        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
            return new SimpleAsyncUncaughtExceptionHandler();
        }

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
    @Autowired
    private SystemDataSources systemDataSources;

    /**
     * Test request ok.
     *
     * @throws InterruptedException
     *             the interrupted exception
     * @throws ExecutionException
     *             the execution exception
     */
    /*
     * TODO Reactivar
     */
    @Ignore
    @Test
    public void testRequestOk() throws InterruptedException, ExecutionException {
    }

    /**
     * Test system requested scope.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    @Ignore
    public void testSystemRequestedScope() throws DAOException {
    }

}
