package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei;

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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.manager.InfoMercadoManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.sei.InfoMercadoSEITest.InfoMercadoSEIITConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.IndicesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.view.InfoMercadoViewIn;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { InfoMercadoSEIITConfiguration.class })
public class InfoMercadoSEITest extends AbstractSEITest {

	@Autowired
	private InfoMercadoManager infoMercadoManager;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void inicioInfoMercadoTest() {
		Respuesta<InfoMercadoView> respuesta = new Respuesta<InfoMercadoView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(infoMercadoManager.inicioInfoMercado(Matchers.any(InfoMercadoViewIn.class))).thenReturn(respuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/informacionMercado/inicio");

		Respuesta<InfoMercadoView> response = client.post(new InfoMercadoViewIn(), Respuesta.class);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void consultaIndicesTest() {
		Respuesta<IndicesView> respuesta = new Respuesta<IndicesView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(infoMercadoManager.obtenerIndices(Matchers.any(InfoMercadoViewIn.class))).thenReturn(respuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/informacionMercado/obtenerIndices");

		Respuesta<IndicesView> response = client.post(new InfoMercadoViewIn(), Respuesta.class);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void obtenerInfoMercadoTest() {
		Respuesta<InfoMercadoView> respuesta = new Respuesta<InfoMercadoView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(infoMercadoManager.obtenerGrillaInfoMercado(Matchers.any(InfoMercadoViewIn.class),
				Matchers.anyBoolean())).thenReturn(respuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/informacionMercado/obtenerInfoMercado");

		Respuesta<IndicesView> response = client.post(new InfoMercadoViewIn(), Respuesta.class);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Configuration
	public static class InfoMercadoSEIITConfiguration {

		@Bean
		public InfoMercadoManager infoMercadoManager() {
			return Mockito.mock(InfoMercadoManager.class);
		}

		@Bean
		public InfoMercadoSEI infoMercadoSEI() {
			return new InfoMercadoSEIImpl();
		}
	}

	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("infoMercadoSEI");
	}

}
