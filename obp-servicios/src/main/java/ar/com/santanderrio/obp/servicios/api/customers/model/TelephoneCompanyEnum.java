package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum TelephoneCompanyEnum {
    @SerializedName("movistar")
    MOVISTAR("MOVISTAR"),

    @SerializedName("personal")
    PERSONAL("PERSONAL"),

    @SerializedName("nextel")
    NEXTEL("NEXTEL"),

    @SerializedName("other")
    OTHER("OTHER");

    private final String value;

    TelephoneCompanyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TelephoneCompanyEnum fromValue(String value) {
        for (TelephoneCompanyEnum b : TelephoneCompanyEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
