package ar.com.santanderrio.obp.servicios.chat.sei.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.sei.ChatSEI;
import ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEstadisticaInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;

/**
 * The Class ChatSEIImpl.
 */
@Component("ChatSEI")
public class ChatSEIImpl implements ChatSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatSEIImpl.class);
	
	/** The chat manager. */
	@Autowired
	private ChatManager chatManager;
	
	
	/**
	 *  
	 * 	Obtener configuracion de chat.
	 *
	 * @return Respuesta<ChatOutView>
	 */
	public Respuesta<ChatConfigView> obtenerConfiguracion() {
		LOGGER.info("Post OK: /obtenerConfiguracion.");
		return chatManager.obtenerConfiguracion();
	}
	
	/**
	 *  
	 * 	Conectar al chat.
	 *
	 * @return Respuesta<ChatOutView>
	 */
	public Respuesta<ChatOutView> conectar(OfertaComercialView oferta, MessageContext mc){
		LOGGER.info("Post OK: /conectar.");
		HttpServletRequest request = mc.getHttpServletRequest();
        String jSessionId = request.getSession().getId();
		return chatManager.conectar(jSessionId, oferta);
	}
	

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.sei.ChatSEI#desconectarChat()
	 */
	public Respuesta<ChatOutView> desconectar(){
		LOGGER.info("Post OK: /desconectar.");
		return chatManager.desconectar();
	}
	
	/**
	 * Enviar email.
	 *
	 * @param chatEnviarEmailInView the chat enviar email in view
	 * @param mc the mc
	 * @return the respuesta
	 */
	public Respuesta<Void> enviarEmail(ChatEnviarEmailInView chatEnviarEmailInView, MessageContext mc) {
		LOGGER.info("Post OK: /enviarEmail.");
		HttpServletRequest request = mc.getHttpServletRequest();
        String jSessionID = request.getSession().getId();
		return chatManager.enviarEmail(chatEnviarEmailInView, jSessionID);
	}

	/**
	 * Grabar estadistica.
	 *
	 * @param ChatEstadisticaInView
	 * @return the respuesta
	 */
	public Respuesta<Void> grabarEstadistica(ChatEstadisticaInView chatEstadisticaInView) {
		LOGGER.info("Post OK: /grabarEstadistica.");
		return chatManager.grabarEstadistica(chatEstadisticaInView);
	}

}
