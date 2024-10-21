/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class PrestamosDTO.
 */
public class PrestamosDTO {

	/** The prestamos. */
	private List<Prestamo> prestamos;

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos
	 *            the new prestamos
	 */
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

}
