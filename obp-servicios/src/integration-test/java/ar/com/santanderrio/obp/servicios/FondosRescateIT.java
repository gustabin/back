/**
 * 
 */
package ar.com.santanderrio.obp.servicios;

import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
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

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.config.ws.FondosConfig;
import ar.com.santanderrio.obp.servicios.FondosRescateIT.FondosRescateITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoEspecie;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { FondosRescateITConfiguration.class,
        FondosConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.TIMEOUT = 30000" })
public class FondosRescateIT {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FondosRescateIT.class);

    @Autowired
    private RescateFondoEspecie rescateFondoEspecie;

    /**
     * The Class MensajeDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    public static class FondosRescateITConfiguration {

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

    @BeforeClass
    public static void setUpClass() throws Exception {
        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", Profiles.TEST);
        properties.setProperty("config/OBP_UBICACION_PROPIEDADES", Object.class.getResource("/config").getPath());
    }

    @Test
    public void levantarYaml() {
        FondoEspecie fondoEspecie = rescateFondoEspecie.getFondosMap().get("007");
        LOGGER.info("Mapa Rescate recuperado {}", rescateFondoEspecie);
        LOGGER.info("Fondo Rescate recuperado {}", fondoEspecie);
        Assert.assertTrue(!rescateFondoEspecie.getFondosMap().isEmpty());
        Assert.assertEquals("E", fondoEspecie.getOperacion());
    }
}
