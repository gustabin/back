package ar.com.santanderrio.obp.servicios.inversiones.comun.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.ConsultaPerfilInversorRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.InversionesManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.sei.InversionesSEIIT.InversionesSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosTestPerfilViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { InversionesSEITestConfiguration.class })
@TestPropertySource(properties = {
		"INVERSIONES.TESTPERFILINVERSOR = http://wasdesainet05.ar.bsch:9081/exec/ti/encuesta.jsp" })
public class InversionesSEIIT extends AbstractSEITest {

	/** The inversiones manager. */
	@Autowired
	private InversionesManager inversionesManager;

	/** The Base Manager */
	@Autowired
	private BaseManager baseManager;

	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class InversionesSEITestConfiguration.
	 */
	@Configuration
	public static class InversionesSEITestConfiguration {

		/**
		 * Fondo SEI.
		 *
		 * @return the fondo SEI
		 */
		@Bean(name = "inversionesSEI")
		public InversionesSEI inversionesSEI() {
			return new InversionesSEIImpl();
		}

		/**
		 * Fondo manager.
		 *
		 * @return the fondo manager
		 */
		@Bean
		public InversionesManager inversionesManager() {
			return Mockito.mock(InversionesManager.class);
		}

		/**
		 * Fondo BO.
		 *
		 * @return the fondo BO
		 */
		@Bean
		public InversionesBO inversionesBO() {
			return Mockito.mock(InversionesBO.class);
		}

		@Bean
		public BaseManager baseManager() {
			return Mockito.mock(BaseManager.class);
		}

		@Bean
		public LegalBO legalBO() {
			return Mockito.mock(LegalBO.class);
		}

		@Bean
		public SesionCliente sesionCliente() {
			return Mockito.mock(SesionCliente.class);
		}

		@Bean
		public RespuestaFactory respuestaFactory() {
			return Mockito.mock(RespuestaFactory.class);
		}

		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}

		@Bean
		public InversionWSHelper inversionWSHelper() {
			return Mockito.mock(InversionWSHelper.class);
		}

		@Bean
		public Sign sign() {
			return Mockito.mock(Sign.class);
		}

		@Bean
		public FondoManager fondoManager() {
			return Mockito.mock(FondoManager.class);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void inicioFondosOK() {
		when(inversionesManager.inicioInversiones(Matchers.any(InicioInversionesViewRequest.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(InicioFondoView.class, new InicioFondoView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/inversiones/inicio");
		// Then
		Respuesta<FondoView> respuesta = client.post(new InicioInversionesViewRequest(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultarPerfilInversor() {
		when(baseManager.consultarPerfilInversor(Matchers.anyBoolean())).thenReturn(respuestaFactory
				.crearRespuestaOk(ConsultaPerfilInversorViewResponse.class, new ConsultaPerfilInversorViewResponse()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/inversiones/consultarPerfilInversor");
		// Then
		Respuesta<FondoView> respuesta = client.post(new ConsultaPerfilInversorRequest(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerDatosTestPerfil() {
		when(baseManager.obtenerDatosTestPerfil(Matchers.anyBoolean())).thenReturn(respuestaFactory.crearRespuestaOk(
				DatosTestPerfilViewResponse.class, new DatosTestPerfilViewResponse(StringUtils.EMPTY)));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/inversiones/obtenerDatosTestPerfil");
		// Then
		addSleepTime(5000);
		Respuesta<FondoView> respuesta = client.post(new InicioInversionesViewRequest(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaPorProducto() {
		when(inversionesManager.obtenerTenenciaConsolidadaPorProducto(Matchers.any(TenenciaPorProductoInView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(TenenciaConsolidadaView.class,
						new TenenciaConsolidadaView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/inversiones/obtenerTenenciaConsolidada");
		// Then
		Respuesta<FondoView> respuesta = client.post(new TenenciaPorProductoInView(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerTenenciaConsolidadaHome() {
		when(inversionesManager.obtenerTenenciaConsolidadaHome(Matchers.any(InicioInversionesViewRequest.class),
				Matchers.any(TipoBancaEnum.class)))
						.thenReturn(respuestaFactory.crearRespuestaOk(TarjetaTenenciaConsolidadaView.class,
								new TarjetaTenenciaConsolidadaView()));
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/inversiones/obtenerTenenciaConsolidadaHome");
		// Then
		Respuesta<FondoView> respuesta = client.post(new InicioInversionesViewRequest(), Respuesta.class);
		// Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
	}

	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("inversionesSEI");
	}

}
