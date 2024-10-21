package ar.com.santanderrio.obp.servicios.oauth2.token;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2AuthorizationException;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;

public class OAuthCachedTokenProvider implements OAuthTokenProvider {
    private final OAuthV2Connector oauthV2Cache;

    public OAuthCachedTokenProvider(OAuthV2Connector oauthV2Cache) {
        this.oauthV2Cache = oauthV2Cache;
    }

    @Override
    public OAuth2AccessToken requestAccessToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException {
        Credential credential = new Credential();
        credential.setUsuario(resourceDetails.getClientId());
        credential.setPassword(resourceDetails.getClientSecret());
        try {
            OAuth2AccessToken accessToken = oauthV2Cache.getAccessToken(credential, resourceDetails.getScope(), resourceDetails.getAccessTokenUri());
            if(accessToken == null) {
                throw new OAuth2AuthorizationException("Access token could not be retrieved");
            }
            //if token is expired, force the refresh and return a new one. Cache could be out of sync and keep expired tokens
            if(accessToken.isExpired()) {
                oauthV2Cache.deleteCachedToken(credential, resourceDetails.getScope());
                accessToken = oauthV2Cache.getAccessToken(credential, resourceDetails.getScope(), resourceDetails.getAccessTokenUri());
            }
            return accessToken;
        } catch (DAOException daoException) {
            throw new OAuth2AuthorizationException("Access token for " + resourceDetails.getScope() + " could not be retrieved", daoException);
        }
    }

    @Override
    public OAuth2AccessToken requestRefreshToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException {
        return null;
    }
}
