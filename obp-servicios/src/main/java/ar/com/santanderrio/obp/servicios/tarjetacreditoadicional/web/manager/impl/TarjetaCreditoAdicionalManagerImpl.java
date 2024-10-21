/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaInhabilitadosOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinWarningException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.manager.MyaManager;
import ar.com.santanderrio.obp.servicios.monedero.dao.ConsultaUnidadControlDAO;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocumentoDescripcionEnum;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.DatosSolicitanteTarjetaAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.TarjetaCreditoAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.EdadIncorrectaException;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.common.TarjCredAdicUtils;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ComprobanteAltaTarjCredAdicionalDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DomiciliosDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaAdicionalSolicitadaDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.TarjetaCreditoAdicionalManager;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ComprobanteAltaTarjCredAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosTarjetaAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DomiciliosView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.NacionalidadView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.PaisDeNacimientoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.ProvinciaView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SexoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SolicitudTarjetaCreditoAdicionalView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.TarjetaCandidataView;

/**
 * The Class TarjetaCreditoAdicionalManagerImpl.
 */
@Component
public class TarjetaCreditoAdicionalManagerImpl implements TarjetaCreditoAdicionalManager {

	private static final String FECHA_DEFAULT_PADRON = "01011901";

    /** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaCreditoAdicionalManagerImpl.class);

	/** The Constant COD_ERROR_CERO. */
	private static final String COD_ERROR_CERO = "00000000";
	
	/** The Constant COD_ERROR_101. */
	private static final String COD_ERROR_101 = "10000101";

	/** The Constant BLANCK_STRING. */
	private static final String BLANCK_STRING = " ";

	/** The Constant EDAD_16. */
	private static final int EDAD_16 = 16;

	/** The Constant EDAD_17. */
	private static final int EDAD_17 = 17;

	/** The Constant EDAD_71. */
	private static final int EDAD_71 = 71;

	/** The Constant TIPOCTA_VISA. */
	public static final String TIPOCTA_VISA = "Cuenta VISA";

	/** The Constant TIPOCTA_AMEX. */
	public static final String TIPOCTA_AMEX = "Cuenta AMEX";

	/** The sesion cliente. */
	@Autowired
	private TarjetaCreditoAdicionalBO tarjetaCreditoAdicionalBO;

	/** The datos solicitante BO. */
	@Autowired
	private DatosSolicitanteTarjetaAdicionalBO datosSolicitanteBO;

	/** The datos selectores service. */
	@Autowired
	private DatosSelectoresBO datosSelectoresBO;

	/** The consulta unidad control DAO. */
	@Autowired
	private ConsultaUnidadControlDAO consultaUnidadControlDAO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mya manager. */
	@Autowired
	private MyaManager myaManager;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/**
	 * Validar la operacion y gestionar el desafio RSA.
	 */
	@Autowired
	private DesafioOperacionRSA<DatosConfirmadosDelSolicitanteView> desafioOperacionRSA;

