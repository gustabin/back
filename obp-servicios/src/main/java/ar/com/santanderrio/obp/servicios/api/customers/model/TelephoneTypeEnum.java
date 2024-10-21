package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum TelephoneTypeEnum {
    @SerializedName("linephone")
    LINEPHONE("LINEPHONE"),

    @SerializedName("fax")
    FAX("FAX"),

    @SerializedName("telfax")
    TELFAX("TELFAX"),

    @SerializedName("radiocall")
    RADIOCALL("RADIOCALL"),

    @SerializedName("cellphone")
    CELLPHONE("CELLPHONE");


    private final String value;

    TelephoneTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TelephoneTypeEnum fromValue(String value) {
        for (TelephoneTypeEnum b : TelephoneTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
