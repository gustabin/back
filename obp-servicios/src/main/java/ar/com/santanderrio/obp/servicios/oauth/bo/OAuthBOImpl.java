package ar.com.santanderrio.obp.servicios.oauth.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.oauth.config.ApicAppContext;
import ar.com.santanderrio.obp.servicios.oauth.config.ThreeScaleAppContext;
import ar.com.santanderrio.obp.servicios.oauth.connector.OAuthConnector;
import ar.com.santanderrio.obp.servicios.oauth.dto.OAuthTokenRequestDTO;
import ar.com.santanderrio.obp.servicios.oauth.model.OAuthTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OAuthBOImpl implements OAuthBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthBOImpl.class);

    @Autowired
    private OAuthConnector oAuthConnector;

    @Override
    public Respuesta<OAuthTokenResponse> getAccessToken(final ApicAppContext apicAppContext,
            final OAuthTokenRequestDTO request) {
        OAuthTokenResponse tokenResponse = OAuthTokenResponse.defaultStateResponse();

        try {
            tokenResponse = oAuthConnector.getAccessToken(apicAppContext, request);
        } catch (DAOException e) {
            LOGGER.error("OAUTH: Error invocación ws.", e);
        }
        return processTokenResponse(tokenResponse);
    }

    @Override
    public Respuesta<OAuthTokenResponse> getAccessToken3scale(final ThreeScaleAppContext threeScaleAppContext, final OAuthTokenRequestDTO request) {
        OAuthTokenResponse tokenResponse = OAuthTokenResponse.defaultStateResponse();

        try {
            tokenResponse = oAuthConnector.getAccessTokenThreeScale(threeScaleAppContext, request);
        } catch (DAOException e) {
            LOGGER.error("OAUTH3SCALE: Error invocación ws.", e);
        }
        return processTokenResponse(tokenResponse);
    }

    private Respuesta<OAuthTokenResponse> processTokenResponse(OAuthTokenResponse tokenResponse){
        final Respuesta<OAuthTokenResponse> serviceResponse = new Respuesta<OAuthTokenResponse>();

        serviceResponse.setEstadoRespuesta(StringUtils.isNotBlank(tokenResponse.getError()) ? EstadoRespuesta.ERROR
                : EstadoRespuesta.OK);
        serviceResponse.setRespuesta(tokenResponse);

        return serviceResponse;
    }
}
