/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.bo.impl;

import java.text.ParseException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.bo.ChatBO;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatConfigDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatOutDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.ConectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.dto.DesconectarInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatConfigEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatResponseEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatTokenEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.OfertaComercialView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;

/**
 * The Class ChatBOImpl.
 */
@Component
public class ChatBOImpl implements ChatBO {
	
	/** The Constant EMAIL_TENANT_NAME. */
	private static final String EMAIL_TENANT_NAME = "Resources";

    /** The Constant EMAIL_SUBJECT. */
    private static final String EMAIL_SUBJECT = "Mail origen Online Banking";

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatBOImpl.class);

	/** The chat DAO. */
	@Autowired
	private ChatDAO chatDAO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The sesion paramentros. */
	@Autowired
	private SesionParametros sesionParametros;
	

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.bo.ChatBO#obtenerChat()
	 */
	@Override
	public Respuesta<ChatConfigDTO> obtenerConfiguracion(String nup) {
		Respuesta<ChatConfigDTO> respuesta;
		
		try {
			ChatConfigEntity chatConfigEntity = chatDAO.obtenerConfiguracion(nup);
			ChatConfigDTO chatConfigDTO = chatConfigEntityToChatConfigDTO(chatConfigEntity);
			respuesta = respuestaFactory.crearRespuestaOk(ChatConfigDTO.class, chatConfigDTO);
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.bo.ChatBO#conectarChat(ar.com.santanderrio.obp.servicios.chat.dto.ChatInDTO)
	 */
	@Override
	public Respuesta<ChatOutDTO> conectar(String jSessionId, OfertaComercialView oferta) {
		Respuesta<ChatOutDTO> respuesta;
		
		try {
			ChatTokenEntity token = generarTokenEntity(jSessionId, oferta);
			ChatResponseEntity response = chatDAO.conectarGenesys(generarRequestConexion(token, jSessionId));
			
			if (response.getStatusCode() == 0) {
				ChatOutDTO chatOutDTO = new ChatOutDTO();
				chatOutDTO.setChatMessageEntity(response);
				respuesta = respuestaFactory.crearRespuestaOk(ChatOutDTO.class, chatOutDTO);
			} else {
				LOGGER.info("No se pudo conectar con genesys");
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.bo.ChatBO#desconectarChat(ar.com.santanderrio.obp.servicios.chat.dto.ChatInDTO)
	 */
	@Override
	public Respuesta<ChatOutDTO> desconectar(DesconectarInDTO desconectarInDto) {

		Respuesta<ChatOutDTO> respuesta;
		
		try {
			ChatResponseEntity response = chatDAO.desconectarGenesys(desconectarInDto);

			if (response.getStatusCode() == 0) {
				ChatOutDTO chatOutDTO = new ChatOutDTO();
				chatOutDTO.setChatMessageEntity(response);
				respuesta = respuestaFactory.crearRespuestaOk(ChatOutDTO.class, chatOutDTO);
			} else {
				LOGGER.info("No se pudo desconectar de genesys");
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuesta;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.chat.bo.ChatBO#enviarMail(ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO)
	 */
	@Override
	public Respuesta<Void> enviarMail(ChatEmailInDTO chatEmailInDto) {
		Respuesta<Void> respuesta;
		
		try {
			ChatEmailRequestEntity request = generarRequestEmail(chatEmailInDto);
			ChatEmailResponseEntity response = chatDAO.enviarEmailGenesys(request);

			if (response.getInteractionId() != null) {
				respuesta = respuestaFactory.crearRespuestaOk(Void.class, null);
			} else {
				LOGGER.info("No se pudo enviar el mail a genesys");
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO", e);
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuesta;
	}

	/**
	 * Generar request email.
	 *
	 * @param chatEmailInDTO the chat email in DTO
	 * @return the request chat email entity
	 */
	private ChatEmailRequestEntity generarRequestEmail(ChatEmailInDTO chatEmailInDTO) {
		ChatEmailRequestEntity request = new ChatEmailRequestEntity();

		request.setFirstName(sesionCliente.getCliente().getNombre());
		request.setLastName(sesionCliente.getCliente().getApellido1());
		request.setFromAddress(chatEmailInDTO.getEmail());
		request.setSubject(EMAIL_SUBJECT);
		request.setTenantName(EMAIL_TENANT_NAME);
		request.setText(chatEmailInDTO.getMensaje());
		request.setUserData(chatEmailInDTO.getUserData()); 
		request.setUserData2(chatEmailInDTO.getUserData2());

		return request;
	}	

	/**
	 * Chat config entity to chat config DTO.
	 *
	 * @param chatConfigEntity the chat config entity
	 * @return the chat config DTO
	 */
	private ChatConfigDTO chatConfigEntityToChatConfigDTO(ChatConfigEntity chatConfigEntity) {
		ChatConfigDTO chatConfigDTO = new ChatConfigDTO();
		
		chatConfigDTO.setIsChatHabilitado(chatConfigEntity.getIsChatHabilitado());
		chatConfigDTO.setHoraDesde(chatConfigEntity.getHoraDesde());
		chatConfigDTO.setHoraHasta(chatConfigEntity.getHoraHasta());
		chatConfigDTO.setHoraHastaMsj(chatConfigEntity.getHoraHastaMsj());
		chatConfigDTO.setMailUrl(chatConfigEntity.getMailUrl());
		chatConfigDTO.setDataUrl(chatConfigEntity.getDataUrl());
		chatConfigDTO.setCsrfUrl(chatConfigEntity.getCsrfUrl());
		chatConfigDTO.setCloseTimeout(chatConfigEntity.getCloseTimeout());
		chatConfigDTO.setConnectionTimeout(chatConfigEntity.getConnectionTimeout());
		chatConfigDTO.setSessionTimeout(chatConfigEntity.getSessionTimeout());
		chatConfigDTO.setWatsonNickname(chatConfigEntity.getWatsonNickname());

		return chatConfigDTO;
	}
	
	
	/**
	 * Generar token entity.
	 *
	 * @param jSessionID
	 *            the j session ID
	 * @return the chat token entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ChatTokenEntity generarTokenEntity(String jSessionID, OfertaComercialView oferta) throws DAOException {
		ChatTokenEntity token = new ChatTokenEntity();

		Cliente cliente = sesionCliente.getCliente();
		
		String numeroCuenta = "";
        String sucursalCuenta = "";
		String tarjetaVisa = "";
		String tarjetaAmex = "";
		String tarjetaDebito = "";
		
		String id = "";
		String idInterno = "";
		String tipoOferta = "";
		String operacionGoto = "";
		
		if (oferta != null) {
			id = oferta.getId() != null ? oferta.getId() :"";
			idInterno = oferta.getIdInterno() != null ? oferta.getIdInterno() :"";
			tipoOferta = oferta.getTipoOferta() != null ? oferta.getTipoOferta() :"";
			if (oferta.getContenidoLink() !=null) {
				operacionGoto = oferta.getContenidoLink().getUrl() != null ? oferta.getContenidoLink().getUrl() :"";
			}else {
				operacionGoto = "";
			}				
		}
		
		
		for (Iterator<Cuenta> it = cliente.getCuentas().iterator(); it.hasNext();) {
			Cuenta cuenta = it.next();
			
			if ("".equals(numeroCuenta) && "".equals(sucursalCuenta)) {
				numeroCuenta = cuenta.obtenerNroCuentaFormateado();
			}
			
			if (cuenta.esTarjetaDeCredito()) {
				if (cuenta.esTitularTarjetaVisa()) {
					tarjetaVisa = formatearNumeroTarjetaCredito(cuenta);
				} else if(cuenta.esTitularTarjetaAmex()) {
					tarjetaAmex = formatearNumeroTarjetaCredito(cuenta);
				}
			} else if(cuenta.esBanelco() && cuenta.esTitular()) {
				tarjetaDebito = cuenta.getNroTarjetaCredito();
			}
		}
		
        CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		
		token.setNup(cliente.getNup());
		token.setJSessionID(jSessionID);
		token.setDocumento(ISBANStringUtils.sacarCerosYBlancosIzq(cliente.getDni()));
		token.setApellido(cliente.getApellido1());
		token.setNombre(cliente.getNombre());
		token.setSegmento(sesionCliente.getCliente().getSegmento().getNombre());
        token.setMail(credencialesMya != null ? credencialesMya.getEmail() : "");
		try {
			token.setFechaNacimiento(ISBANStringUtils.formatearFecha(ISBANStringUtils.parsearFechaIATX(cliente.getFechaNacimiento())));
		} catch (ParseException e) {
			LOGGER.error("No se pudo formatear la fecha de nacimiento", e);
			throw new DAOException("No se pudo formatear la fecha de nacimiento");
		}
		token.setTarjetaVisa(tarjetaVisa);
		token.setTarjetaAmex(tarjetaAmex);
		token.setTarjetaDebito(ISBANStringUtils.sacarCerosYBlancosIzq(tarjetaDebito));
		token.setNumeroCuenta(numeroCuenta);
		token.setIntencionGoto(operacionGoto);
		token.setIdGEC(id);
		token.setIdInternoGEC(idInterno);
		token.setTipoOfertaGEC(tipoOferta);

		LOGGER.info("Token CHAT sin encriptar = ", token.toString());
		return token;
	}
	
	
	/**
	 * Generar request conexion.
	 *
	 * @param token
	 *            the token
	 * @param jSessionId
	 *            the j session id
	 * @return the request chat connect entity
	 */
	private ConectarInDTO generarRequestConexion(ChatTokenEntity token, String jSessionId) {
		ConectarInDTO request = new ConectarInDTO();

		request.setNombre(sesionCliente.getCliente().getNombre());
		request.setApellido(sesionCliente.getCliente().getApellido1());
		request.setNup(sesionCliente.getCliente().getNup());
		request.setJSessionId(jSessionId);
		request.setToken(token);

		return request;
	}
	
	/**
	 * Formatear numero tarjeta credito.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String formatearNumeroTarjetaCredito(Cuenta cuenta) {
		String numeroTarjeta = cuenta.getNroTarjetaCredito().trim();
		int l = numeroTarjeta.length();
		
        if (numeroTarjeta.length() < 16)
            return numeroTarjeta;
        
		if (cuenta.esTitularTarjetaVisa()) {
            numeroTarjeta = numeroTarjeta.substring(l - 16, l - 12) + "-" + 
            				numeroTarjeta.substring(l - 12, l - 8) + "-" + 
            				numeroTarjeta.substring(l - 8, l - 4) + "-" +
            				numeroTarjeta.substring(l - 4, l);

		} else if(cuenta.esTitularTarjetaAmex()) {
            return	numeroTarjeta.substring(l - 16, l - 12) + "-" + 
            		numeroTarjeta.substring(l - 12, l - 6) + "-" + 
            		numeroTarjeta.substring(l - 6, l - 1);

		}
		
		return numeroTarjeta;
	}
}
