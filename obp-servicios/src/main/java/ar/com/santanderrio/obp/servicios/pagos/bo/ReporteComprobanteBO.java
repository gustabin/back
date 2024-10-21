/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;

/**
 * The Interface ReporteComprobanteBO.
 */
public interface ReporteComprobanteBO {

	/**
	 * Obtener reporte.
	 *
	 * @param pagoPendientePrestamo
	 *            the pago pendiente prestamo
	 * @param comprobantePrestamo
	 *            the comprobante prestamo
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	public Respuesta<Reporte> obtenerReporte(PagoPendientePrestamo pagoPendientePrestamo,
			ComprobantePrestamo comprobantePrestamo) throws BusinessException;

	/**
	 * Obtener reporte detalle prestamo.
	 *
	 * @param comprobantePrestamoReporte
	 *            the comprobante prestamo reporte
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<Reporte> obtenerReporteDetallePrestamo(ComprobantePrestamoReporte comprobantePrestamoReporte)
			throws BusinessException;
}
