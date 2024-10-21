package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum FinancialIncomeTypeEnum {
    @SerializedName("personal")
    PERSONAL("PERSONAL"),

    @SerializedName("family")
    FAMILY("FAMILY"),

    @SerializedName("total")
    TOTAL("TOTAL");

    private final String value;

    FinancialIncomeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static FinancialIncomeTypeEnum fromValue(String value) {
        for (FinancialIncomeTypeEnum b : FinancialIncomeTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
