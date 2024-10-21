package ar.com.santanderrio.obp.servicios.cuentas.sei;

import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ComprobanteAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.sei.CuentasSEIIT.CuentasSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.cuentas.sei.impl.CuentasSEIImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.manager.TraspasoManualManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.ResumenCuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSelectorDetalleView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleCBUView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.SelectorCuentasView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class CuentasSEITest.
 */

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { CuentasSEITestConfiguration.class })
public class CuentasSEIIT extends AbstractSEITest {

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** The alias cuenta. */
	@Autowired
	private AliasCuentaManager aliasCuenta;

	/** The resumen cuenta manager. */
	@Autowired
	private ResumenCuentaManager resumenCuentaManager;

	/** The client. */
	private WebClient client = getWebClient();

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class CuentasSEITestConfiguration.
	 */
	@Configuration
	public static class CuentasSEITestConfiguration {

		/**
		 * Cuentas SEI.
		 *
		 * @return the cuentas SEI
		 */
		@Bean(name = "cuentasSEI")
		public CuentasSEI cuentasSEI() {
			return new CuentasSEIImpl();
		}

		/**
		 * Cuenta manager.
		 *
		 * @return the cuenta manager
		 */
		@Bean
		public CuentaManager cuentaManager() {
			return Mockito.mock(CuentaManager.class);
		}

		/**
		 * Alias cuenta manager.
		 *
		 * @return the alias cuenta manager
		 */
		@Bean
		public AliasCuentaManager aliasCuentaManager() {
			return Mockito.mock(AliasCuentaManager.class);
		}

		/**
		 * Traspaso automatico manager.
		 *
		 * @return the traspaso automatico manager
		 */
		@Bean
		public TraspasoAutomaticoManager traspasoAutomaticoManager() {
			return Mockito.mock(TraspasoAutomaticoManager.class);
		}

		/**
		 * Traspaso manual manager.
		 *
		 * @return the traspaso manual manager
		 */
		@Bean
		public TraspasoManualManager traspasoManualManager() {
			return Mockito.mock(TraspasoManualManager.class);
		}

		/**
		 * Resumen cuenta manager.
		 *
		 * @return the resumen cuenta manager
		 */
		@Bean
		public ResumenCuentaManager resumenCuentaManager() {
			return Mockito.mock(ResumenCuentaManager.class);
		}
	}

