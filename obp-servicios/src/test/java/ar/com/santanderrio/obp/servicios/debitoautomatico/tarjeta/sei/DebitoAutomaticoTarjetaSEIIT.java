/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.sei;

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
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.manager.DebitoAutomaticoTarjetaManager;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.ComprobanteDebitoAutomaticoTarjetaViewMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.mock.ParametroAdhesionDebitoTarjetaInViewMock;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.sei.impl.DebitoAutomaticoTarjetaSEIImpl;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ComprobanteDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.web.view.ParametroAdhesionDebitoTarjetaInView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DebitoAutomaticoTarjetaSEIIT.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		DebitoAutomaticoTarjetaSEIIT.DebitoAutomaticoTarjetaSEITestConfiguration.class })
public class DebitoAutomaticoTarjetaSEIIT extends AbstractSEITest {

	/** The Constant MSJ_ERROR_GENERICO. */
	private static final String MSJ_ERROR_GENERICO = "No pudimos realizar la solicitud de adhesión a débito automático de <b>Telecom</b>.";

	/** The debito automatico tarjeta manager. */
	@Autowired
	private DebitoAutomaticoTarjetaManager debitoAutomaticoTarjetaManager;

	/** The respuesta factory. */
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The mensaje BO. */
	@SuppressWarnings("unused")
	@Autowired
	private MensajeBO mensajeBO;

	/** The sesion cliente. */
	@SuppressWarnings("unused")
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@SuppressWarnings("unused")
	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Inits the.
	 */
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("debitoAutomaticoTarjetaSEI");
	}

	/**
	 * The Class DebitoAutomaticoTarjetaSEITestConfiguration.
	 */
	@Configuration
	public static class DebitoAutomaticoTarjetaSEITestConfiguration {

		/**
		 * Debito automatico tarjeta SEI.
		 *
		 * @return the debito automatico tarjeta SEI
		 */
		@Bean(name = "debitoAutomaticoTarjetaSEI")
		public DebitoAutomaticoTarjetaSEI debitoAutomaticoTarjetaSEI() {
			return new DebitoAutomaticoTarjetaSEIImpl();
		}

		/**
		 * Debito automatico tarjeta manager.
		 *
		 * @return the debito automatico tarjeta manager
		 */
		@Bean
		public DebitoAutomaticoTarjetaManager debitoAutomaticoTarjetaManager() {
			return Mockito.mock(DebitoAutomaticoTarjetaManager.class);
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
		 * Mensaje BO.
		 *
		 * @return the mensaje BO
		 */
		@Bean
		public MensajeBO mensajeBO() {
			return Mockito.mock(MensajeBO.class);
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

	/**
	 * Obtener confirmacion ahdesion debito tarjeta OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionAhdesionDebitoTarjetaOKTest() {
		Mockito.when(debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(Matchers.any(ParametroAdhesionDebitoTarjetaInView.class)))
				.thenReturn(respuestaFactory.crearRespuestaOk(ComprobanteDebitoAutomaticoTarjetaView.class,
						ComprobanteDebitoAutomaticoTarjetaViewMock
								.completarInfoComprobanteDebitoAutomaticoTarjetaView()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/debitoAutomaticoTarjeta/adherir");
		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> retorno = client
				.post(ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosVisa(), Respuesta.class);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(EstadoRespuesta.OK, retorno.getEstadoRespuesta());
	}

	/**
	 * Obtener confirmacion ahdesion debito tarjeta error generico.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionAhdesionDebitoTarjetaErrorGenericoTest() {
		Mockito.when(debitoAutomaticoTarjetaManager
				.obtenerAdhesionDebitoTarjeta(Matchers.any(ParametroAdhesionDebitoTarjetaInView.class)))
				.thenReturn(respuestaFactory.crearRespuestaError(ComprobanteDebitoAutomaticoTarjetaView.class,
						obtenerItemsMensajeErrorGenerico()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/debitoAutomaticoTarjeta/adherir");
		Respuesta<ComprobanteDebitoAutomaticoTarjetaView> retorno = client
				.post(ParametroAdhesionDebitoTarjetaInViewMock.completarInfoParametrosVisa(), Respuesta.class);

		Assert.assertNotNull(retorno);
		Assert.assertEquals(EstadoRespuesta.ERROR, retorno.getEstadoRespuesta());
	}

	/**
	 * Obtener items mensaje error generico.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeErrorGenerico() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_GENERICO));
		items.get(0).setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		items.trimToSize();
		return items;
	}

}
