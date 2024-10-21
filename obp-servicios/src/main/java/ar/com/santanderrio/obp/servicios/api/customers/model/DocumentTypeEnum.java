package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum DocumentTypeEnum {

    @SerializedName("dni")
    DNI("DNI"),

    @SerializedName("cuil")
    CUIL("CUIL"),

    @SerializedName("cuit")
    CUIT("CUIT"),

    @SerializedName("identity_card")
    IDENTITY_CARD("IDENTITY_CARD"),

    @SerializedName("passport")
    PASSPORT("PASSPORT"),

    @SerializedName("cdi")
    CDI("CDI"),

    @SerializedName("civic_book")
    CIVIC_BOOK("CIVIC_BOOK"),

    @SerializedName("enrollment_book")
    ENROLLMENT_BOOK("ENROLLMENT_BOOK"),

    @SerializedName("foreing_document")
    FOREING_DOCUMENT("FOREING_DOCUMENT");

    private final String value;

    DocumentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static DocumentTypeEnum fromValue(String value) {
        for (DocumentTypeEnum b : DocumentTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
