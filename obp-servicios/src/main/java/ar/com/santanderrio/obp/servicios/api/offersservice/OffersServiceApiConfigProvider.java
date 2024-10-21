package ar.com.santanderrio.obp.servicios.api.offersservice;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("offersApiConfig")
public class OffersServiceApiConfigProvider implements ApiConfig {
    @Value("${OFFERS-API.PATH.BASE}")
    private String offersBasePath;

    @Value("${OFFERS-API.SCOPE}")
    private String scope;

    @Override
    public String getBasePath() {
        return offersBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }
}


