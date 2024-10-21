/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.entitites;

import ar.com.santanderrio.obp.base.entities.DTO;

/**
 * Representacion en sesion de datos relacionados.
 *
 * @author B039543
 */
public class HomeDTO extends DTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cantidad ingresos. */
	private long cantidadIngresos;

	/**
	 * Gets the cantidad ingresos.
	 *
	 * @return the cantidad ingresos
	 */
	public long getCantidadIngresos() {
		return cantidadIngresos;
	}

	/**
	 * Sets the cantidad ingresos.
	 *
	 * @param cantidadIngresos
	 *            the new cantidad ingresos
	 */
	public void setCantidadIngresos(long cantidadIngresos) {
		this.cantidadIngresos = cantidadIngresos;
	}

}
