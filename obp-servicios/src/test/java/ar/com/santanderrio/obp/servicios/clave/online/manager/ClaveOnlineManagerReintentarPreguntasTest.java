package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.MetodoAutenticacionView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineManagerReintentarPreguntasTest {
	
	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

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
	
	@Test
	public void reintentarPreguntasTest(){
		
		VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
    	verificacionPasosClaveOnline.setValidarPreguntasSeguridad(true);
		
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		CredencialesClaveOnline credencialesClaveOnline = new CredencialesClaveOnline();
		PreguntasSeguridad preguntasSeguridad = new PreguntasSeguridad();
		PreguntasSeguridad preguntasSeguridad2 = new PreguntasSeguridad();
		Respuesta<PreguntasSeguridad> respuestaPreguntasSeguridad = new Respuesta<PreguntasSeguridad>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5);
		Date fechaExpirada = calendar.getTime();
		
		preguntasSeguridad2.setCantidadPreguntas(0);
		preguntasSeguridad2.setIndicePregunta(1);
		respuestaPreguntasSeguridad.setRespuesta(preguntasSeguridad2);
		respuestaPreguntasSeguridad.setEstadoRespuesta(EstadoRespuesta.OK);
		credencialesClaveOnline.setFechaDeNacimiento("20061988");
		
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaExpirada);
		when(claveOnlineBO.obtenerPreguntasSeguridad(Matchers.any(IdentificadorClienteInEntity.class))).thenReturn(respuestaPreguntasSeguridad);
		when(sesionParametros.getPreguntasSeguridad()).thenReturn(preguntasSeguridad, preguntasSeguridad2);
		when(sesionParametros.getCredencialesClaveOnline()).thenReturn(credencialesClaveOnline);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<MetodoAutenticacionView> respuesta = claveOnlineManager.reintentarPreguntas(request);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
		assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.PREGUNTAS.getDescripcion());
	}
}
