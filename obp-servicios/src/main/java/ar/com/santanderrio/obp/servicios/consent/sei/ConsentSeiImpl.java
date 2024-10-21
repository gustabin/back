package ar.com.santanderrio.obp.servicios.consent.sei;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.consent.manager.SSOConsentManagerImpl;
import ar.com.santanderrio.obp.servicios.consent.sei.view.UserConsentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsentSeiImpl implements ConsentSei {

    @Autowired
    private SSOConsentManagerImpl ssoConsentManager;

    @Override
    public Respuesta<UserConsentsResponse> getUserConsents() {
        return ssoConsentManager.getUserConsents();
    }

    @Override
    public Respuesta<Void> revokeUserConsent(String consentedClientId) {
        return ssoConsentManager.revokeUserConsent(consentedClientId);
    }
}
