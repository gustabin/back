/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendamientoTransferenciaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptosTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfiguracionStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * Migracion de AgendaTransferenciaController.
 */
@Path("/agendaTransferencias")
public interface AgendaTransferenciaSEI {

	/** The Constant AGENDA_TRANSFERENCIAS. */
	String AGENDA_TRANSFERENCIAS = "/obtenerAgendaTransferencias";

	/** The Constant DETALLE_TRANSFERENCIA. */
	String DETALLE_TRANSFERENCIA = "/obtenerDetalleTransferenciaAgendada";

	/** The Constant CONFIGURACION_STOP_DEBIT. */
	String CONFIGURACION_STOP_DEBIT = "/obtenerConfiguracionStopDebit";

	/** The Constant CONFIGURACION_MODIFICACION. */
	String CONFIGURACION_MODIFICACION = "/obtenerConfiguracionModificacion";

	/** The Constant CONFIRMACION_MODIFICACION. */
	String CONFIRMACION_MODIFICACION = "/obtenerConfirmacionModificacion";

	/** The Constant COMPROBANTE_STOP_DEBIT. */
	String GRABAR_ESTADISTICAS_COMPROBANTE_STOP_DEBIT = "/grabarEstadisticaComprobanteStopDebit";

	/** The Constant STOP_DEBIT. */
	String STOP_DEBIT = "/stopDebit";

	/** The Constant ELIMINAR_TRANSFERENCIA. */
	String ELIMINAR_TRANSFERENCIA = "/eliminarTransferencia";

	/** The Constant COMPROBANTE_MODIFICACION. */
	String GRABAR_ESTADISTICA_COMPROBANTE_MODIFICACION_TRANSFERENCIA = "/grabarEstadisticaComprobanteModificacionTransferencia";

	/** The Constant SOLICITAR_AGENDAR_TRANSFERENCIA. */
	String SOLICITAR_TRANSFERENCIA_AGENDADA = "/solicitarTransferenciaAgendada";

	/** The Constant SOLICITAR_CONCEPTOS_AGENDAR_TRANSFERENCIA. */
	String SOLICITAR_CONCEPTOS_AGENDAR_TRANSFERENCIA = "/solicitarConceptosAgendarTransferencia";

	/** The Constant SOLICITAR_AGENDAR_TRANSFERENCIA_DESDE_FEEDBACK. */
	String SOLICITAR_AGENDAR_TRANSFERENCIA_DESDE_FEEDBACK = "/solicitarAgendarTransferenciaDesdeFeedback";

	/** The Constant EJECUTAR_TRANSFERENCIA_AGENDADA. */
	String EJECUTAR_TRANSFERENCIA_AGENDADA = "/ejecutarTransferenciaAgendada";

	/** The Constant ejecutar AGENDAMIENTO DE TRANSFERENCIA. */
	String EJECUTAR_AGENDAMIENTO_TRANSFERENCIA = "/ejecutarAgendamientoTransferencia";

	/**
	 * The Constant GRABAR_ESTADISTICAS_COMPROBANTE_AGENDAMIENTO_TRANSFERENCIA.
	 */
	String GRABAR_ESTADISTICAS_COMPROBANTE_AGENDAMIENTO_TRANSFERENCIA = "/grabarEstadisticasComprobanteAgendamientoTransferencia";

	/** The Constant EJECUTAR_MODIFICACION de TRANSFERENCIA_AGENDADA. */
	String EJECUTAR_MODIFICACION_TRANSFERENCIA_AGENDADA = "/ejecutarModificacionTransferenciaAgendada";

	/** The grabar estadisticas acceso ayuda. */
	String GRABAR_ESTADISTICAS_ACCESO_AYUDA = "/grabarEstadisticasAyuda";

	/** The grabar estadisticas acceso ayuda. */
	String GRABAR_ESTADISTICAS_COMPROBANTE_TRANSFERENCIA_AGENDADA = "/grabarEstadisticasComprobanteTransferenciaAgendada";

	/** The Constant ACCEPT_APPLICATION_JSON. */
	String ACCEPT_APPLICATION_JSON = "Accept=application/json";

	/** The descargar pdf. */
	String DESCARGAR_COMPROBANTE_PDF = "/descargarComprobantePDF";
	
	/** The volver transferencia agendada. */
	String VOLVER_TRANSFERENCIA_AGENDADA = "/volverTransferenciaAgendada";

	/**
	 * Obtener agenda transferencias.
	 *
	 * @author manuel.vargas
	 * @return the respuesta
	 */
	@POST
	@Path(AGENDA_TRANSFERENCIAS)
	@CustomPreAuthorize(AccionController.IR_INICIO_TRASNFERENCIAS)
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AgendaView> obtenerAgendaTransferencias();

