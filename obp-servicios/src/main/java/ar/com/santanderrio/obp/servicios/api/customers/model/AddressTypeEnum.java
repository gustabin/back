package ar.com.santanderrio.obp.servicios.api.customers.model;

public enum AddressTypeEnum {
    MAIN("MAIN"),
    WORK("WORK"),
    DELIVERY("DELIVERY"),
    BRANCH("BRANCH");

    private final String value;

    AddressTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AddressTypeEnum fromValue(String value) {
        for (AddressTypeEnum b : AddressTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
