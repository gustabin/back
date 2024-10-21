/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

/**
 * The Class AltaModificacionInDTO.
 *
 * @author IT Resources
 */
public class AltaModificacionOutDTO {

	/** The id turno. */
	private Long idTurno;
	
	/** The mensaje feedback. */
	private String mensajeFeedback;

	/**
	 * Gets the id turno.
	 *
	 * @return the idTurno
	 */
	public Long getIdTurno() {
		return idTurno;
	}

	/**
	 * Sets the id turno.
	 *
	 * @param idTurno
	 *            the idTurno to set
	 */
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the mensajeFeedback to set
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}
	
	
	
}
