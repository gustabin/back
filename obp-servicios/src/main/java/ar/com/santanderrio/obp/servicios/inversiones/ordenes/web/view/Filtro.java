/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view;

/**
 * La clase Filtro.
 *
 * @author luis.ventocilla
 * @author emilio.watemberg
 * @since Wed 8, 2017
 */
public abstract class Filtro {

	/** The nombre. */
	String id;

	/** The valor. */
	String descripcion;

	/**
	 * Instantiates a new tipo.
	 *
	 * @param id
	 *            the nombre
	 * @param descripcion
	 *            the valor
	 */
	public Filtro(String id, String descripcion) {
		super();
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
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
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
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
