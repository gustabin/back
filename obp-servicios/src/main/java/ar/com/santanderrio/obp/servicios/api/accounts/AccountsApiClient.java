package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class AccountsApiClient implements AccountsApi {
    private final URI baseUrl;
    private final OAuth2RestTemplate restTemplate;

    protected AccountsApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountsResponseEntity getAccountsByCustomerId(String customerId) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl.toString() + "/accounts?customerId={id}&status=open&participantQuality=owner,co_owner&_limit=100")
                .buildAndExpand(customerId).toUri();

        RequestEntity<Void> getAccountsByCustomerIdRequest = RequestEntity
                .get(requestUri)
                .header(APIcConfigConstants.SAN_IATX_LOGGED_USER, customerId)
                .build();
        return this.execute(getAccountsByCustomerIdRequest, AccountsResponseEntity.class);
    }

    private <T> T execute (RequestEntity<?> request, Class<T> responseClass) throws ApiException {
        try {
            return this.restTemplate.exchange(request, responseClass).getBody();
        } catch (ResourceAccessException ioException) {
            throw new ApiException(new ErrorResponse()
                    .code("ERROR")
                    .message("NO RESPONSE BODY FOUND"), ioException);
        }
    }

    @Override
    public AccountEntity getAccountByAccountId(String accountId, String customerId) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl.toString() + "/accounts/{id}")
                .buildAndExpand(accountId).toUri();

        RequestEntity<Void> getAccountsByAccountIdRequest = RequestEntity
                .get(requestUri)
                .header(APIcConfigConstants.SAN_IATX_LOGGED_USER, customerId)
                .build();
        return this.execute(getAccountsByAccountIdRequest, AccountEntity.class);
    }

    @Override
    public AccountEntity getAccountPublicByAccountId(String accountId, String currency) throws  ApiException{
        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl.toString() + "/accounts/{accountId}/public-data?currencyCode="+currency)
                .buildAndExpand(accountId).toUri();

        RequestEntity<Void> getAccountsByAccountIdRequest = RequestEntity
                .get(requestUri)
                .build();
        return this.execute(getAccountsByAccountIdRequest,AccountEntity.class);
    }
}