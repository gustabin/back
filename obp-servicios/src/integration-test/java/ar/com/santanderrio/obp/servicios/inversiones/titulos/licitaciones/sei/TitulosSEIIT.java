package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei;

import java.util.List;

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
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEIIT.TitulosSEIITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.DetalleOperacionCompraVentaView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TitulosSEIITConfiguration.class })
public class TitulosSEIIT extends AbstractSEITest {
    
    @Autowired
    private TitulosManager titulosManager;
    
    @Autowired
    private TitulosOrdenVentaManager titulosOrdenVentaManager;
    
    @Autowired
    private TitulosOperacionesManager titulosOperacionesManager;
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void obtenerDetalleOperacionTest() {
        Respuesta<OperacionTitulosView> respuesta = new Respuesta<OperacionTitulosView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
        
        Mockito.when(titulosOperacionesManager.obtenerDetalleOperacion(Matchers.any(ParametrosOperacionesView.class))).thenReturn(respuesta);
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/titulos/obtenerDetalleOperacion");
        Respuesta<DetalleOperacionCompraVentaView> retorno = client.post(parametrosOperacionesView, Respuesta.class);
        
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    
    @Test
    public void consultarOperacionesLicitacionTest() {

        Respuesta<ConsultaOperacionesView> respuesta = new Respuesta<ConsultaOperacionesView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(titulosManager.consultarOperacionesLicitacion(TipoBancaEnum.BANCA_PERSONAL)).thenReturn(respuesta);
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/titulos/consultarOperacionesLicitacion");
        Respuesta<ConsultaOperacionesView> retorno = client.post(null, Respuesta.class);
        
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    @Test
    public void obtenerCuentasOperativas() {

        Respuesta<List<CuentasOperativasView>> respuesta = new Respuesta<List<CuentasOperativasView>>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        ParametrosOperacionesView parametros = new ParametrosOperacionesView();
        
        Mockito.when(titulosOperacionesManager.obtenerCuentasOperativas(parametros)).thenReturn(respuesta);
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/titulos/obtenerCuentas");
        Respuesta<List<CuentasOperativasView>> retorno = client.post(null, Respuesta.class);
        
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    @Test
    public void obtenerMasOperaciones() {

        Respuesta<OperacionesTitulosView> respuesta = new Respuesta<OperacionesTitulosView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Mockito.when(titulosOperacionesManager.obtenerMasOperaciones()).thenReturn(respuesta);
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/titulos/obtenerMasOperaciones");
        Respuesta<OperacionesTitulosView> retorno = client.post(null, Respuesta.class);
        
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    @Test
    public void obtenerOperacionesPrimeraVez() {

        Respuesta<OperacionesTitulosView> respuesta = new Respuesta<OperacionesTitulosView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
        
        Mockito.when(titulosOperacionesManager.obtenerOperacionesPrimeraVez(Matchers.any(ParametrosOperacionesView.class))).thenReturn(respuesta);
        
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/titulos/obtenerOperaciones");
        Respuesta<OperacionesTitulosView> retorno = client.post(parametrosOperacionesView, Respuesta.class);
        
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);

    }
    
    /**
     * The Class MovimientosSEITestConfiguration.
     */
    @Configuration
    public static class TitulosSEIITConfiguration {

        /**
         * Titulos manager.
         *
         * @return the titulos manager
         */
        @Bean
        public TitulosManager titulosManager() {
            return Mockito.mock(TitulosManager.class);
        }
        
        /**
         * TitulosOrdenVentaManager.
         *
         * @return the TitulosOrdenVentaManager
         */
        @Bean
        public TitulosOrdenVentaManager titulosOrdenVentaManager() {
            return Mockito.mock(TitulosOrdenVentaManager.class);
        }
        
        /**
         * TitulosOperacionesManager.
         *
         * @return the TitulosOperacionesManager
         */
        @Bean
        public TitulosOperacionesManager titulosOperacionesManager() {
            return Mockito.mock(TitulosOperacionesManager.class);
        }

        /**
         * Movimientos SEI.
         *
         * @return the movimientos SEI
         */
        @Bean(name = "titulosSEI")
        public TitulosSEI titulosSEI() {
            return new TitulosSEIImpl();
        }

    }

    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("titulosSEI");
    }

}
