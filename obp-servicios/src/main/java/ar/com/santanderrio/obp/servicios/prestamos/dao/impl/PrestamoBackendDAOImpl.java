package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoBackendDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class PrestamoBackendDAOImpl implements PrestamoBackendDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoBackendDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;


    private static final String NOMBRE_WS = "PRESTAMOS-BACKEND";

    private static final String PATH_PRESTAMOS = "/loans";

    @Override
    public void cancelarPrestamo(String loanId) throws DAOException {
        LOGGER.info("Cancelamos el prestamo de id {}.", loanId);

        try {
            WebClient prestamosClient = this.configurarCliente();
            prestamosClient.path(PATH_PRESTAMOS + "/" + loanId);
            Response response = prestamosClient.delete();
            if(response.getStatus() >= 400){
                GenericRestResponseDto responseDto = response.readEntity(GenericRestResponseDto.class);
                throw new DAOException(responseDto.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }


    private WebClient configurarCliente() throws DAOException {

        WebClient prestamosClient = restWebClient.obtenerClienteRest(NOMBRE_WS);

        return prestamosClient
                .type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
    }
}
