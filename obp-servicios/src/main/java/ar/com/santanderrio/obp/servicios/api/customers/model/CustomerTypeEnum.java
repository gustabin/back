package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum CustomerTypeEnum {
    @SerializedName("human_person")
    HUMAN_PERSON("human_person"),

    @SerializedName("legal_person")
    LEGAL_PERSON("legal_person");

    private final String value;

    CustomerTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CustomerTypeEnum fromValue(String value) {
        for (CustomerTypeEnum b : CustomerTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
