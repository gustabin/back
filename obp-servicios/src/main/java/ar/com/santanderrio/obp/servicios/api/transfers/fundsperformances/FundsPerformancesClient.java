package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto.FundsPerformancesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.SQLException;


@Component
public class FundsPerformancesClient implements FundsPerformances {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundsPerformancesClient.class);

    @Value("${FUNDS-API.PATH.BASE}")
    private String fundsBasePath;

    @Value("${OAUTHV2.URL}")
    private String oauthUrl;

    @Value("${OAUTHV2.TOKEN.PATH}")
    private String oauthTokenPath;

    @Value("${FONDOS.OAUTHV2.SEC.ID}")
    private String secId;

    private final TokenManager tokenManager;

    private final RestTemplate restTemplate;

    private final CredentialSecurityFactory credentialSecurityFactory;

    private final IatxObpContext iatxObpContext;

    private Credential credential;

    private static final String AUTHORIZATION_BEARER = "Bearer ";
    private static final String X_SAN_SEGMENT_ID = "x-san-segment-id";
    private static final String X_IBM_CLIENT_ID = "x-ibm-client-id";
    private static final String X_SAN_IATX_USER_ID = "x-san-iatx-user-id";
    private static final String X_SAN_IATX_USER_PASS = "x-san-iatx-user-pass";


    @Autowired
    public FundsPerformancesClient(TokenManager tokenManager, RestTemplate restTemplate,
                                   CredentialSecurityFactory credentialSecurityFactory,
                                   IatxObpContext iatxObpContext) {
        this.tokenManager = tokenManager;
        this.restTemplate = restTemplate;
        this.credentialSecurityFactory = credentialSecurityFactory;
        this.iatxObpContext = iatxObpContext;
    }

    @Override
    public FundsPerformancesResponse getPerformancesById(String id) {

        try {

            LOGGER.info("Obteniendo performance del fci con id: {}", id);

            URI requestUri = UriComponentsBuilder
                    .fromUriString(fundsBasePath + "/performances?id={id}")
                    .replaceQueryParam("id", id)
                    .build().toUri();

            FundsPerformancesResponse fundsPerformancesResponse = restTemplate
                    .exchange(getRequestEntity(requestUri), FundsPerformancesResponse.class).getBody();

            LOGGER.info("Se obtuvo el performance del fci con id: {}", id);

            return fundsPerformancesResponse;

        } catch (HttpClientErrorException e) {

            throw new FundsPerformancesException(e.getResponseBodyAsString(), e);

        } catch (Exception e) {

            throw new FundsPerformancesException(e);
        }
    }

    private RequestEntity<Void> getRequestEntity(URI uri) throws SQLException, UnsupportedEncodingException {

        String tokenUrl = oauthUrl + oauthTokenPath;

        Token token = tokenManager.getToken(tokenUrl, getDBCredential());

        final HttpHeaders headers = getRequestHeaders(token);

        return new RequestEntity<Void>(headers, HttpMethod.GET, uri);

    }

    private HttpHeaders getRequestHeaders(Token token) {

        initIatxObpCtx();

        HttpHeaders headers = new HttpHeaders();

        headers.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION_BEARER + token.getValue());
        headers.set(X_SAN_SEGMENT_ID, "2");
        headers.set(X_IBM_CLIENT_ID, credential.getUsuario());
        headers.set(X_SAN_IATX_USER_ID, iatxObpContext.getIatxUser());
        headers.set(X_SAN_IATX_USER_PASS, iatxObpContext.getIatxPassword());

        return headers;
    }

    private Credential getDBCredential() throws SQLException {

        if (credential != null) {

            return credential;
        }

        credential = credentialSecurityFactory.getCredentialById(secId);

        return credential;

    }

    private void initIatxObpCtx() {

        try {

            Credential cred = credentialSecurityFactory.getCredentialById(iatxObpContext.getSecurityId());

            iatxObpContext.setIatxUser(cred.getUsuario());
            iatxObpContext.setIatxPassword(cred.getPassword());

        } catch (SQLException ex) {

            throw new IllegalStateException("Error at fetching apif-api_mutual_funds securityId credentials");
        }
    }
}
