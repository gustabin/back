package ar.com.santanderrio.obp.servicios.oauth2.resource;

public class OAuth2AuthorizationException extends RuntimeException {

    public OAuth2AuthorizationException(Throwable ex) {
        super(ex);
    }
    public OAuth2AuthorizationException(String message) {
        super(message);
    }

    public OAuth2AuthorizationException(String message, Throwable ex) {
        super(message, ex);
    }
}
