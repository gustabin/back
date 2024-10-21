/**
 * 
 */
package ar.com.santanderrio.obp.servicios.session;

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
import ar.com.santanderrio.obp.servicios.database.dao.AliasFavoritoDAOIT.AliasFavoritoDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;
import ar.com.santanderrio.obp.servicios.session.dao.SessionControlDAO;
import ar.com.santanderrio.obp.servicios.session.dao.impl.SessionControlDAOImpl;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;

/**
 * testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:1521:RIO147D");
 * ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { AliasFavoritoDAOITConfiguration.class,
		SessionControlDAOImpl.class, BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
		"DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
		"MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })
@Ignore
public class SessionControlDAOIT extends HbankBaseDAOIT {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionControlDAOIT.class);

	/** The alias favorito producto DAO. */
	@Autowired
	private SessionControlDAO sessionControlDAO;

	/**
	 * The Class AliasFavoritoDAOITConfiguration.
	 */
	@Configuration
	@Configurable
	@EnableAspectJAutoProxy
	@ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.database",
			"ar.com.santanderrio.obp.base.security.datasource", "ar.com.santanderrio.obp.base.security.aop.aspect",
			"ar.com.santanderrio.obp.base.context", "ar.com.santanderrio.obp.base.log" })
	public static class SessionControlDAOITConfiguration {

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
	 * @return the session control DTO by nup
	 * @throws DAOException             the DAO exception
	 */
	@Test
	public void getSessionControlDTOByNup() throws DAOException {
		SessionControlDTO sessionControlDTO = sessionControlDAO.get(276937L);
		LOGGER.info("Dto retornado {}.", sessionControlDTO);
	}

	/**
	 * Save session control DTO by nup.
	 *
	 * @throws DAOException the DAO exception
	 */
	@Test
	public void saveSessionControlDTOByNup() throws DAOException {
	    sessionControlDAO.saveOrUpdate(276937L, "poto");
	}

}
