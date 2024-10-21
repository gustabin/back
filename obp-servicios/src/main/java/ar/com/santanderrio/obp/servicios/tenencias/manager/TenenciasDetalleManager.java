/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleInView;
import ar.com.santanderrio.obp.servicios.tenencias.view.TenenciasDetalleView;

/**
 * Gestiona la lógica relacionada con los detalles de las tenencias.
 *
 * @author desa
 */
public interface TenenciasDetalleManager {

	/**
	 * realiza consultar en Tenencias Detalle.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<TenenciasDetalleView> consultarDetalleTenencias(TenenciasDetalleInView viewRequest);

	/**
	 * Crea la consulta de todas los detalles de las tenencias.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<TenenciasDetalleView> consultarAllDetalleTenencias(TenenciasDetalleInView viewRequest);

}
