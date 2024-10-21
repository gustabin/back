package ar.com.santanderrio.obp.servicios.api.sso;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.sso.client.SSOClientsApi;
import ar.com.santanderrio.obp.servicios.api.sso.client.entities.ClientRepresentationModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                SSOApiConfigProvider.class,
                SSOConsentManagerContext.class,
                SSOConsentApiClientConfig.class,
                SSOClientsApiTests.SSOClientsApiTestConfig.class
        })
@TestPropertySource( locations = {"classpath:config/files/obp-sso.properties"})
public class SSOClientsApiTests {
    private static final OAuth2AccessToken accessToken = new OAuth2AccessToken();
    private static final String CLIENT_ID = "pkce-test";
    private static final String ID = "33a9b634-87be-482f-988b-dfb273912b34";
    private static final Map<String, String> ATTRIBUTES_QUERY = new HashMap<String, String>();

    static {
        accessToken.setAccessToken("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOeTRMY1VORGh5UVdKMkNGbTM1a1ZxakVfN3Q0Ny1IMjdUNEdnY3JaMk9jIn0");
        accessToken.setTokenType("bearer");
        accessToken.setScope("scope");
        accessToken.setExpiration(new Date(2147483647L * 1000));
        ATTRIBUTES_QUERY.put("client_type", "wallet");
    }

    OAuthTokenProvider oAuthTokenProvider = Mockito.mock(OAuthTokenProvider.class);

    @Autowired
    SSOClientsApi ssoClientsApi;

    @Autowired
    SSOApiConfig ssoApiConfig;

    MockRestServiceServer restServiceServer;

    @Before
    public void setup() throws IllegalAccessException {
        OAuth2RestTemplate restTemplate = (OAuth2RestTemplate) FieldUtils.readField(ssoClientsApi, "restTemplate", true);
        FieldUtils.writeField(restTemplate, "oauthTokenProvider", oAuthTokenProvider, true);
        restServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Configuration
    @Import(SSOTestsConfigLoader.class)
    public static class SSOClientsApiTestConfig {
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
    public void getClients() throws IOException {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients";
        URI uri = URI.create(expectedQuery);
        File jsonResponse = TestUtils.getResourceFile("apimocks/sso/clients/getClients_200.json");
        Resource resource = new FileSystemResource(jsonResponse);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        //Execution
        List<ClientRepresentationModel> clients = ssoClientsApi.getClients();
        Assert.assertNotNull(clients);
        Assert.assertFalse(clients.isEmpty());
        ClientRepresentationModel client = getPkceClient(clients);
        Assert.assertNotNull(client);
        assertClient(client);
        restServiceServer.verify();
    };

    @Test
    public void getClients_error() {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients";
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(""));

        //Execution
        //Execution
        try {
            ssoClientsApi.getClients();
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
    };

    @Test
    public void getClientByClientId() throws IOException {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients?clientId=" + CLIENT_ID;
        URI uri = URI.create(expectedQuery);
        File jsonResponse = TestUtils.getResourceFile("apimocks/sso/clients/getClientsByClientId_200.json");
        Resource resource = new FileSystemResource(jsonResponse);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        //Execution
        List<ClientRepresentationModel> clients = ssoClientsApi.getClientByClientId(CLIENT_ID);
        Assert.assertNotNull(clients);
        Assert.assertFalse(clients.isEmpty());
        Assert.assertEquals("Client result size should be 1", 1, clients.size());
        assertClient(clients.get(0));
        restServiceServer.verify();
    };

    @Test
    public void getClientByClientId_error() {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients?clientId=" + CLIENT_ID;
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(""));

        //Execution
        //Execution
        try {
            ssoClientsApi.getClientByClientId(CLIENT_ID);
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
        restServiceServer.verify();
    };

    @Test
    public void getClientById() throws IOException {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients/" + ID;
        URI uri = URI.create(expectedQuery);
        File jsonResponse = TestUtils.getResourceFile("apimocks/sso/clients/getClientsById_200.json");
        Resource resource = new FileSystemResource(jsonResponse);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource));

        //Execution
        ClientRepresentationModel client = ssoClientsApi.getClientById(ID);
        assertClient(client);
        restServiceServer.verify();
    };

    @Test
    public void getClientById_error() {
        //setup
        String expectedQuery = ssoApiConfig.getBasePath() + "/clients/" + ID;
        URI uri = URI.create(expectedQuery);

        mockAccessToken();
        restServiceServer.expect(requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(new BufferedMockResponseCreator(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(""));

        //Execution
        try {
            ssoClientsApi.getClientById(ID);
        } catch (ApiException exception) {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
            Assert.assertEquals("ERROR", exception.getErrorResponse().getCode());
        }
        restServiceServer.verify();
    };

    private void mockAccessToken() {
        Mockito.when(oAuthTokenProvider.requestAccessToken(Mockito.any(OAuth2ResourceDetails.class)))
                .thenReturn(accessToken);
    }

    private void assertClient(ClientRepresentationModel client) {
        Assert.assertNotNull(client);
        Assert.assertEquals(SSOAssertions.clientModel.getClientId(), client.getClientId());
        Assert.assertEquals(SSOAssertions.clientModel.getName(), client.getName());
        Assert.assertEquals(SSOAssertions.clientModel.getId(), client.getId());
        Assert.assertNotNull(SSOAssertions.clientModel.getAttributes());
        Assert.assertEquals(SSOAssertions.clientModel.getAttributes().get("client_type"), client.getAttributes().get("client_type"));
    }

    private ClientRepresentationModel getPkceClient(List<ClientRepresentationModel> clients) {
        for(ClientRepresentationModel client: clients) {
            if ("pkce-test".equals(client.getClientId())) {
                return client;
            }
        }
        return null;
    }
}
