/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.util.List;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class ConceptosTransferenciaAgendadaView.
 */
public class ConceptosTransferenciaAgendadaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2791839399230361842L;

	/** The conceptos agenda transferencia. */
	public List<ConceptoView> conceptosAgendaTransferencia;
	
	/** The legal concepto. */
	private String legalConcepto;

	/**
	 * Gets the conceptos agenda transferencia.
	 *
	 * @return the conceptos agenda transferencia
	 */
	public List<ConceptoView> getConceptosAgendaTransferencia() {
		return conceptosAgendaTransferencia;
	}

	/**
	 * Sets the conceptos agenda transferencia.
	 *
	 * @param conceptosAgendaTransferencia
	 *            the new conceptos agenda transferencia
	 */
	public void setConceptosAgendaTransferencia(List<ConceptoView> conceptosAgendaTransferencia) {
		this.conceptosAgendaTransferencia = conceptosAgendaTransferencia;
	}

	/**
	 * Gets the legal concepto.
	 *
	 * @return the legalConcepto
	 */
	public String getLegalConcepto() {
		return legalConcepto;
	}

	/**
	 * Sets the legal concepto.
	 *
	 * @param legalConcepto
	 *            the legalConcepto to set
	 */
	public void setLegalConcepto(String legalConcepto) {
		this.legalConcepto = legalConcepto;
	}
	
	

}
