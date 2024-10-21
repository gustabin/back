package ar.com.santanderrio.obp.servicios.comun.contrato.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAOIT.ContratoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;

/**
 * The Class ContratoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ContratoDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.ESTADISTICAS.ID = 40011",
        "DB.TIMEOUT = 30000" })

public class ContratoDAOIT extends HbankBaseDAOIT {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContratoDAOIT.class);

    /**
     * The Class ContratoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.contrato.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class ContratoDAOITConfiguration {
        
        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
        
        @Bean
        public IatxComm iatxComm() {
            return Mockito.mock(IatxComm.class);
        }
    }

    /** The contrato DAO. */
    @Autowired
    ContratoDAO contratoDAO;

    /**
     * Test ver contrato DAO.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testVerContratoDAO() throws DAOException {
        ContratoInEntity contratoInEntity = new ContratoInEntity();
        contratoInEntity.setDni("00013488020");
        contratoInEntity.setFechaNac("19570812");
        contratoDAO.verContrato(contratoInEntity, CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);

    }

    /**
     * Test aceptar contrato DAO.
     *
     * @throws DAOException the DAO exception
     */
    @Test
     @Ignore
    public void testAceptarContratoDAO() throws DAOException {
        ContratoInEntity contratoInEntity = new ContratoInEntity();
        contratoInEntity.setDni("00013488020");
        contratoInEntity.setFechaNac("19570812");
        contratoDAO.aceptarContrato(contratoInEntity, CampoEnum.CPI_FCI_C, SinonimoEnum.NO_REPETIDO);
    }

}