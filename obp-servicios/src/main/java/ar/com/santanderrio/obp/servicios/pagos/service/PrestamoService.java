/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface CuentaService.
 */

public interface PrestamoService {

	/**
	 * Pagar.
	 *
	 * @param pagoPrestamo
	 *            the pago prestamo
	 * @param cantidadCuentasDebito
	 *            the cantidad cuentas debito
	 * @return the respuesta
	 */
	Respuesta<ComprobantePrestamo> pagar(PagoPrestamo pagoPrestamo, int cantidadCuentasDebito);

	/**
	 * Obtener comprobante prestamo.
	 *
	 * @param pagoPendientePrestamo
	 *            the pago pendiente prestamo
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	public Respuesta<Reporte> obtenerComprobantePrestamo(PagoPendientePrestamo pagoPendientePrestamo,
			ComprobantePrestamo comprobantePrestamo) throws ServiceException;

	/**
	 * Obtener reporte detalle prestamo.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<Reporte> obtenerReporteDetallePrestamo(ComprobantePrestamoReporte comprobantePrestamoReporte)
			throws ServiceException;

	/**
	 * Obtener mensaje pago ok.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the string
	 */
	String obtenerMensajePagoOk(Prestamo respuesta, ComprobantePrestamo comprobantePrestamo);

	/**
	 * Validar horario.
	 *
	 * @return true, if successful
	 */
	boolean validarHorario();

}
