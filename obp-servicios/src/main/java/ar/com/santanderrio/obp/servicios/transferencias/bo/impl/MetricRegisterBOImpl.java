package ar.com.santanderrio.obp.servicios.transferencias.bo.impl;

import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricGatewayConnectorBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricRegisterBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.BaseMetric;
import ar.com.santanderrio.obp.servicios.transferencias.exception.MetricGatewayConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricRegisterBOImpl implements MetricRegisterBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricRegisterBOImpl.class);

    private final MetricGatewayConnectorBO metricGatewayConnector;

    @Autowired
    public MetricRegisterBOImpl(MetricGatewayConnectorBO metricGatewayConnector){

        this.metricGatewayConnector = metricGatewayConnector;

    }

    @Override
    public void register(BaseMetric metric) {

        try {

            LOGGER.info("Inicio guardado de la metrica " + metric.getName() + " en MetricGateway");

            String response = metricGatewayConnector.send(metric);

            LOGGER.info("Metrica " + metric.getName() + " guardada en MetricGateway exitosamente. Respuesta de MG: " + response);


        } catch (MetricGatewayConnectorException ex) {

            LOGGER.error("Metrica " + metric.getName() + " no guardada en MetricGateway", ex);

        } catch (Exception ex) {

            LOGGER.error("Error no esperado al registar metrica", ex);

        }

    }

}
