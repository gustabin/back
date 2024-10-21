package ar.com.santanderrio.obp.servicios.pagos.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagoTarjetaCreditoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoTarjetaCreditoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoMonedaPagoTCEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaAdheridaDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaPago;

/**
 * The Class PagoTarjetaCreditoBOTest.
 *
 * @author marcelo.ruiz
 */

@RunWith(MockitoJUnitRunner.class)
public class PagoTarjetaCreditoBOTest {

    /** The pago tarjeta credito BO. */
    @InjectMocks
    private PagoTarjetaCreditoBOImpl pagoTarjetaCreditoBO;

    /** The Moc kpago tarjeta credito DAO. */
    @Mock
    private PagoTarjetaCreditoDAO pagoTarjetaCreditoDAO;

    /** The mock deuda pago automatico DAO. */
    @Mock
    private DeudaPagoAutomaticoDAO deudaPagoAutomaticoDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The legal DAO. */
    @Mock
    private LegalDAO legalDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The session parametros. */
//    @InjectMocks
    @Spy
    private SesionParametros sessionParametros = new SesionParametros();
    


    @Mock
    private DatosTarjetaDAO datosTarjetaDAO;

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private MensajeBO mensajeBO;

    // @Mock
    // private ContadorIntentos contadorIntentos;

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta OK de comprobante pago
     * tarjeta.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaOKComprobantePagoTarjeta()
            throws DAOException, ServiceException {
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        pago.setCodigoMoneda(TipoMonedaPagoTCEnum.PESOS.getValue());
        String nroComprobante = "15380851";

        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenReturn(nroComprobante);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeOKPagoMinimo());
        Mockito.when(legalDAO.obtenerLegal(Matchers.anyString())).thenReturn(obtenerLegalOKSEUO());

        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.OK, rtaPago.getEstadoRespuesta());
        Assert.assertNull(rtaPago.getItemsMensajeRespuesta());
        Assert.assertEquals("<p>Conserve este ticket como</p><p>comprobante S.E.U.O.</p>",
                rtaPago.getRespuesta().getLegalesSEO());
        Assert.assertEquals(
                "<p>¡Listo!</p> <p>El <b>Pago Mínimo</b> de tu tarjeta <b>VISA XXXX-1922</b> por <b>$32.140,00</b> se realizó con éxito.</p>",
                rtaPago.getRespuesta().getMensaje());
        Assert.assertEquals("15380851", rtaPago.getRespuesta().getNroComprobante());
        Assert.assertNull(rtaPago.getRespuesta().getNroComprobanteStopDebit());

    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta OK del comprobante pago
     * tarjeta sin el legal del SEUO.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaOKComprobantePagoTarjetaSinLegalSEUO()
            throws DAOException, ServiceException {
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        pago.setCodigoMoneda(TipoMonedaPagoTCEnum.PESOS.getValue());
        String nroComprobante = "15380851";

        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenReturn(nroComprobante);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeOKPagoMinimo());
        Mockito.when(legalDAO.obtenerLegal(Matchers.anyString()))
                .thenThrow(new DAOException("Falló el legal del SEUO 1005."));

        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.OK, rtaPago.getEstadoRespuesta());
        Assert.assertNull(rtaPago.getItemsMensajeRespuesta());
        Assert.assertNull(rtaPago.getRespuesta().getLegalesSEO());
        Assert.assertEquals(
                "<p>¡Listo!</p> <p>El <b>Pago Mínimo</b> de tu tarjeta <b>VISA XXXX-1922</b> por <b>$32.140,00</b> se realizó con éxito.</p>",
                rtaPago.getRespuesta().getMensaje());
        Assert.assertEquals("15380851", rtaPago.getRespuesta().getNroComprobante());
        Assert.assertNull(rtaPago.getRespuesta().getNroComprobanteStopDebit());

    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por saldo
     * insuficiente en la cuenta unica.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORSaldoInsuficienteCtaUnica()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1178",
                "<p> No disponés de saldo suficiente en la cuenta seleccionada para realizar esta operación.</p> <p>");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10002122"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(mensaje.getMensaje(), rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_SALDO_INSUFICIENTE", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por saldo
     * insuficiente en la cuenta corriente.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORSaldoInsuficienteCtaCorriente()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1178",
                "<p> No disponés de saldo suficiente en la cuenta seleccionada para realizar esta operación.</p> <p>");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10000515"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(mensaje.getMensaje(), rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_SALDO_INSUFICIENTE", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por dia no
     * laborable, con Stop Debit y Sin Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORDiaNoLaborableConStopDebitYSinPagoProgramado()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1176",
                "<p>No pudimos {0} el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.<p>");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10000031"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos realizar el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.<p>",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_FECHA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por dia no
     * laborable, sin Stop Debit y con Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORDiaNoLaborableSinStopDebitYConPagoProgramado()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCConPagoProgramadoSinStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1175",
                "No pudimos {0} el <b>pago</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10000031"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "No pudimos programar el <b>pago</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_FECHA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por dia no
     * laborable, con Stop Debit y con Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORDiaNoLaborableConStopDebitYConPagoProgramado()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCConPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1176",
                "<p>No pudimos {0} el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.<p>");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10000031"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>No pudimos programar el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.<p>",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_FECHA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR por dia no
     * laborable, sin Stop Debit y Sin Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORDiaNoLaborableSinStopDebitYSinPagoProgramado()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoSinStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1175",
                "No pudimos {0} el <b>pago</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10000031"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "No pudimos realizar el <b>pago</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_FECHA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR generico, sin
     * reintentos, sin Stop Debit y con Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     * @throws SecurityException
     *             the security exception
     * @throws NoSuchMethodException
     *             the no such method exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORGenericoTresReintentosConPagoProgramadoSinStopDebit()
            throws DAOException, ServiceException, SecurityException, NoSuchMethodException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCConPagoProgramadoSinStopDebit();
        ContadorIntentos cont = Mockito.mock(ContadorIntentos.class);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1175",
                "No pudimos {0} el <b>pago</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error Generico", "-1"));
        Mockito.doReturn(cont).when(sessionParametros).getContador();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "No pudimos programar el <b>pago</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_PAGO_TARJETAS_GENERAL", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERROROperacionInhabilitadaCuentaAlfaNuncaOpero()
            throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("410097",
                "<p><strong>Para operar en dólares es necesario que completes la apertura de tu cuenta.</strong></p><p>Cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de tu cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");
        
        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10009079"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);
        
        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(mensaje.getMensaje(), rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_OPERACION_INHABILITADA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERROROperacionInhabilitadaCuentaAlfa()
        throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("410098",
                "<p><strong>Te recordamos que es requisito normativo que completes la apertura de tu cuenta para seguir operando en dólares de manera habitual. </strong></p><p>Por tal motivo, cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");
        
        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error", "10009080"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);
        
        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(mensaje.getMensaje(), rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_OPERACION_INHABILITADA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Dados los datos del pago de la tarjeta de credito y el cliente, cuando se
     * invoca al metodo "pagar", obtengo una respuesta de ERROR generico, con
     * reintentos, sin Stop Debit y con Pago Programado.
     *
     * @author florencia.n.martinez
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     * @throws SecurityException
     *             the security exception
     * @throws NoSuchMethodException
     *             the no such method exception
     */
    @Test
    public void dadosDatosYClienteCuandoInvocaPagarObtengoRespuestaERRORGenericoConReintentosConPagoProgramadoSinStopDebit()
            throws DAOException, ServiceException, SecurityException, NoSuchMethodException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCConPagoProgramadoSinStopDebit();
        ContadorIntentos cont = Mockito.mock(ContadorIntentos.class);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1175",
                "No pudimos {0} el <b>pago</b> de tu tarjeta <b>{1}</b>. Por favor, intentá nuevamente en unos minutos.");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("Mensaje Error Generico", "-1"));
        Mockito.doReturn(cont).when(sessionParametros).getContador();
        Mockito.when(sessionParametros.getContador().permiteReintentar()).thenReturn(Boolean.TRUE);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.pagar(pago, cliente);

        // Expected
        Assert.assertNotNull(rtaPago);
        Assert.assertEquals(EstadoRespuesta.WARNING, rtaPago.getEstadoRespuesta());
        Assert.assertEquals(
                "No pudimos programar el <b>pago</b> de tu tarjeta <b>VISA XXXX-1922</b>. Por favor, intentá nuevamente en unos minutos.",
                rtaPago.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_REINTENTAR_PAGO_TARJETA", rtaPago.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertNull(rtaPago.getRespuesta());
    }

    /**
     * Programar pago.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void programarPago() throws DAOException, ServiceException {
        // Given
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCConPagoProgramadoSinStopDebit();
        String nroComprobante = "1234353436";
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1173",
                "</p>El <b>{0}</b> de tu tarjeta <b>{1}</b> por <b>{2}</b> y el <b>Stop Debit</b> se {3}.</p>");

        // When
        Mockito.when(pagoTarjetaCreditoDAO.programarPago(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenReturn(nroComprobante);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<ComprobantePagoTarjeta> rtaPago = pagoTarjetaCreditoBO.programarPago(pago, cliente);

        // Expcted
        Assert.assertNotNull(rtaPago);
    }

    /**
     * Pagar error time out.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void pagarErrorTimeOut() throws DAOException, ServiceException {
        Cliente cliente = new Cliente();
        DatosPagoTC pago = obtenerDatosPagoTCSinPagoProgramadoConStopDebit();

        Mockito.when(pagoTarjetaCreditoDAO.pagar(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("TIME_OUT", "0000001"));
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(obtenerMensajeTimeOutConStopDebit());

        Respuesta<ComprobantePagoTarjeta> rtaTimeOut = pagoTarjetaCreditoBO.pagar(pago, cliente);

        Assert.assertNotNull(rtaTimeOut);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaTimeOut.getEstadoRespuesta());
        Assert.assertEquals(
                "<p>¡Lo sentimos! <p> <p>Ocurrió un error al intentar realizar el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>VISA XXXX-1922</b>. Intentá nuevamente en unos minutos.<p>",
                rtaTimeOut.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_TIME_OUT_PAGO_TARJETA", rtaTimeOut.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Obtener mensaje time out con stop debit.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeTimeOutConStopDebit() {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1176");
        mensaje.setMensaje(
                "<p>¡Lo sentimos! <p> <p>Ocurrió un error al intentar {0} el <b>pago</b> y <b>Stop Debit</b> de tu tarjeta <b>{1}</b>. Intentá nuevamente en unos minutos.<p>");
        return mensaje;
    }

    /**
     * Programar pago error time out.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void programarPagoErrorTimeOut() throws DAOException, ServiceException {
        Cliente cliente = new Cliente();
        DatosPagoTC pago = new DatosPagoTC();

        Mockito.when(pagoTarjetaCreditoDAO.programarPago(Matchers.any(Cliente.class), Matchers.any(DatosPagoTC.class)))
                .thenThrow(new DAOException("TIME_OUT", "0000001"));

        Respuesta<ComprobantePagoTarjeta> rtaTimeOut = pagoTarjetaCreditoBO.programarPago(pago, cliente);

        Assert.assertNotNull(rtaTimeOut);
        Assert.assertEquals(EstadoRespuesta.ERROR, rtaTimeOut.getEstadoRespuesta());
        Assert.assertEquals("<p>La operación expiro por falta de actividad</p>",
                rtaTimeOut.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals("ERROR_TIME_OUT_PAGO_TARJETA", rtaTimeOut.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Obtener deudas con debito automatico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDeudasConDebitoAutomatico() throws DAOException, BusinessException {
        Cliente cliente = new Cliente();
        Cuenta tarjetaActiva = new Cuenta();
        List<DebitoAutomatico> lista = new ArrayList<DebitoAutomatico>();
        Mockito.when(deudaPagoAutomaticoDAO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(lista);

        List<DebitoAutomatico> rta = pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente, tarjetaActiva);

        Assert.assertNotNull(rta);
    }

    /**
     * Obtener deudas con debito automatico error.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDeudasConDebitoAutomaticoError() throws DAOException {
        Cliente cliente = new Cliente();
        Cuenta tarjetaActiva = new Cuenta();
        Mockito.when(deudaPagoAutomaticoDAO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenThrow(new DAOException("ERROR", "ERROR"));

        try {
            pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(cliente, tarjetaActiva);
        } catch (BusinessException e) {
            Assert.assertEquals("ERROR", e.getMessage());
        }
    }

    @Test
    public void obtenerDetalleTarjetaPagoProgramado() throws BusinessException, DAOException {
        // Given
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        DatosTarjeta datosTarjeta = new DatosTarjeta();

        datosTarjeta.setFechaPagoProgramado(new Date());
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Mockito.when(datosTarjetaDAO.obtenerPagoProgramado(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.any(Date.class))).thenReturn(datosTarjeta);
        // Then
        DetalleTarjetaPago detalle = pagoTarjetaCreditoBO.obtenerDetalleTarjetaPago("XXXX-1234", new Date(),
                TipoDePagoEnum.PAGOPROGRAMADO);
        // Expected
        Assert.assertNotNull(detalle);
    }

    @Test
    public void obtenerDetalleTarjetaPagoPuntual() throws BusinessException, DAOException {
        // Given
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        DatosTarjeta datosTarjeta = new DatosTarjeta();

        datosTarjeta.setFechaPagoProgramado(new Date());
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datosTarjeta));
        // Then
        DetalleTarjetaPago detalle = pagoTarjetaCreditoBO.obtenerDetalleTarjetaPago("XXXX-1234", new Date(),
                TipoDePagoEnum.TARJETAPAGOPUNTUAL);
        // Expected
        Assert.assertNotNull(detalle);
    }

    @Test
    public void obtenerDetalleTarjetaDebitoAutomatico() throws BusinessException, DAOException {
        // Given
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        DatosTarjeta datosTarjeta = new DatosTarjeta();
        CuentaAdheridaDebitoAutomatico cuentaAdheridaDebitAutomatico = new CuentaAdheridaDebitoAutomatico();

        datosTarjeta.setFechaPagoProgramado(new Date());
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(datosTarjetaDAO.obtenerDatosTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.anyBoolean()))
                .thenReturn(new DatosTarjetaBuilder(datosTarjeta));
        Mockito.when(deudaPagoAutomaticoDAO.obtenerCuentaAdheridaDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(cuentaAdheridaDebitAutomatico);
        // Then
        DetalleTarjetaPago detalle = pagoTarjetaCreditoBO.obtenerDetalleTarjetaPago("XXXX-1234", new Date(),
                TipoDePagoEnum.TARJETADEBITOAUTOMATICOPAGOMINIMO);
        // Expected
        Assert.assertNotNull(detalle);
    }

    /**
     * Obtener legal OKSEUO.
     *
     * @return the string
     */
    private String obtenerLegalOKSEUO() {
        return "<p>Conserve este ticket como</p><p>comprobante S.E.U.O.</p>";
    }

    /**
     * Obtener mensaje OK pago minimo.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeOKPagoMinimo() {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1173");
        mensaje.setMensaje("<p>¡Listo!</p> <p>El <b>{0}</b> de tu tarjeta <b>{1}</b> por <b>{2}</b> se {3}.</p>");
        return mensaje;
    }

    /**
     * Obtener datos pago TC sin pago programado con stop debit.
     *
     * @return the datos pago TC
     */
    private DatosPagoTC obtenerDatosPagoTCSinPagoProgramadoConStopDebit() {
        DatosPagoTC datosPago = new DatosPagoTC();
        datosPago.setTipoPagoTC("00");
        datosPago.setPagoProgramado(Boolean.FALSE);
        datosPago.setImporteMinimo("32.140,00");
        datosPago.setNroTarjetaFormateado("VISA XXXX-1922");
        datosPago.setStopDebit(Boolean.TRUE);
        return datosPago;
    }

    /**
     * Obtener datos pago TC sin pago programado sin stop debit.
     *
     * @return the datos pago TC
     */
    private DatosPagoTC obtenerDatosPagoTCSinPagoProgramadoSinStopDebit() {
        DatosPagoTC datosPago = new DatosPagoTC();
        datosPago.setTipoPagoTC("00");
        datosPago.setPagoProgramado(Boolean.FALSE);
        datosPago.setImporteMinimo("32.140,00");
        datosPago.setNroTarjetaFormateado("VISA XXXX-1922");
        datosPago.setStopDebit(Boolean.FALSE);
        return datosPago;
    }

    /**
     * Obtener datos pago TC con pago programado con stop debit.
     *
     * @return the datos pago TC
     */
    private DatosPagoTC obtenerDatosPagoTCConPagoProgramadoConStopDebit() {
        DatosPagoTC datosPago = new DatosPagoTC();
        datosPago.setTipoPagoTC("00");
        datosPago.setPagoProgramado(Boolean.TRUE);
        datosPago.setImporteMinimo("32.140,00");
        datosPago.setNroTarjetaFormateado("VISA XXXX-1922");
        datosPago.setStopDebit(Boolean.TRUE);
        return datosPago;
    }

    /**
     * Obtener datos pago TC con pago programado sin stop debit.
     *
     * @return the datos pago TC
     */
    private DatosPagoTC obtenerDatosPagoTCConPagoProgramadoSinStopDebit() {
        DatosPagoTC datosPago = new DatosPagoTC();
        datosPago.setTipoPagoTC("00");
        datosPago.setPagoProgramado(Boolean.TRUE);
        datosPago.setImporteMinimo("32.140,00");
        datosPago.setNroTarjetaFormateado("VISA XXXX-1922");
        datosPago.setStopDebit(Boolean.FALSE);
        return datosPago;
    }
}
