package ar.com.santanderrio.obp.servicios.ejecutivoselect.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chat.bo.impl.ChatBOImpl;
import ar.com.santanderrio.obp.servicios.chat.dao.ChatDAO;
import ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailRequestEntity;
import ar.com.santanderrio.obp.servicios.chat.entities.ChatEmailResponseEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.bo.EjecutivoSelectBO;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.dao.EjecutivoSelectDAO;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.dto.NotificacionLlamadaPorTelDTOIn;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorInEntity;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.entities.ConsultaTelefonoOperadorOutEntity;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.NotificacionLLamadaPorTelOutView;
import ar.com.santanderrio.obp.servicios.ejecutivoselect.view.OperadorEjecutivoEnviarMailOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class EjecutivoSelectBOImpl.
 */
@Component
public class EjecutivoSelectBOImpl implements EjecutivoSelectBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChatBOImpl.class);
	
	/** The Constant EMAIL_TENANT_NAME. */
	private static final String EMAIL_TENANT_NAME = "Resources";

    /** The Constant EMAIL_SUBJECT. */
    private static final String EMAIL_SUBJECT = "Mail origen Online Banking";
	
	/** The ejecutivo select DAO. */
	@Autowired
	private EjecutivoSelectDAO ejecutivoSelectDAO;
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;
	
	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	/** The chat DAO. */
	@Autowired
	private ChatDAO chatDAO;
	
	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.ejecutivoselect.bo.EjecutivoSelectBO#
	 * notificarLlamadaPorTel(ar.com.santanderrio.obp.servicios.ejecutivoselect.
	 * dto.NotificacionLlamadaPorTelDTOIn)
	 */
	public Respuesta<NotificacionLLamadaPorTelOutView> notificarLlamadaPorTel(
			NotificacionLlamadaPorTelDTOIn notificacionLlamadaPorTelDTOIn) {
		ConsultaTelefonoOperadorInEntity entityIn = new ConsultaTelefonoOperadorInEntity();
		ConsultaTelefonoOperadorOutEntity respuestaService = null;
		NotificacionLLamadaPorTelOutView respuesta = new NotificacionLLamadaPorTelOutView();
		entityIn.setSessionId(notificacionLlamadaPorTelDTOIn.getSessionId());
		entityIn.setNupCliente(sesionCliente.getCliente().getNup());
		entityIn.setTelefono("0");
		entityIn.setCanal("MAPP");
		entityIn.setUsuarioRacf(sesionCliente.getCliente().getUsuarioRacf());
		entityIn.setPasswordRacf(sesionCliente.getCliente().getPasswordRacf());
		entityIn.setCodigoAccion("0");
		entityIn.setNroDoc(sesionCliente.getCliente().getDni());
		entityIn.setTipoPersona(sesionCliente.getCliente().getTipoPersona());
		try {
			respuestaService = ejecutivoSelectDAO.notificarLlamadaPorTel(entityIn);
			if (respuestaService != null && "0".equals(respuestaService.getResultado())) {
				estadisticaManager.add(EstadisticasConstants.NOTIFICACION_LLAMADA_OPERADOR_EJECTIVO, "1");
				if(respuestaService.getTelefenoOperador().contains(",")) {
					respuesta.setTelefonoOperador(respuestaService.getTelefenoOperador().replaceAll(",,", " %2C %2C "));
				} else {
					respuesta.setTelefonoOperador(respuestaService.getTelefenoOperador());	
				}
				
				return respuestaFactory.crearRespuestaOk(NotificacionLLamadaPorTelOutView.class, respuesta);
			} else {
				estadisticaManager.add(EstadisticasConstants.NOTIFICACION_LLAMADA_OPERADOR_EJECTIVO, "2");

				return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
						TipoError.ERROR_OPERADOR_TEL_CON_REINTENTOS.toString(),
						CodigoMensajeConstantes.CODIGO_MENSAJE_LLAMADA_POR_TEL_EJECUTIVO_ERROR);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			estadisticaManager.add(EstadisticasConstants.NOTIFICACION_LLAMADA_OPERADOR_EJECTIVO, "2");
			return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(
					TipoError.ERROR_OPERADOR_TEL_SIN_REINTENTOS.toString(),
					CodigoMensajeConstantes.CODIGO_MENSAJE_LLAMADA_POR_TEL_EJECUTIVO_ERROR_2);

		}

	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.ejecutivoselect.bo.EjecutivoSelectBO#enviarEMail(ar.com.santanderrio.obp.servicios.chat.dto.ChatEmailInDTO)
	 */
	@Override
	public Respuesta<OperadorEjecutivoEnviarMailOutView> enviarEMail(ChatEmailInDTO chatEmailInDto) {
		Respuesta<OperadorEjecutivoEnviarMailOutView> respuesta;
		
		try {
			ChatEmailRequestEntity request = generarRequestEmail(chatEmailInDto);
			ChatEmailResponseEntity response = chatDAO.enviarEmailGenesys(request);

			if (response.getInteractionId() != null) {
				OperadorEjecutivoEnviarMailOutView outView = new OperadorEjecutivoEnviarMailOutView();
				outView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_GENESYS_EMAIL_OK).getMensaje());
				estadisticaManager.add(EstadisticasConstants.OPERADOR_EJECUTIVO_ENVIAR_EMAIL, "1");
				respuesta = respuestaFactory.crearRespuestaOk(OperadorEjecutivoEnviarMailOutView.class, outView);
			} else {
				LOGGER.info("No se pudo enviar el mail a genesys");
				estadisticaManager.add(EstadisticasConstants.OPERADOR_EJECUTIVO_ENVIAR_EMAIL, "2");
				respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENESYS_EMAIL_OPERADOR);
			}
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO", e);
			estadisticaManager.add(EstadisticasConstants.OPERADOR_EJECUTIVO_ENVIAR_EMAIL, "2");
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.CODIGO_ERROR_GENESYS_EMAIL_OPERADOR_ARQUITECTURA);
		}
		return respuesta;
	}
	
	/**
	 * Generar request email.
	 *
	 * @param chatEmailInDTO the chat email in DTO
	 * @return the chat email request entity
	 */
	private ChatEmailRequestEntity generarRequestEmail(ChatEmailInDTO chatEmailInDTO) {
		ChatEmailRequestEntity request = new ChatEmailRequestEntity();

		request.setFirstName(sesionCliente.getCliente().getNombre());
		request.setLastName(sesionCliente.getCliente().getApellido1());
		request.setFromAddress(chatEmailInDTO.getEmail());
		 if ( !ISBANStringUtils.isEmptyOrNull(chatEmailInDTO.getSubject())) {
			 request.setSubject(EMAIL_SUBJECT + " "+ chatEmailInDTO.getSubject());
		 } else {
			 request.setSubject(EMAIL_SUBJECT);
		 }
		request.setTenantName(EMAIL_TENANT_NAME);
		request.setText(chatEmailInDTO.getMensaje());
		request.setUserData(chatEmailInDTO.getUserData()); 
		request.setUserData2(chatEmailInDTO.getUserData2());
		if (chatEmailInDTO.getMailBox() != null) {
			request.setMailBox(chatEmailInDTO.getMailBox());
		}

		return request;
	}	

}
