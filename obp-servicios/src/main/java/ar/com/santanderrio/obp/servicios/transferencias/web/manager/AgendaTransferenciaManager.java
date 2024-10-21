/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendamientoTransferenciaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptosTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfiguracionStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * The Interface AgendaTransferenciaManager.
 */
public interface AgendaTransferenciaManager {

    /**
     * Obtener agenda transferencias.
     *
     * @author manuel.vargas
     * @return the respuesta
     */
    Respuesta<AgendaView> obtenerAgendaTransferencias();

    /**
     * Obtener agenda transferencia detalle.
     *
     * @param transferenciaAgendadaView
     *            the transferencia agendada view
     * @return the respuesta
     */
    Respuesta<TransferenciaAgendadaDetalleView> obtenerAgendaTransferenciaDetalle(
            TransferenciaAgendadaView transferenciaAgendadaView);

    /**
     * Solicitar stop debit.
     *
     * @param transferenciaAgendadaView
     *            the transferencia agendada view
     * @return the respuesta
     */
    Respuesta<ConfiguracionStopDebitView> solicitarStopDebit(TransferenciaAgendadaView transferenciaAgendadaView);

    /**
     * Ejecutar stop debit.
     *
     * @param transferenciaAgendadaView
     *            the transferencia agendada view
     * @return the respuesta
     */
    Respuesta<FeedbackStopDebitView> ejecutarStopDebit(TransferenciaAgendadaView transferenciaAgendadaView);

    /**
     * Grabar estadistica al ver un comprobante de un stop debit.
     */
    void grabarEstadisticaComprobanteStopDebit();

    /**
     * Eliminar transferencia.
     *
     * @param transferenciaAgendadaView
     *            the transferencia agendada view
     * @return the respuesta
     */
    Respuesta<FeedbackEliminarView> eliminarTransferencia(TransferenciaAgendadaView transferenciaAgendadaView);

    /**
     * Obtener configuracion modificacion.
     *
     * @param transferenciaAgendadaView
     *            the transferencia agendada view
     * @return the respuesta
     */
    Respuesta<TransferenciaAgendadaDetalleView> solicitarModificacionTransferencia(
            TransferenciaAgendadaView transferenciaAgendadaView);

    /**
     * Solicitar agendar una transferencia.
     *
     * @author emilio.watemberg
     * @param agendaTransferencia
     *            the agenda transferencia
     * @return the respuesta
     * @since 21 Mar, 2017
     */
    Respuesta<TransferenciaAgendadaDetalleView> solicitarTransferenciaAgendada(
            TransferenciaAgendadaDetalleView agendaTransferencia);

    /**
     * Solicitar agendar transferencia desde feedback.
     *
     * @param agendamientoTransferenciaView
     *            the agendamiento transferencia view
     * @return the respuesta
     */
    Respuesta<AgendamientoTransferenciaView> solicitarAgendamientoTransferencia(
            AgendamientoTransferenciaView agendamientoTransferenciaView);

    /**
     * Ejecutar la accion de agendar una transferencia.
     *
     * @author emilio.watemberg
     * @param transferenciaAgendadaDetalleView
     *            the transferencia agendada detalle view
     * @return the respuesta
     * @since 21 Mar, 2017
     */
    Respuesta<TransferenciaAgendadaDetalleView> ejecutarTransferenciaAgendada(
            TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);

    /**
     * Consultar conceptos transferencia agendada.
     *
     * @return the respuesta
     */
    Respuesta<ConceptosTransferenciaAgendadaView> consultarConceptosTransferenciaAgendada();

    /**
     * Ejecutar agendamiento de transferencia ya realizada con exito.
     *
     * @author manuel.vargas B041299
     * @param agendamientoTransferenciaView
     *            the agendamiento transferencia view
     * @return the respuesta
     */
    Respuesta<AgendamientoTransferenciaView> ejecutarAgendamientoTransferencia(
            AgendamientoTransferenciaView agendamientoTransferenciaView);

    /**
     * Ejecutar modificacion transferencia agendada.
     *
     * @param transferenciaAgendadaDetalleView
     *            the transferencia agendada detalle view
     * @return the respuesta
     */
    Respuesta<TransferenciaAgendadaDetalleView> ejecutarModificacionDeTransferenciaAgendada(
            TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);

    /**
     * Descargar comprobante PDF.
     *
     * @param transferenciaAgendadaDetalleView
     *            the transferencia agendada detalle view
     * @return the respuesta
     */
    Respuesta<ReporteView> descargarComprobantePDF(TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView);

    /**
	 * Graba estadisticas al obtener el comprobante de agendamiento
	 * transferencia.
	 *
	 * @return the respuesta
	 */
    Respuesta<Void> grabarEstadisticasComprobanteAgendamientoTransferencia();

    /**
	 * Volver agenda transferencias.
	 */
    void volverAgendaTransferencias();
    
    void validarContratoTransferencias(Cliente cliente, Respuesta<AgendaView> respuestaAgendaView);
    
}
