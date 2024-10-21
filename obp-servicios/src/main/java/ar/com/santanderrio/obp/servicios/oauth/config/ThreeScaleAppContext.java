package ar.com.santanderrio.obp.servicios.oauth.config;

public class ThreeScaleAppContext {
    private String clientSecret;
    private String clientId;

    public ThreeScaleAppContext(){}

    public ThreeScaleAppContext(String clientId, String clientSecret){
        super();
        this.clientSecret = clientSecret;
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
