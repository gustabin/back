/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class PreFormalizacionPrestamoBOImpl.
 */
@Component
public class PreFormalizacionPrestamoBOImpl implements PreFormalizacionPrestamoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PreFormalizacionPrestamoBOImpl.class);

	/** The Constant RESPUESTA_OK. */
	private static final String RESPUESTA_OK = "Respuesta obtenida del servicio: {}.";

	/** The pre formalizacion prestamo DAO. */
	@Autowired
	private PreFormalizacionPrestamoDAO preFormalizacionPrestamoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/**
	 * Obtiene la PreFormalizacion de un prestamo.
	 *
	 * @param prestamo
	 *            the prestamo
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PreFormalizacion> obtenerPreFormalizacion(Prestamo prestamo) {
		return obtenerPreFormalizacion(prestamo.getCuenta());
	}

	/**
	 * Obtiene la PreFormalizacion de un prestamo a partir de la Cuenta del
	 * mismo.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PreFormalizacion> obtenerPreFormalizacion(Cuenta cuenta) {
		try {
			Respuesta<PreFormalizacion> respuesta = respuestaFactory
					.crearRespuestaOk(obtenerPreFormalizacionConFechaInicioFormateada(cuenta));
			LOGGER.info(RESPUESTA_OK, respuesta);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return respuestaFactory.crearRespuestaError(PreFormalizacion.class, null,
					TipoError.ERROR_CONSULTA_PREFORMALIZACION, CodigoMensajeConstantes.CODIGO_ERROR_PREFORMALIZACION);
		}
	}

	/**
	 * Obtiene la PreFormalizacion de un prestamo con fecha de inicio
	 * formateada.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the pre formalizacion
	 * @throws DAOException
	 *             the DAO exception
	 */
	private PreFormalizacion obtenerPreFormalizacionConFechaInicioFormateada(Cuenta cuenta) throws DAOException {
		try {
			PreFormalizacion preFormalizacion = preFormalizacionPrestamoDAO.obtenerPreFormalizacion(cuenta);
			if (!StringUtils.isBlank(preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio())) {
				preFormalizacion.getPrestamoDebitoAdherido().setFechaInicio(StringUtils.substring
						(preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio(), 10, 20));
			}
			
			if (!StringUtils.isBlank(preFormalizacion.getPrestamoDebitoAdherido().getFechaCotizacion())) {
				preFormalizacion.getPrestamoDebitoAdherido().setFechaCotizacion(
						(preFormalizacion.getPrestamoDebitoAdherido().getFechaCotizacion()));
			}
			return preFormalizacion;
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage(), e.getErrorCode());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO#
	 * obtenerPreFormalizacion(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, java.lang.String, java.lang.String)
	 */
	@Override
	public PreFormalizacion obtenerPreFormalizacion(Cliente cliente, String sucursal, String numeroDePrestamo) {
		try {
			PreFormalizacion preFormalizacion = preFormalizacionPrestamoDAO.obtenerPreFormalizacion(cliente, sucursal,
					numeroDePrestamo);
			preFormalizacion.getPrestamoDebitoAdherido().setFechaInicio(
					//StringUtils.substring(preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio(), 10, 20).replace("-", "/"));
					StringUtils.substring(preFormalizacion.getPrestamoDebitoAdherido().getFechaInicio(), 10, 20));
			return preFormalizacion;
		} catch (DAOException e) {
			LOGGER.error("Error al obtenter pre formalizacion", e);
			return null;
		}
	}
}
