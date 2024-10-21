/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.sei;

import static org.mockito.Mockito.when;

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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEIIT.RescateSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateOutView;

/**
 * The Class TransferenciaFondoSEITest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        RescateSEITestConfiguration.class })
public class RescateSEIIT extends AbstractSEITest {

	@Autowired
    private RescateManager rescateManager;
	
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class TransferenciaSEITestConfiguration.
     */
    @Configuration
    public static class RescateSEITestConfiguration {

        /**
         * Rescate SEI.
         *
         * @return the rescate SEI
         */
        @Bean(name = "rescateSEI")
        public RescateSEI rescateSEI() {
            return new RescateSEIImpl();
        }

        /**
         * Rescate manager.
         *
         * @return the rescate manager
         */
        @Bean
        public RescateManager rescateManager() {
            return Mockito.mock(RescateManager.class);
        }

        /**
         * Rescate BO.
         *
         * @return the rescate BO
         */
        @Bean
        public RescateBO rescateBO() {
            return Mockito.mock(RescateBO.class);
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
        return applicationContext.getBean("rescateSEI");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void configuracionRescate() {
        when(rescateManager.configuracionRescate(Matchers.any(ConfiguracionRescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionRescateOutView.class, new ConfiguracionRescateOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/configuracionRescate");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new ConfiguracionRescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarRescate() {
        when(rescateManager.finalizarRescate(Matchers.any(FinalizarRescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, new FinalizarRescateView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/finalizarRescate");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new FinalizarRescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondo() {
        when(rescateManager.simularRescateFondo(Matchers.any(SimulacionRescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimulacionRescateOutView.class, new SimulacionRescateOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/simularRescateFondo");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new SimulacionRescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoBPriv() {
        when(rescateManager.simularRescateFondoBPriv(Matchers.any(SimulacionRescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimulacionRescateOutView.class, new SimulacionRescateOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/simularRescateFondoBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new SimulacionRescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptos() {
        when(rescateManager.obtenerFondosSuscriptos(Matchers.any(RescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(RescateView.class, new RescateView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/obtenerFondosPorCuentas");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new RescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionRescateBPriv() {
        when(rescateManager.configuracionRescateBPriv(Matchers.any(ConfiguracionRescateBPrivInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfiguracionRescateOutView.class, new ConfiguracionRescateOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/configuracionRescateBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new ConfiguracionRescateBPrivInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptosBPriv() {
        when(rescateManager.obtenerFondosSuscriptosBPriv(Matchers.any(RescateInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(RescateView.class, new RescateView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/obtenerFondosPorCuentasBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new RescateInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarRescateBPriv() {
        when(rescateManager.finalizarRescateBPriv(Matchers.any(FinalizarRescateBPrivInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FinalizarRescateView.class, new FinalizarRescateView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/finalizarRescateBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new FinalizarRescateBPrivInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobante() {
        when(rescateManager.verComprobante(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/verComprobante");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobanteBPriv() {
        when(rescateManager.verComprobanteBPriv(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/verComprobanteBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void ObtenerRescateDesdeGrilla() {
        when(rescateManager.obtenerRescateDesdeGrilla(Matchers.any(RescateDesdeGrillaInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(RescateDesdeGrilla.class, new RescateDesdeGrilla()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/rescate/iniciarRescateDesdePaso2");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new RescateDesdeGrillaInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
}
