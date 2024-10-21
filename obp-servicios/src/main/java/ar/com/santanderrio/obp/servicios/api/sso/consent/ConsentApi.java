package ar.com.santanderrio.obp.servicios.api.sso.consent;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserConsentRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;

import java.util.List;

public interface ConsentApi {
    List<UserRepresentationModel> getUserDataByNup(final String customerId) throws ApiException;
    List<UserConsentRepresentationModel> getConsentsByUserId(final String userId) throws ApiException;
    Void revokeConsentByUserId(final String userId, final String consentedClientId) throws ApiException;
}
