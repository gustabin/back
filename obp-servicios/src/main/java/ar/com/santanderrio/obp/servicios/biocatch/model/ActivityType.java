package ar.com.santanderrio.obp.servicios.biocatch.model;

public enum ActivityType {

    ADD_PAYEE_PAY_A_PERSON("ADD_PAYEE_PAY_A_PERSON"),
    AUTHENTICATION_ENROLLMENT("AUTHENTICATION_ENROLLMENT"),
    CHANGE_PASSWORD("CHANGE_PASSWORD"),
    PERSON_PAYMENT("PERSON_PAYMENT"),
    WIRE_PAYMENT("WIRE_PAYMENT"),
    CHANGE_ACCOUNT_LIMIT("CHANGE_ACCOUNT_LIMIT"),

    BULK_AUTHORIZE_PAYMENT("BULK_AUTHORIZE_PAYMENT");

    private final String value;

    ActivityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
