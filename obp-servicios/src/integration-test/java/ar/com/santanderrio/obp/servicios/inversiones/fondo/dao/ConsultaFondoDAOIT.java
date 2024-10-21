package ar.com.santanderrio.obp.servicios.inversiones.fondo.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;

/**
 * The Class ConsultaFondoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultaFondoDAOIT.ConsultaFondoDAOITConfiguration.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.ESTADISTICAS.ID = 40011",
        "DB.TIMEOUT = 30000", "VERFONDOS.VERSION=_V2", "DB.RACFINICIAL.ID = 20090" })
public class ConsultaFondoDAOIT extends HbankBaseDAOIT {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFondoDAOIT.class);

    /**
     * The Class ConsultaFondoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class ConsultaFondoDAOITConfiguration {
        
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

    /** The consulta fondo DAO. */
    @Autowired
    ConsultaFondoDAO consultaFondoDAO;

    /**
     * Test consultar fondo DAO.
     *
     * @throws DAOException the DAO exception
     */
   @Test
    public void testConsultarFondoPorCodigo() throws DAOException {
   
        ConsultaFondoOutEntity consultaFondoOutEntity = consultaFondoDAO.obtenerPorCodigo("001");
        assertNotNull(consultaFondoOutEntity);

    }
    
    /**
     * Test consultar todos los fondos para cache
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testConsultarTodosFondos() throws DAOException {
         List<ConsultaFondoOutEntity> consultaFondoOutEntity = new ArrayList<ConsultaFondoOutEntity>();
         consultaFondoOutEntity = consultaFondoDAO.obtenerFondos();
         assertNotNull(consultaFondoOutEntity);
    }
    

    @Test
    public void testConsultarFondosHabilitadosTR() throws DAOException {
         List<ConsultaFondoOutEntity> consultaFondoOutEntity = new ArrayList<ConsultaFondoOutEntity>();
         Predicate predicate = new Predicate() {
        	  public boolean evaluate(Object object) {
        		  ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity)object;
        		  return fondo.getHabilitarTransferencia().equals("1");
        	  }
        	};
        	 
         consultaFondoOutEntity = consultaFondoDAO.obtenerFondos(predicate);
         assertNotNull(consultaFondoOutEntity);
    }
    
    
    
  
    
}
