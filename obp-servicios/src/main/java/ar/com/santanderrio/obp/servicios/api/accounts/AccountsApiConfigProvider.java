package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("accountsApiConfig")
public class AccountsApiConfigProvider implements ApiConfig {

    @Value("${ACCOUNTS-API.PATH.BASE}")
    private String accountsBasePath;

    @Value("${ACCOUNTS-API.SCOPE.V2}")
    private String scope;

    @Override
    public String getBasePath() {
        return accountsBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }
}
