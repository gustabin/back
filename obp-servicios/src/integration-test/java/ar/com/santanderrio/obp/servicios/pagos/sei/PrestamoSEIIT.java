/* Tests para PagosSEI
 *
 * Manuel Vargas B041299
 */
package ar.com.santanderrio.obp.servicios.pagos.sei;

import static org.junit.Assert.assertNotNull;
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
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.sei.PrestamoSEIIT.PrestamoSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.sei.impl.PrestamoSEIImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoManager;

/**
 * The Class PrestamoSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PrestamoSEITestConfiguration.class })
public class PrestamoSEIIT extends AbstractSEITest {

    /** The Constant PAGO_PRESTAMO. */
    static final String PAGO_PRESTAMO = "/pagoPrestamo";

    /** The Constant OBTENER_DETALLE_PRESTAMO. */
    static final String OBTENER_DETALLE_PRESTAMO = PAGO_PRESTAMO + "/obtenerDetallePrestamo";

    /** The Constant OBTENER_COMPROBANTE. */
    static final String OBTENER_COMPROBANTE = PAGO_PRESTAMO + "/obtenerComprobantePrestamo";

    /** The Constant ACCEPT_APPLICATION_JSON. */
    static final String ACCEPT_APPLICATION_JSON = "Accept=application/json";

    /** The Constant OBTENER_REPORTE_PRESTAMO. */
    static final String OBTENER_REPORTE_PRESTAMO = PAGO_PRESTAMO + "/obtenerReporteComprobantePrestamo";

    /** The Constant OBTENER_CONFIGURACION_PAGO_CUOTA_PRESTAMO. */
    static final String OBTENER_CONFIGURACION_PAGO_CUOTA_PRESTAMO = PAGO_PRESTAMO
            + "/obtenerConfiguracionPagoCuotaPrestamo";

    /** The Constant OBTENER_CONFIRMACION_PAGO. */
    static final String OBTENER_CONFIRMACION_PAGO = PAGO_PRESTAMO + "/obtenerConfirmacionPago";

    /** The Constant PAGAR_PRESTAMO. */
    static final String PAGAR_PRESTAMO = PAGO_PRESTAMO + "/pagoPrestamo";

    /** The Constant OBTENER_REPORTE_DETALLE_PRESTAMO. */
    static final String OBTENER_REPORTE_DETALLE_PRESTAMO = PAGO_PRESTAMO + "/obtenerReporteDetallePrestamo";

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
         * Prestamo manager.
         *
         * @return the prestamo manager
         */
        @Bean
        public PrestamoManager prestamoManager() {
            return Mockito.mock(PrestamoManager.class);
        }

        /**
         * Prestamo SEI.
         *
         * @return the prestamo SEI
         */
        @Bean(name = "prestamoSEI")
        public PrestamoSEI prestamoSEI() {
            return new PrestamoSEIImpl();
        }

    }

    /**
     * Tests prestamoSEI when gets the detalle Prestamo.
     *
     * @return the detalle prestamos test
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getDetallePrestamosTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<DetallePrestamoView> respManager = new Respuesta<DetallePrestamoView>();
        DetallePrestamoView detallePrestamoView = new DetallePrestamoView();
        respManager.setRespuesta(detallePrestamoView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.getDetallePrestamos(Matchers.any(ConsultaPrestamo.class), Matchers.anyBoolean()))
                .thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_DETALLE_PRESTAMO);
        Respuesta<DetallePrestamoView> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);
        assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Tests prestamoSEI when gets the detalle Prestamo.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerComprobanteTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<ComprobanteView> respManager = new Respuesta<ComprobanteView>();
        ComprobanteView comprobanteView = new ComprobanteView();
        respManager.setRespuesta(comprobanteView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.obtenerComprobante(Matchers.anyString())).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_COMPROBANTE);
        Respuesta<ComprobanteView> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);
        assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener configuracion pago cuota prestamo test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracionPagoCuotaPrestamoTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<ConfiguracionPagoCuotaPrestamo> respManager = new Respuesta<ConfiguracionPagoCuotaPrestamo>();
        ConfiguracionPagoCuotaPrestamo configuracionPagoCuotaPrestamo = new ConfiguracionPagoCuotaPrestamo();
        respManager.setRespuesta(configuracionPagoCuotaPrestamo);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.obtenerConfiguracionPagoCuotaPrestamo(Matchers.any(ConsultaPrestamo.class),
                Matchers.anyBoolean())).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_CONFIGURACION_PAGO_CUOTA_PRESTAMO);
        Respuesta<ConfiguracionPagoCuotaPrestamo> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);
        assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener confirmacion test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfirmacionTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<ConfirmacionPagoPrestamoView> respManager = new Respuesta<ConfirmacionPagoPrestamoView>();
        ConfirmacionPagoPrestamoView confirmacionPagoPrestamoView = new ConfirmacionPagoPrestamoView();
        respManager.setRespuesta(confirmacionPagoPrestamoView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.obtenerConfirmacion(Matchers.any(ConsultaPrestamo.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_CONFIRMACION_PAGO);
        Respuesta<ConfirmacionPagoPrestamoView> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);
        assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Pagar test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void pagarTest() {

        Respuesta<RespuestaPagoPrestamoView> respManager = new Respuesta<RespuestaPagoPrestamoView>();
        RespuestaPagoPrestamoView respuestaPagoPrestamoView = new RespuestaPagoPrestamoView();
        respManager.setRespuesta(respuestaPagoPrestamoView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.efectuarPago(Matchers.any(ConsultaPrestamo.class))).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(PAGAR_PRESTAMO);
        Respuesta<RespuestaPagoPrestamoView> respuestaSEI = client.post(null, Respuesta.class);
        assertNotNull(respuestaSEI);

    }

    /**
     * Obtener reporte comprobante test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerReporteComprobanteTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<ReporteView> respManager = new Respuesta<ReporteView>();
        ReporteView reporteView = new ReporteView();
        respManager.setRespuesta(reporteView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.obtenerReporteComprobante(Matchers.anyString())).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_REPORTE_PRESTAMO);
        Respuesta<ReporteView> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);
        assertNotNull(respuestaSEI);
        Assert.assertEquals(respuestaSEI.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    /**
     * Obtener reporte detalle prestamo test.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerReporteDetallePrestamoTest() {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("5");

        Respuesta<ReporteView> respManager = new Respuesta<ReporteView>();
        ReporteView reporteView = new ReporteView();
        respManager.setRespuesta(reporteView);
        respManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(prestamoManager.getReporteDetallePrestamo(Matchers.anyString(), false)).thenReturn(respManager);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path(OBTENER_REPORTE_DETALLE_PRESTAMO);
        addSleepTime(5000);
        Respuesta<ReporteView> respuestaSEI = client.post(consultaPrestamo, Respuesta.class);

        assertNotNull(respuestaSEI);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("prestamoSEI");
    }
}
