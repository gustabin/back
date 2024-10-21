/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.dto;

/**
 * The Class TarjetaAsociadaDTO.
 */
public class TarjetaAsociadaDTO {

	/** The tipo tarjeta. */
	private String tipoTarjeta;
	
	/** The numero formateado. */
	private String numeroFormateado;
	
	/** The titular. */
	private String titular;

	/**
	 * Gets the numero formateado.
	 *
	 * @return the numero formateado
	 */
	public String getNumeroFormateado() {
		return numeroFormateado;
	}

	/**
	 * Sets the numero formateado.
	 *
	 * @param numeroFormateado
	 *            the new numero formateado
	 */
	public void setNumeroFormateado(String numeroFormateado) {
		this.numeroFormateado = numeroFormateado;
	}

	/**
	 * Gets the titular.
	 *
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the titular.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the tipo tarjeta.
	 *
	 * @return the tipo tarjeta
	 */
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	/**
	 * Sets the tipo tarjeta.
	 *
	 * @param tipoTarjeta
	 *            the new tipo tarjeta
	 */
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
}
