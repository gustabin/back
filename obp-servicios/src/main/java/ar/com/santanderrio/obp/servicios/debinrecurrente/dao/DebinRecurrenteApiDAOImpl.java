package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.ProviderErrorDTO;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Component
@Qualifier("debinRecurrenteApiDAOImpl")
public class DebinRecurrenteApiDAOImpl implements  DebinRecurrenteApiDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteApiDAOImpl.class);

    /** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER. */
    private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER = "pageNumber";

    /** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE. */
    private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE = "pageSize";

    /** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT. */
    private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT = "buyerCuit";

    /** The Constant PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU. */
    private static final String PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU = "buyerCbu";

    /** The rest web client. */
    @Autowired
    private RestTemplate restTemplate;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Value("${DEBINAPI.URL}")
    private String debinApiUrl;

    @Value("${DEBINAPI.SEC_ID}")
    private String secId;

    @Value("${DEBINAPI.PATH_RECURRENCE_BUYER_LIST}")
    private String pathRecurrenceList;

    @Value("${DEBINAPI.PAGESIZE}")
    private int maxPageSize;


    /**
     * Si el servicio API respode OK, devuelvo la entity. Si el cliente no tiene recurrencias dicha lista es vacia.
     *
     * Ante un 424 del servicio ->
     * 				- Si la causa es 503 de Coelsa: exception, ya que puede que a su vez coelsa este caido o tenga problemas. No puedo continuar. Logueo problema puntual del servicio.
     * 				- Si es otro caso, puede que no este bien un dato de input. Devolvemos null, pero no es bloqueante. Es Warning.
     * Si es cualquier otro error -> exception. Puede darse 404, 503, etc. Propios de la API de recurrencia y no de Coelsa. Todos ellos son bloqueantes y no pueden continuar.
     *
     * @param cbu
     * @param
     * @param numeroPagina
     * @return
     * @throws DAOException
     */

    public BuyerRecurrenceListRequestDTO getRecurrenceList(String cbu, String formattedCuit, String numeroPagina) throws DAOException {
        LOGGER.info("Init call to DEBIN-API");
        String cuit = StringUtils.remove(formattedCuit, "-");

        BuyerRecurrenceListRequestDTO debinListDTO = null;
        try {
            ResponseEntity<BuyerRecurrenceListRequestDTO> response = restTemplate.exchange(
                    this.getRecurrenceListSearchUrl(cuit, cbu, numeroPagina, pathRecurrenceList),
                    HttpMethod.GET,
                    this.getRequest(),
                    BuyerRecurrenceListRequestDTO.class
            );
            debinListDTO = response.getBody();

        } catch (HttpStatusCodeException hscex) {
            ProviderErrorDTO errorDTO = null;
            if (org.springframework.http.HttpStatus.FAILED_DEPENDENCY.equals(hscex.getStatusCode())) {
                errorDTO = mapProviderErrorDTO(hscex.getResponseBodyAsString());
            }
            if (errorDTO != null) {
                if (errorDTO.getRootCode() == org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE.value()) {
                    String msgError = "Coelsa no disponible. Error en el servicio DEBIN API.  " + pathRecurrenceList;
                    LOGGER.error(msgError);
                    throw new DAOException(msgError);
                } else {
                    String errorCode = errorDTO.getRootDetail() != null ? errorDTO.getRootDetail().getErrors().get(0).getCode() : "";

                    String msgError = "Error" + errorDTO.getRootCode() + " " + errorDTO.getRootMessage() + " " + errorCode + "Error en el servicio DEBIN API " + pathRecurrenceList;
                    LOGGER.error(msgError);
                    throw new DAOException(msgError, errorCode);
                }

            }else  {
                LOGGER.error("Error en el servicio DEBIN API {} con errror {} ", pathRecurrenceList, hscex.getStatusCode());
                throw new DAOException("Error en el servicio DEBIN API " + pathRecurrenceList + " Error : " + hscex.getStatusCode());
            }
        } catch (Exception generalException) {
            LOGGER.error("Error en el servicio " + pathRecurrenceList, generalException);
            throw new DAOException("Error en el servicio DEBIN API " + pathRecurrenceList + " Error : " + generalException.getMessage());
        }
        return debinListDTO;

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

    private String getRecurrenceListSearchUrl(String cuit, String cbu,String nroPagina, String endpoint) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(buildUrl(endpoint));

        nullSafeParam(builder,PARAMETER_QUERY_RECURRENCES_BY_BUYER_CUIT,cuit);
        nullSafeParam(builder,PARAMETER_QUERY_RECURRENCES_BY_BUYER_CBU, cbu);
        nullSafeParam(builder,PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_SIZE, maxPageSize);
        nullSafeParam(builder,PARAMETER_QUERY_RECURRENCES_BY_BUYER_PAGE_NUMBER, nroPagina);
        return builder.toUriString();

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

    private HttpEntity<String> getRequest() throws DAOException{
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
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
