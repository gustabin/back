package ar.com.santanderrio.obp.servicios.perfil.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.bo.BonificacionesVigentesBO;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.view.BonificacionVigenteView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo.ConsultaDeudaBO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GestionEventoComercialOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.NotificacionOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager.GestorEventosComercialesManager;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.NotificacionesComercialesView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.perfil.manager.impl.PerfilManagerImpl;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilAlertaGalaView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilEjecutivoView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilPersonalView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CajaPerfilSucursalView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;

@RunWith(MockitoJUnitRunner.class)
public class PerfilManagerObtenerPerfilTest {

	/** The manager. */
	@InjectMocks
	private PerfilManager perfilManager = new PerfilManagerImpl();

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private MensajeBO mensajeBO;
	
	@Spy
	private RespuestaFactory respuestaFactory;

	@Mock
	private EstadisticaManager estadisticaManager;

	@Mock
	private GestorEventosComercialesManager gestorEventosComercialesManager;
	@Mock
	private ModuloPermisoBO moduloPermisoBO;

	@Mock
	private ConsultaDeudaBO consultaDeudaBO;

	@Mock
	private SesionParametros sesionParametros;

	@Mock
	private ConsultarSucursalesBO consultarSucursalesBO;
	
	@Mock
	private BonificacionesVigentesBO bonificacionesVigentesBO;
	
	private static final String GOTOFORMUMAUFI = "gotoFormularioIMAUIF(http://www.google.com)";

	@Test
	public void obtenerPerfilTest() throws BusinessException {

		Cliente cliente = new Cliente();
		Segmento segmento = new Segmento();
		Respuesta<ClasificacionDeuda> respuestaClasificacionDeuda = new Respuesta<ClasificacionDeuda>();
		ClasificacionDeuda clasifiacionDeuda = new ClasificacionDeuda();
		Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
		Sucursal sucursal = new Sucursal();

		NotificacionOutEntity notifEntity = new NotificacionOutEntity();
		notifEntity.setIdNotificacion("1");
		notifEntity.setTitulo("Gala");
		notifEntity.setMensajeAmigable("Mensaje gala.");
		notifEntity.setLinkPortal(GOTOFORMUMAUFI);
		notifEntity.setUrl("http://www.google.com");
		notifEntity.setIndicaLectura("S");
		notifEntity.setFechaAlta("2017-09-22");
		notifEntity.setGotoLink(new GotoLink(GOTOFORMUMAUFI));
		List<NotificacionOutEntity> notificacionesEnt = new ArrayList<NotificacionOutEntity>();
		notificacionesEnt.add(notifEntity);
		GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
		notificacionesEntity.setCantNotifSinLeer("0");
		notificacionesEntity.setTotalNotif("2");
		notificacionesEntity.setNotificaciones(notificacionesEnt);
		NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, null,
		        cliente);
		Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
		        .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);

		sucursal.setDireccion("CALLE FALSA 123");
		sucursal.setDescripcion("MERLO");
		respuestaSucursal.setRespuesta(sucursal);
		respuestaSucursal.setEstadoRespuesta(EstadoRespuesta.OK);
		clasifiacionDeuda.setSituacionBcra("5");
		clasifiacionDeuda.setDescripcion("Alta Deuda");
		respuestaClasificacionDeuda.setRespuesta(clasifiacionDeuda);
		respuestaClasificacionDeuda.setEstadoRespuesta(EstadoRespuesta.OK);
		segmento.setEjecutivo("Pipo");
		;
		segmento.setSelect(true);
		segmento.setPesucadm("01");
		cliente.setNombre("RUPERTO");
		cliente.setApellido1("PESTO");
		cliente.setSegmento(segmento);
		
		List<BonificacionVigenteView> bonificacionesView = new ArrayList<BonificacionVigenteView>();
		BonificacionVigenteView bonificacion = new BonificacionVigenteView();
		bonificacionesView.add(bonificacion);

		when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSucursal);
		when(sesionParametros.getUltimoAcceso()).thenReturn(new Date());
		when(consultaDeudaBO.consultarDeuda(Matchers.anyString())).thenReturn(respuestaClasificacionDeuda);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(gestorEventosComercialesManager.obetenerNotificacionesPerfil(cliente)).thenReturn(respNot);
		ModuloPermiso moduloPermiso = new ModuloPermiso();
		moduloPermiso.setAccionController(AccionController.LOGOUT_MOBILE);
		moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGOUT_MOBILE)).thenReturn(moduloPermiso);
		when(bonificacionesVigentesBO.obtenerBonificaciones(Matchers.anyString())).thenReturn(bonificacionesView);
		
		Respuesta<PerfilView> respuesta = perfilManager.obtenerPerfil();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals("Ruperto Pesto", respuesta.getRespuesta().getNombreCliente());
		assertTrue(respuesta.getRespuesta().getIsSelect());
		assertEquals(5, respuesta.getRespuesta().getCajas().size());

		CajaPerfilPersonalView cajaPerfilPersonalView = (CajaPerfilPersonalView) respuesta.getRespuesta().getCajas()
		        .get(0);
		CajaPerfilSucursalView cajaPerfilSucursalView = (CajaPerfilSucursalView) respuesta.getRespuesta().getCajas()
		        .get(1);
		CajaPerfilEjecutivoView cajaPerfilEjecutivoView = (CajaPerfilEjecutivoView) respuesta.getRespuesta().getCajas()
		        .get(2);
		CajaPerfilAlertaGalaView cajaPerfilAlertaGalaView = (CajaPerfilAlertaGalaView) respuesta.getRespuesta()
		        .getCajas().get(4);
