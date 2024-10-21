/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.List;

/**
 * The Class DetalleUltimosMovimientos.
 */
public class DetalleUltimosMovimientos {

	/** The last page. */
	private boolean lastPage;

	/** The detalle movimiento. */
	private List<DetalleMovimientoEntity> detalleMovimiento;

	/**
	 * Instantiates a new detalle ultimos movimientos.
	 */
	public DetalleUltimosMovimientos() {
	}

	/**
	 * Instantiates a new detalle ultimos movimientos.
	 *
	 * @param detalleMovimientoList
	 *            the detalle movimiento list
	 */
	public DetalleUltimosMovimientos(List<DetalleMovimientoEntity> detalleMovimientoList) {
		this.detalleMovimiento = detalleMovimientoList;
	}

	/**
	 * Gets the detalle movimiento.
	 *
	 * @return the detalle movimiento
	 */
	public List<DetalleMovimientoEntity> getDetalleMovimiento() {
		return detalleMovimiento;
	}

	/**
	 * Setter para detalle movimiento.
	 *
	 * @param detalleMovimiento
	 *            el nuevo detalle movimiento
	 */
	public void setDetalleMovimiento(List<DetalleMovimientoEntity> detalleMovimiento) {
		this.detalleMovimiento = detalleMovimiento;
	}

	/**
	 * Checks if is last page.
	 *
	 * @return true, if is last page
	 */

	public boolean isLastPage() {
		return lastPage;
	}

	/**
	 * Sets the last page.
	 *
	 * @param lastPage
	 *            the new last page
	 */

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

}
