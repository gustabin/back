/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PreFormalizacionPrestamoService;

/**
 * The Class PreFormalizacionPrestamoServiceImpl.
 */
@Component
public class PreFormalizacionPrestamoServiceImpl implements PreFormalizacionPrestamoService {

	/** The pre formalizacion prestamo BO. */
	@Autowired
	private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagos.service.
	 * PreFormalizacionPrestamoService#obtenerPreFormalizacion(ar.com.
	 * santanderrio.obp.servicios.pagos.entities.Prestamo)
	 */
	@Override
	public Respuesta<PreFormalizacion> obtenerPreFormalizacion(Prestamo prestamo) throws ServiceException {
		return preFormalizacionPrestamoBO.obtenerPreFormalizacion(prestamo);
	}

}