	/** The valor desafio cambio limite extraccion. */
	@Value("${TRJCOORD.OPERAINDISTINTO.TRJADICIONAL:3}")
	private Integer valorDesafioTrjAdicional;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#getDatosIniciales()
	 */
	@Override
	public Respuesta<SolicitudTarjetaCreditoAdicionalView> getDatosIniciales() {
		Respuesta<SolicitudTarjetaCreditoAdicionalView> respuesta = new Respuesta<SolicitudTarjetaCreditoAdicionalView>();
		SolicitudTarjetaCreditoAdicionalView solicitudTarjetaCreditoAdicionalView = new SolicitudTarjetaCreditoAdicionalView();
		Respuesta<List<TarjetaCandidataDTO>> tarjetasDTO = tarjetaCreditoAdicionalBO.getTarjetasCandidatas();
		if (EstadoRespuesta.OK.equals(tarjetasDTO.getEstadoRespuesta())) {
			List<TarjetaCandidataView> tarjetasCandidatasView = new ArrayList<TarjetaCandidataView>();
			for (TarjetaCandidataDTO tarjetaCandidataDTO : tarjetasDTO.getRespuesta()) {
				tarjetasCandidatasView.add(new TarjetaCandidataView(tarjetaCandidataDTO));
			}
			solicitudTarjetaCreditoAdicionalView.setTarjetasCandidatas(tarjetasCandidatasView);
			respuesta = respuestaFactory.crearRespuestaOk(solicitudTarjetaCreditoAdicionalView);
		} else {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.SOLICITUDES_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
		}
		sesionParametros.resetearDesafioEnCurso();
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#getDatosDelSolicitante(ar.com.santanderrio
	 * .obp.servicios.tarjetacreditoadicional.web.view.
	 * DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteView> getDatosDelSolicitante(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView) {

		Respuesta<DatosSolicitanteView> respuesta = null;
		try {
			DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = new DatosSolicitanteCriterioDTO(
					datosSolicitanteCriterioView);
			Cliente clienteTitular = sesionCliente.getCliente();

			LOGGER.info("Se buscaran los datos del solicitante en Altair.");
			Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO = tarjetaCreditoAdicionalBO
					.getDatosDelSolicitanteAltair(datosSolicitanteCriterioDTO, clienteTitular);

			boolean isPadron = false;
			if (EstadoRespuesta.ERROR.equals(datosSolicitanteDTO.getEstadoRespuesta())) {
				LOGGER.info("No se encontraron los datos en Altair, se buscaran en el Padron.");
				datosSolicitanteDTO = datosSolicitanteBO.getDatosPadronBO(datosSolicitanteCriterioDTO, clienteTitular);
				isPadron = true;
			}

			if (EstadoRespuesta.OK.equals(datosSolicitanteDTO.getEstadoRespuesta())) {
				respuesta = validaSeteaDatosSolicitante(datosSolicitanteCriterioDTO, datosSolicitanteDTO,
						clienteTitular, isPadron);
			} else if (EstadoRespuesta.WARNING.equals(datosSolicitanteDTO.getEstadoRespuesta())) {
				respuesta = respuestaFactory.transformar(null, datosSolicitanteDTO);
			}

		} catch (DAOException e) {
			LOGGER.error("DAOException: Error al obtener datos del solicitante y/o cliente titular", e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.SOLICITUDES_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
		} catch (EdadIncorrectaException ex) {
			LOGGER.error("El cliente no tiene la edad permitida para esta operacion. ", ex);
			respuesta = respuestaFactory.crearRespuestaError(DatosSolicitanteView.class, StringUtils.EMPTY,
					TipoError.EDAD_PARA_OPERAR_INCORRECTA, "1677");
		}
		return respuesta;
	}

	/**
	 * Valida setea datos solicitante.
	 *
	 * @param datosSolicitanteCriterioDTO
	 *            the datos solicitante criterio DTO
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 * @param clienteTitular
	 *            the cliente titular
	 * @param isPadron
	 *            the is padron
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteView> validaSeteaDatosSolicitante(
			DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO, Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO,
			Cliente clienteTitular, boolean isPadron) {
		DatosSolicitanteView datosSolicitanteViewResponse = new DatosSolicitanteView();
		datosSolicitanteViewResponse.setIsPadron(isPadron);
		/**
		 * Si se obtuvo del padrón entonces nup es null y no necesita compararse
		 * con el titular porque no esta en Altair
		 */
		LOGGER.info("Se verifica que el titular no sea el mismo que el adicional.");
		if (!isPadron && StringUtils.equals(clienteTitular.getNup(), datosSolicitanteDTO.getRespuesta().getNup())) {
			return respuestaFactory.crearRespuestaWarning(null, TipoError.PERSONA_MISMO_TITULAR_ADICIONAL,
					CodigoMensajeConstantes.ERROR_MISMA_PERSONA_ADICIONAL_TITULAR);
		}
		LOGGER.info("Se verifica la edad y habilitacion del adicional.");
		datosSolicitanteDTO = datosSolicitanteValidos(datosSolicitanteDTO, datosSolicitanteCriterioDTO);

		/**
		 * Se si se pudo validar que el solicitante esté habilitado para la
		 * operación
		 */
		if (EstadoRespuesta.ERROR.equals(datosSolicitanteDTO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.TARJETA_CREDITO_ADIC_PERSONA_INHABILITADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(DatosSolicitanteView.class, datosSolicitanteDTO.getItemsMensajeRespuesta());
		}

		/** Se consultan suscripción en MYA */
		LOGGER.info("Se consulta subscripcion en MYA.");
		if (EstadoRespuesta.OK.equals(datosSolicitanteDTO.getEstadoRespuesta())
				&& clienteSuscriptoMyaOK(clienteTitular)) {
			setDatosSolicitanteAdicional(datosSolicitanteViewResponse, datosSolicitanteDTO.getRespuesta());
			return respuestaFactory.crearRespuestaOk(datosSolicitanteViewResponse);
		} else if (EstadoRespuesta.WARNING.equals(datosSolicitanteDTO.getEstadoRespuesta())) {
			if (datosSolicitanteDTO.getItemsMensajeRespuesta().get(0).getTipoError()
					.equals(TipoError.PERSONA_FECHA_NACIMIENTO_NO_COINCIDE.getDescripcion())) {
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null,
						datosSolicitanteDTO.getItemsMensajeRespuesta().get(0).getMensaje(),
						datosSolicitanteDTO.getItemsMensajeRespuesta().get(0).getTipoError());
			}
			return respuestaFactory.crearRespuestaWarning(null,
					datosSolicitanteDTO.getItemsMensajeRespuesta().get(0).getMensaje(), "");
		} else {
			return respuestaFactory.crearRespuestaWarning(null, TipoError.SOLICITUDES_ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_SOLICITUDES_GENERICO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#getResultadoMerlin(ar.com.santanderrio.obp
	 * .servicios.tarjetacreditoadicional.entities.DatosDeDomicilio)
	 */
	@Override
	public Respuesta<DomiciliosView> getResultadoMerlin(DatosMerlinInEntity datosDeDomicilio) {
		try {
			LOGGER.info("Se consulta Merlin para verificar la direccion ingresada.");
			DomiciliosDTO domiciliosDTO = tarjetaCreditoAdicionalBO.getResultadoMerlin(sesionCliente.getCliente(),
					datosDeDomicilio);
			DomiciliosView domiciliosView = new DomiciliosView(domiciliosDTO);
			return respuestaFactory.crearRespuestaOk(domiciliosView);
		} catch (MerlinWarningException e) {
			return respuestaDatoInvalido(e.getTipoError());
		} catch (BusinessException e) {
			return respuestaDatoInvalido(TipoError.ERROR_GENERICO);
		}
	}

	/**
	 * Respuesta dato invalido.
	 *
	 * @param tipoError
	 *            the tipo error
	 * @return the respuesta
	 */
	private Respuesta<DomiciliosView> respuestaDatoInvalido(TipoError tipoError) {
		Respuesta<DomiciliosView> respuesta;
		if (TipoError.DUDA_DOMICILIO_ALTURA.equals(tipoError)) {
			respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.DUDA_DOMICILIO_ALTURA,
					CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_AI);
		} else if (TipoError.DUDA_DOMICILIO_LOCALIDAD.equals(tipoError)) {
			respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.DUDA_DOMICILIO_LOCALIDAD,
					CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_LI);
		} else if (TipoError.DUDA_DOMICILIO_CALLE.equals(tipoError)) {
			respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.DUDA_DOMICILIO_CALLE,
					CodigoMensajeConstantes.MENSAJE_ERROR_MERLIN_DUDCPA_CA);
		} else {
			respuesta = respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		return respuesta;
	}

	/**
	 * Validar alta tarjeta de cretido adicional.
	 *
	 * @param datosSolicitanteConDomicilioConfirmadoView
	 *            the datos solicitante con domicilio confirmado view
	 * @return the respuesta
	 */
	private Respuesta<DatosConfirmadosDelSolicitanteView> validarAltaTarjCreditoAdicional(
			DatosConfirmadosDelSolicitanteView datosSolicitanteConDomicilioConfirmadoView) {
		AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
		return desafioOperacionRSA.validarOperacionRSA(datosSolicitanteConDomicilioConfirmadoView,
				valorDesafioTrjAdicional, autentificacionCodEstDTO);
	}

	/**
	 * Asignar estadisticas de autenticacion.
	 */
	private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
		AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
		autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(
				EstadisticasConstants.SOFT_TOKEN_SOLICITUD_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.SOFT_TOKEN_VALIDACION_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.COORDENADAS_SOLICITUD_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.COORDENADAS_VALIDACION_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(
				EstadisticasConstants.OCHO_DIGITOS_SOLICITUD_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(
				EstadisticasConstants.OCHO_DIGITOS_VALIDACION_SOLICITUD_TARJ_CREDITO_ADICIONAL);
		return autentificacionCodEstDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.manager.
	 * ModifLimiteDebitoManager#comprobanteModifLimitesExtraccion(ar.com.
	 * santanderrio.obp.servicios.modificacionlimitedebito.web.view.
	 * ComprobanteDescargaCambioLimiteView)
	 */
	@Override
	public void vaciarDesafio() {
		sesionParametros.resetearDesafioEnCurso();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#altaTarjetaCreditoAdicional(ar.com.
	 * santanderrio.obp.servicios.tarjetacreditoadicional.web.view.
	 * DatosSolicitanteConfirmadoViewResponse)
	 */
	@Override
	public Respuesta<DatosConfirmadosDelSolicitanteView> altaTarjetaCreditoAdicional(
			DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoViewResponse) {
		Respuesta<DatosConfirmadosDelSolicitanteView> respuesta = validarAltaTarjCreditoAdicional(
				datosSolicitanteConfirmadoViewResponse);

		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}

		if (sesionParametros.getContador() == null) {
			sesionParametros.setContador(new ContadorIntentos(2));
		}

		Cliente cliente = sesionCliente.getCliente();

		try {
			/** Si la info viene del padrón doy de alta en Altair */
			if (datosSolicitanteConfirmadoViewResponse.getNup() == null) {
				LOGGER.info("Adicional no existe en Altair, se dara de alta la persona fisica.");
				AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity = buildDatosAltaCanalesAutomaticosInDTO(
						datosSolicitanteConfirmadoViewResponse, cliente);
				Respuesta<AltaCanalAutomaticoOutEntity> altaCanalAutomaticoOutEntity = datosSolicitanteBO
						.ejecutarAltaCanalesAutomaticos(altaCanalAutomaticoInEntity, cliente);
				if (!EstadoRespuesta.OK.equals(altaCanalAutomaticoOutEntity.getEstadoRespuesta())) {
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_GENERICO);
				}
				datosSolicitanteConfirmadoViewResponse
						.setNup(altaCanalAutomaticoOutEntity.getRespuesta().getNumeroDelCliente());
			}
			LOGGER.info("Se da dara de alta la tarjeta de credito adicional.");
			Respuesta<ComprobanteAltaTarjCredAdicionalDTO> comprobanteAltaTarjCredAdicionalDTO = tarjetaCreditoAdicionalBO
					.altaTarjetaCreditoAdicional(datosSolicitanteConfirmadoViewResponse, sesionCliente.getCliente());
			grabarEstadistica(comprobanteAltaTarjCredAdicionalDTO);
			respuesta = armaRespuesta(comprobanteAltaTarjCredAdicionalDTO);
		} catch (DAOException e) {
			LOGGER.error("altaTarjetaCreditoAdicional - DAOException", e);
		}
		sesionParametros.resetearDesafioEnCurso();
		return respuesta;
	}

