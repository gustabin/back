package ar.com.santanderrio.obp.servicios.oauth.model;

public enum OAuthGrantTypesEnum {

    CODE("authorization_code"),
    PASSWORD("password"),
    CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");

    private final String code;

    OAuthGrantTypesEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
