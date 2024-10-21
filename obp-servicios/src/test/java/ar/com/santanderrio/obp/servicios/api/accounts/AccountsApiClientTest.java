package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
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
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AccountsApiClientTest.AccountsClientApiTestConfig.class})
@TestPropertySource("classpath:/config/files/obpcontext.properties")
public class AccountsApiClientTest {

    private static final String BASE_URL = "https://fake/api";
    private static final String REQUESTED_NUP = "12345678";
    private static final String ERROR = "ERROR";
    private static final String NO_BODY_FOUND = "NO RESPONSE BODY FOUND";

    @Autowired
    AccountsApi accountsApi;

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
    public static class AccountsClientApiTestConfig {

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
        public AccountsApi accountsApi(OAuth2RestTemplate oauth2RestTemplate) {
            return AccountsApiClientBuilder.builder()
                    .withAccountApiConfig(new ApiConfig() {
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
    public void getAccountsByCustomerId_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/accounts/getAccountsByCustomerId.json");
        URI uri = URI.create(BASE_URL + "/accounts?customerId=" + REQUESTED_NUP + "&status=open&participantQuality=owner,co_owner&_limit=100");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        AccountsResponseEntity apiResponse = accountsApi.getAccountsByCustomerId(REQUESTED_NUP);
        Assert.assertNotNull(apiResponse);
        Assert.assertEquals(7, apiResponse.getAccounts().size());
        restServiceServer.verify();
    }

    @Test
    public void getAccountsByCustomerId_unauthorized() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/accounts/unauthorized.json");
        URI uri = URI.create(BASE_URL + "/accounts?customerId=" + REQUESTED_NUP + "&status=open&participantQuality=owner,co_owner&_limit=100");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.UNAUTHORIZED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        try {
            accountsApi.getAccountsByCustomerId(REQUESTED_NUP);
        } catch (ApiException accountsApiException) {
            ErrorResponse errorResponse = accountsApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.UNAUTHORIZED, accountsApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNull(errorResponse.getErrors());
            Assert.assertEquals(ERROR, errorResponse.getCode());
            Assert.assertEquals(NO_BODY_FOUND, errorResponse.getMessage());
        }
        restServiceServer.verify();
    }

    @Test
    public void getAccountsByCustomerId_InternalError() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/accounts/internalError.json");
        URI uri = URI.create(BASE_URL + "/accounts?customerId=" + REQUESTED_NUP + "&status=open&participantQuality=owner,co_owner&_limit=100");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        try {
            accountsApi.getAccountsByCustomerId(REQUESTED_NUP);
        } catch (ApiException accountsApiException) {
            ErrorResponse errorResponse = accountsApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, accountsApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNotNull(errorResponse.getMessage());
            Assert.assertNotNull(errorResponse.getCode());
            Assert.assertNotNull(errorResponse.getErrors());
            Assert.assertNotNull(accountsApiException.getHttpHeaders());
            Assert.assertEquals(MediaType.APPLICATION_JSON, accountsApiException.getHttpHeaders().getContentType());
            Assert.assertNotEquals(errorResponse.getErrors().size(), 0);
        }
    }

}
