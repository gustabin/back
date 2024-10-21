/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.transferencias.sei.AgendaTransferenciaSEIIT.AgendaTransferenciaSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.transferencias.sei.impl.AgendaTransferenciaSEIImpl;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AgendaTransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ComprobanteTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfiguracionStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackModificarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ModificacionTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;


/**
 * The Class AgendaTransferenciaSEITest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		AgendaTransferenciaSEITestConfiguration.class })
public class AgendaTransferenciaSEIIT extends AbstractSEITest {

	/** The agenda transferencia manager. */
	@Autowired
	private AgendaTransferenciaManager agendaTransferenciaManager;

	/**
	 * Inits the.
	 */
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * The Class AgendaTransferenciaSEITestConfiguration.
	 */
	@Configuration
	public static class AgendaTransferenciaSEITestConfiguration {

		/**
		 * Agenda transferencia manager.
		 *
		 * @return the agenda transferencia manager
		 */
		@Bean
		public AgendaTransferenciaManager agendaTransferenciaManager() {
			return Mockito.mock(AgendaTransferenciaManager.class);
		}

		/**
		 * Login SEI.
		 *
		 * @return the agenda transferencia SEI
		 */
		@Bean(name = "agendaTransferenciaSEI")
		public AgendaTransferenciaSEI loginSEI() {
			return new AgendaTransferenciaSEIImpl();
		}

        @Bean
        public EstadisticaManager estadisticaManager() {
            return Mockito.mock(EstadisticaManager.class);
	}

        @Bean
        public SesionCliente sesionCliente() {
            return Mockito.mock(SesionCliente.class);
        }
	}

	/**
	 * Gets the detalle transferencia agendada test.
	 *
	 * @return the detalle transferencia agendada test
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void getDetalleTransferenciaAgendadaTest() {

		Respuesta<TransferenciaAgendadaDetalleView> transferenciaAgendadaDetalleViewRespuesta = new Respuesta<TransferenciaAgendadaDetalleView>();
		TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView = new TransferenciaAgendadaDetalleView();
		transferenciaAgendadaDetalleViewRespuesta.setRespuesta(transferenciaAgendadaDetalleView);
		transferenciaAgendadaDetalleViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		transferenciaAgendadaDetalleViewRespuesta.setRespuestaVacia(false);

		Mockito.when(
				agendaTransferenciaManager.obtenerAgendaTransferenciaDetalle(Matchers.any(TransferenciaAgendadaView.class)))
				.thenReturn(transferenciaAgendadaDetalleViewRespuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/obtenerDetalleTransferenciaAgendada");
		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Obtener configuracion stop debit test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfiguracionStopDebitTest() {

		Respuesta<ConfiguracionStopDebitView> configuracionStopDebitViewRespuesta = new Respuesta<ConfiguracionStopDebitView>();
		ConfiguracionStopDebitView configuracionStopDebitView = new ConfiguracionStopDebitView();
		configuracionStopDebitViewRespuesta.setRespuesta(configuracionStopDebitView);
		configuracionStopDebitViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		configuracionStopDebitViewRespuesta.setRespuestaVacia(false);

		Mockito.when(agendaTransferenciaManager.solicitarStopDebit(Matchers.any(TransferenciaAgendadaView.class)))
				.thenReturn(configuracionStopDebitViewRespuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/obtenerConfiguracionStopDebit");
		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Stop debit test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void stopDebitTest() {

		Respuesta<FeedbackStopDebitView> feedbackStopDebitViewRespuesta = new Respuesta<FeedbackStopDebitView>();
		FeedbackStopDebitView feedbackStopDebitView = new FeedbackStopDebitView();
		feedbackStopDebitViewRespuesta.setRespuesta(feedbackStopDebitView);
		feedbackStopDebitViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		feedbackStopDebitViewRespuesta.setRespuestaVacia(false);

		Mockito.when(agendaTransferenciaManager.ejecutarStopDebit(Matchers.any(TransferenciaAgendadaView.class)))
				.thenReturn(feedbackStopDebitViewRespuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/stopDebit");
		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Eliminar test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void eliminarTest() {

		Respuesta<FeedbackEliminarView> feedbackEliminarViewRespuesta = new Respuesta<FeedbackEliminarView>();
		FeedbackEliminarView feedbackEliminarView = new FeedbackEliminarView();
		feedbackEliminarViewRespuesta.setRespuesta(feedbackEliminarView);
		feedbackEliminarViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		feedbackEliminarViewRespuesta.setRespuestaVacia(false);

		Mockito.when(agendaTransferenciaManager.eliminarTransferencia(Matchers.any(TransferenciaAgendadaView.class)))
				.thenReturn(feedbackEliminarViewRespuesta);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/eliminarTransferencia");
		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Obtener configuracion modificacion test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfiguracionModificacionTest() {

		Respuesta<TransferenciaAgendadaDetalleView> transferenciaAgendadaDetalleView = new Respuesta<TransferenciaAgendadaDetalleView>();
		TransferenciaAgendadaDetalleView configuracionModificacionView = new TransferenciaAgendadaDetalleView();
		transferenciaAgendadaDetalleView.setRespuesta(configuracionModificacionView);
		transferenciaAgendadaDetalleView.setEstadoRespuesta(EstadoRespuesta.OK);
		transferenciaAgendadaDetalleView.setRespuestaVacia(false);

		Mockito.when(
				agendaTransferenciaManager.solicitarModificacionTransferencia(Matchers.any(TransferenciaAgendadaView.class)))
				.thenReturn(transferenciaAgendadaDetalleView);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/obtenerConfiguracionModificacion");
		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Obtener confirmacion modificacion test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerConfirmacionModificacionTest() {
		Respuesta<TransferenciaAgendadaDetalleView> respuestaTransferenciaAgendadaDetalleView = new Respuesta<TransferenciaAgendadaDetalleView>();
		TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView = new TransferenciaAgendadaDetalleView();
		respuestaTransferenciaAgendadaDetalleView.setRespuesta(transferenciaAgendadaDetalleView);
		respuestaTransferenciaAgendadaDetalleView.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaAgendadaDetalleView.setRespuestaVacia(false);
		
		Mockito.when(agendaTransferenciaManager.solicitarModificacionTransferencia(Matchers.any(TransferenciaAgendadaView.class))).thenReturn(respuestaTransferenciaAgendadaDetalleView);

		WebClient client = getWebClient();
		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/obtenerConfiguracionModificacion");
		ModificacionTransferenciaAgendadaView modificacionTransferenciaAgendadaView = new ModificacionTransferenciaAgendadaView();
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(modificacionTransferenciaAgendadaView,
				Respuesta.class);
		assertNotNull(respuesta.getRespuesta());
	}

	/**
	 * Obtener comprobante modificacion test.
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerComprobanteModificacionTest() {

		Respuesta<ComprobanteTransferenciaAgendadaView> comprobanteTransferenciaAgendadaViewRespuesta = new Respuesta<ComprobanteTransferenciaAgendadaView>();
		ComprobanteTransferenciaAgendadaView comprobanteTransferenciaAgendadaView = new ComprobanteTransferenciaAgendadaView();
		comprobanteTransferenciaAgendadaViewRespuesta.setRespuesta(comprobanteTransferenciaAgendadaView);
		comprobanteTransferenciaAgendadaViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		comprobanteTransferenciaAgendadaViewRespuesta.setRespuestaVacia(false);

//		Mockito.when(agendaTransferenciaManager.obtenerComprobanteModificacion())
//				.thenReturn(comprobanteTransferenciaAgendadaViewRespuesta);

		WebClient client = getWebClient();
		client.accept(MediaType.APPLICATION_JSON);
        client.path("/agendaTransferencias/obtenerComprobanteModificacion");
        addSleepTime(5000);
		Respuesta<AgendaView> respuesta = client.post(null, Respuesta.class);
		assertNotNull(respuesta.getRespuesta());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.AbstractSEITest#getServiceBeanToTest()
	 */
	@Override
	protected Object getServiceBeanToTest() {
		return applicationContext.getBean("agendaTransferenciaSEI");
	}

	
	/**
	 * Ejecutar modificacion agenda transferencia feedback OK.
	 * {@link AgendaTransferenciaManager.ejecutarModificacionTransferenciaAgendada}
	 */
//	@SuppressWarnings("unchecked")
//	@Test
//	public void ejecutarModificacionAgendaTransferencia_OK(){
//		Respuesta<TransferenciaAgendadaView> configuracionModificacionViewRespuesta = new Respuesta<TransferenciaAgendadaView>();
//		ConfiguracionModificacionView configuracionModificacionView = new ConfiguracionModificacionView();
//		configuracionModificacionViewRespuesta.setRespuesta(configuracionModificacionView);
//		configuracionModificacionViewRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
//		configuracionModificacionViewRespuesta.setRespuestaVacia(false);
//
//		Mockito.when(
//				agendaTransferenciaManager.ejecutarModificacionTransferenciaAgendada(Matchers.any(TransferenciaAgendadaView.class)))
//				.thenReturn(configuracionModificacionViewRespuesta);
//
//		WebClient client = getWebClient();
//		client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		client.path("/transferencias/ejecutarModificacionTransferenciaAgendada");
//		TransferenciaAgendadaView agendaTransferencia = new TransferenciaAgendadaView();
//		Respuesta<AgendaView> respuesta = client.post(agendaTransferencia, Respuesta.class);
//		assertNotNull(respuesta.getRespuesta());
//	}
}
