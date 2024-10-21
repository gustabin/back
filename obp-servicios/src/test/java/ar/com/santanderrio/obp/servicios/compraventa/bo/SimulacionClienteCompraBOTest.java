/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

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
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.SimulacionClienteCompraBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.SimulacionClienteCompraDAO;
import ar.com.santanderrio.obp.servicios.compraventa.dao.impl.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.entities.ParametrosSimulacion;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionClienteCompraEntity;
import ar.com.santanderrio.obp.servicios.compraventa.entities.SimulacionDatosEntrada;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class SimulacionClienteCompraBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class SimulacionClienteCompraBOTest {

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

    /** The simulacion cliente compra DAO. */
    @Mock
    private SimulacionClienteCompraDAO simulacionClienteCompraDAO;

    /** The compra venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /** The simulacion cliente compra BO. */
    @InjectMocks
    private SimulacionClienteCompraBO simulacionClienteCompraBO = new SimulacionClienteCompraBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

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

    /**
     * Gets the cliente.
     *
     * @return the cliente
     */
    private Cliente getCliente() {
        return mock(Cliente.class);
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
     * Gets the parametros simulacion.
     *
     * @return the parametros simulacion
     */
    private ParametrosSimulacion getParametrosSimulacion() {
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();
        parametrosSimulacion.setCliente(getCliente());
        parametrosSimulacion.setCotizacion("14,99");
        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        parametrosSimulacion.setImporte("674.55");
        parametrosSimulacion.setIsDolar(true);
        return parametrosSimulacion;
    }

    /**
     * Gets the simulacion compra venta dolar.
     *
     * @return the simulacion compra venta dolar
     */
    private SimulacionCompraVentaDTO getSimulacionCompraVentaDolar() {
        SimulacionCompraVentaDTO simulacionCompraVentaDolar = new SimulacionCompraVentaDTO();
        simulacionCompraVentaDolar.setCotizacion(BigDecimal.valueOf(14.0500));
        simulacionCompraVentaDolar.setEsCompra(true);
        simulacionCompraVentaDolar.setEsVenta(false);
        simulacionCompraVentaDolar.setImporteAcreditarPesos(BigDecimal.valueOf(11.00));
        simulacionCompraVentaDolar.setImporteCompraDolar(null);
        simulacionCompraVentaDolar.setImporteDebitarPesos(BigDecimal.valueOf(14.05));
        simulacionCompraVentaDolar.setImporteVentaDolar(null);
        simulacionCompraVentaDolar.setLegales("");
        simulacionCompraVentaDolar.setNumeroComprobante("");
        simulacionCompraVentaDolar.setNumeroOperacion("");
        simulacionCompraVentaDolar.setNupNumDoc("");
        simulacionCompraVentaDolar.setNupTipo("");
        return simulacionCompraVentaDolar;
    }

    /**
     * Gets the detalle documento DTO.
     *
     * @return the detalle documento DTO
     */
    private DetalleDocumentoDTO getDetalleDocumentoDTO() {
        DetalleDocumentoDTO detalleNup = new DetalleDocumentoDTO();
        detalleNup.setIsDocumentoPrincipal(true);
        detalleNup.setNroDocumento("23059634674");
        detalleNup.setTipoDocumento("N");
        return detalleNup;
    }

    /**
     * Gets the nup DTO.
     *
     * @return the nup DTO
     */
    private NupDTO getNupDTO() {
        NupDTO nupDTO = new NupDTO();
        nupDTO.setCantDescStatusResultado(null);
        nupDTO.setDescripcionStatusResultado(null);
        nupDTO.setId(null);
        nupDTO.setIdSistema(null);
        nupDTO.setMarcaDocumentoPpal(null);
        nupDTO.setNroDocumento(null);
        return nupDTO;
    }

    /**
     * Retorna el objeto Entity para la simulacion compra.
     *
     * @return the simulacion cliente compra DTO
     */
    private SimulacionClienteCompraEntity getSimulacionClienteCompraEntity() {
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = new SimulacionClienteCompraEntity();
        simulacionClienteCompraEntity.setCccarg("00720100007003750162");
        simulacionClienteCompraEntity.setDivcarg("ARS");
        simulacionClienteCompraEntity.setCodcarg("2312");
        simulacionClienteCompraEntity.setConcarg("");
        simulacionClienteCompraEntity.setImpcarg("000000000001405+");
        simulacionClienteCompraEntity.setCccabon("00720100007003750162");
        simulacionClienteCompraEntity.setDivabon("USD");
        simulacionClienteCompraEntity.setCodabon("2254");
        simulacionClienteCompraEntity.setConabon("");
        simulacionClienteCompraEntity.setImpabon("000000000000100+");
        simulacionClienteCompraEntity.setTipcamb("");
        simulacionClienteCompraEntity.setImpcoti("000000000140500+");
        simulacionClienteCompraEntity.setFechval("2016-06-14");
        simulacionClienteCompraEntity.setSdodcar("000000000025001+");
        simulacionClienteCompraEntity.setSdocarb("000000000000000+");
        simulacionClienteCompraEntity.setNromcar("");
        simulacionClienteCompraEntity.setSdodabo("000000000000000+");
        simulacionClienteCompraEntity.setSdoabob("000000000025001+");
        simulacionClienteCompraEntity.setNromabo("");
        simulacionClienteCompraEntity.setCentori("8554");
        simulacionClienteCompraEntity.setCanal("70");
        simulacionClienteCompraEntity.setImpimpu("000000000000000+");
        simulacionClienteCompraEntity.setConcimp("");
        simulacionClienteCompraEntity.setImpuest("");
        simulacionClienteCompraEntity.setRegimen("");
        simulacionClienteCompraEntity.setCodalta("");
        simulacionClienteCompraEntity.setNomclie("FRANCO");
        simulacionClienteCompraEntity.setPpriape("VITA");
        simulacionClienteCompraEntity.setPsegape("");
        simulacionClienteCompraEntity.setTipoid("N");
        simulacionClienteCompraEntity.setNumiden("00030219572");
        simulacionClienteCompraEntity.setNumBole("00000000");
        simulacionClienteCompraEntity.setPorImpu("");
        simulacionClienteCompraEntity.setTotCarg("000000000001405+");
        return simulacionClienteCompraEntity;
    }

    /**
     * Obtener mensaje respuesta.
     *
     * @return the respuesta
     */
    private Respuesta<String> obtenerMensajeRespuesta() {
        Respuesta<String> mensajeRespuesta = new Respuesta<String>();
        mensajeRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        mensajeRespuesta.setRespuestaVacia(false);
        mensajeRespuesta.setRespuesta("Legales");
        return mensajeRespuesta;
    }

    /**
     * Metodo para testear comportamiento de obtener la respuesta con los datos
     * de la simuacion cuando Cliente Compra Dolares.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSimulacionClienteCompraOkIfTest()
            throws DAOException, CompraVentaDolaresException, BusinessException {
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = new SimulacionClienteCompraEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        nupDTO.getDetalleDocumento().put("T", new DetalleDocumentoDTO());
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteCompraEntity.setTieneError(false);

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setRespuesta("pepe legal");
        resLegal.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitar(Matchers.anyString())).thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteCompraDAO.obtenerSimulacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(simulacionClienteCompraEntity);

        Respuesta<SimulacionCompraVentaDTO> respuesta = simulacionClienteCompraBO
                .obtenerSimulacionClienteCompra(getParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Obtener respuesta cliente compra entity error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerRespuestaClienteCompraEntityErrorTest()
            throws DAOException, CompraVentaDolaresException, BusinessException {
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = new SimulacionClienteCompraEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        nupDTO.getDetalleDocumento().put("T", new DetalleDocumentoDTO());
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteCompraEntity.setTieneError(true);
        simulacionClienteCompraEntity.setCodError(5);

        Respuesta<String> resLegal = new Respuesta<String>();
        resLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        resLegal.setRespuesta("pepe legal");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitar(Matchers.anyString())).thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(resLegal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteCompraDAO.obtenerSimulacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(simulacionClienteCompraEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> respuesta = simulacionClienteCompraBO
                .obtenerSimulacionClienteCompra(getParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    /**
     * Obtener simulacion cliente compra ok else test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSimulacionClienteCompraOkElseTest() throws DAOException, BusinessException {
        NupDTO nupDTO = new NupDTO();
        nupDTO.setTieneError(true);
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("T");
        detalleDocumentoDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        nupDTO.getDetalleDocumento().put("T", detalleDocumentoDTO);

        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteCompraBO
                .obtenerSimulacionClienteCompra(new ParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener simulacion cliente compra DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerSimulacionClienteCompraDAOExceptionTest() throws DAOException, BusinessException {
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenThrow(new DAOException());
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteCompraBO
                .obtenerSimulacionClienteCompra(new ParametrosSimulacion());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
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
        simulacionClienteCompraBO.obtenerSimulacionClienteCompra(new ParametrosSimulacion());
    }

    /**
     * Obtener respuesta cliente compra DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     */
    @Test
    public void obtenerRespuestaClienteCompraDAOExceptionTest()
            throws DAOException, BusinessException, CompraVentaDolaresException {
        SimulacionClienteCompraEntity simulacionClienteCompraEntity = new SimulacionClienteCompraEntity();
        NupDTO nupDTO = new NupDTO();
        ParametrosSimulacion parametrosSimulacion = new ParametrosSimulacion();

        parametrosSimulacion.setCuentaDestino(getCuenta());
        parametrosSimulacion.setCuentaOrigen(getCuenta());
        nupDTO.setTieneError(false);
        nupDTO.getDetalleDocumento().put("T", new DetalleDocumentoDTO());
        nupDTO.setError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION);
        simulacionClienteCompraEntity.setTieneError(true);
        simulacionClienteCompraEntity.setCodError(10000077);
        Respuesta<String> legal = new Respuesta<String>();
        legal.setEstadoRespuesta(EstadoRespuesta.OK);
        legal.setRespuesta("Legales");

        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable(Matchers.anyString())).thenReturn("12");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitar(Matchers.anyString())).thenReturn("12");
        Mockito.when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(legal);
        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento(Matchers.anyString())).thenReturn("T");
        Mockito.when(simulacionClienteCompraDAO.obtenerSimulacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<SimulacionCompraVentaDTO> res = simulacionClienteCompraBO
                .obtenerSimulacionClienteCompra(parametrosSimulacion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear datos entrada cliente compra test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws SaldoInsuficienteException
     *             the saldo insuficiente exception
     * @throws CompraVentaDolaresException
     *             the compra venta dolares exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void crearDatosEntradaClienteCompraTest()
            throws DAOException, SaldoInsuficienteException, CompraVentaDolaresException, BusinessException {

        NupDTO dto = getNupDTO();
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento("N");
        dto.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
        when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(dto);

        when(compraVentaDolaresBOImpl.obtenerDocumentoValido(Matchers.any(Cliente.class)))
                .thenReturn(getDetalleDocumentoDTO());

        when(simulacionClienteCompraDAO.obtenerSimulacionClienteCompra(Matchers.any(Cliente.class),
                Matchers.any(SimulacionDatosEntrada.class), null)).thenReturn(getSimulacionClienteCompraEntity());

        SimulacionCompraVentaDTO simulacionCompraVentaDolar = getSimulacionCompraVentaDolar();
        RespuestaFactory respuestaFactory2 = new RespuestaFactory();
        Respuesta<SimulacionCompraVentaDTO> retornoFactory = respuestaFactory2
                .crearRespuestaOk(SimulacionCompraVentaDTO.class, simulacionCompraVentaDolar);

        // Cargar datos para el metodo crearDatosEntradaClienteCompra
        Mockito.when(compraVentaDolaresUtil.obtenerAplicacionPesos(Matchers.any(Cuenta.class))).thenReturn("ACAH");
        Mockito.when(compraVentaDolaresUtil.obtenerSucursalTresDigitos(Matchers.any(Cuenta.class))).thenReturn("461");
        Mockito.when(compraVentaDolaresUtil.obtenerNumeroCtaDoceDigitos(Matchers.any(Cuenta.class)))
                .thenReturn("000000000000");
        Mockito.when(compraVentaDolaresUtil.obtenerAplicacionDolar(getCuenta(), "C")).thenReturn("ACAD");
        Mockito.when(compraVentaDolaresUtil.obtenerSucursalTresDigitos(Matchers.any(Cuenta.class))).thenReturn("461");
        Mockito.when(compraVentaDolaresUtil.obtenerNumeroCtaDoceDigitos(Matchers.any(Cuenta.class)))
                .thenReturn("000000000000");
        Mockito.when(compraVentaDolaresUtil.obtenerIndTuAtesora()).thenReturn("t");
        Mockito.when(compraVentaDolaresUtil.obtenerFechaValor()).thenReturn("2016-08-04");
        Mockito.when(compraVentaDolaresUtil.obtenerCodigoDebi()).thenReturn("ABCD");
        Mockito.when(compraVentaDolaresUtil.obtenerCodigoCre()).thenReturn("ABCD");
        Mockito.when(compraVentaDolaresUtil.obtenerImporteDebito("", "", true)).thenReturn("300");
        Mockito.when(compraVentaDolaresUtil.obtenerNomApell(Matchers.any(Cliente.class))).thenReturn("Razon Social");
        Mockito.when(compraVentaDolaresUtil.obtenerCodigoConcepto()).thenReturn("12345");
        Mockito.when(compraVentaDolaresUtil.obtenerCndCliente()).thenReturn(" ");

        // Legales
        Mockito.when(legalBO.buscarLegal("400001")).thenReturn(obtenerMensajeRespuesta());
        Mockito.when(legalBO.buscarLegal("1003")).thenReturn(obtenerMensajeRespuesta());

        Mockito.when(compraVentaDolaresUtil.obtenerTipoDocumento("N")).thenReturn("N");

        Mockito.when(compraVentaDolaresUtil.generarNumeroDecimal("000000000000100+", 11)).thenReturn("100.00");
        Mockito.when(compraVentaDolaresUtil.obtenerFechVal("2016-08-14")).thenReturn("14/08/2016");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteAComprar("000000000000100+")).thenReturn("11.00");
        Mockito.when(compraVentaDolaresUtil.obtenerCotizacionAplicable("000000000140500+")).thenReturn("14.0500");
        Mockito.when(compraVentaDolaresUtil.convertirSimulacionImporteDebitar("000000000001405+")).thenReturn("14.05");

        // Respuesta
        Mockito.when(respuestaFactory.crearRespuestaOk(SimulacionCompraVentaDTO.class, simulacionCompraVentaDolar))
                .thenReturn(retornoFactory);

        Respuesta<SimulacionCompraVentaDTO> respuesta = null;
        try {
            respuesta = simulacionClienteCompraBO.obtenerSimulacionClienteCompra(getParametrosSimulacion());
        } catch (BusinessException e) {
            System.out.println(e);
        }
        assertNotNull(respuesta);
    }

}
