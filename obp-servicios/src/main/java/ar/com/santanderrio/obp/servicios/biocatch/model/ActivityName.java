package ar.com.santanderrio.obp.servicios.biocatch.model;

public enum ActivityName {

    LOGIN("LOGIN"),
    AUTO_LOGIN("AUTO_LOGIN"),
    CAMBIO_CLAVE_ONLINE("CAMBIO_CLAVE_ONLINE"),
    TRANSFERENCIA("TRANSFERENCIA"),
    TRANSFERENCIA_MODO("TRANSFERENCIA_MODO"),
    ENROLAMIENTO_TOKEN("ENROLAMIENTO_TOKEN"),
    LOGIN_APP("OBP_LOGIN_APP"),
    LOGIN_OBE("OBP_LOGIN_OBE"),
    TRANSFERENCIAS_AUMENTO_LIMITE("TRANSFERENCIAS_AUMENTO_LIMITE"),
    PAGO_HABERES("PAGO_HABERES");

    private final String value;

    ActivityName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
