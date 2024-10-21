package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.premify.dao.impl.PremifyDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoEncoladoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Component
public class PrestamoEncoladoDAOImpl implements PrestamoEncoladoDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PremifyDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;


    private static final String NOMBRE_WS = "PRESTAMO-API";

    private static final String API_VERSION = "/api/v1";

    private static final String PATH_PRESTAMOS = "/loans";

    private static final String ENQUEUE_LOAN_STATE = "Enqueue";

    @Override
    public PrestamosEncoladosEntity getPrestamosEncolados(String nup) throws DAOException {
        LOGGER.info("Obteniendo prestamos encolados para el nup {}.", nup);
        try {
            WebClient prestamosClient = this.configurarClientePrestamosEncolados();
            prestamosClient.path(API_VERSION + PATH_PRESTAMOS);

            prestamosClient.query("Nup", nup);
            prestamosClient.query("State", ENQUEUE_LOAN_STATE);

            List<PrestamoEncoladoEntity> listaPrestamos = new ArrayList<PrestamoEncoladoEntity>(prestamosClient.getCollection(PrestamoEncoladoEntity.class));

            return  new PrestamosEncoladosEntity(listaPrestamos);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public PrestamoEncoladoEntity getPrestamoEncolado(String id) throws DAOException {
        LOGGER.info("Obteniendo prestamo encolado, id: {}.", id);
        try {
            WebClient prestamosClient = this.configurarClientePrestamosEncolados();
            prestamosClient.path(API_VERSION + PATH_PRESTAMOS + "/" + id);
            return prestamosClient.get(PrestamoEncoladoEntity.class);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    private WebClient configurarClientePrestamosEncolados() throws DAOException {

        WebClient prestamosClient = restWebClient.obtenerClienteRest(NOMBRE_WS);

        return prestamosClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }
}
