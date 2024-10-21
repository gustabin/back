package ar.com.santanderrio.obp.servicios.oauth.dto;

import ar.com.santanderrio.obp.servicios.oauth.model.OAuthGrantTypesEnum;

public class OAuthTokenRequestDTO {

    private OAuthGrantTypesEnum grantType;
    private String scope;
    private String refreshToken;

    public OAuthGrantTypesEnum getGrantType() {
        return grantType;
    }

    public OAuthTokenRequestDTO setGrantType(OAuthGrantTypesEnum grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public OAuthTokenRequestDTO setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public OAuthTokenRequestDTO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
