package ar.com.santanderrio.obp.servicios.api.accounts.entities;

import java.util.List;

public class AccountsResponseEntity {

    private List<AccountEntity> accounts;

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}
