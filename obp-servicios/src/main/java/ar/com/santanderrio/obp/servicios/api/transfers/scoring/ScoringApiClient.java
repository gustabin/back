package ar.com.santanderrio.obp.servicios.api.transfers.scoring;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.dto.ScoringApiResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception.ScoringApiException;
import org.apache.commons.lang3.StringUtils;
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
import java.util.UUID;

@Component
public class ScoringApiClient implements ScoringApi{

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoringApiClient.class);

    @Value("${SCORING-API.TOKEN.PROVIDER.URL}")
    private String tokenProviderUrl;

    @Value("${SCORING-API.URL}")
    private String scoringApiUrl;

    @Value("${SCORING-API.TRF.COLLECTION.NAME}")
    private String collectionName;

    @Value("${SCORING-API.SEC.ID}")
    private String secId;

    private final TokenManager tokenManager;

    private final RestTemplate restTemplate;

    private final CredentialSecurityFactory credentialSecurityFactory;

    private Credential credential;

    private static final String AUTHORIZATION_BEARER = "Bearer ";

    private static final String X_SAN_CORRELATION_ID = "X-SAN-CorrelationId";

    @Autowired
    public ScoringApiClient(TokenManager tokenManager, RestTemplate restTemplate, CredentialSecurityFactory credentialSecurityFactory) {

        this.tokenManager = tokenManager;

        this.restTemplate = restTemplate;

        this.credentialSecurityFactory = credentialSecurityFactory;

    }

    @Override
    public float getScoring(String cuit) {

        try {

            LOGGER.info("Obteniendo el scoring para el cuit {}", cuit);

            URI requestUriWithQueryParams = UriComponentsBuilder
                    .fromUriString(scoringApiUrl + "?collection={collection}&cuit={cuit}")
                    .replaceQueryParam("collection", collectionName)
                    .replaceQueryParam("cuit", StringUtils.remove(cuit, "-"))
                    .build().toUri();

            float score = restTemplate.exchange(getRequestEntity(requestUriWithQueryParams), ScoringApiResponse.class).getBody().getScore();

            LOGGER.info("Scoring obtenido para el cuit {}: {}", cuit, score);

            return score;

        } catch (HttpClientErrorException e) {

            throw new ScoringApiException(e.getResponseBodyAsString(), e);

        } catch (Exception e) {

            throw new ScoringApiException(e);

        }

    }

    private RequestEntity<Void> getRequestEntity(URI uri) throws SQLException, UnsupportedEncodingException {

        Token token = tokenManager.getToken(tokenProviderUrl, getDBCredential());

        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION_BEARER + token.getValue());
        String correlationId = UUID.randomUUID().toString();
        headers.set(X_SAN_CORRELATION_ID, correlationId);

        LOGGER.info("CorrelationId = {}", correlationId);

        return new RequestEntity<Void>(headers, HttpMethod.GET, uri);

    }

    private Credential getDBCredential() throws SQLException {

        if (credential != null) {

            return credential;

        }

        credential = credentialSecurityFactory.getCredentialById(secId);

        return credential;

    }

}
