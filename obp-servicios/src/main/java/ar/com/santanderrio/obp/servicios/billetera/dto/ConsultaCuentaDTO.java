/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import java.util.List;

/**
 * The Class ConsultaCuentaDTO.
 */
public class ConsultaCuentaDTO {

	/** The cuentas. */
	protected List<CuentaBilleteraDTO> cuentas;

	/** The existe usuario. */
	protected String existeUsuario;

	/** The status. */
	protected String status;

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaBilleteraDTO> getCuentas() {
		return cuentas;
	}

	/**
	 * Gets the existe usuario.
	 *
	 * @return the existeUsuario
	 */
	public String getExisteUsuario() {
		return existeUsuario;
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
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<CuentaBilleteraDTO> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Sets the existe usuario.
	 *
	 * @param existeUsuario
	 *            the existeUsuario to set
	 */
	public void setExisteUsuario(String existeUsuario) {
		this.existeUsuario = existeUsuario;
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
