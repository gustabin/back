package ar.com.santanderrio.obp.servicios.inversiones.maps.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.PerfilInversorEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.bo.ServiciosDeInversionBO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.InicioServiciosDeInversionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ServicioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ServicioMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.InicioServiciosDeInversionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.ObtenerDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.VerDetallePerfilInversorView;

@RunWith(MockitoJUnitRunner.class)
public class ServiciosDeInversionManagerTest {

	@InjectMocks
	ServiciosDeInversionManagerImpl serviciosDeInversionManager;

	@Spy
	@InjectMocks
	InversionesBO inversionesBO = new InversionesBOImpl();

	/** The plazo fijo BO. */
	@Mock
	ServiciosDeInversionBO serviciosDeInversionBO;

	/** The sesion cliente. */
	@Mock
	SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private EstadisticaManager estadisticaManager;

	@SuppressWarnings("unchecked")
	@Test
	public void inicioServiciosDeInversion() {

		InicioServiciosDeInversionDTO inicioServiciosDeInversionDTO = new InicioServiciosDeInversionDTO(false, false);
		inicioServiciosDeInversionDTO.setBpriv(true);

		Respuesta<InicioServiciosDeInversionDTO> respuestaBO = new Respuesta<InicioServiciosDeInversionDTO>();
		respuestaBO.setRespuesta(inicioServiciosDeInversionDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Mockito.when(serviciosDeInversionBO.inicioServiciosDeInversion(sesionCliente.getCliente()))
				.thenReturn(respuestaBO);

		Respuesta<InicioServiciosDeInversionView> rtaManager = new Respuesta<InicioServiciosDeInversionView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(InicioServiciosDeInversionView.class))).thenReturn(rtaManager);

