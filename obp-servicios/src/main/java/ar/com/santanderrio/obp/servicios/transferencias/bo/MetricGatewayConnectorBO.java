package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.BaseMetric;
import ar.com.santanderrio.obp.servicios.transferencias.exception.MetricGatewayConnectorException;

public interface MetricGatewayConnectorBO {

    String send(BaseMetric metric) throws MetricGatewayConnectorException;

}
