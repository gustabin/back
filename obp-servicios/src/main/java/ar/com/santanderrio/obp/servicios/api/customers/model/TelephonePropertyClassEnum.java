package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum TelephonePropertyClassEnum {
    @SerializedName("particular")
    PARTICULAR("PARTICULAR"),

    @SerializedName("work")
    WORK("WORK"),

    @SerializedName("particular_contact")
    PARTICULAR_CONTACT("PARTICULAR_CONTACT"),

    @SerializedName("work_contact")
    WORK_CONTACT("WORK_CONTACT");

    private final String value;

    TelephonePropertyClassEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TelephonePropertyClassEnum fromValue(String value) {
        for (TelephonePropertyClassEnum b : TelephonePropertyClassEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
