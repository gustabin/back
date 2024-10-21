package ar.com.santanderrio.obp.servicios.api.accounts;

import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountEntity;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.accounts.entities.AccountsResponseEntity;

public interface AccountsApi {
    AccountsResponseEntity getAccountsByCustomerId(String customerId) throws ApiException;

    AccountEntity getAccountByAccountId(String accountId, String customerId) throws ApiException;

    AccountEntity getAccountPublicByAccountId(String accountId, String currency) throws ApiException;
}
