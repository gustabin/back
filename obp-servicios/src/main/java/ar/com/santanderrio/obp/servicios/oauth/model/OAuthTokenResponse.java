package ar.com.santanderrio.obp.servicios.oauth.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class OAuthTokenResponse {

    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private long expiresIn;
    @JsonProperty("consented_on")
    private long consentedOn;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("jwt-token")
    private String jwtToken;

    @JsonProperty("error")
    private String error;

    public static OAuthTokenResponse defaultStateResponse() {
        OAuthTokenResponse response = new OAuthTokenResponse();
        response.setError("error_generico");
        return response;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

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

    public long getConsentedOn() {
        return consentedOn;
    }

    public void setConsentedOn(long consentedOn) {
        this.consentedOn = consentedOn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
