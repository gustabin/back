package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClientImpl;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.PoderCompraDAOIT.PoderCompraDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CrearAdhesionResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.FormCamposAdhesionRequest;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PoderCompraDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "PODER.COMPRA.URL=http://webbpsibedesa02:9000/MAPSAdhesionesService",
		"PODER.COMPRA.TIMEOUT=10000", "PODER.COMPRA.PALABRAS.SENSIBLES.OUT=", "APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PoderCompraDAOIT {

	@Autowired
	private Sign sign;

	@Autowired
	private RestWebClient restWebClient;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class GestionWSTestConfiguration.
	 */
	@Configuration
	@ComponentScan(basePackageClasses = { RestWebClientImpl.class, Sign.class, KeyStoreHelperImpl.class,
			ContextHolder.class,
			KeyStoreHelperImpl.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))

	public static class PoderCompraDAOITConfiguration {

		/**
		 * Key store factory.
		 *
		 * @return the key store factory
		 */
		@Bean
		public KeyStoreFactory keyStoreFactory() {
			return Mockito.mock(KeyStoreFactory.class, new Answer<KeyStore>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.mockito.stubbing.Answer#answer(org.mockito.invocation.
				 * InvocationOnMock)
				 */
				@Override
				public KeyStore answer(InvocationOnMock invocation) throws Throwable {
					KeyStore keyStore = new KeyStore();
					keyStore.setKeystoreType("JKS");
					keyStore.setKeystorePath(Object.class.getResource("/config/test/keyStore/hbkey.jks").getPath());
					keyStore.setKeystoreAlias("obp");
					keyStore.setKeystorePassword("hbpassword");
					return keyStore;
				}

			});
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
	public void crearAdhesionPoderCompra() throws DAOException {

		CrearAdhesionRequestEntity request = new CrearAdhesionRequestEntity();

		request.setDato("Prueba");
		request.setCanal("04");
		request.setSubCanal("0099");
		
		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		CrearAdhesionDatosRequest datos = new CrearAdhesionDatosRequest();
		datos.setNup("00200733");
		datos.setSegmento("RTL");
		datos.setIdServicio("PDC");
		datos.setOperacion("S");
		datos.setFormCompleted(1);
		datos.setIp("1");
		datos.setUsuario("B039514");
		FormCamposAdhesionRequest form1 = new FormCamposAdhesionRequest();
		form1.setId("CodigoMoneda");
		form1.setName("CodigoMoneda");
		form1.setType("input-text");
		form1.setValue("ARS");
		
		FormCamposAdhesionRequest form2 = new FormCamposAdhesionRequest();
		form2.setId("CuentaTitulos");
		form2.setName("CuentaTitulos");
		form2.setType("input-text");
		form2.setValue("2150888");
		
		FormCamposAdhesionRequest form3 = new FormCamposAdhesionRequest();
		form3.setId("NroCtaOperativa");
		form3.setName("NroCtaOperativa");
		form3.setType("input-text");
		form3.setValue("000000000451-0-0201-05-2001-ARS");
		
		datos.getFormCampos().add(form1);
		datos.getFormCampos().add(form2);
		datos.getFormCampos().add(form3);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("PODER.COMPRA");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/CrearAdhesion/");
		CrearAdhesionResponse rta = new CrearAdhesionResponse();
		try {
			rta = tenenciaValuadaService.post(request, CrearAdhesionResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
}
