package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

import java.util.List;

public class MetricWithTags extends BaseMetric {

    private List<MetricTag> tags;

    public MetricWithTags(String cmdb, String name, Long value, MetricType metricType, List<MetricTag> tags) {

        super(cmdb, name, value, metricType);
        this.tags = tags;

    }

    public List<MetricTag> getTags() {

        return tags;

    }

    public void setTags(List<MetricTag> tags) {

        this.tags = tags;

    }
}
