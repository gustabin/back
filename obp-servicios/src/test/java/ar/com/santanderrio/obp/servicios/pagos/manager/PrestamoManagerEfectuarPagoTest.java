package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;

/**
 * The Class PrestamoManagerEfectuarPagoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoManagerEfectuarPagoTest {

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManagerImpl prestamoManager = new PrestamoManagerImpl();

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The prestamo service. */
    @Mock
    private PrestamoService prestamoService;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /**
     * Efectuar pago test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void efectuarPagoTest() throws ServiceException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        ApplicationContext context = mock(ApplicationContext.class);
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        Prestamo prestamo = new Prestamo();
        Cliente cliente = new Cliente();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentaView = new ArrayList<CuentasAdhesionDebitoView>();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        Respuesta<ComprobantePrestamo> respuestaComprobantePrestamo = new Respuesta<ComprobantePrestamo>();
        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();

        cuentaPrestamoDebitoAdherido.setNumero("882006");
        cuentaPrestamoDebitoAdherido.setNroSucursal("234");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);
        preFormalizacion.setPlazo("48 hs");
        identificacionCuenta.setNroCuentaProducto("200688");
        identificacionCuenta.setNroSucursal("123");
        cuentaView.setShowSaldoPesos(true);
        cuentaView.setNumero("123-1234567");
        listaCuentaView.add(cuentaView);
        cuentasView.setCuentas(listaCuentaView);
        respuestaCuentasView.setRespuesta(cuentasView);
        listaPagoPendiente.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendiente);
        ContextHolder.setContext(context);
        consultaPrestamo.setIdProceso("123456");
        prestamo.setNumeroCuentaProducto("4321");
        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPendientePrestamo.setCodigoClienteEmpresa("4321");
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setIdProceso("123456");
        respuestaComprobantePrestamo.setRespuesta(comprobantePrestamo);
        respuestaComprobantePrestamo.setEstadoRespuesta(EstadoRespuesta.OK);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(context.getBean(ContadorIntentos.class)).thenReturn(contadorIntentos);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(prestamoService.pagar(Matchers.any(PagoPrestamo.class), Matchers.anyInt()))
                .thenReturn(respuestaComprobantePrestamo);
        when(prestamoService.obtenerMensajePagoOk(Matchers.any(Prestamo.class),
                Matchers.any(ComprobantePrestamo.class))).thenReturn("Pago Ok");

        Respuesta<RespuestaPagoPrestamoView> respuestaRespuestaPagoPrestamoView = prestamoManager
                .efectuarPago(consultaPrestamo);
        RespuestaPagoPrestamoView respuestaPagoPrestamosView = respuestaRespuestaPagoPrestamoView.getRespuesta();

        assertNotNull(respuestaPagoPrestamosView);
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_PRESTAMO,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        assertEquals(respuestaPagoPrestamosView.getMensaje(), "Pago Ok");
        assertNotNull(pagoPrestamo.getComprobantePrestamo());
    }

    /**
     * Efectuar pago saldo insuficiente test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void efectuarPagoSaldoInsuficienteTest() throws ServiceException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        ApplicationContext context = mock(ApplicationContext.class);
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        Prestamo prestamo = new Prestamo();
        Cliente cliente = new Cliente();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentaView = new ArrayList<CuentasAdhesionDebitoView>();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        Respuesta<ComprobantePrestamo> respuestaComprobantePrestamo = new Respuesta<ComprobantePrestamo>();
        List<ItemMensajeRespuesta> listaItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        cuentaPrestamoDebitoAdherido.setNumero("882006");
        cuentaPrestamoDebitoAdherido.setNroSucursal("234");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);
        preFormalizacion.setPlazo("48 hs");
        identificacionCuenta.setNroCuentaProducto("200688");
        identificacionCuenta.setNroSucursal("123");
        cuentaView.setNumero("123-1234567");
        cuentaView.setShowSaldoPesos(true);
        listaCuentaView.add(cuentaView);
        cuentasView.setCuentas(listaCuentaView);
        respuestaCuentasView.setRespuesta(cuentasView);
        listaPagoPendiente.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendiente);
        ContextHolder.setContext(context);
        consultaPrestamo.setIdProceso("123456");
        prestamo.setNumeroCuentaProducto("4321");
        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPendientePrestamo.setCodigoClienteEmpresa("4321");
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setIdProceso("123456");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_SALDOS_INSUFICIENTES.getDescripcion());
        listaItemMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaComprobantePrestamo.setItemMensajeRespuesta(listaItemMensajeRespuesta);
        respuestaComprobantePrestamo.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(context.getBean(ContadorIntentos.class)).thenReturn(contadorIntentos);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(prestamoService.pagar(Matchers.any(PagoPrestamo.class), Matchers.anyInt()))
                .thenReturn(respuestaComprobantePrestamo);
        when(prestamoService.obtenerMensajePagoOk(Matchers.any(Prestamo.class),
                Matchers.any(ComprobantePrestamo.class))).thenReturn("Pago Ok");

        Respuesta<RespuestaPagoPrestamoView> respuestaRespuestaPagoPrestamoView = prestamoManager
                .efectuarPago(consultaPrestamo);

        Mockito.verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_SALDO_INSUFICIENTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    /**
     * Efectuar pago error anterioridad test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void efectuarPagoErrorAnterioridadTest() throws ServiceException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        ApplicationContext context = mock(ApplicationContext.class);
        ContadorIntentos contadorIntentos = new ContadorIntentos();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendientes = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        Prestamo prestamo = new Prestamo();
        Cliente cliente = new Cliente();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentaView = new ArrayList<CuentasAdhesionDebitoView>();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        Respuesta<ComprobantePrestamo> respuestaComprobantePrestamo = new Respuesta<ComprobantePrestamo>();
        List<ItemMensajeRespuesta> listaItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();

        cuentaPrestamoDebitoAdherido.setNumero("882006");
        cuentaPrestamoDebitoAdherido.setNroSucursal("234");
        preFormalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);
        preFormalizacion.setPlazo("48 hs");
        identificacionCuenta.setNroCuentaProducto("200688");
        identificacionCuenta.setNroSucursal("123");
        cuentaView.setShowSaldoPesos(true);
        cuentaView.setNumero("123-1234567");
        listaCuentaView.add(cuentaView);
        cuentasView.setCuentas(listaCuentaView);
        respuestaCuentasView.setRespuesta(cuentasView);
        listaPagoPendiente.add(pagoPendientePrestamo);
        respuestaListaPagoPendientes.setRespuesta(listaPagoPendiente);
        ContextHolder.setContext(context);
        consultaPrestamo.setIdProceso("123456");
        prestamo.setNumeroCuentaProducto("4321");
        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        pagoPendientePrestamo.setPrestamo(prestamo);
        pagoPendientePrestamo.setCodigoClienteEmpresa("4321");
        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);
        pagoPrestamo.setIdProceso("123456");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_PAGO_CON_ANTERIORIDAD.getDescripcion());
        listaItemMensajeRespuesta.add(itemMensajeRespuesta);
        respuestaComprobantePrestamo.setItemMensajeRespuesta(listaItemMensajeRespuesta);
        respuestaComprobantePrestamo.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(cuentaManager.getCuentasActivas()).thenReturn(respuestaCuentasView);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(context.getBean(ContadorIntentos.class)).thenReturn(contadorIntentos);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendientes);
        when(prestamoService.pagar(Matchers.any(PagoPrestamo.class), Matchers.anyInt()))
                .thenReturn(respuestaComprobantePrestamo);
        when(prestamoService.obtenerMensajePagoOk(Matchers.any(Prestamo.class),
                Matchers.any(ComprobantePrestamo.class))).thenReturn("Pago Ok");

        Respuesta<RespuestaPagoPrestamoView> respuestaRespuestaPagoPrestamoView = prestamoManager
                .efectuarPago(consultaPrestamo);
        RespuestaPagoPrestamoView respuestaPagoPrestamosView = respuestaRespuestaPagoPrestamoView.getRespuesta();

        Mockito.verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PAGO_CON_ANTERIORIDAD,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

}
