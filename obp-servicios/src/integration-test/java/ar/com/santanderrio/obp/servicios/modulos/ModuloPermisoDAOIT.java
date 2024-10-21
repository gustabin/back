/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import org.springframework.test.util.ReflectionTestUtils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.modulos.ModuloPermisoDAOIT.ModuloPermisoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAOImpl;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class ModuloPermisoDAOIT.
 *
 * @author sergio.e.goldentair
 */
@ActiveProfiles(value = Profiles.TEST)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ModuloPermisoDAOITConfiguration.class,
        CacheConfig.class, BackEndSecurityConfig.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
public class ModuloPermisoDAOIT extends HbankBaseDAOIT {

    /** The moduloExcluidos DAO. */
    @Autowired
    @InjectMocks
    private ModuloPermisoDAO moduloPermisoDAO;

    /**
     * Inicializar properties.
     */
    @BeforeClass
    public static void init() {
        Properties properties = System.getProperties();
        properties.setProperty("config/OBP_UBICACION_PROPIEDADES",
                Object.class.getResource("/integration-test/config").getPath());
    }

    /**
     * The Class ModuloExcluidosDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.comun",
            "ar.com.santanderrio.obp.servicios.modulos.dao", "ar.com.santanderrio.obp.base.database",
            "ar.com.santanderrio.obp.base.security.datasource", "ar.com.santanderrio.obp.base.security.aop.aspect",
            "ar.com.santanderrio.obp.base.context", "ar.com.santanderrio.obp.base.log" })
    public static class ModuloPermisoDAOITConfiguration {

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

    /**
     * Obtener modulos permisos.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerModulosPermisosDesdeBase() throws DAOException {
        Map<String, ModuloPermiso> modulosExcluidos = moduloPermisoDAO.obtenerModulosPermisos();
        Map<String, ModuloPermiso> modulosExcluidos2 = moduloPermisoDAO.obtenerModulosPermisos();
        Assert.assertNotNull(modulosExcluidos);
        Assert.assertNotNull(modulosExcluidos2);
    }

    /**
     * Obtener modulos permisos desde un archivo con campos separados por pipes.
     * 
     * @throws DAOException
     * 
     * @throws Exception
     */
    @Test
    public void obtenerModulosPermisosDesdeFile() throws DAOException {
        ReflectionTestUtils.setField(this.<ModuloPermisoDAOImpl> getTargetObject(moduloPermisoDAO), "permisosPorBase",
                Boolean.FALSE);

        Map<String, ModuloPermiso> modulosExcluidos = moduloPermisoDAO.obtenerModulosPermisos();
        Map<String, ModuloPermiso> modulosExcluidos2 = moduloPermisoDAO.obtenerModulosPermisos();
        Assert.assertNotNull(modulosExcluidos);
        Assert.assertNotNull(modulosExcluidos2);
    }

    /**
     * Limpiar modulos permisos.
     */
    @Test
    @Ignore
    public void limpiarModulosPermisos() {
        moduloPermisoDAO.limpiarModulosPermisos();
    }
}
