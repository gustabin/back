/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

import java.util.List;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.dao.impl.AliasFavoritoProductoDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import ar.com.santanderrio.obp.servicios.database.dao.AliasFavoritoDAOIT.AliasFavoritoDAOITConfiguration;

/**
 * testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:1521:RIO147D");
 * ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AliasFavoritoDAOITConfiguration.class,
        AliasFavoritoProductoDAOImpl.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
@Ignore
public class AliasFavoritoDAOIT extends HbankBaseDAOIT {

    /** The alias favorito producto DAO. */
    @Autowired
    private AliasFavoritoProductoDAO aliasFavoritoProductoDAO;

    /**
     * The Class AliasFavoritoDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.database",
            "ar.com.santanderrio.obp.base.security.datasource", "ar.com.santanderrio.obp.base.security.aop.aspect",
            "ar.com.santanderrio.obp.base.context", "ar.com.santanderrio.obp.base.log" })
    public static class AliasFavoritoDAOITConfiguration {
        
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
     * Obtener alias favorito nup error.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void obtenerAliasFavoritoNupError() throws DAOException {
        List<AliasFavoritoProducto> listaAliasFavoritoProducto = aliasFavoritoProductoDAO.obtenerAliasFavoritoNup(2L);
        Assert.assertNotEquals(listaAliasFavoritoProducto.size(), 0);
    }

    /**
     * Actualiza alias.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void actualizaAlias() throws DAOException {
        aliasFavoritoProductoDAO.actualizaAlias(2L, "21", "pruebaAlias");
    }

    /**
     * Actualiza favorito.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void actualizaFavorito() throws DAOException {
        aliasFavoritoProductoDAO.actualizaFavorito(2L, "21", Boolean.TRUE);
    }
}
