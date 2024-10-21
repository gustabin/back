package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.debinapi.dto.OperationMode;
import ar.com.santanderrio.obp.servicios.debinapi.dto.RecurrenceActionEnum;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.*;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpHeaders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.*;
import javax.ws.rs.core.Response;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DebinRecurrenteDAOTest {
    private static final String PATH_SELLERS_SEARCH_BY_NAME = "/sellers/searchByName";
    private static final String PARAMETER_RECURRENCE_ID = "{recurrenceId}";
    private static final String PATH_RECURRECE_STATUS_SETTINGS = "/recurrences/" + PARAMETER_RECURRENCE_ID;
    private static final String PARAMETER_QUERY_SEARCH_BY_NAME = "name";

    private static final String PARAMETER_CUIT = "{cuit}";
    private static final String PATH_SELLER_PROVISIONS = "/sellers/" + PARAMETER_CUIT + "/provisions";

    private static final String DEBIN_USER  ="Vale";
    private static final String DEBIN_PWD  ="1231";
    public static final String SECIDVALUE = "2222";

    @InjectMocks
    private DebinRecurrenteDAO debinRecurrenteDAO = new DebinRecurrenteDAOImpl();

    @InjectMocks
    private RespuestaFactory respuestaFactory;

    @Mock
    private WebClient webClient;

    @Mock
    private RestWebClient restWebClient;

    @Captor
    ArgumentCaptor<RecurrenceUpdateDTO> recurrenceCreationDTOCaptor;

    @Mock
    SesionCliente sesionCliente;

    @Before
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(debinRecurrenteDAO, "secId", SECIDVALUE);

        Credential credential = new Credential();
        credential.setUsuario(DEBIN_USER);
        credential.setPassword(DEBIN_PWD);
        doReturn(credential).when(credentialSecurityFactory).getCredentialById(SECIDVALUE);

    }

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Test
    public void obtenerEmpresasOk() throws Exception {
        //MOCK
        String empresaBuscada = "empresa";
        mockWebClientEmpresas(empresaBuscada);

        List<SellerWithCategoryDTO> mockedResponse = DebinRecurrenteApiMock.getDefaultSellers();
        Mockito.<Collection<? extends SellerWithCategoryDTO>>when(webClient.getCollection(SellerWithCategoryDTO.class)).thenReturn(mockedResponse);

        Response webClientResponse = Mockito.mock(Response.class);
        when(webClient.getResponse()).thenReturn(webClientResponse);
        when(webClientResponse.getStatus()).thenReturn(HttpStatus.SC_OK);

        //CALL METHOD
        List<SellerWithCategoryDTO> actualResponse = debinRecurrenteDAO.obtenerEmpresas(empresaBuscada);

        //ASSERTS
        Assert.assertEquals(mockedResponse, actualResponse);
        verify(restWebClient, times(1)).obtenerClienteRest(anyString());
        verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
        verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
        verify(webClient, times(1)).path(PATH_SELLERS_SEARCH_BY_NAME);
        verify(webClient, times(1)).query(PARAMETER_QUERY_SEARCH_BY_NAME, empresaBuscada);
        verify(webClient, times(1)).getCollection(SellerWithCategoryDTO.class);
        verify(webClient, times(1)).getResponse();
        verify(webClientResponse, times(1)).getStatus();
    }

    @Test
    public void obtenerEmpresasFail() throws Exception {
        String empresaBuscada = "empresa";
        Response webClientResponse = Mockito.mock(Response.class);
        try {
            //MOCK
            mockWebClientEmpresas(empresaBuscada);

            List<SellerWithCategoryDTO> mockedResponse = DebinRecurrenteApiMock.getDefaultSellers();
            Mockito.<Collection<? extends SellerWithCategoryDTO>>when(webClient.getCollection(SellerWithCategoryDTO.class)).thenReturn(mockedResponse);

            when(webClient.getResponse()).thenReturn(webClientResponse);
            when(webClientResponse.getStatus()).thenReturn(HttpStatus.SC_BAD_REQUEST);

            //CALL METHOD
            List<SellerWithCategoryDTO> actualResponse = debinRecurrenteDAO.obtenerEmpresas(empresaBuscada);
            fail("expected exception was not occured.");
        } catch (DAOException daoException) {
            Assert.assertTrue(daoException.getMessage().contains("Error en el servicio DEBIN API "));
            verify(restWebClient, times(1)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_SELLERS_SEARCH_BY_NAME);
            verify(webClient, times(1)).query(PARAMETER_QUERY_SEARCH_BY_NAME, empresaBuscada);
            verify(webClient, times(1)).getCollection(SellerWithCategoryDTO.class);
            verify(webClient, times(1)).getResponse();
            verify(webClientResponse, times(1)).getStatus();
        }
    }

    @Test
    public void obtenerServiciosOk() throws Exception {
        //MOCK
        String cuitEmpresa = "uncuit";
        mockWebClientServicios(cuitEmpresa);

        SellerWithProvisionDTO mockedResponse = DebinRecurrenteApiMock.getDefaultSellerWithProvisions(cuitEmpresa);
        Mockito.when(webClient.get(SellerWithProvisionDTO.class)).thenReturn(mockedResponse);

        Response webClientResponse = Mockito.mock(Response.class);
        when(webClient.getResponse()).thenReturn(webClientResponse);
        when(webClientResponse.getStatus()).thenReturn(HttpStatus.SC_OK);

        //CALL METHOD
        SellerWithProvisionDTO actualResponse = debinRecurrenteDAO.obtenerServicios(cuitEmpresa);

        //ASSERTS
        Assert.assertEquals(mockedResponse, actualResponse);
        verify(restWebClient, times(2)).obtenerClienteRest(anyString());
        verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
        verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
        verify(webClient, times(1)).path(PATH_SELLER_PROVISIONS.replace(PARAMETER_CUIT, cuitEmpresa));
        verify(webClient, times(1)).get(SellerWithProvisionDTO.class);
        verify(webClient, times(1)).getResponse();
        verify(webClientResponse, times(1)).getStatus();
    }

    @Test
    public void obtenerServiciosWarn() throws Exception {
        String cuitEmpresa = "uncuit";
        Response webClientResponse = Mockito.mock(Response.class);
        try {
            //MOCK
            mockWebClientServicios(cuitEmpresa);

            SellerWithProvisionDTO mockedResponse = DebinRecurrenteApiMock.getDefaultSellerWithProvisions(cuitEmpresa);
            Mockito.when(webClient.get(SellerWithProvisionDTO.class)).thenReturn(mockedResponse);

            when(webClient.getResponse()).thenReturn(webClientResponse);
            when(webClientResponse.getStatus()).thenReturn(HttpStatus.SC_NOT_FOUND);

            //CALL METHOD
            SellerWithProvisionDTO actualResponse = debinRecurrenteDAO.obtenerServicios(cuitEmpresa);
            fail("expected exception was not occured.");
        } catch (DAOException daoException) {
            Assert.assertTrue(daoException.getMessage().contains("No se ha encontrados datos para cuit"));
            verify(restWebClient, times(2)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_SELLER_PROVISIONS.replace(PARAMETER_CUIT, cuitEmpresa));
            verify(webClient, times(1)).get(SellerWithProvisionDTO.class);
            verify(webClient, times(1)).getResponse();
            verify(webClientResponse, times(1)).getStatus();
        }

    }

    @Test
    public void obtenerServiciosFail() throws Exception {
        String cuitEmpresa = "uncuit";
        Response webClientResponse = Mockito.mock(Response.class);
        try {
            //MOCK
            mockWebClientServicios(cuitEmpresa);

            SellerWithProvisionDTO mockedResponse = DebinRecurrenteApiMock.getDefaultSellerWithProvisions(cuitEmpresa);
            Mockito.when(webClient.get(SellerWithProvisionDTO.class)).thenReturn(mockedResponse);

            when(webClient.getResponse()).thenReturn(webClientResponse);
            when(webClientResponse.getStatus()).thenReturn(HttpStatus.SC_BAD_REQUEST);

            //CALL METHOD
            SellerWithProvisionDTO actualResponse = debinRecurrenteDAO.obtenerServicios(cuitEmpresa);
            fail("expected exception was not occured.");
        } catch (DAOException daoException) {
            Assert.assertTrue(daoException.getMessage().contains("Error en el servicio DEBIN API"));
            verify(restWebClient, times(2)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_SELLER_PROVISIONS.replace(PARAMETER_CUIT, cuitEmpresa));
            verify(webClient, times(1)).get(SellerWithProvisionDTO.class);
            verify(webClient, times(1)).getResponse();
            verify(webClientResponse, times(1)).getStatus();
        }
    }

    private void mockWebClientEmpresas(String empresaBuscada) throws DAOException {
        when(restWebClient.obtenerClienteRest(anyString())).thenReturn(webClient);

        when(webClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")).thenReturn(webClient);
        when(webClient.accept(MediaType.APPLICATION_JSON)).thenReturn(webClient);
        when(webClient.header(eq(HttpHeaders.AUTHORIZATION), anyString())).thenReturn(webClient);
        when(webClient.path(PATH_SELLERS_SEARCH_BY_NAME)).thenReturn(webClient);
        when(webClient.query(PARAMETER_QUERY_SEARCH_BY_NAME, empresaBuscada)).thenReturn(webClient);
    }

    private void mockWebClientServicios(String cuitEmpresa) throws DAOException {
        when(restWebClient.obtenerClienteRest(anyString())).thenReturn(webClient);

        when(webClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")).thenReturn(webClient);
        when(webClient.accept(MediaType.APPLICATION_JSON)).thenReturn(webClient);
        when(webClient.header(eq(HttpHeaders.AUTHORIZATION), anyString())).thenReturn(webClient);
        when(webClient.path(PATH_SELLER_PROVISIONS.replace(PARAMETER_CUIT, cuitEmpresa))).thenReturn(webClient);
    }

    @Test
    public void applyActionToRecurrenceOK() throws Exception{

        //mock recurrencia main method input
        RecurrenciaView recurrenciaView = new RecurrenciaView();
        recurrenciaView.setId("123");
        String successfulMessage = "Aceptaste correctamente a la recurrencia";

        //MOCK
        mockWebClientRecurrenceService();
        Response responseMocked = getResponseMocked();
        Mockito.when(webClient.put(any(RecurrenceUpdateDTO.class))).thenReturn(responseMocked);

        //MOCK
        Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        //CALL METHOD
        AccionRecurrenciaResponseView accionRecurrenciaResponseView = debinRecurrenteDAO.applyActionToRecurrence(recurrenciaView, RecurrenceActionEnum.ACTIVATE,
                                                     successfulMessage);

        //ASSERTS
        verify(restWebClient, times(1)).obtenerClienteRest(anyString());
        verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
        verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
        verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
        verify(webClient, times(1)).path(PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, "123"));

        verify(webClient, times(1)).put(recurrenceCreationDTOCaptor.capture());
        RecurrenceUpdateDTO recurrenceUpdateDTO = recurrenceCreationDTOCaptor.getValue();
        Assert.assertEquals(RecurrenceActionEnum.ACTIVATE.toString(), recurrenceUpdateDTO.getRecurrenceAction());
        Assert.assertEquals("20209204100", recurrenceUpdateDTO.getAuthorCuit());
        Assert.assertEquals(OperationMode.COMPRADOR, recurrenceUpdateDTO.getOperationMode());
    }

    @Test
    public void applyActionToRecurrenceFailedDependency() throws Exception {

        try {
            //mock recurrencia main method input
            RecurrenciaView recurrenciaView = new RecurrenciaView();
            recurrenciaView.setId("123");
            String successfulMessage = "Aceptaste correctamente a la recurrencia";

            //MOCK
            mockWebClientRecurrenceService();
            Response responseMocked = getResponseMockeFailedDependency();
            Mockito.when(webClient.put(any(ProviderErrorDTO.class))).thenReturn(responseMocked);

            //MOCK
            Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
            Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

            //CALL METHOD
            AccionRecurrenciaResponseView accionRecurrenciaResponseView = debinRecurrenteDAO.applyActionToRecurrence(recurrenciaView, RecurrenceActionEnum.ACTIVATE,
                    successfulMessage);
        } catch (DAOException daoException) {
            //ASSERTS
            verify(restWebClient, times(1)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, "123"));
            verify(webClient, times(1)).put(recurrenceCreationDTOCaptor.capture());
            RecurrenceUpdateDTO recurrenceUpdateDTO = recurrenceCreationDTOCaptor.getValue();
            Assert.assertEquals(RecurrenceActionEnum.ACTIVATE.toString(), recurrenceUpdateDTO.getRecurrenceAction());
            Assert.assertEquals("20209204100", recurrenceUpdateDTO.getAuthorCuit());
            Assert.assertEquals(OperationMode.COMPRADOR, recurrenceUpdateDTO.getOperationMode());
        }
    }

    @Test
    public void applyActionToRecurrenceNotFound() throws Exception {

        try {
            //mock recurrencia main method input
            RecurrenciaView recurrenciaView = new RecurrenciaView();
            recurrenciaView.setId("123");
            String successfulMessage = "Aceptaste correctamente a la recurrencia";

            //MOCK
            mockWebClientRecurrenceService();
            Response responseMocked = getResponseMockeNotFound();
            Mockito.when(webClient.put(any(ProviderErrorDTO.class))).thenReturn(responseMocked);

            //MOCK
            Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
            Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

            //CALL METHOD
            AccionRecurrenciaResponseView accionRecurrenciaResponseView = debinRecurrenteDAO.applyActionToRecurrence(recurrenciaView, RecurrenceActionEnum.ACTIVATE,
                    successfulMessage);
        } catch (DAOException daoException) {
            //ASSERTS
            verify(restWebClient, times(1)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, "123"));
            verify(webClient, times(1)).put(recurrenceCreationDTOCaptor.capture());
            RecurrenceUpdateDTO recurrenceUpdateDTO = recurrenceCreationDTOCaptor.getValue();
            Assert.assertEquals(RecurrenceActionEnum.ACTIVATE.toString(), recurrenceUpdateDTO.getRecurrenceAction());
            Assert.assertEquals("20209204100", recurrenceUpdateDTO.getAuthorCuit());
            Assert.assertEquals(OperationMode.COMPRADOR, recurrenceUpdateDTO.getOperationMode());
        }
    }

    @Test
    public void applyActionToRecurrenceNotAllowed() throws Exception {

        try {
            //mock recurrencia main method input
            RecurrenciaView recurrenciaView = new RecurrenciaView();
            recurrenciaView.setId("123");
            String successfulMessage = "Aceptaste correctamente a la recurrencia";

            //MOCK
            mockWebClientRecurrenceService();
            Response responseMocked = getResponseMockeNotAllowed();
            Mockito.when(webClient.put(any(ProviderErrorDTO.class))).thenReturn(responseMocked);

            //MOCK
            Cliente cliente = DebinRecurrenteApiMock.obtenerCliente();
            Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

            //CALL METHOD
            AccionRecurrenciaResponseView accionRecurrenciaResponseView = debinRecurrenteDAO.applyActionToRecurrence(recurrenciaView, RecurrenceActionEnum.ACTIVATE,
                    successfulMessage);
        } catch (DAOException daoException) {
            //ASSERTS
            verify(restWebClient, times(1)).obtenerClienteRest(anyString());
            verify(webClient, times(1)).type(MediaType.APPLICATION_JSON + ";charset=UTF-8");
            verify(webClient, times(1)).accept(MediaType.APPLICATION_JSON);
            verify(webClient, times(1)).header(eq(HttpHeaders.AUTHORIZATION), anyString());
            verify(webClient, times(1)).path(PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, "123"));
            verify(webClient, times(1)).put(recurrenceCreationDTOCaptor.capture());
            RecurrenceUpdateDTO recurrenceUpdateDTO = recurrenceCreationDTOCaptor.getValue();
            Assert.assertEquals(RecurrenceActionEnum.ACTIVATE.toString(), recurrenceUpdateDTO.getRecurrenceAction());
            Assert.assertEquals("20209204100", recurrenceUpdateDTO.getAuthorCuit());
            Assert.assertEquals(OperationMode.COMPRADOR, recurrenceUpdateDTO.getOperationMode());
        }
    }

    private void mockWebClientRecurrenceService() throws Exception {
        when(restWebClient.obtenerClienteRest(anyString())).thenReturn(webClient);
        when(webClient.type(MediaType.APPLICATION_JSON + ";charset=UTF-8")).thenReturn(webClient);
        when(webClient.accept(MediaType.APPLICATION_JSON)).thenReturn(webClient);
        when(webClient.header(eq(HttpHeaders.AUTHORIZATION), anyString())).thenReturn(webClient);
        when(webClient.path(PATH_RECURRECE_STATUS_SETTINGS.replace(PARAMETER_RECURRENCE_ID, "123"))).thenReturn(webClient);

    }

    private Response getResponseMocked() {
        return new Response() {
            @Override
            public int getStatus() {
                return HttpStatus.SC_OK;
            }

            @Override
            public StatusType getStatusInfo() {
                return null;
            }

            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass) {
                RecurrenceDTO recurrenceDTO = new RecurrenceDTO(123);
                return (T) recurrenceDTO;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType) {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
                return null;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
                return null;
            }

            @Override
            public boolean hasEntity() {
                return false;
            }

            @Override
            public boolean bufferEntity() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public MediaType getMediaType() {
                return null;
            }

            @Override
            public Locale getLanguage() {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Set<String> getAllowedMethods() {
                return null;
            }

            @Override
            public Map<String, NewCookie> getCookies() {
                return null;
            }

            @Override
            public EntityTag getEntityTag() {
                return null;
            }

            @Override
            public Date getDate() {
                return null;
            }

            @Override
            public Date getLastModified() {
                return null;
            }

            @Override
            public URI getLocation() {
                return null;
            }

            @Override
            public Set<Link> getLinks() {
                return null;
            }

            @Override
            public boolean hasLink(String s) {
                return false;
            }

            @Override
            public Link getLink(String s) {
                return null;
            }

            @Override
            public Link.Builder getLinkBuilder(String s) {
                return null;
            }

            @Override
            public MultivaluedMap<String, Object> getMetadata() {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getStringHeaders() {
                return null;
            }

            @Override
            public String getHeaderString(String s) {
                return null;
            }
        };
    }

    private Response getResponseMockeFailedDependency() {
        return new Response() {
            @Override
            public int getStatus() {
                return HttpStatus.SC_FAILED_DEPENDENCY;
            }

            @Override
            public StatusType getStatusInfo() {
                return null;
            }

            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass) {
                ProviderErrorDTO errorDTO = new ProviderErrorDTO();
                DetailErrorDTO detailErrorDTO = new DetailErrorDTO();
                List<ErrorDTO> listErrorDto = new ArrayList<ErrorDTO>();
                ErrorDTO error = new ErrorDTO();
                error.setCode("CO-5013");

                errorDTO.setRootDetail(detailErrorDTO);
                detailErrorDTO.setErrors(listErrorDto);

                listErrorDto.add(error);

                return (T) errorDTO;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType) {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
                return null;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
                return null;
            }

            @Override
            public boolean hasEntity() {
                return false;
            }

            @Override
            public boolean bufferEntity() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public MediaType getMediaType() {
                return null;
            }

            @Override
            public Locale getLanguage() {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Set<String> getAllowedMethods() {
                return null;
            }

            @Override
            public Map<String, NewCookie> getCookies() {
                return null;
            }

            @Override
            public EntityTag getEntityTag() {
                return null;
            }

            @Override
            public Date getDate() {
                return null;
            }

            @Override
            public Date getLastModified() {
                return null;
            }

            @Override
            public URI getLocation() {
                return null;
            }

            @Override
            public Set<Link> getLinks() {
                return null;
            }

            @Override
            public boolean hasLink(String s) {
                return false;
            }

            @Override
            public Link getLink(String s) {
                return null;
            }

            @Override
            public Link.Builder getLinkBuilder(String s) {
                return null;
            }

            @Override
            public MultivaluedMap<String, Object> getMetadata() {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getStringHeaders() {
                return null;
            }

            @Override
            public String getHeaderString(String s) {
                return null;
            }
        };
    }

    private Response getResponseMockeNotFound() {
        return new Response() {
            @Override
            public int getStatus() {
                return HttpStatus.SC_NOT_FOUND;
            }

            @Override
            public StatusType getStatusInfo() {
                return null;
            }

            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass) {
                ProviderErrorDTO errorDTO = new ProviderErrorDTO();
                DetailErrorDTO detailErrorDTO = new DetailErrorDTO();
                List<ErrorDTO> listErrorDto = new ArrayList<ErrorDTO>();
                ErrorDTO error = new ErrorDTO();
                error.setCode("CO-5013");

                errorDTO.setRootDetail(detailErrorDTO);
                detailErrorDTO.setErrors(listErrorDto);

                listErrorDto.add(error);

                return (T) errorDTO;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType) {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
                return null;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
                return null;
            }

            @Override
            public boolean hasEntity() {
                return false;
            }

            @Override
            public boolean bufferEntity() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public MediaType getMediaType() {
                return null;
            }

            @Override
            public Locale getLanguage() {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Set<String> getAllowedMethods() {
                return null;
            }

            @Override
            public Map<String, NewCookie> getCookies() {
                return null;
            }

            @Override
            public EntityTag getEntityTag() {
                return null;
            }

            @Override
            public Date getDate() {
                return null;
            }

            @Override
            public Date getLastModified() {
                return null;
            }

            @Override
            public URI getLocation() {
                return null;
            }

            @Override
            public Set<Link> getLinks() {
                return null;
            }

            @Override
            public boolean hasLink(String s) {
                return false;
            }

            @Override
            public Link getLink(String s) {
                return null;
            }

            @Override
            public Link.Builder getLinkBuilder(String s) {
                return null;
            }

            @Override
            public MultivaluedMap<String, Object> getMetadata() {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getStringHeaders() {
                return null;
            }

            @Override
            public String getHeaderString(String s) {
                return null;
            }
        };
    }

    private Response getResponseMockeNotAllowed() {
        return new Response() {
            @Override
            public int getStatus() {
                return HttpStatus.SC_METHOD_NOT_ALLOWED;
            }

            @Override
            public StatusType getStatusInfo() {
                return null;
            }

            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass) {
                ProviderErrorDTO errorDTO = new ProviderErrorDTO();
                DetailErrorDTO detailErrorDTO = new DetailErrorDTO();
                List<ErrorDTO> listErrorDto = new ArrayList<ErrorDTO>();
                ErrorDTO error = new ErrorDTO();
                error.setCode("CO-5013");

                errorDTO.setRootDetail(detailErrorDTO);
                detailErrorDTO.setErrors(listErrorDto);

                listErrorDto.add(error);

                return (T) errorDTO;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType) {
                return null;
            }

            @Override
            public <T> T readEntity(Class<T> aClass, Annotation[] annotations) {
                return null;
            }

            @Override
            public <T> T readEntity(GenericType<T> genericType, Annotation[] annotations) {
                return null;
            }

            @Override
            public boolean hasEntity() {
                return false;
            }

            @Override
            public boolean bufferEntity() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public MediaType getMediaType() {
                return null;
            }

            @Override
            public Locale getLanguage() {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Set<String> getAllowedMethods() {
                return null;
            }

            @Override
            public Map<String, NewCookie> getCookies() {
                return null;
            }

            @Override
            public EntityTag getEntityTag() {
                return null;
            }

            @Override
            public Date getDate() {
                return null;
            }

            @Override
            public Date getLastModified() {
                return null;
            }

            @Override
            public URI getLocation() {
                return null;
            }

            @Override
            public Set<Link> getLinks() {
                return null;
            }

            @Override
            public boolean hasLink(String s) {
                return false;
            }

            @Override
            public Link getLink(String s) {
                return null;
            }

            @Override
            public Link.Builder getLinkBuilder(String s) {
                return null;
            }

            @Override
            public MultivaluedMap<String, Object> getMetadata() {
                return null;
            }

            @Override
            public MultivaluedMap<String, String> getStringHeaders() {
                return null;
            }

            @Override
            public String getHeaderString(String s) {
                return null;
            }
        };
    }
}
