/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatConfigDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatOutDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;

// TODO: Auto-generated Javadoc
/**
 * The Interface ChatBO.
 *
 * @author Federico_Puente
 */
public interface ChatBO {

	/**
	 * Obtener chat.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<ChatConfigDTO> obtenerConfiguracion(String nup);
	
	/**
	 * Conectar chat.
	 *
	 * @param jSessionID
	 *            the j session ID
	 * @return the respuesta
	 */
	Respuesta<ChatOutDTO> conectar(String jSessionID, OfertaComercialView oferta);
	
	/**
	 * Desconectar chat.
	 *
	 * @param desconectarInDto the desconectar in dto
	 * @return the respuesta
	 */
	Respuesta<ChatOutDTO> desconectar(DesconectarInDTO desconectarInDto);
	
	/**
	 * Enviar mail.
	 *
	 * @param chatEmailInDto the chat email in dto
	 * @return the respuesta
	 */
	Respuesta<Void> enviarMail(ChatEmailInDTO chatEmailInDto);
	
}
