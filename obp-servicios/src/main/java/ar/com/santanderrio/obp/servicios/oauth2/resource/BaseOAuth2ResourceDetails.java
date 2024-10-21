package ar.com.santanderrio.obp.servicios.oauth2.resource;

import org.springframework.util.StringUtils;

public class BaseOAuth2ResourceDetails implements OAuth2ResourceDetails {

    private String id;
    private String clientId;
    private String clientSecret;
    private String grantType = "unsupported";

    private String accessTokenUri;
    private String scope;
    private String tokenName = "access_token";

    private AuthenticationScheme clientAuthenticationScheme = AuthenticationScheme.HEADER;
    private String clientAuthenticationPrefix = "Basic ";
    private AuthenticationScheme authorizationScheme = AuthenticationScheme.HEADER;
    private String authorizationHeaderName = "Authorization";


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    @Override
    public boolean isScoped() {
        return scope != null && !scope.isEmpty();
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public boolean isAuthenticationRequired() {
        return StringUtils.hasText(clientId) && clientAuthenticationScheme != AuthenticationScheme.NONE;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public AuthenticationScheme getClientAuthenticationScheme() {
        return AuthenticationScheme.HEADER;
    }

    @Override
    public String getClientAuthenticationPrefix() {
        return clientAuthenticationPrefix;
    }

    @Override
    public String getGrantType() {
        return grantType;
    }

    @Override
    public AuthenticationScheme getAuthenticationScheme() {
        return AuthenticationScheme.HEADER;
    }

    @Override
    public String getTokenName() {
        return tokenName;
    }

    @Override
    public String getAuthorizationHeaderName() {
        return authorizationHeaderName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public void setClientAuthenticationScheme(AuthenticationScheme clientAuthenticationScheme) {
        this.clientAuthenticationScheme = clientAuthenticationScheme;
    }

    public AuthenticationScheme getAuthorizationScheme() {
        return authorizationScheme;
    }

    public void setAuthorizationScheme(AuthenticationScheme authorizationScheme) {
        this.authorizationScheme = authorizationScheme;
    }

    public void setAuthorizationHeaderName(String authorizationHeaderName) {
        this.authorizationHeaderName = authorizationHeaderName;
    }
}
