/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * DAO base para las operaciones contra la base en el schema Acciones.
 * 
 * testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:1521:RIO147D");
 * ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
 * 
 * @author sergio.e.goldentair
 *
 */
public class HbAccionesBaseDAOIT {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HbAccionesBaseDAOIT.class);

    /**
     * Sets the up class.
     *
     * @throws SQLException the SQL exception
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
    	 String seguridadds = "jdbc/seguridadbd";
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
             ds.setURL("jdbc:oracle:thin:@//180.166.44.165:5321/srv_RIO58D3_ap");
             ds.setUser("seg02_user1");
             ds.setPassword("seg02_user1");

             // Construct DataSource
             OracleConnectionPoolDataSource dsBpriv = new OracleConnectionPoolDataSource();
//             dsBpriv.setURL("jdbc:oracle:thin:@DBHOMO02:1521:GSAH");
             dsBpriv.setURL("jdbc:oracle:thin:@DBHOMO01.ar.bsch:1526:GSAH3");
//             dsBpriv.setURL("jdbc:oracle:thin:@LXDESAORA3:5321:RIO147D");
             try {
             	ic.bind("java:/comp/env/" + seguridadds, ds);
             } catch (NameAlreadyBoundException e) {
             	 LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
            try {
             	ic.bind("java:/comp/env/" + priv, dsBpriv);
	         } catch (NameAlreadyBoundException e) {
	          	 LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
	        }

         } catch (NamingException ex) {
             LOGGER.error("Error de jndi", ex);
         }
    }

}
