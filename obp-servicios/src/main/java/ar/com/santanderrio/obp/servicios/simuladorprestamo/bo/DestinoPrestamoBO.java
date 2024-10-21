/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Interface DestinoPrestamoBO.
 */
public interface DestinoPrestamoBO {

	/**
	 * Buscar por id.
	 *
	 * @param id
	 *            the id
	 * @return the destino prestamo
	 * @throws BusinessException
	 *             the business exception
	 */
	DestinoPrestamo buscarPorId(String id) throws BusinessException;

	/**
	 * Buscar por codigo destino fondo.
	 *
	 * @param codigoDestinoFondo
	 *            the codigo destino fondo
	 * @return the destino prestamo
	 * @throws BusinessException
	 *             the business exception
	 */
	DestinoPrestamo buscarPorCodigoDestinoFondo(String codigoDestinoFondo) throws BusinessException;

	/**
	 * Vaciar destinos prestamo.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> vaciarDestinosPrestamo();

	/**
	 * Buscar descripcion por codigo destino fondo.
	 *
	 * @param codigoDestinoFondo
	 *            the codigo destino fondo
	 * @return the string
	 */
	String buscarDescripcionPorCodigoDestinoFondo(String codigoDestinoFondo);
}
