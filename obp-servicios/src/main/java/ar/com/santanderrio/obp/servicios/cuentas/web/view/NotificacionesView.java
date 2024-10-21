/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

/**
 * The Class NotificacionesView.
 */
public class NotificacionesView {

	/** The movimientos pendientes. */
	private MovimientosPendientesDeConfirmacionView movimientosPendientes;

	/**
	 * Gets the movimientos pendientes.
	 *
	 * @return MovimientosPendientesDeConfirmacion
	 */
	public MovimientosPendientesDeConfirmacionView getMovimientosPendientes() {
		return movimientosPendientes;
	}

	/**
	 * Setter para movimientos pendientes view.
	 *
	 * @param movimientosPendientes
	 *            el nuevo movimientos pendientes view
	 */
	public void setMovimientosPendientesView(MovimientosPendientesDeConfirmacionView movimientosPendientes) {
		this.movimientosPendientes = movimientosPendientes;
	}

}