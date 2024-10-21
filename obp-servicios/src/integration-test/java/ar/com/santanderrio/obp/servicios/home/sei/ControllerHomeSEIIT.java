package ar.com.santanderrio.obp.servicios.home.sei;

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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.home.sei.ControllerHomeSEIIT.ControllerHomeSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.home.sei.impl.ControllerHomeSEIImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionConsultasHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionSolicitudesHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.manager.SeccionTransaccionesHomeManager;

/**
 * The Class ControllerHomeSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ControllerHomeSEITestConfiguration.class })
public class ControllerHomeSEIIT extends AbstractSEITest {

    /** The seccion consultas home manager. */
    @Autowired
    private SeccionConsultasHomeManager seccionConsultasHomeManager;

    /** The seccion transacciones home manager. */
    @Autowired
    private SeccionTransaccionesHomeManager seccionTransaccionesHomeManager;

    /** The seccion solicitudes home manager. */
    @Autowired
    private SeccionSolicitudesHomeManager seccionSolicitudesHomeManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class ControllerHomeSEITestConfiguration.
     */
    @Configuration
    public static class ControllerHomeSEITestConfiguration {

        /**
         * Controller home SEI.
         *
         * @return the controller home SEI
         */
        @Bean(name = "controllerHomeSEI")
        public ControllerHomeSEI controllerHomeSEI() {
            return new ControllerHomeSEIImpl();
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
         * Seccion consultas home manager.
         *
         * @return the seccion consultas home manager
         */
        @Bean
        public SeccionConsultasHomeManager seccionConsultasHomeManager() {
            return Mockito.mock(SeccionConsultasHomeManager.class);
        }

        /**
         * Seccion transacciones home manager.
         *
         * @return the seccion transacciones home manager
         */
        @Bean
        public SeccionTransaccionesHomeManager seccionTransaccionesHomeManager() {
            return Mockito.mock(SeccionTransaccionesHomeManager.class);
        }

        /**
         * Seccion solicitudes home manager.
         *
         * @return the seccion solicitudes home manager
         */
        @Bean
        public SeccionSolicitudesHomeManager seccionSolicitudesHomeManager() {
            return Mockito.mock(SeccionSolicitudesHomeManager.class);
        }
    }

    /**
     * endpoint para consulta de cuentas desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoConsultasCuentas() {
        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionConsultasHomeManager.notificarAccesoCuentas()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoConsultasCuentas");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * endpoint para consulta de tarjetas desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoConsultasTarjetas() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionConsultasHomeManager.notificarAccesoTarjetas()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoConsultasTarjetas");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * endpoint para consulta de prestamos desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoConsultasPrestamos() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionConsultasHomeManager.notificarAccesoPrestamos()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoConsultasPrestamos");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * endpoint para Transaccion calendario de pagos desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoTransaccionesCalendario() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionTransaccionesHomeManager.notificarAccesoCalendario()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoTransaccionesCalendario");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * endpoint para transaccion transferencias desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoTransaccionesTransferencias() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionTransaccionesHomeManager.notificarAccesoTransferencias()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoTransaccionesTransferencias");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * endpoint para solicitud de prestamos desde el controller.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void notificarAccesoSolicitudesPrestamos() {

        Respuesta<Void> respuesta = new Respuesta<Void>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // mock
        Mockito.when(seccionSolicitudesHomeManager.notificarAccesoPrestamos()).thenReturn(respuesta);
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("controller/notificarAccesoSolicitudesPrestamos");
        Respuesta<Void> retorno = client.post(null, Respuesta.class);
        Assert.assertNotNull(retorno);
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
        return applicationContext.getBean("controllerHomeSEI");
    }

}
