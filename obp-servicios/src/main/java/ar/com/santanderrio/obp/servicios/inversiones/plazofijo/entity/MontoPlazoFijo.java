/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

/**
 * Clase que modela los intereses e impuestos de um plazo fijo.
 *
 * @author marcelo.ruiz
 */
public class MontoPlazoFijo {

	/** The descripcion. */
	private String descripcion;

	/** The monto. */
	private String monto;

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
}
