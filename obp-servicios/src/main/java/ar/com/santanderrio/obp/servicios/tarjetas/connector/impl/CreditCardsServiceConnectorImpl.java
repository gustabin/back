package ar.com.santanderrio.obp.servicios.tarjetas.connector.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.CreditCardsServiceConnector;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.exception.ConnectorException;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.creditcard.CreditCardResponseDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastSettlements.CreditCardLastSettlementDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.lastmovements.CreditCardLastMovementsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.connector.models.limits.LimitsAndTotalsDto;
import ar.com.santanderrio.obp.servicios.tarjetas.session.CreditCardApiSession;
import ar.com.santanderrio.obp.servicios.tarjetas.session.model.CreditCardApiIds;

@Component
public class CreditCardsServiceConnectorImpl implements CreditCardsServiceConnector {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardsServiceConnectorImpl.class);
	private static final String ACTUAL_PERIOD = "actual";
	private static final String MOVEMENTS = "/v1/credit-cards/{id}/movements";
	private static final String CREDIT_CARDS = "/v1/credit-cards";
	private static final String CREDIT_CARDS_V2 = "/v2/credit-cards";
	private static final String SETTLEMENT = "/v1/credit-cards/{id}/settlements?period={period}";
	private static final String LIMITS_AND_TOTALS_V2 = "/v2/credit-cards/{id}/limits";
	private static final String ORIGIN = "x-api-origin";
	private static final String VALUE_ORIGIN = "obp-back";

	@Value("${CREDIT-CARDS-API.LIMITS.OCP}")
	private boolean limitsActivated;

	@Value("${CREDIT-CARDS-API.MOVEMENTS.OCP}")
	private boolean movementsActivated;

	@Value("${CREDIT-CARDS-API.SETTLEMENTS.OCP}")
	private boolean settlementsActivated;

	@Value("${CREDIT-CARDS-API.URL}")
	private String creditCardsApiUrl;

	@Value("${CREDIT-CARDS-API.SEC.ID}")
	private String creditCardId;

	@Autowired
	private RestTemplate restTemplate;
	
	/** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

	@Autowired
	private CreditCardApiSession creditCardApiSession;

	private HttpEntity<String> getRequest(Credential credential) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			String auth = credential.getUsuario() + ":" + credential.getPassword();
			String encodedAuth = DatatypeConverter.printBase64Binary(auth.getBytes("UTF-8"));
			headers.set("Authorization", "Basic " + encodedAuth);
			headers.set(ORIGIN, VALUE_ORIGIN);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("No se pudo encodear a base 64 las credenciales de credit-cards-api.", e);
		} 
		return new HttpEntity<String>("parameters", headers);
	}

	private Credential getCredentials() {
		Credential credential = new Credential();
		try{
			credential = credentialSecurityFactory.getCredentialById(this.creditCardId);
		}catch (SQLException e) {
			LOGGER.error("No se pudo acceder a la base de seguridad");
		}
		return credential;
	}

	private String buildUrl(String endpoint) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(creditCardsApiUrl)
				.append(endpoint);
		return urlBuilder.toString();
	}

	private Map<String, String> getParams(Long cardNumber) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(cardNumber));
		return params;
	}

	private Map<String, String> getParams(String creditCardId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(creditCardId));
		return params;
	}

	@Override
	public CreditCardLastMovementsDto getCreditCardLastMovements(String cardNumber) throws ConnectorException {
		if (!movementsActivated) throw new ConnectorException("Credit-Cards: lastMovements service OCP disabled.");

		CreditCardLastMovementsDto movements = null;
		Credential credential = getCredentials();

		try {
			LOGGER.info("Consultando los ultimos movimientos para la tarjeta {}", cardNumber);
			long startTime = System.currentTimeMillis();
			ResponseEntity<CreditCardLastMovementsDto> response = restTemplate.exchange(
					this.buildUrl(MOVEMENTS),
					HttpMethod.GET,
					this.getRequest(credential),
					CreditCardLastMovementsDto.class,
					this.getParams(Long.valueOf(cardNumber)));
			long elapsedTime = System.currentTimeMillis() - startTime;
			movements = response.getBody();
			logRequestInformation(elapsedTime, response.getStatusCode(), response.getBody().toString());
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando los ultimos movimientos.", e);
			throw new ConnectorException(e.getMessage());
		}
		return movements;
	}

	@Override
	public List<CreditCardDto> getAllCreditCardsByAccountNup(String nup) throws ConnectorException {
		List<CreditCardDto> creditCards = null;
		try {
			LOGGER.info("Consultando tarjetas de credito para cuentas asociadas a nup {}", nup);
			long startTime = System.currentTimeMillis();
			ResponseEntity<CreditCardDto[]> response = restTemplate.exchange(
					this.buildUrl(CREDIT_CARDS)+"?creditAccountNup="+nup,
					HttpMethod.GET,
					this.getRequest(getCredentials()),
					CreditCardDto[].class);
			long elapsedTime = System.currentTimeMillis() - startTime;
			creditCards = Arrays.asList(response.getBody());
			logRequestInformation(elapsedTime, response.getStatusCode(), Arrays.toString(response.getBody()));
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando las tarjetas.", e);
			throw new ConnectorException(e.getMessage());
		}
		return creditCards;
	}
	
	public List<CreditCardDto> getAllCreditCardsByNup(String nup) throws ConnectorException {
		List<CreditCardDto> creditCards = null;
		try {
			LOGGER.info("Consultando tarjetas de credito asociadas a nup {}", nup);
			long startTime = System.currentTimeMillis();
			ResponseEntity<CreditCardDto[]> response = restTemplate.exchange(
					this.buildUrl(CREDIT_CARDS)+"?nup="+nup,
					HttpMethod.GET,
					this.getRequest(getCredentials()),
					CreditCardDto[].class);
			long elapsedTime = System.currentTimeMillis() - startTime;
			creditCards = Arrays.asList(response.getBody());
			logRequestInformation(elapsedTime, response.getStatusCode(), Arrays.toString(response.getBody()));
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando las tarjetas por nup.", e);
			throw new ConnectorException(e.getMessage());
		}
		return creditCards;
	}

	@Override
	public CreditCardLastSettlementDto getCreditCardLastSettlement(String cardNumber) throws ConnectorException {
		if (!settlementsActivated) throw new ConnectorException("Credit-Cards: lastSettlements service OCP disabled.");

		CreditCardLastSettlementDto settlements = null;
		Credential credential = getCredentials();
		Map<String, String> parameters = getParams(Long.valueOf(cardNumber));
		parameters.put("period", ACTUAL_PERIOD);
		
		try {
			LOGGER.info("Consultando ultimo resumen para la tarjeta {}", cardNumber);
			long startTime = System.currentTimeMillis();
			ResponseEntity<CreditCardLastSettlementDto> response = restTemplate.exchange(
					this.buildUrl(SETTLEMENT),
					HttpMethod.GET,
					this.getRequest(credential),
					CreditCardLastSettlementDto.class,
					parameters);
			long elapsedTime = System.currentTimeMillis() - startTime;
			settlements = response.getBody();
			logRequestInformation(elapsedTime, response.getStatusCode(), response.getBody().toString());
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando el ultimo resumen.", e);
			throw new ConnectorException(e.getMessage());
		}
		return settlements;
	}

	@Override
	public String getCreditCardId(String cardNumber) throws ConnectorException {
		CreditCardApiIds session = creditCardApiSession.findSession(cardNumber);
		return (session == null) ? findAndStoreCreditCard(cardNumber).getId() : session.getCreditCardId();
	}

	@Override
	public String getCreditAccountId(String cardNumber) throws ConnectorException {
		CreditCardApiIds session = creditCardApiSession.findSession(cardNumber);
		return (session == null) ? findAndStoreCreditCard(cardNumber).getCreditAccount().getId() : session.getCreditAccountId();
	}

	private CreditCardDto findAndStoreCreditCard(String cardNumber) throws ConnectorException {
		try {
			String url = String.format("%s?cardNumber=%s", this.buildUrl(CREDIT_CARDS_V2), cardNumber);
			ResponseEntity<CreditCardResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, this.getRequest(getCredentials()), CreditCardResponseDto.class);

			if(response.getBody() == null || response.getBody().getContent() == null || response.getBody().getContent().length == 0) {
				throw new ConnectorException("Credit-Cards: credit card not found.");
			}

			CreditCardDto cardDto = response.getBody().getContent()[0];
			creditCardApiSession.save(cardNumber, cardDto.getId(), cardDto.getCreditAccount().getId());
			return cardDto;
		} catch (Exception e) {
			throw new ConnectorException(e.getMessage());
		}
	}

	@Override
	public LimitsAndTotalsDto getCreditCardLimits(String id) throws ConnectorException {
		LimitsAndTotalsDto limitsAndTotals = null;
		try {
			LOGGER.info("Getting limits and totals for credit card {}", id);
			long startTime = System.currentTimeMillis();
			ResponseEntity<LimitsAndTotalsDto> response = restTemplate.exchange(
					this.buildUrl(LIMITS_AND_TOTALS_V2),
					HttpMethod.GET,
					this.getRequest(getCredentials()),
					LimitsAndTotalsDto.class,
					this.getParams(id));
			long elapsedTime = System.currentTimeMillis() - startTime;
			limitsAndTotals = response.getBody();
			logRequestInformation(elapsedTime, response.getStatusCode(), response.getBody().toString());
		}
		catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando limites y totales.", e);
			throw new ConnectorException(e.getMessage());
		}
		return limitsAndTotals;
	}

	private List<CreditCardDto> getCreditCardByNumber(Credential credentials, String cardNumber) throws ConnectorException {
		List<CreditCardDto> creditCards = null;
		try {
			LOGGER.info("Consultando tarjetas de credito {}", cardNumber);
			long startTime = System.currentTimeMillis();
			ResponseEntity<CreditCardDto[]> response = restTemplate.exchange(
					this.buildUrl(CREDIT_CARDS)+"?cardNumber="+cardNumber,
					HttpMethod.GET,
					this.getRequest(credentials),
					CreditCardDto[].class);
			long elapsedTime = System.currentTimeMillis() - startTime;
			creditCards = Arrays.asList(response.getBody());
			logRequestInformation(elapsedTime, response.getStatusCode(), Arrays.toString(response.getBody()));
		} catch (Exception e) {
			LOGGER.error("Ocurrio un error recuperando las tarjetas por nup.", e);
			throw new ConnectorException(e.getMessage());
		}
		return creditCards;
	}

	private void logRequestInformation(long elapsedTime, HttpStatus statusCode, String body) {
		LOGGER.info("REST LIMITS- Response Time: {}ms", elapsedTime );
		LOGGER.info("REST LIMITS- Response Status Code: {}", statusCode);
		LOGGER.info("REST LIMITS- Response body: {}", body);
	}
}
