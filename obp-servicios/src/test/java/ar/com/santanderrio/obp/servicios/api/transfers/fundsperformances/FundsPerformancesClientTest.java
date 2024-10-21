package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.common.config.iatx.IatxObpContext;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.dto.TokenProviderResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto.FundsPerformancesResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FundsPerformancesClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Mock
    private IatxObpContext iatxObpContext;


    private FundsPerformancesClient fundsPerformancesClient;


    private final String fundsBasePath = "https://funds-api.com";


    private final String oauthUrl = "https://token-oauth.com";


    private final String oauthTokenPath = "/token";


    private final String secId = "2139";

    private final String iatxSecId = "1234";

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        fundsPerformancesClient = new FundsPerformancesClient(tokenManager, restTemplate, credentialSecurityFactory, iatxObpContext);
        ReflectionTestUtils.setField(fundsPerformancesClient, "fundsBasePath", fundsBasePath);
        ReflectionTestUtils.setField(fundsPerformancesClient, "oauthUrl", oauthUrl);
        ReflectionTestUtils.setField(fundsPerformancesClient, "oauthTokenPath", oauthTokenPath);
        ReflectionTestUtils.setField(fundsPerformancesClient, "secId", secId);

    }

    @Test
    public void whenGetPerformancesById_thenResponseOK() throws SQLException, UnsupportedEncodingException {

        getCredentialFromCredentialSecurityFactoryStub();

        getCredentialIatxFromCredentialSecurityFactoryStub();

        getIatxSecId();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(Matchers.any(RequestEntity.class), Matchers.eq(FundsPerformancesResponse.class)))
                .thenReturn(new ResponseEntity<FundsPerformancesResponse>(FundsFixtureObject.getFundsPerformancesResponse() , HttpStatus.OK));


        FundsPerformancesResponse fundsPerformancesResponse = fundsPerformancesClient.getPerformancesById("6");

        Assert.assertEquals("123%", fundsPerformancesResponse.getResults().get(0).getAnnualizedLast30Day());
        Assert.assertEquals(1, fundsPerformancesResponse.getTotal());
        Assert.assertEquals("2024-05-07T17:58:43-03:00", fundsPerformancesResponse.getCurrentDate());

        Mockito.verify(tokenManager, Mockito.times(1)).getToken(oauthUrl + oauthTokenPath, getCredential());
        Mockito.verify(credentialSecurityFactory, Mockito.times(1)).getCredentialById(secId);
        Mockito.verify(credentialSecurityFactory, Mockito.times(1)).getCredentialById(iatxSecId);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(Matchers.any(RequestEntity.class), Matchers.eq(FundsPerformancesResponse.class));
        Mockito.verify(iatxObpContext, Mockito.times(1)).getSecurityId();

    }

    @Test( expected = FundsPerformancesException.class)
    public void whenGetPerformancesById_thenThrowFundsPerformancesException() throws SQLException, UnsupportedEncodingException {

        getCredentialFromCredentialSecurityFactoryStub();

        getCredentialIatxFromCredentialSecurityFactoryStub();

        getIatxSecId();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(Matchers.any(RequestEntity.class), Matchers.eq(FundsPerformancesResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        fundsPerformancesClient.getPerformancesById("6");
    }


    private void getCredentialFromCredentialSecurityFactoryStub() throws SQLException {

        Mockito.when(credentialSecurityFactory.getCredentialById(secId))
                .thenReturn(getCredential());

    }

    private void getCredentialIatxFromCredentialSecurityFactoryStub() throws SQLException {

        Mockito.when(credentialSecurityFactory.getCredentialById(iatxSecId))
                .thenReturn(getCredentialIatx());

    }

    private Credential getCredential() {

        Credential credential = new Credential();

        credential.setUsuario("user");
        credential.setPassword("123");

        return credential;

    }

    private Credential getCredentialIatx() {

        Credential credential = new Credential();

        credential.setUsuario("userIatx");
        credential.setPassword("passIatx");

        return credential;

    }

    private void getIatxSecId() {

        Mockito.when(iatxObpContext.getSecurityId()).thenReturn(iatxSecId);
    }

    private void getTokenFromTokenProviderStub() throws UnsupportedEncodingException {

        String tokenUrl = oauthUrl + oauthTokenPath;

        Mockito.when(tokenManager.getToken(tokenUrl, getCredential()))
                .thenReturn(new Token(getTokenProviderResponse()));

    }

    private TokenProviderResponse getTokenProviderResponse() {

        TokenProviderResponse response = new TokenProviderResponse();

        response.setAccessToken("ey123");
        response.setExpiresIn(1800);

        return response;

    }
}
