/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class MovimientosPendientesDeConfirmacionView.
 */
public class MovimientosPendientesDeConfirmacionView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The movimientos pendientes de confirmacion. */
	private List<MovimientoDeCuentaView> movimientosPendientesDeConfirmacion;

	/** The cantidad de movimientos pendientes de confirmacion. */
	private int cantidadDeMovimientosPendientesDeConfirmacion;

	/**
	 * Gets the movimientos pendientes de confirmacion.
	 *
	 * @return the movimientos pendientes de confirmacion
	 */
	public List<MovimientoDeCuentaView> getMovimientosPendientesDeConfirmacion() {
		return movimientosPendientesDeConfirmacion;
	}

	/**
	 * Setter para movimientos pendientes de confirmacion.
	 *
	 * @param movimientosPendientesDeConfirmacion
	 *            el nuevo movimientos pendientes de confirmacion
	 */
	public void setMovimientosPendientesDeConfirmacion(
			List<MovimientoDeCuentaView> movimientosPendientesDeConfirmacion) {
		this.movimientosPendientesDeConfirmacion = movimientosPendientesDeConfirmacion;
	}

	/**
	 * Gets the cantidad de movimientos pendientes de confirmacion.
	 *
	 * @return the cantidad de movimientos pendientes de confirmacion
	 */
	public int getCantidadDeMovimientosPendientesDeConfirmacion() {
		return cantidadDeMovimientosPendientesDeConfirmacion;
	}

	/**
	 * Setter para cantidad de movimientos pendientes de confirmacion.
	 *
	 * @param cantidadDeMovimientosPendientesDeConfirmacion
	 *            el nuevo cantidad de movimientos pendientes de confirmacion
	 */
	public void setCantidadDeMovimientosPendientesDeConfirmacion(int cantidadDeMovimientosPendientesDeConfirmacion) {
		this.cantidadDeMovimientosPendientesDeConfirmacion = cantidadDeMovimientosPendientesDeConfirmacion;
	}

}
