package ar.com.santanderrio.obp.servicios.seguros.sei;

import java.io.IOException;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.seguros.sei.SeguroSEIIT.SeguroSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SeguroSEITestConfiguration.class })
public class SeguroSEIIT extends AbstractSEITest {
    
    /** The login manager. */
    @Autowired
    private TokenManager seguroManager;

    @Configuration
    public static class SeguroSEITestConfiguration {

        /**
         * Login SEI.
         *
         * @return the login SEI
         */
        @Bean(name = "seguroSEI")
        public SeguroSEI seguroSEI() {
            return new SeguroSEIImpl();
        }

        /**
         * Login manager.
         *
         * @return the login manager
         */
        @Bean
        TokenManager seguroManager() {
            return Mockito.mock(TokenManager.class);
        }
    }
    
    /** The application context. */
    @Autowired
    protected ApplicationContext applicationContext;
    
    /**
     * Obtener el token encriptado.
     * @throws JsonParseException
     * @throws IOException
     */
    @Test
    public void obtenerTokenEncriptado() {
        String token = "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";
        String url = "http://des4z1.ar.bsch:9190/CanalCryptServlet";
        Respuesta<TokenView> respuesta = new Respuesta<TokenView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        TokenView tokenView = new TokenView();
        tokenView.setTokenFirmado(token);
        tokenView.setUrl(url);
        respuesta.setRespuesta(tokenView);
        Mockito.when(seguroManager.obtenerTokenEncriptado()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/seguros/obtenerTokenEncriptado");
        super.addSleepTime(5000);

        Respuesta<TokenView> retorno = client.post(null, new GenericType<Respuesta<TokenView>>() {});

        Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
        Assert.assertEquals(token, retorno.getRespuesta().getTokenFirmado());
        Assert.assertEquals(url, retorno.getRespuesta().getUrl());
    }
    
    /**
     * Obtener ERROR.
     * @throws JsonParseException
     * @throws IOException
     */
    @Test
    public void obtenerTokenEncriptadoError() {
        Respuesta<TokenView> respuesta = new Respuesta<TokenView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuestaVacia(true);
        
        Mockito.when(seguroManager.obtenerTokenEncriptado()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/seguros/obtenerTokenEncriptado");
        addSleepTime(5000);

        Respuesta<TokenView> retorno = client.post(null, new GenericType<Respuesta<TokenView>>() {});

        Assert.assertEquals(EstadoRespuesta.ERROR, retorno.getEstadoRespuesta());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("seguroSEI");
    }

}
