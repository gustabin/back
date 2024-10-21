package ar.com.santanderrio.obp.servicios.premify.dao.impl;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.premify.contracts.MemberResponse;
import ar.com.santanderrio.obp.servicios.premify.contracts.NotificacionResponse;
import org.slf4j.Logger;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.dao.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.premify.dao.PremifyDAO;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;

@Component
public class PremifyDAOImpl implements PremifyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremifyDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    private static final String NOMBRE_WS_NOTIFICATIONS = "PREMIFY-NOTIFICATIONS";
    private static final String NOMBRE_WS_USERS = "PREMIFY-USERS";

    private static final String HEADER_AUTHORIZATION = "AUTHORIZATION";

    private static final String API_VERSION = "api/v1/";
    private static final String API_VERSION2 = "api/v2/";

    /**
     * Recupera las notificaciones de un cliente.
     * @param nup del cliente loggeado
     * @return lista de notificaciones
     * @throws DAOException
     */
    @Override
    public List<NotificacionResponse> getNotifications(String nup) {
        LOGGER.info("Obteniendo notificaciones del cliente.");
        List<NotificacionResponse> respuesta = new ArrayList<NotificacionResponse>();
        try {
            WebClient premifyClient = this.configurarClientePremify(NOMBRE_WS_NOTIFICATIONS);
            premifyClient.path(API_VERSION + "customers/" + nup + "/notifications");
            premifyClient.query("origin", "ui");
            respuesta = (ArrayList<NotificacionResponse>)premifyClient.get(ArrayList.class);
        } catch (Exception e) {
            LOGGER.error("Error llamando a la API de Premify-Notifications.");
            respuesta = null;
        }
        return respuesta;
    }

    /**
     * Recupera información de un miembro superclub+.
     * @param nup del cliente loggeado
     * @return lista de notificaciones
     * @throws DAOException
     */
    @Override
    public MemberResponse getMember(String nup) {
        LOGGER.info("Obteniendo miembro superclub+.");
        MemberResponse respuesta = null;
        try {
            WebClient premifyClient = this.configurarClientePremify(NOMBRE_WS_USERS);
            premifyClient.path(API_VERSION2 + "members/" + nup);
            respuesta = premifyClient.get(MemberResponse.class);
        } catch (Exception e) {
            LOGGER.error("Error llamando a la API de users para obtener el miembro sc+.");
            respuesta = null;
        }
        return respuesta;
    }

    private WebClient configurarClientePremify(String nombreWs) throws DAOException{

        String jwt = sesionParametros.getJwt();
        WebClient premifyClient = restWebClient.obtenerClienteRest(nombreWs);

        return premifyClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_AUTHORIZATION, "Bearer " + jwt);
    }
}
