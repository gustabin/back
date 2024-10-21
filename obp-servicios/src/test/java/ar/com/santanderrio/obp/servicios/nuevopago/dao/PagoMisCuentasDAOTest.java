package ar.com.santanderrio.obp.servicios.nuevopago.dao;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoMisCuentasDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;

/**
 * The Class PagoMisCuentasDAOTest.
 * 
 * @author emilio.watemberg
 * @since Jan 17, 2017.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PagoMisCuentasDAOTest.PagoMisCuentasDAOTestConfiguration.class })
public class PagoMisCuentasDAOTest extends IatxBaseDAOTest {

    /** The pago mis cuentas DAO. */
    @Autowired
    @InjectMocks
    private PagoMisCuentasDAOImpl pagoMisCuentasDAO;

    /** The mensaje dao. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respueta Oky ERROR trama, v1. Y casos v2 */
    String respuetaOKyERRORTrama;

    /** The respuesta error. */
    String respuestaOK;

    /** The respuesta error. */
    String respuestaError;
    /** The respuesta OK. codigo de retorno 0. */
    String respuestaErrorCaso0;

    /** The respuesta error caso 1. */
    String respuestaErrorCaso1;

    /** The respuesta error caso 2. */
    String respuestaErrorCaso2;

    /** The respuesta error caso 3. */
    String respuestaErrorCaso3;

    /** The respuesta error caso 4. */
    String respuestaErrorCaso4;

    /** The respuesta error 20000000. */
    String respuestaError20000000;

    /** The respuesta OK trama. */
    String respuestaOKTrama;

    /** The respuesta error respuestaError41. */
    String respuestaError41;

    /** The respuesta error respuestaerrorTimeOut. */
    String respuestaErrorTimeOut;
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        respuestaOK = "200000000000P04HTML00099000103FREEUSER  ********00208136000000332017021012411300000000IBF29569000000000000PAGMASSCIO1000000            02826799    00        00 000000000201702211142140000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0044400000000õ02õ0õ0000000õ   õ00õ                                                                                õ6273õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448139608760õ0õ0000000õ   õ00õ                                                                                õ3652õ                                        õCONSERVE EL TICKET COMO COMPROBANTE     õ õ  õ            õ448139940709õ";
        respuetaOKyERRORTrama = "200000000000P04HTML00099000103FREEUSER  XXXXXXXX00507108000000102016111116584900000000IBF26804000000000000PAGMASSCIO1000000            00011903    00        00 000000000201611221108090000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0044400000000õ02õ0õ0000000õ   õ00õ                                                                                õ6265õ                                        õCONSERVE EL TICKET COMO COMPROBANTE     õ õ  õ            õ583694328435õ1õ0000058õBANõ01õINSUFIC. SALDO DISPONIBLE (< O = A 0)                                           õ    õ                                        õ                                        õ õ  õ            õ            õ";
        respuestaError = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF25276000000000000PAGMASSCIO1000000            00011903    00        00 000000000201609191457590000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0036810000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";
        respuestaErrorCaso0 = "200000000000P04HTML00099000103FREEUSER  ********00208136000000332017021012411300000000IBF29569000000000000PAGMASSCIO1000000            02826799    00        00 000000000201702211142140000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0044400000000õ02õ0õ0000000õ   õ00õ                                                                                õ6273õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448139608760õ0õ0000000õ   õ00õ                                                                                õ3652õ                                        õCONSERVE EL TICKET COMO COMPROBANTE     õ õ  õ            õ448139940709õ";
        respuestaErrorCaso1 = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF22255000000000000PAGMASSCIO1000000            00276937    00        00 000000000201702141834410000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0036810000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";
        respuestaErrorCaso2 = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0253120000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ10õ0õ0000000õ   õ00õ                                                                                õ1348õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141155207õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141483775õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141787715õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448142138162õ1õ0000060õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000060õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000047õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000047õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000047õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448145060833õ";
        respuestaErrorCaso3 = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0253120000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ10õ0õ0000000õ   õ00õ                                                                                õ1348õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141155207õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141483775õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141787715õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448142138162õ1õ0000073õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000072õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000063õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000058õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000075õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448145060833õ";
        respuestaErrorCaso4 = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0253120000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ10õ0õ0000000õ   õ00õ                                                                                õ1348õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141155207õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141483775õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448141787715õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448142138162õ1õ0000073õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000072õBANõ01õHA LLEGADO AL LIMITE DIARIO DE TRANSACC.                                        õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000069õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000056õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000056õBANõ01õFACTURA INEXISTENTE                                                             õ    õ                                        õ                                        õ õ  õ            õ            õ0õ0000000õ   õ00õ                                                                                õ4666õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448145060833õ";
        respuestaError20000000 = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0253120000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ0õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ";
        respuestaError41 = "200000000000P04HTML0009900010303348599  ********00056010000000202017102612175500000000IBF2A6KZ000000000000PAGMASSCIO1000000            03348599    00        00 000000000201710261217430000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ1õ0000041õBANõ01õNO COINCIDEN LOS DATOS DE LA DEUDA                                              õ    õ                                        õ                                        õ õ  õ            õ            õ";
        //respuestaErrorTimeOut = "200000000000P04HTML0009900010300934972  ********00841433000000252018050511244400000000IBF16034000000000000PAGMASSCIO1000000            00934972    00        00 000000000201805051124280000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ02õ1õ0000004õBHHõ01õERROR EN RETORNO PROGRAMA ZA1CHTH                                               õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000004õBHHõ01õERROR EN RETORNO PROGRAMA ZA1CHTH                                               õ    õ                                        õ                                        õ õ  õ            õ            õ";
        respuestaErrorTimeOut = "200000000000P04HTML0009900010300934972  ********00841433000000252018050511244400000000IBF16034000000000000PAGMASSCIO1000000            00934972    00        00 000000000201805051124280000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ02õ1õ0000004õBHHõ01õERROR EN RETORNO PROGRAMA ZA1CHTH                                               õ    õ                                        õ                                        õ õ  õ            õ            õ1õ0000004õBHHõ01õERROR EN RETORNO PROGRAMA ZA1CHTH                                               õ    õ                                        õ                                        õ õ  õ            õ            õ";
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
     * Ejecutar pago multiple test caso 3: multiples errores.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestCaso3() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        PagoPMC pago_3 = new PagoPMC();
        PagoPMC pago_4 = new PagoPMC();
        PagoPMC pago_5 = new PagoPMC();
        PagoPMC pago_6 = new PagoPMC();
        PagoPMC pago_7 = new PagoPMC();
        PagoPMC pago_8 = new PagoPMC();
        PagoPMC pago_9 = new PagoPMC();
        PagoPMC pago_10 = new PagoPMC();

        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pago_3.setSucursalCuenta("000");
        pago_3.setNumeroCuenta("3607390");
        pago_3.setMonto("10000");
        pago_4.setSucursalCuenta("000");
        pago_4.setNumeroCuenta("3607390");
        pago_4.setMonto("10000");
        pago_5.setSucursalCuenta("000");
        pago_5.setNumeroCuenta("3607390");
        pago_5.setMonto("10000");
        pago_6.setSucursalCuenta("000");
        pago_6.setNumeroCuenta("3607390");
        pago_6.setMonto("10000");
        pago_7.setSucursalCuenta("000");
        pago_7.setNumeroCuenta("3607390");
        pago_7.setMonto("10000");
        pago_8.setSucursalCuenta("000");
        pago_8.setNumeroCuenta("3607390");
        pago_8.setMonto("10000");
        pago_9.setSucursalCuenta("000");
        pago_9.setNumeroCuenta("3607390");
        pago_9.setMonto("10000");
        pago_10.setSucursalCuenta("000");
        pago_10.setNumeroCuenta("3607390");
        pago_10.setMonto("10000");

        pagos.add(pago_1);
        pagos.add(pago_2);
        pagos.add(pago_3);
        pagos.add(pago_4);
        pagos.add(pago_5);
        pagos.add(pago_6);
        pagos.add(pago_7);
        pagos.add(pago_8);
        pagos.add(pago_9);
        pagos.add(pago_10);

        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu pago de {0} por {1} debido a que ya ha sido realizado anteriormente.</p> ");

        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorCaso3);

        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Ejecutar pago multiple test caso 4: multiples errores.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestCaso4() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        PagoPMC pago_3 = new PagoPMC();
        PagoPMC pago_4 = new PagoPMC();
        PagoPMC pago_5 = new PagoPMC();
        PagoPMC pago_6 = new PagoPMC();
        PagoPMC pago_7 = new PagoPMC();
        PagoPMC pago_8 = new PagoPMC();
        PagoPMC pago_9 = new PagoPMC();
        PagoPMC pago_10 = new PagoPMC();

        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pago_3.setSucursalCuenta("000");
        pago_3.setNumeroCuenta("3607390");
        pago_3.setMonto("10000");
        pago_4.setSucursalCuenta("000");
        pago_4.setNumeroCuenta("3607390");
        pago_4.setMonto("10000");
        pago_5.setSucursalCuenta("000");
        pago_5.setNumeroCuenta("3607390");
        pago_5.setMonto("10000");
        pago_6.setSucursalCuenta("000");
        pago_6.setNumeroCuenta("3607390");
        pago_6.setMonto("10000");
        pago_7.setSucursalCuenta("000");
        pago_7.setNumeroCuenta("3607390");
        pago_7.setMonto("10000");
        pago_8.setSucursalCuenta("000");
        pago_8.setNumeroCuenta("3607390");
        pago_8.setMonto("10000");
        pago_9.setSucursalCuenta("000");
        pago_9.setNumeroCuenta("3607390");
        pago_9.setMonto("10000");
        pago_10.setSucursalCuenta("000");
        pago_10.setNumeroCuenta("3607390");
        pago_10.setMonto("10000");

        pagos.add(pago_1);
        pagos.add(pago_2);
        pagos.add(pago_3);
        pagos.add(pago_4);
        pagos.add(pago_5);
        pagos.add(pago_6);
        pagos.add(pago_7);
        pagos.add(pago_8);
        pagos.add(pago_9);
        pagos.add(pago_10);

        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu pago de {0} por {1} debido a que ya ha sido realizado anteriormente.</p> ");

        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorCaso4);

        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Ejecutar pago multiple test para caso 2.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestCaso2() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        PagoPMC pago_3 = new PagoPMC();
        PagoPMC pago_4 = new PagoPMC();
        PagoPMC pago_5 = new PagoPMC();
        PagoPMC pago_6 = new PagoPMC();
        PagoPMC pago_7 = new PagoPMC();
        PagoPMC pago_8 = new PagoPMC();
        PagoPMC pago_9 = new PagoPMC();
        PagoPMC pago_10 = new PagoPMC();

        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pago_3.setSucursalCuenta("000");
        pago_3.setNumeroCuenta("3607390");
        pago_3.setMonto("10000");
        pago_4.setSucursalCuenta("000");
        pago_4.setNumeroCuenta("3607390");
        pago_4.setMonto("10000");
        pago_5.setSucursalCuenta("000");
        pago_5.setNumeroCuenta("3607390");
        pago_5.setMonto("10000");
        pago_6.setSucursalCuenta("000");
        pago_6.setNumeroCuenta("3607390");
        pago_6.setMonto("10000");
        pago_7.setSucursalCuenta("000");
        pago_7.setNumeroCuenta("3607390");
        pago_7.setMonto("10000");
        pago_8.setSucursalCuenta("000");
        pago_8.setNumeroCuenta("3607390");
        pago_8.setMonto("10000");
        pago_9.setSucursalCuenta("000");
        pago_9.setNumeroCuenta("3607390");
        pago_9.setMonto("10000");
        pago_10.setSucursalCuenta("000");
        pago_10.setNumeroCuenta("3607390");
        pago_10.setMonto("10000");

        pagos.add(pago_1);
        pagos.add(pago_2);
        pagos.add(pago_3);
        pagos.add(pago_4);
        pagos.add(pago_5);
        pagos.add(pago_6);
        pagos.add(pago_7);
        pagos.add(pago_8);
        pagos.add(pago_9);
        pagos.add(pago_10);

        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu pago de {0} por {1} debido a que ya ha sido realizado anteriormente.</p> ");

        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorCaso2);

        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Invocar pago mis cuentas OK test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasOKTest() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("    ");
        pago_1.setIdentificacion("0000000000123456789");
        pago_1.setTipoMonto("3");
        pago_1.setTipoSeleccion("F");
        pago_1.setNumeroFactura("00000123456788990456");
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String tramaResponse = "200000000000P04HTML00099000103FREEUSER  ********00208136000000332017021012411300000000IBF29569000000000000PAGMASSCIO1000000            02826799    00        00 000000000201702211142140000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0044400000000õ01õ0õ0000000õ   õ00õ                                                                                õ6273õ                                        õCOMPROBANTE VALIDO DE PAGO. CONSERVELO. õ õ  õ            õ448139608760õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(tramaResponse);

        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        Assert.assertEquals("6273", pagoPMC.getNumeroControl());
    }

    /**
     * Invocar pago mis cuentas test para Error.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasTestError10000000() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaError);

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        // DAOException is thrown.
    }

    /**
     * Invocar pago mis cuentas test error 20000000.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasTestError41() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("    ");
        pago_1.setIdentificacion("0000000000123456789");
        pago_1.setTipoMonto("3");
        pago_1.setTipoSeleccion("F");
        pago_1.setNumeroFactura("00000123456788990456");
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_1.setEmpresaNombreFantasia("VISA");
        pago_1.setMontoMensajeFeedback("12,00");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String texto = "<p>No pudimos realizar tu pago de {0} por {1} debido a que el importe ingresado es inválido.</p>";

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(texto);

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaError41);
        when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_IMPORTE_INCORRECTO))
                .thenReturn(mensaje);

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        Assert.assertNotNull(respuesta);

        Assert.assertEquals(TipoError.IMPORTE_ERRONEO, respuesta.getTipoError());
    }

    /**
     * Invocar pago mis cuentas test error 1.2
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasTestError1() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String trama = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF25276000000000000PAGMASSCIO1000000            00011903    00        00 000000000201609191457590000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ003681õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(trama);

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        // DAOException is thrown.
    }

    /**
     * Invocar pago mis cuentas test error contiene 1.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasTestErrorContiene1() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String trama = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF25276000000000000PAGMASSCIO1000000            00011903    00        00 000000000201609191457590000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0036800010000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(trama);

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        // DAOException is thrown.
    }

    /**
     * Invocar pago mis cuentas test error 20000000.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasTestError20000000() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("    ");
        pago_1.setIdentificacion("0000000000123456789");
        pago_1.setTipoMonto("3");
        pago_1.setTipoSeleccion("F");
        pago_1.setNumeroFactura("00000123456788990456");
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100"))))
                .thenReturn(respuestaError20000000);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO_NO_HUBO_PAGO,
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>"));

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        Assert.assertNotNull(respuesta);
    }

    /**
     * Invocar pago mis cuentas test error 2.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasTestError2() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("    ");
        pago_1.setIdentificacion("0000000000123456789");
        pago_1.setTipoMonto("3");
        pago_1.setTipoSeleccion("F");
        pago_1.setNumeroFactura("00000123456788990456");
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String trama = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ025312õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ0õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ";
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(trama);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO_NO_HUBO_PAGO,
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>"));

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        Assert.assertNotNull(respuesta);
    }

    /**
     * Invocar pago mis cuentas test error contiene 2.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasTestErrorContiene2() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("    ");
        pago_1.setIdentificacion("0000000000123456789");
        pago_1.setTipoMonto("3");
        pago_1.setTipoSeleccion("F");
        pago_1.setNumeroFactura("00000123456788990456");
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String trama = "200000000000P04HTML00099000103FREEUSER  ********00954127000000782017021410584800000000IBF29571000000000000PAGMASSCIO1000000            00743315    00        00 000000000201702211142190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0253100020000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ0õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ0õ";
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(trama);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO_NO_HUBO_PAGO,
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>"));

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        Assert.assertNotNull(respuesta);
    }

    /**
     * Invocar pago mis cuentas test error no conocido.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasTestErrorNoConocido() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        String trama = "200000000000P04HTML00099000104FREEUSER  XXXXXXXX00000001000000002016071515505100000000IBF25276000000000000PAGMASSCIO1000000            00011903    00        00 000000000201609191457590000000000                            000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX000000000000000XXXX00DHõ0036800-40000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õOPERACION NO REALIZADA                                                          õ";

        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(trama);

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        // DAOException is thrown.
    }

    /**
     * Invocar pago mis cuentas test para iatxException.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasTestIatxException() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        // When
        given(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).willThrow(new IatxException());

        PagoPMC respuesta = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);

        // Then
        // DAOException is thrown.
    }

    /**
     * Invocar pago mis cuentas con TCOK test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCOKTest() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00076585000000242018061114430600000000IBF201RG000000000000PAGMASSCRE1000000            00093098    00        00 000000000201806111442490000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0266600000000õ01õ0õ0000000õ   õ00õ                                                                                õ1234õ                                        õcon leyenda                             õ õ  õ            õ000000002222õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(true, pagoPMC.getRespuestaOK());
    }

    /**
     * Invocar pago mis cuentas con TC error 20000000 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError20000000Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
    }

    /**
     * Invocar pago mis cuentas con TC error empieza con 2 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCErrorEmpiezaCon2Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058728076543õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
    }

    /**
     * Invocar pago mis cuentas con TC error 2 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError2Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ005872õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
    }

    /**
     * Invocar pago mis cuentas con TC error 10000000 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasConTCError10000000Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058710000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
    }

    /**
     * Invocar pago mis cuentas con TC error empieza con 1 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasConTCErrorEmpiezaCon1Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058710074563õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
    }

    /**
     * Invocar pago mis cuentas con TC error 1 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasConTCError1Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ005871õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000000õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
    }

    /**
     * Invocar pago mis cuentas con TC error validacion regex test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasConTCErrorValidacionRegexTest() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF ");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("1944061A");

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
    }

    /**
     * Invocar pago mis cuentas con TC error 30000000 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void invocarPagoMisCuentasConTCError30000000Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058730000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000057õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
    }

    /**
     * Invocar pago mis cuentas con TC error 41 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError41Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000041õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1666",
                "<p>No pudimos realizar tu pago de {0} por {1} debido a que el importe que ingresaste es inválido.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.IMPORTE_ERRONEO, pagoPMC.getTipoError());
        Assert.assertEquals(
                "<p>No pudimos realizar tu pago de UNICEF por $ 20,00 debido a que el importe que ingresaste es inválido.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 50 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError50Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000050õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.USUARIO_O_TARJETA_INVALIDA, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 52 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError52Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000052õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.TARJETA_INHABILITADA_O_INEXISTENTE, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 53 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError53Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000053õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.CLAVEINVALIDA, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 57 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError57Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000057õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.ERROR_REINTENTOS_OPERACION, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 68 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError68Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000068õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.ERROR_REINTENTOS_OPERACION, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 58 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError58Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000058õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1745",
                "<p> No disponés de saldo suficiente en la tarjeta seleccionada para realizar esta operación.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.ERROR_REINTENTOS_OPERACION, pagoPMC.getTipoError());
        Assert.assertEquals(
                "<p> No disponés de saldo suficiente en la tarjeta seleccionada para realizar esta operación.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 61 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError61Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000061õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1298",
                "<p>No se puede realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> debido a que superaste el límite de pagos diarios. Por favor, intentá nuevamente mañana.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.ERROR_LIMITE_MAYOR_NUEVO_PAGO, pagoPMC.getTipoError());
        Assert.assertEquals(
                "<p>No se puede realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b> debido a que superaste el límite de pagos diarios. Por favor, intentá nuevamente mañana.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 65 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError65Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000065õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.SIN_PAGOS_INFORMADOS, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 72 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError72Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000072õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.TIMEOUT, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 74 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError74Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000074õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.TIMEOUT, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Invocar pago mis cuentas con TC error 88 test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void invocarPagoMisCuentasConTCError88Test() throws IatxException, DAOException {
        // Given
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        pago_1.setCodigoEmpresa("UNCF");
        pago_1.setEmpresaNombreFantasia("UNICEF");
        pago_1.setIdentificacion("111111             ");
        pago_1.setMoneda("ARS");
        pago_1.setMonto("00000000002000");
        pago_1.setMontoMensajeFeedback("$ 20,00");
        pago_1.setNroTarjeta("00004050710032218971");
        pago_1.setNumeroCuenta("000017024994");
        pago_1.setNumeroFactura("                    ");
        pago_1.setOperacionDescripcion("pago");
        pago_1.setReintentar(Boolean.FALSE);
        pago_1.setSucursalCuenta("0037");
        pago_1.setTipoCuenta("07");
        pago_1.setTipoCuentaTC(7);
        pago_1.setTipoMonto("0");
        pago_1.setTipoSeleccion("I");
        pagos.add(pago_1);
        Cliente cliente = new Cliente();
        cliente.setApellido1("GONZALEZ            ");
        cliente.setNombre("GERMAN             ");
        cliente.setSexo("H");
        cliente.setFechaNacimiento("19440612");
        String tramaResponse = "200000000000P04HTML0009900010340MROQ27  ********00015585000001042018053114463100000000IBF200S9000000000000PAGMASSCRE1000000            40274627    00        00 000000000201805311446120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0058720000000õZAEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õHAY PAGO(S) SIN EFECTUAR                                                        õ01õ01õ0000088õ   õ00õ                                                                                õ    õ                                        õ                                        õ õ  õ            õ            õ";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCRE", "100")))).thenReturn(tramaResponse);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        PagoPMC pagoPMC = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);

        // Expected
        Assert.assertNotNull(pagoPMC);
        Assert.assertEquals(false, pagoPMC.getRespuestaOK());
        Assert.assertEquals(TipoError.ERROR_GENERICO, pagoPMC.getTipoError());
        Assert.assertEquals("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ 20,00</b>.</p>",
                pagoPMC.getMensaje());
    }

    /**
     * Ejecutar pago multiple test para caso 0, OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestCaso0() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pagos.add(pago_1);
        pagos.add(pago_2);
        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu pago de {0} por {1} debido a que ya ha sido realizado anteriormente.</p> ");
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorCaso0);
        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }

    /**
     * Ejecutar pago multiple test para caso 1, Error general.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestCaso1() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pagos.add(pago_1);
        pagos.add(pago_2);
        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu pago de {0} por {1} debido a que ya ha sido realizado anteriormente.</p> ");
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorCaso1);
        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }
    
    /**
     * Ejecutar pago multiple test para caso 1, Error general.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarPagoMultipleTestTimeOut() throws IatxException, DAOException {
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        PagoPMC pago_1 = new PagoPMC();
        PagoPMC pago_2 = new PagoPMC();
        pago_1.setSucursalCuenta("000");
        pago_1.setNumeroCuenta("3607390");
        pago_1.setMonto("10000");
        pago_2.setSucursalCuenta("000");
        pago_2.setNumeroCuenta("3607390");
        pago_2.setMonto("10000");
        pagos.add(pago_1);
        pagos.add(pago_2);
        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("PAGMASSCIO", "100")))).thenReturn(respuestaErrorTimeOut);
        PagoMultipleDTO respuesta = pagoMisCuentasDAO.ejecutarPagoMultiple(pagos, cliente);
        Assert.assertNotNull(respuesta);
    }
}
