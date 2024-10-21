package ar.com.santanderrio.obp.servicios.oauth2.token;

import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2AuthorizationException;

public interface OAuthTokenProvider {
    OAuth2AccessToken requestAccessToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException;
    OAuth2AccessToken requestRefreshToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException;
}
