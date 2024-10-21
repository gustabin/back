package ar.com.santanderrio.obp.servicios.oauth2.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;

public interface OAuthV2Connector {

	OAuth2AccessToken getAccessToken(Credential credential, String scope, String url) throws DAOException;

	void vaciarCacheOAuthV2();

	void deleteCachedToken(Credential credential, String scope);
}
