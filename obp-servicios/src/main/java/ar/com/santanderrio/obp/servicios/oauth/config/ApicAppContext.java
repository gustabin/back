package ar.com.santanderrio.obp.servicios.oauth.config;

public class ApicAppContext {

    private String clientId;
    private String clientSecret;

    public ApicAppContext(){}

    public ApicAppContext(String clientId, String clientSecret){
        super();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
