package ar.com.santanderrio.obp.servicios.api.sso.consent;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.utils.BaseApiClient;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserConsentRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsentApiClient extends BaseApiClient implements ConsentApi {
    private final URI pcpAdminUrl;

    protected ConsentApiClient(URI baseUrl, URI pcpAdminUrl, OAuth2RestTemplate restTemplate) {
        super(baseUrl, restTemplate);
        this.pcpAdminUrl = pcpAdminUrl;
    }

    @Override
    public List<UserRepresentationModel> getUserDataByNup(String customerId) throws ApiException {
        URI requestUri = UriComponentsBuilder.fromUriString(getBaseUrl().toString() + "/users")
                .queryParam("q", "nup:" + customerId)
                .build().toUri();

        RequestEntity<Void> getUserIdByNupRequest = RequestEntity.get(requestUri).build();
        ParameterizedTypeReference<List<UserRepresentationModel>> typeRef =
                new ParameterizedTypeReference<List<UserRepresentationModel>>() {};
        return execute(getUserIdByNupRequest, typeRef);
    }

    @Override
    public List<UserConsentRepresentationModel> getConsentsByUserId(String userId) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/users/{username}/consents")
                .buildAndExpand(userId)
                .toUri();

        RequestEntity<Void> getUserConsentsRequest = RequestEntity.get(requestUri).build();
        ParameterizedTypeReference<List<UserConsentRepresentationModel>> typeRef =
                new ParameterizedTypeReference<List<UserConsentRepresentationModel>>() {};
        return execute(getUserConsentsRequest, typeRef);
    }

    @Override
    public Void revokeConsentByUserId(String userId, String consentedClientId) throws ApiException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", userId);
        params.put("consentedClientId", consentedClientId);
        URI requestUri = UriComponentsBuilder
                .fromUriString(pcpAdminUrl.toString() + "/users/{userId}/consents/{consentedClientId}")
                .buildAndExpand(params)
                .toUri();
        RequestEntity<Void> getUserConsentsRequest = RequestEntity.delete(requestUri).build();
        return execute(getUserConsentsRequest, Void.class);
    }
}