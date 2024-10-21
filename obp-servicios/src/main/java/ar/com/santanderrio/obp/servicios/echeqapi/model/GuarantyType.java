package ar.com.santanderrio.obp.servicios.echeqapi.model;

public enum GuarantyType {
    EMISSION("EMISSION"),
    ACCEPTANCE("ACCEPTANCE"),
    REPUDIATION("REPUDIATION"),
    CANCELLATION("CANCELLATION");

    private String type;
    GuarantyType(String type) {
        this.type = type;
    }
}
