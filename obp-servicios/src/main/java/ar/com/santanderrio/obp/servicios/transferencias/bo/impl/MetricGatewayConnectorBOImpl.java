package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricGatewayConnectorBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.BaseMetric;
import ar.com.santanderrio.obp.servicios.transferencias.exception.MetricGatewayConnectorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class MetricGatewayConnectorBOImpl implements MetricGatewayConnectorBO {

    private final String url;

    private final RestTemplate restTemplate;

    @Autowired
    public MetricGatewayConnectorBOImpl(@Value("${TRANSFERENCIAS.METRICAS.URL}") String url, RestTemplate restTemplate) {

        this.url = url;
        this.restTemplate = restTemplate;

    }

    @Override
    public String send(BaseMetric metric) throws MetricGatewayConnectorException {

        try {

            return restTemplate.exchange(url, HttpMethod.POST, getHttpEntity(metric), String.class).getBody();

        } catch (HttpStatusCodeException ex) {

            throw new MetricGatewayConnectorException("[HTTP Status Code] = " + ex.getStatusCode().toString() + " [Body] = " + ex.getResponseBodyAsString());

        } catch (Exception ex) {

            throw new MetricGatewayConnectorException(ex);

        }

    }

    private HttpEntity<BaseMetric> getHttpEntity(BaseMetric metric) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<BaseMetric>(metric, headers);

    }

}
