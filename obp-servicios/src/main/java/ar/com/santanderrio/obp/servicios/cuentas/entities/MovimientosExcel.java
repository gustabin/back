/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.List;

/**
 * The Class MovimientosExcel.
 */
public class MovimientosExcel {

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
	 * Setter para movimientos.
	 *
	 * @param movimientos
	 *            el nuevo movimientos
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
	 * Setter para cabecera.
	 *
	 * @param cabecera
	 *            el nuevo cabecera
	 */
	public void setCabecera(MovimientosExcelCabecera cabecera) {
		this.cabecera = cabecera;
	}
	
}
