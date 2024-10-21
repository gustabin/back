package ar.com.santanderrio.obp.servicios.consent.cache;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.client.SSOClientsApi;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SSOClientsService {
    private static final Logger logger = LoggerFactory.getLogger(SSOClientsService.class);

    @Autowired
    private SSOClientsApi ssoClientsApi;

    @Cacheable(
            value = CacheConstants.Names.CACHE_SSO_CONSENTS_CLIENTS,
            key = "#clientId",
            unless="#result == null"
    )
    public ClientRepresentationModel getSSOClient(String clientId) {
        try {
            List<ClientRepresentationModel> clients = ssoClientsApi.getClientByClientId(clientId);
            if(!clients.isEmpty()) {
                return clients.get(0);
            }
        } catch (ApiException exception) {
            logger.info("Error retrieving sso-client details", exception);
        }
        return null;
    }
}
