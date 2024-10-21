package ar.com.santanderrio.obp.servicios.solicitudes.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceRequest;
import ar.com.santanderrio.obp.servicios.api.notificationsservice.entities.NotificationsServiceResponse;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoResponseDTO;
import org.apache.cxf.jaxrs.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class TransferiSueldoApiDAOImpl implements  TransferiSueldoApiDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferiSueldoApiDAOImpl.class);

    @Autowired
    private RestWebClient restWebClient;

    @Autowired
    private SesionParametros sesionParametros;

    private static final String NOMBRE_WS = "TRANSFERITUSUELDO-API";

    @Value("${TRANSFERITUSUELDO-API.TRANSFERI:/api/v1/cases}")
    private String baseUriTransferi;

    @Override
    public TransferiSueldoResponseDTO transferiSueldo(TransferiSueldoRequestDTO transferiSueldoRequestDTO) throws DAOException {
        LOGGER.info("Inicio de solicitud de Transferir Sueldo. Solicitud {}", transferiSueldoRequestDTO);
        return this.postApi(baseUriTransferi, transferiSueldoRequestDTO, TransferiSueldoResponseDTO.class);
    }

    private <R, T> R postApi(String uri, T body, Class<R> responseClass) throws DAOException {
        LOGGER.info("Llamada al uri {} body: {}", uri, body);

        try {
            WebClient transferiSueldoClient = this.configurarCliente();
            Response response = transferiSueldoClient.path(uri).post(body);
            isErrorResponseThrowGenericRestException(response);

            R dto = response.readEntity(responseClass);
            LOGGER.info("Fin llamada al uri {} response: {}", uri, dto);
            return dto;
        } catch (GenericRestException ex1) {
            throw ex1;
        } catch (Exception ex2) {
            throw new DAOException(ex2);
        }
    }

    /**
     * If response is error throw GenericRestException
     *
     * @param response
     * @throws GenericRestException
     */
    private void isErrorResponseThrowGenericRestException(Response response) throws GenericRestException {
        if (response.getStatus() != 200) {
            GenericRestResponseDto responseDto = response.readEntity(GenericRestResponseDto.class);
            throw new GenericRestException(responseDto);
        }
    }

    private WebClient configurarCliente() throws DAOException {
        String jwt = sesionParametros.getJwt();
        WebClient transferiClient = restWebClient.obtenerClienteRest(NOMBRE_WS);

        return transferiClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")
                .acceptEncoding(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
    }

}
