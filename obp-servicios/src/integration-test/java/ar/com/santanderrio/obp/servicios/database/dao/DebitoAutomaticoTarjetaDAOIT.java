/**
 * 
 */
package ar.com.santanderrio.obp.servicios.database.dao;

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
import ar.com.santanderrio.obp.config.ws.CacheConfig;
import ar.com.santanderrio.obp.servicios.database.dao.DebitoAutomaticoTarjetaDAOIT.DebitoAutomaticoTarjetaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao.DebitoAutomaticoTarjetaDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.ClienteDebitoTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities.SolicitudAdhesionDebitoTarjetaEntity;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.ClienteDebitoTarjetaInEntityMock;

/**
 * @author florencia.n.martinez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		DebitoAutomaticoTarjetaDAOITConfiguration.class, BackEndSecurityConfig.class, CacheConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
		"DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.PORTAL.USER = PORTAL", "DB.PORTAL.PASSWORD = desadev290",
		"DB.PORTAL.ID = 20019", "DB.PORTAL.DS = jdbc/PORTALDBDESA", "MENSAJE_ERROR_GENERICO=Error generico",
		"DB.TIMEOUT = 30000" })
@Ignore
public class DebitoAutomaticoTarjetaDAOIT extends HbankBaseDAOIT {

	@Autowired
	private DebitoAutomaticoTarjetaDAO debitoAutomaticoTarjetaDAO;

	@Configuration
	@Configurable
	@EnableAspectJAutoProxy
	@ComponentScan(basePackages = { "package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dao",
			"ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
			"ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
			"ar.com.santanderrio.obp.base.log" })
	public static class DebitoAutomaticoTarjetaDAOITConfiguration {

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
	public void solicitarAdhesionTarjetaOK() throws DAOException {
		ClienteDebitoTarjetaInEntity datosClienteDebitoTarjeta = ClienteDebitoTarjetaInEntityMock.completarInfoOK();
		SolicitudAdhesionDebitoTarjetaEntity solicitud = debitoAutomaticoTarjetaDAO
				.solicitarAdhesionTarjeta(datosClienteDebitoTarjeta);

		Assert.assertNotNull(solicitud);
	}
}
