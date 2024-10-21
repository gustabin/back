package ar.com.santanderrio.obp.servicios.comun.model;

public enum TipoDocumentoEnum {

    DNI("DNI", "N", "Documento Nacional Identidad"),
    CUIT("CUIT", "T", "Cuit"),
    CUIL("CUIL", "L", "Cuil"),
    CDI("CDI", "D", "Clave de identificacion"),
    PASSPORT("PASSPORT", "P", "Pasaporte"),
    FOREING_DOCUMENT("FOREING_DOCUMENT", "X", "Documento Extranjero"),
    IDENTITY_CARD("IDENTITY_CARD", "I", "Cedula de identidad"),
    CIVIC_BOOK("CIVIC_BOOK", "C", "Libreta civica"),
    ENROLLMENT_BOOK("ENROLLMENT_BOOK", "E", "Libreta de enrolamiento");


    private final String codigo;
    private final String descripcion;
    private final String identificadorMainframe;

    TipoDocumentoEnum(String codigo, String identificadorMainframe, String descripcion) {
        this.codigo = codigo;
        this.identificadorMainframe = identificadorMainframe;
        this.descripcion = descripcion;
    }

    public static TipoDocumentoEnum getTipoDocumento(String codigo) {
        for (TipoDocumentoEnum tipoDocumento : TipoDocumentoEnum.values()) {
            if (tipoDocumento.getCodigo().equalsIgnoreCase(codigo)) {
                return tipoDocumento;
            }
        }
        throw new IllegalArgumentException("Tipo de documento: {" + codigo + "} no es un válido");
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIdentificadorMainframe() {
        return identificadorMainframe;
    }
}
