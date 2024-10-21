package ar.com.santanderrio.obp.servicios.pagos.sei;

import java.util.ArrayList;
import java.util.List;

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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.pagos.sei.PagoMultipleSEIIT.PagoMultipleSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.pagos.sei.impl.PagoMultipleSEIImpl;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.PagoMultipleManager;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Class PagoMultipleSEITest.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		PagoMultipleSEITestConfiguration.class })
public class PagoMultipleSEIIT extends AbstractSEITest {

	/** The pago multiple manager. */
	@Autowired
	private PagoMultipleManager pagoMultipleManager;

	/** The pago OK 1. */
	PagoMultipleView pagoOK1;

	/** The pago OK 2. */
	PagoMultipleView pagoOK2;

	/** The pago OK 3. */
	PagoMultipleView pagoOK3;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		pagoOK1 = new PagoMultipleView();
		pagoOK1.setTipoMonto("0");
		pagoOK1.setTipoDeSeleccion("I");
		pagoOK1.setTipoCuenta("09");
		pagoOK1.setMonto("000000000123.34");
		pagoOK1.setMoneda("ARS");
		pagoOK1.setCodigoEmpresa("REPE");
		pagoOK1.setIdentificacionCliente("1135141112         ");
		pagoOK1.setNumeroFactura("");
		pagoOK1.setSucursal("0100");
		pagoOK1.setNumeroCuenta("000002005294");

		pagoOK2 = new PagoMultipleView();
		pagoOK2.setTipoMonto("0");
		pagoOK2.setTipoDeSeleccion("I");
		pagoOK2.setTipoCuenta("09");
		pagoOK2.setMonto("000000000123.34");
		pagoOK2.setMoneda("ARS");
		pagoOK2.setCodigoEmpresa("D265");
		pagoOK2.setIdentificacionCliente("20123458622082016  ");
		pagoOK2.setNumeroFactura("20123458622         ");
		pagoOK2.setSucursal("0033");
		pagoOK2.setNumeroCuenta("000000081995");

		pagoOK3 = new PagoMultipleView();
		pagoOK3.setTipoMonto("0");
		pagoOK3.setTipoDeSeleccion("I");
		pagoOK3.setTipoCuenta("09");
		pagoOK3.setMonto("000000009123.55");
		pagoOK3.setMoneda("ARS");
		pagoOK3.setCodigoEmpresa("REPE");
		pagoOK3.setIdentificacionCliente("1135151112         ");
		pagoOK3.setNumeroFactura("");
		pagoOK3.setSucursal("0100");
		pagoOK3.setNumeroCuenta("000002005294");
	}

	/**
	 * The Class PagoMultipleSEITestConfiguration.
	 */
	@Configuration
	public static class PagoMultipleSEITestConfiguration {

		/**
		 * Pago multiple manager.
		 *
		 * @return the pago multiple manager
		 */
		@Bean
		public PagoMultipleManager pagoMultipleManager() {
			return Mockito.mock(PagoMultipleManager.class);
		}

		/**
		 * Pago multiple SEI.
		 *
		 * @return the pago multiple SEI
		 */
		@Bean(name = "pagoMultipleSEI")
		public PagoMultipleSEI pagoMultipleSEI() {
			return new PagoMultipleSEIImpl();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("pagoMultipleSEI");
	}

	/**
	 * Solicitar pago multiple.
	 */
	@Test
	public void solicitarPagoMultiple() {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		Respuesta<PagoMultipleView> retorno = new Respuesta<PagoMultipleView>();
		Respuesta<PagoMultipleListView> respManager = new Respuesta<PagoMultipleListView>();

		respManager.setRespuesta(pagoMultipleListView);
		respManager.setEstadoRespuesta(EstadoRespuesta.OK);
		respManager.setRespuestaVacia(false);

		Mockito.when(pagoMultipleManager.solicitarPagoMultiple(Matchers.any(PagoMultipleListView.class)))
				.thenReturn(respManager);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/pagoMultiple/solicitarPagoMultiple");
		retorno = client.post(pagoMultipleListView, Respuesta.class);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Ejecutar pago multiple.
	 */
	@Test
	public void ejecutarPagoMultiple() {
		PagoMultipleListView pagoMultipleListView = new PagoMultipleListView();
		Respuesta<PagoMultipleListView> retorno = new Respuesta<PagoMultipleListView>();
		Respuesta<PagoMultipleListView> retornoMsg = new Respuesta<PagoMultipleListView>();

		List<PagoMultipleView> pagos = new ArrayList<PagoMultipleView>();
		pagos.add(pagoOK1);
		pagos.add(pagoOK2);
		pagos.add(pagoOK3);
		pagoMultipleListView.setPagos(pagos);

		retornoMsg.setRespuesta(pagoMultipleListView);
		retornoMsg.setEstadoRespuesta(EstadoRespuesta.OK);
		retornoMsg.setRespuestaVacia(false);
		addSleepTime(5000);
		Mockito.when(pagoMultipleManager.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.class)))
				.thenReturn(retornoMsg);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/pagoMultiple/ejecutarPagoMultiple");
		retorno = client.post(pagoMultipleListView, Respuesta.class);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(retorno.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	/**
	 * Test para grabar una estadistica al acceder a un comprbante.
	 */
	@Test
	public void grabarEstadisticaPagoMultipleTest() {
		Respuesta<Boolean> retorno = new Respuesta<Boolean>();

		Respuesta<Boolean> respuestaEsperada = new Respuesta<Boolean>();
		respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaEsperada.setRespuestaVacia(true);

		Mockito.when(pagoMultipleManager.grabarEstadisticaComprobantePagoMultiple()).thenReturn(respuestaEsperada);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/pagoMultiple/grabarEstadisticaComprobantePagoMultiple");
		retorno = client.post(null, Respuesta.class);

		Assert.assertNotNull(retorno.getEstadoRespuesta());
		Assert.assertEquals(retorno.getEstadoRespuesta(), respuestaEsperada.getEstadoRespuesta());
	}
}
