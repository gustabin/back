package ar.com.santanderrio.obp.servicios.biocatch.model;

public enum Brand {

    OBP("OBP");

    private final String value;

    Brand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
