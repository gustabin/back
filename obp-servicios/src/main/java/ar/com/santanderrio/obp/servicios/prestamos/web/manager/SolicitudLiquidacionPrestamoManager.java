package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoOutView;

/**
 * Manager de prestamo para realizar la solicitud o liquidacion de un préstamo
 * utilizando el bff-prestamo.
 *
 */
public interface SolicitudLiquidacionPrestamoManager {

	/**
	 * Permite solicitar un nuevo prestamo
	 *
	 * @param solicitudPrestamoInView Datos de la solicitud del préstamo
	 * @return Respuesta con el estado del flujo y datos del prestamo
	 */
	Respuesta<SolicitudPrestamoOutView> solicitar(SolicitudPrestamoInView solicitudPrestamoInView);

	/**
	 * Permite liquidar un nuevo prestamo
	 *
	 * @param liquidacionPrestamoInView Datos para liquidar el préstamo
	 * @return Respuesta con el estado del flujo y datos del prestamo
	 */
	Respuesta<SolicitudPrestamoOutView> liquidar(LiquidacionPrestamoInView liquidacionPrestamoInView);

}
