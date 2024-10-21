package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.impl;

import static org.junit.Assert.assertNotNull;

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
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.ConsultaDeudaDAO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.impl.ConsultaDeudaDAOIT.ConsultaDeudaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;
import ar.com.santanderrio.obp.servicios.database.dao.HbClasifideudesaBaseDAOIT;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ConsultaDeudaDAOITConfiguration.class,BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.CLASIFDEUDORES.ID = 20020", "DB.CLASIFDEUDORES.DS = jdbc/hbclasifdeuddesa",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000",
        "DEUDORES.SCHEMA="})
public class ConsultaDeudaDAOIT extends HbClasifideudesaBaseDAOIT{
	
    /** The consulta deuda DAO. */
    @Autowired
    private ConsultaDeudaDAO consultaDeudaDAO;
    
    
    /**
     * The Class EstadisticaDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class ConsultaDeudaDAOITConfiguration {
        
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
    @Ignore
    public void consultaDeudaTest(){
    	try {
			ClasificacionDeuda clasificacionDeuda = consultaDeudaDAO.consultaDeuda("00005032");
			assertNotNull(clasificacionDeuda);
		} catch (DAOException e) {
			TestCase.fail();
		}
    }

}
