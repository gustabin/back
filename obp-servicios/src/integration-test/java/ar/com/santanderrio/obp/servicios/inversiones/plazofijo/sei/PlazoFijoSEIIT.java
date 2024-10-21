package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei.PlazoFijoSEIIT.PlazoFijoSEITestConfiguration;

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
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.PlazoFijoManager;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AltaComprobantePlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfirmacionPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.InteresesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoBprivView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PlazoFijoSEITestConfiguration.class })
public class PlazoFijoSEIIT extends AbstractSEITest {
    /** The PlazoFijo manager. */
    @Autowired
    private PlazoFijoManager plazoFijoManager;
    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
   
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class PlazoFijoSEITestConfiguration.
     */
    @Configuration
    public static class PlazoFijoSEITestConfiguration {

        /**
         * PlazoFijo SEI.
         *
         * @return the PlazoFijo SEI
         */
        @Bean(name = "plazoFijoSEI")
        public PlazoFijoSEI plazoFijoSEI() {
            return new PlazoFijoSEIImpl();
        }

        /**
         * Fondo manager.
         *
         * @return the fondo manager
         */
        @Bean
        public PlazoFijoManager plazoFijoManager() {
            return Mockito.mock(PlazoFijoManager.class);
        }

      
    }
    
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("plazoFijoSEI");
    }
    
   
   
    
    
    
    @Test
    public void grabarEstadisticaLegalBPersonal() {
        when(plazoFijoManager.simularPlazoFijo(Matchers.any(SimularPlazoFijoInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimularPlazoFijoOutView.class, new SimularPlazoFijoOutView()));
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/consultar");
        addSleepTime(5000);
        //Then
        @SuppressWarnings("unchecked")
        Respuesta<SimularPlazoFijoOutView> respuesta = client.post(new SimularPlazoFijoInView(), Respuesta.class);
       Assert.assertNotNull(respuesta);
       Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void finalizarPlazoFijo() {
    	when(plazoFijoManager.finalizarPlazoFijo(Matchers.any(FinalizarPlazoFijoInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FinalizarPlazoFijoView.class, new FinalizarPlazoFijoView()));
    	WebClient client = getWebClient();
    	client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
    	client.path("/plazoFijo/finalizarPlazoFijo");
    	addSleepTime(5000);
        //Then
        @SuppressWarnings("unchecked")
        Respuesta<FinalizarPlazoFijoView> respuesta = client.post(new FinalizarPlazoFijoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @Test
    public void configuracionPlazoFijo() {
    	ConfigPlazoFijoInView configuracionPlazoFijoInView = new ConfigPlazoFijoInView();
        when(plazoFijoManager.configuracionPlazoFijo(configuracionPlazoFijoInView)).thenReturn(respuestaFactory.crearRespuestaOk(ConfigPlazoFijoOutView.class, new ConfigPlazoFijoOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/configuracion");
        addSleepTime(5000);
        //Then
        @SuppressWarnings("unchecked")
        Respuesta<ConfigPlazoFijoOutView> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @Test
    public void accionesAlVencimiento() {
        when(plazoFijoManager.accionesAlVencimiento(Matchers.any(AccionesAlVencimientoInView.class)))
                .thenReturn(respuestaFactory.crearRespuestaOk(AccionesAlVencimientoOutView.class,
                        new AccionesAlVencimientoOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/accionesAlVencimiento");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<ConfigPlazoFijoOutView> respuesta = client.post(new AccionesAlVencimientoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void grabarEstadisticaPlazoFijo() {
        when(plazoFijoManager.grabarEstadisticaPlazoFijo()).thenReturn(null);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/grabarEstadisticaPlazoFijo");
        addSleepTime(5000);
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
    } 
    
    @Test
    public void grabarEstadisticaPlazoFijoBPriv() {
        when(plazoFijoManager.grabarEstadisticaPlazoFijo()).thenReturn(null);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/grabarEstadisticaPlazoFijoBPriv");
        addSleepTime(5000);
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        
    }
    
    @Test
    public void verComprobante() {
        when(plazoFijoManager.verComprobante(Matchers.any(ComprobantePlazoFijoInView.class))).thenReturn(
                respuestaFactory.crearRespuestaOk(ComprobantePlazoFijoOutView.class, new ComprobantePlazoFijoOutView()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/verComprobantePlazoFijo");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<ComprobantePlazoFijoOutView> respuesta = client.post(new ComprobantePlazoFijoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void calcularIntereses() {
        when(plazoFijoManager.calcularIntereses(Matchers.any(CalcularInteresesInView.class))).thenReturn(
                respuestaFactory.crearRespuestaOk(InteresesView.class, new InteresesView()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/calcularIntereses");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<InteresesView> respuesta = client.post(new CalcularInteresesInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void consultarTasas() {
    	ConsultaTasasPlazosFijoView consultaTasasPlazosFijoView = new ConsultaTasasPlazosFijoView();
    	consultaTasasPlazosFijoView.setBancaSeleccionada("bancaPrivada");
        when(plazoFijoManager.consultarTasas(consultaTasasPlazosFijoView)).thenReturn(respuestaFactory
                .crearRespuestaOk(ConsultaTasasPlazosFijoView.class, new ConsultaTasasPlazosFijoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/consultarTasas");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<ConsultaTasasPlazosFijoView> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void obtenerTenenciasPlazoFijo() {
        when(plazoFijoManager.obtenerTenenciasPlazoFijo(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(
                respuestaFactory.crearRespuestaOk(TenenciaPlazoFijoView.class, new TenenciaPlazoFijoView()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/tenenciasPlazoFijo");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<TenenciaPlazoFijoView> respuesta = client.post(new ObtenerTenenciasPlazoFijoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void obtenerTenenciasPlazoFijoBPriv() {
        when(plazoFijoManager.obtenerTenenciasPlazoFijoBPriv(Matchers.any(ObtenerTenenciasPlazoFijoInView.class))).thenReturn(
                respuestaFactory.crearRespuestaOk(TenenciaPlazoFijoBprivView.class, new TenenciaPlazoFijoBprivView()));

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/tenenciasPlazoFijoBPriv");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<TenenciaPlazoFijoBprivView> respuesta = client.post(new ObtenerTenenciasPlazoFijoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void grabarEstadisticaVerDetalle() {
        when(plazoFijoManager.grabarEstadisticaPlazoFijo()).thenReturn(null);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/grabarEstadisticaVerDetalle");
        addSleepTime(5000);
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
    }
    
    @Test
    public void grabarEstadisticaVerDetalleBPriv() {
        when(plazoFijoManager.grabarEstadisticaPlazoFijo()).thenReturn(null);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/grabarEstadisticaVerDetalleBPriv");
        addSleepTime(5000);
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
    }
    
    @Test
    public void confirmacionPlazoFijo() {
        when(plazoFijoManager.confirmacionPlazoFijo(Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaFactory.crearRespuestaOk(ConfirmacionPlazoFijoOutView.class,
                        new ConfirmacionPlazoFijoOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/confirmacion");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<ConfirmacionPlazoFijoOutView> respuesta = client.post(new AccionesAlVencimientoInView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }   
    
    @Test
    public void altaComprobante() {
        when(plazoFijoManager.altaComprobante(Matchers.any(AltaComprobantePlazoFijoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(AltaComprobantePlazoFijoView.class));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/plazoFijo/altaComprobante");
        addSleepTime(5000);
        // Then
        @SuppressWarnings("unchecked")
        Respuesta<AltaComprobantePlazoFijoView> respuesta = client.post(new AltaComprobantePlazoFijoView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }    
}
