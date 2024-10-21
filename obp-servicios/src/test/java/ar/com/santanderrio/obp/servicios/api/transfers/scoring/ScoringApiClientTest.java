package ar.com.santanderrio.obp.servicios.api.transfers.scoring;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.dto.TokenProviderResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.dto.ScoringApiResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.exception.ScoringApiException;
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

import static org.junit.Assert.*;

public class ScoringApiClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    private ScoringApiClient scoringApi;

    private final String scoringApiUrl = "https://scoring-api.com";

    private final String tokenProviderUrl = "https://tokenprovider.com/token";

    private final String collectionName = "transferencias-token";

    private final String secId = "1991";

    private final String cuit = "271515";

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        scoringApi = new ScoringApiClient(tokenManager, restTemplate, credentialSecurityFactory);
        ReflectionTestUtils.setField(scoringApi, "tokenProviderUrl", tokenProviderUrl);
        ReflectionTestUtils.setField(scoringApi, "scoringApiUrl", scoringApiUrl);
        ReflectionTestUtils.setField(scoringApi, "collectionName", collectionName);
        ReflectionTestUtils.setField(scoringApi, "secId", secId);

    }

    @Test
    public void whenGetSCoring_thenGetScoringOk() throws SQLException, UnsupportedEncodingException {

        getCredentialFromCredentialSecurityFactoryStub();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(Matchers.any(RequestEntity.class), Matchers.eq(ScoringApiResponse.class)))
                .thenReturn(new ResponseEntity<ScoringApiResponse>(getScoringApiResponse() ,HttpStatus.OK));

        float scoring = scoringApi.getScoring(cuit);

        assertEquals(1, scoring, 0);

    }

    @Test(expected = ScoringApiException.class)
    public void whenGetSCoring_thenThrowsScoringApiException() throws SQLException, UnsupportedEncodingException {

        getCredentialFromCredentialSecurityFactoryStub();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(Matchers.any(RequestEntity.class), Matchers.eq(ScoringApiResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        scoringApi.getScoring(cuit);

    }

    private void getTokenFromTokenProviderStub() throws UnsupportedEncodingException {

        Mockito.when(tokenManager.getToken(tokenProviderUrl, getCredential()))
                .thenReturn(new Token(getTokenProviderResponse()));

    }

    private TokenProviderResponse getTokenProviderResponse() {

        TokenProviderResponse response = new TokenProviderResponse();

        response.setAccessToken("ey123");
        response.setExpiresIn(1800);

        return response;

    }

    private void getCredentialFromCredentialSecurityFactoryStub() throws SQLException {

        Mockito.when(credentialSecurityFactory.getCredentialById(secId))
                .thenReturn(getCredential());

    }

    private Credential getCredential() {

        Credential credential = new Credential();

        credential.setUsuario("user");
        credential.setPassword("123");

        return credential;

    }

    private ScoringApiResponse getScoringApiResponse() {

        ScoringApiResponse response = new ScoringApiResponse();

        response.setScore(1);

        return response;

    }

}