package ar.com.santanderrio.obp.servicios.chat.web.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.bo.ChatBO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatConfigDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatOutDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatSessionEntity;
import ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatConfigView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEnviarEmailInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatEstadisticaInView;
import ar.com.santanderrio.obp.servicios.chat.web.view.ChatOutView;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatManagerImpl.
 */
@Component
public class ChatManagerImpl implements ChatManager {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatManagerImpl.class);

	private static final String PASO_APERTURA = "1";
	private static final String PASO_CIERRE = "2";
	private static final String PASO_WATSON = "3";
	private static final String PASO_OPERADOR = "4";
	
	/** The sesion paramentros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The legal BO. */
	@Autowired
	protected MensajeBO mensajeBO;
	
	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The chat BO. */
	@Autowired
	private ChatBO chatBO;
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
    /** The ModuloPermiso BO. */
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;
    
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager#obtenerChat()
	 */
	@Override
	public Respuesta<ChatConfigView> obtenerConfiguracion() {
		Respuesta<ChatConfigView> respuesta;	
		
        if (moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CHAT)
                .tienePermisoDeVisibilidad()) {

			Respuesta<ChatConfigDTO> respuestaBO = chatBO.obtenerConfiguracion(sesionCliente.getCliente().getNup());
			LOGGER.info("Chat : Obtener Configuracion");
	
			if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} else {
				LOGGER.info("{} Configuracion de Chat cargada ");
	
				ChatConfigView chatOutView = chatConfigDTOToChatConfigView(respuestaBO.getRespuesta());
				
		        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		        if (credencialesMya != null) {
		        	chatOutView.setEmail(credencialesMya.getEmail());
		        }
				
				respuesta = respuestaFactory.crearRespuestaOk(ChatConfigView.class, chatOutView);
			}
        } else {
    		ChatConfigView chatOutView = new ChatConfigView();
    		chatOutView.setIsChatHabilitado(false);
			respuesta = respuestaFactory.crearRespuestaOk(ChatConfigView.class, chatOutView);
        }
		
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager#conectar(java.lang.String)
	 */
	@Override
	public Respuesta<ChatOutView> conectar(String jSessionId, OfertaComercialView oferta) {	
		Respuesta<ChatOutView> respuesta;	
		LOGGER.info("Chat : Conectar");
		
		Respuesta<ChatOutDTO> respuestaBO = chatBO.conectar(jSessionId, oferta);

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else {
			LOGGER.info("{} Conexion de Chat creada ");
			
			ChatOutDTO chatOutDto = respuestaBO.getRespuesta();			
			sesionParametros.setDetalleSesionChat(chatMessageEntityToChatSessionEntity(chatOutDto.getChatMessageEntity()));			
			ChatOutView chatOutView = chatConectarDTOToChatConectarView(chatOutDto);
			respuesta = respuestaFactory.crearRespuestaOk(ChatOutView.class, chatOutView);
		}
		if (sesionCliente.getCliente() != null && sesionCliente.getCliente().getSegmento().getOperadorEjecutivo() != null) {
			ejecutarEstadisticasSelect(respuestaBO);	
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager#desconectarChat(ar.com.santanderrio.obp.servicios.chat.web.view.ChatInView)
	 */
	@Override
	public Respuesta<ChatOutView> desconectar() {
		Respuesta<ChatOutView> respuesta;	
		DesconectarInDTO desconectarInDto =  chatSessionEntityToDesconectarInDTO(sesionParametros.getDetalleSesionChat());
		Respuesta<ChatOutDTO> respuestaBO = chatBO.desconectar(desconectarInDto);
		LOGGER.info("Chat : Desconectar");

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			LOGGER.error("{} ERROR al desconectar Session de Chat");
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else {
			LOGGER.info("{} Conexion de Chat terminada ");
			
			ChatOutDTO chatOutDto = respuestaBO.getRespuesta();
			ChatOutView chatOutView = chatConectarDTOToChatConectarView(chatOutDto);
			respuesta = respuestaFactory.crearRespuestaOk(ChatOutView.class, chatOutView);
		}
			
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager#enviarMail()
	 */
	public Respuesta<Void> enviarEmail(ChatEnviarEmailInView view, String jSessionId) {
		LOGGER.info("Chat : Enviar email");

		Respuesta<Void> respuesta;	
		ChatEmailInDTO dto = new ChatEmailInDTO();
		
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
        if (credencialesMya != null) {
        	dto.setEmail(credencialesMya.getEmail());
        } else {
    		LOGGER.info("Chat : No se pudo cargar el mail de MyA");
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_ERRONEO);
			return respuesta;
        }
		//dto.setEmail(view.getEmail());		
		dto.setMensaje(view.getMensaje());
		dto.setUserData("WASID=" + jSessionId);
		dto.setUserData2("NUP=" + sesionCliente.getCliente().getNup());

		Respuesta<Void> respuestaBO = chatBO.enviarMail(dto);		

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_ERRONEO);
		} else {
			LOGGER.info("{} Email enviado ");
			respuesta = respuestaFactory.crearRespuestaOk(null, "", CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL_EXITOSO);
		}
			
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.web.manager.ChatManager#grabarEstadistica(ar.com.santanderrio.obp.servicios.chat.web.view.ChatEstadisticaInView)
	 */
	public Respuesta<Void> grabarEstadistica(ChatEstadisticaInView chatEstadisticaInView) {
		
		String estadistica;
		if (PASO_APERTURA.equals(chatEstadisticaInView.getPaso())) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICA_CHAT_APERTURA;
		} else if (PASO_CIERRE.equals(chatEstadisticaInView.getPaso())) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICA_CHAT_CIERRE;
		} else if (PASO_WATSON.equals(chatEstadisticaInView.getPaso())) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICA_CHAT_WATSON;			
		} else if (PASO_OPERADOR.equals(chatEstadisticaInView.getPaso())) {
			estadistica = EstadisticasConstants.CODIGO_ESTADISTICA_CHAT_OPERADOR;			
		} else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
        estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaFactory.crearRespuestaOk(null, "", null);
	}

	/**
	 * Ejecutar estadisticas select.
	 * @param respuestaBO 
	 */
	private void ejecutarEstadisticasSelect(Respuesta<ChatOutDTO> respuestaBO) {
		
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
		    estadisticaManager.add(EstadisticasConstants.APERTURA_CHAT_SELECT, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
		    estadisticaManager.add(EstadisticasConstants.APERTURA_CHAT_SELECT, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

		}
	}

	/**
	 * Chat config DTO to chat out view.
	 *
	 * @param chatConfigDTO the chat config DTO
	 * @return the chat out view
	 */
	private ChatConfigView chatConfigDTOToChatConfigView(ChatConfigDTO chatConfigDTO) {
		ChatConfigView chatOutView = new ChatConfigView();
		chatOutView.setIsChatHabilitado(chatConfigDTO.getIsChatHabilitado());
		
		if (chatConfigDTO.getIsChatHabilitado()) {
			chatOutView.setHoraDesde(chatConfigDTO.getHoraDesde());
			chatOutView.setHoraHasta(chatConfigDTO.getHoraHasta());
			chatOutView.setHoraHastaMsj(chatConfigDTO.getHoraHastaMsj());
			chatOutView.setMailUrl(chatConfigDTO.getMailUrl());
			chatOutView.setDataUrl(chatConfigDTO.getDataUrl());
			chatOutView.setCsrfUrl(chatConfigDTO.getCsrfUrl());
			chatOutView.setCloseTimeout(chatConfigDTO.getCloseTimeout());
			chatOutView.setConnectionTimeout(chatConfigDTO.getConnectionTimeout());
			chatOutView.setSessionTimeout(chatConfigDTO.getSessionTimeout());
			chatOutView.setWatsonNickname(chatConfigDTO.getWatsonNickname());
			chatOutView.setMensajeReconectarSesionExpirada(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_RECONECTAR_SESION_EXPIRADA).getMensaje());
			chatOutView.setMensajeEnvioEmail(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ENVIO_MAIL).getMensaje());
			chatOutView.setMensajeConexion(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_SIN_OPERADORES_CONEXION).getMensaje());
			chatOutView.setMensajeErrorGenerico(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_CHAT_MENSAJE_ERROR_GENERICO).getMensaje());
		}
		return chatOutView;
	}
	
	/**
	 * Chat conectar DTO to chat conectar view.
	 *
	 * @param chatConectarDTO the chat conectar DTO
	 * @return the chat conectar view
	 */
	private ChatOutView chatConectarDTOToChatConectarView(ChatOutDTO chatConectarDTO) {
		ChatOutView chatConectarView = new ChatOutView();
		
		chatConectarView.setChatId(chatConectarDTO.getChatMessageEntity().getChatId());
		chatConectarView.setUserId(chatConectarDTO.getChatMessageEntity().getUserId());
		chatConectarView.setSecureKey(chatConectarDTO.getChatMessageEntity().getSecureKey());
		chatConectarView.setAlias(chatConectarDTO.getChatMessageEntity().getAlias());
		chatConectarView.setStatusCode(chatConectarDTO.getChatMessageEntity().getStatusCode());
		chatConectarView.setChatEnded(chatConectarDTO.getChatMessageEntity().getChatEnded());
		chatConectarView.setNextPosition(chatConectarDTO.getChatMessageEntity().getNextPosition());
		chatConectarView.setMessages(chatConectarDTO.getChatMessageEntity().getMessages());
		
		return chatConectarView;
	}
	
	/**
	 * Chat session entity to desconectar in DTO.
	 *
	 * @param sesionChat the sesion chat
	 * @return the desconectar in DTO
	 */
	private DesconectarInDTO chatSessionEntityToDesconectarInDTO(ChatSessionEntity sesionChat) {
		DesconectarInDTO desconectarInDto = new DesconectarInDTO();
		
		desconectarInDto.setAlias(sesionChat.getAlias());
		desconectarInDto.setChatId(sesionChat.getChatId());
		desconectarInDto.setSecureKey(sesionChat.getSecureKey());
		desconectarInDto.setUserId(sesionChat.getUserId());
		
		return desconectarInDto;
	}

	/**
	 * Response chat connect entity to chat session entity.
	 *
	 * @param response the response
	 * @return the chat session entity
	 */
	private ChatSessionEntity chatMessageEntityToChatSessionEntity(ChatResponseEntity response) {
		ChatSessionEntity sesionChat = new ChatSessionEntity();
		
		sesionChat.setAlias(response.getAlias());
		sesionChat.setChatId(response.getChatId());
		sesionChat.setSecureKey(response.getSecureKey());
		sesionChat.setUserId(response.getUserId());
		
		return sesionChat;
	}
	
}
