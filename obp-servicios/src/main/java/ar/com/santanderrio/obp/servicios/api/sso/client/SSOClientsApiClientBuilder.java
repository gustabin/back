package ar.com.santanderrio.obp.servicios.api.sso.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.sso.SSOApiBuilderCommons;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.springframework.util.Assert;

import java.net.URI;

public class SSOClientsApiClientBuilder {
    private final ApiConfig ssoApiConfig;

    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;

    private SSOClientsApiClientBuilder(ApiConfig ssoApiConfig) {
        this.ssoApiConfig = ssoApiConfig;
    }

    public static SSOClientsApiClientBuilder builder(ApiConfig ssoApiConfig) {
        return new SSOClientsApiClientBuilder(ssoApiConfig);
    }

    public SSOClientsApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public SSOClientsApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public SSOClientsApiClient build() {
        Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
        Assert.notNull(ssoApiConfig, "sso api config missing");
        Assert.notNull(resourceDetails, "sso api resource details missing");
        return new SSOClientsApiClient(
                URI.create(ssoApiConfig.getBasePath()),
                SSOApiBuilderCommons.buildRestTemplate(oauthTokenProvider, resourceDetails)
        );
    }
}
