package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum EmploymentStatusEnum {
    @SerializedName("merchant_with_employees")
    MERCHANT_WITH_EMPLOYEES("MERCHANT_WITH_EMPLOYEES"),

    @SerializedName("professional_with_employees")
    PROFESSIONAL_WITH_EMPLOYEES("PROFESSIONAL_WITH_EMPLOYEES"),

    @SerializedName("businessman_with_employees")
    BUSINESSMAN_WITH_EMPLOYEES("BUSINESSMAN_WITH_EMPLOYEES"),

    @SerializedName("merchant_without_employees")
    MERCHANT_WITHOUT_EMPLOYEES("MERCHANT_WITHOUT_EMPLOYEES"),

    @SerializedName("professional_without_employees")
    PROFESSIONAL_WITHOUT_EMPLOYEES("PROFESSIONAL_WITHOUT_EMPLOYEES"),

    @SerializedName("self_employed_without_employees")
    SELF_EMPLOYED_WITHOUT_EMPLOYEES("SELF_EMPLOYED_WITHOUT_EMPLOYEES"),

    @SerializedName("senior_manager")
    SENIOR_MANAGER("SENIOR_MANAGER"),

    @SerializedName("middle_manager")
    MIDDLE_MANAGER("MIDDLE_MANAGER"),

    @SerializedName("employee")
    EMPLOYEE("EMPLOYEE"),

    @SerializedName("laborer")
    LABORER("LABORER"),

    @SerializedName("homemaker")
    HOMEMAKER("HOMEMAKER"),

    @SerializedName("student")
    STUDENT("STUDENT"),

    @SerializedName("retired")
    RETIRED("RETIRED"),

    @SerializedName("rentier")
    RENTIER("RENTIER"),

    @SerializedName("unemployed")
    UNEMPLOYED("UNEMPLOYED");

    private final String value;

    EmploymentStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static EmploymentStatusEnum fromValue(String value) {
        for (EmploymentStatusEnum b : EmploymentStatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
