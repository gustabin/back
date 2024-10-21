package ar.com.santanderrio.obp.servicios.debin.sei;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.codehaus.jackson.JsonParseException;
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

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.debin.entities.DebinView;
import ar.com.santanderrio.obp.servicios.debin.manager.DebinTokenManager;
import ar.com.santanderrio.obp.servicios.debin.sei.debinSEIIT.DebinSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Probar el SEI de login. validar que se pueda hacer el post segun lo definido
 * y que el request y response se generen segun lo esperado. Solo llega al sei,
 * no sigue la cadena del manager (mockeado).
 * 
 * @author sergio.e.goldentair
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { DebinSEITestConfiguration.class })
public class debinSEIIT extends AbstractSEITest {

    /** The debin manager. */
    @Autowired
    private DebinTokenManager debinTokenManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class LoginSEITestConfiguration.
     */
    @Configuration
    public static class DebinSEITestConfiguration {

        /**
         * Login SEI.
         *
         * @return the login SEI
         */
        @Bean(name = "debinSEI")
        public DebinSEI debinSEI() {
            return new DebinSEIImpl();
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean(name = "respuestaFactory")
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean(name = "mensajeBO")
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * The debin Token Manager.
         * 
         * @return
         */
        @Bean(name = "debinTokenManager")
        public DebinTokenManager debinTokenManager() {
            return Mockito.mock(DebinTokenManager.class);
        }

    }

    /**
     * Login OK.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void debinPrepararSalto() throws IOException {
        Respuesta<DebinView> respuesta = new Respuesta<DebinView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.setRespuestaVacia(false);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError("DESAFIO");
        item.setMensaje(StringUtils.EMPTY);
        item.setTag(StringUtils.EMPTY);
        respuesta.add(item);

        DebinView debinView = new DebinView();
        DebinView debinResponse = new DebinView();
        debinResponse.setTipoOperacion(OperacionesRSAEnum.DEBIN);

        respuesta.setRespuesta(debinResponse);

        Mockito.when(debinTokenManager.obtenerTokenEncriptado(Matchers.any(DebinView.class),
                Matchers.any(MessageContext.class))).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/debin/prepararSalto");

        Respuesta<DebinView> retorno = client.post(debinView, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void habilitado() throws IOException {
        Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);
        respuesta.setRespuesta(Boolean.TRUE);
        Mockito.when(debinTokenManager.habilitado()).thenReturn(respuesta);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//        client.encoding("UTF-8");
        client.path("/debin/iniciarFlujo");

        Respuesta<Boolean> retorno = client.post(null, Respuesta.class);

        Assert.assertNotNull(retorno);
    }

    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("debinSEI");
    }

}