	/**
	 * Gets the detalle transferencia agendada.
	 *
	 * @author alejandro.vigano
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the detalle transferencia agendada
	 */
	@POST
	@Path(DETALLE_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaAgendadaDetalleView> getDetalleTransferenciaAgendada(
			TransferenciaAgendadaView transferenciaAgendadaView);

	/**
	 * Obtener configuracion stop debit.
	 *
	 * @author alejandro.vigano
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the respuesta
	 */
	@POST
	@Path(CONFIGURACION_STOP_DEBIT)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionStopDebitView> obtenerConfiguracionStopDebit(
			TransferenciaAgendadaView transferenciaAgendadaView);

	/**
	 * Stop debit.
	 *
	 * @author alejandro.vigano
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the respuesta
	 */
	@POST
	@Path(STOP_DEBIT)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackStopDebitView> stopDebit(TransferenciaAgendadaView transferenciaAgendadaView);

	/**
	 * Eliminar.
	 *
	 * @author alejandro.vigano
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the respuesta
	 */
	@POST
	@Path(ELIMINAR_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FeedbackEliminarView> eliminar(TransferenciaAgendadaView transferenciaAgendadaView);

	/**
	 * Obtener comprobante stop debit.
	 *
	 * @author alejandro.vigano
	 * @return the respuesta
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_COMPROBANTE_STOP_DEBIT)
	@Produces(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaComprobanteStopDebit();

	/**
	 * Obtener configuracion modificacion.
	 *
	 * @author alejandro.vigano
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the respuesta
	 */
	@POST
	@Path(CONFIGURACION_MODIFICACION)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaAgendadaDetalleView> solicitarModificacionTransferencia(
			TransferenciaAgendadaView transferenciaAgendadaView);

	/**
	 * Obtener comprobante modificacion.
	 *
	 * @author alejandro.vigano
	 * @return the respuesta
	 */
	@POST
	@Path(GRABAR_ESTADISTICA_COMPROBANTE_MODIFICACION_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaComprobanteModificacionTransferencia();

	/**
	 * Solicitar agendar una transferencia.
	 *
	 * @author emilio.watemberg
	 * @param transferenciaAgendadaDetalleView
	 *            the transferencia agendada detalle view
	 * @return the respuesta
	 * @since 21 Mar, 2017
	 */
	@POST
	@Path(SOLICITAR_TRANSFERENCIA_AGENDADA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaAgendadaDetalleView> solicitarTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);

	/**
	 * Solicitar la lista de conceptos transferencia agendada.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(SOLICITAR_CONCEPTOS_AGENDAR_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConceptosTransferenciaAgendadaView> solicitarConceptosTransferenciaAgendada();

	/**
	 * Solicitar agendar transferencia desde feedback.
	 *
	 * @param agendamientoTransferenciaView
	 *            the agendamiento transferencia view
	 * @return the respuesta
	 */
	@POST
	@Path(SOLICITAR_AGENDAR_TRANSFERENCIA_DESDE_FEEDBACK)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AgendamientoTransferenciaView> solicitarAgendamientoTransferencia(
			AgendamientoTransferenciaView agendamientoTransferenciaView);

	/**
	 * Ejecutar la transferenci previamente agendada.
	 * 
	 * @author emilio.watemberg
	 * @param transferenciaAgendadaView
	 *            the transferencia agendada view
	 * @return the respuesta
	 * @see #ejecutarAgendamientoTransferencia(TransferenciaAgendadaDetalleView)
	 * @since 21 Mar, 2017
	 */
	@POST
	@Path(EJECUTAR_TRANSFERENCIA_AGENDADA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaAgendadaDetalleView> ejecutarTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaView);

	/**
	 * Ejecutar el agendamiento de una transferencia realizada exitosamente.
	 * 
	 * @author B041299 manuel.vargas
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the respuesta
	 * @since 21 Jul, 2017
	 */
	@POST
	@Path(EJECUTAR_AGENDAMIENTO_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<AgendamientoTransferenciaView> ejecutarAgendamientoTransferencia(
			AgendamientoTransferenciaView transferenciaView);

	/**
	 * Grabar estadisticas comprobante agendamiento transferencia.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_COMPROBANTE_AGENDAMIENTO_TRANSFERENCIA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticasComprobanteAgendamientoTransferencia();

	/**
	 * Ejecutar modificacion transferencia agendada. see feedback ok: 538971,
	 * 79181; feddback nook 53973, 79183.
	 *
	 * @author B041299
	 * @param transferenciaAgendadaDetalleView
	 *            the transferencia agendada detalle view
	 * @return the respuesta
	 */
	@POST
	@Path(EJECUTAR_MODIFICACION_TRANSFERENCIA_AGENDADA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TransferenciaAgendadaDetalleView> ejecutarModificacionTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);

	/**
	 * Este metodo se encarga de grabar las estadisticas de acceso al componente
	 * de ayuda.
	 *
	 * @author emilio.watemberg.
	 * @return the respuesta
	 * @since Aug 7, 2017.
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_ACCESO_AYUDA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Respuesta<Void> grabarEstadisticasAccesoAyuda();

	/**
	 * Este metodo se encarga de grabar las estadisticas de acceso al componente
	 * de ayuda.
	 *
	 * @author emilio.watemberg.
	 * @since Aug 7, 2017.
	 */
	@POST
	@Path(GRABAR_ESTADISTICAS_COMPROBANTE_TRANSFERENCIA_AGENDADA)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticasComprobanteTransferenciaAgendada();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @param transferenciaAgendadaDetalleView
	 *            the transferencia agendada detalle view
	 * @return the respuesta
	 */
	@POST
	@Path(DESCARGAR_COMPROBANTE_PDF)
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobantePDF(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);
	
	/**
	 * Volver agenda transferencias.
	 */
	@POST
    @Path(VOLVER_TRANSFERENCIA_AGENDADA)
    @CustomPreAuthorize(AccionController.IR_INICIO_TRASNFERENCIAS)
    @Produces(value = { MediaType.APPLICATION_JSON })
	void volverAgendaTransferencias();
}
