/**
 * 
 */
package ar.com.santanderrio.obp.servicios.status.sei;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.status.manager.StatusManager;
import ar.com.santanderrio.obp.servicios.status.sei.StatusSEIIT.StatusSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.status.view.StatusView;

/**
 * The Class StatusSEITest.
 *
 * @author sergio.e.goldentair
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { StatusSEITestConfiguration.class })
public class StatusSEIIT extends AbstractSEITest {
    /** The cliente manager. */
    @Autowired
    private StatusManager statusManager;

	/** The respuesta factory. */
    @Mock
	private RespuestaFactory respuestaFactory;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class StatusSEITestConfiguration.
     */
    @Configuration
    public static class StatusSEITestConfiguration {
        /**
         * StatusSEI SEI.
         *
         * @return the Status SEI
         */
        @Bean(name = "statusSEI")
        public StatusSEI statusSEI() {
            return new StatusSEIImpl();
        }

        /**
         * Login manager.
         *
         * @return the login manager
         */
        @Bean
        StatusManager statusManager() {
            return Mockito.mock(StatusManager.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean(name = "respuestaFactory")
        public RespuestaFactory respuestaFactory() {
            return new RespuestaFactory();
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

    }

    /**
     * Loing error.
     *
     * @return the status OK
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getStatusOK() {
        String status = "OK";
        StatusView view = new StatusView();
        view.setBc(status);
        view.setFechaDistribucion("2016-11-17T14:24:20Z");
        view.setServidor("CPX-46UXSXGYY8L");
        view.setVersionEnpaquetado("0.0.1-SNAPSHOT");

        Respuesta<StatusView> respuesta = new Respuesta<StatusView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(view);
        respuesta.setRespuestaVacia(false);

        Mockito.when(statusManager.getStatus()).thenReturn(respuesta);

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaError.setRespuestaVacia(true);
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		itemMensajeRespuesta.setTag("");
		itemMensajeRespuesta.setMensaje("");
		respuestaError.add(itemMensajeRespuesta);

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), 
        		Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaError);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/health/status");

        Respuesta<StatusView> retorno = client.invoke(HttpMethod.GET, null, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("statusSEI");
    }

}
