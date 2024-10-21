package ar.com.santanderrio.obp.servicios.comprobantes.sei;

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
import ar.com.santanderrio.obp.servicios.comprobantes.sei.impl.ComprobantesSEIImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.ComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.manager.DetalleComprobantesManager;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesView;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ComprobantesViewIn;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

// TODO: Auto-generated Javadoc
/**
 * The Class ComprobantesSEIIT.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ComprobantesSEIIT.ComprobantesSEITestConfiguration.class })
public class ComprobantesSEIIT extends AbstractSEITest {

	/** The comprobantes manager. */
	@Autowired
	ComprobantesManager comprobantesManager;

	/** The detalle comprobantes manager. */
	@Autowired
	DetalleComprobantesManager detalleComprobantesManager;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class ComprobantesSEITestConfiguration.
	 */
	@Configuration
	public static class ComprobantesSEITestConfiguration {

		/**
		 * Comprobantes SEI.
		 * 
		 * @return the comprobantes SEI
		 */
		@Bean(name = "comprobantesSEI")
		public ComprobantesSEI comprobantesSEI() {
			return new ComprobantesSEIImpl();
		}

		/**
		 * Comprobantes manager.
		 * 
		 * @return the comprobantes manager
		 */
		@Bean
		public ComprobantesManager comprobantesManager() {
			return Mockito.mock(ComprobantesManager.class);
		}

		/**
		 * Detalle comprobantes manager.
		 * 
		 * @return the detalle comprobantes manager
		 */
		@Bean
		public DetalleComprobantesManager detalleComprobantesManager() {
			return Mockito.mock(DetalleComprobantesManager.class);
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
		return applicationContext.getBean("comprobantesSEI");
	}

	/**
	 * Obtener comprobantes test.
	 */
	@Test
	public void obtenerComprobantesTest() {
		Respuesta<ComprobantesView> respuesta = new Respuesta<ComprobantesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesView comprobantes = new ComprobantesView();
		ComprobanteView comprobante = new ComprobanteView();
		List<ComprobanteView> listaComprobantes = new ArrayList<ComprobanteView>();
		comprobante.setDestinatario("Juan Prueba");
		comprobante.setFecha("03/03/2017");
		comprobante.setImportePesos("$1000,00");
		comprobante.setTransaccion("Transferencia");
		comprobante.setCtaMedioDePagoPesos("000-012345/6");
		comprobante.setTipoCtaMedioDePagoPesos("Cuenta única en $");
		listaComprobantes.add(comprobante);
		comprobantes.setComprobantes(listaComprobantes);
		respuesta.setRespuesta(comprobantes);
		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		Mockito.when(comprobantesManager.obtenerComprobantes(Matchers.any(ComprobantesViewIn.class))).thenReturn(
				respuesta);

		viewIn.setRecargaPagina(Boolean.TRUE);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("comprobantes/obtenerTransacciones");
		addSleepTime(5000);
		Respuesta<ComprobantesView> retorno = client.post(viewIn, Respuesta.class);
		Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
	}

	/**
	 * Obtener detalle comprobantes test.
	 */
	@Test
	public void obtenerDetalleComprobantesTest() {
		Respuesta<DetalleComprobanteView> respuesta = new Respuesta<DetalleComprobanteView>();
		DetalleComprobanteView comprobantes = new DetalleComprobanteView();
		respuesta.setRespuesta(comprobantes);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(detalleComprobantesManager.obtenerDetalleComprobantes(Matchers.anyString(), true)).thenReturn(respuesta);
		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("comprobantes/obtenerDetalleTransacciones");
		Object comprobantesIn = new ComprobantesViewIn();
		Respuesta<DetalleComprobanteView> retorno = client.post(comprobantesIn, Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Descargar comprobante PDF test.
	 */
	@Test
	public void descargarComprobantePDFTest() {
		Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ReporteView reporte = new ReporteView();
		reporte.setNombre("pepe");
		respuesta.setRespuesta(reporte);
		Mockito.when(detalleComprobantesManager.descargaComprobanteGrilla()).thenReturn(respuesta);
		WebClient client = getWebClient();
		client.accept(MediaType.APPLICATION_JSON);
		client.path("comprobantes/descargarComprobantePDF");
		addSleepTime(5000);
		Respuesta<ReporteView> retorno = client.post(null, Respuesta.class);
		Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
	}

	/**
	 * Request comprobantes test.
	 */
	@Test
	public void RequestComprobantesTest() {
		Respuesta<ComprobantesView> respuesta = new Respuesta<ComprobantesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		ComprobantesView comprobantes = new ComprobantesView();
		ComprobanteView comprobante = new ComprobanteView();
		List<ComprobanteView> listaComprobantes = new ArrayList<ComprobanteView>();
		comprobante.setDestinatario("Juan Prueba");
		comprobante.setFecha("03/03/2017");
		comprobante.setImportePesos("$1000,00");
		comprobante.setTransaccion("Transferencia");
		comprobante.setCtaMedioDePagoPesos("000-012345/6");
		comprobante.setTipoCtaMedioDePagoPesos("Cuenta única en $");
		listaComprobantes.add(comprobante);
		comprobantes.setComprobantes(listaComprobantes);
		respuesta.setRespuesta(comprobantes);

		Mockito.when(comprobantesManager.obtenerComprobantes(Matchers.any(ComprobantesViewIn.class))).thenReturn(
				respuesta);

		ComprobantesViewIn viewIn = new ComprobantesViewIn();
		viewIn.setRecargaPagina(Boolean.TRUE);
		viewIn.setFechaDesde("24/10/2017");
		viewIn.setFechaDesde("26/10/2017");
		viewIn.setImporteDesde(null);
		viewIn.setImporteHasta(null);
		viewIn.setTransacciones(null);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("comprobantes/obtenerTransacciones");
		addSleepTime(5000);
		Respuesta<ComprobantesView> retorno = client.post(viewIn, Respuesta.class);
		Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
	}

	/**
	 * Test des serializar json comprobantes view in.
	 */
	@Test
	public void testDesSerializarJsonComprobantesViewIn() {

		Gson gson = new Gson();
		String json = " {" + "empresa : null, " + "fechaDesde : \"24/10/2017\", " + "fechaHasta  : \"26/10/2017\",  "
				+ "fechaTipo  :  null,  " + "importeDesde  :  null,  importeHasta  :  null,  "
				+ "recargaPagina   :  true,  " + "transacciones  : " + "[ {" + "idTransaccion : 1 " + "} ]" + "}";
		String json2 = "{\"recargaPagina\":true,\"fechaDesde\":\"24/10/2017\",\"fechaHasta\":\"26/10/2017\",\"transacciones\":[{\"idTransaccion\":0}]}";

		try {

			ComprobantesViewIn viewIn = gson.fromJson(json2, ComprobantesViewIn.class);

			String in = gson.toJson(viewIn);

			String respuesta = "{\"recargaPagina\":true,\"fechaDesde\":\"24/10/2017\",\"fechaHasta\":\"26/10/2017\",\"transacciones"
					+ "\":[{\"idTransaccion\":0}]}";

			Assert.assertEquals(respuesta, in);

		} catch (JsonSyntaxException e) {
			System.out.print(e);
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
