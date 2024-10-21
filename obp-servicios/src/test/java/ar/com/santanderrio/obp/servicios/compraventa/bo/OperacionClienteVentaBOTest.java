package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.mockito.Mockito.mock;

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
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.OperacionClienteVentaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteVentaEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionCompraVentaDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosOperacion;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class OperacionClienteVentaBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class OperacionClienteVentaBOTest {

    /** The operacion cliente venta BO. */
    @InjectMocks
    private OperacionClienteVentaBO operacionClienteVentaBO = new OperacionClienteVentaBOImpl();

    /** The operacion venta DAO. */
    @Mock
    private OperacionClienteVendeDAO operacionVentaDAO;

    /** The legal bo. */
    @Mock
    private LegalBO legalBO;

    /** The nup DAO. */
    @Mock
    private NUPDAO nupDAO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The compra venta dolares BO impl. */
    @Mock
    private CompraVentaDolaresBOImpl compraVentaDolaresBOImpl;

    /** The compra venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /**
     * Operacion cliente venta ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void operacionClienteVentaOkTest() throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteVentaEntity operacionClienteVentaEntity = new OperacionClienteVentaEntity();
        operacionClienteVentaEntity.setTieneError(false);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setRespuesta("pepe legal");
        resLegal.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionVentaDAO.operacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteVentaEntity);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteVentaBO.operacionClienteVenta(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta cliente venta entity con error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerRespuestaClienteVentaEntityConErrorTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteVentaEntity operacionClienteVentaEntity = new OperacionClienteVentaEntity();
        operacionClienteVentaEntity.setTieneError(true);
        operacionClienteVentaEntity.setCodError(10000004);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setRespuesta("pepe legal");

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionVentaDAO.operacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteVentaEntity);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteVentaBO.operacionClienteVenta(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta cliente venta DAO exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerRespuestaClienteVentaDAOExceptionTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteVentaEntity operacionClienteVentaEntity = new OperacionClienteVentaEntity();
        operacionClienteVentaEntity.setTieneError(true);
        operacionClienteVentaEntity.setCodError(10000004);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());
        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setRespuesta("pepe legal");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionVentaDAO.operacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenThrow(new DAOException());
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteVentaBO.operacionClienteVenta(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear datos response cliente venta compra venta dolares exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void crearDatosResponseClienteVentaCompraVentaDolaresExceptionTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteVentaEntity operacionClienteVentaEntity = new OperacionClienteVentaEntity();
        operacionClienteVentaEntity.setTieneError(false);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());

        Respuesta<String> legal = new Respuesta<String>();
        legal.setRespuesta("Legal OK");
        legal.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(operacionVentaDAO.operacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteVentaEntity);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteVentaBO.operacionClienteVenta(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Operacion cliente venta exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void operacionClienteVentaExceptionTest() throws BusinessException {
        operacionClienteVentaBO.operacionClienteVenta(new ParametrosOperacion());
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
