package ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances;

import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.dto.FundsPerformancesResponse;

public interface FundsPerformances {

    FundsPerformancesResponse getPerformancesById(String id);
}
