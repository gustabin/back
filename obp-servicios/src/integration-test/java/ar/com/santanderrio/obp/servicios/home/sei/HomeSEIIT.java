package ar.com.santanderrio.obp.servicios.home.sei;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.home.sei.HomeSEIIT.HomeSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.home.sei.impl.HomeSEIImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.HomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeSegurosView;
import ar.com.santanderrio.obp.servicios.home.web.view.SaldosConsolidadosView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TopbarHomeView;

/**
 * The Class HomeSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { HomeSEITestConfiguration.class })
public class HomeSEIIT extends AbstractSEITest {

	/** The home manager. */
	@Autowired
	private HomeManager homeManager;

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
	 * The Class HomeSEITestConfiguration.
	 */
	@Configuration
	public static class HomeSEITestConfiguration {

		/**
		 * Home SEI.
		 *
		 * @return the home SEI
		 */
		@Bean(name = "homeSEI")
		public HomeSEI homeSEI() {
			return new HomeSEIImpl();
		}

		/**
		 * Cajas home manager.
		 *
		 * @return the home manager
		 */
		@Bean
		public HomeManager cajasHomeManager() {
			return Mockito.mock(HomeManager.class);
		}
	}

	/**
	 * Obtener tablero test.
	 */
	@Test
	public void obtenerTableroTest() {
		Respuesta<TableroHomeView> respuesta = new Respuesta<TableroHomeView>();
		TableroHomeView tableroHomeView = new TableroHomeView();
		respuesta.setRespuesta(tableroHomeView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		Mockito.when(homeManager.obtenerTablero()).thenReturn(respuesta);

		client.accept(MediaType.APPLICATION_JSON);
		client.path("home/obtenerTablero");
		Respuesta<TableroHomeView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtener saldos cuentas test.
	 */
	@Test
	public void obtenerSaldosCuentasTest() {
		Respuesta<SaldosConsolidadosView> respuesta = new Respuesta<SaldosConsolidadosView>();
		SaldosConsolidadosView saldosConsolidadosView = new SaldosConsolidadosView();
		respuesta.setRespuesta(saldosConsolidadosView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		Mockito.when(homeManager.obtenerSaldosCuentas()).thenReturn(respuesta);

		client.accept(MediaType.APPLICATION_JSON);
		client.path("home/obtenerSaldosCuentas");
		Respuesta<SaldosConsolidadosView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	@Test
	public void obtenerSeguro() {
		Respuesta<CajaHomeSegurosView> respuesta = new Respuesta<CajaHomeSegurosView>();
		CajaHomeSegurosView cajaHomeSegurosView = new CajaHomeSegurosView();
		respuesta.setRespuesta(cajaHomeSegurosView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		Mockito.when(homeManager.obtenerSeguro()).thenReturn(respuesta);
		client.accept(MediaType.APPLICATION_JSON);
		client.path("home/obtenerSeguro");
		Respuesta<CajaHomeSegurosView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtener topbar test.
	 */
	@Test
	public void obtenerTopbarTest() {
		Respuesta<TopbarHomeView> respuesta = new Respuesta<TopbarHomeView>();
		TopbarHomeView topbarHomeView = new TopbarHomeView();
		respuesta.setRespuesta(topbarHomeView);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuestaVacia(false);
		Mockito.when(homeManager.obtenerTopbar()).thenReturn(respuesta);

		client.accept(MediaType.APPLICATION_JSON);
		client.path("home/obtenerTopbar");
		Respuesta<TopbarHomeView> retorno = client.post(null, Respuesta.class);
		Assert.assertNotNull(retorno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("homeSEI");
	}

}
