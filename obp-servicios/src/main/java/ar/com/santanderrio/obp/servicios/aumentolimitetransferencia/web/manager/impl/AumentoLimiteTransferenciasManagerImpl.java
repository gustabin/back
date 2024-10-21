/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.impl;

import java.math.BigDecimal;
import java.util.*;

import ar.com.santanderrio.obp.servicios.api.calendar.CalendarApi;
import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.dto.FechaHabilitadaDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.exception.CalendarApiException;
import ar.com.santanderrio.obp.servicios.api.calendar.mapper.CalendarApiConverter;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.customers.CustomersApi;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception.ScoringApiException;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.*;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.connector.TransferenciaRsaApi;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.bo.AumentoLimiteTransferenciaBO;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.AumentoLimiteTransferenciasManager;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.DatosComprobanteAumentoLimiteTransferencia;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Clase AumentoLimiteTransferenciasManagerImpl.
 */
@Component
public class AumentoLimiteTransferenciasManagerImpl implements AumentoLimiteTransferenciasManager {

	/** Logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AumentoLimiteTransferenciasManagerImpl.class);
	public static final int DOS_DECIMALES = 2;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

    @Autowired
    private DesafioOperacionRSA<AumentoLimiteTransferenciaInOutView> desafioOperacionRSA;

	/** Estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** AumentoLimiteTransferenciaBO. */
	@Autowired
	private AumentoLimiteTransferenciaBO aumentoLimiteTransferenciaBO;

	/** The cuenta manager. */
	@Autowired
	private CuentaManager cuentaManager;

	/** BiocatchManager. */
	@Autowired
	private BiocatchManager biocatchManager;

	@Autowired
	private CustomersApi customersApi;

	/** The valor desafio cambio limite extraccion. */
	@Value("${TRJCOORD.OPERAINDISTINTO.SOLAUMENTOLIMTRANSF:3}")
	private Integer valorDesafio;

	/** Valor del importe minimo en pesos. */
	@Value("${SOLAUMENTOLIMTRANSF.IMPORTE.PESOS.MIN}")
	private String importeMinPesos;

	/** Valor del importe minimo en dolares. */
	@Value("${SOLAUMENTOLIMTRANSF.IMPORTE.DOLARES.MIN}")
	private String importeMinDolares;

	/** The ayuda token url. */
	@Value("${SOLAUMENTOLIMTRANSF.AYUDA.TOKEN}")
	private String ayudaTokenUrl;


	/** Variable del mensaje de validación de fecha. */
	private String mensajeFechaInvalida;

	/** The Constant MSJ_ERROR_CUENTA_INVALIDA. */
	private static final String MSJ_ERROR_CUENTA_INVALIDA = "La cuenta no se encuentra en la lista de cuentas disponibles.";

	/** The Constant ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS. */
	private static final String ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS = "Error al obtener el saldo de las cuentas.";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant ERROR_EN_PARAMETRO. */
	private static final String ERROR_EN_PARAMETRO = "Error en parametro.";
	
	/** The Constant MSG_ERROR_DESTINO_CVU. */
	private static final String MSG_ERROR_DESTINO_CVU = "No puede generarse la solicitud para las cuentas virtuales (CVU)";

	/** The Constant MENSAJE_ERROR. */
	public static final String MENSAJE_ERROR = "Ocurrio un error y no podemos procesar tu solicitud en este momento.";

    /** The Constant WARNING_CVU. */
    private static final String WARNING_CVU = "WARNING_CVU";

	private static final float DEFAULT_DESTINATION_SCORING = -1;



	@Autowired
	private TransferenciaRsaApi transferenciaRsaApi;

	@Autowired
	private ClienteManager clienteManager;

	@Autowired
	private ScoringApi scoringApi;

