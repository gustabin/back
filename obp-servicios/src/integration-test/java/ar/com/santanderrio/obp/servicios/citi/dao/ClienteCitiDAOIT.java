package ar.com.santanderrio.obp.servicios.citi.dao;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.servicios.citi.dao.ClienteCitiDAOIT.ClienteCitiDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.citi.entities.RepuestaSPClienteCitiEntity;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;

// TODO: Auto-generated Javadoc
/**
 * The Class ClienteCitiDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ClienteCitiDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS =jdbc/seguridadbd", "DB.CITI.DS =jdbc/exciti", "DB.CITI.ID =20600", "DB.TIMEOUT = 30000",
        "APP.ENCODING=UTF-8" })

public class ClienteCitiDAOIT extends HbankBaseDAOIT {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteCitiDAOIT.class);

	@Autowired
	private ClienteCitiDAO clienteCitiDAO;

	/**
	 * The Class ClienteCitiDAOITConfiguration.
	 */
	@Configuration
	@Configurable
	@EnableAspectJAutoProxy
	@ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.citi.dao",
	        "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
	        "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
	        "ar.com.santanderrio.obp.base.log" })
	public static class ClienteCitiDAOITConfiguration {

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

	/** The Constant NUP. */
	private static final String NUP = "14331";

	/**
	 * Test consulta stored procedure clientes citi.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void testConsultarClienteCiti() throws DAOException {

		String nup = NUP;

		RepuestaSPClienteCitiEntity SPNup = clienteCitiDAO.consultarCitiClienteIdentificacion(nup);

		String testNUP = SPNup.getPoNup();

		Assert.assertEquals(testNUP, nup);
	}

}