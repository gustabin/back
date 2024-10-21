/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteClase;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimiteDebito;

/**
 * The Class LimitesExtraccionDebitoDTO.
 */
public class LimitesExtraccionDebitoDTO {

	/** The limite extraccion actual. */
	private String limiteExtraccionActual;

	/** The limite compra actual. */
	private String limiteCompraActual;
	

	/** The limites completos compra. */
	private List<LimiteClase> limitesCompletosCompra;	

	/** The limites extraccion. */
	private LimiteDebito limitesExtraccion;

	/** The limites compra. */
	private LimiteDebito limitesCompra;

	/**
	 * Gets the limite extraccion actual.
	 *
	 * @return the limite extraccion actual
	 */
	public String getLimiteExtraccionActual() {
		return limiteExtraccionActual;
	}

	/**
	 * Sets the limite extraccion actual.
	 *
	 * @param limiteExtraccionActual
	 *            the new limite extraccion actual
	 */
	public void setLimiteExtraccionActual(String limiteExtraccionActual) {
		this.limiteExtraccionActual = limiteExtraccionActual;
	}

	/**
	 * Gets the limite compra actual.
	 *
	 * @return the limite compra actual
	 */
	public String getLimiteCompraActual() {
		return limiteCompraActual;
	}

	/**
	 * Sets the limite compra actual.
	 *
	 * @param limiteCompraActual
	 *            the new limite compra actual
	 */
	public void setLimiteCompraActual(String limiteCompraActual) {
		this.limiteCompraActual = limiteCompraActual;
	}

	/**
	 * Gets the limites extraccion.
	 *
	 * @return the limites extraccion
	 */
	public LimiteDebito getLimitesExtraccion() {
		return limitesExtraccion;
	}

	/**
	 * Sets the limites extraccion.
	 *
	 * @param limitesExtraccion
	 *            the new limites extraccion
	 */
	public void setLimitesExtraccion(LimiteDebito limitesExtraccion) {
		this.limitesExtraccion = limitesExtraccion;
	}

	/**
	 * Gets the limites compra.
	 *
	 * @return the limites compra
	 */
	public LimiteDebito getLimitesCompra() {
		return limitesCompra;
	}

	/**
	 * Sets the limites compra.
	 *
	 * @param limitesCompra
	 *            the new limites compra
	 */
	public void setLimitesCompra(LimiteDebito limitesCompra) {
		this.limitesCompra = limitesCompra;
	}

	/**
	 * Gets the limites completos compra.
	 *
	 * @return the limites completos compra
	 */
	public List<LimiteClase> getLimitesCompletosCompra() {
		return limitesCompletosCompra;
	}

	/**
	 * Sets the limites completos compra.
	 *
	 * @param limitesCompletosCompra the new limites completos compra
	 */
	public void setLimitesCompletosCompra(List<LimiteClase> limitesCompletosCompra) {
		this.limitesCompletosCompra = limitesCompletosCompra;
	}
	
}
