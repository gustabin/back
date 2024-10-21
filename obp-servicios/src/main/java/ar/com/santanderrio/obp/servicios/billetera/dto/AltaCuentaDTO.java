/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

/**
 * The Class AltaCuentaDTO.
 */
public class AltaCuentaDTO {

	/** The id cuenta. */
	private String idCuenta;

	/** The status. */
	protected String status;

	/**
	 * Gets the id cuenta.
	 *
	 * @return the idCuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the idCuenta to set
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
