/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface DetalleComprobantesManager.
 * 
 * @author sabrina.cis
 */
public interface DetalleComprobantesManager {

	/**
	 * Obtener detalle comprobantes.
	 * 
	 * @param id
	 *            the id
	 * @param esGrillaComprobantes
	 *            the es grilla comprobantes
	 * @return the respuesta
	 */
	Respuesta<DetalleComprobanteView> obtenerDetalleComprobantes(String id, boolean esGrillaComprobantes);

	/**
	 * Descarga PDF de detalle de comprobante desde grilla
	 * 
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargaComprobanteGrilla();

	/**
	 * Descarga PDF de detalle desde historial.
	 * 
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargaHistorial(String idComprobante);

}
