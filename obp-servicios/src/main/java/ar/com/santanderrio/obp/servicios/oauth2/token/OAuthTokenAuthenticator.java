package ar.com.santanderrio.obp.servicios.oauth2.token;

import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import org.springframework.http.client.ClientHttpRequest;

public interface OAuthTokenAuthenticator {
    void authenticate(OAuth2ResourceDetails resourceDetails, OAuth2AccessToken accessToken, ClientHttpRequest requests);
}
