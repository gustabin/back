/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.servicios.billetera.bo.BilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.ValidarUsuarioBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.BilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraHelper;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.CuentaAcreditacionView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.MedioDePagoBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraInView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.ValidarUsuarioBilleteraView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMyaIn;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoCuentaTarjetaEnum;

/**
 * The Class BilleteraManagerImpl.
 */
@Component("billeteraManager")
public class BilleteraManagerImpl implements BilleteraManager {

	/** The Constant ACTION_MODAL. */
	private static final String ACTION_MODAL = "MODAL";

	/** The Constant NOMODAL_IMPACTED_OK. */
	private static final String ACTION_NOMODAL_IMPACTED_OK = "NOMODAL_IMPACTED_OK";

	/** The Constant NOMODAL_IMPACTED_ERROR. */
	private static final String ACTION_NOMODAL_IMPACTED_ERROR = "NOMODAL_IMPACTED_ERROR";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraManagerImpl.class);

	/** The billetera BO. */
	@Autowired
	@Qualifier("billeteraBO")
	protected BilleteraBO billeteraBO;

	/** The billetera helper. */
	@Autowired
	protected BilleteraHelper billeteraHelper;

	/** The datos selectores service. */
	@Autowired
	protected DatosSelectoresService datosSelectoresService;

	/** The estadistica manager. */
	@Autowired
	protected EstadisticaManager estadisticaManager;

	/** The legal BO. */
	@Autowired
	protected LegalBO legalBO;

	/** The mensaje manager. */
	@Autowired
	protected MensajeManager mensajeManager;

	/** The mya BO. */
	@Autowired
	protected MyaBO myaBO;

	/** The respuesta factory. */
	@Autowired
	protected RespuestaFactory respuestaFactory;

	/** The sesion billetera. */
	@Autowired
	protected SesionBilletera sesionBilletera;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	protected SesionParametros sesionParametros;

	/** The tyc url. */
	@Value("${BILLETERA.TYC.URL}")
	protected String tycUrl;

	/**
	 * Pre confirmar.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param estCode
	 *            the est code
	 * @return the respuesta
	 */
	public Respuesta<PreConfirmarBilleteraView> preConfirmar(PreConfirmarBilleteraView viewRequest, String estCode) {
		if (!BilleteraHelper.validarMail(viewRequest.getEmail())) {
			LOGGER.error("Error en campo email");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
		sesionBilletera.setNumeroCelular("");
		sesionBilletera.setEmpresaCelular("");
		sesionBilletera.setEmail(viewRequest.getEmail());
		sesionBilletera.setMode(viewRequest.getMode());
		if ("1".equals(viewRequest.getMode())) {
			Respuesta<Void> respuestaProcesoMya = procesarMyA(viewRequest.getEmail());
			if (respuestaProcesoMya != null && EstadoRespuesta.ERROR.equals(respuestaProcesoMya.getEstadoRespuesta())) {
				estadisticaManager.add(estCode, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
			return prcRespuestaMyA(respuestaProcesoMya);
		}
		return respuestaFactory.crearRespuestaOk(PreConfirmarBilleteraView.class);
	}

	/**
	 * Validar usuario.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.BilleteraManager#validarUsuario(ValidarUsuarioBilleteraInView
	 *      viewRequest)
	 */
	@Override
	public Respuesta<ValidarUsuarioBilleteraView> validarUsuario(ValidarUsuarioBilleteraInView viewRequest) {
		int idxCtaTp = 0;
		if (StringUtils.isNumeric(viewRequest.getIdxCtaTp()) && StringUtils.isNotEmpty(viewRequest.getIdxCtaTp())) {
			idxCtaTp = Integer.parseInt(viewRequest.getIdxCtaTp());
		}
		RegistroSesion registroSesion = sesionParametros.getRegistroSession();
		Respuesta<ValidarUsuarioBilleteraDTO> respuestaValidarUsuarioBilletera = billeteraBO.validarUsuario(idxCtaTp,
				registroSesion);
		if (EstadoRespuesta.OK.equals(respuestaValidarUsuarioBilletera.getEstadoRespuesta())) {
			ValidarUsuarioBilleteraDTO validarUsuarioBilleteraDTO = respuestaValidarUsuarioBilletera.getRespuesta();
			// Carga datos a presentar
			ValidarUsuarioBilleteraView validarUsuarioBilleteraView;
			try {
				validarUsuarioBilleteraView = crearRetornoValidarUsuario(validarUsuarioBilleteraDTO);
				validarUsuarioBilleteraView.setPreguntaSeguridad(validarUsuarioBilleteraDTO.getPreguntaSeguridad());
				validarUsuarioBilleteraView.setEsMonoProducto(verifiarCuentaMonoProducto());
			} catch (ParseException e) {
				LOGGER.error("Error al crear validarUsuarioBilleteraView.");
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
			return respuestaFactory.crearRespuestaOk(validarUsuarioBilleteraView);
		} else {
			return respuestaFactory.crearRespuestaError(null,
					respuestaValidarUsuarioBilletera.getItemsMensajeRespuesta());
		}
	}
	
	/**
	 * Verifiar cuenta mono producto.
	 *
	 * @return true, if successful
	 */
	private boolean verifiarCuentaMonoProducto(){
		for (Cuenta cuenta : sesionCliente.getCliente().getCuentas()) {
			if(!(cuenta.getTipoCuenta().equals(TipoCuentaTarjetaEnum.TIPOCTA_VISA.getCodigo()) ||
						cuenta.getTipoCuenta().equals(TipoCuentaTarjetaEnum.TIPOCTA_AMEX.getCodigo())
						|| cuenta.getTipoCuenta().equals(TipoCuentaTarjetaEnum.TIPOCTA_MASTER.getCodigo()))) {
					return false;
				}
		}
		return true;
	}
	
	/**
	 * Cargar hash.
	 *
	 * @param viewMap
	 *            the view map
	 */
	public void cargarHash(Map<String, Object> viewMap) {
		String hashView = HashUtils.obtenerHash(viewMap);
		LOGGER.info("Se guarda el hash en sesion.");
		sesionParametros.setValidacionHash(hashView);
	}

	/**
	 * Crear mapa de la vista.
	 *
	 * @param billeteraView
	 *            the billetera view
	 * @return the map
	 */
	public Map<String, Object> crearMapaDeLaVista(BilleteraView billeteraView) {
		LOGGER.info("Creando hash de los atributos...");
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("clave", billeteraView.getClave() != null ? billeteraView.getClave() : "");
		mapaAtributos.put("codigoArea", billeteraView.getCodigoArea() != null ? billeteraView.getCodigoArea() : "");
		mapaAtributos.put("cuentaAcreditacion",
				billeteraView.getCuentaAcreditacion() != null ? billeteraView.getCuentaAcreditacion() : "");
		mapaAtributos.put("email", billeteraView.getEmail() != null ? billeteraView.getEmail() : "");
		mapaAtributos.put("empresaSeleccionada",
				billeteraView.getEmpresaSeleccionada() != null ? billeteraView.getEmpresaSeleccionada() : null);
		mapaAtributos.put("idxCtaTp", billeteraView.getIdxCtaTp());
		mapaAtributos.put("preguntaSeguridad",
				billeteraView.getPreguntaSeguridad() != null ? billeteraView.getPreguntaSeguridad() : "");
		mapaAtributos.put("respuestaSeguridad",
				billeteraView.getRespuestaSeguridad() != null ? billeteraView.getRespuestaSeguridad() : "");
		mapaAtributos.put("telefono", billeteraView.getTelefono() != null ? billeteraView.getTelefono() : "");
		int i = 0;
		for (TarjetaActivaView tarjeta : billeteraView.getTarjetasActivas()) {
			mapaAtributos.put("tarjetaActivaFavorita_" + i, tarjeta.getFavorita());
			mapaAtributos.put("tarjetaActivaId_" + i, tarjeta.getId());
			i++;
		}
		LOGGER.info("String mapa vista: {}", mapaAtributos.toString());
		return mapaAtributos;
	}

	/**
	 * Validar hash.
	 *
	 * @param viewMap
	 *            the view map
	 */
	public void validarHash(Map<String, Object> viewMap) {
		String inputHash = HashUtils.obtenerHash(viewMap);
		LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
				sesionParametros.getValidacionHash(), inputHash);
		HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
	}

	/**
	 * Crear retorno validar usuario.
	 *
	 * @param dto
	 *            the dto
	 * @return the validar usuario billetera view
	 * @throws ParseException
	 *             the parse exception
	 */
	private ValidarUsuarioBilleteraView crearRetornoValidarUsuario(ValidarUsuarioBilleteraDTO dto)
			throws ParseException {
		ValidarUsuarioBilleteraView retornoView = new ValidarUsuarioBilleteraView();
		List<CuentaAcreditacionView> ctas = getCuentasAcreditacionView(dto.getCuentasAcreditacion());
		List<MedioDePagoBilleteraView> mediosPagoView = getMediosPagoBilleteraView(dto.getMediosPago());
//		List<MedioDePagoBilleteraView> mediosPagoOBView = getMediosPagoBilleteraView(dto.getMediosPagoOtrosBancos());
		retornoView.setIdxCtaTp(dto.getIdxCtaTp());
		retornoView.setCuentasDestino(ctas);
		retornoView.setTarjetas(mediosPagoView);
//		retornoView.setTarjetasOtrosBancos(mediosPagoOBView);
		retornoView.setMensaje(dto.getMensaje());
		retornoView.setMensajeTarjetas(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_TARJETAS_ACTIVAS).getMensaje());
		retornoView.setMensajeTarjetasOtrosBancos(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_TARJETAS_OTROS_BANCOS).getMensaje());
		retornoView.setViewName(dto.getViewName());
		retornoView.setEstadoMail(dto.getEstadoMail());
		retornoView.setMailRegistrado(dto.getMailRegistrado());
		retornoView.setTelefono(dto.getTelefono());
		retornoView.setEmpresa(dto.getEmpresa());
		retornoView.setTycUrl(tycUrl);
		retornoView.setEmailsRegistrados(dto.getEmailsRegistrados());
		if (StringUtils.isNotBlank(dto.getMailRegistrado())) {
			CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
			if (ClienteEstadoEnum.SUSCRIPTO_ACTIVO.getCodigo().equals(credencialesMya.getClienteEstado())) {
				if (credencialesMya.getEmail() == null
						|| !dto.getMailRegistrado().trim().equalsIgnoreCase(credencialesMya.getEmail().trim())) {
					retornoView.setAction(ACTION_MODAL);
				}
			} else if (ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())) {
				Respuesta<Void> respuestaProcesoMya = procesarMyA(dto.getMailRegistrado());
				if (respuestaProcesoMya != null
						&& EstadoRespuesta.OK.equals(respuestaProcesoMya.getEstadoRespuesta())) {
					retornoView.setAction(ACTION_NOMODAL_IMPACTED_OK);
				} else {
					retornoView.setAction(ACTION_NOMODAL_IMPACTED_ERROR);
					retornoView.setMensajeMyA(mensajeManager
							.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_ERROR_MYA).getMensaje());
				}
			}
		}
		return retornoView;
	}

	/**
	 * Gets the cuenta default.
	 *
	 * @param ctas
	 *            the ctas
	 * @return the cuenta default
	 */
	private int getCuentaDefault(List<Cuenta> ctas) {
		int defCtaIdx = -1;
		// Busca cuenta para seleccionar
		for (Cuenta cuenta : ctas) {
			if ("01".equals(cuenta.getNroOrdenFirmante())) {
				defCtaIdx = cuenta.getIndex();
				break;
			}
		}
		if (defCtaIdx == -1 && !ctas.isEmpty()) {
			// Fija la primera cuenta como seleccionada por defecto
			defCtaIdx = 0;
		}
		return defCtaIdx;
	}

	/**
	 * Gets the cuentas acreditacion view.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the cuentas acreditacion view
	 */
	private List<CuentaAcreditacionView> getCuentasAcreditacionView(List<Cuenta> cuentas) {
		List<CuentaAcreditacionView> cuentasAcreditacionView = new ArrayList<CuentaAcreditacionView>();
		int id = 0;
		int defIdx = getCuentaDefault(cuentas);
		for (Cuenta cuenta : cuentas) {
			CuentaAcreditacionView cuentaAcreditacion = new CuentaAcreditacionView();
			cuentaAcreditacion.setAlias(cuenta.getAlias());
			cuentaAcreditacion.setDescripcionTipoCuenta(cuenta.getTipoCuentaEnum().getDescripcion());
			cuentaAcreditacion.setId(String.valueOf(id));
			cuentaAcreditacion.setNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta));
			cuentaAcreditacion.setSaldoPesos(cuenta.obtenerSaldoFormateado());
			cuentaAcreditacion.setDefaultCta(id == defIdx);
			cuentasAcreditacionView.add(cuentaAcreditacion);
			id++;
		}
		return cuentasAcreditacionView;
	}

	/**
	 * Gets the medios pago billetera view.
	 *
	 * @param mediosPagoDTO
	 *            the medios pago DTO
	 * @return the medios pago billetera view
	 * @throws ParseException
	 *             the parse exception
	 */
	private List<MedioDePagoBilleteraView> getMediosPagoBilleteraView(List<MedioDePagoBilleteraDTO> mediosPagoDTO)
			throws ParseException {
		List<MedioDePagoBilleteraView> mediosPagoBilleteraView = new ArrayList<MedioDePagoBilleteraView>();
		if (mediosPagoDTO != null) {
			for (MedioDePagoBilleteraDTO medioPagoBilleteraDTO : mediosPagoDTO) {
				MedioDePagoBilleteraView medioPagoBilleteraView = new MedioDePagoBilleteraView();
				Cuenta cuenta = medioPagoBilleteraDTO.getCtaAsociada();
				medioPagoBilleteraView.setMarca(medioPagoBilleteraDTO
						.getMarca()/* BilleteraUtils.getMarcaTrj(cuenta) */);
				medioPagoBilleteraView.setNumero(medioPagoBilleteraDTO.getNumeroTarjeta());
				medioPagoBilleteraView.setAlias(StringUtils.defaultString(cuenta.getAlias()));
				medioPagoBilleteraView.setEstadoVinc(
						BilleteraConstants.S.equals(medioPagoBilleteraDTO.getEstadoVinc()) ? true : false);
				medioPagoBilleteraView
						.setFavorita(BilleteraConstants.S.equals(medioPagoBilleteraDTO.getFavorito()) ? true : false);
				medioPagoBilleteraView.setVencimiento(medioPagoBilleteraDTO.getFechaVencimiento());
				medioPagoBilleteraView.setId(cuenta.getIndex());
				medioPagoBilleteraView.setDisableControls(medioPagoBilleteraDTO.getDisableControls());
				medioPagoBilleteraView.setEstadoEsp(medioPagoBilleteraDTO.getEstadoEsp());
				mediosPagoBilleteraView.add(medioPagoBilleteraView);
			}
		}
		return mediosPagoBilleteraView;
	}

	/**
	 * isWarningMyAError.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return true, if is warning my A error
	 */
	private boolean isWarningMyAError(Respuesta<Void> respuestaMyaWS) {
		try {
			String tipoError = obtenerTipoError(respuestaMyaWS);
			return EstadoRespuesta.WARNING.equals(respuestaMyaWS.getEstadoRespuesta())
					&& TipoError.MYA_ERROR.getDescripcion().equals(tipoError);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Obtener tipo error.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return the string
	 */
	private String obtenerTipoError(Respuesta<Void> respuestaMyaWS) {
		if (respuestaMyaWS.getItemsMensajeRespuesta() != null && !respuestaMyaWS.getItemsMensajeRespuesta().isEmpty()) {
			return respuestaMyaWS.getItemsMensajeRespuesta().get(0).getTipoError();
		}
		return "";
	}

	/**
	 * Prc respuesta MyA.
	 *
	 * @param respuestaMyaWS
	 *            the respuesta mya WS
	 * @return the respuesta
	 */
	private Respuesta<PreConfirmarBilleteraView> prcRespuestaMyA(Respuesta<Void> respuestaMyaWS) {
		if (respuestaMyaWS != null) {
			if (EstadoRespuesta.ERROR.equals(respuestaMyaWS.getEstadoRespuesta())
					|| isWarningMyAError(respuestaMyaWS)) {
				LOGGER.error("Error al impactar en MyA.");
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			} else if (EstadoRespuesta.WARNING.equals(respuestaMyaWS.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaWarning(PreConfirmarBilleteraView.class,
						new PreConfirmarBilleteraView(), respuestaMyaWS.getItemsMensajeRespuesta());
			} else if (EstadoRespuesta.OK.equals(respuestaMyaWS.getEstadoRespuesta())) {
				return respuestaFactory.crearRespuestaOk(PreConfirmarBilleteraView.class);
			}
		}
		return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
	}

	/**
	 * getInMyA.
	 *
	 * @param emailBilletera
	 *            the email billetera
	 * @param credencialesMya
	 *            the credenciales mya
	 * @return the credenciales mya in
	 */
	private CredencialesMyaIn getInMyA(String emailBilletera, CredencialesMya credencialesMya) {
		CredencialesMyaIn credencialesMyaIn = new CredencialesMyaIn();

		if (StringUtils.isNotBlank(emailBilletera) && StringUtils.isNotBlank(credencialesMya.getEmail())
				&& !emailBilletera.equals(credencialesMya.getEmail())) {
			credencialesMyaIn.setTipoOperacionEmail(MyaCodOperacionEnum.MODIFICACION);
		} else if (StringUtils.isNotBlank(emailBilletera) && StringUtils.isBlank(credencialesMya.getEmail())) {
			credencialesMyaIn.setTipoOperacionEmail(MyaCodOperacionEnum.ALTA);
		}

		credencialesMyaIn.setEmail(emailBilletera);

		return credencialesMyaIn;
	}

	/**
	 * Procesar MyA.
	 *
	 * @param emailBilletera
	 *            the emailBilletera
	 * @return the respuesta
	 */
	private Respuesta<Void> procesarMyA(String emailBilletera) {
		Cliente cliente = sesionCliente.getCliente();
		CredencialesMya credencialesMya = sesionParametros.getCredencialesMya();
		CredencialesMyaIn credencialesMyaIn = getInMyA(emailBilletera, credencialesMya);
		Respuesta<Void> respuestaMyaWS = null;
		if (ClienteEstadoEnum.SUSCRIPTO_ACTIVO.getCodigo().equals(credencialesMya.getClienteEstado())) {
			respuestaMyaWS = myaBO.updateDestinos(credencialesMyaIn, cliente);
		} else if (ClienteEstadoEnum.NO_SUSCRIPTO.getCodigo().equals(credencialesMya.getClienteEstado())) {
			respuestaMyaWS = myaBO.registrarClienteMya(cliente, credencialesMyaIn);
		}
		return respuestaMyaWS;
	}

}
