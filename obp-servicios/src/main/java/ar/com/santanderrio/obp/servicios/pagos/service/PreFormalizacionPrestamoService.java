/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Interface PreFormalizacionPrestamoService.
 */
public interface PreFormalizacionPrestamoService {

	/**
	 * Obtener pre formalizacion.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @return the respuesta
	 * @throws ServiceException
	 *             the service exception
	 */
	Respuesta<PreFormalizacion> obtenerPreFormalizacion(Prestamo prestamo) throws ServiceException;
}
