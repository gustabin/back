package ar.com.santanderrio.obp.servicios.api.funds;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsResponse;
import ar.com.santanderrio.obp.servicios.api.funds.entities.holdings.HoldingsSummaryResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.*;

public interface FundsApi {
    HoldingsResponse getHoldingBff(Cliente cliente) throws ApiException;

    HoldingsSummaryResponse getHoldingSummary(Cliente cliente) throws ApiException;

    void cleanCacheFunds(String custodyAccount, Cliente cliente, String operacion, String segmento) throws ApiException;

    Boolean verifyAccessToGetHolding(Cliente cliente) throws ApiException;

    SimulateRedemptionResponseDataEntity simulateRedemption(SimulateRedemptionRequestEntity request) throws ApiException;

    ConfirmRedemptionResponseDataEntity confirmRedemption(ConfirmRedemptionRequestEntity request) throws ApiException;

    boolean checkBouncerAccess(String pathIdentifier, String accessResource, String nup);
}

