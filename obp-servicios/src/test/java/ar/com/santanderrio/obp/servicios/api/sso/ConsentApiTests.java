package ar.com.santanderrio.obp.servicios.api.sso;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.consent.ConsentApi;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserConsentRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.sso.consent.entities.UserRepresentationModel;
import ar.com.santanderrio.obp.servicios.api.utils.BufferedMockResponseCreator;
import ar.com.santanderrio.obp.servicios.api.utils.SSOTestsConfigLoader;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;
import ar.com.santanderrio.obp.servicios.oauth2.resource.OAuth2ResourceDetails;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuth2AccessToken;
import ar.com.santanderrio.obp.servicios.oauth2.token.OAuthTokenProvider;
import ar.com.santanderrio.obp.servicios.ws.SSOConsentApiClientConfig;
import ar.com.santanderrio.obp.servicios.ws.SSOConsentManagerContext;
import ar.com.santanderrio.obp.test.utils.TestUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                SSOApiConfigProvider.class,
                SSOConsentManagerContext.class,
                SSOConsentApiClientConfig.class,
                ConsentApiTests.ConsentApiTestsConfig.class
        })
@TestPropertySource( locations = {"classpath:config/files/obp-sso.properties"})
public class ConsentApiTests {
    private static final String NUP = "12345678";
    private static final String USER_ID = "ff35bbfe-f192-4ada-9422-2b625bd98ecb";
    private static final String CONSENTED_CLIENT_ID = "pkce-test";
    private static final OAuth2AccessToken accessToken = new OAuth2AccessToken();

    static {
        accessToken.setAccessToken("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOeTRMY1VORGh5UVdKMkNGbTM1a1ZxakVfN3Q0Ny1IMjdUNEdnY3JaMk9jIn0");
        accessToken.setTokenType("bearer");
        accessToken.setScope("scope");
        accessToken.setExpiration(new Date(2147483647L * 1000));
    }

    OAuthTokenProvider oAuthTokenProvider = Mockito.mock(OAuthTokenProvider.class);

    @Autowired
    ConsentApi consentApi;

    @Autowired
    SSOApiConfig ssoApiConfig;

    MockRestServiceServer restServiceServer;

    @Before
    public void setup() throws IllegalAccessException {
        OAuth2RestTemplate restTemplate =
                (OAuth2RestTemplate) FieldUtils.readField(consentApi, "restTemplate", true);
        FieldUtils.writeField(restTemplate, "oauthTokenProvider", oAuthTokenProvider, true);
        restServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Configuration
    @Import(SSOTestsConfigLoader.class)
    public static class ConsentApiTestsConfig {

        @Bean
        public CredentialSecurityFactory credentialSecurityFactory() throws SQLException {
            Credential credential = new Credential();
            credential.setUsuario("clientId");
            credential.setPassword("clientSecret");

            CredentialSecurityFactory mock = Mockito.mock(CredentialSecurityFactory.class);
            Mockito.when(mock.getCredentialById(Mockito.anyString())).thenReturn(credential);
            return mock;
        }
    }

    @Test
    public void getUserDataByNup_ok() throws IOException {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/users?q=nup:" + NUP;
        File jsonResponse = TestUtils.getResourceFile("apimocks/sso/users/getUsersByNup_200.json");
        Resource resource = new FileSystemResource(jsonResponse);
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        //Execution
        List<UserRepresentationModel> users = consentApi.getUserDataByNup(NUP);
        Assert.assertNotNull(users);
        Assert.assertFalse(users.isEmpty());
        UserRepresentationModel user = users.get(0);
        Assert.assertEquals(SSOAssertions.userModel.getId(), user.getId());
        Assert.assertEquals(SSOAssertions.userModel.getUsername(), user.getUsername());
        Assert.assertNotNull(user.getAttributes());
        Assert.assertTrue(user.getAttributes().containsKey("nup"));
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("nup").get(0), user.getAttributes().get("nup").get(0));
        Assert.assertTrue(user.getAttributes().containsKey("pkce-test"));
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("pkce-test").get(0), user.getAttributes().get("pkce-test").get(0));
        Assert.assertTrue(user.getAttributes().containsKey("cuil"));
        Assert.assertEquals(SSOAssertions.userModel.getAttributes().get("cuil").get(0), user.getAttributes().get("cuil").get(0));
        restServiceServer.verify();
    }

    @Test
    public void getUserDataByNup_error() {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/users?q=nup:" + NUP;
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(""));

        //Execution
        try {
            List<UserRepresentationModel> users = consentApi.getUserDataByNup(NUP);
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
        restServiceServer.verify();
    }

    @Test
    public void getConsentsByUserId_ok() throws IOException {
        String expectedQuery = ssoApiConfig.getBasePath() + "/users/" + USER_ID + "/consents";
        File jsonResponse = TestUtils.getResourceFile("apimocks/sso/users/getUserConsents_200.json");
        Resource resource = new FileSystemResource(jsonResponse);
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        //Execution
        List<UserConsentRepresentationModel> consents = consentApi.getConsentsByUserId(USER_ID);
        Assert.assertNotNull(consents);
        Assert.assertFalse(consents.isEmpty());
        UserConsentRepresentationModel consent = consents.get(0);
        Assert.assertEquals(SSOAssertions.consentModel.getClientId(), consent.getClientId());
        Assert.assertEquals(SSOAssertions.consentModel.getCreatedDate(), consent.getCreatedDate());
        restServiceServer.verify();
    }

    @Test
    public void getConsentsByUserId_error()  {
        String expectedQuery = ssoApiConfig.getBasePath() + "/users/" + USER_ID + "/consents";
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(""));

        //Execution
        try {
            consentApi.getConsentsByUserId(USER_ID);
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
        restServiceServer.verify();
    }

    @Test
    public void revokeConsentByUserId_ok() {
        String expectedQuery = ssoApiConfig.getPcpBasePath() + "/users/" + USER_ID + "/consents/" + CONSENTED_CLIENT_ID;
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.NO_CONTENT).body(""));

        //Execution
        consentApi.revokeConsentByUserId(USER_ID, CONSENTED_CLIENT_ID);
        restServiceServer.verify();
    }

    @Test
    public void revokeConsentByUserId_error() {
        String expectedQuery = ssoApiConfig.getPcpBasePath() + "/users/" + USER_ID + "/consents/" + CONSENTED_CLIENT_ID;
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR).body(""));

        //Execution
        try {
            consentApi.revokeConsentByUserId(USER_ID, CONSENTED_CLIENT_ID);
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
        restServiceServer.verify();
    }

    private void mockAccessToken() {
        Mockito.when(oAuthTokenProvider.requestAccessToken(Mockito.any(OAuth2ResourceDetails.class)))
                .thenReturn(accessToken);
    }
}
