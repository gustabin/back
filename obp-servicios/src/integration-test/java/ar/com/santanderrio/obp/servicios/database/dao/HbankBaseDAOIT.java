package ar.com.santanderrio.obp.servicios.database.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * DAO base para las operaciones contra la base en el schema HBANK.
 * 
 * testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:1521:RIO147D");
 * ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
 * 
 * @author sergio.e.goldentair
 *
 */
public class HbankBaseDAOIT {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HbankBaseDAOIT.class);

    /**
     * Sets the up class.
     *
     * @throws SQLException
     *             the SQL exception
     */
    @BeforeClass
    public static void setUpClass() throws SQLException {
        String seguridadds = "jdbc/seguridadbd";
        String testDs = "jdbc/hbalias";
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
            ds.setURL("jdbc:oracle:thin:@//RIO58D3DESA.ar.bsch:5321/srv_RIO58D3_ap");
            ds.setUser("seg02_user1");
            ds.setPassword("seg02_user1");

            // Construct DataSource
            OracleConnectionPoolDataSource testConDS = new OracleConnectionPoolDataSource();
            testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:5321:RIO147D");
            try {
                ic.bind("java:/comp/env/" + seguridadds, ds);
                ic.bind("java:/comp/env/" + testDs, testConDS);
            } catch (NameAlreadyBoundException e) {
                LOGGER.error("Ya fueron definidos estos nombres en el contexto", e);
            }
        } catch (NamingException ex) {
            LOGGER.error("Error de jndi", ex);
        }
    }

    /**
     * Poder obtener la implementacion que utiliza spring del proxy con el que
     * se esta operando.
     * 
     * @param proxy
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> T getTargetObject(Object proxy) {
        if (AopUtils.isJdkDynamicProxy(proxy)) {
            try {
                return (T) ((Advised) proxy).getTargetSource().getTarget();
            } catch (Exception e) {
                LOGGER.error(
                        "No se pudo obtener la implementacion detras del proxy solicitado gestionado por spring, se devuelve el proxy recibido.",
                        e);
                return (T) proxy;
            }
        } else {
            return (T) proxy;
        }
    }

}