	@Autowired
	private CalendarApi calendarApi;


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.
	 * AumentoLimiteTransferenciasManager#verificarTokenAsociado()
	 */
	@Override
	public Respuesta<AgendaDestinatarioLimiteTransferenciasView> verificarTokenAsociado() {
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaTokenAsociado = new Respuesta<AgendaDestinatarioLimiteTransferenciasView>();
		respuestaTokenAsociado.setEstadoRespuesta(EstadoRespuesta.OK);
		if (!sesionCliente.getCliente().tieneSoftToken()) {
			AgendaDestinatarioLimiteTransferenciasView view = new AgendaDestinatarioLimiteTransferenciasView();
			view.setMensajeAyudaAumentoLimite(ayudaTokenUrl);
			respuestaTokenAsociado = respuestaFactory.crearRespuestaWarning(
					AgendaDestinatarioLimiteTransferenciasView.class, view, TipoError.SIN_METODO_DESAFIO,
					CodigoMensajeConstantes.ADHERIR_METODO_DESAFIO_MENSAJE, TipoDesafioEnum.TOKEN.getId());
		}
		return respuestaTokenAsociado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.
	 * AumentoLimiteTransferenciasManager#obtenerAgendaDestinatarios(ar.com.
	 * santanderrio.obp.base.respuesta.entities.Respuesta)
	 */
	@Override
	public Respuesta<AgendaDestinatarioLimiteTransferenciasView> obtenerAgendaDestinatarios(
			Respuesta<AgendaDestinatarioView> respuestaAgendaDestinatariosManager) {
		LOGGER.info(
				"Seteando el texto del cuadro de ayuda para agregar un nuevo destinatario - obtenerAgendaDestinatarios");
		Respuesta<AgendaDestinatarioLimiteTransferenciasView> respuestaFinal = new Respuesta<AgendaDestinatarioLimiteTransferenciasView>();
		AgendaDestinatarioLimiteTransferenciasView agendaDestinatarioLimiteTransferenciaView = new AgendaDestinatarioLimiteTransferenciasView();
		if (respuestaAgendaDestinatariosManager.getRespuesta() != null) {
			agendaDestinatarioLimiteTransferenciaView.setListaDestinatarios(filtrarDestinatariosTerceros(
					respuestaAgendaDestinatariosManager.getRespuesta().getListaDestinatarios()));
			agendaDestinatarioLimiteTransferenciaView
					.setMensajeCabecera(respuestaAgendaDestinatariosManager.getRespuesta().getMensajeCabecera());
			agendaDestinatarioLimiteTransferenciaView.setMensajeAyudaNuevoDestinatario(mensajeManager
					.obtenerMensajePorCodigo(
							CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_MENSAJE_AYUDA_AGREGAR_DESTINATARIO).getMensaje());
			agendaDestinatarioLimiteTransferenciaView.setMensajeValidacionImporte(mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_MENSAJE_VALIDACION_IMPORTE).getMensaje());
			agendaDestinatarioLimiteTransferenciaView.setMensajeAyudaAumentoLimite(obtenerMensajeAyuda());
		}
		respuestaFinal.setEstadoRespuesta(respuestaAgendaDestinatariosManager.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuestaAgendaDestinatariosManager.getItemsMensajeRespuesta());
		respuestaFinal.setRespuestaVacia(respuestaAgendaDestinatariosManager.isRespuestaVacia());
		respuestaFinal.setSkipLog(respuestaAgendaDestinatariosManager.getSkipLog());

