/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view;

/**
 * Detalle de frecuencia de cobro intereses por cada mes.
 *
 * @author B039920
 */
public class ItemDetalleIntereses {

	/** Fecha de liquidacion. */
	private String fechaLiquidacion;

	/** Capital. */
	private String capital;

	/** Intereses. */
	private String intereses;

	/** Impuestos. */
	private String impuestos;

	/** Total. */
	private String total;

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public String getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(String intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
