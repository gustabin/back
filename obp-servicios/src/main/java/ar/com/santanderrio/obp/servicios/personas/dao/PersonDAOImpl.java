package ar.com.santanderrio.obp.servicios.personas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.personas.dto.Person;
import ar.com.santanderrio.obp.servicios.personas.dto.PersonResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;

@Component
@Qualifier("personDAOImpl")
public class PersonDAOImpl implements  PersonDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDAOImpl.class);

    /** The rest web client. */
    @Autowired
    private RestTemplate restTemplate;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    @Value("${"+ PersonApiConfig.URL+"}")
    private String personApiUrl;

    @Value("${"+ PersonApiConfig.SEC_ID+"}")
    private String secId;

    @Value("${"+ PersonApiConfig.PATH_PERSON_INFO+"}")
    private String pathPersonInfo;


    @Override
    public Person findPersonInfoByCuit ( String cuit, String source ) throws DAOException {
        String sourceParam = StringUtils.isEmpty(source)? "": "&source=" +source ;
        String url =personApiUrl + pathPersonInfo + "?" + "cuit=" + cuit + sourceParam;
        HttpEntity<String> req = new HttpEntity(getHeaders());
        ResponseEntity<PersonResponse> response = null;
        try {
            response = this.restTemplate.exchange(url, HttpMethod.GET, req, PersonResponse.class);
            //we expect a list with at least one element
            PersonResponse personResponse = response.getBody();
            if(personResponse!= null){
                List<Person> personDetailResponses = personResponse.getResults();
                if (personDetailResponses!= null && !personDetailResponses.isEmpty() ){
                    if (StringUtils.isEmpty(source) && StringUtils.isBlank(personDetailResponses.get(0).getNup())){
                        throw new  DAOException("Person without nup for cuit " );
                    }else {
                        return personDetailResponses.get(0);
                    }
                }
            }

        } catch (HttpStatusCodeException hscex) {
            LOGGER.error(hscex.getMessage(), hscex);
            throw new DAOException(hscex.getResponseBodyAsString() );
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException( String.valueOf(HttpStatus.FAILED_DEPENDENCY.value()));
        }
        return null;
    }

    private HttpHeaders getHeaders() throws DAOException{
        Credential credential = obtenerCredencial();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-key", credential.getPassword());

        return headers;
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
}
