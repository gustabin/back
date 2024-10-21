/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

/**
 * The Class RtaLoadTitular.
 */
public class RtaLoadTitular {

	
	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;
	
	

	/**
	 * Instantiates a new rta load titular.
	 *
	 * @param apellido
	 *            the apellido
	 * @param nombre
	 *            the nombre
	 */
	public RtaLoadTitular(String apellido, String nombre) {
		this.apellido = apellido;
		this.nombre = nombre;
	}

	/**
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido
	 *            the new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
