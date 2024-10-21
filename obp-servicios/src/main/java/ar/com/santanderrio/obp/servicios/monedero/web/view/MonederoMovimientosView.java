/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

/**
 * The Class MonederoMovimientosView.
 */
public class MonederoMovimientosView {

	/** The saldos Y movimientos. */
	private List<SaldosYMovimientosView> saldosYMovimientos;

	/**
	 * Gets the saldos Y movimientos.
	 *
	 * @return the saldos Y movimientos
	 */
	public List<SaldosYMovimientosView> getSaldosYMovimientos() {
		return saldosYMovimientos;
	}

	/**
	 * Sets the saldos Y movimientos.
	 *
	 * @param saldosYMovimientos
	 *            the new saldos Y movimientos
	 */
	public void setSaldosYMovimientos(List<SaldosYMovimientosView> saldosYMovimientos) {
		this.saldosYMovimientos = saldosYMovimientos;
	}

}
