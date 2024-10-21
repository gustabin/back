/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.sei;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.prestamos.sei.impl.InicioPrestamoSEIImpl;
import ar.com.santanderrio.obp.servicios.prestamos.view.ObtenerPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ObtenerPrestamoInViewMock;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamosViewMock;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.CuotasPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.InicioPrestamoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SimuladorPrestamoManager;

/**
 * The Class InicioPrestamoIISEIIT.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		InicioPrestamoIISEIIT.InicioPrestamosSEITestConfiguration.class })
public class InicioPrestamoIISEIIT extends AbstractSEITest {

	/** The inicio prestamo manager. */
	@Autowired

	private InicioPrestamoManager inicioPrestamoManager;

	/** The respuesta factory. */
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The simulador prestamo manager. */
	@SuppressWarnings("unused")
	@Autowired
	private SimuladorPrestamoManager simuladorPrestamoManager;

	/** The estadistica manager. */
	@SuppressWarnings("unused")
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The cuotas prestamo manager. */
	@SuppressWarnings("unused")
	@Autowired
	private CuotasPrestamoManager cuotasPrestamoManager;

	/** The mensaje BO. */
	@SuppressWarnings("unused")
	@Autowired
	private MensajeBO mensajeBO;

	/**
	 * Inits the.
	 */
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Obtiene el Service Bean para testear.
	 *
	 * @return the service bean to test
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("inicioPrestamoSEI");
	}

	/**
	 * The Class InicioPrestamosSEITestIIConfiguration.
	 */
	@Configuration
	public static class InicioPrestamosSEITestConfiguration {

		/**
		 * Inicio prestamo SEI.
		 *
		 * @return the inicio prestamo SEI
		 */
		@Bean(name = "inicioPrestamoSEI")
		public InicioPrestamoSEI inicioPrestamoSEI() {
			return new InicioPrestamoSEIImpl();
		}

		/**
		 * Inicio prestamo manager.
		 *
		 * @return the inicio prestamo manager
		 */
		@Bean
		public InicioPrestamoManager inicioPrestamoManager() {
			return Mockito.mock(InicioPrestamoManager.class);
		}

		/**
		 * Respuesta factory.
		 *
		 * @return the respuesta factory
		 */
		@Bean
		public RespuestaFactory respuestaFactory() {
			return Mockito.mock(RespuestaFactory.class);
		}

		/**
		 * Simulador prestamo manager.
		 *
		 * @return the simulador prestamo manager
		 */
		@Bean
		public SimuladorPrestamoManager simuladorPrestamoManager() {
			return Mockito.mock(SimuladorPrestamoManager.class);
		}

		/**
		 * Estadistica manager.
		 *
		 * @return the estadistica manager
		 */
		@Bean
		public EstadisticaManager estadisticaManager() {
			return Mockito.mock(EstadisticaManager.class);
		}

		/**
		 * Cuotas prestamo manager.
		 *
		 * @return the cuotas prestamo manager
		 */
		@Bean
		public CuotasPrestamoManager cuotasPrestamoManager() {
			return Mockito.mock(CuotasPrestamoManager.class);
		}

		/**
		 * Mensaje BO.
		 *
		 * @return the mensaje BO
		 */
		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
		}
	}

	/**
	 * Test para obtener el listado de prestamos OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamosTest() {
		Mockito.when(inicioPrestamoManager.obtenerPrestamos(Matchers.any(ObtenerPrestamoInView.class))).thenReturn(
				respuestaFactory.crearRespuestaOk(PrestamosView.class, PrestamosViewMock.obtenerPretamosViewOK()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamos");
		Respuesta<PrestamosView> retorno = client.post(ObtenerPrestamoInViewMock.obtenerPrestamoInViewPersonal(),
				Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Test para obtener el listado de prestamos con Error en la consulta al
	 * servicio de Preformalizacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerPrestamosConErrorPreformalizacionTest() {
		Mockito.when(inicioPrestamoManager.obtenerPrestamos(Matchers.any(ObtenerPrestamoInView.class)))
				.thenReturn(obtenerRespuestaPrestamosViewConErrorPreformalizacion());

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/prestamos/obtenerPrestamos");
		Respuesta<PrestamosView> retorno = client.post(ObtenerPrestamoInViewMock.obtenerPrestamoInViewPersonal(),
				Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la respuesta de prestamos view con error en la preformalizacion.
	 *
	 * @return the respuesta
	 */
	private Respuesta<PrestamosView> obtenerRespuestaPrestamosViewConErrorPreformalizacion() {
		Respuesta<PrestamosView> respuestaPrestamosView = new Respuesta<PrestamosView>();
		respuestaPrestamosView.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaPrestamosView.setItemMensajeRespuesta(obtenerItemsMensaje());
		respuestaPrestamosView.setRespuesta(PrestamosViewMock.obtenerPretamosViewConErrorPreformalizacion());
		respuestaPrestamosView.setRespuestaVacia(false);
		return respuestaPrestamosView;
	}

	/**
	 * Obtiene la lista de items mensaje.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensaje() {
		List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(obtenerItem("", TipoError.ERROR_PLAZO_PRESTAMOS.getDescripcion(),
				"Ourrió un error en nuestros servicios y no podemos mostrarte el plazo de tus préstamos.  Por favor volvé a intentar en unos minutos."));
		return items;
	}

	/**
	 * Obtiene el item.
	 *
	 * @param tag
	 *            the tag
	 * @param tipoError
	 *            the tipo error
	 * @param mensaje
	 *            the mensaje
	 * @return the item mensaje respuesta
	 */
	private ItemMensajeRespuesta obtenerItem(String tag, String tipoError, String mensaje) {
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTag(tag);
		item.setTipoError(tipoError);
		item.setMensaje(mensaje);
		return item;
	}
}
