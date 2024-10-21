package ar.com.santanderrio.obp.servicios.api.transfers.recipients.model;

public enum AccountType {

    PESOS_ACCOUNT("05", "00"),
    PESOS_SAVINGS_ACCOUNT("02", "01"),
    UNIQUE_ACCOUNT("07", "02"),
    USD_ACCOUNT("06", "03"),
    USD_SAVINGS_ACCOUNT("03", "04");

    private final String accountTypeDigits;

    private final String accountTypeValue;

    AccountType(String accountTypeDigits, String accountTypeValue) {

        this.accountTypeDigits = accountTypeDigits;
        this.accountTypeValue = accountTypeValue;

    }

    public String getAccountTypeDigits() {

        return accountTypeDigits;

    }

    public String getAccountTypeValue() {

        return accountTypeValue;

    }

    public static AccountType getAccountTypeFromAccountDigits(String accountDigits) {

        for (AccountType accountType : values()) {

            if (accountType.getAccountTypeDigits().equals(accountDigits)) {

                return accountType;

            }

        }

        return null;

    }

}
