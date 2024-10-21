package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PreFormalizacionPrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.impl.SessionPagos;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;

/**
 * The Class PrestamoManagerObtenerConfigTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoManagerObtenerConfigTest {

    /** The Constant INICIO_OPERACIONES. */
    private static final String INICIO_OPERACIONES = "0";

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManagerImpl prestamoManager = new PrestamoManagerImpl();

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The session pagos. */
    @Mock
    private SessionPagos sessionPagos;

    /** The pre formalizacion prestamo service. */
    @Mock
    private PreFormalizacionPrestamoService preFormalizacionPrestamoService;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The prestamo service. */
    @Mock
    private PrestamoService prestamoService;
    
    /** The respuesta Factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /**
     * Obtener configuracion test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionTest() throws ServiceException, IllegalAccessException {

        Calendar calendar = Calendar.getInstance();
        FieldUtils.writeField(prestamoManager, "horaInicioOperaciones", INICIO_OPERACIONES, true);
        FieldUtils.writeField(prestamoManager, "horaFinOperaciones", "24", true);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        CuentasView cuentasViewFiltradas = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        List<CuentasAdhesionDebitoView> listaCuentasFiltradas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView3 = new CuentasAdhesionDebitoView();
        Cliente cliente = new Cliente();

        cuentaView1.setNumero("000-000001/1");
        cuentaView2.setNumero("000-000002/1");
        cuentaView3.setNumero("000-000003/1");

        IdentificacionCuenta identificacionCuenta1 = new IdentificacionCuenta();
        identificacionCuenta1.setNroCuentaProducto("0000011");
        identificacionCuenta1.setNroSucursal("000");

        IdentificacionCuenta identificacionCuenta2 = new IdentificacionCuenta();
        identificacionCuenta2.setNroCuentaProducto("0000021");
        identificacionCuenta2.setNroSucursal("000");

        IdentificacionCuenta identificacionCuenta3 = new IdentificacionCuenta();
        identificacionCuenta3.setNroCuentaProducto("0000031");
        identificacionCuenta3.setNroSucursal("000");

        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setNroSucursal("000");
        prestamoDebitoAdherido.setNumero("0000031");
        Cuenta cuenta = new Cuenta();
        consultaPrestamo.setNumeroPrestamo("200688");
        prestamo.setNumeroCuentaProducto("200688");
        preFormalizacion.setPlazo("Plazo Prueba");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);

        cuentaView1.setShowSaldoPesos(true);
        cuentaView2.setShowSaldoPesos(false);
        cuentaView3.setShowSaldoPesos(true);

        listaCuentas.add(cuentaView1);
        listaCuentas.add(cuentaView2);
        listaCuentas.add(cuentaView3);

        listaCuentasFiltradas.add(cuentaView1);
        cuentasView.setCuentas(listaCuentas);
        cuentasViewFiltradas.setCuentas(listaCuentasFiltradas);
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPreformalizacion.setRespuesta(preFormalizacion);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(prestamoService.validarHorario()).thenReturn(true);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = respuesta.getRespuesta();

        assertNotNull(respuesta);
        assertNotNull(configuracionPagoCuotaPrestamo);
        assertNotNull(configuracionPagoCuotaPrestamo.getIdProceso());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        // Deberia filtrarse la cuenta en dolares y quedar solo la cuentaView1
        assertEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas().get(0).getNumero(), "000-000001/1");
        assertNotEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas().get(0).getNumero(),
                "000-000003/1");
        assertTrue("Plazo Prueba".equals(configuracionPagoCuotaPrestamo.getPlazoPrestamo()));
        assertEquals(configuracionPagoCuotaPrestamo.getFechaVencimiento(), "20/10/2017");
        assertEquals(configuracionPagoCuotaPrestamo.getImporteCuota(), "500,00");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroCuota(), "81243");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamoLabel(), "1");
        assertEquals(configuracionPagoCuotaPrestamo.getDivisa(), DivisaEnum.PESO.getSimbolo());

    }

    /**
     * Obtener configuracion reintento test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionReintentoTest() throws ServiceException, IllegalAccessException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        Cuenta cuenta = new Cuenta();

        consultaPrestamo.setNumeroPrestamo("200688");
        consultaPrestamo.setIdProceso("2345");
        pagoPrestamo.setIdProceso("2345");
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        prestamo.setNumeroCuentaProducto("200688");
        identificacionCuenta.setNroCuentaProducto("1234567");
        preFormalizacion.setPlazo("Plazo Prueba");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        cuentaView1.setShowSaldoPesos(true);
        cuentaView1.setNumero("123-1234567");
        listaCuentas.add(cuentaView1);
        cuentasView.setCuentas(listaCuentas);
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.DOLAR);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        when(prestamoService.validarHorario()).thenReturn(true);
        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = respuesta.getRespuesta();

        assertNotNull(respuesta);
        assertNotNull(configuracionPagoCuotaPrestamo);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas(), cuentasView.getCuentas());
        assertTrue("Plazo Prueba".equals(configuracionPagoCuotaPrestamo.getPlazoPrestamo()));
        assertEquals(configuracionPagoCuotaPrestamo.getFechaVencimiento(), "20/10/2017");
        assertEquals(configuracionPagoCuotaPrestamo.getImporteCuota(), "500,00");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroCuota(), "81243");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamoLabel(), "1");
        assertEquals(configuracionPagoCuotaPrestamo.getDivisa(), DivisaEnum.DOLAR.getSimbolo());

    }

    /**
     * Obtener configuracion pre formalizacion exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracionPreFormalizacionExceptionTest() throws ServiceException, IllegalAccessException {

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        Cuenta cuenta = new Cuenta();

        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        pagoPrestamo.setCuentaSeleccionada(cuenta);

        consultaPrestamo.setNumeroPrestamo("200688");
        prestamo.setNumeroCuentaProducto("200688");
        cuentaView1.setShowSaldoPesos(true);
        listaCuentas.add(cuentaView1);
        cuentasView.setCuentas(listaCuentas);
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        cuenta.setNroCuentaProducto("1234567");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenThrow(ServiceException.class);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(prestamoService.validarHorario()).thenReturn(true);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = respuesta.getRespuesta();

        assertNotNull(respuesta);
        assertNotNull(configuracionPagoCuotaPrestamo);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas(), cuentasView.getCuentas());
        assertTrue("-".equals(configuracionPagoCuotaPrestamo.getPlazoPrestamo()));
        assertEquals(configuracionPagoCuotaPrestamo.getFechaVencimiento(), "20/10/2017");
        assertEquals(configuracionPagoCuotaPrestamo.getImporteCuota(), "500,00");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroCuota(), "81243");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamoLabel(), "1");
        assertEquals(configuracionPagoCuotaPrestamo.getDivisa(), DivisaEnum.PESO.getSimbolo());

    }

    /**
     * Obtener configuracion cuentas warning test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionCuentasWarningTest() throws ServiceException, IllegalAccessException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        Cuenta cuenta = new Cuenta();
        List<ItemMensajeRespuesta> listaItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        List<CuentasAdhesionDebitoView> temp = new ArrayList<CuentasAdhesionDebitoView>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        consultaPrestamo.setNumeroPrestamo("200688");
        prestamo.setNumeroCuentaProducto("200688");
        identificacionCuenta.setNroCuentaProducto("1234567");
        preFormalizacion.setPlazo("Plazo Prueba");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        cuentaView1.setShowSaldoPesos(true);
        cuentaView1.setNumero("123-1234567");
        cuentaView2.setShowSaldoPesos(true);
        cuentaView2.setNumero("123-1234567");
        listaCuentas.add(cuentaView1);
        listaCuentas.add(cuentaView2);
        cuentasView.setCuentas(listaCuentas);
        listaItemMensajeRespuesta.add(itemMensajeRespuesta);
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_ITEM_CUENTA.getDescripcion());
        itemMensajeRespuesta.setTag("cuentas[123]");
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaCuentasView.setItemMensajeRespuesta(listaItemMensajeRespuesta);
        respuestaPreformalizacion.setRespuesta(preFormalizacion);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        respuestaCuentasView.getRespuesta().getCuentas().get(0).setSaldoPesos("--");
        when(cuentaManager.agregarSaldoErrorneoEnCuentasActivas(respuestaCuentasView, temp))
                .thenReturn(respuestaCuentasView);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);

        when(prestamoService.validarHorario()).thenReturn(true);
        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = respuesta.getRespuesta();

        assertNotNull(respuesta);
        assertNotNull(configuracionPagoCuotaPrestamo);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas(), cuentasView.getCuentas());
        assertTrue("Plazo Prueba".equals(configuracionPagoCuotaPrestamo.getPlazoPrestamo()));
        assertEquals(configuracionPagoCuotaPrestamo.getFechaVencimiento(), "20/10/2017");
        assertEquals(configuracionPagoCuotaPrestamo.getImporteCuota(), "500,00");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroCuota(), "81243");
        assertEquals(configuracionPagoCuotaPrestamo.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(configuracionPagoCuotaPrestamo.getTipoPrestamoLabel(), "1");
        assertEquals(configuracionPagoCuotaPrestamo.getDivisa(), DivisaEnum.PESO.getSimbolo());
        assertEquals(configuracionPagoCuotaPrestamo.getCuentasView().getCuentas().get(0).getSaldoPesos(), "--");

    }

    /**
     * Obtener configuracion mantiene cuenta seleccionada test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionMantieneCuentaSeleccionadaTest() throws ServiceException, IllegalAccessException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        CuentasView cuentasViewFiltradas = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        List<CuentasAdhesionDebitoView> listaCuentasFiltradas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView3 = new CuentasAdhesionDebitoView();

        cuentaView1.setNumero("000-000001/1");
        cuentaView2.setNumero("000-000002/1");
        cuentaView3.setNumero("000-000003/1");

        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setNroSucursal("000");
        prestamoDebitoAdherido.setNumero("0000031");
        Cuenta cuenta = new Cuenta();
        consultaPrestamo.setNumeroPrestamo("200688");
        prestamo.setNumeroCuentaProducto("200688");
        preFormalizacion.setPlazo("Plazo Prueba");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);

        cuentaView1.setShowSaldoPesos(true);
        cuentaView2.setShowSaldoPesos(false);
        cuentaView3.setShowSaldoPesos(true);

        listaCuentas.add(cuentaView1);
        listaCuentas.add(cuentaView2);
        listaCuentas.add(cuentaView3);

        listaCuentasFiltradas.add(cuentaView1);
        cuentasView.setCuentas(listaCuentas);
        cuentasViewFiltradas.setCuentas(listaCuentasFiltradas);
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPreformalizacion.setRespuesta(preFormalizacion);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        Cuenta cuentaSeleccionada = new Cuenta();
        cuentaSeleccionada.setNroCuentaProducto("0000011");
        cuentaSeleccionada.setNroSucursal("000");
        pagoPrestamo.setCuentaSeleccionada(cuentaSeleccionada);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);

        when(prestamoService.validarHorario()).thenReturn(true);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = respuesta.getRespuesta();

        assertNotNull(respuesta);
        assertNotNull(configuracionPagoCuotaPrestamo);
        assertNotNull(configuracionPagoCuotaPrestamo.getIdProceso());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getCuentasView().getSelected(), 0);

    }

    /**
     * Obtener configuracion fuera horario test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionFueraHorarioTest() throws ServiceException, IllegalAccessException {

        final String FIN_OPERACIONES = INICIO_OPERACIONES;

        FieldUtils.writeField(prestamoManager, "horaInicioOperaciones", INICIO_OPERACIONES, true);
        FieldUtils.writeField(prestamoManager, "horaFinOperaciones", INICIO_OPERACIONES, true);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error El horario es de {0} a {1}.");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(prestamoService.validarHorario()).thenReturn(false);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                "Mensaje de error El horario es de " + INICIO_OPERACIONES + " a " + FIN_OPERACIONES + ".");

    }

    /**
     * Obtener configuracion get cuentas error test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void obtenerConfiguracionGetCuentasErrorTest() throws ServiceException, IllegalAccessException {

        Calendar calendar = Calendar.getInstance();
        FieldUtils.writeField(prestamoManager, "horaInicioOperaciones", INICIO_OPERACIONES, true);
        FieldUtils.writeField(prestamoManager, "horaFinOperaciones", "24", true);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendientes = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();

        IdentificacionCuenta identificacionCuenta1 = new IdentificacionCuenta();
        identificacionCuenta1.setNroCuentaProducto("0000011");
        identificacionCuenta1.setNroSucursal("000");

        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setNroSucursal("000");
        prestamoDebitoAdherido.setNumero("0000031");
        Cuenta cuenta = new Cuenta();
        consultaPrestamo.setNumeroPrestamo("200688");
        prestamo.setNumeroCuentaProducto("200688");
        preFormalizacion.setPlazo("Plazo Prueba");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);

        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaPreformalizacion.setRespuesta(preFormalizacion);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setNroSucursal("123");
        prestamo.setCuenta(cuenta);
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setClaseCuenta("1");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setNumeroRecibo("081243");
        calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(500));
        pagoPendientePrestamo.setPrestamo(prestamo);
        listaPagoPendientes.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendientes);
        respuestaListaPagoPendientes.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(prestamoService.validarHorario()).thenReturn(true);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = prestamoManager
                .obtenerConfiguracionPagoCuotaPrestamo(consultaPrestamo, false);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Mensaje de error");

    }

}
