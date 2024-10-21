/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.AltaChequesViewOut;
import ar.com.santanderrio.obp.servicios.descuento.cheques.web.view.DetalleOperacionesPrecargadasView;

/**
 * The Interface ReporteDescuentoChequesPDFBO.
 */
public interface ReporteDescuentoChequesPDFBO {

	/**
	 * Obtener operacion PDF.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerOperacionPDF(DetalleOperacionesPrecargadasView detalleIn);
	
	/**
	 * Obtener operacion PDF.
	 *
	 * @param chequesView
	 *            the cheques view
	 * @return the respuesta
	 */
	Respuesta<Reporte> obtenerOperacionPDF(AltaChequesViewOut chequesView);

}
