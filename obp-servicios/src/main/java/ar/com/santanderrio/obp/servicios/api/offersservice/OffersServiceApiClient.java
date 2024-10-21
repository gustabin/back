package ar.com.santanderrio.obp.servicios.api.offersservice;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.offersservice.entities.OffersEntity;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;

public class OffersServiceApiClient implements OffersServiceApi {
    private final URI baseUrl;
    private final OAuth2RestTemplate restTemplate;

    public OffersServiceApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public OffersEntity getCommercialOffers(String customerId, String device, String channel, String subchannel) {
        URI requestUri = UriBuilder.fromUri(baseUrl)
            .queryParam("customer_id", customerId)
            .queryParam("device", device)
            .queryParam("channel", channel)
            .queryParam("subchannel", subchannel)
            .build();
        RequestEntity<Void> getCommercialOffers = RequestEntity
            .get(requestUri)
            .build();
        return this.execute(getCommercialOffers, OffersEntity.class);
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
}
