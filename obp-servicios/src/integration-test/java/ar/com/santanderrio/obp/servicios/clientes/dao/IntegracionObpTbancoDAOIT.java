/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao;

import org.junit.Assert;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.servicios.clientes.dao.IntegracionObpTbancoDAOIT.IntegracionObpTbancoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.clientes.dao.impl.IntegracionObpTbancoDAOImpl;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;

/**
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegracionObpTbancoDAOITConfiguration.class, BackEndSecurityConfig.class, IntegracionObpTbancoDAOImpl.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
		"DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
		"MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
public class IntegracionObpTbancoDAOIT extends HbankBaseDAOIT {

	@InjectMocks
	@Autowired
	private IntegracionObpTbancoDAO integracionObpTbancoDAO;

	/**
	 * The Class LegalDAOITConfiguration.
	 */
	@Configuration
	@Configurable
	@EnableAspectJAutoProxy
	@ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.database",
			"ar.com.santanderrio.obp.base.security.datasource", "ar.com.santanderrio.obp.base.security.aop.aspect",
			"ar.com.santanderrio.obp.base.context", "ar.com.santanderrio.obp.base.log" })
	public static class IntegracionObpTbancoDAOITConfiguration {

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

	@Test
	public void usuarioHabilitadoAccederTbanco() throws DAOException {
		Boolean tbancoOK = integracionObpTbancoDAO.usuarioHabilitadoAccederTbanco("02615492");
		Assert.assertTrue(tbancoOK);
	}
}
