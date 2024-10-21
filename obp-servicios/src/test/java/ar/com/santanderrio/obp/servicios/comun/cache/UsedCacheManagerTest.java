/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.cache;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.cache.manager.UsedCacheManager;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class UsedCacheManagerTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CacheConfig.class,
        UsedCacheManagerTest.UsedCacheManagerTestConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UsedCacheManagerTest {

    /** The used cache manager. */
    @Autowired
    @InjectMocks
    private UsedCacheManager usedCacheManager;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    /**
     * Setup mock.
     */
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class UsedCacheManagerTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackageClasses = { UsedCacheManager.class, RespuestaFactory.class })
    public static class UsedCacheManagerTestConfiguration {

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }

    }

    /**
     * Obtener estadisticas.
     */
    @Test
    public void obtenerEstadisticas() {
        Respuesta<List<CacheView>> listCacheView = usedCacheManager.obtenerEstadisticas();
        Assert.assertEquals(18, listCacheView.getRespuesta().size());
        Assert.assertEquals(CacheConstants.Names.CACHE_LEGALES,
                listCacheView.getRespuesta().get(0).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_MODULOS_PERMISOS,
                listCacheView.getRespuesta().get(1).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL,
                listCacheView.getRespuesta().get(8).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE,
                listCacheView.getRespuesta().get(9).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP,
                listCacheView.getRespuesta().get(10).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF,
                listCacheView.getRespuesta().get(16).getNombreCache());
        Assert.assertEquals(CacheConstants.Names.CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF,
                listCacheView.getRespuesta().get(17).getNombreCache());
    }
}
