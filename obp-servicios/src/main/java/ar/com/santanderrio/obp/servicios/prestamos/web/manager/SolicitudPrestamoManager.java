/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudSimulacionView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 * The Interface SolicitudPrestamoManager.
 *
 * @author florencia.n.martinez
 */
public interface SolicitudPrestamoManager {

	/**
	 * Obtener feeback solicitud prestamo.
	 *
	 * @param solicitudSimulacionView
	 *            the solicitud simulacion view
	 * @return the respuesta
	 */
	Respuesta<ComprobanteFeedbackView> obtenerFeebackSolicitudPrestamo(SolicitudSimulacionView solicitudSimulacionView);
}
