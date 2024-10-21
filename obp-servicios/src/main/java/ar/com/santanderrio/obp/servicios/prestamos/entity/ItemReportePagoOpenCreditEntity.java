/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

/**
 * ItemReportePagoOpenCreditEntity.
 *
 * @author Silvina_Luque
 */
public class ItemReportePagoOpenCreditEntity {

	/** numeroPagoMinimo. */
	private String numeroPagoMinimo;

	/** fechaVencimiento. */
	private String fechaVencimiento;

	/** lineaCreditoTotal. */
	private String pagoMinimo;

	/** numeroPagoMinimo. */
	private String lineaCreditoTotal;

	/**
	 * Gets the numero pago minimo.
	 *
	 * @return the numero pago minimo
	 */
	public String getNumeroPagoMinimo() {
		return numeroPagoMinimo;
	}

	/**
	 * Sets the numero pago minimo.
	 *
	 * @param numeroPagoMinimo
	 *            the new numero pago minimo
	 */
	public void setNumeroPagoMinimo(String numeroPagoMinimo) {
		this.numeroPagoMinimo = numeroPagoMinimo;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the linea credito total.
	 *
	 * @return the linea credito total
	 */
	public String getLineaCreditoTotal() {
		return lineaCreditoTotal;
	}

	/**
	 * Sets the linea credito total.
	 *
	 * @param lineaCreditoTotal
	 *            the new linea credito total
	 */
	public void setLineaCreditoTotal(String lineaCreditoTotal) {
		this.lineaCreditoTotal = lineaCreditoTotal;
	}

}
