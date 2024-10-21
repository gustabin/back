package ar.com.santanderrio.obp.servicios.api.sso.consent;

import ar.com.santanderrio.obp.servicios.api.sso.SSOApiBuilderCommons;
import ar.com.santanderrio.obp.servicios.api.sso.SSOApiConfig;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.springframework.util.Assert;

import java.net.URI;

public class ConsentApiClientBuilder {
    private final SSOApiConfig ssoApiConfig;
    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;

    private ConsentApiClientBuilder(SSOApiConfig ssoApiConfig) {
        this.ssoApiConfig = ssoApiConfig;
    }

    public static ConsentApiClientBuilder builder(SSOApiConfig ssoApiConfig) {
        return new ConsentApiClientBuilder(ssoApiConfig);
    }

    public ConsentApiClientBuilder withResourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public ConsentApiClientBuilder withOAuthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public ConsentApiClient build() {
        Assert.notNull(oauthTokenProvider, "oauthTokenProvider missing");
        Assert.notNull(ssoApiConfig, "sso api config missing");
        Assert.notNull(resourceDetails, "sso api resource details missing");
        return new ConsentApiClient(
                URI.create(ssoApiConfig.getBasePath()),
                URI.create(ssoApiConfig.getPcpBasePath()),
                SSOApiBuilderCommons.buildRestTemplate(oauthTokenProvider, resourceDetails)
        );
    }
}
