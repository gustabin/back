package ar.com.santanderrio.obp.servicios.plazofijo.dao;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DescripcionAccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;
import ar.com.santanderrio.obp.servicios.plazofijo.dao.ConsultaPlazoFijoDAOIT.ConsultaPlazoFijoDAOITTConfiguration;

/**
 * The Class ConsultaPlazoFijoDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ConsultaPlazoFijoDAOITTConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })

public class ConsultaPlazoFijoDAOIT extends HbankBaseDAOIT {

	 /**
	     * The Class AccionAlVencimientoDAOITTConfiguration.
	     */
	    @Configuration
	    @Configurable
	    @EnableAspectJAutoProxy
	    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao",
	            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
	            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
	            "ar.com.santanderrio.obp.base.log" })
	    public static class ConsultaPlazoFijoDAOITTConfiguration{
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
	    
	    /** The Accion Al Vencimiento DAO. */
	    @Autowired
	    ConsultaPlazoFijoDAO consultaPlazoFijoDAO;
	    
	    /**
	     * Test consultar plazo fijo banca privada.
	     *
	     * @throws DAOException the DAO exception
	     */
	    @Test
	    public void obtenerPlazosFijosTest() throws DAOException {

	        List<PlazoFijoEntity> outEntity = consultaPlazoFijoDAO.obtenerPlazosFijos();
	        assertNotNull(outEntity);
	        System.out.println();
	    }
	    
    @Test
    public void detalleAccionAlVencimiento() throws DAOException {

        List<String> listaCodigoAcciones = new ArrayList<String>();
        listaCodigoAcciones.add("DC");
        listaCodigoAcciones.add("RC");
        listaCodigoAcciones.add("SR");
        listaCodigoAcciones.add("RCI");

        for (String accion : listaCodigoAcciones) {
            String codigoAccion = accion;
            List<DescripcionAccionAlVencimientoOutEntity> listDescripcionAccionAlVencimientoOutEntity = consultaPlazoFijoDAO
                    .obtenerDescripcionAccionAlVencimiento(codigoAccion);
            Assert.assertNotNull(listDescripcionAccionAlVencimientoOutEntity);
        }
    }
}
