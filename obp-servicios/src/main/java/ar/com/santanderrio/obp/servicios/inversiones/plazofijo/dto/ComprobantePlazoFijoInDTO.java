/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

/**
 * The Class ComprobantePlazoFijoInDTO.
 */
public class ComprobantePlazoFijoInDTO {

	/** The codigo plazo. */
	@NotNull
	@Pattern(regexp = "[0-9]*")
	private String codigoPlazo;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The minimo dias precancelar. */
	private String minimoDiasPrecancelar;

	/** The porcentaje penalizacion. */
	private String porcentajePenalizacion;

	/** The plazo. */
	private String plazo;

	/**
	 * Gets the codigo plazo.
	 *
	 * @return the codigo plazo
	 */
	public String getCodigoPlazo() {
		return codigoPlazo;
	}

	/**
	 * Sets the codigo plazo.
	 *
	 * @param codigoPlazo
	 *            the new codigo plazo
	 */
	public void setCodigoPlazo(String codigoPlazo) {
		this.codigoPlazo = codigoPlazo;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the minimo dias precancelar.
	 *
	 * @return the minimo dias precancelar
	 */
	public String getMinimoDiasPrecancelar() {
		return minimoDiasPrecancelar;
	}

	/**
	 * Sets the minimo dias precancelar.
	 *
	 * @param minimoDiasPrecancelar
	 *            the new minimo dias precancelar
	 */
	public void setMinimoDiasPrecancelar(String minimoDiasPrecancelar) {
		this.minimoDiasPrecancelar = minimoDiasPrecancelar;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentaje penalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the new porcentaje penalizacion
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

}
