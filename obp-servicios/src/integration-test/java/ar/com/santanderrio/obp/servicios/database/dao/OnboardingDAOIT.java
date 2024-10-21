package ar.com.santanderrio.obp.servicios.database.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import ar.com.santanderrio.obp.servicios.database.dao.OnboardingDAOIT.OnboardingDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.onboarding.dao.OnboardingDAO;

/**
 * The Class MensajeDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { OnboardingDAOITConfiguration.class,
        BackEndSecurityConfig.class, CacheConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.TIMEOUT = 30000" })
@Ignore
public class OnboardingDAOIT extends HbankBaseDAOIT {

    /** The mensaje DAO. */
    @Autowired
    private OnboardingDAO onboardingDAO;

    /**
     * The Class MensajeDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.onboarding.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class OnboardingDAOITConfiguration {
        
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

    @Test
    public void obtenerEstadoOK() throws DAOException {
        List<String> seccionesActivas = onboardingDAO.obtenerSeccionesActivas("0", "1");
        Assert.assertNotNull(seccionesActivas);
    }
    
    
    @Test
    public void informarListo() throws DAOException {
        Boolean resultado = onboardingDAO.informarListo("0", "1", "HOME");
        Assert.assertNotNull(resultado);
    }
    
}
