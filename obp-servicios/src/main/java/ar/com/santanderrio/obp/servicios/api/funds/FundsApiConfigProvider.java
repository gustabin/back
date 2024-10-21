package ar.com.santanderrio.obp.servicios.api.funds;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfigFunds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FundsApiConfigProvider implements ApiConfigFunds {

    @Value("${FUNDS-API.PATH.BASE}")
    private String fundsBasePath;

    @Value("${FUNDS-API.PATH.REDEMPTIONS}")
    private String fundsRedemptionsPath;

    @Value("${FUNDS-API.PATH.HOLDINGS-BFF}")
    private String holdingsBffPath;

    @Value("${FUNDS-API.PATH.PYL-SERVICE}")
    private String cleanCachePylPath;

    @Value("${FUNDS-API.PATH.HOLDINGS-CACHE}")
    private String cleanCacheHoldingsPath;

    @Value("${FUNDS-API.SCOPE.V2}")
    private String scope;

    @Override
    public String getCommonBasePath() {
        return fundsBasePath;
    }

    @Override
    public String getHoldingsBffPath() {
        return holdingsBffPath;
    }

    @Override
    public String getFundsRedemptionsPath() { return fundsRedemptionsPath; }

    @Override
    public String getCleanCachePylPath() {
        return cleanCachePylPath;
    }

    @Override
    public String getCleanCacheHoldingsPath() {
        return cleanCacheHoldingsPath;
    }

    @Override
    public String getCommonScope() {
        return scope;
    }

}
