package ar.com.santanderrio.obp.servicios.debinapi.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiDAO;
import ar.com.santanderrio.obp.servicios.debinapi.dao.DebinApiDAOImpl;
import ar.com.santanderrio.obp.servicios.debinapi.dto.*;
import ar.com.santanderrio.obp.servicios.debinapi.mock.DebinApiMock;

import org.codehaus.jackson.map.ObjectMapper;
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

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DebinApiDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DebinApiDAOTest.class);

    private  static final String SELLER_CUIT = "30500010912";
    private  static final  String SELLER_CBU = "0110599540000066934930";
    private  static final  String BUYER_CUIT = "23302005044";
    private  static final  String BUYER_CBU = "0720009088000036828504";
    private  static final  String CLIENT_IP = "180.250.241.61";
    private  static final  String PAGE_SIZE = "8";
    private  static final  String CREATION_TO = "2020-12-18T14:35:50.6895343-03:00";
    private  static final  DebinListMode SELLER_LIST_MODE = DebinListMode.SELLER;
    private  static final  DebinListMode BUYER_LIST_MODE = DebinListMode.BUYER;

    private  static final  DebinStatus DEBIN_STATUS = DebinStatus.CREDITED;
    private  static final  String PAGE_NUMBER = "3";

    private static final String DEBIN_URL ="http://miurl";
    private static final String PATH_DEBIN_LIST ="/debins";

    private static final String DEBIN_USER  ="Vale";
    private static final String DEBIN_PWD  ="1231";


    @InjectMocks
    private DebinApiDAO debinApiDAO = new DebinApiDAOImpl();

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(debinApiDAO, "debinApiUrl", DEBIN_URL);
        ReflectionTestUtils.setField(debinApiDAO, "secId", "2222");

        ReflectionTestUtils.setField(debinApiDAO, "pathDebinList",PATH_DEBIN_LIST);
        Credential credential = new Credential();
        credential.setUsuario(DEBIN_USER);
        credential.setPassword(DEBIN_PWD);
        doReturn(credential).when(credentialSecurityFactory).getCredentialById("2222");

    }

    @Test
    public void getDebinSellerListOK() throws DAOException {

        //PREPARE
        DebinListRequest debinListRequest = new DebinListRequest();
        debinListRequest.setClientIp(CLIENT_IP);
        debinListRequest.setPageSize(PAGE_SIZE);
        debinListRequest.setMode(SELLER_LIST_MODE);
        debinListRequest.setCreationTo(CREATION_TO);
        debinListRequest.setPageNumber(PAGE_NUMBER);
        debinListRequest.setStatus(DEBIN_STATUS);

        List<DebinType> debinTypes = new ArrayList<DebinType>();
        debinTypes.add(DebinType.SPOT);
        debinTypes.add(DebinType.RECURRENT);
        debinListRequest.setTypes(debinTypes);

        debinListRequest.setSellerCuit(SELLER_CUIT);
        debinListRequest.setSellerCbu(SELLER_CBU);

        //MOCK

        ResponseEntity<DebinListDTO> response = new ResponseEntity<DebinListDTO>( DebinApiMock.getDefaultDebinListDTO(), HttpStatus.OK);
        String url = DEBIN_URL +
                PATH_DEBIN_LIST + "?" +
                "mode=" + SELLER_LIST_MODE.toString() + "&" +
                "sellerCbu=" +SELLER_CBU + "&" +
                "sellerCuit=" + SELLER_CUIT + "&" +
                "creationTo=" + CREATION_TO + "&" +
                "pageNumber=" + PAGE_NUMBER + "&" +
                "pageSize=" + PAGE_SIZE + "&"+
                "status=" + DEBIN_STATUS + "&" +
                "type=" +DebinType.SPOT.toString() + "&" +
                "type=" + DebinType.RECURRENT.toString() + "&" +
                "clientIp=" + CLIENT_IP;
        doReturn(response).when(restTemplate).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                DebinListDTO.class
                );

        //CALL METHOD
        DebinListDTO debinListDTO =  debinApiDAO.getDebinList(debinListRequest);

        //ASSERTS
        Assert.assertEquals(1, debinListDTO.getDebins().size());
        Assert.assertEquals(2, debinListDTO.getTotalPages());
        verify(restTemplate, times(1)).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                DebinListDTO.class
        );

    }

    @Test
    public void getDebinbuyerListOK() throws DAOException {
        //PREPARE
        DebinListRequest debinListRequest = new DebinListRequest();
        debinListRequest.setClientIp(CLIENT_IP);
        debinListRequest.setPageSize(PAGE_SIZE);
        debinListRequest.setMode(BUYER_LIST_MODE);
        debinListRequest.setCreationTo(CREATION_TO);
        debinListRequest.setPageNumber(PAGE_NUMBER);
        debinListRequest.setStatus(DEBIN_STATUS);

        List<DebinType> debinTypes = new ArrayList<DebinType>();
        debinTypes.add(DebinType.SPOT);
        debinTypes.add(DebinType.RECURRENT);
        debinListRequest.setTypes(debinTypes);

        debinListRequest.setBuyerCbu(BUYER_CBU);
        debinListRequest.setBuyerCuit(BUYER_CUIT);

        //MOCK
        ResponseEntity<DebinListDTO> response = new ResponseEntity<DebinListDTO>( DebinApiMock.getDefaultDebinListDTO(), HttpStatus.OK);
        String url = DEBIN_URL +
                PATH_DEBIN_LIST + "?" +
                "mode=" + BUYER_LIST_MODE.toString() + "&" +
                "buyerCbu=" +BUYER_CBU + "&" +
                "buyerCuit=" + BUYER_CUIT + "&" +
                "creationTo=" + CREATION_TO + "&" +
                "pageNumber=" + PAGE_NUMBER + "&" +
                "pageSize=" + PAGE_SIZE + "&"+
                "status=" + DEBIN_STATUS + "&" +
                "type=" +DebinType.SPOT.toString() + "&" +
                "type=" + DebinType.RECURRENT.toString() + "&" +
                "clientIp=" + CLIENT_IP;
        doReturn(response).when(restTemplate).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                DebinListDTO.class
        );
        //CALL METHOD
        DebinListDTO debinListDTO =  debinApiDAO.getDebinList(debinListRequest);

        //ASSERTS
        Assert.assertEquals(1, debinListDTO.getDebins().size());
        Assert.assertEquals(2, debinListDTO.getTotalPages());

        verify(restTemplate, times(1)).exchange(
                url,
                HttpMethod.GET,
                this.getRequest(),
                DebinListDTO.class
        );

    }


    @Test
    public void getDebinSellerListServiceUnavailable() throws DAOException {
        //PREPARE
        DebinListRequest debinListRequest = new DebinListRequest();
        debinListRequest.setClientIp(CLIENT_IP);
        debinListRequest.setPageSize(PAGE_SIZE);
        debinListRequest.setMode(SELLER_LIST_MODE);
        debinListRequest.setCreationTo(CREATION_TO);
        debinListRequest.setPageNumber(PAGE_NUMBER);
        debinListRequest.setStatus(DEBIN_STATUS);

        List<DebinType> debinTypes = new ArrayList<DebinType>();
        debinTypes.add(DebinType.SPOT);
        debinTypes.add(DebinType.RECURRENT);
        debinListRequest.setTypes(debinTypes);

        debinListRequest.setSellerCuit(SELLER_CUIT);
        debinListRequest.setSellerCbu(SELLER_CBU);

        //MOCK
        String url = DEBIN_URL +
                PATH_DEBIN_LIST + "?" +
                "mode=" + SELLER_LIST_MODE.toString() + "&" +
                "sellerCbu=" +SELLER_CBU + "&" +
                "sellerCuit=" + SELLER_CUIT + "&" +
                "creationTo=" + CREATION_TO + "&" +
                "pageNumber=" + PAGE_NUMBER + "&" +
                "pageSize=" + PAGE_SIZE + "&"+
                "status=" + DEBIN_STATUS + "&" +
                "type=" +DebinType.SPOT.toString() + "&" +
                "type=" + DebinType.RECURRENT.toString() + "&" +
                "clientIp=" + CLIENT_IP;
        HttpStatusCodeException exception = new HttpStatusCodeException(HttpStatus.FAILED_DEPENDENCY) {

            @Override
            public String getResponseBodyAsString() {
                ProviderErrorDTO providerErrorDTO = DebinApiMock.getDefaultProviderErrorDTO(HttpStatus.SERVICE_UNAVAILABLE.value());
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
                DebinListDTO.class
        );

        //CALL METHOD
        try {
            debinApiDAO.getDebinList(debinListRequest);
        }catch(DAOException e) {
            //ASSERTS
            String msgError = "Coelsa no disponible. Error en el servicio DEBIN API.  /debins";
            Assert.assertEquals(msgError, e.getMessage());
        }

    }

    @Test
    public void getDebinSellerListErrorCode() throws DAOException {
        //PREPARE
        DebinListRequest debinListRequest = new DebinListRequest();
        debinListRequest.setPageSize(PAGE_SIZE);
        debinListRequest.setMode(DebinListMode.SELLER);
        debinListRequest.setCreationTo(CREATION_TO);
        debinListRequest.setPageNumber(PAGE_NUMBER);
        debinListRequest.setStatus(DebinStatus.CREDITED);

        List<DebinType> debinTypes = new ArrayList<DebinType>();
        debinTypes.add(DebinType.SPOT);
        debinTypes.add(DebinType.RECURRENT);
        debinListRequest.setTypes(debinTypes);

        debinListRequest.setSellerCuit(SELLER_CUIT);
        debinListRequest.setSellerCbu(SELLER_CBU);

        //MOCK
        String url = DEBIN_URL +
                PATH_DEBIN_LIST + "?" +
                "mode=" + SELLER_LIST_MODE.toString() + "&" +
                "sellerCbu=" +SELLER_CBU + "&" +
                "sellerCuit=" + SELLER_CUIT + "&" +
                "creationTo=" + CREATION_TO + "&" +
                "pageNumber=" + PAGE_NUMBER + "&" +
                "pageSize=" + PAGE_SIZE + "&"+
                "status=" + DEBIN_STATUS + "&" +
                "type=" +DebinType.SPOT.toString() + "&" +
                "type=" + DebinType.RECURRENT.toString()  ;
        HttpStatusCodeException exception = new HttpStatusCodeException(HttpStatus.FAILED_DEPENDENCY) {

            @Override
            public String getResponseBodyAsString() {
                ProviderErrorDTO providerErrorDTO = DebinApiMock.getDefaultProviderErrorDTO(HttpStatus.BAD_REQUEST.value());
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
                DebinListDTO.class
        );

        //CALL METHOD
        try {
            DebinListDTO debinListDTO = debinApiDAO.getDebinList(debinListRequest);
        }catch(DAOException e) {
            //ASSERTS
            Assert.assertEquals("C0-0798", e.getErrorCode());
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
