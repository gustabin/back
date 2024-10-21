package ar.com.santanderrio.obp.servicios.consent.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.consent.sei.view.UserConsentsResponse;

public interface SSOConsentManager {
    Respuesta<UserConsentsResponse> getUserConsents();
    Respuesta<Void> revokeUserConsent(String consentedClientId);
}
