package ar.com.santanderrio.obp.servicios.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OBP3ScaleOAuthContext {

    @Value("${OAUTHV2.3SCALE.SEC.ID}")
    private String oauthV2SecurityId;

    @Value("${OAUTHV2.3SCALE.URL}")
    private String oauthUrl;

    @Value("${OAUTHV2.3SCALE.TOKEN.PATH}")
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
