package ar.com.santanderrio.obp.servicios.echeqapi.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.echeqapi.config.EcheqApiConstants;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationResponse;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationResponse;
import ar.com.santanderrio.obp.servicios.oauth.bo.OAuthBO;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.config.ThreeScaleAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.UUID;


@Component
public class EcheqApiConnector implements EcheqApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(EcheqApiConnector.class);

    @Value("${OBP.3SCALE.SEC_ID_ENDORSEMENT}")
    private String secIdEndorsement;

    @Value("${OBP.3SCALE.SEC_ID_GUARANTY}")
    private String secIdGuaranty;

    @Value("${ECHEQ-API.PATH.POST-GUARANTY-EMISSION}")
    private String postGuarantyEmissionPath;

    @Value("${ECHEQ-API.PATH.POST-GUARANTY-REPUDIATION}")
    private String postGuarantyRepudiationPath;

    @Value("${ECHEQ-API.PATH.POST-GUARANTY-CANCELLATION}")
    private String postGuarantyCancellationPath;

    @Value("${ECHEQ-API.PATH.POST-GUARANTY-ACCEPTANCE}")
    private String postGuarantyAcceptancePath;

    @Value("${ECHEQ-API.PATH.POST-ENDORSEMENT-CANCELLATION}")
    private String postEndorsementCancellationPath;

    @Value("${ECHEQ-API.SCOPE}")
    private String oAuthScope;

    @Value("${OBP.CHANNEL}")
    private String obpChannel;

    @Value("${OBP.SUBCHANNEL}")
    private String obpSubchannel;

    @Autowired
    private OAuthBO oAuthBO;

    @Autowired
    private RestWebClient restWebClient;

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Autowired
    SesionCliente cliente;

    @Override
    public EcheqEndorsementCancellationResponse postEndorsementCancellation(EcheqEndorsementCancellationRequest request)
            throws DAOException {
        LOGGER.info("Inicio Echeq.EndorsementCancellation WS call");
        try {
            LOGGER.info("**************** INGRESO DESDE CANCELACION ENDOSO *****************");
            final WebClient webClient = restWebClient.obtenerClienteRest(EcheqApiConstants.ECHEQ_API_ENDORSEMENT);
            webClient
                    .header(EcheqApiConstants.CLIENT_CHANNEL, this.obpChannel)
                    .header(EcheqApiConstants.CLIENT_SUBCHANNEL, this.obpSubchannel)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(postEndorsementCancellationPath, request.getEndorser().getDocumentNumber());
            injectOAuthToken(webClient,secIdEndorsement);
            return webClient.post(request).readEntity(EcheqEndorsementCancellationResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al solicitar cancelación de endoso");
        }
    }

    @Override
    public GuarantyOrderCreationResponse postGuarantyEmissionOrder(GuarantyOrderCreationRequest request)
            throws DAOException {
        LOGGER.info("Inicio Echeq.Guaranty emission WS call");
        try {
            final WebClient webClient = restWebClient.obtenerClienteRest(EcheqApiConstants.ECHEQ_API);
            webClient
                    .header(EcheqApiConstants.CLIENT_CHANNEL, this.obpChannel)
                    .header(EcheqApiConstants.CLIENT_SUBCHANNEL, this.obpSubchannel)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(postGuarantyEmissionPath, request.getRequester().getDocumentNumber());
            injectOAuthToken(webClient,secIdGuaranty);
            return webClient.post(request).readEntity(GuarantyOrderCreationResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al solicitar cancelación de endoso");
        }
    }

    @Override
    public GuarantyOrderCreationResponse postGuarantyAcceptanceOrder(GuarantyOrderCreationRequest request)
            throws DAOException {
        LOGGER.info("Inicio Echeq.Guaranty acceptance WS call");
        try {
            final WebClient webClient = restWebClient.obtenerClienteRest(EcheqApiConstants.ECHEQ_API);
            webClient
                    .header(EcheqApiConstants.CLIENT_CHANNEL, this.obpChannel)
                    .header(EcheqApiConstants.CLIENT_SUBCHANNEL, this.obpSubchannel)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(postGuarantyAcceptancePath, request.getRequester().getDocumentNumber());
            injectOAuthToken(webClient,secIdGuaranty);
            return webClient.post(request).readEntity(GuarantyOrderCreationResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al solicitar la aceptación de aval");
        }
    }

    @Override
    public GuarantyOrderCreationResponse postGuarantyRepudiationOrder(GuarantyOrderCreationRequest request) throws DAOException {
        LOGGER.info("Inicio Echeq.Guaranty Repudiation WS call");
        try {
            final WebClient webClient = restWebClient.obtenerClienteRest(EcheqApiConstants.ECHEQ_API);
            webClient
                    .header(EcheqApiConstants.CLIENT_CHANNEL, this.obpChannel)
                    .header(EcheqApiConstants.CLIENT_SUBCHANNEL, this.obpSubchannel)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(postGuarantyRepudiationPath, request.getRequester().getDocumentNumber());
            injectOAuthToken(webClient,secIdGuaranty);
            return webClient.post(request).readEntity(GuarantyOrderCreationResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al solicitar cancelación de endoso");
        }
    }

    @Override
    public GuarantyOrderCreationResponse postGuarantyCancellationOrder(GuarantyOrderCreationRequest request) throws DAOException {
        LOGGER.info("Inicio Echeq.Guaranty Cancellation WS call");
        try {
            final WebClient webClient = restWebClient.obtenerClienteRest(EcheqApiConstants.ECHEQ_API);
            webClient
                    .header(EcheqApiConstants.CLIENT_CHANNEL, this.obpChannel)
                    .header(EcheqApiConstants.CLIENT_SUBCHANNEL, this.obpSubchannel)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(postGuarantyCancellationPath, request.getRequester().getDocumentNumber());
            injectOAuthToken(webClient,secIdGuaranty);
            return webClient.post(request).readEntity(GuarantyOrderCreationResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, "Ocurrió un error al solicitar cancelación de endoso");
        }
    }

    private void injectOAuthToken(final WebClient webClient, String secIdApp) throws DAOException {
        Respuesta<OAuthTokenResponse> oAuthTokenResponse =
                oAuthBO.getAccessToken3scale(buildThreeAppContext(secIdApp), buildTokenRequest());
        if(!EstadoRespuesta.OK.equals(oAuthTokenResponse.getEstadoRespuesta())) {
            throw new DAOException("Ocurrió un error al solicitar autorización  3scale");
        }
        OAuthTokenResponse tokenData = oAuthTokenResponse.getRespuesta();
        webClient.header(APIcConfigConstants.AUTH, buildBearerToken(tokenData.getAccessToken()));
        webClient.header(APIcConfigConstants.APIC_ID, buildThreeAppContext(secIdApp).getClientId());
        webClient.header(APIcConfigConstants.SAN_CORRELATIONID, UUID.randomUUID());
    }

    private OAuthTokenRequestDTO buildTokenRequest(){
        return new OAuthTokenRequestDTO()
                .setGrantType(OAuthGrantTypesEnum.CREDENTIALS)
                .setScope(this.oAuthScope);
    }

    private ThreeScaleAppContext buildThreeAppContext(String secIdApp) throws DAOException {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(secIdApp);
            if(credential == null) {
                throw new DAOException("Error al obtener credenciales scale");
            }
            return new ThreeScaleAppContext(credential.getUsuario(), credential.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales 3scale");
        }
    }

    private String buildBearerToken(String token) {
        return "Bearer " + token;
    }
}
