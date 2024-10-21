/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

/**
 * CabeceraReportePagosOpenCreditEntity.
 *
 * @author Silvina_Luque
 */
public class CabeceraReportePagosOpenCreditEntity {

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The linea credito total. */
	private String lineaCreditoTotal;

	/** The disponible para uso. */
	private String disponibleParaUso;

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

}
