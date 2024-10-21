/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.prestamos.view.CnsDetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetallePagosMinimosOpenCreditView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportePagosMinimosOpenCreditInView;

/**
 * OLYMPUS OpenCredit.
 *
 * @author Silvina_Luque
 */

@Path("/prestamosOpenCredit")
public interface PrestamoOpenCreditSEI {

	/**
	 * Consulta historial de pagos minimos.
	 *
	 * @param consultaDetallePagoMinimo
	 *            the consulta detalle pago minimo
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/obtenerDetallePagosMinimos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<DetallePagosMinimosOpenCreditView> obtenerDetallePagoMinimo(
			CnsDetallePagosMinimosOpenCreditView consultaDetallePagoMinimo);

	/**
	 * Exportar movimientos.
	 *
	 * @param reportePagosInView
	 *            the reporte pagos in view
	 * @return the respuesta
	 */
	@POST
	@Path("/exportarHistorialPagosMinimos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> exportarHistorialPagosMinimos(ReportePagosMinimosOpenCreditInView reportePagosInView);

}
