package ar.com.santanderrio.obp.servicios.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSOConsentManagerContext {

    @Value("${SSO-CONSENT-MANAGER.SEC.ID}")
    private String consentManagerSecId;

    @Value("${SSO-CONSENT-MANAGER.URL}")
    private String consentManagerSSOUrl;

    @Value("${SSO-CONSENT-MANAGER.TOKEN.PATH}")
    private String consentManagerTokenPath;

    public String getConsentManagerSecId() {
        return consentManagerSecId;
    }

    public String getConsentManagerSSOUrl() {
        return consentManagerSSOUrl;
    }

    public String getConsentManagerTokenPath() {
        return consentManagerTokenPath;
    }
}
