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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaFiltroInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaIndicesResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.ConsultaInformacionMercadoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaFiltroInformacionMercadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaIndicesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosConsultaInformacionMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.DatosVariacionInfoMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.entities.VariacionInfoMercadoRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAOIT.OrdenesTitulosDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CompraVtaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ComptaVtaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaCuentaTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOrdenesRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaSuscripcionSaldoPDCResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosCompraVtaTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaCuentaTitulosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperacionesTextRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaSuscripcionSaldoPDCRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaTitulosOrdenes;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.ConsultaAperturaEspecieResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities.DatosConsultaAperturaEspecieRequestEntity;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { OrdenesTitulosDAOITConfiguration.class,
		SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "ORDENES.TENENCIAS.URL=http://webbpsibedesa01.ar.bsch:6100/MWCanalesService",
		"ORDENES.TENENCIAS.TIMEOUT=10000", "ORDENES.TENENCIAS.PALABRAS.SENSIBLES.OUT=", "APP.ENCODING = UTF-8" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrdenesTitulosDAOIT {

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
	public static class OrdenesTitulosDAOITConfiguration {

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
				 * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.
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
	public void consultaCuentaTitulos() throws DAOException {

		ConsultaCuentaTitulosRequestEntity request = new ConsultaCuentaTitulosRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosConsultaCuentaTitulosRequestEntity datos = new DatosConsultaCuentaTitulosRequestEntity();
		datos.setSegmento("RTL");
		datos.setTipoOperacion("C");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.getListaCuentasTitulos().add("123456");

		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/ConsultaCuentaTitulo/");
		ConsultaCuentaTitulosResponse rta = new ConsultaCuentaTitulosResponse();
		try {
			rta = tenenciaValuadaService.post(request, ConsultaCuentaTitulosResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultaAperturaEspecie() throws DAOException {

		ConsultaAperturaEspecieRequestEntity request = new ConsultaAperturaEspecieRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosConsultaAperturaEspecieRequestEntity datos = new DatosConsultaAperturaEspecieRequestEntity();
		datos.setSegmento("RTL");
		datos.setTipoOperacion("V");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCodigoEspecieRossi("92584");
		datos.setCuentaTitulo(2013893);
		datos.setFecha("20180307");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/ConsultaAperturaEspecie/");
		ConsultaAperturaEspecieResponse rta = new ConsultaAperturaEspecieResponse();
		try {
			rta = tenenciaValuadaService.post(request, ConsultaAperturaEspecieResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultarOperacionesText() throws DAOException {

		RequestConsultarOperacionesTextEntity request = new RequestConsultarOperacionesTextEntity();
		request.setDato("Prueba");
		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {
			throw new DAOException(e);
		}
		request.setFirma(new String(firma));

		DatosConsultaOperacionesTextRequestEntity datos = new DatosConsultaOperacionesTextRequestEntity();
		datos.setSegmento("PRIV");
		datos.setCno(" ");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setFechaDesde("12/12/2017");
		datos.setFechaHasta("12/12/2017");
		datos.setCuentasTitulo("123456");
		datos.setNup("123456");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/ConsultaDeOperacionesText/");
		ConsultarOperacionesTextResponse rta = new ConsultarOperacionesTextResponse();
		try {
			rta = tenenciaValuadaService.post(request, ConsultarOperacionesTextResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void compraVtaTitulos() throws DAOException {

		ComptaVtaTitulosRequestEntity request = new ComptaVtaTitulosRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));

		DatosCompraVtaTitulosEntity datos = new DatosCompraVtaTitulosEntity();
		datos.setTipoAccion("S");
		datos.setTipoEspecie("SHS");
		datos.setSucursalCuenta("0123");
		datos.setCuentaTitulo("20615417");
		datos.setTipoCtaOper("09");
		datos.setMonedaOperacion("ARS");
		datos.setNumeroCuenta("0185987");
		datos.setTipoOperacion("C");
		datos.setCantidadTitulo("150.0");
		datos.setEspecieCodigo("40115");
		datos.setImporteDebitoCredito("0.0");
		datos.setCotizacionLimite("17.0");
		datos.setCotizacion("16.0");
		datos.setPlazo("2");
		datos.setOperadorAlta("HB");
		datos.setSegmento("RTL");
		datos.setFechaPrecioRef("2018-02-26");
		datos.setHoraPrecioRef("10:55:00");
		datos.setIp("127.0.0.1");
		datos.setUsuario("B042583");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/CompraVtaTitulosValores/");
		CompraVtaTitulosResponse rta = new CompraVtaTitulosResponse();
		try {
			rta = tenenciaValuadaService.post(request, CompraVtaTitulosResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultaSaldoPDC() throws DAOException {

		ConsultaSuscripcionSaldoPDCRequest request = new ConsultaSuscripcionSaldoPDCRequest();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));
		DatosConsultaSuscripcionSaldoPDCRequest datos = new DatosConsultaSuscripcionSaldoPDCRequest();
		datos.setCuentaTitulos("123456");
		datos.setProductoInversion("SHS");
		datos.setSegmento("RTL");
		datos.setIp("127.0.0.1");
		datos.setUsuario("B042583");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.path("/ConsultaSuscripcionSaldoPDC/");
		ConsultaSuscripcionSaldoPDCResponse rta = new ConsultaSuscripcionSaldoPDCResponse();
		try {
			rta = tenenciaValuadaService.post(request, ConsultaSuscripcionSaldoPDCResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultaDeOrdenes() throws DAOException {

		ConsultaOrdenesRequestEntity request = new ConsultaOrdenesRequestEntity();
		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}
		request.setFirma(new String(firma));

		DatosConsultaTitulosOrdenes datosConsultaTitulosOrdenes = new DatosConsultaTitulosOrdenes();

		datosConsultaTitulosOrdenes.setCuentaTitulos("10615735");
		datosConsultaTitulosOrdenes.setEstado("0");

		datosConsultaTitulosOrdenes.setFechaDesde("01/01/2017");
		datosConsultaTitulosOrdenes.setFechaHasta("03/26/2018");

		datosConsultaTitulosOrdenes.setIp("180.166.16.134");
		datosConsultaTitulosOrdenes.setUsuario("system");
		datosConsultaTitulosOrdenes.setNumeroOrden("039903");
		datosConsultaTitulosOrdenes.setNup("60001194");
		datosConsultaTitulosOrdenes.setCanal("04");
		datosConsultaTitulosOrdenes.setSubCanal("0099");

		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("0000");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("0000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datosConsultaTitulosOrdenes.setCabecera(cabecera);

		request.setDatos(datosConsultaTitulosOrdenes);

		WebClient tenenciaValuadaService = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		tenenciaValuadaService.accept(MediaType.APPLICATION_JSON);
		tenenciaValuadaService.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		DatosConsultaOrdenes rta = new DatosConsultaOrdenes();
		try {
			rta = tenenciaValuadaService.post(request, DatosConsultaOrdenes.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}

	}

	@Test
	public void consultaFiltrosInfoMercado() throws DAOException {

		ConsultaFiltroInformacionMercadoRequestEntity request = new ConsultaFiltroInformacionMercadoRequestEntity();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));
		request.setCanal("04");
		request.setSubCanal("0099");

		DatosConsultaFiltroInformacionMercadoRequestEntity datos = new DatosConsultaFiltroInformacionMercadoRequestEntity();
		datos.setSegmento("RTL");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setCanal("04");
		datos.setSubCanal("0099");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient consultaFiltroInfoMercado = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		consultaFiltroInfoMercado.accept(MediaType.APPLICATION_JSON);
		consultaFiltroInfoMercado.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		consultaFiltroInfoMercado.path("/ConsultaFiltroInformacionMercado/");
		ConsultaFiltroInformacionMercadoResponse rta = new ConsultaFiltroInformacionMercadoResponse();
		try {
			rta = consultaFiltroInfoMercado.post(request, ConsultaFiltroInformacionMercadoResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultaIndices() throws DAOException {

		ConsultaIndicesRequest request = new ConsultaIndicesRequest();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));
		request.setCanal("04");
		request.setSubCanal("0099");

		DatosConsultaIndicesRequestEntity datos = new DatosConsultaIndicesRequestEntity();
		datos.setSegmento("RTL");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient consultaIndicesResponse = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		consultaIndicesResponse.accept(MediaType.APPLICATION_JSON);
		consultaIndicesResponse.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		consultaIndicesResponse.path("/ConsultaIndices/");
		ConsultaIndicesResponse rta = new ConsultaIndicesResponse();
		try {
			rta = consultaIndicesResponse.post(request, ConsultaIndicesResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}

	@Test
	public void consultaInformacionMercado() throws DAOException {

		ConsultaInformacionMercadoRequest request = new ConsultaInformacionMercadoRequest();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));
		request.setCanal("04");
		request.setSubCanal("0099");

		DatosConsultaInformacionMercadoRequest datos = new DatosConsultaInformacionMercadoRequest();
		datos.setSegmento("RTL");
		datos.setIp("180.166.16.134");
		datos.setUsuario("system");
		datos.setAgrupamientoId("2");
		datos.setMonedaNegociacion("ARS");
		datos.setPlazo(48);
		datos.setCuentaTitulo("");
		datos.setTipoBusqueda("T");
		datos.setCanal("04");
		datos.setSubCanal("0099");

		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalId("00");
		cabecera.setCanalTipo("04");
		cabecera.setCanalVer("000");
		cabecera.setFechaNac("");
		cabecera.setIdUsConc("00000000");
		cabecera.setIndSincro("1");
		cabecera.setNroIdCliente("");
		cabecera.setNumSec("00000000");
		cabecera.setNup("00001312");
		cabecera.setSubCanalId("HTML");
		cabecera.setSubCanalTipo("99");
		cabecera.setTipoIdCliente("");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioId("AABD12");
		cabecera.setUsuarioPwd("");
		cabecera.setUsuarioTipo("00");
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient consultaIndicesResponse = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		consultaIndicesResponse.accept(MediaType.APPLICATION_JSON);
		consultaIndicesResponse.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		consultaIndicesResponse.path("/ConsultaInformacionMercado/");
		ConsultaInformacionMercadoResponse rta = new ConsultaInformacionMercadoResponse();
		try {
			rta = consultaIndicesResponse.post(request, ConsultaInformacionMercadoResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}
	}
	
	@Test
	public void variacionInfoMercadoTest() throws DAOException {
		VariacionInfoMercadoRequest request = new VariacionInfoMercadoRequest();

		request.setDato("Prueba");

		byte[] firma;
		try {
			firma = sign.buildB64Signature("Prueba".getBytes("UTF-8"), "MYA", true);
		} catch (UnsupportedEncodingException e) {

			throw new DAOException(e);
		}

		request.setFirma(new String(firma));
		request.setCanal("79");
		request.setSubCanal("0000");
		
		DatosVariacionInfoMercadoRequest datos = new DatosVariacionInfoMercadoRequest();
		
		datos.setSegmento("RTL");
		datos.setIp("180.166.95.133");
		datos.setUsuario("system");
		datos.setCodigoEspecieRossi("0019");
		datos.setTipoEspecie("SHS");
		datos.setMonedaCotizacion("ARS");
		datos.setPlazo("24");
		datos.setCanal("04");
		datos.setSubcanal("0099");
		
		CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();
		cabecera.setCanalTipo("04");
		cabecera.setSubCanalId("HTML");
		cabecera.setCanalVer("000");
		cabecera.setSubCanalTipo("99");
		cabecera.setCanalId("00");
		cabecera.setUsuarioTipo("00");
		cabecera.setUsuarioId("00DNQB32");
		cabecera.setUsuarioAttr("");
		cabecera.setUsuarioPwd("@DM71ZE3");
		cabecera.setIdUsConc("00000000");
		cabecera.setNumSec("00000000");
		cabecera.setNup("02890069");
		cabecera.setIndSincro("1");
		cabecera.setTipoCliente("I");
		cabecera.setTipoIdCliente("N");
		cabecera.setNroIdCliente("00006469990");
		cabecera.setFechaNac("19680916");
		
		datos.setCabecera(cabecera);
		request.setDatos(datos);

		WebClient variacionInfoMercadoResponse = restWebClient.obtenerClienteRest("ORDENES.TENENCIAS");
		variacionInfoMercadoResponse.accept(MediaType.APPLICATION_JSON);
		variacionInfoMercadoResponse.type("application/json;charset=utf-8").accept(MediaType.APPLICATION_JSON);
		variacionInfoMercadoResponse.path("/VariacionInfoMercado/");
		ConsultaInformacionMercadoResponse rta = new ConsultaInformacionMercadoResponse();
		try {
			rta = variacionInfoMercadoResponse.post(request, ConsultaInformacionMercadoResponse.class);
			assertNotNull(rta);
		} catch (ResponseProcessingException e) {
			TestCase.fail();
		}

	}

}
