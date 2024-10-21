/**
 * 
 */
package ar.com.santanderrio.obp.servicios;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http_jetty.JettyHTTPServerEngineFactory;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.config.ws.CxfSpringConfig;

/**
 * The Class AbstractSEITest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CxfSpringConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(Profiles.SEI)
public abstract class AbstractSEITest {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSEITest.class);

    /** The Constant ENDPOINT_PORT. */
    private final static Integer ENDPOINT_PORT = 8083;

    /** The Constant ENDPOINT_PROTOCOL. */
    private final static String ENDPOINT_PROTOCOL = "http";

    /** The Constant ENDPOINT_ADDRESS. */
    private final static String ENDPOINT_ADDRESS = ENDPOINT_PROTOCOL + "://localhost:" + ENDPOINT_PORT + "/xxx";

    /** The Constant WADL_ADDRESS. */
    private final static String WADL_ADDRESS = ENDPOINT_ADDRESS + "?_wadl";

    /** The jaxrs server factory bean. */
    @Autowired
    private JAXRSServerFactoryBean jaxrsServerFactoryBean;

    /** The application context. */
    @Autowired
    protected ApplicationContext applicationContext;

    /** The server. */
    private Server server;

    /**
     * Start server.
     *
     * @throws GeneralSecurityException
     *             the general security exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Before
    public void startServer() throws GeneralSecurityException, IOException {
        Thread.interrupted();
    	LOGGER.info("Start cxf server escuchando en {}.", ENDPOINT_ADDRESS);
        jaxrsServerFactoryBean.setAddress(ENDPOINT_ADDRESS);
        jaxrsServerFactoryBean.setServiceBeans(getServiceBean());
        jaxrsServerFactoryBean.getBus().getExtension(JettyHTTPServerEngineFactory.class)
                .createJettyHTTPServerEngine(ENDPOINT_PORT, ENDPOINT_PROTOCOL).setSessionSupport(true);
        server = jaxrsServerFactoryBean.create();
        waitForWADL();
    }

    /**
     * Indica si para el test hay que generar el wadl. Default FALSE
     *
     * @return true, if successful
     */
    protected boolean generarWADL() {
        return Boolean.FALSE;
    }

    /**
     * Hay test que requieren mas tiempo para que el jetty este disponible, la idea
     * es darle unos segundos mas puntualmente a los que requieren.
     * 
     * @param tiempo
     *            segundos agregados al test.
     */
    protected void addSleepTime(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            LOGGER.error("Error al dar aire al test.", e);
        }

    }

    /**
     * Cargar la coleccion con el servicio indicado en la clase que implementa.
     *
     * @return the service bean
     */
    private List<Object> getServiceBean() {
        LOGGER.info("Cargar SEI a testear");
        return Collections.singletonList(getServiceBeanToTest());
    }

    /**
     * Indicar el SEI que se debe invocar
     * 
     * <pre>
     *  <code>
     *  	applicationContext.getBean("loginSEI")
     *  </code>
     * </pre>
     *
     * @return the service bean to test
     */
    protected abstract Object getServiceBeanToTest();

    /**
     * Cliente para consultar el WS. No inyecto el bean JacksonJaxbJsonProvider
     * definido en CxfSpringConfig porque rompe cuando hay mas de un contexto en los
     * test.
     *
     * @return the web client
     */
    public WebClient getWebClient() {
        return WebClient.create(ENDPOINT_ADDRESS, Arrays.asList(new JacksonJaxbJsonProvider()));
    }

    /**
     * Generar el wadl de las operaciones contenidas en este ws por si se quiere
     * distribuir.
     * 
     */
    private void waitForWADL() {
        if (generarWADL()) {
            LOGGER.info("Generacion del WADL Activado.");
            WebClient client = WebClient.create(WADL_ADDRESS);
            // wait for 20 secs or so
            for (int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("Error al generar el wadl", e);
                }
                Response response = client.get();
                if (response.getStatus() == 200) {
                    break;
                } else {
                    LOGGER.info("Esperando el wadl intento {} de 20.", i);
                }
            }
        } else {
            LOGGER.info("La Generacion del WADL no fue seleccionada.");
        }
    }

    /**
     * Destroy.
     */
    @After
    public void destroy() {
        if(server != null) {
        	LOGGER.info("Stop y destroy server");
        	server.stop();
        	server.destroy();
        }
    }
        
}
