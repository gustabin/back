package ar.com.santanderrio.obp.servicios.oauth.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.config.ThreeScaleAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class OAuthConnectorImpl implements OAuthConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthConnectorImpl.class);
    private static final String OAUTH_WS = "OAUTH";
    private static final String OAUTH_GRANT_TYPE_DEFAULT = "client_credentials";
    private static final String OAUTH_3SCALE_WS = "OAUTH_3SCALE";

    @Value("${OAUTH.TOKEN.PATH}")
    private String pathOAuthToken;

    @Value("${OAUTH.3SCALE.TOKEN.PATH}")
    private String pathOAuthTokenThreeScale;



    @Autowired
    private RestWebClient restWebClient;

    public OAuthTokenResponse getAccessToken(final ApicAppContext context, final OAuthTokenRequestDTO request)
            throws DAOException {
        LOGGER.info("Inicio OAUTH.getToken WS call");
        WebClient webClient = restWebClient.obtenerClienteRest(OAUTH_WS);
        webClient.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .path(pathOAuthToken);
        try {
            Response oauthResponse = webClient.form(buildTokenForm(context, request));
            LOGGER.info("OAUTH: Token response status code {}", oauthResponse.getStatus());
            return oauthResponse.readEntity(OAuthTokenResponse.class);
        } catch (ProcessingException pe) {
            throw new DAOException(pe, pe.getMessage());
        }
    }


     public OAuthTokenResponse getAccessTokenThreeScale(final ThreeScaleAppContext context, final OAuthTokenRequestDTO request)
            throws DAOException {
        LOGGER.info("Inicio OAUTH-3SCALE.getToken WS call");
        WebClient webClient = restWebClient.obtenerClienteRest(OAUTH_3SCALE_WS);
        webClient.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .path(pathOAuthTokenThreeScale);
        try {
            Response oauthResponse = webClient.form(buildTokenFormThreeScale(context, request));
            LOGGER.info("OAUTH-3SCALE: Token response status code {}", oauthResponse.getStatus());
            return oauthResponse.readEntity(OAuthTokenResponse.class);
        } catch (ProcessingException pe) {
            throw new DAOException(pe, pe.getMessage());
        }
    }



    public Form buildTokenForm(ApicAppContext context, OAuthTokenRequestDTO request){
        return new Form()
            .param("client_id", context.getClientId())
            .param("client_secret", context.getClientSecret())
            .param("grant_type", request.getGrantType() != null ? request.getGrantType().getCode() : OAUTH_GRANT_TYPE_DEFAULT)
            .param("scope", request.getScope() != null ? request.getScope() : "");
    }

    public Form buildTokenFormThreeScale(ThreeScaleAppContext context, OAuthTokenRequestDTO request){
        return  new Form()
                .param("client_id", context.getClientId())
                .param("client_secret", context.getClientSecret())
                .param("grant_type", request.getGrantType() != null ? request.getGrantType().getCode() : OAUTH_GRANT_TYPE_DEFAULT)
                .param("scope", request.getScope() != null ? request.getScope() : "");
    }


}

