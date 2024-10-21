/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.List;

/**
 * OLYMPUS OpenCredit.
 *
 * @author Silvina_Luque
 */
public class DetallePagosMinimosOpenCreditDTO {

	/** The linea credito total. */
	private String lineaCreditoTotal;

	/** The disponible para uso. */
	private String disponibleParaUso;

	/** The numero prestamo. */
	private String numeroPrestamo;

	/** The despcripcion. */
	private String despcripcion;

	/** The pagos. */
	private List<PagoOpenCreditDTO> pagos;

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<PagoOpenCreditDTO> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the new pagos
	 */
	public void setPagos(List<PagoOpenCreditDTO> pagos) {
		this.pagos = pagos;
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

	/**
	 * Gets the despcripcion.
	 *
	 * @return the despcripcion
	 */
	public String getDespcripcion() {
		return despcripcion;
	}

	/**
	 * Sets the despcripcion.
	 *
	 * @param despcripcion
	 *            the new despcripcion
	 */
	public void setDespcripcion(String despcripcion) {
		this.despcripcion = despcripcion;
	}

}
