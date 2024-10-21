package ar.com.santanderrio.obp.servicios.oauth2.resource;

public interface OAuth2ResourceDetails {

    String getId();

    String getClientId();

    String getAccessTokenUri();

    boolean isScoped();

    String getScope();

    boolean isAuthenticationRequired();

    String getClientSecret();

    AuthenticationScheme getClientAuthenticationScheme();

    String getClientAuthenticationPrefix();

    String getGrantType();

    AuthenticationScheme getAuthenticationScheme();

    String getTokenName();

    String getAuthorizationHeaderName();

}
