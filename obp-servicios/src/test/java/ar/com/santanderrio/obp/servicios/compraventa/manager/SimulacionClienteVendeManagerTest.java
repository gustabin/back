package ar.com.santanderrio.obp.servicios.compraventa.manager;

import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.SimulacionClienteVendeBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.web.entity.ContinuarCompraVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.ConfiguracionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.SimulacionCompraVentaManager;
import ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl.SimulacionCompraVentaManagerImpl;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class SimulacionClienteVendeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SimulacionClienteVendeManagerTest {

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The simulacion cliente venta BO. */
    @Mock
    private SimulacionClienteVendeBO simulacionClienteVentaBO;

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

    /** The manager. */
    @InjectMocks
    private SimulacionCompraVentaManager manager = new SimulacionCompraVentaManagerImpl();

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
    private SesionParametros sesion;

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Continuar venta dolares ok test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void continuarVentaDolaresOkTest() throws BusinessException {
        ContinuarCompraVentaEntity confirmarClienteCompra = new ContinuarCompraVentaEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");

        Respuesta<SimulacionCompraVentaDTO> resSimulacion = new Respuesta<SimulacionCompraVentaDTO>();
        resSimulacion.setEstadoRespuesta(EstadoRespuesta.OK);
        resSimulacion.setRespuesta(getResumenCompraVentaDTO());
        resSimulacion.getRespuesta().setEsCompra(true);
        resSimulacion.getRespuesta().setImporteCompraDolar(new BigDecimal(43.50));

        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(simulacionClienteVentaBO.obtenerSimulacionClienteVende(Matchers.any(ParametrosSimulacion.class)))
                .thenReturn(resSimulacion);
        Respuesta<SimulacionCompraVentaDolarView> res = manager.continuarVentaDolares(confirmarClienteCompra);

        Assert.assertEquals(res.getRespuesta().getCotizacion(), "15,35");
        Assert.assertEquals(true, res.getRespuesta().getEsCompra());
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Continuar venta dolares exception test.
     */
    @Test
    public void continuarVentaDolaresExceptionTest() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDolarView> res = manager.continuarVentaDolares(new ContinuarCompraVentaEntity());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta simulacion error test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearRespuestaSimulacionErrorTest() throws BusinessException {
        ContinuarCompraVentaEntity confirmarClienteCompra = new ContinuarCompraVentaEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");

        Respuesta<SimulacionCompraVentaDTO> resSimulacion = new Respuesta<SimulacionCompraVentaDTO>();
        resSimulacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resSimulacion.setRespuesta(getResumenCompraVentaDTO());
        resSimulacion.getRespuesta().setEsCompra(true);
        resSimulacion.getRespuesta().setImporteCompraDolar(new BigDecimal(43.50));

        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(simulacionClienteVentaBO.obtenerSimulacionClienteVende(Matchers.any(ParametrosSimulacion.class)))
                .thenReturn(resSimulacion);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDolarView> res = manager.continuarVentaDolares(confirmarClienteCompra);

        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta simulacion compra business exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearRespuestaSimulacionCompraBusinessExceptionTest() throws BusinessException {
        ContinuarCompraVentaEntity confirmarClienteCompra = new ContinuarCompraVentaEntity();
        confirmarClienteCompra.setNumeroCuentaOrigen("12-12");
        confirmarClienteCompra.setNumeroCuentaDestino("13-13");

        Respuesta<SimulacionCompraVentaDTO> resSimulacion = new Respuesta<SimulacionCompraVentaDTO>();
        resSimulacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resSimulacion.setRespuesta(getResumenCompraVentaDTO());
        resSimulacion.getRespuesta().setEsCompra(true);
        resSimulacion.getRespuesta().setImporteCompraDolar(new BigDecimal(43.50));

        Mockito.when(sesionCliente.getCliente()).thenReturn(getCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(getCuenta());
        Mockito.when(compraOVentaDolaresUtil.quitarSimboloMonedaAImporte(Matchers.anyString())).thenReturn("12.22");
        Mockito.when(simulacionClienteVentaBO.obtenerSimulacionClienteVende(Matchers.any(ParametrosSimulacion.class)))
                .thenThrow(new BusinessException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDolarView> res = manager.continuarVentaDolares(confirmarClienteCompra);

        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Gets the resumen compra venta DTO.
     *
     * @return the resumen compra venta DTO
     */
    private SimulacionCompraVentaDTO getResumenCompraVentaDTO() {
        SimulacionCompraVentaDTO comprobanteCompraVentaDTO = new SimulacionCompraVentaDTO();
        comprobanteCompraVentaDTO.setEsCompra(true);
        comprobanteCompraVentaDTO.setImporteCompraDolar(new BigDecimal(2.5));
        comprobanteCompraVentaDTO.setImporteDebitarPesos(new BigDecimal(1.3));
        comprobanteCompraVentaDTO.setNumeroCuentaOrigen("1245678978");
        comprobanteCompraVentaDTO.setTipoCuentaOrigen("T");
        comprobanteCompraVentaDTO.setNumeroCuentaDestino("6371834917");
        comprobanteCompraVentaDTO.setTipoCuentaDestino("D");
        comprobanteCompraVentaDTO.setCotizacion(new BigDecimal(15.35));
        comprobanteCompraVentaDTO.setFecha(new Date());
        comprobanteCompraVentaDTO.setLegales("legalUno");
        comprobanteCompraVentaDTO.setNumeroComprobante("123178261492978");
        comprobanteCompraVentaDTO.setNumeroOperacion("15");
        comprobanteCompraVentaDTO.setNupTipo("T");
        comprobanteCompraVentaDTO.setNupNumDoc("123124141515");

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
