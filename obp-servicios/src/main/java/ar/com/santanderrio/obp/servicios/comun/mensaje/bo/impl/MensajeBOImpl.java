/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.exception.RobotException;

/**
 * The Class MensajeBOImpl.
 */
@Component
public class MensajeBOImpl implements MensajeBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MensajeBOImpl.class);

	/** The mensaje dao. */
	@Autowired
	private MensajeDAO mensajeDao;

	/** The Constant MENSAJE_VACIO. */
	private static final String MENSAJE_VACIO = "";

	/** The Constant ERROR_MENSAJE. */
	private static final String ERROR_MENSAJE = "Ha ocurrido un error al acceder al mensaje con el codigo: ";

	/** The Constant PATRON_LLAVE_OK. */
	private static final String PATRON_LLAVE_OK = "\\{[0-9]\\}";

	/** The Constant PATRON_LLAVE. */
	private static final String PATRON_LLAVE = "\\{[0-9]?\\}";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensajeDescripcion(java.lang.String)
	 * 
	 * Usar MensajeBOImpl#obtenerMensajePorCodigo
	 */
	@Deprecated
	public String obtenerMensajeDescripcion(String codigoMensaje) {
		return mensajeDao.obtenerMensajeDescripcion(codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensaje(java.lang.String)
	 * 
	 * Usar MensajeBOImpl#obtenerMensajePorCodigo
	 */
	@Override
	@Deprecated
	public Mensaje obtenerMensaje(String codigoMensaje) {
		return mensajeDao.obtenerMensaje(codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensajePorCodigo(java.lang.String)
	 */
	/*
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensaje(java.lang.String)
	 */
	@Override
	public Mensaje obtenerMensajePorCodigo(String codigoMensaje) {
		try {
			Mensaje mensaje = new Mensaje(mensajeDao.obtenerMensajePorCodigo(codigoMensaje));
			if (validarFormatoLlaves(mensaje.getMensaje())) {
				return mensaje;
			} else {
				throw new RobotException(
						"El mensaje con el codigo: " + codigoMensaje + " no ha superado la validación de llaves");
			}
		} catch (RobotException e) {
			LOGGER.error(ERROR_MENSAJE + codigoMensaje, e);
			Mensaje mensajeVacio = new Mensaje();
			mensajeVacio.setMensaje(MENSAJE_VACIO);
			return mensajeVacio;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensajePorCodigo(java.lang.String, java.lang.String[])
	 */
	@Override
	public Mensaje obtenerMensajePorCodigo(String codigoMensaje, String... parametros) {
		try {
			Mensaje mensaje = new Mensaje(mensajeDao.obtenerMensajePorCodigo(codigoMensaje));
			if (validarFormatoLlaves(mensaje.getMensaje())) {
				mensaje.setMensaje(MessageFormat.format(mensaje.getMensaje(), parametros));
				return mensaje;
			} else {
				throw new RobotException(
						"El mensaje con el codigo: " + codigoMensaje + " no ha superado la validación de llaves");
			}
		} catch (RobotException e) {
			LOGGER.error(ERROR_MENSAJE + codigoMensaje, e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(ERROR_MENSAJE + codigoMensaje, e);
		}
		Mensaje mensajeVacio = new Mensaje();
		mensajeVacio.setMensaje(MENSAJE_VACIO);
		return mensajeVacio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensaje(java.lang.String)
	 */
	@Override
	public Mensaje obtenerMensajePorCodigoConErrorGenerico(String codigoMensaje) {
		return new Mensaje(mensajeDao.obtenerMensajePorCodigo(codigoMensaje));
	}

	/**
	 * Valida si el formato del mensaje contiene correctamente el patron {#}
	 * para luego inyectar de manera correcta los textos dinamicamente.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @return true, if successful
	 */
	private boolean validarFormatoLlaves(String mensaje) {
		Pattern pattern = Pattern.compile(PATRON_LLAVE);
		Matcher matcher = pattern.matcher(mensaje);
		boolean respuesta = true;
		while (matcher.find() && respuesta == true) {
			respuesta = matcher.group().matches(PATRON_LLAVE_OK);
		}
		return respuesta;
	}

}
