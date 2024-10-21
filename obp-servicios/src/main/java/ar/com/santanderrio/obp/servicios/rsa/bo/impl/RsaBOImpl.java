/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.generated.webservices.rsa.APIType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.AuthenticationLevel;
import ar.com.santanderrio.obp.generated.webservices.rsa.AuthorizationMethod;
import ar.com.santanderrio.obp.generated.webservices.rsa.ChallengeRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.ChallengeResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.CreateUserRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.CreateUserResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.DeviceRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.DeviceResult;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventDataList;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ExecutionSpeed;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericActionType;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericActionTypeList;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericResponse;
import ar.com.santanderrio.obp.generated.webservices.rsa.IdentificationData;
import ar.com.santanderrio.obp.generated.webservices.rsa.MessageHeader;
import ar.com.santanderrio.obp.generated.webservices.rsa.NotifyRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.NotifyResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.RequestType;
import ar.com.santanderrio.obp.generated.webservices.rsa.RiskResult;
import ar.com.santanderrio.obp.generated.webservices.rsa.RunRiskType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.SecurityHeader;
import ar.com.santanderrio.obp.generated.webservices.rsa.StatusHeader;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.UpdateUserRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.UpdateUserResponse;
import ar.com.santanderrio.obp.generated.webservices.rsa.UserStatus;
import ar.com.santanderrio.obp.generated.webservices.rsa.WSUserType;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;
import ar.com.santanderrio.obp.servicios.rsa.dao.RsaDAO;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzePaymentRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaAnalyzeResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaCreateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;

/**
 * The Class RsaBOImpl.
 *
 * @author Ignacio_Valek
 */
@Component
public class RsaBOImpl implements RsaBO {

    /** The Constant NO_PUDIERON_OBTENERSE_LOS_TOKENS_DE_RSA. */
    private static final String NO_PUDIERON_OBTENERSE_LOS_TOKENS_DE_RSA = "No pudieron obtenerse los tokens de RSA al loguearse la app. Se fuerza CHALLENGE para todas las operaciones.";

    /** The Constant DATE_FORMATTER. */
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /** The Constant ERROR_RSA_CREDENTIALS. */
    private static final String ERROR_RSA_CREDENTIALS = "Error al obtener usuario y password para RSA";

    /** The Constant RSA_SECURITY_ID. */
    private static final String RSA_SECURITY_ID = "RSA";

    /** The Constant RSA_NO_NOTIFICA_APRENDIZAJE. */
    private static final String RSA_NO_NOTIFICA_MODO_APRENDIZAJE = "RSA se encuentra en modo aprendizaje. NO SE NOTIFICA";

    /** The Constant RSA_ANALIZAR_MODO_APRENDIZAJE. */
    private static final String RSA_ANALIZAR_MODO_APRENDIZAJE = "RSA se encuentra en modo aprendizaje. Se solicita CHALLENGE";

    /** The Constant ORGNAME. */
    private static final String ORGNAME = "OBP";

    /** The Constant RSA_VERSION. */
    public static final String RSA_VERSION = "7.0";

    /** The Constant MSG_ERROR_INVOCAR_ANALIZAR. */
    private static final String MSG_ERROR_INVOCAR_ANALIZAR = "Ha ocurrido un error al invocar RSA";

    /**
     * The Constant FALLBACK_RULE: indica si rsa esta en modo de aprendizaje o no.
     */
    private static final String FALLBACK_RULE = "FALLBACK RULE";

    /** The rsa dao. */
    @Autowired
    private RsaDAO rsaDAO;

    /** The credential security. */
    @Autowired
    private CredentialSecurityFactory credentialSecurity;

