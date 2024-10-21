package ar.com.santanderrio.obp.servicios.delete.account.connector;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountRequest;
import ar.com.santanderrio.obp.servicios.delete.account.model.DeleteAccountResponse;

public interface DeleteAccountConnector {

	DeleteAccountResponse deleteAccountById(DeleteAccountRequest request) throws DAOException;
}
