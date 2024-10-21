package ar.com.santanderrio.obp.servicios.api.sso.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.utils.BaseApiClient;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class SSOClientsApiClient extends BaseApiClient implements SSOClientsApi {

    protected SSOClientsApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
        super(baseUrl, restTemplate);
    }

    @Override
    public List<ClientRepresentationModel> getClientByClientId(String clientId) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/clients?clientId={clientId}")
                .replaceQueryParam("clientId", clientId)
                .build()
                .toUri();
        RequestEntity<Void> getUserIdByNupRequest = RequestEntity.get(requestUri).build();
        ParameterizedTypeReference<List<ClientRepresentationModel>> typeRef =
                new ParameterizedTypeReference<List<ClientRepresentationModel>>() {};
        return execute(getUserIdByNupRequest, typeRef);
    }

    @Override
    public ClientRepresentationModel getClientById(String id) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/clients/{id}")
                .buildAndExpand(id)
                .toUri();
        RequestEntity<Void> getUserIdByNupRequest = RequestEntity.get(requestUri).build();
        return execute(getUserIdByNupRequest, ClientRepresentationModel.class);
    }

    @Override
    public List<ClientRepresentationModel> getClients() throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(getBaseUrl().toString() + "/clients")
                .build()
                .toUri();
        RequestEntity<Void> getUserIdByNupRequest = RequestEntity.get(requestUri).build();
        ParameterizedTypeReference<List<ClientRepresentationModel>> typeRef =
                new ParameterizedTypeReference<List<ClientRepresentationModel>>() {};
        return execute(getUserIdByNupRequest, typeRef);
    }
}