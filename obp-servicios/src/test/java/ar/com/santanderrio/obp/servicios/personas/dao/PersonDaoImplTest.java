package ar.com.santanderrio.obp.servicios.personas.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.personas.dto.Person;
import ar.com.santanderrio.obp.servicios.personas.dto.PersonResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonDaoImplTest {
    private static final String PERSON_URL ="http://unaurlperson";
    private static final String PATH_PERSON_INFO ="/persons";

    private static final String PERSON_APIKEY  ="1231";


    @InjectMocks
    private PersonDAO personApiDAO = new PersonDAOImpl();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(personApiDAO, "personApiUrl", PERSON_URL);
        ReflectionTestUtils.setField(personApiDAO, "secId", "2222");

        ReflectionTestUtils.setField(personApiDAO, "pathPersonInfo",PATH_PERSON_INFO);
        Credential credential = new Credential();
        credential.setUsuario(PERSON_APIKEY);
        credential.setPassword(PERSON_APIKEY);
        doReturn(credential).when(credentialSecurityFactory).getCredentialById("2222");

    }

    @Test
    public void shouldGetPersonCompany() throws Exception {
        String mockJsonResponse = "{\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"documentType\": \"CUIT\",\n" +
                "            \"documentNumber\": \"34533771795\",\n" +
                "            \"nup\": \"\",\n" +
                "            \"personType\": \"JURIDICAL_PERSON\",\n" +
                "            \"companyName\": \"ASOCIACION DE TRABAJADORES DE LA INDUSTR\",\n" +
                "            \"birthDate\": \"1901-01-01\",\n" +
                "            \"condition\": \"NON_CUSTOMER\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ResponseEntity<PersonResponse> response = getMockResponse(mockJsonResponse, PersonResponse.class);

        String url = "http://unaurlperson/persons?cuit=34533771795&source=bcra";
        HttpEntity<String> req = new HttpEntity(getHeaders());
        doReturn(response).when(this.restTemplate).exchange(url, HttpMethod.GET, req, PersonResponse.class);

        Person result = personApiDAO.findPersonInfoByCuit("34533771795", "bcra");

        Assert.assertEquals("CUIT", result.getDocumentType());
        Assert.assertEquals("34533771795", result.getDocumentNumber());
        Assert.assertEquals("JURIDICAL_PERSON", result.getPersonType());
        Assert.assertEquals("ASOCIACION DE TRABAJADORES DE LA INDUSTR", result.getCompanyName());
        Assert.assertNull(result.getFirstName());
        Assert.assertNull(result.getLastName());
        Assert.assertEquals("1901-01-01", result.getBirthDate());
        Assert.assertEquals("NON_CUSTOMER", result.getCondition());

        verify(this.restTemplate, times(1)).exchange(url, HttpMethod.GET, req, PersonResponse.class);

    }

    @Test
    public void shouldGetPersonPhysical() throws Exception {
        String jsonMockResponse ="{\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"documentType\": \"DNI\",\n" +
                "            \"documentNumber\": \"00030926543\",\n" +
                "            \"nup\": \"\",\n" +
                "            \"personType\": \"HUMAN_PERSON\",\n" +
                "            \"gender\": \"MALE\",\n" +
                "            \"lastName\": \"SANCHEZ\",\n" +
                "            \"firstName\": \"JUAN CRUZ\",\n" +
                "            \"birthDate\": \"1984-04-16\",\n" +
                "            \"condition\": \"NON_CUSTOMER\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ResponseEntity<PersonResponse> response = getMockResponse(jsonMockResponse, PersonResponse.class);

        String url = "http://unaurlperson/persons?cuit=20309265433&source=bcra";
        HttpEntity<String> req = new HttpEntity(getHeaders());
        doReturn(response).when(this.restTemplate).exchange(url, HttpMethod.GET, req, PersonResponse.class);

        Person result = personApiDAO.findPersonInfoByCuit("20309265433", "bcra");
        Assert.assertEquals("DNI", result.getDocumentType());
        Assert.assertEquals("00030926543", result.getDocumentNumber());
        Assert.assertEquals("HUMAN_PERSON", result.getPersonType());
        Assert.assertNull(result.getCompanyName());
        Assert.assertEquals("JUAN CRUZ", result.getFirstName());
        Assert.assertEquals("SANCHEZ", result.getLastName());
        Assert.assertEquals("1984-04-16", result.getBirthDate());
        Assert.assertEquals("NON_CUSTOMER", result.getCondition());

        verify(this.restTemplate, times(1)).exchange(url, HttpMethod.GET, req, PersonResponse.class);

    }

    @Test
    public void shouldFail() throws Exception {
        HttpEntity<String> req = new HttpEntity(getHeaders());
        String jsonResponse = "{\n" +
                "    \"error\": {\n" +
                "        \"errorCode\": 10099906,\n" +
                "        \"errorSystem\": \"CCO\",\n" +
                "        \"messages\": [\n" +
                "            \"Servicio Transaccional inactivo para este canal\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        HttpStatusCodeException exception = new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "",
                jsonResponse.getBytes(), Charset.defaultCharset()) {

        };
        // MOCK restTemplate
        String url = "http://unaurlperson/persons?cuit=34533771795&source=bcra";
        doThrow(exception).when(this.restTemplate).exchange(url, HttpMethod.GET, req, PersonResponse.class);

        try {
            personApiDAO.findPersonInfoByCuit("34533771795", "bcra");
        } catch (DAOException e) {
            Assert.assertEquals("{\n" +
                    "    \"error\": {\n" +
                    "        \"errorCode\": 10099906,\n" +
                    "        \"errorSystem\": \"CCO\",\n" +
                    "        \"messages\": [\n" +
                    "            \"Servicio Transaccional inactivo para este canal\"\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}", e.getMessage());


        }
    }

    private <T> ResponseEntity<T> getMockResponse(String jsonResponse, Class<T> responseType) throws Exception {
        T mockResponse = objectMapper.readValue(jsonResponse, responseType);
        return new ResponseEntity(mockResponse, HttpStatus.OK);

    }
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-key",PERSON_APIKEY);

        return headers;
    }


}
