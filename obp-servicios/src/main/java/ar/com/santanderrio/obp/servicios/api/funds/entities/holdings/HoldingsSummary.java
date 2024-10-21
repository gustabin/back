package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class HoldingsSummary {

    private String segment;

    private String currency;

    private Double amount;

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
