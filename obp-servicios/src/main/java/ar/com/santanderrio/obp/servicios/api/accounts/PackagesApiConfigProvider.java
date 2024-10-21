package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("packagesApiConfig")
public class PackagesApiConfigProvider implements ApiConfig {
    @Value("${PACKAGES-API.PATH.BASE}")
    private String packagesBasePath;
     @Value("${ACCOUNTS-API.SCOPE.V2}")
    private String scope;

    @Override
    public String getBasePath() {
        return packagesBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }


}
