/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * @author sergio.e.goldentair
 *
 */
public interface BuscadorEmpresaIndexadoBO {
    /**
     * Limpiar el mapa de memoria y reindexar lucene en base a la existencia de un
     * pid.
     * 
     * @return
     */
    Respuesta<Boolean> limpiarMediosDePago();
}
