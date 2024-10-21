/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.sei;

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

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.mock.ConfirmacionRecargaViewMock;
import ar.com.santanderrio.obp.servicios.nuevarecarga.sei.impl.NuevaRecargaSEIImpl;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.NuevaRecargaManager;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;

/**
 * The Class NuevaRecargaSEITest.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.nuevarecarga.sei.NuevaRecargaSEIIT.NuevaRecargaSEITestConfiguration.class })
public class NuevaRecargaSEIIT extends AbstractSEITest {

	/** The Constant MSJ_ERROR_TOKEN_INPUT. */
	private static final String MSJ_ERROR_TOKEN_INPUT = "<p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>";

	/** The Constant MSJ_ERROR_SYNC_TOKEN. */
	private static final String MSJ_ERROR_SYNC_TOKEN = "La operación solicitada no pudo ser validada. Por favor, verifica que el Token sea correcto, y que el horario de tu dispositivo esté sincronizado con el de la App Santander.";

	/** The Constant MSJ_ERROR_BLOQUEO_TOKEN. */
	private static final String MSJ_ERROR_BLOQUEO_TOKEN = "La operación solicitada no pudo ser validada. Por favor, dirigite a un Cajero Automático de la Red Banelco para activar tu Token de seguridad.";

	/** The Constant MSJ_ERROR_GENERICO. */
	private static final String MSJ_ERROR_GENERICO = "<p>Ocurrió un error en nuestros servicios.Por favor, volvé a ingresar en unos minutos.</p>";

	private static final String MSJ_ERROR_SIN_METODO = "No se encuentran medios de pago disponibles para ese servicio.";

	/** The nueva recarga manager. */
	@Autowired
	private NuevaRecargaManager nuevaRecargaManager;

	/** The mensaje BO. */
	@SuppressWarnings("unused")
	@Autowired
	private MensajeBO mensajeBO;

	/** The respuesta factory. */
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/**
	 * Inits the.
	 */
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Gets bean nuevaRecargaSEI.
	 *
	 * @return the service bean to test
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("nuevaRecargaSEI");
	}

	/**
	 * The Class NuevaRecargaSEITestConfiguration.
	 */
	@Configuration
	public static class NuevaRecargaSEITestConfiguration {

		/**
		 * Nueva recarga SEI.
		 *
		 * @return the nueva recarga SEI
		 */
		@Bean(name = "nuevaRecargaSEI")
		public NuevaRecargaSEI nuevaRecargaSEI() {
			return new NuevaRecargaSEIImpl();
		}

		/**
		 * Nueva recarga manager.
		 *
		 * @return the nueva recarga manager
		 */
		@Bean
		public NuevaRecargaManager nuevaRecargaManager() {
			return Mockito.mock(NuevaRecargaManager.class);
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
	}

	/**
	 * Obtiene la confirmacion para nueva recarga con challenge token
	 * habilitado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTokenHabilitado() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafio(),
						obtenerItemsMensajeTipoErrorDesafioTokenHabilitado()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge tarj coord OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTarjCoordOK() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTarjetaCoordenadasDesafio(),
						obtenerItemsMensajeTipoErrorDesafio()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge tarj coord con
	 * reintentos agotados.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTarjCoordConReintentosAgotados() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock
								.completarInfoConfirmacionRecargaViewWarningTarjetaCoordenadasDesafioConReintentosAgotados(),
						obtenerItemsMensajeTipoErrorDesafio()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge ocho digitos OK.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeOchoDigitosOK() {

		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningOchoDigitos(),
						obtenerItemsMensajeTipoErrorDesafio()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge token sync exception
	 * validacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTokenSyncExceptionValidacion() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafioSyncError(),
						obtenerItemsMensajeTipoErrorSyncToken()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafio(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge token bloqueo
	 * exception validacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTokenBloqueoExceptionValidacion() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaWarning(ConfirmacionRecargaView.class,
						ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafioErrorBloqueo(),
						obtenerItemsMensajeTipoErrorBloqueoToken()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafio(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	/**
	 * Obtiene la confirmacion para nueva recarga challenge token error generico
	 * validacion.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaChallengeTokenErrorGenericoValidacion() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaError(ConfirmacionRecargaView.class,
						obtenerItemsMensajeTipoErrorGenerico()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafio(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionNuevaRecargaErrorSinMetodoAutentificacionValidacion() {
		Mockito.when(nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(Matchers.any(ConfirmacionRecargaView.class)))
				.thenReturn(respuestaFactory.crearRespuestaError(ConfirmacionRecargaView.class,
						obtenerItemsMensajeTipoErrorSinMetodoAutentificacion()));

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		client.path("/nuevaRecarga/recargar");
		Respuesta<ConfirmacionRecargaView> retorno = client
				.post(ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewWarningTokenDesafio(), Respuesta.class);

		Assert.assertNotNull(retorno);
	}

	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorSinMetodoAutentificacion() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_SIN_METODO));
		items.get(0).setTipoError(TipoError.DENY_RSA.getDescripcion());
		items.trimToSize();
		return items;
	}

	/**
	 * Obtener items mensaje tipo error generico.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorGenerico() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_GENERICO));
		items.get(0).setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
		items.trimToSize();
		return items;
	}

	/**
	 * Obtener items mensaje tipo error bloqueo token.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorBloqueoToken() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_BLOQUEO_TOKEN));
		items.get(0).setTipoError(TipoError.ERROR_BLOQUEO_TOKEN.getDescripcion());
		items.trimToSize();
		return items;
	}

	/**
	 * Obtener items mensaje tipo error sync token.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorSyncToken() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_SYNC_TOKEN));
		items.get(0).setTipoError(TipoError.ERROR_SYNC_TOKEN.getDescripcion());
		items.trimToSize();
		return items;
	}

	/**
	 * Obtener items mensaje tipo error desafio.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorDesafio() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta());
		items.get(0).setTipoError(TipoError.DESAFIO.getDescripcion());
		items.trimToSize();
		return items;
	}

	/**
	 * Obtener items mensaje tipo error desafio token habilitado.
	 *
	 * @return the list
	 */
	private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorDesafioTokenHabilitado() {
		ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
		items.add(new ItemMensajeRespuesta(MSJ_ERROR_TOKEN_INPUT));
		items.get(0).setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
		items.trimToSize();
		return items;
	}

}
