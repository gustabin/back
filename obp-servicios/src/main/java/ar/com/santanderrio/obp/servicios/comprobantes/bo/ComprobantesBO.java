/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;

/**
 * The Interface ComprobantesBO.
 * 
 * @author sabrina.cis
 */
public interface ComprobantesBO {

    /**
     * Obtener titulo archivo.
     * 
     * @param tipoOperacion
     *            the tipo operacion
     * @param isEfectuada
     *            the is efectuada
     * @return the string
     */
    String obtenerTituloArchivo(TipoOperacionComprobanteEnum tipoOperacion, Boolean isEfectuada);

    /**
     * Obtener titulo archivo.
     * 
     * @param tipoOperacion
     *            the tipo operacion
     * @param detalleEstado
     *            the detalle estado
     * @return the string
     */
    String obtenerTituloArchivo(TipoOperacionComprobanteEnum tipoOperacion, String detalleEstado);

}
