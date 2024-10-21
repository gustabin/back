/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto;

import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentPorSubclasifEntity;

/**
 * The Class DetalleSubclasificionDTO.
 */
public class DetalleSubclasificionDTO {

	/** The resultado. */
	private DetalleRentPorSubclasifEntity resultado;

	
	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public DetalleRentPorSubclasifEntity getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(DetalleRentPorSubclasifEntity resultado) {
		this.resultado = resultado;
	}
	
}
