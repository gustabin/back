package ar.com.santanderrio.obp.servicios.api.accounts.entities;

import java.util.List;

public class AccountDetailsEntity {

    private String type;

    private String currency;

    private List<BalancesEntity> balances;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<BalancesEntity> getBalances() {
        return balances;
    }

    public void setBalances(List<BalancesEntity> balances) {
        this.balances = balances;
    }

}
