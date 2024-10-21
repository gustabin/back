/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cache.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;

/**
 * Informacion de estadistica de las cache.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface UsedCacheManager {

    /**
     * Obtener informacion de estadistica de las cache.
     *
     * @return the respuesta
     */
    Respuesta<List<CacheView>> obtenerEstadisticas();
}
