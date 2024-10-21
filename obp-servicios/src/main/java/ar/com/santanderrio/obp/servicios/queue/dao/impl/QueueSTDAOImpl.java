package ar.com.santanderrio.obp.servicios.queue.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.queue.dao.QueueSTAuthDAO;
import ar.com.santanderrio.obp.servicios.queue.dao.QueueSTDAO;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnRequest;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnResponse;

/**
 * The Class QueueSTBOImpl.
 *
 */
@Component
public class QueueSTDAOImpl implements QueueSTDAO {

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSTDAOImpl.class);

    /** The Constant HEADER_AUTHORIZATION. */
    private static final String HEADER_AUTHORIZATION = "Authorization";

    /** The Constant HEADER_X_CONFIG_ID. */
    private static final String HEADER_X_CONFIG_ID = "X-ConfigId";

    /** The Constant PATH_TURNS. */
	private static final String PATH_TURNS = "/turns/";

	/** The Constant QUEUE_ST. */
	private static final String QUEUE_ST = "QUEUE_ST";

    /** The rest web client. */
    @Autowired
    private RestWebClient restWebClient;

    /** The queue ST auth DAO. */
    @Autowired
    private QueueSTAuthDAO queueSTAuthDAO;

    /** The config ID. */
    @Value("${QUEUE_ST.CONFIG.ID}")
    private String configID;

	@Override
	public TurnResponse crearTurno(String dni) throws DAOException {
		WebClient service = obtenerCliente(PATH_TURNS);
		service.header(HEADER_X_CONFIG_ID, configID);
		TurnRequest request = new TurnRequest(new Integer(dni), null);
		TurnResponse response = service.post(request, TurnResponse.class);

		int statusCode = service.getResponse().getStatus();
		if (statusCode != Response.Status.CREATED.getStatusCode()) {
			throw new DAOException("Error al crear turno en queue-st api: " + statusCode);
		}
		return response;
	}

	@Override
	public TurnResponse verificarTurno(String turnId) throws DAOException {
		LOGGER.info("verificarTurno()");
		WebClient service = obtenerCliente(String.format(PATH_TURNS + "%s", turnId));
		TurnResponse response = service.get(TurnResponse.class);

		int statusCode = service.getResponse().getStatus();
		if (statusCode != Response.Status.OK.getStatusCode()) {
			LOGGER.info("Error al verificar turno en queue-st api: " + statusCode);
			throw new DAOException("Error al verificar turno en queue-st api");
		}
		return response;
	}

	@Override
	public void actualizarTurno(String turnId, String turnStatus) throws DAOException {
		LOGGER.info("actualizarTurno()");
		WebClient service = obtenerCliente(String.format(PATH_TURNS + "%s", turnId));
		TurnRequest request = new TurnRequest(null, turnStatus);
		Response response = service.put(request);

		int statusCode = service.getResponse().getStatus();
		if (statusCode != Response.Status.OK.getStatusCode()) {
			LOGGER.info("Error al actualizar turno en queue-st api: " + statusCode);
			if (statusCode == Response.Status.NOT_FOUND.getStatusCode() || 
					statusCode == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
				TurnResponse queueTurnResponse;
				try {
					queueTurnResponse = getQueueTurnResponse(response);
					if (queueTurnResponse != null) {
						LOGGER.info("Detalle de error: {} - {}", queueTurnResponse.getCode(),
								queueTurnResponse.getMessage());
						throw new DAOException(queueTurnResponse.getMessage());
					}
				} catch (IOException e) {
					LOGGER.info("Error al obtener respuesta de actualizacion de turno", e);
				}
			}
			throw new DAOException("Error al actualizar turno en queue-st api");
		}
	}

	/**
	 * Gets the queue turn response.
	 *
	 * @param response the response
	 * @return the queue turn response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private TurnResponse getQueueTurnResponse(Response response) throws IOException {
		Object entity = response.getEntity();
		InputStream is = InputStream.class.cast(entity);
		if (is != null) {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(is, TurnResponse.class);
		}
		return null;
	}

	/**
	 * Obtener cliente.
	 *
	 * @param path the path
	 * @return the web client
	 * @throws DAOException the DAO exception
	 */
	private WebClient obtenerCliente(String path) throws DAOException {
		WebClient service = restWebClient.obtenerClienteRest(QUEUE_ST);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.header(HEADER_AUTHORIZATION, queueSTAuthDAO.obtenerAuthToken());
		service.path(path);
		return service;
	}

}
