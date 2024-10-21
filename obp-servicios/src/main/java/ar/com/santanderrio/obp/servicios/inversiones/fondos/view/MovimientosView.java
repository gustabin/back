/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean con la lista de movimientos de un fondo de inversion en particular.
 *
 * @author marcelo.ruiz
 */
public class MovimientosView {

	/** The movimientos. */
	List<MovimientoFondoView> movimientos = new ArrayList<MovimientoFondoView>();

	/**
	 * Gets the movimientos.
	 *
	 * @return the movimientos
	 */
	public List<MovimientoFondoView> getMovimientos() {
		return movimientos;
	}

	/**
	 * Sets the movimientos.
	 *
	 * @param movimientos
	 *            the movimientos to set
	 */
	public void setMovimientos(List<MovimientoFondoView> movimientos) {
		this.movimientos = movimientos;
	}
}
