package ar.com.santanderrio.obp.servicios.inversiones.maps.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.ReportesMapsDAO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.InicioServiciosDeInversionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ConsultaAdhesionControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaCompuestaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.InputTextControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ListaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ServicioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ItemGenericoMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items.ServicioMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ServicioMapsException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;

@RunWith(MockitoJUnitRunner.class)
public class ServiciosDeInversionBOTest {

	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Mock
	private MensajeBO mensajeBO;

	/** The MapServiceDAO. */
	@Mock
	private MapServiceDAO mapServiceDAO;

	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private ReportesMapsDAO reportesMapsDAO;

	@InjectMocks
	private ServiciosDeInversionBOImpl serviciosDeInversionBO;

	@Test
	public void inicioServiciosDeInversion() {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Respuesta<InicioServiciosDeInversionDTO> respuestaInicioServiciosDeInversion = serviciosDeInversionBO
				.inicioServiciosDeInversion(cliente);
		Assert.assertNotNull(respuestaInicioServiciosDeInversion);
		Assert.assertEquals(respuestaInicioServiciosDeInversion.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void inicioServiciosDeInversionRTL() {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasRetail(cuentas);
		Respuesta<InicioServiciosDeInversionDTO> respuestaInicioServiciosDeInversion = serviciosDeInversionBO
				.inicioServiciosDeInversion(cliente);
		Assert.assertNotNull(respuestaInicioServiciosDeInversion);
		Assert.assertEquals(respuestaInicioServiciosDeInversion.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void obtenerControlesDisponiblesErrorDAO() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenThrow(new DAOException(""));
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void obtenerControlesDisponiblesEstadoConsultaError() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("No consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void obtenerControlesDisponiblesOk() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		formularioControl.setItems(new ArrayList<ControlMaps>());
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		ServicioControl control = new ServicioControl();
		ListaControl<ServicioMaps> servicioMaps = new ListaControl<ServicioMaps>();
		servicioMaps.setId("id");
		servicioMaps.setNombre("nombre");
		List<ServicioMaps> items = new ArrayList<ServicioMaps>();
		servicioMaps.setItems(items);
		ServicioMaps item = new ServicioMaps();
		item.setCantidadAdhesiones("2");
		item.setSeleccionado(false);
		item.setFooter("Footer");
		item.setIcono("Icono");
		item.setTipoServicio("tipo servicio");
		item.setTitulo("titulo");
		item.setValor("Valor");
		item.setPosicion(2);
		item.setDesc("desc");
		items.add(item);
		servicioMaps.getItems().add(item);
		ServicioMaps item2 = new ServicioMaps();
		item2.setDesc("desc");
		item2.setSeleccionado(false);
		item2.setCantidadAdhesiones("4");
		item2.setFooter("Footer");
		item2.setIcono("Icono");
		item2.setValor("Valor");
		item2.setTipoServicio("tipo servicio");
		item2.setTitulo("titulo");
		item2.setPosicion(2);
		items.add(item2);
		control.setItems(items);
		control.setNombre("nombre");
		control.setId("id");
		control.setTipoDataValor("string");

		formularioControl.setId("id");
		formularioControl.setNombre("nombre");
		formularioControl.setNup("nup");
		formularioControl.setSegmento("segmento");
		formularioControl.setCanal("canal");
		formularioControl.setSubCanal("subCanal");
		formularioControl.setEstado("consulta");
		formularioControl.setIdSimulacion("idSimulacion");
		formularioControl.setComprobante("comprobante");

		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();
		itemsControlMaps.add(control);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertEquals(respuesta.getRespuesta().cantidadAdhesiones(), new Integer(8));
	}

	@Test
	public void obtenerControlesDisponiblesError() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void obtenerControlesDisponiblesValidationError() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl formularioControl = new FormularioControl();
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);

		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();
		itemsControlMaps.add(formularioControl);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void obtenerControlesDisponiblesControlMapValidationException() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl formularioControl = new FormularioControl();
		ServicioControl servicioControl = new ServicioControl();

		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);
		formularioControl.setId("id");
		formularioControl.setNombre("nombre");
		formularioControl.setNup("nup");
		formularioControl.setSegmento("segmento");
		formularioControl.setCanal("canal");
		formularioControl.setSubCanal("subCanal");
		formularioControl.setEstado("consulta");
		formularioControl.setIdSimulacion("idSimulacion");
		formularioControl.setComprobante("comprobante");
		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();

		servicioControl.setId("id");
		servicioControl.setNombre("nombre");

		itemsControlMaps.add(servicioControl);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void obtenerControlesDisponiblesSinDatos() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl formularioControl = new FormularioControl();
		ServicioControl servicioControl = new ServicioControl();

		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);
		formularioControl.setId("id");
		formularioControl.setNombre("nombre");
		formularioControl.setNup("nup");
		formularioControl.setSegmento("segmento");
		formularioControl.setCanal("canal");
		formularioControl.setSubCanal("subCanal");
		formularioControl.setEstado("consulta");
		formularioControl.setIdSimulacion("idSimulacion");
		formularioControl.setComprobante("comprobante");
		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();

		servicioControl.setId("id");
		servicioControl.setNombre("nombre");
		servicioControl.setTipoDataValor("tipoDataValor");

		List<ServicioMaps> items = new ArrayList<ServicioMaps>();
		servicioControl.setItems(items);

		itemsControlMaps.add(servicioControl);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerControlesDisponibles(cliente, "RTL");
		Assert.assertNotNull(respuesta);

		ServicioControl sservicioControl = (ServicioControl) respuesta.getRespuesta().getItems().get(0);
		Assert.assertTrue(sservicioControl.getItems().isEmpty());
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void altaServicioOk() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		formularioControl.setItems(new ArrayList<ControlMaps>());
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		ServicioControl control = new ServicioControl();
		ListaControl<ServicioMaps> servicioMaps = new ListaControl<ServicioMaps>();
		servicioMaps.setId("id");
		servicioMaps.setNombre("nombre");
		List<ServicioMaps> items = new ArrayList<ServicioMaps>();
		servicioMaps.setItems(items);
		ServicioMaps item = new ServicioMaps();
		item.setCantidadAdhesiones("2");
		item.setSeleccionado(false);
		item.setFooter("Footer");
		item.setIcono("Icono");
		item.setTipoServicio("tipo servicio");
		item.setTitulo("titulo");
		item.setValor("Valor");
		item.setPosicion(2);
		item.setDesc("desc");
		items.add(item);
		servicioMaps.getItems().add(item);
		ServicioMaps item2 = new ServicioMaps();
		item2.setDesc("desc");
		item2.setSeleccionado(false);
		item2.setCantidadAdhesiones("4");
		item2.setFooter("Footer");
		item2.setIcono("Icono");
		item2.setValor("Valor");
		item2.setTipoServicio("tipo servicio");
		item2.setTitulo("titulo");
		item2.setPosicion(2);
		items.add(item2);
		control.setItems(items);
		control.setNombre("nombre");
		control.setId("id");
		control.setTipoDataValor("string");

		formularioControl.setId("id");
		formularioControl.setNombre("nombre");
		formularioControl.setNup("nup");
		formularioControl.setSegmento("segmento");
		formularioControl.setCanal("canal");
		formularioControl.setSubCanal("subCanal");
		formularioControl.setEstado("consulta");
		formularioControl.setIdSimulacion("idSimulacion");
		formularioControl.setComprobante("comprobante");

		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();
		itemsControlMaps.add(control);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicio("00001312",
				new FormulariosAltaInicioInView());
		ResumenCliente resumenCliente = new ResumenCliente();
		Mockito.when( sesionCliente.getCliente()).thenReturn(new Cliente());
		Mockito.when( sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
		Respuesta<FormularioControl> respuestaFlujo = serviciosDeInversionBO.altaServicioFlujo("00001312",
				respuesta.getRespuesta());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertEquals(respuesta.getRespuesta().cantidadAdhesiones(), new Integer(8));
		Assert.assertNotNull(respuestaFlujo);
		Assert.assertEquals(respuestaFlujo.getEstadoRespuesta(), EstadoRespuesta.OK);
		Assert.assertEquals(respuestaFlujo.getRespuesta().cantidadAdhesiones(), new Integer(8));
	}

	@Test
	public void altaServicioError() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		respuestaAltaAdhesion.setDatos(formularioControl);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenThrow(new DAOException());

		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicio("00001312",
				new FormulariosAltaInicioInView());
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void altaServicioControlMapValidationException() throws DAOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		formularioControl.setItems(new ArrayList<ControlMaps>());
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");
		ServicioControl control = new ServicioControl();
		ListaControl<ServicioMaps> servicioMaps = new ListaControl<ServicioMaps>();
		servicioMaps.setId("id");
		servicioMaps.setNombre("nombre");
		List<ServicioMaps> items = new ArrayList<ServicioMaps>();
		servicioMaps.setItems(items);
		ServicioMaps item = new ServicioMaps();
		item.setCantidadAdhesiones("2");
		item.setSeleccionado(false);
		item.setFooter("Footer");
		item.setIcono("Icono");
		item.setTipoServicio("tipo servicio");
		item.setTitulo("titulo");
		item.setValor("Valor");
		item.setPosicion(2);
		item.setDesc("desc");
		items.add(item);
		servicioMaps.getItems().add(item);
		ServicioMaps item2 = new ServicioMaps();
		item2.setDesc("desc");
		item2.setSeleccionado(false);
		item2.setCantidadAdhesiones("4");
		item2.setFooter("Footer");
		item2.setIcono("Icono");
		item2.setValor("Valor");
		item2.setTipoServicio("tipo servicio");
		item2.setTitulo("titulo");
		item2.setPosicion(2);
		items.add(item2);
		control.setItems(items);
		control.setNombre("nombre");
		control.setId("id");
		control.setTipoDataValor("string");

		List<ControlMaps> itemsControlMaps = new ArrayList<ControlMaps>();
		itemsControlMaps.add(control);
		formularioControl.setItems(itemsControlMaps);

		respuestaAltaAdhesion.setDatos(formularioControl);
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicio("00001312",
				new FormulariosAltaInicioInView());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

	@Test
	public void altaServicioPadreIdsOk() throws DAOException, JsonParseException, JsonMappingException, IOException {

		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuentas.add(cuenta);
		cliente.setCuentasPrivadas(cuentas);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		String formMock = getMock();

		AltaAdhesionMapsResponse response = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			response = mapper.readValue(formMock, AltaAdhesionMapsResponse.class);
		} catch (Exception e) {
			System.out.println(e);
		}

		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(response);
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicio("00001312",
				new FormulariosAltaInicioInView());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaAdhesionOk() throws DAOException {
		ConsultaAdhesionMapsResponse respuesta = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		respuesta.setDatos(fc);
		List<ControlMaps> list = new ArrayList<ControlMaps>();
		ConsultaAdhesionControl ca = new ConsultaAdhesionControl();
		ca.setNombre("nombre");
		ca.setId("id");
		ca.setBloqueado(true);
		ca.setActivas(new ArrayList<FormularioControl>());
		ca.setInactivas(new ArrayList<FormularioControl>());
		FormularioControl fc2 = new FormularioControl();
		fc2.setItems(new ArrayList<ControlMaps>());
		fc2.setEstado("consulta");
		fc2.setBloqueado(true);
		fc2.setIdAdhesion(123L);
		InputTextControl inputText = new InputTextControl();
		inputText.setMinLength(1);
		inputText.setMaxLength(10);
		inputText.setId("id");
		inputText.setNombre("alias");
		inputText.setValor("valor");
		fc2.getItems().add(inputText);
		InputTextControl inputText2 = new InputTextControl();
		inputText2.setMinLength(1);
		inputText2.setMaxLength(10);
		inputText2.setId("id");
		inputText2.setNombre("descripcion-dinamica");
		inputText2.setValor("valor");
		fc2.getItems().add(inputText2);
		InputTextControl inputText3= new InputTextControl();
		inputText3.setMinLength(1);
		inputText3.setMaxLength(10);
		inputText3.setId("id");
		inputText3.setNombre("estado-adhesion");
		inputText3.setValor("valor");
		fc2.getItems().add(inputText3);
		FechaCompuestaControl fechaCompuesta = new FechaCompuestaControl();
		fechaCompuesta.setId("id");
		fechaCompuesta.setNombre("nombre");
		fechaCompuesta.setBloqueado(true);
		fechaCompuesta.setItems(new ArrayList<ControlMaps>());
		FechaControl fecha1 = new FechaControl();
		fecha1.setId("id");
		fecha1.setNombre("fecha-desde");
		fecha1.setFechaMax("2019-07-31");
		fecha1.setFechaMin("2019-01-01");
		fecha1.setValor("2019-01-01");
		FechaControl fecha2 = new FechaControl();
		fecha2.setId("id");
		fecha2.setNombre("fecha-hasta");
		fecha2.setFechaMax("2019-07-31");
		fecha2.setFechaMin("2019-01-01");
		fecha2.setValor("2019-08-31");
		fechaCompuesta.getItems().add(fecha1);
		fechaCompuesta.getItems().add(fecha2);
		ListaControl<ItemGenericoMaps> lista = new ListaControl<ItemGenericoMaps>();
		lista.setId("id");
		lista.setNombre("nombre");
		lista.setTipoDataValor("tipodatavalor");
		lista.setItems(new ArrayList<ItemGenericoMaps>());
		ItemGenericoMaps item = new ItemGenericoMaps();
		item.setDesc("desc");
		item.setSeleccionado(true);
		item.setValor("valor");
		lista.getItems().add(item);
		fechaCompuesta.getItems().add(lista);
		fc2.getItems().add(fechaCompuesta);
		ca.getActivas().add(fc2);
		ca.getInactivas().add(fc2);
		ca.setBloqueado(true);
		list.add(ca);
		fc.setItems(list);
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
				.thenReturn(respuesta);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<GrillaConsultaAdhesionDTO> rta = serviciosDeInversionBO.consultaAdhesion(new Cliente(), "rtl");
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaAdhesionDAOException() throws DAOException {
		ConsultaAdhesionMapsResponse respuesta = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		respuesta.setDatos(fc);
		List<ControlMaps> list = new ArrayList<ControlMaps>();
		ConsultaAdhesionControl ca = new ConsultaAdhesionControl();
		ca.setNombre("nombre");
		ca.setId("id");
		ca.setBloqueado(true);
		ca.setActivas(new ArrayList<FormularioControl>());
		ca.setInactivas(new ArrayList<FormularioControl>());
		FormularioControl fc2 = new FormularioControl();
		fc2.setItems(new ArrayList<ControlMaps>());
		fc2.setEstado("consulta");
		fc2.setBloqueado(true);
		fc2.setIdAdhesion(123L);
		ca.getActivas().add(fc2);
		ca.setBloqueado(true);
		list.add(ca);
		fc.setItems(list);
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
				.thenThrow(new DAOException());
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<GrillaConsultaAdhesionDTO> rta = serviciosDeInversionBO.consultaAdhesion(new Cliente(), "rtl");
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void consultaAdhesionControlMapValidationException() throws DAOException {
		ConsultaAdhesionMapsResponse respuesta = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		respuesta.setDatos(fc);
		List<ControlMaps> list = new ArrayList<ControlMaps>();
		ConsultaAdhesionControl ca = new ConsultaAdhesionControl();
		ca.setNombre("nombre");
		ca.setId("id");
		ca.setBloqueado(true);
		ca.setActivas(new ArrayList<FormularioControl>());
		ca.setInactivas(new ArrayList<FormularioControl>());
		FormularioControl fc2 = new FormularioControl();
		fc2.setItems(new ArrayList<ControlMaps>());
		fc2.setEstado("consulta");
		fc2.setBloqueado(false);
		fc2.setIdAdhesion(123L);
		ca.getActivas().add(fc2);
		ca.setBloqueado(true);
		list.add(ca);
		fc.setItems(list);
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
				.thenReturn(respuesta);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<GrillaConsultaAdhesionDTO> rta = serviciosDeInversionBO.consultaAdhesion(new Cliente(), "rtl");
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void bajaAdhesionOk() throws DAOException {
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		response.setCodigo(0);
		Mockito.when(mapServiceDAO.bajaAdhesionMaps(Matchers.any(BajaAdhesionRequestEntity.class)))
				.thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		response.setDatos(fc);

		List<ControlMaps> list = new ArrayList<ControlMaps>();
		response.getDatos().setItems(list);

		Respuesta<FormularioControl> rta = serviciosDeInversionBO.bajaAdhesion(new Cliente(), new BajaAdhesionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void bajaAdhesionWarning() throws DAOException {
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		response.setCodigo(1);
		Mockito.when(mapServiceDAO.bajaAdhesionMaps(Matchers.any(BajaAdhesionRequestEntity.class)))
				.thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FormularioControl> rta = serviciosDeInversionBO.bajaAdhesion(new Cliente(), new BajaAdhesionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
	}

	@Test
	public void bajaAdhesionError() throws DAOException {
		Mockito.when(mapServiceDAO.bajaAdhesionMaps(Matchers.any(BajaAdhesionRequestEntity.class)))
				.thenThrow(new DAOException(""));
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<FormularioControl> rta = serviciosDeInversionBO.bajaAdhesion(new Cliente(), new BajaAdhesionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void bajaAdhesionControlMapValidationException() throws DAOException {
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		response.setCodigo(0);
		Mockito.when(mapServiceDAO.bajaAdhesionMaps(Matchers.any(BajaAdhesionRequestEntity.class)))
				.thenReturn(response);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		response.setDatos(fc);

		List<ControlMaps> list = new ArrayList<ControlMaps>();
		InputTextControl inputText = new InputTextControl();
		list.add(inputText);
		response.getDatos().setItems(list);

		Respuesta<FormularioControl> rta = serviciosDeInversionBO.bajaAdhesion(new Cliente(), new BajaAdhesionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void bajaAdhesionDAOException() throws DAOException {
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		response.setCodigo(0);
		Mockito.when(mapServiceDAO.bajaAdhesionMaps(Matchers.any(BajaAdhesionRequestEntity.class)))
				.thenThrow(new DAOException());
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		response.setDatos(fc);

		List<ControlMaps> list = new ArrayList<ControlMaps>();
		InputTextControl inputText = new InputTextControl();
		list.add(inputText);
		response.getDatos().setItems(list);

		Respuesta<FormularioControl> rta = serviciosDeInversionBO.bajaAdhesion(new Cliente(), new BajaAdhesionView());
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void descargaComprobanteAltaOk() throws DAOException {
		Respuesta<Reporte> respuesta = serviciosDeInversionBO.descargaComprobanteAltaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void descargaComprobanteBajaOk() throws DAOException {
		Respuesta<Reporte> respuesta = serviciosDeInversionBO.descargaComprobanteBajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void descargaComprobanteAltaDAOException() throws DAOException {
		Mockito.when(reportesMapsDAO.descargaComprobanteAltaAdhesion(Matchers.any(FormularioControl.class)))
				.thenThrow(new DAOException());

		Respuesta<Reporte> respuesta = serviciosDeInversionBO.descargaComprobanteAltaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void descargaComprobanteBajaDAOException() throws DAOException {
		Mockito.when(reportesMapsDAO.descargaComprobanteBajaAdhesion(Matchers.any(FormularioControl.class)))
				.thenThrow(new DAOException());

		Respuesta<Reporte> respuesta = serviciosDeInversionBO.descargaComprobanteBajaAdhesion(new FormularioControl());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void AltaServicioFlujoServicioMapsException() throws DAOException {
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenThrow(new ServicioMapsException());
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicioFlujo("00001312",
				new FormularioControl());
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void AltaServicioFlujoDAOException() throws DAOException {
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenThrow(new DAOException());
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicioFlujo("00001312",
				new FormularioControl());
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void altaServicioFlujoErrorAltaAdhesion() throws DAOException {

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		FormularioControl formularioControl = new FormularioControl();
		formularioControl.setItems(new ArrayList<ControlMaps>());
		AltaAdhesionMapsResponse respuestaAltaAdhesion = new AltaAdhesionMapsResponse();
		formularioControl.setEstado("consulta");

		formularioControl.setId("id");
		formularioControl.setNombre("nombre");
		formularioControl.setNup("nup");
		formularioControl.setSegmento("segmento");
		formularioControl.setCanal("canal");
		formularioControl.setSubCanal("subCanal");
		formularioControl.setEstado("confirmacion");
		formularioControl.setIdSimulacion("idSimulacion");
		formularioControl.setComprobante("comprobante");
		formularioControl.setError(1);

		respuestaAltaAdhesion.setDatos(formularioControl);
		
		Mockito.when(mapServiceDAO.altaAdhesionMaps(Matchers.any(AltaAdhesionMapsRequestEntity.class)))
				.thenReturn(respuestaAltaAdhesion);
		
        ResumenCliente resumenCliente = new ResumenCliente();
        Mockito.when( sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when( sesionCliente.getResumenCliente()).thenReturn(resumenCliente);
        
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.altaServicioFlujo("00001312",
				new FormularioControl());

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_ALTA_ADHESION_MAPS.getDescripcion());
	}
	
	@Test
	public void obtenerDetalleSuscripcionActivasOk() throws DAOException {
		ConsultaAdhesionMapsResponse respuesta = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		respuesta.setDatos(fc);
		List<ControlMaps> list = new ArrayList<ControlMaps>();
		ConsultaAdhesionControl ca = new ConsultaAdhesionControl();
		ca.setNombre("nombre");
		ca.setId("id");
		ca.setBloqueado(true);
		ca.setActivas(new ArrayList<FormularioControl>());
		ca.setInactivas(new ArrayList<FormularioControl>());
		FormularioControl fc2 = new FormularioControl();
		fc2.setItems(new ArrayList<ControlMaps>());
		fc2.setEstado("consulta");
		fc2.setBloqueado(true);
		fc2.setIdAdhesion(1L);
		InputTextControl inputText = new InputTextControl();
		inputText.setMinLength(1);
		inputText.setMaxLength(10);
		inputText.setId("id");
		inputText.setNombre("alias");
		inputText.setValor("valor");
		fc2.getItems().add(inputText);
		InputTextControl inputText2 = new InputTextControl();
		inputText2.setMinLength(1);
		inputText2.setMaxLength(10);
		inputText2.setId("id");
		inputText2.setNombre("descripcion-dinamica");
		inputText2.setValor("valor");
		fc2.getItems().add(inputText2);
		InputTextControl inputText3= new InputTextControl();
		inputText3.setMinLength(1);
		inputText3.setMaxLength(10);
		inputText3.setId("id");
		inputText3.setNombre("estado-adhesion");
		inputText3.setValor("valor");
		fc2.getItems().add(inputText3);
		FechaCompuestaControl fechaCompuesta = new FechaCompuestaControl();
		fechaCompuesta.setId("id");
		fechaCompuesta.setNombre("nombre");
		fechaCompuesta.setBloqueado(true);
		fechaCompuesta.setItems(new ArrayList<ControlMaps>());
		FechaControl fecha1 = new FechaControl();
		fecha1.setId("id");
		fecha1.setNombre("fecha-desde");
		fecha1.setFechaMax("2019-07-31");
		fecha1.setFechaMin("2019-01-01");
		fecha1.setValor("2019-01-01");
		FechaControl fecha2 = new FechaControl();
		fecha2.setId("id");
		fecha2.setNombre("fecha-hasta");
		fecha2.setFechaMax("2019-07-31");
		fecha2.setFechaMin("2019-01-01");
		fecha2.setValor("2019-08-31");
		fechaCompuesta.getItems().add(fecha1);
		fechaCompuesta.getItems().add(fecha2);
		ListaControl<ItemGenericoMaps> lista = new ListaControl<ItemGenericoMaps>();
		lista.setId("id");
		lista.setNombre("nombre");
		lista.setTipoDataValor("tipodatavalor");
		lista.setItems(new ArrayList<ItemGenericoMaps>());
		ItemGenericoMaps item = new ItemGenericoMaps();
		item.setDesc("desc");
		item.setSeleccionado(true);
		item.setValor("valor");
		lista.getItems().add(item);
		fechaCompuesta.getItems().add(lista);
		fc2.getItems().add(fechaCompuesta);
		ca.getActivas().add(fc2);
		ca.getInactivas().add(fc2);
		ca.setBloqueado(true);
		list.add(ca);
		fc.setItems(list);
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
				.thenReturn(respuesta);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		DetalleSuscripcionView detalle = new DetalleSuscripcionView();
		detalle.setBanca("rtl");
		detalle.setIdServicio(1L);
		Respuesta<FormularioControl> rta = serviciosDeInversionBO.obtenerDetalleSuscripcion(new Cliente(), detalle);
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void obtenerDetalleSuscripcionInactivasOk() throws DAOException {
		ConsultaAdhesionMapsResponse respuesta = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		fc.setEstado("consulta");
		fc.setBloqueado(true);
		respuesta.setDatos(fc);
		List<ControlMaps> list = new ArrayList<ControlMaps>();
		ConsultaAdhesionControl ca = new ConsultaAdhesionControl();
		ca.setNombre("nombre");
		ca.setId("id");
		ca.setBloqueado(true);
		ca.setActivas(new ArrayList<FormularioControl>());
		ca.setInactivas(new ArrayList<FormularioControl>());
		FormularioControl fc2 = new FormularioControl();
		fc2.setItems(new ArrayList<ControlMaps>());
		fc2.setEstado("consulta");
		fc2.setBloqueado(true);
		fc2.setIdAdhesion(1L);
		InputTextControl inputText = new InputTextControl();
		inputText.setMinLength(1);
		inputText.setMaxLength(10);
		inputText.setId("id");
		inputText.setNombre("alias");
		inputText.setValor("valor");
		fc2.getItems().add(inputText);
		InputTextControl inputText2 = new InputTextControl();
		inputText2.setMinLength(1);
		inputText2.setMaxLength(10);
		inputText2.setId("id");
		inputText2.setNombre("descripcion-dinamica");
		inputText2.setValor("valor");
		fc2.getItems().add(inputText2);
		InputTextControl inputText3= new InputTextControl();
		inputText3.setMinLength(1);
		inputText3.setMaxLength(10);
		inputText3.setId("id");
		inputText3.setNombre("estado-adhesion");
		inputText3.setValor("valor");
		fc2.getItems().add(inputText3);
		FechaCompuestaControl fechaCompuesta = new FechaCompuestaControl();
		fechaCompuesta.setId("id");
		fechaCompuesta.setNombre("nombre");
		fechaCompuesta.setBloqueado(true);
		fechaCompuesta.setItems(new ArrayList<ControlMaps>());
		FechaControl fecha1 = new FechaControl();
		fecha1.setId("id");
		fecha1.setNombre("fecha-desde");
		fecha1.setFechaMax("2019-07-31");
		fecha1.setFechaMin("2019-01-01");
		fecha1.setValor("2019-01-01");
		FechaControl fecha2 = new FechaControl();
		fecha2.setId("id");
		fecha2.setNombre("fecha-hasta");
		fecha2.setFechaMax("2019-07-31");
		fecha2.setFechaMin("2019-01-01");
		fecha2.setValor("2019-08-31");
		fechaCompuesta.getItems().add(fecha1);
		fechaCompuesta.getItems().add(fecha2);
		ListaControl<ItemGenericoMaps> lista = new ListaControl<ItemGenericoMaps>();
		lista.setId("id");
		lista.setNombre("nombre");
		lista.setTipoDataValor("tipodatavalor");
		lista.setItems(new ArrayList<ItemGenericoMaps>());
		ItemGenericoMaps item = new ItemGenericoMaps();
		item.setDesc("desc");
		item.setSeleccionado(true);
		item.setValor("valor");
		lista.getItems().add(item);
		fechaCompuesta.getItems().add(lista);
		fc2.getItems().add(fechaCompuesta);
		ca.getInactivas().add(fc2);
		ca.setBloqueado(true);
		list.add(ca);
		fc.setItems(list);
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
				.thenReturn(respuesta);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		DetalleSuscripcionView detalle = new DetalleSuscripcionView();
		detalle.setBanca("rtl");
		detalle.setIdServicio(1L);
		Respuesta<FormularioControl> rta = serviciosDeInversionBO.obtenerDetalleSuscripcion(new Cliente(), detalle);
		Assert.assertNotNull(rta);
		Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void obtenerDetalleSuscripcionDAOException() throws DAOException {
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
		.thenThrow(new DAOException());
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerDetalleSuscripcion(new Cliente(), new DetalleSuscripcionView());
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_GENERICO.getDescripcion());
	}
	
	@Test
	public void obtenerDetalleSuscripcionControlMapValidationException() throws DAOException {
		ConsultaAdhesionMapsResponse response = new ConsultaAdhesionMapsResponse();
		FormularioControl fc = new FormularioControl();
		response.setDatos(fc);
		
		Mockito.when(mapServiceDAO.consultaAdhesionMaps(Matchers.any(ConsultaAdhesionRequestEntity.class)))
		.thenReturn(response);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("mensaje prueba");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<FormularioControl> respuesta = serviciosDeInversionBO.obtenerDetalleSuscripcion(new Cliente(), new DetalleSuscripcionView());
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		Assert.assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_GENERICO.getDescripcion());
	}
	
	private String getMock() {
//	    String formMock = "{     \"Datos\":{        \"Id\":\"SAF2-CASO-BASE-0001\",      \"Implementa\":null,      \"Etiqueta\":\"Etiqueta\",      \"Nombre\":\"SAF-Home\",      \"Tipo\":\"formulario\",      \"Ayuda\":\"Ayuda\",      \"Requerido\":true,      \"Bloqueado\":false,      \"Posicion\":1,      \"Error\":0,      \"Error_desc\":\"hubo errores\",      \"Error_tecnico\":\"hubo errores\",      \"Validado\":false,      \"Información\":\"texto de ayuda alternativo debajo del control\",      \"IdServicio\":\"SAF\",      \"IdSimulacion\":\"0234\",      \"Comprobante\":\"34234\",      \"Estado\":\"carga\",      \"FormAnterior\":null,      \"IdAdhesion\":null,      \"Titulo\":\"Servicios de inversion\",      \"Nup\":\"00001312\",      \"Segmento\":\"RTL\",      \"Items\":[           {              \"Id\":\"lista-monedas\",            \"Implementa\":\"lista\",            \"Etiqueta\":\"Servicio de Inversiones lista-monedas\",            \"Nombre\":\"lista-monedas\",            \"Tipo\":\"moneda\",            \"Ayuda\":\"Listado de monedas disponibles\",            \"Requerido\":true,            \"Bloqueado\":false,            \"Error\":0,            \"Error_desc\":\"Hay errores\",            \"Error_tecnico\":\"Hay errores\",            \"Validado\":false,            \"Informacion\":\"Seleccione la moneda a operar se add text de prueba limite caracteres\",            \"PadreId\":null,            \"TipoDataValor\":\"\",            \"Items\":[                 {                    \"Valor\":\"USD\",                  \"Desc\":\"Dolares\",                  \"ValorPadre\":null,                  \"Seleccionado\":true,                  \"TipoCambio\":38.99,                  \"CodigoIso\":\"USD\"               },               {                    \"Valor\":\"ARS\",                  \"Desc\":\"Pesos\",                  \"ValorPadre\":null,                  \"Seleccionado\":false,                  \"TipoCambio\":1.0,                  \"CodigoIso\":\"ARS\"               },   {                    \"Valor\":\"EUR\",                  \"Desc\":\"Euros\",                  \"ValorPadre\":null,                  \"Seleccionado\":false,                  \"TipoCambio\":0.99,                  \"CodigoIso\":\"EUR\"               }            ]         },    {  \"Tipo\":\"cuenta-operativa\",  \"Id\":\"cuenta-operativa-1\",  \"Nombre\":\"cuenta-operativa\",  \"Implementa\":\"lista\",  \"Etiqueta\":\"Lista de cuentas operativas\",  \"Ayuda\":\"Seleccione una cuenta operativa\",  \"Requerido\":false,  \"Bloqueado\":false,  \"Posicion\":2,  \"Error\":0,  \"Error_desc\":\"Hay errores\",  \"Error_tecnico\":\"Hay errores\",  \"Validado\":false,  \"Informacion\":\"Seleccione su cuenta operativa\",  \"Config\":\"lista-cuenta-operativa-pdc\",  \"PadreId\":\"lista-monedas\",  \"TipoDataValor\":\"string\",  \"Items\":[{        \"Valor\":\"000003560951\",        \"Desc\":\"Cuenta corriente en $\",        \"ValorPadre\":\"ARS\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"00\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0166\",        \"NumeroCtaOperativa\":\"000003560951\",        \"CodigoMoneda\":\"ARS\",        \"SaldoCta\":90.99,        \"Titulares\":[           \"Apellido1 Apellido2, Nombre1 Nombre2\"        ]     },{        \"Valor\":\"000003560953\",        \"Desc\":\"Caja de ahorro en $\",        \"ValorPadre\":\"ARS\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"01\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0006\",        \"NumeroCtaOperativa\":\"000003560953\",        \"CodigoMoneda\":\"ARS\",        \"SaldoCta\":0.99,        \"Titulares\":[           \"Apellido1 Apellido2, Nombre1 Nombre2\"        ]     },{        \"Valor\":\"000003560955\",        \"Desc\":\"Cuenta corriente en u$s\",        \"ValorPadre\":\"USD\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"03\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0506\",        \"NumeroCtaOperativa\":\"000003560955\",        \"CodigoMoneda\":\"USD\",        \"SaldoCta\":19990.0,        \"Titulares\":[           \"Apellido1 Apellido2, Nombre1 Nombre2\"        ]     },{        \"Valor\":\"000003560956\",        \"Desc\":\"Caja de ahorro en u$s\",        \"ValorPadre\":\"USD\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"04\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0066\",        \"NumeroCtaOperativa\":\"000003560956\",        \"CodigoMoneda\":\"USD\",        \"SaldoCta\":19990.0,        \"Titulares\":[           \"Apellido1 Apellido2, Nombre1 Nombre2\"        ]     },     {        \"Valor\":\"000003560954\",        \"Desc\":\"Cuenta única $\",        \"ValorPadre\":\"ARS\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"09\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0066\",        \"NumeroCtaOperativa\":\"000003560954\",        \"CodigoMoneda\":\"ARS\",        \"SaldoCta\":19990.0,        \"Titulares\":[           \"Sanchez Ferrer, Amelio Ramiro\"        ]     },     {        \"Valor\":\"000003560954\",        \"Desc\":\"Cuenta única USD\",        \"ValorPadre\":\"USD\",        \"Seleccionado\":false,        \"TipoCtaOperativa\":\"10\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0066\",        \"NumeroCtaOperativa\":\"000003560954\",        \"CodigoMoneda\":\"USD\",        \"SaldoCta\":28880.0,        \"Titulares\":[           \"Sanchez Ferrer, Amelio Ramiro\"        ]     }, {        \"Valor\":\"000003560952\",        \"Desc\":\"Cuenta única USD\",        \"ValorPadre\":\"USD\",        \"Seleccionado\":true,        \"TipoCtaOperativa\":\"10\",        \"Producto\":\"07\",        \"SubProducto\":\"0001\",        \"SucursalCtaOperativa\":\"0066\",        \"NumeroCtaOperativa\":\"000003560952\",        \"CodigoMoneda\":\"USD\",        \"SaldoCta\":999990.0,        \"Titulares\":[           \"Apellido1 Apellido2, Nombre1 Nombre2\"        ]     }  ]},         {              \"Id\":\"lista-cuentas-Titulos\",            \"Implementa\":\"lista\",            \"Etiqueta\":\"Seleccione la cuenta titulos\",            \"Nombre\":\"lista-cuentas\",            \"Tipo\":\"cuenta-titulos\",            \"Ayuda\":\"El listado de cuentas titulos pueden ser tanto en pesos como dolares\",            \"Requerido\":false,            \"Bloqueado\":false,            \"Error\":0,            \"Error_desc\":\"hay errores\",            \"Error_tecnico\":\"hay errores\",            \"Validado\":false,            \"Informacion\":\"Seleccione la cuenta titulos\",            \"PadreId\":\"lista-monedas\",            \"TipoDataValor\":\"String\",            \"Items\":[                 {                    \"Valor\":\"181025/9\",                  \"Desc\":\"Cuenta Ti­tulos numero 181025/9\",                  \"ValorPadre\":\"ARS\",                  \"Seleccionado\":false,                  \"Titulares\":[                       \"Sanchez Ferrer, Amelio Ramiro\",                     \"Santamaria De Soleto, Pedro Enon\"                    ],                  \"NumeroCtaTitulo\":\"1810259\"               },               {                    \"Valor\":\"181982/9\",                  \"Desc\":\"Cuenta Titulos numero 181982/9\",                  \"ValorPadre\":\"USD\",                  \"Seleccionado\":false,                  \"Titulares\":[                       \"Sanchez Ferrero, Wanda Clara\",                  \"Sanchez Ferrer, Amelio Ramiro\",\"Apellido1 Apellido2, Nombre1 Nombre2\",                     \"Santamaria De Soleto, Pedro Enon\"],                  \"NumeroCtaTitulo\":\"1819829\"               },   {                    \"Valor\":\"35869/5\",                  \"Desc\":\"Cuenta Titulos numero 35869/5\",                  \"ValorPadre\":\"USD\",                  \"Seleccionado\":false,                  \"Titulares\":[                       \"Fuentes, Jesus\"                  ],                  \"NumeroCtaTitulo\":\"358695\"               },   {                    \"Valor\":\"35869/4\",                  \"Desc\":\"Cuenta Titulos numero 35869/4\",                  \"ValorPadre\":\"ARS\",                  \"Seleccionado\":false,                  \"Titulares\":[                       \"Fuentes, Jesus\"                  ],                  \"NumeroCtaTitulo\":\"358694\"               },   {                    \"Valor\":\"35869/3\",                  \"Desc\":\"Cuenta Titulos numero 35869/3\",                  \"ValorPadre\":\"USD\",                  \"Seleccionado\":false,                  \"Titulares\":[                       \"Fuentes, Jesus\"                  ],                  \"NumeroCtaTitulo\":\"358693\"               }            ]         }         ],      \"Canal\":\"04\",      \"SubCanal\":\"99\",      \"PerfilInversor\":\"Moderado\"   },   \"Codigo\":0,   \"Mensaje\":\"Mensaje Error\",   \"MensajeTecnico\":\"Mensaje Error Tecnico\"}";
		return "{ \"Datos\":{\"Tipo\": \"formulario\",\"Id\": \"SAF2-CASO-BASE-0001\",\"Nombre\": \"SAF-Home\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta\",\"Ayuda\": \"Ayuda\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"hubo errores\",\"Error_tecnico\": \"hubo errores\",\"Validado\": false,\"Informacion\": null,\"Config\": null,\"IdServicio\": \"SAF\",\"IdSimulacion\": \"0234\",\"Comprobante\": \"34234\",\"Estado\": \"carga\",\"FormAnterior\": null,\"IdAdhesion\": null,\"Titulo\": \"Titulo del Forms de Carga\",\"Nup\": \"00001312\",\"Segmento\": \"BP\",\"Canal\": \"04\",\"SubCanal\": \"99\",\"PerfilInversor\": \"Moderado\",\"Items\": [{\"Tipo\": \"input-number\",\"Id\": \"saldo-min-1-saf-001-tbanco-CP-2-21\",\"Nombre\": \"saldo-min-1-saf-001-tbanco-CP-2\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta Input Number 1\",\"Ayuda\": \"Ayuda Prueba Tbanco 2 Texto extendido para llegar tres lin\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": null,\"Error_tecnico\": null,\"Validado\": false,\"Informacion\": \"Informaci?n Prueba Tbanco 2 Texto extendido para llegar tres lin\",\"Config\": \"saldo-min-1-saf-001-tbanco-CP-2\",\"Simbolo\": \"EUR\",\"Valor\": null,\"Incremento\": 0.12345,\"MinValor\": 2,\"MaxValor\": 100},{\"MaxValor\": 99.99,\"MinValor\": 2,\"Valor\": null,\"Incremento\": 1.1234,\"Simbolo\": \"EUR\",\"Id\": \"saldo-min-1-saf-001-tbanco-CP-2-22\",\"Etiqueta\": \"Etiqueta Input Number\",\"Nombre\": \"saldo-min-1-saf-001-tbanco-CP-2\",\"Tipo\": \"input-number\",\"Ayuda\": \"Tooltip de ayuda\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": 1.0,\"Validado\": false,\"Informacion\": \"Información Prueba Tbanco 2\",\"Error\": 0.0,\"Config\": \"saldo-min-1-saf-001-tbanco-CP-2\"},{\"MaxValor\": 10000.9999,\"MinValor\": 0.99,\"Valor\": null,\"Incremento\": 1.12,\"Simbolo\": \"ARS\",\"Id\": \"saldo-min-1-saf-001-tbanco-CP-2-33\",\"Etiqueta\": \"Etiqueta Input Number\",\"Nombre\": \"saldo-min-1-saf-001-tbanco-CP-2\",\"Tipo\": \"input-number\",\"Ayuda\": \"Tooltip de ayuda\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": 1.0,\"Validado\": false,\"Informacion\": \"Información Prueba Tbanco 2\",\"Error\": 0.0,\"Config\": \"saldo-min-1-saf-001-tbanco-CP-2\"},{\"Tipo\": \"input-number\",\"Id\": \"input-number 24\",\"Nombre\": \"input-number 2\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta Input Number 2\",\"Ayuda\": \"Ayuda Prueba Tbanco 2\",\"Requerido\": false,\"Bloqueado\": true,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"Texto de Error_desc \",\"Error_tecnico\": \"Texto de Error_tecnico \",\"Validado\": false,\"Informacion\": \"Informaci?n Prueba Tbanco 2\",\"Config\": \"saldo-min-1-saf-001-tbanco-CP-2\",\"Simbolo\": \" \",\"Valor\": 99,\"Incremento\": 3,\"MinValor\": 1,\"MaxValor\": 100},{\"Tipo\": \"input-text\",\"Id\": \"input-text-nombre\",\"Nombre\": \"input-text-nombre\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta puede tener hasta tres lineas completas\",\"Ayuda\": \"Ayuda Este será el nombre que tomará el fondo\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 2,\"Error\": 0,\"Error_desc\": \"Eror_desc se detectaron errores\",\"Error_tecnico\": \"Error_tecnico se detectaron errores\",\"Validado\": false,\"Informacion\": \"Informacion El nombre del fondo solo podrá contener caracteres alfabéticos y tener 3 lineas\",\"Config\": null,\"Valor\": \"Texto del servicio en campo Valor\",\"MinLength\": 4,\"MaxLength\": 50},{\"Tipo\": \"input-text\",\"Id\": \"input-mail 1\",\"Nombre\": \"e-mail\",\"Implementa\": null,\"Etiqueta\": \"email Personal\",\"Ayuda\": \"e-mails son necesarios para dar aviso a los destinatarios. Campo no obligatorio.\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": null,\"Error\": 0,\"Error_desc\": \"Error_desc se detectaron errores\",\"Error_tecnico\": \"Error_tecnico se detectaron errores\",\"Validado\": false,\"Informacion\": \"E-mail generado a partir del alta del fondo. e-mails son necesarios para dar aviso a los destinatarios. Campo no obligatorio.\",\"Config\": null,\"Valor\": \" , \",\"MinLength\": 0,\"MaxLength\": 12},{\"Tipo\": \"input-text\",\"Id\": \"input-text-apellido\",\"Nombre\": \"input-text-apellido\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta Ingrese apellido\",\"Ayuda\": \"Ayuda Este será el apellido que tomará el fondo\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": \"2\",\"Error\": 0,\"Error_desc\": \"Erroooooooooooooooor_desc  se detectaron errores apellido\",\"Error_tecnico\": \"Error_tecnico  se detectaron errores apellido\",\"Validado\": false,\"Informacion\": \"Informacion El apellido del fondo solo podrá contener caracteres alfabéticos\",\"Config\": null,\"Valor\": \"algonuevoqueponerparapasarmedelacotamaxdecaracteres en el input\",\"MinLength\": 0,\"MaxLength\": 50},{\"Tipo\": \"input-text\",\"Id\": \"input-mail-laboral\",\"Nombre\": \"e-mail laboral\",\"Implementa\": null,\"Etiqueta\": \"E-mail Laboral del destinatario. Longitud maxima 120 caracteres.\",\"Ayuda\": \"Los e-mails laboral son necesarios para dar aviso a los destinatarios\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": null,\"Error\": 0,\"Error_desc\": \"se detectaron errores laboral\",\"Error_tecnico\": \"se detectaron errores laboral\",\"Validado\": false,\"Informacion\": \"E-mail laboral generado a partir del alta del fondo\",\"Config\": null,\"Valor\": \"micorreolaboral1.pepito@laboral.com,micorreolaboral2.pepito@laboral.com, \",\"MinLength\": 0,\"MaxLength\": 50},{\"Id\": \"importe-compuesto-1\",\"Implementa\": null,\"Etiqueta\": \"Ingrese rango de precio\",\"Nombre\": \"Imp_compuesto\",\"Tipo\": \"importe-compuesto\",\"Ayuda\": \"Aqui podrá ingresar un rango del importe a solicitar\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"Datos inconsistentes\",\"Error_tecnico\": \"Se ingresaron datos no validados\",\"Validado\": true,\"Informacion\": \"Información de adhesiones\",\"Items\": [{\"Id\": \"input-number-1\",\"Implementa\": null,\"Etiqueta\": \"Mínimo a solicitar\",\"Nombre\": \"minimo\",\"Tipo\": \"input-number\",\"Ayuda\": \"Debe ingresar un precio mayor a 10\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"No hay errores\",\"Error_tecnico\": \"No hay errores\",\"Validado\": false,\"Informacion\": \"Aqui podrá visualizar como cargar los datos\",\"Valor\": null,\"MinValor\": 10,\"MaxValor\": 25,\"Incremento\": 1.15,\"Simbolo\": \"ARS\"},{\"Id\": \"input-number-2\",\"Implementa\": null,\"Etiqueta\": \"Máximo a solicitar\",\"Nombre\": \"maximo\",\"Tipo\": \"input-number\",\"Ayuda\": \"Debe ingresar un precio menor a 20\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 2,\"Error\": 0,\"Error_desc\": \"No se ha detectado error\",\"Error_tecnico\": \"No se ha detectado error\",\"Validado\": false,\"Informacion\": \"Aqui podrá visualizar como cargar los datos\",\"Valor\": null,\"MinValor\": 15,\"MaxValor\": 50,\"Incremento\": 1.15,\"Simbolo\": \"ARS\"}]},{\"Id\": \"importe-compuesto-bloq\",\"Implementa\": null,\"Etiqueta\": \"Ingrese rango de precio\",\"Nombre\": \"Imp_compuesto\",\"Tipo\": \"importe-compuesto\",\"Ayuda\": \"Aqui podrá ingresar un rango del importe a solicitar\",\"Requerido\": true,\"Bloqueado\": true,\"Posicion\": 1,\"Error\": 1,\"Error_desc\": \"Datos inconsistentes\",\"Error_tecnico\": \"Se ingresaron datos no validados\",\"Validado\": true,\"Informacion\": \"Información de adhesiones\",\"Items\": [{\"Id\": \"input-number-bloq-1\",\"Implementa\": null,\"Etiqueta\": \"Mínimo a solicitar\",\"Nombre\": \"minimo\",\"Tipo\": \"input-number\",\"Ayuda\": \"Debe ingresar un precio mayor a 10\",\"Requerido\": true,\"Bloqueado\": true,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"No hay errores\",\"Error_tecnico\": \"No hay errores\",\"Validado\": false,\"Informacion\": \"Aqui podrá visualizar como cargar los datos\",\"Valor\": 123.2412,\"MinValor\": 10,\"MaxValor\": 25,\"Incremento\": 1.15,\"Simbolo\": \"EUR\"},{\"Id\": \"input-number-bloq-2\",\"Implementa\": null,\"Etiqueta\": \"Máximo a solicitar\",\"Nombre\": \"maximo\",\"Tipo\": \"input-number\",\"Ayuda\": \"Debe ingresar un precio menor a 20\",\"Requerido\": true,\"Bloqueado\": true,\"Posicion\": 2,\"Error\": 0,\"Error_desc\": \"No se ha detectado error\",\"Error_tecnico\": \"No se ha detectado error\",\"Validado\": false,\"Informacion\": \"Aqui podrá visualizar como cargar los datos\",\"Valor\": 100,\"MinValor\": 15,\"MaxValor\": 50,\"Incremento\": 1.15,\"Simbolo\": \"VES\"}]},{\"Tipo\": \"lista\",\"Id\": \"ListaCiudad\",\"Nombre\": \"listadoCiudad\",\"Implementa\": null,\"Etiqueta\": \"Seleccione Ciudad\",\"Ayuda\": \"Indique su ciudad\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 1,\"Error_desc\": \"No se detectaron errores\",\"Error_tecnico\": \"No se detectaron errores\",\"Validado\": false,\"Informacion\": \"Indique su ciudad\",\"Config\": null,\"PadreId\": \"\",\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"0\",\"Desc\": \"CABA\",\"ValorPadre\": null,\"Seleccionado\": false},{\"Valor\": \"1\",\"Desc\": \"GBA\",\"ValorPadre\": null,\"Seleccionado\": false},{\"Valor\": \"2\",\"Desc\": \"Lorem ipsum dolor sit amet.\",\"ValorPadre\": null,\"Seleccionado\": false},{\"Valor\": \"3\",\"Desc\": \"moneda pesos argentinos\",\"ValorPadre\": null,\"Seleccionado\": false}]},{\"Tipo\": \"lista\",\"Id\": \"ListaLocalidad\",\"Nombre\": \"listado\",\"Implementa\": null,\"Etiqueta\": \"Etiqueta Seleccione localidad de la lista desplegable\",\"Ayuda\": \"Indique su localidad para poder establecer los parametros \",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"Error_desc se detectaron errores\",\"Error_tecnico\": \"Error_tecnico se detectaron errores\",\"Validado\": false,\"Informacion\": \"Informacion Adicional localidad sera tomada como prametro\",\"Config\": null,\"PadreId\": \"ListaCiudad\",\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"0\",\"Desc\": \"Lugano\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"1\",\"Desc\": \"Flores\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"2\",\"Desc\": \"Floresta\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"3\",\"Desc\": \"Palermo\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"4\",\"Desc\": \"Belgrano\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"5\",\"Desc\": \"Colegiales\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"6\",\"Desc\": \"Nuñez\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"7\",\"Desc\": \"Vicente Lopez\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"8\",\"Desc\": \"Beccar\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"9\",\"Desc\": \"Martinez\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"10\",\"Desc\": \"ValorPadreLLegoEnNull\",\"ValorPadre\": null,\"Seleccionado\": false},{\"Valor\": \"11\",\"Desc\": \"Consectur elis dolor solis.\",\"ValorPadre\": null,\"Seleccionado\": false}]},{\"Tipo\": \"lista\",\"Id\": \"ListaCalles\",\"Nombre\": \"listado\",\"Implementa\": null,\"Etiqueta\": \"Seleccione calle\",\"Ayuda\": \"Indique su calle\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"No se detectaron errores\",\"Error_tecnico\": \"No se detectaron errores\",\"Validado\": false,\"Informacion\": \"Indique su calle\",\"Config\": null,\"PadreId\": \"ListaLocalidad\",\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"0\",\"Desc\": \"LuganoCalle01\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"1\",\"Desc\": \"LuganoCalle02\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"2\",\"Desc\": \"FloresCalle01\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"3\",\"Desc\": \"FloresCalle02\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"4\",\"Desc\": \"VicenteLopezCalle01\",\"ValorPadre\": \"7\",\"Seleccionado\": false},{\"Valor\": \"5\",\"Desc\": \"VicenteLopezCalle02\",\"ValorPadre\": \"7\",\"Seleccionado\": false},{\"Valor\": \"8\",\"Desc\": \"BeccarCalle01\",\"ValorPadre\": \"8\",\"Seleccionado\": false},{\"Valor\": \"8\",\"Desc\": \"BeccarCalle02\",\"ValorPadre\": \"8\",\"Seleccionado\": true},{\"Valor\": \"9\",\"Desc\": \"ValorPadreNull\",\"ValorPadre\": null,\"Seleccionado\": false}]},{\"Tipo\": \"lista\",\"Id\": \"ListaCalles2\",\"Nombre\": \"listado\",\"Implementa\": null,\"Etiqueta\": \"Seleccione calle 2\",\"Ayuda\": \"Indique su calle 2\",\"Requerido\": true,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"No se detectaron errores\",\"Error_tecnico\": \"No se detectaron errores\",\"Validado\": false,\"Informacion\": \"Indique su calle\",\"Config\": null,\"PadreId\": \"ListaLocalidad\",\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"0\",\"Desc\": \"LuganoCalle03\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"1\",\"Desc\": \"LuganoCalle04\",\"ValorPadre\": \"0\",\"Seleccionado\": false},{\"Valor\": \"2\",\"Desc\": \"FloresCalle03\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"3\",\"Desc\": \"FloresCalle04\",\"ValorPadre\": \"1\",\"Seleccionado\": false},{\"Valor\": \"4\",\"Desc\": \"VicenteLopezCalle03\",\"ValorPadre\": \"7\",\"Seleccionado\": false},{\"Valor\": \"5\",\"Desc\": \"VicenteLopezCalle04\",\"ValorPadre\": \"7\",\"Seleccionado\": false},{\"Valor\": \"8\",\"Desc\": \"BeccarCalle03\",\"ValorPadre\": \"8\",\"Seleccionado\": false},{\"Valor\": \"8\",\"Desc\": \"BeccarCalle04\",\"ValorPadre\": \"8\",\"Seleccionado\": true},{\"Valor\": \"9\",\"Desc\": \"ValorPadreNull\",\"ValorPadre\": null,\"Seleccionado\": false}]},{\"Tipo\": \"lista\",\"Id\": \"ListaSinFamilia\",\"Nombre\": \"listadoCiudad\",\"Implementa\": null,\"Etiqueta\": \"Lista sin familia\",\"Ayuda\": \"Lista solitaria :'(\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": 1,\"Error\": 0,\"Error_desc\": \"Se detectaron errores\",\"Error_tecnico\": \"Se detectaron errores\",\"Validado\": false,\"Informacion\": \"Lista solitaria\",\"Config\": null,\"PadreId\": \"\",\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"0\",\"Desc\": \"Lista sin amigos\",\"ValorPadre\": \"1001\",\"Seleccionado\": true},{\"Valor\": \"1\",\"Desc\": \"Lista sin conocidos\",\"ValorPadre\": null,\"Seleccionado\": true},{\"Valor\": \"2\",\"Desc\": \":(\",\"ValorPadre\": \"1\",\"Seleccionado\": true}]},{\"FechaMin\": \"2015-01-15T00:00:00\",\"FechaMax\": \"2020-12-15T00:00:00\",\"Valor\": null,\"MaxLength\": 20,\"MinLength\": 1,\"Id\": \"fecha-2\",\"Etiqueta\": \"Fecha Alta\",\"Nombre\": \"fecha\",\"Tipo\": \"fecha\",\"Requerido\": false,\"Bloqueado\": false,\"Posicion\": 7,\"Validado\": false,\"Error\": 1,\"Error_desc\": \"error_desc\",\"Implementa\": \"input-text\",\"Config\": \"fecha\",\"Informacion\": \"Seleccione fecha para el alta\",\"Ayuda\": \"A partir de esta fecha el sistema no realizará mas suscripciones automáticas para esta activación\"},{\"Tipo\": \"legal\",\"Id\": \"legal-2\",\"Nombre\": \"legal\",\"Implementa\": null,\"Etiqueta\": null,\"Ayuda\": null,\"Requerido\": null,\"Bloqueado\": false,\"Posicion\": null,\"Error\": null,\"Error_desc\": null,\"Error_tecnico\": null,\"Validado\": null,\"Informacion\": null,\"Config\": null,\"PadreId\": null,\"TipoDataValor\": \"string\",\"Items\": [{\"Valor\": \"The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy veldt fox. Bright vixens jump; dozy fowl quack. Quick wafting zephyrs vex bold Jim. Quick zephyrs blow, vexing daft Jim.charged fop blew my junk TV quiz. How quickly daft jumping zebras vex.\",\"Desc\": \"Aviso Legal ====> legal y 2 checks\",\"ValorPadre\": null,\"Seleccionado\": false,\"Items\": [{\"TextoLink\": \"Consectur elis\",\"Etiqueta\": \"Contrato Servicio 1\",\"Posicion\": \"2\",\"Requerido\": false,\"Checked\": true},{\"TextoLink\": \"Lorem ipsum dolor sit amet\",\"Etiqueta\": \"Contrato Servicio 2\",\"Posicion\": \"3\",\"Requerido\": true,\"Checked\": false}]}]}]},   \"Codigo\":0,   \"Mensaje\":\"Mensaje Error\",   \"MensajeTecnico\":\"Mensaje Error Tecnico\"}";

	}

}
