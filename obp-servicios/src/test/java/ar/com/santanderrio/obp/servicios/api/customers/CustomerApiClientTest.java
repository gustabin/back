package ar.com.santanderrio.obp.servicios.api.customers;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.api.customers.model.AdditionalData;
import ar.com.santanderrio.obp.servicios.api.customers.model.AddressesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.AudiencesResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.Contacts;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomerEconomicDataResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.Customers;
import ar.com.santanderrio.obp.servicios.api.customers.model.CustomersSearch;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentTypeEnum;
import ar.com.santanderrio.obp.servicios.api.customers.model.DocumentsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.EmailsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.JobsResponse;
import ar.com.santanderrio.obp.servicios.api.customers.model.PhonesResponse;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplateBuilder;
import ar.com.santanderrio.obp.servicios.oauth2.resource.BaseOAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2AuthorizationException;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import ar.com.santanderrio.obp.test.utils.TestUtils;

import com.google.gson.GsonBuilder;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CustomerApiClientTest.CustomersClientApiTestConfig.class})
@TestPropertySource("classpath:/config/files/obpcontext.properties")
public class CustomerApiClientTest {

    private static final String BASE_URL = "https://fake/api";
    private static final String REQUESTED_NUP = "12345678";
    private static final String REQUESTED_DOCUMENT_TYPE = "DNI";
    private static final String REQUESTED_DOCUMENT_NUMBER = "123456789";
    private static final String ERROR = "ERROR";
    private static final String NO_BODY_FOUND = "NO RESPONSE BODY FOUND";

    @Autowired
    CustomersApi customersApi;

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Autowired
    OAuthTokenProvider tokenProvider;

    MockRestServiceServer restServiceServer;

    @Before
    public void setup() {
        restServiceServer = MockRestServiceServer.createServer(restTemplate);
        MockitoAnnotations.initMocks(this);
    }

    @Configuration
    public static class CustomersClientApiTestConfig {

        @Bean
        public OAuthTokenProvider tokenProvider() {
            return Mockito.spy(new OAuthTokenProvider() {
                @Override
                public OAuth2AccessToken requestAccessToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException {
                    OAuth2AccessToken token = new OAuth2AccessToken();
                    token.setAccessToken("");
                    return token;
                }

                @Override
                public OAuth2AccessToken requestRefreshToken(OAuth2ResourceDetails resourceDetails) throws OAuth2AuthorizationException {
                    return null;
                }
            });
        }

        @Bean
        @Autowired
        public OAuth2RestTemplate oAuth2RestTemplate(OAuthTokenProvider tokenProvider) {
            OAuth2RestTemplate restTemplate = OAuth2RestTemplateBuilder.builder()
                .oauthTokenProvider(tokenProvider)
                .resourceDetails(new BaseOAuth2ResourceDetails())
                .build();

            DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
            GsonDateTypeConverter dateTypeConverter = new GsonDateTypeConverter().withDeserializationFormat(formatWithTimezone);

            GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
            gsonHttpMessageConverter.setGson(new GsonBuilder().registerTypeAdapter(Date.class, dateTypeConverter).create());

            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            messageConverters.add(gsonHttpMessageConverter);

            restTemplate.setMessageConverters(messageConverters);
            restTemplate.setErrorHandler(new CommonApiErrorResponseHandler(messageConverters));
            return Mockito.spy(restTemplate);
        }

        @Bean
        @Autowired
        public CustomersApi customersApi(OAuth2RestTemplate oauth2RestTemplate) {
            return CustomersApiClientBuilder.builder()
                .withCustomersApiConfig(new ApiConfig() {
                    @Override
                    public String getBasePath() {
                        return BASE_URL;
                    }

                    @Override
                    public String getScope() {
                        return "some.scope";
                    }
                })
                .withOAuthRestTemplate(oauth2RestTemplate).build();
        }
    }

