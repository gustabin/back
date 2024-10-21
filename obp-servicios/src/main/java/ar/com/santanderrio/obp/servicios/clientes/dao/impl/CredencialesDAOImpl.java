/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.internal.org.apache.commons.codec.binary.Base64;
import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.AuthCliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.LoginResponse;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesArchivoFeparmException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClaveIndividuoVencidaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavePinVencidaNotieneUsuarioException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavesExpiradasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesDocumentoNoExisteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesLoginException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioBloqueadoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioCambioPendienteException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioConSinonimoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioNoDefinidoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioTrivialException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SesionConcurrenteException;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.loggedinusercache.dao.UserCacheDAO;
import ar.com.santanderrio.obp.servicios.login.dao.ApiAuthDAO;
import ar.com.santanderrio.obp.servicios.login.dao.ProxyLoginDAO;
import ar.com.santanderrio.obp.servicios.login.dao.impl.ApiAuthDAOImpl;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPines;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthJWT;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthLoginResponse;
import ar.com.santanderrio.obp.servicios.login.entity.Metadata;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;

/**
 * The Class CredencialesDAOImpl.
 */
@Component
public class CredencialesDAOImpl implements CredencialesDAO {

	private static final String ERROR_AL_VALIDAR_CREDENCIALES = "Error al validar credenciales: {}";

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(CredencialesDAOImpl.class);

	/** The Constant OPERACION_VALIDACION. */
	private static final String OPERACION_VALIDACION = "VALIDAC ";

	/** The Constant OPERACION_ALTA. */
	private static final String OPERACION_ALTA = "ALTA    ";

	/** The Constant FECHA_NAC_VACIA. */
	private static final String FECHA_NAC_VACIA = "        ";

	/** The Constant TRAMA_AREA_DATOS_0499. */
	private static final String TRAMA_AREA_DATOS_0499 = "0499";

	/** The Constant ERROR_USUARIO_BLOQUEADO. */
	private static final Integer ERROR_USUARIO_BLOQUEADO = 10001018;

	/** The Constant ERROR_USUARIO_CON_SINONIMO. */
	private static final Integer ERROR_USUARIO_CON_SINONIMO = 10001001;

	/** The Constant ERROR_DOCUMENTO_NO_EXISTE. */
	private static final Integer ERROR_DOCUMENTO_NO_EXISTE = 10001015;

	/** The Constant PIN_VENCIDO_Y_NO_TIENE_USUARIO_DEFINIDO. */
	private static final Integer PIN_VENCIDO_Y_NO_TIENE_USUARIO_DEFINIDO = 10001002;

	/** The Constant CLAVE_VENCIDA_Y_USUARIO_CORRECTO. */
	private static final Integer CLAVE_VENCIDA_Y_USUARIO_CORRECTO = 10001003;

	/** The Constant CLAVE_INDIVIDUO_VENCIDA. */
	private static final Integer CLAVE_INDIVIDUO_VENCIDA = 10003001;

	/** The Constant CLAVE_OK_USUARIO_INCORRECTO. */
	private static final Integer CLAVE_OK_USUARIO_INCORRECTO = 10001020;

	/** The Constant CLAVE_TRIVIAL_HISTORICO_USUARIO_CORRECTO. */
	private static final Integer CLAVE_TRIVIAL_HISTORICO_USUARIO_CORRECTO = 10001021;

	/** The Constant CLAVE_INCORRECTA. */
	private static final Integer CLAVE_INCORRECTA = 10001016;

	/** The Constant CLAVE_OK_USUARIO_TRIVIAL_HISTORICO. */
	private static final Integer CLAVE_OK_USUARIO_TRIVIAL_HISTORICO = 10001023;

	/** The Constant ERROR_ENCRIPCION. */
	private static final Integer ERROR_ENCRIPCION = 10030000;

	/** The Constant SERVICIO_NO_DISPONIBLE_PARA_CANAL. */
	private static final Integer SERVICIO_NO_DISPONIBLE_PARA_CANAL = 10030010;

	/** The Constant LOGIN_ERROR_USUARIO_NO_DEFINIDO. */
	private static final Integer LOGIN_ERROR_USUARIO_NO_DEFINIDO = 10001004;

	/** The Constant LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE. */
	private static final Integer LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE = 10001036;

	/** The Constant NUEVO_USUARIO_TRIVIAL_O_HISTORICO. */
	private static final Integer NUEVO_USUARIO_TRIVIAL_O_HISTORICO = 10001026;

