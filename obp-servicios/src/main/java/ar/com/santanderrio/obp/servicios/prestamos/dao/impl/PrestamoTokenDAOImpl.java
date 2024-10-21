package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.dao.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoTokenDAO;
import ar.com.santanderrio.obp.servicios.premify.dao.impl.PremifyDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.GetTokenResponse;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;

@Component
public class PrestamoTokenDAOImpl implements PrestamoTokenDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremifyDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;

    @Autowired
    private SesionParametros sesionParametros;

    @Autowired
    private SesionCliente sesionCliente;

    private static final String NOMBRE_WS = "PRESTAMO-TOKEN";

    private static final String HEADER_AUTHORIZATION = "AUTHORIZATION";

    private static final String API_VERSION = "/api";

    private static final String PATH_VALIDATE_TOKEN = "/tokens";

    @Override
    public String getToken(){
        LOGGER.info("Obteniendo token prestamos.");
        GetTokenResponse respuesta;
        try {
            WebClient premifyClient = this.configurarClientePrestamosValidator(NOMBRE_WS);
            premifyClient.path(API_VERSION + PATH_VALIDATE_TOKEN);

            respuesta = premifyClient.get(GetTokenResponse.class);
            return respuesta != null ? respuesta.token : "";

        } catch (Exception e) {
            LOGGER.error("Error llamando a la API de validador de prestamos");
            return "";
        }
    }

    private WebClient configurarClientePrestamosValidator(String nombreWs) throws DAOException{

        String jwt = sesionParametros.getJwt();
        WebClient premifyClient = restWebClient.obtenerClienteRest(nombreWs);

        return premifyClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_AUTHORIZATION, "Bearer " + jwt);
    }
}
