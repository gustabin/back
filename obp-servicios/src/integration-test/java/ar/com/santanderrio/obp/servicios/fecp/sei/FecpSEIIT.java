package ar.com.santanderrio.obp.servicios.fecp.sei;

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
import ar.com.santanderrio.obp.servicios.fecp.sei.FecpSEIIT.FecpSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.token.externos.TokenManager;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { FecpSEITestConfiguration.class })
public class FecpSEIIT extends AbstractSEITest {


    /** The login manager. */
    @Autowired
    private TokenManager fecpManager;

    @Configuration
    public static class FecpSEITestConfiguration {

        /**
         * Login SEI.
         *
         * @return the login SEI
         */
        @Bean(name = "fecpSEI")
        public FecpSEI seguroSEI() {
            return new FecpSEIImpl();
        }

        /**
         * Login manager.
         *
         * @return the login manager
         */
        @Bean
        TokenManager fecpManager() {
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
        String url = "http://180.166.119.46:9082/fecp/inicial.htm";
        Respuesta<TokenView> respuesta = new Respuesta<TokenView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(true);
        TokenView tokenView = new TokenView();
        tokenView.setTokenFirmado(token);
        tokenView.setUrl(url);
        respuesta.setRespuesta(tokenView);
        Mockito.when(fecpManager.obtenerTokenEncriptado()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fecp/obtenerTokenEncriptado");
        super.addSleepTime(2000);

        Respuesta<TokenView> retorno = client.post(null, new GenericType<Respuesta<TokenView>>() {});

        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
        Assert.assertEquals(retorno.getRespuesta().getTokenFirmado(), token);
        Assert.assertEquals(retorno.getRespuesta().getUrl(), url);
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
        
        Mockito.when(fecpManager.obtenerTokenEncriptado()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fecp/obtenerTokenEncriptado");
        super.addSleepTime(2000);

        Respuesta<TokenView> retorno = client.post(null, new GenericType<Respuesta<TokenView>>() {});

        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("fecpSEI");
    }
}
