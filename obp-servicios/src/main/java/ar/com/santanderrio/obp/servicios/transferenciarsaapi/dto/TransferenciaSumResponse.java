package ar.com.santanderrio.obp.servicios.transferenciarsaapi.dto;

import java.math.BigDecimal;

public class TransferenciaSumResponse {

    private Long nup;

    private String destinationCuit;

    private Integer nupQuantity;

    private BigDecimal nupAmount;

    private Integer destinationCuitQuantity;

    private BigDecimal destinationCuitAmount;

    public TransferenciaSumResponse() {

    }

    public TransferenciaSumResponse(Long nup, String destinationCuit) {
        this.nup = nup;
        this.destinationCuit = destinationCuit;
        this.nupQuantity = 0;
        this.nupAmount = new BigDecimal("0");
        this.destinationCuitQuantity = 0;
        this.destinationCuitAmount = new BigDecimal("0");
    }

    public Long getNup() {
        return nup;
    }

    public void setNup(Long nup) {
        this.nup = nup;
    }

    public String getDestinationCuit() {
        return destinationCuit;
    }

    public void setDestinationCuit(String destinationCuit) {
        this.destinationCuit = destinationCuit;
    }

    public Integer getNupQuantity() {
        return nupQuantity;
    }

    public void setNupQuantity(Integer nupQuantity) {
        this.nupQuantity = nupQuantity;
    }

    public BigDecimal getNupAmount() {
        return nupAmount;
    }

    public void setNupAmount(BigDecimal nupAmount) {
        this.nupAmount = nupAmount;
    }

    public Integer getDestinationCuitQuantity() {
        return destinationCuitQuantity;
    }

    public void setDestinationCuitQuantity(Integer destinationCuitQuantity) {
        this.destinationCuitQuantity = destinationCuitQuantity;
    }

    public BigDecimal getDestinationCuitAmount() {
        return destinationCuitAmount;
    }

    public void setDestinationCuitAmount(BigDecimal destinationCuitAmount) {
        this.destinationCuitAmount = destinationCuitAmount;
    }
}