	/**
	 * Arma respuesta.
	 *
	 * @param comprobanteAltaTarjCredAdicionalDTO
	 *            the comprobante alta tarj cred adicional DTO
	 * @return the respuesta
	 */
	private Respuesta<DatosConfirmadosDelSolicitanteView> armaRespuesta(
			Respuesta<ComprobanteAltaTarjCredAdicionalDTO> comprobanteAltaTarjCredAdicionalDTO) {

		ComprobanteAltaTarjCredAdicionalView comprobanteView = new ComprobanteAltaTarjCredAdicionalView(
				comprobanteAltaTarjCredAdicionalDTO.getRespuesta());
		DatosConfirmadosDelSolicitanteView datosConfSolicitanteView = new DatosConfirmadosDelSolicitanteView();
		datosConfSolicitanteView.setComprobante(comprobanteView);
		Respuesta<DatosConfirmadosDelSolicitanteView> respuesta = new Respuesta<DatosConfirmadosDelSolicitanteView>();
		respuesta.setRespuesta(datosConfSolicitanteView);
		respuesta.setEstadoRespuesta(datosConfSolicitanteView.getComprobante().getEstadoRespuesta());
		respuesta.setItemMensajeRespuesta(comprobanteAltaTarjCredAdicionalDTO.getItemsMensajeRespuesta());
		sesionParametros.setComprobanteTarjetaCredAdic(comprobanteView);
		return respuesta;
	}

