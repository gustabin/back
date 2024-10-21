/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
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
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.database.dao.MensajeDAOIT.MensajeDAOITConfiguration;

/**
 * The Class MensajeDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { MensajeDAOITConfiguration.class,
        BackEndSecurityConfig.class, CacheConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
@Ignore
public class MensajeDAOIT extends HbankBaseDAOIT {

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The mensaje Error Generico. */
    @Value("${MENSAJE_ERROR_GENERICO}")
    private String mensajeErrorGenerico;

    /**
     * The Class MensajeDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.mensaje.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class MensajeDAOITConfiguration {
        
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
     * Obtener mensaje OK.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void obtenerMensajeOK() throws DAOException {
        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo("1005");
        Long tiempoInicial = System.currentTimeMillis();
        Mensaje mensaje2 = mensajeDAO.obtenerMensajePorCodigo("1005");
        Long tiempoFinal = System.currentTimeMillis();
        Assert.assertEquals(mensaje.getCodigo(), "1005");
        Assert.assertTrue(tiempoFinal - tiempoInicial < 10);
        Assert.assertEquals(mensaje, mensaje2);
    }

    /**
     * Obtener mensaje error.
     *
     * @throws DAOException the DAO exception
     */
    @Ignore
    @Test
    public void obtenerMensajeError() throws DAOException {
        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo("100115");
        Assert.assertEquals(mensaje.getMensaje(), mensajeErrorGenerico);
    }
}
