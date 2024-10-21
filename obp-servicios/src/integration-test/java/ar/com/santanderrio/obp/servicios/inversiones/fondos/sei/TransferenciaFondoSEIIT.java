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
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.TransferenciaFondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.TransferenciaFondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.TransferenciaFondoSEIIT.TransferenciaSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;

/**
 * The Class TransferenciaFondoSEITest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        TransferenciaSEITestConfiguration.class })
public class TransferenciaFondoSEIIT extends AbstractSEITest {

	@Autowired
    private TransferenciaFondoManager transferenciaManager;
	
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    protected boolean generarWADL() {
        return Boolean.TRUE;
    }
    
    /**
     * The Class TransferenciaSEITestConfiguration.
     */
    @Configuration
    public static class TransferenciaSEITestConfiguration {

        /**
         * Transferencia SEI.
         *
         * @return the transferencia fondo SEI
         */
        @Bean(name = "transferenciaSEI")
        public TransferenciaFondoSEI transferenciaSEI() {
            return new TransferenciaFondoSEIImpl();
        }

        /**
         * Transferencia manager.
         *
         * @return the transferencia fondo manager
         */
        @Bean
        public TransferenciaFondoManager transferenciaManager() {
            return Mockito.mock(TransferenciaFondoManager.class);
        }

        /**
         * Transferencia BO.
         *
         * @return the transferencia fondo BO
         */
        @Bean
        public TransferenciaFondoBO transferenciaBO() {
            return Mockito.mock(TransferenciaFondoBO.class);
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
        return applicationContext.getBean("transferenciaSEI");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void iniciarTransferencia() {
        when(transferenciaManager.iniciarTransferencia(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, new CuentasConsultaFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/iniciarTransferencia");
        addSleepTime(5000);
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new CuentasConsultaFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        addSleepTime(5000);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void iniciarTransferenciaBPriv() {
        when(transferenciaManager.iniciarTransferenciaBpriv(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaFactory.crearRespuestaOk(CuentasConsultaFondoView.class, new CuentasConsultaFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/iniciarTransferenciaBPriv");
        //Then
        Respuesta<CuentasConsultaFondoView> respuesta = client.post(new CuentasConsultaFondoView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        addSleepTime(5000);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDatosConfig() {
        when(transferenciaManager.obtenerDatosConfig(Matchers.any(ConfigTransferenciaInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(ConfigTransferenciaView.class, new ConfigTransferenciaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/configurarTransferencia");
        //Then
        Respuesta<ConfigTransferenciaView> respuesta = client.post(new ConfigTransferenciaInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarTransferenciaFondos() {
        when(transferenciaManager.finalizarTransferenciaFondos(Matchers.any(TransferenciaView.class))).thenReturn(respuestaFactory.crearRespuestaOk(TransferenciaView.class, new TransferenciaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/finalizarTransferenciaFondos");
        //Then
        Respuesta<TransferenciaView> respuesta = client.post(new TransferenciaView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void finalizarTransferenciaFondosBPriv() {
        when(transferenciaManager.finalizarTransferenciaFondosBpriv(Matchers.any(FinalizarTransferenciaFondoInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(FinalizarTransferenciaFondoView.class, new FinalizarTransferenciaFondoView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/finalizarTransferenciaFondosBPriv");
        //Then
        Respuesta<FinalizarTransferenciaFondoView> respuesta = client.post(new FinalizarTransferenciaFondoInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularTrasnferencia() {
        when(transferenciaManager.simularTransferencia(Matchers.any(SimulacionTransferenciaInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimulacionTransferenciaView.class, new SimulacionTransferenciaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/simularTransferencia");
        //Then
        Respuesta<SimulacionTransferenciaView> respuesta = client.post(new SimulacionTransferenciaInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularTrasnferenciaBPriv() {
        when(transferenciaManager.simularTransferenciaBpriv(Matchers.any(SimulacionInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(SimulacionTransferenciaView.class, new SimulacionTransferenciaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/simularTransferenciaBPriv");
        //Then
        Respuesta<SimulacionTransferenciaView> respuesta = client.post(new SimulacionInView(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @Test
    public void grabarEstadisticaGastosBPriv() {
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/grabarEstadisticaGastosBPriv");
        //Then
       client.post(new ConfigTransferenciaInView(), Respuesta.class);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobante() {
        when(transferenciaManager.verComprobante(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/verComprobante");
        //Then
        Respuesta<ComprobanteSuscripcionView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobanteBPriv() {
        when(transferenciaManager.verComprobanteBPriv(Matchers.any(DatosComprobante.class))).thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteSuscripcionView.class, new ComprobanteSuscripcionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/transferenciaFondos/verComprobanteBPriv");
        //Then
        Respuesta<ComprobanteSuscripcionView> respuesta = client.post(new DatosComprobante(), Respuesta.class);
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
}
