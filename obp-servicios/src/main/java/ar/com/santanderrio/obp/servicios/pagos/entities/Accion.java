/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Enum Accion.
 */
public enum Accion {

	/** The alta. */
	ALTA("Alta", false),
	/** The baja. */
	BAJA("B", false);

	/** The nombre accion. */
	private String nombreAccion;

	/** The permite aplicar accion. */
	private boolean permiteAplicarAccion;

	/**
	 * Instantiates a new accion.
	 *
	 * @param nombreAccion
	 *            the nombre accion
	 * @param permiteAplicarAccion
	 *            the permite aplicar accion
	 */
	private Accion(String nombreAccion, boolean permiteAplicarAccion) {
		this.nombreAccion = nombreAccion;
		this.permiteAplicarAccion = permiteAplicarAccion;
	}

	/**
	 * Gets the nombre accion.
	 *
	 * @return the nombre accion
	 */
	public String getNombreAccion() {
		return nombreAccion;
	}

	/**
	 * Checks if is permite aplicar accion.
	 *
	 * @return true, if is permite aplicar accion
	 */
	public boolean isPermiteAplicarAccion() {
		return permiteAplicarAccion;
	}

}