    /** The builder factory. */
    @Autowired
    private RsaRequestBuilderFactory rsaRequestBuilderFactory;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaBOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO#analizar(ar.com.
     * santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData)
     */
    @Override
    public Respuesta<RsaAnalyzeResponseData> analizar(RsaAnalyzeRequestData requestData) {
        return analizar(requestData, Boolean.FALSE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO#analizarDev1(ar.com.
     * santanderrio.obp.servicios.rsa.entities.RsaAnalyzeRequestData)
     */
    @Override
    public Respuesta<RsaAnalyzeResponseData> analizar(RsaAnalyzeRequestData requestData, Boolean isLogin) {
        LOGGER.info("Preparando el llamado a Analize........");
        LOGGER.info("Request: " + requestData);
        AnalyzeResponse2 response = null;
        RsaAnalyzeResponseData rsaAnalyzeResponseData = null;
        try {
            RsaRequestBuilder rsaRequestBuilder = rsaRequestBuilderFactory
                    .getBuilder(requestData.getRsaDTO().getClass());
            AnalyzeRequest analyzeRequest = rsaRequestBuilderFactory.getRequest(requestData, rsaRequestBuilder);
            if (isLogin) {
                LOGGER.info("isLogin = true........ llamando a analize");
                response = rsaDAO.analizar(analyzeRequest);
                rsaAnalyzeResponseData = readAnalyzeResponse(response);
            } else {
                rsaAnalyzeResponseData = analizarToken(analyzeRequest);
            }
        } catch (BusinessException e) {
            LOGGER.error(MSG_ERROR_INVOCAR_ANALIZAR, e);
            return respuestaFactory.crearRespuestaError(RsaAnalyzeResponseData.class, MSG_ERROR_INVOCAR_ANALIZAR, null);
        } catch (DAOException e) {
            LOGGER.error(MSG_ERROR_INVOCAR_ANALIZAR, e);
            return respuestaFactory.crearRespuestaError(RsaAnalyzeResponseData.class, MSG_ERROR_INVOCAR_ANALIZAR, e.getErrorCode());
        }

        String modoAprendizaje = rsaAnalyzeResponseData.getRrActionName();
        if (!OperacionesRSAEnum.LOG_IN.equals(requestData.getRsaDTO().getTipoOperacion())) {
            if (isModoAprendizajeDeny(modoAprendizaje) || isActionCodeDeny(rsaAnalyzeResponseData) || isRrActionCodeDeny(rsaAnalyzeResponseData)) {
                rsaAnalyzeResponseData.setActionCode(ActionCode.DENY);
            }else if (modoAprendizaje != null && modoAprendizaje.equals(FALLBACK_RULE)) {
                sesionParametros.setRsaEnAprendizaje(true);
                ActionCode actionCode = rsaAnalyzeResponseData.getActionCode();
                if (actionCode.equals(ActionCode.ALLOW)) {
                    LOGGER.info(RSA_ANALIZAR_MODO_APRENDIZAJE);
                    rsaAnalyzeResponseData.setActionCode(ActionCode.CHALLENGE);
                }
            } else if (fuerzaDesafio(requestData.getOperacion())) {
                LOGGER.info("Para la operacion {} se fuerza desafio", requestData.getOperacion());
                rsaAnalyzeResponseData.setActionCode(ActionCode.CHALLENGE);
            }
        }
        LOGGER.info("Response: " + rsaAnalyzeResponseData);
        Respuesta<RsaAnalyzeResponseData> respuesta = respuestaFactory.crearRespuestaOk(RsaAnalyzeResponseData.class);
        respuesta.setRespuesta(rsaAnalyzeResponseData);
        return respuesta;
    }

    private static boolean isModoAprendizajeDeny(String modoAprendizaje) {
        LOGGER.info("RSA modoAprendizaje (RrActioName): {}", modoAprendizaje);
        return null != modoAprendizaje && modoAprendizaje.equalsIgnoreCase(ActionCode.DENY.value());
    }

    private static boolean isActionCodeDeny(RsaAnalyzeResponseData rsaAnalyzeResponseData) {
        LOGGER.info("RSA ActionCode: {}", rsaAnalyzeResponseData.getActionCode());
        return null != rsaAnalyzeResponseData.getActionCode() && rsaAnalyzeResponseData.getActionCode().equals(ActionCode.DENY);
    }

    private static boolean isRrActionCodeDeny(RsaAnalyzeResponseData rsaAnalyzeResponseData) {
        LOGGER.info("RSA RrActionCode: {}", rsaAnalyzeResponseData.getRrActionCode());
        return null != rsaAnalyzeResponseData.getRrActionCode() && rsaAnalyzeResponseData.getRrActionCode().equalsIgnoreCase(ActionCode.DENY.value());
    }
    /**
	 * Poder forzar que una operacion requiera desafio mas alla que rsa haya
	 * devuelto ALLOW y no este el modo aprendizaje.<br/>
	 * Agregar la operacion a la listaForzada.
	 *
	 * @param operacion
	 *            the operacion
	 * @return true, if successful
	 */
    private boolean fuerzaDesafio(OperacionesRSAEnum operacion) {
        List<OperacionesRSAEnum> listaForzada = Arrays.asList(OperacionesRSAEnum.DEBIN, OperacionesRSAEnum.PAGODESUELDOS);
        return CollectionUtils.contains(listaForzada.iterator(), operacion);
    }

    /**
     * Analizar token.
     *
     * @param analyzeRequest
     *            the analyze request
     * @return the rsa analyze response data
     * @throws DAOException
     *             the DAO exception
     */
    private RsaAnalyzeResponseData analizarToken(AnalyzeRequest analyzeRequest) throws DAOException {
        AnalyzeResponse2 response;
        RsaAnalyzeResponseData rsaAnalyzeResponseData;
        if (sesionCliente.getTieneTokenRSA()) {
            response = rsaDAO.analizar(analyzeRequest);
            rsaAnalyzeResponseData = readAnalyzeResponse(response);
        } else {
            LOGGER.info(NO_PUDIERON_OBTENERSE_LOS_TOKENS_DE_RSA);
            rsaAnalyzeResponseData = new RsaAnalyzeResponseData();
            rsaAnalyzeResponseData.setActionCode(ActionCode.CHALLENGE);
        }
        return rsaAnalyzeResponseData;
    }

    /**
     * Armar request analizar.
     *
     * @param requestData
     *            the request data
     * @return the analyze request
     * @throws BusinessException
     *             the business exception
     */
    @Deprecated
    // usar RsaRequestBuilderFactory
    private AnalyzeRequest armarRequestAnalizar(RsaRequestData requestData) throws BusinessException {

        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        fillGenericFields(analyzeRequest, requestData.getRsaGenericRequestData());
        EventDataList edl = new EventDataList();
        ResumenCliente resumenCliente = requestData.getRsaGenericRequestData().getResumenCliente();
        EventData ed = fillAnalyzeEventData(requestData, resumenCliente);

        edl.getEventData().add(ed);
        analyzeRequest.setEventDataList(edl);

        IdentificationData id = new IdentificationData();

        id.setUserName(resumenCliente.getNup());
        String userStatus = requestData.getUserStatus();
        if (userStatus != null && !userStatus.trim().isEmpty()) {
            id.setUserStatus(UserStatus.fromValue(userStatus));
        }
        String userType = requestData.getWsUserType();
        if (userType != null && !userType.trim().isEmpty()) {
            id.setUserType(WSUserType.fromValue(userType));
        }
        id.setOrgName(ORGNAME);

        id.setUserType(WSUserType.PERSISTENT);

        analyzeRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.ANALYZE);
        mh.setVersion(RSA_VERSION);
        analyzeRequest.setMessageHeader(mh);

        analyzeRequest.setRunRiskType(RunRiskType.ALL);
        return analyzeRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.rsa.bo.RsaAnalizarBO#analizar(ar.com.santanderrio
     * .obp.rsa.entities.RsaAnalyzeRequestData)
     */
    @Override
    @Deprecated
    // Usar #analizar(rsaAnalyzeRequestData)
    public RsaAnalyzeResponseData analizar(RsaRequestData requestData) throws BusinessException {
        LOGGER.info("Request: " + requestData);
        AnalyzeRequest request = armarRequestAnalizar(requestData);
        AnalyzeResponse2 response = null;
        try {
            response = rsaDAO.analizar(request);
        } catch (DAOException e) {
            LOGGER.error("Error al invocar analizar", e);
            throw new BusinessException(e);
        }
        RsaAnalyzeResponseData rta = readAnalyzeResponse(response);
        LOGGER.info("Response: " + rta);
        return rta;
    }

    /**
     * Armar request analizar.
     *
     * @param requestData
     *            the request data
     * @return the analyze request
     * @throws BusinessException
     *             the business exception
     */
    @Deprecated
    // usar RsaRequestBuilderFactory
    private ChallengeRequest armarRequestChallenge(RsaAnalyzeRequestData requestData) throws BusinessException {
        ChallengeRequest analyzeRequest = new ChallengeRequest();
        fillGenericFields(analyzeRequest, requestData.getRsaGenericRequestData());

        EventDataList edl = new EventDataList();
        ResumenCliente rc = requestData.getRsaGenericRequestData().getResumenCliente();
        EventData ed = fillAnalyzeEventData(requestData, rc);

        edl.getEventData().add(ed);
        analyzeRequest.setEventDataList(edl);

        IdentificationData id = new IdentificationData();

        id.setUserName(rc.getNup());
        String userStatus = requestData.getUserStatus();
        if (userStatus != null && !userStatus.trim().isEmpty()) {
            id.setUserStatus(UserStatus.fromValue(userStatus));
        }
        id.setOrgName(ORGNAME);
        analyzeRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.CHALLENGE);
        mh.setVersion(RSA_VERSION);
        analyzeRequest.setMessageHeader(mh);

        return analyzeRequest;
    }

    /**
     * Completa los datos de evento.
     *
     * @param requestData
     *            OperacionesEnum a analizar
     * @param resumen
     *            RawClienteData que representa el usuario de banca
     * @return Objeto EventData con los datos completos
     * @throws BusinessException
     *             the business exception
     */
    @Deprecated
    // usar RsaRequestBuilderFactory
    private EventData fillAnalyzeEventData(RsaRequestData requestData, ResumenCliente resumen)
            throws BusinessException {

        /*
         * TODO Loggers ficticios para validar pmd
         */
        OperacionesRSAEnum operacion = requestData.getOperacion();

        LOGGER.info(operacion.name());
        LOGGER.info(resumen.toString());
        EventData ed = new EventData();
        // TODO logica segun operacion
        switch (operacion) {
        case LOG_IN:
            ed.setEventType(EventType.SESSION_SIGNIN);
            break;
        case NUEVO_PAGO:
            ed.setEventType(EventType.PAYMENT);
            TransactionData td = generarTransactionDataNuevoPago((RsaAnalyzePaymentRequestData) requestData);
            ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_NUEVOPAGO);
            ed.setTransactionData(td);
            break;
        case NUEVO_PAGO_AUTOMATICO:
            ed.setEventType(EventType.CLIENT_DEFINED);
            ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_ADHPAGOAUTO);
            break;
        default:
            throw new BusinessException("Operacion no implementada");
        }

        return ed;
    }

    /**
     * Read analyze response.
     *
     * @param response
     *            the response
     * @return the rsa analyze response data
     */
    private RsaAnalyzeResponseData readAnalyzeResponse(AnalyzeResponse2 response) {
        RsaAnalyzeResponseData responseData = new RsaAnalyzeResponseData();
        readGenericFields(response, responseData.getRsaGenericResponseData());
        RiskResult riskResult = response.getRiskResult();
        if (riskResult != null) {
            responseData.setRrActionCode(riskResult.getTriggeredRule().getActionCode().value());
            responseData.setActionCode(riskResult.getTriggeredRule().getActionCode());
            responseData.setRrActionName(riskResult.getTriggeredRule().getActionName());
            responseData.setRuleId(riskResult.getTriggeredRule().getRuleId());
            responseData.setRuleName(riskResult.getTriggeredRule().getRuleName());
        }
        IdentificationData identificationData = response.getIdentificationData();
        if (identificationData != null) {
            responseData.setIdUserStatus(identificationData.getUserStatus().value());
            responseData.setIdUserType(identificationData.getUserType().value());
            responseData.getRsaGenericResponseData().setTransactionId(identificationData.getTransactionId());
        }
        return responseData;
    }

    /**
     * Read challenge response.
     *
     * @param response
     *            the response
     * @return the rsa analyze response data
     */
    // TODO: revisar el comportamiento de este metodo
    private RsaAnalyzeResponseData readChallengeResponse(ChallengeResponse2 response) {
        RsaAnalyzeResponseData responseData = new RsaAnalyzeResponseData();
        readGenericFields(response, responseData.getRsaGenericResponseData());
        // Lee campos espec�ficos desde la respuesta
        responseData.setRrActionCode("CHALLENGE");
        IdentificationData identificationData = response.getIdentificationData();
        if (identificationData != null) {
            responseData.setIdUserStatus(identificationData.getUserStatus().value());
            responseData.setIdUserType(identificationData.getUserType().value());
            responseData.getRsaGenericResponseData().setTransactionId(identificationData.getTransactionId());
        }
        return responseData;
    }

    /**
     * Crear usuario.
     *
     * @param requestData
     *            the request data
     * @return the rsa create user response data
     * @throws BusinessException
     *             the business exception
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.rsa.bo.RsaCrearUsuarioBO#crearUsuario(ar.com.
     * santanderrio.obp.rsa.entities.RsaCreateUserRequestData)
     */
    @Override
    public RsaCreateUserResponseData crearUsuario(RsaCreateUserRequestData requestData) throws BusinessException {

        CreateUserRequest request = armarRequestCrearUsuario(requestData);
        CreateUserResponse2 response = null;
        try {
            response = rsaDAO.crearUsuario(request);
        } catch (DAOException e) {
            LOGGER.error("Error en crearUsuario.", e);
            throw new BusinessException(e);
        }
        return readCreateUserResponse(response);
    }

    /**
     * Armar request crear usuario.
     *
     * @param requestData
     *            the request data
     * @return the creates the user request
     * @throws BusinessException
     *             the business exception
     */
    private CreateUserRequest armarRequestCrearUsuario(RsaCreateUserRequestData requestData) throws BusinessException {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        fillGenericFields(createUserRequest, requestData.getRsaGenericRequestData());

        // Inicializaci?n espec?fica
        GenericActionTypeList actionsList = new GenericActionTypeList();
        actionsList.getGenericActionTypes().add(GenericActionType.SET_USER_STATUS);
        createUserRequest.setActionTypeList(actionsList);

        IdentificationData id = new IdentificationData();
        ResumenCliente rd = requestData.getRsaGenericRequestData().getResumenCliente();
        id.setUserName(rd.getNup());
        String userStatus = requestData.getUserStatus();
        if (userStatus != null && !userStatus.trim().isEmpty()) {
            id.setUserStatus(UserStatus.fromValue(userStatus));
        }
        String userType = requestData.getUserType();
        if (userType != null && !userType.trim().isEmpty()) {
            id.setUserType(WSUserType.fromValue(userType));
        }
        id.setOrgName(ORGNAME);
        createUserRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.CREATEUSER);
        mh.setVersion(RSA_VERSION);
        createUserRequest.setMessageHeader(mh);

        return createUserRequest;
    }

    /**
     * Read create user response.
     *
     * @param response
     *            the response
     * @return the rsa create user response data
     */
    private RsaCreateUserResponseData readCreateUserResponse(CreateUserResponse2 response) {
        RsaCreateUserResponseData responseData = new RsaCreateUserResponseData();
        readGenericFields(response, responseData.getRsaGenericResponseData());
        return responseData;
    }

    /**
     * Notificar.
     *
     * @param requestData
     *            the request data
     * @return the rsa notify response data
     * @throws BusinessException
     *             the business exception
     */
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.rsa.bo.RsaNotificarBO#notificar(ar.com.
     * santanderrio.obp.rsa.entities.RsaNotifyRequestData)
     */
    @Override
    public RsaNotifyResponseData notificar(RsaNotifyRequestData requestData) throws BusinessException {
        boolean modoAprendizaje = sesionParametros.isRsaEnAprendizaje();
        NotifyResponse2 response = null;
        if (!modoAprendizaje && sesionCliente.getTieneTokenRSA()) {
            NotifyRequest request = armarRequestNotificar(requestData);
            try {
                LOGGER.info("Llamando a notify............");
                response = rsaDAO.notificar(request);
            } catch (DAOException e) {
                LOGGER.error("Error en notificar {}", requestData, e);
                throw new BusinessException(e);
            }
        } else {
            LOGGER.info(RSA_NO_NOTIFICA_MODO_APRENDIZAJE);
            LOGGER.info(NO_PUDIERON_OBTENERSE_LOS_TOKENS_DE_RSA);
            RsaNotifyResponseData respuestaNotify = new RsaNotifyResponseData();
            respuestaNotify.setRsaGenericResponseData(new RsaGenericResponseData());
            sesionParametros.setRsaEnAprendizaje(false);
            return respuestaNotify;
        }
        return readNotifyResponse(response);
    }

    /**
     * Armar request notificar.
     *
     * @param requestData
     *            the request data
     * @return the notify request
     * @throws BusinessException
     *             the business exception
     */
    private NotifyRequest armarRequestNotificar(RsaNotifyRequestData requestData) throws BusinessException {
        LOGGER.info("Generando Notify request....");
        NotifyRequest notifyRequest = new NotifyRequest();
        RsaGenericRequestData rsaGenericRequestData = requestData.getRsaGenericRequestData();
        fillGenericFields(notifyRequest, rsaGenericRequestData);

        EventDataList edl = new EventDataList();
        EventData ed = new EventData();
        LOGGER.info("Notify EventReferenceId: {}", rsaGenericRequestData.getTransactionId());
        ed.setEventReferenceId(rsaGenericRequestData.getTransactionId());
        // FIXME ver si el event cambia para otras cosas
        ed.setEventType(EventType.EXTRA_AUTH);
        AuthenticationLevel al = new AuthenticationLevel();
        al.setAttemptsTryCount(requestData.getTryCount());
        al.setLevel(requestData.getLevel());
        al.setSuccessful(requestData.isSuccess());
        ed.setAuthenticationLevel(al);
        edl.getEventData().add(ed);
        notifyRequest.setEventDataList(edl);

        IdentificationData id = new IdentificationData();

        ResumenCliente rd = rsaGenericRequestData.getResumenCliente();
        id.setUserName(rd.getNup());
        String userStatus = requestData.getUserStatus();
        if (userStatus != null && !userStatus.trim().isEmpty()) {
            id.setUserStatus(UserStatus.fromValue(userStatus));
        }
        String userType = requestData.getWsUserType();
        if (userType != null && !userType.trim().isEmpty()) {
            id.setUserType(WSUserType.fromValue(userType));
        }
        id.setOrgName(ORGNAME);
        notifyRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.NOTIFY);
        mh.setVersion(RSA_VERSION);
        notifyRequest.setMessageHeader(mh);

        return notifyRequest;
    }

    /**
     * Read notify response.
     *
     * @param response
     *            the response
     * @return the rsa notify response data
     */
    private RsaNotifyResponseData readNotifyResponse(NotifyResponse2 response) {
        RsaNotifyResponseData responseData = new RsaNotifyResponseData();
        readGenericFields(response, responseData.getRsaGenericResponseData());
        return responseData;
    }

    /**
     * Fill generic fields.
     *
     * @param request
     *            the request
     * @param requestData
     *            the request data
     * @throws BusinessException
     *             the business exception
     */
    private void fillGenericFields(GenericRequest request, RsaGenericRequestData requestData) throws BusinessException {
        DeviceRequest dr = new DeviceRequest();

        dr.setHttpAccept(requestData.getHttpAccept());
        dr.setHttpReferrer(requestData.getHttpReferrer());
        dr.setHttpAcceptLanguage(requestData.getHttpAcceptLanguage());
        dr.setHttpAcceptEncoding(requestData.getHttpAcceptEncoding());
        dr.setHttpAcceptChars(requestData.getHttpAcceptChars());
        dr.setUserAgent(requestData.getUserAgent());
        dr.setDevicePrint(requestData.getDevicePrint());

        String pmdataCookie = requestData.getDeviceTokenCookie();

        if (pmdataCookie != null && !pmdataCookie.trim().isEmpty()) {
            dr.setDeviceTokenCookie(pmdataCookie);
        }

        dr.setIpAddress(requestData.getRemoteIp());
        request.setDeviceRequest(dr);

        SecurityHeader sh = new SecurityHeader();
        Credential credential = null;
        try {
            credential = credentialSecurity.getCredentialBySistema(RSA_SECURITY_ID);
            sh.setCallerCredential(credential.getPassword());
            sh.setCallerId(credential.getUsuario());
            sh.setMethod(AuthorizationMethod.PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("Error de SQL al invocar fillGenericFields", e);
            throw new BusinessException(e, ERROR_RSA_CREDENTIALS);
        }
        request.setSecurityHeader(sh);
    }

    /**
     * Read generic fields.
     *
     * @param response
     *            the response
     * @param responseData
     *            the response data
     */
    private void readGenericFields(GenericResponse response, RsaGenericResponseData responseData) {
        DeviceResult dr = response.getDeviceResult();
        StatusHeader sh = response.getStatusHeader();
        if (dr != null) {
            responseData.setDrStatusCode(dr.getCallStatus().getStatusCode());
            responseData.setDrStatusDescription(dr.getCallStatus().getStatusDescription());
            responseData.setDeviceTokenCookie(dr.getDeviceData().getDeviceTokenCookie());
            responseData.setDeviceTokenFso(dr.getDeviceData().getDeviceTokenFSO());
        }
        if (sh != null) {
            responseData.setShReasonCode(sh.getReasonCode().toString());
            responseData.setShReasonDescription(sh.getReasonDescription());
            responseData.setShStatusCode(sh.getStatusCode().toString());
        }
    }

    /**
     * Challenge.
     *
     * @param requestData
     *            the request data
     * @return the rsa analyze response data
     * @throws BusinessException
     *             the business exception
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.rsa.bo.RsaAnalizarBO#analizar(ar.com.santanderrio
     * .obp.rsa.entities.RsaAnalyzeRequestData)
     */
    @Override
    public RsaAnalyzeResponseData challenge(RsaAnalyzeRequestData requestData) throws BusinessException {
        LOGGER.info("Request: " + requestData);
        ChallengeRequest request = armarRequestChallenge(requestData);
        ChallengeResponse2 response = null;
        try {
            response = rsaDAO.challenge(request);
        } catch (DAOException e) {
            LOGGER.error("Error al invocar analizar", e);
            throw new BusinessException(e);
        }
        RsaAnalyzeResponseData rta = readChallengeResponse(response);
        LOGGER.info("Response: " + rta);
        return rta;
    }

    /*
     * 
     * <ws:transactionData> <ws:amount> <ws:amount>344399</ws:amount>
     * <ws:currency>ARS</ws:currency> </ws:amount>
     * 
     * <ws:dueDate>2014-04-03</ws:dueDate>
     * <ws:estimatedDeliveryDate>2014-04-03</ws:estimatedDeliveryDate>
     * 
     * <ws:executionSpeed>REAL_TIME</ws:executionSpeed>
     * 
     * <ws:myAccountData> <ws:accountBalance> <ws:amount>369590</ws:amount>
     * <ws:currency>ARS</ws:currency> </ws:accountBalance>
     * <ws:accountNickName>SOURCE</ws:accountNickName>
     * <ws:accountNumber>100-200773/3</ws:accountNumber>
     * <ws:accountType>USER_DEFINED</ws:accountType> </ws:myAccountData>
     * 
     * <ws:otherAccountData> <ws:accountNickName>DESTINATION</ws:accountNickName>
     * </ws:otherAccountData>
     * 
     * <ws:otherAccountOwnershipType>ME_TO_YOU</ws:otherAccountOwnershipType>
     * <ws:otherAccountType>BILLER</ws:otherAccountType>
     * <ws:schedule>IMMEDIATE</ws:schedule>
     * <ws:transferMediumType>BILLPAY_ELEC</ws:transferMediumType>
     * </ws:transactionData>
     */

    /**
     * Generar transaction data nuevo pago.
     *
     * @param requestData
     *            the request data
     * @return the transaction data
     */
    private TransactionData generarTransactionDataNuevoPago(RsaAnalyzePaymentRequestData requestData) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        amount.setAmount(new BigDecimal(requestData.getPago().getAmount()).longValue());
        amount.setCurrency(requestData.getPago().getDivisa().getCodigo());
        data.setAmount(amount);
        data.setExecutionSpeed(ExecutionSpeed.FEW_HOURS);
        data.setSchedule(Schedule.IMMEDIATE);
        data.setEstimatedDeliveryDate(DATE_FORMATTER.format(requestData.getPago().getDeliveryDate()));
        data.setDueDate(DATE_FORMATTER.format(requestData.getPago().getDueDate()));

        return data;
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.rsa.bo.RsaBO#updateUser(ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData)
	 */
	@Override
	public Respuesta<RsaUpdateUserResponseData> updateUser(RsaUpdateUserRequestData requestData) {
		UpdateUserResponse response = null;
		requestData.setRsaGenericRequestData(sesionParametros.getRsaGenericRequestData());
		try {
			UpdateUserRequest request = armarRequestUpdateUser(requestData);
			response = rsaDAO.updateUser(request);
		}  catch (BusinessException e) {
            LOGGER.error(MSG_ERROR_INVOCAR_ANALIZAR, e);
            return respuestaFactory.crearRespuestaError(RsaUpdateUserResponseData.class, MSG_ERROR_INVOCAR_ANALIZAR, null);
        } catch (DAOException e) {
            LOGGER.error(MSG_ERROR_INVOCAR_ANALIZAR, e);
            return respuestaFactory.crearRespuestaError(RsaUpdateUserResponseData.class, MSG_ERROR_INVOCAR_ANALIZAR, e.getErrorCode());
        }

		DeviceResult dr = response.getDeviceResult();
		StatusHeader sh = response.getStatusHeader();
		RsaUpdateUserResponseData responseData = new RsaUpdateUserResponseData();
		if (dr != null) {
			responseData.getRsaGenericResponseData().setDrStatusCode(dr.getCallStatus().getStatusCode());
			responseData.getRsaGenericResponseData().setDrStatusDescription(dr.getCallStatus().getStatusDescription());
			responseData.getRsaGenericResponseData().setDeviceTokenCookie(dr.getDeviceData().getDeviceTokenCookie());
			responseData.getRsaGenericResponseData().setDeviceTokenFso(dr.getDeviceData().getDeviceTokenFSO());
		}
		if (sh != null) {
			responseData.getRsaGenericResponseData().setShReasonCode(sh.getReasonCode().toString());
			responseData.getRsaGenericResponseData().setShReasonDescription(sh.getReasonDescription());
			responseData.getRsaGenericResponseData().setShStatusCode(sh.getStatusCode().toString());
		}
        LOGGER.info("Response: " + responseData);
        Respuesta<RsaUpdateUserResponseData> respuesta = respuestaFactory.crearRespuestaOk(RsaUpdateUserResponseData.class);
        respuesta.setRespuesta(responseData);
        return respuesta;
	}

    /**
     * Armar request update user.
     *
     * @param requestData
     *            the request data
     * @return the creates the user request
     * @throws BusinessException
     *             the business exception
     */
    private UpdateUserRequest armarRequestUpdateUser(RsaUpdateUserRequestData requestData) throws BusinessException {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        fillGenericFields(updateUserRequest, requestData.getRsaGenericRequestData());

        // Inicializaci?n espec?fica
        GenericActionTypeList actionsList = new GenericActionTypeList();
        actionsList.getGenericActionTypes().add(GenericActionType.SET_USER_STATUS);
        updateUserRequest.setActionTypeList(actionsList);

        IdentificationData id = new IdentificationData();
        ResumenCliente rd = requestData.getRsaGenericRequestData().getResumenCliente();
        id.setUserName(rd.getNup());
        id.setUserStatus(UserStatus.fromValue(requestData.getUserStatus()));
        id.setUserType(WSUserType.PERSISTENT);
        id.setOrgName(ORGNAME);
        updateUserRequest.setIdentificationData(id);

        MessageHeader mh = new MessageHeader();
        mh.setApiType(APIType.DIRECT_SOAP_API);
        mh.setRequestType(RequestType.UPDATEUSER);
        mh.setVersion(RSA_VERSION);
        updateUserRequest.setMessageHeader(mh);

        return updateUserRequest;
    }

}
