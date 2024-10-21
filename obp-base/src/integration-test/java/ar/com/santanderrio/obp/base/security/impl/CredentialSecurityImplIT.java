package ar.com.santanderrio.obp.base.security.impl;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.impl.CredentialSecurityFactoryDAOImpl;
import ar.com.santanderrio.obp.base.security.impl.CredentialSecurityImplIT.CredentialSecurityImplITConfiguration;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * The Class CredentialSecurityImplIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CredentialSecurityImplITConfiguration.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.DIAGNOSTIC)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CredentialSecurityImplIT {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CredentialSecurityImplIT.class);

    /**
     * The Class CredentialSecurityImplITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @PropertySource(value = { "classpath:/integration-test/credentialSecurityImplTest.properties" })
    public static class CredentialSecurityImplITConfiguration {

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

    /** The cs. */
    @Autowired
    private CredentialSecurity cs;

    /** The csf. */
    @Autowired
    private CredentialSecurityFactory csf;

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
        String aliasds = "jdbc/hbalias";
        String estds = "jdbc/hbestad";
        String priv = "jdbc/hbdbacciones";
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
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }

            // Construct DataSource
            OracleConnectionPoolDataSource ds = new OracleConnectionPoolDataSource();
            ds.setURL("jdbc:oracle:thin:@//180.166.41.124:1570/RIO58D2.ar.bsch");
            // ds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            ds.setUser("seg02_user1");
            ds.setPassword("seg02_user1");

            try {
                ic.bind("java:/comp/env/" + seguridadds, ds);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
            try {
                ic.bind("java:/comp/env/" + aliasds, ds);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
            try {
                ic.bind("java:/comp/env/" + premiosds, ds);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
            try {
                ic.bind("java:/comp/env/" + estds, ds);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
            try {
                ic.bind("java:/comp/env/" + priv, ds);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }

        } catch (NamingException ex) {
            LOGGER.error("Error de jndi", ex);
        }

    }

    /**
     * Test.
     */
    @Test
    public void test() {
        assertNotNull(csf);
        assertNotNull(cs);
    }

    /**
     * Gets the credential.
     *
     * @return the credential
     * @throws SQLException
     *             the SQL exception
     * @throws NamingException
     *             the naming exception
     */
    @Test
    public void getCredential() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        Context webContext = (Context) initContext.lookup("java:/comp/env");
        DataSource dataSource = (DataSource) webContext.lookup("jdbc/seguridadbd");

        CredentialSecurityFactory csf = new CredentialSecurityFactoryDAOImpl(dataSource);
        Credential cr = csf.getCredentialById("20104");
        Assert.notNull(cr);
        LOGGER.info("{} {}.", cr.getUsuario(), cr.getPassword());
    }

    /**
     * Look up jndi not exists.
     *
     * @throws NamingException
     *             the naming exception
     */
    @Test(expected = NamingException.class)
    public void lookUpJndiNotExists() throws NamingException {
        Context initContext = new InitialContext();
        Context webContext = (Context) initContext.lookup("java:/comp/env");
        webContext.lookup("jdbc/noexiste");

    }

}
