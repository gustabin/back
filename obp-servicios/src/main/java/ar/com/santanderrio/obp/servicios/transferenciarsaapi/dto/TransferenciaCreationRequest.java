package ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto;


import java.math.BigDecimal;

public class TransferenciaCreationRequest {

    private BigDecimal amount;

    private String created;

    private String destinationCuit;

    private String currency;

    public TransferenciaCreationRequest(BigDecimal amount,
                                        String created,
                                        String destinationCuit,
                                        String currency) {
        this.amount = amount;
        this.created = created;
        this.destinationCuit = destinationCuit;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDestinationCuit() {
        return destinationCuit;
    }

    public void setDestinationCuit(String destinationCuit) {
        this.destinationCuit = destinationCuit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
