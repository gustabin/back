package ar.com.santanderrio.obp.servicios.login.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.bo.MyaSuscripcionesBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.login.manager.impl.MyaManagerImpl;
import ar.com.santanderrio.obp.servicios.login.view.DatosMyaView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioGeneralSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.InicioProductoSuscripcionMyAView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum;
import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;
import ar.com.santanderrio.obp.servicios.suscripciones.entities.MensajeSuscripcion;

/**
 * The Class MyaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class MyaManagerTest {

	/** The mya manager. */
	@InjectMocks
	private MyaManagerImpl myaManager;

	/** The Respuesta factory. */
	@Spy
	private RespuestaFactory RespuestaFactory = new RespuestaFactory();

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;

	/** The mya BO. */
	@Mock
	private MyaBO myaBO;
	
	@Mock
	private MyaSuscripcionesBO myaSuscripcionesBO;
	
	@Mock
	private MensajeBO mensajeBO;

	/**
	 * Obtener estado mya test.
	 */
	@Test
	public void obtenerEstadoMyaTest() {

		Respuesta<CredencialesMya> respuestaCredencialesMya = new Respuesta<CredencialesMya>();
		CredencialesMya credencialesMya = new CredencialesMya();
		Cliente cliente = new Cliente();
		RegistroSesion registroSesion = new RegistroSesion();

		registroSesion.setClienteSinonimo(false);
		credencialesMya.setClienteEstado("SA");
		respuestaCredencialesMya.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaCredencialesMya.setRespuesta(credencialesMya);

		Mensaje mensajePrincipal = new Mensaje();
		mensajePrincipal.setMensaje("MENSAJE PRINCIPAL");
		
		Mensaje mensajeSecundario = new Mensaje();
		mensajeSecundario.setMensaje("MENSAJE SECUNDARIO");
		
		when(myaBO.obtenerEstadoMya(Matchers.any(Cliente.class), Matchers.anyBoolean()))
				.thenReturn(respuestaCredencialesMya);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PRINCIPAL_ACTUALIZACION_DATOS)).thenReturn(mensajePrincipal);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_SECUNDARIO_ACTUALIZACION_DATOS)).thenReturn(mensajeSecundario);
		
		respuestaCredencialesMya = myaManager.obtenerEstadoMya(cliente);

		assertNotNull(respuestaCredencialesMya);
		assertEquals(respuestaCredencialesMya.getEstadoRespuesta(), EstadoRespuesta.WARNING);
		assertEquals(respuestaCredencialesMya.getItemsMensajeRespuesta().get(0).getTipoError(),
				TipoError.SUSCRIPCION_MYA.getDescripcion());

	}

	@Test
	public void confirmarDatosMyaAltaSATest() {
		DatosMyaView datosMyaView = new DatosMyaView();
		Cliente cliente = new Cliente();
		CredencialesMya credencialesMya = new CredencialesMya();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();

		credencialesMya.setAceptacionContrato((long) 1);
		credencialesMya.setClienteEstado("SA");
		datosMyaView.setCelular("1150501010");
		datosMyaView.setEmail("asd@asd.com.ar");
		datosMyaView.setCompania("PERSONAL");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(myaBO.updateDestinos(Matchers.any(CredencialesMyaIn.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaVoid);

		Respuesta<CredencialesMya> respuesta = myaManager.confirmarDatosMya(datosMyaView);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void confirmarDatosMyaAltaNSTest() {
		DatosMyaView datosMyaView = new DatosMyaView();
		Cliente cliente = new Cliente();
		CredencialesMya credencialesMya = new CredencialesMya();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();

		credencialesMya.setAceptacionContrato((long) 1);
		credencialesMya.setClienteEstado("NS");
		datosMyaView.setCelular("1150501010");
		datosMyaView.setEmail("asd@asd.com.ar");
		datosMyaView.setCompania("PERSONAL");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(myaBO.registrarClienteMya(Matchers.any(Cliente.class), Matchers.any(CredencialesMyaIn.class)))
				.thenReturn(respuestaVoid);

		Respuesta<CredencialesMya> respuesta = myaManager.confirmarDatosMya(datosMyaView);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void confirmarDatosMyaAltaSNTest() {
		DatosMyaView datosMyaView = new DatosMyaView();
		Cliente cliente = new Cliente();
		CredencialesMya credencialesMya = new CredencialesMya();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();
		Respuesta<Void> respuestaEstadoMya = new Respuesta<Void>();

		credencialesMya.setAceptacionContrato((long) 1);
		credencialesMya.setClienteEstado("SN");
		datosMyaView.setCelular("1150501010");
		datosMyaView.setEmail("asd@asd.com.ar");
		datosMyaView.setCompania("PERSONAL");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaEstadoMya.setEstadoRespuesta(EstadoRespuesta.OK);

		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(myaBO.updateDestinos(Matchers.any(CredencialesMyaIn.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaVoid);
		when(myaBO.actualizarEstadoMya(Matchers.any(Cliente.class))).thenReturn(respuestaEstadoMya);
		Respuesta<CredencialesMya> respuesta = myaManager.confirmarDatosMya(datosMyaView);
		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void confirmarDatosMyaModificacionTest() {
		DatosMyaView datosMyaView = new DatosMyaView();
		Cliente cliente = new Cliente();
		CredencialesMya credencialesMya = new CredencialesMya();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();

		credencialesMya.setAceptacionContrato((long) 1);
		credencialesMya.setClienteEstado("SA");
		datosMyaView.setCodigoArea("11");
		datosMyaView.setCelular("50501010");
		datosMyaView.setEmail("asd@asd.com.ar");
		datosMyaView.setCompania("PERSONAL");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		credencialesMya.setCelular("5151111");
		credencialesMya.setCompaniaSeleccionada("MOVISTAR");
		credencialesMya.setEmail("asd@asd.com");
		credencialesMya.setCodigoArea("011");
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(myaBO.updateDestinos(Matchers.any(CredencialesMyaIn.class), Matchers.any(Cliente.class)))
				.thenReturn(respuestaVoid);

		Respuesta<CredencialesMya> respuesta = myaManager.confirmarDatosMya(datosMyaView);

		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void obtenerInicioMensajesAvisosTest() {

		CredencialesMya credencialesMya = new CredencialesMya();
		credencialesMya.setEmail("pepe@tutopia.com");
		credencialesMya.setEmailSecundario("pipo@aol.com");
		
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta.setCodigoTitularidad("TI");
		Cliente cliente = new Cliente();
		List<Cuenta> cuentas = new ArrayList<Cuenta>(Arrays.asList(cuenta));
		cliente.setCuentas(cuentas);

		when(sesionParametros.getCredencialesMya()).thenReturn(credencialesMya);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		
		Respuesta<InicioGeneralSuscripcionMyAView> respuesta = myaManager.obtenerInicioMensajesAvisos();
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(respuesta.getRespuesta().getProductos().size(), 4);
		assertEquals(respuesta.getRespuesta().getEmail1(), "pep*@tutopia.com");
		assertEquals(respuesta.getRespuesta().getEmail2(), "pip*@aol.com");
	}

	@Test
	public void obtenerInicioProductoMyATest() {
		when(sesionCliente.getCliente()).thenReturn(new Cliente());
		when(sesionParametros.getCredencialesMya()).thenReturn(new CredencialesMya());

		SuscripcionesDTO suscripcionesDTO = crearSuscripcionesDTO();
		Respuesta<SuscripcionesDTO> respuestaBO = new Respuesta<SuscripcionesDTO>();
		respuestaBO.setRespuesta(suscripcionesDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(myaSuscripcionesBO.obtenerMensajesSuscripciones(Matchers.any(Cliente.class), Matchers.any(CredencialesMya.class),
				Matchers.any(ProductoMyAEnum.class))).thenReturn(respuestaBO);

		Respuesta<InicioProductoSuscripcionMyAView> respuesta = myaManager.obtenerInicioProductoMyA("Cuentas");
		assertNotNull(respuesta);
		assertNotNull(respuesta.getRespuesta());
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		assertEquals("Alertas de cuentas", respuesta.getRespuesta().getCabecera());
		assertEquals(2, respuesta.getRespuesta().getOpcionesAlertas().size());
		assertEquals("Exceso de descubierto", respuesta.getRespuesta().getOpcionesAlertas().get(0).getLabel());
		assertEquals(true, respuesta.getRespuesta().getOpcionesAlertas().get(0).getActiva());
		assertEquals(true, respuesta.getRespuesta().getOpcionesAlertas().get(0).getConfiguracion().getEmail1());
		assertEquals("Mensaje prueba 2", respuesta.getRespuesta().getOpcionesAlertas().get(1).getConfiguracion().getTextoCabecera());
	}

	private SuscripcionesDTO crearSuscripcionesDTO() {
		SuscripcionesDTO suscripcionesDTO = new SuscripcionesDTO();
		suscripcionesDTO.setTituloSuscripcion("Alertas de cuentas");

		MensajeSuscripcion mensaje1 = new MensajeSuscripcion();
		mensaje1.setLabel("Exceso de descubierto");
		mensaje1.setActiva(true);
		mensaje1.setMailUnoActivo(true);
		mensaje1.setMailDosActivo(false);
		mensaje1.setMensaje("Mensaje prueba 1");
		mensaje1.setFrecuencia("Avisar cuando hay novedades");

		MensajeSuscripcion mensaje2 = new MensajeSuscripcion();
		mensaje2.setLabel("Cheque depositado rechazado");
		mensaje2.setActiva(false);
		mensaje2.setMailUnoActivo(false);
		mensaje2.setMailDosActivo(false);
		mensaje2.setMensaje("Mensaje prueba 2");
		mensaje2.setFrecuencia("Avisar cuando hay novedades");

		List<MensajeSuscripcion> mensajes = new ArrayList<MensajeSuscripcion>(Arrays.asList(mensaje1, mensaje2));
		suscripcionesDTO.setMensajes(mensajes);
		return suscripcionesDTO;
	}

}
