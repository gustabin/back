package ar.com.santanderrio.obp.servicios.api.transfers.recipients;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.api.transfers.common.TokenManager;
import ar.com.santanderrio.obp.servicios.api.transfers.common.dto.TokenProviderResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.dto.RecipientsResponse;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.exception.RecipientsApiException;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.model.Recipient;
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

public class RecipientsApiClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TokenManager tokenManager;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    private RecipientsApiClient recipientsApi;

    private final String basePath = "https://recipl-api-dev-recipl-api.apps.ocp.ar.bsch";

    private final String tokenProviderUrl = "https://provider-dev.apps.ocp.ar.bsch";

    private final String nup = "12345";

    private final String token = "ey123";

    private final String secId = "1991";

    private final String secret = "password";

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        recipientsApi = new RecipientsApiClient(restTemplate, tokenManager, credentialSecurityFactory);
        ReflectionTestUtils.setField(recipientsApi, "recipientsToFetchLimit", 1);
        ReflectionTestUtils.setField(recipientsApi, "basePath", basePath);
        ReflectionTestUtils.setField(recipientsApi, "secId", secId);
        ReflectionTestUtils.setField(recipientsApi, "tokenProviderUrl", tokenProviderUrl);

    }

    @Test
    public void givenAFetchLimitOfOne_whenGetAllRecipients_ThenReturnsRecipientListOfSizeOneAndRestTemplateIsCalledOnce() throws UnsupportedEncodingException, SQLException {

        getCredentialFromCredentialSecurityFactoryStub();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(
                Matchers.any(RequestEntity.class),
                Matchers.eq(RecipientsResponse.class)
        )).thenReturn(new ResponseEntity<RecipientsResponse>(getRecipientResponseWithTotalRecipientsSetToOne(), HttpStatus.OK));

        List<Recipient> recipients = recipientsApi.getAllRecipients(nup);

        Assert.assertNotNull(recipients);
        Assert.assertEquals(1, recipients.size());
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(Matchers.any(RequestEntity.class), Matchers.eq(RecipientsResponse.class));

    }

    @Test
    public void givenAFetchLimitOfOne_whenGetAllRecipients_ThenReturnsRecipientListOfSizeTwoAndRestTemplateIsCalledTwice() throws UnsupportedEncodingException, SQLException {

        getCredentialFromCredentialSecurityFactoryStub();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(
                Matchers.any(RequestEntity.class),
                Matchers.eq(RecipientsResponse.class)
        )).thenReturn(new ResponseEntity<RecipientsResponse>(getRecipientResponseWithTotalRecipientsSetToTwo(), HttpStatus.OK));

        List<Recipient> recipients = recipientsApi.getAllRecipients(nup);

        Assert.assertNotNull(recipients);
        Assert.assertEquals(2, recipients.size());
        Mockito.verify(restTemplate, Mockito.times(2)).exchange(Matchers.any(RequestEntity.class), Matchers.eq(RecipientsResponse.class));

    }

    @Test(expected = RecipientsApiException.class)
    public void whenGetAllRecipients_ThenThrowsRecipientsApiException() throws UnsupportedEncodingException, SQLException {

        getCredentialFromCredentialSecurityFactoryStub();

        getTokenFromTokenProviderStub();

        Mockito.when(restTemplate.exchange(
                Matchers.any(RequestEntity.class),
                Matchers.eq(RecipientsResponse.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        recipientsApi.getAllRecipients(nup);

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

    private RecipientsResponse getRecipientResponseWithTotalRecipientsSetToOne() {

        RecipientsResponse response = new RecipientsResponse();
        response.setLimit(1);
        response.setOffset(1);
        List<Recipient> recipients = new ArrayList<Recipient>();
        recipients.add(new Recipient());
        response.setRecipients(recipients);
        response.setTotal(1);

        return response;

    }

    private RecipientsResponse getRecipientResponseWithTotalRecipientsSetToTwo() {

        RecipientsResponse response = new RecipientsResponse();
        response.setLimit(1);
        response.setOffset(1);
        List<Recipient> recipients = new ArrayList<Recipient>();
        recipients.add(new Recipient());
        response.setRecipients(recipients);
        response.setTotal(2);

        return response;

    }

}
