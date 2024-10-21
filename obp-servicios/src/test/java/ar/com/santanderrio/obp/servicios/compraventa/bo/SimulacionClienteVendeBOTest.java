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
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.SimulacionClienteVendeBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteVendeDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteVendeEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class SimulacionClienteVendeBOTest.
 * 
 * @author dante.omar.olmedo
 *
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class SimulacionClienteVendeBOTest {

    /** The simulacion cliente vende BO. */
    @InjectMocks
    private SimulacionClienteVendeBO simulacionClienteVendeBO = new SimulacionClienteVendeBOImpl();

    /** The simulacion cliente vende DAO. */
    @Mock
    private SimulacionClienteVendeDAO simulacionClienteVendeDAO;

    /** The nup DAO. */
    @Mock
    private NUPDAO nupDAO;

    /** The compra venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion compra venta. */
    @Mock
    private SesionCompraVenta sesionCompraVenta;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private EstadisticaManager estadisticaManager;
    
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
     * Obtener simulacion cliente vende ok if test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerSimulacionClienteVendeOkIfTest()
            throws DAOException, BusinessException, CompraVentaDolaresException {
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = new SimulacionClienteVendeEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteVendeEntity.setTieneError(false);

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setRespuesta("pepe legal");
        resLegal.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteVendeDAO.obtenerSimulacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(simulacionClienteVendeEntity);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(parametrosSimulacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener simulacion cliente vende ok else test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSimulacionClienteVendeOkElseTest() throws DAOException, BusinessException {
        NupDTO nupDTO = new NupDTO();
        nupDTO.setTieneError(true);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);

        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(new ParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener simulacion cliente vende DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSimulacionClienteVendeDAOExceptionTest() throws DAOException, BusinessException {
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenThrow(new DAOException());
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(new ParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener simulacion cliente vende legal business exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerSimulacionClienteVendeLegalBusinessExceptionTest()
            throws DAOException, BusinessException, CompraVentaDolaresException {
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = new SimulacionClienteVendeEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteVendeEntity.setTieneError(false);

        Respuesta<String> legal = new Respuesta<String>();
        legal.setEstadoRespuesta(EstadoRespuesta.OK);
        legal.setRespuesta("Legales");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteVendeDAO.obtenerSimulacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(simulacionClienteVendeEntity);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(parametrosSimulacion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener simulacion cliente vende exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test(expected = BusinessException.class)
    public void obtenerSimulacionClienteVendeExceptionTest() throws DAOException, BusinessException {
        simulacionClienteVendeBO.obtenerSimulacionClienteVende(new ParametrosSimulacion());
    }

    /**
     * Obtener simulacion cliente vende simulacion cliente vende entity error
     * test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerSimulacionClienteVendeSimulacionClienteVendeEntityErrorTest()
            throws DAOException, BusinessException, CompraVentaDolaresException {
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = new SimulacionClienteVendeEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        nupDTO.setError(ErrorCompraVentaEnum.ERROR_DE_SERVICIO);
        simulacionClienteVendeEntity.setTieneError(true);
        simulacionClienteVendeEntity.setCodError(5);

        Respuesta<String> legal = new Respuesta<String>();
        legal.setEstadoRespuesta(EstadoRespuesta.OK);
        legal.setRespuesta("Legales");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteVendeDAO.obtenerSimulacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(simulacionClienteVendeEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(parametrosSimulacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta cliente vende DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerRespuestaClienteVendeDAOExceptionTest()
            throws DAOException, BusinessException, CompraVentaDolaresException {
        SimulacionClienteVendeEntity simulacionClienteVendeEntity = new SimulacionClienteVendeEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteVendeEntity.setTieneError(true);
        simulacionClienteVendeEntity.setCodError(10000077);

        Respuesta<String> legal = new Respuesta<String>();
        legal.setEstadoRespuesta(EstadoRespuesta.OK);
        legal.setRespuesta("Legales");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicableVentaConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitarConPunto(Matchers.anyString()))
                .thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteVendeDAO.obtenerSimulacionClienteVende(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteVendeBO
                .obtenerSimulacionClienteVende(parametrosSimulacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
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
