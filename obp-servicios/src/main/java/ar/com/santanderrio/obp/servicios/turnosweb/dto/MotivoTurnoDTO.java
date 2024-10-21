/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dto;

/**
 * The Class MotivoDTO.
 */
public class MotivoTurnoDTO {

	/** The descripcion. */
	private String descripcion;

	/** The id. */
	private String id;

	/**
	 * Instantiates a new motivo DTO.
	 *
	 * @param descripcion the descripcion
	 * @param id the id
	 */
	public MotivoTurnoDTO(String descripcion, String id) {
		super();
		this.descripcion = descripcion;
		this.id = id;
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
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
