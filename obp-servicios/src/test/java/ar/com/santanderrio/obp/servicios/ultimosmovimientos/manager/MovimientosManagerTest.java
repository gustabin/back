package ar.com.santanderrio.obp.servicios.ultimosmovimientos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import java.util.Calendar;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionMovimientosPendientesDeConfirmacion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionMovimientosValores;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ChequesYValoresPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.MovimientosPendientesDeConfirmacionBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.UltimosMovimientosBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.CuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.UltimosMovimientosBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientosPaginated;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosPendientesDeConfirmacion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosValoresPendientes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoOperacionMovimimiento;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.MovimientosManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.EstadoDetalleMovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoValoresView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientosPendientesDeConfirmacionView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class MovimientosManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovimientosManagerTest {

    /** The movimientos manager. */
    @InjectMocks
    private MovimientosManagerImpl movimientosManager = new MovimientosManagerImpl();

    /** The session cliente. */
    @Mock
    private SesionCliente sessionCliente;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO = new CuentaBOImpl();

    /** The estadistica manager. */
    @Mock
    private SessionMovimientosPendientesDeConfirmacion sessionMovimientosPendientesDeConfirmacion;

    /** The session movimientos valores. */
    @Mock
    private SessionMovimientosValores sessionMovimientosValores;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The obtener movimientos pendientes. */
    @Mock
    private MovimientosPendientesDeConfirmacionBO obtenerMovimientosPendientes;

    /** The session movimientos. */
    @Mock
    private SessionMovimientos sessionMovimientos;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    @Mock
    private ReporteDAO reporteDAO;
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje manager. */
    @Mock
    private MensajeManager mensajeManager;
    
    /** The ultimos movimientos BO. */
    @Mock
    private UltimosMovimientosBO ultimosMovimientosBO = new UltimosMovimientosBOImpl();
    
    /** The cheques Y valores pendientes de confirmacion BO. */
    @Mock
    private ChequesYValoresPendientesDeConfirmacionBO chequesYValoresPendientesDeConfirmacionBO;
    
    /**
     * Gets the ultimos movimientos test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void getUltimosMovimientosTest() throws ServiceException, BusinessException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("01");
        Cliente cliente = new Cliente();
        Respuesta<DetalleUltimosMovimientosPaginated> respuestaDetalleUltimosMovimientosPaginated = new Respuesta<DetalleUltimosMovimientosPaginated>();
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        DetalleUltimosMovimientosPaginated detalleUltimosMovimientosPaginated = new DetalleUltimosMovimientosPaginated(
                detalleMovimientoList);
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        ConsultaUltimosMovimientos cm = new ConsultaUltimosMovimientos();
        cm.setCuenta(getCuenta(getCliente()));
        cm.setOffset(0);
        when(sessionMovimientos.getFiltro()).thenReturn(cm);

        Calendar calendarFechaValor = Calendar.getInstance();
        calendarFechaValor.set(2016, 9, 20);
        Calendar calendarFecha = Calendar.getInstance();
        calendarFecha.set(2016, 10, 20);
        detalleMovimiento.setFecha(calendarFecha.getTime());
        detalleMovimiento.setFechaValor(calendarFechaValor.getTime());
        detalleMovimiento.setImporteMovimiento(new BigDecimal("500"));
        detalleMovimiento.setSaldoCuenta(new BigDecimal("50000"));
        detalleMovimientoList.add(detalleMovimiento);

        DetalleMovimientoEntity detalleMovimientoDelDia = new DetalleMovimientoEntity();

        detalleMovimientoDelDia.setFecha(new Date());
        detalleMovimientoDelDia.setFechaValor(new Date());
        detalleMovimientoDelDia.setImporteMovimiento(new BigDecimal("500"));
        detalleMovimientoDelDia.setSaldoCuenta(new BigDecimal("50000"));
        detalleMovimientoDelDia.setDelDia(true);
        detalleMovimientoList.add(detalleMovimientoDelDia);

        respuestaDetalleUltimosMovimientosPaginated.setRespuesta(detalleUltimosMovimientosPaginated);
        respuestaDetalleUltimosMovimientosPaginated.setEstadoRespuesta(EstadoRespuesta.OK);
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        cuenta.setCliente(cliente);
        cuenta.setAlias("Alias Cuenta");
        cuenta.setTipoCuenta("2");
        consultaUltimosMovimientosView.setNumero("033-587456");
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setImporteDesde(new BigDecimal("5"));
        consultaUltimosMovimientos.setImporteHasta(new BigDecimal("5"));
        consultaUltimosMovimientos.setPalabraClave("");
        consultaUltimosMovimientos.setCuenta(cuenta);

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("phone");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        
        Mensaje mensaje = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosRespuesta = getDetalleUltimosMovimientosRespuesta();
        Respuesta<ConsultaUltimosMovimientos> resp = respuestaFactory.crearRespuestaOk(consultaUltimosMovimientos);
        when(ultimosMovimientosBO.validarFiltros(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class), Matchers.anyBoolean())).thenReturn(resp);
        when(ultimosMovimientosBO.obtenerUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class))).thenReturn(detalleUltimosMovimientosRespuesta);
        
        Respuesta<MovimientoView> respuesta = movimientosManager.getMovimientos(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        // Boolean.FALSE);
        // assertEquals(movimientoView.getItemsMovimiento().get(0).getDivisa(),
        // DivisaEnum.PESO.getSimbolo());
        // assertEquals(movimientoView.getItemsMovimiento().get(1).getSaldo(),

    }

    
    public Respuesta<DetalleUltimosMovimientos> getDetalleUltimosMovimientosRespuesta() {
        Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosRespuesta = new Respuesta<DetalleUltimosMovimientos>();
        detalleUltimosMovimientosRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        List<DetalleMovimientoEntity> detalleMovimientosList = new ArrayList<DetalleMovimientoEntity>();
        for (int i = 0; i < 5; i++) {
            DetalleMovimientoEntity detalleMovimientoEntity = new DetalleMovimientoEntity();
            detalleMovimientoEntity.setId(String.valueOf(i));
            detalleMovimientoEntity.setFechaValor(new Date());
            detalleMovimientoEntity.setDescripcion("descripcion" + i);
            detalleMovimientoEntity.setDescripcionAdicional(StringUtils.EMPTY);
            detalleMovimientoEntity.setImporteMovimiento(BigDecimal.ZERO);
            detalleMovimientoEntity.setIsCajaDeAhoroEnPesos(Boolean.TRUE);
            detalleMovimientoEntity.setIsCuentaCorrienteEnPesos(Boolean.FALSE);
            detalleMovimientoEntity.setIsMovimientoEnDolares(Boolean.FALSE);
            detalleMovimientoEntity.setFecha(new Date());
            detalleMovimientoEntity.setSaldoCuenta(BigDecimal.ZERO);
            detalleMovimientoEntity.setNumeroReferencia(StringUtils.EMPTY);
            detalleMovimientoEntity.setHora(StringUtils.EMPTY);
            detalleMovimientoEntity.setNumeroSucursal("00" + i);
            detalleMovimientoEntity.setDescripcionSucursal("Sucursal " + i);
            detalleMovimientoEntity.setCheque(Boolean.FALSE);
            detalleMovimientoEntity.setRechazado(Boolean.FALSE);
            detalleMovimientoEntity.setMotivoRechazo(StringUtils.EMPTY);
            detalleMovimientoEntity.setObservacion(StringUtils.EMPTY);
            detalleMovimientoEntity.setDelDia(Boolean.FALSE);
            detalleMovimientosList.add(detalleMovimientoEntity);
        }
        detalleUltimosMovimientos.setDetalleMovimiento(detalleMovimientosList);
        detalleUltimosMovimientosRespuesta.setRespuesta(detalleUltimosMovimientos);
        return detalleUltimosMovimientosRespuesta;
    }
    
    /**
     * Gets the ultimos movimientos warning vacio test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void getUltimosMovimientosWarningVacioTest() throws ServiceException, BusinessException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        Respuesta<DetalleUltimosMovimientosPaginated> respuestaDetalleUltimosMovimientosPaginated = new Respuesta<DetalleUltimosMovimientosPaginated>();
        DetalleUltimosMovimientosPaginated detalleUltimosMovimientosPaginated = new DetalleUltimosMovimientosPaginated(
                null);
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        List<ItemMensajeRespuesta> listItemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError(TipoError.SIN_MOVIMIENTOS.getDescripcion());
        itemMensajeRespuesta.setMensaje(
                "No se registran movimientos para la búsqueda. Por favor, intentalo con otro rango de fechas, palabra clave o importe.");
        listItemMensajeRespuesta.add(itemMensajeRespuesta);
        Calendar calendarFechaValor = Calendar.getInstance();
        calendarFechaValor.set(2016, 9, 20);
        Calendar calendarFecha = Calendar.getInstance();
        calendarFecha.set(2016, 10, 20);
        respuestaDetalleUltimosMovimientosPaginated.setRespuesta(detalleUltimosMovimientosPaginated);
        respuestaDetalleUltimosMovimientosPaginated.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaDetalleUltimosMovimientosPaginated.setRespuestaVacia(true);
        respuestaDetalleUltimosMovimientosPaginated.setItemMensajeRespuesta(listItemMensajeRespuesta);
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        cuenta.setCliente(cliente);
        cuenta.setAlias("Alias Cuenta");
        cuenta.setTipoCuenta("1");
        consultaUltimosMovimientosView.setNumero("033-587456");
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setImporteDesde(new BigDecimal("5"));
        consultaUltimosMovimientos.setImporteHasta(new BigDecimal("5"));
        consultaUltimosMovimientos.setPalabraClave("");
        consultaUltimosMovimientos.setCuenta(cuenta);

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);

        Mensaje mensaje = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosRespuesta = new Respuesta<DetalleUltimosMovimientos>();
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        detalleUltimosMovimientos.setDetalleMovimiento(new ArrayList<DetalleMovimientoEntity>());
        
        detalleUltimosMovimientosRespuesta.setRespuesta(detalleUltimosMovimientos);
        detalleUltimosMovimientosRespuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        detalleUltimosMovimientosRespuesta.setRespuestaVacia(Boolean.TRUE);
        detalleUltimosMovimientosRespuesta.setItemMensajeRespuesta(listItemMensajeRespuesta);
        
        Respuesta<ConsultaUltimosMovimientos> respuestaFiltro = new Respuesta<ConsultaUltimosMovimientos>();
        respuestaFiltro.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaFiltro.setRespuesta(consultaUltimosMovimientos);
        when(ultimosMovimientosBO.validarFiltros(Matchers.any(ConsultaUltimosMovimientos.class), 
        		Matchers.any(ConsultaUltimosMovimientos.class), Matchers.anyBoolean())).thenReturn(respuestaFiltro);
        when(ultimosMovimientosBO.obtenerUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class), 
        		Matchers.any(ConsultaUltimosMovimientos.class))).thenReturn(detalleUltimosMovimientosRespuesta);
        when(sessionMovimientos.getFiltro()).thenReturn(null,consultaUltimosMovimientos, consultaUltimosMovimientos);
        Respuesta<MovimientoView> respuesta = movimientosManager.getMovimientos(consultaUltimosMovimientosView);
        
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertNotNull(respuesta.getRespuesta());
        assertEquals(TipoError.SIN_MOVIMIENTOS.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Gets the ultimos movimientos warning parcial test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void getUltimosMovimientosWarningParcialTest() throws ServiceException, BusinessException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();

        Calendar calendarFechaValor = Calendar.getInstance();
        calendarFechaValor.set(2016, 9, 20);
        Calendar calendarFecha = Calendar.getInstance();
        calendarFecha.set(2016, 10, 20);
        detalleMovimiento.setFecha(calendarFecha.getTime());
        detalleMovimiento.setFechaValor(calendarFechaValor.getTime());
        detalleMovimiento.setImporteMovimiento(new BigDecimal("500"));
        detalleMovimiento.setSaldoCuenta(new BigDecimal("50000"));
        detalleMovimiento.setDelDia(true);
        detalleMovimientoList.add(detalleMovimiento);

        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        cuenta.setCliente(cliente);
        cuenta.setAlias("Alias Cuenta");
        cuenta.setTipoCuenta("02");
        consultaUltimosMovimientosView.setNumero("033-587456");
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setImporteDesde(new BigDecimal("5"));
        consultaUltimosMovimientos.setImporteHasta(new BigDecimal("5"));
        consultaUltimosMovimientos.setPalabraClave("");
        consultaUltimosMovimientos.setOffset(0);
        consultaUltimosMovimientos.setCuenta(cuenta);
        Respuesta<ConsultaUltimosMovimientos> respuestaFiltro = new Respuesta<ConsultaUltimosMovimientos>();
        respuestaFiltro.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaFiltro.setRespuesta(consultaUltimosMovimientos);
        

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("phone");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        Mensaje mensaje = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosRespuesta = getDetalleUltimosMovimientosRespuesta();
        detalleUltimosMovimientosRespuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        when(ultimosMovimientosBO.validarFiltros(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class), Matchers.anyBoolean())).thenReturn(respuestaFiltro);
        when(ultimosMovimientosBO.obtenerUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class))).thenReturn(detalleUltimosMovimientosRespuesta);
        when(sessionMovimientos.getFiltro()).thenReturn(null,consultaUltimosMovimientos, consultaUltimosMovimientos);
        Respuesta<MovimientoView> respuesta = movimientosManager.getMovimientos(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        MovimientoView movimientoView = respuesta.getRespuesta();
         assertEquals(movimientoView.getItemsMovimiento().get(0).getImporte(), "0,00");
         assertEquals(movimientoView.getItemsMovimiento().get(0).getNumeroSucursal(), "000");
         assertEquals(movimientoView.getItemsMovimiento().get(0).getDescripcionSucursal(), "Sucursal 0");
         assertEquals(movimientoView.getItemsMovimiento().get(0).getId(), "0");
         assertEquals(movimientoView.getItemsMovimiento().get(0).getIsDebito(), Boolean.FALSE);
         assertEquals(movimientoView.getItemsMovimiento().get(0).getDivisa(), "$");
         assertEquals(movimientoView.getItemsMovimiento().get(1).getNumeroSucursal(), "001");
         assertEquals(movimientoView.getItemsMovimiento().get(1).getDescripcionSucursal(), "Sucursal 1");
         assertEquals(movimientoView.getItemsMovimiento().get(1).getId(), "1");
    }

    /**
     * Gets the ultimos movimientos error test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException 
     */
    @Test
    public void getUltimosMovimientosErrorTest() throws ServiceException, BusinessException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        Cuenta cuenta = new Cuenta();
        Cliente cliente = new Cliente();
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();

        Calendar calendarFechaValor = Calendar.getInstance();
        calendarFechaValor.set(2016, 9, 20);
        Calendar calendarFecha = Calendar.getInstance();
        calendarFecha.set(2016, 10, 20);
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        cuenta.setCliente(cliente);
        cuenta.setAlias("Alias Cuenta");
        cuenta.setTipoCuenta("1");
        consultaUltimosMovimientosView.setNumero("033-587456");
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setImporteDesde(new BigDecimal("5"));
        consultaUltimosMovimientos.setImporteHasta(new BigDecimal("5"));
        consultaUltimosMovimientos.setPalabraClave("");
        consultaUltimosMovimientos.setCuenta(cuenta);
        Respuesta<ConsultaUltimosMovimientos> respuestaFiltro = new Respuesta<ConsultaUltimosMovimientos>();
        respuestaFiltro.setRespuesta(consultaUltimosMovimientos);
        respuestaFiltro.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        Mensaje mensaje = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosRespuesta = getDetalleUltimosMovimientosRespuesta();
        detalleUltimosMovimientosRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(ultimosMovimientosBO.obtenerUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class)))
                .thenReturn(detalleUltimosMovimientosRespuesta);
        when(ultimosMovimientosBO.validarFiltros(Matchers.any(ConsultaUltimosMovimientos.class), Matchers.any(ConsultaUltimosMovimientos.class), Matchers.anyBoolean())).thenReturn(respuestaFiltro);

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("phone");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);

        Respuesta<MovimientoView> respuesta = movimientosManager.getMovimientos(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertTrue(respuesta.isRespuestaVacia());
    }

    /**
     * Gets the movimientos pendientes test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosPendientesTest() throws ServiceException {
        Cliente cliente = getCliente();
        Cuenta cuenta = getCuenta(cliente);

        List<MovimientoDeCuenta> movimientosDeCuenta = new ArrayList<MovimientoDeCuenta>();
        movimientosDeCuenta.add(getMovimientoDeCuenta());
        MovimientosPendientesDeConfirmacion movimientosPendientesDeConfirmacion = new MovimientosPendientesDeConfirmacion();
        movimientosPendientesDeConfirmacion.setMovimientosPendientesDeConfirmacion(movimientosDeCuenta);

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        consultaUltimosMovimientosView.setNumero("033-587456");

        Respuesta<MovimientosPendientesDeConfirmacion> respuestaMovimientosPendientesDeConfirmacion = new Respuesta<MovimientosPendientesDeConfirmacion>();
        respuestaMovimientosPendientesDeConfirmacion.setRespuesta(movimientosPendientesDeConfirmacion);
        respuestaMovimientosPendientesDeConfirmacion.setEstadoRespuesta(EstadoRespuesta.OK);
        
        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setRangoFecha(RangoFechaEnum.DEFAULT);
        consultaUltimosMovimientos.setImporteDesde(new BigDecimal("5"));
        consultaUltimosMovimientos.setImporteHasta(new BigDecimal("5"));
        consultaUltimosMovimientos.setPalabraClave("");

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        when(obtenerMovimientosPendientes
                .obtenerMovimientosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaMovimientosPendientesDeConfirmacion);

        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = movimientosManager
                .getMovimientosPendientes(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        MovimientoDeCuentaView primerMovimiento = respuesta.getRespuesta().getMovimientosPendientesDeConfirmacion()
                .get(0);
        assertEquals("10/10/2016", primerMovimiento.getFecha());
        assertEquals("10:10", primerMovimiento.getHora());
        assertEquals("50.000,00", primerMovimiento.getImporteOperacion());
        assertEquals("22222222", primerMovimiento.getNumeroDeTicket());
        assertEquals(primerMovimiento.getDivisa(), DivisaEnum.PESO.getSimbolo());
        assertEquals("Deposito de efectivo por RIO Self", primerMovimiento.getDescripcionDeOperacion());

    }

    /**
     * Gets the movimientos pendientes error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosPendientesErrorTest() throws ServiceException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        Cliente cliente = getCliente();
        Cuenta cuenta = getCuenta(cliente);

        Respuesta<MovimientosPendientesDeConfirmacion> respuestaMovimientosPendientesDeConfirmacion = new Respuesta<MovimientosPendientesDeConfirmacion>();

        MovimientosPendientesDeConfirmacion movimientosPendientesDeConfirmacion = new MovimientosPendientesDeConfirmacion();
        MovimientoDeCuenta movimientoDeCuenta = new MovimientoDeCuenta();

        movimientoDeCuenta.setSucursalOrigen(getSucursal());
        movimientoDeCuenta.setFecha("10/10/2016");
        movimientoDeCuenta.setImporteOperacion(new BigDecimal("50000"));

        List<MovimientoDeCuenta> movimientosDeCuenta = new ArrayList<MovimientoDeCuenta>();
        movimientosDeCuenta.add(movimientoDeCuenta);

        movimientosPendientesDeConfirmacion.setMovimientosPendientesDeConfirmacion(movimientosDeCuenta);

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        getRespuestaMock(respuestaMensaje, EstadoRespuesta.ERROR, true, "Mensaje de error",
                TipoError.ERROR_MOVIMIENTOS_PENDIENTES);

        respuestaMovimientosPendientesDeConfirmacion.setRespuesta(movimientosPendientesDeConfirmacion);
        respuestaMovimientosPendientesDeConfirmacion.setEstadoRespuesta(EstadoRespuesta.ERROR);

        consultaUltimosMovimientosView.setNumero("033-587456");

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR-Mensaje de error");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        when(obtenerMovimientosPendientes
                .obtenerMovimientosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaMovimientosPendientesDeConfirmacion);

        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = movimientosManager
                .getMovimientosPendientes(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("ERROR-Mensaje de error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());

    }

    /**
     * Gets the movimientos pendientes exception test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
	@Test
    public void getMovimientosPendientesExceptionTest() throws ServiceException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();

        Cliente cliente = getCliente();

        Respuesta<MovimientosPendientesDeConfirmacion> respuestaMovimientosPendientesDeConfirmacion = new Respuesta<MovimientosPendientesDeConfirmacion>();
        MovimientosPendientesDeConfirmacion movimientosPendientesDeConfirmacion = new MovimientosPendientesDeConfirmacion();

        respuestaMovimientosPendientesDeConfirmacion.setRespuesta(movimientosPendientesDeConfirmacion);
        respuestaMovimientosPendientesDeConfirmacion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        consultaUltimosMovimientosView.setNumero("033-587456");

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenThrow(ServiceException.class);
        when(obtenerMovimientosPendientes
                .obtenerMovimientosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaMovimientosPendientesDeConfirmacion);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = movimientosManager
                .getMovimientosPendientes(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("Mensaje de error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        Gson gson = new Gson();
        System.out.println(gson.toJson(respuesta));
    }

    /**
     * Gets the movimientos valores pendientes test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosValoresPendientesTest() throws ServiceException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = getConsultaUltimosMovimientosView();

        Cliente cliente = getCliente();

        Cuenta cuenta = getCuenta(cliente);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaDebito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaDebito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesDebito = getDetalleMovimientoChequesYValores(
                true, true, "Prueba Debito1", "290");
        listaRespuestaDebito.add(detalleMovimientoChequesDebito);
        respuestaDebito.setRespuesta(listaRespuestaDebito);
        getRespuestaMock(respuestaDebito, EstadoRespuesta.OK, false, "", null);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaCredito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaCredito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesCredito = getDetalleMovimientoChequesYValores(
                true, false, "Prueba Credito1", "430");
        listaRespuestaCredito.add(detalleMovimientoChequesCredito);
        respuestaCredito.setRespuesta(listaRespuestaCredito);
        getRespuestaMock(respuestaCredito, EstadoRespuesta.OK, false, "", null);

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerDebitosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaDebito);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerCreditosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaCredito);

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("phone");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerDebitosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaDebito);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerCreditosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaCredito);

        Respuesta<MovimientoValoresView> respuesta = movimientosManager
                .getMovimientosValores(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

        assertEquals(1, respuesta.getRespuesta().getValoresCredito().size());
        assertEquals(1, respuesta.getRespuesta().getValoresDebito().size());

        assertEquals("Prueba Debito1", respuesta.getRespuesta().getValoresDebito().get(0).getDescripcion());
        assertEquals("Prueba Credito1", respuesta.getRespuesta().getValoresCredito().get(0).getDescripcion());

        assertEquals("290,00", respuesta.getRespuesta().getValoresDebito().get(0).getImporte());
        assertEquals("430,00", respuesta.getRespuesta().getValoresCredito().get(0).getImporte());
        // Gson gson = new Gson();
        // System.out.println(gson.toJson(respuesta));
    }

    
    @Mock
    private MovimientosValoresPendientes movimientosValoresPendientes;
    

    
    /**
     * Gets the movimientos valores pendientes warning test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosValoresPendientesWarningTest() throws ServiceException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = getConsultaUltimosMovimientosView();

        Cliente cliente = getCliente();

        Cuenta cuenta = getCuenta(cliente);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaDebito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaDebito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesDebito = getDetalleMovimientoChequesYValores(
                true, true, "Prueba Debito1", "290");
        listaRespuestaDebito.add(detalleMovimientoChequesDebito);
        respuestaDebito.setRespuesta(listaRespuestaDebito);
        getRespuestaMock(respuestaDebito, EstadoRespuesta.ERROR, true, "falla en debitos", null);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaCredito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaCredito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesCredito = getDetalleMovimientoChequesYValores(
                true, false, "Prueba Credito1", "430");
        listaRespuestaCredito.add(detalleMovimientoChequesCredito);
        respuestaCredito.setRespuesta(listaRespuestaCredito);
        getRespuestaMock(respuestaCredito, EstadoRespuesta.OK, false, "", null);

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerDebitosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaDebito);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerCreditosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaCredito);

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("phone");
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR-falla en debitos");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<MovimientoValoresView> respuesta = movimientosManager
                .getMovimientosValores(consultaUltimosMovimientosView);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals("ERROR-falla en debitos", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        // Gson gson = new Gson();
        // System.out.println(gson.toJson(respuesta));

    }

    /**
     * Gets the movimientos valores pendientes error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosValoresPendientesErrorTest() throws ServiceException {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = getConsultaUltimosMovimientosView();

        Cliente cliente = getCliente();

        Cuenta cuenta = getCuenta(cliente);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaDebito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaDebito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesDebito = getDetalleMovimientoChequesYValores(
                true, true, "Prueba Debito1", "290");
        listaRespuestaDebito.add(detalleMovimientoChequesDebito);
        respuestaDebito.setRespuesta(listaRespuestaDebito);
        getRespuestaMock(respuestaDebito, EstadoRespuesta.ERROR, true, "falla en debitos", null);
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR-falla en creditos");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaCredito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        Respuesta<List<DetalleMovimientoChequesYValoresEntity>> respuestaCredito = new Respuesta<List<DetalleMovimientoChequesYValoresEntity>>();

        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesCredito = getDetalleMovimientoChequesYValores(
                true, false, "Prueba Credito1", "430");
        listaRespuestaCredito.add(detalleMovimientoChequesCredito);
        respuestaCredito.setRespuesta(listaRespuestaCredito);
        getRespuestaMock(respuestaCredito, EstadoRespuesta.ERROR, false, "falla en creditos", null);

        when(sessionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(cuenta);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerDebitosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaDebito);
        when(chequesYValoresPendientesDeConfirmacionBO
                .obtenerCreditosPendientesDeConfirmacionPorCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaCredito);
        
        Respuesta<MovimientoValoresView> respuesta = movimientosManager
                .getMovimientosValores(consultaUltimosMovimientosView);

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("ERROR-falla en creditos", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Gets the detalle movimientos test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getDetalleMovimientosTest() throws ServiceException {

        Respuesta<DetalleUltimosMovimientos> respuestaDetalleUltimosMovimientos = new Respuesta<DetalleUltimosMovimientos>();

        DetalleUltimosMovimientos um = getDetalleUltimosMovimientos();

        respuestaDetalleUltimosMovimientos.setRespuesta(um);

        getRespuestaMock(respuestaDetalleUltimosMovimientos, EstadoRespuesta.OK, false, "", null);

        ConsultaUltimosMovimientos cm = new ConsultaUltimosMovimientos();
        cm.setCuenta(getCuenta(getCliente()));
        cm.setOffset(0);
        when(sessionMovimientos.getFiltro()).thenReturn(cm);
        EstadoDetalleMovimientoView view = new EstadoDetalleMovimientoView();
        view.setIsRechazado(true);
        view.setIsDelDia(true);
        Respuesta<DetalleMovimientosView> respuesta = movimientosManager.getDetalleMovimiento(view);

        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("033-058745/6", respuesta.getRespuesta().getNumeroCuenta());
        assertEquals("Alias Cuenta", respuesta.getRespuesta().getAliasCuenta());

    }

    /**
     * Gets the movimientos valores pendientes detalle test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosValoresPendientesDetalleTest() throws ServiceException {

        String cuenta = "033-587456";
        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = movimientosManager
                .getMovimientosValoresPendientesDetalle(cuenta, getCliente());
        assertNotNull(respuesta);
    }

    /**
     * Gets the movimientos pendientes detalle test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getMovimientosPendientesDetalleTest() throws ServiceException {

        String cuenta = "033-587456";

        when(cuentaBO.getCuentaById(Matchers.any(IdentificacionCuenta.class), Matchers.any(Cliente.class)))
                .thenReturn(getCuenta(getCliente()));
        Respuesta<MovimientosPendientesDeConfirmacionView> respuesta = movimientosManager
                .getMovimientosPendientesDetalle(cuenta, getCliente());

        assertNotNull(respuesta);
    }

    /**
     * Gets the movimientos pendientes reporte test.
     *
     */
    @Test
    public void getMovimientosPendientesReporteTest() {

        String cuenta = "033-587456";
        String tipoCuenta = "Cuenta Unica";

        List<MovimientoDeCuenta> movimientosDeCuenta = new ArrayList<MovimientoDeCuenta>();

        movimientosDeCuenta.add(getMovimientoDeCuenta());

        when(sessionMovimientosPendientesDeConfirmacion.getCuenta()).thenReturn(cuenta);
        when(sessionMovimientosPendientesDeConfirmacion.getCuenta()).thenReturn(cuenta);
        when(sessionMovimientosPendientesDeConfirmacion.getTipoCuenta()).thenReturn(tipoCuenta);
        when(sessionMovimientosPendientesDeConfirmacion.getMovimientoDeCuenta()).thenReturn(movimientosDeCuenta);
        when(sessionCliente.getCliente()).thenReturn(getCliente());

        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        Reporte reporte = getReporteMock(TipoArchivoEnum.EXCEL);
        respuestaReporte.setRespuesta(reporte);
        getRespuestaMock(respuestaReporte, EstadoRespuesta.OK, false, "xls", null);
        when(chequesYValoresPendientesDeConfirmacionBO.obtenerReporte(Matchers.any(MovimientoDeCuenta.class),
                Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(respuestaReporte);
        
        Response respuesta = movimientosManager.getMovimientosPendientesReporte(cuenta);

        assertNotNull(respuesta);

    }

    /**
     * Exportar movimientos test.
     */
    @Test
    public void exportarMovimientosTest() {
        RegistroSesion rg = new RegistroSesion();
        rg.setDispositivo("");

        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(getCuenta(getCliente()));

        Reporte reporte = getReporteMock(TipoArchivoEnum.EXCEL);
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        respuestaReporte.setRespuesta(reporte);
        getRespuestaMock(respuestaReporte, EstadoRespuesta.OK, false, "xls", null);

        List<MovimientoDeCuenta> movimientosDeCuenta = new ArrayList<MovimientoDeCuenta>();
        movimientosDeCuenta.add(getMovimientoDeCuenta());

        when(sesionParametros.getRegistroSession()).thenReturn(rg);
        when(sessionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
        when(ultimosMovimientosBO.exportarUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class),
                Matchers.anyString())).thenReturn(respuestaReporte);
        when(sessionMovimientosPendientesDeConfirmacion.getMovimientoDeCuenta()).thenReturn(movimientosDeCuenta);

        Response respuesta = movimientosManager.exportarMovimientos();

        assertNotNull(respuesta);
    }

    /**
     * Exportar movimientos error test.
     */
    @SuppressWarnings("unchecked")
	@Test
    public void exportarMovimientosErrorTest() {
        RegistroSesion rg = new RegistroSesion();
        rg.setDispositivo("");

        ConsultaUltimosMovimientos consultaUltimosMovimientos = new ConsultaUltimosMovimientos();
        consultaUltimosMovimientos.setCuenta(getCuenta(getCliente()));

        Reporte reporte = new Reporte();
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        respuestaReporte.setRespuesta(reporte);
        getRespuestaMock(respuestaReporte, EstadoRespuesta.ERROR, false, "xls", null);

        List<MovimientoDeCuenta> movimientosDeCuenta = new ArrayList<MovimientoDeCuenta>();
        movimientosDeCuenta.add(getMovimientoDeCuenta());

        when(sesionParametros.getRegistroSession()).thenReturn(rg);
        when(sessionMovimientos.getFiltro()).thenReturn(consultaUltimosMovimientos);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(ultimosMovimientosBO.exportarUltimosMovimientos(Matchers.any(ConsultaUltimosMovimientos.class),
                Matchers.anyString())).thenReturn(respuestaReporte);
        when(sessionMovimientosPendientesDeConfirmacion.getMovimientoDeCuenta()).thenReturn(movimientosDeCuenta);

        Response respuesta = movimientosManager.exportarMovimientos();
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, ((Respuesta<ReporteView>)respuesta.getEntity()).getEstadoRespuesta());
    }

    /**
     * Gets the movimientos valores pendientes reporte test.
     *
     */
    @Test
    public void getMovimientosValoresPendientesReporteTest() {

        String cuenta = "033-587456";

        MovimientosValoresPendientes movimientosValoresPendientes = new MovimientosValoresPendientes();

        movimientosValoresPendientes.setCuenta(cuenta);
        movimientosValoresPendientes.setTipoCuenta(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcion());
        List<DetalleMovimientoChequesYValoresEntity> listaRespuestaDebito = new ArrayList<DetalleMovimientoChequesYValoresEntity>();
        DetalleMovimientoChequesYValoresEntity detalleMovimientoChequesDebito = getDetalleMovimientoChequesYValores(
                true, true, "Prueba Debito1", "290");
        listaRespuestaDebito.add(detalleMovimientoChequesDebito);
        movimientosValoresPendientes.setValoresCredito(listaRespuestaDebito);

        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();

        Reporte reporte = getReporteMock(TipoArchivoEnum.EXCEL);

        respuestaReporte.setRespuesta(reporte);

        getRespuestaMock(respuestaReporte, EstadoRespuesta.OK, false, "pdf", null);

        when(chequesYValoresPendientesDeConfirmacionBO.obtenerReporte(
                Matchers.any(MovimientosValoresPendientes.class), Matchers.anyString(), Matchers.any(Cliente.class)))
                        .thenReturn(respuestaReporte);

        when(sessionMovimientosValores.getMovimientosValoresPendientes()).thenReturn(movimientosValoresPendientes);

        Response respuesta = movimientosManager.getMovimientosValoresPendientesReporte(cuenta);

        assertNotNull(respuesta);
    }

    /**
     * Gets the reporte mock.
     *
     * @return the reporte mock
     */
    private Reporte getReporteMock(TipoArchivoEnum tipoArchivo) {
        Reporte reporte = new Reporte();
        reporte.setBytes(new byte[0]);
        reporte.setNombre("Reporte");
        reporte.setTipoArchivo(tipoArchivo);
        return reporte;
    }

    /**
     * Gets the detalle ultimos movimientos.
     *
     * @return the detalle ultimos movimientos
     */
    private DetalleUltimosMovimientos getDetalleUltimosMovimientos() {
        DetalleUltimosMovimientos detalleUltimosMovimientos = new DetalleUltimosMovimientos();
        List<DetalleMovimientoEntity> detalleMovimientoList = new ArrayList<DetalleMovimientoEntity>();
        detalleMovimientoList
                .add(getDetalleMovimento("033-587456", true, true, new Date(), "Descripcion ", new BigDecimal(1000)));
        for (int i = 1; i < 5; i++) {

            detalleMovimientoList.add(getDetalleMovimento("033-587456", i < 2, i < 4, new Date(), "Descripcion " + i,
                    new BigDecimal(1000 + i)));

        }
        detalleUltimosMovimientos.setDetalleMovimiento(detalleMovimientoList);
        return detalleUltimosMovimientos;
    }

    /**
     * Gets the detalle movimento.
     *
     * @param id
     *            the id
     * @param delDia
     *            the del dia
     * @param isCheque
     *            the is cheque
     * @param fecha
     *            the fecha
     * @param descripcion
     *            the descripcion
     * @param importe
     *            the importe
     * @return the detalle movimento
     */
    private DetalleMovimientoEntity getDetalleMovimento(String id, boolean delDia, boolean isCheque, Date fecha,
            String descripcion, BigDecimal importe) {
        DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
        detalleMovimiento.setId(id);
        detalleMovimiento.setDelDia(delDia);
        detalleMovimiento.setCheque(isCheque);
        detalleMovimiento.setFecha(fecha);
        detalleMovimiento.setDescripcion(descripcion);
        detalleMovimiento.setImporteMovimiento(importe);
        return detalleMovimiento;
    }

    /**
     * Gets the detalle movimiento cheques Y valores.
     *
     * @param isCheque
     *            the is cheque
     * @param isDebito
     *            the is debito
     * @param descripcion
     *            the descripcion
     * @param importeMovimiento
     *            the importe movimiento
     * @return DetalleMovimientoChequesYValores Mock
     */
    private DetalleMovimientoChequesYValoresEntity getDetalleMovimientoChequesYValores(boolean isCheque,
            boolean isDebito, String descripcion, String importeMovimiento) {
        DetalleMovimientoChequesYValoresEntity detalleMovimientoCheques = new DetalleMovimientoChequesYValoresEntity();
        detalleMovimientoCheques.setCheque(isCheque);
        detalleMovimientoCheques.setDebito(isDebito);
        detalleMovimientoCheques.setDescripcionAdicional("ChequeTest");
        detalleMovimientoCheques.setDescripcion(descripcion);
        detalleMovimientoCheques.setImporteMovimiento(new BigDecimal(importeMovimiento));
        detalleMovimientoCheques.setDescripcionSucursal("Desc Generic Sucur");
        detalleMovimientoCheques.setFecha(new Date());
        return detalleMovimientoCheques;
    }

    /**
     * Gets the consulta ultimos movimientos view.
     *
     * @return the consulta ultimos movimientos view
     */
    private ConsultaUltimosMovimientosView getConsultaUltimosMovimientosView() {

        ConsultaUltimosMovimientosView consultaUltimosMovimientosView = new ConsultaUltimosMovimientosView();
        consultaUltimosMovimientosView.setNumero("033-587456");
        consultaUltimosMovimientosView.setFechaDesde("10/10/2016");
        consultaUltimosMovimientosView.setFechaHasta("14/12/2016");
        consultaUltimosMovimientosView.setImporteDesde("100");
        consultaUltimosMovimientosView.setImporteHasta("555");
        consultaUltimosMovimientosView.setRango(RangoFechaEnum.SIETE_DIAS);
//        consultaUltimosMovimientosView.setOffset(1);
        consultaUltimosMovimientosView.setMoneda(DivisaEnum.DOLAR);
        consultaUltimosMovimientosView.setPalabraClave("Inf");
        return consultaUltimosMovimientosView;
    }

    /**
     * Gets the movimiento de cuenta.
     *
     * @return the movimiento de cuenta
     */
    private MovimientoDeCuenta getMovimientoDeCuenta() {
        MovimientoDeCuenta movimientoDeCuenta = new MovimientoDeCuenta();
        movimientoDeCuenta.setImporteOperacion(new BigDecimal("400"));
        movimientoDeCuenta.setDivisa(DivisaEnum.fromSimboloString("$"));
        movimientoDeCuenta.setHora("10:10:10");
        movimientoDeCuenta.setNumeroDeCuenta("033-587456");
        movimientoDeCuenta.setNumeroDeTicket("22222222");
        movimientoDeCuenta.setFecha("10/10/2016");
        movimientoDeCuenta.setTipoDeOperacion(TipoOperacionMovimimiento.DEPOSITO_DE_EFECTIVO);
        movimientoDeCuenta.setSucursalOrigen(getSucursal());
        movimientoDeCuenta.setFecha("10/10/2016");
        movimientoDeCuenta.setImporteOperacion(new BigDecimal("50000"));
        movimientoDeCuenta.setOrigenTransaccion("RIO");
        return movimientoDeCuenta;
    }

    /**
     * Gets the sucursal.
     *
     * @return the sucursal
     */
    private Sucursal getSucursal() {
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal("033");
        sucursal.setDescripcion("Sucursal");
        return sucursal;
    }

    /**
     * UTILES TEST *.
     *
     * @param <E>
     *            the element type
     * @param respuesta
     *            the respuesta
     * @param estado
     *            the estado
     * @param isVacia
     *            the is vacia
     * @param mensajeEsperado
     *            the mensaje esperado
     * @param tipoError
     *            the tipo error
     */
    /**
     * Respuesta Generica
     * 
     * @param respuesta
     * @param estado
     * @param isVacia
     * @param mensajeEsperado
     */
    public <E> void getRespuestaMock(Respuesta<E> respuesta, EstadoRespuesta estado, boolean isVacia,
            String mensajeEsperado, TipoError tipoError) {
        respuesta.setEstadoRespuesta(estado);
        respuesta.setRespuestaVacia(isVacia);
        if (EstadoRespuesta.ERROR.equals(estado) || EstadoRespuesta.WARNING.equals(estado)) {
            Respuesta<Mensaje> resMsje = new Respuesta<Mensaje>();
            Mensaje msje = new Mensaje();
            msje.setMensaje(estado.toString() + "-" + mensajeEsperado);
            resMsje.setRespuesta(msje);
            when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msje);
        }

    }

    /**
     * Gets the cuenta.
     *
     * @param cliente
     *            the cliente
     * @return Cuenta Mock
     */
    private Cuenta getCuenta(Cliente cliente) {

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta.setCliente(cliente);
        cuenta.setAlias("Alias Cuenta");
        cuenta.setTipoCuenta("2");
        cuenta.setEstadoTarjetaCredito("S");
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("587456");
        return cuenta;
    }

    /**
     * Gets the cliente.
     *
     * @return Cliente Mock
     */
    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        return cliente;
    }

}
