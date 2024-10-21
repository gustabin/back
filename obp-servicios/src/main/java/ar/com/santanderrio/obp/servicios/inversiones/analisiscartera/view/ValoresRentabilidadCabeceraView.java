/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.math.BigDecimal;

/**
 * The Class ValoresRentabilidadCabeceraView.
 */
public class ValoresRentabilidadCabeceraView {

	/** The total pesos. */
	private String totalPesos;
	
	/** The big total pesos. */
	private BigDecimal bigTotalPesos;
	
	/** The total dolares. */
	private String totalDolares;
	
	/** The big total dolares. */
	private BigDecimal bigTotalDolares;

	
	/**
	 * Gets the total pesos.
	 *
	 * @return the total pesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the new total pesos
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the total dolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the new total dolares
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * Gets the big total pesos.
	 *
	 * @return the big total pesos
	 */
	public BigDecimal getBigTotalPesos() {
		return bigTotalPesos;
	}

	/**
	 * Sets the big total pesos.
	 *
	 * @param bigTotalPesos
	 *            the new big total pesos
	 */
	public void setBigTotalPesos(BigDecimal bigTotalPesos) {
		this.bigTotalPesos = bigTotalPesos;
	}

	/**
	 * Gets the big total dolares.
	 *
	 * @return the big total dolares
	 */
	public BigDecimal getBigTotalDolares() {
		return bigTotalDolares;
	}

	/**
	 * Sets the big total dolares.
	 *
	 * @param bigTotalDolares
	 *            the new big total dolares
	 */
	public void setBigTotalDolares(BigDecimal bigTotalDolares) {
		this.bigTotalDolares = bigTotalDolares;
	}
		
}
