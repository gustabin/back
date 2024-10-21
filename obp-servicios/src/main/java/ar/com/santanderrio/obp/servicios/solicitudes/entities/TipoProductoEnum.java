package ar.com.santanderrio.obp.servicios.solicitudes.entities;

public enum TipoProductoEnum {

    INTERNACIONAL("1", "MASTERCARD INTERNACIONAL"),
    ORO ("2", "MASTERCARD ORO"),
    PURCHASING("5", "PURCHASING"),
    CORPORATE_ORO("7", "CORPORATE ORO"),
    SIGNATURE ("H","SIGNATURE"),
    CORPORATE_CUENTA_CENTRAL("9", "CORPORATE CUENTA CENTRAL"),
    PREPAGA_RECARGABLE("A", "PREPAGA RECARGABLE"),
    PURCHASING_SERVICE("B", "PURCHASING SERVICE"),
    VISA_DISTRIBUTION("D", "VISA DISTRIBUTION"),
    MONEDERO("F", "MONEDERO"),
    PREPAGA_NACIONAL("K", "PREPAGA NACIONAL"),
    PLATINUM("L", "PLATINUM"),
    CORPORATE_RECARGABLE("N", "CORPORATE RECARGABLE"),
    DISTRIBUTION("O", "DISTRIBUTION");

    private final String codigo;
    private final String descripcion;

    TipoProductoEnum(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}