package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.HistorialComprobantesView;

public interface HistorialComprobantesManager {

	/**
	 * Consulta historial.
	 * 
	 * @param viewIn
	 *            the view in
	 * @return the respuesta
	 */
	Respuesta<HistorialComprobantesView> consultaHistorial(String id);

}
