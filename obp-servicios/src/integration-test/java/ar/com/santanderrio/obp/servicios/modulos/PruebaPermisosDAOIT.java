/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.config.ws.MethodSecurityConfig;
import ar.com.santanderrio.obp.config.ws.SecurityConfig;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.PruebaPermisosDAOIT.ModuloPermisoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManagerImpl;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPermissionEvaluator;

/**
 * The Class ModuloPermisoDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ModuloPermisoDAOITConfiguration.class,
        MethodSecurityConfig.class, SecurityConfig.class, CustomPermissionEvaluator.class,
        ModuloPermisoManagerImpl.class, ModuloPermisoBOImpl.class, CacheConfig.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
public class PruebaPermisosDAOIT extends HbankBaseDAOIT {

    @Autowired
    private PruebaPermisosDAO pruebaPermisosDAO;

    @Before
    public void preTest() {
        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setNup("13236881");

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
                AccionController.IR_INICIO_CUENTAS.getDescripcion());
        Set<SimpleGrantedAuthority> updatedAuthorities = new HashSet<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(resumenCliente.getDni(), resumenCliente, updatedAuthorities));

    }

    /**
     * The Class ModuloExcluidosDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.modulos.dao",
            "ar.com.santanderrio.obp.servicios.factory", "ar.com.santanderrio.obp.servicios.modulos",
            "ar.com.santanderrio.obp.servicios.seguridad", "ar.com.santanderrio.obp.base.database",
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

        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
    }

    /**
     * Obtener modulos permisos.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerModulosPermisos() {
        pruebaPermisosDAO.probarPermiso();
        pruebaPermisosDAO.probarPermiso();
    }
}