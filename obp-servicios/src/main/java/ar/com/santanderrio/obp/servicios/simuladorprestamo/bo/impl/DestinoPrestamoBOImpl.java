/*
 * 
 */
package ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.entity.DestinoPrestamo;

/**
 * The Class DestinoPrestamoBOImpl.
 */
@Component
public class DestinoPrestamoBOImpl implements DestinoPrestamoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinoPrestamoBOImpl.class);

	/** The destino prestamo dao. */
	@Autowired
	private DestinoPrestamoDAO destinoPrestamoDao;

	/** The respuesta factory. */
	@Autowired
	RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO#
	 * vaciarDestinosPrestamo()
	 */
	@Override
	public Respuesta<Boolean> vaciarDestinosPrestamo() {
		destinoPrestamoDao.vaciarDestinosPrestamo();
		return respuestaFactory.crearRespuestaOk(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO#
	 * buscarPorId(java.lang.String)
	 */
	@Override
	public DestinoPrestamo buscarPorId(String id) throws BusinessException {
		try {
			for (DestinoPrestamo destinoPrestamo : destinoPrestamoDao.obtener()) {
				if (destinoPrestamo.obtenerId().equals(id)) {
					return destinoPrestamo;
				}
			}

			return null;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO#
	 * buscarPorCodigoDestinoFondo(java.lang.String)
	 */
	@Override
	public DestinoPrestamo buscarPorCodigoDestinoFondo(String codigoDestinoFondo) throws BusinessException {
		try {
			for (DestinoPrestamo destinoPrestamo : destinoPrestamoDao.obtener()) {
				if (destinoPrestamo.getDestinoDeFondosUG().equals(codigoDestinoFondo)) {
					return destinoPrestamo;
				}
			}

			return null;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO#
	 * buscarDescripcionPorCodigoDestinoFondo(java.lang.String)
	 */
	@Override
	public String buscarDescripcionPorCodigoDestinoFondo(String codigoDestinoFondo) {
		try {
			DestinoPrestamo destinoPrestamo = buscarPorCodigoDestinoFondo(codigoDestinoFondo);
			if (destinoPrestamo != null) {
				return destinoPrestamo.getDescripcionUG();
			}
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
