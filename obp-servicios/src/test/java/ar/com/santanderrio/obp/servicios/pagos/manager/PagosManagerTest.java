
package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IdentificacionCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoDePagoEnum;
import ar.com.santanderrio.obp.servicios.pagos.service.PagosService;
import ar.com.santanderrio.obp.servicios.pagos.service.StopDebitService;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagosManager;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.impl.PagosManagerImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPagosView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagosPendientesView;

/**
 * Test para {@link PagosManager}.
 *
 * @author manuel.vargas
 * @since Aug 25, 2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagosManagerTest {

    /** The un pago pendiente view. */
    PagoPendienteView unPagoPendiente;

    /** The un pago pendiente. */
    PagoPendiente pagoPendiente;

    /** The respuesta pago pendiente. */
    Respuesta<PagoPendienteView> respuestaPagoPendiente = new Respuesta<PagoPendienteView>();

    /** The respuesta resultado transaccion. */
    Respuesta<ResultadoTransaccion> respuestaResultadoTransaccion = new Respuesta<ResultadoTransaccion>();

    /** The resultado transaccion. */
    ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();

    /** The respuesta pagos pendientes sesion. */
    Respuesta<List<PagoPendiente>> respuestaPagosPendientesSesion = new Respuesta<List<PagoPendiente>>();

    /** The respuesta mensaje. */
    Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();

    /** The contador. */
    ContadorIntentos contador = new ContadorIntentos();

    /** The pagos manager. */
    @InjectMocks
    PagosManagerImpl pagosManager;

    /** The estadistica manager. */
    @Mock
    EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Mock
    SesionParametros sesionParametros;

    /** The cuenta manager. */
    @Mock
    CuentaManager cuentaManager;

    /** The pagos service. */
    @Mock
    PagosService pagosService;

    /** The StopDebit service. */
    @Mock
    StopDebitService stopDebitService;

    /** The sesion cliente. */
    @Mock
    SesionCliente sesionCliente;

    /** The mensaje manager. */
    @Mock
    MensajeManager mensajeManager;

    /** The medios pago BO. */
    @Mock
    MediosPagoBO mediosPagoBO;

    /** The mock medios pago DAO. */
    @Mock
    BuscadorEmpresaDAO mockMediosPagoDAO;

    /** The mensaje dao. */
    @Mock
    MensajeBO mensajeBO;
    
    @Mock
    PagoTarjetaCreditoBO pagoTarjetaCreditoBO;


    /** The modulo permiso BO. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        contador.reset();
        // pago pendiente para request/response
        unPagoPendiente = new PagoPendienteView("832163120110033379");
        unPagoPendiente.setIdSesion("1");
        unPagoPendiente.setCodigoEmpresa("30500143297");
        unPagoPendiente.setNombreEmpresa("ACA SEGUROS");
        unPagoPendiente.setCodigoClienteEmpresa("12341234123");
        unPagoPendiente.setVencimiento("29/08/2016");
        unPagoPendiente.setImporte("230,00");
        unPagoPendiente.setImporteUSS("0,00");
        unPagoPendiente.setMoneda("$");
        unPagoPendiente.setCuit("23-30338256-9");
        unPagoPendiente.setPagoAutomatico(true);
        unPagoPendiente.setIdentificacionFactura("");
        unPagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        unPagoPendiente.setTipoPagoDescripcion("Débito Automático en Cuenta Pago Total");
        unPagoPendiente.setEditable(false);
        unPagoPendiente.setIcono("a");
        unPagoPendiente.setTooltip("Este pago se debitará automáticamente todos los meses");
        unPagoPendiente.setDatosAdicionales("");
        unPagoPendiente.setInformacionAdicional("");
        unPagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        unPagoPendiente.setMedioDePago(medioDePago);
        unPagoPendiente.setImporteLimiteAdhesion("700,00");

        List<PagoPendiente> listaPagosPendientes = new ArrayList<PagoPendiente>();
        PagoPendiente pagoPendiente = new PagoPendiente();
        pagoPendiente.setId(832163120110033379L);
        pagoPendiente.setNombreEmpresa("ACA SEGUROS");
        pagoPendiente.setCodigoClienteEmpresa("12341234123");
        pagoPendiente.setCuitEmpresa("31242765549");
        pagoPendiente.setTipoPago(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA);
        pagoPendiente.setImporte(new BigDecimal("123.50"));
        pagoPendiente.setDivisa(DivisaEnum.PESO);
        pagoPendiente.setVencimiento(new Date());
        pagoPendiente.setNombreEmpresaIatx("SEGURO");
        DatosPagoAutomaticoEntity datosPagoAutomatico = new DatosPagoAutomaticoEntity();
        datosPagoAutomatico.setTope(new BigDecimal("999.99"));
        datosPagoAutomatico.setIdentificacionCuenta(new IdentificacionCuenta("123-987654/3"));
        datosPagoAutomatico.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS);
        pagoPendiente.setDatosPagoAutomatico(datosPagoAutomatico);
        listaPagosPendientes.add(pagoPendiente);

        respuestaPagosPendientesSesion.setRespuesta(listaPagosPendientes);
        respuestaPagoPendiente.setRespuesta(unPagoPendiente);
        respuestaResultadoTransaccion.setRespuesta(resultadoTransaccion);
        respuestaResultadoTransaccion.getRespuesta().setNumeroComprobante("000234728");
        //
        // datos para recarga:
        List<PagoPendiente> listaPagosPendientesRec = new ArrayList<PagoPendiente>();
        PagoPendiente pagoPendienteRec = new PagoPendiente();
        pagoPendienteRec.setId(832163120110033379L);
        pagoPendienteRec.setNombreEmpresa("ACA SEGUROS");
        pagoPendienteRec.setCodigoClienteEmpresa("12341234123");
        pagoPendienteRec.setCuitEmpresa("31242764449");
        pagoPendienteRec.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL);
        pagoPendienteRec.setImporte(new BigDecimal("123.50"));
        pagoPendienteRec.setDivisa(DivisaEnum.PESO);
        pagoPendienteRec.setVencimiento(null);
        pagoPendienteRec.setNombreEmpresaIatx("SEGURO");
        DatosPagoAutomaticoEntity datosPagoAutomaticoRec = new DatosPagoAutomaticoEntity();
        datosPagoAutomaticoRec.setTope(new BigDecimal("999.99"));
        datosPagoAutomaticoRec.setIdentificacionCuenta(new IdentificacionCuenta("123-987654/3"));
        datosPagoAutomaticoRec.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS);
        pagoPendienteRec.setDatosPagoAutomatico(datosPagoAutomaticoRec);
        listaPagosPendientesRec.add(pagoPendienteRec);

        this.sesionParametros.setPagoPendienteView(unPagoPendiente);
    }

    /**
     * Ver detalle debito automatico.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void verDetalleDebitoAutomatico() throws ServiceException {
        Cuenta cuentaDolares = new Cuenta();
        cuentaDolares.setCbu("1111111111");
        Cuenta cuentaPesos = new Cuenta();
        cuentaPesos.setCbu("222222222");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaDolares);
        cuentas.add(cuentaPesos);

        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);

        // PagoPendienteView input output
        PagoPendienteView unPagoPendiente = new PagoPendienteView("832163120110033379");
        unPagoPendiente.setCodigoEmpresa("30500143297");
        unPagoPendiente.setNombreEmpresa("ACA SEGUROS");
        unPagoPendiente.setCodigoClienteEmpresa("12341234123");
        ;
        unPagoPendiente.setVencimiento("29/08/2016");
        unPagoPendiente.setImporte("230,00");
        unPagoPendiente.setImporteUSS("0,00");
        unPagoPendiente.setMoneda("$");

        IdentificacionCuentaView identificacionCuentaView = new IdentificacionCuentaView();
        identificacionCuentaView.setNumeroDeCuenta("2344532");
        identificacionCuentaView.setTipoDeCuenta(TipoCuentaTarjeta.TIPOCTA_VISA.getAbreviatura());
        unPagoPendiente.setMedioPagoDolares(identificacionCuentaView);

        unPagoPendiente.setPagoAutomatico(true);
        unPagoPendiente.setIdentificacionFactura("4566545");
        unPagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        unPagoPendiente.setTipoPagoDescripcion("Débito Automático en Cuenta Pago Total");
        unPagoPendiente.setEditable(false);
        unPagoPendiente.setIcono("a");
        unPagoPendiente.setTooltip("Este pago se debitará automáticamente todos los meses");
        unPagoPendiente.setDatosAdicionales("");
        unPagoPendiente.setInformacionAdicional("");
        unPagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        unPagoPendiente.setMedioDePago(medioDePago);
        unPagoPendiente.setImporteLimiteAdhesion("700,00");
        unPagoPendiente.setNumeroCuentaBancoDolares("1111111111");
        unPagoPendiente.setNumeroCuentaBancoPesos("222222222");

        // Mock
        Respuesta<List<PagoPendiente>> respuestaPagosPendientesSesion = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagosPendientes = new ArrayList<PagoPendiente>();
        PagoPendiente pagoPendiente = new PagoPendiente();
        pagoPendiente.setId(832163120110033379L);
        pagoPendiente.setNombreEmpresa("ACA SEGUROS");
        pagoPendiente.setCodigoClienteEmpresa("12341234123");
        pagoPendiente.setIdentificacionDeFactura("765987");
        pagoPendiente.setTipoPago(TipoDePagoEnum.SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA);
        pagoPendiente.setImporte(new BigDecimal("123.50"));
        pagoPendiente.setDivisa(DivisaEnum.PESO);
        pagoPendiente.setVencimiento(new Date());
        pagoPendiente.setNumeroCuentaBancoDolares(1111111111);
        pagoPendiente.setNumeroCuentaBancoPesos(222222222);
        pagoPendiente.setTipoCuentaTarjeta(TipoCuentaTarjeta.TIPOCTA_VISA);
        DatosPagoAutomaticoEntity datosPagoAutomatico = new DatosPagoAutomaticoEntity();
        datosPagoAutomatico.setTope(new BigDecimal("999.99"));
        datosPagoAutomatico.setIdentificacionCuenta(new IdentificacionCuenta("123-987654/3"));
        datosPagoAutomatico.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS);
        pagoPendiente.setDatosPagoAutomatico(datosPagoAutomatico);
        listaPagosPendientes.add(pagoPendiente);
        respuestaPagosPendientesSesion.setRespuesta(listaPagosPendientes);

        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagosPendientesSesion);
        AbstractCuenta abstractCuenta = Mockito.mock(AbstractCuenta.class);
        Mockito.when(abstractCuenta.getAlias()).thenReturn("Alias mock");
        Mockito.when(abstractCuenta.getTipoCuentaEnum()).thenReturn(TipoCuenta.CAJA_AHORRO_PESOS);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(abstractCuenta);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("000");
        mensaje.setMensaje("Mensaje");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // test OK
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.verDetalle(unPagoPendiente);

        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertFalse(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respuestaManager.getRespuesta().getId()));

        // verificaciones
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.VER_DETALLE_DE_PAGO_DE_SERVICIO_ADDI,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        // test SIN ELEMENTO EN SESION
        unPagoPendiente.setId("inexistente");
        respuestaManager = pagosManager.verDetalle(unPagoPendiente);
        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertNull(respuestaManager.getRespuesta());
        Assert.assertTrue(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getItemsMensajeRespuesta());

        // verificaciones
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.VER_DETALLE_DE_PAGO_DE_SERVICIO_ADDI,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

    /**
     * Test de ejecutar Stop debit de debito Pago Mis Cuentas y servicio adherido a
     * debito automatico en cuenta. Verificar estado de respuesta en Exception.
     */
    @Test
    public void ejecutarStopDebitConExcepetion() {
        /** inicializacion local */
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1251");
        mensaje.setMensaje(
                "<p>¡Lo sentimos!</p><p>Ocurrió un error con la carga de los movimientos. Por favor, intentá entrar de nuevo en unos minutos.</p>");

        Mockito.when(sesionParametros.getPagoPendienteView()).thenReturn(unPagoPendiente);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(this.respuestaPagosPendientesSesion);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        /** test Exception */
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.ejecutarStopDebit(unPagoPendiente);
        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertTrue(respuestaManager.isRespuestaVacia());
        Assert.assertNull(respuestaManager.getRespuesta());
    }

    /**
     * Test de ejecutar el servicio adherido a debito automatico en cuenta. caso:
     * SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA Verificar estado de respuesta en
     * OK.
     * 
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void ejecutarStopDebit_AdheridoADEBITOAUTOMATICO_OK() throws ServiceException {
        /** incializacion */
        this.contador.reset();
        Cuenta cuentaDolares = new Cuenta();
        cuentaDolares.setCbu("1111111111");
        Cuenta cuentaPesos = new Cuenta();
        cuentaPesos.setCbu("222222222");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaDolares);
        cuentas.add(cuentaPesos);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        respuestaResultadoTransaccion.setRespuestaVacia(false);
        respuestaResultadoTransaccion.setEstadoRespuesta(EstadoRespuesta.OK);
        Date fechatran = new Date();
        respuestaResultadoTransaccion.getRespuesta().setFechaTransaccion(fechatran);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1251");
        mensaje.setMensaje(
                "<b>¡Listo!</b><p>La solicitud de stop debit para el débito automático de {0} se realizó con éxito.</p> ");

        /** inicializacion mocks local */
        Mockito.when(sesionParametros.getPagoPendienteView()).thenReturn(unPagoPendiente);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(this.respuestaPagosPendientesSesion);
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(stopDebitService.ejecutarStopDebitoEnCuenta(Matchers.any(PagoPendiente.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaResultadoTransaccion);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        /** test OK */
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.ejecutarStopDebit(unPagoPendiente);
        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertFalse(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respuestaManager.getRespuesta().getId()));
    }

    /**
     * Test de ejecutar Stop debit de debito Pago Mis Cuentas. caso:
     * PAGOMISCUENTASDEBITO Verificar estado de respuesta en OK.
     * 
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void ejecutarStopDebit_PAGOMISCUENTASDEBITO_OK() throws ServiceException {
        /** incializacion */
        this.contador.reset();
        Cuenta cuentaDolares = new Cuenta();
        cuentaDolares.setCbu("1111111111");
        Cuenta cuentaPesos = new Cuenta();
        cuentaPesos.setCbu("222222222");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaDolares);
        cuentas.add(cuentaPesos);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        respuestaResultadoTransaccion.setRespuestaVacia(false);
        respuestaResultadoTransaccion.setEstadoRespuesta(EstadoRespuesta.OK);
        Date fechatran = new Date();
        respuestaResultadoTransaccion.getRespuesta().setFechaTransaccion(fechatran);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1251");
        mensaje.setMensaje(
                "<b>¡Listo!</b><p>La solicitud de stop debit para el débito automático de {0} se realizó con éxito.</p> ");
        unPagoPendiente.setTipoPago("PAGOMISCUENTASDEBITO");

        /** inicializacion mocks local */
        Mockito.when(sesionParametros.getPagoPendienteView()).thenReturn(unPagoPendiente);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(this.respuestaPagosPendientesSesion);
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(stopDebitService.ejecutarStopDebitoEnCuenta(Matchers.any(PagoPendiente.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaResultadoTransaccion);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        /** test OK */
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.ejecutarStopDebit(unPagoPendiente);
        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertFalse(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respuestaManager.getRespuesta().getId()));
    }

    /**
     * Solicitud baja debito automatico test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @SuppressWarnings("static-access")
    @Test
    public void solicitudBajaDebitoAutomaticoTest() throws ServiceException {
        ContextHolder contextHolder = new ContextHolder();
        ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
        contextHolder.setContext(applicationContext);
        when(applicationContext.getBean(Matchers.anyString())).thenReturn(new ContadorIntentos());

        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(this.respuestaPagosPendientesSesion);
        AbstractCuenta abstractCuenta = Mockito.mock(AbstractCuenta.class);
        Mockito.when(abstractCuenta.getAlias()).thenReturn("Alias mock");
        Mockito.when(abstractCuenta.getTipoCuentaEnum()).thenReturn(TipoCuenta.CAJA_AHORRO_PESOS);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(abstractCuenta);

        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("000");
        mensaje.setMensaje("Mensaje");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        /** test OK */
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.solicitarBajaAdhesion(unPagoPendiente);

        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertFalse(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respuestaManager.getRespuesta().getId()));

        // verificaciones
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.SOLICITUD_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        /** test no OK */
        this.unPagoPendiente.setId("nulo");
        respuestaManager = pagosManager.solicitarBajaAdhesion(unPagoPendiente);

        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertTrue(respuestaManager.isRespuestaVacia());
        Assert.assertNull(respuestaManager.getRespuesta());

        // verificaciones
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.SOLICITUD_BAJA_ADHESION_DEBITOAUTOMATICO_ADDI,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Ejecutar baja adhesion debito automatico de servicios adheridos. Test para
     * {@link PagosManagerImpl}
     *
     * @throws ServiceException
     *             the service exception
     * @see PagosManager
     */
    @Test
    public void ejecutarBajaAdhesionDebitoAutomatico() throws ServiceException {
        // mocks init
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(this.respuestaPagosPendientesSesion);
        ContadorIntentos contador = new ContadorIntentos();
		contador.setIdView("1", 2, "Superado intentos");
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        AbstractCuenta abstractCuenta = Mockito.mock(AbstractCuenta.class);
        Mockito.when(abstractCuenta.getAlias()).thenReturn("Alias mock");
        Mockito.when(abstractCuenta.getTipoCuentaEnum()).thenReturn(TipoCuenta.CAJA_AHORRO_PESOS);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(new Cuenta());
        Mockito.when(this.pagosService.ejecutarBajaAdhesionDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(this.respuestaResultadoTransaccion);
        
        

        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("000");
        mensaje.setMensaje("Mensaje");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // test OK
        this.respuestaResultadoTransaccion.setEstadoRespuesta(EstadoRespuesta.OK);
        Date fechatran = new Date();
        this.respuestaResultadoTransaccion.getRespuesta().setFechaTransaccion(fechatran);
        Respuesta<PagoPendienteView> respuestaManager = pagosManager.ejecutarBajaAdhesion(unPagoPendiente);

        // TODO: corregir el caso OK
        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertFalse(respuestaManager.isRespuestaVacia());
        Assert.assertNotNull(respuestaManager.getRespuesta());
        Assert.assertTrue(unPagoPendiente.getId().equals(respuestaManager.getRespuesta().getId()));

        // test no OK
        this.unPagoPendiente.setId("nulo");
        respuestaManager.setRespuestaVacia(true);
        respuestaManager = pagosManager.ejecutarBajaAdhesion(unPagoPendiente);

        Assert.assertNotNull(respuestaManager);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertTrue(respuestaManager.isRespuestaVacia());
        Assert.assertNull(respuestaManager.getRespuesta());

        // test Exception
        this.unPagoPendiente.setId(null);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(null);

        respuestaManager = pagosManager.ejecutarBajaAdhesion(unPagoPendiente);
        Assert.assertTrue(EstadoRespuesta.ERROR.equals(respuestaManager.getEstadoRespuesta()));
        Assert.assertTrue(respuestaManager.isRespuestaVacia());
        Assert.assertNull(respuestaManager.getRespuesta());
    }

    /**
     * Gets the pagos totales OK.
     *
     * @author emilio.watemberg
     * @return the pagos totales OK
     */
    @Test
    public void getPagosTotalesOKenSesion() {

        Respuesta<PagosPendientesView> respuestaEsperada;

        Respuesta<List<CuentaPagoMisCuentas>> respuestaCuentaPagoMisCuentasOK = new Respuesta<List<CuentaPagoMisCuentas>>();
        respuestaCuentaPagoMisCuentasOK.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = new Cliente();

        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("000");
        mensaje.setMensaje("Mensaje");
        respuestaMensaje.setRespuesta(mensaje);

        respuestaPagosPendientesSesion.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagosPendientesSesion);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
        		.thenReturn(new ModuloPermiso());

        Calendar cal = Calendar.getInstance();
        String mes = "02";
        String anioActual = new Integer(cal.get(Calendar.YEAR)).toString();
        ConsultaPagosView consultaPagosView = new ConsultaPagosView();
        consultaPagosView.setAnio(anioActual);
        consultaPagosView.setMes(mes);

        respuestaEsperada = pagosManager.getPagosTotales(consultaPagosView);

        Assert.assertNotNull(respuestaEsperada.getRespuesta());

    }

    /**
     * Gets the pagos totales para test de recarga, DTF 9775.
     *
     * @author Manuel.Vargas
     * @return the pagos totales OK, con recarga.
     * @throws BusinessException
     *             the business exception
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getPagosTotalesRecarga() throws BusinessException, ServiceException {
        Cuenta cuentaDolares = new Cuenta();
        cuentaDolares.setCbu("1111111111");
        cuentaDolares.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        Cuenta cuentaPesos = new Cuenta();
        cuentaPesos.setCbu("222222222");
        cuentaPesos.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaDolares);
        cuentas.add(cuentaPesos);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);

        this.respuestaPagosPendientesSesion.setEstadoRespuesta(EstadoRespuesta.OK);

        MedioPago medioPago = new MedioPago();
        medioPago.setNombreFantasia("NombreF");
        medioPago.setPescodigorubro("RCEL");
        Respuesta<MedioPago> mediosPagoRta = new Respuesta<MedioPago>();
        mediosPagoRta.setRespuesta(medioPago);
        mediosPagoRta.setEstadoRespuesta(EstadoRespuesta.OK);
        mediosPagoRta.setRespuestaVacia(false);

        Respuesta<List<CuentaPagoMisCuentas>> respuestaCuentaPagoMisCuentasERROR = new Respuesta<List<CuentaPagoMisCuentas>>();
        respuestaCuentaPagoMisCuentasERROR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaCuentaPagoMisCuentasERROR.setItemMensajeRespuesta(null);
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("000");
        mensaje.setMensaje("Mensaje");
        respuestaMensaje.setRespuesta(mensaje);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        this.respuestaPagosPendientesSesion.setEstadoRespuesta(EstadoRespuesta.OK);
        //
        /** The respuesta pagos pendientes sesion para recarga. */
        List<PagoPendiente> listaPagosPendientesRec = new ArrayList<PagoPendiente>();
        Respuesta<List<PagoPendiente>> respuestaPagosPendientesSesionRec = new Respuesta<List<PagoPendiente>>();
        PagoPendiente pagoPendienteRec = new PagoPendiente();
        pagoPendienteRec.setId(832163120110033379L);
        pagoPendienteRec.setNombreEmpresa("ACA SEGUROS");
        pagoPendienteRec.setCodigoClienteEmpresa("12341234123");
        pagoPendienteRec.setCuitEmpresa("31242764449");
        pagoPendienteRec.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL);
        pagoPendienteRec.setImporte(new BigDecimal("123.50"));
        pagoPendienteRec.setDivisa(DivisaEnum.PESO);
        pagoPendienteRec.setVencimiento(null);
        pagoPendienteRec.setNombreEmpresaIatx("SEGURO");
        pagoPendienteRec.setNumeroCuentaBancoPesos(new Integer("2344532"));
        pagoPendienteRec.setNumeroCuentaBancoDolares(new Integer("2344532"));
        pagoPendienteRec.setTipoCuentaTarjeta(TipoCuentaTarjeta.TIPOCTA_VISA);
        DatosPagoAutomaticoEntity datosPagoAutomaticoRec = new DatosPagoAutomaticoEntity();
        datosPagoAutomaticoRec.setTope(new BigDecimal("999.99"));
        datosPagoAutomaticoRec.setIdentificacionCuenta(new IdentificacionCuenta("123-987654/3"));
        datosPagoAutomaticoRec.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS);
        pagoPendienteRec.setDatosPagoAutomatico(datosPagoAutomaticoRec);
        listaPagosPendientesRec.add(pagoPendienteRec);
        respuestaPagosPendientesSesionRec.setRespuesta(listaPagosPendientesRec);
        respuestaPagosPendientesSesionRec.setEstadoRespuesta(EstadoRespuesta.OK);
        //
        AbstractCuenta abstractCuenta = Mockito.mock(AbstractCuenta.class);
        Mockito.when(abstractCuenta.getAlias()).thenReturn("Alias mock");
        Mockito.when(abstractCuenta.getTipoCuentaEnum()).thenReturn(TipoCuenta.CAJA_AHORRO_PESOS);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagosPendientesSesionRec);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(mediosPagoRta);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(abstractCuenta);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
				.thenReturn(new ModuloPermiso());
        //
        Calendar cal = Calendar.getInstance();
        String mes = "02";
        String anioActual = new Integer(cal.get(Calendar.YEAR)).toString();
        ConsultaPagosView consultaPagosView = new ConsultaPagosView();
        consultaPagosView.setAnio(anioActual);
        consultaPagosView.setMes(mes);
        // TEST
        Respuesta<PagosPendientesView> respuestaEsperada = pagosManager.getPagosTotales(consultaPagosView);

        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaEsperada.getEstadoRespuesta()));
        Assert.assertFalse(respuestaEsperada.isRespuestaVacia());
        Assert.assertNotNull(respuestaEsperada.getRespuesta());
    }

    /**
     * Solicitud adhesion pago automatico test. No funciona correctamente porque el
     * contador recurre a una clase estatica. DTF 10216
     * 
     * @author Manuel.Vargas
     */
    @Test
    public void solicitudAdhesionPagoAutomaticoTest() {
        Respuesta<PagoPendienteView> respuestaOK = new Respuesta<PagoPendienteView>();
        Respuesta<PagoPendienteView> respuestaOKEsperada = new Respuesta<PagoPendienteView>();
        //
        respuestaOKEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOKEsperada.setItemMensajeRespuesta(null);
        //
        PagoPendienteView pagoPendiente = new PagoPendienteView("832163120110033379");
        pagoPendiente.setCodigoEmpresa("30500143297");
        pagoPendiente.setNombreEmpresa("ACA SEGUROS");
        pagoPendiente.setCodigoClienteEmpresa("12341234123");
        pagoPendiente.setVencimiento("29/08/2016");
        pagoPendiente.setImporte("230,00");
        pagoPendiente.setImporteUSS("0,00");
        pagoPendiente.setMoneda("$");
        pagoPendiente.setPagoAutomatico(true);
        pagoPendiente.setIdentificacionFactura("");
        pagoPendiente.setTipoPago("SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA");
        pagoPendiente.setTipoPagoDescripcion("Débito Automático en Cuenta Pago Total");
        pagoPendiente.setEditable(false);
        pagoPendiente.setIcono("a");
        pagoPendiente.setTooltip("Este pago se debitará automáticamente todos los meses");
        pagoPendiente.setDatosAdicionales("");
        pagoPendiente.setInformacionAdicional("");
        pagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        pagoPendiente.setMedioDePago(medioDePago);
        pagoPendiente.setImporteLimiteAdhesion("700,00");
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagosPendientesSesion);
        //

        respuestaOK = pagosManager.solicitarAdhesionPagoAuto(pagoPendiente);

        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaOK.getEstadoRespuesta()));
    }

    /**
     * Solicitar adhesion debito automatico.
     */
    @Test
    public void solicitarAdhesionDebitoAutomatico() {
        Respuesta<PagoPendienteView> respuestaOK = new Respuesta<PagoPendienteView>();
        Respuesta<PagoPendienteView> respuestaOKEsperada = new Respuesta<PagoPendienteView>();
        //
        respuestaOKEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOKEsperada.setItemMensajeRespuesta(null);
        //
        PagoPendienteView pagoPendiente = new PagoPendienteView("832163120110033379");
        pagoPendiente.setCodigoEmpresa("30500143297");
        pagoPendiente.setNombreEmpresa("ACA SEGUROS");
        pagoPendiente.setCodigoClienteEmpresa("12341234123");
        pagoPendiente.setVencimiento("29/08/2016");
        pagoPendiente.setImporte("230,00");
        pagoPendiente.setImporteUSS("0,00");
        pagoPendiente.setMoneda("$");
        pagoPendiente.setPagoAutomatico(true);
        pagoPendiente.setIdentificacionFactura("");
        pagoPendiente.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL.getNombreTipoDePago());
        pagoPendiente.setEditable(false);
        pagoPendiente.setIcono("p");
        pagoPendiente.setDatosAdicionales("");
        pagoPendiente.setInformacionAdicional("");
        pagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        pagoPendiente.setMedioDePago(medioDePago);
        pagoPendiente.setImporteLimiteAdhesion("700,00");
        //

        respuestaOK = pagosManager.solicitarAdhesionDebitoAutomatico(pagoPendiente);

        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaOK.getEstadoRespuesta()));
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.SOLICITUD_ADHESION_DEBITO_AUTOMATICO_CALENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Solicitar adhesion pago automatico.
     */
    @Test
    public void solicitarAdhesionPagoAutomatico() {

        // when
        Respuesta<PagoPendienteView> respuestaOK = new Respuesta<PagoPendienteView>();
        Respuesta<PagoPendienteView> respuestaOKEsperada = new Respuesta<PagoPendienteView>();

        respuestaOKEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOKEsperada.setItemMensajeRespuesta(null);

        PagoPendienteView pagoPendiente = new PagoPendienteView("832163120110033379");
        pagoPendiente.setCodigoEmpresa("30500143297");
        pagoPendiente.setNombreEmpresa("ACA SEGUROS");
        pagoPendiente.setCodigoClienteEmpresa("12341234123");
        pagoPendiente.setVencimiento("29/08/2016");
        pagoPendiente.setImporte("230,00");
        pagoPendiente.setImporteUSS("0,00");
        pagoPendiente.setMoneda("$");
        pagoPendiente.setPagoAutomatico(true);
        pagoPendiente.setIdentificacionFactura("");
        pagoPendiente.setTipoPago(TipoDePagoEnum.PAGOMISCUENTASPUNTUAL.getNombreTipoDePago());
        pagoPendiente.setEditable(false);
        pagoPendiente.setIcono("p");
        pagoPendiente.setDatosAdicionales("");
        pagoPendiente.setInformacionAdicional("");
        pagoPendiente.setBotonHabilitado(false);
        IdentificacionCuentaView medioDePago = new IdentificacionCuentaView();
        medioDePago.setAliasDeCuenta("Con alias");
        medioDePago.setNumeroDeCuenta("033-361253/2");
        medioDePago.setTipoDeCuenta("Cuenta única");
        pagoPendiente.setMedioDePago(medioDePago);
        pagoPendiente.setImporteLimiteAdhesion("700,00");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        respuestaOK = pagosManager.solicitarAdhesionPagoAutomatico(pagoPendiente);

        // expected
        Assert.assertTrue(EstadoRespuesta.OK.equals(respuestaOK.getEstadoRespuesta()));
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.SOLICITUD_ADHESION_PAGO_AUTOMATICO_CALENDARIO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }
}
