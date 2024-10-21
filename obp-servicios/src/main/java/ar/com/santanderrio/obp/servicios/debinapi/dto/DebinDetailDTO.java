package ar.com.santanderrio.obp.servicios.debinapi.dto;

import java.math.BigDecimal;

public class DebinDetailDTO {
    private String id;
    private DebinType debinType;
    private DebinStatusDTO debinStatusDTO;
    private DebinBuyerDTO debinBuyerDTO;
    private DebinSellerDTO debinSellerDTO;
    private CurrencyType currency;
    private BigDecimal amount;
    private ConceptType concept;
    private Boolean refund;
    private String associatedOperationId;
    private String expirationDate;
    private String creationDate;

    public String getId() {
        return id;
    }

    public DebinType getDebinType() {
        return debinType;
    }

    public DebinStatusDTO getDebinStatusDTO() {
        return debinStatusDTO;
    }

    public DebinBuyerDTO getDebinBuyerDTO() {
        return debinBuyerDTO;
    }

    public DebinSellerDTO getDebinSellerDTO() {
        return debinSellerDTO;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public ConceptType getConcept() {
        return concept;
    }

    public Boolean getRefund() {
        return refund;
    }

    public String getAssociatedOperationId() {
        return associatedOperationId;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDebinType(DebinType debinType) {
        this.debinType = debinType;
    }

    public void setDebinStatusDTO(DebinStatusDTO debinStatusDTO) {
        this.debinStatusDTO = debinStatusDTO;
    }

    public void setDebinBuyerDTO(DebinBuyerDTO debinBuyerDTO) {
        this.debinBuyerDTO = debinBuyerDTO;
    }

    public void setDebinSellerDTO(DebinSellerDTO debinSellerDTO) {
        this.debinSellerDTO = debinSellerDTO;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setConcept(ConceptType concept) {
        this.concept = concept;
    }

    public void setRefund(Boolean refund) {
        this.refund = refund;
    }

    public void setAssociatedOperationId(String associatedOperationId) {
        this.associatedOperationId = associatedOperationId;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
