package ar.com.santanderrio.obp.servicios.turnosweb.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.turnosweb.manager.TurnosWebManager;
import ar.com.santanderrio.obp.servicios.turnosweb.sei.impl.TurnosWebSEIImpl;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;

/**
 * The Class TurnosWebSEIIT.
 * 
 * @author ITResources
 */
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class, 
		classes = {ar.com.santanderrio.obp.servicios.turnosweb.sei.TurnosWebSEIIT.TurnosWebSEITestConfiguration.class }
		)
public class TurnosWebSEIIT extends AbstractSEITest{

	/** The turnos web manager. */
    @Autowired
    private TurnosWebManager turnosWebManager;
    
    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
		
	
    @Configuration
    public static class  TurnosWebSEITestConfiguration {
        
        @Bean(name = "turnosWebSEI")
        public TurnosWebSEI turnosWebSEI() {
            return new TurnosWebSEIImpl();
        }
        
        @Bean
        public TurnosWebManager turnosWebManager() {
            return Mockito.mock(TurnosWebManager.class);
        }
        
    }


	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("turnosWebSEI");
	}
	
    @SuppressWarnings("unchecked")
    @Test
    public void consultaCita() {
        when(turnosWebManager.consultaCita(Matchers.anyBoolean())).thenReturn(respuestaFactory.crearRespuestaOk(new CitaOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/consultaCita");
        Respuesta<Void> respuesta = client.post(Boolean.FALSE, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void consultaHorarioDisponibles() {
    	when(turnosWebManager.consultaHorariosDisponibles(Matchers.any(HorariosDisponiblesInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new HorariosDisponiblesOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/consultaHorarioDisponibles");
        Respuesta<Void> respuesta = client.post(new HorariosDisponiblesInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    
    @SuppressWarnings("unchecked")
    @Test
    public void consultaSucursales() {
        when(turnosWebManager.consultaSucursales()).thenReturn(respuestaFactory.crearRespuestaOk(new SucursalesOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/consultaSucursales");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void altaModificacionCita() {
    	when(turnosWebManager.altaModificacionCita(Matchers.any(AltaModificacionCitaInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new AltaModificacionCitaOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/altaModificacionCita");
        Respuesta<Void> respuesta = client.post(new AltaModificacionCitaInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void bajaTurno() {
    	when(turnosWebManager.bajaTurno(Matchers.any(BajaTurnoInView.class))).thenReturn(new Respuesta<Void>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/bajaTurno");
        Respuesta<Void> respuesta = client.post(new BajaTurnoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
    }
    
    @SuppressWarnings("unchecked")  
    @Test
    public void descargarComprobantePDF() {
    	when(turnosWebManager.descargaComprobanteTurnoPDF(Matchers.any(ComprobanteTurnoInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new ReporteView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/turnosWeb/descargarComprobantePDF");
        Respuesta<Void> respuesta = client.post(new ComprobanteTurnoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }    
    

}
