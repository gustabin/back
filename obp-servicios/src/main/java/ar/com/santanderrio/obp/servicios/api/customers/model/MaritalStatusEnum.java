package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum MaritalStatusEnum {

    @SerializedName("married")
    MARRIED("MARRIED"),

    @SerializedName("divorced")
    DIVORCED("DIVORCED"),

    @SerializedName("separated")
    SEPARATED("SEPARATED"),

    @SerializedName("single")
    SINGLE("SINGLE"),

    @SerializedName("widower")
    WIDOWER("WIDOWER");

    private final String value;

    MaritalStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static MaritalStatusEnum fromValue(String value) {
        for (MaritalStatusEnum b : MaritalStatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
