/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosComprobante;

/**
 * The Interface ReporteBO.
 */
public interface ReporteBO {

	/**
	 * Descargar comprobante.
	 *
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	Respuesta<Reporte> descargarComprobante(DatosComprobante datos);
}
