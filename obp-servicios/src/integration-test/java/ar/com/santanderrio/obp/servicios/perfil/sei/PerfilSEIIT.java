package ar.com.santanderrio.obp.servicios.perfil.sei;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClaveUsuarioManager;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.PerfilManager;
import ar.com.santanderrio.obp.servicios.perfil.manager.PreguntasSeguridadManager;
import ar.com.santanderrio.obp.servicios.perfil.sei.PerfilSEIIT.PerfilSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.perfil.sei.impl.PerfilSEIImpl;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PerfilView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PerfilSEITestConfiguration.class })

public class PerfilSEIIT extends AbstractSEITest{
    
    @Autowired
    private PerfilManager perfilManager;
    
    
    /** The agenda cambioDomicilioManager . */
    @Autowired
    private CambioDomicilioManager cambioDomicilioManager;
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * The Class AgendaTransferenciaSEITestConfiguration.
     */
    @Configuration
    public static class PerfilSEITestConfiguration {

        /**
         * Agenda transferencia manager.
         *
         * @return the agenda transferencia manager
         */
        @Bean(name = "perfilManager")
        public PerfilManager perfilManager() {
            return Mockito.mock(PerfilManager.class);
        }

        /**
         * Login SEI.
         *
         * @return the agenda transferencia SEI
         */
        @Bean(name = "perfilSEI")
        public PerfilSEI perfilSEI() {
            return new PerfilSEIImpl();
        }
        
        @Bean
        public ClienteManager clienteManager() {
            return Mockito.mock(ClienteManager.class);
        }
        
        @Bean
        public ClaveUsuarioManager claveUsuarioManager() {
            return Mockito.mock(ClaveUsuarioManager.class);
        }
        
        @Bean
        public CambioDomicilioManager cambioDomicilioManager() {
            return Mockito.mock(CambioDomicilioManager.class);
        }
        
        @Bean
        public PreguntasSeguridadManager preguntasSeguridadManager() {
            return Mockito.mock(PreguntasSeguridadManager.class);
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("perfilSEI");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCabeceraPerfilTest() {

        Respuesta<PerfilView> respuestaCabeceraPerfilView = new Respuesta<PerfilView>();
        respuestaCabeceraPerfilView.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(perfilManager.obtenerPerfil()).thenReturn(respuestaCabeceraPerfilView);

        WebClient client = getWebClient();
        client.accept(MediaType.APPLICATION_JSON);
        client.path("/perfil/obtenerPerfil");
        Respuesta<PerfilView> respuesta = client.post(null, Respuesta.class);
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerInfoAdicionalDomTelTest(){
        Respuesta<FeedbackMensajeView> respuestaView = new Respuesta<FeedbackMensajeView>();
        String viewReq ="123456789";
        Mockito.when(cambioDomicilioManager.obtenerInfoAdicionalDomTel(Matchers.any(CambioDomicilioView.class))).thenReturn(respuestaView);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/perfil/obtenerInfoAdicionalDomTel");
        Respuesta<AgendaDestinatarioView> retorno = client.post(viewReq, Respuesta.class);
        Assert.assertNotNull(retorno);
         
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void guardarCambioDomicilioTest(){
       Respuesta<CambioDomicilioView> respuestaView = new Respuesta<CambioDomicilioView>();
       CambioDomicilioView request = new CambioDomicilioView();
       Mockito.when(cambioDomicilioManager.guardarCambioDomicilio(Matchers.any(CambioDomicilioView.class))).thenReturn(respuestaView);
       WebClient client = getWebClient();            
       client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
       client.path("/perfil/guardarCambioDomicilio");
       Respuesta<AgendaDestinatarioView> retorno = client.post(request, Respuesta.class);
       Assert.assertNotNull(retorno);

             
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void normalizarDomicilioTest(){
       Respuesta<List<CambioDomicilioView>> respuestaView = new Respuesta<List<CambioDomicilioView>>();
       
       CambioDomicilioView request = new CambioDomicilioView();
       Mockito.when(cambioDomicilioManager.normalizarDomicilio(Matchers.any(CambioDomicilioView.class))).thenReturn(respuestaView);
       WebClient client = getWebClient();           
       client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
       client.path("/perfil/normalizarDomicilio");
       Respuesta<AgendaDestinatarioView> retorno = client.post(request, Respuesta.class);
       Assert.assertNotNull(retorno);

             
    }    
    
    
    
}
