package ar.com.santanderrio.obp.servicios.biocatch.model;

public enum PlatformType {

    WEB("WEB");

    private final String value;

    PlatformType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
