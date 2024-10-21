/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioRSADTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class AgendaDestinatariosRCAManagerImpl.
 */
@Component
public class AgendaDestinatariosRCAManagerImpl implements AgendaDestinatariosRCAManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDestinatariosRCAManagerImpl.class);

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	@Autowired
	private MensajeBO mensajeBO;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.AGENDADESTINATARIOS}")
	private String valorDesafio;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;
	
	@Autowired
	private SesionAgendaDestinatarios sesionAgenda;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl.
	 * AgendaDestinatariosRCAManager#ejecutarValidacionRSA(ar.com.santanderrio.
	 * obp.
	 * servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView,
	 * ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.
	 * TipoOperacionACTAGEDESTEnum,
	 * ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.
	 * TipoAgendaEnum)
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> ejecutarValidacionRSA(
			ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum tipoOperacion,
			TipoAgendaEnum tipoAgenda) {
		LOGGER.info("Se ejecuta la validacion por RSA");
		AutentificacionDTO autentificacionDTO;

		Respuesta<ConfirmacionAltaDestinatarioView> estadoDesafio = autentificacionManager
				.verificarEstadoDesafio(confirmacionAltaDestinatario.getDesafio(), Integer.parseInt(valorDesafio));
		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = confirmacionAltaDestinatario.getDesafio();
		} else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = generarAutentificacionDTO(confirmacionAltaDestinatario, tipoOperacion, tipoAgenda);
		} else {
			return estadoDesafio;
		}
		// Transformacion respuesta RSA
		Respuesta<AutentificacionDTO> rstaAutentificacionDTO = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		if(!EstadoRespuesta.OK.equals(rstaAutentificacionDTO.getEstadoRespuesta())){
			if(rstaAutentificacionDTO.getRespuesta() != null && rstaAutentificacionDTO.getRespuesta().getBloquearUsuario()){
				LOGGER.info("Inicio de bloqueo de usuario.");
				Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
				if(EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())){      
					return respuestaFactory.crearRespuestaError(ConfirmacionAltaDestinatarioView.class, StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
			                    CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
				} else{
					LOGGER.info("Error al bloquear el usuario");
					return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.DENY_RSA, CodigoMensajeConstantes.DENY_RSA);
				}
			} else if(TipoError.DENY_RSA.getDescripcion().equals(rstaAutentificacionDTO.getItemsMensajeRespuesta().get(0).getTipoError())){
				rstaAutentificacionDTO.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DENY_RSA_AGENDA_BLACK_LIST).getMensaje());
			}
		}
		confirmacionAltaDestinatario.setDesafio(rstaAutentificacionDTO.getRespuesta());
		return respuestaFactory.transformar(confirmacionAltaDestinatario, rstaAutentificacionDTO);
	}

	/**
	 * Generar autentificacion DTO.
	 *
	 * @param confirmacionAltaDestinatario
	 *            the confirmacion alta destinatario
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param tipoAgenda
	 *            the tipo agenda
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO generarAutentificacionDTO(ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario,
			TipoOperacionACTAGEDESTEnum tipoOperacion, TipoAgendaEnum tipoAgenda) {
		LOGGER.info("Se carga la informacion de autentificationDTO");
		Integer operacion = Integer.parseInt(valorDesafio);
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

		// Carga de estadisticas
		if (TipoOperacionACTAGEDESTEnum.ALTA.equals(tipoOperacion)) {
			autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
					EstadisticasConstants.ALTA_DESTINATARIO_SOLICITUD_COORDENADAS);
			autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
					EstadisticasConstants.ALTA_DESTINATARIO_VALIDACION_COORDENADAS);
			autentificacionDTO
					.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ALTA_DESTINATARIO_SOLICITUD_TOKEN);
			autentificacionDTO
					.setCodigoEstadisticaValidacionToken(EstadisticasConstants.ALTA_DESTINATARIO_VALIDACION_TOKEN);
			autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.ALTA_DESTINATARIO_SIN_DESAFIOS);
		} else {
			autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
					EstadisticasConstants.EDICION_DESTINATARIO_SOLICITUD_COORDENADAS);
			autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
					EstadisticasConstants.EDICION_DESTINATARIO_VALIDACION_COORDENADAS);
			autentificacionDTO
					.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.EDICION_DESTINATARIO_SOLICITUD_TOKEN);
			autentificacionDTO
					.setCodigoEstadisticaValidacionToken(EstadisticasConstants.EDICION_DESTINATARIO_VALIDACION_TOKEN);
			autentificacionDTO.setCodigoEstadisticaSinDesafios(EstadisticasConstants.EDICION_DESTINATARIO_SIN_DESAFIOS);
		}

		// Datos informados a RSA
		TipoAgendaEnum tipoAgendaDiferenciado = tipoAgenda;
		if (TipoAgendaEnum.AGENDA_ALIAS.equals(tipoAgenda)) {
			tipoAgendaDiferenciado = confirmacionAltaDestinatario.getIsRio() ? TipoAgendaEnum.AGENDA_RIO
					: TipoAgendaEnum.AGENDA_OTROS_BANCOS;
		}

		autentificacionDTO
				.setRsaDTO(new AgendaDestinatarioRSADTO(false, confirmacionAltaDestinatario.obtenerReferenciaOTitular(),
						confirmacionAltaDestinatario.obtenerNumeroAgendado(), tipoAgendaDiferenciado, confirmacionAltaDestinatario.getNumeroDocumento()));
		autentificacionDTO.getRsaDTO().setValidarBloqueo(Boolean.TRUE);
		return autentificacionDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
	 * AgendaDestinatariosRCAManager#validarSiPoseeSegundoFactorAutenticacion()
	 */
	@Override
	public Respuesta<Boolean> validarSiPoseeSegundoFactorAutenticacion() {
		AutentificacionDTO autenticacionDTO = new AutentificacionDTO();
		autenticacionDTO.setOperacion(Integer.parseInt(valorDesafio));
		return autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(autenticacionDTO);
	}

}
