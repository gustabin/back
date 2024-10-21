/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ValidacionRespuestasDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionCvv2InactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionPinInactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticarTarjetaDebitoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.CLienteSinContratoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado3Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado4Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SinCelularRegistradoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SistemaClaveNoDisponibleException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaNoReintentarException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaReintentarException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ValidacionRespuestasDAOImpl.
 */
@Component
public class ValidacionRespuestasDAOImpl extends IatxBaseDAO implements ValidacionRespuestasDAO {

	/** The Constant FUNCION. */
	private static final String FUNCION = "00";
	
	/** The Constant FUNCION_WHATSAPP_HABILITADO. */
	private static final String FUNCION_WHATSAPP_SELECCIONADO = "70";
	
	/** The Constant FUNCION_WHATSAPP_DESHABILITADO. */
	private static final String FUNCION_WHATSAPP_NO_SELECCIONADO = "80";
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionRespuestasDAOImpl.class);

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The prefijo seginform. */
	@Value("${SERVICIO.PREFIJO.VALSGIDESF}")
	private String prefijoValsgidesf;

	/** The version 100. */
	@Value("${SERVICIO.VERSION.VALSGIDESF}")
	private String version;
	
	/** The prefijo seginform. */
	@Value("${SERVICIO.PREFIJO.VALSGITEL}")
	private String prefijoValsgitel;

	/** The version 100. */
	@Value("${SERVICIO.VERSION.VALSGITEL}")
	private String versionValsgitel;

	/** The Constant ERROR_RESPUESTAS_REINTENTAR. */
	private static final Integer ERROR_RESPUESTAS_REINTENTAR = 10050040;

	
	/** The Constant ERROR_HAY_RESPUESTAS_ERRONEAS. */
	private static final Integer ERROR_HAY_RESPUESTAS_ERRONEAS = 10050041;

	/** The Constant ERROR_USUARIO_BLOQUEADO. */
	private static final Integer ERROR_USUARIO_BLOQUEADO = 10050011;

	/** The Constant ERROR_USUARIO_BLOQUEADO2. */
	private static final Integer ERROR_USUARIO_BLOQUEADO2 = 10050014;
	
	/** The Constant ERROR_USUARIO_BLOQUEADO2. */
	private static final Integer ERROR_USUARIO_BLOQUEADO3 = 10050015;
	
	/** The Constant SISTEMA_CLAVE_NO_DISPONIBLE. */
	private static final Integer SISTEMA_CLAVE_NO_DISPONIBLE = 10050020;
	
	/** The Constant ERROR_USUARIO_BLOQUEADO4. */
	private static final Integer ERROR_USUARIO_BLOQUEADO4 = 10050016;
	/** The Constant CLIENTE_SIN_CONTRATO. */
	private static final Integer CLIENTE_SIN_CONTRATO = 10050031;

	/** The Constant SIN_TARJETA_VALIDA. */
	private static final Integer SIN_TARJETA_DEBITO_VALIDA = 10050032;

	/** The Constant SIN_CELULAR_REGISTRADO. */
	private static final Integer SIN_CELULAR_REGISTRADO = 10050033;
	
	/** The Constant NO_ES_POSIBLE_AUTENTICAR_TARJETA_DE_DEBITO. */
	private static final Integer NO_ES_POSIBLE_AUTENTICAR_TARJETA_DE_DEBITO = 10050034;
	
	/** The Constant AUTENTICACION_PIN_INACTIVA. */
	private static final Integer AUTENTICACION_PIN_INACTIVA = 10050036;
	/** The Constant AUTENTICACION_SMS_OTP_INACTIVA. */
	private static final Integer AUTENTICACION_SMS_OTP_INACTIVA = 10050037;
	/** The Constant AUTENTICACION_CVV2_INACTIVA. */
	private static final Integer AUTENTICACION_CVV2_INACTIVA = 10050038;
	
	/** The Constant ERROR_RUTINA_FECHAS. */
	private static final Integer ERROR_RUTINA_FECHAS = 10050090;

	/** The Constant IDENTIFICACION_CORRECTA. */
	private static final Integer IDENTIFICACION_CORRECTA = 00000000;
	
	/** The Constant ERROR_CICS. */
	private static final Integer ERROR_CICS = 10050098 ;
	
	/** The Constant ERROR_DB2. */
	private static final Integer ERROR_DB2 = 10050097;
	/** The Constant ERROR_IATX_NO_RECONOCIDO. */
	private static final String ERROR_IATX_NO_RECONOCIDO = "Código de retorno de IATX no reconocido";
	/** The Constant FUNCION_INVALIDA. */
	private static final Integer FUNCION_INVALIDA = 10050099 ;
	/** The Constant ERROR_MODULO. */
	private static final Integer ERROR_MODULO = 10050094;

	/**
	 * Validar respuesta.
	 *
	 * @param validacionPreguntaIn
	 *            the validacion pregunta in
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionRespuestaReintentarException
	 *             the validacion respuesta reintentar exception
	 * @throws ValidacionRespuestaNoReintentarException
	 *             the validacion respuesta no reintentar exception
	 * @throws ClienteBloqueado2Exception
	 *             the cliente bloqueado 2 exception
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorDb2Exception 
	 * @throws ErrorCicsException 
	 * @throws FuncionInvalidaException 
	 * @throws ErrorModuloException 
	 * @returns
	 */
	@Override
	public void validarRespuesta(ValidacionPreguntaIn validacionPreguntaIn)
			throws DAOException, ValidacionRespuestaReintentarException, ValidacionRespuestaNoReintentarException,
			ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException,
			FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException {

		IatxRequest request = new IatxRequest(prefijoValsgidesf, version);
		ResumenCliente resumenCliente = new ResumenCliente();
		resumenCliente.setDni(StringUtils.leftPad(validacionPreguntaIn.getDni(), 11, '0'));
		resumenCliente.setNup(validacionPreguntaIn.getNup());
		resumenCliente.setFechaNacimiento(
				FechaUtils.parsearFechaDeNacimientoParaIATX(validacionPreguntaIn.getFechaNacimiento()));
		IatxRequestData requestData = new IatxRequestData(resumenCliente);

		requestData.setIndAutenticacion();
		requestData.setRacfInicial();

		requestData = getDatosRequest(requestData, validacionPreguntaIn);

		request.setData(requestData);

		Integer errorCode = null;
		String mensajeErrorIatx=null;
		IatxResponse iatxResponse = null;
		try {
			iatxResponse = iatxComm.exec(request);
			errorCode = iatxResponse.getErrorCode();
		    mensajeErrorIatx = iatxResponse.getErrorMessage();

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}

		if (ERROR_RESPUESTAS_REINTENTAR.equals(errorCode)) {
			LOGGER.debug(iatxResponse.getErrorMessage());
			throw new ValidacionRespuestaReintentarException("Hay respuestas erroneas, se puede reintentar.");
		}
		if (ERROR_USUARIO_BLOQUEADO.equals(errorCode)) {
			LOGGER.debug(iatxResponse.getErrorMessage());
			throw new ValidacionRespuestaNoReintentarException("Hay respuestas erroneas, no se puede reintentar.");
		}
		 if (ERROR_USUARIO_BLOQUEADO2.equals(errorCode)) {
             throw new ClienteBloqueado2Exception(mensajeErrorIatx);
         }
		if (ERROR_RUTINA_FECHAS.equals(errorCode)) {
			throw new ErrorRutinaFechasException(mensajeErrorIatx);
		}
		if (ERROR_DB2.equals(errorCode)) {
			throw new ErrorDb2Exception(mensajeErrorIatx);
		}
		if (ERROR_CICS .equals(errorCode)) {
			throw new ErrorCicsException(mensajeErrorIatx);
		}
		if (FUNCION_INVALIDA .equals(errorCode)) {
			throw new FuncionInvalidaException(mensajeErrorIatx);
		}
		if (ERROR_MODULO.equals(errorCode)) {
			throw new ErrorModuloException(iatxResponse.getErrorMessage());
		}
		if (ERROR_HAY_RESPUESTAS_ERRONEAS.equals(errorCode)) {
			throw new HayRespuestasErroneasException(mensajeErrorIatx);
		}
		
		// En caso de recibir un código de error no reconocido
		if (!IDENTIFICACION_CORRECTA.equals(errorCode)) {
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Arma la seccion de datos del request.
	 *
	 * @param requestData
	 *            the request data
	 * @param validacionPreguntaIn
	 *            the validacion pregunta in
	 * @return the datos request
	 */
	private IatxRequestData getDatosRequest(IatxRequestData requestData, ValidacionPreguntaIn validacionPreguntaIn) {

		String ip = IpUtils.dottedIp2Hex(validacionPreguntaIn.getIp());
		requestData.addBodyValue(FUNCION);
		requestData.addBodyValue(ip);
		requestData.addBodyValue(validacionPreguntaIn.getIdSesion());
		requestData.addBodyValue(String.valueOf(validacionPreguntaIn.getCiclo()));
		requestData.addBodyValue(String.valueOf(validacionPreguntaIn.getOrdenPregunta()));
		requestData.addBodyValue(validacionPreguntaIn.getIdPregunta());
		requestData.addBodyValue(validacionPreguntaIn.getValidacion());

		return requestData;
	}

	/**
	 * Arma la seccion de datos del request.
	 *
	 * @param requestData
	 *            the request data
	 * @param validacionPreguntaIn
	 *            the validacion pregunta in
	 * @return the datos request
	 */
	private IatxRequestData getDatosRequestValsgitel(IatxRequestData requestData, ValidacionPreguntaIn validacionPreguntaIn) {

		String ip = IpUtils.dottedIp2Hex(validacionPreguntaIn.getIp());
		if (validacionPreguntaIn.getCheckWhatsappHabilitado() && validacionPreguntaIn.getCheckWhatsapp()) {
			requestData.addBodyValue(FUNCION_WHATSAPP_SELECCIONADO);
		} else if (validacionPreguntaIn.getCheckWhatsappHabilitado() && !validacionPreguntaIn.getCheckWhatsapp()) {
			requestData.addBodyValue(FUNCION_WHATSAPP_NO_SELECCIONADO);
		} else {
			requestData.addBodyValue(FUNCION);
		}
		requestData.addBodyValue(ip);
		requestData.addBodyValue(validacionPreguntaIn.getIdSesion());
		requestData.addBodyValue(String.valueOf(validacionPreguntaIn.getCiclo()));
		requestData.addBodyValue(String.valueOf("1"));
		requestData.addBodyValue("C01");
		requestData.addBodyValue(validacionPreguntaIn.getValidacion());

		return requestData;
	}

	@Override
	public IdentificadorClienteOutEntity validarRespuestaTelefono(ValidacionPreguntaIn validacionPreguntaIn)
			throws DAOException, ValidacionRespuestaNoReintentarException, ValidacionRespuestaReintentarException,
			ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException,
			ErrorModuloException, FuncionInvalidaException, ClienteBloqueado2Exception, ClienteBloqueado3Exception,
			SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException,
			SinCelularRegistradoException, AutenticarTarjetaDebitoException, AutenticacionPinInactivaException,
			AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ClienteBloqueado4Exception,
			HayRespuestasErroneasException {
		IdentificadorClienteOutEntity identificadorClienteOutEntity = null;

		IatxRequest request = new IatxRequest(this.prefijoValsgitel, this.versionValsgitel);
		ResumenCliente resumenCliente = new ResumenCliente();
		resumenCliente.setDni(StringUtils.leftPad(validacionPreguntaIn.getDni(), 11, '0'));
		resumenCliente.setNup(validacionPreguntaIn.getNup());
		resumenCliente.setFechaNacimiento(
				FechaUtils.parsearFechaDeNacimientoParaIATX(validacionPreguntaIn.getFechaNacimiento()));
		IatxRequestData requestData = new IatxRequestData(resumenCliente);

		requestData.setIndAutenticacion();
		requestData.setRacfInicial();

		requestData = getDatosRequestValsgitel(requestData, validacionPreguntaIn);

		request.setData(requestData);

		IatxResponse iatxResponse = null;
		try {
			iatxResponse = iatxComm.exec(request);
			identificadorClienteOutEntity = analizarRespuesta(iatxResponse);
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}

		return identificadorClienteOutEntity;		
	}
	
	private IdentificadorClienteOutEntity analizarRespuesta(IatxResponse iatxResponse)
			throws DAOException, ValidacionRespuestaNoReintentarException, ValidacionRespuestaReintentarException,
			ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException,
			ErrorModuloException, FuncionInvalidaException, ClienteBloqueado3Exception,
			SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException,
			SinCelularRegistradoException, AutenticarTarjetaDebitoException, AutenticacionPinInactivaException,
			AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ClienteBloqueado4Exception, HayRespuestasErroneasException {
		Integer codigoRetorno = iatxResponse.getErrorCode();
		String mensajeErrorIatx = iatxResponse.getErrorMessage();

		IdentificadorClienteOutEntity identificadorClienteOutEntity = null;
		if (IDENTIFICACION_CORRECTA.equals(codigoRetorno)) {
			identificadorClienteOutEntity = processTrama(iatxResponse.getTrama(), IdentificadorClienteOutEntity.class);
		}
		if (ERROR_RESPUESTAS_REINTENTAR.equals(codigoRetorno)) {
			throw new ValidacionRespuestaReintentarException(mensajeErrorIatx);
		}
		if (ERROR_USUARIO_BLOQUEADO.equals(codigoRetorno)) {
			LOGGER.debug(iatxResponse.getErrorMessage());
			throw new ValidacionRespuestaNoReintentarException("Hay respuestas erroneas, no se puede reintentar.");
		}
		if (ERROR_USUARIO_BLOQUEADO2.equals(codigoRetorno)) {
			throw new ClienteBloqueado2Exception(mensajeErrorIatx);
		}
		if (ERROR_RUTINA_FECHAS.equals(codigoRetorno)) {
			throw new ErrorRutinaFechasException(mensajeErrorIatx);
		}
		if (ERROR_DB2.equals(codigoRetorno)) {
			throw new ErrorDb2Exception(mensajeErrorIatx);
		}
		if (ERROR_CICS.equals(codigoRetorno)) {
			throw new ErrorCicsException(mensajeErrorIatx);
		}
		if (FUNCION_INVALIDA.equals(codigoRetorno)) {
			throw new FuncionInvalidaException(mensajeErrorIatx);
		}
		if (ERROR_MODULO.equals(codigoRetorno)) {
			throw new ErrorModuloException(iatxResponse.getErrorMessage());
		}
		if (ERROR_USUARIO_BLOQUEADO3.equals(codigoRetorno)) {
			throw new ClienteBloqueado3Exception(iatxResponse.getErrorMessage());
		}
		if (SISTEMA_CLAVE_NO_DISPONIBLE.equals(codigoRetorno)) {
			throw new SistemaClaveNoDisponibleException(iatxResponse.getErrorMessage());
		}
		if (ERROR_USUARIO_BLOQUEADO4.equals(codigoRetorno)) {
			throw new ClienteBloqueado4Exception(iatxResponse.getErrorMessage());
		}
		if (CLIENTE_SIN_CONTRATO.equals(codigoRetorno)) {
			throw new CLienteSinContratoException(iatxResponse.getErrorMessage());
		}
		if (SIN_TARJETA_DEBITO_VALIDA.equals(codigoRetorno)) {
			throw new SinTarjetaValidaException(iatxResponse.getErrorMessage());
		}
		if (SIN_CELULAR_REGISTRADO.equals(codigoRetorno)) {
			throw new SinCelularRegistradoException(iatxResponse.getErrorMessage());
		}
		if (NO_ES_POSIBLE_AUTENTICAR_TARJETA_DE_DEBITO.equals(codigoRetorno)) {
			throw new AutenticarTarjetaDebitoException(iatxResponse.getErrorMessage());
		}
		if (AUTENTICACION_PIN_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionPinInactivaException(iatxResponse.getErrorMessage());
		}
		if (AUTENTICACION_SMS_OTP_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionSmsOtpInactivaException(iatxResponse.getErrorMessage());
		}
		if (AUTENTICACION_CVV2_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionCvv2InactivaException(iatxResponse.getErrorMessage());
		}
		if (ERROR_HAY_RESPUESTAS_ERRONEAS.equals(codigoRetorno)) {
			throw new HayRespuestasErroneasException(iatxResponse.getErrorMessage());
		}
		// En caso de recibir un código de error no reconocido
		if (!IDENTIFICACION_CORRECTA.equals(codigoRetorno)) {
			String e = ERROR_IATX_NO_RECONOCIDO;
			throw new DAOException(e);
		}

		return identificadorClienteOutEntity;
	}

}
