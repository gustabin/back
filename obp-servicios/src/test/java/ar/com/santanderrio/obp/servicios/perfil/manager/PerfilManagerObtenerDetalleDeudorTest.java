package ar.com.santanderrio.obp.servicios.perfil.manager;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.manager.impl.PerfilManagerImpl;
import ar.com.santanderrio.obp.servicios.perfil.view.ConsultaPerfil;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilDetalleDeudorView;

@RunWith(MockitoJUnitRunner.class)
public class PerfilManagerObtenerDetalleDeudorTest {
	
	/** The manager. */
	@InjectMocks
	private PerfilManager perfilManager = new PerfilManagerImpl();
	
	@Spy
	private RespuestaFactory respuestaFactory;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Mock
	private MensajeManager mensajeManager;
	
	@Test
	@Ignore
	public void obtenerDetalleDeudorTest(){
		ConsultaPerfil consultaPerfil = new ConsultaPerfil();
		Mensaje mensaje = new Mensaje();
		
		consultaPerfil.setCodigoDeudor("3");
		mensaje.setMensaje("");
	    
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		Respuesta<PerfilDetalleDeudorView> respuesta = perfilManager.obtenerDetalleDeudor(consultaPerfil);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		
	}
	
}
