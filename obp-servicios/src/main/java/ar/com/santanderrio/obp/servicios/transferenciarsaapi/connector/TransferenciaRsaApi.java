package ar.com.santanderrio.obp.servicios.transferenciarsaapi.connector;

import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaCreationRequest;
import ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto.TransferenciaSumResponse;

public interface TransferenciaRsaApi {

    TransferenciaSumResponse getTransactionSum(Long nup, String destinationCuit, String currency);

    void postTransaction(Long nup, TransferenciaCreationRequest request);
}
