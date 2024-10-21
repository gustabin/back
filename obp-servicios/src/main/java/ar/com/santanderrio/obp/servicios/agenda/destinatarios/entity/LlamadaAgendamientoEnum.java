/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * Distintos tipos de llamada de agendamiento.
 *
 */
public enum LlamadaAgendamientoEnum {

	/** The cpi fci c. */
	PRIMERA("PR", "Primera"),
	/** The opprog. */
	CONTINUAR("CN", "Continuar");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new campo enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	LlamadaAgendamientoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
