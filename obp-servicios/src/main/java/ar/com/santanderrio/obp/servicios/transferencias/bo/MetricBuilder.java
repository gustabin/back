package ar.com.santanderrio.obp.servicios.transferencias.bo;

import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricWithTags;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;

public interface MetricBuilder {

    MetricWithTags createMetricTransfer(TransferMetricInfoDTO transferInfo);

}
