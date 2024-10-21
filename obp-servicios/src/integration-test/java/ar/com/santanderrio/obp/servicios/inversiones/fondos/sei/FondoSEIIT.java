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
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.FondoSEIIT.FondoSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConsultaHorariosYHonorariosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionSuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TenenciasFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TipoBancaView;

/**
 * The Class FondoSEITest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { FondoSEITestConfiguration.class })
public class FondoSEIIT extends AbstractSEITest {

    /** The fondo manager. */
    @Autowired
    private FondoManager fondoManager;
    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class FondoSEITestConfiguration.
     */
    @Configuration
    public static class FondoSEITestConfiguration {

        /**
         * Fondo SEI.
         *
         * @return the fondo SEI
         */
        @Bean(name = "fondoSEI")
        public FondoSEI fondoSEI() {
            return new FondoSEIImpl();
        }

        /**
         * Fondo manager.
         *
         * @return the fondo manager
         */
        @Bean
        public FondoManager fondoManager() {
            return Mockito.mock(FondoManager.class);
        }

        /**
         * Fondo BO.
         *
         * @return the fondo BO
         */
        @Bean
        public FondoBO fondoBO() {
            return Mockito.mock(FondoBO.class);
        }
    }

    
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularSuscripcionOK() {
        when(fondoManager.simularSuscripcion(Matchers.any(FondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FondoView.class, new FondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/simularSuscripcion");
        //Then
        Respuesta<FondoView> respuesta = client.post(new FondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void suscribirOK() {
        when(fondoManager.suscribir(Matchers.any(FondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FondoView.class, new FondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/suscribir");
        //Then
        Respuesta<FondoView> respuesta = client.post(new FondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptosYDisponiblesBPrivOK() {
        when(fondoManager.obtenerFondosSuscriptosYDisponiblesBPriv(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, new CuentasConsultaFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/obtenerFondosPorCuentasBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new CuentasConsultaFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
  

   
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptosYDisponiblesOK() {
        when(fondoManager.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, new CuentasConsultaFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/obtenerFondosPorCuentas");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new CuentasConsultaFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDatosConfigOK() {
        when(fondoManager.obtenerDatosConfig(Matchers.any(ConfigFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfigFondoView.class, new ConfigFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/configurarSuscripcion");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new ConfigFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void aceptarContratoOK() {
        when(fondoManager.aceptarContrato(Matchers.any(ConfigFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfigFondoView.class, new ConfigFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/aceptarContrato");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new ConfigFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void tenenciasOK() {
        when(fondoManager.obtenerTenencias(Matchers.any(TipoBancaView.class))).thenReturn(respuestaFactory.crearRespuestaOk(TenenciasFondoView.class, new TenenciasFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/tenencias");
        //Then
        Respuesta<TenenciasFondoView> respuesta = client.post(new TipoBancaView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarSuscripcionFondosOK() {
        when(fondoManager.finalizarSuscribirFondos(Matchers.any(FondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FondoView.class, new FondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/finalizarSuscripcionFondos");
        //Then
        Respuesta<FondoView> respuesta = client.post(new FondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDatosConfigBPrivOK() {
        when(fondoManager.obtenerDatosConfigBpriv(Matchers.any(ConfigFondoBPrivView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfigFondoBPrivView.class, new ConfigFondoBPrivView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/configurarSuscripcionBPriv");
        //Then
        Respuesta<ConfigFondoBPrivView> respuesta = client.post(new ConfigFondoBPrivView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularSuscripcionBPrivOK() {
        when(fondoManager.simularSuscripcionBPriv(Matchers.any(SimulacionInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimulacionSuscripcionOutView.class, new SimulacionSuscripcionOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/simularSuscripcionBPriv");
        //Then
        Respuesta<SimulacionSuscripcionOutView> respuesta = client.post(new SimulacionInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarSuscripcionFondosBPrivOK() {
        when(fondoManager.finalizarSuscribirFondosBPriv(Matchers.any(SuscripcionInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SuscripcionOutView.class, new SuscripcionOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/finalizarSuscripcionFondosBPriv");
        //Then
        Respuesta<SuscripcionOutView> respuesta = client.post(new SuscripcionInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void consultarCotizacionesOK() {
        when(fondoManager.consultarCotizaciones()).thenReturn(respuestaFactory.crearRespuestaOk(CotizacionDeFondosView.class, new CotizacionDeFondosView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/consultarCotizaciones");
        //Then
        Respuesta<CotizacionDeFondosView> respuesta = client.post(void.class, Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void consultarHorariosYHonorariosOK() {
        when(fondoManager.consultarHorariosYHonorarios()).thenReturn(respuestaFactory.crearRespuestaOk(ConsultaHorariosYHonorariosView.class, new ConsultaHorariosYHonorariosView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/consultarHorariosYHonorarios");
        //Then
        Respuesta<ConsultaHorariosYHonorariosView> respuesta = client.post(void.class, Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    
  
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobanteBPrivOK() {
        when(fondoManager.verComprobanteBPriv(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/verComprobanteBPriv");
        //Then
        Respuesta<ComprobanteSuscripcionView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobanteOK() {
        when(fondoManager.verComprobante(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/verComprobante");
        addSleepTime(5000);        
        //Then
        Respuesta<ComprobanteSuscripcionView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void ultimosMovimientos() {
        when(fondoManager.ultimosMovimientos(Matchers.any(MovimientosInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(MovimientosView.class, new MovimientosView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/ultimosMovimientos");
        addSleepTime(5000);        
        //Then
        Respuesta<MovimientosView> respuesta = client.post(new MovimientosInView(), Respuesta.class);
        //Expected
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void detalleFondo() {
        when(fondoManager.obtenerDetalleDeFondo(Matchers.any(DetalleDeFondoIn.class))).thenReturn(respuestaFactory.crearRespuestaOk(DetalleDeFondoOutView.class, 
                new DetalleDeFondoOutView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/detalleDeFondo");
        addSleepTime(5000);        
        //Then
        Respuesta<DetalleDeFondoOutView> respuesta = client.post(new DetalleDeFondoIn(), Respuesta.class);
        //Expected
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 
    
    @SuppressWarnings("unchecked")
    @Test
    public void busquedaMovimientos() {
        when(fondoManager.busquedaMovimientos(Matchers.any(MovimientosInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(MovimientosView.class, 
                new MovimientosView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/busquedaMovimientos");
        addSleepTime(5000);        
        //Then
        Respuesta<MovimientosView> respuesta = client.post(new MovimientosInView(), Respuesta.class);
        //Expected
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    } 

    
    @Test
    public void grabarEstadisticaLegalBPersonal() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/grabarEstadisticaGastos");
        //Then
       client.post(new ConfigFondoView(), Respuesta.class);
        //Expected
       addSleepTime(5000);
    } 
    
   
    @Test
    public void grabarEstadisticaGastosBPriv() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/fondos/grabarEstadisticaGastosBPriv");
        //Then
       client.post(new ConfigFondoView(), Respuesta.class);
       addSleepTime(5000);
        //Expected
        
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("fondoSEI");
    }

}
