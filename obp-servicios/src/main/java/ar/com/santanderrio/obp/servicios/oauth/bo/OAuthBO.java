package ar.com.santanderrio.obp.servicios.oauth.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.config.ThreeScaleAppContext;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;

public interface OAuthBO {

    Respuesta<OAuthTokenResponse> getAccessToken(ApicAppContext apicAppContext, OAuthTokenRequestDTO request);
    Respuesta<OAuthTokenResponse> getAccessToken3scale(ThreeScaleAppContext apicAppContext, OAuthTokenRequestDTO request);
}
