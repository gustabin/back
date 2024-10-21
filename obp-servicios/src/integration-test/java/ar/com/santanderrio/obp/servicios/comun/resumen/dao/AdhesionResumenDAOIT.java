package ar.com.santanderrio.obp.servicios.comun.resumen.dao;


import org.junit.Assert;
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
import ar.com.santanderrio.obp.servicios.comun.resumen.dao.AdhesionResumenDAOIT.AdhesionResumenDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comun.resumen.entity.ResumenInEntity;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AdhesionResumenDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.DS = jdbc/hbalias", "DB.ESTADISTICAS.ID = 40011",
        "DB.TIMEOUT = 30000" })

public class AdhesionResumenDAOIT extends HbankBaseDAOIT {

    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.resumen.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class AdhesionResumenDAOITConfiguration {

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }

    @Autowired
    ResumenDAO resumenDAO;

    @Test
    public void testAgregarMotivoDAO() throws DAOException {
        ResumenInEntity resumenInEntity = new ResumenInEntity();
        resumenInEntity.setNup("11903");
        resumenInEntity.setMotivo("No se como se usa mi computadora para ver los resumenes");
        String respuesta = resumenDAO.grabarMotivo(resumenInEntity);
        Assert.assertEquals("OK",respuesta);
    }

}