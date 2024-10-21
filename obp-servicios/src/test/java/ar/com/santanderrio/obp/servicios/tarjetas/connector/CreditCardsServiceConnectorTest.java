package ar.com.santanderrio.obp.servicios.tarjetas.connector;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.impl.CreditCardsServiceConnectorImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditAccountDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardResponseDto;
import ar.com.santanderrio.obp.servicios.tarjetas.session.CreditCardApiSession;
import ar.com.santanderrio.obp.servicios.tarjetas.session.CreditCardApiSessionTest;
import ar.com.santanderrio.obp.servicios.tarjetas.session.model.CreditCardApiIds;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardsServiceConnectorTest {

    @InjectMocks
    private CreditCardsServiceConnectorImpl connector = new CreditCardsServiceConnectorImpl();

    @Mock
    private CreditCardApiSession creditCardApiSession;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    @Test
    public void testFindCardIdDoesNotExecuteApiCall() throws ConnectorException {
        mockSession();
        String cardID = connector.getCreditCardId(CreditCardApiSessionTest.CARD_NUMBER);
        Assert.assertNotNull(cardID);
        Mockito.verify(restTemplate, Mockito.times(0)).exchange(Mockito.any(String.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.<Class<Object>>any());
    }

    @Test
    public void testCardIdNotStoredIsRequestedFromApi() throws ConnectorException, SQLException {
        mockCredentials();
        mockCardResponse(cardResponse());

        String cardID = connector.getCreditCardId(CreditCardApiSessionTest.CARD_NUMBER);

        Assert.assertNotNull(cardID);
        Assert.assertEquals(CreditCardApiSessionTest.CARD_ID, cardID);
        Mockito.verify(creditCardApiSession, Mockito.times(1)).save(CreditCardApiSessionTest.CARD_NUMBER, CreditCardApiSessionTest.CARD_ID, CreditCardApiSessionTest.ACCOUNT_ID);
    }

    @Test
    public void testFindAccountIdDoesNotExecuteApiCall() throws ConnectorException {
        mockSession();

        String accountId = connector.getCreditAccountId(CreditCardApiSessionTest.CARD_NUMBER);

        Assert.assertNotNull(accountId);
        Assert.assertEquals(CreditCardApiSessionTest.ACCOUNT_ID, accountId);
        Mockito.verify(restTemplate, Mockito.times(0)).exchange(Mockito.any(String.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.<Class<Object>>any());
    }

    @Test
    public void testAccountIdNotStoredIsRequestedFromApi() throws ConnectorException, SQLException {
        mockCredentials();
        mockCardResponse(cardResponse());

        String accountId = connector.getCreditAccountId(CreditCardApiSessionTest.CARD_NUMBER);

        Assert.assertNotNull(accountId);
        Assert.assertEquals(CreditCardApiSessionTest.ACCOUNT_ID, accountId);
        Mockito.verify(creditCardApiSession, Mockito.times(1)).save(CreditCardApiSessionTest.CARD_NUMBER, CreditCardApiSessionTest.CARD_ID, CreditCardApiSessionTest.ACCOUNT_ID);
    }

    @Test
    public void testFindCardWithNullBodyResponseThrowsException() throws SQLException {
        mockCredentials();
        try {
            mockCardResponse(null);
            connector.getCreditCardId(CreditCardApiSessionTest.CARD_NUMBER);
            Assert.fail();
        } catch (Exception ex) {
            assertException(ex);
        }
    }

    @Test
    public void testFindCardWithNullContentResponseThrowsException() throws SQLException {
        mockCredentials();
        try {
            mockCardResponse(new CreditCardResponseDto());
            connector.getCreditCardId(CreditCardApiSessionTest.CARD_NUMBER);
            Assert.fail();
        } catch (Exception ex) {
            assertException(ex);
        }
    }

    @Test
    public void testFindCardWithEmptyContentResponseThrowsException() throws SQLException {
        mockCredentials();
        try {
            mockCardResponse(emptyResponse());
            connector.getCreditCardId(CreditCardApiSessionTest.CARD_NUMBER);
            Assert.fail();
        } catch (Exception ex) {
            assertException(ex);
        }
    }

    private void assertException(Exception ex) {
        Assert.assertTrue(ex instanceof ConnectorException);
        Assert.assertTrue(ex.getMessage().startsWith("Credit-Cards: credit card not found"));
    }

    private void mockSession() {
        Mockito.when(creditCardApiSession.findSession(CreditCardApiSessionTest.CARD_NUMBER)).thenReturn(session());
    }

    private CreditCardApiIds session() {
        CreditCardApiIds session = new CreditCardApiIds();
        session.setCreditCardId(CreditCardApiSessionTest.CARD_ID);
        session.setCreditAccountId(CreditCardApiSessionTest.ACCOUNT_ID);
        return session;
    }

    private void mockCardResponse(CreditCardResponseDto response) {
        ResponseEntity<CreditCardResponseDto> entity = ResponseEntity.ok(response);
        Mockito.when(restTemplate.exchange(Mockito.any(String.class), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class), Mockito.eq(CreditCardResponseDto.class))).thenReturn(entity);
    }

    private CreditCardResponseDto cardResponse() {
        CreditAccountDto accountDto = new CreditAccountDto();
        accountDto.setId(CreditCardApiSessionTest.ACCOUNT_ID);
        CreditCardDto cardDto = new CreditCardDto();
        cardDto.setId(CreditCardApiSessionTest.CARD_ID);
        cardDto.setCreditAccount(accountDto);
        CreditCardDto[] cards = new CreditCardDto[1];
        cards[0] = cardDto;
        CreditCardResponseDto response = new CreditCardResponseDto();
        response.setContent(cards);
        return response;
    }

    private CreditCardResponseDto emptyResponse() {
        CreditCardResponseDto response = new CreditCardResponseDto();
        response.setContent(new CreditCardDto[0]);
        return response;
    }

    private void mockCredentials() throws SQLException {
        Mockito.when(credentialSecurityFactory.getCredentialById(Mockito.any(String.class))).thenReturn(credentials());
    }

    private Credential credentials() {
        Credential credential = new Credential();
        credential.setPassword("password");
        credential.setUsuario("usuario");
        return credential;
    }
}
