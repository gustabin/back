/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * ReportePagosMinimosOpenCreditInView.
 *
 * @author Silvina_Luque
 */
public class ReportePagosMinimosOpenCreditInView {

	/** detallePagosMinimos. */
	private DetallePagosMinimosOpenCreditView detallePagosMinimos;

	/** lineaCreditoTotal. */
	private String lineaCreditoTotal;

	/** disponibleParaUso. */
	private String disponibleParaUso;

	/** numeroPrestamo. */
	private String numeroPrestamo;

	/**
	 * Gets the detalle pagos minimos.
	 *
	 * @return the detalle pagos minimos
	 */
	public DetallePagosMinimosOpenCreditView getDetallePagosMinimos() {
		return detallePagosMinimos;
	}

	/**
	 * Sets the detalle pagos minimos.
	 *
	 * @param detallePagosMinimos
	 *            the new detalle pagos minimos
	 */
	public void setDetallePagosMinimos(DetallePagosMinimosOpenCreditView detallePagosMinimos) {
		this.detallePagosMinimos = detallePagosMinimos;
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

	/**
	 * Gets the disponible para uso.
	 *
	 * @return the disponible para uso
	 */
	public String getDisponibleParaUso() {
		return disponibleParaUso;
	}

	/**
	 * Sets the disponible para uso.
	 *
	 * @param disponibleParaUso
	 *            the new disponible para uso
	 */
	public void setDisponibleParaUso(String disponibleParaUso) {
		this.disponibleParaUso = disponibleParaUso;
	}

	/**
	 * Gets the numero prestamo.
	 *
	 * @return the numero prestamo
	 */
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	/**
	 * Sets the numero prestamo.
	 *
	 * @param numeroPrestamo
	 *            the new numero prestamo
	 */
	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

}