	/** The Constant USUARIO_ACTUAL_INCORRECTO. */
	private static final Integer USUARIO_ACTUAL_INCORRECTO = 10001027;

	/** The Constant CLAVE_ACTUAL_INCORRECTA. */
	private static final Integer CLAVE_ACTUAL_INCORRECTA = 10001029;

	/** The Constant USUARIO_NUEVO_INCORRECTO. */
	private static final Integer USUARIO_NUEVO_INCORRECTO = 10001030;

	/** The Constant NUEVA_CLAVE_Y_NUEVO_USUARIO_TRIVIALES. */
	private static final Integer NUEVA_CLAVE_Y_NUEVO_USUARIO_TRIVIALES = 10001031;

	/** The Constant NUEVO_USUARIO_OK_NUEVA_CLAVE_TRIVIAL. */
	private static final Integer NUEVO_USUARIO_OK_NUEVA_CLAVE_TRIVIAL = 10001032;

	/** The Constant CLAVE_INCORRECTA_Y_USUARIO_INCORRECTO. */
	private static final Integer CLAVE_INCORRECTA_Y_USUARIO_INCORRECTO = 10001033;

	/** The Constant NUEVO_USUARIO_INC_NUEVA_CLAVE_TRIVIAL. */
	private static final Integer NUEVO_USUARIO_INC_NUEVA_CLAVE_TRIVIAL = 10001034;

	/** The Constant CLAVE_POR_VENCER_EN_X_DIAS. */
	private static final Integer CLAVE_POR_VENCER_EN_X_DIAS = 20001010;

	/** The Constant CLAVE_VENCIDA_U_TR_CLAVE_VENCIDA_Y_USUARIO_TRIVIAL_O_HISTORICO. */
	private static final Integer CLAVE_VENCIDA_U_TR_CLAVE_VENCIDA_Y_USUARIO_TRIVIAL_O_HISTORICO = 10001025;

	/** The Constant OPERACION_CMBUSER. */
	private static final String OPERACION_CMBUSER = "CMBUSER ";
	
	/** The Constant LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE. */
	private static final Integer CODE_FALLO_READ_FEPARM = 10010001;

	private static final Integer CODE_SESION_CONCURRENTE_API_AUTH = 10000003;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The encry pines. */
	@Autowired
	private EncryPines encryPines;

	/** The prefijo seginform. */
	@Value("${SERVICIO.PREFIJO.SEGINFORM}")
	private String prefijoSeginform;

	/** The version130. */
	@Value("${SERVICIO.VERSION.130}")
	private String version130;

	/** The proxy login DAO. */
	@Autowired
	private ProxyLoginDAO proxyLoginDAO;

    /** The iatx inicial. */
    @Autowired
    private IatxInicial iatxInicial;
    
    @Autowired
    private ApiAuthDAO apiAuthDAO;
    
    @Autowired
    private UserCacheDAO userCacheDAO;
    
