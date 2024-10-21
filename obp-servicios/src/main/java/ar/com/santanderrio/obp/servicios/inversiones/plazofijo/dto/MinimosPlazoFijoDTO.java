/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.math.BigDecimal;

/**
 * The Class MinimosPlazoFijoDTO.
 *
 * @author juan.pablo.picate
 */
public class MinimosPlazoFijoDTO {

	/** The plazo minimo pesos. */
	private int plazoMinimoPesos = 0;

	/** The importe minimo pesos. */
	private BigDecimal importeMinimoPesos = null;

	/** The plazo minimo dolares. */
	private int plazoMinimoDolares = 0;

	/** The importe minimo dolares. */
	private BigDecimal importeMinimoDolares = null;

	/**
	 * Gets the plazo minimo pesos.
	 *
	 * @return the plazoMinimoPesos
	 */
	public int getPlazoMinimoPesos() {
		return plazoMinimoPesos;
	}

	/**
	 * Sets the plazo minimo pesos.
	 *
	 * @param plazoMinimoPesos
	 *            the plazoMinimoPesos to set
	 */
	public void setPlazoMinimoPesos(int plazoMinimoPesos) {
		this.plazoMinimoPesos = plazoMinimoPesos;
	}

	/**
	 * Gets the importe minimo pesos.
	 *
	 * @return the importeMinimoPesos
	 */
	public BigDecimal getImporteMinimoPesos() {
		return importeMinimoPesos;
	}

	/**
	 * Sets the importe minimo pesos.
	 *
	 * @param importeMinimoPesos
	 *            the importeMinimoPesos to set
	 */
	public void setImporteMinimoPesos(BigDecimal importeMinimoPesos) {
		this.importeMinimoPesos = importeMinimoPesos;
	}

	/**
	 * Gets the plazo minimo dolares.
	 *
	 * @return the plazoMinimoDolares
	 */
	public int getPlazoMinimoDolares() {
		return plazoMinimoDolares;
	}

	/**
	 * Sets the plazo minimo dolares.
	 *
	 * @param plazoMinimoDolares
	 *            the plazoMinimoDolares to set
	 */
	public void setPlazoMinimoDolares(int plazoMinimoDolares) {
		this.plazoMinimoDolares = plazoMinimoDolares;
	}

	/**
	 * Gets the importe minimo dolares.
	 *
	 * @return the importeMinimoDolares
	 */
	public BigDecimal getImporteMinimoDolares() {
		return importeMinimoDolares;
	}

	/**
	 * Sets the importe minimo dolares.
	 *
	 * @param importeMinimoDolares
	 *            the importeMinimoDolares to set
	 */
	public void setImporteMinimoDolares(BigDecimal importeMinimoDolares) {
		this.importeMinimoDolares = importeMinimoDolares;
	}

}