		respuestaFinal.setRespuesta(agendaDestinatarioLimiteTransferenciaView);
		LOGGER.info("Devolviendo información de los destinatarios - obtenerAgendaDestinatarios");
		return respuestaFinal;
	}

	/**
	 * Filtrar destinatarios terceros.
	 *
	 * @param listaDestinatarios
	 *            the lista destinatarios
	 * @return the list
	 */
	private List<DestinatarioView> filtrarDestinatariosTerceros(List<DestinatarioView> listaDestinatarios) {

		List<DestinatarioView> listaFiltrada = new ArrayList<DestinatarioView>();
		for (DestinatarioView destinatario : listaDestinatarios) {
			if (!destinatario.getEsCuentaPropia()) {
				listaFiltrada.add(destinatario);
			}
		}
		return listaFiltrada;
	}

	/**
	 * Obtener mensaje ayuda.
	 *
	 * @return the string
	 */
	private String obtenerMensajeAyuda() {

		StringBuilder mensajeBuilder = new StringBuilder();
		mensajeBuilder.append(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_MENSAJE_AYUDA_PARTE_UNO)
				.getMensaje());
		mensajeBuilder.append(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_MENSAJE_AYUDA_PARTE_DOS)
				.getMensaje());
		mensajeBuilder.append(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_MENSAJE_AYUDA_PARTE_TRES)
				.getMensaje());
		return mensajeBuilder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.
	 * AumentoLimiteTransferenciasManager#obtenerInformacionDestinatario(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.web.view.
	 * DestinatarioView)
	 */
	@Override
	public Respuesta<AumentoTransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView) {
		LOGGER.info(
				"Seteando informacion del destinatario con los importes minimos para la solicitud de aumento - obtenerInformacionDestinatario");

		Respuesta<TransferenciaView> respuesta = new Respuesta<TransferenciaView>();
		try {
			if (null != destinatarioView) {
				TransferenciaView transferenciaView = new TransferenciaView();
				if (null == destinatarioView.getCbu()) {
					transferenciaView.setTipoCuentaDestino(
							TipoCuenta.fromDescripcionConMoneda(destinatarioView.getTipoCuenta()).getAbreviatura());
				}
				if (ISBANStringUtils.validarCVU(destinatarioView.getCbu())) {
				    Respuesta<AumentoTransferenciaView> resp = new Respuesta<AumentoTransferenciaView>();
				    resp.setRespuestaVacia(Boolean.TRUE);
				    resp.setEstadoRespuesta(EstadoRespuesta.WARNING);
				    ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(MSG_ERROR_DESTINO_CVU);
				    itemMensajeRespuesta.setTipoError(WARNING_CVU);
				    resp.add(itemMensajeRespuesta);
				    return resp;
				}

				transferenciaView.setNroCuentaDestino(destinatarioView.getNroCuenta());

				Respuesta<CuentasView> respuestaCuentasView = cuentaManager.getCuentasSaldo();
				if (respuestaCuentasView.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
					throw new BusinessException(ERROR_AL_OBTENER_EL_SALDO_DE_LAS_CUENTAS);
				}
				List<CuentasAdhesionDebitoView> listaCuentasAdhesionDebitoViewList = null;
				if (null != destinatarioView.getCbu() && !BancoEnum.SANTANDER_RIO.getDescripcion().equalsIgnoreCase(destinatarioView.getBanco())) {
					listaCuentasAdhesionDebitoViewList = eliminarCuentasCerradas(respuestaCuentasView.getRespuesta());
				} else {
					listaCuentasAdhesionDebitoViewList = TransferenciaUtil.eliminarCuentasCerradasYFiltroOrigen(
							respuestaCuentasView.getRespuesta(), transferenciaView);
				}
				CuentasView cuentasViewFiltradas = new CuentasView();
				cuentasViewFiltradas.setCuentas(listaCuentasAdhesionDebitoViewList);
				transferenciaView = cargarTransferenciaView(destinatarioView, cuentasViewFiltradas);
				respuesta = respuestaFactory.crearRespuestaOk(TransferenciaView.class, transferenciaView);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(transferenciaView);
				transferenciaView.generarNuevoIdSesion();
			} else {
				throw new BusinessException(ERROR_EN_PARAMETRO);
			}
		} catch (BusinessException bex) {
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(MENSAJE_ERROR);
			itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.add(itemMensajeRespuesta);
		}

		AumentoTransferenciaView aumentoTransferenciaView = new AumentoTransferenciaView();
		Respuesta<AumentoTransferenciaView> respuestaFinal = new Respuesta<AumentoTransferenciaView>();

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			BeanUtils.copyProperties(respuesta.getRespuesta(), aumentoTransferenciaView);
			aumentoTransferenciaView.setImporteMinPesos(importeMinPesos);
			aumentoTransferenciaView.setImporteMinDolares(importeMinDolares);
			aumentoTransferenciaView.setMensajeAyudaAumentoLimite(obtenerMensajeAyuda());
		}
		respuestaFinal.setEstadoRespuesta(respuesta.getEstadoRespuesta());
		respuestaFinal.setItemMensajeRespuesta(respuesta.getItemsMensajeRespuesta());
		respuestaFinal.setRespuestaVacia(respuesta.isRespuestaVacia());
		respuestaFinal.setSkipLog(respuesta.getSkipLog());

		respuestaFinal.setRespuesta(aumentoTransferenciaView);
		LOGGER.info(
				"Devolviendo información del destinatario con montos minimos seteados - obtenerInformacionDestinatario");
		return respuestaFinal;
	}

		/**
         * Eliminar cuentas cerradas.
         *
         * @param cuentas
         *            the cuentas
         * @return the list
         */
	private List<CuentasAdhesionDebitoView> eliminarCuentasCerradas(CuentasView cuentas) {
		List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoViewList = new ArrayList<CuentasAdhesionDebitoView>();
		LOGGER.info("Excluyendo cuentas cerradas.");
		for (CuentasAdhesionDebitoView cuentaView : cuentas.getCuentas()) {
			if (!cuentaView.getIsCerrada()) {
				cuentasAdhesionDebitoViewList.add(cuentaView);
			}
		}
		return cuentasAdhesionDebitoViewList;
	}

	/**
	 * Cargar transferencia view.
	 *
	 * @param destinatarioView
	 *            the destinatario view
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaView cargarTransferenciaView(DestinatarioView destinatarioView,
			CuentasView cuentasViewFiltradas) throws BusinessException {
		TransferenciaView transferenciaView = new TransferenciaView();
		transferenciaView.setCuentasView(cuentasViewFiltradas);
		transferenciaView.setNroCuentaDestino(destinatarioView.getNroCuenta());
		transferenciaView.setCbu(destinatarioView.getCbu());
		transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
		transferenciaView.setCuentaPropia(destinatarioView.getEsCuentaPropia());
		transferenciaView.setFromAgendaDestinatario(true);
		transferenciaView.setCuit(destinatarioView.getCuitOCuil());
		transferenciaView.setBanco(destinatarioView.getBanco());
		transferenciaView.setTitular(destinatarioView.getTitular());
		transferenciaView.setAliasDestino(destinatarioView.getAlias());
		TipoCuenta tipoCuentaDestino = null;
		if (null != destinatarioView.getTipoCuenta()) {
			tipoCuentaDestino = TipoCuenta.fromDescripcionConMoneda(destinatarioView.getTipoCuenta());
			transferenciaView.setTipoCuentaDestino(
					tipoCuentaDestino == null ? STRING_VACIO : tipoCuentaDestino.getDescripcion());
			transferenciaView.setTipoCuentaDestinoDescripcion(
					tipoCuentaDestino == null ? STRING_VACIO : tipoCuentaDestino.getDescripcion());
		}
		if (BancoEnum.SANTANDER_RIO.getDescripcion().equalsIgnoreCase(destinatarioView.getBanco())) {
			transferenciaView.setIsRioRio(true);
		} else {
			transferenciaView.setIsRioRio(false);
		}
		transferenciaView.setFechaCreacionDestinatario(destinatarioView.getFechaCreacion());
		transferenciaView.setMonedasDisponibles(TransferenciaUtil.getMonedasValidasParaTransferirView(
				sesionCliente.getCliente(), tipoCuentaDestino, !transferenciaView.getIsRioRio()));
		return transferenciaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.
	 * AumentoLimiteTransferenciasManager#
	 * altaSolicitudAumentoLimiteTransferencia(ar.com.santanderrio.obp.servicios
	 * .aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView)
	 */
	@Override
	public Respuesta<AumentoLimiteTransferenciaInOutView> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInOutView inView) {
		LOGGER.info(
				"Alta de solicitud de aumento de limite de transferencia - altaSolicitudAumentoLimiteTransferencia");
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;

		respuestaFinal = validarDatosSolicitud(inView);
		if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta())) {
			return respuestaFinal;
		}

		Cliente cliente = sesionCliente.getCliente();

		if (!sesionParametros.isRsaTisAnalysisOk()) {
			respuestaFinal = invocarRsaValidarOperacion(inView);

			if (!EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta())) {
				if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta())) {
					vaciarDesafio();
				}
				return respuestaFinal;
			}

		} else {
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaFinal.setRespuesta(inView);
		}

		validarHash(crearMapaDeLaVista(inView));

		Respuesta<AumentoLimiteTransferenciaOutDTO> respuestaBO = new Respuesta<AumentoLimiteTransferenciaOutDTO>();
		try {
			LOGGER.info(
					"Llamada al BO de alta de solicitud de aumento de limite de transferencia - altaSolicitudAumentoLimiteTransferencia");
			AumentoLimiteTransferenciaInDTO inDTO = new AumentoLimiteTransferenciaInDTO();
			BeanUtils.copyProperties(inView, inDTO);
			inDTO.setRioRio(inView.getIsRioRio());
			inDTO.setCuentaPropia(inView.isCuentaPropia());
			inDTO.setCliente(cliente);

			respuestaBO = aumentoLimiteTransferenciaBO.altaSolicitudAumentoLimiteTransferencia(inDTO);

			if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.ALTA_GESTION_AUMENTO_LIMITE_TRANSFERENCIA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				this.almacenarComprobanteGestion(inView, respuestaBO.getRespuesta().getNroGestion());
				this.crearRespuestaSolicitud(respuestaFinal, respuestaBO.getRespuesta().getNroGestion(), inView);
			} else {
				estadisticaManager.add(EstadisticasConstants.ALTA_GESTION_AUMENTO_LIMITE_TRANSFERENCIA,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				this.crearRespuestaSolicitud(respuestaFinal, null, inView);
			}
		} catch (DAOException e) {
			estadisticaManager.add(EstadisticasConstants.ALTA_GESTION_AUMENTO_LIMITE_TRANSFERENCIA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(
					mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_ERROR_INESPERADO)
							.getMensaje());
		}
		LOGGER.info("Retornando respuesta del manager de alta de solicitud de aumento de limite de transferencia: "
				+ respuestaFinal.getEstadoRespuesta());
		vaciarDesafio();
		return respuestaFinal;
	}

	private void cargarDatosClaveToken(AumentoLimiteTransferenciaInOutView aumentoLimiteTransferenciaInOutView) {
		Respuesta<List<BigDecimal>> antiguedades = clienteManager.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
		if(antiguedades != null && antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
			if(antiguedades.getRespuesta().get(0) != null) {
				aumentoLimiteTransferenciaInOutView.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
			}
			if(antiguedades.getRespuesta().get(1) != null) {
				aumentoLimiteTransferenciaInOutView.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
			}
		}
	}

	/**
	 * Validar datos solicitud.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
	// Validaciones de los datos de la solicitud
	private Respuesta<AumentoLimiteTransferenciaInOutView> validarDatosSolicitud(
			AumentoLimiteTransferenciaInOutView inView) {
		Respuesta<AumentoLimiteTransferenciaInOutView> respuesta = new Respuesta<AumentoLimiteTransferenciaInOutView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		// Valido que la fecha de ejecucion este dentro de los parametros
		// aceptables
		if (!fechaEjecucionValida(inView.getFechaEjecucion())) {
			LOGGER.info(
					"ERROR - Alta de solicitud de aumento de limite de transferencia - fecha de ejecución no valida.");
			respuesta = crearRespuestaError(mensajeFechaInvalida);
		}

		// Valido que la cuenta origen sea distinta de la cuenta destino
		if (inView.getNroCuenta().equalsIgnoreCase(inView.getNroCuentaDestino())) {
			LOGGER.info(
					"ERROR - Alta de solicitud de aumento de limite de transferencia - cuenta origen es igual a la cuenta destino.");
			respuesta = crearRespuestaError(mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ALERTA_CUENTA_ORIGEN_IGUAL_DESTINO)
					.getMensaje());
		}

		// Valido el importe ingresado por el usuario
		if (!importeValido(inView.getImporte(), inView.getMoneda())) {
			LOGGER.info(
					"ERROR - Alta de solicitud de aumento de limite de transferencia - importe ingresado por debajo del limite establecido.");
			respuesta = crearRespuestaError(
					mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.IMPORTE_NO_VALIDO).getMensaje());
		}
		return respuesta;
	}

	/**
	 * Genera el comprobante de la solicitud.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAumentoLimiteTransferencia() {
		DatosComprobanteAumentoLimiteTransferencia datosComprobante = sesionParametros.getDatosComprobanteAumentoLimiteTransferencia();

		Respuesta<Reporte> reporteRespuesta = aumentoLimiteTransferenciaBO
				.generarComprobanteAumentoLimiteTransferencia(datosComprobante);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		return respuestaView;
	}

	/**
	 * Se guardan los datos del comprobante en sesion.
	 *
	 * @param inView
	 *            the in view
	 * @param nroGestion
	 *            the nro gestion
	 */
	private void almacenarComprobanteGestion(AumentoLimiteTransferenciaInOutView inView, String nroGestion) {
		DatosComprobanteAumentoLimiteTransferencia datosComprobante = new DatosComprobanteAumentoLimiteTransferencia();
		datosComprobante.setBanco(inView.getBanco());
		datosComprobante.setDestinatario(inView.getTitular());
		datosComprobante.setFechaEjecucion(inView.getFechaEjecucion());
		datosComprobante.setNroComprobante(nroGestion);
		datosComprobante.setNroCuentaDestino(inView.getNroCuentaDestino());
		datosComprobante.setTipoCuentaDestino(inView.getTipoCuentaDestinoDescripcion());
		datosComprobante.setNroCuentaOrigen(inView.getNroCuenta());
		datosComprobante.setTipoCuentaOrigen(inView.getTipoCuentaDescripcion());
		datosComprobante.setFechaOperacion(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		String signoTipoMoneda = TipoMonedaInversionEnum.ARG.getMoneda().equals(inView.getMoneda())
				? TipoMonedaInversionEnum.ARG.getSimbolo() : TipoMonedaInversionEnum.USD.getSimbolo();
		datosComprobante.setImporte(
				signoTipoMoneda + " " + ISBANStringUtils.formatearConComaYDosDecimales(inView.getImporte()));
		sesionParametros.setDatosComprobanteAumentoLimiteTransferencia(datosComprobante);

	}

	/**
	 * Agrega mensaje adecuado a la respuesta final del alta de gestion de
	 * solicitud.
	 *
	 * @param respuestaFinal
	 *            con el resultado del alta de solicitud, pero sin mensaje
	 *            seteado
	 * @param nroGestion
	 *            numero del comprobante devuelto por el WS
	 * @param inView
	 *            the in view
	 */
	private void crearRespuestaSolicitud(Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal,
			String nroGestion, AumentoLimiteTransferenciaInOutView inView) {
		AumentoLimiteTransferenciaInOutView respuesta = inView;
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();

		List<String> params = crearParametrosMensajeRespuesta(respuesta);

		boolean respuestaOk = !ISBANStringUtils.isEmptyOrNull(nroGestion);
		String mensajeRespuesta;
		if (respuestaOk) {
		    respuesta.setNumeroComprobante(nroGestion);
			respuesta.setFechaOperacion(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
			respuestaFinal.setRespuesta(respuesta);
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
			mensajeRespuesta = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_OK)
					.getMensaje();
		} else {
			respuestaFinal.setEstadoRespuesta(EstadoRespuesta.ERROR);
			mensajeRespuesta = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLAUMENTOLIMTRANSF_ERROR)
					.getMensaje();
		}
		mensajesList.add(new ItemMensajeRespuesta(String.format(mensajeRespuesta, params.toArray())));
		respuestaFinal.setItemMensajeRespuesta(mensajesList);
	}

	/**
	 * Creo los parametros necesarios para el armado del mensaje de respuesta
	 * final de la solicitud.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return lista de parametros
	 */
	private List<String> crearParametrosMensajeRespuesta(AumentoLimiteTransferenciaInOutView respuesta) {
		String titular = respuesta.getTitular();
		String signoTipoMoneda = TipoMonedaInversionEnum.ARG.getMoneda().equals(respuesta.getMoneda())
				? TipoMonedaInversionEnum.ARG.getSimbolo() : TipoMonedaInversionEnum.USD.getSimbolo();
		String importe = ISBANStringUtils.formatearConComaYDosDecimales(respuesta.getImporte());
		importe = importe.indexOf('.') == -1 ? importe.concat(".00") : importe;
		List<String> params = new ArrayList<String>();
		params.add(titular);
		params.add(signoTipoMoneda);
		params.add(importe);
		return params;
	}

	/**
	 * Valida que el importe ingresado sea mayor al limite minimo establecido
	 * para solicitar el aumento de limite de transferencia.
	 *
	 * @param importe
	 *            - importe ingresado por el usuario
	 * @param tipoMoneda
	 *            - tipo de moneda ingresado por el usuario
	 * @return booleano del resultado de la validacion (true en caso exitoso)
	 */
	private boolean importeValido(String importe, String tipoMoneda) {
		BigDecimal importeMinimo;
		BigDecimal importeIngresado;
		try {
			importeIngresado = ISBANStringUtils.convertirImporteConNCantDeDecimales(importe, 2);
			if (TipoMonedaInversionEnum.ARG.getMoneda().equals(tipoMoneda)) {
				importeMinimo = ISBANStringUtils.convertirImporteConNCantDeDecimales(importeMinPesos, DOS_DECIMALES);

			} else if (TipoMonedaInversionEnum.USD.getMoneda().equals(tipoMoneda)) {
				importeMinimo = ISBANStringUtils.convertirImporteConNCantDeDecimales(importeMinDolares, DOS_DECIMALES);
			} else {
				return false;
			}
			return importeIngresado.compareTo(importeMinimo) >= 0;
		} catch (ImporteConvertException e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Valida la fecha de ejecución.
	 * 
	 * @param fecha
	 *            de ejecución prevista para la transferencia ingresada por el
	 *            usuario.
	 * @return true en caso de ser valida
	 */
	private boolean fechaEjecucionValida(String fecha) {

		Boolean isFechaValida = false;

		// valida si la fecha fue ingresada
		if (null == fecha || fecha.trim().isEmpty()) {
			mensajeFechaInvalida = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FECHA_NO_INGRESADA)
					.getMensaje();
			return isFechaValida;
		}

		Date fechaEjecucionDate = ISBANStringUtils.formatearFecha(fecha);
		Calendar fechaEjecucion = Calendar.getInstance();
		fechaEjecucion.setTime(fechaEjecucionDate);

		int diaDeSemana = fechaEjecucion.get(Calendar.DAY_OF_WEEK);

		// valida si la fecha cae en un fin de semana
		if (diaDeSemana == Calendar.SATURDAY || diaDeSemana == Calendar.SUNDAY) {
			mensajeFechaInvalida = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ALERTA_FIN_SEMANA).getMensaje();
			return isFechaValida;
		}

		isFechaValida = controlaRangoFechas(fechaEjecucion);

		return isFechaValida;
	}

	/**
	 * Valida si la fecha de ejecucion se encuentra en el rango valido de entre
	 * 1 y 30 dias desde la fecha actual.
	 *
	 * @param fechaEjecucion
	 *            the fecha ejecucion
	 * @return true, if successful
	 */
	private boolean controlaRangoFechas(Calendar fechaEjecucion) {
		Boolean rangoFechasOk = false;

		Calendar fechaHoy = Calendar.getInstance();
		fechaHoy.setTime(ISBANStringUtils.formatearFecha(ISBANStringUtils.formatearFecha(new Date())));

		// valida si la fecha de ejecucion es posterior a la fecha actual
		if (fechaEjecucion.compareTo(fechaHoy) <= 0) {
			mensajeFechaInvalida = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ALERTA_FECHA_POSTERIOR).getMensaje();
			return rangoFechasOk;
		}

		// valida si la fecha de ejecucion se encuentra en el rango valido de
		// entre 1 y 30 dias desde la fecha actual
		int intervaloDias = 0;
		while (!fechaHoy.after(fechaEjecucion)) {
			int diaDeSemana = fechaHoy.get(Calendar.DAY_OF_WEEK);
			if ((diaDeSemana != Calendar.SATURDAY) && (diaDeSemana != Calendar.SUNDAY)) {
				intervaloDias++;
			}
			fechaHoy.add(Calendar.DATE, 1);
		}

		if (intervaloDias < 1 || intervaloDias > 30) {
			mensajeFechaInvalida = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_ALERTA_FECHA_NO_VALIDA).getMensaje();
			return rangoFechasOk;
		}

		return true;
	}

	/**
	 * Crea respuesta de ERROR seteando el mensaje.
	 *
	 * @param mensaje
	 *            de error
	 * @return respuesta
	 */
	private Respuesta<AumentoLimiteTransferenciaInOutView> crearRespuestaError(String mensaje) {

		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaError = respuestaFactory
				.crearRespuestaError(AumentoLimiteTransferenciaInOutView.class, null);
		List<ItemMensajeRespuesta> mensajesList = new ArrayList<ItemMensajeRespuesta>();
		mensajesList.add(new ItemMensajeRespuesta(mensaje));
		respuestaError.addAll(mensajesList);
		return respuestaError;
	}

	/**
	 * Seteo de estadisticas de los metodos de autenticación de la
	 * funcionalidad.
	 * @return AutentificacionCodEstDTO
	 */
	private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOFT_TOKEN_SOLICITUD_AUMENTO_LIMITE_TRANSFERENCIA);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.SOFT_TOKEN_VALIDACION_AUMENTO_LIMITE_TRANSFERENCIA);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.COORDENADAS_SOLICITUD_AUMENTO_LIMITE_TRANSFERENCIA);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.COORDENADAS_VALIDACION_AUMENTO_LIMITE_TRANSFERENCIA);
        return autentificacionCodEstDTO;
	}

	/**
	 * Cargar vista con datos necesarios para rsa.
	 *
	 * @param inView
	 *            the in view
	 * @param cliente
	 *            the cliente
	 */
	private void cargarVistaConDatosNecesariosParaRsa(AumentoLimiteTransferenciaInOutView inView, Cliente cliente) {
		String nroProductoCuenta = ISBANStringUtils
				.formateadorConCerosIzq(ISBANStringUtils.extraerCuenta(inView.getNroCuenta()), 16);
		Cuenta cuentaOrigen = cliente.getCuentaPorNumero(nroProductoCuenta);
		if (cuentaOrigen == null) {
			LOGGER.info(MSJ_ERROR_CUENTA_INVALIDA);
			throw new RobotException(MSJ_ERROR_CUENTA_INVALIDA);
		} else {
			inView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUPesos());
			inView.setTipoCuentaEnum(cuentaOrigen.getTipoCuentaEnum());
			inView.setMonedaAltair(cuentaOrigen.getMonedaAltair());
			if (DivisaEnum.DOLAR.getMoneda().equalsIgnoreCase(inView.getMoneda())) {
				inView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUDls());
			} else {
				inView.setSaldoCuentaOrigen(cuentaOrigen.getSaldoCUPesos());
			}
			setTransactionMeter(inView);
			inView.setStopOperacionErrorRSA(true);
			inView.setCuitCliente(cliente.getNumeroCUILCUIT());
			cargarDatosClaveToken(inView);
			inView.setScoringDestinatario(getScoringDestinatario(inView.getCuit()));
			setBiocatchData(inView);
			setCantDiasUltimoCambioMail(inView);

		}
	}

	private float getScoringDestinatario(String cuit) {

		try {

			return scoringApi.getScoring(StringUtils.remove(cuit, '-'));

		} catch (ScoringApiException e) {

			LOGGER.error("Error al obtener scoring del cuit " + cuit, e);

			return DEFAULT_DESTINATION_SCORING;
		}

	}

	private void setTransactionMeter(AumentoLimiteTransferenciaInOutView inView) {
		if(canInvokeTransferRsaApi(inView)) {
			final TransferenciaSumResponse response = transferenciaRsaApi.getTransactionSum(
					Long.parseLong(sesionCliente.getCliente().getNup()),
					formatearCuit(inView.getCuit()),
					DivisaEnum.fromMonedaString(inView.getMoneda()).getCodigo()
			);
			inView.setControlSum(response);
		}

	}

	private void setBiocatchData(AumentoLimiteTransferenciaInOutView inView){
		BiocatchResponseDataDTO biocatchResponseDataDTO =
				biocatchManager.getScore(
						sesionCliente.getCliente().getNup(),
						sesionCliente.getIpCliente(),
						ActivityName.TRANSFERENCIAS_AUMENTO_LIMITE,
						ActivityType.CHANGE_ACCOUNT_LIMIT
				);
		inView.setBiocatchResponseDataDTO(biocatchResponseDataDTO);
	}

	private void setCantDiasUltimoCambioMail(AumentoLimiteTransferenciaInOutView inView) {

		try {

			String nup = sesionCliente.getCliente().getNup();

			LOGGER.info("Conectando con customers-api para obtener cantDiasUltimoCambioMail. Nup: {}", nup);

			Customers customers = customersApi.getCustomerById(nup);

			LOGGER.info("Fechas obtenidas cantDiasUltimoCambioMail para el nup: {}. createdAt: [{}] , updateAt: [{}]",
					nup, customers.getCreatedAt(), customers.getUpdatedAt());

			Long cantDiasUltimoCambioMail = TransferenciaUtil.obtenerCantDiasUltimoCambioMail(
					customers.getCreatedAt(),
					customers.getUpdatedAt(),
					new Date());

			LOGGER.info("CantDiasUltimoCambioMail: {}. Nup: {}", cantDiasUltimoCambioMail, nup);

			inView.setCantDiasUltimoCambioMail(cantDiasUltimoCambioMail.intValue());

		} catch (ApiException e) {

			LOGGER.error(String.format("[Error conexión customers-api]: %s", e.getMessage()));
		}

	}

	private boolean canInvokeTransferRsaApi(AumentoLimiteTransferenciaInOutView inView) {
		return inView.getCuit() != null && inView.getMoneda() != null;
	}


	private String formatearCuit(String cuit) {
		if(!cuit.matches("^[0-9]{2}-[0-9]{8,9}-[0-9]{1}")) {
			return cuit.substring(0, 2) + "-" + cuit.substring(2, cuit.length() - 1) + "-" + cuit.substring(cuit.length() - 1);
		}
		return cuit;
	}

	/**
	 * Cargar hash.
	 *
	 * @param viewMap
	 *            the view map
	 */
	private void cargarHash(Map<String, Object> viewMap) {
		String hashView = HashUtils.obtenerHash(viewMap);
		LOGGER.info("Validando HASH en OBTENERFECHAS: {}", hashView);
		sesionParametros.setValidacionHashTIS(hashView);
	}

	/**
	 * Crear mapa de la vista.
	 *
	 * @param inView
	 *            the in view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVista(AumentoLimiteTransferenciaInOutView inView) {
		LOGGER.info("Creando hash de los atributos...");
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("cuentaPropia", inView.isCuentaPropia());
		mapaAtributos.put("isRioRio", inView.getIsRioRio());
		mapaAtributos.put("tipoCuentaDescripcion",
				inView.getTipoCuentaDescripcion() != null ? inView.getTipoCuentaDescripcion() : StringUtils.EMPTY);
		mapaAtributos.put("banco", inView.getBanco() != null ? inView.getBanco() : StringUtils.EMPTY);
		mapaAtributos.put("cbu", inView.getCbu() != null ? inView.getCbu() : StringUtils.EMPTY);
		mapaAtributos.put("tipoCuentaDestinoDescripcion", inView.getTipoCuentaDestinoDescripcion() != null
				? inView.getTipoCuentaDestinoDescripcion() : StringUtils.EMPTY);
		mapaAtributos.put("moneda", inView.getMoneda() != null ? inView.getMoneda() : StringUtils.EMPTY);
		mapaAtributos.put("importe", inView.getImporte() != null ? inView.getImporte() : StringUtils.EMPTY);
		mapaAtributos.put("nroCuenta", inView.getNroCuenta() != null ? inView.getNroCuenta() : StringUtils.EMPTY);
		mapaAtributos.put("fechaEjecucion", StringUtils.EMPTY);
		mapaAtributos.put("nroCuentaDestino",
				inView.getNroCuentaDestino() != null ? inView.getNroCuentaDestino() : StringUtils.EMPTY);

		LOGGER.info("String mapa vista: {}", mapaAtributos.toString());
		LOGGER.info("Fecha de ejecucion en mapa: {}", mapaAtributos.get("fechaEjecucion"));
		return mapaAtributos;
	}

	/**
	 * Validar hash.
	 *
	 * @param viewMap
	 *            the view map
	 */
	private void validarHash(Map<String, Object> viewMap) {
		String inputHash = HashUtils.obtenerHash(viewMap);
		LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
				sesionParametros.getValidacionHashTIS(), inputHash);
		HashUtils.compareHash(sesionParametros.getValidacionHashTIS(), inputHash);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.manager.
	 * AumentoLimiteTransferenciasManager#vaciarDesafio()
	 */
	@Override
	public Respuesta<Void> vaciarDesafio() {
		sesionParametros.resetearDesafioEnCurso();
		return new Respuesta<Void>();
	}

	/**
	 * Gets the mensaje fecha invalida.
	 *
	 * @return the mensaje fecha invalida
	 */
	public String getMensajeFechaInvalida() {
		return mensajeFechaInvalida;
	}

	/**
	 * Sets the mensaje fecha invalida.
	 *
	 * @param mensajeFechaInvalida
	 *            the new mensaje fecha invalida
	 */
	public void setMensajeFechaInvalida(String mensajeFechaInvalida) {
		this.mensajeFechaInvalida = mensajeFechaInvalida;
	}

	private Respuesta<AumentoLimiteTransferenciaInOutView> invocarRsaValidarOperacion(AumentoLimiteTransferenciaInOutView inView) {

		Cliente cliente = sesionCliente.getCliente();
		AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();

		cargarVistaConDatosNecesariosParaRsa(inView, cliente);

		if (inView.getDesafio() == null) {
			cargarHash(crearMapaDeLaVista(inView));
		}

		return desafioOperacionRSA.validarOperacionRSA(inView, valorDesafio, autentificacionCodEstDTO);
	}

	@Override
	public Respuesta<AumentoLimiteTransferenciaInOutView> obtenerFechasHabilitadas(AumentoLimiteTransferenciaInOutView inView) {
		LOGGER.info("Obtener fechas habilitadas");
		Respuesta<AumentoLimiteTransferenciaInOutView> respuestaFinal;
		respuestaFinal = invocarRsaValidarOperacion(inView);

		sesionParametros.setRsaTisAnalysisOk(EstadoRespuesta.OK.equals(respuestaFinal.getEstadoRespuesta()));

		if (EstadoRespuesta.ERROR.equals(respuestaFinal.getEstadoRespuesta())) {
				vaciarDesafio();
			return respuestaFinal;
		}

		String status = sesionParametros.getReglaRsaTis();
		sesionParametros.setReglaRsaTis(null);

		try {
			List<CalendarApiDateDTO> listCalendarApiDateDTO = calendarApi.getDates();
			List<FechaHabilitadaDTO>listaFechasDTO = CalendarApiConverter.calendarApiDTOMapperFechaHabilitadaDTO(listCalendarApiDateDTO);
			LOGGER.info("Resultado analisis TIS RSA: {}", status);

			int endIndex = status != null && status.toUpperCase().startsWith("TIS_APROBADA") ? 2 : 3;
			listaFechasDTO.subList(0, endIndex).clear();

			inView.setTisFechasHabilitadasResponse(new TISFechasHabilitadasResponse(status,listaFechasDTO));
			respuestaFinal.setRespuesta(inView);

		}catch (Exception e){
			throw new CalendarApiException(e);
		}

		return respuestaFinal;
	}
}
