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
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.OperacionClienteCompraBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.OperacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.OperacionClienteCompraEntity;
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
 * The Class OperacionClienteCompraBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class OperacionClienteCompraBOTest {

    /** The operacion cliente compra BO. */
    @InjectMocks
    private OperacionClienteCompraBO operacionClienteCompraBO = new OperacionClienteCompraBOImpl();

    /** The operacion compra DAO. */
    @Mock
    private OperacionClienteCompraDAO operacionCompraDAO;

    /** The legal bo. */
    @Mock
    private LegalBO legalBO;

    /** The nup DAO. */
    @Mock
    private NUPDAO nupDAO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

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

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /**
     * Operacion cliente compra ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void operacionClienteCompraOkTest() throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
        operacionClienteCompraEntity.setTieneError(false);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        resLegal.setRespuesta("pepe legal");

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionCompraDAO.operacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteCompraEntity);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteCompraBO.operacionClienteCompra(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta cliente compra entity con error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerRespuestaClienteCompraEntityConErrorTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
        operacionClienteCompraEntity.setTieneError(true);
        operacionClienteCompraEntity.setCodError(10000004);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();
        Respuesta<String> resLegal = new Respuesta<String>();

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());
        resLegal.setRespuesta("pepe legal");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitar(Matchers.anyString())).thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionCompraDAO.operacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteCompraEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteCompraBO.operacionClienteCompra(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta cliente compra DAO exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerRespuestaClienteCompraDAOExceptionTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
        operacionClienteCompraEntity.setTieneError(true);
        operacionClienteCompraEntity.setCodError(10000004);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();
        Respuesta<String> resLegal = new Respuesta<String>();

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());
        resLegal.setRespuesta("pepe legal");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(operacionCompraDAO.operacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteCompraBO.operacionClienteCompra(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear datos response cliente compra compra compra dolares OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void crearDatosResponseClienteCompraCompraCompraDolaresOKTest()
            throws BusinessException, CompraVentaDolaresException, DAOException {
        OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
        operacionClienteCompraEntity.setTieneError(false);
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion();

        parametrosOperacion.setCuentaDestino(getCuenta());
        parametrosOperacion.setCuentaOrigen(getCuenta());
        Respuesta<String> legal = new Respuesta<String>();
        legal.setRespuesta("Legal OK");
        legal.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(operacionCompraDAO.operacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteCompraEntity);
        Respuesta<ComprobanteCompraVentaDTO> res = operacionClienteCompraBO.operacionClienteCompra(parametrosOperacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Operacion cliente compra exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = BusinessException.class)
    public void operacionClienteCompraExceptionTest() throws BusinessException, DAOException {
        OperacionClienteCompraEntity operacionClienteCompraEntity = new OperacionClienteCompraEntity();
        Mockito.when(operacionCompraDAO.operacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(OperacionCompraVentaDatosEntrada.class), null)).thenReturn(operacionClienteCompraEntity);
        operacionClienteCompraBO.operacionClienteCompra(new ParametrosOperacion());
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
