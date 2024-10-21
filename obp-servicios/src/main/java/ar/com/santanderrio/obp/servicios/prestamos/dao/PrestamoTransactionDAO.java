package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.contracts.TransactionRequest;

public interface PrestamoTransactionDAO {

    /**
     * Encola alta de prestamos a 48hs.
     *
     * @return Encolar OK?
     * @throws DAOException
     *             the DAO exception
     */
    void postEnqueue(TransactionRequest request);
    void putDequeue(TransactionRequest request);

}
