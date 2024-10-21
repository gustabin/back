package ar.com.santanderrio.obp.servicios.oauth2.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;

public interface OAuthV2BO {

    Respuesta<String> getAccessToken(Credential credential, String scope, String url);
}
