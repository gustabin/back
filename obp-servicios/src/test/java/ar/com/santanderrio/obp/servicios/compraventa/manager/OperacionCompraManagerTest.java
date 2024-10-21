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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.OperacionClienteCompraBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ConfirmarClienteCompraEntity;
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

/**
 * The Class OperacionCompraManagerTest.
 *
 * @author florencia.n.martinez
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class OperacionCompraManagerTest {

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The operacion cliente compra BO. */
    @Mock
    private OperacionClienteCompraBO operacionClienteCompraBO;

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

    /** The Operacion cliente compra manager. */
    @InjectMocks
    private OperacionCompraVentaManager OperacionClienteCompraManager = new OperacionCompraVentaManagerImpl();

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();
    
    @Mock
	private SesionParametros sesionParametros;

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Genera respuesta OK del objeto ComprobanteCompraVentaView para la compra.
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
     * Operacion cliente compra respuesta ok test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void operacionClienteCompraRespuestaOkTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setMontoCache("2800");

        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        cliente.setTipoDocumento("N");
        cliente.setDni("37866881");
        
        Respuesta<ComprobanteCompraVentaDTO> resComprobante = new Respuesta<ComprobanteCompraVentaDTO>();
        resComprobante.setEstadoRespuesta(EstadoRespuesta.OK);
        resComprobante.setRespuesta(getResumenCompraVentaDTO());
        resComprobante.getRespuesta().setEsCompra(true);
        resComprobante.getRespuesta().setImporteDolarAcreditado(new BigDecimal(43.50));

        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La prueba de la {0} dio {1} y es OK");
        resMensaje.setRespuesta(mensaje);

        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn("12-12");
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteCompraBO.operacionClienteCompra(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("1814A4608C2D2BA58B4AFE31EAECE9CA");
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager
                .operacionClienteCompra(confirmarClienteCompra);

        Assert.assertEquals("La prueba de la compra dio 43,50 y es OK", respuesta.getRespuesta().getMensajeCompra());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente compra respuesta warning test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void operacionClienteCompraRespuestaWarningTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(false);

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

        Mockito.when(sesionCompraVenta.getContadorCompra()).thenReturn(new ContadorIntentos(2));
        Mockito.when(sesionCompraVenta.getNumeroCuentaOrigen()).thenReturn(null);
        Mockito.when(sesionCompraVenta.getNumeroCuentaDestino()).thenReturn("13-13");
        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(operacionClienteCompraBO.operacionClienteCompra(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(configuracionCompraVentaManager.obtenerCotizacionParaCompra(Matchers.any(Cotizacion.class)))
                .thenReturn(resCompra);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("413EDA6BF3F112D04D8855832F2ECF44");
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager
                .operacionClienteCompra(confirmarClienteCompra);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        Assert.assertEquals("15,00", respuesta.getRespuesta().getCotizacion());
    }

    /**
     * Operacion cliente compra respuesta warning respuesta vacia test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void operacionClienteCompraRespuestaWarningRespuestaVaciaTest()
            throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setMontoCache("2800");

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
        Mockito.when(operacionClienteCompraBO.operacionClienteCompra(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("1814A4608C2D2BA58B4AFE31EAECE9CA");
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager
                .operacionClienteCompra(confirmarClienteCompra);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente compra respuesta error test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void operacionClienteCompraRespuestaErrorTest() throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setMontoCache("2800");

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
        Mockito.when(operacionClienteCompraBO.operacionClienteCompra(Matchers.any(ParametrosOperacion.class)))
                .thenReturn(resComprobante);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("1814A4608C2D2BA58B4AFE31EAECE9CA");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager
                .operacionClienteCompra(confirmarClienteCompra);

        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Crear respuesta operacion cliente compra business exception test.
     *
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearRespuestaOperacionClienteCompraBusinessExceptionTest()
            throws CompraVentaDolaresException, BusinessException {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setMontoCache("2800");

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
        Mockito.when(operacionClienteCompraBO.operacionClienteCompra(Matchers.any(ParametrosOperacion.class)))
                .thenThrow(new BusinessException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("1814A4608C2D2BA58B4AFE31EAECE9CA");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager
                .operacionClienteCompra(confirmarClienteCompra);
        OperacionClienteCompraManager.generarEstadisticaComprobanteClienteCompra();
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Operacion cliente compra exception.
     */
    @Test
    public void operacionClienteCompraException() {
        ConfirmarClienteCompraEntity confirmarClienteCompra = new ConfirmarClienteCompraEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");
        confirmarClienteCompra.setIsDolar(true);
        confirmarClienteCompra.setMontoCache("2800");
        
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn("1814A4608C2D2BA58B4AFE31EAECE9CA");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaView> respuesta = OperacionClienteCompraManager.operacionClienteCompra(confirmarClienteCompra);
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
