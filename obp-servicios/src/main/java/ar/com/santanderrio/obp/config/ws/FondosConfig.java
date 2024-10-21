/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.yaml.snakeyaml.Yaml;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.RescateFondoEspecie;

/**
 * @author sergio.e.goldentair
 *
 */
@Configuration
public class FondosConfig {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FondosConfig.class);

    /**
     * Nombre del file con la configuracion de las cache.
     */
    private static final String RESCATE_SEMILLA_FILE = "rescateSemilla.yaml";

    /** The env. */
    @Autowired
    private Environment env;

    /**
     * Load yaml.
     *
     * @return the seguros urls
     */
    @Bean
    public RescateFondoEspecie rescateFondoEspecie() {
        Yaml yaml = new Yaml();
        RescateFondoEspecie rescateFondoEspecie = new RescateFondoEspecie();
        FileInputStream file = null;
        try {
            StringBuilder path = new StringBuilder("");
            path.append(env.getProperty("config/OBP_UBICACION_PROPIEDADES")).append("/")
                    .append(env.getProperty("spring.profiles.active")).append("/").append(RESCATE_SEMILLA_FILE);
            file = new FileInputStream(new File(path.toString()));
            LOGGER.info("Cargando configuracion de fondos rescate semilla: {}", RESCATE_SEMILLA_FILE);
            rescateFondoEspecie = yaml.loadAs(file, RescateFondoEspecie.class);
        } catch (FileNotFoundException e) {
            LOGGER.info("El archivo {} con la configuracion de los fondos rescate no esta disponible.",
                    RESCATE_SEMILLA_FILE, e);
        }
        return rescateFondoEspecie;
    }
}
