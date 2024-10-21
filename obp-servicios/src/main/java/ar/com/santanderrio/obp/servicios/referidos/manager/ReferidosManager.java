package ar.com.santanderrio.obp.servicios.referidos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.referidos.view.DatosEstadisticasReferidosView;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;

/**
 * Manager de referidos.
 *
 * @author A309331
 */
public interface ReferidosManager {
	
	/**
	 * Obtiene los datos necesarios para el inicio de referidos.
	 *
	 * @return Respuesta<ReferidosInicioResponseView>
	 */
	public Respuesta<ReferidosInicioResponseView> obtenerInicioReferidos();
	
	/**
	 * Grabar estadistica referidos.
	 *
	 * @param referidosView the referidos view
	 */
	public void grabarEstadisticaReferidos(DatosEstadisticasReferidosView referidosView);
	
	public Respuesta<ReferidosInicioResponseView> inicioReferidos();
}
