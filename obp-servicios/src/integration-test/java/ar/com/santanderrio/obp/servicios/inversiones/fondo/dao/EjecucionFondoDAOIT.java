package ar.com.santanderrio.obp.servicios.inversiones.fondo.dao;

import java.math.BigDecimal;

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
import ar.com.santanderrio.obp.servicios.inversiones.fondo.dao.EjecucionFondoDAOIT.EjecucionFondoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaOutEntity;

/**
 * The Class EjecucionFondoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { EjecucionFondoDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.BPRIV.ID = 41142", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.TIMEOUT = 30000" , "TAGS.A.ELIMINAR.DB=", "DB.RACFINICIAL.ID = 20090"})
public class EjecucionFondoDAOIT extends HbAccionesBaseDAOIT {

    /**
     * The Class EjecucionFondoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class EjecucionFondoDAOITConfiguration {
        
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

    /** The fondoBPriv DAO. */    
    @Autowired
    private FondoBPrivDAO fondoBPrivDAO;

    /**
     * Ejecutar fondo test.
     *
     * @throws DAOException the DAO exception
     */
    @Test(expected=DAOException.class)
    public void ejecutarFondoTest() throws DAOException {
        EjecucionFondoBancaPrivadaInEntity ejecucionFondoBancaPrivadaInEntity = new EjecucionFondoBancaPrivadaInEntity();
        ejecucionFondoBancaPrivadaInEntity.setTipo("SU");
        ejecucionFondoBancaPrivadaInEntity.setNroCuenta("7003523508");
        ejecucionFondoBancaPrivadaInEntity.setEspecie("BR07");
        ejecucionFondoBancaPrivadaInEntity.setMoneda("ARG");
        ejecucionFondoBancaPrivadaInEntity.setCuotasPartes("");
        ejecucionFondoBancaPrivadaInEntity.setCapital(BigDecimal.valueOf(1500));
        ejecucionFondoBancaPrivadaInEntity.setEspecieDestino("");
        ejecucionFondoBancaPrivadaInEntity.setIsPerfilInversion("S0");
        ejecucionFondoBancaPrivadaInEntity.setUsuarioRacf("ONLINEBP");
        ejecucionFondoBancaPrivadaInEntity.setPasswordRacf("DV09SA10");

        EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity = fondoBPrivDAO.suscribir(ejecucionFondoBancaPrivadaInEntity);
        
        System.out.println(ejecucionFondoBancaPrivadaOutEntity);
        

    }

}