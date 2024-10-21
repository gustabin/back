/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoTransferenciaAgendada.
 */
public enum TipoTransferenciaAgendada {

	/** The recordatorio. */
	RECORDATORIO("RECORDATORIO"),

	/** The recurrente. */
	RECURRENTE("RECURRENTE"),

	/** The programada. */
	PROGRAMADA("PROGRAMADA");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo transferencia agendada.
	 *
	 * @param tipoTransferenciaAgendada
	 *            the tipo transferencia agendada
	 */
	TipoTransferenciaAgendada(String tipoTransferenciaAgendada) {
		this.descripcion = tipoTransferenciaAgendada;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
