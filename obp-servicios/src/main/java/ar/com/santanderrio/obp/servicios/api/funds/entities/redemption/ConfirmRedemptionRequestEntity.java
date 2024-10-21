package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

public class ConfirmRedemptionRequestEntity {
    private String transactionToken;

    public ConfirmRedemptionRequestEntity(String transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }
}
