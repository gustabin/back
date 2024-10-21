package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PreFormalizacionPrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.UvaView;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetalleCuotaPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class PrestamoManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoManagerTest {

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManagerImpl prestamoManager = new PrestamoManagerImpl();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The prestamo service. */
    @Mock
    private PrestamoService prestamoService;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The pre formalizacion prestamo service. */
    @Mock
    private PreFormalizacionPrestamoService preFormalizacionPrestamoService;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The legalBO bo. */
    @Mock
    private LegalBO legalBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta Factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    PrestamoBO prestamoBo;

    @Mock
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    @Mock
    private DestinoPrestamoBO destinoPrestamoBo;

    /**
     * Test detalle prestamos.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDetallePrestamos() throws ServiceException, IllegalAccessException {
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();

        Prestamo prestamo = Mockito.mock(Prestamo.class);
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = Mockito.mock(Respuesta.class);
        PreFormalizacion preFormalizacion = Mockito.mock(PreFormalizacion.class);
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = Mockito.mock(CuentaPrestamoDebitoAdherido.class);

        pagoPendientePrestamo.setPrestamo(prestamo);
        List<PagoPendiente> c = new ArrayList<PagoPendiente>();
        c.add(pagoPendientePrestamo);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(c);

        Mockito.when(preFormalizacionRespuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        Mockito.when(preFormalizacionRespuesta.getRespuesta()).thenReturn(preFormalizacion);
        Mockito.when(prestamo.getNumeroCuentaProducto()).thenReturn("0000000000000000000000000011");
        Mockito.when(prestamo.getTipoPrestamoEnum()).thenReturn(TipoPrestamoEnum.PRENDARIO);
        Mockito.when(prestamo.getCuenta()).thenReturn(cuenta);
        Mockito.when(prestamo.getNumeroRecibo()).thenReturn("0000000000000001111");
        Mockito.when(prestamo.getDivisa()).thenReturn(DivisaEnum.PESO);
        Mockito.when(prestamo.getSaldoPrevio()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getIndex()).thenReturn("CVS");
        Mockito.when(prestamo.getFactorIndex()).thenReturn("999120000");
        Mockito.when(prestamo.getImporteAjussal()).thenReturn(new BigDecimal("10"));
        Mockito.when(prestamo.getVencimientoCuota()).thenReturn(new Date());
        Mockito.when(prestamo.getImporteTotalCuota()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getFecCotiz()).thenReturn(new Date());
        Mockito.when(prestamo.getNroExp()).thenReturn("3");
        Mockito.when(prestamo.getCotizCambio()).thenReturn(new BigDecimal("90"));
        Mockito.when(prestamo.getAjusteSaldo()).thenReturn(new BigDecimal("1500"));
        Mockito.when(prestamo.getCapitalPend()).thenReturn(new BigDecimal("1000"));
        Mockito.when(prestamo.getInteresesPend()).thenReturn(new BigDecimal("4000"));

        Mockito.when(cuenta.getNroSucursal()).thenReturn("033");
        Mockito.when(cuenta.getTipoCuenta()).thenReturn("1");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNroSucursal()).thenReturn("033");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNumero()).thenReturn("033");
        Mockito.when(preFormalizacion.getPrestamoDebitoAdherido()).thenReturn(cuentaPrestamoDebitoAdherido);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);
        Mockito.when(preFormalizacionPrestamoService.obtenerPreFormalizacion(prestamo))
                .thenReturn(preFormalizacionRespuesta);
        FieldUtils.writeField(prestamoManager, "sessionParametros", sesionParametros, true);
        ConsultaPrestamo consulta = new ConsultaPrestamo();
        consulta.setNumeroPrestamo("11");

        Respuesta<DetallePrestamoView> detallePrestamoView = prestamoManager.getDetallePrestamos(consulta, false);
        assertNotNull(detallePrestamoView);
        assertEquals("033-00000000001/1", detallePrestamoView.getRespuesta().getNumeroPrestamo());
        assertEquals("Caja de ahorro en $", detallePrestamoView.getRespuesta().getTipoCuenta());
        assertEquals("CVS 999,12", detallePrestamoView.getRespuesta().getCoeficienteIndexacionReciboEnTermino());
    }

    /**
     * Test reporte detalle prestamo.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testReporteDetallePrestamo() throws ServiceException, IllegalAccessException {
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        Respuesta<Reporte> resp = new Respuesta<Reporte>();

        PreFormalizacion preFormalizacion = Mockito.mock(PreFormalizacion.class);
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        Prestamo prestamo = Mockito.mock(Prestamo.class);
        Reporte reporte = Mockito.mock(Reporte.class);
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = Mockito.mock(CuentaPrestamoDebitoAdherido.class);

        preFormalizacionRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        resp.setRespuesta(reporte);

        Mockito.when(reporte.getNombre()).thenReturn("NOMBRE_ARCHIVO");
        Mockito.when(reporte.getTipoArchivo()).thenReturn(TipoArchivoEnum.PDF);
        Mockito.when(preFormalizacionPrestamoService.obtenerPreFormalizacion(prestamo))
                .thenReturn(preFormalizacionRespuesta);
        Mockito.when(prestamoService.obtenerReporteDetallePrestamo(Matchers.any(ComprobantePrestamoReporte.class)))
                .thenReturn(resp);
        Mockito.when(prestamo.getNumeroCuentaProducto()).thenReturn("0000000000000000000000000011");
        Mockito.when(prestamo.getTipoPrestamoEnum()).thenReturn(TipoPrestamoEnum.PRENDARIO);
        Mockito.when(prestamo.getCuenta()).thenReturn(cuenta);
        Mockito.when(prestamo.getNumeroRecibo()).thenReturn("0000000000000001111");
        Mockito.when(prestamo.getDivisa()).thenReturn(DivisaEnum.PESO);
        Mockito.when(prestamo.getSaldoPrevio()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getIndex()).thenReturn("1");
        Mockito.when(prestamo.getFactorIndex()).thenReturn("010123123");
        Mockito.when(prestamo.getImporteAjussal()).thenReturn(new BigDecimal("10"));
        Mockito.when(prestamo.getVencimientoCuota()).thenReturn(new Date());
        Mockito.when(prestamo.getImporteTotalCuota()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getFecCotiz()).thenReturn(new Date());
        Mockito.when(prestamo.getNroExp()).thenReturn("3");
        Mockito.when(prestamo.getCotizCambio()).thenReturn(new BigDecimal("90"));
        Mockito.when(prestamo.getAjusteSaldo()).thenReturn(new BigDecimal("1500"));
        Mockito.when(prestamo.getCapitalPend()).thenReturn(new BigDecimal("1000"));
        Mockito.when(prestamo.getInteresesPend()).thenReturn(new BigDecimal("4000"));

        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);
        FieldUtils.writeField(prestamoManager, "sessionParametros", sesionParametros, true);

        Mockito.when(cuenta.getNroSucursal()).thenReturn("033");
        Mockito.when(cuenta.getTipoCuenta()).thenReturn("1");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNroSucursal()).thenReturn("033");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNumero()).thenReturn("033");
        Mockito.when(preFormalizacion.getPrestamoDebitoAdherido()).thenReturn(cuentaPrestamoDebitoAdherido);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);

        pagoPendientePrestamo.setPrestamo(prestamo);
        List<PagoPendiente> c = new ArrayList<PagoPendiente>();
        c.add(pagoPendientePrestamo);
        respuesta.setRespuesta(c);

        Respuesta<ReporteView> reporteView = prestamoManager.getReporteDetallePrestamo("11", false);
        assertNotNull(reporteView);
        assertEquals("NOMBRE_ARCHIVO", reporteView.getRespuesta().getNombre());
        assertEquals(TipoArchivoEnum.PDF.getMimeTipe(), reporteView.getRespuesta().getTipoArchivo());
    }

    /**
     * Test obtener reporte comprobante.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void testObtenerReporteComprobante() throws ServiceException, IllegalAccessException {
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PagoPendientePrestamo pagoPendientePrestamo = Mockito.mock(PagoPendientePrestamo.class);
        List<PagoPendiente> pagoPendientes = new ArrayList<PagoPendiente>();
        Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
        Reporte reporte = new Reporte();
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        Prestamo prestamo = Mockito.mock(Prestamo.class);
        Respuesta<Reporte> resp = new Respuesta<Reporte>();

        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);

        Mockito.when(prestamo.getNumeroCuentaProducto()).thenReturn("0000000000000000000000000011");
        Mockito.when(prestamo.getTipoPrestamoEnum()).thenReturn(TipoPrestamoEnum.PRENDARIO);
        Mockito.when(prestamo.getCuenta()).thenReturn(cuenta);
        Mockito.when(prestamo.getNumeroRecibo()).thenReturn("0000000000000001111");
        Mockito.when(prestamo.getDivisa()).thenReturn(DivisaEnum.PESO);
        Mockito.when(prestamo.getSaldoPrevio()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getIndex()).thenReturn("1");
        Mockito.when(prestamo.getFactorIndex()).thenReturn("10");
        Mockito.when(prestamo.getImporteAjussal()).thenReturn(new BigDecimal("10"));
        Mockito.when(prestamo.getVencimientoCuota()).thenReturn(new Date());
        Mockito.when(prestamo.getImporteTotalCuota()).thenReturn(new BigDecimal(10));
        Mockito.when(pagoPendientePrestamo.getCantidadCuotas()).thenReturn("20");
        Mockito.when(pagoPendientePrestamo.getPrestamo()).thenReturn(prestamo);

        reporte.setNombre("NOMBRE_ARCHIVO");
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        pagoPendientes.add(pagoPendientePrestamo);
        respuesta.setRespuesta(pagoPendientes);
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        resp.setRespuesta(reporte);

        Mockito.when(prestamoService.obtenerComprobantePrestamo(Matchers.any(PagoPendientePrestamo.class),
                Matchers.any(ComprobantePrestamo.class))).thenReturn(resp);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);
        Mockito.when(sesionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        FieldUtils.writeField(prestamoManager, "sessionParametros", sesionParametros, true);

        Respuesta<ReporteView> reporteView = prestamoManager.obtenerReporteComprobante("11");
        assertNotNull(reporteView);
        assertEquals("NOMBRE_ARCHIVO", reporteView.getRespuesta().getNombre());
        assertEquals(TipoArchivoEnum.PDF.getMimeTipe(), reporteView.getRespuesta().getTipoArchivo());
    }

    /**
     * Test detalle prestamos error servicio.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDetallePrestamosErrorServicio() throws ServiceException, IllegalAccessException {

        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();
        Cliente cliente = new Cliente();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();

        Cuenta cuenta = Mockito.mock(Cuenta.class);
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);
        Prestamo prestamo = Mockito.mock(Prestamo.class);
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = Mockito.mock(Respuesta.class);
        PreFormalizacion preFormalizacion = Mockito.mock(PreFormalizacion.class);
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = Mockito.mock(CuentaPrestamoDebitoAdherido.class);

        pagoPendientePrestamo.setPrestamo(prestamo);
        List<PagoPendiente> c = new ArrayList<PagoPendiente>();
        c.add(pagoPendientePrestamo);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(c);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(preFormalizacionRespuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.ERROR);
        Mockito.when(preFormalizacionRespuesta.getRespuesta()).thenReturn(null);
        Mockito.when(prestamo.getNumeroCuentaProducto()).thenReturn("0000000000000000000000000011");
        Mockito.when(prestamo.getTipoPrestamoEnum()).thenReturn(TipoPrestamoEnum.PRENDARIO);
        Mockito.when(cuenta.getNroSucursal()).thenReturn("033");
        Mockito.when(cuenta.getTipoCuenta()).thenReturn("1");
        Mockito.when(prestamo.getCuenta()).thenReturn(cuenta);
        Mockito.when(prestamo.getNumeroRecibo()).thenReturn("0000000000000001111");
        Mockito.when(prestamo.getDivisa()).thenReturn(DivisaEnum.PESO);
        Mockito.when(prestamo.getSaldoPrevio()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getIndex()).thenReturn("1");
        Mockito.when(prestamo.getFactorIndex()).thenReturn("110123123");
        Mockito.when(prestamo.getImporteAjussal()).thenReturn(new BigDecimal("10"));
        Mockito.when(prestamo.getVencimientoCuota()).thenReturn(new Date());
        Mockito.when(prestamo.getImporteTotalCuota()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getFecCotiz()).thenReturn(new Date());
        Mockito.when(prestamo.getNroExp()).thenReturn("3");
        Mockito.when(prestamo.getCotizCambio()).thenReturn(new BigDecimal("90"));
        Mockito.when(prestamo.getAjusteSaldo()).thenReturn(new BigDecimal("1500"));
        Mockito.when(prestamo.getCapitalPend()).thenReturn(new BigDecimal("1000"));
        Mockito.when(prestamo.getInteresesPend()).thenReturn(new BigDecimal("4000"));

        Mockito.when(cuentaPrestamoDebitoAdherido.getNroSucursal()).thenReturn("033");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNumero()).thenReturn("033");
        Mockito.when(preFormalizacion.getPrestamoDebitoAdherido()).thenReturn(cuentaPrestamoDebitoAdherido);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);
        Mockito.when(preFormalizacionPrestamoService.obtenerPreFormalizacion(prestamo))
                .thenReturn(preFormalizacionRespuesta);
        FieldUtils.writeField(prestamoManager, "sessionParametros", sesionParametros, true);
        ConsultaPrestamo consulta = new ConsultaPrestamo();
        consulta.setNumeroPrestamo("11");

        Respuesta<DetallePrestamoView> detallePrestamoView = prestamoManager.getDetallePrestamos(consulta, false);
        assertNotNull(detallePrestamoView);
        assertEquals(detallePrestamoView.getRespuesta().getPlazoPrestamo(), "-");
        assertEquals("033-00000000001/1", detallePrestamoView.getRespuesta().getNumeroPrestamo());
    }

    /**
     * Gets the detalle pre formalizacion error test.
     *
     * @return the detalle pre formalizacion error test
     * @throws ServiceException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getDetallePreFormalizacionErrorTest() throws ServiceException {

        Prestamo prestamo = new Prestamo();
        Respuesta<List<PagoPendiente>> respuestaListPagoPendiente = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listPagoPendiente = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendiente = new PagoPendientePrestamo();
        Mensaje mensaje = new Mensaje();
        Cuenta cuenta = new Cuenta();

        cuenta.setNroSucursal("012");
        mensaje.setMensaje("Mensaje de error");
        prestamo.setImporteTotalCuota(new BigDecimal("2000"));
        prestamo.setVencimientoCuota(new Date());
        prestamo.setIndex("");
        prestamo.setSaldoPrevio(new BigDecimal("500"));
        prestamo.setImporteAjussal(new BigDecimal("200"));
        prestamo.setNumeroRecibo("12345");
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setNumeroCuentaProducto("1234567");
        prestamo.setCuenta(cuenta);
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroCuentaProducto("11");
        prestamo.setNroExp("3");
        prestamo.setCotizCambio(new BigDecimal("90"));
        prestamo.setAjusteSaldo(new BigDecimal("1500"));
        prestamo.setCapitalPend(new BigDecimal("1000"));
        prestamo.setInteresesPend(new BigDecimal("4000"));
        prestamo.setFecCotiz(new Date());
        pagoPendiente.setPrestamo(prestamo);
        listPagoPendiente.add(pagoPendiente);
        respuestaListPagoPendiente.setRespuesta(listPagoPendiente);

        Mockito.when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenThrow(ServiceException.class);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListPagoPendiente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        ConsultaPrestamo consulta = new ConsultaPrestamo();
        consulta.setNumeroPrestamo("11");

        Respuesta<DetallePrestamoView> respuestaDetallePrestamoView = prestamoManager.getDetallePrestamos(consulta,
                false);
        DetallePrestamoView detallePrestamoView = respuestaDetallePrestamoView.getRespuesta();

        assertEquals("-", detallePrestamoView.getNumeroCuenta());
        assertNull(detallePrestamoView.getAliasCuenta());
        assertNull(detallePrestamoView.getTipoCuenta());
    }

    /**
     * Test detalle prestamos con importe ajuste zero.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDetallePrestamosConImporteAjusteZero() throws ServiceException, IllegalAccessException {
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuesta = new Respuesta<List<PagoPendiente>>();

        Prestamo prestamo = Mockito.mock(Prestamo.class);
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = Mockito.mock(Respuesta.class);
        PreFormalizacion preFormalizacion = Mockito.mock(PreFormalizacion.class);
        Cuenta cuenta = Mockito.mock(Cuenta.class);
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = Mockito.mock(CuentaPrestamoDebitoAdherido.class);

        pagoPendientePrestamo.setPrestamo(prestamo);
        List<PagoPendiente> c = new ArrayList<PagoPendiente>();
        c.add(pagoPendientePrestamo);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(c);

        Mockito.when(preFormalizacionRespuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        Mockito.when(preFormalizacionRespuesta.getRespuesta()).thenReturn(preFormalizacion);
        Mockito.when(prestamo.getNumeroCuentaProducto()).thenReturn("0000000000000000000000000011");
        Mockito.when(prestamo.getTipoPrestamoEnum()).thenReturn(TipoPrestamoEnum.PRENDARIO);
        Mockito.when(prestamo.getCuenta()).thenReturn(cuenta);
        Mockito.when(prestamo.getNumeroRecibo()).thenReturn("0000000000000001111");
        Mockito.when(prestamo.getDivisa()).thenReturn(DivisaEnum.PESO);
        Mockito.when(prestamo.getSaldoPrevio()).thenReturn(new BigDecimal(10));
        Mockito.when(prestamo.getIndex()).thenReturn("CVS");
        Mockito.when(prestamo.getFactorIndex()).thenReturn("999120000");
        Mockito.when(prestamo.getImporteAjussal()).thenReturn(new BigDecimal("0"));

        Mockito.when(prestamo.getVencimientoCuota()).thenReturn(new Date());
        Mockito.when(prestamo.getImporteTotalCuota()).thenReturn(new BigDecimal(10));

        Mockito.when(prestamo.getNroExp()).thenReturn("3");
        Mockito.when(prestamo.getCotizCambio()).thenReturn(new BigDecimal("90"));
        Mockito.when(prestamo.getAjusteSaldo()).thenReturn(new BigDecimal("1500"));
        Mockito.when(prestamo.getCapitalPend()).thenReturn(new BigDecimal("1000"));
        Mockito.when(prestamo.getInteresesPend()).thenReturn(new BigDecimal("4000"));
        Mockito.when(prestamo.getFecCotiz()).thenReturn(new Date());
        Mockito.when(cuenta.getNroSucursal()).thenReturn("033");
        Mockito.when(cuenta.getTipoCuenta()).thenReturn("1");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNroSucursal()).thenReturn("033");
        Mockito.when(cuentaPrestamoDebitoAdherido.getNumero()).thenReturn("033");
        Mockito.when(preFormalizacion.getPrestamoDebitoAdherido()).thenReturn(cuentaPrestamoDebitoAdherido);
        Mockito.when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        Mockito.when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(respuesta);
        Mockito.when(preFormalizacionPrestamoService.obtenerPreFormalizacion(prestamo))
                .thenReturn(preFormalizacionRespuesta);
        FieldUtils.writeField(prestamoManager, "sessionParametros", sesionParametros, true);
        ConsultaPrestamo consulta = new ConsultaPrestamo();
        consulta.setNumeroPrestamo("11");

        Respuesta<DetallePrestamoView> detallePrestamoView = prestamoManager.getDetallePrestamos(consulta, false);
        assertNotNull(detallePrestamoView);
        assertEquals(false, detallePrestamoView.getRespuesta().isShowCapitalAjustodoSobreLaCuota());
        assertEquals("033-00000000001/1", detallePrestamoView.getRespuesta().getNumeroPrestamo());
        assertEquals("Caja de ahorro en $", detallePrestamoView.getRespuesta().getTipoCuenta());
        assertEquals("CVS 999,12", detallePrestamoView.getRespuesta().getCoeficienteIndexacionReciboEnTermino());
    }

    /**
     * Armar uva view list test.
     */
    @Test
    public void armarUvaViewListTest() {
        Prestamo prestamo = new Prestamo();
        List<UvaView> uvaViewList = new ArrayList<UvaView>();

        prestamo.setCotizCambio(new BigDecimal("1386.1889"));
        prestamo.setAjusteSaldo(new BigDecimal("17.52000"));
        prestamo.setCapitalPend(new BigDecimal("227.6946"));
        prestamo.setInteresesPend(new BigDecimal("6.7111"));
        prestamo.setFecCotiz(new Date());
        uvaViewList = prestamoManager.armarUvaViewList(prestamo);

        assertNotNull(uvaViewList);

    }

    @Test
    public void verDetallePrestamoOk() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        
        Prestamo prestamo = new Prestamo();
        prestamo.setTasaPrestamo(new BigDecimal("38.384849"));
        prestamo.setTasaAnualEfectiva(new BigDecimal("20.3984302"));
        prestamo.setCostoFinancieroTotal(new BigDecimal("21.48493"));
        prestamo.setCostoFinancieroTotalSinImpuestos(new BigDecimal("23.8483839"));
        prestamo.setNumeroCuentaProducto(cuenta.getNroCuentaProducto());
        prestamo.setCuenta(cuenta);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");
        consultaPrestamo.setTipoPrestamo("Súper Préstamo Personal");
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.getPrestamoDebitoAdherido().setNumero("000020202");
        preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar("00000000010000000");
        preFormalizacion.getPrestamoDebitoAdherido().setCft(BigDecimal.ZERO);
        preFormalizacion.getPrestamoDebitoAdherido().setCftsimp(BigDecimal.ZERO);
        preFormalizacion.setCodigoDestinoPrestamo("00351");
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);
        preFormalizacionRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);


        when(prestamoBo.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(prestamo)).thenReturn(preFormalizacionRespuesta);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
        when(destinoPrestamoBo.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");

        // Then
        Respuesta<DetalleCuotaPrestamoView> respuesta = prestamoManager.verDetallePrestamo(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void verDetallePrestamoOkCuentaDebitoConAlias() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        cuenta.setAlias("Pepito");
        
        Prestamo prestamo = new Prestamo();
        prestamo.setTasaPrestamo(new BigDecimal("38.384849"));
        prestamo.setTasaAnualEfectiva(new BigDecimal("20.3984302"));
        prestamo.setCostoFinancieroTotal(new BigDecimal("21.48493"));
        prestamo.setCostoFinancieroTotalSinImpuestos(new BigDecimal("23.8483839"));
        prestamo.setNumeroCuentaProducto(cuenta.getNroCuentaProducto());
        prestamo.setCuenta(cuenta);
        
        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");
        consultaPrestamo.setTipoPrestamo("Súper Préstamo Prendario");
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.getPrestamoDebitoAdherido().setNumero("000020202");
        preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar("00000000010000000");
        preFormalizacion.setCodigoDestinoPrestamo("00351");
        preFormalizacion.getPrestamoDebitoAdherido().setCft(BigDecimal.ZERO);
        preFormalizacion.getPrestamoDebitoAdherido().setCftsimp(BigDecimal.ZERO);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);
        preFormalizacionRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);

        when(prestamoBo.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(prestamo)).thenReturn(preFormalizacionRespuesta);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
        when(destinoPrestamoBo.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");

        // Then
        Respuesta<DetalleCuotaPrestamoView> respuesta = prestamoManager.verDetallePrestamo(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void verDetallePrestamoErrorPreFormalizacion() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Prestamo prestamo = new Prestamo();
        prestamo.setTasaPrestamo(new BigDecimal("38.384849"));
        prestamo.setTasaAnualEfectiva(new BigDecimal("20.3984302"));
        prestamo.setCostoFinancieroTotal(new BigDecimal("21.48493"));
        prestamo.setCostoFinancieroTotalSinImpuestos(new BigDecimal("23.8483839"));

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");
        consultaPrestamo.setTipoPrestamo("Súper Préstamo Prendario");
        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        preFormalizacionRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        preFormalizacionRespuesta.setRespuestaVacia(true);

        Cuenta cuenta = CuentaMock.completarInfoCuenta();

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, null);

        when(prestamoBo.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(prestamo)).thenReturn(preFormalizacionRespuesta);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
        when(destinoPrestamoBo.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");

        // Then
        Respuesta<DetalleCuotaPrestamoView> respuesta = prestamoManager.verDetallePrestamo(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    @Test
    public void verDetallePrestamoErrorPrestamo() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");
        consultaPrestamo.setTipoPrestamo("Súper Préstamo Hipotecario");

        Cuenta cuenta = CuentaMock.completarInfoCuenta();

        when(prestamoBo.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentaSiContieneNumero(Matchers.anyString())).thenReturn(cuenta);
        when(destinoPrestamoBo.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");

        // Then
        Respuesta<DetalleCuotaPrestamoView> respuesta = prestamoManager.verDetallePrestamo(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

}