    @Test
    public void getAdditionalDataById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getAdditionalData.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/additional-data");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        AdditionalData apiResponse = customersApi.getAdditionalData(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getAddressesById() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getAddressesById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/addresses");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        AddressesResponse apiResponse = customersApi.getAddressesById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getAudiencesById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getAudiencesById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/audiences" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        AudiencesResponse apiResponse = customersApi.getAudiencesById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getContactsById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getContactsById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/contacts" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        Contacts apiResponse = customersApi.getContactsById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getCustomerById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getCustomerById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        Customers apiResponse = customersApi.getCustomerById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getCustomersSearch_OK() throws Exception {
        DocumentTypeEnum docType = null;
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getCustomersSearch.json");
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL + "/customers?document_type={document_type}&document_number={document_number}")
                .replaceQueryParam("document_type", REQUESTED_DOCUMENT_TYPE)
                .replaceQueryParam("document_number", REQUESTED_DOCUMENT_NUMBER).build().toUri();
        URI uri2 = UriComponentsBuilder.fromUriString(BASE_URL + "/customers?document_type={document_type}&document_number={document_number}")
                .replaceQueryParam("document_type", docType)
                .replaceQueryParam("document_number", REQUESTED_DOCUMENT_NUMBER).build().toUri();
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri)).andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(resource));

        restServiceServer.expect(requestTo(uri2)).andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(resource));

        restServiceServer.expect(requestTo(uri2)).andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(resource));

        CustomersSearch apiResponse = customersApi.getCustomerByParams(REQUESTED_DOCUMENT_TYPE, REQUESTED_DOCUMENT_NUMBER);
        CustomersSearch apiResponse2 = customersApi.getCustomerByParams(null, REQUESTED_DOCUMENT_NUMBER);
        CustomersSearch apiResponse3 = customersApi.getCustomerByParams("invalid", REQUESTED_DOCUMENT_NUMBER);
        Assert.assertNotNull(apiResponse);
        Assert.assertNotNull(apiResponse2);
        Assert.assertNotNull(apiResponse3);
        restServiceServer.verify();
    }

    @Test
    public void getDocumentsById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getDocumentsById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        DocumentsResponse apiResponse = customersApi.getDocumentsById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(2, apiResponse.getDocuments().size());
        restServiceServer.verify();
    }

    @Test
    public void getEconomicDataById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getEconomicDataById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/economic-data" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        CustomerEconomicDataResponse apiResponse = customersApi.getEconomicDataById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getEmailsById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getEmailsById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/emails" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resource));

        EmailsResponse apiResponse = customersApi.getEmailsById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getJobsById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getJobsById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/jobs" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(resource));

        JobsResponse apiResponse = customersApi.getJobsById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void getPhonesById_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/getPhonesById.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/phones" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        PhonesResponse apiResponse = customersApi.getPhonesById(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        restServiceServer.verify();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getDocumentsById_providerExeption() throws Exception {
        Mockito.when(tokenProvider.requestAccessToken(Mockito.any(OAuth2ResourceDetails.class)))
                .thenThrow(new OAuth2AuthorizationException(ERROR));

        try {
            customersApi.getDocumentsById(REQUESTED_NUP);
        } catch (ApiException customersApiException) {
            ErrorResponse errorResponse = customersApiException.getErrorResponse();
            Assert.assertNotNull(errorResponse);
            Assert.assertEquals(ERROR, errorResponse.getCode());
            Assert.assertEquals(NO_BODY_FOUND, errorResponse.getMessage());
            Assert.assertNull(errorResponse.getErrors());
        }
        restServiceServer.verify();
    }

    @Test
    public void getDocumentsById_notBodyFound() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/emptyBody.html");
        Resource resource = new FileSystemResource(jsonResponse);
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.TEXT_HTML)
                .body(resource));

        try {
            customersApi.getDocumentsById(REQUESTED_NUP);
        } catch (ApiException customersApiException) {
            ErrorResponse errorResponse = customersApiException.getErrorResponse();
            Assert.assertNotNull(errorResponse);
            Assert.assertEquals(ERROR, errorResponse.getCode());
            Assert.assertEquals(NO_BODY_FOUND, errorResponse.getMessage());
            Assert.assertNull(errorResponse.getErrors());
        }
        restServiceServer.verify();
    }


    //TODO: MOVE THIS TO ERRORHANDLER TEST CLASSES
    @Test(expected = ApiException.class)
    public void getDocumentsById_uncaughtException() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/internalError.json");
        Resource resource = new FileSystemResource(jsonResponse);
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        customersApi.getDocumentsById(REQUESTED_NUP);
    }

    @Test
    public void getDocumentsById_InternalError() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/internalError.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        try {
            customersApi.getDocumentsById(REQUESTED_NUP);
        } catch (ApiException customersApiException) {
            ErrorResponse errorResponse = customersApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, customersApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNotNull(errorResponse.getMessage());
            Assert.assertNotNull(errorResponse.getCode());
            Assert.assertNotNull(errorResponse.getErrors());
            Assert.assertNotNull(customersApiException.getHttpHeaders());
            Assert.assertEquals(MediaType.APPLICATION_JSON, customersApiException.getHttpHeaders().getContentType());
            Assert.assertNotEquals(errorResponse.getErrors().size(), 0);
        }
    }

    @Test
    public void getDocumentsById_unauthorized() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/unauthorized.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        try {
            customersApi.getDocumentsById(REQUESTED_NUP);
        } catch (ApiException customersApiException) {
            ErrorResponse errorResponse = customersApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.UNAUTHORIZED, customersApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNull(errorResponse.getErrors());
            Assert.assertEquals(ERROR, errorResponse.getCode());
            Assert.assertEquals(NO_BODY_FOUND, errorResponse.getMessage());
        }
        restServiceServer.verify();
    }

    @Test
    public void getDocumentsById_badUrl() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/customers/badUrl.json");
        URI uri = URI.create(BASE_URL + "/customers/" + REQUESTED_NUP + "/documents" );
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
            .andExpect(method(HttpMethod.GET))
            .andRespond(MockRestResponseCreators.withStatus(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource));

        try {
            customersApi.getDocumentsById(REQUESTED_NUP);
        } catch (ApiException customersApiException) {
            ErrorResponse errorResponse = customersApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.UNAUTHORIZED, customersApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNull(errorResponse.getCode());
            //This message comes from the api gateway hence the different format in the response
            Assert.assertEquals("No message available", errorResponse.getMessage());
            Assert.assertNull(errorResponse.getErrors());
        }
        restServiceServer.verify();
    }

}
