package ar.com.santanderrio.obp.servicios.chat.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEstadisticaInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;

/**
 * The Interface ChatManager.
 */
public interface ChatManager {

	/**
	 * Obtener chat.
	 *
	 * @return the respuesta
	 */
	Respuesta<ChatConfigView> obtenerConfiguracion();
	
	/**
	 * Conectar chat.
	 *
	 * @return the respuesta
	 */
	Respuesta<ChatOutView> conectar(String jSessionId, OfertaComercialView oferta);
	
	/**
	 * Desconectar chat.
	 *
	 * @return the respuesta
	 */
	Respuesta<ChatOutView> desconectar();
	
	/**
	 * Enviar email.
	 *
	 * @param chatEnviarEmailInView the chat enviar email in view
	 * @param jSessionId the jsessionid
	 * @return the respuesta
	 */
	Respuesta<Void> enviarEmail(ChatEnviarEmailInView chatEnviarEmailInView, String jSessionId);
	
	/**
	 * Grabar estadistica.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadistica(ChatEstadisticaInView chatEstadisticaInView);
}
