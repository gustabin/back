/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.utils;

/**
 * The Class Tarjeta.
 */
public class Tarjeta {

	/** The tipo. */
	private String tipo;

	/** The numero. */
	private String numero;

	/** The ubicacion. */
	private int ubicacion;

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the ubicacion.
	 *
	 * @return the ubicacion
	 */
	public int getUbicacion() {
		return ubicacion;
	}

	/**
	 * Sets the ubicacion.
	 *
	 * @param ubicacion
	 *            the new ubicacion
	 */
	public void setUbicacion(int ubicacion) {
		this.ubicacion = ubicacion;
	}

}