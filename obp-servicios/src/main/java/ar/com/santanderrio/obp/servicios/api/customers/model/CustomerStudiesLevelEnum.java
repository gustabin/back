package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum CustomerStudiesLevelEnum {
    @SerializedName("none")
    NONE("NONE"),

    @SerializedName("primary")
    PRIMARY("PRIMARY"),

    @SerializedName("secondary")
    SECONDARY("SECONDARY"),

    @SerializedName("tertiary")
    TERTIARY("TERTIARY"),

    @SerializedName("university_studies")
    UNIVERSITY_STUDIES("UNIVERSITY_STUDIES"),

    @SerializedName("postgraduate")
    POSTGRADUATE("POSTGRADUATE");

    private final String value;

    CustomerStudiesLevelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CustomerStudiesLevelEnum fromValue(String value) {
        for (CustomerStudiesLevelEnum b : CustomerStudiesLevelEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
