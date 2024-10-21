package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import org.beanio.annotation.Field;

/**
 * The Class MensajeErrorEntity.
 */
public class MensajeErrorEntity {

	/** The descripcionError. */
	@Field
	private String descripcionError;

	/**
	 * Gets the descripcion error.
	 *
	 * @return the descripcion error
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * Sets the descripcion error.
	 *
	 * @param descripcionError the new descripcion error
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

}
