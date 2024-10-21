/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import ar.com.santanderrio.obp.base.entities.DTO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.comprobante.entities.AbstractComprobante;

/**
 * Comprobante de operacion.
 *
 * @author B039543
 * @param <E>
 *            the element type
 */
public class AbstractAccionTransferenciaAgendada<E extends AbstractComprobante<?>> extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The transferencia agendada DTO. */
	private TransferenciaAgendadaDTO transferenciaAgendadaDTO;

	/** The comprobante. */
	private E comprobante;

	/** The contador intentos. */
	private ContadorIntentos contadorIntentos;

	/** The id proceso. */
	private String idProceso;

	/**
	 * Gets the contador intentos.
	 *
	 * @return the contadorIntentos
	 */
	public ContadorIntentos getContadorIntentos() {
		return contadorIntentos;
	}

	/**
	 * Sets the contador intentos.
	 *
	 * @param contadorIntentos
	 *            the contadorIntentos to set
	 */
	public void setContadorIntentos(ContadorIntentos contadorIntentos) {
		this.contadorIntentos = contadorIntentos;
	}

	/**
	 * Gets the transferencia agendada DTO.
	 *
	 * @return the transferenciaAgendadaDTO
	 */
	public TransferenciaAgendadaDTO getTransferenciaAgendadaDTO() {
		return transferenciaAgendadaDTO;
	}

	/**
	 * Sets the transferencia agendada DTO.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferenciaAgendadaDTO to set
	 */
	public void setTransferenciaAgendadaDTO(TransferenciaAgendadaDTO transferenciaAgendadaDTO) {
		this.transferenciaAgendadaDTO = transferenciaAgendadaDTO;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public E getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(E comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the id proceso.
	 *
	 * @return the id proceso
	 */
	public String getIdProceso() {
		return idProceso;
	}

	/**
	 * Sets the id proceso.
	 *
	 * @param idProceso
	 *            the new id proceso
	 */
	public void setIdProceso(String idProceso) {
		this.idProceso = idProceso;
	}

}
