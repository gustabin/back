/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.manager.PagoAutomaticoManager;
import ar.com.santanderrio.obp.servicios.pagoautomatico.sei.impl.PagoAutomaticoSEIImpl;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.CodigoEmpresaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;

/**
 * The Class NuevoPagoAutomaticoSEITest.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.pagoautomatico.sei.PagoAutomaticoSEIIT.NuevoPagoAutomaticoSEITestConfiguration.class })
public class PagoAutomaticoSEIIT extends AbstractSEITest {

	/** The nuevo pago automatico manager. */
	@Autowired
	private PagoAutomaticoManager pagoAutomaticoManager;

	/**
	 * Inits the.
	 */
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("nuevoPagoAutomaticoSEI");
	}

	/**
	 * The Class NuevoPagoAutomaticoSEITestConfiguration.
	 */
	public static class NuevoPagoAutomaticoSEITestConfiguration {

		/**
		 * Nuevo pago automatico SEI.
		 *
		 * @return the nuevo pago automatico SEI
		 */
		@Bean(name = "nuevoPagoAutomaticoSEI")
		public PagoAutomaticoSEI nuevoPagoAutomaticoSEI() {
			return new PagoAutomaticoSEIImpl();
		}

		/**
		 * Nuevo pago automatico manager.
		 *
		 * @return the nuevo pago automatico manager
		 */
		@Bean
		public PagoAutomaticoManager nuevoPagoAutomaticoManager() {
			return Mockito.mock(PagoAutomaticoManager.class);
		}

		/**
		 * Sesion cliente.
		 *
		 * @return the sesion cliente
		 */
		@Bean
		public SesionCliente sesionCliente() {
			return Mockito.mock(SesionCliente.class);
		}

		/**
		 * Sesion parametros.
		 *
		 * @return the sesion parametros
		 */
		@Bean
		public SesionParametros sesionParametros() {
			return Mockito.mock(SesionParametros.class);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerCuentas() {
		// When
		when(pagoAutomaticoManager.obtenerCuentas(Matchers.any(Cliente.class)))
				.thenReturn(new Respuesta<MedioPagoSelectionView>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/obtenerCuentas");
		addSleepTime(5000);
		// Then
		Respuesta<Void> respuesta = client.post(null, Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void confirmarAdhesionPagoAutomatico() {
		// When
		when(pagoAutomaticoManager.confirmarAdhesionPagoAutomatico(Matchers.any(AdhesionPagoAutomatico.class)))
				.thenReturn(new Respuesta<AdhesionPagoAutomatico>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/confirmarAdhesionPagoAutomatico");
		addSleepTime(5000);
		// Then
		Respuesta<AdhesionPagoAutomatico> respuesta = client.post(new AdhesionPagoAutomatico(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void cargarTerminosCondiciones() {
		// When
		when(pagoAutomaticoManager.cargarTerminosCondiciones()).thenReturn(new Respuesta<TerminosCondiciones>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/terminosCondiciones");
		addSleepTime(5000);
		// Then
		Respuesta<TerminosCondiciones> respuesta = client.post(null, Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void adherirDebito() {
		// When
		when(pagoAutomaticoManager.adherirDebitoEnCuenta(Matchers.any(RegistroSesion.class),
				Matchers.any(AdhesionDebitoAutomaticoEnCuentaView.class)))
						.thenReturn(new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/adherirDebito");
		addSleepTime(5000);
		// Then
		Respuesta<AdhesionDebitoAutomaticoEnCuentaView> respuesta = client
				.post(new AdhesionDebitoAutomaticoEnCuentaView(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void generarComprobanteAdhesion() {
		// When
		when(pagoAutomaticoManager.generarComprobanteAdhesion(Matchers.any(ComprobanteDebitoAutomatico.class)))
				.thenReturn(new Respuesta<ReporteView>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/comprobanteAdhesion");
		addSleepTime(5000);
		// Then
		Respuesta<ReporteView> respuesta = client.post(new ComprobanteDebitoAutomatico(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@Test
	public void estadisticaComprobanteNuevoPagoAutomatico() {
		// When
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/estadisticaComprobanteNuevoPagoAutomatico");
		addSleepTime(5000);
		// Then
		client.post(null);
		// Expected
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerDatosAdhesionDebitoAutomaticoEnTarjeta() {
		// When
		when(pagoAutomaticoManager.obtenerDatosAdhesionDebitoAutomaticoEnTarjeta(Matchers.anyString()))
				.thenReturn(new Respuesta<AdhesionDebitoAutomaticoEnTarjetaView>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/obtenerDatosAdhesionDebitoAutomaticoEnTarjeta");
		addSleepTime(5000);
		// Then
		Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> respuesta = client.post(new CodigoEmpresaView(),
				Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@Test
	public void generarHashDeSeguridad() {
		// When
		when(pagoAutomaticoManager.generarHashSeguridad(Matchers.any(HashDebitoAutomaticoTarjetaView.class)))
				.thenReturn(true);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/confirmacionAdhesionADebitoEnTarjeta");
		addSleepTime(5000);
		// Then
		Boolean respuesta = client.post(new HashDebitoAutomaticoTarjetaView(), Boolean.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void validarAutenticacionPagoAutomatico() {
		// When
		when(pagoAutomaticoManager.validarAutenticacionPagoAutomatico()).thenReturn(new Respuesta<Boolean>());
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevoPagoAutomatico/validarAutenticacionPagoAutomatico");
		addSleepTime(5000);
		// Then
		Respuesta<Boolean> respuesta = client.post(null, Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
	}

}
