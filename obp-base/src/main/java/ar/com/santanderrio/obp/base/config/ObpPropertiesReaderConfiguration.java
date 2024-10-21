/**
 *
 */
package ar.com.santanderrio.obp.base.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.yaml.snakeyaml.Yaml;

import ar.com.santanderrio.obp.base.profile.Profiles;

// TODO: Auto-generated Javadoc
/**
 * Levantar las propiedades por jndi.
 *
 * Los archivos que contienen las props de la app son: -obp-database.properties
 * con lo referido a config de la base de datos. -obp-webservice.properties con
 * lo referido a ws. -obp-config.properties con todas las otras props que no se
 * correspondan con los dos criterios anteriores. -obp-servicios.properties lo
 * referido a iatx.
 *
 * @author sergio.e.goldentair
 *
 */
@Configuration
@PropertySource(value = { "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/obp-config.properties",
        "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/obp-database.properties",
        "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/obp-webservice.properties",
        "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/obp-servicios.properties",
        "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/Hbconfig.properties",
        "file:${config/OBP_UBICACION_PROPIEDADES}/${spring.profiles.active}/routes-config.properties"}, ignoreResourceNotFound = false)

/*
 * El parametro ignoreResourceNotFound debe estar en false asi si hay algun
 * error se muestra en el log
 */
@Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.MOCK, Profiles.TOMCAT })
public class ObpPropertiesReaderConfiguration {

    /** The Constant LOGGER. */
    private static final Logger logger = LoggerFactory.getLogger(ObpPropertiesReaderConfiguration.class);

    /** The log4j archivo. */
    private static final String LOG4J2_ARCHIVO = "log4j2.xml";

    /** The env. */
    @Autowired
    private Environment env;

    /**
     * After.
     */
    @PostConstruct
    public void after() {
        String log4j2Path = getLog4j2Path();
        configLog4j2(log4j2Path);
        logger.info("************************************************************************");
        if (env.getActiveProfiles().length > 0) {
            logger.info("PROFILE : [" + env.getActiveProfiles()[0] + "]");

            logger.info("OBP_UBICACION_PROPIEDADES : [" + env.getProperty("config/OBP_UBICACION_PROPIEDADES") + "]");

            logger.info("************************************************************************");

        } else {
            logger.info("PROFILE : [ profile no cargado ]");
        }
    }

    /**
     * Obtener el path donde se encuentra el archivo de configuracion de log4j2 en
     * los diferentes ambientes.
     *
     * @return the log 4 j 2 path
     */
    private String getLog4j2Path() {
        StringBuilder log4j2FileLocation = new StringBuilder("file:");
        log4j2FileLocation.append(env.getProperty("config/OBP_UBICACION_PROPIEDADES")).append("/")
                .append(env.getProperty("spring.profiles.active")).append("/");
        log4j2FileLocation.append(LOG4J2_ARCHIVO);
        return log4j2FileLocation.toString();
    }

    /**
     * Dejar disponible el archivo de log4j2 para que lo utilice la app desde el
     * path indicado. <br/>
     *
     * @param log4j2Path the log 4 j 2 path
     */
    private void configLog4j2(String log4j2Path) {
        Configurator.initialize(null, log4j2Path);
        logger.info("Ubicacion del log4j2 file: {}", log4j2Path);
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

    /**
     * Load yaml.
     *
     * @return the seguros urls
     */
    @Bean
    public SegurosUrls loadYaml() {
        Yaml yaml = new Yaml();
        SegurosUrls seguros = new SegurosUrls();
        FileInputStream file = null;
        try {
            StringBuilder path = new StringBuilder("");
            path.append(env.getProperty("config/OBP_UBICACION_PROPIEDADES")).append("/")
                    .append(env.getProperty("spring.profiles.active")).append("/prestamos.yaml");
            file = new FileInputStream(new File(path.toString()));
            logger.info("Cargando configuracion de prestamos : prestamos.yaml");
            seguros = yaml.loadAs(file, SegurosUrls.class);
        } catch (FileNotFoundException e) {
            logger.info(
                    "El archivo con la configuracion global para matchear prestamos a contextuales no esta disponible.",
                    e);
        }
        return seguros;
    }
}
