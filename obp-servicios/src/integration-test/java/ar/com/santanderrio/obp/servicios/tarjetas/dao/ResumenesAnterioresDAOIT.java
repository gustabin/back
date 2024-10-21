package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.dao.ResumenesCitiDAOImpl;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.ResumenesAnterioresDAOIT.ResumenesAnterioresDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ResumenesAnterioresDAOITConfiguration.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = {
		"RESUMEN.ONDEMAND.CITI.URL = http://wasolympo.ar.bsch:9080/olympus-web/api/ondemand/cuentas",
		"RESUMEN.ONDEMAND.TARJETA.CITI.TIMEOUT = 10", "RESUMEN.ONDEMAND.TARJETA.CITI.MESES = 18",
		"RESUMEN.ONDEMAND.TARJETA.CITI.MASTER.FECHA.URL = http://localhost:8081/olympus-web/api/ondemand/tarjetas/master/",
		"RESUMEN.ONDEMAND.TARJETA.CITI.PUNTUAL.MASTER.URL = http://localhost:8081/olympus-web/api/ondemand/tarjetas/master/",
		"APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ResumenesAnterioresDAOIT extends IatxBaseDAOTest {

	@Autowired
	private ResumenesCitiDAO resumenesCitiDAO;

	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Configuration
	@ComponentScan(basePackageClasses = { ResumenesCitiDAOImpl.class, ContextHolder.class })
	public static class ResumenesAnterioresDAOITConfiguration {
		@Bean(name = "resumenesCitiDAO")
		public ResumenesCitiDAO resumenesCitiDAO() {
			return new ResumenesCitiDAOImpl();
		}
	}

	@Test
	public void obtenerDatosResumenMensualTarjeta() throws IatxException, DAOException {

		FechasResumenCitiIn fechasResumenCitiIn = new FechasResumenCitiIn();

		String formatearNumeroTarjeta = "4050710052641581";
		fechasResumenCitiIn.setTarjeta(formatearNumeroTarjeta);
		fechasResumenCitiIn.setDocumento("00008538911");
		fechasResumenCitiIn.setFechaCierreDesde("08/11/16");
		fechasResumenCitiIn.setFechaCierreHaste("28/05/18");
		fechasResumenCitiIn.setCuenta("0000000812209970");

		List<ResumenMensualTarjetaDTO> resumenMensualTarjetaList = null;
		resumenMensualTarjetaList = resumenesCitiDAO.consultarFechasTarjetas(fechasResumenCitiIn, "VISA");
		Assert.assertNotNull(resumenMensualTarjetaList);

	}

}