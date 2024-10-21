/*
 * 
 */
package ar.com.santanderrio.obp.servicios.trackingtarjetas.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;

/**
 * TrackingTarjetasManager.
 *
 * @author Silvina_Luque
 */
public interface TrackingTarjetasManager {
    
    /**
	 * Obtiene datos de tracking de tarjetas.
	 *
	 * @return the respuesta
	 */
    Respuesta<TrackingTarjetasView> obtenerTrackingTarjetas();


}
