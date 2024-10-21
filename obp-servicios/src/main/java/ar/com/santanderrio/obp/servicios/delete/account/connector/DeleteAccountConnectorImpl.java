package ar.com.santanderrio.obp.servicios.delete.account.connector;

import java.sql.SQLException;
import java.util.UUID;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.helpers.FormatFep;
import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountRequest;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountResponse;
import ar.com.santanderrio.obp.servicios.oauth.bo.OAuthBO;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;

@Component
public class DeleteAccountConnectorImpl implements DeleteAccountConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAccountConnectorImpl.class);
    private static final String ACCOUNTS_WS = "ACCOUNTS-API";

    @Value("${OBP.APIC.SEC_ID}")
    private String secId;

    @Value("${ACCOUNTS-API.SCOPE}")
    private String oAuthAccountsScope;

    @Autowired
    private OAuthBO oAuthBO;

    @Autowired
    private RestWebClient restWebClient;

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;
    
    /** The iatxInicial. */
    @Autowired
    private IatxInicial iatxInicial;
    
    /** The Constant CHANNEL_VALUE. */
	private static final String CHANNEL_ID_VALUE = "HTML";
	/** The Constant SUBCHANNEL_VALUE. */
	private static final String SUBCHANNEL_ID_VALUE = "0001";
	/** The Constant CHANNEL_VALUE. */
	private static final String CHANNEL_TYPE_VALUE = "04";
	/** The Constant SUBCHANNEL_VALUE. */
	private static final String SUBCHANNEL_TYPE_VALUE = "99";
    /** The Constant USUARIO_RACF_ID_SIZE. */
    private static final int USUARIO_RACF_ID_SIZE = 8;
    /** The Constant USUARIO_RACF_PWD_SIZE. */
    private static final int USUARIO_RACF_PWD_SIZE = 8;
    
    @Override
    public DeleteAccountResponse deleteAccountById(DeleteAccountRequest request) throws DAOException {
        LOGGER.info("Inicio AccountsApi.delete WS call");
        try {
            final WebClient webClient = restWebClient.obtenerClienteRest(ACCOUNTS_WS);
            webClient.type(MediaType.APPLICATION_JSON_TYPE)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .path(request.getFormattedAccountId())
                    .query("simulate", request.getSimulate())
                    .query("cancellationReason", request.getCancellationReason())
                    .query("holdSavingAccount", request.getKeepsAccount());
            injectHeader(webClient, request.getNup());
            return webClient.delete().readEntity(DeleteAccountResponse.class);
        } catch (ResponseProcessingException pe) {
            throw new DAOException(pe, pe.getMessage(), Integer.toString(pe.getResponse().getStatus()));
        } catch (Exception e) {
            throw new DAOException(e, e.getMessage());
        }
    }

    /**
     * Method for getting the jwt and access tokens
     * @param webClient
     * @throws DAOException
     */
	private void injectHeader(final WebClient webClient, String nup) throws DAOException {
        final ApicAppContext apicAppContext = buildApicAppContext();
        final Respuesta<OAuthTokenResponse> oAuthTokenResponse =
                oAuthBO.getAccessToken(apicAppContext, buildTokenRequest());
        if(!EstadoRespuesta.OK.equals(oAuthTokenResponse.getEstadoRespuesta())) {
            throw new DAOException("Ocurrió un error al solicitar autorización de APIc");
        }
        OAuthTokenResponse tokenData = oAuthTokenResponse.getRespuesta();

        String userRacfId = FormatFep.fillStr(iatxInicial.getRacfInicialId(), USUARIO_RACF_ID_SIZE);
        String passRacfId = FormatFep.fillStr(iatxInicial.getRacfInicialPwd(), USUARIO_RACF_PWD_SIZE);
        
        webClient.header(APIcConfigConstants.AUTH, buildBearerToken(tokenData.getAccessToken()));
        webClient.header(APIcConfigConstants.X_AUTH, buildBearerToken(tokenData.getJwtToken()));
        webClient.header(APIcConfigConstants.APIC_ID, buildApicAppContext().getClientId());
        webClient.header(APIcConfigConstants.SAN_CORRELATIONID, UUID.randomUUID());
        webClient.header(APIcConfigConstants.SAN_IATX_CHANNEL_ID, CHANNEL_ID_VALUE);
        webClient.header(APIcConfigConstants.SAN_IATX_CHANNEL_TYPE, CHANNEL_TYPE_VALUE);
        webClient.header(APIcConfigConstants.SAN_IATX_LOGGED_USER, nup);
        webClient.header(APIcConfigConstants.SAN_IATX_SUBCHANNEL_ID, SUBCHANNEL_ID_VALUE);
        webClient.header(APIcConfigConstants.SAN_IATX_SUBCHANNEL_TYPE, SUBCHANNEL_TYPE_VALUE);
        webClient.header(APIcConfigConstants.SAN_IATX_USER_ID, userRacfId);
        webClient.header(APIcConfigConstants.SAN_IATX_USER_PASS, passRacfId);
     
    }

	/**
	 * Building token request
	 * @return
	 */
    private OAuthTokenRequestDTO buildTokenRequest(){
        return new OAuthTokenRequestDTO()
                .setGrantType(OAuthGrantTypesEnum.CREDENTIALS)
                .setScope(this.oAuthAccountsScope);
    }

    /**
     * Getting credentials
     * @return
     * @throws DAOException
     */
    private ApicAppContext buildApicAppContext() throws DAOException {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(secId);
            if(credential == null) {
                throw new DAOException("Error al obtener credenciales");
            }
            return new ApicAppContext(credential.getUsuario(), credential.getPassword());
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales");
        }
    }


    /**
     * Bearer
     * @param token
     * @return
     */
    private String buildBearerToken(String token) {
        return "Bearer " + token;
    }
}
