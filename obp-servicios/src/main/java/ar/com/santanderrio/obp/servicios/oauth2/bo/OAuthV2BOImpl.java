package ar.com.santanderrio.obp.servicios.oauth2.bo;

import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;

@Component
public class OAuthV2BOImpl implements OAuthV2BO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthV2BOImpl.class);

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "OAUTHV2: error al obtener accessToken";

	/** The oAuthV2Connector. */
	@Autowired
	private OAuthV2Connector oAuthV2Connector;

	@Override
	public Respuesta<String> getAccessToken(final Credential credential, final String scope, String url) {
		Respuesta<String> respuesta = new Respuesta<String>();

		try {
			OAuth2AccessToken accessToken = oAuthV2Connector.getAccessToken(credential, scope, url);
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			respuesta.setRespuesta(accessToken.getAccessToken());
		} catch (DAOException e) {
			LOGGER.error("OAUTHV2: Error invocación ws.", e);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ERROR_MESSAGE);
			item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
			respuesta.add(item);
		}
		return respuesta;
	}

}
