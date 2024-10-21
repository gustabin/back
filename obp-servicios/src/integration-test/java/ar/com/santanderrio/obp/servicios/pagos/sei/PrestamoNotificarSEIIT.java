package ar.com.santanderrio.obp.servicios.pagos.sei;

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

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoNotificarSEIIT.PrestamoSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.sei.impl.PrestamoSEIImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;

/**
 * The Class PrestamoSEINotificarTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PrestamoSEITestConfiguration.class })
public class PrestamoNotificarSEIIT extends AbstractSEITest {

    /** The prestamo manager. */
    @Autowired
    private PrestamoManager prestamoManager;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class PrestamoSEITestConfiguration.
     */
    @Configuration
    public static class PrestamoSEITestConfiguration {

        /**
         * Prestamo SEI.
         *
         * @return the prestamo SEI
         */
        @Bean(name = "prestamoSEI")
        public PrestamoSEI prestamoSEI() {
            return new PrestamoSEIImpl();
        }

        /**
         * PrestamoManager manager.
         *
         * @return the Prestamo manager
         */
        @Bean
        public PrestamoManager prestamoManager() {
            return Mockito.mock(PrestamoManager.class);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("prestamoSEI");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getDetallePrestamosTest() {
        // When
        when(prestamoManager.getDetallePrestamos(Matchers.any(ConsultaPrestamo.class), Matchers.anyBoolean()))
                .thenReturn(new Respuesta<DetallePrestamoView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerDetallePrestamo");
        // Then
        Respuesta<DetallePrestamoView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerComprobanteTest() {
        // When
        when(prestamoManager.obtenerComprobante(Matchers.anyString())).thenReturn(new Respuesta<ComprobanteView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerComprobantePrestamo");
        addSleepTime(5000);
        // Then
        Respuesta<ComprobanteView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracionPagoCuotaPrestamoTest() {
        // When
        when(prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(Matchers.any(ConsultaPrestamo.class),
                Matchers.anyBoolean())).thenReturn(new Respuesta<ConfiguracionPagoCuotaPrestamo>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerConfiguracionPagoCuotaPrestamo");
        // Then
        Respuesta<ConfiguracionPagoCuotaPrestamo> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfirmacionTest() {
        // When
        when(prestamoManager.obtenerConfirmacion(Matchers.any(ConsultaPrestamo.class)))
                .thenReturn(new Respuesta<ConfirmacionPagoPrestamoView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerConfirmacionPago");
        // Then
        Respuesta<ConfirmacionPagoPrestamoView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void pagarTest() {
        // When
        when(prestamoManager.efectuarPago(Matchers.any(ConsultaPrestamo.class)))
                .thenReturn(new Respuesta<RespuestaPagoPrestamoView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/pagoPrestamo");
        // Then
        Respuesta<RespuestaPagoPrestamoView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerReporteComprobanteTest() {
        // When
        when(prestamoManager.obtenerReporteComprobante(Matchers.anyString())).thenReturn(new Respuesta<ReporteView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerReporteComprobantePrestamo");
        // Then
        Respuesta<ReporteView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerReporteDetallePrestamoTest() {
        // When
        when(prestamoManager.getReporteDetallePrestamo(Matchers.anyString(), false)).thenReturn(new Respuesta<ReporteView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/obtenerReporteDetallePrestamo");
        // Then
        Respuesta<ReporteView> respuesta = client.post(new ConsultaPrestamo(), Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void notificarDetalleImporteCuotaTest() {
        // When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/notificarDetalleImporteCuota");
        // Then
        client.post("", Respuesta.class);
        // Expected
    }

    @Test
    public void notificarDetalleTasasCuotaTest() {
        // When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/pagoPrestamo/notificarDetalleTasasCuota");
        // Then
        client.post("", Respuesta.class);
        // Expected
    }
}
