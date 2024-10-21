/**
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
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;

/**
 * The Interface PrestamoSEI.
 */
@Path("/pagoPrestamo")
public interface PrestamoSEI {

	/**
	 * Gets the detalle prestamos.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the detalle prestamos
	 */
	@POST
	@Path("/obtenerDetallePrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo);

	/**
	 * Obtener comprobante.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerComprobantePrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteView> obtenerComprobante(ConsultaPrestamo consultaPrestamo);
	
	/**
	 * Descargar pdf tasas.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarTasasPDF")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarPdfTasas();

	/**
	 * Obtener configuracion pago cuota prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerConfiguracionPagoCuotaPrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(ConsultaPrestamo consultaPrestamo);

	/**
	 * Obtener confirmacion.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerConfirmacionPago")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionPagoPrestamoView> obtenerConfirmacion(ConsultaPrestamo consultaPrestamo);

	/**
	 * Pagar.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/pagoPrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<RespuestaPagoPrestamoView> pagar(ConsultaPrestamo consultaPrestamo);

	/**
	 * Obtener reporte comprobante.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerReporteComprobantePrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> obtenerReporteComprobante(ConsultaPrestamo consultaPrestamo);

	/**
	 * Obtener reporte detalle prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerReporteDetallePrestamo")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> obtenerReporteDetallePrestamo(ConsultaPrestamo consultaPrestamo);

	/**
	 * Notificar detalle importe cuota.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the respuesta
	 */
	@POST
	@Path("/notificarDetalleImporteCuota")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void notificarDetalleImporteCuota(String tipo);

	/**
	 * Notificar detalle tasas cuota.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the respuesta
	 */
	@POST
	@Path("/notificarDetalleTasasCuota")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void notificarDetalleTasasCuota(String tipo);

}
