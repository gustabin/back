/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * Tipos de consulta de Agendamiento.
 *
 */
public enum TipoConsultaAgendamientoEnum {

	/** The cpi fci c. */
	PUNTUAL("P", "Puntual"),
	/** The opprog. */
	LISTADO("L", "Listado");

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
	TipoConsultaAgendamientoEnum(String campo, String descripcion) {
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
