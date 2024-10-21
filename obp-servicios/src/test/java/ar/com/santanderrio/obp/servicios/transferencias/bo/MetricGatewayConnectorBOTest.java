package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.MetricGatewayConnectorBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.BaseMetric;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricType;
import ar.com.santanderrio.obp.servicios.transferencias.exception.MetricGatewayConnectorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MetricGatewayConnectorBOTest {

    @Mock
    private RestTemplate restTemplate;

    private MetricGatewayConnectorBO metricGatewayConnector;

    private final String MGURL = "https://metric-gateway-dev-metric-gateway.apps.ocp.ar.bsch/metrics";

    private final BaseMetric metric = new BaseMetric("TRFIND" ,"obp_transfer_test", 1L, MetricType.COUNTER);

    private final String bodyForSucces = "{\"info\":\"metric seted.\"}";

    @Before
    public void setup(){

        metricGatewayConnector = new MetricGatewayConnectorBOImpl(MGURL, restTemplate);

    }

    @Test(expected = MetricGatewayConnectorException.class)
    public void whenSendThenThrowException() throws MetricGatewayConnectorException {

        when(restTemplate.exchange(MGURL, HttpMethod.POST, getHttpEntityForTest(), String.class)).thenThrow(new RestClientException(""));

        metricGatewayConnector.send(metric);

    }

    @Test
    public void whenSendThenReturnOk() throws MetricGatewayConnectorException {

        when(restTemplate.exchange(MGURL, HttpMethod.POST, getHttpEntityForTest(), String.class))
                .thenReturn(new ResponseEntity<String>(bodyForSucces, HttpStatus.OK));

        String response = metricGatewayConnector.send(metric);

        assertNotNull(response);
        assertTrue(response.contains("metric seted."));

    }

    private HttpEntity<BaseMetric> getHttpEntityForTest() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<BaseMetric>(metric, headers);

    }
}
