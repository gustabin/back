/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcelCabecera;

/**
 * The Class MovimientosExcel.
 */
public class MovimientosExcelEntity {

	/** The movimientos. */
	private List<MovimientoExcelItem> movimientos;

	/** The cabecera. */
	private MovimientosExcelCabecera cabecera;

	/**
	 * Gets the movimientos.
	 *
	 * @return the movimientos
	 */
	public List<MovimientoExcelItem> getMovimientos() {
		return movimientos;
	}

	/**
	 * Sets the movimientos.
	 *
	 * @param movimientos
	 *            the new movimientos
	 */
	public void setMovimientos(List<MovimientoExcelItem> movimientos) {
		this.movimientos = movimientos;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public MovimientosExcelCabecera getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(MovimientosExcelCabecera cabecera) {
		this.cabecera = cabecera;
	}

}
