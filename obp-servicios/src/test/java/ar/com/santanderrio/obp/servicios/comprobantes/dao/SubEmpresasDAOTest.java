package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAOTest.SubEmpresasDAOTestConfiguration;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.impl.SubEmpresasDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SubEmpresasDAOTestConfiguration.class,
		ArchivoDeRecursosDAOImpl.class, SubEmpresasDAOImpl.class, FilePath.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1" })
public class SubEmpresasDAOTest {
	@Autowired
	private SubEmpresasDAO subEmpresas;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class LegalDAOITConfiguration.
	 */
	@Configuration
	public static class SubEmpresasDAOTestConfiguration {

		@PostConstruct
		public void onStartup() {
			Properties properties = System.getProperties();
			properties.setProperty("config/OBP_UBICACION_PROPIEDADES", Object.class.getResource("/config").getPath());
		}

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
	public void obtenerSubEmpresasTest() throws DAOException {
		String listaRes = subEmpresas.obtenerSubEmpresa("AD07");
		Assert.assertEquals("ADMV", listaRes);
	}

	@Test
	public void obtenerSubEmpresasSinSubempresasTest() throws DAOException {
		String listaRes = subEmpresas.obtenerSubEmpresa("RNI");
		Assert.assertNull(listaRes);
	}

	@Test
	public void obtenerSubEmpresasSinParamTest() throws DAOException {
		List<String[]> listaRes = subEmpresas.obtenerSubEmpresas();
		Assert.assertNotNull(listaRes);
		Assert.assertTrue(listaRes.size() > 0);
	}

}
