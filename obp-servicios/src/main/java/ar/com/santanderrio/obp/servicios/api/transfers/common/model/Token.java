package ar.com.santanderrio.obp.servicios.api.transfers.common.model;

import ar.com.santanderrio.obp.servicios.api.transfers.common.dto.TokenProviderResponse;

public class Token {

    private final String value;
    private final long expiry;

    public Token(TokenProviderResponse response) {

        this.value = response.getAccessToken();
        this.expiry = System.currentTimeMillis() + response.getExpiresIn() * 1000;

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
