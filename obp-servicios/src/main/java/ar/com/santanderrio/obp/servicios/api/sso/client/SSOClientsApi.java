package ar.com.santanderrio.obp.servicios.api.sso.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;

import java.util.List;

public interface SSOClientsApi {
    List<ClientRepresentationModel> getClients() throws ApiException;
    List<ClientRepresentationModel> getClientByClientId(final String clientId) throws ApiException;
    ClientRepresentationModel getClientById(String id) throws ApiException;
}
