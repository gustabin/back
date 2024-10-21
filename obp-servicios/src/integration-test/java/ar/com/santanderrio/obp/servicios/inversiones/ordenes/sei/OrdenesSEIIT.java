package ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei;

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
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.OrdenesSEIIT.OrdenesSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.sei.impl.OrdenesSEIImpl;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.manager.OrdenesManager;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.FiltrosOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class OrdenesSEITest.
 * 
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Mon 23, 2017
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { OrdenesSEITestConfiguration.class })
public class OrdenesSEIIT extends AbstractSEITest {

    /** The ordenes manager. */
    @Autowired
    private OrdenesManager ordenesManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class OrdenesSEITestConfiguration.
     */
    @Configuration
    public static class OrdenesSEITestConfiguration {

        /**
         * Ordenes manager.
         *
         * @return the ordenes manager
         */
        @Bean
        public OrdenesManager ordenesManager() {
            return Mockito.mock(OrdenesManager.class);
        }

        /**
         * Ordenes SEI.
         *
         * @return the ordenes SEI
         */
        @Bean(name = "ordenesSEI")
        public OrdenesSEI ordenesSEI() {
            return new OrdenesSEIImpl();
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
        return applicationContext.getBean("ordenesSEI");
    }

    /**
     * Iniciar ordenes test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void iniciarOrdenesTest() {
        OrdenesView ordenesView = new OrdenesView();
        Respuesta<OrdenesView> respuestaManager = new Respuesta<OrdenesView>();
        respuestaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaManager.setRespuesta(ordenesView);

        Respuesta<PagoMultipleView> respuestaSEI = new Respuesta<PagoMultipleView>();
        Mockito.when(ordenesManager.iniciarOrdenes()).thenReturn(respuestaManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/ordenes/iniciarOrdenesOperaciones");
        respuestaSEI = client.post(ordenesView, Respuesta.class);

        Assert.assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Buscar ordenes test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void buscarOrdenesTest() {
        OrdenesView ordenesView = new OrdenesView();
        Respuesta<OrdenesView> respuestaManager = new Respuesta<OrdenesView>();
        respuestaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaManager.setRespuesta(ordenesView);

        Respuesta<PagoMultipleView> respuestaSEI = new Respuesta<PagoMultipleView>();
        Mockito.when(ordenesManager.buscarOrdenes(Matchers.any(FiltrosOrdenesView.class))).thenReturn(respuestaManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/ordenes/buscarOrdenesOperaciones");
        respuestaSEI = client.post(ordenesView, Respuesta.class);

        Assert.assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void buscarOrdenesOperacionesPorCuentaTest() {
        OrdenesView ordenesView = new OrdenesView();
        Respuesta<OrdenesView> respuestaManager = new Respuesta<OrdenesView>();
        respuestaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaManager.setRespuesta(ordenesView);

        Respuesta<PagoMultipleView> respuestaSEI = new Respuesta<PagoMultipleView>();
        Mockito.when(ordenesManager.buscarOrdenesPorCuenta(Matchers.any(String.class))).thenReturn(respuestaManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/ordenes/buscarOrdenesOperacionesPorCuenta");
        respuestaSEI = client.post(new CuentaView(), Respuesta.class);

        Assert.assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFiltrosDeBusquedaTest() {
        FiltrosOrdenesView filtrosOrdenesView = new FiltrosOrdenesView();
        Respuesta<FiltrosOrdenesView> respuestaManager = new Respuesta<FiltrosOrdenesView>();
        respuestaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaManager.setRespuesta(filtrosOrdenesView);

        Respuesta<PagoMultipleView> respuestaSEI = new Respuesta<PagoMultipleView>();
        Mockito.when(ordenesManager.obtenerFiltrosDeBusqueda()).thenReturn(respuestaManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/ordenes/obtenerFiltrosDeBusqueda");
        respuestaSEI = client.post(void.class, Respuesta.class);

        Assert.assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);
       
    }
   
    
}
