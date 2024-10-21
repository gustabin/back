/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;

/**
 * The Interface DetalleComprobanteBO.
 *
 * @author sabrina.cis
 */
public interface DetalleComprobanteBO {
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

}
