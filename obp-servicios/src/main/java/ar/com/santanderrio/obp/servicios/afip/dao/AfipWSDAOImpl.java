package ar.com.santanderrio.obp.servicios.afip.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.afip.dto.TieneDeudaRequestDto;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;

;

@Component
@Qualifier("afipWSDAOImpl")
public class AfipWSDAOImpl extends IatxBaseDAO implements AfipWSDAO {

    /** The Constant LOGGER. */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AfipWSDAOImpl.class);

    /* Tags de respuesta XML */
    private static final String TAG_PREFIX = "ns.:";
    private static final String OPEN_TAG = "<tieneDeuda>";
    private static final String CLOSE_TAG = "</tieneDeuda>";

    /* Estados de deuda */
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    /** The client. */
    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Value("${AFIP.WS.URL}")
    private String url;

    @Value("${AFIP.WS.IDSEGURIDAD}")
    private String idSec;

    /** The Constant CANAL. */
    protected static final String CANAL = "41";

    /**
     * Consulta tiene deuda previsional
     *
     * @param request the request
     * @return the response boolean
     * @throws DAOException the DAO exception
     */
    @Override
    public Boolean consultaTieneDeuda(String cuit) throws DAOException {
        try {
            LOGGER.info("consultando estado de deuda previsional del CUIT {}", cuit);
            HttpEntity<String> requestBody = new HttpEntity<String>(createRequest(cuit).toXmlString(), crearHeaderWS());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestBody, String.class);
            Boolean tieneDeuda = parsearRespuesta(response.getBody());
            LOGGER.info("respuesta estado deuda previsional: {}", tieneDeuda);
            return tieneDeuda;
        } catch (HttpStatusCodeException e) {
            LOGGER.error("error al invocar servicio afip. httpStatus: {}", e.getStatusCode());
            throw new DAOException(e);
        }
    }

    private TieneDeudaRequestDto createRequest(String cuit) throws DAOException {
        Credential credential = obtenerCredenciales();

        TieneDeudaRequestDto request = new TieneDeudaRequestDto();
        request.setCanal(CANAL);
        request.setCuit(cuit.replaceAll("-", ""));
        request.setUser(credential.getUsuario());
        request.setPassword(credential.getPassword());

        return request;
    }

    private Credential obtenerCredenciales() throws DAOException {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(idSec);
            if(credential == null) {
                throw new DAOException("Error al obtener credenciales");
            }
            return credential;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener credenciales");
        }
    }
    private Boolean parsearRespuesta(String response) throws DAOException {
        String noPrefixResponse = response.replaceAll(TAG_PREFIX, "");
        String tieneDeuda = noPrefixResponse.substring(noPrefixResponse.indexOf(OPEN_TAG) + OPEN_TAG.length(), noPrefixResponse.indexOf(CLOSE_TAG));
        if (!tieneDeuda.equals(TRUE) && !tieneDeuda.equals(FALSE)) {
            LOGGER.error("error en el parseo de respuesta: {}", response);
            throw new DAOException("Error al parsear respuesta XML de AFIP");
        }
        return tieneDeuda.equals(TRUE);
    }

    private HttpHeaders crearHeaderWS() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("SOAPAction", "");
        return headers;
    }
}
