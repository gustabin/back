package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

import org.codehaus.jackson.annotate.JsonValue;

public enum MetricType {

    COUNTER("counter"),
    GAUGE("gauge"),
    HISTOGRAM("histogram"),
    SUMMARY("summary");

    private final String value;

    MetricType(String value) {

        this.value = value;

    }

    @JsonValue
    public String getValue() {

        return this.value;

    }

}
