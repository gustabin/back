package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSender;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxSenderImpl;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.base.security.datasource.DataSourceJNDIFactory;
import ar.com.santanderrio.obp.base.security.datasource.SystemRequested;
import ar.com.santanderrio.obp.base.security.datasource.impl.DataSourceJNDIFactoryTestImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.impl.IatxCommImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoPrestamoBOMensajeIT.PagoPrestamoBOMensajeITConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagoPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.IntervinientesDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.IntervinientesDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoPrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.prestamos.bo.SimuladorPrestamoBO;
import ar.com.santanderrio.obp.servicios.ws.RestTemplateConfig;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;

/**
 * The Class PagoPrestamoBOMensajeIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PagoPrestamoBOMensajeITConfiguration.class, RestTemplateConfig.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
public class PagoPrestamoBOMensajeIT {

    /** The pago prestamo BO. */
    @Autowired
    private PagoPrestamoBO pagoPrestamoBO;

    /**
     * The Class PagoPrestamoBOMensajeITConfiguration.
     */
    @Configurable
    @EnableAspectJAutoProxy
    @PropertySource("classpath:integration-test/servicios-it.properties")
    public static class PagoPrestamoBOMensajeITConfiguration {

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        public SimuladorPrestamoBO simuladorPrestamoBO() {
            return Mockito.mock(SimuladorPrestamoBO.class);
        }

        /**
         * Pago prestamo BO.
         *
         * @return the pago prestamo BO
         */
        @Bean
        public PagoPrestamoBO pagoPrestamoBO() {
            return new PagoPrestamoBOImpl();
        }

        /**
         * Respuesta BO.
         *
         * @return the respuesta BO
         */
        @Bean
        public RespuestaBO respuestaBO() {
            return new RespuestaBOImpl();
        }

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeDAO mensajeDAO() {
            return new MensajeDAOImpl();
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return new MensajeBOImpl();
        }

        /**
         * Data source JNDI factory test impl.
         *
         * @return the data source JNDI factory
         */
        @Bean
        public DataSourceJNDIFactory dataSourceJNDIFactoryTestImpl() {
            return new DataSourceJNDIFactoryTestImpl();
        }

        /**
         * System requested.
         *
         * @return the system requested
         */
        @Bean
        public SystemRequested systemRequested() {
            return new SystemRequested();
        }

        /**
         * Pago prestamo DAO.
         *
         * @return the pago prestamo DAO
         */
        @Bean
        public PagoPrestamoDAO pagoPrestamoDAO() {
            return new PagoPrestamoDAOImpl();
        }

        /**
         * Iatx comm.
         *
         * @return the iatx comm
         */
        @Bean
        public IatxComm iatxComm() {
            return new IatxCommImpl();
        }

        /**
         * Iatx sender.
         *
         * @return the iatx sender
         */
        @Bean
        public IatxSender iatxSender() {
            return new IatxSenderImpl();
        }

        /**
         * Intervinientes DAO.
         *
         * @return the intervinientes DAO
         */
        @Bean
        public IntervinientesDAO intervinientesDAO() {
            return new IntervinientesDAOImpl();
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
        String aliasds = "jdbc/hbalias";
        String estadisticasds = "jdbc/hbestad";

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
            ds.setURL("jdbc:oracle:thin:@//BDCD20001:1570/BDCD20001.ar.bsch");

            // Construct DataSource
            OracleConnectionPoolDataSource dsEst = new OracleConnectionPoolDataSource();
            dsEst.setURL("jdbc:oracle:thin:@LXDESAORA3:1521/RIO147D");
            try {
                ic.bind("java:/comp/env/" + seguridadds, ds);
            } catch (NameAlreadyBoundException e) {
                System.out.println("Ya fueron definidos estos nombres en el contexto");
            }
            try {
                ic.bind("java:/comp/env/" + estadisticasds, dsEst);
            } catch (NameAlreadyBoundException e) {
                System.out.println("Ya fueron definidos estos nombres en el contexto");
            }
            try {
                ic.bind("java:/comp/env/" + aliasds, dsEst);
            } catch (NameAlreadyBoundException e) {
                System.out.println("Ya fueron definidos estos nombres en el contexto");
            }

        } catch (NamingException ex) {
            System.out.println(ex);
        }

        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", Profiles.TEST);
    }

    /**
     * Test get transferencia agendada rio rio.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws ParseException
     *             the parse exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testGetTransferenciaAgendadaRioRio() throws DAOException, ParseException, IllegalAccessException {

        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();

        cuenta.setNroSucursal("123");
        cuenta.setAlias("Cuenta Alias");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("200688");
        prestamo.setNumeroCuentaProducto("1234567");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);

        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();
        comprobantePrestamo.setImporteDebito(BigDecimal.valueOf(220.50));

        String respuesta = pagoPrestamoBO.obtenerMensajePagoOk(prestamo, comprobantePrestamo);
        assertNotNull(respuesta);
    }

}
