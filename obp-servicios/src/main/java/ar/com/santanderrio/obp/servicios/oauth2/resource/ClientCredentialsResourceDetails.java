package ar.com.santanderrio.obp.servicios.oauth2.resource;

public class ClientCredentialsResourceDetails extends BaseOAuth2ResourceDetails {

    public ClientCredentialsResourceDetails() {
        setGrantType("client_credentials");
    }
}
