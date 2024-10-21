/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl.CasuisticaAgendaDestinatariosImpl;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AgendaDestinatariosRCAManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.AltaDestinatarioManager;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AltaAliasCBUHashView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioManagerImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class AltaDestinatarioManagerImpl implements AltaDestinatarioManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaDestinatarioManagerImpl.class);

	/** The Constant INFO_RSA. */
	private static final String INFO_RSA = "Ejecutando modulo de RSA";

	/** The Constant ERROR_INESPERADO. */
	private static final String ERROR_INESPERADO = "Error Inesperado.";

	/** The Constant REINTENTAR. */
	private static final String REINTENTAR = "reintentar";

	/** The Constant CONTINUAR. */
	private static final String CONTINUAR = "continuar";

	/** The Constant AGENDADO. */
	private static final String AGENDADO = "yaAgendado";

	/** The Constant RESETEO_RSA. */
	private static final String RESETEO_RSA = "Reseteando desafio en curso";

	/** The Constant CODIGO_DESTINATARIO_RIO. */
	private static final String CODIGO_DESTINATARIO_RIO = "RIO";

	/** The Constant CODIGO_DESTINATARIO_ALIAS. */
	private static final String CODIGO_DESTINATARIO_ALIAS = "ALIAS";

	/** The alta destinatario BO. */
	@Autowired
	private AltaDestinatarioBO altaDestinatarioBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The sesion agenda. */
	@Autowired
	private SesionAgendaDestinatarios sesionAgenda;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The casuistica. */
	@Autowired
	private CasuisticaAgendaDestinatarios casuistica;

	/** The casuistica. */
	@Autowired
	private AgendaDestinatariosRCAManager agendaDestinatariosRCAManager;

	@Autowired
	private ClienteManager clienteManager;

	/**
	 * Obtiene la confirmacion del alta de un destinatario. Incluye la logica de
	 * validaciones de RSA Tocken, Coordenadas y Ocho digitos
	 *
	 * @param view
	 *            the datos confirmados
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioRio(
			ConfirmacionAltaDestinatarioView view) {
		LOGGER.info(INFO_RSA);
		cargarDatosClaveToken(view);
		Respuesta<ConfirmacionAltaDestinatarioView> respuestaRSA = agendaDestinatariosRCAManager
				.ejecutarValidacionRSA(view, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_RIO);
		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			LOGGER.info("Validacion RSA Exitosa. Ejecutando Alta Destinatario Rio Rio.");
			return ejecutarConfirmacionAltaDestinatarioRio(view);
		}
		return respuestaRSA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
	 * AltaDestinatarioManager#obtenerConfirmacionAltaDestinatarioOtrosBancos(ar
	 * .com.santanderrio.obp.servicios.agenda.destinatarios.web.view.
	 * ConfirmacionAltaDestinatarioInView)
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioOtrosBancos(
			ConfirmacionAltaDestinatarioView view) {
		String referenciaTitular = view.obtenerReferenciaOTitular();
		LOGGER.info(INFO_RSA);
		cargarDatosClaveToken(view);
		Respuesta<ConfirmacionAltaDestinatarioView> respuestaRSA = agendaDestinatariosRCAManager
				.ejecutarValidacionRSA(view, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_OTROS_BANCOS);
		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			LOGGER.info("Validacion RSA Exitosa. Ejecutando Alta Destinatario Otros Bancos.");
			return obtenerConfirmacionAltaDestinatarioOtrosBancos(view, referenciaTitular);
		}
		return respuestaRSA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
	 * AltaDestinatarioManager#obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
	 * ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.
	 * ConfirmacionAltaDestinatarioInView)
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
			ConfirmacionAltaDestinatarioView view) throws BusinessException {
		String referenciaTitular = view.obtenerReferenciaOTitular();
		LOGGER.info(INFO_RSA);
		cargarDatosClaveToken(view);
		Respuesta<ConfirmacionAltaDestinatarioView> respuestaRSA = agendaDestinatariosRCAManager
				.ejecutarValidacionRSA(view, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_EXTRACCIONES);
		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			LOGGER.info("Validacion RSA Exitosa. Ejecutando Alta Destinatario Envio En Efectivo.");
			return obtenerConfirmacionAltaDestinatarioEnvioEfectivo(view, referenciaTitular);
		}
		return respuestaRSA;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager.
	 * AltaDestinatarioManager#obtenerConfirmacionAltaDestinatarioAlias(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.web.view.
	 * ConfirmacionAltaDestinatarioView)
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioAlias(
			ConfirmacionAltaDestinatarioView view) throws BusinessException {
		LOGGER.info(INFO_RSA);
		cargarDatosClaveToken(view);
		Respuesta<ConfirmacionAltaDestinatarioView> respuestaRSA = agendaDestinatariosRCAManager
				.ejecutarValidacionRSA(view, TipoOperacionACTAGEDESTEnum.ALTA, TipoAgendaEnum.AGENDA_ALIAS);
		if (EstadoRespuesta.OK.equals(respuestaRSA.getEstadoRespuesta())) {
			LOGGER.info("Validacion RSA Exitosa. Ejecutando Alta Destinatario Rio.");
			return ejecutarConfirmacionAltaDestinatarioAlias(view);
		}
		return respuestaRSA;

	}

	/**
	 * Cancelar alta agenda destinatario.
	 */
	@Override
	public void cancelarAltaAgendaDestinatario() {
		resetearDesafioEnCurso();
	}

	/**
	 * Graba la estadistica de la configuracion del alta destinatario por alias
	 * CBU.
	 */
	@Override
	public void grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU() {
		estadisticaManager.add("13564", EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/**
	 * Resetear desafio en curso.
	 */
	private void resetearDesafioEnCurso() {
		LOGGER.info(RESETEO_RSA);
		sesionParametros.setExisteDesafioEnCurso(false);
		sesionParametros.setDesafioEnCurso(null);
	}

	/**
	 * Obtener confirmacion alta destinatario rio.
	 *
	 * @param view
	 *            the datos confirmados
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> ejecutarConfirmacionAltaDestinatarioRio(
			ConfirmacionAltaDestinatarioView view) {
		String referenciaTitular = view.obtenerReferenciaOTitular();
		try {
			Cliente cliente = sesionCliente.getCliente();
			String ip = sesionParametros.getRegistroSession().getIp();

			String cuitCuil = sesionAgenda.getCuitCuil();
			if (sesionAgenda.getContadorAlta() == null) {
				sesionAgenda.setContadorAlta(new ContadorIntentos(2));
			}
			view.setDesafio(null);
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO.confirmarAltaDestinatarioRio(ip,
					cliente, view, referenciaTitular, cuitCuil);
			return crearRespuesta(respuestaDTO, CODIGO_DESTINATARIO_RIO);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		}
	}

	/**
	 * Obtener confirmacion alta destinatario otros bancos.
	 *
	 * @param view
	 *            the view
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioOtrosBancos(
			ConfirmacionAltaDestinatarioView view, String referenciaTitular) {
		try {
			if (sesionAgenda.getContadorAlta() == null) {
				sesionAgenda.setContadorAlta(new ContadorIntentos(2));
			}
			String ip = sesionParametros.getRegistroSession().getIp();
			Cliente cliente = sesionCliente.getCliente();
			String cuitCuil = sesionAgenda.getCuitCuil();
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
					.confirmarAltaDestinatarioOtrosBancos(ip, cliente, view, referenciaTitular, cuitCuil);
			return crearRespuestaOtrosBancos(respuestaDTO);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		}
	}

	/**
	 * Obtener confirmacion alta destinatario envio efectivo.
	 *
	 * @param view
	 *            the view
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
			ConfirmacionAltaDestinatarioView view, String referenciaTitular) {
		try {
			String ip = sesionParametros.getRegistroSession().getIp();
			Cliente cliente = sesionCliente.getCliente();
			String cuitCuil = sesionAgenda.getCuitCuil();
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
					.confirmarAltaDestinatarioEnvioEfectivo(ip, cliente, view, cuitCuil);

			if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ENVIO_EFECTIVO_REALIZADA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				respuestaDTO.getRespuesta()
						.setMensajeEfectivizacion(mensajeBO.obtenerMensajePorCodigo("1454").getMensaje());
				return crearRespuestaOkEnvioEfectivo(respuestaDTO, referenciaTitular);
			}
			return crearRespuestaErrorEnvioEfectivo(respuestaDTO.getItemsMensajeRespuesta(), referenciaTitular);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		}
	}

	/**
	 * Ejecutar confirmacion alta destinatario alias.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfirmacionAltaDestinatarioView> ejecutarConfirmacionAltaDestinatarioAlias(
			ConfirmacionAltaDestinatarioView view) {
		String referenciaTitular = view.obtenerReferenciaOTitular();
		try {
			Cliente cliente = sesionCliente.getCliente();
			String ip = sesionParametros.getRegistroSession().getIp();

			String cuitCuil = sesionAgenda.getCuitCuil();
			if (sesionAgenda.getContadorAlta() == null) {
				sesionAgenda.setContadorAlta(new ContadorIntentos(2));
			}
			view.setDesafio(null);
			if (validarHash(view)) {
				Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = altaDestinatarioBO
						.confirmarAltaDestinatarioAlias(ip, cliente, view, referenciaTitular, cuitCuil);
				return crearRespuesta(respuestaDTO, CODIGO_DESTINATARIO_ALIAS);
			} else {
				return crearRespuestaErrorInesperado(referenciaTitular);
			}
		} catch (BusinessException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		} catch (NullPointerException e) {
			LOGGER.error(ERROR_INESPERADO, e);
			return crearRespuestaErrorInesperado(referenciaTitular);
		}
	}

	/**
	 * Validar hash.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the boolean
	 */
	private Boolean validarHash(ConfirmacionAltaDestinatarioView datosConfirmados) {

		AltaAliasCBUHashView datosParaHash = new AltaAliasCBUHashView();
		datosParaHash.setAlias(datosConfirmados.getAlias());
		datosParaHash.setBanco(datosConfirmados.getBancoDestino());
		datosParaHash.setCbu(datosConfirmados.getCbu());
		datosParaHash.setNumeroCuil(datosConfirmados.getNumeroDocumento());
		datosParaHash.setTitular(datosConfirmados.getTitular());

		String hash = HashUtils.obtenerHash(datosParaHash);

		if (StringUtils.equals(sesionParametros.getValidacionHash(), hash)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Crear respuesta error inesperado.
	 *
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaErrorInesperado(String referenciaTitular) {
		estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_RIO_REALIZADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		resetearDesafioEnCurso();
		Respuesta<ConfirmacionAltaDestinatarioView> respuestaView = casuistica
				.crearRespuestaError(ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_RIO);
		respuestaView.getItemsMensajeRespuesta().get(0).setMensaje(
				CasuisticaAgendaDestinatariosImpl.obtenerMensajeItemFormateado(respuestaView, referenciaTitular));
		return respuestaView;
	}

	/**
	 * Crear respuesta para destinatarios de otros bancos.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaOtrosBancos(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO) {
		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
			return crearRespuestaOKOtrosBancos(respuestaDTO);
		}
		return crearRespuestaErrorOtrosBancos(respuestaDTO);
	}

	/**
	 * Crear respuesta para destinatarios de banco Rio.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param codigoAlta
	 *            the codigo alta
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuesta(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO, String codigoAlta) {
		if (EstadoRespuesta.OK.equals(respuestaDTO.getEstadoRespuesta())) {
			return crearRespuestaOK(respuestaDTO, codigoAlta);
		}
		return crearRespuestaError(respuestaDTO, codigoAlta);
	}

	/**
	 * Crear respuesta OK.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param codigoAlta
	 *            the codigo alta
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaOK(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO, String codigoAlta) {
		if (CODIGO_DESTINATARIO_RIO.equals(codigoAlta)) {
			estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_RIO_REALIZADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ALIAS_REALIZADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaFactory.crearRespuestaOk(ConfirmacionAltaDestinatarioView.class,
				new ConfirmacionAltaDestinatarioView(respuestaDTO.getRespuesta()));
	}

	/**
	 * Crear respuesta error.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @param codigoAlta
	 *            the codigo alta
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaError(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO, String codigoAlta) {
		if (CODIGO_DESTINATARIO_RIO.equals(codigoAlta)) {
			estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_RIO_REALIZADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ALIAS_REALIZADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		Respuesta<ConfirmacionAltaDestinatarioView> respuesta = respuestaFactory
				.crearRespuestaError(ConfirmacionAltaDestinatarioView.class, respuestaDTO.getItemsMensajeRespuesta());

		if (StringUtils.equals(TipoError.ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER.getDescripcion(),
				respuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
			return respuesta;
		}

		if (sesionAgenda.getContadorAlta().permiteReintentar()
				&& !"DESTINATARIO_AGENDADO_ALTA_DESTINATARIO"
						.equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError())
				&& !ErrorAgendaDestinatariosEnum.CUENTA_INVALIDA_ALTA_DESTINATARIO_FEEDBACK_RIO.getTipoError()
						.getDescripcion().equals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
			respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(REINTENTAR);
		} else {
			respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(CONTINUAR);
			sesionAgenda.setContadorAlta(null);
		}
		return respuesta;
	}

	/**
	 * Crear respuesta OK para otros bancos.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaOKOtrosBancos(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO) {
		estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_OTROS_BANCOS_REALIZADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(ConfirmacionAltaDestinatarioView.class,
				new ConfirmacionAltaDestinatarioView(respuestaDTO.getRespuesta()));
	}

	/**
	 * Crear respuesta error otros bancos.
	 *
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaErrorOtrosBancos(
			Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO) {
		estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_OTROS_BANCOS_REALIZADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<ConfirmacionAltaDestinatarioView> respuesta = respuestaFactory
				.crearRespuestaError(ConfirmacionAltaDestinatarioView.class, respuestaDTO.getItemsMensajeRespuesta());
		if (sesionAgenda.getContadorAlta().permiteReintentar()) {
			respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(REINTENTAR);
		} else {
			respuesta.getItemsMensajeRespuesta().get(0).setDetalleTipoError(CONTINUAR);
			sesionAgenda.setContadorAlta(null);
		}
		return respuesta;
	}

	/**
	 * Crear respuesta ok envio efectivo.
	 *
	 * @param res
	 *            the res
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaOkEnvioEfectivo(
			Respuesta<ComprobanteAltaDestinatarioDTO> res, String referenciaTitular) {
		Respuesta<ConfirmacionAltaDestinatarioView> respuesta = respuestaFactory.crearRespuestaOk(
				ConfirmacionAltaDestinatarioView.class, new ConfirmacionAltaDestinatarioView(res.getRespuesta()));
		respuesta.getRespuesta().setMensajeEfectivizacion(
				MessageFormat.format(respuesta.getRespuesta().getMensajeEfectivizacion(), referenciaTitular));
		return respuesta;
	}

	/**
	 * Crear respuesta error envio efectivo.
	 *
	 * @param list
	 *            the list
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	private Respuesta<ConfirmacionAltaDestinatarioView> crearRespuestaErrorEnvioEfectivo(
			List<ItemMensajeRespuesta> list, String referenciaTitular) {
		estadisticaManager.add(EstadisticasConstants.ALTA_DESTINATARIO_ENVIO_EFECTIVO_REALIZADA,
				EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		Respuesta<ConfirmacionAltaDestinatarioView> res = respuestaFactory
				.crearRespuestaError(ConfirmacionAltaDestinatarioView.class, list);
		res.getItemsMensajeRespuesta().get(0).setDetalleTipoError(CONTINUAR);
		if (!AGENDADO.equals(list.get(0).getTag())) {
			if (sesionAgenda.getContadorAlta() == null) {
				sesionAgenda.setContadorAlta(new ContadorIntentos(2));
			}
			if (sesionAgenda.getContadorAlta().permiteReintentar()) {
				res.getItemsMensajeRespuesta().get(0).setDetalleTipoError(REINTENTAR);
			} else {
				sesionAgenda.setContadorAlta(null);
			}
		} else {
			res.getItemsMensajeRespuesta().get(0)
					.setMensaje(MessageFormat.format(list.get(0).getMensaje(), referenciaTitular));
		}
		return res;
	}

	private void cargarDatosClaveToken(ConfirmacionAltaDestinatarioView view) {
		Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				view.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				view.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
	}

}