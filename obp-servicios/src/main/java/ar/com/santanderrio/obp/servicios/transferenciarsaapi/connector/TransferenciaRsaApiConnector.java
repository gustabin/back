package ar.com.santanderrio.obp.servicios.transferenciarsaapi.connector;

import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationRequest;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationResponse;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class TransferenciaRsaApiConnector implements TransferenciaRsaApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaRsaApiConnector.class);
    public static final String CURRENCY = "currency";
    public static final String DESTINATION_CUIT = "destination_cuit";

    @Autowired
    @Qualifier("restTransferenciaRsaTemplate")
    private RestTemplate restTemplate;

    @Value("${TRANSFERENCIA-API.URL}")
    private String getUrlBase;

    @Value("${TRANSFERENCIA-API.PATH.GET-TRANSACTION}")
    private String getTransactionUrl;
    @Value("${TRANSFERENCIA-API.PATH.POST-TRANSACTION}")
    private String postTransactionUrl;

    @Override
    public TransferenciaSumResponse getTransactionSum(Long nup, String destinationCuit, String currency) {
        LOGGER.info("Inicio TransferenciaRsa.GetTransaction WS call with NUP {}, DESTINATION_CUIT {}", nup, destinationCuit);

        String uri = buildUri(destinationCuit, currency);

        try {
            return restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    new HttpEntity<Void>(null, getHeader(nup)),
                    TransferenciaSumResponse.class).getBody();
        } catch (HttpClientErrorException ex) {
            LOGGER.error("Fallo TransferenciaRsa.GetTransaction WS call with NUP {}, DESTINATION_CUIT {} try to build response", nup, destinationCuit, ex);
            return new TransferenciaSumResponse(nup, destinationCuit);
        } catch (Exception ex) {
            LOGGER.error("Fallo TransferenciaRsa.GetTransaction WS call with NUP {}, DESTINATION_CUIT {}", nup, destinationCuit, ex);
            return new TransferenciaSumResponse(nup, destinationCuit);
        }
    }

    @Override
    public void postTransaction(Long nup, TransferenciaCreationRequest body) {
        LOGGER.info("Inicio TransferenciaRsa.PostTransaction WS call with NUP {}", nup);
        try {
             restTemplate.exchange(
                    buildUrl(postTransactionUrl),
                    HttpMethod.POST,
                    new HttpEntity<TransferenciaCreationRequest>(body, getHeader(nup)),
                     TransferenciaCreationResponse.class);
        } catch (Exception ex) {
            LOGGER.error("Fallo TransferenciaRsa.PostTransaction WS call with NUP {}", nup, ex);
        }
    }

    private String buildUrl(String endpoint) {
        return getUrlBase.concat(endpoint);
    }

    private HttpHeaders getHeader(Long nup) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("nup", String.valueOf(nup));
        return headers;
    }

    private String buildUri(String destinationCuit, String currency) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(DESTINATION_CUIT, destinationCuit);
        return   UriComponentsBuilder
                .fromUriString(getUrlBase)
                .path(getTransactionUrl)
                .queryParam(CURRENCY,currency)
                .buildAndExpand(params)
                .toUriString();
    }

}
