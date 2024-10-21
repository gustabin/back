package ar.com.santanderrio.obp.servicios.queue.dao.impl;

import java.sql.SQLException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.queue.dao.QueueSTAuthDAO;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnLoginRequest;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnLoginResponse;

/**
 * The Class QueueSTAuthDAOImpl.
 */
@Component
public class QueueSTAuthDAOImpl implements QueueSTAuthDAO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSTDAOImpl.class);

    /** The Constant PATH_LOGIN. */
    private static final String PATH_LOGIN = "/login";

	/** The Constant QUEUE_ST. */
	private static final String QUEUE_ST = "QUEUE_ST";

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

	/** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The config ID. */
    @Value("${QUEUE_ST.CONFIG.ID}")
    private String configID;

    /** The sec id. */
    @Value("${QUEUE_ST.SEC.ID}")
    private String secId;

	/**
	 * Obtener auth token.
	 *
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_QUEUE_ST_TOKEN })
	@Override
	public String obtenerAuthToken() throws DAOException {
		Credential credential = obtenerCredencial();
		String authTkn = auth(credential.getUsuario(), credential.getPassword());
		if (authTkn != null) {
			return authTkn;
		} else {
			throw new DAOException("No es posible autenticarse en queue-st api.");
		}
	}

	/**
	 * Obtener credencial.
	 *
	 * @return the credential
	 * @throws DAOException the DAO exception
	 */
	private Credential obtenerCredencial() throws DAOException {
		Credential credential;
		try {
			credential = credentialSecurityFactory.getCredentialById(secId);
		} catch (SQLException e) {
			throw new DAOException("Error al obtener credenciales");
		}
		return credential;
	}

	/**
	 * Auth.
	 *
	 * @param applicationName the application name
	 * @param password the password
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	private String auth(String applicationName, String password) throws DAOException {
		try {
			WebClient service = restWebClient.obtenerClienteRest(QUEUE_ST);
			service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
			service.path(PATH_LOGIN);
			TurnLoginRequest request = new TurnLoginRequest(applicationName, password);
			TurnLoginResponse response = service.post(request, TurnLoginResponse.class);

			int statusCode = service.getResponse().getStatus();
			if (statusCode != Response.Status.OK.getStatusCode()) {
				throw new DAOException("Error de autenticacion en queue-st api");
			}
			return response.getToken();
		} catch (RuntimeException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Limpiar cache queue ST token.
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_QUEUE_ST_TOKEN, allEntries = true)
	@Override
	public void limpiarCacheQueueSTToken() {
		LOGGER.info("Se limpia la cache de {}", CacheConstants.Names.CACHE_QUEUE_ST_TOKEN);
	}

}
