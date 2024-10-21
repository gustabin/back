package ar.com.santanderrio.obp.servicios.ws;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.sso.SSOApiConfig;
import ar.com.santanderrio.obp.servicios.api.sso.client.SSOClientsApi;
import ar.com.santanderrio.obp.servicios.api.sso.client.SSOClientsApiClientBuilder;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApiClientBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.ClientCredentialsResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthInternalCacheTokenProvider;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

@Configuration
public class SSOConsentApiClientConfig {
    private final OAuthTokenProvider tokenProvider = new OAuthInternalCacheTokenProvider();
    private OAuth2ResourceDetails resourceDetails;

    @Autowired
    @Qualifier("ssoApiConfig")
    private SSOApiConfig ssoApiConfig;

    @Autowired
    private SSOConsentManagerContext ssoAuthContext;

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @PostConstruct
    public void init() {
        this.resourceDetails = buildOAuthResourceDetails(ssoApiConfig, ssoAuthContext);
    }

    @Bean
    public ConsentApi consentApi() {
        ConsentApiClientBuilder builder = ConsentApiClientBuilder.builder(ssoApiConfig)
                .withResourceDetails(resourceDetails)
                .withOAuthTokenProvider(tokenProvider);
        return builder.build();
    }

    @Bean
    public SSOClientsApi ssoClientsApi() {
        SSOClientsApiClientBuilder builder = SSOClientsApiClientBuilder.builder(ssoApiConfig)
                .withResourceDetails(resourceDetails)
                .withOAuthTokenProvider(tokenProvider);
        return builder.build();
    }

    private OAuth2ResourceDetails buildOAuthResourceDetails(
            final SSOApiConfig consentApiConfig,
            final SSOConsentManagerContext ssoAuthContext) {
        Credential cred = getDBCredentials(ssoAuthContext.getConsentManagerSecId());
        String authUrl = ssoAuthContext.getConsentManagerSSOUrl() + ssoAuthContext.getConsentManagerTokenPath();

        ClientCredentialsResourceDetails consentOAuthContext = new ClientCredentialsResourceDetails();
        consentOAuthContext.setId("consent-API");
        consentOAuthContext.setAccessTokenUri(authUrl);
        consentOAuthContext.setScope(consentApiConfig.getScope());
        consentOAuthContext.setClientId(cred.getUsuario());
        consentOAuthContext.setClientSecret(cred.getPassword());
        return consentOAuthContext;
    }

    private Credential getDBCredentials(String securityId) {
        try {
            return credentialSecurityFactory.getCredentialById(securityId);
        } catch (SQLException ex) {
            throw new IllegalStateException("Error at fetching sso-consent-manager securityId credentials");
        }
    }
}
