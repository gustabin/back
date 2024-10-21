package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.MetricRegisterBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.BaseMetric;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricType;
import ar.com.santanderrio.obp.servicios.transferencias.exception.MetricGatewayConnectorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MetricRegisterBOTest {

    @Mock
    private MetricGatewayConnectorBO metricGatewayConnector;

    @InjectMocks
    private MetricRegisterBOImpl metricRegisterBO;

    private final BaseMetric metric = new BaseMetric("TRFIND" ,"obp_transfer_test", 2L, MetricType.COUNTER);

    @Test
    public void whenRegisterMetricGatewayConnectorIsCalled() throws MetricGatewayConnectorException {

        metricRegisterBO.register(metric);
        verify(metricGatewayConnector, times(1)).send(metric);

    }

}
