package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ClaveOnlineManagerEnviarSMSTest {
	
	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

	@Mock
	private ClaveOnlineBO claveOnlineBO;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Spy
	private RespuestaFactory respuestaFactory;
	
	@Mock
	private MensajeManager mensajeManager;
	
	@Mock
	private LegalBO legalBO;
	
	@Test
	public void enviarSMSTest(){
		VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
    	verificacionPasosClaveOnline.setValidarAutenticacion(true);
		
		RegistroSesion registroSesion = new RegistroSesion();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 3000);
		Date fechaSesion = calendar.getTime();
		
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		datosAutenticacion.setNumero("12345678");
		datosAutenticacion.setCiclo(0);
		registroSesion.setDatosAutenticacion(datosAutenticacion);
		
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
		when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(claveOnlineBO.validarClave(Matchers.any(DatosAutenticacion.class))).thenReturn(respuestaVoid);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.enviarSMS(request, false,false);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		MetodoAutenticacionView metodoAutenticacionView = respuesta.getRespuesta();
		assertEquals("SMS", metodoAutenticacionView.getAccion());
		assertEquals("5678", metodoAutenticacionView.getMensaje());
	}
	
	@Test
	public void enviarWHATSAPPTest(){
		VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
    	verificacionPasosClaveOnline.setValidarAutenticacion(true);
		
		RegistroSesion registroSesion = new RegistroSesion();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 3000);
		Date fechaSesion = calendar.getTime();
		
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		datosAutenticacion.setNumero("12345678");
		datosAutenticacion.setCiclo(0);
		registroSesion.setDatosAutenticacion(datosAutenticacion);
		
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
		when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		when(claveOnlineBO.validarClave(Matchers.any(DatosAutenticacion.class))).thenReturn(respuestaVoid);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.enviarSMS(request, false,true);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		MetodoAutenticacionView metodoAutenticacionView = respuesta.getRespuesta();
		assertEquals("SMS", metodoAutenticacionView.getAccion());
		assertEquals("5678", metodoAutenticacionView.getMensaje());
	}
	
	@Test
	public void enviarSMSReenvioTest() throws DAOException{
		VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
    	verificacionPasosClaveOnline.setValidarAutenticacion(true);
    	
		RegistroSesion registroSesion = new RegistroSesion();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();
		Mensaje mensaje = new Mensaje();
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 3000);
		Date fechaSesion = calendar.getTime();
		
		mensaje.setMensaje("Mi Mensaje");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		datosAutenticacion.setNumero("12345678");
		datosAutenticacion.setCiclo(0);
		registroSesion.setDatosAutenticacion(datosAutenticacion);
		String legal = "legal";
		
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
		when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
		//when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(legal);
		when(claveOnlineBO.validarClave(Matchers.any(DatosAutenticacion.class))).thenReturn(respuestaVoid);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.enviarSMS(request, true,false);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		MetodoAutenticacionView metodoAutenticacionView = respuesta.getRespuesta();
		//assertEquals("Mi Mensaje", metodoAutenticacionView.getMensaje());
		assertEquals("legal", metodoAutenticacionView.getLegal());
	}
	
	@Test
	public void enviarSMSErrorTest() throws DAOException{
		VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
    	verificacionPasosClaveOnline.setValidarAutenticacion(true);
    	
		RegistroSesion registroSesion = new RegistroSesion();
		DatosAutenticacion datosAutenticacion = new DatosAutenticacion();
		Respuesta<Void> respuestaVoid = new Respuesta<Void>();
		Mensaje mensaje = new Mensaje();
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 3000);
		Date fechaSesion = calendar.getTime();
		
		mensaje.setMensaje("Mi Mensaje");
		respuestaVoid.setEstadoRespuesta(EstadoRespuesta.ERROR);
		datosAutenticacion.setNumero("12345678");
		datosAutenticacion.setCiclo(0);
		registroSesion.setDatosAutenticacion(datosAutenticacion);
		String legal = "legal";
		
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
		when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
//		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn(legal);
		when(claveOnlineBO.validarClave(Matchers.any(DatosAutenticacion.class))).thenReturn(respuestaVoid);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.enviarSMS(request, true,false);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		MetodoAutenticacionView metodoAutenticacionView = respuesta.getRespuesta();
//		assertEquals("Mi Mensaje", metodoAutenticacionView.getMensaje());
		assertEquals("legal", metodoAutenticacionView.getLegal());
	}
}