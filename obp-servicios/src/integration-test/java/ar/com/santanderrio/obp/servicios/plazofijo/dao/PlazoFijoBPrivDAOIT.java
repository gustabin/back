package ar.com.santanderrio.obp.servicios.plazofijo.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.database.dao.HbAccionesBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao.PlazoFijoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.entity.ConsultaPlazoFijoBPrivOutEntity;
import ar.com.santanderrio.obp.servicios.plazofijo.dao.PlazoFijoBPrivDAOIT.PlazoFijoBPrivDAOITTConfiguration;

/**
 * The Class PlazoFijoBPrivDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PlazoFijoBPrivDAOITTConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.BPRIV.ID = 41142", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.TIMEOUT = 30000", "DB.RACFINICIAL.ID = 20090" })

public class PlazoFijoBPrivDAOIT  {
    
    /**
     * The Class PlazoFijoBPrivDAOITTConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.plazofijo.bancaprivada.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class PlazoFijoBPrivDAOITTConfiguration {
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
    
    /** The plazo fijo B priv DAO. */
    @Autowired
    PlazoFijoBPrivDAO plazoFijoBPrivDAO;
    
    /**
     * Test consultar plazo fijo banca privada.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testConsultarPlazoFijoBancaPrivada() throws DAOException {
    	ConsultaPlazoFijoBPrivInEntity consultarInEntity = new ConsultaPlazoFijoBPrivInEntity();

    	consultarInEntity.setCuenta("7003521458");
    	consultarInEntity.setTipoPF("TR");
    	consultarInEntity.setFecha("2017-03-29");

        List<ConsultaPlazoFijoBPrivOutEntity> outEntity = new ArrayList<ConsultaPlazoFijoBPrivOutEntity>();
        outEntity = plazoFijoBPrivDAO.obtenerPlazosFijo(consultarInEntity);
        assertNotNull(outEntity);
        System.out.println();
    }
}
