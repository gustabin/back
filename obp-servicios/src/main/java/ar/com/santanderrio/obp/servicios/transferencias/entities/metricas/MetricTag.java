package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

public class MetricTag {

    private final String label;

    private final String value;

    public MetricTag(String label, String value) {

        this.label = label;
        this.value = value;

    }

    public String getLabel() {

        return label;

    }

    public String getValue() {

        return value;

    }

}
