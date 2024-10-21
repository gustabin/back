package ar.com.santanderrio.obp.servicios.solicitudes.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.solicitudes.manager.SolicitudesManager;
import ar.com.santanderrio.obp.servicios.solicitudes.sei.impl.SolicitudesSEIImpl;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.manager.TrackingTarjetasManager;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.solicitudes.sei.SolicitudesSEIIT.SolicitudesSEITestConfiguration.class })
public class SolicitudesSEIIT extends AbstractSEITest {

    @Autowired
    private SolicitudesManager solicitudesManager;
    
    @Autowired
    private TrackingTarjetasManager trackingTarjetasManager;
    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("solicitudesSEI");
    }
    
    @Configuration
    public static class SolicitudesSEITestConfiguration {
        
        @Bean(name = "solicitudesSEI")
        public SolicitudesSEI solicitudesSEI() {
            return new SolicitudesSEIImpl();
        }
        
        @Bean
        public SolicitudesManager solicitudesManager() {
            return Mockito.mock(SolicitudesManager.class);
        }
                
        @Bean
        public TrackingTarjetasManager trackingTarjetasManager() {
            return Mockito.mock(TrackingTarjetasManager.class);
        }
        
    }
   
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCuentasYPaquetes() {
        
        //When
        when(solicitudesManager.obtenerCuentasYPaquetes())
                .thenReturn(respuestaFactory.crearRespuestaOk(SolicitudesView.class, new SolicitudesView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/solicitudes/obtenerCuentasYPaquetes");
        
        //Then
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void trackingTarjetas() {
        
        //When
        when(trackingTarjetasManager.obtenerTrackingTarjetas())
                .thenReturn(respuestaFactory.crearRespuestaOk(TrackingTarjetasView.class, new TrackingTarjetasView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/solicitudes/obtenerTrackingTarjetas");
        
        //Then
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    

    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerTarjetas() {
        
        //When
        when(solicitudesManager.obtenerTarjetas())
                .thenReturn(respuestaFactory.crearRespuestaOk(SolicitudesView.class, new SolicitudesView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/solicitudes/obtenerTarjetas");
        
        //Then
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    

}
