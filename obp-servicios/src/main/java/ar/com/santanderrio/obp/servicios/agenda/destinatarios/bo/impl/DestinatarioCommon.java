/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;

/**
 * The Class DestinatarioCommon.
 */
public class DestinatarioCommon {

	/** The mensaje BO. */
	@Autowired
	protected MensajeBO mensajeBO;

	/** The Constant ERROR_RECUPERAR_MENSAJE. */
	private static final String ERROR_RECUPERAR_MENSAJE = "Error al recuperar el mensaje.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinatarioCommon.class);

	/**
	 * Completa el mensaje de feedback OK.
	 *
	 * @param dto
	 *            the dto
	 * @param referenciaTitular
	 *            the referencia titular
	 * @param mensajeFeedbackOk
	 *            the mensaje feedback ok
	 * @return the string
	 */
	protected String completarMensajeFeedbackOK(ComprobanteAltaDestinatarioDTO dto, String referenciaTitular,
			String mensajeFeedbackOk) {
		if (esDTOOK(dto)) {
			return obtenerMensajeFeedbackOK(referenciaTitular, mensajeFeedbackOk);
		} else {
			return null;
		}
	}

	/**
	 * Obtiene el mensaje de feedback OK.
	 *
	 * @param referenciaTitular
	 *            the referencia titular
	 * @param mensajeFeedbackOk
	 *            the mensaje feedback ok
	 * @return the string
	 */
	private String obtenerMensajeFeedbackOK(String referenciaTitular, String mensajeFeedbackOk) {
		Mensaje msj = new Mensaje();
		try {
			msj = mensajeBO.obtenerMensajePorCodigo(mensajeFeedbackOk);
		} catch (Exception e) {
			LOGGER.error(ERROR_RECUPERAR_MENSAJE, e);
			msj = new Mensaje();
		}
		return completarParametroNuloConReferenciaTitular(msj, referenciaTitular.trim());
	}

	/**
	 * Completa el parametro nulo con la referencia o el titular.
	 *
	 * @param msj
	 *            the msj
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the string
	 */
	private String completarParametroNuloConReferenciaTitular(Mensaje msj, String referenciaTitular) {
		if (msj.getMensaje() != null) {
			return CasuisticaAgendaDestinatariosImpl.agregarReferenciaTitularAMensajeError(msj.getMensaje(),
					referenciaTitular);
		} else {
			return null;
		}
	}

	/**
	 * Es DTOOK.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean esDTOOK(ComprobanteAltaDestinatarioDTO dto) {
		return (dto.getFecha() != null) && (dto.getHora() != null) && (dto.getNroComprobante() != null);
	}
}
