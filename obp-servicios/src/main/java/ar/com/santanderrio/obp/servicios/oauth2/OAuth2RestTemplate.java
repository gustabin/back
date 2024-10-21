package ar.com.santanderrio.obp.servicios.oauth2;

import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2AuthorizationException;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

public class OAuth2RestTemplate extends RestTemplate {

    private final OAuth2ResourceDetails resourceDetails;
    private final OAuthTokenProvider oauthTokenProvider;

    protected OAuth2RestTemplate(OAuth2ResourceDetails resourceDetails, OAuthTokenProvider oauthTokenProvider) {
        super();
        this.resourceDetails = resourceDetails;
        this.oauthTokenProvider = oauthTokenProvider;
    }

    @Override
    protected ClientHttpRequest createRequest(URI url, HttpMethod method) throws IOException {
        //TODO: cache this token for easier access
        OAuth2AccessToken accessToken = getAccessToken();
        ClientHttpRequest request = super.createRequest(url, method);
        //TODO: Add support for other authorization schemas with OAuthTokenAuthenticator or a type of enhancer for url auth
        request.getHeaders().set(APIcConfigConstants.AUTH, String.format("Bearer %s", accessToken.getAccessToken()));
        return request;
    }

    private OAuth2AccessToken getAccessToken() {
        OAuth2AccessToken accessToken = oauthTokenProvider.requestAccessToken(resourceDetails);
        if(accessToken.isExpired()) {
            accessToken = oauthTokenProvider.requestRefreshToken(resourceDetails);
        }
        if(accessToken == null) {
            throw new OAuth2AuthorizationException("Access token can not be null");
        }
        return accessToken;
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
              ResponseExtractor<T> responseExtractor) throws RestClientException {
        try {
            return super.doExecute(url, method, requestCallback, responseExtractor);
        } catch (OAuth2AuthorizationException oAuth2AuthorizationException) {
            throw new ResourceAccessException("Could not authorize request");
        }
    }
}
