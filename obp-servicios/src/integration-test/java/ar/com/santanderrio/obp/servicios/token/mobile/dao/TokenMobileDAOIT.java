package ar.com.santanderrio.obp.servicios.token.mobile.dao;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.EmptyResultDataAccessException;
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
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.token.mobile.dao.TokenMobileDAOIT.TokenMobileDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;

/**
 * The Class ContratoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TokenMobileDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.ESTADISTICAS.ID = 40011",
        "DB.TIMEOUT = 30000", "DB.RACFINICIAL.ID = 20090" , "TAGS.A.ELIMINAR.DB=" })

public class TokenMobileDAOIT extends HbankBaseDAOIT {
    
    /**
     * The Class ContratoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.token.mobile.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class TokenMobileDAOITConfiguration {
        
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
    TokenMobileDAO tokenMobileDAO;

    /**
     * Test ver contrato DAO.
     * @throws EmptyResultDataAccessException 
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testConsultarTokenMobileOK() throws DAOException {
        String nup = "276937";
        TokenMobile tokenMobile = tokenMobileDAO.consultarTokenMobile(nup);
        assertThat(tokenMobile.getToken(), notNullValue());
    }

    /**
     * Test ver contrato DAO.
     * @throws EmptyResultDataAccessException 
     *
     * @throws DAOException the DAO exception
     */
    @Test(expected = DAOException.class)
    public void testConsultarTokenMobileSinValor() throws DAOException {
        String nup = "126345";
        tokenMobileDAO.consultarTokenMobile(nup);
    }

}