package ar.com.santanderrio.obp.servicios.buscadorempresa.sei;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.buscadorempresa.sei.BuscadorEmpresaSEIIT.BuscadorEmpresaSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.buscadorempresa.sei.impl.BuscadorEmpresaSEIImpl;
import ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.BuscadorEmpresaManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * The Class BuscadorEmpresaSEITest.
 *
 * @author sergio.e.goldentair
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        BuscadorEmpresaSEITestConfiguration.class })
public class BuscadorEmpresaSEIIT extends AbstractSEITest {

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The buscador medios de pago manager. */
    @Autowired
    private BuscadorEmpresaManager buscadorArchivoPagoManager;
    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class BuscadorEmpresaSEITestConfiguration.
     */
    @Configuration
    public static class BuscadorEmpresaSEITestConfiguration {

        /**
         * Buscador empresa SEI.
         *
         * @return the buscador empresa SEI
         */
        @Bean(name = "buscadorEmpresaSEI")
        public BuscadorEmpresaSEI buscadorEmpresaSEI() {
            return new BuscadorEmpresaSEIImpl();
        }
        
        /**
         * Estadistica manager.
         *
         * @return the estadistica manager
         */
        @Bean
        public EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
        }

        /**
         * Buscador archivo pago manager.
         *
         * @return the buscador archivo pago manager
         */
        @Bean
        public BuscadorEmpresaManager buscadorArchivoPagoManager() {
            return Mockito.mock(BuscadorEmpresaManager.class);
        }

        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
        
        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }
        
    }


    /**
     * Index.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void index() throws JsonParseException, IOException {
        MedioPagoView medioPago = new MedioPagoView();
        medioPago.setNombreFantasia("telefonica");

        Respuesta<BuscadorEmpresaRta> respuestaList = new Respuesta<BuscadorEmpresaRta>();
        List<MedioPagoView> medioPagoViewList = new ArrayList<MedioPagoView>();
        medioPagoViewList.add(medioPago);

        BuscadorEmpresaRta buscadorEmpresaRta = new BuscadorEmpresaRta();
        buscadorEmpresaRta.setEmpresas(medioPagoViewList);
        respuestaList.setRespuesta(buscadorEmpresaRta);
        respuestaList.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaList.setRespuestaVacia(false);

        BuscadorEmpresaView buscador = new BuscadorEmpresaView();
        buscador.setQuery("Telecom");

        Mockito.when(buscadorArchivoPagoManager.searchByRubroEmpresa(Matchers.anyString())).thenReturn(respuestaList);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/empresa");
        addSleepTime(5000);
        Respuesta<BuscadorEmpresaRta> retorno = client.post(buscador, Respuesta.class);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Empresa pago automatico.
     *
     * @throws JsonParseException
     *             the json parse exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void empresaPagoAutomatico() throws JsonParseException, IOException {
        MedioPagoView medioPago = new MedioPagoView();
        medioPago.setNombreFantasia("Telecom");

        Respuesta<BuscadorEmpresaRta> respuestaList = new Respuesta<BuscadorEmpresaRta>();
        List<MedioPagoView> medioPagoViewList = new ArrayList<MedioPagoView>();
        medioPagoViewList.add(medioPago);

        BuscadorEmpresaRta buscadorEmpresaRta = new BuscadorEmpresaRta();
        buscadorEmpresaRta.setEmpresas(medioPagoViewList);
        respuestaList.setRespuesta(buscadorEmpresaRta);
        respuestaList.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaList.setRespuestaVacia(false);

        Mockito.when(buscadorArchivoPagoManager.searchEmpresaPagoAutomatico(Matchers.anyString()))
                .thenReturn(respuestaList);
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

        BuscadorEmpresaView buscador = new BuscadorEmpresaView();
        buscador.setQuery("Telecom");

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/empresaPagoAutomatico");
        addSleepTime(5000);
        Respuesta<BuscadorEmpresaRta> retorno = client.post(buscador, Respuesta.class);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("buscadorEmpresaSEI");
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void buscadorRecargaCelulares() {

        //When
        when(buscadorArchivoPagoManager.searchEmpresaRecargaCelulares())
                .thenReturn(respuestaFactory.crearRespuestaOk(EmpresaRecargaCelularView.class, new EmpresaRecargaCelularView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/empresaRecargaCelulares");
        BuscadorEmpresaView query = new BuscadorEmpresaView();
        query.setQuery("rec");
        
        //Then
        Respuesta<EmpresaRecargaCelularView> respuesta = client.post(query, Respuesta.class);

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }
    
    
    @Test
    public void grabarEstadisticaBuscadorRecarga(){
        
        //When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/grabarEstadisticaRecargaIngresoNumero");
        addSleepTime(5000);
        
        //Then
        Boolean respuesta = client.post(null, Boolean.class);
        
        //Expected
        Assert.assertEquals(true, respuesta);
    }

    
    @Test
    public void estadistica(){
        
        //When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/grabarEstadistica");
        
        //Then
        Boolean respuesta = client.post(null, Boolean.class);
        
        //Expected
        Assert.assertEquals(true, respuesta); 
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void estadisticaAdhesionDebitoAutomaticoEnTarjeta() {

        //When
        when(buscadorArchivoPagoManager.searchEmpresaDebitoAutomaticoEnTarjeta(Matchers.anyString()))
                .thenReturn(respuestaFactory.crearRespuestaOk(EmpresaDebitoAutomaticoTarjetaView.class, new EmpresaDebitoAutomaticoTarjetaView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/empresaDebitoAutomaticoEnTarjeta");
        BuscadorEmpresaView query = new BuscadorEmpresaView();
        query.setQuery("bil");
        addSleepTime(5000);
        
        //Then
        Respuesta<EmpresaDebitoAutomaticoTarjetaView> respuesta = client.post(query, Respuesta.class);

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }
    
    
    @Test
    public void grabarEstadisticaAdhesionDebitoAutomaticoEnTarjeta(){
        
        //When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/buscador/estadisticaAdhesionDebitoAutomaticoEnTarjeta");
        
        //Then
        Boolean respuesta = client.post(null, Boolean.class);
        
        //Expected
        Assert.assertEquals(true, respuesta);
    }
    
}
