package ar.com.santanderrio.obp.servicios.api.transfers.common.dto;

import com.google.gson.annotations.SerializedName;

public class TokenProviderResponse {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private long expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("refresh_expires_in")
    private long refreshExpiresIn;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {

        return accessToken;

    }

    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;

    }

    public long getExpiresIn() {

        return expiresIn;

    }

    public void setExpiresIn(long expiresIn) {

        this.expiresIn = expiresIn;

    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getRefreshExpiresIn() {

        return refreshExpiresIn;

    }

    public void setRefreshExpiresIn(long refreshExpiresIn) {

        this.refreshExpiresIn = refreshExpiresIn;

    }

    public String getTokenType() {

        return tokenType;

    }

    public void setTokenType(String tokenType) {

        this.tokenType = tokenType;

    }

}
