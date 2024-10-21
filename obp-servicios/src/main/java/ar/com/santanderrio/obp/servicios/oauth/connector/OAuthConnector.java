package ar.com.santanderrio.obp.servicios.oauth.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.config.ThreeScaleAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;

public interface OAuthConnector {

    OAuthTokenResponse getAccessToken(final ApicAppContext context, final OAuthTokenRequestDTO request)
            throws DAOException;

    OAuthTokenResponse getAccessTokenThreeScale(final ThreeScaleAppContext context, final OAuthTokenRequestDTO request)
            throws DAOException;
}
