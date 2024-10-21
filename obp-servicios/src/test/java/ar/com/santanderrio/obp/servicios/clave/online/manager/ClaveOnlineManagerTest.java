package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clave.online.view.HashView;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.impl.ClienteManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ClaveOnlineManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineManagerTest {

	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

	/** The cliente manager. */
	@Mock
	private ClienteManagerImpl clienteManager;

	/** The Respuesta factory. */
	@Spy
	private RespuestaFactory RespuestaFactory = new RespuestaFactory();

	@Mock
	private MensajeManager mensajeManager;

	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private ClaveOnlineBO claveOnlineBO;

	/**
	 * Obtener inicio claveOnline test.
	 */
	@Test
	public void grabarEstadisticaSolucionarTest() {
		Mensaje mensaje = new Mensaje();

		mensaje.setMensaje("MensajeOK");

		when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		Respuesta<Mensaje> respuesta = claveOnlineManager.grabarEstadisticaSolucionar();

		assertNotNull(respuesta);
		assertEquals(respuesta.getRespuesta().getMensaje(), "MensajeOK");
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
	@Test
	public void identificarClienteTest(){
		
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession value = Mockito.mock(HttpSession.class);
		
		when(request.getSession()).thenReturn(value);
		
		Respuesta<Void> respuesta = claveOnlineManager.identificarCliente(credencialesClaveOnline, request);
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}
	
	@Test
	public void altaSGIClaveTest(){
		MessageContext messageContext = Mockito.mock(MessageContext.class);
    	VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession value = Mockito.mock(HttpSession.class);
		RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
		Respuesta<Void> respuestaClaveOnlineBO = new Respuesta<Void>();
		respuestaClaveOnlineBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		CredencialCliente credencialCliente = new CredencialCliente();
		
		when(request.getSession()).thenReturn(value);
		when(messageContext.getHttpServletRequest()).thenReturn(request);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		when(sesionParametros.getTimerClaveOnline()).thenReturn(calendar.getTime());
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(claveOnlineBO.altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class))).thenReturn(respuestaClaveOnlineBO);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<AltaClaveOnlineView> respuesta = claveOnlineManager.altaSGIClave(credencialCliente, messageContext);
		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(),EstadoRespuesta.OK);
	}
	
	
	@Test
	public void altaSGIClaveTestERROR(){
		MessageContext messageContext = Mockito.mock(MessageContext.class);
    	VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession value = Mockito.mock(HttpSession.class);
		RegistroSesion registroSesion = Mockito.mock(RegistroSesion.class);
		Respuesta<Void> respuestaClaveOnlineBO = new Respuesta<Void>();
		respuestaClaveOnlineBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		CredencialCliente credencialCliente = new CredencialCliente();
		
		when(request.getSession()).thenReturn(value);
		when(messageContext.getHttpServletRequest()).thenReturn(request);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		when(sesionParametros.getTimerClaveOnline()).thenReturn(calendar.getTime());
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(claveOnlineBO.altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class))).thenReturn(respuestaClaveOnlineBO);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		
		Respuesta<AltaClaveOnlineView> respuesta = claveOnlineManager.altaSGIClave(credencialCliente, messageContext);
		assertNotNull(respuesta);
		assertEquals(respuesta.getEstadoRespuesta(),EstadoRespuesta.ERROR);
	}
	
	

}
