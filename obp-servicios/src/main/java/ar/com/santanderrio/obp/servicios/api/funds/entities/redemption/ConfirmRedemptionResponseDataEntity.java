package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

import java.math.BigDecimal;
import java.util.List;

public class ConfirmRedemptionResponseDataEntity {
    private Integer fundId;
    private String type;
    private Double value;
    private PaymentMethodEntity paymentMethod;
    private Integer investmentAccount;
    private Integer transactionId;
    private String status;
    private String certificateId;
    private BigDecimal netAmount;
    private BigDecimal shareAmount;
    private String processDate;
    private List<LegalEntity> legals;

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(Integer investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    public String getProcessDate() {
        return processDate;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public List<LegalEntity> getLegals() {
        return legals;
    }

    public void setLegals(List<LegalEntity> legals) {
        this.legals = legals;
    }
}
