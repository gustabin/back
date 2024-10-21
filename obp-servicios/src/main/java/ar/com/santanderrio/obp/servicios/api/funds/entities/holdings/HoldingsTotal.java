package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class HoldingsTotal {

    private String currency;

    private double amount;

    public String getCurrency() { return currency; }

    public void setCurrency(String value) { this.currency = value; }

    public double getAmount() { return amount; }

    public void setAmount(double value) { this.amount = value; }

}
