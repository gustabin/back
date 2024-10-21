/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.dao.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.rsa.AdaptiveAuthenticationInterface;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.AnalyzeResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.ChallengeRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.ChallengeResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.CreateUserRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.CreateUserResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.GenericResponse;
import ar.com.santanderrio.obp.generated.webservices.rsa.NotifyRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.NotifyResponse2;
import ar.com.santanderrio.obp.generated.webservices.rsa.UpdateUserRequest;
import ar.com.santanderrio.obp.generated.webservices.rsa.UpdateUserResponse;
import ar.com.santanderrio.obp.servicios.rsa.dao.RsaDAO;

/**
 * The Class RsaDAOImpl.
 *
 * @author Ignacio_Valek
 */
@Component
public class RsaDAOImpl implements RsaDAO {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaDAOImpl.class);

    /** The Constant RESPONSE_OK. */
    private static final Integer RESPONSE_OK = 0;

    /** The Constant RSA_FAULT. */
    private static final String RSA_FAULT = "-1";

    /** The estadistica BO. */
    @Autowired
    private EstadisticaBO estadisticaBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    SesionCliente sesionCliente;

    /** The adaptive authentication interface. */
    @Autowired
    private GestionarWS<AdaptiveAuthenticationInterface> wsClient;

    private static final String RSA_ANALISIS_USUARIO = "RSA_ANALISIS_USUARIO";
    private static final String RSA_CREAR_USUARIO = "RSA_CREAR_USUARIO";
    private static final String RSA_UPDATE_USUARIO = "RSA_UPDATE_USUARIO";
    private static final String RSA_NOTIFICAR = "RSA_NOTIFICAR";
    private static final String RSA_CHALLENGE = "RSA_CHALLENGE";
    private static final String RSA_BLOQUEARUSUARIO = "RSA_BLOQUEARUSUARIO";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.rsa.dao.RsaDAO#analizar(ar.com.santanderrio.obp.
     * rsa.entities.RsaAnalyzeRequestData)
     */
    @Override
    public AnalyzeResponse2 analizar(AnalyzeRequest request) throws DAOException {
        AnalyzeResponse2 response = null;
        AdaptiveAuthenticationInterface adaptiveAuthenticationInterface = null;
        try {
            adaptiveAuthenticationInterface = wsClient.obtenerPort();
            LOGGER.info("Ejecutando analize..... ");
            response = adaptiveAuthenticationInterface.analyze(request);
            
        } catch (Exception e) {

            Cliente cliente = sesionCliente.getCliente();

            grabarEstadisticaEjecucionRSA(response, cliente, request, RSA_ANALISIS_USUARIO);

            LOGGER.error("Error al analizar", e);
            throw new DAOException(e);
        } catch (Error e) {

            Cliente cliente = sesionCliente.getCliente();
            grabarEstadisticaEjecucionRSA(response, cliente, request, RSA_ANALISIS_USUARIO);

            LOGGER.error("RSA Fault", e);
            if (e instanceof LinkageError) {
            	throw new DAOException(e, "", RSA_FAULT);
            }
            throw new DAOException(e);
        } finally {
            wsClient.liberarPort(adaptiveAuthenticationInterface);
        }
        LOGGER.info("Validando Analize response..... ");
        analizeResponse(response, RSA_ANALISIS_USUARIO);
        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.rsa.dao.RsaDAO#notificar(ar.com.santanderrio.obp.
     * rsa.entities.RsaNotifyRequestData)
     */
    @Override
    public NotifyResponse2 notificar(NotifyRequest request) throws DAOException {
        NotifyResponse2 response = null;
        AdaptiveAuthenticationInterface adaptiveAuthenticationInterface = null;
        try {
            adaptiveAuthenticationInterface = wsClient.obtenerPort();
            response = adaptiveAuthenticationInterface.notify(request);
        } catch (Exception e) {

            Cliente cliente = sesionCliente.getCliente();
            grabarEstadisticaEjecucionRSA(response, cliente, null, RSA_NOTIFICAR);

            LOGGER.error("Error al notificar", e);
            throw new DAOException(e);
        } finally {
            wsClient.liberarPort(adaptiveAuthenticationInterface);
        }
        analizeResponse(response, RSA_NOTIFICAR);
        LOGGER.info("notificar -  notify -> Se ejecuto correctamente ");
        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.rsa.dao.RsaDAO#crearUsuario(ar.com.santanderrio.
     * obp.rsa.entities.RsaCreateUserRequestData)
     */
    @Override
    public CreateUserResponse2 crearUsuario(CreateUserRequest request) throws DAOException {
        CreateUserResponse2 response = null;
        AdaptiveAuthenticationInterface adaptiveAuthenticationInterface = null;
        try {
            adaptiveAuthenticationInterface = wsClient.obtenerPort();
            response = adaptiveAuthenticationInterface.createUser(request);
        } catch (Exception e) {

            Cliente cliente = sesionCliente.getCliente();
            grabarEstadisticaEjecucionRSA(response, cliente, null, RSA_CREAR_USUARIO);

            LOGGER.error("Error al crear usuario", e);
            throw new DAOException(e);
        } finally {
            wsClient.liberarPort(adaptiveAuthenticationInterface);
        }
        analizeResponse(response, RSA_CREAR_USUARIO);
        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.rsa.dao.RsaDAO#challenge(ar.com.
     * santanderrio.obp.generated.webservices.rsa.ChallengeRequest)
     */
    @Override
    public ChallengeResponse2 challenge(ChallengeRequest request) throws DAOException {
        ChallengeResponse2 response = null;
        AdaptiveAuthenticationInterface adaptiveAuthenticationInterface = null;
        try {
            adaptiveAuthenticationInterface = wsClient.obtenerPort();
            response = adaptiveAuthenticationInterface.challenge(request);
        } catch (Exception e) {

            Cliente cliente = sesionCliente.getCliente();
            grabarEstadisticaEjecucionRSA(response, cliente, null, RSA_CHALLENGE);

            LOGGER.error("Error al consultar challenge", e);
            throw new DAOException(e);
        } finally {
            wsClient.liberarPort(adaptiveAuthenticationInterface);
        }
        analizeResponse(response, RSA_CHALLENGE);
        return response;
    }

    /**
     * Analize response.
     *
     * @param response
     *            the response
     * @throws DAOException
     *             the DAO exception
     */
    private void analizeResponse(GenericResponse response, String tipoOperacion) throws DAOException {
        if (!RESPONSE_OK.equals(response.getStatusHeader().getReasonCode())) {

            Cliente cliente = sesionCliente.getCliente();
            grabarEstadisticaEjecucionRSA(response, cliente, null, tipoOperacion);

            LOGGER.error("Error en servicio RSA StatusCode: " + response.getStatusHeader().getReasonCode());
            throw new DAOException("Error en servicio RSA StatusCode: " + response.getStatusHeader().getReasonCode());
        }
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.rsa.dao.RsaDAO#updateUser(ar.com.santanderrio.obp.generated.webservices.rsa.UpdateUserRequest)
	 */
	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request) throws DAOException {
		UpdateUserResponse response = null;
	        AdaptiveAuthenticationInterface adaptiveAuthenticationInterface = null;
	        try {
	        	LOGGER.info("Request update user RSA: " + request);
	            adaptiveAuthenticationInterface = wsClient.obtenerPort();
	            response = adaptiveAuthenticationInterface.updateUser(request);
	        } catch (Exception e) {

                Cliente cliente = sesionCliente.getCliente();
                grabarEstadisticaEjecucionRSA(response, cliente, null, RSA_UPDATE_USUARIO);

	            LOGGER.error("Error al realizar update del usuario", e);
	            throw new DAOException(e);
	        } finally {
	            wsClient.liberarPort(adaptiveAuthenticationInterface);
	        }
	        LOGGER.info("Response update user RSA: " + response);
	        analizeResponse(response, RSA_UPDATE_USUARIO);
	        return response;
	}


    /**
     * Grabar estadistica ejecucion pago multiple.
     *
     * @param response
     * @param cliente
     * @param analyzeRequest
     * @param tipoOperacion
     *
     */
    public void grabarEstadisticaEjecucionRSA(GenericResponse response, Cliente cliente,
                                               AnalyzeRequest analyzeRequest, String tipoOperacion) {
        try {
            Estadistica estadistica = new Estadistica();

            estadistica.setCodigoTransaccion(EstadisticasConstants.CODIGO_ESTADISTICA_ERROR_RSA);
            estadistica.setNroCtaOrigen(tipoOperacion);

            //Registro el evento en caso de que la operacion sea Analisis de Usuario.
            if (analyzeRequest != null && analyzeRequest.getEventDataList().getEventData() != null &&
                    !analyzeRequest.getEventDataList().getEventData().isEmpty() &&
                    analyzeRequest.getEventDataList().getEventData().get(0).getEventType() != null) {
                String eventData = analyzeRequest.getEventDataList().getEventData().get(0).getEventType().toString();

                LOGGER.info("Event data RSA: " + eventData);
                estadistica.setNroCtaDestino(eventData);
            }

            //Registro el codigo en caso de que la respuesta sea diferente de ok.
            if (response != null) {
                String responseCode = response.getStatusHeader().getReasonCode().toString();
                LOGGER.info("Response code RSA: " + responseCode);
                estadistica.setCodigoError(responseCode);
            }

            estadisticaBO.add(estadistica, sesionParametros.getRegistroSession(), cliente);
        } catch (BusinessException e) {
            LOGGER.error("Error al grabar estadistica", e);
        }
    }
}
