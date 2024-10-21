package ar.com.santanderrio.obp.servicios.api.transfers.recipients;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.dto.RecipientsResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.exception.RecipientsApiException;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipientsApiClient implements RecipientsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipientsApiClient.class);

    private final RestTemplate restTemplate;

    private final TokenManager tokenManager;

    private final CredentialSecurityFactory credentialSecurityFactory;

    private Credential credential;

    @Value("${RECIPIENTS-API.RECIPIENTS.FETCH.LIMIT}")
    private int recipientsToFetchLimit;

    @Value("${RECIPIENTS-API.BASEPATH.URL}")
    private String basePath;

    @Value("${RECIPIENTS-API.TOKEN.PROVIDER.URL}")
    private String tokenProviderUrl;

    @Value("${RECIPIENTS-API.SEC.ID}")
    private String secId;

    private static final String AUTHORIZATION_BEARER = "Bearer ";

    @Autowired
    public RecipientsApiClient(@Qualifier("recipientsApiRestTemplate") RestTemplate restTemplate, TokenManager tokenManager, CredentialSecurityFactory credentialSecurityFactory) {

        this.restTemplate = restTemplate;
        this.tokenManager = tokenManager;
        this.credentialSecurityFactory = credentialSecurityFactory;

    }

    @Override
    public List<Recipient> getAllRecipients(String nup) {

        long totalRecipientsToFetch;
        long recipientsFetched = 0;
        int pageNumber = 0;
        List<Recipient> recipients = new ArrayList<Recipient>();

        do {

            RecipientsResponse response = getAllRecipientsWithPagination(nup, pageNumber, recipientsToFetchLimit);
            totalRecipientsToFetch = response.getTotal();
            recipientsFetched += response.getRecipients().size();
            pageNumber += 1;
            recipients.addAll(response.getRecipients());

        } while (recipientsFetched < totalRecipientsToFetch && (pageNumber <= totalRecipientsToFetch / recipientsToFetchLimit));

        LOGGER.info("Nup = " + nup + " - " + "Total agendados obtenidos = " + recipients.size());

        return recipients;

    }

    @Override
    public RecipientsResponse getAllRecipientsWithPagination(String nup, int page, int limit) {

        String requestUri = UriComponentsBuilder
                .fromUriString(basePath + "/{nup}/recipients")
                .buildAndExpand(nup).toUriString();

        URI requestUriWithQueryParams = UriComponentsBuilder
                .fromUriString(requestUri + "?offset={offset}&limit={limit}")
                .replaceQueryParam("offset", page)
                .replaceQueryParam("limit", limit)
                .build().toUri();

        return execute(requestUriWithQueryParams, RecipientsResponse.class);

    }

    private <T> T execute (URI uri, Class<T> responseClass) {

        try {

            Token token = tokenManager.getToken(tokenProviderUrl, getDBCredential());

            return restTemplate.exchange(getRequestEntity(token, uri), responseClass).getBody();

        } catch (HttpClientErrorException e) {

            throw new RecipientsApiException(e.getResponseBodyAsString(), e);

        } catch (Exception e) {

            throw new RecipientsApiException(e);

        }

    }

    private RequestEntity<Void> getRequestEntity(Token token, URI uri) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, AUTHORIZATION_BEARER + token.getValue());

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
