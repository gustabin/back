/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.database.dao.LegalDAOIT.LegalDAOITConfiguration;

/**
 * Probar obtener el mapa de legales y que este cacheado. Probar limpiar la
 * cache y que lo vuelva a pedir.
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { LegalDAOITConfiguration.class,
        CacheConfig.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT =30000" })
@Ignore
public class LegalDAOIT extends HbankBaseDAOIT {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LegalDAOIT.class);

    /** The legal DAO. */
    @Autowired
    private LegalDAO legalDAO;

    /**
     * The Class LegalDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.legal.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class LegalDAOITConfiguration {
        
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
     * Obtener legales as map.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void obtenerLegalesAsMap() throws DAOException {
        long initTime1 = System.currentTimeMillis();
        Map<String, String> legales = legalDAO.obtenerLegales();
        LOGGER.info("Tiempo en miliseg consumido en la primer llamada {}.", System.currentTimeMillis() - initTime1);
        LOGGER.info("Se reciben {} registros.", legales.size());
        Assert.assertTrue(legales.size() > 0);

        long initTime2 = System.currentTimeMillis();
        Map<String, String> legales2 = legalDAO.obtenerLegales();
        LOGGER.info("Tiempo en miliseg consumido en la segunda llamada {}.", System.currentTimeMillis() - initTime2);
        LOGGER.info("Se reciben {} registros.", legales2.size());
        Assert.assertTrue(legales2.size() > 0);
    }

    /**
     * Limpiar legales.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void limpiarLegales() throws DAOException {
        long initTime1 = System.currentTimeMillis();
        Map<String, String> legales = legalDAO.obtenerLegales();
        LOGGER.info("Tiempo en miliseg consumido en la primer llamada {}.", System.currentTimeMillis() - initTime1);
        LOGGER.info("Se reciben {} registros.", legales.size());
        Assert.assertTrue(legales.size() > 0);

        long initTime2 = System.currentTimeMillis();
        Map<String, String> legales2 = legalDAO.obtenerLegales();
        LOGGER.info("Tiempo en miliseg consumido en la segunda llamada {}.", System.currentTimeMillis() - initTime2);
        LOGGER.info("Se reciben {} registros.", legales2.size());
        Assert.assertTrue(legales2.size() > 0);

        LOGGER.info("Se limpia la cache.");
        legalDAO.limpiarLegales();

        long initTime3 = System.currentTimeMillis();
        Map<String, String> legales3 = legalDAO.obtenerLegales();
        LOGGER.info("Tiempo en miliseg consumido en la segunda llamada {}.", System.currentTimeMillis() - initTime3);
        LOGGER.info("Se reciben {} registros.", legales3.size());
        Assert.assertTrue(legales3.size() > 0);
    }

    /**
     * Gets the legal error.
     *
     * @throws DAOException the DAO exception
     */
    @Test(expected = DAOException.class)
    public void getLegalError() throws DAOException {
        legalDAO.obtenerLegal("");
    }

}
