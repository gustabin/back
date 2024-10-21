package ar.com.santanderrio.obp.servicios.debinapi.dto;

public enum OperationMode {
    COMPRADOR("COMPRADOR"),
    VENDEDOR("VENDEDOR");

    private String description;

    OperationMode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
