package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.BotonPanicoCompraventaException;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.ConfiguracionCompraVentaDolaresBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.CotizacionDolaresDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ConsultaCotizacionEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ConfiguracionCompraVentaDolaresBOTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ConfiguracionCompraVentaDolaresBOTest {

    /** The cotizacion dolares DAO. */
    @Mock
    private CotizacionDolaresDAO cotizacionDolaresDAO;

    /** The compra venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The configuracion compra venta dolares BO. */
    @InjectMocks
    private ConfiguracionCompraVentaDolaresBO configuracionCompraVentaDolaresBO = new ConfiguracionCompraVentaDolaresBOImpl();

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    private boolean vieneDeCuentas;
    
    @Mock
	private EstadisticaManager estadisticaManager;
    
    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
        vieneDeCuentas = false;
    }

    /**
     * Gets the compra venta dolar inicio.
     *
     * @return the compra venta dolar inicio
     */
    private CompraVentaInicioDTO getCompraVentaDolarInicio() {
        CompraVentaInicioDTO compraVentaDolarInicio = new CompraVentaInicioDTO();
        compraVentaDolarInicio.setAliasCtaDestino(null);
        compraVentaDolarInicio.setAliasCtaOrigen(null);
        compraVentaDolarInicio.setCotizacion(15.33);
        compraVentaDolarInicio.setCotizacionString("15.33");
        compraVentaDolarInicio.setCuentas(null);
        compraVentaDolarInicio.setEsCompra(true);
        compraVentaDolarInicio.setEsVenta(false);
        compraVentaDolarInicio.setImporteDolares(null);
        compraVentaDolarInicio.setImportePesos(null);
        compraVentaDolarInicio.setMostrarNroCtaDestino(true);
        compraVentaDolarInicio.setMostrarNroCtaOrigen(true);
        compraVentaDolarInicio.setNroCuentaOrigen(null);
        compraVentaDolarInicio.setNroCuentaDestino(null);
        compraVentaDolarInicio.setMostrarNroCtaOrigen(true);
        compraVentaDolarInicio.setSaldoCuentaDestino(null);
        compraVentaDolarInicio.setSaldoCuentaOrigen(null);
        compraVentaDolarInicio.setTieneAliasCtaDestino(false);
        compraVentaDolarInicio.setTieneAliasCtaOrigen(false);
        compraVentaDolarInicio.setTieneCtaOrigen(true);
        compraVentaDolarInicio.setTieneCtaDestino(true);
        return compraVentaDolarInicio;
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

    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        Cliente c1 = new Cliente();
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        return c1;
    }

    
    /**
     * Gets the consulta cotizacion.
     *
     * @return the consulta cotizacion
     */
    private ConsultaCotizacionEntity getConsultaCotizacion() {
        ConsultaCotizacionEntity consultaCotizacion = new ConsultaCotizacionEntity();
        consultaCotizacion.setCantDescStatusResultado(null);
        consultaCotizacion.setCeros(null);
        consultaCotizacion.setCotizacionSalida("00153300000");
        consultaCotizacion.setDescripcionStatusResultado(null);
        consultaCotizacion.setId(null);
        consultaCotizacion.setIdSistema(null);
        consultaCotizacion.setImporteSalida("000000000000000");
        consultaCotizacion.setSpredAplicado("000000000000000");
        return consultaCotizacion;
    }

    /**
     * Copiar array cuenta con cuenta test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void copiarArrayCuentaConCuentaTest() throws BusinessException {
        Cliente cliente = new Cliente();
        Cuenta cuenta = getCuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setProductoAltair("03");
        cuenta.setSubproductoAltair("0001");
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);

        Respuesta<List<ResumenDetalleCuenta>> retornoFactory = new Respuesta<List<ResumenDetalleCuenta>>();
        List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        respuestaDetalleCuenta.add(resumenDetalleCuenta);

        retornoFactory.setRespuesta(respuestaDetalleCuenta);
        retornoFactory.setEstadoRespuesta(EstadoRespuesta.OK);
        retornoFactory.setRespuestaVacia(false);
        retornoFactory.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        when(cuentaBO.getCuentasSaldo(cliente, cliente.getCuentas())).thenReturn(retornoFactory);

//        Mockito.doNothing().when(cuentasUtils.ordenarCuentas(respuestaDetalleCuenta));

        Respuesta<List<ResumenDetalleCuenta>> respuesta = null;

        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        respuesta = configuracionCompraVentaDolaresBO.getCuentasSaldoOrdenadas(cliente, null, identificacionCuenta);

        assertNotNull(respuesta);
        assertEquals(respuesta, retornoFactory);
    }

    /**
     * Metodo para testear comportamiento de generar la respuesta con los datos
     * de la cotizacion, de la cuanta origen.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test
    public void obtenerCotizacionTest() throws DAOException, BotonPanicoCompraventaException {
        Cliente cliente = obtenerCliente();
        Cuenta cuenta = getCuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        String tipoCotizacion = "C";

        CompraVentaInicioDTO compraVentaDolarInicio = getCompraVentaDolarInicio();

        Mockito.when(cotizacionDolaresDAO.obtenerCotizacion(cliente, cuenta, tipoCotizacion, vieneDeCuentas))
                .thenReturn(getConsultaCotizacion());

        RespuestaFactory respuestaFactory2 = new RespuestaFactory();
        Respuesta<CompraVentaInicioDTO> retornoFactory = respuestaFactory2.crearRespuestaOk(CompraVentaInicioDTO.class,
                compraVentaDolarInicio);

        Mockito.when(respuestaFactory.crearRespuestaOk(CompraVentaInicioDTO.class, compraVentaDolarInicio))
                .thenReturn(retornoFactory);

        Respuesta<CompraVentaInicioDTO> respuesta = null;
        try {
            respuesta = configuracionCompraVentaDolaresBO.obtenerCotizacion(cliente, cuenta, tipoCotizacion, vieneDeCuentas, vieneDeCuentas );
            assertNotNull(respuesta);
            assertEquals(respuesta, retornoFactory);
        } catch (BusinessException e) {
            System.out.println(e);
        }
    }

    /**
     * Test SucursalFueraDeHorarioException (1084).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void sucursalFueraDeHorarioExceptionTest() throws BusinessException {

        Cliente cliente = getCliente();
        Cuenta cuenta = getCuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        String tipoCotizacion = "C";
        estadisticaManager.add(Mockito.anyString() , Mockito.anyString());
        configuracionCompraVentaDolaresBO.obtenerCotizacion(cliente, cuenta, tipoCotizacion , vieneDeCuentas, vieneDeCuentas);

    }

    /**
     * Test SinAccesoALaInformacionException (1085).
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test
    public void sinAccesoALaInformacionExceptionTest() throws BusinessException, DAOException, BotonPanicoCompraventaException {

        Cliente cliente = getCliente();
        Cuenta cuenta = getCuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        String tipoCotizacion = "C";
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(cotizacionDolaresDAO.obtenerCotizacion(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString(), vieneDeCuentas)).thenThrow(new DAOException());
        Respuesta<CompraVentaInicioDTO> respuesta = configuracionCompraVentaDolaresBO.obtenerCotizacion(cliente, cuenta,
                tipoCotizacion, vieneDeCuentas, vieneDeCuentas);
        assertEquals(TipoError.ERROR_SIN_ACCESO_A_INFORMACION.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Metodo para testear comportamiento de generar la respuesta con los datos
     * de la cotizacion, de la cuanta origen.
     *
     * @return the cuentas saldo ordenadas test
     */
    @Test
    public void getCuentasSaldoOrdenadasTest() {
        Cliente cliente = getCliente();
        Cuenta cuenta = getCuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);

        Respuesta<List<ResumenDetalleCuenta>> retornoFactory = new Respuesta<List<ResumenDetalleCuenta>>();
        List<ResumenDetalleCuenta> respuestaDetalleCuenta = new ArrayList<ResumenDetalleCuenta>();
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        respuestaDetalleCuenta.add(resumenDetalleCuenta);

        retornoFactory.setRespuesta(respuestaDetalleCuenta);
        retornoFactory.setEstadoRespuesta(EstadoRespuesta.OK);
        retornoFactory.setRespuestaVacia(false);
        retornoFactory.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        try {
            when(cuentaBO.getCuentasSaldo(cliente, cliente.getCuentas())).thenReturn(retornoFactory);
        } catch (BusinessException e1) {
            System.out.println(e1);
        }

