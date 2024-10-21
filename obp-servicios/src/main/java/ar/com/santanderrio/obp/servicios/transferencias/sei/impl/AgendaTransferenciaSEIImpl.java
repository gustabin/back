/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.sei.AgendaTransferenciaSEI;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AgendaTransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendamientoTransferenciaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptosTransferenciaAgendadaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfiguracionStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackEliminarView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.FeedbackStopDebitView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * The Class AgendaTransferenciaSEIImpl.
 */
@Component("agendaTransferenciaSEI")
public class AgendaTransferenciaSEIImpl implements AgendaTransferenciaSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTransferenciaSEIImpl.class);

	/** The agenda transferencia manager. */
	@Autowired
	private AgendaTransferenciaManager agendaTransferenciaManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#obtenerAgendaTransferencias()
	 */
	@Override
	public Respuesta<AgendaView> obtenerAgendaTransferencias() {
        return agendaTransferenciaManager.obtenerAgendaTransferencias();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#getDetalleTransferenciaAgendada(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> getDetalleTransferenciaAgendada(
			TransferenciaAgendadaView transferenciaAgendadaView) {
		return agendaTransferenciaManager.obtenerAgendaTransferenciaDetalle(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#obtenerConfiguracionStopDebit(ar.com.santanderrio.
	 * obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<ConfiguracionStopDebitView> obtenerConfiguracionStopDebit(
			TransferenciaAgendadaView transferenciaAgendadaView) {
		return agendaTransferenciaManager.solicitarStopDebit(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#stopDebit(ar.com.santanderrio.obp.servicios.
	 * cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<FeedbackStopDebitView> stopDebit(TransferenciaAgendadaView transferenciaAgendadaView) {
		return agendaTransferenciaManager.ejecutarStopDebit(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#eliminar(ar.com.santanderrio.obp.servicios.cuentas
	 * .web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<FeedbackEliminarView> eliminar(TransferenciaAgendadaView transferenciaAgendadaView) {
		return agendaTransferenciaManager.eliminarTransferencia(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#obtenerComprobanteStopDebit()
	 */
	@Override
	public void grabarEstadisticaComprobanteStopDebit() {
		agendaTransferenciaManager.grabarEstadisticaComprobanteStopDebit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#obtenerConfiguracionModificacion(ar.com.
	 * santanderrio.obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> solicitarModificacionTransferencia(
			TransferenciaAgendadaView transferenciaAgendadaView) {
		return agendaTransferenciaManager.solicitarModificacionTransferencia(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#obtenerComprobanteModificacion()
	 */
	@Override
	public void grabarEstadisticaComprobanteModificacionTransferencia() {
		LOGGER.info("Grabando estadisicas: ", EstadisticasConstants.ESTADISTICA_COMPROBANTE_MODIFICACION_TRANSFERENCIA);
		estadisticaManager.add(EstadisticasConstants.ESTADISTICA_COMPROBANTE_MODIFICACION_TRANSFERENCIA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#solicitarAgendarTransferencia(ar.com.santanderrio.
	 * obp.servicios.cuentas.web.view.AgendaTransferencia)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> solicitarTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		return agendaTransferenciaManager.solicitarTransferenciaAgendada(transferenciaAgendadaDetalleView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#ejecutarAgendarTransferencia(ar.com.santanderrio.
	 * obp.servicios.transferencias.web.view.TransferenciaAgendadaView)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> ejecutarTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaView) {
		return agendaTransferenciaManager.ejecutarTransferenciaAgendada(transferenciaAgendadaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#solicitarConceptosTransferenciaAgendada()
	 */
	@Override
	public Respuesta<ConceptosTransferenciaAgendadaView> solicitarConceptosTransferenciaAgendada() {
		return agendaTransferenciaManager.consultarConceptosTransferenciaAgendada();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#
	 * grabarEstadisticasComprobanteAgendamientoTransferencia(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticasComprobanteAgendamientoTransferencia() {
		return agendaTransferenciaManager.grabarEstadisticasComprobanteAgendamientoTransferencia();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#ejecutarAgendamientoTransferencia(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaAgendadaDetalleView)
	 */
	@Override
	public Respuesta<AgendamientoTransferenciaView> ejecutarAgendamientoTransferencia(
			AgendamientoTransferenciaView agendamientoTransferenciaView) {
		return agendaTransferenciaManager.ejecutarAgendamientoTransferencia(agendamientoTransferenciaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * solicitarAgendarTransferenciaDesdeFeedback(ar.com.santanderrio.obp.
	 * servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<AgendamientoTransferenciaView> solicitarAgendamientoTransferencia(
			AgendamientoTransferenciaView agendamientoTransferenciaView) {
		return agendaTransferenciaManager.solicitarAgendamientoTransferencia(agendamientoTransferenciaView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#ejecutarModificacionTransferenciaAgendada(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.
	 * TransferenciaAgendadaView)
	 */
	@Override
	public Respuesta<TransferenciaAgendadaDetalleView> ejecutarModificacionTransferenciaAgendada(
			TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		return agendaTransferenciaManager.ejecutarModificacionDeTransferenciaAgendada(transferenciaAgendadaDetalleView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#grabarEstadisticasAccesoAyuda()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticasAccesoAyuda() {
		Respuesta<Void> respuesta = new Respuesta<Void>();
		estadisticaManager.add(EstadisticasConstants.CODIGO_AGENDAMIENTO_TRANSFERENCIA_AYUDA_MAS_INFORMACION,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#grabarEstadisticasComprobanteTransferenciaAgendada
	 * ()
	 */
	@Override
	public void grabarEstadisticasComprobanteTransferenciaAgendada() {
		LOGGER.info("Grabando estadisicas: ",
				EstadisticasConstants.ESTADISTICAS_COMPROBANTE_TRANSFERENCIA_AGENDADA_RECORDATORIO);
		estadisticaManager.add(EstadisticasConstants.ESTADISTICAS_COMPROBANTE_TRANSFERENCIA_AGENDADA_RECORDATORIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.sei.
	 * AgendaTransferenciaSEI#descargarComprobantePDF()
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobantePDF(
			TransferenciaAgendadaDetalleView transferenciaAgendadaDetalleView) {
		return agendaTransferenciaManager.descargarComprobantePDF(transferenciaAgendadaDetalleView);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.transferencias.sei.AgendaTransferenciaSEI#volverAgendaTransferencias()
     */
    @Override
    public void volverAgendaTransferencias() {
        agendaTransferenciaManager.volverAgendaTransferencias();
    }
}
