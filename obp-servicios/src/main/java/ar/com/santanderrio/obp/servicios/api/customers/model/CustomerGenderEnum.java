package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum CustomerGenderEnum {

    @SerializedName("female")
    FEMALE("FEMALE"),

    @SerializedName("male")
    MALE("MALE");

    private final String value;

    CustomerGenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CustomerGenderEnum fromValue(String value) {
        for (CustomerGenderEnum b : CustomerGenderEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