//        Mockito.doNothing().when(compraVentaDolaresUtil).ordenarCuentas(respuestaDetalleCuenta);

        Respuesta<List<ResumenDetalleCuenta>> respuesta = null;
        try {
            IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
            respuesta = configuracionCompraVentaDolaresBO.getCuentasSaldoOrdenadas(cliente, identificacionCuenta,
                    identificacionCuenta);
        } catch (BusinessException e) {
            System.out.println(e);
        }
        assertNotNull(respuesta);
        assertEquals(respuesta, retornoFactory);
    }

    /**
     * Obtener cotizacion ok.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test
    public void obtenerCotizacionOk() throws BusinessException, DAOException, CompraVentaDolaresException, BotonPanicoCompraventaException {
        ConsultaCotizacionEntity cotizacionRet = new ConsultaCotizacionEntity();
        Mockito.when(cotizacionDolaresDAO.obtenerCotizacion(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString(), vieneDeCuentas)).thenReturn(cotizacionRet);
        Mockito.when(compraVentaDolaresUtil.parcearCotizacionCuatroDecimales(Matchers.anyString())).thenReturn("1234");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionString(Matchers.anyString())).thenReturn("1234");
        Respuesta<CompraVentaInicioDTO> res = configuracionCompraVentaDolaresBO.obtenerCotizacion(getCliente(),
                getCuenta(), "" , vieneDeCuentas, vieneDeCuentas);
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener cotizacion DAO exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test
    public void obtenerCotizacionDAOException() throws BusinessException, DAOException, CompraVentaDolaresException, BotonPanicoCompraventaException {
        Mockito.when(cotizacionDolaresDAO.obtenerCotizacion(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString(), vieneDeCuentas)).thenThrow(new DAOException());
        Mockito.when(compraVentaDolaresUtil.parcearCotizacionCuatroDecimales(Matchers.anyString())).thenReturn("1234");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionString(Matchers.anyString())).thenReturn("1234");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaInicioDTO> res = configuracionCompraVentaDolaresBO.obtenerCotizacion(getCliente(),
                getCuenta(), "" , vieneDeCuentas, vieneDeCuentas);
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener cotizacion compra venta dolares exception.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BotonPanicoCompraventaException 
     */
    @Test(expected = BusinessException.class)
    public void obtenerCotizacionCompraVentaDolaresException()
            throws BusinessException, DAOException, CompraVentaDolaresException, BotonPanicoCompraventaException {
        ConsultaCotizacionEntity cotizacionRet = new ConsultaCotizacionEntity();
        Mockito.when(cotizacionDolaresDAO.obtenerCotizacion(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString(), vieneDeCuentas)).thenReturn(cotizacionRet);
        Mockito.when(compraVentaDolaresUtil.parcearCotizacionCuatroDecimales(Matchers.anyString()))
                .thenThrow(new CompraVentaDolaresException());
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionString(Matchers.anyString())).thenReturn("1234");
        Respuesta<CompraVentaInicioDTO> res = configuracionCompraVentaDolaresBO.obtenerCotizacion(getCliente(),
                getCuenta(), "" , vieneDeCuentas, vieneDeCuentas);
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

}
