package ar.com.santanderrio.obp.servicios.prestamos.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoOpenCreditDAO;
import ar.com.santanderrio.obp.servicios.prestamos.sei.impl.PrestamoOpenCreditSEIImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoOpenCreditManager;

/**
 * The Class PrestamoOpenCreditSEIIT.
 *
 * @author Silvina_Luque
 */

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.prestamos.sei.PrestamoOpenCreditSEIIT.PrestamoOpenCreditSEIITConfiguration.class })
public class PrestamoOpenCreditSEIIT extends AbstractSEITest {
    
    /** The prestamo open credit manager. */
    @Autowired
    private PrestamoOpenCreditManager prestamoOpenCreditManager;
    
    /** The respuesta factory. */
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /**
     * The Class PrestamoOpenCreditSEIITConfiguration.
     */
    @Configuration
    public static class PrestamoOpenCreditSEIITConfiguration {
        
        /**
         * Prestamo open credit SEI.
         *
         * @return the prestamo open credit SEI
         */
        @Bean(name = "extraccionYComprasExteriorSEI")
        public PrestamoOpenCreditSEI prestamoOpenCreditSEI() {
            return new PrestamoOpenCreditSEIImpl();
        }
        
        /**
         * Prestamo open credit manager.
         *
         * @return the prestamo open credit manager
         */
        @Bean
        public PrestamoOpenCreditManager prestamoOpenCreditManager() {
            return Mockito.mock(PrestamoOpenCreditManager.class);
        }
        
        /**
         * Prestamo open credit DAO.
         *
         * @return the prestamo open credit DAO
         */
        @Bean
        public PrestamoOpenCreditDAO prestamoOpenCreditDAO() {
            return Mockito.mock(PrestamoOpenCreditDAO.class);
        }
    }

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("prestamoOpenCreditSEI");
    }
    
    /**
     * obtenerDetallePagosMinimosTest.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDetallePagosMinimosTest() {
        when(prestamoOpenCreditManager.obtenerDetallePagosMinimos(Matchers.any(CnsDetallePagosMinimosOpenCreditView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new DetallePagosMinimosOpenCreditView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/prestamosOpenCredit/obtenerDetallePagosMinimos");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    /**
     * exportarHistorialPagosMinimosTest.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void exportarHistorialPagosMinimosTest() {
        when(prestamoOpenCreditManager.exportarHistorialPagosMinimos(Matchers.any(ReportePagosMinimosOpenCreditInView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new ReporteView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/prestamosOpenCredit/exportarHistorialPagosMinimos");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

}
