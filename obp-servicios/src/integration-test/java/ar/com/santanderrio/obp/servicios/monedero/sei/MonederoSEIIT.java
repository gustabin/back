/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.sei;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.sei.impl.MonederoSEIImpl;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.AltaTagMonederoManager;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.DatosSolicitanteManager;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosAltaCanalesAutomaticosView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;


/**
 * The Class MonederoSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
	ar.com.santanderrio.obp.servicios.monedero.sei.MonederoSEIIT.MonederoSEITestConfiguration.class })
public class MonederoSEIIT extends AbstractSEITest {

    /** The agenda destinatarios manager. */
    @Autowired
    private DatosSolicitanteManager datosSolicitanteManager;

    /** The alta tag monedero manager. */
    @Autowired
    private AltaTagMonederoManager altaTagMonederoManager;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;
    
    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Inits the mock.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Gets the service bean to test.
     *
     * @return the service bean to test
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("monederoSEI");
    }

    /**
     * The Class MonederoSEITestConfiguration.
     *
     * @author nicolas_lavagna
     */
    @Configuration
    public static class MonederoSEITestConfiguration {

        /**
         * Agenda destinatarios SEI.
         *
         * @return the agenda destinatarios SEI
         */
        @Bean(name = "monederoSEI")
        public MonederoSEI monederoSEI() {
            return new MonederoSEIImpl();
        }

        /**
         * Agenda destinatarios manager.
         *
         * @return the agenda destinatarios manager
         */
        @Bean
        public DatosSolicitanteManager DatosSolicitanteManager() {
            return Mockito.mock(DatosSolicitanteManager.class);
        }

        /**
         * Alta destinatarios manager.
         *
         * @return the alta destinatario manager
         */
        @Bean
        public AltaTagMonederoManager altaTagMonederoManager() {
            return Mockito.mock(AltaTagMonederoManager.class);
        }
        
        /**
         * Alta destinatarios manager.
         *
         * @return the alta destinatario manager
         */
        @Bean
        public SesionCliente sesionCliente() {
            return new SesionCliente();
        }        
    }

    /**
     * Datos solicitante.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void datosSolicitante() {
    	DatosSolicitanteCriterioView viewReq = new DatosSolicitanteCriterioView();
    	viewReq.setAdicional(false);
    	viewReq.setDoc("27636371");
    	viewReq.setDocTipo("DNI");
    	viewReq.setFechaNacimiento("04/09/1979");
    	
        Respuesta<DatosSolicitanteResponseView> viewResp = crearRespuestaOK();
        Mockito.when(datosSolicitanteManager.getDatosDelSolicitante(viewReq)).thenReturn(viewResp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/monedero/datosDelSolicitante");
        addSleepTime(5000);
        Respuesta<AgendaDestinatarioView> retorno = client.post(viewReq, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Datos padron.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void datosPadron() {
    	DatosSolicitanteCriterioView viewReq = new DatosSolicitanteCriterioView();
    	viewReq.setAdicional(false);
    	viewReq.setDoc("27636371");
    	viewReq.setDocTipo("DNI");
    	viewReq.setFechaNacimiento("04/09/1979");
    	
        Respuesta<DatosSolicitanteResponseView> viewResp = crearRespuestaOK();
        Mockito.when(datosSolicitanteManager.datosPadron(viewReq)).thenReturn(viewResp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/monedero/datosDelSolicitante");
        addSleepTime(5000);
        Respuesta<AgendaDestinatarioView> retorno = client.post(viewReq, Respuesta.class);
        Assert.assertNotNull(retorno);
    }

    /**
     * Ejecutar alta canales automaticos.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void ejecutarAltaCanalesAutomaticos() {
    	DatosAltaCanalesAutomaticosView viewReq = new DatosAltaCanalesAutomaticosView();
    	viewReq.setNombre("Nombre");
    	viewReq.setApellido("Apellido");
    	viewReq.setDocumento("27636371");
    	viewReq.setDocTipo("DNI");
    	viewReq.setFechaNacimiento("04/09/1979");
    		
        Respuesta<AltaCanalAutomaticoOutEntity> viewResp = respuestaFactory.crearRespuestaOk(AltaCanalAutomaticoOutEntity.class,  new AltaCanalAutomaticoOutEntity());
        Mockito.when(datosSolicitanteManager.ejecutarAltaCanalesAutomaticos(viewReq)).thenReturn(viewResp);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/monedero/datosDelSolicitante");
        addSleepTime(5000);
        Respuesta<AgendaDestinatarioView> retorno = client.post(viewReq, Respuesta.class);
        Assert.assertNotNull(retorno);
    }    
    
    /**
     * Crear respuesta OK.
     *
     * @return the respuesta
     */
    private Respuesta<DatosSolicitanteResponseView> crearRespuestaOK() {
        return respuestaFactory.crearRespuestaOk(DatosSolicitanteResponseView.class, crearRespuesta());
    }    
    
    /**
     * Generar compra/venta dolar view con cuentas.
     *
     * @return the compra venta dolar view
     */
    private DatosSolicitanteResponseView crearRespuesta() {
        DatosSolicitanteResponseView view = new DatosSolicitanteResponseView();
        return view;
    }
}
