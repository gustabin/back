/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraHelper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.bo.AdhesionBilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dto.AdministrarClaveBilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.CuentaBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.AdhesionBilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CrearUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.EmpresaCelularView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.FeedbackBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.IngresoBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.RecuperoClaveBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;

/**
 * The Class AdhesionBilleteraManagerImpl.
 */
@Component("adhesionBilleteraManager")
public class AdhesionBilleteraManagerImpl extends BilleteraManagerImpl implements AdhesionBilleteraManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdhesionBilleteraManagerImpl.class);

	/** The adhesion billetera BO. */
	@Autowired
	private AdhesionBilleteraBO adhesionBilleteraBO;

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * AdhesionBilleteraManager#confirmarAltaUsuario(ar.com.santanderrio.obp.
	 * servicios.billetera.web.view.BilleteraView)
	 */
	@Override
	public Respuesta<BilleteraView> confirmarAltaUsuario(BilleteraView viewRequest) {
		try {
			if (viewRequest.getDesafio() == null) {
				cargarHash(crearMapaDeLaVista(viewRequest));
			}
			Respuesta<BilleteraView> respuestaRsa = billeteraHelper.ejecutarValidacionRsa(viewRequest,
					BilleteraConstants.MODE_ADH);
			if (!EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
				return respuestaRsa;
			}
			validarHash(crearMapaDeLaVista(viewRequest));

			if (!billeteraHelper.validarCampos(viewRequest)) {
				estadisticaManager.add(EstadisticasConstants.BILLETERA_ADHESION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
			}

			return altaUsuario(viewRequest);
		} catch (Exception e) {
			LOGGER.error("Error al confirmar alta de usuario", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}

	/**
	 * Crear usuario.
	 *
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.AdhesionBilleteraManager#crearUsuario()
	 */
	@Override
	public Respuesta<CrearUsuarioBilleteraView> crearUsuario() {
		CrearUsuarioBilleteraView crearUsuarioBilleteraView = new CrearUsuarioBilleteraView();

		List<EmpresaCelularView> empresasCelular = new ArrayList<EmpresaCelularView>();
		TipoCompaniaEnum[] values = TipoCompaniaEnum.values();
		for (TipoCompaniaEnum tipo : values) {
			EmpresaCelularView empresaCelularView = new EmpresaCelularView();
			empresaCelularView.setDescripcion(tipo.getDescripcion());
			empresaCelularView.setId(tipo.getCodigo());
			empresasCelular.add(empresaCelularView);
		}
		crearUsuarioBilleteraView.setEmpresas(empresasCelular);
		crearUsuarioBilleteraView.setCodArea("");
		crearUsuarioBilleteraView.setNroCel("");
		crearUsuarioBilleteraView.setNroCel("");
		crearUsuarioBilleteraView.setEmpCel("");

		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		if (!ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())
				&& credencialesMya.getEmail() != null && StringUtils.isNotEmpty(credencialesMya.getEmail().trim())) {
			crearUsuarioBilleteraView.setMail(credencialesMya.getEmail().trim());
		}
		if (credencialesMya.getCelular() != null && !"".equals(credencialesMya.getCelular().trim())) {
			crearUsuarioBilleteraView.setCodArea(credencialesMya.getCodigoArea());
			crearUsuarioBilleteraView.setNroCel(credencialesMya.getCelular());
			TipoCompaniaEnum compania = TipoCompaniaEnum.fromDescripcion(credencialesMya.getCompaniaSeleccionada());
			crearUsuarioBilleteraView.setEmpCel(compania.getCodigo());
		}

		crearUsuarioBilleteraView.setPreguntasSeguridad(getPreguntasSeguridad());

		crearUsuarioBilleteraView.setMensaje(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_CREAR_USUARIO).getMensaje());
		crearUsuarioBilleteraView.setMensajePassInvalida(mensajeManager
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.BILLETERA_PASSWORD_INVALIDA).getMensaje());
		crearUsuarioBilleteraView.setMensajePassNoCoincide(mensajeManager
                .obtenerMensajePorCodigo(CodigoMensajeConstantes.BILLETERA_PASSWORD_NO_COINCIDE).getMensaje());
		return respuestaFactory.crearRespuestaOk(crearUsuarioBilleteraView);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * AdhesionBilleteraManager#descargaComprobanteAdhesion()
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteAdhesion() {
		Respuesta<Reporte> reporteRespuesta = adhesionBilleteraBO.generarComprobante();
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * AdhesionBilleteraManager#preConfirmarAltaUsuario(ar.com.santanderrio.obp.
	 * servicios.billetera.web.view.PreConfirmarBilleteraView)
	 */
	@Override
	public Respuesta<PreConfirmarBilleteraView> preConfirmarAltaUsuario(PreConfirmarBilleteraView viewRequest) {
		return preConfirmar(viewRequest, EstadisticasConstants.BILLETERA_ADHESION);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * AdhesionBilleteraManager#primerIngreso(ar.com.santanderrio.obp.servicios.
	 * billetera.web.view.IngresoBilleteraInView)
	 */
	@Override
	public Respuesta<ValidarUsuarioBilleteraView> primerIngreso(IngresoBilleteraInView viewRequest) {
		if (!BilleteraHelper.validarCampoSeg(viewRequest.getClave(), BilleteraConstants.PATTERN_CLAVE_TP)) {
			LOGGER.error("Error en campo clave");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		AdministrarClaveBilleteraInDTO dto = new AdministrarClaveBilleteraInDTO();
		BeanUtils.copyProperties(viewRequest, dto);
		Respuesta<Void> respuestaAdministrarClaveBilletera = adhesionBilleteraBO.administrarClave(dto,
				BilleteraConstants.MODE_ADMCLAVES_LOGIN);
		if (EstadoRespuesta.OK.equals(respuestaAdministrarClaveBilletera.getEstadoRespuesta())) {
			sesionBilletera.setConsultaCuentaDto(null);
			return respuestaFactory.crearRespuestaOk(ValidarUsuarioBilleteraView.class);
		} else {
			return respuestaFactory.crearRespuestaError(ValidarUsuarioBilleteraView.class,
					respuestaAdministrarClaveBilletera.getItemsMensajeRespuesta());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * AdhesionBilleteraManager#recuperoClaveBilletera(ar.com.santanderrio.obp.
	 * servicios.billetera.web.view.RecuperoClaveBilleteraView)
	 */
	@Override
	public Respuesta<RecuperoClaveBilleteraView> recuperoClaveBilletera(RecuperoClaveBilleteraView viewRequest) {
		try {
			if (viewRequest.getDesafio() == null) {
				cargarHash(crearMapaDeLaVistaRecupero(viewRequest));
			}
			Respuesta<RecuperoClaveBilleteraView> respuestaRsa = billeteraHelper.ejecutarValidacionRsa(viewRequest,
					BilleteraConstants.MODE_MOD);
			if (!EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
				return respuestaRsa;
			}
			validarHash(crearMapaDeLaVistaRecupero(viewRequest));
			return recuperoClave(viewRequest);
		} catch (Exception e) {
			LOGGER.error("Error al recuperar clave de usuario", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}

	/**
	 * Alta usuario.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	private Respuesta<BilleteraView> altaUsuario(BilleteraView viewRequest) {
		BilleteraInDTO dto = billeteraHelper.crearBilleteraInDTO(viewRequest);
		setearCuentaEnAlta(viewRequest, dto);
		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		Respuesta<BilleteraDTO> respuestaAltaUsuarioBilletera = adhesionBilleteraBO.altaUsuario(dto, registroSesion);
		if (EstadoRespuesta.OK.equals(respuestaAltaUsuarioBilletera.getEstadoRespuesta())) {
			BilleteraView altaUsuarioBilleteraView;
			try {
				altaUsuarioBilleteraView = crearRetornoAltaUsuario(respuestaAltaUsuarioBilletera.getRespuesta(),
						viewRequest);
			} catch (ParseException e) {
				LOGGER.error("Error al crear retorno para Alta Usuario.", e);
				estadisticaManager.add(EstadisticasConstants.BILLETERA_ADHESION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
			}

			// Fija la marca de adhesion en HB_CLIENT_MASTER
			if (respuestaAltaUsuarioBilletera.getRespuesta().getMarcarAdhesion()
					&& !billeteraBO.marcarAdhesion(sesionCliente.getCliente(), registroSesion)) {
				estadisticaManager.add(EstadisticasConstants.BILLETERA_ADHESION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ADHESION_BILLETERA,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
			}

			estadisticaManager.add(EstadisticasConstants.BILLETERA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(altaUsuarioBilleteraView);
		} else {
			estadisticaManager.add(EstadisticasConstants.BILLETERA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null, respuestaAltaUsuarioBilletera.getItemsMensajeRespuesta());
		}
	}
	
	/**
	 * Setear cuenta en alta.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param dto
	 *            the dto
	 */
	private void setearCuentaEnAlta(BilleteraView viewRequest, BilleteraInDTO dto) {
		if (null == sesionBilletera.getConsultaCuentaDto().getCuentas()) {
			List<CuentaBilleteraDTO> cuentas = new ArrayList<CuentaBilleteraDTO>();
			CuentaBilleteraDTO cuenta = new CuentaBilleteraDTO();
			cuenta.setMail(viewRequest.getEmail());
			cuenta.setNumeroCelular(dto.getCodigoArea() + " " + dto.getTelefono());
			cuenta.setCompaniaCelular(dto.getEmpresaSeleccionada());
			cuentas.add(cuenta);
			sesionBilletera.getConsultaCuentaDto().setCuentas(cuentas);
		}
	}

	/**
	 * Crear retorno alta usuario.
	 *
	 * @param billeteraDTO
	 *            the alta usuario billetera DTO
	 * @param viewRequest
	 *            the view request
	 * @return the alta usuario billetera view
	 * @throws ParseException
	 *             the parse exception
	 */
	private BilleteraView crearRetornoAltaUsuario(BilleteraDTO billeteraDTO, BilleteraView viewRequest)
			throws ParseException {
		BilleteraView retornoView = new BilleteraView();
		retornoView.setFecha(billeteraDTO.getFecha());
		retornoView.setHora(billeteraDTO.getHora());
		retornoView.setNroComprobante(billeteraDTO.getNroComprobante());
		retornoView.setMensaje("");
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_CONSERVAR_COMPROBANTE);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			retornoView.setLegalesSEO(respuestaLegales.getRespuesta());
		}
		retornoView.setCodigoArea(viewRequest.getCodigoArea());
		retornoView.setEmail(sesionBilletera.getEmail());
		retornoView.setEmpresaSeleccionada(
				TipoCompaniaEnum.fromCodigo(viewRequest.getEmpresaSeleccionada()).getDescripcion());
		retornoView.setTelefono(viewRequest.getTelefono());
		retornoView.setIdTransaccion(obtenerIdTransaccionRandomParaTealium());

		// Valores Feedback
		FeedbackBilleteraView feedbackView = billeteraHelper.fillFeedbackView(billeteraDTO);
		retornoView.setFeedback(feedbackView);

		return retornoView;
	}

	/**
	 * Obtener id transaccion random para tealium.
	 *
	 * @return the string
	 */
	private String obtenerIdTransaccionRandomParaTealium() {
	    Random rand = new Random();
	    int num = rand.nextInt(900000) + 100000;
	    return Integer.toString(num);
    }

    /**
	 * Gets the preguntas seguridad.
	 *
	 * @return the preguntas seguridad
	 */
	private List<PreguntaSeguridadView> getPreguntasSeguridad() {
		List<PreguntaSeguridadView> preguntasSeguridadView = new ArrayList<PreguntaSeguridadView>();
		List<Opcion> preguntasSeguridad = datosSelectoresService.obtenerPreguntasSeguridad();
		for (Opcion opcion : preguntasSeguridad) {
			PreguntaSeguridadView preguntaSeguridadView = new PreguntaSeguridadView();
			preguntaSeguridadView.setDescripcion(opcion.getOpcion());
			preguntaSeguridadView.setId(opcion.getId());
			preguntasSeguridadView.add(preguntaSeguridadView);
		}
		return preguntasSeguridadView;
	}

	/**
	 * Recupero clave.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	private Respuesta<RecuperoClaveBilleteraView> recuperoClave(RecuperoClaveBilleteraView viewRequest) {
		if (!BilleteraHelper.validarCampoSeg(viewRequest.getRespuestaSeguridad(),
				BilleteraConstants.PATTERN_RESP_SEG_TP)) {
			LOGGER.error("Error en campo respuestaSeguridad");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		AdministrarClaveBilleteraInDTO dto = new AdministrarClaveBilleteraInDTO();
		BeanUtils.copyProperties(viewRequest, dto);
		Respuesta<Void> respuestaAdministrarClaveBilletera = adhesionBilleteraBO.administrarClave(dto,
				BilleteraConstants.MODE_ADMCLAVES_REC);
		if (EstadoRespuesta.OK.equals(respuestaAdministrarClaveBilletera.getEstadoRespuesta())) {
			sesionBilletera.setConsultaCuentaDto(null);
			return respuestaFactory.crearRespuestaOk(RecuperoClaveBilleteraView.class);
		} else {
			return respuestaFactory.crearRespuestaError(RecuperoClaveBilleteraView.class,
					respuestaAdministrarClaveBilletera.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Crear mapa de la vista recupero.
	 *
	 * @param recuperoClaveBilleteraView
	 *            the recupero clave billetera view
	 * @return the map
	 */
	private Map<String, Object> crearMapaDeLaVistaRecupero(RecuperoClaveBilleteraView recuperoClaveBilleteraView) {
		LOGGER.info("Creando hash de los atributos...");
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("idxCtaTp",
				recuperoClaveBilleteraView.getIdxCtaTp() != null ? recuperoClaveBilleteraView.getIdxCtaTp() : "");
		mapaAtributos.put("respuestaSeguridad", recuperoClaveBilleteraView.getRespuestaSeguridad() != null
				? recuperoClaveBilleteraView.getRespuestaSeguridad() : "");
		LOGGER.info("String mapa vista: {}", mapaAtributos.toString());
		return mapaAtributos;
	}

}
