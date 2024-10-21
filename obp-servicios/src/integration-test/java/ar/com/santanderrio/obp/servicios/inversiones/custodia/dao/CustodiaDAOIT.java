package ar.com.santanderrio.obp.servicios.inversiones.custodia.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.database.dao.HbAccionesBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.dao.CustodiaDAOIT.CustodiaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaOutEntity;

/**
 * The Class CustodiaDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CustodiaDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.BPRIV.ID = 41142", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.TIMEOUT = 30000", "DB.RACFINICIAL.ID = 20090"})
public class CustodiaDAOIT extends HbAccionesBaseDAOIT {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustodiaDAOIT.class);

    /**
     * The Class CustodiaDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.custodia.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class CustodiaDAOITConfiguration {
        
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

    /** The custodia DAO. */
    @Autowired
    CustodiaDAO custodiaDAO;

    /**
     * Parses the date.
     *
     * @param date the date
     * @return the date
     */
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Test ver tenencia DAO.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testVerTenenciaDAO() throws DAOException {
        Date myDate = parseDate("10-11-2016");
        CustodiaInEntity custodiaInEntity = new CustodiaInEntity();
        custodiaInEntity.setCuenta("7003535154");
        custodiaInEntity.setFecha(myDate);
        custodiaInEntity.setTipoEspecie("FCI");
        CustodiaOutEntity out = new CustodiaOutEntity();
        out = custodiaDAO.verTenenciaOl(custodiaInEntity);
        
        org.junit.Assert.assertNotNull(out);

    }

    /**
     * Testver tenencia BPDAO.
     *
     * @throws DAOException the DAO exception
     */
    @Ignore
    @Test
    public void testverTenenciaBPDAO() throws DAOException {
        Date myDate = parseDate("25-12-2016");
        CustodiaBPInEntity custodiaBPInEntity = new CustodiaBPInEntity();
        custodiaBPInEntity.setCuenta("7003535154");
        custodiaBPInEntity.setFecha(myDate);
        custodiaBPInEntity.setCanal("79");
        CustodiaBPOutEntity out = new CustodiaBPOutEntity();
        out = custodiaDAO.verTenenciaBP(custodiaBPInEntity);
        
        org.junit.Assert.assertNotNull(out);
    }

}
