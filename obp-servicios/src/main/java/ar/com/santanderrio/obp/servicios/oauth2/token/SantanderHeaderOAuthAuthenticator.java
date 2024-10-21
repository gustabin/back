package ar.com.santanderrio.obp.servicios.oauth2.token;

import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import org.springframework.http.client.ClientHttpRequest;

public class SantanderHeaderOAuthAuthenticator implements OAuthTokenAuthenticator {

    @Override
    public void authenticate(OAuth2ResourceDetails resourceDetails, OAuth2AccessToken accessToken, ClientHttpRequest request) {
        request.getHeaders().set(resourceDetails.getAuthorizationHeaderName(), String.format("Bearer %s", accessToken.getAccessToken()));
    }
}
