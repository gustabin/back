package ar.com.santanderrio.obp.servicios.inversiones.maps.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.ServiciosDeInversionDAOTest.ServiciosDeInversionDAOTestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(Profiles.TEST)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SecurityProviderConfig.class,
        ServiciosDeInversionDAOTestConfiguration.class })
@TestPropertySource(properties = { "INVERSIONES.MAPS.MOCK.FILE=/temp/mock.txt" })
public class ServiciosDeInversionDAOTest {

	/** The MapServiceMockDAOImpl. */
	private MapServiceDAO mapServiceDAO = new MapServiceMockDAOImpl();
	
	public static class ServiciosDeInversionDAOTestConfiguration {
	    
	    
	}
	@Ignore
	@Test
	public void altaAdhesion() throws DAOException {
		AltaAdhesionMapsRequestEntity request = new AltaAdhesionMapsRequestEntity();
		AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
		datos.setSegmento("RTL");
		datos.setNup("01390639");
		request.setDatos(datos);
		
	    AltaAdhesionMapsResponse altaAdhesionMapsResponse = mapServiceDAO.altaAdhesionMaps(request);
		Assert.assertNotNull(altaAdhesionMapsResponse);
	}

}
