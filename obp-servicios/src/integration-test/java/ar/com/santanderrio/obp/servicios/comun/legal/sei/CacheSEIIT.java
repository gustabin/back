/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.sei;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.cache.manager.UsedCacheManager;
import ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI;
import ar.com.santanderrio.obp.servicios.cache.sei.CacheSEIImpl;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;
import ar.com.santanderrio.obp.servicios.comun.legal.manager.LegalManager;
import ar.com.santanderrio.obp.servicios.comun.legal.sei.CacheSEIIT.CacheSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.DestinoPrestamoManager;

/**
 * The Class CacheSEITest.
 *
 * @author sergio.e.goldentair
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CacheSEITestConfiguration.class })
@Ignore
public class CacheSEIIT extends AbstractSEITest {

    /** The used cache manager. */
    @Autowired
    private UsedCacheManager usedCacheManager;

    /** The legal manager. */
    @Autowired
    private LegalManager legalManager;

    /** The destino prestamo manager. */
    @Autowired
    private DestinoPrestamoManager destinoPrestamoManager;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class StatusSEITestConfiguration.
     */
    @Configuration
    public static class CacheSEITestConfiguration {
        /**
         * StatusSEI SEI.
         *
         * @return the Status SEI
         */
        @Bean(name = "cacheSEI")
        public CacheSEI cacheSEI() {
            return new CacheSEIImpl();
        }

        /**
         * ModuloExcluidosManager manager.
         *
         * @return the moduloPermisoManager manager
         */
        @Bean
        public ModuloPermisoManager moduloPermisoManager() {
            return Mockito.mock(ModuloPermisoManager.class);
        }

        /**
         * Login manager.
         *
         * @return the login manager
         */
        @Bean
        public LegalManager legalManager() {
            return Mockito.mock(LegalManager.class);
        }

        /**
         * Gestor de la Cache.
         *
         * @return the used cache manager
         */
        @Bean
        public UsedCacheManager usedCacheManager() {
            return Mockito.mock(UsedCacheManager.class);
        }

        /**
         * Mensaje manager.
         *
         * @return the mensajeManager
         */
        @Bean
        public MensajeManager mensajeManager() {
            return Mockito.mock(MensajeManager.class);
        }

        /**
         * Destino prestamo manager.
         *
         * @return the destino prestamo manager
         */
        @Bean
        public DestinoPrestamoManager destinoPrestamoManager() {
            return Mockito.mock(DestinoPrestamoManager.class);
        }

        /**
         * Fondos manager.
         *
         * @return the destino prestamo manager
         */
        @Bean
        public FondoManager fondoManager() {
            return Mockito.mock(FondoManager.class);
        }
    }

    /**
     * Limpiar legales OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void limpiarLegalesOK() {
        Respuesta<Boolean> respOK = respuestaFactory.crearRespuestaOk(true);
        Mockito.when(legalManager.vaciarLegales()).thenReturn(respOK);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cache/limpiarLegales");

        Respuesta<Boolean> retorno = client.invoke(HttpMethod.GET, null, Respuesta.class);
        Assert.assertTrue(retorno.getRespuesta());
    }

    /**
     * Obtener estadisticas.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerEstadisticas() {
        CacheView cacheView = new CacheView();
        cacheView.setNombreCache(CacheConstants.Names.CACHE_LEGALES);
        cacheView.setNumeroElementos("2");
        cacheView.setNumeroEvicts("0");
        cacheView.setNumeroHintsRealizados("32");
        List<CacheView> listCacheView = new ArrayList<CacheView>();
        listCacheView.add(cacheView);

        Respuesta<List<CacheView>> respOK = respuestaFactory.crearRespuestaOk(listCacheView);
        Mockito.when(usedCacheManager.obtenerEstadisticas()).thenReturn(respOK);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cache/obtenerEstadisticas");

        Respuesta<List<CacheView>> retorno = client.invoke(HttpMethod.GET, null, Respuesta.class);
        Assert.assertTrue(retorno.getRespuesta().size() == 1);
    }

    /**
     * Vaciar destinos prestamo OK.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void vaciarDestinosPrestamoOK() {
        Respuesta<Boolean> respOK = respuestaFactory.crearRespuestaOk(true);
        Mockito.when(destinoPrestamoManager.vaciarDestinosPrestamo()).thenReturn(respOK);

        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/cache/limpiarMotivosPrestamo");

        Respuesta<Boolean> retorno = client.invoke(HttpMethod.GET, null, Respuesta.class);
        Assert.assertTrue(retorno.getRespuesta());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
     */
    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("cacheSEI");
    }

}
