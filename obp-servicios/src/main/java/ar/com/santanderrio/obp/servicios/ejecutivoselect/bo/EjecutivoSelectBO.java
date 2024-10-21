package ar.com.santanderrio.obp.servicios.ejecutivoselect.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.dto.NotificacionLlamadaPorTelDTOIn;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;

/**
 * The Interface EjecutivoSelectBO.
 */
public interface EjecutivoSelectBO {
	 
 	/**
 	 * Notificar llamada por tel.
 	 *
 	 * @param notificacionLlamadaPorTelDTOIn the notificacion llamada por tel DTO in
 	 * @return the respuesta
 	 */
 	Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(NotificacionLlamadaPorTelDTOIn notificacionLlamadaPorTelDTOIn);

 	

	/**
	 * Enviar E mail.
	 *
	 * @param chatEmailInDto the chat email in dto
	 * @return the respuesta
	 */
	Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEMail(ChatEmailInDTO chatEmailInDto);
}
