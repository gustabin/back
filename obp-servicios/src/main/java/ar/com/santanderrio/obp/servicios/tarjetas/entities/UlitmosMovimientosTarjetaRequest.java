/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class UlitmosMovimientosTarjetaRequest.
 */
public class UlitmosMovimientosTarjetaRequest {

	/** The tarea unificada. */
	private String tareaUnificada;

	/** The token. */
	private String token;

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Setter para token.
	 *
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the tarea unificada.
	 *
	 * @return the tareaUnificada
	 */
	public String getTareaUnificada() {
		return tareaUnificada;
	}

	/**
	 * Setter para tarea unificada.
	 *
	 * @param tareaUnificada
	 *            the tareaUnificada to set
	 */
	public void setTareaUnificada(String tareaUnificada) {
		this.tareaUnificada = tareaUnificada;
	}

}
