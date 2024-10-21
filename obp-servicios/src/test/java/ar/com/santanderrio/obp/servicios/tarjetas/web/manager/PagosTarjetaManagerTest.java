package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cotizacion.bo.CotizacionBO;
import ar.com.santanderrio.obp.servicios.cotizacion.dto.CotizacionDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosTarjetaMock;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaCreditoView;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ComprobantePagoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReintentarMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.mock.ComprobantePagoTarjetaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.PagosTarjetaManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaInfoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.PagoTarjetaView;

/**
 * The Class PagosTarjetaManagerTest.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class PagosTarjetaManagerTest {

    /** The pagos tarjeta manager. */
    @InjectMocks
    private PagosTarjetaManagerImpl pagosTarjetaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The pagos pendientes BO. */
    @Mock
    private PagosPendientesBO pagosPendientesBO;

    /** The cotizacion BO. */
    @Mock
    private CotizacionBO cotizacionBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The contratos BO. */
    @Mock
    private ContratoBO contratosBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The pago tarjeta credito BO. */
    @Mock
    private PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

    /** The stop debit tarjetas BO. */
    @Mock
    private StopDebitTarjetasBO stopDebitTarjetasBO;

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Obtener datos iniciales pago tarjeta OK.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaOK() throws BusinessException, DAOException {

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
        Mockito.when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        Mockito.when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeStopDebit());
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        Mockito.when(cuentaManager.getCuentasSaldo()).thenReturn(obtenerRespuestaOKCuentasView());
        Mockito.when(contratosBO.buscarContratoAceptadoOld(Matchers.anyString(), Matchers.anyString(),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("0");

        // Then
        Respuesta<PagoTarjetaInfoView> respPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respPagoTC);
        Assert.assertEquals(EstadoRespuesta.OK, respPagoTC.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta OK sin cotizacion.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaOKSinCotizacion() throws BusinessException, DAOException {

        // When
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(new CotizacionDTO(true));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeStopDebit());
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(cuentaManager.getCuentasSaldo()).thenReturn(obtenerRespuestaOKCuentasView());
        when(contratosBO.buscarContratoAceptadoOld(Matchers.anyString(), Matchers.anyString(),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("0");

        // Then
        Respuesta<PagoTarjetaInfoView> respPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respPagoTC);
        Assert.assertEquals(EstadoRespuesta.OK, respPagoTC.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta OK saldos null.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaOKSaldosNull() throws BusinessException, DAOException {

        // When
        List<DatosTarjeta> datosTarjeta = DatosTarjetaMock.obtenerListaDatosTarjeta();
        datosTarjeta.get(0).setSaldoDolaresTC(null);
        datosTarjeta.get(0).setSaldoPesosTC(null);
        datosTarjeta.get(0).setSaldoUltimoCierreDolaresTC(null);
        datosTarjeta.get(0).setSaldoUltimoCierrePesosTC(null);
        datosTarjeta.get(0).setPagoMinimoPesosTC(null);
        datosTarjeta.get(0).setAlias("mi tarjetita");
        datosTarjeta.get(0).setFormaPagoTarjetaCredito("04");
        datosTarjeta.get(0).setCotizacionVendedor(new BigDecimal(60));
        datosTarjeta.get(0).setCotizacionComprador(new BigDecimal(58));
        datosTarjeta.get(0).setSaldoPendienteDolares(new BigDecimal(1000));
        datosTarjeta.get(0).setSaldoPendientePesos(new BigDecimal(1000));
        datosTarjeta.get(0).setSaldoTotalConvDolares(new BigDecimal(1000));
        datosTarjeta.get(0).setSaldoTotalConvPesos(new BigDecimal(1000));   
        datosTarjeta.get(1).setSaldoDolaresTC(new BigDecimal("11"));
        datosTarjeta.get(1).setSaldoPesosTC(new BigDecimal("200"));
        datosTarjeta.get(1).setSaldoUltimoCierreDolaresTC(new BigDecimal("11"));
        datosTarjeta.get(1).setSaldoUltimoCierrePesosTC(new BigDecimal("200"));
        datosTarjeta.get(1).setAlias("mi tarjetita");
        datosTarjeta.get(1).setFormaPagoTarjetaCredito("03");
        datosTarjeta.get(1).setCotizacionVendedor(new BigDecimal(60));
        datosTarjeta.get(1).setCotizacionComprador(new BigDecimal(58));
        datosTarjeta.get(1).setSaldoPendienteDolares(new BigDecimal(800));
        datosTarjeta.get(1).setSaldoPendientePesos(new BigDecimal(800));
        datosTarjeta.get(1).setSaldoTotalConvDolares(new BigDecimal(1000));
        datosTarjeta.get(1).setSaldoTotalConvPesos(new BigDecimal(1000));
        datosTarjeta.get(2).setSaldoDolaresTC(new BigDecimal("0"));
        datosTarjeta.get(2).setSaldoPesosTC(new BigDecimal("0"));
        datosTarjeta.get(2).setFormaPagoTarjetaCredito("05");
        datosTarjeta.get(2).setCotizacionVendedor(new BigDecimal(60));
        datosTarjeta.get(2).setCotizacionComprador(new BigDecimal(58));
        datosTarjeta.get(2).setSaldoPendienteDolares(new BigDecimal(10000));
        datosTarjeta.get(2).setSaldoPendientePesos(new BigDecimal(10000));
        datosTarjeta.get(2).setSaldoTotalConvDolares(new BigDecimal(10000));
        datosTarjeta.get(2).setSaldoTotalConvPesos(new BigDecimal(10000));

        when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class))).thenReturn(datosTarjeta);
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeStopDebit());
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(cuentaManager.getCuentasSaldo()).thenReturn(obtenerRespuestaOKCuentasView());
        when(contratosBO.buscarContratoAceptadoOld(Matchers.anyString(), Matchers.anyString(),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("0");

        // Then
        Respuesta<PagoTarjetaInfoView> respPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respPagoTC);
        Assert.assertEquals(EstadoRespuesta.OK, respPagoTC.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta OK tiene pagos programados.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaOKTienePagosProgramados() throws BusinessException, DAOException {

        // When
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("TIENE PAGOS PROGRAMADOS");

        String tienePagosProgramados = "1177";

        when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoClienteVisaYAmex());
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeStopDebit());
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(cuentaManager.getCuentasSaldo()).thenReturn(obtenerRespuestaOKCuentasView());
        when(contratosBO.buscarContratoAceptadoOld(Matchers.anyString(), Matchers.anyString(),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("0");
        when(pagosPendientesBO.tienePagosProgramados(Matchers.any(Cliente.class), Matchers.any(DatosTarjeta.class),
                Matchers.anyString())).thenReturn(true);
        when(mensajeBO.obtenerMensajePorCodigo(tienePagosProgramados)).thenReturn(mensaje);

        // Then
        Respuesta<PagoTarjetaInfoView> respPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respPagoTC);
        Assert.assertEquals(EstadoRespuesta.OK, respPagoTC.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta error business exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaErrorBusinessException() throws BusinessException, DAOException {

        // When
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(3));
        when(sesionCliente.getCliente()).thenReturn(ClienteMock.completarInfoCliente());
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(obtenerMensajeBusinessException());

        // Then
        Respuesta<PagoTarjetaInfoView> respPagoTC = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerSinReintentos());

        // Expected
        Assert.assertNotNull(respPagoTC);
        Assert.assertEquals(EstadoRespuesta.ERROR, respPagoTC.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta error no hay tarjetas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaErrorNoHayTarjetas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR PAGO NO HAY TARJETAS ACTIVAS");
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(cliente)).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_NO_HAY_TARJETAS_ACTIVAS))
                .thenReturn(mensaje);

        // Then
        Respuesta<PagoTarjetaInfoView> respuesta = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta error recuperar cuentas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaErrorRecuperarCuentas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MENSAJE");

        Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getTarjetaDesdeNroTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
        when(mensajeBO.obtenerMensajePorCodigo("1207")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1208")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1372")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1374")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1272")).thenReturn(mensaje);

        // Then
        Respuesta<PagoTarjetaInfoView> respuesta = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta error no hay cuentas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDatosInicialesPagoTarjetaErrorNoHayCuentas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR PAGO NO HAY CUENTAS ACTIVAS");

        Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getTarjetaDesdeNroTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_NO_HAY_CUENTAS_ACTIVAS))
                .thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1207")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1208")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1372")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1374")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1272")).thenReturn(mensaje);

        // Then
        Respuesta<PagoTarjetaInfoView> respuesta = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener datos iniciales pago tarjeta DAO exception recuperar contrato.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDatosInicialesPagoTarjetaDAOExceptionRecuperarContrato() throws BusinessException, DAOException {

        // When
        Cliente cliente = mock(Cliente.class);

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR PAGO NO HAY CUENTAS ACTIVAS");

        Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
        CuentasView cuentas = new CuentasView();
        respuestaCuentas.setRespuesta(cuentas);
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getTarjetaDesdeNroTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(pagosPendientesBO.getDatosInicialesPagoTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta());
        when(cotizacionBO.obtenerDatosCotizacionSinExcepcion(Matchers.any(Cliente.class),
                Matchers.any(DatosTarjeta.class), Matchers.anyString())).thenReturn(obtenerCotizacionUSD());
        when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
        when(contratosBO.buscarContratoAceptadoOld(Matchers.anyString(), Matchers.anyString(),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenThrow(DAOException.class);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_PAGO_TARJETAS_NO_HAY_CUENTAS_ACTIVAS))
                .thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1207")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1208")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1372")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1374")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1272")).thenReturn(mensaje);
        when(mensajeBO.obtenerMensajePorCodigo("1137")).thenReturn(mensaje);

        // Then
        Respuesta<PagoTarjetaInfoView> respuesta = pagosTarjetaManager
                .obtenerDatosInicialesPagoTarjeta(ReintentarMock.obtenerConReintentos());

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Pagar tarjeta OK.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException 
     */
    @Test
    @Ignore
    public void pagarTarjetaOK() throws ServiceException, IllegalAccessException, BusinessException {
        // When
        Cliente cliente = new Cliente();
        Cuenta laCuenta = new Cuenta();
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();

        laCuenta.setNroTarjetaCredito("00000000000000000001");
        laCuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        laCuenta.setNroTarjetaCredito("4511611177763198");
        laCuenta.setCbu("9900001111222233334444");
        laCuenta.setTipoCuentaEnum(TipoCuenta.VISA);

        cuentaList.add(laCuenta);
        cliente.setCuentas(cuentaList);

        pago.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(laCuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setFechaDePago("12/12/2016");
        pago.setTipoPagoTC("0");

        Respuesta<ComprobantePagoTarjeta> respuestaBO = respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class,
                ComprobantePagoTarjetaMock.completarInfo("Mensaje Feedback"));

        FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaHastaPTC", "22:00", true);
        FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaDesdePTC", "08:00", true);

        when(pagoTarjetaCreditoBO.obtenerDatosTarjetaPago(Matchers.anyString()))
        		.thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta().get(0));
        when(pagoTarjetaCreditoBO.pagar(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);
        when(sesionParametros.getValidacionHash()).thenReturn("6A6870626D13BB587BD8DC62A4285A45");
        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.pagarTarjeta(pago, cliente);

        // Expected
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Pagar tarjeta mastercard OK.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException 
     * 				
     */
    @Test
    @Ignore
    public void pagarTarjetaMastercardOK() throws ServiceException, IllegalAccessException, BusinessException {
        // Given
        Cliente cliente = new Cliente();
        Cuenta laCuenta = new Cuenta();
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();

        laCuenta.setNroTarjetaCredito("00000000000000000001");
        laCuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_MASTER.getCodigo());
        laCuenta.setNroTarjetaCredito("4511611177763198");
        laCuenta.setCbu("9900001111222233334444");
        laCuenta.setTipoCuentaEnum(TipoCuenta.MASTERCARD);

        cuentaList.add(laCuenta);
        cliente.setCuentas(cuentaList);

        pago.setNumeroTarjeta("MASTER "
                + TarjetaUtils.crearMascaraNroTarjeta(laCuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setFechaDePago("12/12/2016");
        pago.setTipoPagoTC("0");

        Respuesta<ComprobantePagoTarjeta> respuestaBO = respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class,
                ComprobantePagoTarjetaMock.completarInfo("Mensaje Feedback"));

        FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaHastaPTC", "22:00", true);
        FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaDesdePTC", "08:00", true);

        // When
        when(pagoTarjetaCreditoBO.obtenerDatosTarjetaPago(Matchers.anyString()))
				.thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta().get(0));
        when(pagoTarjetaCreditoBO.pagar(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);
        when(sesionParametros.getValidacionHash()).thenReturn("3364E988408FE782745D8A0DC9D5152E");

        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.pagarTarjeta(pago, cliente);

        // Expected
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Pagar tarjeta con fecha pago menor not empty.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void pagarTarjetaConFechaPagoMenorNotEmpty() throws ServiceException, IllegalAccessException {

        // When
        setearHorarioBancario(-1, 5);
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();
        Respuesta<ComprobantePagoTarjeta> toRet = new Respuesta<ComprobantePagoTarjeta>();

        cuenta.setNroTarjetaCredito("00000000000000000001");
        cuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuenta.setNroTarjetaCredito("4511611177763198");
        cuenta.setCbu("9900001111222233334444");

        cuentaList.add(cuenta);
        cliente.setCuentas(cuentaList);

        pago.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setStopDebit("true");
        pago.setFechaDePago("12/12/2014");
        pago.setTipoPagoTC("0");

        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("algoNotEmpty");

        when(pagoTarjetaCreditoBO.pagar(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(toRet);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getValidacionHash()).thenReturn("559D09DF441FDDDD6CD2019C1E46A79A");
        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.pagarTarjeta(pago, cliente);

        // Expected
        assertEquals("algoNotEmpty", res.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Pagar tarjeta respuesta ok stop debit vacio.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void pagarTarjetaRespuestaOkStopDebitVacio()
            throws ServiceException, IllegalAccessException, BusinessException, DAOException {

        // When
        setearHorarioBancario(-1, 5);
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        Cuenta cuentaInvalida = new Cuenta();
        Cuenta cuentaDebito = new Cuenta();
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();
        Respuesta<ComprobantePagoTarjeta> toRet = new Respuesta<ComprobantePagoTarjeta>();
        List<DebitoAutomatico> debitos = new ArrayList<DebitoAutomatico>();
        DebitoAutomatico debitoAutomatico = new DebitoAutomatico();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StopDebitOut stopDebitOut = new StopDebitOut();
        ComprobantePagoTarjeta comprobantePagoTarjeta = new ComprobantePagoTarjeta();

        cuenta.setNroTarjetaCredito("00000000000000000001");
        cuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuenta.setNroTarjetaCredito("4511611177763198");
        cuenta.setCbu("9900001111222233334444");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setNroCuentaProducto("01234");

        cuentaList.add(cuenta);
        cuentaInvalida.setTipoCuenta("11");
        cuentaInvalida.setNroTarjetaCredito("00000000000000000002");
        cuentaInvalida.setTipoCuentaSinUnificar("11");
        cuentaInvalida.setNroTarjetaCredito("4511611177763100");
        cuentaInvalida.setCbu("5512341234123412341234");
        cuentaInvalida.setTipoCuentaEnum(TipoCuenta.CUENTA_RECAUDADORA_PESOS);

        cuentaList.add(cuentaInvalida);

        cuentaDebito.setTipoCuenta("01");
        cuentaDebito.setNroTarjetaCredito("00000000000000000003");
        cuentaDebito.setTipoCuentaSinUnificar("01");
        cuentaDebito.setNroTarjetaCredito("");
        cuentaDebito.setCbu("5511112222333344445555");
        cuentaDebito.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);

        cuentaList.add(cuentaDebito);

        cliente.setCuentas(cuentaList);

        pago.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setStopDebit("true");

        pago.setFechaDePago(simpleDateFormat.format(new Date()));
        pago.setTipoPagoTC("0");

        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        comprobantePagoTarjeta.setMensaje(" ");
        toRet.setRespuesta(comprobantePagoTarjeta);

        debitoAutomatico.setNumeroPartida("01234");
        debitos.add(debitoAutomatico);

        stopDebitOut.setResultado(StopDebitTarjetasDAO.ESTADO_OK);
        stopDebitOut.setNroDeComprobante("0000000");

        when(pagoTarjetaCreditoBO.obtenerDatosTarjetaPago(Matchers.anyString()))
				.thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta().get(0));
        when(pagoTarjetaCreditoBO.pagar(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(toRet);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(debitos);
        when(stopDebitTarjetasBO.realizarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class))).thenReturn(stopDebitOut);
        when(sesionParametros.getValidacionHash())
                .thenReturn(HashUtils.obtenerHash(crearMapaDePagoTarjetaCreditoView(pago)));
        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.pagarTarjeta(pago, cliente);

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    private Map<String, String> crearMapaDePagoTarjetaCreditoView(PagoTarjetaCreditoView entity) {
        Map<String, String> mapaAtributos = new HashMap<String, String>();
        mapaAtributos.put("cbuDolares", entity.getCbuDolares());
        mapaAtributos.put("cbuPesos", entity.getCbuPesos());
        mapaAtributos.put("fechaDePago", entity.getFechaDePago());
        mapaAtributos.put("importeMinimo", entity.getImporteMinimo());
        mapaAtributos.put("monedaSeleccionado", entity.getMonedaSeleccionado());
        mapaAtributos.put("numeroTarjeta", entity.getNumeroTarjeta());
        mapaAtributos.put("saldoAPagarConvertidoADolares", entity.getSaldoAPagarConvertidoADolares());
        mapaAtributos.put("saldoAPagarConvertidoAPesos", entity.getSaldoAPagarConvertidoAPesos());
        mapaAtributos.put("saldoDolaresTC", entity.getSaldoDolaresTC());
        mapaAtributos.put("saldoPesosTC", entity.getSaldoPesosTC());
        mapaAtributos.put("saldoSinSiguienteCierreDolares", entity.getSaldoSinSiguienteCierreDolares());
        mapaAtributos.put("saldoSinSiguienteCierrePesos", entity.getSaldoSinSiguienteCierrePesos());
        mapaAtributos.put("stopDebit", entity.getStopDebit());
        mapaAtributos.put("tienePagosProgramados", entity.getTienePagosProgramados());
        mapaAtributos.put("tipoPagoTC", entity.getTipoPagoTC());
        mapaAtributos.put("tipoTarjeta", entity.getTipoTarjeta());
        mapaAtributos.put("totalAPagarEnDolares", entity.getTotalAPagarEnDolares());
        mapaAtributos.put("totalAPagarEnPesos", entity.getTotalAPagarEnPesos());
        if (entity.getImportePagoDolares() != null) {
            mapaAtributos.put("importePagoPesos", entity.getImportePagoDolares().getMonto());
        }
        if (entity.getImportePagoPesos() != null) {
            mapaAtributos.put("importePagoDolares", entity.getImportePagoPesos().getMonto());
        }
        return mapaAtributos;
    }

    /**
     * Obtener datos tarjeta error.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception 
     */
    @Test
    public void obtenerDatosTarjetaError() throws ServiceException, BusinessException {

        // When
        setearHorarioBancario(-1, 5);
        Cliente cliente = new Cliente();
        Cuenta laCuenta = new Cuenta();
        List<Cuenta> cuentaList = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();
        Respuesta<ComprobantePagoTarjeta> toRet = new Respuesta<ComprobantePagoTarjeta>();
        List<PagoTarjetaView> pagoTarjetaList = new ArrayList<PagoTarjetaView>();
        PagoTarjetaView pagoTarjeta = new PagoTarjetaView();

        laCuenta.setNroTarjetaCredito("00000000000000000001");
        laCuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        laCuenta.setNroTarjetaCredito("4511611177763198");
        laCuenta.setCbu("9900001111222233334444");
        pagoTarjetaList.add(pagoTarjeta);
        pagoTarjeta.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(laCuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setTipoPagoTC("0");
        cuentaList.add(laCuenta);
        cliente.setCuentas(cuentaList);

        pago.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(laCuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setFechaDePago("12/12/2016");

        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(pagoTarjetaCreditoBO.obtenerDatosTarjetaPago(Matchers.anyString()))
				.thenReturn(DatosTarjetaMock.obtenerListaDatosTarjeta().get(0));
        when(sesionParametros.getValidacionHash()).thenReturn("7DF01F758E45E865AAC6C3CE0E7E049E");
        when(pagoTarjetaCreditoBO.programarPago(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(toRet);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.pagarTarjeta(pago, cliente);

        // Expected
        assertEquals(toRet.getEstadoRespuesta(), res.getEstadoRespuesta());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Programar pago error parse.
     *
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void programarPagoErrorParse() throws ParseException {

        // When
        Cliente cliente = mock(Cliente.class);
        PagoTarjetaCreditoView pagoTarjetaView = new PagoTarjetaCreditoView();

        pagoTarjetaView.setFechaDePago("pepe");

        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.programarPago(pagoTarjetaView, cliente);

        // Expected
        assertEquals("Unparseable date: \"pepe\"", res.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Programar pago no error.
     *
     * @throws ParseException
     *             the parse exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void programarPagoNoError() throws ParseException, ServiceException {

        // When
        Cliente cliente = mock(Cliente.class);
        PagoTarjetaCreditoView pago = new PagoTarjetaCreditoView();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuenta.setNroTarjetaCredito("4511611177763198");
        cuenta.setCbu("9900001111222233334444");
        cuenta.setNroCuentaProducto("123244567");

        Cuenta cuentaPesos = new Cuenta();

        pago.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pago.setCbuPesos("9900001111222233334444");
        pago.setCbuDolares("9900001111222233334444");
        pago.setFechaDePago("12/12/2016");
        pago.setFechaDePago("12/12/1212");
        pago.setMonedaSeleccionado("2");

        Respuesta<ComprobantePagoTarjeta> resok = respuestaFactory.crearRespuestaOk(ComprobantePagoTarjeta.class);

        when(pagoTarjetaCreditoBO.programarPago(Matchers.any(DatosPagoTC.class), Matchers.any(Cliente.class)))
                .thenReturn(resok);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaPesos);

        // Then
        Respuesta<ComprobantePagoTarjeta> res = pagosTarjetaManager.programarPago(pago, cliente);

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Aceptacion contrato pago programado error.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptacionContratoPagoProgramadoError() throws DAOException {

        // When
        Cliente cliente = new Cliente();
        DAOException e = new DAOException("DAOError forzado");
        when(contratosBO.confirmarAceptacionContratoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenThrow(e);

        // Then
        Respuesta<String> res = pagosTarjetaManager.aceptacionContratoPagoProgramado(cliente);

        // Expected
        assertEquals("DAOError forzado", res.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Aceptacion contrato pago programado aceptacion OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptacionContratoPagoProgramadoAceptacionOK() throws DAOException {

        // When
        Cliente cliente = new Cliente();
        when(contratosBO.confirmarAceptacionContratoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("OK");

        // Then
        Respuesta<String> res = pagosTarjetaManager.aceptacionContratoPagoProgramado(cliente);

        // Expected
        assertEquals("OK", res.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Aceptacion contrato pago programado aceptacion error.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptacionContratoPagoProgramadoAceptacionError() throws DAOException {

        // When
        Cliente cliente = new Cliente();
        when(contratosBO.confirmarAceptacionContratoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("ERROR");

        // Then
        Respuesta<String> res = pagosTarjetaManager.aceptacionContratoPagoProgramado(cliente);

        // Expected
        assertEquals("ERROR", res.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Aceptacion contrato pago programado aceptacion other.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void aceptacionContratoPagoProgramadoAceptacionOther() throws DAOException {

        // When
        Cliente cliente = new Cliente();
        when(contratosBO.confirmarAceptacionContratoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("OTHER");

        // Then
        Respuesta<String> res = pagosTarjetaManager.aceptacionContratoPagoProgramado(cliente);

        // Expected
        assertEquals("No acepto el contrato", res.getItemsMensajeRespuesta().get(0).getMensaje());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Horario bancario en hora.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void horarioBancarioEnHora() throws DAOException, IllegalAccessException {

        // When
        setearHorarioBancario(-1, 5);

        when(mensajeBO.obtenerMensajePorCodigo("310021")).thenReturn(new Mensaje());

        // Then
        String res = pagosTarjetaManager.horarioBancario();

        // Expected
        assertEquals(StringUtils.EMPTY, res);
    }

    /**
     * Horario bancario fuera de hora.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void horarioBancarioFueraDeHora() throws DAOException, IllegalAccessException {

        // When
        setearHorarioBancario(-2, -1);
        String msg = ""; // "Fuera de horario"
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(msg);

        when(mensajeBO.obtenerMensajePorCodigo("310021")).thenReturn(mensaje);
        // Then
        String res = pagosTarjetaManager.horarioBancario();

        // Expected
        assertEquals(mensaje.getMensaje(), res);
    }

    /**
     * Stop debit DAO error.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void stopDebitDAOError() throws DAOException, BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = new Cuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        DebitoAutomatico debito = new DebitoAutomatico();
        List<DebitoAutomatico> listaDebitos = new ArrayList<DebitoAutomatico>();
        Respuesta<StopDebitOut> resStopDebitOut = new Respuesta<StopDebitOut>();
        StopDebitOut stopDebitOut = new StopDebitOut();
        PagoTarjetaCreditoView pagoTarjetaView = new PagoTarjetaCreditoView();

        cuenta.setNroTarjetaCredito("00000000000000000001");
        cuenta.setNroCuentaProducto("0234");
        cuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuenta.setNroTarjetaCredito("4511611177763198");
        cuenta.setCbu("9900001111222233334444");

        pagoTarjetaView.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pagoTarjetaView.setCbuPesos("9900001111222233334444");
        pagoTarjetaView.setCbuDolares("9900001111222233334444");
        pagoTarjetaView.setFechaDePago("12/12/2016");

        stopDebitOut.setResultado(StopDebitTarjetasDAO.ESTADO_OK);
        stopDebitOut.setNroDeComprobante("01203004");

        debito.setNumeroPartida("0234");

        cuenta.setTipoCuenta("01");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        stopDebitOut.setResultado("Paso por el catch del DAOException");
        resStopDebitOut.setRespuesta(stopDebitOut);
        listaDebitos.add(debito);

        when(stopDebitTarjetasBO.realizarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class))).thenThrow(DAOException.class);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(cuentas);

        // Then
        Respuesta<StopDebitOut> res = pagosTarjetaManager.stopDebit(pagoTarjetaView, cliente);

        // Expected
        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Stop debit business error.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void stopDebitBusinessError() throws BusinessException {

        // When
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        PagoTarjetaCreditoView pagoTarjetaView = new PagoTarjetaCreditoView();
        BusinessException e = new BusinessException();

        cuenta.setTipoCuenta("01");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);

        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenThrow(e);

        // Then
        Respuesta<StopDebitOut> res = pagosTarjetaManager.stopDebit(pagoTarjetaView, cliente);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Stop debit no error.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void stopDebitNoError() throws DAOException, BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta cuenta = new Cuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        DebitoAutomatico debito = new DebitoAutomatico();
        List<DebitoAutomatico> listaDebitos = new ArrayList<DebitoAutomatico>();
        Respuesta<StopDebitOut> resStopDebitOut = new Respuesta<StopDebitOut>();
        StopDebitOut stopDebitOut = new StopDebitOut();
        PagoTarjetaCreditoView pagoTarjetaView = new PagoTarjetaCreditoView();

        cuenta.setNroTarjetaCredito("00000000000000000001");
        cuenta.setNroCuentaProducto("0234");
        cuenta.setTipoCuentaSinUnificar(TipoCuentaTarjeta.TIPOCTA_VISA.getCodigo());
        cuenta.setNroTarjetaCredito("4511611177763198");
        cuenta.setCbu("9900001111222233334444");

        pagoTarjetaView.setNumeroTarjeta("VISA "
                + TarjetaUtils.crearMascaraNroTarjeta(cuenta.getNroTarjetaCredito(), TipoCuentaTarjeta.TIPOCTA_VISA));
        pagoTarjetaView.setCbuPesos("9900001111222233334444");
        pagoTarjetaView.setCbuDolares("9900001111222233334444");
        pagoTarjetaView.setFechaDePago("12/12/2016");

        stopDebitOut.setResultado(StopDebitTarjetasDAO.ESTADO_OK);
        stopDebitOut.setNroDeComprobante("01203004");

        debito.setNumeroPartida("0234");

        cuenta.setTipoCuenta("01");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        stopDebitOut.setResultado("retorno de flujo completo");
        resStopDebitOut.setRespuesta(stopDebitOut);
        listaDebitos.add(debito);

        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(stopDebitTarjetasBO.realizarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class))).thenReturn(stopDebitOut);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(cuenta);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(cuentas);

        // Then
        Respuesta<StopDebitOut> res = pagosTarjetaManager.stopDebit(pagoTarjetaView, cliente);

        // Expected
        assertEquals("retorno de flujo completo", res.getRespuesta().getResultado());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta OK cuentas view.
     *
     * @return the respuesta
     */
    private Respuesta<CuentasView> obtenerRespuestaOKCuentasView() {
        Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentas.setRespuesta(obtenerCuentasView());
        respuestaCuentas.setRespuestaVacia(Boolean.FALSE);
        return respuestaCuentas;
    }

    /**
     * Obtener cuentas view.
     *
     * @return the cuentas view
     */
    private CuentasView obtenerCuentasView() {
        CuentasView cuentasView = new CuentasView();
        cuentasView.setCuentas(obtenerCuentasAdhesionDebitoView());
        cuentasView.setHasCuentasActivas(Boolean.FALSE);
        cuentasView.setSelected(0);
        return cuentasView;
    }

    /**
     * Obtener cuentas adhesion debito view.
     *
     * @return the list
     */
    private List<CuentasAdhesionDebitoView> obtenerCuentasAdhesionDebitoView() {
        ArrayList<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentas.add(obtenerCuenta("CU", "", "Lastfoigel", "0720370988000035052368", "Cuenta única", "Marcio Elisandro",
                "370-350523/6", "0,00", "0,00", "0,00", "1.999.159,37", "1.975.316,44", "DNI",
                "cuentas/imprimir?numeroCuenta=0000000003505236&sucursal=0370"));
        cuentas.add(obtenerCuenta("CU", "", "Lastfoigel", "0720370988000035087922", "Cuenta única", "Marcio Elisandro",
                "370-350879/2", "0,00", "0,00", "0,00", "2.000.810,54", "1.962.459,06", "DNI",
                "cuentas/imprimir?numeroCuenta=0000000003508792&sucursal=0370"));
        cuentas.add(obtenerCuenta("CCP", "", "Lastfoigel", "0720370920000000031086", "Cuenta corriente en $",
                "Marcio Elisandro", "370-000310/8", "0", "0", "0,00", "0,00", "868.888.605.409,55", "DNI",
                "cuentas/imprimir?numeroCuenta=0000000000003108&sucursal=0370"));
        return cuentas;
    }

    /**
     * Obtener cuenta.
     *
     * @param abreviaturaTipoCuenta
     *            the abreviatura tipo cuenta
     * @param alias
     *            the alias
     * @param apellidoCliente
     *            the apellido cliente
     * @param cbu
     *            the cbu
     * @param descripcionTipoCuenta
     *            the descripcion tipo cuenta
     * @param nombreCliente
     *            the nombre cliente
     * @param numero
     *            the numero
     * @param saldoCajaAhorro
     *            the saldo caja ahorro
     * @param saldoCuentaCorriente
     *            the saldo cuenta corriente
     * @param saldoDescubierto
     *            the saldo descubierto
     * @param saldoDolares
     *            the saldo dolares
     * @param saldoPesos
     *            the saldo pesos
     * @param tipoIdentificacion
     *            the tipo identificacion
     * @param urlResporteCBU
     *            the url resporte CBU
     * @return the cuentas adhesion debito view
     */
    private CuentasAdhesionDebitoView obtenerCuenta(String abreviaturaTipoCuenta, String alias, String apellidoCliente,
            String cbu, String descripcionTipoCuenta, String nombreCliente, String numero, String saldoCajaAhorro,
            String saldoCuentaCorriente, String saldoDescubierto, String saldoDolares, String saldoPesos,
            String tipoIdentificacion, String urlResporteCBU) {
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuenta.setAbreviaturaTipoCuenta(abreviaturaTipoCuenta);
        cuenta.setAlias(alias);
        cuenta.setApellidoCliente(apellidoCliente);
        cuenta.setCbu(cbu);
        cuenta.setDescripcionTipoCuenta(descripcionTipoCuenta);
        cuenta.setNombreCliente(nombreCliente);
        cuenta.setNumero(numero);
        cuenta.setSaldoCajaAhorro(saldoCajaAhorro);
        cuenta.setSaldoCuentaCorriente(saldoCuentaCorriente);
        cuenta.setSaldoDescubierto(saldoDescubierto);
        cuenta.setSaldoDolares(saldoDolares);
        cuenta.setSaldoPesos(saldoPesos);
        cuenta.setTipoIdentificacion(tipoIdentificacion);
        cuenta.setUrlReporteCBU(urlResporteCBU);
        cuenta.setShowSaldoDolares(Boolean.TRUE);
        cuenta.setShowSaldoPesos(Boolean.TRUE);
        return cuenta;
    }

    /**
     * Obtener mensaje stop debit.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeStopDebit() {
        Mensaje msj = new Mensaje();
        msj.setCodigo("1207");
        msj.setMensaje("Mensaje stop debit.");
        return msj;
    }

    /**
     * Obtener mensaje business exception.
     *
     * @return the mensaje
     */
    private Mensaje obtenerMensajeBusinessException() {
        Mensaje msj = new Mensaje();
        msj.setCodigo("1137");
        msj.setMensaje("Mensaje Business Exception.");
        return msj;
    }

    /**
     * Obtener cotizacion USD.
     *
     * @return the cotizacion DTO
     */
    private CotizacionDTO obtenerCotizacionUSD() {
        CotizacionDTO cotizacionDTO = new CotizacionDTO();
        cotizacionDTO.setCotizacionDolares("0000000001595");
        cotizacionDTO.setImporteDolares("000000000012780+");
        cotizacionDTO.setImporteDolaresConvertido("000000000012780+");
        cotizacionDTO.setImportePesos("000000000513722+");
        cotizacionDTO.setImportePesosConvertido("000000000032208+");
        cotizacionDTO.setImporteTotalConvertido("000000000044988+");
        cotizacionDTO.setMonedaPago("USD");
        cotizacionDTO.setNumeroCuentaTarjeta("000000240088068");
        cotizacionDTO.setNumeroTarjeta("4050710080531614");
        cotizacionDTO.setTipoTarjeta("7");
        return cotizacionDTO;
    }

    /**
     * Setear horario bancario.
     *
     * @param diferenciaInicio
     *            the diferencia inicio
     * @param diferenciaFin
     *            the diferencia fin
     */
    private void setearHorarioBancario(int diferenciaInicio, int diferenciaFin) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, diferenciaInicio);
        String startTime = simpleDateFormat.format(cal.getTime());
        cal.add(Calendar.MINUTE, diferenciaFin - diferenciaInicio);
        String endTime = simpleDateFormat.format(cal.getTime());
        try {
            FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaDesdePTC", startTime, true);
            FieldUtils.writeDeclaredField(pagosTarjetaManager, "horaHastaPTC", endTime, true);
        } catch (IllegalAccessException e) {
        }
    }
}
