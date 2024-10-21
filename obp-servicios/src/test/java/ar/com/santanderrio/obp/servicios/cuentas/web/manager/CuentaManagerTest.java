package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lowagie.text.pdf.codec.GifImage;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.impl.LegalBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.impl.ConsultarSucursalesBOImpl;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.AliasCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.SaldosConsolidadosCuentaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentaManagerTest {

    /** The Constant CUENTA_CORRIENTE. */
    private static final String CUENTA_CORRIENTE = "0";

    /** The Constant CAJA_AHORROS. */
    private static final String CAJA_AHORROS = "1";

    /** The Constant CUENTA_NO_PESOS. */
    private static final String CUENTA_NO_PESOS = "3";

    /** The cuenta manager. */
    @InjectMocks
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManagerImpl estadisticaManager;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The session detalle cuentas. */
    @Mock
    private SessionDetalleCuentas sessionDetalleCuentas;

    /** The sesion resumen cuenta. */
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;

    /** The alias cuenta. */
    @Mock
    private AliasCuentaManager aliasCuenta;

    /** The resumen mensual cuenta BO. */
    @Mock
    private ResumenMensualCuentaBO resumenMensualCuentaBO;

    /** The alias cuenta BO. */
    @Mock
    private AliasCuentaBO aliasCuentaBO = new AliasCuentaBOImpl();

    /** The legal BO. */
    @Mock
    private LegalBO legalBO = new LegalBOImpl();

    /** The consultar sucursales BO. */
    @Mock
    private ConsultarSucursalesBO consultarSucursalesBO = new ConsultarSucursalesBOImpl();

    /** The session movimientos. */
    @Mock
    private SessionMovimientos sessionMovimientos;

    /** The Constant SOBREGIRO. */
    protected static final String SOBREGIRO = "S";

    /** The Constant CAJA_AHORRO_PESOS. */
    protected static final String CAJA_AHORRO_PESOS = "Caja de ahorro en $";

    /** The Constant CUENTA_CORRIENTE_PESOS. */
    protected static final String CUENTA_CORRIENTE_PESOS = "Cuenta corriente en $";

    /** The Constant CUENTA_CORRIENTE_DOLARES. */
    protected static final String CUENTA_CORRIENTE_DOLARES = "Cuenta corriente en u$s";

    /**
     * Obtener cuentas test.
     *
     * @throws BusinessException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasTest() throws BusinessException, DAOException {

        Cliente cliente = crearCliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = crearRespuestaListResumen(detalleCuentas);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de favorito");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaDetalleCuentas);

        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setNroDocumento("32323232333");
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(detalleDocumentoDTO);
        when(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumentoDTO)).thenReturn("23-32323232-3");

        Respuesta<Sucursal> respSucursal = crearRespuestaSucursal();
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respSucursal);

        Respuesta<Integer> respuestaCuentaSeleccionada = new Respuesta<Integer>();
        respuestaCuentaSeleccionada.setRespuesta(0);
        respuestaCuentaSeleccionada.setEstadoRespuesta(EstadoRespuesta.OK);
        when(cuentaBO.obtenerCuentaSeleccionada(respuestaDetalleCuentas)).thenReturn(respuestaCuentaSeleccionada);

        Respuesta<SaldosConsolidadosCuentaDTO> respuestaObtenerSaldoConsolidado = new Respuesta<SaldosConsolidadosCuentaDTO>();
        respuestaObtenerSaldoConsolidado.setEstadoRespuesta(EstadoRespuesta.OK);
        SaldosConsolidadosCuentaDTO saldoConsolidado = crearSaldoConsolidado();
        respuestaObtenerSaldoConsolidado.setRespuesta(saldoConsolidado);
        when(cuentaBO.obtenerSaldoConsolidadoCliente(detalleCuentas)).thenReturn(respuestaObtenerSaldoConsolidado);

        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCBU.setRespuesta(new DetalleCBUView());
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString())).thenReturn(detalleCBU);
        
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("", "");

        assertNotNull(respuesta);
        SelectorCuentasView cuentas = respuesta.getRespuesta();
        assertEquals(cuentas.getCuentas().get(1).getDescripcionTipoCuenta(), CAJA_AHORRO_PESOS);
        assertEquals(cuentas.getCuentas().get(2).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_PESOS);
        assertEquals(cuentas.getCuentas().get(3).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_DOLARES);

    }

    /**
     * Descubierto disponible positivo negativo.
     *
     * @throws BusinessException the business exception
     * @throws DAOException the DAO exception
     */
    @Test
    public void descubiertoDisponiblePositivoNegativo() throws BusinessException, DAOException {
        Cliente cliente = crearCliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);

        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "0001", SOBREGIRO, BigDecimal.ONE));
        detalleCuentas
                .add(getDetalleCuentas("000", "000022", CUENTA_CORRIENTE, "0002", SOBREGIRO, BigDecimal.ONE.negate()));
        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = crearRespuestaListResumen(detalleCuentas);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de favorito");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaDetalleCuentas);

        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setNroDocumento("32323232333");
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(detalleDocumentoDTO);
        when(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumentoDTO)).thenReturn("23-32323232-3");

        Respuesta<Sucursal> respSucursal = crearRespuestaSucursal();
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respSucursal);

        Respuesta<Integer> respuestaCuentaSeleccionada = new Respuesta<Integer>();
        respuestaCuentaSeleccionada.setRespuesta(0);
        respuestaCuentaSeleccionada.setEstadoRespuesta(EstadoRespuesta.OK);
        when(cuentaBO.obtenerCuentaSeleccionada(respuestaDetalleCuentas)).thenReturn(respuestaCuentaSeleccionada);

        Respuesta<SaldosConsolidadosCuentaDTO> respuestaObtenerSaldoConsolidado = new Respuesta<SaldosConsolidadosCuentaDTO>();
        respuestaObtenerSaldoConsolidado.setEstadoRespuesta(EstadoRespuesta.OK);
        SaldosConsolidadosCuentaDTO saldoConsolidado = crearSaldoConsolidado();
        respuestaObtenerSaldoConsolidado.setRespuesta(saldoConsolidado);
        when(cuentaBO.obtenerSaldoConsolidadoCliente(detalleCuentas)).thenReturn(respuestaObtenerSaldoConsolidado);

        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.OK);
        detalleCBU.setRespuesta(new DetalleCBUView());
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString())).thenReturn(detalleCBU);
    
        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("", "");
        assertNotNull(respuesta);
        SelectorCuentasView cuentas = respuesta.getRespuesta();
        assertEquals(cuentas.getCuentas().get(1).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_PESOS);
        assertEquals(cuentas.getCuentas().get(2).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_PESOS);
    }

    /**
     * Obtener cuentas con error iten cuenta.
     *
     * @throws BusinessException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasConErrorItemCuenta() throws BusinessException, DAOException {

        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido1("Lopez");
        cliente.setApellido2("Perez");
        cliente.setDni("2323");
        cliente.setTipoDocumento("N");
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_ITEM_CUENTA.getDescripcion());
        String cuentaTag = "cuentas[000-000001/1]";
        itemMensajeRespuesta.setTag(cuentaTag);
        itemsMensajeRespuesta.add(itemMensajeRespuesta);

        detalleCuentas.add(getDetalleCuentas("000", "000011", CAJA_AHORROS, "1999", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_CORRIENTE, "2214", null, null));
        detalleCuentas.add(getDetalleCuentas("000", "000021", CUENTA_NO_PESOS, "14124", null, null));

        respuestaDetalleCuentas.setRespuesta(detalleCuentas);
        respuestaDetalleCuentas.setRespuestaVacia(false);
        respuestaDetalleCuentas.setEstadoRespuesta(EstadoRespuesta.WARNING);

        respuestaDetalleCuentas.setRespuestaVacia(false);
        respuestaDetalleCuentas.setItemMensajeRespuesta(itemsMensajeRespuesta);

        when(cuentaBO.obtenerInfoCuentas(Matchers.any(Cliente.class))).thenReturn(respuestaDetalleCuentas);

        Respuesta<Integer> respuestaCuentaSeleccionada = new Respuesta<Integer>();

        respuestaCuentaSeleccionada.setRespuesta(0);
        respuestaCuentaSeleccionada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        when(cuentaBO.obtenerCuentaSeleccionada(respuestaDetalleCuentas)).thenReturn(respuestaCuentaSeleccionada);
        Respuesta<DetalleCBUView> detalleCBU = new Respuesta<DetalleCBUView>();
        detalleCBU.setEstadoRespuesta(EstadoRespuesta.ERROR);
        detalleCBU.setRespuesta(new DetalleCBUView());
        when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString())).thenReturn(detalleCBU);

        Respuesta<SaldosConsolidadosCuentaDTO> respuestaObtenerSaldoConsolidado = new Respuesta<SaldosConsolidadosCuentaDTO>();
        respuestaObtenerSaldoConsolidado.setEstadoRespuesta(EstadoRespuesta.OK);
        SaldosConsolidadosCuentaDTO saldoConsolidado = new SaldosConsolidadosCuentaDTO();
        saldoConsolidado.setSaldoPesos("0");
        saldoConsolidado.setSaldoPesosValue(new BigDecimal(0));
        saldoConsolidado.setSaldoDolares("0");
        saldoConsolidado.setSaldoDolaresValue(new BigDecimal(0));
        respuestaObtenerSaldoConsolidado.setRespuesta(saldoConsolidado);
        when(cuentaBO.obtenerSaldoConsolidadoCliente(detalleCuentas)).thenReturn(respuestaObtenerSaldoConsolidado);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de favorito");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setNroDocumento("32323232333");
        when(aliasCuentaBO.obtenerDocumentoValidoDTO(cliente)).thenReturn(detalleDocumentoDTO);
        when(aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumentoDTO)).thenReturn("23-32323232-3");

        Respuesta<Sucursal> respSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("TRIBUNALES - 033");
        respSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        respSucursal.setRespuesta(sucursal);
        when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respSucursal);

        Respuesta<SelectorCuentasView> respuesta = cuentaManager.getCuentas("", "");

        assertNotNull(respuesta);
        SelectorCuentasView cuentas = respuesta.getRespuesta();
        assertEquals(cuentas.getCuentas().get(0).getDescripcionTipoCuenta(), "Saldos totales");
        assertEquals(cuentas.getCuentas().get(1).getAlias(), "Cuenta1");
        assertEquals(cuentas.getCuentas().get(1).getDescripcionTipoCuenta(), CAJA_AHORRO_PESOS);
        assertEquals(cuentas.getCuentas().get(2).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_PESOS);
        assertEquals(cuentas.getCuentas().get(3).getDescripcionTipoCuenta(), CUENTA_CORRIENTE_DOLARES);
        assertNotNull(respuesta.getItemsMensajeRespuesta());
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), "ERROR_ITEM_CUENTA");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTag(), "cuentas[000-000001/1]");
    }

    /**
     * Gets the detalle cuentas.
     *
     * @param sucursal
     *            the sucursal
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @param tipoCuenta
     *            the tipo cuenta
     * @param saldo
     *            the saldo
     * @param sobregiro
     *            the sobregiro
     * @param limiteDescubierto
     *            the limite descubierto
     * @return the detalle cuentas
     */
    private ResumenDetalleCuenta getDetalleCuentas(String sucursal, String nroCuentaProducto, String tipoCuenta,
            String saldo, String sobregiro, BigDecimal limiteDescubierto) {
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        resumenDetalleCuenta.setNroSucursal(sucursal);
        resumenDetalleCuenta.setNroCuentaProducto(nroCuentaProducto);
        resumenDetalleCuenta.setTipoCuenta(tipoCuenta);
        resumenDetalleCuenta.setCuentaCerrada(false);
        resumenDetalleCuenta.setCuentaUnica(false);
        resumenDetalleCuenta.setCuentaPrincipal(true);
        resumenDetalleCuenta.setAlias("Cuenta1");
        resumenDetalleCuenta.setFechaDesdeMovimiento("10/10/2010");
        resumenDetalleCuenta.setFechaHastaMovimiento("10/10/2010");
        resumenDetalleCuenta.setIndicadorSobregiro(sobregiro);
        resumenDetalleCuenta.setLimiteDescubierto(limiteDescubierto);
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setApellido1("Luis");
        cliente.setTipoDocumento("N");
        cliente.setDni("33333333");
        resumenDetalleCuenta.setCliente(cliente);

        if (CUENTA_CORRIENTE.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCuentaCorrientePesos(new BigDecimal(2244));
        } else if (CAJA_AHORROS.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCajaAhorroPesos(new BigDecimal(2244));
        } else if (CUENTA_NO_PESOS.equals(tipoCuenta)) {

            resumenDetalleCuenta.setSaldoCuentaSueldoPesos(new BigDecimal(2244));
        }
        return resumenDetalleCuenta;
    }

    /**
     * Crear cliente.
     *
     * @return the cliente
     */
    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido1("Lopez");
        cliente.setApellido2("Perez");
        cliente.setDni("2323");
        cliente.setTipoDocumento("N");
        return cliente;
    }

    /**
     * Crear saldo consolidado.
     *
     * @return the saldos consolidados cuenta DTO
     */
    private SaldosConsolidadosCuentaDTO crearSaldoConsolidado() {
        SaldosConsolidadosCuentaDTO saldoConsolidado = new SaldosConsolidadosCuentaDTO();
        saldoConsolidado.setSaldoPesos("0");
        saldoConsolidado.setSaldoPesosValue(new BigDecimal(0));
        saldoConsolidado.setSaldoDolares("0");
        saldoConsolidado.setSaldoDolaresValue(new BigDecimal(0));
        return saldoConsolidado;
    }

    /**
     * Crear respuesta list resumen.
     *
     * @param detalleCuentas
     *            the detalle cuentas
     * @return the respuesta
     */
    private Respuesta<List<ResumenDetalleCuenta>> crearRespuestaListResumen(List<ResumenDetalleCuenta> detalleCuentas) {
        Respuesta<List<ResumenDetalleCuenta>> respuestaDetalleCuentas = new Respuesta<List<ResumenDetalleCuenta>>();
        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

        respuestaDetalleCuentas.setRespuesta(detalleCuentas);
        respuestaDetalleCuentas.setRespuestaVacia(false);
        respuestaDetalleCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDetalleCuentas.setRespuestaVacia(false);
        respuestaDetalleCuentas.setItemMensajeRespuesta(itemsMensajeRespuesta);
        return respuestaDetalleCuentas;
    }

    /**
     * Crear respuesta sucursal.
     *
     * @return the respuesta
     */
    private Respuesta<Sucursal> crearRespuestaSucursal() {
        Respuesta<Sucursal> respSucursal = new Respuesta<Sucursal>();
        Sucursal sucursal = new Sucursal();
        sucursal.setDescripcion("TRIBUNALES - 033");
        respSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
        respSucursal.setRespuesta(sucursal);
        return respSucursal;
    }

}
