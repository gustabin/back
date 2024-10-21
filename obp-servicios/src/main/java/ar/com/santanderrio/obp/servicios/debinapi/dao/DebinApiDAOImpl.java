package ar.com.santanderrio.obp.servicios.debinapi.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListRequest;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinType;
import ar.com.santanderrio.obp.servicios.debinapi.dto.ProviderErrorDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Component
@Qualifier("debinApiDAOImpl")
public class DebinApiDAOImpl implements DebinApiDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinApiDAOImpl.class);

    /** The rest web client. */
    @Autowired
    private RestTemplate restTemplate;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Value("${"+DebinApiConfig.URL+"}")
    private String debinApiUrl;

    @Value("${"+ DebinApiConfig.SEC_ID+"}")
    private String secId;

    @Value("${"+ DebinApiConfig.PATH_DEBIN_LIST+"}")
    private String pathDebinList;

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

    @Override
    public DebinListDTO getDebinList(DebinListRequest debinListRequest) throws DAOException {
        LOGGER.info("Init call to DEBIN-API");
        DebinListDTO debinListDTO = null;
        try {
            ResponseEntity<DebinListDTO> response = restTemplate.exchange(
                    this.getDebinListSearchUrl(debinListRequest, pathDebinList),
                    HttpMethod.GET,
                    this.getRequest(),
                    DebinListDTO.class
            );
            debinListDTO = response.getBody();

        } catch (HttpStatusCodeException hscex) {
            ProviderErrorDTO errorDTO = null;
            if (HttpStatus.FAILED_DEPENDENCY.equals(hscex.getStatusCode())) {
                 errorDTO = mapProviderErrorDTO(hscex.getResponseBodyAsString());
            }
            if (errorDTO != null) {
                    if (errorDTO.getRootCode() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                        String msgError = "Coelsa no disponible. Error en el servicio DEBIN API.  " + pathDebinList;
                        LOGGER.error(msgError);
                        throw new DAOException(msgError);
                    } else {
                        String errorCode = errorDTO.getRootDetail() != null ? errorDTO.getRootDetail().getErrors().get(0).getCode() : "";

                        String msgError = "Error" + errorDTO.getRootCode() + " " + errorDTO.getRootMessage() + " " + errorCode + "Error en el servicio DEBIN API " + DebinApiConfig.PATH_DEBIN_LIST;
                        LOGGER.error(msgError);
                        throw new DAOException(msgError, errorCode);
                    }

            }else  {
                LOGGER.error("Error en el servicio DEBIN API {} con errror {} ", pathDebinList, hscex.getStatusCode());
                throw new DAOException("Error en el servicio DEBIN API " + pathDebinList + " Error : " + hscex.getStatusCode());
            }
        } catch (Exception generalException) {
            LOGGER.error("Error en el servicio " + pathDebinList, generalException);
            throw new DAOException("Error en el servicio DEBIN API " + pathDebinList + " Error : " + generalException.getMessage());
        }
        return debinListDTO;

    }


    private HttpEntity<String> getRequest() throws DAOException{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Credential credential = obtenerCredencial();
        try {
            String auth = credential.getUsuario() + ":" + credential.getPassword();
            String encodedAuth = DatatypeConverter.printBase64Binary(auth.getBytes("UTF-8"));
            headers.set("Authorization", "Basic " + encodedAuth);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("No se pudo encodear a base 64 las credenciales de debinApi.", e);
        }
        return new HttpEntity<String>("parameters",headers);
    }


    private String buildUrl(String endpoint) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(debinApiUrl)
                .append(endpoint);
        return urlBuilder.toString();
    }


    private UriComponentsBuilder nullSafeParam(UriComponentsBuilder builder, String paramName, Object paramValue) {
        return paramValue != null ? builder.queryParam(paramName, paramValue.toString()) : builder;
    }

    private String getDebinListSearchUrl( DebinListRequest debinListRequest, String endpoint) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildUrl(endpoint));

        nullSafeParam(builder,"mode", debinListRequest.getMode());
        nullSafeParam(builder,"buyerCbu", debinListRequest.getBuyerCbu());
        nullSafeParam(builder,"buyerCuit", debinListRequest.getBuyerCuit());
        nullSafeParam(builder,"sellerCbu", debinListRequest.getSellerCbu());
        nullSafeParam(builder,"sellerCuit", debinListRequest.getSellerCuit());
        nullSafeParam(builder,"creationFrom", debinListRequest.getCreationFrom());
        nullSafeParam(builder,"creationTo", debinListRequest.getCreationTo());
        nullSafeParam(builder,"pageNumber", debinListRequest.getPageNumber());
        nullSafeParam(builder,"pageSize", debinListRequest.getPageSize());
        nullSafeParam(builder,"status", debinListRequest.getStatus());
        if (debinListRequest.getTypes() != null) {
            for (DebinType debinType : debinListRequest.getTypes()) {
                nullSafeParam(builder, "type", debinType);

            }
        }
        nullSafeParam(builder, "clientIp", debinListRequest.getClientIp());
        nullSafeParam(builder,"recurrenceId", debinListRequest.getRecurrenceId());
        return builder.toUriString();

    }

    private ProviderErrorDTO mapProviderErrorDTO (String bodyStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProviderErrorDTO errorDTO = null;
        try {
            errorDTO = objectMapper.readValue(bodyStr, ProviderErrorDTO.class);
        } catch (Exception e) {
            LOGGER.warn(" No se pudo mapear el error {} a ProviderErrorDTO", bodyStr);
        }
        return errorDTO;
    }

}
