package ar.com.santanderrio.obp.servicios.ordenes.dao;

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
import ar.com.santanderrio.obp.servicios.database.dao.HbAccionesBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao.OrdenDAOBpriv;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.OrdenOutEntity;

/**
 * The Class OrdenDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        OrdenDAOIT.OrdenDAOITConfiguration.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.BPRIV.ID = 41142", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.TIMEOUT = 30000", "DB.RACFINICIAL.ID=20090" })
public class OrdenDAOIT extends HbAccionesBaseDAOIT {

    /**
     * The Class OrdenDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.ordenes.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class OrdenDAOITConfiguration {

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

    /** The orden DAO. */
    @Autowired
    OrdenDAOBpriv ordenDAO;

    /**
     * Test cargar ordenes DAO.
     *
     * @throws DAOException
     *             the DAO exception
     */
//    @Test(expected = DAOException.class)
    @Test
    public void testCargarOrdenesDAO() throws DAOException {
        OrdenInEntity ordenInEntity = new OrdenInEntity();
        ordenInEntity.setCuenta("7003549140");
        ordenInEntity.setCodigoSis("PF");
        ordenInEntity.setFechaDesde("08-07-2018");
        ordenInEntity.setFechaHasta("07-08-2018");
        ordenInEntity.setOperaciones(null);
        @SuppressWarnings("unused")
		OrdenOutEntity out = ordenDAO.cargarOrdenes(ordenInEntity);
    }
    
    /**
     * Test cargar ordenes de plazo fijo DAO.
     *
     * @throws DAOException
     *             the DAO exception
     */
//    @Test(expected = DAOException.class)
    @Test
    public void testCargarOrdenesPFDAO() throws DAOException {
        OrdenInEntity ordenInEntity = new OrdenInEntity();
        ordenInEntity.setCuenta("7003549140");
        ordenInEntity.setCodigoSis("PF");
        ordenInEntity.setFechaDesde("08-07-2018");
        ordenInEntity.setFechaHasta("07-08-2018");
        ordenInEntity.setOperaciones(null);
        OrdenOutEntity out = ordenDAO.cargarOrdenes(ordenInEntity);
        Assert.assertNotNull(out);
    }
    
    
    
    @Test(expected = DAOException.class)
    public void testConfirmarOrdenPFDAO() throws DAOException {
    	ConfirmarOrdenInEntity confirmarOrdenInEntity = new ConfirmarOrdenInEntity();
    	confirmarOrdenInEntity.setCuenta("7003526910");
    	confirmarOrdenInEntity.setNumeroOrden("0193096");
    	confirmarOrdenInEntity.setCodigoSist("PF");
    	confirmarOrdenInEntity.setRegMant(null);
        ConfirmarOrdenOutEntity out = ordenDAO.confirmarOrden(confirmarOrdenInEntity);
        Assert.assertNotNull(out);
    }
    
    
}
