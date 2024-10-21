package ar.com.santanderrio.obp.servicios.api.transfers.recipients.model;

public class RecipientsApiToken {

    private final String value;
    private final long expiry;

    public RecipientsApiToken(String value, long expiry) {

        this.value = value;
        this.expiry = expiry;

    }

    public String getValue() {

        return value;

    }

    public long getExpiry() {

        return expiry;

    }

    public boolean isExpired() {

        return expiry < System.currentTimeMillis();

    }

}
