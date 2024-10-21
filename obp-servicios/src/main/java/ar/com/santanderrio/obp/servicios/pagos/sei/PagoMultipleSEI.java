/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ListaPDFPagosMultiples;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;

/**
 * The Interface PagoMultipleSEI.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@Path("/pagoMultiple")
public interface PagoMultipleSEI {

	/**
	 * Solicitar stop debit.
	 *
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	@POST
	@Path("/solicitarPagoMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoMultipleListView> solicitarPagoMultiple(PagoMultipleListView pagoMultipleListView);

	/**
	 * Ejecutar pago multiple. DTF: 10221. iatx: PAGMASSCIO100.
	 * 
	 * @author Manuel.Vargas -B041299
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarPagoMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<PagoMultipleListView> ejecutarPagoMultiple(PagoMultipleListView pagoMultipleListView);

	/**
	 * Grabar estadistica comprobante pago multiple.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaComprobantePagoMultiple")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Boolean> grabarEstadisticaComprobantePagoMultiple();

	/**
	 * Descargar comprobante PDF.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobantePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobantePDF(PagoMultipleView pagoMultipleView);
	
	/**
	 * Continuar pago.
	 *
	 * @param pagoMultipleListView
	 *            the pago multiple list view
	 * @return the respuesta
	 */
	@POST
	@Path("/continuarPago")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> continuarPago(PagoMultipleListView pagoMultipleListView);
	
	/**
	 * Descargar comprobante PDF.
	 *
	 * @param pagoMultipleView
	 *            the pago multiple view
	 * @return the respuesta
	 */
	@POST
	@Path("/imprimirComprobantes")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> imprimirComprobantes();
	
	@POST
	@Path("/descargaMultipleComprobantesPDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ListaPDFPagosMultiples> descargaMultipleComprobantesPDF();
	
}