//		CajaPerfilDeudorView cajaPerfilDeudorView = (CajaPerfilDeudorView)respuesta.getRespuesta().getCajas().get(3);
		assertTrue(cajaPerfilPersonalView.getIsPersonal());
		assertEquals("50", cajaPerfilPersonalView.getDescripcion());
		assertTrue(cajaPerfilEjecutivoView.getIsEjecutivo());
		assertEquals("Pipo", cajaPerfilEjecutivoView.getDescripcion());
		assertFalse(cajaPerfilEjecutivoView.getHasError());
//		assertTrue(cajaPerfilDeudorView.getIsDeudor());
//		assertEquals("Alta Deuda", cajaPerfilDeudorView.getDescripcion());
//		assertEquals("5", cajaPerfilDeudorView.getSituacion());
		assertTrue(cajaPerfilSucursalView.getIsSucursal());
		assertEquals(cajaPerfilSucursalView.getDescripcion(), "001 - Merlo");
		assertEquals(cajaPerfilSucursalView.getDireccion(), "Calle Falsa 123");
		assertTrue(cajaPerfilAlertaGalaView.getTieneAlertaGala());
		assertEquals(cajaPerfilAlertaGalaView.getLinkFormPortal(), GOTOFORMUMAUFI);
	}

	@Test
	public void obtenerPerfilErrorSegmentoErrorDeudorTest() throws BusinessException {

		Cliente cliente = new Cliente();
		Respuesta<ClasificacionDeuda> respuestaClasificacionDeuda = new Respuesta<ClasificacionDeuda>();

		respuestaClasificacionDeuda.setEstadoRespuesta(EstadoRespuesta.ERROR);
		cliente.setNombre("RUPERTO");
		cliente.setApellido1("PESTO");

		NotificacionOutEntity notifEntity = new NotificacionOutEntity();
		notifEntity.setIdNotificacion("1");
		notifEntity.setTitulo("Gala");
		notifEntity.setMensajeAmigable("Mensaje gala.");
		notifEntity.setLinkPortal(GOTOFORMUMAUFI);
		notifEntity.setUrl("http://www.google.com");
		notifEntity.setIndicaLectura("S");
		notifEntity.setFechaAlta("2017-09-22");
		notifEntity.setGotoLink(new GotoLink(GOTOFORMUMAUFI));
		List<NotificacionOutEntity> notificacionesEnt = new ArrayList<NotificacionOutEntity>();
		notificacionesEnt.add(notifEntity);
		GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
		notificacionesEntity.setCantNotifSinLeer("0");
		notificacionesEntity.setTotalNotif("2");
		notificacionesEntity.setNotificaciones(notificacionesEnt);
		NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, null,
		        cliente);
		Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
		        .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);
		List<BonificacionVigenteView> bonificacionesView = new ArrayList<BonificacionVigenteView>();
		BonificacionVigenteView bonificacion = new BonificacionVigenteView();
		bonificacionesView.add(bonificacion);

		when(sesionParametros.getUltimoAcceso()).thenReturn(new Date());
		when(consultaDeudaBO.consultarDeuda(Matchers.anyString())).thenReturn(respuestaClasificacionDeuda);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(gestorEventosComercialesManager.obetenerNotificacionesPerfil(cliente)).thenReturn(respNot);
		ModuloPermiso moduloPermiso = new ModuloPermiso();
		moduloPermiso.setAccionController(AccionController.LOGOUT_MOBILE);
		moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGOUT_MOBILE)).thenReturn(moduloPermiso);
		when(bonificacionesVigentesBO.obtenerBonificaciones(Matchers.anyString())).thenReturn(bonificacionesView);
		
		Respuesta<PerfilView> respuesta = perfilManager.obtenerPerfil();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals("Ruperto Pesto", respuesta.getRespuesta().getNombreCliente());
		assertFalse(respuesta.getRespuesta().getIsSelect());
		assertEquals(2, respuesta.getRespuesta().getCajas().size());

		CajaPerfilPersonalView cajaPerfilPersonalView = (CajaPerfilPersonalView) respuesta.getRespuesta().getCajas()
		        .get(0);
		assertTrue(cajaPerfilPersonalView.getIsPersonal());
		assertEquals("50", cajaPerfilPersonalView.getDescripcion());
	}

	@Test
	public void obtenerPerfilSegmentoSucursalVaciaTest() throws BusinessException {

		Cliente cliente = new Cliente();
		Respuesta<ClasificacionDeuda> respuestaClasificacionDeuda = new Respuesta<ClasificacionDeuda>();
		Segmento segmento = new Segmento();

		segmento.setPesucadm("");
		respuestaClasificacionDeuda.setEstadoRespuesta(EstadoRespuesta.ERROR);
		cliente.setSegmento(segmento);
		cliente.setNombre("RUPERTO");
		cliente.setApellido1("PESTO");

		NotificacionOutEntity notifEntity = new NotificacionOutEntity();
		notifEntity.setIdNotificacion("1");
		notifEntity.setTitulo("Gala");
		notifEntity.setMensajeAmigable("Mensaje gala.");
		notifEntity.setLinkPortal(GOTOFORMUMAUFI);
		notifEntity.setUrl("http://www.google.com");
		notifEntity.setIndicaLectura("S");
		notifEntity.setFechaAlta("2017-09-22");
		notifEntity.setGotoLink(new GotoLink(GOTOFORMUMAUFI));
		List<NotificacionOutEntity> notificacionesEnt = new ArrayList<NotificacionOutEntity>();
		notificacionesEnt.add(notifEntity);
		GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
		notificacionesEntity.setCantNotifSinLeer("0");
		notificacionesEntity.setTotalNotif("2");
		notificacionesEntity.setNotificaciones(notificacionesEnt);
		NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, null,
		        cliente);
		Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
		        .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);
		List<BonificacionVigenteView> bonificacionesView = new ArrayList<BonificacionVigenteView>();
		BonificacionVigenteView bonificacion = new BonificacionVigenteView();
		bonificacionesView.add(bonificacion);
		
		when(sesionParametros.getUltimoAcceso()).thenReturn(new Date());
		when(consultaDeudaBO.consultarDeuda(Matchers.anyString())).thenReturn(respuestaClasificacionDeuda);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(gestorEventosComercialesManager.obetenerNotificacionesPerfil(cliente)).thenReturn(respNot);
		ModuloPermiso moduloPermiso = new ModuloPermiso();
		moduloPermiso.setAccionController(AccionController.LOGOUT_MOBILE);
		moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGOUT_MOBILE)).thenReturn(moduloPermiso);
		when(bonificacionesVigentesBO.obtenerBonificaciones(Matchers.anyString())).thenReturn(bonificacionesView);
		
		Respuesta<PerfilView> respuesta = perfilManager.obtenerPerfil();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals("Ruperto Pesto", respuesta.getRespuesta().getNombreCliente());
		assertFalse(respuesta.getRespuesta().getIsSelect());
		assertEquals(2, respuesta.getRespuesta().getCajas().size());

		CajaPerfilPersonalView cajaPerfilPersonalView = (CajaPerfilPersonalView) respuesta.getRespuesta().getCajas()
		        .get(0);
		assertTrue(cajaPerfilPersonalView.getIsPersonal());
		assertEquals("50", cajaPerfilPersonalView.getDescripcion());
	}

	@Test
	public void obtenerPerfilErrorConsultaSucursalTest() throws BusinessException {

		Cliente cliente = new Cliente();
		Respuesta<ClasificacionDeuda> respuestaClasificacionDeuda = new Respuesta<ClasificacionDeuda>();
		Segmento segmento = new Segmento();
		Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();

		respuestaSucursal.setEstadoRespuesta(EstadoRespuesta.ERROR);
		segmento.setPesucadm("001");
		respuestaClasificacionDeuda.setEstadoRespuesta(EstadoRespuesta.ERROR);
		cliente.setSegmento(segmento);
		cliente.setNombre("RUPERTO");
		cliente.setApellido1("PESTO");

		NotificacionOutEntity notifEntity = new NotificacionOutEntity();
		notifEntity.setIdNotificacion("1");
		notifEntity.setTitulo("Gala");
		notifEntity.setMensajeAmigable("Mensaje gala.");
		notifEntity.setLinkPortal(GOTOFORMUMAUFI);
		notifEntity.setUrl("http://www.google.com");
		notifEntity.setIndicaLectura("S");
		notifEntity.setFechaAlta("2017-09-22");
		notifEntity.setGotoLink(new GotoLink(GOTOFORMUMAUFI));
		List<NotificacionOutEntity> notificacionesEnt = new ArrayList<NotificacionOutEntity>();
		notificacionesEnt.add(notifEntity);
		GestionEventoComercialOutEntity notificacionesEntity = new GestionEventoComercialOutEntity();
		notificacionesEntity.setCantNotifSinLeer("0");
		notificacionesEntity.setTotalNotif("2");
		notificacionesEntity.setNotificaciones(notificacionesEnt);
		NotificacionesComercialesView notificaciones = new NotificacionesComercialesView(notificacionesEntity, null,
		        cliente);
		Respuesta<NotificacionesComercialesView> respNot = respuestaFactory
		        .crearRespuestaOk(NotificacionesComercialesView.class, notificaciones);
		List<BonificacionVigenteView> bonificacionesView = new ArrayList<BonificacionVigenteView>();
		BonificacionVigenteView bonificacion = new BonificacionVigenteView();
		bonificacionesView.add(bonificacion);
		
		when(consultarSucursalesBO.consultarSucursalPorId(Matchers.anyString())).thenReturn(respuestaSucursal);
		when(sesionParametros.getUltimoAcceso()).thenReturn(new Date());
		when(consultaDeudaBO.consultarDeuda(Matchers.anyString())).thenReturn(respuestaClasificacionDeuda);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(gestorEventosComercialesManager.obetenerNotificacionesPerfil(cliente)).thenReturn(respNot);
		ModuloPermiso moduloPermiso = new ModuloPermiso();
		moduloPermiso.setAccionController(AccionController.LOGOUT_MOBILE);
		moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
		when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGOUT_MOBILE)).thenReturn(moduloPermiso);
		when(bonificacionesVigentesBO.obtenerBonificaciones(Matchers.anyString())).thenReturn(bonificacionesView);
		
		Respuesta<PerfilView> respuesta = perfilManager.obtenerPerfil();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals("Ruperto Pesto", respuesta.getRespuesta().getNombreCliente());
		assertFalse(respuesta.getRespuesta().getIsSelect());
		assertEquals(3, respuesta.getRespuesta().getCajas().size());

		CajaPerfilPersonalView cajaPerfilPersonalView = (CajaPerfilPersonalView) respuesta.getRespuesta().getCajas()
		        .get(0);
		CajaPerfilSucursalView cajaPerfilSucursalView = (CajaPerfilSucursalView) respuesta.getRespuesta().getCajas()
		        .get(1);
		assertTrue(cajaPerfilPersonalView.getIsPersonal());
		assertEquals("50", cajaPerfilPersonalView.getDescripcion());
		assertTrue(cajaPerfilSucursalView.getIsSucursal());
		assertEquals(cajaPerfilSucursalView.getDescripcion(), "001 - -");
		assertEquals(cajaPerfilSucursalView.getDireccion(), "-");
	}

}
