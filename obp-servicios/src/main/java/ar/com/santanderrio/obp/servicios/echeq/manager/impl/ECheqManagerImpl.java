package ar.com.santanderrio.obp.servicios.echeq.manager.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.generated.webservices.echeq.IMF;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO;
import ar.com.santanderrio.obp.servicios.echeq.common.ECheqAmcoEstados;
import ar.com.santanderrio.obp.servicios.echeq.common.IOperacionECheq;
import ar.com.santanderrio.obp.servicios.echeq.common.OperacionEcheqFactory;
import ar.com.santanderrio.obp.servicios.echeq.common.TiposGrilla;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConsultaImporteTotalesInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.EcheqRSADTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.TotalesECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.echeq.view.BeneficiarioView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAdhesionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarOperacionECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.CuentaView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqInView;
import ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoECheqOutView;
import ar.com.santanderrio.obp.servicios.echeq.view.IngresoOperacionECheqView;
import ar.com.santanderrio.obp.servicios.echeq.view.TotalesECheqOutView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class ECheqManagerImpl.
 */
@Component
public class ECheqManagerImpl implements ECheqManager {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ECheqManagerImpl.class);

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The echeqBO. */
    @Autowired
    private ECheqBO eCheqBO;

	/** The contratosBO. */
	@Autowired
	private ContratoBO contratosBO;

	/** The cuentaBO. */
	@Autowired
	CuentaBO cuentaBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The desafio operacion RSA. */
    @Autowired
    private DesafioOperacionRSA<ConfirmarAltaEndosarECheqView> desafioOperacionRSA;

    /** The valor desafio Echeq Alta. */
    @Value("${TRJCOORD.OPERAINDISTINTO.ECHEQ_ALTA:2}")
    private Integer valorDesafioEcheqAlta;

    /** The valor desafio Echeq Endosar. */
    @Value("${TRJCOORD.OPERAINDISTINTO.ECHEQ_ENDOSAR:2}")
    private Integer valorDesafioEcheqEndosar;

    /** The legalBO. */
    @Autowired
    private LegalBO legalBO;

    /** The mensajeBO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The selectoresBO. */
    @Autowired
    private DatosSelectoresBO selectoresBO;

    /** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/** The echeq hora desde. */
	@Value("${ECHEQ.HORA.DESDE}")
	private String echeqHoraDesde;

	/** The echeq hora hasta. */
	@Value("${ECHEQ.HORA.HASTA}")
	private String echeqHoraHasta;

	/** The cuentas habilitadas. */
	@Value("#{'${ECHEQ.TIPO.CUENTAS.HABILITADAS}'.split(',')}")
	private List<Integer> cuentasHabilitadas;

	/** The rango fecha pago alta. */
	@Value("${ECHEQ.RANGO.FECHA.PAGO.ALTA}")
	private String rangoFechaPagoAlta;

	/** The rango fecha emision default. */
	@Value("${ECHEQ.RANGO.FECHA.EMISION.CONSULTA.TOTALES}")
	private String rangoFechaEmisionDefault;

	/** The Constant CONTRATO_ACEPTADO. */
	private static final String CONTRATO_ACEPTADO = "1";

	/** The Constant EMPTY. */
	private static final String EMPTY = "";

	/** The intervalo buscador. */
	@Value("#{'${ECEHQ.INTERVALO.BUSCADOR}'.split('\\|')}")
	private List<Integer> intervaloBuscador;

	/** The echeq hora desde. */
	@Value("${ECHEQ.RANGO.FECHA.BUSCADOR}")
	private Integer rangoFechaBuscador;

	/** The echeq hora desde. */
	@Value("${ECHEQ.HABILITA.TOTALES:true}")
	private Boolean habilitaTotales;

	/** The echeq hora desde. */
	@Value("${ECHEQ.HABILITA.BUSCADOR:true}")
	private Boolean habilitaBuscador;

	/** The echeq hora desde. */
	@Value("${ECHEQ.HABILITA.EMITIR:true}")
	private Boolean habilitaEmitir;

	@Autowired
	private AutentificacionManager autentificacionManager;

	@Autowired
	private ClienteBO clienteBO;

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#obtenerECheqs(ar.com.santanderrio.obp.servicios.echeq.view.GrillaECheqInView)
	 */
	@Override
	public Respuesta<GrillaECheqOutView> obtenerECheqs(GrillaECheqInView grillaECheqInView) {

		if (!validarCuentaOperativa(sesionCliente.getCliente())) {
			LOGGER.error("ECHEQ - Cliente sin cuentas operativas");
            estadisticaManager.add(EstadisticasConstants.CODIGO_INGRESO_ECHEQ,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_INGRESO, CodigoMensajeConstantes.ECHEQ_ERROR_SIN_CUENTAS_OPERATIVAS);
		}

		GrillaECheqOutView grillaECheqOutView = new GrillaECheqOutView();
		String rangoFechaEmision = grillaECheqInView.getFechaRangoEmision() != null ? grillaECheqInView.getFechaRangoEmision() : rangoFechaEmisionDefault;
		grillaECheqOutView.setCantidadDiasDeLaConsulta(habilitaTotales ? rangoFechaEmision : null);
		Respuesta<TotalesECheqOutDTO> respuestaTotales = obtenerRespuestaConsultaImporteTotales(grillaECheqOutView, grillaECheqInView, grillaECheqInView.getjSessionId()); 

		GrillaECheqInDTO grillaECheqInDTO = createGrillaECheqInDTO(grillaECheqInView);
		Respuesta<GrillaECheqOutDTO> respuestaGrilla = eCheqBO.obtenerECheqs(grillaECheqInDTO);
		if (EstadoRespuesta.OK.equals(respuestaGrilla.getEstadoRespuesta())) {
			BeanUtils.copyProperties(respuestaGrilla.getRespuesta(), grillaECheqOutView);
		} else if (EstadoRespuesta.WARNING.equals(respuestaGrilla.getEstadoRespuesta()) && grillaECheqInView.getIngresoDesdeEmitidos()) {
			String legal = legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIDOS_PENDIENTE);
			grillaECheqOutView.setLegal(legal);
		}
		return crearRespuestaObtenerECheqs(respuestaTotales, respuestaGrilla, grillaECheqOutView, grillaECheqInView);
	}

	private Respuesta<TotalesECheqOutDTO> obtenerRespuestaConsultaImporteTotales(GrillaECheqOutView grillaECheqOutView, GrillaECheqInView grillaECheqInView, String jSessionId) {
		if (habilitaTotales) {
			Cliente cliente = sesionCliente.getCliente();
			ConsultaImporteTotalesInDTO consultaImporteTotalesInDTO = new ConsultaImporteTotalesInDTO(cliente.getNroDocCnsDocXNup(),
					grillaECheqOutView.getCantidadDiasDeLaConsulta(), jSessionId);
			Respuesta<TotalesECheqOutDTO> respuestaTotales = eCheqBO.obtenerTotales(consultaImporteTotalesInDTO);
			if (EstadoRespuesta.OK.equals(respuestaTotales.getEstadoRespuesta())) {
				grillaECheqOutView.setTotalesEcheq(new TotalesECheqOutView());
				BeanUtils.copyProperties(respuestaTotales.getRespuesta(), grillaECheqOutView.getTotalesEcheq());
			}
			return respuestaTotales;			
		} else {
			return respuestaFactory.crearRespuestaOk(new TotalesECheqOutDTO());
		}
	}

	private Respuesta<GrillaECheqOutView> crearRespuestaObtenerECheqs(Respuesta<TotalesECheqOutDTO> respuestaTotales,
			Respuesta<GrillaECheqOutDTO> respuestaGrilla, GrillaECheqOutView grillaECheqOutView, GrillaECheqInView grillaECheqInView) {
		//SI FALLA GRILLA ERROR TOTAL
		if (EstadoRespuesta.ERROR.equals(respuestaGrilla.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
		} else if (EstadoRespuesta.OK.equals(respuestaGrilla.getEstadoRespuesta())) {
			if (EstadoRespuesta.OK.equals(respuestaTotales.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaOk(GrillaECheqOutView.class, grillaECheqOutView);				
			}
			return respuestaFactory.crearRespuestaWarning(GrillaECheqOutView.class, grillaECheqOutView, TipoError.ECHEQ_WARNING_TOTALES_GRILLA, EMPTY, EMPTY);
		} else {
			String codigoMensaje = null;
			if (grillaECheqInView.getIngresoDesdeEmitidos()) {
				codigoMensaje = CodigoMensajeConstantes.ECHEQ_SIN_DATOS_GRILLA_EMITIDOS;
			} else if (grillaECheqInView.getIngresoDesdeEndosados()) {
				codigoMensaje = CodigoMensajeConstantes.ECHEQ_SIN_DATOS_GRILLA_ENDOSADOS;
			} else {
				codigoMensaje = CodigoMensajeConstantes.ECHEQ_SIN_DATOS_GRILLA_RECIBIDOS;
			}
			if (EstadoRespuesta.OK.equals(respuestaTotales.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(GrillaECheqOutView.class, grillaECheqOutView, TipoError.ECHEQ_WARNING_SIN_DATOS_GRILLA, codigoMensaje, EMPTY);
			}
			return respuestaFactory.crearRespuestaWarning(EMPTY, TipoError.ECHEQ_WARNING_SIN_DATOS_GRILLA_ERROR_TOTALES, codigoMensaje);
		}
	}

	private GrillaECheqInDTO createGrillaECheqInDTO(GrillaECheqInView grillaECheqInView) {
		GrillaECheqInDTO grillaECheqInDTO = new GrillaECheqInDTO();
		BeanUtils.copyProperties(grillaECheqInView, grillaECheqInDTO);
		return grillaECheqInDTO;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#ingresoModulo()
	 */
	@Override
	public Respuesta<IngresoECheqOutView> ingresoModulo() {
		LOGGER.debug("Ingreso modulo Echeq");
		Cliente cliente = sesionCliente.getCliente();

		Boolean tieneCuentaOperativa = validarCuentaOperativa(cliente);
		if (!tieneCuentaOperativa) {
			LOGGER.error("ECHEQ - Cliente sin cuentas operativas");
            estadisticaManager.add(EstadisticasConstants.CODIGO_INGRESO_ECHEQ,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_INGRESO, CodigoMensajeConstantes.ECHEQ_ERROR_SIN_CUENTAS_OPERATIVAS);
		}

		Respuesta<Void> respuestaObtenerCuit = eCheqBO.obtenerCuit();
		if (EstadoRespuesta.ERROR.equals(respuestaObtenerCuit.getEstadoRespuesta())) {
			String tipoError = respuestaObtenerCuit.getItemsMensajeRespuesta().get(0).getTipoError();
            estadisticaManager.add(EstadisticasConstants.CODIGO_INGRESO_ECHEQ,  EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (TipoError.ERROR_GENERICO.getDescripcion().equals(tipoError)) {
				return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
			}
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_INGRESO, CodigoMensajeConstantes.ECHEQ_ERROR_CUIT);
		}

		estadisticaManager.add(EstadisticasConstants.CODIGO_INGRESO_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		Boolean esEcheqAdherido = buscarEcheqAdhesion();
		IngresoECheqOutView ingresoECheqOutView = new IngresoECheqOutView();
		ingresoECheqOutView.setLegal(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGALES_ADHESION));
		if (!esEcheqAdherido) {
			ingresoECheqOutView.setApellido(WordUtils.capitalizeFully(cliente.getApellido1()));
			ingresoECheqOutView.setNombre(WordUtils.capitalizeFully(cliente.getNombre()));
			ingresoECheqOutView.setCuit(obtenerCuitCuil(cliente));
			return respuestaFactory.crearRespuestaWarning(IngresoECheqOutView.class, ingresoECheqOutView, 
					TipoError.ECHEQ_NO_ADHERIDO, CodigoMensajeConstantes.ECHEQ_ERROR_ACEPTACION_CONTRATO, EMPTY);
		}

		Boolean esContratoAceptado = buscarContratoAceptado();
		if (!esContratoAceptado) {
			ingresoECheqOutView.setApellido(WordUtils.capitalizeFully(cliente.getApellido1()));
			ingresoECheqOutView.setNombre(WordUtils.capitalizeFully(cliente.getNombre()));
			ingresoECheqOutView.setCuit(obtenerCuitCuil(cliente));
			return respuestaFactory.crearRespuestaWarning(IngresoECheqOutView.class, ingresoECheqOutView, 
					TipoError.ECHEQ_NO_ADHERIDO, CodigoMensajeConstantes.ECHEQ_ERROR_ACTUALIZACION_TYC, EMPTY);
		}
		ingresoECheqOutView.setEstadosEmitidos(ECheqAmcoEstados.getComboEstadoView(TiposGrilla.EMITIDOS.getId()));
		ingresoECheqOutView.setEstadosEndosados(ECheqAmcoEstados.getComboEstadoView(TiposGrilla.ENDOSADOS.getId()));
		ingresoECheqOutView.setEstadosRecibidos(ECheqAmcoEstados.getComboEstadoView(TiposGrilla.RECIBIDOS.getId()));
		ingresoECheqOutView.setIntervaloBuscador(intervaloBuscador);
		ingresoECheqOutView.setRangoFechaBuscador(rangoFechaBuscador);
		ingresoECheqOutView.setHabilitaBuscador(habilitaBuscador);
		ingresoECheqOutView.setHabilitaEmitirEcheq(habilitaEmitir);
		return respuestaFactory.crearRespuestaOk(IngresoECheqOutView.class, ingresoECheqOutView);
	}

	private String obtenerCuitCuil(Cliente cliente) {
		return ISBANStringUtils.agregarGuionesANumeroCuitCuil(cliente.getNroDocCnsDocXNup());
	}

	private Boolean validarCuentaOperativa(Cliente cliente) {
		Boolean retorno = false;
		for (Cuenta c : cliente.getCuentas()) {
			if (!c.isCuentaCerrada() && cuentasHabilitadas.contains(c.getTipoCuentaEnum().getCodigo())) {
				retorno = true;
				break;
			}	
		}
		return retorno;
	}

	/**
	 * Busca si el cliente esta adherido a ECHEQ
	 * 
	 * @return boolean
	 */
	private boolean buscarEcheqAdhesion() {
		Respuesta<String> respuestaBO = new Respuesta<String>();
		respuestaBO = contratosBO.buscarContratoAceptado(sesionCliente.getCliente().getFechaNacimiento(),
				sesionCliente.getCliente().getDni(), CampoEnum.ECHEQ,
				SinonimoEnum.NO_REPETIDO);
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			LOGGER.info("ECHEQ - Respuesta OK de la consulta por el flag de adhesión a Echeq");
			return CONTRATO_ACEPTADO.equals(respuestaBO.getRespuesta());
		} 
		LOGGER.info("ECHEQ - Respuesta NO-OK de la consulta por el flag de adhesión a Echeq");
		return false;
	}
	
	/**
	 * Busca si el contrato ECHEQ esta aceptado
	 * 
	 * @return boolean
	 */
	private boolean buscarContratoAceptado() {
		Respuesta<String> respuestaBO = new Respuesta<String>();
		respuestaBO = contratosBO.buscarContratoAceptado(sesionCliente.getCliente().getFechaNacimiento(),
				sesionCliente.getCliente().getDni(), CampoEnum.ACEPTACION_TYC_ECHEQ,
				SinonimoEnum.NO_REPETIDO);
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			LOGGER.info("ECHEQ - Cliente con contrato ECHEQ Aceptado");
			return CONTRATO_ACEPTADO.equals(respuestaBO.getRespuesta());
		} 
		LOGGER.info("ECHEQ - Cliente con contrato ECHEQ No Aceptado");
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#confirmarAdhesion(ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAdhesionECheqInView)
	 */
	@Override
	public Respuesta<ConfirmarOperacionECheqOutView> confirmarAdhesion(ConfirmarAdhesionECheqInView confirmarAdhesionInView) {
		if (!confirmarAdhesionInView.getAceptaTyC()) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_ADHESION_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_ERROR_ACEPTACION_CONTRATO, CodigoMensajeConstantes.ECHEQ_ERROR_ACEPTACION_CONTRATO);
		}
		Respuesta<String> respuestaBO = contratosBO.confirmarAceptacionContrato(
				sesionCliente.getCliente().getFechaNacimiento(), 
				sesionCliente.getCliente().getDni(),
				CampoEnum.ECHEQ, SinonimoEnum.NO_REPETIDO);
		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.CODIGO_ADHESION_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_OK_ACEPTACION_CONTRATO);
			return respuestaFactory.crearRespuestaOk(ConfirmarOperacionECheqOutView.class, new ConfirmarOperacionECheqOutView(mensaje.getMensaje()));
		}
		return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_ERROR_ACEPTACION_CONTRATO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#ingresoOperacion(ar.com.santanderrio.obp.servicios.echeq.view.IngresoOperacionECheqView)
     */
    @Override
    public Respuesta<IngresoOperacionECheqView> ingresoOperacion(IngresoOperacionECheqView ingresoOperacionECheqInView) {
    	final IOperacionECheq operacion = OperacionEcheqFactory.getOperacion(ingresoOperacionECheqInView.getOperacion());
		final String codigoEstadistica = operacion.getCodigoEstadistica();
		LOGGER.debug("Ingreso operacion Echeq : {}", operacion.getOperacion());

		sesionParametros.setEcheqEstadoValidado(new ECheqDTO());
		sesionParametros.setComprobanteEcheqDTO(null);
		IngresoOperacionECheqView ingresoOperacionECheqOutView = new IngresoOperacionECheqView();

        // Verifica y carga cuentas habilitadas
        if (ECheqUtils.verificarCuentasHabilitadas(operacion.getOperacion())) {
            Respuesta<IngresoOperacionECheqView> respuestaCargaCuentas = cargarCuentasHabilitadas(operacion.getOperacion(),
					ingresoOperacionECheqOutView);
            if (EstadoRespuesta.ERROR.equals(respuestaCargaCuentas.getEstadoRespuesta())) {
            	String tipoError = respuestaCargaCuentas.getItemsMensajeRespuesta().get(0).getTipoError();
            	if (TipoError.ECHEQ_INGRESO_OPERACION_SIN_CUENTAS.getDescripcion().equals(tipoError)) {
            		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
            	} else {
            		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            	}
                return Respuesta.copy(IngresoOperacionECheqView.class, respuestaCargaCuentas);
            }
        }
		if (!operacion.getOperacionDisponible()) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.FUERA_DE_HORARIO,
					OperacionEcheqFactory.getMensajeFueraHorario(operacion.getOperacion()),
					echeqHoraDesde, echeqHoraHasta);
		}
        if (ingresoOperacionECheqInView.getId() != null) {
            Respuesta<Void> respuestaValidarEcheq = eCheqBO.validarEstado(ingresoOperacionECheqInView.getId(), 
            		ingresoOperacionECheqInView.getTipoGrilla(), ingresoOperacionECheqInView.getjSessionId(),
					operacion.getOperacion());
            if (EstadoRespuesta.ERROR.equals(respuestaValidarEcheq.getEstadoRespuesta())) {
                estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
                return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
            }
        }
        // Carga atributos
        if (OperacionEcheqEnum.ALTA.equals(operacion.getOperacion())) {
            ingresoOperacionECheqOutView.setRangoFechaPagoAlta(rangoFechaPagoAlta);
            ingresoOperacionECheqOutView.setModalidades(selectoresBO.obtenerModalidadesECheq());
        } else if (OperacionEcheqEnum.VER_DETALLE.equals(operacion.getOperacion())) {
        	Cheque echeq = sesionParametros.getEcheqEstadoValidado().getEcheqValidado();
        	
        	ingresoOperacionECheqOutView.setTiposEndoso(
        			selectoresBO.obtenerTiposEndosoECheq(
        			ISBANStringUtils.convertirPrimerLetraEnMayuscula(echeq.getChequeCaracter().toLowerCase())
        			));
        	
        	ingresoOperacionECheqOutView.setFechaUltimaModificacion(echeq.getFechaUltModif());
			String legales = obtenerLegalesDetalle(echeq.getEstado(), ingresoOperacionECheqInView.getIngresoDesdeEndosados(), ingresoOperacionECheqInView.getIngresoDesdeEmitidos());
			ingresoOperacionECheqOutView.setLegal(legales);
        	// Descarga de detalle
        	ComprobanteECheqDTO comprobanteDTO = generarReporteDetalle(ingresoOperacionECheqInView.getIngresoDesdeEmitidos(), 
        			ingresoOperacionECheqInView.getIngresoDesdeEndosados(), ingresoOperacionECheqInView.getIngresoDesdeRecibidos(),
        			ingresoOperacionECheqInView.getIngresoDesdeCedidos(), echeq);
			sesionParametros.setComprobanteEcheqDTO(comprobanteDTO);
        } else if(OperacionEcheqEnum.ENDOSAR.equals(operacion.getOperacion())) {
        	ingresoOperacionECheqOutView.setTiposEndoso(selectoresBO.obtenerTiposEndosoECheq(ingresoOperacionECheqInView.getModalidadEcheq()));
        }
		ingresoOperacionECheqOutView.setMensajePopup(mapMensajePopup(operacion,
				sesionParametros.getEcheqEstadoValidado().getEcheqValidado()));
        setearLegalesOperacion(operacion, ingresoOperacionECheqOutView);
		setearMensajeAyudaOperacion(operacion, ingresoOperacionECheqOutView);
        return respuestaFactory.crearRespuestaOk(IngresoOperacionECheqView.class, ingresoOperacionECheqOutView);
    }

    private ComprobanteECheqDTO generarReporteDetalle(Boolean ingresoDesdeEmitidos, Boolean ingresoDesdeEndosados, Boolean ingresoDesdeRecibidos, Boolean ingresoDesdeCedidos, Cheque echeq) {
    	ComprobanteECheqDTO comprobanteDTO = new ComprobanteECheqDTO();
    	comprobanteDTO.setNombreBeneficiario(EMPTY);
    	comprobanteDTO.setCuitBeneficiario(EMPTY);
    	comprobanteDTO.setEmisorCuit(EMPTY);
    	comprobanteDTO.setEmisorRazonSocial(EMPTY);
		String tipoGrilla = null;
		if (ingresoDesdeRecibidos) {
			tipoGrilla = TiposGrilla.RECIBIDOS.getId();
			setearEmisor(comprobanteDTO, echeq);
			setearCuentaDeposito(comprobanteDTO, echeq);
		} else if (ingresoDesdeEmitidos) {
			tipoGrilla = TiposGrilla.EMITIDOS.getId();
			setearBeneficiario(comprobanteDTO, echeq);
			setearCuentaDebito(comprobanteDTO, echeq);
		} else if (ingresoDesdeEndosados) {
			tipoGrilla = TiposGrilla.ENDOSADOS.getId();
			setearBeneficiario(comprobanteDTO, echeq);
		} else if(ingresoDesdeCedidos){
			tipoGrilla = TiposGrilla.CEDIDOS.getId();
			setearBeneficiario(comprobanteDTO, echeq);
		}else {
			return null;
		}
		setearLegalesDetalle(comprobanteDTO, ingresoDesdeEndosados, ingresoDesdeEmitidos, echeq.getEstado());
		comprobanteDTO.setGrillaOrigen(tipoGrilla);
		comprobanteDTO.setOperacion(OperacionEcheqEnum.VER_DETALLE);
		comprobanteDTO.setImporte(ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearSaldo(new BigDecimal(echeq.getMonto())));
		ECheqAmcoEstados eCheqAmcoEstados = ECheqAmcoEstados.getByEstado(tipoGrilla, 
				echeq.getStatus().getBaeCCERegstatus(), 
				echeq.getEstado(),
				echeq.isSolicitandoAcuerdo(),
				echeq.isChequeAcordado(),
				echeq.isCertificadoEmitido(),
				echeq.isCedido(),
				echeq.isCesionPendiente());
		comprobanteDTO.setEstado(eCheqAmcoEstados.getDescripcion());
		comprobanteDTO.setModalidad(echeq.getChequeCaracter());
		comprobanteDTO.setFechaEmision(ECheqUtils.parsearFecha(echeq.getFechaEmision(), ECheqUtils.MASCARA_FECHA_FRONT));
		comprobanteDTO.setFechaPago(ECheqUtils.parsearFecha(echeq.getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT));
		comprobanteDTO.setMotivo(echeq.getChequeMotivoPago());
		comprobanteDTO.setNumeroCheque(echeq.getChequeNumero());
		comprobanteDTO.setEndosos(ECheqUtils.obtenerEndosos(echeq.getEndosos()));
		comprobanteDTO.setCedidos(ECheqUtils.obtenerCesiones(echeq.getCesiones()));
		comprobanteDTO.setNumeroComprobante(EMPTY);
		comprobanteDTO.setCaracter(echeq.getChequeCaracter().substring(0, 1).toUpperCase()
				+ echeq.getChequeCaracter().substring(1).toLowerCase());
		if ("CC".equals(echeq.getChequeTipo())) {
			comprobanteDTO.setChequeTipo("Cheque común");
		} else if ("CPD".equals(echeq.getChequeTipo())) {
			comprobanteDTO.setChequeTipo("Cheque de pago diferido");
		}
		if ("0".equals(echeq.getChequeModo())) {
			comprobanteDTO.setModalidad("No cruzado");
		} else if ("1".equals(echeq.getChequeModo())) {
			comprobanteDTO.setModalidad("Cruzado");
		}
		comprobanteDTO.setDomicilioEmisor(obtenerStringSeparadoPorEspacioDomilio(echeq.getCuentaEmisora().getEmisorDomicilio()));
		comprobanteDTO.setEntidadGirada(obtenerPalabrasSeparadasPorEspacio(echeq.getCuentaEmisora().getBancoNombre()));
		comprobanteDTO.setDomicilioPago(obtenerPalabrasSeparadasPorEspacio(echeq.getCuentaEmisora().getSucursalDomicilio() + ", "
				+ echeq.getCuentaEmisora().getSucursalProvincia()));
		comprobanteDTO.setCmc7(echeq.getCmc7());
		comprobanteDTO.setId(echeq.getIntchequeId());

		SimpleDateFormat sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_COMPROBANTE);
		comprobanteDTO.setFechaComprobante(sdf.format(new Date()));

		return comprobanteDTO;
	}
	
	private String obtenerPalabrasSeparadasPorEspacio(String nombreBanco) {
		List<String> valores  = new ArrayList<String>(); 
		for (String x : nombreBanco.split(" ")) {
			if(x.length() > 0 ){
				valores.add(x);
			}
		}
		String domicilio = "";
		for (String c : valores) {
			domicilio += c + " ";
		}
		return domicilio;
	}
	
	private void setearEmisor(ComprobanteECheqDTO comprobanteDTO, Cheque echeq) {
		if (echeq.getCuentaEmisora() != null) {
			String nombreEmisor = ECheqUtils.eliminarEspacios(echeq.getCuentaEmisora().getEmisorRazonSocial());
			comprobanteDTO.setEmisorRazonSocial(WordUtils.capitalizeFully(nombreEmisor));
			comprobanteDTO.setEmisorCuit(ISBANStringUtils.agregarGuionesANumeroCuitCuil(echeq.getCuentaEmisora().getEmisorCuit()));
		}
	}

	private void setearBeneficiario(ComprobanteECheqDTO comprobanteDTO, Cheque echeq) {
		if (echeq.getEmitidoA() != null) {
			String nombreBeneficiario = ECheqUtils.eliminarEspacios(echeq.getEmitidoA().getBeneficiarioNombre());
			comprobanteDTO.setNombreBeneficiario(WordUtils.capitalizeFully(nombreBeneficiario));
			comprobanteDTO.setCuitBeneficiario(ISBANStringUtils.agregarGuionesANumeroCuitCuil(echeq.getEmitidoA().getBeneficiarioDocumento()));
		}
	}

	private void setearCuentaDebito(ComprobanteECheqDTO comprobanteDTO, Cheque echeq) {
		Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(echeq.getCuentaEmisora().getEmisorCbu());
		if (cuentaCliente != null) {
			comprobanteDTO.setCuentaNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
			comprobanteDTO.setCuentaTipo(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
			comprobanteDTO.setCuentaAlias(cuentaCliente.getAlias());
		}
	}

	private void setearCuentaDeposito(ComprobanteECheqDTO comprobanteDTO, Cheque echeq) {
		String cbu = null;
		if (!StringUtils.isBlank(echeq.getCbuDeposito())) {
			cbu = echeq.getCbuDeposito();
		} else if (StringUtils.isBlank(echeq.getCbuDeposito()) && !StringUtils.isBlank(echeq.getCbuCustodia())) {
			cbu = echeq.getCbuCustodia();
		} else {
			return;
		}
		Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cbu);
		if (cuentaCliente != null) {
			comprobanteDTO.setCuentaNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
			comprobanteDTO.setCuentaTipo(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
			comprobanteDTO.setCuentaAlias(cuentaCliente.getAlias());
		}
	}

	private String obtenerLegalesDetalle(String estado, Boolean ingresoDesdeEndosados, Boolean ingresoDesdeEmitidos) {
		if (ingresoDesdeEmitidos &&
				(ECheqAmcoEstados.ACTIVO_PENDIENTE_EMITIDOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.ACTIVO_EMITIDOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.CUSTODIA_EMITIDOS_E.getEstadoAmco().equals(estado))) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIDOS);
		} else if (ingresoDesdeEndosados &&
				(ECheqAmcoEstados.ACTIVO_PENDIENTE_ENDOSADOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.ACTIVO_ENDOSADOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.CUSTODIA_ENDOSADOS_E.getEstadoAmco().equals(estado))) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIDOS);
		}
		return null;
	}

	private void setearLegalesDetalle(ComprobanteECheqDTO comprobanteDTO, Boolean ingresoDesdeEndosados, Boolean ingresoDesdeEmitidos, String estado) {
		String legales = obtenerLegalesDetalle(estado, ingresoDesdeEndosados, ingresoDesdeEmitidos);
		if (legales != null) {
			comprobanteDTO.setLegales(legales);
		}
	}

	private String mapMensajePopup(IOperacionECheq operacion, Cheque cheque) {
        String codigoMensajePopUp = OperacionEcheqFactory.getCodigoMensajePopUp(operacion.getOperacion());
        if (codigoMensajePopUp == null) {
        	return null;
        }
		//TODO: REFACTOR THIS AS PARAMETER MAP
		return mensajeBO.obtenerMensajePorCodigo(codigoMensajePopUp,
			cheque.getCuentaEmisora().getEmisorRazonSocial(),
			ECheqUtils.PREFIJO_PESOS + ISBANStringUtils.formatearSaldo(new BigDecimal(cheque.getMonto())),
			ECheqUtils.parsearFecha(cheque.getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT))
				.getMensaje();
    }

    private void setearLegalesOperacion(IOperacionECheq operacion, IngresoOperacionECheqView ingresoOperacionECheqOutView) {
        String codigoLegalesOperacion = OperacionEcheqFactory.getLegalesOperacion(operacion.getOperacion());
        if (codigoLegalesOperacion != null) {
            ingresoOperacionECheqOutView.setLegal(legalBO.obtenerLegalesPorCodigo(codigoLegalesOperacion));
        }
    }
    
    private void setearMensajeAyudaOperacion(IOperacionECheq operacion, IngresoOperacionECheqView ingresoOperacionECheqOutView) {
        String codigoLegalesOperacion = OperacionEcheqFactory.getLegalesAyudaOperacion(operacion.getOperacion());
        if (codigoLegalesOperacion != null) {
            ingresoOperacionECheqOutView.setMensajeAyuda(legalBO.obtenerLegalesPorCodigo(codigoLegalesOperacion));
        }
    }

	private Respuesta<IngresoOperacionECheqView> cargarCuentasHabilitadas(OperacionEcheqEnum operacion, IngresoOperacionECheqView ingresoOperacionECheqOutView) {
		List<Cuenta> cuentasHabilitadasOperacion;
		if (OperacionEcheqEnum.ALTA.equals(operacion) && sesionParametros.getCuentasHabilitadasAltaEcheq() != null) {
			cuentasHabilitadasOperacion = sesionParametros.getCuentasHabilitadasAltaEcheq();
		} else {
			String tipoCuentasHabilitadasPrefix = OperacionEcheqFactory.getTipoCuentasHabilitadasPrefix(operacion);
			cuentasHabilitadasOperacion = ECheqUtils.obtenerCuentasHabilitadas(sesionCliente.getCliente(), cuentaBO, 
					ECheqUtils.getParamsCuentasHabilitadas(tipoCuentasHabilitadasPrefix, propertyMap));
			if (cuentasHabilitadasOperacion.isEmpty()) {
				LOGGER.error("No se encontraron cuentas habilitadas para la operacion {} echeq.", operacion);
				return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_INGRESO_OPERACION_SIN_CUENTAS, obtenerCodigoErrorSinCuentas(operacion)); 
			}
			if (OperacionEcheqEnum.ALTA.equals(operacion)) {
				sesionParametros.setCuentasHabilitadasAltaEcheq(cuentasHabilitadasOperacion);
			}
		}
		try {
			ingresoOperacionECheqOutView.setCuentasOperacion(obtenerCuentasOperacion(cuentasHabilitadasOperacion));
		} catch (BusinessException e) {
			LOGGER.error("Error al actualizar el saldo de las cuentas.", e);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
		}
		return respuestaFactory.crearRespuestaOk(IngresoOperacionECheqView.class); 
	}

	private List<CuentaView> obtenerCuentasOperacion(List<Cuenta> cuentas) throws BusinessException {
		List<CuentaView> cuentasViewPesos = new ArrayList<CuentaView>();
		List<ResumenDetalleCuenta> cuentasValidas = obtenerCuentasValidas(cuentas);
		int id = 0;
		for (ResumenDetalleCuenta cuentaValida : cuentasValidas) {
			boolean isSaldoPesosNegativo = false;
			int tipoCuenta = Integer.valueOf(cuentaValida.getTipoCuenta());
			CuentaView cuentaView = new CuentaView();
			cuentaView.setId(id++);
			cuentaView.setAlias(cuentaValida.getAlias());
			cuentaView.setDescripcionTipoCuenta(TipoCuenta.fromCodigo(tipoCuenta).getDescripcionConMoneda());
			cuentaView.setNumero(ISBANStringUtils.formatearSucursal(cuentaValida.getNroSucursal())
					.concat(ISBANStringUtils.GUION_STRING)
					.concat(ISBANStringUtils.formatearNumeroCuenta(cuentaValida.getNroCuentaProducto())));
			if (TipoCuenta.CUENTA_UNICA.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(
						ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(cuentaValida.getSaldoPesos())));
				if (cuentaValida.getSaldoDolares().equals(BigDecimal.ZERO)) {
					cuentaView.setSaldoDolares(ISBANStringUtils
							.formatearConComaYDosDecimales(String.valueOf(cuentaValida.getSaldoCajaAhorroDolares())));
				} else {
					cuentaView.setSaldoDolares(ISBANStringUtils
							.formatearConComaYDosDecimales(String.valueOf(cuentaValida.getSaldoDolares())));
				}
				cuentaView.setDescripcionTipoCuenta(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda());
				cuentasViewPesos.add(cuentaView);
			} else if (TipoCuenta.CUENTA_CORRIENTE_PESOS.getCodigo() == tipoCuenta
					|| TipoCuenta.CAJA_AHORRO_PESOS.getCodigo() == tipoCuenta) {
				cuentaView.setSaldoPesos(
						ISBANStringUtils.formatearConComaYDosDecimales(String.valueOf(cuentaValida.getSaldoPesos())));
				cuentasViewPesos.add(cuentaView);
			}
			if (cuentaView.getSaldoPesos() != null && cuentaValida.getSaldoPesos().signum() < 0) {
				cuentaView.setIsSaldoPesosNegativo(!isSaldoPesosNegativo);
			} else {
				cuentaView.setIsSaldoPesosNegativo(isSaldoPesosNegativo);
			}
		}
		return cuentasViewPesos;
	}

	private List<ResumenDetalleCuenta> obtenerCuentasValidas(List<Cuenta> cuentas) throws BusinessException {
		List<ResumenDetalleCuenta> cuentasValidas = cuentaBO.getCuentasSaldo(sesionCliente.getCliente(), cuentas).getRespuesta();
		if (cuentasValidas == null || cuentasValidas.isEmpty()) {
			throw new BusinessException();
		}
		return cuentasValidas;
	}

	private String obtenerCodigoErrorSinCuentas(OperacionEcheqEnum operacion) {
		if (OperacionEcheqEnum.ALTA.equals(operacion)) {
			return CodigoMensajeConstantes.ECHEQ_ERROR_SINCUENTAS_ALTA;
		} else if (OperacionEcheqEnum.CUSTODIAR.equals(operacion)) {
			return CodigoMensajeConstantes.ECHEQ_ERROR_SINCUENTAS_CUSTODIAR;
		} else if (OperacionEcheqEnum.DEPOSITAR.equals(operacion)) {
			return CodigoMensajeConstantes.ECHEQ_ERROR_SINCUENTAS_DEPOSITAR;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#validarBeneficiario(ar.com.santanderrio.obp.servicios.echeq.view.BeneficiarioView)
	 */
	@Override
	public Respuesta<BeneficiarioView> validarBeneficiario(BeneficiarioView beneficiarioInView) {
		BeneficiarioDTO beneficiarioInDTO = new BeneficiarioDTO();
		sesionParametros.getEcheqEstadoValidado().setBeneficiarioValidado(null);
		BeanUtils.copyProperties(beneficiarioInView, beneficiarioInDTO);
		Respuesta<BeneficiarioDTO> respuestaValidarBeneficiario = eCheqBO.validarBeneficiario(beneficiarioInDTO);
		if (EstadoRespuesta.OK.equals(respuestaValidarBeneficiario.getEstadoRespuesta())) {
			BeneficiarioView beneficiarioOutView = new BeneficiarioView();
			BeneficiarioDTO beneficiarioValidado = respuestaValidarBeneficiario.getRespuesta();
			
			// IDENTIFICAMOS SI ES BENEFICIARIO DE ENTIDAD IMF (para endoso de eCheq)
			beneficiarioValidado.setEsIMF(esBeneficiarioIMF(beneficiarioInDTO));
			beneficiarioValidado.setAlertaIMF(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_MENSAJE_ALERTA_IMF).getMensaje());
			
			BeanUtils.copyProperties(beneficiarioValidado, beneficiarioOutView);
			beneficiarioOutView.setBenDocumento(ECheqUtils.obtenerDocumento(beneficiarioOutView.getBenDocumento()));
			sesionParametros.getEcheqEstadoValidado().setBeneficiarioValidado(beneficiarioValidado);
			return respuestaFactory.crearRespuestaOk(BeneficiarioView.class, beneficiarioOutView);
		} else if (EstadoRespuesta.WARNING.equals(respuestaValidarBeneficiario.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaWarning(EMPTY, TipoError.ECHEQ_WARNING_VALIDAR_BENEFICIARIO, CodigoMensajeConstantes.ECHEQ_WARNING_VALIDAR_BENEFICIARIO);
		} else if (EstadoRespuesta.ERROR.equals(respuestaValidarBeneficiario.getEstadoRespuesta())) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
		}
		return null;
	}

	/**
	 * Indica si el beneficiario se encuentra en la lista
	 * de entidades habilitadas
	 * @param beneficiarioDTO
	 * @return
	 */
	private boolean esBeneficiarioIMF(BeneficiarioDTO beneficiarioDTO) {
		
		if(sesionParametros.getListadoIMF() == null) {
    		// Buscamos entidades habilitadas
			Respuesta<ResponseFull> retMarketInfInOutEntity = eCheqBO.obtenerListadoEntidadesHabilitadas(beneficiarioDTO.getjSessionId());
    		if(EstadoRespuesta.OK.equals(retMarketInfInOutEntity.getEstadoRespuesta())) {
    			estadisticaManager.add(EstadisticasConstants.RETRIEVE_MARKET_INFRASTRUCTURE, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    			if(retMarketInfInOutEntity.getRespuesta() != null
    				&& !retMarketInfInOutEntity.getRespuesta().getInfraestructurasMercado().isEmpty()) {
    				sesionParametros.setListadoIMF(retMarketInfInOutEntity.getRespuesta().getInfraestructurasMercado());
    			}
    		} else {
    			estadisticaManager.add(EstadisticasConstants.RETRIEVE_MARKET_INFRASTRUCTURE, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    		}
    	}
		
		List<IMF> listadoIMF = sesionParametros.getListadoIMF();
		if(listadoIMF != null && !listadoIMF.isEmpty()) {
			for (IMF imf : listadoIMF) {
				if(imf.getDocumento().equals(beneficiarioDTO.getDocumento()) 
						&& imf.getDocumentoTipo().equals(beneficiarioDTO.getTipoDocumento()))
					return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.manager.ECheqManager#confirmarAltaEndosar(ar.com.santanderrio.obp.servicios.echeq.view.ConfirmarAltaEndosarECheqView)
	 */
	@Override
	public Respuesta<ConfirmarAltaEndosarECheqView> confirmarAltaEndosar(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView) {
		LOGGER.info("Init - confirmarAltaEndosar");
		final IOperacionECheq operation = OperacionEcheqFactory.getOperacion(confirmarAltaEndosarECheqInView.getOperacion());
		if (operation == null) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ECHEQ_BY_ID);
		}

		if (confirmarAltaEndosarECheqInView.getDesafio() == null) {
			LOGGER.info("DESAFIO ES NULO, SE CARGA EL HASH");
			cargarHashValidacion(crearMapaDeLaVista(confirmarAltaEndosarECheqInView));
		}
		Respuesta<ConfirmarAltaEndosarECheqView> respuestaRsa = null;

		if (OperacionEcheqEnum.ALTA.equals(operation.getOperacion()) || OperacionEcheqEnum.ENDOSAR.equals(operation.getOperacion()) ) {
			respuestaRsa = ejecutarValidacionRSA(confirmarAltaEndosarECheqInView);
		}
		if (OperacionEcheqEnum.EMITIR_CED.equals(operation.getOperacion())) {
			final AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion(operation.getOperacion());
			respuestaRsa = desafioOperacionRSA.validarOperacionRSA(confirmarAltaEndosarECheqInView, valorDesafioEcheqAlta, autentificacionCodEstDTO);
		}

		if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
            return respuestaRsa;
        }

        validarHash(crearMapaDeLaVista(confirmarAltaEndosarECheqInView));

		ConfirmarOperacionInDTO confirmarOperacionInDTO;
		try {
			confirmarOperacionInDTO = crearConfirmarOperacionInDTO(confirmarAltaEndosarECheqInView);
		} catch (ImporteConvertException e) {
			estadisticaManager.add(operation.getCodigoEstadistica(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_ERROR_GENERICO);
		}

		Respuesta<ConfirmarOperacionOutDTO> respuestaBO =
				eCheqBO.confirmarOperacion(operation, confirmarOperacionInDTO, sesionCliente.getCliente());
		estadisticaManager.add(operation.getEstadisticaOperacion());

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqOutView = crearConfirmarAltaEndosarECheqView(operation.getOperacion());
			confirmarAltaEndosarECheqOutView.setMensajeFeedback(respuestaBO.getRespuesta().getMensajeFeedback());
			confirmarAltaEndosarECheqOutView.setFechaComprobante(respuestaBO.getRespuesta().getFechaComprobante());
			confirmarAltaEndosarECheqOutView.setNumeroComprobante(respuestaBO.getRespuesta().getNumeroComprobante());
			confirmarAltaEndosarECheqOutView.setLegales(respuestaBO.getRespuesta().getLegales());
			return respuestaFactory.crearRespuestaOk(ConfirmarAltaEndosarECheqView.class, confirmarAltaEndosarECheqOutView);
		}
		
		if(sesionParametros.getContador() == null) {
			ContadorIntentos contador = new ContadorIntentos();
			sesionParametros.setContador(contador);			
		}
		
		if (!TipoError.ERROR_GENERICO.getDescripcion().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())
                && !sesionParametros.getContador().permiteReintentar()) {
            sesionParametros.setContador(null);
            sesionParametros.setEcheqEstadoValidado(null);
            respuestaBO.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
        }
		return Respuesta.copy(ConfirmarAltaEndosarECheqView.class, respuestaBO);
	}

	private ConfirmarAltaEndosarECheqView crearConfirmarAltaEndosarECheqView(OperacionEcheqEnum operacion) {
		OperacionesRSAEnum operacionRSA = null;
		if (OperacionEcheqEnum.ALTA.equals(operacion)) {
			operacionRSA = OperacionesRSAEnum.ECHEQ_ALTA;
		} else if (OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
			operacionRSA = OperacionesRSAEnum.ECHEQ_ENDOSAR;				
		} else if (OperacionEcheqEnum.EMITIR_CED.equals(operacion)) {
			operacionRSA = OperacionesRSAEnum.ECHEQ_EMITIR_CED;				
		} 
		return new ConfirmarAltaEndosarECheqView(operacionRSA);
	}

	private ConfirmarOperacionInDTO crearConfirmarOperacionInDTO(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqInView) throws ImporteConvertException {
		ConfirmarOperacionInDTO confirmarOperacionInDTO = new ConfirmarOperacionInDTO();
		confirmarOperacionInDTO.setBeneficiario(sesionParametros.getEcheqEstadoValidado().getBeneficiarioValidado());
		confirmarOperacionInDTO.setCuentaSeleccionada(confirmarAltaEndosarECheqInView.getCuentaDebitoSeleccionada());
		confirmarOperacionInDTO.setFechaPago(confirmarAltaEndosarECheqInView.getFechaPago());
        if (confirmarAltaEndosarECheqInView.getImporte() != null) {
            confirmarOperacionInDTO.setImporte(ISBANStringUtils.convertirImporte(confirmarAltaEndosarECheqInView.getImporte()));
        }
		confirmarOperacionInDTO.setIngresoDesdeEmitidos(confirmarAltaEndosarECheqInView.getIngresoDesdeEmitidos());
		confirmarOperacionInDTO.setIngresoDesdeEndosados(confirmarAltaEndosarECheqInView.getIngresoDesdeEndosados());
		confirmarOperacionInDTO.setIngresoDesdeRecibidos(confirmarAltaEndosarECheqInView.getIngresoDesdeRecibidos());
		confirmarOperacionInDTO.setjSessionId(confirmarAltaEndosarECheqInView.getjSessionId());
		confirmarOperacionInDTO.setOperacion(confirmarAltaEndosarECheqInView.getOperacion());
		confirmarOperacionInDTO.setId(confirmarAltaEndosarECheqInView.getIdCheque());
		confirmarOperacionInDTO.setModalidad(confirmarAltaEndosarECheqInView.getModalidad());
		confirmarOperacionInDTO.setTipoEndoso(confirmarAltaEndosarECheqInView.getTipoEndoso());
		if(confirmarAltaEndosarECheqInView.getDomicilio()!= null && !confirmarAltaEndosarECheqInView.getDomicilio().isEmpty()) {
			confirmarOperacionInDTO.getBeneficiario().setBenDomicilio(confirmarAltaEndosarECheqInView.getDomicilio());
		}
		return confirmarOperacionInDTO;
	}

	/**
     * Validar hash.
     *
     *
     * @param viewMap
     *            the view map
     */
    private void validarHash(Map<String, Object> viewMap) {
        String inputHash = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
                sesionParametros.getValidacionHash(), inputHash);
        HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
    }

    private Map<String, Object> crearMapaDeLaVista(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqView) {
        LOGGER.info("Creando hash de los atributos...");
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("importe", confirmarAltaEndosarECheqView.getImporte());
        mapaAtributos.put("cuntaDebitoSeleccionada", confirmarAltaEndosarECheqView.getCuentaDebitoSeleccionada());
        mapaAtributos.put("fechaPago", confirmarAltaEndosarECheqView.getFechaPago() != null ? confirmarAltaEndosarECheqView.getFechaPago() : EMPTY);
        mapaAtributos.put("operacion", confirmarAltaEndosarECheqView.getOperacion().getAccion());
        mapaAtributos.put("modalidad", confirmarAltaEndosarECheqView.getModalidad() != null ? confirmarAltaEndosarECheqView.getModalidad() : EMPTY);
        mapaAtributos.put("tipoEndoso", confirmarAltaEndosarECheqView.getTipoEndoso() != null ? confirmarAltaEndosarECheqView.getTipoEndoso() : EMPTY);
        mapaAtributos.put("idCheque", confirmarAltaEndosarECheqView.getIdCheque() != null ? confirmarAltaEndosarECheqView.getIdCheque() : EMPTY);
        return mapaAtributos;
    }

    private void cargarHashValidacion(Map<String, Object> viewMap) {
        String hashView = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Se guarda el hash en sesion.");
        sesionParametros.setValidacionHash(hashView);
    }

    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion(OperacionEcheqEnum operacion) {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        if (OperacionEcheqEnum.ALTA.equals(operacion)) {
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_ALTA_SOLICITUD_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.ECHEQ_ALTA_VALIDACION_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.ECHEQ_ALTA_SOLICITUD_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.ECHEQ_ALTA_VALIDACION_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(null);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(null);
        } else if (OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
        	autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_ENDOSAR_SOLICITUD_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.ECHEQ_ENDOSAR_VALIDACION_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.ECHEQ_ENDOSAR_SOLICITUD_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.ECHEQ_ENDOSAR_VALIDACION_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(null);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(null);
        } else if (OperacionEcheqEnum.EMITIR_CED.equals(operacion)) {
        	autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_EMITIR_CED_SOLICITUD_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.ECHEQ_EMITIR_CED_VALIDACION_TOKEN);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.ECHEQ_EMITIR_CED_SOLICITUD_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.ECHEQ_EMITIR_CED_VALIDACION_COORDENADAS);
	        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(null);
	        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(null);
        }
        return autentificacionCodEstDTO;
    }

    @Override
    public Respuesta<ConfirmarOperacionECheqOutView> confirmar(ConfirmarOperacionECheqInView confirmarOperacionECheqInView) {
		final IOperacionECheq operation = OperacionEcheqFactory.getOperacion(confirmarOperacionECheqInView.getOperacion());
		if (operation == null) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ECHEQ_BY_ID);
		}

		if (!operation.getOperacionDisponible()) {
			estadisticaManager.add(operation.getCodigoEstadistica(), EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.FUERA_DE_HORARIO,
				OperacionEcheqFactory.getMensajeFueraHorario(operation.getOperacion()), echeqHoraDesde, echeqHoraHasta);
		}
		ConfirmarOperacionInDTO confirmarOperacionInDTO = new ConfirmarOperacionInDTO();
		BeanUtils.copyProperties(confirmarOperacionECheqInView, confirmarOperacionInDTO);
		confirmarOperacionInDTO.setBeneficiario(sesionParametros.getEcheqEstadoValidado().getBeneficiarioValidado());
        Respuesta<ConfirmarOperacionOutDTO> respuestaBO = eCheqBO.confirmarOperacion(operation,
				confirmarOperacionInDTO, sesionCliente.getCliente());

        estadisticaManager.add(operation.getEstadisticaOperacion());

        if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			ConfirmarOperacionECheqOutView confirmarOperacionECheqOutView = new ConfirmarOperacionECheqOutView();
			confirmarOperacionECheqOutView.setMensajeFeedback(respuestaBO.getRespuesta().getMensajeFeedback());
            return respuestaFactory.crearRespuestaOk(ConfirmarOperacionECheqOutView.class,
                    confirmarOperacionECheqOutView);
        }

        if (!TipoError.ERROR_GENERICO.getDescripcion().equals(respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError())
                && !sesionParametros.getContador().permiteReintentar()) {
            sesionParametros.setContador(null);
            sesionParametros.setEcheqEstadoValidado(null);
            respuestaBO.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_REINTENTOS_AGOTADOS.getDescripcion());
        }
        return Respuesta.copy(ConfirmarOperacionECheqOutView.class, respuestaBO);
    }

    @Override
    public Respuesta<ReporteView> descargarComprobante(OperacionEcheqEnum operacion) {
    	ComprobanteECheqDTO comprobanteDTO = sesionParametros.getComprobanteEcheqDTO();
    	if (!comprobanteDTO.getOperacion().equals(operacion)) {
			return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);
		}
    	Respuesta<ReporteView> respuestaView;
    	Respuesta<Reporte> reporte = eCheqBO.descargarComprobante(comprobanteDTO);
    	if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
		return respuestaView;
    }

    private String obtenerStringSeparadoPorEspacioDomilio(String domicilio) {
		List<String> valores  = new ArrayList<String>(); 
		for (String x : domicilio.split(" ")) {
			if(x.length() > 0 ){
				if(sePuedeConvertirEnNumero(x)){
					valores.add(ISBANStringUtils.sacarCerosYBlancosIzq(x));
				} else {
					valores.add(x);

				}
			}
		}
		String domicilioParseado = "";
		for (String c : valores) {
			domicilioParseado += c + " ";
		}
		return domicilioParseado;
	}
	
	private Boolean sePuedeConvertirEnNumero(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private Respuesta<ConfirmarAltaEndosarECheqView> ejecutarValidacionRSA(ConfirmarAltaEndosarECheqView confirmaAltaEndosoView) {
		LOGGER.info("INIT ECHEQ - Se ejecuta la validacion por RSA");
		AutentificacionDTO autentificacionDTO;

		Respuesta<ConfirmarAltaEndosarECheqView> estadoDesafio = autentificacionManager.verificarEstadoDesafio(confirmaAltaEndosoView.getDesafio(),
				confirmaAltaEndosoView.getOperacion().equals(OperacionEcheqEnum.ALTA) ? valorDesafioEcheqAlta : valorDesafioEcheqEndosar);
		LOGGER.info("*ESTADO DESAFIO: "+estadoDesafio);
		LOGGER.info("*estadoDesafio.getEstadoRespuesta(): "+estadoDesafio.getEstadoRespuesta());

		if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = confirmaAltaEndosoView.getDesafio();
		} else if ( EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
			autentificacionDTO = generarAutentificacionDTO(confirmaAltaEndosoView);
		} else {
			return estadoDesafio;
		}
		// Transformacion respuesta RSA
		Respuesta<AutentificacionDTO> rstaAutentificacion = autentificacionManager
				.ejecutarValidacionRSA(autentificacionDTO);
		confirmaAltaEndosoView.setDesafio(rstaAutentificacion.getRespuesta());

		Respuesta<ConfirmarAltaEndosarECheqView> respuesta = respuestaFactory.transformar(confirmaAltaEndosoView, rstaAutentificacion);

		LOGGER.info("FIN ECHEQ - Se ejecuta la validacion por RSA");
		return respuesta;
	}


	private AutentificacionDTO generarAutentificacionDTO(ConfirmarAltaEndosarECheqView confirmaAltaEndosoView) {
		LOGGER.info("Se carga la informacion de autentificationDTO");
		Integer operacion = confirmaAltaEndosoView.getOperacion().equals(OperacionEcheqEnum.ALTA) ? valorDesafioEcheqAlta : valorDesafioEcheqEndosar;
		AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
		autentificacionDTO.setOperacion(operacion);
		autentificacionDTO.setCoordenadasOperacion(new CoordenadasOperacionDTO(
				new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION, "192168001001")));

		// Carga de estadisticas

		if (confirmaAltaEndosoView.getOperacion().equals(OperacionEcheqEnum.ALTA)){
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_ALTA_SOLICITUD_TOKEN);
			autentificacionDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.ECHEQ_ALTA_VALIDACION_TOKEN);
		}else{
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_ENDOSAR_SOLICITUD_TOKEN);
			autentificacionDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.ECHEQ_ENDOSAR_VALIDACION_TOKEN);
		}

		// Datos informados a RSA
		LOGGER.info("**** tipo operacion echeq: "+ confirmaAltaEndosoView.getOperacion());
		EcheqRSADTO echeqRSADTO = new EcheqRSADTO(operacion,
			obtenerMonto(confirmaAltaEndosoView),
			confirmaAltaEndosoView.getIdCheque(),
			sesionCliente.getCliente().getNumeroCUILCUIT(),
				ISBANStringUtils.agregarGuionesANumeroCuitCuil(sesionParametros.getEcheqEstadoValidado().getBeneficiarioValidado().getDocumento()),
			confirmaAltaEndosoView.getOperacion());

		cargarUltimosDiasDesdeCambioDeClaveToken(echeqRSADTO);
		autentificacionDTO.setRsaDTO(echeqRSADTO);

		return autentificacionDTO;
	}

	public void cargarUltimosDiasDesdeCambioDeClaveToken(EcheqRSADTO echeqRSADTO) {

		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						echeqRSADTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
						LOGGER.info("Valor de ultimos dias cambio clave: {}", antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						echeqRSADTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
						LOGGER.info("Valor de ultimos dias cambio token: {}", antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}

	}

	private Long obtenerMonto(ConfirmarAltaEndosarECheqView confirmarAltaEndosarECheqView) {
		String monto = confirmarAltaEndosarECheqView.getImporte();
		int cantDecimals = monto.length() - monto.indexOf('.');
		if(cantDecimals > monto.length()) {
			return Long.valueOf(monto + StringUtils.repeat("0",2));
		}
		monto = monto.replace(".","");
		return Long.valueOf(monto + StringUtils.repeat("0",3- cantDecimals));
	}

}