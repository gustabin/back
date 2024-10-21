package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum SegmentEnum {
    @SerializedName("select_rent")
    SELECT_RENT("select_rent"),

    @SerializedName("high_rent")
    HIGH_RENT("high_rent"),

    @SerializedName("medium_rent")
    MEDIUM_RENT("MEDIUM_RENT"),

    @SerializedName("massive_rent")
    MASSIVE_RENT("MASSIVE_RENT"),

    @SerializedName("primary_small_and_midsize_enterprise")
    PRIMARY_SMALL_AND_MIDSIZE_ENTERPRISE("PRIMARY_SMALL_AND_MIDSIZE_ENTERPRISE"),

    @SerializedName("secondary_small_and_midsize_enterprise")
    SECONDARY_SMALL_AND_MIDSIZE_ENTERPRISE("SECONDARY_SMALL_AND_MIDSIZE_ENTERPRISE"),

    @SerializedName("business")
    BUSINESS("BUSINESS"),

    @SerializedName("companies")
    COMPANIES("COMPANIES"),

    @SerializedName("large_companies")
    LARGE_COMPANIES("LARGE_COMPANIES"),

    @SerializedName("public_institutions")
    PUBLIC_INSTITUTIONS("PUBLIC_INSTITUTIONS"),

    @SerializedName("private_institutions")
    PRIVATE_INSTITUTIONS("PRIVATE_INSTITUTIONS"),

    @SerializedName("global_groups")
    GLOBAL_GROUPS("GLOBAL_GROUPS"),

    @SerializedName("latam_groups")
    LATAM_GROUPS("LATAM_GROUPS"),

    @SerializedName("local_groups")
    LOCAL_GROUPS("LOCAL_GROUPS"),

    @SerializedName("multinational_companies")
    MULTINATIONAL_COMPANIES("MULTINATIONAL_COMPANIES"),

    @SerializedName("public_sector")
    PUBLIC_SECTOR("PUBLIC_SECTOR"),

    @SerializedName("other_corporate")
    OTHER_CORPORATE("OTHER_CORPORATE"),

    @SerializedName("financial_institutions")
    FINANCIAL_INSTITUTIONS("FINANCIAL_INSTITUTIONS"),

    @SerializedName("banks_insurance_afjp")
    BANKS_INSURANCE_AFJP("BANKS_INSURANCE_AFJP"),

    @SerializedName("international_financial_institutions")
    INTERNATIONAL_FINANCIAL_INSTITUTIONS("INTERNATIONAL_FINANCIAL_INSTITUTIONS"),

    @SerializedName("other_financial_institutions")
    OTHER_FINANCIAL_INSTITUTIONS("OTHER_FINANCIAL_INSTITUTIONS");

    private final String value;

    SegmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static SegmentEnum fromValue(String value) {
        for (SegmentEnum b : SegmentEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
