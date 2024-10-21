/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

import java.util.List;

import ar.com.santanderrio.base.web.view.View;

/**
 * Clase representativa de la grilla de agenda.
 *
 * @author B039543
 */
public class AgendaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cantidad recordatorios. */
	private String cantidadRecordatorios;

	/** The cantidad automaticas. */
	private String cantidadAutomaticas;

	/** The transferencias agendadas. */
	private List<TransferenciaAgendadaView> transferenciasAgendadas;

	/**
	 * Gets the transferencias agendadas.
	 *
	 * @return the transferenciasAgendadas
	 */
	public List<TransferenciaAgendadaView> getTransferenciasAgendadas() {
		return transferenciasAgendadas;
	}

	/**
	 * Sets the transferencias agendadas.
	 *
	 * @param transferenciasAgendadas
	 *            the transferenciasAgendadas to set
	 */
	public void setTransferenciasAgendadas(List<TransferenciaAgendadaView> transferenciasAgendadas) {
		this.transferenciasAgendadas = transferenciasAgendadas;
	}

	/**
	 * Gets the cantidad recordatorios.
	 *
	 * @return the cantidadRecordatorios
	 */
	public String getCantidadRecordatorios() {
		return cantidadRecordatorios;
	}

	/**
	 * Sets the cantidad recordatorios.
	 *
	 * @param cantidadRecordatorios
	 *            the cantidadRecordatorios to set
	 */
	public void setCantidadRecordatorios(String cantidadRecordatorios) {
		this.cantidadRecordatorios = cantidadRecordatorios;
	}

	/**
	 * Gets the cantidad automaticas.
	 *
	 * @return the cantidadAutomaticas
	 */
	public String getCantidadAutomaticas() {
		return cantidadAutomaticas;
	}

	/**
	 * Sets the cantidad automaticas.
	 *
	 * @param cantidadAutomaticas
	 *            the cantidadAutomaticas to set
	 */
	public void setCantidadAutomaticas(String cantidadAutomaticas) {
		this.cantidadAutomaticas = cantidadAutomaticas;
	}

}
