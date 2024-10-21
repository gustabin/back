package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.accounts.entities.PackagesResponseEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class PackagesApiClient implements PackagesApi {
    private final URI baseUrl;
    private final OAuth2RestTemplate restTemplate;

    protected PackagesApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
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
    public PackagesResponseEntity getPackagesByNup(String customerId) throws ApiException {

        URI requestUri = UriComponentsBuilder
                .fromUriString(baseUrl.toString() + "?customerId={id}")
                .buildAndExpand(customerId).toUri();

        RequestEntity<Void> getPackagesByNupRequest = RequestEntity
                .get(requestUri)
                .header(APIcConfigConstants.SAN_IATX_LOGGED_USER, customerId)
                .build();
        return this.execute(getPackagesByNupRequest, PackagesResponseEntity.class);
    }
}