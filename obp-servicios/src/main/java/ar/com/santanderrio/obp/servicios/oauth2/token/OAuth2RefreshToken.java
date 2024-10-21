package ar.com.santanderrio.obp.servicios.oauth2.token;

public class OAuth2RefreshToken extends OAuth2ExpirableToken {
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
