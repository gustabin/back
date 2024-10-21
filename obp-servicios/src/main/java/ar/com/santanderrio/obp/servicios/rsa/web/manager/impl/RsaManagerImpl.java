/**
 *
 */
package ar.com.santanderrio.obp.servicios.rsa.web.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.generated.webservices.rsa.UserStatus;
import ar.com.santanderrio.obp.generated.webservices.rsa.WSUserType;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class RsaManagerImpl.
 *
 * @author B039636
 */
@Component()
public class RsaManagerImpl implements RsaManager {

	/** The Constant LLAMANDO_A_RSA. */
	private static final String LLAMANDO_A_RSA = "Llamando a RSA";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RsaManagerImpl.class);

	/** The Constant ERROR_NO_IDENTIFICADO_DE_RSA. */
	private static final String ERROR_NO_IDENTIFICADO_DE_RSA = "Error No identificado de RSA";

	/** The Constant RSA_ACTIVO. */
	private static final String RSA_ACTIVO = "RSA.ACTIVO.";

	/** The Constant DOT. */
	private static final Character DOT = '.';

	/** The Constant POSICION_DESC. */
	private static final int POSICION_DESC = 2;

	/**
	 * RuleID que se utiliza para determinar si una transferencia tiene riesgo
	 * alto.
	 */
	@Value("${TRANSFERENCIAS.RULEID.24HS:TRANSFERENCIA24}")
	private String prefijoRuleTransferenciaRiesgoAlto;

	/** The rsa estado. */
	private final Map<OperacionesRSAEnum, Boolean> rsaEstado = new HashMap<OperacionesRSAEnum, Boolean>();

	/** The rsa BO. */
	@Autowired
	private RsaBO rsaBO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#init()
	 */
	@PostConstruct
	public void init() {
		rsaEstado.clear();
		Properties props = new Properties();
		File file = null;
		FileInputStream fileInputStream = null;
		try {

			file = new File(filePath.getFilePath() + ExternalPropertyType.FILE_RSA.getName());
			fileInputStream = new FileInputStream(file);
			props.load(fileInputStream);
			for (Map.Entry<Object, Object> entry : props.entrySet()) {
				String key = (String) entry.getKey();
				if (key.startsWith(RSA_ACTIVO)) {
					String[] parametros = StringUtils.split(key, DOT);
					OperacionesRSAEnum operacion = OperacionesRSAEnum.getByDescripcion(parametros[POSICION_DESC]);
					rsaEstado.put(operacion, Boolean.valueOf((String) entry.getValue()));
				}
			}
		} catch (IOException e) {
			LOGGER.error("Error con el archivo {} {} no se pudo levantar.", filePath.getFilePath(),
					ExternalPropertyType.FILE_RSA.getName(), e);
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error al intentar cerrar el archivo de RSA", e);

			}
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * analizarRsaLogin(ar.com.santanderrio.obp.servicios.rsa.entities.
	 * RsaAnalyzeRequestData)
	 */
	public Respuesta<RsaAnalyzeResponseData> analizarRsa(RsaAnalyzeRequestData analyzeRequestData) {
		return rsaBO.analizar(analyzeRequestData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * analizarRsaLogin(ar.com.santanderrio.obp.servicios.rsa.entities.
	 * RsaAnalyzeRequestData)
	 */
	public Respuesta<RsaAnalyzeResponseData> analizarRsa(RsaAnalyzeRequestData analyzeRequestData, Boolean isLogin) {
		return rsaBO.analizar(analyzeRequestData, isLogin);
	}

	/**
	 * Generate rsa notify request data.
	 *
	 * @param tipoDesafioEnum
	 *            the tipo desafio enum
	 * @param resultado
	 *            the resultado
	 * @param intentosRealizados
	 *            the intentos realizados
	 * @return the rsa notify request data
	 */
	private RsaNotifyRequestData generateRsaNotifyRequestData(TipoDesafioEnum tipoDesafioEnum, boolean resultado,
			int intentosRealizados) {
		RsaNotifyRequestData rsaNotifyRequestData = new RsaNotifyRequestData();
		LOGGER.info("Tomando RsaGenericRequestData y RsaGenericResponseData de sesionParametros...." );

		RsaGenericRequestData ultimoRsaGenericRequestData = sesionParametros.getRsaGenericRequestData();
		LOGGER.info("ultimoRsaGenericRequestData .... transaction_ID: {}", ultimoRsaGenericRequestData.getTransactionId()  );
		RsaGenericResponseData ultimoRsaGenericResponseData = sesionParametros.getRsaGenericResponseData();
		LOGGER.info("ultimoRsaGenericResponseData .... transaction_ID: {}", ultimoRsaGenericResponseData.getTransactionId()  );
		// se cargan los datos devuetos por el ultimo response
		ultimoRsaGenericRequestData.setTransactionId(ultimoRsaGenericResponseData.getTransactionId());
		ultimoRsaGenericRequestData.setDeviceTokenCookie(ultimoRsaGenericResponseData.getDeviceTokenCookie());
		LOGGER.info("Seteando los valores para rsaNotifyRequestData...." );
		rsaNotifyRequestData.setRsaGenericRequestData(ultimoRsaGenericRequestData);
		rsaNotifyRequestData.setWsUserType(WSUserType.PERSISTENT.value());
		rsaNotifyRequestData.setUserStatus(UserStatus.VERIFIED.value());
		rsaNotifyRequestData.setLevel(tipoDesafioEnum.getLevel());
		rsaNotifyRequestData.setTryCount(intentosRealizados);
		rsaNotifyRequestData.setSuccess(resultado);

		return rsaNotifyRequestData;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * isServicioActivo(ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum)
	 */
	public boolean isServicioActivo(OperacionesRSAEnum rsaIngreso) {
		if (sesionParametros.getRsaEstado() == null) {
			return false;
		}
		boolean activoGeneral = sesionParametros.getRsaEstado().get(OperacionesRSAEnum.ACTIVO);
		boolean activoServicio = sesionParametros.getRsaEstado().get(rsaIngreso);
		return activoGeneral && activoServicio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * analizarOperacionDeRiesgo(ar.com.santanderrio.obp.servicios.comun.
	 * autentificacion.entities.RsaDTO, ar.com.santanderrio.obp.servicios.
	 * comun.challenge.entities.TipoDesafioEnum)
	 */
	@Override
	public Respuesta<ActionCode> analizar(RsaDTO rsaDTO, TipoDesafioEnum desafioAplicable) {
		// verifica en el properties si el servicio debe operar con RSA, de lo
		// contrario siempre solicitara desafio.
		if (!isServicioActivo(rsaDTO.getTipoOperacion())) {
			if(null != rsaDTO.getTipoOperacion()) {
				LOGGER.info("El servicio " + rsaDTO.getTipoOperacion().getDescripcion() + " no esta activo." );
			}
			return respuestaWARNING();
		}
		LOGGER.info("Analize.... Creando RsaAnalyzeRequestData..." );
		RsaAnalyzeRequestData rsaAnalyzeRequestData = crearRsaAnalyzeRequestData(rsaDTO, desafioAplicable);

		Respuesta<RsaAnalyzeResponseData> respuestaRsaAnalyze = analizarRsa(rsaAnalyzeRequestData);

		rsaDTO.setReglaRsaTis(respuestaRsaAnalyze.getRespuesta().getRuleId());

		// si existio un error, se devuelve un warning
		if (EstadoRespuesta.ERROR.equals(respuestaRsaAnalyze.getEstadoRespuesta())) {
			if (rsaDTO.getStopOperacionErrorRSA()) {
				Respuesta<ActionCode> respuesta = new Respuesta<ActionCode>();
				respuesta.setRespuesta(ActionCode.DENY);
				LOGGER.info("si existio un error, se devuelve un warning con Action code: Deny");
				return respuesta;
			} else {
				return respuestaWARNING();
			}
		} else {
			actualizarSesionRsa(rsaAnalyzeRequestData.getRsaGenericRequestData(),
					respuestaRsaAnalyze.getRespuesta().getRsaGenericResponseData());
			Respuesta<ActionCode> respuesta = new Respuesta<ActionCode>();
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			if(rsaDTO.getValidarBloqueo() && ActionCode.DENY.equals(respuestaRsaAnalyze.getRespuesta().getActionCode()) &&respuestaRsaAnalyze.getRespuesta().getRuleName() != null && respuestaRsaAnalyze.getRespuesta().getRuleName().startsWith("BLOQUEARUSUARIO")){
				LOGGER.info("RSA devolvio: " + respuestaRsaAnalyze.getRespuesta().getRuleName() + " se bloquea usuario.");
				respuesta.setRespuesta(ActionCode.LOCKED);
			} else{
				respuesta.setRespuesta(respuestaRsaAnalyze.getRespuesta().getActionCode());
			}
			return respuesta;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * analizarRiesgoTransferencia(ar.com.santanderrio.obp.servicios.comun.
	 * autentificacion.entities.RsaDTO, ar.com.santanderrio.obp.servicios.
	 * comun.challenge.entities.TipoDesafioEnum)
	 */
	@Override
	public Respuesta<RsaRiesgoTransferenciaDTO> analizarRiesgoTransferencia(RsaDTO rsaDTO, TipoDesafioEnum desafioAplicable) {

		LOGGER.info(LLAMANDO_A_RSA);
		Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferenciaDTO = respuestaFactory
				.crearRespuestaOk(RsaRiesgoTransferenciaDTO.class);
		RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO = new RsaRiesgoTransferenciaDTO(ActionCode.ALLOW,
				Boolean.FALSE, prefijoRuleTransferenciaRiesgoAlto);

		// verifica en el properties obp-rsa.properties si el servicio debe
		// operar con RSA, de lo
		// contrario siempre solicitara desafio
		// CAMBIO RSA 20190129 - En caso que RSA no se encuentre disponible durante el ingreso, 
		// no se eleva el riesgo de transferencia
		if (!isServicioActivo(rsaDTO.getTipoOperacion())) {
			return crearWarningAnalisisRiesgoTransferencia(respuestaRsaRiesgoTransferenciaDTO,
					rsaRiesgoTransferenciaDTO);
		}

		RsaAnalyzeRequestData rsaAnalyzeRequestData = crearRsaAnalyzeRequestData(rsaDTO, desafioAplicable);
		Respuesta<RsaAnalyzeResponseData> respuestaRsaAnalyze = analizarRsa(rsaAnalyzeRequestData);
		// si existe un error, se devuelve un estado warning
		if (EstadoRespuesta.ERROR.equals(respuestaRsaAnalyze.getEstadoRespuesta())) {
			if (rsaDTO.getStopOperacionErrorRSA()) {
				rsaRiesgoTransferenciaDTO.setActionCode(ActionCode.DENY);
				respuestaRsaRiesgoTransferenciaDTO.setRespuesta(rsaRiesgoTransferenciaDTO);
				return respuestaRsaRiesgoTransferenciaDTO;
			} else {
				return crearWarningAnalisisRiesgoTransferencia(respuestaRsaRiesgoTransferenciaDTO,
						rsaRiesgoTransferenciaDTO);				
			}
		} else {
			actualizarSesionRsa(rsaAnalyzeRequestData.getRsaGenericRequestData(),
					respuestaRsaAnalyze.getRespuesta().getRsaGenericResponseData());
			rsaRiesgoTransferenciaDTO.setRuleId(respuestaRsaAnalyze.getRespuesta().getRuleId());
			rsaRiesgoTransferenciaDTO.setActionCode(respuestaRsaAnalyze.getRespuesta().getActionCode());
			respuestaRsaRiesgoTransferenciaDTO.setRespuesta(rsaRiesgoTransferenciaDTO);
			return respuestaRsaRiesgoTransferenciaDTO;
		}

	}

	/**
	 * Crear warning analisis riesgo transferencia.
	 *
	 * @param respuestaRsaRiesgoTransferenciaDTO
	 *            the respuesta rsa riesgo transferencia DTO
	 * @param rsaRiesgoTransferenciaDTO
	 *            the rsa riesgo transferencia DTO
	 * @return the respuesta
	 */
	private Respuesta<RsaRiesgoTransferenciaDTO> crearWarningAnalisisRiesgoTransferencia(
			Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferenciaDTO,
			RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO) {
		respuestaRsaRiesgoTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		rsaRiesgoTransferenciaDTO.setActionCode(ActionCode.CHALLENGE);
		rsaRiesgoTransferenciaDTO.setRiesgoAlto(true);
		respuestaRsaRiesgoTransferenciaDTO.setRespuesta(rsaRiesgoTransferenciaDTO);
		return respuestaRsaRiesgoTransferenciaDTO;
	}

	/**
	 * Respuesta WARNING.
	 *
	 * @return the respuesta
	 */
	private Respuesta<ActionCode> respuestaWARNING() {
		Respuesta<ActionCode> respuesta;
		respuesta = new Respuesta<ActionCode>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuesta.setRespuesta(ActionCode.CHALLENGE);
		LOGGER.info("EstadoRespuesta: WARNING, ActionCode: CHALLENGE");
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#
	 * crearRsaAnalyzeRequestData(ar.com.santanderrio.obp.servicios.comun.
	 * autentificacion.entities.RsaDTO, ar.com.santanderrio.obp.servicios.
	 * comun.challenge.entities.TipoDesafioEnum)
	 */
	public RsaAnalyzeRequestData crearRsaAnalyzeRequestData(RsaDTO operacion, TipoDesafioEnum desafioAplicable) {
		LOGGER.info("Creando RsaAnalyzeRequestData");
		RsaAnalyzeRequestData analyzeRequestData = new RsaAnalyzeRequestData(operacion, desafioAplicable);
		RsaGenericRequestData ultimoRsaGenericRequestData = sesionParametros.getRsaGenericRequestData();
		RsaGenericResponseData ultimoRsaGenericResponseData = sesionParametros.getRsaGenericResponseData();
		loggerRsaAnalyzeRequestData(ultimoRsaGenericRequestData, ultimoRsaGenericResponseData);
		if (ultimoRsaGenericResponseData != null) {
			// se cargan los datos devuetos por el ultimo response
			// FIXME este lo comento porque creo que solo es para el notify
			ultimoRsaGenericRequestData.setDeviceTokenCookie(ultimoRsaGenericResponseData.getDeviceTokenCookie());
			/*if (OperacionesRSAEnum.LOG_IN.equals(operacion.getTipoOperacion())) {
				LogIn logIn = (LogIn) operacion;
				ultimoRsaGenericRequestData.setRemoteIp(logIn.getIpClient());
			}*/
		}
		ResumenCliente resumenCliente = sesionCliente.getResumenCliente();
		ultimoRsaGenericRequestData.setResumenCliente(resumenCliente);
		analyzeRequestData.setRsaGenericRequestData(ultimoRsaGenericRequestData);
		analyzeRequestData.setWsUserType(WSUserType.PERSISTENT.value());

		return analyzeRequestData;
	}

	private void loggerRsaAnalyzeRequestData(RsaGenericRequestData ultimoRsaGenericRequestData, RsaGenericResponseData ultimoRsaGenericResponseData) {
		if(null != ultimoRsaGenericRequestData){
			LOGGER.info("ultimoRsaGenericRequestData transaction_ID: {}" + ultimoRsaGenericRequestData.getTransactionId());
		}
		if(null != ultimoRsaGenericResponseData){
			LOGGER.info("ultimoRsaGenericResponseData transaction_ID: {}" + ultimoRsaGenericResponseData.getTransactionId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#notificar(ar
	 * .com.santanderrio.obp.servicios.comun.autentificacion.entities.
	 * AutentificacionDTO, boolean, int)
	 */
	@Override
	public Respuesta<RsaNotifyResponseData> notificar(AutentificacionDTO dto, int intentosRealizados) {
		Respuesta<RsaNotifyResponseData> respuesta = new Respuesta<RsaNotifyResponseData>();
		if (!isServicioActivo(OperacionesRSAEnum.ACTIVO)) {
			LOGGER.info("RSA No esta Activo");
			respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			return respuesta;
		}
		LOGGER.info("Generando RsaNotifyRequestData.....");
		RsaNotifyRequestData rsaNotifyRequestData = generateRsaNotifyRequestData(dto.getTipoDesafio(),
				dto.getValorNotificarRSA(), intentosRealizados);

		RsaNotifyResponseData respuestaNotify = null;
		try {
			LOGGER.info("RSA ...... llamando a notify");
			respuestaNotify = rsaBO.notificar(rsaNotifyRequestData);
			actualizarSesionRsa(rsaNotifyRequestData.getRsaGenericRequestData(),
					respuestaNotify.getRsaGenericResponseData());
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(respuestaNotify);
			respuesta.setRespuestaVacia(false);
		} catch (BusinessException e) {
			// FIXME
			LOGGER.error(ERROR_NO_IDENTIFICADO_DE_RSA, e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			respuesta.add(new ItemMensajeRespuesta(ERROR_NO_IDENTIFICADO_DE_RSA));
			respuesta.setRespuestaVacia(true);
		}

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#getRsaEstado
	 * ()
	 */
	@Override
	public Map<? extends OperacionesRSAEnum, ? extends Boolean> getRsaEstado() {
		return rsaEstado;
	}

	/**
	 * Actualiza el ultimo request y response de RSA en la sesion.
	 *
	 * @param rsaGenericRequestData
	 *            the rsa generic request data
	 * @param rsaGenericResponseData
	 *            the rsa generic response data
	 * @TODO : ver el tema de que estamos manteniendo los cookies en sesion
	 */
	private void actualizarSesionRsa(RsaGenericRequestData rsaGenericRequestData,
			RsaGenericResponseData rsaGenericResponseData) {
		LOGGER.info("actualizando sesion parametros con los datos de RsaGenericRequestData y RsaGenericResponseData");
		sesionParametros.setRsaGenericRequestData(rsaGenericRequestData);
		sesionParametros.setRsaGenericResponseData(rsaGenericResponseData);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#enrolarUser(
	 * ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData)
	 */
	public Respuesta<RsaGenericResponseData> enrolarUser(RsaCreateUserRequestData createUserRequestData) {
		Respuesta<RsaGenericResponseData> respuesta = new Respuesta<RsaGenericResponseData>();

		sesionParametros.setRsaEstado(new HashMap<OperacionesRSAEnum, Boolean>(rsaEstado));
		Cliente cliente = sesionCliente.getCliente();
		try {
			RsaCreateUserResponseData createUserResponseData = rsaBO.crearUsuario(createUserRequestData);
			actualizarSesionRsa(createUserRequestData.getRsaGenericRequestData(),
					createUserResponseData.getRsaGenericResponseData());
			String deviceTokenCookieCU = createUserResponseData.getRsaGenericResponseData().getDeviceTokenCookie();
			LOGGER.info("Crear usuario RSA deviceTokenCookieCU: {}", deviceTokenCookieCU);
			respuesta.setRespuesta(createUserResponseData.getRsaGenericResponseData());
			LOGGER.info("Se genero un nuevo usuario de RSA para el NUP :" + cliente.getNup());
		} catch (Exception e) {
			LOGGER.error("Error al invocar servicio RSA CreateUser", e);
			completarErrorRespuesta(respuesta, EstadoRespuesta.ERROR, ERROR_NO_IDENTIFICADO_DE_RSA);
			return respuesta;
		}

		return respuesta;
	}

	/**
	 * Completar error respuesta.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param mensaje
	 *            the mensaje
	 */
	private void completarErrorRespuesta(Respuesta<RsaGenericResponseData> respuesta, EstadoRespuesta estadoRespuesta,
			String mensaje) {
		respuesta.setEstadoRespuesta(estadoRespuesta);
		respuesta.add(new ItemMensajeRespuesta(mensaje));
	}


	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#updateUser(ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData)
	 */
	@Override
	public Respuesta<RsaUpdateUserResponseData> updateUser(RsaUpdateUserRequestData request) {
		return rsaBO.updateUser(request);
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager#getRsaDeviceTokenCookie()
	 */
	@Override
	public Respuesta<String> getRsaDeviceTokenCookie() {
		Respuesta<String> respuesta = new Respuesta<String>();
		
		if (sesionParametros.getRsaEstado() == null || !sesionCliente.getTieneTokenRSA()) {
			respuesta.setRespuesta("");
		} else {
			respuesta.setRespuesta(sesionParametros.getRsaGenericResponseData().getDeviceTokenCookie());
		}
		
		return respuesta;
	}

}
