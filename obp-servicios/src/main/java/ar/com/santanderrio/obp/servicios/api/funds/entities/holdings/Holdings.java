package ar.com.santanderrio.obp.servicios.api.funds.entities.holdings;

public class Holdings {

    private FundEntity fund;

    private Balance balance;

    private Performance performance;

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

}
