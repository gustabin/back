/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Interface PagoMultiple.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
public interface PagoMultipleManager {

	/**
	 * Solicitar pago multiple. DTF: 10221. iatx: PAGMASSCIO100.
	 *
	 * @author Manuel.Vargas -B041299
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	Respuesta<PagoMultipleListView> solicitarPagoMultiple(PagoMultipleListView pagoMultipleListView);

	/**
	 * Ejecutar pago multiple. DTF: 10221. iatx: PAGMASSCIO100.
	 * 
	 * @author Manuel.Vargas -B041299
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	Respuesta<PagoMultipleListView> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView);

	/**
	 * Graba la estadistica de acceso al comprobante de un pago multiple.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> grabarEstadisticaComprobantePagoMultiple();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobantePDF(PagoMultipleView pagoMultipleView);
	
	/**
	 * Continuar Pago – Carga en sesion el hash del pago.
	 *
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	Respuesta<Void> continuarPago(PagoMultipleListView pagoMultipleListView);
	
	Respuesta<ReporteView> imprimirComprobantes();

	
	Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF();

}
