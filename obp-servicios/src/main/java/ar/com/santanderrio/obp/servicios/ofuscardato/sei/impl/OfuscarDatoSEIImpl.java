/**
 * 
 */
package ar.com.santanderrio.obp.servicios.ofuscardato.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.ofuscardato.sei.OfuscarDatoSEI;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.manager.MostrarDatoManager;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MailDesafioView;
import ar.com.santanderrio.obp.servicios.ofuscardato.web.view.MostrarMailView;

/**
 * The Class CompraVentaSEIImpl.
 *
 * @author Emanuel Diaz
 */
@Component("ofuscarDatoSEI")
public class OfuscarDatoSEIImpl implements OfuscarDatoSEI {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OfuscarDatoSEIImpl.class);

	/** The MENSAJE_RESPUESTA. */
	private static final String MENSAJE_RESPUESTA = "Respuesta: {}.";

	/** The manager. */
	@Autowired
	private MostrarDatoManager manager;
	
	/**
	 * Obtiene los datos iniciales para ofuscar el mail del usuario
	 *
	 * @param configuracionView
	 *            the configuracion view
	 * @return the MailDesafioView
	 */
	@Override
	public Respuesta<MostrarMailView> mostrarMail(MailDesafioView mailDesafioView) {
		LOGGER.info("Post OK: /getMail.");

		Respuesta<MostrarMailView> respuesta = manager.obtenerMailSinOfuscar(mailDesafioView);
		LOGGER.debug(MENSAJE_RESPUESTA, respuesta.toString());
		
		return respuesta;

	}

	
}
