package ar.com.santanderrio.obp.servicios.api.customers;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("customersApiConfig")
public class CustomersApiConfigProvider implements ApiConfig {

    @Value("${CUSTOMERS-API.PATH.BASE}")
    private String customersBasePath;

    @Value("${CUSTOMERS-API.SCOPE}")
    private String scope;

    @Override
    public String getBasePath() {
        return customersBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }
}