    @Autowired
    private SesionCliente sesionCliente;
    

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.clientes.dao.ClienteDAO#validarCredenciales(ar.
	 * com.santanderrio.base.clientes.entities.CredencialCliente)
	 */
	@Override
	public ResumenCliente validarCredenciales(CredencialCliente cc) throws CredencialesException, RobotException {

		IatxResponse iatxResponse = validarCredenciales(cc, OPERACION_VALIDACION);

		if (EstadoRespuesta.ERROR.equals(iatxResponse.getEstadoRespuesta())) {

			Integer errorCodeIatx = iatxResponse.getErrorCode();
			logger.debug(iatxResponse.getErrorMessage());
			if (CLAVE_POR_VENCER_EN_X_DIAS.equals(errorCodeIatx)) {
				logger.info(iatxResponse.getErrorMessage());
			} else {

				throw identificarErrorLogin(iatxResponse.getErrorCode(), iatxResponse.getErrorMessage());
			}
		}

		ResumenCliente resumenCliente = parsearResumenCliente(iatxResponse);

		resumenCliente.setDni(cc.getDniOri());

		return resumenCliente;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.clientes.dao.ClienteDAO#validarCredencialesProxyLogin(ar.
	 * com.santanderrio.base.clientes.entities.CredencialCliente)
	 */
	@Override
	public ResumenCliente validarCredencialesProxyLogin(CredencialCliente cc) throws CredencialesException, RobotException {
		ProxyLoginResponse response = null;

		try {
			response = proxyLoginDAO.obtenerTokenLogin(cc);
		} catch (DAOException e) {
			throw new RobotException("");
		}

		if (response.getCode() != null && !"".equals(response.getCode().trim())) {
			Integer errorCode = Integer.parseInt(response.getMessage());
			String errorMessage = response.getDescription();
			logger.info(ERROR_AL_VALIDAR_CREDENCIALES, errorMessage);
			if (CLAVE_POR_VENCER_EN_X_DIAS.equals(errorCode)) {
				logger.info(ERROR_AL_VALIDAR_CREDENCIALES, errorMessage);
			} else {
				throw identificarErrorLogin(errorCode, errorMessage);
			}
		}

		ResumenCliente resumenCliente = parsearResumenCliente(response);
		resumenCliente.setDni(cc.getDniOri());

		return resumenCliente;
	}

	/**
	 * Parsear resumen cliente.
	 *
	 * @param proxyLoginresponse the proxy loginresponse
	 * @return the resumen cliente
	 */
	private ResumenCliente parsearResumenCliente(ProxyLoginResponse proxyLoginresponse) {
		String token = proxyLoginresponse.getToken();
		String payload = decodeTokenPayload(token);
		LoginResponse response = new Gson().fromJson(payload, LoginResponse.class);

		ResumenCliente resumenCliente = new ResumenCliente();
		resumenCliente.setUsuarioRacf(iatxInicial.getRacfInicialId());
		resumenCliente.setPasswordRacf(iatxInicial.getRacfInicialPwd());
		resumenCliente.setNup(response.getUniquePersonNumber());
		String birthDate = response.getBirthDate();
		if (birthDate != null && !"".equals(birthDate.trim())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			try {
				Date dt = sdf.parse(birthDate);
				resumenCliente.setFechaNacimiento(sdf2.format(dt));
			} catch (Exception e) {
				logger.info("Error al parsear fecha: ", e);
			}
		}
		resumenCliente.setTipoPersona(response.getUserType());
		resumenCliente.setMarcaANPH(response.getAnphFlag());
		resumenCliente.setTipoClave(response.getPasswordType());
		resumenCliente.setNroComprobante(response.getTicketNumber());

		return resumenCliente;
	}

	/**
	 * Decode token payload.
	 *
	 * @param token the token
	 * @return the string
	 */
	public String decodeTokenPayload(String token) {
	    String[] parts = token.split("\\.", 0);
	    byte[] bytes = Base64.decodeBase64(parts[1]);
	    return new String(bytes);
	}

	/**
	 * Actualizar clave usuario.
	 *
	 * @param credencialCliente the credencial cliente
	 * @return the resumen cliente
	 * @throws CredencialesException                 the credenciales exception
	 * @throws CredencialesUsuarioBloqueadoException the credenciales usuario
	 *                                               bloqueado exception
	 * @throws RobotException                        the robot exception
	 */
	@Override
	public ResumenCliente actualizarClaveUsuario(CredencialCliente credencialCliente)
	        throws CredencialesException, CredencialesUsuarioBloqueadoException, RobotException {

		String operacion = OPERACION_VALIDACION;

		if (credencialCliente.getIsAlta()) {
			operacion = OPERACION_ALTA;
		}

		IatxResponse iatxResponse = validarCredenciales(credencialCliente, operacion);

		if (EstadoRespuesta.ERROR.equals(iatxResponse.getEstadoRespuesta())) {

			logger.debug(iatxResponse.getErrorMessage());
			throw identificarErrorActualizacionClaveUsuario(iatxResponse.getErrorCode(), iatxResponse.getErrorMessage());
		}

		ResumenCliente resumenCliente = parsearResumenCliente(iatxResponse);

		resumenCliente.setDni(credencialCliente.getDniOri());

		return resumenCliente;
	}

	/**
	 * Identificar error actualizacion clave usuario.
	 *
	 * @param errorCodeIatx the error code iatx
	 * @param errorMessageIatx the error message iatx
	 * @return the credenciales exception
	 * @throws CredencialesException the credenciales exception
	 */
	private CredencialesException identificarErrorActualizacionClaveUsuario(Integer errorCodeIatx, String errorMessageIatx)
	        throws CredencialesException {

		if (USUARIO_ACTUAL_INCORRECTO.equals(errorCodeIatx) || CLAVE_ACTUAL_INCORRECTA.equals(errorCodeIatx)
		        || USUARIO_NUEVO_INCORRECTO.equals(errorCodeIatx)
		        || NUEVA_CLAVE_Y_NUEVO_USUARIO_TRIVIALES.equals(errorCodeIatx)
		        || NUEVO_USUARIO_OK_NUEVA_CLAVE_TRIVIAL.equals(errorCodeIatx)
		        || CLAVE_INCORRECTA_Y_USUARIO_INCORRECTO.equals(errorCodeIatx)// agregar
		                                                                      // usuario
		                                                                      // bloqueado
		        || NUEVO_USUARIO_INC_NUEVA_CLAVE_TRIVIAL.equals(errorCodeIatx)) {

			return new CredencialesException(errorMessageIatx);
		}
		if (CLAVE_OK_USUARIO_INCORRECTO.equals(errorCodeIatx)) {
			return new CredencialesLoginException(TipoError.USUARIO_ACTUAL_INCORRECTO.getDescripcion());
		}
		if (CLAVE_INCORRECTA.equals(errorCodeIatx) || CLAVE_OK_USUARIO_TRIVIAL_HISTORICO.equals(errorCodeIatx)
		        || CLAVE_VENCIDA_U_TR_CLAVE_VENCIDA_Y_USUARIO_TRIVIAL_O_HISTORICO.equals(errorCodeIatx)) {

			return new CredencialesLoginException("Error usuario actual incorrecto");
		}
		if (ERROR_USUARIO_BLOQUEADO.equals(errorCodeIatx)) {

			return new CredencialesUsuarioBloqueadoException(errorMessageIatx);
		}

		if (CLAVE_TRIVIAL_HISTORICO_USUARIO_CORRECTO.equals(errorCodeIatx)
		        || NUEVO_USUARIO_TRIVIAL_O_HISTORICO.equals(errorCodeIatx)) {

			return new CredencialesUsuarioTrivialException(errorMessageIatx);
		}

		if (ERROR_ENCRIPCION.equals(errorCodeIatx) || SERVICIO_NO_DISPONIBLE_PARA_CANAL.equals(errorCodeIatx)) {

			return new CredencialesServiceException(errorMessageIatx);
		}
		
		if (CODE_FALLO_READ_FEPARM.equals(errorCodeIatx)) {
			return new CredencialesArchivoFeparmException(errorMessageIatx);
		}

		if (errorCodeIatx == -1) {
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} 

		return new CredencialesServiceException(errorMessageIatx);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.clientes.dao.ClienteDAO#actualizarClaveUsuarioProxyLogin(ar.
	 * com.santanderrio.base.clientes.entities.CredencialCliente)
	 */
	@Override
	public ResumenCliente actualizarClaveUsuarioProxyLogin(CredencialCliente credencialCliente) 
			throws CredencialesException, CredencialesUsuarioBloqueadoException, RobotException {
		ProxyLoginResponse response = null;

		try {
			if (credencialCliente.getIsAlta()) {
				response = proxyLoginDAO.updatePinPassword(credencialCliente);
			} else {
				response = proxyLoginDAO.obtenerToken(credencialCliente);
			}
		} catch (DAOException e) {
			throw new CredencialesException("");
		}

		verificarRespuestaProxyLogin(response);

		ResumenCliente resumenCliente = parsearResumenCliente(response);

		resumenCliente.setDni(credencialCliente.getDniOri());

		return resumenCliente;
	}

	/**
	 * Validar credenciales.
	 *
	 * @param cc        the cc
	 * @param operacion the operacion
	 * @return the iatx response
	 * @throws CredencialesException the credenciales exception
	 */
	private IatxResponse validarCredenciales(CredencialCliente cc, String operacion) throws CredencialesException {

		IatxRequest request = new IatxRequest(prefijoSeginform, version130);

		ResumenCliente resumenCliente = new ResumenCliente(cc);
		IatxRequestData requestData = new IatxRequestData(resumenCliente);

		requestData.setIndAutenticacion();
		requestData.setRacfInicial();

		String areaDatos = getDatosRequest(cc, operacion);

		requestData.addBodyValue("N");
		requestData.addBodyValue(areaDatos);

		request.setData(requestData);
		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);

		} catch (IatxException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}

		return iatxResponse;

	}

	/**
	 * Arma seccion de datos del Request.
	 *
	 * @param cc        the cc
	 * @param operacion the operacion
	 * @return the datos request
	 */
	private String getDatosRequest(CredencialCliente cc, String operacion) {

		String fechaNac = parsearFechaDeNacimiento(cc.getFechaNacimiento());

		String ip = IpUtils.dottedIp2Hex(cc.getIp());
		String prefijo = cc.getPrefijo();
		String sufijo = cc.getSufijo();
		String usuario = cc.getUsuario();
		String claveNueva = cc.getClaveNueva();

		String areaDatos = operacion + fechaNac + TRAMA_AREA_DATOS_0499 + ip + encryPines.obtenerCadena3Des(
		        prefijo + cc.getDni() + usuario + cc.getUsuarioNuevo() + cc.getClave() + claveNueva + sufijo);

		return areaDatos;
	}

	/**
	 * parsea la fecha, si es null devuelve FECHA_NAC_VACIA.
	 *
	 * @param fechaNacimiento the fecha nacimiento
	 * @return the string
	 */
	private String parsearFechaDeNacimiento(Date fechaNacimiento) {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String fecha;

		if (fechaNacimiento == null) {
			fecha = FECHA_NAC_VACIA;
			return fecha;
		}

		fecha = df.format(fechaNacimiento);

		return fecha;

	}

	/**
	 * Identificar error login.
	 *
	 * @param errorCodeIatx the error code iatx
	 * @param errorMessageIatx the error message iatx
	 * @return the credenciales exception
	 */
	private CredencialesException identificarErrorLogin(Integer errorCodeIatx, String errorMessageIatx) {

		if (ERROR_USUARIO_BLOQUEADO.equals(errorCodeIatx)) {

			return new CredencialesUsuarioBloqueadoException(errorMessageIatx);
		}

		if (ERROR_USUARIO_CON_SINONIMO.equals(errorCodeIatx)) {

			return new CredencialesUsuarioConSinonimoException(errorMessageIatx);
		}

		if (ERROR_DOCUMENTO_NO_EXISTE.equals(errorCodeIatx)) {

			return new CredencialesDocumentoNoExisteException(errorMessageIatx);
		}

		if (CLAVE_VENCIDA_Y_USUARIO_CORRECTO.equals(errorCodeIatx)) {

			return new CredencialesClavesExpiradasException(errorMessageIatx);
		}

		if (CLAVE_INDIVIDUO_VENCIDA.equals(errorCodeIatx)) {
			return new CredencialesClaveIndividuoVencidaException(errorMessageIatx);
		}

		if (PIN_VENCIDO_Y_NO_TIENE_USUARIO_DEFINIDO.equals(errorCodeIatx)) {
			return new CredencialesClavePinVencidaNotieneUsuarioException(errorMessageIatx);
		}

		if (LOGIN_ERROR_USUARIO_NO_DEFINIDO.equals(errorCodeIatx)) {
			return new CredencialesUsuarioNoDefinidoException(errorMessageIatx);
		}

		if (LOGIN_ERROR_USUARIO_CAMBIO_PENDIENTE.equals(errorCodeIatx)) {
			return new CredencialesUsuarioCambioPendienteException(errorMessageIatx);
		}
		if (CODE_SESION_CONCURRENTE_API_AUTH.equals(errorCodeIatx)) {
			return new SesionConcurrenteException(errorMessageIatx);
		}
		if (CODE_FALLO_READ_FEPARM.equals(errorCodeIatx)) {
			return new CredencialesArchivoFeparmException(errorMessageIatx);
		}

		if (errorCodeIatx == -1) {
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}

		return new CredencialesException(errorMessageIatx);
	}

	/**
	 * Parsear resumen cliente.
	 *
	 * @param iatxResponse the iatx response
	 * @return the resumen cliente
	 */
	private ResumenCliente parsearResumenCliente(IatxResponse iatxResponse) {

		String usrrf = iatxResponse.getNextData();
		String pwdrf = iatxResponse.getNextData();
		String nup = iatxResponse.getNextData();
		String fn = iatxResponse.getNextData();
		String tp = iatxResponse.getNextData();
		String anph = iatxResponse.getNextData();
		String tipoClave = iatxResponse.getNextData();

		ResumenCliente resumenCliente = new ResumenCliente();

		resumenCliente.setUsuarioRacf(usrrf);
		resumenCliente.setPasswordRacf(pwdrf);
		resumenCliente.setNup(nup);
		resumenCliente.setFechaNacimiento(fn);
		resumenCliente.setTipoPersona(tp);
		resumenCliente.setMarcaANPH(anph);
		resumenCliente.setTipoClave(tipoClave);
		resumenCliente.setNroComprobante(iatxResponse.getNroComprobante());

		return resumenCliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO#
	 * cambioUsuario(ar.com.santanderrio.obp.base.clientes.entities.
	 * CredencialCliente,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity)
	 */
	@Override
	public ResumenCliente cambioUsuario(CredencialCliente cc, CambioUsuarioEntity cambioUsuarioEntity)
	        throws CredencialesException {
		IatxRequest request = new IatxRequest(prefijoSeginform, version130);
		IatxRequestData iatxRequestData = generateRequestCambioUsuario(cc, cambioUsuarioEntity);
		request.setData(iatxRequestData);

		IatxResponse iatxResponse = null;
		try {

			iatxResponse = iatxComm.exec(request);

			if (EstadoRespuesta.ERROR.equals(iatxResponse.getEstadoRespuesta())) {
				logger.debug(iatxResponse.getErrorMessage());
				throw identificarErrorActualizacionClaveUsuario(iatxResponse.getErrorCode(), iatxResponse.getErrorMessage());
			}

			ResumenCliente resumenCliente = parsearResumenCliente(iatxResponse);

			resumenCliente.setDni(cc.getDniOri());

			return resumenCliente;

		} catch (IatxException e) {
			logger.error(e.getMessage());
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Casi copiado del CambioUsuarioPin.java del OBP productivo
	 *
	 * @param cc  the cc
	 * @param cue the cue
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestCambioUsuario(CredencialCliente cc, CambioUsuarioEntity cue) {
		IatxRequestData iatxRequestData = new IatxRequestData(cue.getCliente());

		String fechaNac = parsearFechaDeNacimiento(cc.getFechaNacimiento());

		String ip = IpUtils.dottedIp2Hex(cc.getIp());
		String prefijo = cc.getPrefijo();
		String sufijo = cc.getSufijo();

		iatxRequestData.setIndAutenticacion();
		iatxRequestData.setRacfInicial();

		String areaDatos = OPERACION_CMBUSER + fechaNac + TRAMA_AREA_DATOS_0499 + ip + prefijo + cc.getDni()
		        + cue.getStrOldUsr() + cue.getStrNewUsr() + cue.getStrOldPin() + cue.getStrNewPin() + sufijo;

		iatxRequestData.addBodyValue("N");
		iatxRequestData.addBodyValue(areaDatos);

		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.clientes.dao.ClienteDAO#cambioUsuarioProxyLogin(ar.
	 * com.santanderrio.base.clientes.entities.CredencialCliente, 
	 * ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity, boolean)
	 */
	@Override
	public ResumenCliente cambioUsuarioProxyLogin(CredencialCliente cc, CambioUsuarioEntity entity, boolean esSoloUsuario) 
			throws CredencialesException {
		ProxyLoginResponse response = null;
		try {
			if (esSoloUsuario == false) {
				response = proxyLoginDAO.setPinAndPassword(cc, entity);
			} else {
				response = proxyLoginDAO.setPassword(cc, entity);				
			}
			verificarRespuestaProxyLogin(response);
			ResumenCliente resumenCliente = obtenerResumenCliente(entity);
			resumenCliente.setDni(cc.getDniOri());
			return resumenCliente;
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		} catch (RuntimeException e) {
			logger.error(e.getMessage());
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Obtener resumen cliente.
	 *
	 * @param entity the entity
	 * @return the resumen cliente
	 */
	private ResumenCliente obtenerResumenCliente(CambioUsuarioEntity entity) {
		ResumenCliente resumenCliente = new ResumenCliente();
		Cliente cliente = entity.getCliente();
		resumenCliente.setUsuarioRacf(cliente.getUsuarioRacf());
		resumenCliente.setPasswordRacf(cliente.getPasswordRacf());
		resumenCliente.setNup(cliente.getNup());
		resumenCliente.setFechaNacimiento(cliente.getFechaNacimiento());
		resumenCliente.setTipoPersona(cliente.getTipoPersona());
		resumenCliente.setMarcaANPH(cliente.getMarcaANPH());
		resumenCliente.setTipoClave(cliente.getTipoClave());
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		resumenCliente.setNroComprobante(sdf.format(new Date()));
		return resumenCliente;
	}

	/**
	 * Verificar respuesta proxy login.
	 *
	 * @param response the response
	 * @throws CredencialesException the credenciales exception
	 */
	private void verificarRespuestaProxyLogin(ProxyLoginResponse response) throws CredencialesException {
		if (response.getCode() != null && !"".equals(response.getCode().trim())) {
			Integer errorCode = Integer.parseInt(response.getMessage());
			String errorMessage = response.getDescription();
			logger.debug("Error al invocar ProxyLogin: " + errorMessage);
			throw identificarErrorActualizacionClaveUsuario(errorCode, errorMessage);
		}
	}
	
	
    @Override
    public ResumenCliente validarCredencialesApiAuth(CredencialCliente cc, String csid)
            throws CredencialesException, RobotException {
        ApiAuthLoginResponse response = null;

        try {
            response = apiAuthDAO.obtenerTokenLogin(cc, csid);
        } catch (DAOException e) {
            throw new RobotException("");
        }
        if (response.getCode() != null && !"".equals(response.getCode().trim())) {
            Integer errorCode = Integer.parseInt(response.getCode());
            String errorMessage = response.getDetail();
            logger.debug(ERROR_AL_VALIDAR_CREDENCIALES, errorMessage);
            if (CLAVE_POR_VENCER_EN_X_DIAS.equals(errorCode)) {
                logger.info(ERROR_AL_VALIDAR_CREDENCIALES, errorMessage);
            } else {
                throw identificarErrorLogin(errorCode, errorMessage);
            }
        }
		AuthCliente authCliente = sesionCliente.getAuthCliente();
        ResumenCliente resumenCliente = parsearResumenCliente(response, authCliente);
        return resumenCliente;
    }
    
    private ResumenCliente parsearResumenCliente(ApiAuthLoginResponse apiAuthLoginResponse, AuthCliente authCliente) throws RobotException {
        String token = apiAuthLoginResponse.getAccess_token();
        String refreshToken = apiAuthLoginResponse.getRefresh_token();
        String payload = decodeTokenPayload(token);
        ApiAuthJWT response = new Gson().fromJson(payload, ApiAuthJWT.class);
        try {
            response.setMetadata(getDecryptedMetadata(response.getMetadata()));
        } catch (DAOException e) {
            logger.info("Error al desencriptar el metatada del jwt de api-auth: ", e);
            throw new RobotException("Error al desencriptar el metatada");
        }

        ResumenCliente resumenCliente = new ResumenCliente();
        resumenCliente.setUsuarioRacf(iatxInicial.getRacfInicialId());
        resumenCliente.setPasswordRacf(iatxInicial.getRacfInicialPwd());
        resumenCliente.setNup(StringUtils.leftPad(response.getUniquePersonNumber(),8,'0'));

        resumenCliente.setFechaNacimiento(response.getMetadata().getBirthDate());
        resumenCliente.setTipoPersona(response.getMetadata().getUserType());
        resumenCliente.setMarcaANPH(response.getMetadata().getAnphFlag());
        resumenCliente.setTipoClave(response.getMetadata().getPasswordType());
        resumenCliente.setDni(response.getMetadata().getDocumentNumber());
        // agrego un número random de comprobante porque la respueta del login de
        // apiauth no lo envia
        resumenCliente.setNroComprobante("123123123123123");
		authCliente.setAuthJWT(token);
		authCliente.setRefreshToken(refreshToken);
		authCliente.setApiAuthClient(Boolean.TRUE);
        return resumenCliente;
    }

    private Metadata getDecryptedMetadata(Metadata encryptedMetada) throws DAOException {
        encryptedMetada.setBirthDate(ApiAuthDAOImpl.decrypt(encryptedMetada.getBirthDate()));
        encryptedMetada.setPasswordType(ApiAuthDAOImpl.decrypt(encryptedMetada.getPasswordType()));
        encryptedMetada.setUserType(ApiAuthDAOImpl.decrypt(encryptedMetada.getUserType()));
        encryptedMetada.setSynonym(ApiAuthDAOImpl.decrypt(encryptedMetada.getSynonym()));
        encryptedMetada.setDocumentNumber(ApiAuthDAOImpl.decrypt(encryptedMetada.getDocumentNumber()));
        String anphFlag = ApiAuthDAOImpl.decrypt(encryptedMetada.getAnphFlag());
        encryptedMetada.setAnphFlag(StringUtils.EMPTY.equals(anphFlag)? " ":anphFlag);

        return encryptedMetada;
    }
    
}
