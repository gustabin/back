/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

/**
 * Enum que representa las pestañas del modulo de comprobantes.
 */
public enum ComprobantesEnum {

	/** The transacciones. */
	TRANSACCIONES("1", "Transacciones"),
	/** The inversiones. */
	INVERSIONES("2", "Inversiones"),
	/** The solicitudes. */
	SOLICITUDES("3", "Solicitudes"),
	/** The superclub. */
	SUPERCLUB("4", "SuperClub");

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new comprobantes enum.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	ComprobantesEnum(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
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
