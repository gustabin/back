/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.login.bo.LoginBO;

/**
 * The Class LoginBOImpl.
 */
@Component
public class LoginBOImpl implements LoginBO {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginBOImpl.class);

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The cliente BO. */
	@Autowired
	private ClienteBO clienteBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.login.bo.LoginBO#obtenerInicioLogin()
	 */
	@Override
	public Respuesta<Mensaje> obtenerInicioLogin() {

		Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
		Mensaje mensaje = new Mensaje();
		respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.OK);

		try {
			mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.LOGIN_INICIO_AYUDA);
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			mensaje.setMensaje("");
			respuestaMensaje.setEstadoRespuesta(EstadoRespuesta.ERROR);
		}

		respuestaMensaje.setRespuesta(mensaje);

		return respuestaMensaje;
	}

}
