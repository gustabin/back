/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionPagoCuotaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.DetallePrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.RespuestaPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DetalleCuotaPrestamoView;

/**
 * The Interface PrestamoManager.
 *
 * @author B039637
 */
public interface PrestamoManager {

	/**
	 * Gets the detalle prestamos.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param desdeInicioPrestamo
	 *            the desde inicio prestamo
	 * @return the detalle prestamos
	 */
	Respuesta<DetallePrestamoView> getDetallePrestamos(ConsultaPrestamo consultaPrestamo, Boolean desdeInicioPrestamo);

	/**
	 * Obtener comprobante.
	 *
	 * @param nroPrestamo
	 *            the nro prestamo
	 * @return the respuesta
	 */
	Respuesta<ComprobanteView> obtenerComprobante(String nroPrestamo);

	/**
	 * Obtener configuracion pago cuota prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @param desdeInicioPrestamo
	 *            the desde inicio prestamo
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionPagoCuotaPrestamo> obtenerConfiguracionPagoCuotaPrestamo(ConsultaPrestamo consultaPrestamo,
			Boolean desdeInicioPrestamo);

	/**
	 * Obtener confirmacion.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionPagoPrestamoView> obtenerConfirmacion(ConsultaPrestamo consultaPrestamo);

	/**
	 * Obtener reporte comprobante.
	 *
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @return the respuesta
	 */
	Respuesta<ReporteView> obtenerReporteComprobante(String numeroPrestamo);

	/**
	 * Gets the reporte detalle prestamo.
	 *
	 * @param numeroPrestamo
	 *            the numero prestamo
	 * @param isUva 
	 * @return the reporte detalle prestamo
	 */
	Respuesta<ReporteView> getReporteDetallePrestamo(String numeroPrestamo, Boolean isUva);

	/**
	 * Efectuar pago.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	Respuesta<RespuestaPagoPrestamoView> efectuarPago(ConsultaPrestamo consultaPrestamo);

	/**
	 * Notificar detalle importe cuota.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the respuesta
	 */
	void notificarDetalleImporteCuota(String tipo);

	/**
	 * Notificar detalle tasas cuota.
	 *
	 * @param tipo
	 *            the tipo
	 * @return the respuesta
	 */
	void notificarDetalleTasasCuota(String tipo);

	/**
	 * Ver detalle prestamo.
	 *
	 * @param consultaPrestamo
	 *            the consulta prestamo
	 * @return the respuesta
	 */
	Respuesta<DetalleCuotaPrestamoView> verDetallePrestamo(ConsultaPrestamo consultaPrestamo);

	/**
	 * Descargar pdf tasas.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarPdfTasas();

}