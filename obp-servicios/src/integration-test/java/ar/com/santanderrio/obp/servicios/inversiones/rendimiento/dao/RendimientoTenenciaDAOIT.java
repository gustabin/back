package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DatosRentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DetalleRentabilidadTotalEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao.RendimientoTenenciaDAOIT.RendimientoTenenciaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosAperturaGraficaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosComparativaCarteraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosDetalleMovimientosPeriodoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosDetalleRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosDetalleSubclasificacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosFiltroComparativoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosGraficoRentabilidad;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DatosRentailidadPeriodo;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendimientoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoTenenciaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity;
import junit.framework.TestCase;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		RendimientoTenenciaDAOITConfiguration.class, SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "INVERSIONES.RENDIMIENTO.URL=http://webbpsibehomo01:5499/PLDashboardService/",
		"INVERSIONES.RENDIMIENTO.TIMEOUT=10000", "INVERSIONES.RENDIMIENTO.PALABRAS.SENSIBLES.OUT=",
		"INVERSIONES.RENDIMIENTO.PALABRAS.SENSIBLES.IN=", "APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RendimientoTenenciaDAOIT {
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
	public static class RendimientoTenenciaDAOITConfiguration {

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
	public void obtenerRendimientoConsolidadoTest() throws DAOException {

		RendimientoConsolidadoRequestEntity request = new RendimientoConsolidadoRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosRendimientoConsolidadoRequestEntity datos = new DatosRendimientoConsolidadoRequestEntity();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("50115032");
		datos.setErrores(null);
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		// CuentaOPEntity cuenta1= new CuentaOPEntity();
		// cuenta1.setNroCuentaOP("0000000013557926");
		// cuenta1.setSucursal("000");
		// CuentaOPEntity cuenta2= new CuentaOPEntity();
		// cuenta2.setNroCuentaOP("454548");
		// cuenta2.setSucursal("250");
		// datos.getListaCuentas().add(cuenta2);
		// datos.getListaCuentas().add(cuenta1);
		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("50LLPK32");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@GPEKN2K");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00029063920");
		datosServicios.setFechaNac("19811006");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/ObtenerRendimientoConsolidado/");
		RendimientoTenenciaEntity rta = new RendimientoTenenciaEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, RendimientoTenenciaEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void aperturaGraficaTest() throws DAOException {
		AperturaGraficaRequestEntity request = new AperturaGraficaRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosAperturaGraficaRequest datos = new DatosAperturaGraficaRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraAConsultar("TOT");
		datos.setCodigoPeriodo("ANIO");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/ObtenerAperturaGrafica/");
		AperturaGraficaEntity rta = new AperturaGraficaEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, AperturaGraficaEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void GraficoRendimientoTest() throws DAOException {
		AperturaGraficaRequestEntity request = new AperturaGraficaRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosAperturaGraficaRequest datos = new DatosAperturaGraficaRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraAConsultar("TOT");
		datos.setCodigoPeriodo("ANIO");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerGraficoRendimiento/");
		GraficoRendimientoEntity rta = new GraficoRendimientoEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, GraficoRendimientoEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void RentabilidadTotalTest() throws DAOException {
		RentabilidadTotalRequestEntity request = new RentabilidadTotalRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosRentabilidadTotalRequestEntity datos = new DatosRentabilidadTotalRequestEntity();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraConsulta("TOT");
		datos.setCodigoPeriodo("30D");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerRentabilidadTotal/");
		DetalleRentabilidadTotalEntity rta = new DetalleRentabilidadTotalEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, DetalleRentabilidadTotalEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void RentabilidadPeriodoTest() throws DAOException {
		RentabilidadPeriodoRequestEntity request = new RentabilidadPeriodoRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosRentailidadPeriodo datos = new DatosRentailidadPeriodo();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraConsulta("TOT");
		datos.setCodigoPeriodo("ANIO");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerRentabilidadPeriodo/");
		RentabilidadPeriodoEntity rta = new RentabilidadPeriodoEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, RentabilidadPeriodoEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void FiltroPorFechaTest() throws DAOException {
		ObtenerFiltroPorFechaRequestEntity request = new ObtenerFiltroPorFechaRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosFiltroPorFechaRequestEntity datos = new DatosFiltroPorFechaRequestEntity();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraAConsultar("TOT");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerFiltroPorFecha/");
		FiltroPorFechaEntity rta = new FiltroPorFechaEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, FiltroPorFechaEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void FiltroCarteraTest() throws DAOException {
		ObtenerFiltroCarteraRequestEntity request = new ObtenerFiltroCarteraRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosFiltroCarteraRequestEntity datos = new DatosFiltroCarteraRequestEntity();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerFiltroCartera/");
		FiltroCarteraEntity rta = new FiltroCarteraEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, FiltroCarteraEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void DetalleRentabilidadTest() throws DAOException {
		DetalleRentabilidadRequestEntity request = new DetalleRentabilidadRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosDetalleRentabilidad datos = new DatosDetalleRentabilidad();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraConsulta("TOT");
		datos.setCodigoPeriodo("ANIO");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerDetalleRentabilidad/");
		DetalleRentabilidadEntity rta = new DetalleRentabilidadEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, DetalleRentabilidadEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void GraficoRentabilidadTest() throws DAOException {
		GraficoRentabilidadRequestEntity request = new GraficoRentabilidadRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosGraficoRentabilidad datos = new DatosGraficoRentabilidad();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setIp("1.1.1.2");
		datos.setUsuario("B046621");
		datos.setCarteraAConsultar("TOT");
		datos.setCodigoPeriodo("365D");
		datos.setPeriodoFechaInicial(null);
		datos.setPeriodoFechaFinal(null);
		datos.setMoneda("");
		datos.setListaCuentas(new ArrayList<CuentaOPEntity>());
		datos.setSegmento("BP");
		datos.setNup("1686083");
		datos.setClasificacion("PROD");
		datos.setSubclasificacion("");
		

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("I");

		datosServicios.setUsuarioId("B049684");
		datosServicios.setUsuarioAtrib("AA");
		datosServicios.setUsuarioPwd("17522NOA");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00008239583");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerGraficoRentabilidad/");
		GraficoRentabilidadEntity rta = new GraficoRentabilidadEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, GraficoRentabilidadEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void DetalleSubclasificacionTest() throws DAOException {
		DetalleSubclasificacionRequestEntity request = new DetalleSubclasificacionRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosDetalleSubclasificacionRequest datos = new DatosDetalleSubclasificacionRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraAConsultar("TOT");
		datos.setCodigoPeriodo("ANIO");
		datos.setClasificacion("PROD");
		datos.setSubclasificacion("FCI");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerDetalleSubclasificacion/");
		DetalleSubclasificacionEntity rta = new DetalleSubclasificacionEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, DetalleSubclasificacionEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void DetalleMovimientosPeriodoTest() throws DAOException {
		DetalleMovimientosPeriodoRequestEntity request = new DetalleMovimientosPeriodoRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosDetalleMovimientosPeriodoRequest datos = new DatosDetalleMovimientosPeriodoRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraAConsultar("TOT");
		datos.setCodigoPeriodo("ANIO");
		datos.setClasificacion("PROD");
		datos.setSubclasificacion("TV");
		datos.setAgrupacion("PRIV");
		datos.setInstrumento("BON");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerDetalleMovimientosPeriodo/");
		DetalleMovimientosPeriodoEntity rta = new DetalleMovimientosPeriodoEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, DetalleMovimientosPeriodoEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void FiltroComparativoTest() throws DAOException {
		FiltroComparativoRequestEntity request = new FiltroComparativoRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosFiltroComparativoRequest datos = new DatosFiltroComparativoRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCodigoPeriodo("ANIO");

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerFiltroComparativo/");
		FiltroComparativoEntity rta = new FiltroComparativoEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, FiltroComparativoEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
		
		
	}
	
	@Test
	public void ComparativaCarteraTest() throws DAOException {
		ComparativaCarteraRequestEntity request = new ComparativaCarteraRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosComparativaCarteraRequest datos = new DatosComparativaCarteraRequest();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCodigoPeriodo("ANIO");
		datos.setCodigoComparacion("MER");
		CuentaOPEntity cuenta = new CuentaOPEntity();
		cuenta.setNroCuentaOP("000090053223");
		cuenta.setSucursal("0");
		datos.setListaCuentas(cuenta);

		request.setDatos(datos);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");

		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/obtenerComparativaCartera/");
		ComparativaCarteraEntity rta = new ComparativaCarteraEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, ComparativaCarteraEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
		
		
	}
	
	@Test
	public void detalleRentabilidad() throws DAOException {
		DetalleRentabilidadRequestEntity request = new DetalleRentabilidadRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosDetalleRentabilidad datos = new DatosDetalleRentabilidad();
		datos.setSegmento(Segmento.BANCA_PERSONAL.getCodigo());
		datos.setNup("1884685");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCarteraConsulta("PF");
		datos.setCodigoPeriodo(null);
		datos.setPeriodoFechaInicial(null);
		datos.setPeriodoFechaFinal(null);
		datos.setMoneda(null);
		datos.setClasificacion(null);
		datos.setSubClasificacion(null);

		DatosServiciosEntity datosServicios = new DatosServiciosEntity();
		datosServicios.setCanalTipo("04");
		datosServicios.setCanalId("HTML");
		datosServicios.setCanalVersion("000");
		datosServicios.setSubcanalTipo("99");
		datosServicios.setSubcanalId("0001");
		datosServicios.setUsuarioTipo("03");
		datosServicios.setUsuarioId("00BIAB29");
		datosServicios.setUsuarioAtrib("  ");
		datosServicios.setUsuarioPwd("@CUT4FVY");
		datosServicios.setTipoPersona("I");
		datosServicios.setTipoId("N");
		datosServicios.setIdCliente("00013488020");
		datosServicios.setFechaNac("19500611");
		datosServicios.setErrores(null);
		datosServicios.setIp(null);
		datosServicios.setUsuario(null);

		datos.setDatosServicios(datosServicios);

		request.setDatos(datos);
		
		WebClient rendimientoConsolidadoService = restWebClient.obtenerClienteRest("INVERSIONES.RENDIMIENTO");
		rendimientoConsolidadoService.accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		rendimientoConsolidadoService.path("/ObtenerDetalleRentabilidad/");
		DetalleRentabilidadEntity rta = new DetalleRentabilidadEntity();
		try {
			rta = rendimientoConsolidadoService.post(request, DetalleRentabilidadEntity.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
		
		
	}
}
