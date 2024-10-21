package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseMetric {

    protected String name;

    protected String cmdb;

    protected Long value;

    @JsonProperty("metric_type")
    protected MetricType metricType;

    public BaseMetric(String cmdb, String name, Long value, MetricType metricType) {

        this.cmdb = cmdb;
        this.name = name;
        this.value = value;
        this.metricType = metricType;

    }


    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getCmdb() {

        return cmdb;

    }

    public void setCmdb(String cmdb) {

        this.cmdb = cmdb;

    }

    public Long getValue() {

        return value;

    }

    public void setValue(Long value) {

        this.value = value;

    }

    public MetricType getMetricType() {

        return metricType;

    }

    public void setMetricType(MetricType metricType) {

        this.metricType = metricType;

    }
}
