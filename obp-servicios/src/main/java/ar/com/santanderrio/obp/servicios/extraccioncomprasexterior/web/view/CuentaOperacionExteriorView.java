/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.web.view;

/**
 * CuentaOperacionExteriorView.
 *
 * @author Silvina_Luque
 */
public class CuentaOperacionExteriorView {

	/** The id. */
	private String id;

	/** The cuenta. */
	private String cuenta;

	/** The cuenta preferida. */
	private boolean cuentaPreferida;

	/** The descripcion. */
	private String descripcion;

	/** The alias. */
	private String alias;

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Checks if is cuenta preferida.
	 *
	 * @return true, if is cuenta preferida
	 */
	public boolean isCuentaPreferida() {
		return cuentaPreferida;
	}

	/**
	 * Sets the cuenta preferida.
	 *
	 * @param cuentaPreferida
	 *            the new cuenta preferida
	 */
	public void setCuentaPreferida(boolean cuentaPreferida) {
		this.cuentaPreferida = cuentaPreferida;
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
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
