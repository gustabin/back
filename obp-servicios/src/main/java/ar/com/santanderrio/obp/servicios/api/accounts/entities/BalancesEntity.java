package ar.com.santanderrio.obp.servicios.api.accounts.entities;

public class BalancesEntity {

    private String type;

    private Number amount;

    private String currency;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
