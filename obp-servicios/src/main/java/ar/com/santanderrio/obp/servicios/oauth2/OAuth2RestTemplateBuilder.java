package ar.com.santanderrio.obp.servicios.oauth2;

import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import org.springframework.util.Assert;

public class OAuth2RestTemplateBuilder {
    private OAuth2ResourceDetails resourceDetails;
    private OAuthTokenProvider oauthTokenProvider;

    public static OAuth2RestTemplateBuilder builder() {
        return new OAuth2RestTemplateBuilder();
    }

    public OAuth2RestTemplateBuilder resourceDetails(OAuth2ResourceDetails resourceDetails) {
        this.resourceDetails = resourceDetails;
        return this;
    }

    public OAuth2RestTemplateBuilder oauthTokenProvider(OAuthTokenProvider oauthTokenProvider) {
        this.oauthTokenProvider = oauthTokenProvider;
        return this;
    }

    public OAuth2RestTemplate build() {
        Assert.notNull(resourceDetails, "customers api resourceDetails missing");
        Assert.notNull(oauthTokenProvider, "customers oauthTokenProvider missing");
        return new OAuth2RestTemplate(resourceDetails, oauthTokenProvider);
    }
}
