/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import ar.com.santanderrio.obp.base.entities.DTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;

/**
 * Datos de origen necesarios para futuras llamadas a servicios.
 *
 * @author B039543
 */
public class DatosOrigenTransferenciaAgendadaDTO extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The transferencia agendada. */
	private TransferenciaAgendada transferenciaAgendada;

	/**
	 * Gets the transferencia agendada.
	 *
	 * @return the transferencia agendada
	 */
	public TransferenciaAgendada getTransferenciaAgendada() {
		return transferenciaAgendada;
	}

	/**
	 * Sets the transferencia agendada.
	 *
	 * @param transferenciaAgendada
	 *            the new transferencia agendada
	 */
	public void setTransferenciaAgendada(TransferenciaAgendada transferenciaAgendada) {
		this.transferenciaAgendada = transferenciaAgendada;
	}

}
