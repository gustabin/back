/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.List;

/**
 * PrestamosOpenCredit Olympus.
 *
 * @author Silvina_Luque
 */
public class PrestamosOpenCreditView {

	/** Lista de prestamos openCredit. */
	private List<PrestamoOpenCreditView> prestamos;

	/** Detalle de legales. */
	private String legales;

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public List<PrestamoOpenCreditView> getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos
	 *            the new prestamos
	 */
	public void setPrestamos(List<PrestamoOpenCreditView> prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

}
