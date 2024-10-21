/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao.impl;

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
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;
import ar.com.santanderrio.obp.servicios.clave.online.dao.ClaveOnlineDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionCvv2InactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionPinInactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticarTarjetaDebitoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.CLienteSinContratoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineNoEsPersonaFisicaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinAutValidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinonimoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.DniInvalidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.IpBloqueadaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SinCelularRegistradoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SistemaClaveNoDisponibleException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.comun.utils.IpUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class ClaveOnlineDAOImpl.
 */
@Component
public class ClaveOnlineDAOImpl extends IatxBaseDAO implements ClaveOnlineDAO {

	/** The servicio cnsvaldebi. */
	@Value("${SERVICIO.PREFIJO.IDESGICLIE}")
	private String servicioIdesgiclie;

	/** The version cnsvaldebi. */
	@Value("${SERVICIO.VERSION.IDESGICLIE}")
	private String versionIdesgiclie;
		
	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaveOnlineDAOImpl.class);

	/** The Constant IDENTIFICACION_CORRECTA. */
	private static final Integer IDENTIFICACION_CORRECTA = 00000000;

	/** The Constant FUNCION. */
	private static final String FUNCION = "01";

	/** The Constant DNI_NO_VALIDO. */
	private static final Object DNI_NO_VALIDO = 10050002;

	/** The Constant IP_BLOQUEADA. */
	private static final Integer IP_BLOQUEADA = 10050012;
	
	/** The Constant NO_ES_PERSONA_FISICA. */
	private static final Integer NO_ES_PERSONA_FISICA = 10050013;
	
	/** The Constant SISTEMA_CLAVE_NO_DISPONIBLE. */
	private static final Integer SISTEMA_CLAVE_NO_DISPONIBLE = 10050020;
	
	/** The Constant CLIENTE_BLOQUEADO. */
	private static final Integer CLIENTE_BLOQUEADO = 10050011;

	/** The Constant CLIENTE_BLOQUEADO2. */
	private static final Integer CLIENTE_BLOQUEADO2 = 10050015;

	/** The Constant CLIENTE_BLOQUEADO3. */
	private static final Integer CLIENTE_BLOQUEADO3 = 10050016;

	/** The Constant CLIENTE_SINONIMO. */
	private static final Integer CLIENTE_SINONIMO = 10050010;

	/** The Constant CLIENTE_SIN_AUT_VALIDO. */
	private static final Integer CLIENTE_SIN_AUT_VALIDO = 10050035;

	/** The Constant ERROR_IATX_NO_RECONOCIDO. */
	private static final String ERROR_IATX_NO_RECONOCIDO = "Código de retorno de IATX no reconocido";

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
	/** The Constant ERROR_MODULO. */
	private static final Integer ERROR_MODULO = 10050094;
	/** The Constant ERROR_DB2. */
	private static final Integer ERROR_DB2 = 10050097;
	/** The Constant ERROR_CICS. */
	private static final Integer ERROR_CICS = 10050098 ;
	/** The Constant FUNCION_INVALIDA. */
	private static final Integer FUNCION_INVALIDA = 10050099 ;
	
	/** The Constant TRUE. */
	private static final String TRUE = "true";
	
	/** The file path. */
	@Autowired
	private ArchivoDeRecursosDAO archivoResource;
	
	
	/*
	 * (non-Javadoc)S
	 * 
	 * @see ar.com.santanderrio.obp.servicios.clave.online.dao.ClaveOnlineDAO#
	 * identificarCliente(ar.com.santanderrio.obp.servicios.clave.online.
	 * entities.IdentificadorClienteInEntity)
	 */
	@Override
	public IdentificadorClienteOutEntity identificarCliente(IdentificadorClienteInEntity identificadorCliente)
			throws DAOException, DniInvalidoException, ClienteSinonimoException, ClienteSinAutValidoException,
			ClienteBloqueadoException, ClienteBloqueado2Exception, ClaveOnlineNoEsPersonaFisicaException, IpBloqueadaException, SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException, SinCelularRegistradoException, AutenticacionPinInactivaException, AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ErrorRutinaFechasException, ErrorModuloException, ErrorDb2Exception, FuncionInvalidaException, AutenticarTarjetaDebitoException, ErrorCicsException {
		IdentificadorClienteOutEntity identificadorClienteOutEntity = null;
		ResumenCliente resumenCliente = new ResumenCliente();
		resumenCliente.setDni(identificadorCliente.getDni());
		resumenCliente.setFechaNacimiento(FechaUtils.parsearFechaDeNacimientoParaIATX(identificadorCliente.getFecha()));
		try {
			IatxRequest request = new IatxRequest(servicioIdesgiclie, versionIdesgiclie);
			IatxRequestData requestData = new IatxRequestData(resumenCliente);

			requestData.setIndAutenticacion();
			requestData.setRacfInicial();

			String ip = IpUtils.dottedIp2Hex(identificadorCliente.getIp());

			requestData.addBodyValue(FUNCION);
			requestData.addBodyValue(ip);

			request.setData(requestData);
			IatxResponse iatxResponse = iatxComm.exec(request);
			
			identificadorClienteOutEntity = analizarRespuesta(iatxResponse);

		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}

		return identificadorClienteOutEntity;
	}

	/**
	 * Analizar respuesta.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the identificador cliente out entity
	 * @throws DniInvalidoException
	 *             the dni invalido exception
	 * @throws ClienteSinonimoException
	 *             the cliente sinonimo exception
	 * @throws ClienteSinAutValidoException
	 *             the cliente sin aut valido exception
	 * @throws ClienteBloqueadoException
	 *             the cliente bloqueado exception
	 * @throws ClienteBloqueado2Exception
	 *             the cliente bloqueado 2 exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ClaveOnlineNoEsPersonaFisicaException 
	 * @throws IpBloqueadaException 
	 * @throws SistemaClaveNoDisponibleException 
	 * @throws CLienteSinContratoException 
	 * @throws SinTarjetaValidaException 
	 * @throws SinCelularRegistradoException 
	 * @throws AutenticacionPinInactivaException 
	 * @throws AutenticacionSmsOtpInactivaException 
	 * @throws AutenticacionCvv2InactivaException 
	 * @throws ErrorRutinaFechasException 
	 * @throws ErrorModuloException 
	 * @throws ErrorDb2Exception 
	 * @throws FuncionInvalidaException 
	 * @throws AutenticarTarjetaDebitoException 
	 * @throws ErrorCicsException 
	 */	
	private IdentificadorClienteOutEntity analizarRespuesta(IatxResponse iatxResponse)
			throws DniInvalidoException, ClienteSinonimoException, ClienteSinAutValidoException,
			ClienteBloqueadoException, ClienteBloqueado2Exception, DAOException, ClaveOnlineNoEsPersonaFisicaException, IpBloqueadaException, SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException, SinCelularRegistradoException, AutenticacionPinInactivaException, AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ErrorRutinaFechasException, ErrorModuloException, ErrorDb2Exception, FuncionInvalidaException, AutenticarTarjetaDebitoException, ErrorCicsException {
		Integer codigoRetorno = iatxResponse.getErrorCode();
		String mensajeErrorIatx = iatxResponse.getErrorMessage();		
		IdentificadorClienteOutEntity identificadorClienteOutEntity = null;
		if (IDENTIFICACION_CORRECTA.equals(codigoRetorno)) {
			identificadorClienteOutEntity = processTrama(iatxResponse.getTrama(), IdentificadorClienteOutEntity.class);
		}
		if (DNI_NO_VALIDO.equals(codigoRetorno)) {
			throw new DniInvalidoException(mensajeErrorIatx);
		}
		if (CLIENTE_SINONIMO.equals(codigoRetorno)) {
			throw new ClienteSinonimoException(mensajeErrorIatx);
		}
		if (CLIENTE_SIN_AUT_VALIDO.equals(codigoRetorno) ) {
			throw new ClienteSinAutValidoException(mensajeErrorIatx);
		}
		if (IP_BLOQUEADA.equals(codigoRetorno)) {
			throw new IpBloqueadaException(mensajeErrorIatx);
		}
			
		if (CLIENTE_BLOQUEADO.equals(codigoRetorno) || CLIENTE_BLOQUEADO2.equals(codigoRetorno)) {
			throw new ClienteBloqueadoException(mensajeErrorIatx);
		}
		if (CLIENTE_BLOQUEADO3.equals(codigoRetorno)) {
			throw new ClienteBloqueado2Exception(mensajeErrorIatx);
		}
		if (NO_ES_PERSONA_FISICA.equals(codigoRetorno)) {
			throw new ClaveOnlineNoEsPersonaFisicaException(mensajeErrorIatx);
		}
		if (SISTEMA_CLAVE_NO_DISPONIBLE.equals(codigoRetorno)) {
			throw new SistemaClaveNoDisponibleException(mensajeErrorIatx);
		}
		if (CLIENTE_SIN_CONTRATO.equals(codigoRetorno)) {
			throw new CLienteSinContratoException(mensajeErrorIatx);
		}
		if (SIN_TARJETA_DEBITO_VALIDA.equals(codigoRetorno)) {
			throw new SinTarjetaValidaException(mensajeErrorIatx);
		}
		if (SIN_CELULAR_REGISTRADO.equals(codigoRetorno)) {
			throw new SinCelularRegistradoException(mensajeErrorIatx);
		}
		
		if (NO_ES_POSIBLE_AUTENTICAR_TARJETA_DE_DEBITO.equals(codigoRetorno)) {
			throw new AutenticarTarjetaDebitoException(mensajeErrorIatx);
		}
		if (AUTENTICACION_PIN_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionPinInactivaException(mensajeErrorIatx);
		}
		if (AUTENTICACION_SMS_OTP_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionSmsOtpInactivaException(mensajeErrorIatx);
		}
		if (AUTENTICACION_CVV2_INACTIVA.equals(codigoRetorno)) {
			throw new AutenticacionCvv2InactivaException(mensajeErrorIatx);
		}
		if (ERROR_RUTINA_FECHAS.equals(codigoRetorno)) {
			throw new ErrorRutinaFechasException(mensajeErrorIatx);
		}
		if (ERROR_MODULO.equals(codigoRetorno)) {
			throw new ErrorModuloException(mensajeErrorIatx);
		}
		if (ERROR_DB2.equals(codigoRetorno)) {
			throw new ErrorDb2Exception(mensajeErrorIatx);
		}
		if (ERROR_CICS .equals(codigoRetorno)) {
			throw new ErrorCicsException(mensajeErrorIatx);
		}
		if (FUNCION_INVALIDA .equals(codigoRetorno)) {
			throw new FuncionInvalidaException(mensajeErrorIatx);
		}
		// En caso de recibir un código de error no reconocido
		if (!IDENTIFICACION_CORRECTA.equals(codigoRetorno)) {
			String e = ERROR_IATX_NO_RECONOCIDO;
			throw new DAOException(e);
		}

		return identificadorClienteOutEntity;
	}

	@Override
	public Boolean obtenerClaveWhatsapp() throws DAOException {
		for (String lineaTexto : archivoResource.leerArchivo(ExternalPropertyType.FILE_WHATSAPP_HABILITADO)) {
			String linea = lineaTexto.split("=")[1].trim();
			if(TRUE.equalsIgnoreCase(linea)) {
				return true;
			}
		}
		return false;
	}

}
