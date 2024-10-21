package ar.com.santanderrio.obp.servicios.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OBPOAuthContext {

    @Value("${OAUTHV2.SEC.ID}")
    private String oauthV2SecurityId;

    @Value("${OAUTHV2.URL}")
    private String oauthUrl;

    @Value("${OAUTHV2.TOKEN.PATH}")
    private String oauthTokenPath;

    public String getOauthV2SecurityId() {
        return oauthV2SecurityId;
    }

    public String getOauthUrl() {
        return oauthUrl;
    }

    public String getOauthTokenPath() {
        return oauthTokenPath;
    }
}
