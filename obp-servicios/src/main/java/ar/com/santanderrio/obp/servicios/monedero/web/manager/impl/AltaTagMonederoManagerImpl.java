/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.BanelcoOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.AltaTagMonederoBO;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTag;
import ar.com.santanderrio.obp.servicios.monedero.dto.AltaTagRSA;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.monedero.service.AltaTagMonederoService;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.AltaTagMonederoManager;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAReqView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.AltaTagRSAView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteConfirmadoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class AltaTagMonederoManagerImpl.
 */
@Component
public class AltaTagMonederoManagerImpl implements AltaTagMonederoManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AltaTagMonederoManagerImpl.class);
    
    /** The Constant ERROR_RSA. */
    private static final String ERROR_RSA = "1043";
    
    /** The Constant COORDENADAS. */
    public static final int COORDENADAS = 0;
    
    /** The Constant COORDENADAS_BANELCO. */
    public static final int COORDENADAS_BANELCO = 1;
    
    /** The Constant TOKEN_COORDENADAS_BANELCO. */
    public static final int TOKEN_COORDENADAS_BANELCO = 2;

    /** The Constant BLANCK_STRING. */
    private static final String BLANCK_STRING = " ";
    
    /** The Constant COD_ERROR_CERO. */
    private static final String COD_ERROR_CERO = "00000000";

    /** The Constant MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO. */
    public static final String MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO = "6000";

    /** The Constant TIME_FORMAT_PATTERN. */
    public static final String TIME_FORMAT_PATTERN = "HH:mm";

    /** The Constant ERROR_VALIDACION. */
    private static final String ERROR_REINTENTOS_AGOTADOS_MENSAJE = "1165";

    /** The Constant ERROR_VALIDACION. */
    private static final String ERROR_DESAFIO_MENSAJE = "1515";

	/** The Constant CODIGO_MENSAJE_OCHO_DIGITOS. */
	private static final String CODIGO_MENSAJE_OCHO_DIGITOS = "1199";

    /** The alta tag monedero service. */
	@Autowired
	AltaTagMonederoService altaTagMonederoService;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The cuenta BO. */
	@Autowired
	private CuentaBO cuentaBO;

	/** The alta tag monedero BO. */
	@Autowired
	private AltaTagMonederoBO altaTagMonederoBO;

	/** The datos solicitante BO. */
	@Autowired
	private DatosSolicitanteBO datosSolicitanteBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;
	
	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;
	
	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<DatosSolicitanteConfirmadoInOutView> desafioOperacionRSA;

	/** The hora desde ALTA. */
	@Value("${MONEDERO.ALTA.HORA.DESDE}")
	private String horaDesdeALTA;

	/** The hora hasta ALTA. */
	@Value("${MONEDERO.ALTA.HORA.HASTA}")
	private String horaHastaALTA;

	/** The valor desafio monedero. */
	@Value("${TRJCOORD.OPERAINDISTINTO.MONEDERO:3}")
	private Integer valorDesafioMonedero;

	/**
	 * Guardar estadisticas.
	 *
	 * @param respuestaView
	 *            the respuesta view
	 */
	private void guardarEstadisticas(Respuesta<ComprobanteAltaTagMonederoView> respuestaView) {
		if (EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.MONEDERO_SOLICITUD_TITULAR_TOKEN,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(EstadisticasConstants.ALT_CTA_MONEDERO_COD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ALTA_EXITOSA_MONEDERO_TITULAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ALTA_ERROR_TITULAR,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
	}

	/**
	 * Validar nup Y dar de alta.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @param consultaInhabilitadosInEntity
	 *            the consulta inhabilitados in entity
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 * @return the respuesta
	 * @throws ParseException
	 *             the parse exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<ComprobanteAltaTagMonederoView> validarNupYDarDeAlta(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView, Cliente cliente,
			ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity,
			Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO) throws ParseException, DAOException {

		Respuesta<ComprobanteAltaTagMonederoView> respuestaView;

		/** Valido que la persona solicitante este en ALTAIR */
		if (datosSolicitanteDTO.getRespuesta().getNup() != null) {
			/**
			 * Valido que la fecha de nacimiento ingresada sea la misma que la
			 * que contiene ALTAIR
			 */
			if (datosSolicitanteConfirmadoView.getFechaNacimiento()
					.equals(datosSolicitanteDTO.getRespuesta().getFechaNacimiento())) {
				respuestaView = validarFechaNacYDarDeAlta(datosSolicitanteConfirmadoView, cliente,
						consultaInhabilitadosInEntity, datosSolicitanteDTO);
			} else {
				respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
				estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_FEC_NAC_NO_COINCIDEN,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} else {
			/** Valido que la persona solicitante exista en el padron */
			respuestaView = llamadaCnsPadCuit(datosSolicitanteConfirmadoView, cliente);
		}

		return respuestaView;
	}

	/**
	 * Validar fecha nac Y dar de alta.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @param consultaInhabilitadosInEntity
	 *            the consulta inhabilitados in entity
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 * @return the respuesta
	 * @throws ParseException
	 *             the parse exception
	 */
	private Respuesta<ComprobanteAltaTagMonederoView> validarFechaNacYDarDeAlta(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView, Cliente cliente,
			ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity,
			Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO) throws ParseException {

		Respuesta<ComprobanteAltaTagMonederoView> respuestaView;

		/** Valido que el nup ingresado no sea el mismo que el del titular */
		if (!sesionCliente.getCliente().getNup().equals(datosSolicitanteDTO.getRespuesta().getNup())) {

			consultaInhabilitadosInEntity
					.setApellido(StringUtils.leftPad(datosSolicitanteConfirmadoView.getApellido(), 30, BLANCK_STRING));
			consultaInhabilitadosInEntity
					.setNombre(StringUtils.leftPad(datosSolicitanteConfirmadoView.getNombre(), 30, BLANCK_STRING));
			consultaInhabilitadosInEntity.setSexo(datosSolicitanteConfirmadoView.getSexo());
			consultaInhabilitadosInEntity
					.setFechaNac(MonederoUtils.formatearyyyyMMdd(datosSolicitanteConfirmadoView.getFechaNacimiento()));
			consultaInhabilitadosInEntity
					.setTipoDoc(ISBANStringUtils.traerIdComboDni(datosSolicitanteConfirmadoView.getDocTipo()));
			consultaInhabilitadosInEntity
					.setNroDoc(StringUtils.leftPad(datosSolicitanteConfirmadoView.getDoc(), 11, "0"));

			consultaInhabilitadosInEntity.setCliente(cliente);

			respuestaView = ejecutarAlta(datosSolicitanteConfirmadoView, cliente, consultaInhabilitadosInEntity);
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_ALTA_TAG_EXITOSA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		} else {
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_MISMO_NUP,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuestaView;
	}

	/**
	 * Ejecutar alta.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @param consultaInhabilitadosInEntity
	 *            the consulta inhabilitados in entity
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAltaTagMonederoView> ejecutarAlta(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView, Cliente cliente,
			ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity) {

		ConsultaUnidadControlOutEntity consultaUnidadControlOutEntity = getSucursal(cliente);

		datosSolicitanteConfirmadoView.setIdCuentaSeleccionada(datosSolicitanteConfirmadoView.getNroCuenta());

		DatosAltaTagMonederoEntity datosAltaTagMonedero = getDatos(datosSolicitanteConfirmadoView, cliente);
		datosAltaTagMonedero.setAdicional(true);
		datosAltaTagMonedero.setSucursal(consultaUnidadControlOutEntity.getSucursalAdministradora());

		String limiteParteNumerica = obtieneParteNumericaDeExpresion(datosSolicitanteConfirmadoView.getLimite());
		datosAltaTagMonedero.setLimiteMensualRecarga(limiteParteNumerica);

		String importeParteNumerica = obtieneParteNumericaDeExpresion(datosSolicitanteConfirmadoView.getImporte());
		datosAltaTagMonedero.setImporteARecargar(importeParteNumerica);

		float edad1 = MonederoUtils.calcularAniosDesdeHoy(datosSolicitanteConfirmadoView.getFechaNacimiento());
		float edad2 = MonederoUtils.calcularAniosDesdeHoy(datosSolicitanteConfirmadoView.getFechaNacimiento());

		if (edad1 > 14 && edad2 < 18) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_EDAD_ENTRE_14_Y_18);
		} else {
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_MENOR_EDAD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		if (esPersonaHabilitada(consultaInhabilitadosInEntity)) {
			return altaTagMonederoService.ejecutarAltaTagMonedero(datosAltaTagMonedero, cliente);

		} else {
			estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_PERSONA_INHABILITADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PERSONA_INHABILITADA,
					CodigoMensajeConstantes.MENSAJE_ERROR_PERSONA_INHABILITADA);
		}
	}

	/**
	 * Obtiene parte numerica de expresion.
	 *
	 * @param expresion
	 *            the expresion
	 * @return the string
	 */
	private String obtieneParteNumericaDeExpresion(String expresion) {
		String[] s = expresion.split(" ");
		String parteNumerica = s[1];
		Double d = Double.parseDouble(parteNumerica);
		int i = (int) d.doubleValue();
		return String.valueOf(i);
	}

	/**
	 * Llamada cns pad cuit.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<ComprobanteAltaTagMonederoView> llamadaCnsPadCuit(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView, Cliente cliente) throws DAOException {

		DatosSolicitanteCriterioView datosSolicitanteCriterioView;
		// busco si existe en el padron con el servicio CNSPADCUIT
		datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
		datosSolicitanteCriterioView.setDocTipo(datosSolicitanteConfirmadoView.getTipoDoc());
		datosSolicitanteCriterioView.setDoc(datosSolicitanteConfirmadoView.getDoc());
		datosSolicitanteCriterioView.setFechaNacimiento(datosSolicitanteConfirmadoView.getFechaNacimiento());
		Respuesta<ConsultaPadronCuitOutEntity> responseConsultaPadronOutEntity = datosSolicitanteBO
				.getDatosPadronBO(datosSolicitanteCriterioView, cliente);

		// valido si existe en el padron
		if (responseConsultaPadronOutEntity != null) {

			// valido que la fecha de nac coincidan
			if (responseConsultaPadronOutEntity.getRespuesta().getAbaFechaNacimiento()
					.equals(datosSolicitanteConfirmadoView.getFechaNacimiento())) {

				// mandar al formulario de alta persona al frontend
				// ver como mandar la respuesta de OK al front
				ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView = new ComprobanteAltaTagMonederoView();
				return respuestaFactory.crearRespuestaOk(ComprobanteAltaTagMonederoView.class,
						comprobanteAltaTagMonederoView);

			} else {
				// si no coincide la fecha de nacimiento, ver como mandar el
				// error
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
			}
		} else {
			// no existe en el padron, volver con error
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
	}

	/**
	 * Gets the sucursal.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the sucursal
	 */
	private ConsultaUnidadControlOutEntity getSucursal(Cliente cliente) {
		ConsultaUnidadControlInEntity consultaUnidadControlInEntity = new ConsultaUnidadControlInEntity();
		consultaUnidadControlInEntity.setCliente(cliente);
		consultaUnidadControlInEntity.setNroDocumento(cliente.getDni());
		consultaUnidadControlInEntity.setTipoDocumento(cliente.getTipoDocumento());
		consultaUnidadControlInEntity.setNup(cliente.getNup());

		return altaTagMonederoBO.obtenerDatosSucursal(consultaUnidadControlInEntity);
	}

	/**
	 * Gets the solicitante DTO.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @return the solicitante DTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<DatosSolicitanteDTO> getSolicitanteDTO(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView, Cliente cliente)
			throws BusinessException {
		Respuesta<DatosSolicitanteDTO> respuesta = null;
		try {
			DatosSolicitanteCriterioView datosSolicitanteCriterioView = new DatosSolicitanteCriterioView();
			datosSolicitanteCriterioView.setDocTipo(TipoDocumentoEnum
					.obtenerTipoDocumento(datosSolicitanteConfirmadoView.getDocTipo()).getDescripcion());
			datosSolicitanteCriterioView.setDoc(datosSolicitanteConfirmadoView.getDoc());
			datosSolicitanteCriterioView.setAdicional(datosSolicitanteConfirmadoView.isAdicional());
			datosSolicitanteCriterioView.setFechaNacimiento(datosSolicitanteConfirmadoView.getFechaNacimiento());
			DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = new DatosSolicitanteCriterioDTO();
			BeanUtils.copyProperties(datosSolicitanteCriterioDTO, datosSolicitanteCriterioView);
			respuesta = datosSolicitanteBO.getDatosDelSolicitante(datosSolicitanteCriterioDTO, cliente);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#generarComprobanteAltaTagMonedero(ar.com.
	 * santanderrio.obp.servicios.monedero.web.view.
	 * ComprobanteAltaTagMonederoView)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteAltaTagMonedero(
			ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView) {
		Respuesta<Reporte> reporteRespuesta = altaTagMonederoService
				.generarComprobanteAltaTagMonedero(comprobanteAltaTagMonederoView);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaView;
	}

	/**
	 * Es persona habilitada.
	 *
	 * @param consultaInhabilitadosInEntity
	 *            the consulta inhabilitados in entity
	 * @return true, if successful
	 */
	private boolean esPersonaHabilitada(ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity) {
		boolean respuesta = false;
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = altaTagMonederoBO
				.esPersonaHabilitada(consultaInhabilitadosInEntity);
		if (consultaInhabilitadosOutEntity.getCodigoRetornoExtendido().equals(COD_ERROR_CERO)) {
			respuesta = true;
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#activarMonederoTag(ar.com.santanderrio.obp.
	 * servicios.monedero.web.view.DatosParaActivacionView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ComprobanteActivacionTagMonederoView> activarMonederoTag(
			DatosParaActivacionView datosParaActivacionView, Cliente cliente) {
		Respuesta<ComprobanteActivacionTagMonederoView> respuesta;

		Respuesta<ResultadoTransaccion> respuestaActivacion = altaTagMonederoService
				.activarMonederoTag(datosParaActivacionView, cliente);

		if (EstadoRespuesta.OK.equals(respuestaActivacion.getEstadoRespuesta())) {
			Cuenta cuenta = cuentaBO.obtenerCuentaPorId(cliente, datosParaActivacionView.getIdCuentaSeleccionada());
			respuesta = mapeo(respuestaActivacion, cuenta.getNroTarjetaCredito());
			estadisticaManager.add(EstadisticasConstants.ACT_CTA_MONEDERO_COD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuesta = Respuesta.copy(ComprobanteActivacionTagMonederoView.class, respuestaActivacion);
			estadisticaManager.add(EstadisticasConstants.ACT_CTA_MONEDERO_COD,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#generarComprobanteActivacionTagMonedero(ar.com.
	 * santanderrio.obp.servicios.monedero.web.view.
	 * ComprobanteActivacionTagMonederoView)
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView) {
		Respuesta<Reporte> reporteRespuesta = altaTagMonederoService
				.generarComprobanteActivacionTagMonedero(comprobanteActivacionTagMonederoView);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaView;
	}

	/**
	 * Mapeo.
	 *
	 * @param resp
	 *            the resp
	 * @param tagInactivo
	 *            the tag inactivo
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteActivacionTagMonederoView> mapeo(Respuesta<ResultadoTransaccion> resp,
			String tagInactivo) {
		Respuesta<ComprobanteActivacionTagMonederoView> respuesta = new Respuesta<ComprobanteActivacionTagMonederoView>();
		ComprobanteActivacionTagMonederoView comprobanteActivacionView = new ComprobanteActivacionTagMonederoView();
		comprobanteActivacionView.setFechaHora(resp.getRespuesta().getFechaTransaccion().toString());
		comprobanteActivacionView.setNroTagMonedero(tagInactivo);
		comprobanteActivacionView.setMensaje(MessageFormat.format(resp.getRespuesta().getMensajeError(), tagInactivo));
		comprobanteActivacionView.setComprobante(resp.getRespuesta().getNumeroComprobante());
		try {
			comprobanteActivacionView.setLegalesSEO(legalBO.obtenerLegal("1005"));
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		/** comprobanteActivacionView.setMensaje(mensajeManager.buscarMensaje(CodigoMensajeConstantes.MENSAJE_ACTIVACION_TAG_MONEDERO_OK).getRespuesta().getMensaje()); */
		respuesta.setEstadoRespuesta(resp.getEstadoRespuesta());
		respuesta.setRespuesta(comprobanteActivacionView);
		return respuesta;
	}

	/**
	 * Gets the datos.
	 *
	 * @param datosSolicitanteConfirmadoView
	 *            the datos solicitante confirmado view
	 * @param cliente
	 *            the cliente
	 * @return the datos
	 */
	private DatosAltaTagMonederoEntity getDatos(DatosSolicitanteConfirmadoInOutView datosSolicitanteConfirmadoView,
			Cliente cliente) {
		DatosAltaTagMonederoEntity datosAltaTagMonedero = new DatosAltaTagMonederoEntity();

		datosAltaTagMonedero.setApellidoEmbozado(datosSolicitanteConfirmadoView.getApellido());
		datosAltaTagMonedero.setNombreEmbozado(datosSolicitanteConfirmadoView.getNombre());

		Cuenta cuenta = cuentaBO.obtenerCuentaPorId(cliente, datosSolicitanteConfirmadoView.getIdCuentaSeleccionada());
		List<Cuenta> cuentas = cliente.getCuentas(); /** .getCuentasTarjetaDeCredito(); */
		String marcaRecargaPrincipal = MonederoUtils.resuelveMarcaRecarga(cuenta);
		datosAltaTagMonedero.setCuentaPesosSeleccionada(cuenta.getNroCuentaProducto());
		datosAltaTagMonedero.setMarcaEmisoraTarjetaOrigenPrincipal(marcaRecargaPrincipal);
		datosAltaTagMonedero.setNroTarjetaOrigenRecargasPrincipal(cuenta.getNroTarjetaCredito().substring(4, 20));
		String marcaRecargaSec = MonederoUtils.resuelveMarcaRecargaSecundaria(cuentas, marcaRecargaPrincipal);
		datosAltaTagMonedero.setMarcaEmisoraTarjetaOrigenSecundaria(marcaRecargaSec);
		datosAltaTagMonedero.setNroTarjetaOrigenRecargasSecundaria(
				MonederoUtils.resuelveNroTarjetaOrigenRecargaSecundaria(cliente, marcaRecargaSec));
		datosAltaTagMonedero.setNup(cliente.getNup());

		datosAltaTagMonedero.setImporteARecargar(datosSolicitanteConfirmadoView.getImporteSeleccionado().substring(1,
				datosSolicitanteConfirmadoView.getImporteSeleccionado().length() - 3));
		datosAltaTagMonedero.setLimiteMensualRecarga(datosSolicitanteConfirmadoView.getLimiteSeleccionado().substring(1,
				datosSolicitanteConfirmadoView.getLimiteSeleccionado().length() - 3));
		datosAltaTagMonedero.setFechaNacimiento(datosSolicitanteConfirmadoView.getFechaNacimiento());
		datosAltaTagMonedero.setPaisDeNacimiento(datosSolicitanteConfirmadoView.getPaisNacimiento());
		datosAltaTagMonedero.setSexo(datosSolicitanteConfirmadoView.getSexo());
		datosAltaTagMonedero.setEstadoCivil(datosSolicitanteConfirmadoView.getEstadoCivil());
		datosAltaTagMonedero.setNacionalidad(datosSolicitanteConfirmadoView.getNacionalidad());
		datosAltaTagMonedero.setCalle(datosSolicitanteConfirmadoView.getCalle());
		datosAltaTagMonedero.setNro(datosSolicitanteConfirmadoView.getCalleNro());
		datosAltaTagMonedero.setPiso(datosSolicitanteConfirmadoView.getPiso());
		datosAltaTagMonedero.setDepto(datosSolicitanteConfirmadoView.getDepto());
		datosAltaTagMonedero.setCp(datosSolicitanteConfirmadoView.getCp());
		datosAltaTagMonedero.setLocalidad(datosSolicitanteConfirmadoView.getLocalidad());
		datosAltaTagMonedero.setProvincia(datosSolicitanteConfirmadoView.getProvincia());

		datosAltaTagMonedero.setApellidoEmbozadoAdicional(datosSolicitanteConfirmadoView.getApellido());
		datosAltaTagMonedero.setNombreEmbozadoAdicional(datosSolicitanteConfirmadoView.getNombre());
		datosAltaTagMonedero.setNupAdicional(cliente.getNup());

		return datosAltaTagMonedero;
	}

	/**
	 * Horario bancario.
	 *
	 * @return the string
	 */
	public String horarioBancario() {

		DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT_PATTERN);

		DateTime horarioBancarioFinal = df.parseLocalTime(horaHastaALTA).toDateTimeToday();
		DateTime horarioBancarioInicio = df.parseLocalTime(horaDesdeALTA).toDateTimeToday().plusDays(1);

		DateTime horaActual = new DateTime();

		Interval intervaloFueraHorario = new Interval(horarioBancarioFinal, horarioBancarioInicio);

		if (intervaloFueraHorario.contains(horaActual)) {
			return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO)
					.getMensaje();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Configurar banelco.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 */
	private void configurarBanelco(AutentificacionDTO autentificacionDTO) {
		String mensajeBanelco = mensajeBO.obtenerMensajePorCodigo(CODIGO_MENSAJE_OCHO_DIGITOS).getMensaje();
		mensajeBanelco = MessageFormat.format(mensajeBanelco, sesionCliente.getCliente().getUltimosDigitosBanelco());

		BanelcoOperacionDTO banelcoOperacionDTO = new BanelcoOperacionDTO();
		banelcoOperacionDTO.setDigitos(sesionCliente.getCliente().getUltimosDigitosBanelco());
		banelcoOperacionDTO.setMensaje(
				MessageFormat.format(mensajeBanelco, sesionCliente.getCliente().getUltimosDigitosBanelco()));

		sesionParametros.setTipoDesafio(TipoDesafioEnum.BANELCO);

		AltaTagRSA altaTagRSA = new AltaTagRSA();

		autentificacionDTO.setTipoDesafio(TipoDesafioEnum.BANELCO);
		autentificacionDTO.setBanelcoOperacion(banelcoOperacionDTO);
		autentificacionDTO.setRsaDTO(altaTagRSA);
	}

	/**
	 * Configurar coordenadas.
	 *
	 * @param nroCuenta
	 *            the nro cuenta
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 */
	private void configurarCoordenadas(String nroCuenta, AutentificacionDTO autentificacionDTO) {
		PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
		pedidoCoordenada.setIp("192168001001");
		pedidoCoordenada.setNroCuenta(nroCuenta);

		AltaTagRSA altaTagRSA = new AltaTagRSA();

		CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
		coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
		autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
		autentificacionDTO.setRsaDTO(altaTagRSA);
	}

	/**
	 * Generar alta tag RSA view.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @return the alta tag RSA view
	 */
	private AltaTagRSAView generarAltaTagRSAView(AutentificacionDTO autentificacionDTO) {

		AltaTagRSAView view = new AltaTagRSAView();

		switch (autentificacionDTO.getOperacion()) {
		case COORDENADAS:
			view.setChallenge("COORDENADAS");
			break;
		case COORDENADAS_BANELCO:

			view.setChallenge("BANELCO");
			break;
		case TOKEN_COORDENADAS_BANELCO:
			view.setChallenge("");
			break;
		default:
		}

		view.setDesafio(autentificacionDTO);
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#validarOperacionRSA(ar.com.santanderrio.obp.
	 * servicios.monedero.dto.AltaTag)
	 */
	@Override
	public Respuesta<AltaTagRSAView> validarOperacionRSA(AltaTag altaTag) {

		estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_PEDIDO_COORD,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		// OJO ojo aca.. esto iria por configuracion?
		if (!sesionParametros.getRsaEstado().containsKey(OperacionesRSAEnum.ALTA_TAG_MONEDERO)) {
			sesionParametros.getRsaEstado().put(OperacionesRSAEnum.ALTA_TAG_MONEDERO, true);
			sesionParametros.getRsaEstado().put(OperacionesRSAEnum.ACTIVO, true);
		}
		Respuesta<AltaTagRSAView> respuesta = new Respuesta<AltaTagRSAView>();

		LOGGER.info("ALta tag monedero, validar desafio RSA ...");
		LOGGER.info("Validando cuenta origen...");

		String nroCuenta = obtenerNumeroCuenta(altaTag);

		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();

		autentificacionDTO.setOperacion(valorDesafioMonedero);
		if (valorDesafioMonedero.equals(COORDENADAS_BANELCO)) {
			configurarCoordenadas(nroCuenta, autentificacionDTO);

		} else {
			configurarCoordenadas(nroCuenta, autentificacionDTO);
			configurarBanelco(autentificacionDTO);
		}

		Respuesta<AutentificacionDTO> estadoRespuestaAutentificacion = autentificacionManager
				.analizarRSAyObtenerAutenticacionValida(autentificacionDTO);
		EstadoRespuesta resp = estadoRespuestaAutentificacion.getEstadoRespuesta();

		switch (resp) {
		case OK:
			respuesta.setRespuestaVacia(false);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			return respuesta;
		case WARNING:
			return armarRespuestaWarningDesafioRSA(respuesta, estadoRespuestaAutentificacion);
		case ERROR:
			return armarRespuestaErrorDesafioRSA();
		default:
		}

		return respuesta;
	}

	/**
	 * Armar respuesta error desafio RSA.
	 *
	 * @return the respuesta
	 */
	private Respuesta<AltaTagRSAView> armarRespuestaErrorDesafioRSA() {
		List<DatoItemMensaje> datosItems = new ArrayList<DatoItemMensaje>();

		DatoItemMensaje datosItem = new DatoItemMensaje();
		datosItem.setCodigoMensaje(CodigoMensajeConstantes.ERROR_RSA);
		datosItem.setTipoError(TipoError.LOGIN_RSA_DENY);
		datosItems.add(datosItem);

		return respuestaFactory.crearRespuestaError(datosItems);
	}

	/**
	 * Armar respuesta warning desafio RSA.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param estadoRespuestaAutentificacion
	 *            the estado respuesta autentificacion
	 * @return the respuesta
	 */
	private Respuesta<AltaTagRSAView> armarRespuestaWarningDesafioRSA(Respuesta<AltaTagRSAView> respuesta,
			Respuesta<AutentificacionDTO> estadoRespuestaAutentificacion) {
		respuesta.setEstadoRespuesta(estadoRespuestaAutentificacion.getEstadoRespuesta());
		respuesta.setRespuestaVacia(true);

		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuesta.addAll(estadoRespuestaAutentificacion.getItemsMensajeRespuesta());

		respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
		respuesta.setRespuesta(generarAltaTagRSAView(estadoRespuestaAutentificacion.getRespuesta()));
		return respuesta;
	}

	/**
	 * Obtener numero cuenta.
	 *
	 * @param altaTag
	 *            the alta tag
	 * @return the string
	 */
	private String obtenerNumeroCuenta(AltaTag altaTag) {
		// identifico al nro de cuenta
		String nroCuenta = null;
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			if (cuenta.getId().toString().equals(altaTag.getNroCuenta())) {
				nroCuenta = cuenta.getSucursalPaquete().substring(1) + "-"
						+ cuenta.getNroCuentaProducto().substring(9, 15) + "/"
						+ cuenta.getNroCuentaProducto().substring(15);
				break;
			}
		}
		return nroCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#validarMetodoSeguridad(ar.com.santanderrio.obp.
	 * servicios.monedero.web.view.AltaTagRSAReqView)
	 */
	@Override
	public Respuesta<AltaTagRSAReqView> validarMetodoSeguridad(AltaTagRSAReqView altaTagRSAReqView) {

		estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_PEDIDO_COORD_CONFIRM,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		LOGGER.info("Alta Monedero, vakida autenticacion RSA RSA...");

		Respuesta<AltaTagRSAReqView> respuesta;

		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		AltaTagRSA altaTagRSA = new AltaTagRSA();
		autentificacionDTO.setRsaDTO(altaTagRSA);
		autentificacionDTO.setCoordenadasOperacion(altaTagRSAReqView.getCoordenadasOperacion());
		autentificacionDTO.setBanelcoOperacion(altaTagRSAReqView.getBanelcoOperacion());
		autentificacionDTO.setTokenOperacion(altaTagRSAReqView.getTokenOperacion());

		if (altaTagRSAReqView.getCoordenadasOperacion() != null) {
			PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
			pedidoCoordenada.setIp("192168001001");
			autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
		}
		// ****

		// ejecutamos el desafio con los parametros que vienen de la vista
		Respuesta<AutentificacionDTO> ejecutarMetodoAutentificacion = autentificacionManager
				.ejecutarMetodoAutenticacionNotificandoRSA(autentificacionDTO);

		boolean reintentosAgotados = ejecutarMetodoAutentificacion.getRespuesta().isReintentosAgotados();

		// Default, respuesta post OK challenge
		respuesta = new Respuesta<AltaTagRSAReqView>();
		respuesta.setEstadoRespuesta(ejecutarMetodoAutentificacion.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(ejecutarMetodoAutentificacion.getItemsMensajeRespuesta());
		respuesta.setRespuesta(altaTagRSAReqView);

		EstadoRespuesta resp = ejecutarMetodoAutentificacion.getEstadoRespuesta();

		switch (resp) {
		case OK:
			respuesta.setRespuestaVacia(false);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setItemMensajeRespuesta(null);
			respuesta.setRespuesta(null);
			return respuesta;
		case WARNING:
			return armarRespuestaWarningMetodoSeguridad(respuesta, reintentosAgotados);
		case ERROR:
			Mensaje mensajeError = mensajeManager.obtenerMensajePorCodigo(ERROR_RSA);
			ItemMensajeRespuesta itemError = new ItemMensajeRespuesta();
			itemError.setMensaje(mensajeError.getMensaje());
			respuesta.add(itemError);
			return respuesta;
		default:
			return null;
		}
	}

	/**
	 * Armar respuesta warning metodo seguridad.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param reintentosAgotados
	 *            the reintentos agotados
	 * @return the respuesta
	 */
	private Respuesta<AltaTagRSAReqView> armarRespuestaWarningMetodoSeguridad(Respuesta<AltaTagRSAReqView> respuesta,
			boolean reintentosAgotados) {
		respuesta.setRespuestaVacia(false);
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		// Mensaje Descriptivo
		String mensajeDesc = reintentosAgotados ? ERROR_REINTENTOS_AGOTADOS_MENSAJE : ERROR_DESAFIO_MENSAJE;
		Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(mensajeDesc);
		// Item
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setMensaje(mensaje.getMensaje());
		String tipoError = reintentosAgotados ? TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion()
				: TipoError.ERROR_DESAFIO.getDescripcion();
		item.setTipoError(tipoError);
		// Agrega Item a lista
		List<ItemMensajeRespuesta> itemList = new ArrayList<ItemMensajeRespuesta>();
		itemList.add(item);
		respuesta.setItemMensajeRespuesta(itemList);

		respuesta.setRespuesta(null);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#ejecutarAltaTagMonederoRsa(ar.com.santanderrio.obp
	 * .servicios.monedero.web.view.DatosSolicitanteConfirmadoInOutView)
	 */
	@Override
	public Respuesta<DatosSolicitanteConfirmadoInOutView> ejecutarAltaTagMonederoRsa(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteView) {
		
		String solicitudTagMonederoMsg = horarioBancario();
		if (!StringUtils.isBlank(solicitudTagMonederoMsg)) {
			vaciarDesafio();
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}

		Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaView = validarAltaTagMonederoRsa(datosSolicitanteView);
		if (!EstadoRespuesta.OK.equals(respuestaView.getEstadoRespuesta())) {
			if (EstadoRespuesta.ERROR.equals(respuestaView.getEstadoRespuesta())) {
				vaciarDesafio();
			}
			return respuestaView;
		}

		Cliente cliente = sesionCliente.getCliente();
		estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_ALTA_TAG,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		try {
			ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity = new ConsultaInhabilitadosInEntity();

			Respuesta<ComprobanteAltaTagMonederoView> respuestaComprobanteView = null;
			if (datosSolicitanteView.isAdicional()) {
				Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO = getSolicitanteDTO(datosSolicitanteView, cliente);
				respuestaComprobanteView = validarNupYDarDeAlta(datosSolicitanteView, cliente,
						consultaInhabilitadosInEntity, datosSolicitanteDTO);

				respuestaView = mapearRespuestaAlta(respuestaComprobanteView, respuestaView);

			} else {
				DatosAltaTagMonederoEntity datosAltaTagMonedero = getDatos(datosSolicitanteView, cliente);
				datosAltaTagMonedero.setAdicional(false);
				respuestaComprobanteView = altaTagMonederoService.ejecutarAltaTagMonedero(datosAltaTagMonedero,
						cliente);

				guardarEstadisticas(respuestaComprobanteView);

				respuestaView = mapearRespuestaAlta(respuestaComprobanteView, respuestaView);

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			vaciarDesafio();
			respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
		vaciarDesafio();
		return respuestaView;
	}

	/**
	 * Mapear respuesta alta.
	 *
	 * @param respuestaComprobanteView
	 *            the respuesta comprobante view
	 * @param datosSolicitanteView
	 *            the datos solicitante view
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteConfirmadoInOutView> mapearRespuestaAlta(
			Respuesta<ComprobanteAltaTagMonederoView> respuestaComprobanteView,
			Respuesta<DatosSolicitanteConfirmadoInOutView> datosSolicitanteView) {

		Respuesta<DatosSolicitanteConfirmadoInOutView> respuestaMapeada = datosSolicitanteView;
		respuestaMapeada.setEstadoRespuesta(respuestaComprobanteView.getEstadoRespuesta());
		respuestaMapeada.setItemMensajeRespuesta(respuestaComprobanteView.getItemsMensajeRespuesta());
		respuestaMapeada.setRespuestaVacia(respuestaComprobanteView.isRespuestaVacia());

		DatosSolicitanteConfirmadoInOutView datosSolicitanteRespuesta = datosSolicitanteView.getRespuesta();
		ComprobanteAltaTagMonederoView comprobanteRespuesta = respuestaComprobanteView.getRespuesta();
		DatosSolicitanteConfirmadoView datosSolicitantesComprobanteRespuesta = comprobanteRespuesta
				.getDatosSolicitanteConfirmadoView();

		datosSolicitanteRespuesta.setComprobante(comprobanteRespuesta.getComprobante());
		datosSolicitanteRespuesta.setFechaHora(comprobanteRespuesta.getFechaHora());
		datosSolicitanteRespuesta.setMensaje(comprobanteRespuesta.getMensaje());
		datosSolicitanteRespuesta.setLegalesSEO(comprobanteRespuesta.getLegalesSEO());
		datosSolicitanteRespuesta.setAdicional(datosSolicitantesComprobanteRespuesta.isAdicional());
		datosSolicitanteRespuesta.setApellido(datosSolicitantesComprobanteRespuesta.getApellido());
		datosSolicitanteRespuesta.setCalle(datosSolicitantesComprobanteRespuesta.getCalle());
		datosSolicitanteRespuesta.setCalleNro(datosSolicitantesComprobanteRespuesta.getCalleNro());
		datosSolicitanteRespuesta.setCp(datosSolicitantesComprobanteRespuesta.getCp());
		datosSolicitanteRespuesta
				.setCuentaPesosSeleccionada(datosSolicitantesComprobanteRespuesta.getCuentaPesosSeleccionada());
		datosSolicitanteRespuesta.setCuitOCuil(datosSolicitantesComprobanteRespuesta.getCuitOCuil());
		datosSolicitanteRespuesta.setDepto(datosSolicitantesComprobanteRespuesta.getDepto());
		datosSolicitanteRespuesta.setDoc(datosSolicitantesComprobanteRespuesta.getDoc());
		datosSolicitanteRespuesta.setDocTipo(datosSolicitantesComprobanteRespuesta.getDocTipo());
		datosSolicitanteRespuesta.setEstadoCivil(datosSolicitantesComprobanteRespuesta.getEstadoCivil());
		datosSolicitanteRespuesta.setFechaNacimiento(datosSolicitantesComprobanteRespuesta.getFechaNacimiento());
		datosSolicitanteRespuesta
				.setIdCuentaSeleccionada(datosSolicitantesComprobanteRespuesta.getIdCuentaSeleccionada());
		datosSolicitanteRespuesta.setIdNacionalidad(datosSolicitantesComprobanteRespuesta.getIdNacionalidad());
		datosSolicitanteRespuesta.setIdPais(datosSolicitantesComprobanteRespuesta.getIdPais());
		datosSolicitanteRespuesta.setImporte(datosSolicitantesComprobanteRespuesta.getImporte());
		datosSolicitanteRespuesta
				.setImporteSeleccionado(datosSolicitantesComprobanteRespuesta.getImporteSeleccionado());
		datosSolicitanteRespuesta.setLimite(datosSolicitantesComprobanteRespuesta.getLimite());
		datosSolicitanteRespuesta.setLimiteSeleccionado(datosSolicitantesComprobanteRespuesta.getLimiteSeleccionado());
		datosSolicitanteRespuesta.setLocalidad(datosSolicitantesComprobanteRespuesta.getLocalidad());
		datosSolicitanteRespuesta.setNacionalidad(datosSolicitantesComprobanteRespuesta.getNacionalidad());
		datosSolicitanteRespuesta.setNombre(datosSolicitantesComprobanteRespuesta.getNombre());
		datosSolicitanteRespuesta.setNroCuenta(datosSolicitantesComprobanteRespuesta.getNroCuenta());
		datosSolicitanteRespuesta.setNroCuentaProducto(datosSolicitantesComprobanteRespuesta.getNroCuentaProducto());
		datosSolicitanteRespuesta.setNumeroComprobante(datosSolicitantesComprobanteRespuesta.getNumeroComprobante());
		datosSolicitanteRespuesta.setNup(datosSolicitantesComprobanteRespuesta.getNup());
		datosSolicitanteRespuesta.setPais(datosSolicitantesComprobanteRespuesta.getPais());
		datosSolicitanteRespuesta.setPaisNacimiento(datosSolicitantesComprobanteRespuesta.getPaisNacimiento());
		datosSolicitanteRespuesta.setPiso(datosSolicitantesComprobanteRespuesta.getPiso());
		datosSolicitanteRespuesta.setProvincia(datosSolicitantesComprobanteRespuesta.getProvincia());
		datosSolicitanteRespuesta.setSexo(datosSolicitantesComprobanteRespuesta.getSexo());
		datosSolicitanteRespuesta.setTelefono(datosSolicitantesComprobanteRespuesta.getTelefono());
		datosSolicitanteRespuesta.setTipoDoc(datosSolicitantesComprobanteRespuesta.getTipoDoc());

		respuestaMapeada.setRespuesta(datosSolicitanteRespuesta);
		return respuestaMapeada;
	}

	/**
	 * Validar alta tag monedero rsa.
	 *
	 * @param datosSolicitanteView
	 *            the datos solicitante view
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteConfirmadoInOutView> validarAltaTagMonederoRsa(
			DatosSolicitanteConfirmadoInOutView datosSolicitanteView) {

		Respuesta<DatosSolicitanteConfirmadoInOutView> respuesta = new Respuesta<DatosSolicitanteConfirmadoInOutView>();
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion(datosSolicitanteView.isAdicional());
        Respuesta<DatosSolicitanteConfirmadoInOutView> respEjecucionMetodoAutentificacion = desafioOperacionRSA
                .validarOperacionRSA(datosSolicitanteView, valorDesafioMonedero,
                        autentificacionCodEstDTO);
        respuesta.setRespuesta(respEjecucionMetodoAutentificacion.getRespuesta());
		respuesta.setEstadoRespuesta(respEjecucionMetodoAutentificacion.getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta());
		return respuesta;

	}

	/**
	 * Asignar estadisticas de autenticacion.
	 *
	 * @param isAdicional
	 *            the is adicional
	 */
	private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion(Boolean isAdicional) {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();

		if (isAdicional) {
		    autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(
					EstadisticasConstants.MONEDERO_SOLICITUD_COORDENADAS_ADICIONAL);
		    autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(
					EstadisticasConstants.MONEDERO_VALIDACION_COORDENADAS_ADICIONAL);
		} else {
		    autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(
					EstadisticasConstants.MONEDERO_SOLICITUD_COORDENADAS_TITULAR);
		    autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(
					EstadisticasConstants.MONEDERO_VALIDACION_COORDENADAS_TITULAR);
		}

		autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.MONEDERO_SOLICITUD_SOFT_TOKEN);
		autentificacionCodEstDTO
				.setCodigoEstadisticaValidacionToken(EstadisticasConstants.MONEDERO_VALIDACION_SOFT_TOKEN);

		return autentificacionCodEstDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#vaciarDesafio()
	 */
	@Override
	public Respuesta<Void> vaciarDesafio() {
		sesionParametros.resetearDesafioEnCurso();
		return new Respuesta<Void>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * AltaTagMonederoManager#cargarTerminosCondiciones()
	 */
	@Override
	public Respuesta<TerminosCondiciones> cargarTerminosCondiciones() {
		Respuesta<TerminosCondiciones> respuesta = new Respuesta<TerminosCondiciones>();
		String codigoLegal = "10100";
		try {
			Respuesta<String> legal = legalBO.buscarLegal(codigoLegal);
			TerminosCondiciones terminosCondiciones = new TerminosCondiciones();
			terminosCondiciones.setTitulo("Términos y Condiciones Monedero");
			terminosCondiciones.setCuerpo(legal.getRespuesta());
			List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
			respuesta.setItemMensajeRespuesta(itemMensajeRespuesta);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(terminosCondiciones);
			return respuesta;
		} catch (Exception e) {
			LOGGER.error("ERROR al buscar el legal {}.", codigoLegal, e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO, "");
		}
	}
}
