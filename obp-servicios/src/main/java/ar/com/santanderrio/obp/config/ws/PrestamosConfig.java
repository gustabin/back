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

import ar.com.santanderrio.obp.servicios.prestamos.utils.CodigosPrestamos;

@Configuration
public class PrestamosConfig {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamosConfig.class);

    /**
     * Nombre del file con la configuracion.
     */
    private static final String PRESTAMOS_FILE = "codigosPrestamos.yaml";
    
    /** The env. */
    @Autowired
    private Environment env;

    /**
     * Load yaml.
     *
     * @return the seguros urls
     */
    @Bean
    public CodigosPrestamos codigosPrestamos() {
        Yaml yaml = new Yaml();
        CodigosPrestamos codigosPrestamos = new CodigosPrestamos();
        FileInputStream file = null;
        try {
            StringBuilder path = new StringBuilder("");
            path.append(env.getProperty("config/OBP_UBICACION_PROPIEDADES")).append("/")
                    .append(env.getProperty("spring.profiles.active")).append("/").append(PRESTAMOS_FILE);
            file = new FileInputStream(new File(path.toString()));
            LOGGER.info("Cargando configuracion de prestamos: {}", PRESTAMOS_FILE);
            codigosPrestamos = yaml.loadAs(file, CodigosPrestamos.class);
        } catch (FileNotFoundException e) {
            LOGGER.info("El archivo {} con la configuracion de los fondos rescate no esta disponible.",
            		PRESTAMOS_FILE, e);
        }
        return codigosPrestamos;
    }
}
