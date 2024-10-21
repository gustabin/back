package ar.com.santanderrio.obp.servicios.api.common.config;

public interface ApiConfigFunds {
    String getCommonBasePath();

    String getHoldingsBffPath();

    String getCleanCachePylPath();

    String getCleanCacheHoldingsPath();
    String getFundsRedemptionsPath();

    String getCommonScope();
}
