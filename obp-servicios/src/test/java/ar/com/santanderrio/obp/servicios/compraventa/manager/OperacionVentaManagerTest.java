package ar.com.santanderrio.obp.servicios.compraventa.manager;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteVentaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.VentaUSDDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.Cotizacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.OperacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl.OperacionCompraVentaManagerImpl;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaViewTest;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class OperacionVentaManagerTest.
 *
 * @author dante.omar.olmedo
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class OperacionVentaManagerTest {

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The operacion cliente vende BO. */
    @Mock
    private OperacionClienteVentaBO operacionClienteVendeBO;

    /** The mensaje manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The configuracion compra venta manager. */
    @Mock
    private ConfiguracionCompraVentaManager configuracionCompraVentaManager;

    /** The compra O venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraOVentaDolaresUtil;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The sesion compra venta. */
    @Mock
    private SesionCompraVenta sesionCompraVenta;
    
    @Mock
    private ScompBO altaComprobante;
    
    @Mock
	private SesionParametros sesionParametros;
    

    /** The manager. */
    @InjectMocks
    private OperacionCompraVentaManager manager = new OperacionCompraVentaManagerImpl();

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private RsaManager rsaManager;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Generar respuesta comprobante compra venta view OK para compra.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void generarRespuestaComprobanteCompraVentaViewOKParaCompra() throws CompraVentaDolaresException {
        Respuesta<ComprobanteCompraVentaView> respuesta = respuestaFactory.crearRespuestaOk(
                ComprobanteCompraVentaView.class, ComprobanteCompraVentaViewTest.generarComprobanteCompraView());

        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);
    }

    /**
     * Genera respuesta OK del objeto ComprobanteCompraVentaView para la venta.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void generarRespuestaComprobanteCompraVentaViewOKParaVenta() throws CompraVentaDolaresException {
        Respuesta<ComprobanteCompraVentaView> respuesta = respuestaFactory.crearRespuestaOk(
                ComprobanteCompraVentaView.class, ComprobanteCompraVentaViewTest.generarComprobanteVentaView());

        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(json);
    }

    /**
     * Operacion cliente venta respuesta ok test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void operacionClienteVentaRespuestaOkTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);
        
        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setTipoDocumento("N");
        cliente.setDni("37866881");

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.getRespuesta().setEsCompra(true);
        resComprobante.getRespuesta().setImporteDolaresDebitado(new BigDecimal(43.50));

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");
        resMensaje.setRespuesta(mensaje);

        Mockito.when(sesionCompraVenta.getContadorVenta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals("La prueba de la venta dio 43,50 y es OK", respuesta.getRespuesta().getMensajeCompra());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Operacion cliente venta respuesta ok test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void operacionClienteVentaRespuestaCCOkTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setTipoDocumento("N");
        cliente.setDni("37866881");
        
        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.getRespuesta().setEsCompra(true);
        resComprobante.getRespuesta().setImporteDolaresDebitado(new BigDecimal(43.50));

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");
        resMensaje.setRespuesta(mensaje);

        Cuenta cuenta = getCuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_CORRIENTE_PESOS);
        
        Mockito.when(sesionCompraVenta.getContadorVenta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals("La prueba de la venta dio 43,50 y es OK", respuesta.getRespuesta().getMensajeCompra());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Operacion cliente venta respuesta ok test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void operacionClienteVentaRespuestaCAOkTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.getRespuesta().setEsCompra(true);
        resComprobante.getRespuesta().setImporteDolaresDebitado(new BigDecimal(43.50));

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");
        resMensaje.setRespuesta(mensaje);

        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setTipoDocumento("N");
        cliente.setDni("37866881");
        
        Cuenta cuenta = getCuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        
        Mockito.when(sesionCompraVenta.getContadorVenta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals("La prueba de la venta dio 43,50 y es OK", respuesta.getRespuesta().getMensajeCompra());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Operacion cliente venta respuesta ok test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void operacionClienteVentaRespuestaCUOkTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.getRespuesta().setEsCompra(true);
        resComprobante.getRespuesta().setImporteDolaresDebitado(new BigDecimal(43.50));

        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setTipoDocumento("N");
        cliente.setDni("37866881");
        
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");
        resMensaje.setRespuesta(mensaje);

        Cuenta cuenta = getCuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        
        Mockito.when(sesionCompraVenta.getContadorVenta()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals("La prueba de la venta dio 43,50 y es OK", respuesta.getRespuesta().getMensajeCompra());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente venta respuesta warning test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    @Ignore
    public void operacionClienteVentaRespuestaWarningTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        resComprobante.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        resComprobante.getItemsMensajeRespuesta().get(0)
                .setTipoError(TipoError.ERROR_CAMBIO_COTIZACION_DOLAR.getDescripcion());

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(new Mensaje());
        resMensaje.getRespuesta().setMensaje("La prueba de la {0} dio {1} y es OK");

        Respuesta<CompraVentaDolarView> resCompra = new Respuesta<CompraVentaDolarView>();
        resCompra.setRespuesta(new CompraVentaDolarView());
        resCompra.getRespuesta().setCotizacion(new Double(15));

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn(null);
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(configuracionCompraVentaManager.obtenerCotizacionParaVenta(Matchers.any(Cotizacion.class)))
                .thenReturn(resCompra);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("EED4CD87C3C73467B51D578FE67D580C");
        Respuesta<ActionCode> respuestaRsaManager = new Respuesta<ActionCode>();
        respuestaRsaManager.setRespuesta(ActionCode.ALLOW);
        Mockito.when(rsaManager.analizar(Matchers.any(VentaUSDDTO.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaManager);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente venta respuesta warning respuesta vacia test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    @Ignore
    public void operacionClienteVentaRespuestaWarningRespuestaVaciaTest()
            throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resComprobante.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        resComprobante.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        resComprobante.getItemsMensajeRespuesta().get(0)
                .setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(new Mensaje());
        resMensaje.getRespuesta().setMensaje("La prueba de la {0} dio {1} y es OK");

        Respuesta<CompraVentaDolarView> resCompra = new Respuesta<CompraVentaDolarView>();
        resCompra.setRespuesta(new CompraVentaDolarView());
        resCompra.getRespuesta().setCotizacion(new Double(15));

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn(null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("EED4CD87C3C73467B51D578FE67D580C");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ActionCode> respuestaRsaManager = new Respuesta<ActionCode>();
        respuestaRsaManager.setRespuesta(ActionCode.ALLOW);
        Mockito.when(rsaManager.analizar(Matchers.any(VentaUSDDTO.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaManager);
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente venta respuesta error test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void operacionClienteVentaRespuestaErrorTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);
        
        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resComprobante.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        resComprobante.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        resComprobante.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.DESAFIO.getDescripcion());

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");

        Respuesta<CompraVentaDolarView> resCompra = new Respuesta<CompraVentaDolarView>();
        resCompra.setRespuesta(new CompraVentaDolarView());
        resCompra.getRespuesta().setCotizacion(new Double(15));

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn(null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Crear respuesta operacion cliente venta business exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearRespuestaOperacionClienteVentaBusinessExceptionTest()
            throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);

        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resComprobante.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        resComprobante.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        resComprobante.getItemsMensajeRespuesta().get(0)
                .setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");

        Respuesta<CompraVentaDolarView> resCompra = new Respuesta<CompraVentaDolarView>();
        resCompra.setRespuesta(new CompraVentaDolarView());
        resCompra.getRespuesta().setCotizacion(new Double(15));

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn(null);
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn(null);
        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteVendeBO.operacionClienteVenta(Matchers.any(ParametrosOperacion.class)))
                .thenThrow(new BusinessException());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);
        manager.generarEstadisticaComprobanteClienteVende();
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente venta exception.
     */
    @Test
    public void operacionClienteVentaException() {
        ConfirmarClienteVentaEntity confirmarClienteVenta = new ConfirmarClienteVentaEntity();
        confirmarClienteVenta.setNumeroCuentaOrigen("12-12");
        confirmarClienteVenta.setNumeroCuentaDestino("13-13");
        confirmarClienteVenta.setIsDolar(true);
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("57F2B7A6CFBC9EB1F08E28FF44780869");
        Respuesta<ComprobanteCompraVentaView> respuesta = manager.operacionClienteVende(confirmarClienteVenta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Gets the resumen compra venta DTO.
     *
     * @return the resumen compra venta DTO
     */
    private ComprobanteCompraVentaDTO getResumenCompraVentaDTO() {
        ComprobanteCompraVentaDTO comprobanteCompraVentaDTO = new ComprobanteCompraVentaDTO();
        comprobanteCompraVentaDTO.setEsCompra(true);
        comprobanteCompraVentaDTO.setImporteDolarAcreditado(new BigDecimal(2.5));
        comprobanteCompraVentaDTO.setImportePesosDebitado(new BigDecimal(1.3));
        comprobanteCompraVentaDTO.setCuentaOrigenNumero("1245678978");
        comprobanteCompraVentaDTO.setCuentaOrigenTipo("T");
        comprobanteCompraVentaDTO.setCuentaDestinoNumero("6371834917");
        comprobanteCompraVentaDTO.setCuentaDestinoTipo("D");
        comprobanteCompraVentaDTO.setCotizacionAplicada(new BigDecimal(15.35));
        comprobanteCompraVentaDTO.setFecha(new Date());
        comprobanteCompraVentaDTO.setHora("12");
        comprobanteCompraVentaDTO.setLegalCompraUno("legalUno");
        comprobanteCompraVentaDTO.setLegalCompraDos("legalDos");
        comprobanteCompraVentaDTO.setNumeroComprobante("123178261492978");
        comprobanteCompraVentaDTO.setNumeroOperacion("15");

        return comprobanteCompraVentaDTO;
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    private Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Alias Nombre");
        cuenta.setCliente(getCliente());
        cuenta.setIsFavorita(true);
        cuenta.setNroCuentaProducto("0000000338501392");
        cuenta.setNroSucursal("0033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        return cuenta;
    }

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    private Cliente getCliente() {
        return mock(Cliente.class);
    }
}
