/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class MensajeManagerImpl.
 */
@Component
public class MensajeManagerImpl implements MensajeManager {

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MensajeManagerImpl.class);

	/** The respuesta factory. */
	@Autowired
	/** Respuesta factory */
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensaje(java.lang.String)
	 */
	@Override
	public Mensaje obtenerMensajePorCodigo(String codigoMensaje) {
		return mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO#
	 * obtenerMensaje(java.lang.String)
	 */
	@Override
	public Mensaje obtenerMensajePorCodigoConErrorGenerico(String codigoMensaje) {
		return mensajeBO.obtenerMensajePorCodigoConErrorGenerico(codigoMensaje);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.
	 * MensajeManager#limpiarMensajes()
	 */
	@CacheEvict(value = "mensajesCache", allEntries = true)
	@Override
	public Respuesta<Boolean> limpiarMensajes() {
		LOGGER.info("Se limpia la cache.");
		return respuestaFactory.crearRespuestaOk(true);
	}
}
