package ar.com.santanderrio.obp.servicios.mya.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoMisCuentasDAOImpl;

/**
 * The Class PagoMisCuentasDAOTest.
 * 
 * @author emilio.watemberg
 * @since Jan 17, 2017.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        MyaDAOTest.PagoMisCuentasDAOTestConfiguration.class })
public class MyaDAOTest {

    /** The pago mis cuentas DAO. */
    @Autowired
    @InjectMocks
    private PagoMisCuentasDAOImpl pagoMisCuentasDAO;

    /** The mensaje dao. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The respueta O ky ERROR trama. */
    String respuetaOKyERRORTrama;

    /** The respuesta error. */
    String respuestaError;

    /** The respuesta OK trama. */
    String respuestaOKTrama;

    /** The mensaje pago duplicado. */
    private String MENSAJE_SALDO_INSUFICIENTE = "1273";

    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        respuetaOKyERRORTrama = "200000000000P04HTML00099000103FREEUSER  XXXXXXXX00507108000000102016111116584900000000IBF26804000000000000PAGMASSCIO1000000            00011903    00        00 000000000201611221108090000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0044400000000õ02õ0õ0000000õ   õ00õ                                                                                õ6265õ                                        õCONSERVE EL TICKET COMO COMPROBANTE     õ õ  õ            õ583694328435õ1õ0000058õBANõ01õINSUFIC. SALDO DISPONIBLE (< O = A 0)                                           õ    õ                                        õ                                        õ õ  õ            õ            õ";
        respuestaError = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF25276000000000000PAGMASSCIO1000000            00011903    00        00 000000000201609191457590000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0036810000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";
    }

    /**
     * The Class PagoMisCuentasDAOTestConfiguration.
     */
    @Configuration
    public static class PagoMisCuentasDAOTestConfiguration {

        /**
         * Pago mis cuentas DAO.
         *
         * @return the pago mis cuentas DAO
         */
        @Bean
        public PagoMisCuentasDAO pagoMisCuentasDAO() {
            return new PagoMisCuentasDAOImpl();
        }

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeDAO mensajeDAO() {
            return Mockito.mock(MensajeDAO.class);
        }
    }

    /**
     * Ejecutar pago multiple test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    @Ignore
    public void ejecutarPagoMultipleTest() throws IatxException, DAOException {

    }
}
