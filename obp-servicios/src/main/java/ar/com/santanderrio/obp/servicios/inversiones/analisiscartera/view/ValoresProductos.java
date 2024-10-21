/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class ValoresProductos.
 */
public class ValoresProductos implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1087592305031683202L;

	/** The t. */
	private String t;
	
	/** The y. */
	private BigDecimal y;

	/**
	 * Gets the t.
	 *
	 * @return the t
	 */
	public String getT() {
		return t;
	}

	/**
	 * Sets the t.
	 *
	 * @param x
	 *            the new t
	 */
	public void setT(String x) {
		this.t = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public BigDecimal getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y
	 *            the new y
	 */
	public void setY(BigDecimal y) {
		this.y = y;
	}
}
