package ar.com.santanderrio.obp.base.properties;
/**
 * 
 */

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Levanta el archivo de props para el core.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@PropertySource(value = { "classpath:coreTest.properties" }, ignoreResourceNotFound = false)
public class PropertiesTestConfig {

    /**
     * Property configurer.
     *
     * @return the property sources placeholder configurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Inits the.
     */
    @PostConstruct
    public void init() {
        Properties properties = System.getProperties();
        properties.setProperty("config/OBP_UBICACION_PROPIEDADES", Object.class.getResource("/config").getPath());
    }
}