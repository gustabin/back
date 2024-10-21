/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.util;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.ModificacionTransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConfirmacionModificacionView;

/**
 * Visitor para la configuracion de la modificacion de transferencias agendadas
 * No thread-safe.
 *
 * @author B039543
 */
public class ConfirmacionModificacionViewVisitor
		extends AbstractTransferenciaAgendadaDetalleViewVisitor<ConfirmacionModificacionView> {

	/** The modificacion transferencia agendada. */
	private ModificacionTransferenciaAgendadaDTO modificacionTransferenciaAgendada;

	/**
	 * Instantiates a new confirmacion modificacion view visitor.
	 *
	 * @param modificacionTransferenciaAgendada
	 *            the modificacion transferencia agendada
	 */
	public ConfirmacionModificacionViewVisitor(ModificacionTransferenciaAgendadaDTO modificacionTransferenciaAgendada) {
		super(false);
		this.modificacionTransferenciaAgendada = modificacionTransferenciaAgendada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.util.
	 * AbstractTransferenciaAgendadaViewVisitor#crearVista(ar.com.santanderrio.
	 * obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO)
	 */
	@Override
	protected ConfirmacionModificacionView crearVista(TransferenciaAgendadaDTO transferenciaAgendada) {
		return new ConfirmacionModificacionView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#visit(ar.com.santanderrio
	 * .obp.transferencias.entities.agenda.TransferenciaAgendadaRecordatorioDTO)
	 */
	@Override
	public void visit(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		super.visit(transferenciaAgendadaDTO);
		// SOBREESCRIBIR LA FRECUENCIA CON LA SELECCIONADA
		this.getView().setFrecuencia(modificacionTransferenciaAgendada.getFrecuencia().getDescripcion());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#visit(ar.com.santanderrio
	 * .obp.transferencias.entities.agenda.TransferenciaAgendadaRecurrenteDTO)
	 */
	// @Override
	// public void visit(TransferenciaAgendadaRecurrenteDTO
	// transferenciaAgendada) {
	// super.visit(transferenciaAgendada);
	// // SOBREESCRIBIR LA FRECUENCIA CON LA SELECCIONADA
	// this.getView().setFrecuencia(modificacionTransferenciaAgendada.getFrecuencia().getDescripcion());
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.web.util.
	 * AbstractTransferenciaAgendadaDetalleViewVisitor#popularVista(ar.com.
	 * santanderrio.obp.transferencias.entities.agenda.TransferenciaAgendadaDTO,
	 * ar.com.santanderrio.obp.transferencias.web.view.
	 * TransferenciaAgendadaDetalleView)
	 */
	@Override
	protected void popularVista(TransferenciaAgendadaDTO transferenciaAgendada,
			ConfirmacionModificacionView viewResponse) {
		super.popularVista(transferenciaAgendada, viewResponse);

		viewResponse.setEmailActivo(false);

		viewResponse.setEmailActivo(modificacionTransferenciaAgendada.getEmailActivo());
		viewResponse.setEmail(modificacionTransferenciaAgendada.getEmail());
		viewResponse.setMensajeEmail(modificacionTransferenciaAgendada.getMensajeEmail());

		viewResponse.setFecha(ISBANStringUtils.formatearFecha(modificacionTransferenciaAgendada.getFechaEjecucion()));
		viewResponse
				.setImporte(ISBANStringUtils.formatearSaldoConSigno(modificacionTransferenciaAgendada.getImporte()));

		viewResponse.setConcepto(new ConceptoView(modificacionTransferenciaAgendada.getConcepto().getDescripcion(),modificacionTransferenciaAgendada.getConcepto().getOrdinal(),modificacionTransferenciaAgendada.getConcepto().getCodigo(),modificacionTransferenciaAgendada.getConcepto().getDescripcionAbreviada(),modificacionTransferenciaAgendada.getConcepto().getHabilitaLegal()));

	}

}