	/**
	 * Test get cuentas.
	 */
	@Test
	public void testGetCuentas() {
		Respuesta<SelectorCuentasView> respuesta = new Respuesta<SelectorCuentasView>();
		SelectorCuentasView selectorCuentasView = new SelectorCuentasView();
		selectorCuentasView.setCuentas(new ArrayList<CuentaSelectorDetalleView>());
		selectorCuentasView.setHasCuentasActivas(Boolean.FALSE);
		selectorCuentasView.setSelected(1);
		respuesta.setRespuesta(selectorCuentasView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(true);
		Mockito.when(cuentaManager.getCuentas(Matchers.anyString(), Matchers.anyString())).thenReturn(respuesta);
		client.accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/obtenerCuentas");
		addSleepTime(5000);
		Respuesta<CuentasView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test home get.
	 */
	@Test
	public void testHomeGet() {
		client.accept(MediaType.TEXT_PLAIN);
		client.path("cuentas/ ");
		String retorno = client.get(String.class);
		client.close();
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test home post.
	 */
	@Test
	public void testHomePost() {
		client.accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/ ");
		Respuesta<Void> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test copiar CBU.
	 */
	@Test
	public void testCopiarCBU() {
		Respuesta<CopiarMensajeView> respCopiar = new Respuesta<CopiarMensajeView>();
		CopiarMensajeView copiarMensajeView = new CopiarMensajeView();
		copiarMensajeView.setMensaje("Mensaje");
		respCopiar.setRespuesta(copiarMensajeView);
		client.accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/copiarCBU");
		addSleepTime(5000);
		Mockito.when(cuentaManager.copiarCBU()).thenReturn(respCopiar);
		Respuesta<CopiarMensajeView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test compartir CBU.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@Test
	public void testCompartirCBU() throws InterruptedException {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(true);
		Mockito.when(cuentaManager.compartirCBU()).thenReturn(respuesta);
		WebClient client = getWebClient();
		client.accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/compartirCBU");
		Thread.sleep(5000);
		Respuesta<Void> retorno = client.post(null, Respuesta.class);
		client.close();
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test imprimir.
	 */
	@Test
	public void testImprimir() {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setNroSucursal("033");
		cuenta.setNumeroCuenta("12");

		Mockito.when(cuentaManager.obtenerReporteCuenta(Matchers.any(ConsultaCuentaView.class))).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/imprimir");
		Respuesta<ReporteView> retorno = client.post(cuenta, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test actualizar alias.
	 */
	@Test
	public void testActualizarAlias() {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setAlias("papa");
		cuenta.setNumeroCuenta("12");
		Mockito.when(cuentaManager.actualizarAlias(cuenta.getNumeroCuenta(), cuenta.getAlias())).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<Void> retorno = client.path("cuentas/actualizarAlias").post(cuenta, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test marcar alias.
	 */
	@Test
	public void testMarcarAlias() {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setAlias("papa");
		cuenta.setNumeroCuenta("12");
		Mockito.when(cuentaManager.marcarFavorita(cuenta.getNumeroCuenta())).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<Void> retorno = client.path("cuentas/marcarFavorita").post(cuenta, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test obtener lista resumen.
	 */
	@Test
	public void testObtenerListaResumen() {
		Respuesta<ResumenesMensualesCuentaView> respuesta = new Respuesta<ResumenesMensualesCuentaView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setAlias("papa");
		cuenta.setNumeroCuenta("12");

		Mockito.when(resumenCuentaManager.obtenerListaResumen(Matchers.anyString())).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<ResumenesMensualesCuentaView> retorno = client.path("cuentas/obtenerListaResumen").post(cuenta,
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test obtener resumenes PDF.
	 */
	@Test
	public void testObtenerResumenesPDF() {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		ReporteView reporteView = new ReporteView();
		reporteView.setNombre("Prueba");
		reporteView.setTipoArchivo(TipoArchivoEnum.PDF.getMimeTipe());
		String str = "HOLA";
		reporteView.setBytes(str);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporteView);
		ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
		consultaCuentaView.setFechas(new String[] { "2016-07-01" });
		consultaCuentaView.setNumeroCuenta("12");

		Mockito.when(resumenCuentaManager.obtenerResumenesPDF(Matchers.any(ConsultaCuentaView.class)))
				.thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<ReporteView> retorno = client.path("cuentas/obtenerResumenes").post(consultaCuentaView,
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test obtener resumenes ZIP.
	 */
	@Test
	public void testObtenerResumenesMultiple() {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setFechas(new String[] { "2016-07-01" });
		cuenta.setNumeroCuenta("12");
		cuenta.setCantidadADescargar(1);
		Mockito.when(resumenCuentaManager.obtenerResumenDescargaMultiple(Arrays.asList(cuenta.getFechas()),
				cuenta.getNumeroCuenta(), cuenta.getCantidadADescargar())).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<ReporteView> retorno = client.path("cuentas/obtenerResumenesDescargaMultiple").post(cuenta,
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test verificar cuenta.
	 */
	@Test
	public void testVerificarCuenta() {
		Respuesta<Cliente> respuesta = new Respuesta<Cliente>();
		TransferenciaView transferenciaView = new TransferenciaView();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Cliente cliente = new Cliente();
		cliente.setNombre("PEPE");
		respuesta.setRespuesta(cliente);
		Mockito.when(cuentaManager.verificarCuenta(Mockito.any(TransferenciaView.class), Matchers.anyInt()))
				.thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<Cliente> retorno = client.path("cuentas/verificarCuenta").post(transferenciaView, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test obtener saldo.
	 */
	@Test
	public void testObtenerSaldo() {
		Respuesta<CuentasView> respuesta = new Respuesta<CuentasView>();
		CuentasView cuentasView = new CuentasView();
		cuentasView.setCuentas(new ArrayList<CuentasAdhesionDebitoView>());
		cuentasView.setHasCuentasActivas(Boolean.FALSE);
		cuentasView.setSelected(1);
		respuesta.setRespuesta(cuentasView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(cuentaManager.getCuentasSaldo()).thenReturn(respuesta);
		client.accept(MediaType.APPLICATION_JSON);
		Respuesta<CuentasAdhesionDebitoView> retorno = client.path("cuentas/obtenerCuentasSaldo").post(null,
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Test obtener detalle CBU.
	 */
	@Test
	public void testObtenerDetalleCBU() {
		Respuesta<DetalleCBUView> respuesta = new Respuesta<DetalleCBUView>();
		DetalleCBUView detalleCBUView = new DetalleCBUView();
		ConsultaCuentaView cuenta = new ConsultaCuentaView();
		cuenta.setFechas(new String[] { "2016-07-01" });
		cuenta.setNumeroCuenta("12");

		respuesta.setRespuesta(detalleCBUView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		addSleepTime(5000);
		Mockito.when(cuentaManager.obtenerDetalleCBU(Matchers.any(ConsultaCuentaView.class), Matchers.anyString()))
				.thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<DetalleCBUView> retorno = client.path("cuentas/obtenerDetalleCBU").post(cuenta, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Configurar alta alias CBU test.
	 */
	@Test
	public void configurarAltaAliasCBUTest() {
		Respuesta<DetalleCBUView> respuesta = new Respuesta<DetalleCBUView>();
		DetalleCBUView detalleCBUView = new DetalleCBUView();
		respuesta.setRespuesta(detalleCBUView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(aliasCuenta.continuarAliasCBU(Matchers.any(DetalleCBUView.class))).thenReturn(respuesta);
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<DetalleCBUView> retorno = client.path("cuentas/configurarAliasCBU").post(detalleCBUView,
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Confirmacion alta alias CBU.
	 */
	@Test
	public void confirmacionAltaAliasCBU() {
		Mockito.when(aliasCuenta.confirmacionCrearAliasCBU(Matchers.any(ComprobanteAltaDestinatarioView.class),
				Matchers.anyString())).thenReturn(new Respuesta<ComprobanteAltaDestinatarioView>());
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<ComprobanteAltaDestinatarioView> retorno = client.path("cuentas/confirmacionAltaAliasCBU")
				.post(new ComprobanteAltaDestinatarioView(), Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtener alias CBU.
	 */
	@Test
	public void obtenerAliasCBU() {
		Mockito.when(aliasCuenta.obtenerAliasCBU(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(new Respuesta<DetalleCBUView>());
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		addSleepTime(5000);
		Respuesta<DetalleCBUView> retorno = client.path("cuentas/obtenerAliasDeCBU").post(new DetalleCBUView(),
				Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Confirmacion editar alias CBU.
	 */
	@Test
	public void confirmacionEditarAliasCBU() {
		Mockito.when(aliasCuenta.confirmacionEditarAliasCBU(Matchers.any(ComprobanteAltaDestinatarioView.class),
				Matchers.anyString())).thenReturn(new Respuesta<ComprobanteAltaDestinatarioView>());
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		Respuesta<ComprobanteAltaDestinatarioView> retorno = client.path("cuentas/confirmacionEditarAliasCBU")
				.post(new ComprobanteAltaDestinatarioView(), Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Testea el llamado del manager del grabado de estadistica para entrar a la
	 * ayuda de alta.
	 */
	@Test
	public void grabarEstadisticaAyudaAliasCBUOK() {
		DetalleCBUView detalle = new DetalleCBUView();

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("cuentas/estadisticaAyudaAliasCBU");
		client.post(detalle, Respuesta.class);
		Mockito.verify(aliasCuenta).grabadoEstadisticaAyuda(Matchers.any(DetalleCBUView.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("cuentasSEI");
	}

}