		Respuesta<InicioServiciosDeInversionView> respuesta = serviciosDeInversionManager.inicioServiciosDeInversion();
		Assert.assertNotNull(respuesta);

	}

	@Test
	public void notificarGotoTenenciaConsolidadaTest() {

		Respuesta<Void> respuestaBO = new Respuesta<Void>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("BP");
		Respuesta<Void> respuesta = serviciosDeInversionManager.notificarGotoTenenciaConsolidada(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_GOTO_TENENCIA_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		inView.setBanca("RTL");
		respuesta = serviciosDeInversionManager.notificarGotoTenenciaConsolidada(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_GOTO_TENENCIA_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	@Test
	public void obtenerControlesDisponiblesRespuesta_WARNING() {

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("bpriv");

		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl respuestaF = crearFormularioConControles();
		respuestaBO.setRespuesta(respuestaF);

		Mockito.when(serviciosDeInversionBO.obtenerControlesDisponibles(sesionCliente.getCliente(),
				inView.getBanca().toUpperCase())).thenReturn(respuestaBO);

		BancaInversionesView obtenerDisponiblesInView = new BancaInversionesView();
		obtenerDisponiblesInView.setBanca("bpriv");

		Respuesta<ObtenerDisponiblesOutView> rtaManager = new Respuesta<ObtenerDisponiblesOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewError = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<ObtenerDisponiblesOutView> respuesta = serviciosDeInversionManager
				.obtenerControlesDisponibles(obtenerDisponiblesInView);
		Assert.assertNotNull(respuesta);

	}

	@Test
	public void obtenerControlesDisponiblesRespuesta_OK() throws ControlMapValidationException {

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("bpriv");

		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");

		List<ControlMaps> controles = new ArrayList<ControlMaps>();
		ServicioControl controlMaps = new ServicioControl();
		List<ServicioMaps> servicios = new ArrayList<ServicioMaps>();
		ServicioMaps servicio = new ServicioMaps();
		servicio.setCantidadAdhesiones("2");
		servicio.setFooter("footer");
		servicio.setIcono("icono");
		servicio.setPosicion(1);
		servicio.setSeleccionado(true);
		servicio.setTipoServicio("tipoServicio");
		servicio.setTitulo("titulo");
		servicio.setValor("valor");
		servicio.setValorPadre("valorPadre");
		servicio.setBloqueado(false);
		servicio.setDesc("descripcion");
		controlMaps.setItems(servicios);
		controlMaps.setId("ere");
		controlMaps.setNombre("gfd");
		controlMaps.setTipoDataValor("string");
		formulario.setItems(controles);

		respuestaBO.setRespuesta(formulario);

		Mockito.when(serviciosDeInversionBO.obtenerControlesDisponibles(sesionCliente.getCliente(),
				inView.getBanca().toUpperCase())).thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		BancaInversionesView obtenerDisponiblesInView = new BancaInversionesView();
		obtenerDisponiblesInView.setBanca("bpriv");

		ServicioMaps servicioMaps = new ServicioMaps();
		servicioMaps.setBloqueado(Boolean.FALSE);
		servicioMaps.setCantidadAdhesiones("2");
		servicioMaps.setFooter("footer");
		servicioMaps.setIcono("icono");
		servicioMaps.setPosicion(1);
		servicioMaps.setSeleccionado(true);
		servicioMaps.setTipoServicio("tipoServicio");
		servicioMaps.setTitulo("titulo");
		servicioMaps.setValor("valor");
		servicioMaps.setValorPadre("valorPadre");
		servicioMaps.setDesc("desc");

		ServicioMaps servicio2 = new ServicioMaps();
		servicio2.setCantidadAdhesiones("2");
		servicio2.setFooter("footer");
		servicio2.setIcono("icono");
		servicio2.setPosicion(1);
		servicio2.setSeleccionado(true);
		servicio2.setTipoServicio("tipoServicio");
		servicio2.setTitulo("titulo");
		servicio2.setValor("valor");
		servicio2.setValorPadre("valorPadre");
		servicio2.setBloqueado(Boolean.TRUE);
		servicio2.setDesc("descripcion");

		Respuesta<ObtenerDisponiblesOutView> rtaManager = new Respuesta<ObtenerDisponiblesOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);

		List<ServicioMaps> controlesServicios = new ArrayList<ServicioMaps>();
		controlesServicios.add(servicioMaps);
		controlesServicios.add(servicio2);
		ServicioControl controlServicio = new ServicioControl();
		controlServicio.setItems(controlesServicios);
		controlServicio.setId("id");
		controlServicio.setNombre("nombre");
		controlServicio.setTipoDataValor("tipoDataValor");

		rtaManager.setRespuesta(new ObtenerDisponiblesOutView(formulario));
		rtaManager.getRespuesta().agregarControl(controlServicio);

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewError = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Respuesta<ObtenerDisponiblesOutView> respuesta = serviciosDeInversionManager
				.obtenerControlesDisponibles(obtenerDisponiblesInView);
		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.CONTROLES_DISPONIBLES_HOME_MAPS_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void obtenerControlesDisponiblesError() {

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Respuesta<FormularioControl> respuestaMockBO = new Respuesta<FormularioControl>();
		respuestaMockBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(
				serviciosDeInversionBO.obtenerControlesDisponibles(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(respuestaMockBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		BancaInversionesView inView = new BancaInversionesView();
		String bancaRTL = "rtl";
		inView.setBanca(bancaRTL);
		Respuesta<ObtenerDisponiblesOutView> respuesta = serviciosDeInversionManager
				.obtenerControlesDisponibles(inView);

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.CONTROLES_DISPONIBLES_HOME_MAPS_BPERS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	@Test
	public void obtenerControlesDisponiblesWarningTodosBloquedos() {

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		FormularioControl respuestaF = crearFormularioConControles();
		ServicioControl controlServicio = new ServicioControl();
		controlServicio.setBloqueado(true);
		controlServicio.setItems(new ArrayList<ServicioMaps>());
		ServicioMaps servicioMaps = new ServicioMaps();
		servicioMaps.setCantidadAdhesiones("19");
		servicioMaps.setBloqueado(true);
		controlServicio.getItems().add(servicioMaps);
		respuestaF.getItems().add(controlServicio);
		respuestaBO.setRespuesta(respuestaF);

		respuestaBO.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		respuestaBO.getItemsMensajeRespuesta().add(itemMensajeRespuesta);
		itemMensajeRespuesta.setTipoError(TipoError.ALGUN_SERVICIO_ERRONEO.getDescripcion());

		Mockito.when(
				serviciosDeInversionBO.obtenerControlesDisponibles(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(respuestaBO);

		BancaInversionesView inView = new BancaInversionesView();
		String bancaRTL = "rtl";
		inView.setBanca(bancaRTL);
		Respuesta<ObtenerDisponiblesOutView> respuesta = serviciosDeInversionManager
				.obtenerControlesDisponibles(inView);

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.CONTROLES_DISPONIBLES_HOME_MAPS_BPERS,
				EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
	}

	/**
	 * Se prueba que la para una respuesta warning por parte del BO a causa de algun
	 * control servicio descartado quede al menos un control servicio no bloqueado.
	 * Lo cual generaria una respuesta OK de parte del manager
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerControlesDisponiblesWarningParcialesYNoBloqueados() {

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewOK = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewOK.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
				Matchers.any(InicioServiciosDeInversionView.class))).thenReturn(respuestaViewOK);

		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		FormularioControl respuestaF = crearFormularioConControles();
		ServicioControl controlServicio = new ServicioControl();
		controlServicio.setBloqueado(true);
		controlServicio.setItems(new ArrayList<ServicioMaps>());
		ServicioMaps servicioMaps = new ServicioMaps();
		servicioMaps.setCantidadAdhesiones("19");
		servicioMaps.setBloqueado(true);
		controlServicio.getItems().add(servicioMaps);
		ServicioMaps servicioMaps2 = new ServicioMaps();
		servicioMaps2.setCantidadAdhesiones("23");
		servicioMaps2.setBloqueado(false);
		controlServicio.getItems().add(servicioMaps2);
		respuestaF.getItems().add(controlServicio);
		respuestaBO.setRespuesta(respuestaF);

		respuestaBO.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		respuestaBO.getItemsMensajeRespuesta().add(itemMensajeRespuesta);
		itemMensajeRespuesta.setTipoError(TipoError.ALGUN_SERVICIO_ERRONEO.getDescripcion());

		Mockito.when(
				serviciosDeInversionBO.obtenerControlesDisponibles(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(respuestaBO);

		BancaInversionesView inView = new BancaInversionesView();
		String bancaRTL = "rtl";
		inView.setBanca(bancaRTL);
		Respuesta<ObtenerDisponiblesOutView> respuesta = serviciosDeInversionManager
				.obtenerControlesDisponibles(inView);
		Assert.assertNotNull(respuesta);
	}

	private FormularioControl crearFormularioConControles() {
		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");

		ServicioControl controlMaps = new ServicioControl();
		List<ServicioMaps> servicios = new ArrayList<ServicioMaps>();
		ServicioMaps servicio = new ServicioMaps();
		servicio.setCantidadAdhesiones("2");
		servicios.add(servicio);
		controlMaps.setItems(servicios);
		List<ControlMaps> controles = new ArrayList<ControlMaps>();
		formulario.setItems(controles);
		return formulario;
	}

	@Test
	public void altaServicio_OK() throws ControlMapValidationException {

		Cliente cliente = new Cliente();
		cliente.setNup("00001312");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		FormulariosAltaInicioInView inView = new FormulariosAltaInicioInView();
		inView.setBanca("RTL");

		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);

		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");

		List<ControlMaps> controles = new ArrayList<ControlMaps>();
		ServicioControl controlMaps = new ServicioControl();
		List<ServicioMaps> servicios = new ArrayList<ServicioMaps>();
		ServicioMaps servicio = new ServicioMaps();
		servicio.setCantidadAdhesiones("2");
		servicio.setFooter("footer");
		servicio.setIcono("icono");
		servicio.setPosicion(1);
		servicio.setSeleccionado(true);
		servicio.setTipoServicio("tipoServicio");
		servicio.setTitulo("titulo");
		servicio.setValor("valor");
		servicio.setValorPadre("valorPadre");
		servicio.setBloqueado(false);
		servicio.setDesc("descripcion");
		servicios.add(servicio);
		controlMaps.setItems(servicios);
		controlMaps.setId("ere");
		controlMaps.setNombre("gfd");
		controlMaps.setTipoDataValor("string");
		formulario.setItems(controles);
		formulario.setEstado("carga");

		respuestaBO.setRespuesta(formulario);
		Mockito.when(serviciosDeInversionBO.altaServicio(Matchers.any(String.class),
				Matchers.any(FormulariosAltaInicioInView.class))).thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormulariosAltaInicioInView formulariosAltaInicioInView = new FormulariosAltaInicioInView();
		formulariosAltaInicioInView.setBanca("RTL");

		ServicioMaps servicioMaps = new ServicioMaps();
		servicioMaps.setBloqueado(Boolean.FALSE);
		servicioMaps.setCantidadAdhesiones("2");
		servicioMaps.setFooter("footer");
		servicioMaps.setIcono("icono");
		servicioMaps.setPosicion(1);
		servicioMaps.setSeleccionado(true);
		servicioMaps.setTipoServicio("tipoServicio");
		servicioMaps.setTitulo("titulo");
		servicioMaps.setValor("valor");
		servicioMaps.setValorPadre("valorPadre");
		servicioMaps.setDesc("desc");

		Respuesta<ObtenerDisponiblesOutView> rtaManager = new Respuesta<ObtenerDisponiblesOutView>();
		rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);

		List<ServicioMaps> controlesServicios = new ArrayList<ServicioMaps>();
		controlesServicios.add(servicioMaps);
		ServicioControl controlServicio = new ServicioControl();
		controlServicio.setItems(controlesServicios);
		controlServicio.setId("id");
		controlServicio.setNombre("nombre");
		controlServicio.setTipoDataValor("tipoDataValor");
		rtaManager.setRespuesta(new ObtenerDisponiblesOutView(formulario));
		rtaManager.getRespuesta().agregarControl(controlServicio);

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewError = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.altaServicio(formulariosAltaInicioInView);
		formulariosAltaInicioInView.setBanca("BP");
		serviciosDeInversionManager.altaServicio(formulariosAltaInicioInView);

		Mockito.when(serviciosDeInversionBO.altaServicioFlujo(Matchers.any(String.class),
				Matchers.any(FormularioControl.class))).thenReturn(respuestaBO);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_MAPS_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_MAPS_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(respuesta);

		Respuesta<FormularioControl> respuestaFlujo = serviciosDeInversionManager
				.altaServicioFlujo(respuesta.getRespuesta());

		Assert.assertNotNull(respuestaFlujo);

	}

	@Test
	public void verDetallePerfilTestOK() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Descripcion larga de perfil agresivo");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		VerDetallePerfilInversorView inView = new VerDetallePerfilInversorView();
		inView.setPerfilDeInversor(PerfilInversorEnum.AGRESIVO.getDescripcion());
		Respuesta<DetallePerfilInversorView> respuestaManager = serviciosDeInversionManager.verDetallePerfil(inView);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertEquals(respuestaManager.getRespuesta().getDescripcionPerfil(), mensaje.getMensaje());
	}

	@Test
	public void verDetallePerfilTestError() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Descripcion larga de perfil agresivo");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		VerDetallePerfilInversorView inView = new VerDetallePerfilInversorView();
		inView.setPerfilDeInversor("perfil perfil perfil");
		Respuesta<DetallePerfilInversorView> respuestaManager = serviciosDeInversionManager.verDetallePerfil(inView);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void descargaComprobanteAltaAdhesionTest() {

		FormularioControl formulario = new FormularioControl();

		Respuesta<Reporte> reporteRespuesta = new Respuesta<Reporte>();
		reporteRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Reporte reporte = new Reporte();
		reporte.setBytes("ejemploByteArray".getBytes());
		reporte.setNombre("nombre archivo alta adhesion");
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		reporteRespuesta.setRespuesta(reporte);
		when(serviciosDeInversionBO.descargaComprobanteAltaAdhesion(Matchers.any(FormularioControl.class)))
				.thenReturn(reporteRespuesta);

		Respuesta<ReporteView> respuestaManager = serviciosDeInversionManager
				.descargaComprobanteAltaAdhesion(formulario);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void altaServicioEstadoDisclaimerTest() throws ControlMapValidationException {

		Cliente cliente = new Cliente();
		cliente.setNup("00001312");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		FormulariosAltaInicioInView inView = new FormulariosAltaInicioInView();
		inView.setBanca("bpriv");

		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");
		formulario.setEstado("disclaimer");
		Respuesta<FormularioControl> respuestaBO = respuestaFactory.crearRespuestaOk(formulario);

		Mockito.when(serviciosDeInversionBO.altaServicio(Matchers.any(String.class),
				Matchers.any(FormulariosAltaInicioInView.class))).thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormulariosAltaInicioInView formulariosAltaInicioInView = new FormulariosAltaInicioInView();
		formulariosAltaInicioInView.setBanca("RTL");

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewError = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.altaServicio(formulariosAltaInicioInView);
		formulariosAltaInicioInView.setBanca("RTL");

		Mockito.when(serviciosDeInversionBO.altaServicioFlujo(Matchers.any(String.class),
				Matchers.any(FormularioControl.class))).thenReturn(respuestaBO);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.DISCLAIMER_SERVICIOS_MAPS_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(respuesta);

		Respuesta<FormularioControl> respuestaFlujo = serviciosDeInversionManager
				.altaServicio(formulariosAltaInicioInView);

		Assert.assertNotNull(respuestaFlujo);

	}

	@Test
	public void accesoComprobanteAltaAdhesionTest() {

		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("BP");
		Respuesta<Void> respuesta = serviciosDeInversionManager.accesoComprobanteAltaAdhesion(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager).add(
				EstadisticasConstants.ESTADISTICA_ACCESO_COMPROBANTE_ALTA_ADHESION_MAPS_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		inView.setBanca("RTL");
		respuesta = serviciosDeInversionManager.accesoComprobanteAltaAdhesion(inView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		Mockito.verify(estadisticaManager).add(
				EstadisticasConstants.ESTADISTICA_ACCESO_COMPROBANTE_ALTA_ADHESION_MAPS_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	@Test
	public void altaServicioSimulacionTest() throws ControlMapValidationException {

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Cliente cliente = new Cliente();
		cliente.setNup("00001312");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");
		formulario.setEstado("simulacion");
		formulario.setSegmento("BP");
		Respuesta<FormularioControl> respuestaBO = respuestaFactory.crearRespuestaOk(formulario);
		Mockito.when(serviciosDeInversionBO.altaServicio(Matchers.any(String.class),
				Matchers.any(FormulariosAltaInicioInView.class))).thenReturn(respuestaBO);

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

		Respuesta<ObtenerDisponiblesOutView> respuestaViewError = new Respuesta<ObtenerDisponiblesOutView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.WARNING);

		Mockito.when(serviciosDeInversionBO.altaServicioFlujo(Matchers.any(String.class),
				Matchers.any(FormularioControl.class))).thenReturn(respuestaBO);

		Respuesta<FormularioControl> respuestaFlujo = serviciosDeInversionManager.altaServicioFlujo(formulario);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_FLUJO_MAPS_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		formulario.setEstado("carga");
		serviciosDeInversionManager.altaServicioFlujo(formulario);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_FLUJO_MAPS_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		formulario.setEstado("confirmacion");
		serviciosDeInversionManager.altaServicioFlujo(formulario);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.CONFIRMACION_SERVICIOS_FLUJO_MAPS_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		Assert.assertNotNull(respuestaFlujo);

	}

	@Test
	public void altaServicioSimulacionErrorTest() throws ControlMapValidationException {
		Cliente cliente = new Cliente();
		cliente.setNup("00001312");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		FormularioControl formulario = new FormularioControl();
		formulario.setAyuda("");
		formulario.setTitulo("");
		formulario.setIdServicio("");
		formulario.setPerfilInversor("");
		formulario.setEstado("simulacion");
		formulario.setSegmento("BP");
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(serviciosDeInversionBO.altaServicio(Matchers.any(String.class),
				Matchers.any(FormulariosAltaInicioInView.class))).thenReturn(respuestaBO);

		Mockito.when(serviciosDeInversionBO.altaServicioFlujo(Matchers.any(String.class),
				Matchers.any(FormularioControl.class))).thenReturn(respuestaBO);

		Respuesta<FormularioControl> respuestaFlujo = serviciosDeInversionManager.altaServicioFlujo(formulario);
		
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_MAPS_ERROR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertNotNull(respuestaFlujo);
		Assert.assertEquals(respuestaFlujo.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void altaServicio_Error() throws ControlMapValidationException {
		FormulariosAltaInicioInView inView = new FormulariosAltaInicioInView();
		inView.setBanca("RTL");
		Cliente cliente = new Cliente();
		cliente.setNup("00001312");
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

		Mockito.when(serviciosDeInversionBO.altaServicio(Matchers.any(String.class),
				Matchers.any(FormulariosAltaInicioInView.class))).thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager
				.altaServicio(new FormulariosAltaInicioInView());

		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ALTA_SERVICIOS_MAPS_ERROR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.ERROR_GENERICO.getDescripcion());
	}

	@Test
	public void descargaComprobanteBajaAdhesionTest() {

		FormularioControl formulario = new FormularioControl();

		Respuesta<Reporte> reporteRespuesta = new Respuesta<Reporte>();
		reporteRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Reporte reporte = new Reporte();
		reporte.setBytes("ejemploByteArray".getBytes());
		reporte.setNombre("nombre archivo alta adhesion");
		reporte.setTipoArchivo(TipoArchivoEnum.PDF);
		reporteRespuesta.setRespuesta(reporte);
		when(serviciosDeInversionBO.descargaComprobanteBajaAdhesion(Matchers.any(FormularioControl.class)))
				.thenReturn(reporteRespuesta);

		Respuesta<ReporteView> respuestaManager = serviciosDeInversionManager
				.descargaComprobanteBajaAdhesion(formulario);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void descargaComprobanteBajaAdhesionErrorTest() {

		FormularioControl formulario = new FormularioControl();
		formulario.setSegmento("bp");
		Respuesta<Reporte> reporteRespuesta = new Respuesta<Reporte>();
		reporteRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(serviciosDeInversionBO.descargaComprobanteBajaAdhesion(Matchers.any(FormularioControl.class)))
				.thenReturn(reporteRespuesta);

		Respuesta<ReporteView> respuestaManager = serviciosDeInversionManager
				.descargaComprobanteBajaAdhesion(formulario);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void descargaComprobanteAltaAdhesionErrorTest() {

		FormularioControl formulario = new FormularioControl();
		formulario.setSegmento("bp");
		Respuesta<Reporte> reporteRespuesta = new Respuesta<Reporte>();
		reporteRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(serviciosDeInversionBO.descargaComprobanteAltaAdhesion(Matchers.any(FormularioControl.class)))
				.thenReturn(reporteRespuesta);

		Respuesta<ReporteView> respuestaManager = serviciosDeInversionManager
				.descargaComprobanteAltaAdhesion(formulario);
		Assert.assertNotNull(respuestaManager);
		Assert.assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void grabarEstadisticaComprobanteBajaRTLTest() {
		BancaInversionesView view = new BancaInversionesView();
		view.setBanca("RTL");
		Respuesta<Void> respuesta = serviciosDeInversionManager.grabarEstadisticaComprobanteBaja(view);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void grabarEstadisticaComprobanteBajaBPTest() {
		BancaInversionesView view = new BancaInversionesView();
		view.setBanca("BP");
		Respuesta<Void> respuesta = serviciosDeInversionManager.grabarEstadisticaComprobanteBaja(view);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionErrorTest() {
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(FormularioControl.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaBO);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.bajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ERROR_BAJA_ADHESION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void bajaAdhesionOkSimulacionRTLTest() {
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setEstado("simulacion");
		fc.setSegmento("rtl");
		respuestaBO.setRespuesta(fc);
		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(FormularioControl.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaBO);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.bajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_SIMULACION_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionOkSimulacionBPTest() {
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setEstado("simulacion");
		fc.setSegmento("bp");
		respuestaBO.setRespuesta(fc);
		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(FormularioControl.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaBO);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.bajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_SIMULACION_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionOkConfirmacionRTLTest() {
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setEstado("confirmacion");
		fc.setSegmento("rtl");
		respuestaBO.setRespuesta(fc);
		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(FormularioControl.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.bajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_CONFIRMACION_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionOkConfirmacionBpTest() {
		Respuesta<FormularioControl> respuestaBO = new Respuesta<FormularioControl>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setEstado("confirmacion");
		fc.setSegmento("bp");
		respuestaBO.setRespuesta(fc);
		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(FormularioControl.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaBO);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FormularioControl> respuesta = serviciosDeInversionManager.bajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_CONFIRMACION_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaAdhesionOkBpTest() {
		Respuesta<GrillaConsultaAdhesionDTO> rta = new Respuesta<GrillaConsultaAdhesionDTO>();
		rta.setEstadoRespuesta(EstadoRespuesta.OK);
		when(serviciosDeInversionBO.consultaAdhesion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(rta);
		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("bp");
		Respuesta<GrillaConsultaAdhesionDTO> response = serviciosDeInversionManager.consultaAdhesion(inView);

		Assert.assertNotNull(response);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaAdhesionOkRtlTest() {
		Respuesta<GrillaConsultaAdhesionDTO> rta = new Respuesta<GrillaConsultaAdhesionDTO>();
		rta.setEstadoRespuesta(EstadoRespuesta.OK);
		when(serviciosDeInversionBO.consultaAdhesion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(rta);
		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("rtl");
		Respuesta<GrillaConsultaAdhesionDTO> response = serviciosDeInversionManager.consultaAdhesion(inView);

		Assert.assertNotNull(response);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaAdhesionErrorBpTest() {
		Respuesta<GrillaConsultaAdhesionDTO> rta = new Respuesta<GrillaConsultaAdhesionDTO>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(serviciosDeInversionBO.consultaAdhesion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(rta);
		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("bp");
		Respuesta<GrillaConsultaAdhesionDTO> response = serviciosDeInversionManager.consultaAdhesion(inView);

		Assert.assertNotNull(response);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_BPRIV,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void consultaAdhesionErrorRtlTest() {
		Respuesta<GrillaConsultaAdhesionDTO> rta = new Respuesta<GrillaConsultaAdhesionDTO>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(serviciosDeInversionBO.consultaAdhesion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(rta);
		BancaInversionesView inView = new BancaInversionesView();
		inView.setBanca("rtl");
		Respuesta<GrillaConsultaAdhesionDTO> response = serviciosDeInversionManager.consultaAdhesion(inView);

		Assert.assertNotNull(response);
		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void obtenerDetalleSuscripcion() {
		Respuesta<FormularioControl> response = new Respuesta<FormularioControl>();
		response.setEstadoRespuesta(EstadoRespuesta.OK);

		when(serviciosDeInversionBO.obtenerDetalleSuscripcion(Matchers.any(Cliente.class),
				Matchers.any(DetalleSuscripcionView.class))).thenReturn(response);

		Respuesta<FormularioControl> rta = serviciosDeInversionManager
				.obtenerDetalleSuscripcion(new DetalleSuscripcionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionDetalleError() {
		Respuesta<FormularioControl> response = new Respuesta<FormularioControl>();
		response.setEstadoRespuesta(EstadoRespuesta.ERROR);

		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(Cliente.class), Matchers.any(BajaAdhesionView.class)))
				.thenReturn(response);

		Respuesta<FormularioControl> rta = serviciosDeInversionManager.bajaAdhesion(new BajaAdhesionView());

		Mockito.verify(estadisticaManager).add(EstadisticasConstants.ERROR_BAJA_ADHESION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void bajaAdhesionDetalleRtlOk() {
		Respuesta<FormularioControl> response = new Respuesta<FormularioControl>();
		response.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setSegmento("rtl");
		response.setRespuesta(fc);

		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(Cliente.class), Matchers.any(BajaAdhesionView.class)))
				.thenReturn(response);

		Respuesta<FormularioControl> rta = serviciosDeInversionManager.bajaAdhesion(new BajaAdhesionView());

		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_SIMULACION_RTL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void bajaAdhesionDetalleBpOk() {
		Respuesta<FormularioControl> response = new Respuesta<FormularioControl>();
		response.setEstadoRespuesta(EstadoRespuesta.OK);
		FormularioControl fc = new FormularioControl();
		fc.setSegmento("bp");
		response.setRespuesta(fc);

		when(serviciosDeInversionBO.bajaAdhesion(Matchers.any(Cliente.class), Matchers.any(BajaAdhesionView.class)))
				.thenReturn(response);

		Respuesta<FormularioControl> rta = serviciosDeInversionManager.bajaAdhesion(new BajaAdhesionView());

		Mockito.verify(estadisticaManager).add(EstadisticasConstants.BAJA_ADHESION_SIMULACION_BP,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
}
