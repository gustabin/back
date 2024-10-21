/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.web.view;

/**
 * The Class CajaPerfilSucursalView.
 */
public class CajaPerfilSucursalView extends CajaPerfil {

	/** The is sucursal. */
	private Boolean isSucursal;

	/** The has error. */
	private Boolean hasError;

	/** The direccion. */
	private String direccion;

	/**
	 * Gets the checks if is sucursal.
	 *
	 * @return the checks if is sucursal
	 */
	public Boolean getIsSucursal() {
		return isSucursal;
	}

	/**
	 * Sets the checks if is sucursal.
	 *
	 * @param isSucursal
	 *            the new checks if is sucursal
	 */
	public void setIsSucursal(Boolean isSucursal) {
		this.isSucursal = isSucursal;
	}

	/**
	 * Gets the checks for error.
	 *
	 * @return the checks for error
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the new checks for error
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion
	 *            the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Instantiates a new caja perfil sucursal view.
	 */
	public CajaPerfilSucursalView() {
		isSucursal = true;
	}

}
