package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum CustomerConditionEnum {
    @SerializedName("prospect")
    PROSPECT("prospect"),

    @SerializedName("no_customer")
    NO_CUSTOMER("no_customer"),

    @SerializedName("customer")
    CUSTOMER("customer"),

    @SerializedName("ex_customer")
    EX_CUSTOMER("ex_customer");

    private final String value;

    CustomerConditionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CustomerConditionEnum fromValue(String value) {
        for (CustomerConditionEnum b : CustomerConditionEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
