package ar.com.santanderrio.obp.servicios.api.customers.model;

public enum LanguageEnum {
    ES_AR("ES_AR"),
    CA_ES("CA_ES");

    private final String value;

    LanguageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static LanguageEnum fromValue(String value) {
        for (LanguageEnum b : LanguageEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
