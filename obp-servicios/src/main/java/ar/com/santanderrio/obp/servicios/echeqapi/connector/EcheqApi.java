package ar.com.santanderrio.obp.servicios.echeqapi.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.EcheqEndorsementCancellationResponse;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationRequest;
import ar.com.santanderrio.obp.servicios.echeqapi.dto.GuarantyOrderCreationResponse;

public interface EcheqApi {
    EcheqEndorsementCancellationResponse postEndorsementCancellation(EcheqEndorsementCancellationRequest request)
            throws DAOException;

    GuarantyOrderCreationResponse postGuarantyEmissionOrder(GuarantyOrderCreationRequest request)
            throws DAOException;

    GuarantyOrderCreationResponse postGuarantyAcceptanceOrder(GuarantyOrderCreationRequest request)
            throws DAOException;

    GuarantyOrderCreationResponse postGuarantyRepudiationOrder(GuarantyOrderCreationRequest request)
            throws DAOException;

    GuarantyOrderCreationResponse postGuarantyCancellationOrder(GuarantyOrderCreationRequest request)
            throws DAOException;

}
