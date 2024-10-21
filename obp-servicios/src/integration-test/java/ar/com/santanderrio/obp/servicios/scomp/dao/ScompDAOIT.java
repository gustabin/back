package ar.com.santanderrio.obp.servicios.scomp.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.keystore.KeyStore;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreHelperImpl;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.config.executor.ExecutorConfig;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.AltaComprobanteRequest;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteResponse;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Fecha;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroDetalleComprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.FiltroListaComprobantesOrExt;
import ar.com.santanderrio.obp.servicios.scomp.client.entities.Filtros;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Cliente;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.Comprobante;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteCompraVentaDolar;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobantePMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteScomp;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ComprobanteTransfInmRioRio;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaDestino;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.CuentaOrigen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.OperacionEstado;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.ScomServicioNombreEnum;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TarjetaFinanciacionResumen;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaOB;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCAfip;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCConDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCSinDeuda;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaPMCVEP;
import ar.com.santanderrio.obp.servicios.scomp.client.domain.TransferenciaRio;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.scomp.dao.ScompDAOIT.ScompDAOITConfiguration;

/**
 * The Class ScompDAOIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ScompDAOITConfiguration.class,
		ExecutorConfig.class })
@ActiveProfiles(Profiles.TEST)
@TestPropertySource(properties = { "SCOMP.POOL.ACTIVO=false", "SCOMP.URL=http://desawas.ar.bsch:9140/SCOMPWS/service",
		// "SCOMP.URL=http://localhost:29140/SCOMPWS/service",
		// "SCOMP.URL=http://localhost:2544/SCOMPWS/service",
		// "SCOMP.URL=http://localhost:8089/SCOMPWS/service",
		"SCOMP.TIMEOUT=100000", "SCOMP.POOL.SIZE=30", "SCOMP.POOL.MAXWAIT=5000", "APP.ENCODING = UTF-8",
		"THREAD.CORE.POOL.SIZE=7", "THREAD.MAX.POOL.SIZE=42", "THREAD.QUEUE.CAPACITY=11" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ScompDAOIT {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ScompDAOIT.class);

	private Filtros filtros;

	/** The scomp DAO. */
	@Autowired
	private ScompDAO scompDAO;

	@Autowired
	private ScompBO scompBO;

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
	@ComponentScan(basePackageClasses = { ScompDAOImpl.class, ScompGestionarWSImpl.class, Sign.class,
			KeyStoreHelperImpl.class,
			ContextHolder.class }, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
					KeyStoreFactory.class }))
	public static class ScompDAOITConfiguration {

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
	public void altaComprobanteTransferenciaInmediataRioRio() throws DAOException {
		ComprobanteTransfInmRioRio inEntity = new ComprobanteTransfInmRioRio();
		inEntity.setCanal("04");
		inEntity.setSubCanal("99");
		inEntity.setTpoComprobante("2");
		inEntity.setSubTpoComprobante("9");
		inEntity.setEstadoOper(OperacionEstado.A);
		inEntity.setDescEstado("Aceptada");
		inEntity.setIdMedioPago("");
		inEntity.setDescMedioPago("");
		inEntity.setFechaOper("2017-09-06T00:00:00Z");
		inEntity.setHoraOper("11:20:00");
		inEntity.setFechaGen("2017-08-09T00:00:00Z");

		Cliente cliente = new Cliente();
		cliente.setNup("03348599");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("24422451");

		inEntity.setCliente(cliente);

		TransferenciaRio transferencia = new TransferenciaRio();
		transferencia.setAlias("Juancito");
		transferencia.setNombreDestinatario("Juan Pose");
		transferencia.setImporte("1234,00");
		transferencia.setTipoCuentaOrigen("09");
		transferencia.setSucursalCuentaOrigen("123");
		transferencia.setNumeroCuentaOrigen("0037007");
		transferencia.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		transferencia.setTipoCuentaDestino("00");
		transferencia.setSucursalCuentaDestino("080");
		transferencia.setNumeroCuentaDestino("0691708");
		transferencia.setConcepto("Alquiler");
		transferencia.setDescripcionConcepto("Pago de ALQ");
		transferencia.setPlazoAcreditacion("Inmediata");
		transferencia.setEmailDestinatario("");
		transferencia.setMensaje("");
		transferencia.setNroComprobante("1234567");

		inEntity.setTransferencia(transferencia);

		AltaComprobanteRequest altaComprobanteRequest = new AltaComprobanteRequest();
		altaComprobanteRequest.setComprobante(inEntity);
		altaComprobanteRequest.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		altaComprobanteRequest.setVersion("200");
		altaComprobanteRequest.setCanal("04");
		altaComprobanteRequest.setSubcanal("99");
		altaComprobanteRequest.setTipoComprobante("2");
		altaComprobanteRequest.setSubTipoComprobante("9");

		ComprobanteResponse response = scompDAO.altaComprobante(altaComprobanteRequest);
		Assert.assertEquals(0, response.getCodRet());
	}

	public void obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco() {

		ComprobanteTransfInmRioRio comprobant = new ComprobanteTransfInmRioRio();
		comprobant.setCanal("04");

		Cliente cliente = new Cliente();
		cliente.setFechaNac("19771108");
		cliente.setNroDoc("00026328189");
		cliente.setNup("02810993");
		cliente.setTpoDoc("N");
		comprobant.setCliente(cliente);

		comprobant.setDescEstado("Aceptada");
		comprobant.setDescMedioPago("");
		comprobant.setEstadoOper(OperacionEstado.A);
		comprobant.setFechaGen("2017-10-04T00:00:00Z");
		comprobant.setFechaOper("2017/10/04T00:00:00Z");
		comprobant.setHoraOper("HoraOper");
		comprobant.setIdMedioPago("");
		comprobant.setSubCanal("99");
		comprobant.setSubTpoComprobante("10");
		comprobant.setTpoComprobante("2");

		TransferenciaRio transferencia = new TransferenciaRio();
		transferencia.setAlias("Juancito");
		transferencia.setNombreDestinatario("Juan Pose");
		transferencia.setImporte("1234,00");
		transferencia.setTipoCuentaOrigen("09");
		transferencia.setSucursalCuentaOrigen("123");
		transferencia.setNumeroCuentaOrigen("0037007");
		transferencia.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
		transferencia.setTipoCuentaDestino("00");
		transferencia.setSucursalCuentaDestino("080");
		transferencia.setNumeroCuentaDestino("0691708");
		transferencia.setConcepto("Alquiler");
		transferencia.setDescripcionConcepto("Pago de ALQ");
		transferencia.setPlazoAcreditacion("Inmediata");
		transferencia.setEmailDestinatario("");
		transferencia.setMensaje("");
		transferencia.setNroComprobante("1234567");

		comprobant.setTransferencia(transferencia);

		scompBO.obtenerComprobanteDTOParaTransfInmediatasRioRioTBanco(comprobant);
	}

	@Test
	public void altaComprobanteTransferenciaInmediataRioOB() throws DAOException {
		ComprobanteTransfInmRioOB inEntity = new ComprobanteTransfInmRioOB();
		inEntity.setCanal("04");
		inEntity.setSubCanal("99");
		inEntity.setTpoComprobante("2");
		inEntity.setSubTpoComprobante("10");
		inEntity.setEstadoOper(OperacionEstado.A);
		inEntity.setDescEstado("Aceptada");
		inEntity.setFechaOper("2017/10/04T00:00:00Z");
		inEntity.setHoraOper("17:19:00");
		inEntity.setFechaGen("2017-10-04T00:00:00Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		inEntity.setCliente(cliente);

		TransferenciaOB transferencia = new TransferenciaOB();
		transferencia.setNombreDestinatario("Emilio A.");
		transferencia.setImporte("1234.00");
		transferencia.setTipoCuentaOrigen("09");
		transferencia.setSucursalCuentaOrigen("000");
		transferencia.setNumeroCuentaOrigen("0639170");
		transferencia.setBanco("Echeisbizi");
		transferencia.setCbu("0270006910000102550013");
		transferencia.setCuitCuil("20378668817");
		transferencia.setConcepto("Varios");
		transferencia.setDescripcionConcepto("Varios");
		transferencia.setPlazoAcreditacion("48 Horas");
		transferencia.setNroComprobante("00003369");

		inEntity.setTransferencia(transferencia);

		AltaComprobanteRequest altaComprobanteRequest = new AltaComprobanteRequest();
		altaComprobanteRequest.setComprobante(inEntity);
		altaComprobanteRequest.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		altaComprobanteRequest.setVersion("200");
		altaComprobanteRequest.setCanal("04");
		altaComprobanteRequest.setSubcanal("99");
		altaComprobanteRequest.setTipoComprobante("2");
		altaComprobanteRequest.setSubTipoComprobante("10");

		ComprobanteResponse response = scompDAO.altaComprobante(altaComprobanteRequest);
		Assert.assertEquals(0, response.getCodRet());
	}

	@Test
	public void altaComprobantePMCConDeuda() throws DAOException {
		ComprobantePMCConDeuda comprobante = new ComprobantePMCConDeuda();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("11");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setIdMedioPago("");
		comprobante.setDescMedioPago("");
		comprobante.setFechaOper("2017-08-11T00:00:00Z");
		comprobante.setHoraOper("12:50:52");
		comprobante.setFechaGen("2017-08-11T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		TransferenciaPMCConDeuda transferencia = new TransferenciaPMCConDeuda();
		transferencia.setEmpresa("DEPO MAX");
		transferencia.setMoneda("$");
		transferencia.setImporte("454,00");
		transferencia.setIdentificacion("12345");
		transferencia.setFechaHoraPago("20170904101310");
		transferencia.setFechaVencimiento("27072017");
		transferencia.setTipoCuentaDebito("09");
		transferencia.setSucursalCuentaDebito("126");
		transferencia.setNumeroCuentaDebito("0693701");
		transferencia.setLeyendaFactura("2322234222");
		transferencia.setNumControl("3456");
		transferencia.setNroComprobante("82983217");

		comprobante.setTransferencia(transferencia);

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("99");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("11");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());
	}

	@Test
	public void altaComprobantePMCSinDeuda() throws DAOException {
		ComprobantePMCSinDeuda comprobante = new ComprobantePMCSinDeuda();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("12");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setIdMedioPago("");
		comprobante.setDescMedioPago("");
		comprobante.setFechaOper("2017-08-11T00:00:00Z");
		comprobante.setHoraOper("12:50:52");
		comprobante.setFechaGen("2017-08-11T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		TransferenciaPMCSinDeuda transferencia = new TransferenciaPMCSinDeuda();
		transferencia.setEmpresa("Sin deuda");
		transferencia.setMoneda("$");
		transferencia.setImporte("100,00");
		transferencia.setTipoCuentaDebito("01");
		transferencia.setSucursalCuentaDebito("122");
		transferencia.setNumeroCuentaDebito("0089181");
		transferencia.setFechaHoraPago("20170822103009");
		transferencia.setIdentificacion("3487111111");
		transferencia.setNumControl("1234");
		transferencia.setNroComprobante("56473829");

		comprobante.setTransferencia(transferencia);

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("99");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("12");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());

	}

	@Test
	public void altaComprobantePMCVEP() throws DAOException {
		ComprobantePMCVEP comprobante = new ComprobantePMCVEP();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("13");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setIdMedioPago("");
		comprobante.setDescMedioPago("");
		comprobante.setFechaOper("2017-08-11T00:00:00Z");
		comprobante.setHoraOper("12:50:52");
		comprobante.setFechaGen("2017-08-11T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		TransferenciaPMCVEP transferencia = new TransferenciaPMCVEP();
		transferencia.setEmpresa("AFIP - PAGO DE IMPUESTOS AFIP (VEP)");
		transferencia.setMoneda("$");
		transferencia.setImporte("540,01");
		transferencia.setFechaHoraPago("20170828111800");
		transferencia.setFechaVencimiento("29072017");
		transferencia.setIdentificacion("21345231237");
		transferencia.setIdentificacion2("20378668817");
		transferencia.setNumeroVep("000126721490");
		transferencia.setPeriodo("0001");
		transferencia.setAnticipoCuota("091");
		transferencia.setTipoCuentaDebito("09");
		transferencia.setSucursalCuentaDebito("019");
		transferencia.setNumeroCuentaDebito("0693171");
		transferencia.setNumControl("7382");
		transferencia.setNroComprobante("22910219");
		comprobante.setTransferencia(transferencia);

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("99");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("13");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());

	}

	@Test
	public void altaComprobantePMCAFIP() throws DAOException {
		ComprobantePMCAfip comprobante = new ComprobantePMCAfip();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("14");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setIdMedioPago("");
		comprobante.setDescMedioPago("");
		comprobante.setFechaOper("2017-08-11T00:00:00Z");
		comprobante.setHoraOper("12:50:52");
		comprobante.setFechaGen("2017-08-11T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		TransferenciaPMCAfip transferencia = new TransferenciaPMCAfip();
		transferencia.setEmpresa("Pago AFIP");
		transferencia.setMoneda("$");
		transferencia.setImporte("1012,15");
		transferencia.setFechaHoraPago("20170822111900");
		transferencia.setFechaVencimiento("00000000");
		transferencia.setIdentificacion("20301234561");
		transferencia.setIdentificacion2("27218472350");
		transferencia.setPeriodoPago("092017");
		transferencia.setDatosAdicionales("");
		transferencia.setTipoCuentaDebito("09");
		transferencia.setSucursalCuentaDebito("065");
		transferencia.setNumeroCuentaDebito("0691371");
		transferencia.setNumControl("7382");
		transferencia.setNroComprobante("21284932");
		comprobante.setTransferencia(transferencia);

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("11");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("14");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());

	}

	@Test
	public void altaComprobanteFinanciacionResumenVISAAMEX() throws DAOException {
		ComprobanteFinanciacionResumen comprobante = new ComprobanteFinanciacionResumen();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("19");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setIdMedioPago("");
		comprobante.setDescMedioPago("");
		comprobante.setFechaOper("2017-10-03T00:00:00Z");
		comprobante.setHoraOper("16:50:17");
		comprobante.setFechaGen("2017-10-03T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		TarjetaFinanciacionResumen tarjeta = new TarjetaFinanciacionResumen();
		tarjeta.setTipoTarjeta("AMEX");
		tarjeta.setNumTarjeta("00004509950008734695");
		tarjeta.setImporteFinanciado("1234,56");
		tarjeta.setImporteCuota("200,11");
		tarjeta.setCantCuotas("2");
		tarjeta.setTasaNominalAnual("24,01");
		tarjeta.setCostoFinancieroTotal("14,74");
		tarjeta.setNumComprobante("10106789");
		comprobante.setTarjeta(tarjeta);

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("99");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("19");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());

	}

	@Test
	public void altaComprobanteCompraVenta() throws DAOException {
		ComprobanteCompraVentaDolar comprobante = new ComprobanteCompraVentaDolar();
		comprobante.setCanal("04");
		comprobante.setSubCanal("99");
		comprobante.setTpoComprobante("2");
		comprobante.setSubTpoComprobante("31");
		comprobante.setEstadoOper(OperacionEstado.A);
		comprobante.setDescEstado("Aceptada");
		comprobante.setFechaOper("2017-10-17T00:00:00Z");
		comprobante.setHoraOper("16:50:17");
		comprobante.setFechaGen("2017-10-17T16:50:17Z");

		Cliente cliente = new Cliente();
		cliente.setNup("02615492");
		cliente.setTpoDoc("N");
		cliente.setNroDoc("13238861");

		comprobante.setCliente(cliente);

		CuentaOrigen cuentaOrigen = new CuentaOrigen();
		cuentaOrigen.setTipoCuentaOrigen("09");
		cuentaOrigen.setNumeroCuentaOrigen("363238/1");
		cuentaOrigen.setSucursalCuentaOrigen("201");

		CuentaDestino cuentaDestino = new CuentaDestino();
		cuentaDestino.setTipoCuentaDestino("10");
		cuentaDestino.setNumeroCuentaDestino("063917/0");
		cuentaDestino.setSucursalCuentaDestino("000");

		comprobante.setCuentaOrigen(cuentaOrigen);
		comprobante.setCuentaDestino(cuentaDestino);

		comprobante.setImporteAcreditado("u$s 123,00");
		comprobante.setImporteDebitado("$ 2140,20");
		comprobante.setCotizacionAplicada("u$s 1 = $17,40");
		comprobante.setNumeroOperacion("14424603");
		comprobante.setNroComprobante("0277041A");

		AltaComprobanteRequest alta = new AltaComprobanteRequest();
		alta.setNombre(ScomServicioNombreEnum.ALTACOMPROBANTE);
		alta.setVersion("200");
		alta.setCanal("04");
		alta.setSubcanal("99");
		alta.setTipoComprobante("2");
		alta.setSubTipoComprobante("31");

		alta.setComprobante(comprobante);

		ComprobanteResponse response = scompDAO.altaComprobante(alta);
		Assert.assertEquals(0, response.getCodRet());

	}

	@Before
	public void setupListarComprobantes() throws DatatypeConfigurationException {
		filtros = new Filtros();
		filtros.setNup("02615492");
		filtros.setEstadoOper("A");
		Fecha fecha = new Fecha();

		Calendar gcDate = GregorianCalendar.getInstance();
		XMLGregorianCalendar fechaHasta = fecha.getXmlGregorianCalendar(gcDate.getTime());
		gcDate.add(GregorianCalendar.MONTH, -6);
		XMLGregorianCalendar fechaDesde = fecha.getXmlGregorianCalendar(gcDate.getTime());
		fecha.setDesde(fechaDesde);
		fecha.setHasta(fechaHasta);
		filtros.setFecha(fecha);

	}

	@Test
	public void listarComprobantesAsync() throws DAOException {
		try {
			Filtros filtros2 = new Filtros();
			filtros2.setNup("02615492");
			filtros2.setEstadoOper("A");
			Fecha fecha = new Fecha();

			Calendar gcDate = GregorianCalendar.getInstance();
			XMLGregorianCalendar fechaHasta = fecha.getXmlGregorianCalendar(gcDate.getTime());
			gcDate.add(GregorianCalendar.YEAR, -1);
			XMLGregorianCalendar fechaDesde = fecha.getXmlGregorianCalendar(gcDate.getTime());
			fecha.setDesde(fechaDesde);
			fecha.setHasta(fechaHasta);
			filtros2.setFecha(fecha);

			filtros2.setTpoComp("18");
			filtros2.setSubTpoComp("0");

			Future<ComprobanteResponse> rta = scompDAO.listarComprobantesAsync(filtros2);

			filtros.setTpoComp("19");
			filtros.setSubTpoComp("0");

			Future<ComprobanteResponse> rta2 = scompDAO.listarComprobantesAsync(this.filtros);

			while (!rta.isDone() && !rta2.isDone()) {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					LOGGER.error("Error");
				}
			}

			ComprobanteResponse respuesta = null;
			try {
				respuesta = rta.get();
			} catch (ExecutionException e) {
				if (e.getCause() instanceof DAOException) {
					LOGGER.error("Dao Exception");
					throw (DAOException) e.getCause();
				}
				throw new DAOException();
			} catch (InterruptedException e) {
				LOGGER.error("Error");
			}
			try {
				ComprobanteResponse comp2 = rta.get();
				Assert.assertNotNull(comp2);
			} catch (ExecutionException e) {
				if (e.getCause() instanceof DAOException) {
					LOGGER.error("Dao Exception");
					throw (DAOException) e.getCause();
				}
				throw new DAOException();
			} catch (InterruptedException e) {
				LOGGER.error("Error");
			}

			Assert.assertNotNull(respuesta);
			Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes()
					.get(0)).getComprobanteList().get(0));
			Assert.assertEquals("04", comp.getCanal());
			Assert.assertEquals(1, ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
					.getComprobanteList().size());
		} catch (DatatypeConfigurationException e1) {
			LOGGER.error("Error");
		}
	}

	/**
	 * Listar Comprobantes PAGHABCCI tipo 19 subtipo 0.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws DatatypeConfigurationException
	 */
	@Test
	public void listarComprobantesPAGHABCCI() throws DAOException {
		Filtros filtros = this.filtros;
		filtros.setTpoComp("18");
		filtros.setSubTpoComp("0");

		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros, "200", "99");
		Assert.assertNotNull(respuesta);
		Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().get(0));
		Assert.assertEquals("04", comp.getCanal());
		Assert.assertEquals(1, ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().size());
	}

	@Test
	public void listarComprobantes80() throws DAOException {
		Filtros filtros = this.filtros;
		filtros.setTpoComp("2");
		filtros.setSubTpoComp("10");
		// filtros.setNup("00276937");
		filtros.setNup("02615492");

		Calendar gcDate = GregorianCalendar.getInstance();
		gcDate.setTimeZone(TimeZone.getTimeZone("UTC"));
		Fecha fecha = new Fecha();
		try {
			XMLGregorianCalendar fechaHastaXML = fecha.getXmlGregorianCalendar(gcDate.getTime()).normalize();
			fechaHastaXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			gcDate.add(GregorianCalendar.YEAR, -1);
			fecha.setHasta(fechaHastaXML);
		} catch (DatatypeConfigurationException e) {
			LOGGER.error("Error al parsear la fecha:" + e);
		}

		try {
			XMLGregorianCalendar fechaDesdeXML = fecha.getXmlGregorianCalendar(gcDate.getTime()).normalize();
			fechaDesdeXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			fecha.setDesde(fechaDesdeXML);
		} catch (DatatypeConfigurationException e) {
			LOGGER.error("Error al parsear la fecha:" + e);
		}
		filtros.setFecha(fecha);

		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros, "200", "99");
		Assert.assertNotNull(respuesta);
		Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().get(0));
		Assert.assertEquals("04", comp.getCanal());
		Assert.assertEquals(1, ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().size());
	}

	/**
	 * Listar Comprobantes TRFCTA tipo 18 subtipo 0.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws DatatypeConfigurationException
	 */
	@Test
	public void listarComprobantesTrfcta() throws DAOException {
		Filtros filtros2 = this.filtros;
		filtros2.setTpoComp("18");
		filtros2.setSubTpoComp("0");

		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros2, "200", "99");
		Assert.assertNotNull(respuesta);
		Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().get(0));
		Assert.assertEquals("04", comp.getCanal());
		Assert.assertEquals(1, ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().size());
	}

	@Test
	public void listarComprobantesTest() throws DAOException, ParseException {
		Filtros filtros2 = this.filtros;
		filtros2.setTpoComp("2");
		filtros2.setSubTpoComp("11");
		Fecha fecha = new Fecha();
		Date fechaDesde = ISBANStringUtils.parsearFechaConAnio("07/10/2017");
		Date fechaHasta = ISBANStringUtils.parsearFechaConAnio("08/11/2017");
		try {
			XMLGregorianCalendar fechaDesdeXML = fecha.getXmlGregorianCalendar(fechaDesde).normalize();
			fechaDesdeXML.setSecond(0);
			fechaDesdeXML.setMinute(0);
			fechaDesdeXML.setHour(0);
			fechaDesdeXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			fecha.setDesde(fechaDesdeXML);

			XMLGregorianCalendar fechaHastaXML = fecha.getXmlGregorianCalendar(fechaHasta).normalize();
			fechaHastaXML.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			fechaHastaXML.setSecond(59);
			fechaHastaXML.setMinute(59);
			fechaHastaXML.setHour(23);
			fecha.setHasta(fechaHastaXML);
		} catch (DatatypeConfigurationException e) {
			LOGGER.error("Error al parsear la fecha:" + e);
		}
		filtros2.setFecha(fecha);
		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros2, "200", "99");
		Assert.assertNotNull(respuesta);
		Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().get(0));
	}

	@Test
	public void listarComprobantesResumenFinanciacion() throws DAOException {
		Filtros filtros2 = this.filtros;
		filtros2.setTpoComp("2");
		filtros2.setSubTpoComp("19");

		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros2, "200", "99");
		Assert.assertNotNull(respuesta);
		Comprobante comp = ((Comprobante) ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().get(0));
		Assert.assertEquals("04", comp.getCanal());
		Assert.assertEquals(1, ((ComprobanteScomp) respuesta.getRespuestaScomp().getComprobantes().get(0))
				.getComprobanteList().size());
	}

	/**
	 * Listar Comprobantes TRFCCI tipo 18 subtipo 1.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 * @throws DatatypeConfigurationException
	 */
	@Test
	public void listarComprobantesTipoIncorrecto() throws DAOException {
		Filtros filtros2 = this.filtros;
		filtros2.setTpoComp("188");
		filtros2.setSubTpoComp("1");

		ComprobanteResponse respuesta = scompDAO.listarComprobantes(filtros2, "200", "99");
		Assert.assertNotNull(respuesta);
		Assert.assertNull(respuesta.getRespuestaScomp().getComprobantes());
	}

	@Test(expected = DAOException.class)
	public void listaComprobantesOrExt() throws DAOException {
		FiltroListaComprobantesOrExt filtro = new FiltroListaComprobantesOrExt();
		filtro.setTpoComp("17");
		filtro.setCuentasList(new ArrayList<String>());
		filtro.getCuentasList().add("000-00009565892");

		ComprobanteResponse respuesta = scompDAO.listaComprobantesOrExt(filtro);
	}

	@Test
	public void detalleComprobante() throws DAOException {
		FiltroDetalleComprobante filtro = new FiltroDetalleComprobante();
		filtro.setIdComp("2227004");
		ComprobanteResponse respuesta = scompDAO.detalleComprobante(filtro);

		Assert.assertNotNull(respuesta);
		Assert.assertNotNull(respuesta.getDetalleHtml());
		LOGGER.info(respuesta.getDetalleHtml());
	}

}