	/**
	 * Grabar estadistica.
	 *
	 * @param comprobanteAltaTarjCredAdicionalDTO
	 *            the comprobante alta tarj cred adicional DTO
	 */
	private void grabarEstadistica(Respuesta<ComprobanteAltaTarjCredAdicionalDTO> comprobanteAltaTarjCredAdicionalDTO) {
		for (TarjetaAdicionalSolicitadaDTO tarjetaAdicionalSolicitadaDTO : comprobanteAltaTarjCredAdicionalDTO
				.getRespuesta().getTarjetasAdicionalesSolicitadas()) {
			String resultado = tarjetaAdicionalSolicitadaDTO.isConError()
					? EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR : EstadisticasConstants.CODIGO_ESTADISTICAS_OK;

			String codigoTransaccion = TIPOCTA_VISA.equals(tarjetaAdicionalSolicitadaDTO.getNombreTarjeta())
					? EstadisticasConstants.TARJETA_CREDITO_ADIC_VISA_ALTA
					: (TIPOCTA_AMEX.equals(tarjetaAdicionalSolicitadaDTO.getNombreTarjeta())
							? EstadisticasConstants.TARJETA_CREDITO_ADIC_AMEX_ALTA
							: EstadisticasConstants.TARJETA_CREDITO_ADIC_MASTER_ALTA);
			estadisticaManager.add(codigoTransaccion, resultado);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#descargaComprobanteAltaTarjeta()
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteAltaTarjeta() {
		ComprobanteAltaTarjCredAdicionalView view = sesionParametros.getComprobanteTarjetaCredAdic();
		Respuesta<Reporte> reporteRespuesta = tarjetaCreditoAdicionalBO.generarComprobante(view);
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
	 * Builds the datos alta canales automaticos in DTO.
	 *
	 * @param datosSolicitanteDTO
	 *            the datos solicitante confirmado DTO
	 * @param cliente
	 *            the cliente
	 * @return the alta canal automatico in entity
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SinAccesoALaInformacionException
	 *             the sin acceso A la informacion exception
	 */
	private AltaCanalAutomaticoInEntity buildDatosAltaCanalesAutomaticosInDTO(
			DatosConfirmadosDelSolicitanteView datosSolicitanteDTO, Cliente cliente) throws DAOException {
		AltaCanalAutomaticoInEntity altaCanalInEntity = new AltaCanalAutomaticoInEntity();

		altaCanalInEntity.setTipoDocumentoTIT(datosSolicitanteDTO.getDocumentoTipo());
		altaCanalInEntity.setNumDocumentoTIT(StringUtils.remove(datosSolicitanteDTO.getDocumentoNro(), '.'));
		altaCanalInEntity.setApellido(datosSolicitanteDTO.getApellido());
		altaCanalInEntity.setNombre(datosSolicitanteDTO.getNombre());
		altaCanalInEntity.setEstadoCivil(datosSolicitanteDTO.getEstadoCivil());
		if (StringUtils.startsWithIgnoreCase(datosSolicitanteDTO.getSexo(), "M")) {
			altaCanalInEntity.setSexo("H");
		} else if (StringUtils.startsWithIgnoreCase(datosSolicitanteDTO.getSexo(), "F")) {
			altaCanalInEntity.setSexo("M");
		}

		altaCanalInEntity.setFecNacimiento(datosSolicitanteDTO.getFechaNacimiento());
		altaCanalInEntity.setPaisNacimiento(datosSolicitanteDTO.getPaisNacimiento());
		altaCanalInEntity.setNacionalidad(datosSolicitanteDTO.getNacionalidad());
		altaCanalInEntity.setDomPartCalle(datosSolicitanteDTO.getCalle());
		altaCanalInEntity.setDomPartNro(datosSolicitanteDTO.getNro());

		altaCanalInEntity.setDomPartCPPatente(StringUtils.substring(datosSolicitanteDTO.getCp(), 0, 1));
		altaCanalInEntity.setDomPartCPCodPostal(StringUtils.substring(datosSolicitanteDTO.getCp(), 1, 5));
		altaCanalInEntity.setDomPartCPManzana(StringUtils.substring(datosSolicitanteDTO.getCp(), 5, 8));

		altaCanalInEntity.setDomPartLocalidad(datosSolicitanteDTO.getLocalidad());
		altaCanalInEntity.setDomPartProvincia(datosSolicitanteDTO.getProvincia());
		altaCanalInEntity.setTipoInscripcionCuitCuil("CUIL".equals(datosSolicitanteDTO.getTipoCuitCuil()) ? "L" : "T");
		altaCanalInEntity.setNroInscripcion(datosSolicitanteDTO.getCuit());
		altaCanalInEntity.setDomCodPais(datosSolicitanteDTO.getNacionalidad());

		obtenerConsultaUnidadControlInEntity(cliente, altaCanalInEntity);

		altaCanalInEntity.setTelefono1DDN(datosSolicitanteDTO.getTelediscado());
		altaCanalInEntity.setTelefono1Caracteristica(StringUtils.substring(datosSolicitanteDTO.getTelefono(), 0, -4));
		altaCanalInEntity.setTelefono1Numero(StringUtils.substring(datosSolicitanteDTO.getTelefono(), -4));

		// OJO! Esta no es la sucursal principal.. la suc principal, hay que
		// obtenerla con una logica que esta
		// desarrollada en com.rio.ijhb.fep.login.Identificacion del OBP
		// productivo, linea 315
		altaCanalInEntity.setSucursal(cliente.getCuentas().get(0).getNroSucursal());

		return altaCanalInEntity;
	}

	/**
	 * Obtener consulta unidad control in entity.
	 *
	 * @param cliente
	 *            the cliente
	 * @param altaCanalAutomaticoInEntity
	 *            the alta canal automatico in entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public void obtenerConsultaUnidadControlInEntity(Cliente cliente,
			AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity) throws DAOException {
		ConsultaUnidadControlInEntity in = new ConsultaUnidadControlInEntity();
		in.setCliente(cliente);
		in.setNup(cliente.getNup());
		in.setTipoDocumento("");
		in.setNroDocumento("");
		ConsultaUnidadControlOutEntity out = consultaUnidadControlDAO.consultaUC(in);
		altaCanalAutomaticoInEntity.setBanca(out.getBanca());
		altaCanalAutomaticoInEntity.setDivision(out.getDivision());
		altaCanalAutomaticoInEntity.setTeamLeader(out.getTeamLeader());
		altaCanalAutomaticoInEntity.setEjecutivoCta(out.getEjecutivoDeCuenta());
		altaCanalAutomaticoInEntity.setJefeArea(out.getJefeDeArea());
		altaCanalAutomaticoInEntity.setGerente(out.getGerente());
	}

	/**
	 * Estado cliente OK.
	 *
	 * @param cliente
	 *            the cliente
	 * @return true, if successful
	 */
	private boolean clienteSuscriptoMyaOK(Cliente cliente) {
		boolean respuesta = false;
		Respuesta<CredencialesMya> estadoCliente = myaManager.obtenerEstadoMya(cliente);
		CredencialesMya credencialesMya = estadoCliente.getRespuesta();
		if (!"NS".equals(credencialesMya.getClienteEstado())
				&& EstadoRespuesta.OK.equals(estadoCliente.getEstadoRespuesta())) {
			respuesta = true;
		}
		return respuesta;
	}

	/**
	 * Datos solicitante validos.
	 *
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 * @param datosSolicitanteCriterioDTO
	 *            the datos solicitante criterio view
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteDTO> datosSolicitanteValidos(Respuesta<DatosSolicitanteDTO> datosSolicitanteDTO,
			DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO) {
		Respuesta<DatosSolicitanteDTO> respuesta = datosSolicitanteDTO;
		int edad;
		try {
		    if(datosSolicitanteDTO.getRespuesta().getFechaNacimiento() !=  null && FECHA_DEFAULT_PADRON.equals(datosSolicitanteDTO.getRespuesta().getFechaNacimiento())) {
                datosSolicitanteDTO.getRespuesta().setFechaNacimiento(FechaUtils.modificarFormatoFechas(datosSolicitanteCriterioDTO.getFechaNacimiento(), "dd/MM/yyyy", "ddMMyyyy"));
            }
			edad = TarjCredAdicUtils.calculaEdad(datosSolicitanteDTO.getRespuesta().getFechaNacimiento());
			if (!TarjCredAdicUtils.fechasIguales(datosSolicitanteDTO.getRespuesta().getFechaNacimiento(),
					FechaUtils.modificarFormatoFechas(datosSolicitanteCriterioDTO.getFechaNacimiento(), "dd/MM/yyyy",
					                "ddMMyyyy"))) {
				respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.PERSONA_FECHA_NACIMIENTO_NO_COINCIDE,
						CodigoMensajeConstantes.ERROR_FECHA_NACIMIENTO_NO_COINCIDE);
				/** Valida si el solicitante no es titular **/
			} else if (edad < EDAD_16) {
				estadisticaManager.add(EstadisticasConstants.TARJETA_CREDITO_ADIC_MENOR_16,
						EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
				respuesta = respuestaFactory.crearRespuestaOk(datosSolicitanteDTO.getRespuesta());
			} else if (edad <= EDAD_17) {
				respuestaFactory.crearRespuestaOk(datosSolicitanteDTO.getRespuesta());
			} else if (edad <= EDAD_71 && !this
					.esPersonaHabilitada(getConsultaInhabilitadosInEntity(datosSolicitanteDTO.getRespuesta()))) {
				respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.PERSONA_INHABILITADA,
						CodigoMensajeConstantes.ERROR_PERSONA_INHABILITADA);
			}

			return respuesta;
		} catch (Exception e) {
			LOGGER.error("Error no contemplado", e);
			estadisticaManager.add(EstadisticasConstants.TARJETA_CREDITO_ADIC_PERSONA_INHABILITADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("errorNoContemplado", TipoError.NO_CONTEMPLADO_TJ_ADICIONAL,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Gets the consulta inhabilitados in entity.
	 *
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 * @return the consulta inhabilitados in entity
	 * @throws ParseException
	 *             the parse exception
	 */
	private ConsultaInhabilitadosInEntity getConsultaInhabilitadosInEntity(DatosSolicitanteDTO datosSolicitanteDTO)
			throws ParseException {
		ConsultaInhabilitadosInEntity consultaInhabilitadosInEntity = new ConsultaInhabilitadosInEntity();
		consultaInhabilitadosInEntity
				.setApellido(StringUtils.leftPad(datosSolicitanteDTO.getApellido(), 30, BLANCK_STRING));
		consultaInhabilitadosInEntity
				.setNombre(StringUtils.leftPad(datosSolicitanteDTO.getNombre(), 30, BLANCK_STRING));
		consultaInhabilitadosInEntity.setSexo(datosSolicitanteDTO.getSexo());
		consultaInhabilitadosInEntity.setFechaNac(MonederoUtils.formatearyyyyMMdd(
				FechaUtils.modificarFormatoFechas(datosSolicitanteDTO.getFechaNacimiento(), "ddMMyyyy", "dd/MM/yyyy")));
		consultaInhabilitadosInEntity.setTipoDoc(datosSolicitanteDTO.getDocumentoTipo());
		consultaInhabilitadosInEntity.setNroDoc(StringUtils.leftPad(datosSolicitanteDTO.getDocumentoNro(), 11, "0"));

		consultaInhabilitadosInEntity.setCliente(sesionCliente.getCliente());
		return consultaInhabilitadosInEntity;
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
		ConsultaInhabilitadosOutEntity consultaInhabilitadosOutEntity = tarjetaCreditoAdicionalBO
				.esPersonaHabilitada(consultaInhabilitadosInEntity);
		if ((consultaInhabilitadosOutEntity != null
				&& COD_ERROR_CERO.equals(consultaInhabilitadosOutEntity.getCodigoRetornoExtendido())) 
				|| (consultaInhabilitadosOutEntity != null
						&& COD_ERROR_101.equals(consultaInhabilitadosOutEntity.getCodigoRetornoExtendido()))) {
			// SI CODIGO COD_ERROR ES 101, INDICA QUE ES UN NO CLIENTE Y LE PERMITIMOS CONTINUAR
			estadisticaManager.add(EstadisticasConstants.TARJETA_CREDITO_ADIC_PERSONA_INHABILITADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuesta = true;
		} else {
			estadisticaManager.add(EstadisticasConstants.TARJETA_CREDITO_ADIC_PERSONA_INHABILITADA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuesta;
	}

	/**
	 * Sets the datos solicitante adicional.
	 *
	 * @param datosSolicitanteViewResponse
	 *            the datos solicitante view response
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 */
	private void setDatosSolicitanteAdicional(DatosSolicitanteView datosSolicitanteViewResponse,
			DatosSolicitanteDTO datosSolicitanteDTO) {
		datosSolicitanteViewResponse.setNup(datosSolicitanteDTO.getNup());
		datosSolicitanteViewResponse.setDocumentoTipo(TipoDocumentoDescripcionEnum
				.obtenerTipoDocumentoDescripcion(datosSolicitanteDTO.getDocumentoTipo().trim()).getDescripcion());
		datosSolicitanteViewResponse
				.setDocumentoNro(ISBANStringUtils.eliminarCeros(datosSolicitanteDTO.getDocumentoNro()));
		datosSolicitanteViewResponse.setApellido(datosSolicitanteDTO.getApellido().trim());
		datosSolicitanteViewResponse.setNombre(datosSolicitanteDTO.getNombre().trim());
		datosSolicitanteViewResponse.setFechaNacimiento(
				FechaUtils.modificarFormatoFechas(datosSolicitanteDTO.getFechaNacimiento(), "ddMMyyyy", "dd/MM/yyyy"));
		setPaisDeNacimiento(datosSolicitanteViewResponse, datosSolicitanteDTO);
		datosSolicitanteViewResponse.setSexo(datosSolicitanteDTO.getSexo());
		setEstadoCivil(datosSolicitanteViewResponse, datosSolicitanteDTO);
		setNacionalidad(datosSolicitanteViewResponse, datosSolicitanteDTO);
		datosSolicitanteViewResponse.setCuitCuil(datosSolicitanteDTO.getCuitCuil());
		datosSolicitanteViewResponse.setCuit(datosSolicitanteDTO.getCuitCuilNroIns());

		datosSolicitanteViewResponse.setProvincia(getProvincias(datosSelectoresBO.obtenerProvincias()));

		if (datosSolicitanteDTO.getNup() != null) {
			datosSolicitanteViewResponse.setCalle(datosSolicitanteDTO.getCalle().trim());
			datosSolicitanteViewResponse.setCalleNro(datosSolicitanteDTO.getCalleNro().trim());
			datosSolicitanteViewResponse.setPiso(datosSolicitanteDTO.getPiso().trim());
			datosSolicitanteViewResponse.setDepto(datosSolicitanteDTO.getDepto().trim());
			datosSolicitanteViewResponse.setLocalidad(datosSolicitanteDTO.getLocalidad().trim());
			datosSolicitanteViewResponse.setCp(datosSolicitanteDTO.getCp().trim());
			datosSolicitanteViewResponse.setTelefono(datosSolicitanteDTO.getTelefono().trim());
		}
	}

	/**
	 * Sets the pais de nacimiento.
	 *
	 * @param datosSolicitanteViewResponse
	 *            the datos solicitante view response
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 */
	private void setPaisDeNacimiento(DatosSolicitanteView datosSolicitanteViewResponse,
			DatosSolicitanteDTO datosSolicitanteDTO) {
		if (datosSolicitanteDTO.getPaisNacimiento() == null) {
			datosSolicitanteViewResponse
					.setPaisNacimiento(dtoToViewPaises(datosSelectoresBO.obtenerPaisesDeNacimiento()));
		} else {
			List<PaisDeNacimientoView> paises = new ArrayList<PaisDeNacimientoView>();
			PaisDeNacimientoView paisDeNacimientoView = new PaisDeNacimientoView();
			paisDeNacimientoView.setId(datosSolicitanteDTO.getIdPaisNacimiento());
			paisDeNacimientoView.setDescripcion(datosSolicitanteDTO.getPaisNacimiento());
			paises.add(paisDeNacimientoView);
			datosSolicitanteViewResponse.setPaisNacimiento(paises);
		}
	}

	/**
	 * Sets the estado civil.
	 *
	 * @param datosSolicitanteViewResponse
	 *            the datos solicitante view response
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 */
	private void setEstadoCivil(DatosSolicitanteView datosSolicitanteViewResponse,
			DatosSolicitanteDTO datosSolicitanteDTO) {
		if (datosSolicitanteDTO.getEstadoCivil() == null) {
			datosSolicitanteViewResponse.setEstadoCivil(dtoToViewEstadoCivil(datosSelectoresBO.obtenerEstadoCivil()));
		} else {
			List<EstadoCivilView> estadoCivil = new ArrayList<EstadoCivilView>();
			EstadoCivilView estadoCivilView = new EstadoCivilView();
			estadoCivilView.setId("0");
			estadoCivilView.setDescripcion(datosSolicitanteDTO.getEstadoCivil());
			estadoCivil.add(estadoCivilView);
			datosSolicitanteViewResponse.setEstadoCivil(estadoCivil);
		}
	}

	/**
	 * Sets the nacionalidad.
	 *
	 * @param datosSolicitanteViewResponse
	 *            the datos solicitante view response
	 * @param datosSolicitanteDTO
	 *            the datos solicitante DTO
	 */
	private void setNacionalidad(DatosSolicitanteView datosSolicitanteViewResponse,
			DatosSolicitanteDTO datosSolicitanteDTO) {
		if (datosSolicitanteDTO.getNacionalidad() == null) {
			datosSolicitanteViewResponse
					.setNacionalidad(dtoToViewNacionalidad(datosSelectoresBO.obtenerNacionalidad()));
		} else {
			List<NacionalidadView> nacionalidad = new ArrayList<NacionalidadView>();
			NacionalidadView nacionalidadView = new NacionalidadView();
			nacionalidadView.setId(datosSolicitanteDTO.getIdNacionalidad());
			nacionalidadView.setDescripcion(datosSolicitanteDTO.getNacionalidad());
			nacionalidad.add(nacionalidadView);
			datosSolicitanteViewResponse.setNacionalidad(nacionalidad);
		}
	}

	/**
	 * Dto to view paises.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	@Override
	public List<PaisDeNacimientoView> dtoToViewPaises(List<Opcion> lista) {
		List<PaisDeNacimientoView> listaRespuesta = new ArrayList<PaisDeNacimientoView>();
		for (Opcion opcion : lista) {
			PaisDeNacimientoView importesARecargarView = new PaisDeNacimientoView();
			importesARecargarView.setId(opcion.getId());
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Dto to view sexo.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	@Override
	public List<SexoView> dtoToViewSexo(List<Opcion> lista) {
		List<SexoView> listaRespuesta = new ArrayList<SexoView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			SexoView importesARecargarView = new SexoView();
			importesARecargarView.setId(Integer.toString(i));
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Dto to view estado civil.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	@Override
	public List<EstadoCivilView> dtoToViewEstadoCivil(List<Opcion> lista) {
		List<EstadoCivilView> listaRespuesta = new ArrayList<EstadoCivilView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			EstadoCivilView importesARecargarView = new EstadoCivilView();
			importesARecargarView.setId(Integer.toString(i));
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Dto to view nacionalidad.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<NacionalidadView> dtoToViewNacionalidad(List<Opcion> lista) {
		List<NacionalidadView> listaRespuesta = new ArrayList<NacionalidadView>();
		for (Opcion opcion : lista) {
			NacionalidadView importesARecargarView = new NacionalidadView();
			importesARecargarView.setId(opcion.getId());
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Gets the provincias.
	 *
	 * @param lista
	 *            the lista
	 * @return the provincias
	 */
	private List<ProvinciaView> getProvincias(List<Opcion> lista) {
		List<ProvinciaView> listaRespuesta = new ArrayList<ProvinciaView>();
		for (Opcion opcion : lista) {
			ProvinciaView provinciaView = new ProvinciaView();
			provinciaView.setId(opcion.getId());
			provinciaView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(provinciaView);
		}
		return listaRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.
	 * TarjetaCreditoAdicionalManager#continuarSolicitud(ar.com.santanderrio.
	 * obp.
	 * servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView)
	 */
	@Override
	public Respuesta<String> continuarSolicitud(DatosAdicionalSolicitudView datosAdicionalSolicitudView) {
		try {
			if (comprobarSolicitanteMenor(datosAdicionalSolicitudView.getFechaNacAdicional())) {
				String legal = obtenerLegalesMenor(datosAdicionalSolicitudView);
				if (StringUtils.isNotBlank(legal)) {
					return respuestaFactory.crearRespuestaOk(legal);
				} else {
					sesionParametros.resetearDesafioEnCurso();
					return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
							CodigoMensajeConstantes.ERROR_GENERICO);
				}
			}
		} catch (ParseException e) {
			LOGGER.error("Error al parsear la fecha de nacimiento del solicitante");
			sesionParametros.resetearDesafioEnCurso();
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_GENERICO);
		}
		return respuestaFactory.crearRespuestaOk(StringUtils.EMPTY);
	}

	/**
	 * Obtener legales menor.
	 *
	 * @param datosAdicionalSolicitudView
	 *            the datos adicional solicitud view
	 * @return the string
	 */
	private String obtenerLegalesMenor(DatosAdicionalSolicitudView datosAdicionalSolicitudView) {
		List<DatosTarjetaAdicionalView> tarjetasAdicionales = datosAdicionalSolicitudView.getTarjetas();
		if (CollectionUtils.isNotEmpty(tarjetasAdicionales)) {
			if (tarjetasAdicionales.size() == 1) {
				return obtenerLegalesMenorUnaTarjeta(datosAdicionalSolicitudView);
			} else {
				return obtenerLegalesMenorDosTarjeta(datosAdicionalSolicitudView);
			}
		} else {
			return null;
		}
	}

	/**
	 * Obtener legales menor una tarjeta.
	 *
	 * @param datosSolicitanteView
	 *            the datos solicitante view
	 * @return the string
	 */
	private String obtenerLegalesMenorUnaTarjeta(DatosAdicionalSolicitudView datosSolicitanteView) {
		Cliente cliente = sesionCliente.getCliente();
		String legal = legalBO
				.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_TERMINOS_Y_CONDICIONES_ADICIONAL_MENORES);
		String nombreTitular = cliente.getNombre() + " " + cliente.getApellido1();
		String dniTitular = Long.valueOf(cliente.getDni()).toString();
		String marcaTarjeta = datosSolicitanteView.getTarjetas().get(0).getMarcaTarjeta();
		String numeroTarjeta = datosSolicitanteView.getTarjetas().get(0).getNroTarjeta();
		String nombreAdicional = datosSolicitanteView.getNombreApellidoAdicional();
		String dniAdicional = datosSolicitanteView.getNroDocAdicional();
		String fechaNacAdicional = datosSolicitanteView.getFechaNacAdicional();

		return MessageFormat.format(legal, nombreTitular, dniTitular, marcaTarjeta, numeroTarjeta, nombreAdicional,
				dniAdicional, fechaNacAdicional);
	}

	/**
	 * Obtener legales menor dos tarjeta.
	 *
	 * @param datosSolicitanteView
	 *            the datos solicitante view
	 * @return the string
	 */
	private String obtenerLegalesMenorDosTarjeta(DatosAdicionalSolicitudView datosSolicitanteView) {
		Cliente cliente = sesionCliente.getCliente();
		String legal = legalBO
				.obtenerLegalesPorCodigo(CodigoMensajeConstantes.LEGAL_TERMINOS_Y_CONDICIONES_ADICIONAL_MENORES_VARIAS);
		String nombreTitular = cliente.getNombre() + " " + cliente.getApellido1();
		String dniTitular = Long.valueOf(cliente.getDni()).toString();
		String marcaTarjeta1 = datosSolicitanteView.getTarjetas().get(0).getMarcaTarjeta();
		String numeroTarjeta1 = datosSolicitanteView.getTarjetas().get(0).getNroTarjeta();
		String marcaTarjeta2 = datosSolicitanteView.getTarjetas().get(1).getMarcaTarjeta();
		String numeroTarjeta2 = datosSolicitanteView.getTarjetas().get(1).getNroTarjeta();
		String nombreAdicional = datosSolicitanteView.getNombreApellidoAdicional();
		String dniAdicional = datosSolicitanteView.getNroDocAdicional();
		String fechaNacAdicional = datosSolicitanteView.getFechaNacAdicional();

		return MessageFormat.format(legal, nombreTitular, dniTitular, marcaTarjeta1, numeroTarjeta1, marcaTarjeta2,
				numeroTarjeta2, nombreAdicional, dniAdicional, fechaNacAdicional);
	}

	/**
	 * Comprobar solicitante menor.
	 *
	 * @param fechaNacimiento
	 *            the fecha nacimiento
	 * @return true, if successful
	 * @throws ParseException
	 *             the parse exception
	 */
	private boolean comprobarSolicitanteMenor(String fechaNacimiento) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -18);
		Date dieciochoAnios = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNacimiento = format.parse(fechaNacimiento);

		return dateNacimiento.after(dieciochoAnios);
	}

}
