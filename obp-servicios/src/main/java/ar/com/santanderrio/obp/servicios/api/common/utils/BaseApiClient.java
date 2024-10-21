package ar.com.santanderrio.obp.servicios.api.common.utils;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;

import java.net.URI;

public abstract class BaseApiClient {
    private final URI baseUrl;
    private final OAuth2RestTemplate restTemplate;

    protected BaseApiClient(final URI baseUrl, final OAuth2RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    protected URI getBaseUrl() {
        return this.baseUrl;
    }

    protected <T> T execute (RequestEntity<?> request, Class<T> responseClass) throws ApiException {
        try {
            return this.restTemplate.exchange(request, responseClass).getBody();
        } catch (ResourceAccessException ioException) {
            throw new ApiException(new ErrorResponse()
                    .code("ERROR")
                    .message("NO RESPONSE BODY FOUND"), ioException);
        }
    }

    protected <T> T execute (RequestEntity<?> request, ParameterizedTypeReference<T> type) throws ApiException {
        try {
            return this.restTemplate.exchange(request, type).getBody();
        } catch (ResourceAccessException ioException) {
            throw new ApiException(new ErrorResponse()
                    .code("ERROR")
                    .message("NO RESPONSE BODY FOUND"), ioException);
        }
    }
}
