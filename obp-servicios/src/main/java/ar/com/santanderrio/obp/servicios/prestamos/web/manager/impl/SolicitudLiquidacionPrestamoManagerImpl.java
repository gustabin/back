package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamosRsaBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionPrestamoBaseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosBffMapper;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosEncoladosMapper;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.LiquidacionPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoInView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoOutView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.SolicitudLiquidacionPrestamoManager;

@Component
public class SolicitudLiquidacionPrestamoManagerImpl implements SolicitudLiquidacionPrestamoManager {

	private static final String ERROR_RETRY_LATER = "Por favor, volvé a intentar en unos minutos.";

	private static final String TO_BE_CHALLENGED = "ToBeChallenged";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SolicitudLiquidacionPrestamoManagerImpl.class);

	private static final String FASE_ENCOLAR = "1";

	private static final String FASE_LIQUIDAR = "2";

	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mya BO. */
	@Autowired
	protected MyaBO myaBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta Factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private PrestamoBO prestamoBO;

	@Autowired
	PrestamoManagerBO prestamoManagerBO;

	@Autowired
	private PrestamosBffMapper prestamoBffMapper;

	@Autowired
	private PrestamosEncoladosMapper prestamosEncoladosMapper;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.PRESTAMOS}")
	private String valorDesafioPrestamo;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	@Autowired
	private PrestamosRsaBO prestamosRsaBO;

	/**
	 * Permite solicitar un nuevo prestamo utilizando el bff. Según los parametros
	 * de entrada y el estado de RSA se encarga de encolar y liquidar el prestamo.
	 * 
	 * <h3>Condiciones y flujo:</h3>
	 * <ul>
	 * <li>estado de RSA == ALLOW -> encola y liquida un préstamo. Responde con
	 * estado PayOff junto a los datos del préstamo.</li>
	 * 
	 * <li>estado de RSA == CHALLENGE y tokenPrisma == null -> Responde
	 * ToBeChallenged para que desde el frontend se envié el token prisma.</li>
	 * 
	 * <li>estado de RSA == CHALLENGE y tokenPrisma != null -> valida el token,
	 * notifica a RSA y encola el prestamo. respondiendo Enqueue.</li>
	 * 
	 * <li>estado de RSA = DENY -> se rechaza la solicitud de préstamo y responde
	 * Denied</li>
	 * </ul>
	 * 
	 * @param solicitudPrestamoInView Datos de la solicitud del préstamo
	 * @return Respuesta con el estado del flujo y datos del prestamo
	 */
	@SuppressWarnings("unchecked")
	public Respuesta<SolicitudPrestamoOutView> solicitar(SolicitudPrestamoInView solicitudPrestamoInView) {

		SolicitudPrestamoDTO solicitud = solicitudPrestamoInView.getSolicitud();
		solicitud.setNup(sesionCliente.getCliente().getNup());
		LOGGER.info("SE VA A SOLICITAR UN PRESTAMO PARA EL NUP: " + solicitudPrestamoInView.getSolicitud().getNup()
				+ " DATOS DEL PRESTAMO: ", solicitudPrestamoInView);
		LOGGER.info("SID: {}", PrestamosUtils.getSessionId());

		String tokenPrisma = solicitudPrestamoInView.getTokenPrisma();

		boolean existeDesafioEnCurso = sesionParametros.isExisteDesafioEnCurso();

		LOGGER.info("--SOLICITAR-- EXISTE DESAFIO PARA EL NUP: " + solicitudPrestamoInView.getSolicitud().getNup()
				+ "?: " + existeDesafioEnCurso);

		Respuesta<SimuladorPrestamoDTO> respuestaRSA = prestamosRsaBO.validarRsa(solicitud, tokenPrisma, FASE_ENCOLAR,
				existeDesafioEnCurso);

		LOGGER.info("--SOLICITAR-- RESPUESTA RSA PARA EL NUP: " + solicitudPrestamoInView.getSolicitud().getNup() + ": "
				+ respuestaRSA);

		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			if (existeDesafioEnCurso) {
				// OK por CHALLENGE verificado con token prisma
				LOGGER.info("--SOLICITAR-- LA RTA DE RSA PARA EL NUP: "
						+ solicitudPrestamoInView.getSolicitud().getNup() + " DIO OK POR CHALLENGE: ");
				return encolar(solicitud);
			}
			LOGGER.info("--SOLICITAR-- LA RTA DE RSA PARA EL NUP: " + solicitudPrestamoInView.getSolicitud().getNup()
					+ " DIO OK POR ALLOW: ");
			// OK por ALLOW
			return encolarYLiquidar(solicitud, solicitud.getNup());
		}
		if (EstadoRespuesta.WARNING.equals(respuestaRSA.getEstadoRespuesta())
				&& noIngresoTokenPrisma(solicitudPrestamoInView)) {
			return respuestaFactory.crearRespuestaWarning(new SolicitudPrestamoOutView(TO_BE_CHALLENGED),
					respuestaRSA.getItemsMensajeRespuesta());
		}
		if (EstadoRespuesta.WARNING.equals(respuestaRSA.getEstadoRespuesta())
				&& ingresoTokenPrisma(solicitudPrestamoInView)) {
			return respuestaFactory.crearRespuestaWarning(new SolicitudPrestamoOutView(TO_BE_CHALLENGED),
					respuestaRSA.getItemsMensajeRespuesta());
		}

		if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta()))
			return armarRespuestaErrorRSA(respuestaRSA);

		LOGGER.error("--SOLICITAR-- SE PRODUJO UN ERROR AL SOLICITAR UN PRESTAMO PARA EL NUP {}",
				solicitudPrestamoInView.getSolicitud().getNup());
		String codigoMensajeError = CodigoMensajeConstantes.ERROR_GENERICO;
		TipoError tipoError = TipoError.ERROR_GENERICO;
		return respuestaFactory.crearRespuestaError(ERROR_RETRY_LATER, tipoError, codigoMensajeError);
	}

	/**
	 * Permite liquidar un prestamo que se encuentra encolado utilizando el bff.
	 *
	 * @param liquidacionPrestamoInView Datos del prestamo a liquidar.
	 * @return Respuesta con el estado del flujo y el prestamo liquidado.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Respuesta<SolicitudPrestamoOutView> liquidar(LiquidacionPrestamoInView liquidacionPrestamoInView) {
		LOGGER.info(
				"SE VA A LIQUIDAR UN PRESTAMO PARA EL NUP: " + liquidacionPrestamoInView.getNup() + " TIPO OFERTA: ",
				liquidacionPrestamoInView.getTipoOferta());
		LOGGER.info("SID: {}", PrestamosUtils.getSessionId());

		String tokenPrisma = liquidacionPrestamoInView.getTokenPrisma();
		boolean existeDesafioEnCurso = sesionParametros.isExisteDesafioEnCurso();

		PrestamoEncoladoEntity prestamoEncoladoEntity = new PrestamoEncoladoEntity();
		try {
			prestamoEncoladoEntity = obtenerPrestamoEncolado(liquidacionPrestamoInView);
		} catch (DAOException e) {
			LOGGER.info("--LIQUIDAR-- SE PRODUJO UN ERROR AL OBTENER EL PRESTAMO ENCOLADO: "
					+ liquidacionPrestamoInView.getNup());
			String codigoMensajeError = CodigoMensajeConstantes.ERROR_GENERICO;
			TipoError tipoError = TipoError.ERROR_GENERICO;
			return respuestaFactory.crearRespuestaError(ERROR_RETRY_LATER, tipoError, codigoMensajeError);
		}

		// se mapea a solicitudPrestamoDTO
		SolicitudPrestamoDTO solicitudPrestamoDTO = prestamosEncoladosMapper.toDTO(prestamoEncoladoEntity,
				liquidacionPrestamoInView.getNroCuenta(), liquidacionPrestamoInView.getDisponible());
		solicitudPrestamoDTO.setNup(sesionCliente.getCliente().getNup());

		// si esta en true, ya se valido RSA en el solicitar y el prestamo quedo
		// encolado, por lo que no es necesario volver a ir a RSA.
		if (sesionParametros.getDesafioPrestamosActivo()) {
			return validarSMSyLiquidar(liquidacionPrestamoInView, solicitudPrestamoDTO);
		}

		// todavia no se ejecuto RSA.
		Respuesta<SimuladorPrestamoDTO> respuestaRSA = prestamosRsaBO.validarRsa(solicitudPrestamoDTO, tokenPrisma,
				FASE_LIQUIDAR, existeDesafioEnCurso);

		LOGGER.info(
				"--LIQUIDAR-- RESPUESTA RSA PARA EL NUP: " + liquidacionPrestamoInView.getNup() + ": " + respuestaRSA);

		// ifs para RSA
		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			if (existeDesafioEnCurso) {
				// OK por CHALLENGE verificado con token prisma. Se valida el SMS
				LOGGER.info("--LIQUIDAR-- LA RTA DE RSA PARA EL NUP: " + liquidacionPrestamoInView.getNup()
						+ " DIO OK POR CHALLENGE: ");
				sesionParametros.setDesafioPrestamosActivo(true);
				return respuestaFactory.crearRespuestaWarning(new SolicitudPrestamoOutView("Need2F"),
						respuestaRSA.getItemsMensajeRespuesta());

			}
			// OK por ALLOW (por si cambia el estado dentro de las 48hs donde si liquida el
			// prestamo automaticamente)
			LOGGER.info("--LIQUIDAR-- LA RTA DE RSA PARA EL NUP: " + liquidacionPrestamoInView.getNup()
					+ " DIO OK POR ALLOW: ");
			return liquidar(solicitudPrestamoDTO, solicitudPrestamoDTO.getNup());
		}

		if (EstadoRespuesta.WARNING.equals(respuestaRSA.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaWarning(new SolicitudPrestamoOutView(TO_BE_CHALLENGED),
					respuestaRSA.getItemsMensajeRespuesta());
		}

		if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta()))
			return armarRespuestaErrorRSA(respuestaRSA);

		LOGGER.error("--LIQUIDAR-- SE PRODUJO UN ERROR AL LIQUIDAR UN PRESTAMO PARA EL NUP {}",
				liquidacionPrestamoInView.getNup());
		String codigoMensajeError = CodigoMensajeConstantes.ERROR_GENERICO;
		TipoError tipoError = TipoError.ERROR_GENERICO;
		return respuestaFactory.crearRespuestaError(ERROR_RETRY_LATER, tipoError, codigoMensajeError);
	}

	@SuppressWarnings("unchecked")
	private Respuesta<SolicitudPrestamoOutView> validarSMSyLiquidar(LiquidacionPrestamoInView liquidacionPrestamoInView,
			SolicitudPrestamoDTO solicitudPrestamoDTO) {
		LOGGER.info(
				"--LIQUIDAR-- EL DESAFIO PARA EL NUP: " + liquidacionPrestamoInView.getNup() + " SE ENCUENTRA ACTIVO ");
		// valido el token SMS y si da ok liquido
		if (this.prestamoManagerBO.isValidToken(liquidacionPrestamoInView.getTokenSms())) {
			LOGGER.info("--LIQUIDAR-- EL TOKEN SMS PARA EL NUP: " + liquidacionPrestamoInView.getNup() + " ES VALIDO");
			return liquidar(solicitudPrestamoDTO, solicitudPrestamoDTO.getNup());
		} else {
			// Se chequea si se llego al maximo de reintentos permitidos
			if (sesionParametros.getCantidadIntentosSMS() == 2) {
				sesionParametros.setCantidadIntentosSMS(0);
				LOGGER.info("--LIQUIDAR-- SE ALCANZO EL MAXIMO DE REINTENTOS SMS PARA EL NUP: "
						+ liquidacionPrestamoInView.getNup());
				return respuestaFactory.crearRespuestaError("Maximo de reintentos", TipoError.ERROR_REINTENTOS_AGOTADOS,
						"Se alcanzo maximo de reintentos");
			}
			sesionParametros.setCantidadIntentosSMS(sesionParametros.getCantidadIntentosSMS() + 1);
			ArrayList<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
			itemsMensajeRespuesta
					.add(new ItemMensajeRespuesta("Necesitamos validar su identidad, ingrese el código SMS"));

			LOGGER.error("--LIQUIDAR--ERROR EN LA VALIDACION DEL TOKEN SMS PARA EL NUP: {}",
					liquidacionPrestamoInView.getNup());

			// retornar algun error.
			return respuestaFactory.crearRespuestaWarning(new SolicitudPrestamoOutView("Need2F"),
					itemsMensajeRespuesta);
		}
	}

	private Respuesta<SolicitudPrestamoOutView> armarRespuestaErrorRSA(Respuesta<SimuladorPrestamoDTO> respuestaRSA) {
		if (EstadoRespuesta.ERROR.equals(respuestaRSA.getEstadoRespuesta())) {
			if (TipoError.SIN_METODO_DESAFIO.toString()
					.equals(respuestaRSA.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return respuestaFactory.crearRespuestaErrorPersonalizado(SolicitudPrestamoOutView.class,
						respuestaRSA.getItemsMensajeRespuesta().get(0).getMensaje(),
						TipoError.SIN_METODO_DESAFIO.toString());
			}
			if (TipoError.RSA_BLOQUEAR_USUARIO.toString()
					.equals(respuestaRSA.getItemsMensajeRespuesta().get(0).getTipoError())) {
				return respuestaFactory.crearRespuestaWarning(SolicitudPrestamoOutView.class,
						respuestaRSA.getItemsMensajeRespuesta().get(0).getMensaje(),
						TipoError.RSA_BLOQUEAR_USUARIO.toString());
			}
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
					CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
		}
		return null;
	}

	private PrestamoEncoladoEntity obtenerPrestamoEncolado(LiquidacionPrestamoInView liquidacionPrestamoInView)
			throws DAOException {
		return prestamoBO.obtenerPrestamoEncolado(liquidacionPrestamoInView.getId());
	}

	private boolean ingresoTokenPrisma(SolicitudPrestamoInView solicitudPrestamoInView) {
		return !StringUtils.isBlank(solicitudPrestamoInView.getTokenPrisma());
	}

	private boolean noIngresoTokenPrisma(SolicitudPrestamoInView solicitudPrestamoInView) {
		return !ingresoTokenPrisma(solicitudPrestamoInView);
	}

	@SuppressWarnings("rawtypes")
	private Respuesta encolar(SolicitudPrestamoDTO solicitud) {
		try {
			EncolamientoRequestDTO encolamientoDTO = prestamoBffMapper.toDTO(solicitud);
			EncolamientoResponseDTO baseDTO = prestamoBO.encolarPrestamo(encolamientoDTO);
			grabarEstadisticasEncolarOk(solicitud);
			sesionParametros.setExisteDesafioEnCurso(false);
			sesionParametros.setDesafioPrestamosActivo(true);
			return respuestaFactory.crearRespuestaOk(new SolicitudPrestamoOutView(baseDTO));
		} catch (GenericRestException e) {
			LOGGER.error("ERROR AL ENCOLAR EL PRESTAMO PARA EL NUP {}", solicitud.getNup(), e);
			grabarEstadisticasEncolarError(solicitud);
			return respuestaFactory.crearRespuestaError(GenericRestResponseDto.class, e.getResponseErrorDTO(),
					"No se pudo encolar el prestamo", TipoError.ERROR_ENCOLAR_PRESTAMO_PREAPROBADO,
					CodigoMensajeConstantes.FEEDBACK_ERROR_ENCOLAR_PREAPROBADO);
		} catch (DAOException e) {
			LOGGER.error("ERROR AL ENCOLAR EL PRESTAMO PARA EL NUP {}", solicitud.getNup(), e);
			grabarEstadisticasEncolarError(solicitud);
			return respuestaFactory.crearRespuestaError("No se pudo encolar el prestamo",
					TipoError.ERROR_ENCOLAR_PRESTAMO_PREAPROBADO,
					CodigoMensajeConstantes.FEEDBACK_ERROR_ENCOLAR_PREAPROBADO);
		}
	}

	@SuppressWarnings("unchecked")
	private Respuesta<SolicitudPrestamoOutView> encolarYLiquidar(SolicitudPrestamoDTO solicitud, String nup) {
		// encola
		Respuesta<SolicitudPrestamoOutView> respuesta = encolar(solicitud);
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta()))
			return respuesta;

		// liquida
		return liquidar(solicitud, nup);
	}

	@SuppressWarnings("rawtypes")
	private Respuesta liquidar(SolicitudPrestamoDTO solicitud, String nup) {
		LiquidacionPrestamoBaseDTO baseDTO;
		try {
			baseDTO = prestamoBO.liquidarPrestamo(solicitud.getTipoOferta(), nup, solicitud.getOrigenFront());
			grabarEstadisticasLiquidarOk(solicitud);
			sesionParametros.resetearDesafioEnCurso();
			sesionParametros.setDesafioPrestamosActivo(false);
			return respuestaFactory.crearRespuestaOk(new SolicitudPrestamoOutView(baseDTO));
		} catch (GenericRestException e) {
			LOGGER.error("ERROR AL LIQUIDAR EL PRESTAMO PARA EL NUP {}", solicitud.getNup(), e);
			grabarEstadisticasLiquidarError(solicitud);
			return respuestaFactory.crearRespuestaError(GenericRestResponseDto.class, e.getResponseErrorDTO(),
					"No se pudo liquidar el prestamo", TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO,
					CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO);
		} catch (DAOException e) {
			LOGGER.error("ERROR AL LIQUIDAR EL PRESTAMO PARA EL NUP {}", solicitud.getNup(), e);
			grabarEstadisticasLiquidarError(solicitud);
			return respuestaFactory.crearRespuestaError("No se pudo liquidar el prestamo",
					TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO, CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO);
		}
	}

	private void grabarEstadisticasLiquidarError(SolicitudPrestamoDTO solicitud) {
		grabarEstadisticasLiquidar(solicitud, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

	private void grabarEstadisticasLiquidarOk(SolicitudPrestamoDTO solicitud) {
		grabarEstadisticasLiquidar(solicitud, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	private void grabarEstadisticasLiquidar(SolicitudPrestamoDTO solicitud, String estadoEstadistica) {
		if (TipoOfertaEnum.PREAPROBADO.equals(solicitud.getTipoOferta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_ALTA_FEEDBACK, estadoEstadistica,
					null, solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
		}
		if (TipoOfertaEnum.PREACORDADO.equals(solicitud.getTipoOferta())) {
			if (solicitud.isLineaUva()) {
				estadisticaManager.add(EstadisticasConstants.RESULTADO_ALTA_PRESTAMO_UVA, estadoEstadistica, null,
						solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
			} else {
				estadisticaManager.add(EstadisticasConstants.RESULTADO_ALTA_PRESTAMO, estadoEstadistica, null,
						solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
			}
		}
	}

	private void grabarEstadisticasEncolar(SolicitudPrestamoDTO solicitud, String estadoEstadistica) {
		if (TipoOfertaEnum.PREAPROBADO.equals(solicitud.getTipoOferta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_ENCOLAR_FEEDBACK,
					estadoEstadistica, null, solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
		}
		if (TipoOfertaEnum.PREACORDADO.equals(solicitud.getTipoOferta())) {
			if (solicitud.isLineaUva()) {
				estadisticaManager.add(EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO_UVA, estadoEstadistica, null,
						solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
			} else {
				estadisticaManager.add(EstadisticasConstants.RESULTADO_ENCOLAR_PRESTAMO, estadoEstadistica, null,
						solicitud.getMontoTotal(), MonedaEspecieEnum.ARS.getCodigo());
			}
		}
	}

	private void grabarEstadisticasEncolarOk(SolicitudPrestamoDTO solicitud) {
		grabarEstadisticasEncolar(solicitud, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	private void grabarEstadisticasEncolarError(SolicitudPrestamoDTO solicitud) {
		grabarEstadisticasEncolar(solicitud, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
	}

}
