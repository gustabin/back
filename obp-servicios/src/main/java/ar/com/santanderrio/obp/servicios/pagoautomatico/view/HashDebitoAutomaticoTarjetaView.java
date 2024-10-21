/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.view;

import java.io.Serializable;

/**
 * The Class HashDebitoAutomaticoTarjetaView.
 */
public class HashDebitoAutomaticoTarjetaView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero tarjeta enmascarado. */
	private String numeroTarjetaEnmascarado;

	/** The nro identificacion. */
	private String nroIdentificacion;

	/** The nombre fantasia. */
	private String nombreFantasia;

	/** The cuit. */
	private String cuit;

	/**
	 * Gets the numero tarjeta enmascarado.
	 *
	 * @return the numero tarjeta enmascarado
	 */
	public String getNumeroTarjetaEnmascarado() {
		return numeroTarjetaEnmascarado;
	}

	/**
	 * Sets the numero tarjeta enmascarado.
	 *
	 * @param numeroTarjetaEnmascarado
	 *            the new numero tarjeta enmascarado
	 */
	public void setNumeroTarjetaEnmascarado(String numeroTarjetaEnmascarado) {
		this.numeroTarjetaEnmascarado = numeroTarjetaEnmascarado;
	}

	/**
	 * Gets the nro identificacion.
	 *
	 * @return the nro identificacion
	 */
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	/**
	 * Sets the nro identificacion.
	 *
	 * @param nroIdentificacion
	 *            the new nro identificacion
	 */
	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	/**
	 * Gets the nombre fantasia.
	 *
	 * @return the nombre fantasia
	 */
	public String getNombreFantasia() {
		return nombreFantasia;
	}

	/**
	 * Sets the nombre fantasia.
	 *
	 * @param nombreFantasia
	 *            the new nombre fantasia
	 */
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit
	 *            the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
