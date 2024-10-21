package ar.com.santanderrio.obp.servicios.transferencias.entities.metricas;

public enum TransferStatus {

    OK("OK"),
    FAIL("FAIL"),
    WARNING("WARNING");

    private final String value;

    TransferStatus(String value) {

        this.value = value;

    }

    public String getValue() {

        return this.value;

    }

}
