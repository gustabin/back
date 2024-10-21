/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.math.BigDecimal;

/**
 * The Class PrestamoPermitidoDTO.
 */
public class PrestamoPermitidoDTO {

	/** The monto prestamo. */
	private BigDecimal montoPrestamo;

	/**
	 * Gets the monto prestamo.
	 *
	 * @return the monto prestamo
	 */
	public BigDecimal getMontoPrestamo() {
		return montoPrestamo;
	}

	/**
	 * Sets the monto prestamo.
	 *
	 * @param montoPrestamo
	 *            the new monto prestamo
	 */
	public void setMontoPrestamo(BigDecimal montoPrestamo) {
		this.montoPrestamo = montoPrestamo;
	}

}
