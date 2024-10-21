/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * @author sergio.e.goldentair
 *
 */
public interface BuscadorEmpresaIndexadoManager {

    /**
     * Limpiar lucene y satelites.
     * 
     * @return
     */
    Respuesta<Boolean> limpiarMediosDePago();
}
