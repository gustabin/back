/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimitesClasesCompras;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.entities.LimitesTarjetaBanelco;

/**
 * The Class DatosModificacionLimiteDebitoView.
 */
public class DatosModificacionLimiteDebitoView {

	/** The cuentas debito. */
	private List<CuentaDebito> cuentasDebito;

	/** The limite extraccion actual. */
	private List<String> limiteExtraccionActual;

	/** The limite compra actual. */
	private List<String> limiteCompraActual;

	/** The limites extraccion. */
	private List<LimitesTarjetaBanelco> limitesExtraccion;
	
	/** The limites completos compra. */
	private List<LimitesClasesCompras> limitesCompletosCompra;

	/**
	 * Gets the cuentas debito.
	 *
	 * @return the cuentas debito
	 */
	public List<CuentaDebito> getCuentasDebito() {
		return cuentasDebito;
	}

	/**
	 * Sets the cuentas debito.
	 *
	 * @param cuentasDebito
	 *            the new cuentas debito
	 */
	public void setCuentasDebito(List<CuentaDebito> cuentasDebito) {
		this.cuentasDebito = cuentasDebito;
	}

	/**
	 * Gets the limite extraccion actual.
	 *
	 * @return the limite extraccion actual
	 */
	public List<String> getLimiteExtraccionActual() {
		return limiteExtraccionActual;
	}

	/**
	 * Sets the limite extraccion actual.
	 *
	 * @param limiteExtraccionActual
	 *            the new limite extraccion actual
	 */
	public void setLimiteExtraccionActual(List<String> limiteExtraccionActual) {
		this.limiteExtraccionActual = limiteExtraccionActual;
	}

	/**
	 * Gets the limite compra actual.
	 *
	 * @return the limite compra actual
	 */
	public List<String> getLimiteCompraActual() {
		return limiteCompraActual;
	}

	/**
	 * Sets the limite compra actual.
	 *
	 * @param limiteCompraActual
	 *            the new limite compra actual
	 */
	public void setLimiteCompraActual(List<String> limiteCompraActual) {
		this.limiteCompraActual = limiteCompraActual;
	}

	/**
	 * Gets the limites extraccion.
	 *
	 * @return the limites extraccion
	 */
	public List<LimitesTarjetaBanelco> getLimitesExtraccion() {
		return limitesExtraccion;
	}

	/**
	 * Sets the limites extraccion.
	 *
	 * @param limitesExtraccion
	 *            the new limites extraccion
	 */
	public void setLimitesExtraccion(List<LimitesTarjetaBanelco> limitesExtraccion) {
		this.limitesExtraccion = limitesExtraccion;
	}

	/**
	 * Gets the limites completos compra.
	 *
	 * @return the limites completos compra
	 */
	public List<LimitesClasesCompras> getLimitesCompletosCompra() {
		return limitesCompletosCompra;
	}

	/**
	 * Sets the limites completos compra.
	 *
	 * @param limitesCompletosCompra the new limites completos compra
	 */
	public void setLimitesCompletosCompra(List<LimitesClasesCompras> limitesCompletosCompra) {
		this.limitesCompletosCompra = limitesCompletosCompra;
	}

}
