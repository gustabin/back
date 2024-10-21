/*
 *
 */
package ar.com.santanderrio.obp.servicios.billetera.web.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraRSADTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.MedioDePagoBilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.DesafioView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.FeedbackBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.MedioDePagoBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionBilletera;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * The Class BilleteraHelper.
 */
@Component
public class BilleteraHelper {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BilleteraHelper.class);

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion billetera. */
	@Autowired
	private SesionBilletera sesionBilletera;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.BILLETERA}")
	private String valorDesafio;

	/**
	 * Crear billetera in DTO.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the billetera in DTO
	 */
	public BilleteraInDTO crearBilleteraInDTO(BilleteraView viewRequest) {
		BilleteraInDTO dto = new BilleteraInDTO();
		BeanUtils.copyProperties(viewRequest, dto);
		List<MedioDePagoBilleteraDTO> mediosDePagoActivos = new ArrayList<MedioDePagoBilleteraDTO>();
		List<Cuenta> cuentas = sesionCliente.getCliente().getCuentas();
		for (TarjetaActivaView tarjetaActiva : viewRequest.getTarjetasActivas()) {
			MedioDePagoBilleteraDTO medioPagoActivo = new MedioDePagoBilleteraDTO();
			Cuenta ctaAsociada = cuentas.get(tarjetaActiva.getId());
			medioPagoActivo.setCtaAsociada(ctaAsociada);
			medioPagoActivo.setFavorito(tarjetaActiva.getFavorita() ? BilleteraConstants.S : BilleteraConstants.N);
			medioPagoActivo.setNumeroTarjeta(ctaAsociada.getNroTarjetaCredito());
			medioPagoActivo.setEstadoVinc(BilleteraConstants.S);
			mediosDePagoActivos.add(medioPagoActivo);
		}
		dto.setMediosDePagoActivos(mediosDePagoActivos);
		return dto;
	}

	/**
	 * Ejecutar validacion rsa.
	 *
	 * @param <T>
	 *            the generic type
	 * @param viewRequest
	 *            the view request
	 * @param mode
	 *            the mode
	 * @return the respuesta
	 */
	public <T> Respuesta<T> ejecutarValidacionRsa(T viewRequest, String mode) {
		if (((DesafioView) viewRequest).getDesafio() == null) {
			sesionParametros.resetearDesafioEnCurso();
		}
		if (!sesionParametros.isExisteDesafioEnCurso()) {
			LOGGER.info("Consultando RSA y evaluando metodos de autentificacion");
			return analizarRsaObteniendoUnDesafio(viewRequest, mode);
		} else {
			LOGGER.info("Validando desafio en curso");
			return obtenerRespuestaDesafioEnCurso(viewRequest);
		}
	}

	/**
	 * Fill feedback view.
	 *
	 * @param billeteraDTO
	 *            the billetera DTO
	 * @return the feedback billetera view
	 * @throws ParseException
	 *             the parse exception
	 */
	public FeedbackBilleteraView fillFeedbackView(BilleteraDTO billeteraDTO) throws ParseException {
		FeedbackBilleteraView feedbackView = new FeedbackBilleteraView();
		feedbackView.setEmail(sesionBilletera.getEmail());
		List<MedioDePagoBilleteraView> tarjetasOk = getTarjetasOk(billeteraDTO);
		feedbackView.setTarjetasOk(tarjetasOk);
		if (billeteraDTO.getCuentaAcreditacionOk() != null) {
			feedbackView.setCuentaAcreditacionOk(billeteraDTO.getCuentaAcreditacionOk());
		}
		boolean impactCta = billeteraDTO.getCuentaAcreditacion() != null;
		if (!billeteraDTO.getMediosDePagoError().isEmpty() || (impactCta && sesionBilletera.getErrCta())) {
			String mensajeError = getMensajeError(billeteraDTO, impactCta);
			feedbackView.setMensajeError(mensajeError);
		} else {
			feedbackView.setMensaje(mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_IMPACTO_OK).getMensaje());
		}
		feedbackView.setMode(sesionBilletera.getMode());
		return feedbackView;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @param billeteraDTO
	 *            the billetera DTO
	 * @param impactCta
	 *            the impact cta
	 * @return the mensaje error
	 */
	public String getMensajeError(BilleteraDTO billeteraDTO, boolean impactCta) {
		String errTrj = "";
		if (!billeteraDTO.getMediosDePagoError().isEmpty()) {
			StringBuilder trjErrors = new StringBuilder();
			for (int i = 0; i < billeteraDTO.getMediosDePagoError().size(); i++) {
				String trjError = billeteraDTO.getMediosDePagoError().get(i);
				trjErrors.append(trjError);
				if (i < billeteraDTO.getMediosDePagoError().size() - 1) {
					trjErrors.append(", ");
				}
			}
			errTrj = MessageFormat.format(
					mensajeManager
							.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_ERROR_ADHESION_TARJETAS)
							.getMensaje(),
					billeteraDTO.getMediosDePagoError().size() == 1 ? "la tarjeta" : "las tarjetas", trjErrors);
		}
		String errCta = "";
		if (impactCta && billeteraDTO.getCuentaAcreditacionError() != null) {
			errCta = MessageFormat.format(mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_ERROR_ADHESION_CUENTA)
					.getMensaje(), billeteraDTO.getCuentaAcreditacionError());
		}
		return MessageFormat.format(mensajeManager
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_BILLETERA_ERROR_ADHESION).getMensaje(), errTrj,
				errCta);
	}

	/**
	 * Gets the tarjetas ok.
	 *
	 * @param billeteraDTO
	 *            the billetera DTO
	 * @return the tarjetas ok
	 * @throws ParseException
	 *             the parse exception
	 */
	public List<MedioDePagoBilleteraView> getTarjetasOk(BilleteraDTO billeteraDTO) throws ParseException {
		List<MedioDePagoBilleteraView> tarjetasOk = new ArrayList<MedioDePagoBilleteraView>();
		for (MedioDePagoBilleteraDTO mp : billeteraDTO.getMediosDePagoOk()) {
			MedioDePagoBilleteraView tarjetaOk = new MedioDePagoBilleteraView();
			Cuenta cuenta = mp.getCtaAsociada();
			tarjetaOk.setAlias(cuenta.getAlias());
			tarjetaOk.setEstadoVinc(true);
			tarjetaOk.setFavorita(BilleteraConstants.S.equals(mp.getFavorito()) ? true : false);
			tarjetaOk.setId(cuenta.getIndex());
			tarjetaOk.setMarca(BilleteraUtils.getMarcaTrj(cuenta));
			tarjetaOk.setNumero(BilleteraUtils.getNroTrjFmt(mp.getNumeroTarjeta(), cuenta.getTipoCuenta()));
			tarjetaOk.setVencimiento(BilleteraUtils.getFechaVtoTrjDisplay(cuenta.getCbu()));
			tarjetasOk.add(tarjetaOk);
		}
		return tarjetasOk;
	}

	/**
	 * Validar campos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return true, if successful
	 */
	public static boolean validarCampos(BilleteraView viewRequest) {
		if (!validarCampoSeg(viewRequest.getClave(), BilleteraConstants.PATTERN_CLAVE_TP)) {
			LOGGER.error("Error en campo clave");
			return false;
		}
		if (!validarCampoSeg(viewRequest.getRespuestaSeguridad(), BilleteraConstants.PATTERN_RESP_SEG_TP)) {
			LOGGER.error("Error en campo respuestaSeguridad");
			return false;
		}
		if (!validarCelular(viewRequest.getCodigoArea(), viewRequest.getTelefono(),
				viewRequest.getEmpresaSeleccionada())) {
			return false;
		}
		return true;
	}

	/**
	 * Validar campo seg.
	 *
	 * @param campoSeg
	 *            the campo seg
	 * @param pattern
	 *            the pattern
	 * @return true, if successful
	 */
	public static boolean validarCampoSeg(String campoSeg, String pattern) {
		if ("".equals(campoSeg) || !validarCampo(campoSeg, pattern)) {
			return false;
		}
		return true;
	}

	/**
	 * Validar mail.
	 *
	 * @param mail
	 *            the mail
	 * @return true, if successful
	 */
	public static boolean validarMail(String mail) {
		if ("".equals(mail) || mail.length() > BilleteraConstants.MAXLEN_MAIL_TP
				|| !ISBANStringUtils.validarEmail(mail)) {
			return false;
		}
		return true;
	}

	/**
	 * Analizar rsa obteniendo un desafio.
	 *
	 * @param <T>
	 *            the generic type
	 * @param viewRequest
	 *            the view request
	 * @param mode
	 *            the mode
	 * @return the respuesta
	 */
	private <T> Respuesta<T> analizarRsaObteniendoUnDesafio(T viewRequest, String mode) {
		if (BilleteraConstants.MODE_ADH.equals(mode)) {
			asignarEstadisticasAutenticacionAdh();
		} else {
			asignarEstadisticasAutenticacionMod();
		}
		Respuesta<AutentificacionDTO> respuestaAutentificacion = autentificacionManager
				.analizarRSAyObtenerAutenticacionValida(crearAutentificacionDTO());
		if (EstadoRespuesta.OK.equals(respuestaAutentificacion.getEstadoRespuesta())) {
			LOGGER.info("La operacion no requiere un desafio adicional");
			sesionParametros.resetearDesafioEnCurso();
			return respuestaFactory.crearRespuestaOk(viewRequest);
		} else if (EstadoRespuesta.WARNING.equals(respuestaAutentificacion.getEstadoRespuesta())) {
			LOGGER.info("Desafiando al usuario");
//					+ respuestaAutentificacion.getRespuesta().getTipoDesafio().getDescripcion());
			boolean hacerChallenge = !TipoError.SIN_METODO_DESAFIO.getDescripcion()
					.equals(respuestaAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError());
			sesionParametros.setExisteDesafioEnCurso(hacerChallenge);
			((DesafioView) viewRequest).setDesafio(respuestaAutentificacion.getRespuesta());
			return respuestaFactory.crearRespuestaWarning(null, viewRequest,
					respuestaAutentificacion.getItemsMensajeRespuesta());
		} else {
			LOGGER.info("Operacion Desafio con ERROR");
			sesionParametros.setDesafioEnCurso(null);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}

	/**
	 * Asigna estadisticas de autenticacion para adhesion.
	 */
	private void asignarEstadisticasAutenticacionAdh() {
		autentificacionManager
				.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOFT_TOKEN_SOLICITUD_BILLETERA_ADHESION);
		autentificacionManager
				.setCodigoEstadisticaValidacionToken(EstadisticasConstants.SOFT_TOKEN_VALIDACION_BILLETERA_ADHESION);
		autentificacionManager.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.COORDENADAS_SOLICITUD_BILLETERA_ADHESION);
		autentificacionManager.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.COORDENADAS_VALIDACION_BILLETERA_ADHESION);
		autentificacionManager.setCodigoEstadisticaValidacionBanelco(
				EstadisticasConstants.OCHO_DIGITOS_VALIDACION_BILLETERA_ADHESION);
		autentificacionManager
				.setCodigoEstadisticaValidacionCvv2(EstadisticasConstants.CVV2_VALIDACION_BILLETERA_ADHESION);
	}

	/**
	 * Asigna estadisticas de autenticacion para recupero de clave y
	 * modificacion.
	 */
	private void asignarEstadisticasAutenticacionMod() {
		autentificacionManager
				.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.SOFT_TOKEN_SOLICITUD_BILLETERA_MODIFICACION);
		autentificacionManager.setCodigoEstadisticaValidacionToken(
				EstadisticasConstants.SOFT_TOKEN_VALIDACION_BILLETERA_MODIFICACION);
		autentificacionManager.setCodigoEstadisticaSolicitudCoordenadas(
				EstadisticasConstants.COORDENADAS_SOLICITUD_BILLETERA_MODIFICACION);
		autentificacionManager.setCodigoEstadisticaValidacionCoordenadas(
				EstadisticasConstants.COORDENADAS_VALIDACION_BILLETERA_MODIFICACION);
		autentificacionManager.setCodigoEstadisticaValidacionBanelco(
				EstadisticasConstants.OCHO_DIGITOS_VALIDACION_BILLETERA_MODIFICACION);
		autentificacionManager
				.setCodigoEstadisticaValidacionCvv2(EstadisticasConstants.CVV2_VALIDACION_BILLETERA_MODIFICACION);
	}

	/**
	 * Cargar autentificacion DTO.
	 *
	 * @return the autentificacion DTO
	 */
	private AutentificacionDTO crearAutentificacionDTO() {
		AutentificacionDTO desafio = new AutentificacionDTO();
		desafio.setRsaDTO(new BilleteraRSADTO());
		desafio.setOperacion(Integer.parseInt(valorDesafio));
		desafio.setValorNotificarRSA(true);
		desafio.setCoordenadasOperacion(obtenerCoordenadasOperacionDTO());
		desafio.setBanelcoOperacion(null);
		desafio.setTokenOperacion(null);
		desafio.setCvv2Operacion(null);
		desafio.setClienteDTO(null);
		desafio.setReintentosAgotados(false);
		desafio.setValorNotificarRSA(false);
		return desafio;
	}

	/**
	 * Obtener coordenadas operacion DTO.
	 *
	 * @return the coordenadas operacion DTO
	 */
	private CoordenadasOperacionDTO obtenerCoordenadasOperacionDTO() {
		// TODO **** LIMPIAR CUANDO SE UNIFIQUE OPERACION COORDENADA, ver
		// @leonardo.medina
		PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
		pedidoCoordenada.setIp("192168001001");
		CoordenadasOperacionDTO dto = new CoordenadasOperacionDTO();
		dto.setPedidoCoordenada(pedidoCoordenada);
		return dto;
	}

	/**
	 * Obtener respuesta desafio en curso.
	 *
	 * @param <T>
	 *            the generic type
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	private <T> Respuesta<T> obtenerRespuestaDesafioEnCurso(T viewRequest) {
		AutentificacionDTO desafio = ((DesafioView) viewRequest).getDesafio();
		if (desafio.getCoordenadasOperacion() != null) {
			// TODO_VER
			PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
			pedidoCoordenada.setIp("192168001001");
			desafio.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
		}
		Respuesta<AutentificacionDTO> autentificacion = autentificacionManager.ejecutarMetodoAutentificacion(desafio);
		if (autentificacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			sesionParametros.resetearDesafioEnCurso();
			return respuestaFactory.crearRespuestaOk(viewRequest);
		} else {
			if (autentificacion.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
				return respuestaFactory.crearRespuestaWarning(null, viewRequest,
						autentificacion.getItemsMensajeRespuesta());
			}
		}
		return respuestaFactory.crearRespuestaError(null, autentificacion.getItemsMensajeRespuesta());
	}

	/**
	 * Validar campo.
	 *
	 * @param campo
	 *            the campo
	 * @param pattern
	 *            the pattern
	 * @return true, if successful
	 */
	private static boolean validarCampo(String campo, String pattern) {
		Pattern patternCampo = Pattern.compile(pattern);
		Matcher matcher = patternCampo.matcher(campo);
		return matcher.matches();
	}

	/**
	 * Validar celular.
	 *
	 * @param codArea
	 *            the cod area
	 * @param telefono
	 *            the telefono
	 * @param empresaSeleccionada
	 *            the empresa seleccionada
	 * @return true, if successful
	 */
	private static boolean validarCelular(String codArea, String telefono, String empresaSeleccionada) {
		if (!validarTelefono(codArea, telefono)) {
			return false;
		}
		if (empresaSeleccionada == null || "".equals(empresaSeleccionada)) {
			LOGGER.error("empresaSeleccionada invalido: {}", empresaSeleccionada);
			return false;
		}
		return true;
	}

	/**
	 * Validar telefono.
	 *
	 * @param codigoArea
	 *            the codigo area
	 * @param nroCel
	 *            the nro cel
	 * @return true, if successful
	 */
	private static boolean validarTelefono(String codigoArea, String nroCel) {
		if ("".equals(codigoArea) || !StringUtils.isNumeric(codigoArea)) {
			LOGGER.error("codigoArea invalido: {}", codigoArea);
			return false;
		}
		if ("".equals(nroCel) || !StringUtils.isNumeric(nroCel)) {
			LOGGER.error("nroCel invalido: {}", nroCel);
			return false;
		}
		String cel = codigoArea + "-" + nroCel;
		if (cel.length() != BilleteraConstants.MAXLEN_CEL_TP) {
			LOGGER.error("codigoArea + nroCel debe tener 10 digitos: {}", cel);
			return false;
		}
		return true;
	}
}
