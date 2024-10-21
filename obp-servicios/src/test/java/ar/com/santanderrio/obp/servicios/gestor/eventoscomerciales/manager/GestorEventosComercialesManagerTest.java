package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
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
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.OfertasComercialesEntityMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChanceDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.FinalizarPromesaEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.impl.GestorEventosComercialesManagerImpl;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.GestionEventoComercialOutEntityMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.OfertaComercialDTOMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceHistorialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoInView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.FinalizarPromesaPagoOutView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.NotificacionesComercialesView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertasComercialesView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosDeDomicilioDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;

/**
 * The Class GestorEventosComercialesManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class GestorEventosComercialesManagerTest {

    /** The gestor eventos comerciales manager. */
    @InjectMocks
    private GestorEventosComercialesManagerImpl gestorEventosComercialesManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The gestor evento comercial BO. */
    @Mock
    private GestorEventoComercialBO gestorEventoComercialBO;

    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
	@Mock

	private TokenManager seguroManager;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	
    /** The contador intentos. */
    @Mock
    private ContadorIntentos contadorIntentos;
    
    @Mock
	private SesionParametros sessionParametros;


    /** The cliente. */
    private Cliente cliente;

    /**
     * Inits the mocks.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(gestorEventosComercialesManager, "carruselImagenDefault", "", true);
        FieldUtils.writeDeclaredField(gestorEventosComercialesManager, "carruselCallToActionDefault", "", true);
        FieldUtils.writeDeclaredField(gestorEventosComercialesManager, "carruselAutodesplazamiento", "5", true);
        cliente = ClienteMock.infoCliente();
    }

    /**
     * Obtener cantidad notificaciones sin permiso test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCantidadNotificacionesSinPermisoTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1608",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Para revisar tus notificaciones, por favor cerrá tu sesión y volvé a intentar en unos minutos.</p>");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // Then
        Respuesta<NotificacionesComercialesView> cantidadNotif = gestorEventosComercialesManager
                .obtenerCantidadNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(cantidadNotif);
        Assert.assertEquals(EstadoRespuesta.ERROR, cantidadNotif.getEstadoRespuesta());
    }

    /**
     * Obtener cantidad notificaciones sin notificaciones test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCantidadNotificacionesSinNotificacionesTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        GestionEventoComercialOutEntity notificaciones = new GestionEventoComercialOutEntity();
        notificaciones.setNup("00276937");
        notificaciones.setCantNotifSinLeer("0");
        notificaciones.setTotalNotif("0");
        notificaciones.setCodigoError("ERROR000");
        notificaciones.setDescripcionError("");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1609",
                "En esta sección vas a poder consultar los avisos sobre tus operaciones.");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerCantidadNotificaciones(Matchers.any(Cliente.class)))
                .thenReturn(notificaciones);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<NotificacionesComercialesView> cantidadNotif = gestorEventosComercialesManager
                .obtenerCantidadNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(cantidadNotif);
        Assert.assertEquals(EstadoRespuesta.WARNING, cantidadNotif.getEstadoRespuesta());
    }

    /**
     * Obtener cantidad notificaciones business exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCantidadNotificacionesBusinessExceptionTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1608",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Para revisar tus notificaciones, por favor cerrá tu sesión y volvé a intentar en unos minutos.</p>");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerCantidadNotificaciones(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error inesperado!"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<NotificacionesComercialesView> cantidadNotif = gestorEventosComercialesManager
                .obtenerCantidadNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(cantidadNotif);
        Assert.assertEquals(EstadoRespuesta.ERROR, cantidadNotif.getEstadoRespuesta());
    }

    /**
     * Obtener notificaciones OK test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerNotificacionesOKTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        GestionEventoComercialOutEntity notificaciones = new GestionEventoComercialOutEntity();
        notificaciones.setNup("00276937");
        notificaciones.setCantNotifSinLeer("1");
        notificaciones.setTotalNotif("6");
        notificaciones.setCodigoError("ERROR000");
        notificaciones.setDescripcionError("");
        List<NotificacionOutEntity> notificacionesList = new ArrayList<NotificacionOutEntity>();
        NotificacionOutEntity notif1 = new NotificacionOutEntity();
        notif1.setLink("gotoPagoTarjetaCredito()");
        notif1.setGotoLink(new GotoLink(notif1.getLink()));
        notif1.setFechaAlta("2017-09-22");
        notificacionesList.add(notif1);
        notificaciones.setNotificaciones(notificacionesList);
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerCantidadNotificaciones(Matchers.any(Cliente.class)))
                .thenReturn(notificaciones);

        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager
                .obtenerCantidadNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.OK, notif.getEstadoRespuesta());
    }

    /**
     * Obtener notificaciones sin permiso test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerNotificacionesSinPermisoTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1608",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Para revisar tus notificaciones, por favor cerrá tu sesión y volvé a intentar en unos minutos.</p>");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager.obtenerNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.ERROR, notif.getEstadoRespuesta());
    }

    /**
     * Obtener notificaciones sin notificaciones test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerNotificacionesSinNotificacionesTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        GestionEventoComercialOutEntity notificaciones = new GestionEventoComercialOutEntity();
        notificaciones.setNup("00276937");
        notificaciones.setCantNotifSinLeer("0");
        notificaciones.setTotalNotif("0");
        notificaciones.setCodigoError("ERROR000");
        notificaciones.setDescripcionError("");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1609",
                "En esta sección vas a poder consultar los avisos sobre tus operaciones.");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerNotificaciones(Matchers.any(Cliente.class)))
                .thenReturn(notificaciones);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager.obtenerNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.WARNING, notif.getEstadoRespuesta());
    }

    /**
     * Obtener notificaciones business exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerNotificacionesBusinessExceptionTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1608",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Para revisar tus notificaciones, por favor cerrá tu sesión y volvé a intentar en unos minutos.</p>");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerNotificaciones(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error inesperado!"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager.obtenerNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.ERROR, notif.getEstadoRespuesta());
    }

    @Test
    public void obtenerNotificacionesWebContentMasGotoLinkTest() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        GestionEventoComercialOutEntity notificaciones = GestionEventoComercialOutEntityMock
                .completarInfoGotoLandingMasGotoLinkNotificaciones();
        Cliente cliente = ClienteMock.infoCliente();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1702",
                "<p><h1>Estás saliendo del Online Banking.</h1></p><p> La información contenida en dicho sitio es de exclusiva responsabilidad de su autor</p>");

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(gestorEventoComercialBO.obtenerNotificaciones(Matchers.any(Cliente.class)))
                .thenReturn(notificaciones);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager.obtenerNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.OK, notif.getEstadoRespuesta());
    }

    /**
     * Obtener carrusel OK.
     */
    @Test
    public void obtenerCarruselOK() {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);

        // When
        Mockito.when(sesionParametros.getOfertasComerciales())
                .thenReturn(OfertasComercialesEntityMock.eventosComercialesDTO());
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
        Assert.assertEquals("Seguro de accidentes personales",
                carruselView.getRespuesta().getOfertas().get(0).getTitulo());

    }

    /**
     * Obtener carrusel OK con web content.
     */
    @Test
    public void obtenerCarruselOKConWebContentTest() {
        // Given
        EventosComercialesDTO ofertas = OfertasComercialesEntityMock.eventosComercialesDTO();
        ofertas.getOfertas().get(0).getGotoLink().setLink("gotoLanding()");
        ofertas.getOfertas().get(0).getGotoLink().setParametros(";;gotoInicioMiPerfil()");
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);


        // When
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertas);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);


        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
        Assert.assertEquals("Seguro de accidentes personales",
                carruselView.getRespuesta().getOfertas().get(0).getTitulo());

    }

    /**
     * Obtener carrusel OK con web content y goto link.
     */
    @Test
    public void obtenerCarruselOKConWebContentYGotoLinkTest() {
        // Given
        EventosComercialesDTO ofertas = OfertasComercialesEntityMock.eventosComercialesDTO();
        ofertas.getOfertas().get(0).getGotoLink().setLink("gotoLanding()");
        ofertas.getOfertas().get(0).getGotoLink().setParametros(";;gotoLink(www.santanderrio.com.ar;E)");
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);


        // When
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertas);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);


        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
        Assert.assertEquals("Seguro de accidentes personales",
                carruselView.getRespuesta().getOfertas().get(0).getTitulo());

    }

    @Test
    public void obtenerCarruselOKConGotoLinkTest() {
        // Given
        EventosComercialesDTO ofertas = OfertasComercialesEntityMock.eventosComercialesDTO();
        ofertas.getOfertas().get(0).getGotoLink().setLink("gotoLink()");
        ofertas.getOfertas().get(0).getGotoLink().setParametros("www.santanderrio.com.ar;E");
        ofertas.getOfertas().get(0).setTitulo(null);
        ofertas.getOfertas().get(0).setDescripcion(null);
        
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);


        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);

        // When
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertas);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);


        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
    }

    /**
     * Obtener carrusel OK sin goto link test.
     */
    @Test
    public void obtenerCarruselOKSinGotoLinkTest() {
        // Given
        EventosComercialesDTO ofertas = OfertasComercialesEntityMock.eventosComercialesDTO();
        ofertas.getOfertas().get(0).getGotoLink().setLink(null);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);


        // When
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertas);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);


        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
    }

    /**
     * Obtener carrusel default.
     */
    @Test
    public void obtenerCarruselDefault() {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        
        Cliente cliente = new Cliente();
        cliente.setSinProductos(false);
        // When
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo("1611"))
                .thenReturn(MensajeMock.completarInfoMensaje("1611", "Titulo"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo("1634"))
                .thenReturn(MensajeMock.completarInfoMensaje("1634", ""));
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();

        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.OK, carruselView.getEstadoRespuesta());
        Assert.assertEquals("Titulo", carruselView.getRespuesta().getOfertas().get(0).getTitulo());

    }

    /**
     * Obtener carrusel sin permisos.
     */
    @Test
    public void obtenerCarruselSinPermisos() {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        // Then
        Respuesta<OfertasComercialesView> carruselView = gestorEventosComercialesManager.obtenerCarrusel();
        // Expected
        Assert.assertNotNull(carruselView);
        Assert.assertEquals(EstadoRespuesta.ERROR, carruselView.getEstadoRespuesta());

    }

    /**
     * Informar interes en notificacion OK.
     */
    @Test
    public void informarInteresEnNotificacionOK() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorEventoComercialBO.informarInteresEnNotificacion(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(Boolean.TRUE);

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarInteresEnNotificacion("123454455");

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Informar interes en notificacion error.
     */
    @Test
    public void informarInteresEnNotificacionError() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(gestorEventoComercialBO.informarInteresEnNotificacion(Matchers.any(Cliente.class),
                Matchers.anyString())).thenReturn(Boolean.FALSE);

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarInteresEnNotificacion("123454455");

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Actualizar notificaciones OK test.
     */
    @Test
    public void actualizarNotificacionesOKTest() {
        // Given
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(gestorEventoComercialBO.actualizarNotificaciones(Matchers.any(Cliente.class))).thenReturn(true);

        // Then
        Respuesta<Boolean> respuesta = gestorEventosComercialesManager.actualizarNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Actualizar notificaciones error test.
     */
    @Test
    public void actualizarNotificacionesErrorTest() {
        // Given
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1608",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Para revisar tus notificaciones, por favor cerrá tu sesión y volvé a intentar en unos minutos.</p>");
        Cliente cliente = ClienteMock.infoCliente();

        // When
        Mockito.when(gestorEventoComercialBO.actualizarNotificaciones(Matchers.any(Cliente.class))).thenReturn(false);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<Boolean> respuesta = gestorEventosComercialesManager.actualizarNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Informar interes oferta OK.
     */
    @Test
    public void informarFeedbackOfertaOK() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getOfertasComerciales())
                .thenReturn(OfertasComercialesEntityMock.eventosComercialesDTO());
        Mockito.when(gestorEventoComercialBO.informarFeedbackOferta(Mockito.any(Cliente.class), Mockito.anyString(),
                Mockito.any(OfertaComercialDTO.class),Mockito.anyString())).thenReturn(true);

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarFeedbackOferta(new OfertaComercialView(), "CLICKED");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Informar interes oferta error.
     */
    @Test
    public void informarFeedbackOfertaError() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getOfertasComerciales())
                .thenReturn(OfertasComercialesEntityMock.eventosComercialesDTO());
        Mockito.when(gestorEventoComercialBO.informarFeedbackOferta(Mockito.any(Cliente.class), Mockito.anyString(),
                Mockito.any(OfertaComercialDTO.class), Mockito.anyString())).thenReturn(false);

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarFeedbackOferta(new OfertaComercialView(), "CLICKED");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Informar interes oferta generico sin ofertas.
     */
    @Test
    public void informarFeedbackOfertaGenericoSinOfertas() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarFeedbackOferta(new OfertaComercialView(), "CLICKED");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Informar interes oferta error previo.
     */
    @Test
    public void informarFeedbackOfertaErrorPrevio() {
        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(null);

        // Then
        Respuesta<Void> respuesta = gestorEventosComercialesManager.informarFeedbackOferta(new OfertaComercialView(), "CLICKED");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener ofertas por seccion OK.
     */
    @Test
    public void obtenerOfertasPorSeccionOK() {
        // Given

        // When
        Mockito.when(sesionParametros.getOfertasComerciales())
                .thenReturn(OfertasComercialesEntityMock.eventosComercialesDTO());

        // Then
        Respuesta<OfertasComercialesView> ofertasView = gestorEventosComercialesManager.obtenerOfertasPorSeccion();

        // Expected
        Assert.assertNotNull(ofertasView);
        Assert.assertEquals(EstadoRespuesta.OK, ofertasView.getEstadoRespuesta());
        Assert.assertEquals("CUENTAS", ofertasView.getRespuesta().getOfertas().get(0).getSeccion());

    }
    
    /**
     * Informar gestion AC: OK.
     */
    @Test
    public void informarGestionACTestOK() {
    	
    	//DomicilioView completar con los datos necesarios
    	DomicilioView domi  = new DomicilioView();
    	domi.setEsSucursal(Boolean.TRUE);
    	domi.setTipoDomicilio("No Sucursal");
    	domi.setCalle("Los Jazmines");
    	domi.setPuerta("275");
    	domi.setPiso("4");
    	domi.setDepartamento("A");
    	domi.setLocalidad("Cuidad Evita");
    	domi.setCodigoPostal("1778");
    	domi.setProvincia("Buenos Aires");
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setJerarquiaCuenta("P");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuenta.setJerarquiaCuenta("p");
    	cuenta.setTipoCuenta("33");
    	cuentas.add(cuenta);
    	cliente.setCuentas(cuentas);
    	cliente.setNup("00276937");
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	ContadorIntentos contador = new ContadorIntentos(2);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getContadorReemplazoTarjetas()).thenReturn(contador);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
    	Mockito.when(gestorEventoComercialBO.informarGestionAC(Matchers.any(Cliente.class)
    			, Matchers.any(DatosDeDomicilioDTO.class))).thenReturn(Boolean.TRUE);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
    	
    	Respuesta<Void> respuesta = gestorEventosComercialesManager.informarGestionAC(domi);

    	Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	
    }
    
    /**
     * Informar gestion AC: ERROR supero el limite de reintendo.
     */
    @Test
    public void informarGestionACTestERROR() {
    	
    	//DomicilioView completar con los datos necesarios
    	DomicilioView domi  = new DomicilioView();
    	domi.setEsSucursal(Boolean.TRUE);
    	domi.setTipoDomicilio("No Sucursal");
    	domi.setCalle("Los Jazmines");
    	domi.setPuerta("275");
    	domi.setPiso("4");
    	domi.setDepartamento("A");
    	domi.setLocalidad("Cuidad Evita");
    	domi.setCodigoPostal("1778");
    	domi.setProvincia("Buenos Aires");
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = new Cuenta();
    	cuenta.setJerarquiaCuenta("P");
    	cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuenta.setTipoCuenta("77");
    	cuenta.setJerarquiaCuenta("p");
    	cuentas.add(cuenta);
    	cliente.setCuentas(cuentas);
    	cliente.setNup("00276937");
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	ContadorIntentos contador = new ContadorIntentos(1);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getContadorReemplazoTarjetas()).thenReturn(contador);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
    	Mockito.when(gestorEventoComercialBO.informarGestionAC(Matchers.any(Cliente.class)
    			, Matchers.any(DatosDeDomicilioDTO.class))).thenReturn(Boolean.TRUE);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
    	
    	//tiene 1 reintendo. tengo que llamar al metodo dos veces para que me de Error de reintentos
    	Respuesta<Void> respuesta = gestorEventosComercialesManager.informarGestionAC(domi);
    	respuesta = gestorEventosComercialesManager.informarGestionAC(domi);

    	Assert.assertEquals(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion(), 
    			respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }
    
    /**
    * obtener Premiaciones: OK: 
    */
    @Test
    public void obtenerPremiacionesTestOk() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos respuesta ChancesDTO
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	//Datos ChancesDTO
    	ChancesDTO chancesDTO = new ChancesDTO();
    	chancesDTO.setTotal("80");
    	chancesDTO.setHeader(new ChanceDTO(ChanceDTO.ACCION,"Chances Junio","Chances Julio"));
    	List<ChanceDTO> listChanceDTO = new ArrayList<ChanceDTO>();
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COBRO_POR_SUELDO,"10","20"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_DEBITO,"20","40"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_VISA,"20","0"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_AMERICAN_EXPRESS,"0","20"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.TOTAL,"50","80"));
    	chancesDTO.setListaChances(listChanceDTO);
    	respuestaDTO.setRespuesta(chancesDTO);
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	//Datos HistorialView
    	ChanceHistorialView historialView  = new ChanceHistorialView();
    	historialView.setAnioMax(2019);
    	historialView.setAnioMin(2019);
    	historialView.setMesMax(07);
    	historialView.setMesMin(06);
    	//Datos respuesta ChacnesHistorialView
    	Respuesta<ChanceHistorialView> respuestaHistorialView = new Respuesta<ChanceHistorialView>();
    	respuestaHistorialView.setRespuesta(historialView);
    	respuestaHistorialView.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(gestorEventoComercialBO.obtenerPremiaciones(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	    	.thenReturn(respuestaDTO);
    	Mockito.when(gestorEventoComercialBO.obtenerCotasMaximaYMinina(Matchers.any(String.class), Matchers.any(Cliente.class)))
    	.thenReturn(respuestaHistorialView);
    	
    	String fechaCorte = "20190707";
    	Respuesta<ChanceView> respuestaView = gestorEventosComercialesManager.obtenerPremiaciones(fechaCorte);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
    }
    
    
    /**
     * obtener Premiaciones: WARNING: el servicio nos responde con un lista de chances vacia.
     */
    @Test
	public void obtenerPremiacionesTestWarning() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos respuesta ChancesDTO    	
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	//Datos respuesta ChacnesHistorialView
    	Respuesta<ChanceHistorialView> respuestaHistorialView = new Respuesta<ChanceHistorialView>();
    	respuestaHistorialView.setRespuesta(null);
    	respuestaHistorialView.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(gestorEventoComercialBO.obtenerPremiaciones(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	    	.thenReturn(respuestaDTO);
    	Mockito.when(gestorEventoComercialBO.obtenerCotasMaximaYMinina(Matchers.any(String.class), Matchers.any(Cliente.class)))
    	.thenReturn(respuestaHistorialView);
    	
    	String fechaCorte = "20190624";
    	Respuesta<ChanceView> respuestaView = gestorEventosComercialesManager.obtenerPremiaciones(fechaCorte);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.WARNING, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtener Premiaciones: ERROR: el servicio nos da un error.
     */
    @Test
	public void obtenerPremiacionesTestErrorServicio() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos respuesta ChancesDTO  
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	//Datos respuesta ChacnesHistorialView
    	Respuesta<ChanceHistorialView> respuestaHistorialView = new Respuesta<ChanceHistorialView>();
    	respuestaHistorialView.setRespuesta(null);
    	respuestaHistorialView.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(gestorEventoComercialBO.obtenerPremiaciones(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	    	.thenReturn(respuestaDTO);
    	Mockito.when(gestorEventoComercialBO.obtenerCotasMaximaYMinina(Matchers.any(String.class), Matchers.any(Cliente.class)))
    	.thenReturn(respuestaHistorialView);
    	
    	String fechaCorte = "20190624";
    	Respuesta<ChanceView> respuestaView = gestorEventosComercialesManager.obtenerPremiaciones(fechaCorte);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtener Premiaciones: ERROR: el servicio nos da un error cliente igual null o cliente no tiene nup.
     */
    @Test
	public void obtenerPremiacionesTestErrorCliente() {
    	//Datos del Cliente
    	Cliente cliente = null;
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje("1897",
    			"<p>No hay información para mostrar.</p><p>Ocurrió un error en nuestros servicios. Por favor, volvé a ingresar más tarde.</p>");
    	//Datos respuesta ChancesDTO  
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	//Datos respuesta ChacnesHistorialView
    	Respuesta<ChanceHistorialView> respuestaHistorialView = new Respuesta<ChanceHistorialView>();
    	respuestaHistorialView.setRespuesta(null);
    	respuestaHistorialView.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialBO.obtenerPremiaciones(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	    	.thenReturn(respuestaDTO);
    	Mockito.when(gestorEventoComercialBO.obtenerCotasMaximaYMinina(Matchers.any(String.class), Matchers.any(Cliente.class)))
    	.thenReturn(respuestaHistorialView);
    	
    	String fechaCorte = "20190624";
    	Respuesta<ChanceView> respuestaView = gestorEventosComercialesManager.obtenerPremiaciones(fechaCorte);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
    }
    
    @Test
    public void obtenerNotificacionesPromesaPagoOK() throws BusinessException {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        GestionEventoComercialOutEntity notificaciones = new GestionEventoComercialOutEntity();
        notificaciones.setNup("00276937");
        notificaciones.setCantNotifSinLeer("1");
        notificaciones.setTotalNotif("6");
        notificaciones.setCodigoError("ERROR000");
        notificaciones.setDescripcionError("");
        List<NotificacionOutEntity> notificacionesList = new ArrayList<NotificacionOutEntity>();
        
        NotificacionOutEntity notificacionOutEntity = crearNotificacionPromesaPago();
        notificacionesList.add(notificacionOutEntity);
        notificaciones.setNotificaciones(notificacionesList);
        Cliente cliente = cargarClientePromesaPago();

        Mensaje mensajeDescriptivoPP = new Mensaje();
        mensajeDescriptivoPP.setMensaje("Mensaje descriptivo PP");

        Mensaje mensajeAyudaPP = new Mensaje();
        mensajeAyudaPP.setMensaje("Mensaje ayuda PP");
        
        Mensaje mensajeDescubiertoPP = new Mensaje();
        mensajeDescubiertoPP.setMensaje("Mensaje descubierto PP");
        
        Mensaje mensajeErrorDeudasPP = new Mensaje();
        mensajeErrorDeudasPP.setMensaje("Mensaje error deudas PP");
        
        
        
        // When
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Mockito.any(AccionController.class)))
        	.thenReturn(moduloPermiso);
        when(gestorEventoComercialBO.obtenerNotificaciones(Matchers.any(Cliente.class)))
        	.thenReturn(notificaciones);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_DESCRIPTIVO_DEUDAS_PP))
        	.thenReturn(mensajeDescriptivoPP);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AYUDA_PROMESA_PAGO))
			.thenReturn(mensajeAyudaPP);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_AYUDA_DESCUBIERTO))
			.thenReturn(mensajeDescubiertoPP);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PROMESA_PAGO_ERROR_DEUDAS))
			.thenReturn(mensajeErrorDeudasPP);
		when(sesionCliente.getCliente()).thenReturn(cliente);
        

        // Then
        Respuesta<NotificacionesComercialesView> notif = gestorEventosComercialesManager
                .obtenerNotificaciones(cliente);

        // Expected
        Assert.assertNotNull(notif);
        Assert.assertEquals(EstadoRespuesta.OK, notif.getEstadoRespuesta());
    }
    
    private NotificacionOutEntity crearNotificacionPromesaPago() {
    	
    	NotificacionOutEntity notificacionOutEntity = new NotificacionOutEntity();
    	notificacionOutEntity.setIdNotificacion("16824672");
    	notificacionOutEntity.setNup("00093098");
    	notificacionOutEntity.setCodigo("PM0001");
    	notificacionOutEntity.setFechaAlta("2019-10-01");
    	notificacionOutEntity.setLink("gotoAvisoDeudaPP()");
    	notificacionOutEntity.setPmDia("H");
    	notificacionOutEntity.setMontoTotal("250");
    	notificacionOutEntity.setSubCodigo("1");
    	notificacionOutEntity.setTitulo("Importante!");
    	notificacionOutEntity.setIndicaLectura("S");
    	notificacionOutEntity.setIndicaInactivable("N");
    	notificacionOutEntity.setMensajeAmigable("Usted posee deuda en uno o más productos.");
    	notificacionOutEntity.setPmCodNotificacion("PM0001");
    	notificacionOutEntity.setPmProductos("40|INT |0084|035100303551|100|20190901||05|1001|0084|035100037821|150|20190901||");
    	
    	GotoLink gotoLink = new GotoLink("gotoAvisoDeudaPP()");
    	notificacionOutEntity.setGotoLink(gotoLink);
    	
    	return notificacionOutEntity;
    	
    }
    
    private Cliente cargarClientePromesaPago() {
    	
    	Cliente cliente = mock(Cliente.class);
    	List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
    	
    	Cuenta cuenta = new Cuenta();
    	cuenta.setProductoAltair("40");
    	cuenta.setSubproductoAltair("INT");
    	cuenta.setNroSucursal("0084");
    	listaCuentas.add(cuenta);
    	
    	Cuenta cuenta2 = new Cuenta();
    	cuenta.setProductoAltair("05");
    	cuenta.setSubproductoAltair("1001");
    	cuenta.setNroSucursal("0084");
    	listaCuentas.add(cuenta2);
    	
    	cliente.setCuentas(listaCuentas);
    	
    	return cliente;
    }

        
	@SuppressWarnings("unchecked")
	@Test
	public void finalizarPromesaPagoTestOk() {
		FinalizarPromesaPagoInView finalizarPlazoFijoInView = new FinalizarPromesaPagoInView();
		finalizarPlazoFijoInView.setFechaPromesa("");
		finalizarPlazoFijoInView.setImportePromesa("");

		Respuesta<FinalizarPromesaEntity> respuestaBO = new Respuesta<FinalizarPromesaEntity>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarPromesaEntity respuesta = new FinalizarPromesaEntity();
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(gestorEventoComercialBO.finalizarPromesaPago(Matchers.any(Cliente.class), Matchers.any(FinalizarPromesaPagoInView.class))).thenReturn(respuestaBO);

		RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
		Mockito.when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(true);
		when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.TRUE);
		when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FinalizarPromesaPagoOutView> rtaFactory = new Respuesta<FinalizarPromesaPagoOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarPlazoFijoView.class)))
				.thenReturn(rtaFactory);

		Respuesta<FinalizarPromesaPagoOutView> rtaManager = gestorEventosComercialesManager
				.finalizarPromesaPago(finalizarPlazoFijoInView);
		Assert.assertNotNull(rtaManager);

	}
    
    

	@Test
	public void finalizarPromesaPagoTestERROR() {
		FinalizarPromesaPagoInView finalizarPlazoFijoInView = new FinalizarPromesaPagoInView();
		finalizarPlazoFijoInView.setFechaPromesa("");
		finalizarPlazoFijoInView.setImportePromesa("");

		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("mensaje test");
		itemMensajeRespuesta.setTipoError("tipo de error test");
		List<ItemMensajeRespuesta> itemMensajeRespuestas = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuestas.add(itemMensajeRespuesta);

		Respuesta<FinalizarPromesaEntity> respuestaBO = new Respuesta<FinalizarPromesaEntity>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		FinalizarPromesaEntity respuesta = new FinalizarPromesaEntity();
		respuestaBO.setRespuesta(respuesta);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuestas);

		
		Mockito.when(gestorEventoComercialBO.finalizarPromesaPago(Matchers.any(Cliente.class), Matchers.any(FinalizarPromesaPagoInView.class))).thenReturn(respuestaBO);
		RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
		Mockito.when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Mockito.when(estadisticaManager.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
				Matchers.any(Cliente.class))).thenReturn(true);
		when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.TRUE);
		when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Object> rtaFactory = new Respuesta<Object>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(rtaFactory);

		Respuesta<FinalizarPromesaPagoOutView> rtaManager = gestorEventosComercialesManager
				.finalizarPromesaPago(finalizarPlazoFijoInView);
		Assert.assertNotNull(rtaManager);
		Assert.assertEquals(EstadoRespuesta.ERROR, rtaManager.getEstadoRespuesta());

	}
	
	

    
}
