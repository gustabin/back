/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

import javax.validation.constraints.NotNull;

/**
 * Objeto request para obtener el detalle de la frecuencia de cobro de
 * intereses.
 *
 * @author B039920
 */
public class DetalleInteresesViewRequest {

	/** Cuenta Plazo. */
	@NotNull
	private String cuentaPlazo;

	/** Secuencia. */
	@NotNull
	private String secuencia;

	/**
	 * Gets the cuenta plazo.
	 *
	 * @return the cuenta plazo
	 */
	public String getCuentaPlazo() {
		return cuentaPlazo;
	}

	/**
	 * Sets the cuenta plazo.
	 *
	 * @param cuentaPlazo
	 *            the new cuenta plazo
	 */
	public void setCuentaPlazo(String cuentaPlazo) {
		this.cuentaPlazo = cuentaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}
}
