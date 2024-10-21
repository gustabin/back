package ar.com.santanderrio.obp.servicios.clave.online.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clave.online.bo.ClaveOnlineBO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.VerificacionPasosClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.manager.impl.ClaveOnlineManagerImpl;
import ar.com.santanderrio.obp.servicios.clave.online.view.AltaClaveOnlineView;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineManagerAltaSGIClaveTest {
	
	/** The claveOnline manager. */
	@InjectMocks
	private ClaveOnlineManagerImpl claveOnlineManager;

	/** The Respuesta factory. */
	@Spy
	private RespuestaFactory RespuestaFactory = new RespuestaFactory();
	
	@Mock
	private ClaveOnlineBO claveOnlineBO;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Mock
	private SesionParametros sesionParametros;
	
	@Test
	public void altaSGIClaveTest(){
    	VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
		CredencialCliente credencialCliente = new CredencialCliente();
		Respuesta<Void> repuestaVoid = new Respuesta<Void>();
		RegistroSesion registroSesion = new RegistroSesion();
		MessageContext mc = Mockito.mock(MessageContext.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		repuestaVoid.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 3000);
		Date fechaSesion = calendar.getTime();
		when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
		when(mc.getHttpServletRequest()).thenReturn(request);
		when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
		when(claveOnlineBO.altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class))).thenReturn(repuestaVoid);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);

		Respuesta<AltaClaveOnlineView> respuesta = claveOnlineManager.altaSGIClave(credencialCliente, mc);
		
		assertNotNull(respuesta);
		assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}


    @Test
    public void altaSGIClaveErrorTest(){
    	VerificacionPasosClaveOnline verificacionPasosClaveOnline=new VerificacionPasosClaveOnline();
        CredencialCliente credencialCliente = new CredencialCliente();
        Respuesta<Void> repuestaVoid = new Respuesta<Void>();
        RegistroSesion registroSesion = new RegistroSesion();
        MessageContext mc = Mockito.mock(MessageContext.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(mc.getHttpServletRequest()).thenReturn(request);
        repuestaVoid.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 3000);
        Date fechaSesion = calendar.getTime();
        when(sesionParametros.getTimerClaveOnline()).thenReturn(fechaSesion);
        when(this.sesionParametros.getVerificacionPasosClaveOnline()).thenReturn(verificacionPasosClaveOnline);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(claveOnlineBO.altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class))).thenReturn(repuestaVoid);
        Respuesta<AltaClaveOnlineView> respuesta = claveOnlineManager.altaSGIClave(credencialCliente, mc);
        
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

}
