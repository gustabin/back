package ar.com.santanderrio.obp.servicios.transferenciarsaapi;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.connector.TransferenciaRsaApiConnector;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationRequest;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationResponse;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TransferenciaRsaApiConnectorTest {

    @InjectMocks private TransferenciaRsaApiConnector apiConnector;
    @Mock private RestTemplate restTemplate;

    private final String getUrlBase = "http://transferencia-rsa:8080/api";
    private final String getTransactionUrl = "/transactions/destinations/{destination_cuit}";
    private final String postTransactionUrl = "/transactions/destinations";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(apiConnector, "getUrlBase", getUrlBase);
        ReflectionTestUtils.setField(apiConnector, "getTransactionUrl", getTransactionUrl);
        ReflectionTestUtils.setField(apiConnector, "postTransactionUrl", postTransactionUrl);
    }

    @Test
    public void whenGetTransactionSum_withCurrencyPeso_ThenOk() {

        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setMoneda("peso");

        final Long nup = 123456L;
        final String destinationCuit = "20-32932932-1";
        final TransferenciaSumResponse response = this.buildSumResponse();

        final String url = buildUrlWithCurrencyPeso(destinationCuit);

        Mockito.when(restTemplate.exchange(
                Matchers.eq(url),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<Void>>any(),
                Matchers.eq(TransferenciaSumResponse.class)
        )).thenReturn(ResponseEntity.ok(response));

        final TransferenciaSumResponse result = apiConnector.getTransactionSum(nup, destinationCuit, DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo());

        Mockito.verify(restTemplate, Mockito.times(1))
                        .exchange(
                                Matchers.eq(url),
                                Matchers.eq(HttpMethod.GET),
                                Matchers.<HttpEntity<Void>>any(),
                                Matchers.eq(TransferenciaSumResponse.class)
                        );

        Assert.assertNotNull(result);
        Assert.assertEquals(nup, result.getNup());
        Assert.assertEquals(destinationCuit, result.getDestinationCuit());
        Assert.assertEquals(new Integer(4), result.getDestinationCuitQuantity());
        Assert.assertEquals(new BigDecimal(1203), result.getDestinationCuitAmount());
        Assert.assertEquals(new Integer(6), result.getNupQuantity());
        Assert.assertEquals(new BigDecimal(2532), result.getNupAmount());

    }

    @Test
    public void whenGetTransactionSum_withCurrencyDollar_ThenOk() {

        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setMoneda("dolar");

        final Long nup = 123456L;
        final String destinationCuit = "20-32932932-1";
        final TransferenciaSumResponse response = this.buildSumResponse();

        final String url = buildUrlWithCurrencyDollar(destinationCuit);

        Mockito.when(restTemplate.exchange(
                Matchers.eq(url),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<Void>>any(),
                Matchers.eq(TransferenciaSumResponse.class)
        )).thenReturn(ResponseEntity.ok(response));

        final TransferenciaSumResponse result = apiConnector.getTransactionSum(nup, destinationCuit, DivisaEnum.fromMonedaString(transferenciaView.getMoneda()).getCodigo());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.GET),
                        Matchers.<HttpEntity<Void>>any(),
                        Matchers.eq(TransferenciaSumResponse.class)
                );

        Assert.assertNotNull(result);
        Assert.assertEquals(nup, result.getNup());
        Assert.assertEquals(destinationCuit, result.getDestinationCuit());
        Assert.assertEquals(new Integer(4), result.getDestinationCuitQuantity());
        Assert.assertEquals(new BigDecimal(1203), result.getDestinationCuitAmount());
        Assert.assertEquals(new Integer(6), result.getNupQuantity());
        Assert.assertEquals(new BigDecimal(2532), result.getNupAmount());

    }

    @Test
    public void whenGetTransactionSumThenHttpClientErrorException() {

        final Long nup = 123456L;
        final String destinationCuit = "20-32932932-1";
        final String url = buildUrlWithCurrencyPeso(destinationCuit);

        Mockito.when(restTemplate.exchange(
                Matchers.eq(url),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<Void>>any(),
                Matchers.eq(TransferenciaSumResponse.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));


        final TransferenciaSumResponse result = apiConnector.getTransactionSum(nup, destinationCuit, "ARS");

        Mockito.verify(restTemplate, Mockito.times(1))
                        .exchange(Matchers.eq(url),
                                Matchers.eq(HttpMethod.GET),
                                Matchers.<HttpEntity<Void>>any(),
                                Matchers.eq(TransferenciaSumResponse.class)
                        );

        Assert.assertNotNull(result);
        Assert.assertEquals(nup, result.getNup());
        Assert.assertEquals(destinationCuit, result.getDestinationCuit());
        Assert.assertEquals(BigDecimal.ZERO, result.getNupAmount());
        Assert.assertEquals(BigDecimal.ZERO, result.getDestinationCuitAmount());

    }

    @Test
    public void whenGetTransactionSumThenException() {

        final Long nup = 123456L;
        final String destinationCuit = "20-32932932-1";
        final String url = buildUrlWithCurrencyPeso(destinationCuit);


        Mockito.when(restTemplate.exchange(
                Matchers.eq(url),
                Matchers.eq(HttpMethod.GET),
                Matchers.<HttpEntity<Void>>any(),
                Matchers.eq(TransferenciaSumResponse.class)
        )).thenThrow(new RuntimeException("Ocurrido lo peor"));

        final TransferenciaSumResponse result = apiConnector.getTransactionSum(nup, destinationCuit, "ARS");

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.GET),
                        Matchers.<HttpEntity<Void>>any(),
                        Matchers.eq(TransferenciaSumResponse.class)
                );

        Assert.assertNotNull(result);
        Assert.assertEquals(nup, result.getNup());
        Assert.assertEquals(destinationCuit, result.getDestinationCuit());
        Assert.assertEquals(BigDecimal.ZERO, result.getNupAmount());
        Assert.assertEquals(BigDecimal.ZERO, result.getDestinationCuitAmount());

    }

    @Test
    public void whenPostTransactionThenOk()  {

        final Long nup = 123456L;
        final TransferenciaCreationRequest request = new TransferenciaCreationRequest(
                new BigDecimal(2010),
                "2022-03-01 12:21:12",
                "20-32932932-1",
                "ARS"
        );

        final String url = getUrlBase.concat(postTransactionUrl);

        Mockito.when(restTemplate
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.POST),
                        Matchers.<HttpEntity<TransferenciaCreationRequest>>any(),
                        Matchers.eq(TransferenciaCreationResponse.class)
                )).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(buildCreationResponse()));

        apiConnector.postTransaction(nup, request);

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.POST),
                        Matchers.<HttpEntity<TransferenciaCreationRequest>>any(),
                        Matchers.eq(TransferenciaCreationResponse.class)
                );

    }

    @Test
    public void whenPostTransactionThenException()  {

        final Long nup = 123456L;
        final TransferenciaCreationRequest request = new TransferenciaCreationRequest(
                new BigDecimal(2010),
                "2022-03-01 12:21:12",
                "20-32932932-1",
                "ARS"
        );

        final String url = getUrlBase.concat(postTransactionUrl);

        Mockito.when(restTemplate
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.POST),
                        Matchers.<HttpEntity<TransferenciaCreationRequest>>any(),
                        Matchers.eq(TransferenciaCreationResponse.class)
                )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        apiConnector.postTransaction(nup, request);

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Matchers.eq(url),
                        Matchers.eq(HttpMethod.POST),
                        Matchers.<HttpEntity<TransferenciaCreationRequest>>any(),
                        Matchers.eq(TransferenciaCreationResponse.class));


    }

    private TransferenciaSumResponse buildSumResponse() {
        TransferenciaSumResponse response = new TransferenciaSumResponse();
        response.setNup(123456L);
        response.setDestinationCuit("20-32932932-1");
        response.setDestinationCuitQuantity(4);
        response.setDestinationCuitAmount(new BigDecimal(1203));
        response.setNupQuantity(6);
        response.setNupAmount(new BigDecimal(2532));
        return response;
    }

    private TransferenciaCreationResponse buildCreationResponse() {
        TransferenciaCreationResponse response = new TransferenciaCreationResponse();
        response.setId("e060f263-2f37-4b42-84a6-f102f4af44b3");
        response.setNup(123456L);
        response.setDestinationCuit("20-32932932-1");
        response.setCurrency("ARS");

        return response;
    }

    private String buildUrlWithCurrencyPeso(String destinationCuit) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("destination_cuit", destinationCuit);

        return UriComponentsBuilder
                .fromUriString(getUrlBase)
                .path(getTransactionUrl)
                .queryParam("currency", "ARS")
                .buildAndExpand(params)
                .toUriString();
    }

    private String buildUrlWithCurrencyDollar(String destinationCuit) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("destination_cuit", destinationCuit);

        return UriComponentsBuilder
                .fromUriString(getUrlBase)
                .path(getTransactionUrl)
                .queryParam("currency", "USD")
                .buildAndExpand(params)
                .toUriString();
    }

}


