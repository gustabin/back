package ar.com.santanderrio.obp.servicios.api.sso;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("ssoApiConfig")
public class SSOApiConfigProvider implements SSOApiConfig {

    @Value("${CONSENT-API.PATH.BASE}")
    private String accountsBasePath;

    @Value("${CONSENT-PCP-API.PATH.BASE}")
    private String pcpBasePath;

    @Value("${CONSENT-API.SCOPE}")
    private String scope;

    @Override
    public String getBasePath() {
        return accountsBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public String getPcpBasePath() {
        return pcpBasePath;
    }
}
