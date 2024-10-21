/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

/**
 * The Class DatosMonederoConMovimientosYSaldo.
 */
public class DatosMonederoConMovimientosYSaldoView {

	/** The tag. */
	private MonederoTagView tag;

	/** The saldos Y movimientos. */
	private List<SaldosYMovimientosMonederoView> saldosYMovimientos;

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public MonederoTagView getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag
	 *            the new tag
	 */
	public void setTag(MonederoTagView tag) {
		this.tag = tag;
	}

	/**
	 * Gets the saldos Y movimientos.
	 *
	 * @return the saldos Y movimientos
	 */
	public List<SaldosYMovimientosMonederoView> getSaldosYMovimientos() {
		return saldosYMovimientos;
	}

	/**
	 * Sets the saldos Y movimientos.
	 *
	 * @param saldosYMovimientos
	 *            the new saldos Y movimientos
	 */
	public void setSaldosYMovimientos(List<SaldosYMovimientosMonederoView> saldosYMovimientos) {
		this.saldosYMovimientos = saldosYMovimientos;
	}

}
