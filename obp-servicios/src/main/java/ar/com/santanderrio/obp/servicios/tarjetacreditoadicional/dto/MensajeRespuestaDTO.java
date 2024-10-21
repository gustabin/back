/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto;

import java.util.List;

/**
 * The Class MensajeRespuestaDTO.
 */
public class MensajeRespuestaDTO {

	/** The tipos tarjetas. */
	List<String> tiposTarjetas;

	/** The codigo mensaje. */
	String codigoMensaje;

	/** The resultado. */
	String resultado;

	/**
	 * Gets the tipos tarjetas.
	 *
	 * @return the tipos tarjetas
	 */
	public List<String> getTiposTarjetas() {
		return tiposTarjetas;
	}

	/**
	 * Sets the tipos tarjetas.
	 *
	 * @param tiposTarjetas
	 *            the new tipos tarjetas
	 */
	public void setTiposTarjetas(List<String> tiposTarjetas) {
		this.tiposTarjetas = tiposTarjetas;
	}

	/**
	 * Gets the codigo mensaje.
	 *
	 * @return the codigo mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	/**
	 * Sets the codigo mensaje.
	 *
	 * @param codigoMensaje
	 *            the new codigo mensaje
	 */
	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
