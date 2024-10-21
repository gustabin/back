package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.ExtraccionYComprasExteriorDAO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.manager.ExtraccionYComprasExteriorManager;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.impl.ExtraccionYComprasExteriorSEIImpl;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.CuentaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.DatosTarjetasExtraccionYComprasExteriorView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.ModifTarjetaOperaExtraccionView;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view.TarjetaOperacionExteriorView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;



@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.sei.ExtraccionYComprasExteriorSEIIT.ExtYComprasExteriorSEITestConfiguration.class })
public class ExtraccionYComprasExteriorSEIIT extends AbstractSEITest {

    @Autowired
    private ExtraccionYComprasExteriorManager extraccionYComprasExteriorManager;
    
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("extraccionYComprasExteriorSEI");
    }
    
    @Configuration
    public static class ExtYComprasExteriorSEITestConfiguration {
        
        @Bean(name = "extraccionYComprasExteriorSEI")
        public ExtraccionYComprasExteriorSEI extraccionYComprasExteriorSEI() {
            return new ExtraccionYComprasExteriorSEIImpl();
        }
        
        @Bean
        public ExtraccionYComprasExteriorManager extraccionYComprasExteriorManager() {
            return Mockito.mock(ExtraccionYComprasExteriorManager.class);
        }
        
        @Bean
        public ExtraccionYComprasExteriorDAO extraccionYComprasExteriorDAO() {
            return Mockito.mock(ExtraccionYComprasExteriorDAO.class);
        }
    }
   
    
    @SuppressWarnings("unchecked")
    @Test
    public void consultarTarjetasOperacionExterior() {
        
        when(extraccionYComprasExteriorManager.consultarTarjetasOperacionExterior()).thenReturn(respuestaFactory.crearRespuestaOk(new DatosTarjetasExtraccionYComprasExteriorView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/extraccionYComprasExterior/consultarTarjetas");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void consultarCuentasOperacionExterior() {
        List<CuentaOperacionExteriorView> listaCuentas = new ArrayList<CuentaOperacionExteriorView>();
        when(extraccionYComprasExteriorManager.consultarCuentasOperacionExterior(Matchers.any(TarjetaOperacionExteriorView.class))).thenReturn(respuestaFactory.crearRespuestaOk(listaCuentas));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/extraccionYComprasExterior/consultarCuentas");
        Respuesta<Void> respuesta = client.post(new TarjetaOperacionExteriorView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
   
    @SuppressWarnings("unchecked")
    @Test
    public void modificarTarjeta() {
        when(extraccionYComprasExteriorManager.modificarTarjetaOperacionExterior(Matchers.any(ModifTarjetaOperaExtraccionView.class))).thenReturn(respuestaFactory.crearRespuestaOk(new ModifTarjetaOperaExtraccionView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/extraccionYComprasExterior/modificarTarjeta");
        Respuesta<Void> respuesta = client.post(new ModifTarjetaOperaExtraccionView(), Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void descargarComprobante() {
        when(extraccionYComprasExteriorManager.descargarComprobante()).thenReturn(respuestaFactory.crearRespuestaOk(new ReporteView()));
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/extraccionYComprasExterior/descargarComprobante");
        Respuesta<Void> respuesta = client.post(null, Respuesta.class);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
 
}
