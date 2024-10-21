/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.io.Serializable;

/**
 * The Class DatosCliente.
 */
public class DatosCliente implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nombre. */
	private String nombre;

	/** The nup. */
	private String nup;

	/** The String tipoCUILCUIT. */
	private String tipoCUILCUIT;

	/** The numeroCUILCUIT. */
	private String numeroCUILCUIT;

	/** The codigoError. */
	private Integer codigoError;
	
	/** The mensajeError. */
	private String mensajeError;	

	/**
	 * Gets the tipo CUILCUIT.
	 *
	 * @return the tipo CUILCUIT
	 */
	public String getTipoCUILCUIT() {
		return tipoCUILCUIT;
	}

	/**
	 * Sets the tipo CUILCUIT.
	 *
	 * @param tipoCUILCUIT
	 *            the new tipo CUILCUIT
	 */
	public void setTipoCUILCUIT(String tipoCUILCUIT) {
		this.tipoCUILCUIT = tipoCUILCUIT;
	}

	/**
	 * Gets the numero CUILCUIT.
	 *
	 * @return the numero CUILCUIT
	 */
	public String getNumeroCUILCUIT() {
		return numeroCUILCUIT;
	}

	/**
	 * Sets the numero CUILCUIT.
	 *
	 * @param numeroCUILCUIT
	 *            the new numero CUILCUIT
	 */
	public void setNumeroCUILCUIT(String numeroCUILCUIT) {
		this.numeroCUILCUIT = numeroCUILCUIT;
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

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigoError
	 */
	public Integer getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the codigoError to set
	 */
	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}
