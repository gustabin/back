package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.ProviderErrorDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;

import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DebinRecurrenteApiDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteApiDAOTest.class);
    private  static final  String PAGE_NUMBER = "3";
    private  static final int PAGE_SIZE = 8;

    private static final String DEBIN_URL ="http://miurl";
    private static final String PATH_RECURRENCE_LIST ="/recurrences/recurrencesByBuyer";

    private static final String DEBIN_USER  ="Vale";
    private static final String DEBIN_PWD  ="1231";

    private static final String BUYER_CUIT = "27473501231";
    private static final String BUYER_CBU = "0720000701560000000178";

    @InjectMocks
    private DebinRecurrenteApiDAO debinApiDAO = new DebinRecurrenteApiDAOImpl();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(debinApiDAO, "debinApiUrl", DEBIN_URL);
        ReflectionTestUtils.setField(debinApiDAO, "secId", "2222");
        ReflectionTestUtils.setField(debinApiDAO, "maxPageSize", 8);

        ReflectionTestUtils.setField(debinApiDAO, "pathRecurrenceList",PATH_RECURRENCE_LIST);
        Credential credential = new Credential();
        credential.setUsuario(DEBIN_USER);
        credential.setPassword(DEBIN_PWD);
        doReturn(credential).when(credentialSecurityFactory).getCredentialById("2222");

    }

    @Test
    public void getRecurrenceListOK() throws DAOException {
        //MOCK
        ResponseEntity<BuyerRecurrenceListRequestDTO> response = new ResponseEntity<BuyerRecurrenceListRequestDTO>( DebinRecurrenteApiMock.getDefaultRecurrenceListDTO(), HttpStatus.OK);
        String url = DEBIN_URL +
                PATH_RECURRENCE_LIST + "?" +
                "buyerCuit=" + BUYER_CUIT + "&" +
                "buyerCbu=" + BUYER_CBU+ "&" +
                "pageSize=" + PAGE_SIZE + "&" +
                "pageNumber=" + PAGE_NUMBER
          ;
        doReturn(response).when(restTemplate).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                BuyerRecurrenceListRequestDTO.class
        );

        //CALL METHOD
        BuyerRecurrenceListRequestDTO recurrenceListDTO =  debinApiDAO.getRecurrenceList(BUYER_CBU, BUYER_CUIT, PAGE_NUMBER);

        //ASSERTS
        Assert.assertEquals(3, recurrenceListDTO.getRecurrences().size());
        Assert.assertEquals(Integer.valueOf(4), recurrenceListDTO.getTotalPages());
        Assert.assertEquals("PENDING",recurrenceListDTO.getRecurrences().get(2).getStatus());
        verify(restTemplate, times(1)).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                BuyerRecurrenceListRequestDTO.class
        );

    }


    @Test
    public void getRecurrenceBuyerListServiceUnavailable() throws DAOException {

        //MOCK
        String url = DEBIN_URL +
                PATH_RECURRENCE_LIST + "?" +
                "buyerCuit=" + BUYER_CUIT + "&" +
                "buyerCbu=" + BUYER_CBU+ "&" +
                "pageSize=" + PAGE_SIZE + "&" +
                "pageNumber=" + PAGE_NUMBER
                ;
        HttpStatusCodeException exception = new HttpStatusCodeException(HttpStatus.FAILED_DEPENDENCY) {

            @Override
            public String getResponseBodyAsString() {
                ProviderErrorDTO providerErrorDTO = DebinRecurrenteApiMock.getDefaultProviderErrorDTO(HttpStatus.SERVICE_UNAVAILABLE.value());
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = "";
                try{
                    responseBody = objectMapper.writeValueAsString(providerErrorDTO);
                }catch(Exception e){
                    LOGGER.warn("could not serialize");
                }
                return responseBody;
            }
        };

        doThrow(exception).when(restTemplate).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                BuyerRecurrenceListRequestDTO.class
        );

        //CALL METHOD
        try {
            debinApiDAO.getRecurrenceList(BUYER_CBU, BUYER_CUIT, PAGE_NUMBER);
        }catch(DAOException e) {
            //ASSERTS
            String msgError = "Coelsa no disponible. Error en el servicio DEBIN API.  /recurrences/recurrencesByBuyer";
            Assert.assertEquals(msgError, e.getMessage());
        }

    }

    @Test
    public void getRecurrenceBuyerListErrorCode() throws DAOException {
        //MOCK
        String url = DEBIN_URL +
                PATH_RECURRENCE_LIST + "?" +
                "buyerCuit=" + BUYER_CUIT + "&" +
                "buyerCbu=" + BUYER_CBU+ "&" +
                "pageSize=" + PAGE_SIZE + "&" +
                "pageNumber=" + PAGE_NUMBER
                ;
        HttpStatusCodeException exception = new HttpStatusCodeException(HttpStatus.FAILED_DEPENDENCY) {

            @Override
            public String getResponseBodyAsString() {
                ProviderErrorDTO providerErrorDTO = DebinRecurrenteApiMock.getDefaultProviderErrorDTO(HttpStatus.BAD_REQUEST.value());
                ObjectMapper objectMapper = new ObjectMapper();
                String responseBody = "";
                try{
                    responseBody = objectMapper.writeValueAsString(providerErrorDTO);
                }catch(Exception e){
                    LOGGER.warn("could not serialize");
                }
                return responseBody;
            }
        };

        doThrow(exception).when(restTemplate).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                BuyerRecurrenceListRequestDTO.class
        );

        //CALL METHOD
        try {
            BuyerRecurrenceListRequestDTO debinListDTO = debinApiDAO.getRecurrenceList(BUYER_CBU, BUYER_CUIT, PAGE_NUMBER);
        }catch(DAOException e) {
            //ASSERTS
            Assert.assertEquals("C0-5198", e.getErrorCode());
        }

    }


    private HttpEntity<String> getRequest() {
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        try {
            String auth = this.DEBIN_USER + ":" + this.DEBIN_PWD;
            String encodedAuth = DatatypeConverter.printBase64Binary(auth.getBytes("UTF-8"));
            headers.set("Authorization", "Basic " + encodedAuth);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("No se pudo encodear a base 64 las credenciales de credit-cards-api.", e);
        }
        return new HttpEntity<String>("parameters",headers);
    }
}
