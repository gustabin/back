package ar.com.santanderrio.obp.servicios.chat.sei;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.chat.sei.impl.ChatSEIImpl;
import ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;

/**
 * The Class ChatSEIIT.
 * 
 * @author ITResources
 */
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class, 
		classes = {ar.com.santanderrio.obp.servicios.chat.sei.ChatSEIIT.ChatSEITestConfiguration.class }
		)
public class ChatSEIIT extends AbstractSEITest{
	
	/** The chat manager. */
	@Autowired
	private ChatManager chatManager;

    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Configuration
    public static class  ChatSEITestConfiguration {
        
        @Bean(name = "ChatSEI")
        public ChatSEI chatSEI() {
            return new ChatSEIImpl();
        }
        
        @Bean
        public ChatManager chatManager() {
            return Mockito.mock(ChatManager.class);
        }
        
    }

	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("ChatSEI");
	}
	
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracion() {
        Mockito.when(chatManager.obtenerConfiguracion()).thenReturn(respuestaFactory.crearRespuestaOk(new ChatConfigView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/chat/obtenerChat");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void conectar() {
    	OfertaComercialView oferta = new OfertaComercialView();
    	oferta.setId("");
        Mockito.when(chatManager.conectar(Matchers.anyString(), Matchers.any(OfertaComercialView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new ChatOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/chat/conectarChat");
        Respuesta<ChatOutView> respuesta = client.post(new String(), Respuesta.class);
        Assert.assertNotNull(respuesta);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void desconectar() {
        Mockito.when(chatManager.desconectar()).thenReturn(respuestaFactory.crearRespuestaOk(new ChatOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/chat/desconectarChat");
        Respuesta<ChatOutView> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void enviarEmail() {
        Mockito.when(chatManager.enviarEmail(Matchers.any(ChatEnviarEmailInView.class), Matchers.any(String.class))).thenReturn(new Respuesta<Void>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/chat/enviarEmail");
        Respuesta<Void> respuesta = client.post(new ChatEnviarEmailInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
    }
	
}
