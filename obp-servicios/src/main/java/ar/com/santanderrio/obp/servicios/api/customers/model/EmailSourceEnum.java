package ar.com.santanderrio.obp.servicios.api.customers.model;

import com.google.gson.annotations.SerializedName;

public enum EmailSourceEnum {
    @SerializedName("messages_and_notifications")
    MESSAGES_AND_NOTIFICATIONS("MESSAGES_AND_NOTIFICATIONS"),

    @SerializedName("early_default")
    EARLY_DEFAULT("EARLY_DEFAULT");

    private final String value;

    EmailSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static EmailSourceEnum fromValue(String value) {
        for (EmailSourceEnum b : EmailSourceEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
