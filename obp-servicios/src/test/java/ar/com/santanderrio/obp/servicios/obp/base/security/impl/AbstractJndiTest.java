package ar.com.santanderrio.obp.servicios.obp.base.security.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import org.junit.BeforeClass;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * The Class AbstractJndiTest.
 */
public class AbstractJndiTest {

    /**
     * Sets the up class.
     *
     * @throws Exception
     *             the exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        String seguridadds = "jdbc/seguridadbd";
        String fecpestad = "jdbc/fecpestad";
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
            ds.setUser("user1");
            ds.setPassword("user1");
            try {
            	ic.bind("java:/comp/env/" + seguridadds, ds);
            } catch (NameAlreadyBoundException e) {
            	 System.out.println("Ya fueron definidos estos nombres en el contexto");
           }
           try {
            	ic.bind("java:/comp/env/" + fecpestad, ds);
        	} catch (NameAlreadyBoundException e) {
        		System.out.println("Ya fueron definidos estos nombres en el contexto");
        	}

        } catch (NamingException ex) {
            System.out.println(ex);
        }

    }

}
