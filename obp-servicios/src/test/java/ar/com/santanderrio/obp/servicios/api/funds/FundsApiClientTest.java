package ar.com.santanderrio.obp.servicios.api.funds;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfigFunds;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.common.utils.CommonApiErrorResponseHandler;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
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
@ContextConfiguration(classes = {ar.com.santanderrio.obp.servicios.api.funds.FundsApiClientTest.FundsClientApiTestConfig.class})
@TestPropertySource("classpath:/config/files/obpcontext.properties")
public class FundsApiClientTest {

    private static final String BASE_URL = "https://fake/api";
    private static final String REQUESTED_CUENTA_TITULO = "0000000000209452";
    private static final String IBMCLIENTID = "xxxxxxxxxxxxxxxxx";
    private static final String FUND_ID = "07";
    private static final String FUND_ID_BAD = "4444";
    private static final String ERROR = "APF_404";
    private static final String MESSAGE = "GET / not found";
    private static final String ERROR_SALESPOINT = "APF_500";
    private static final String MESSAGE_SALESPOINT = "Error al obtener los fondos";
    private static final String NUP = "00276937";
    private static final String CUSTODY_ACCOUNT = "26382210";
    private static final String CUSTODY_ACCOUNT_BP = "250-364628/5";
    private static final String CUSTODY_ACCOUNT_RTL = "1612010/5";
    private static final String SUSCRIPCION = "suscripcion";
    private static final String SEGMENTO = "RTL";

    @Autowired
    FundsApi fundsApi;

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
    public static class FundsClientApiTestConfig {

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
        public FundsApi fundsApi(OAuth2RestTemplate oauth2RestTemplate) {
            return FundsApiClientBuilder.builder()
                    .withFundsApiConfig(new ApiConfigFunds() {
                        @Override
                        public String getCommonBasePath() {
                            return BASE_URL;
                        }

                        @Override
                        public String getCommonScope() {
                            return BASE_URL;
                        }

                        @Override
                        public String getHoldingsBffPath() {
                            return BASE_URL;
                        }

                        @Override
                        public String getFundsRedemptionsPath() { return  BASE_URL; }

                        @Override
                        public String getCleanCachePylPath() {
                            return BASE_URL;
                        }

                        @Override
                        public String getCleanCacheHoldingsPath() {
                            return BASE_URL;
                        }
                    })
                    .withOAuthRestTemplate(oauth2RestTemplate).build();
        }
    }

    @Test
    public void getHoldingBff_OK() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/funds/getHoldingBff.json");

        Cliente cliente = new Cliente();
        cliente.setNup(NUP);

        URI uri = URI.create(BASE_URL + "/holdings?nup=" + NUP);
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        HoldingsResponse apiResponse = fundsApi.getHoldingBff(cliente);
        Assert.assertNotNull(apiResponse.getData().getHoldings());
        restServiceServer.verify();
    }

    @Test
    public void getHoldingBff_400() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/funds/getHoldingBff_400.json");

        Cliente cliente = new Cliente();

        URI uri = URI.create(BASE_URL + "/holdings?nup=");
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        try {
            HoldingsResponse apiResponse = fundsApi.getHoldingBff(cliente);
        } catch (ApiException fundsApiException) {
            ErrorResponse errorResponse = fundsApiException.getErrorResponse();
            Assert.assertEquals(HttpStatus.BAD_REQUEST, fundsApiException.getHttpStatus());
            Assert.assertNotNull(errorResponse);
            Assert.assertNull(errorResponse.getErrors());
        }
        restServiceServer.verify();
    }

    @Test
    public void verifyAccessToGetHolding() throws Exception {
        File jsonResponse = TestUtils.getResourceFile("apimocks/funds/verifyAccessToGetHolding.json");

        Cliente cliente = new Cliente();
        cliente.setNup(NUP);

        URI uri = URI.create(BASE_URL + "/access?nup=" + NUP);
        Resource resource = new FileSystemResource(jsonResponse);

        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        Boolean apiResponse = fundsApi.verifyAccessToGetHolding(cliente);
        Assert.assertNotNull(apiResponse);
        Assert.assertFalse(apiResponse);
        restServiceServer.verify();
    }

    @Test
    public void formatCustodyAccount_BP(){
        String accountFormatted = CUSTODY_ACCOUNT_BP.replace("/", "");

        if (accountFormatted.contains("-")) {
            accountFormatted = accountFormatted.split("-")[1];
        }

        accountFormatted = String.format("%08d", Long.valueOf(accountFormatted));

        Assert.assertNotNull(accountFormatted);
        Assert.assertEquals("03646285", accountFormatted);
    }

    @Test
    public void formatCustodyAccount_RTL(){
        String accountFormatted = CUSTODY_ACCOUNT_RTL.replace("/", "");

        if (accountFormatted.contains("-")) {
            accountFormatted = accountFormatted.split("-")[1];
        }

        accountFormatted = String.format("%08d", Long.valueOf(accountFormatted));

        Assert.assertNotNull(accountFormatted);
        Assert.assertEquals("16120105", accountFormatted);
    }

}