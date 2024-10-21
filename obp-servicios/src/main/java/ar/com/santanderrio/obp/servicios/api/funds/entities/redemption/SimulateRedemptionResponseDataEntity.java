package ar.com.santanderrio.obp.servicios.api.funds.entities.redemption;

import java.math.BigDecimal;
import java.util.List;

public class SimulateRedemptionResponseDataEntity {
    private String transactionToken;
    private String certificateId;
    private String status;
    private Integer fundId;
    private String fundCode;
    private String type;
    private Double value;
    private Integer investmentAccount;
    private PaymentMethodEntity paymentMethod;
    private BigDecimal shareAmount;
    private BigDecimal netAmount;
    private List<LegalEntity> legals;
    private DisclaimerEntity disclaimer;

    public String getTransactionToken() {
        return transactionToken;
    }

    public void setTransactionToken(String transactionToken) {
        this.transactionToken = transactionToken;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Number getFundId() {
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

    public Number getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Number getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(Integer investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Number getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    public Number getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public List<LegalEntity> getLegals() {
        return legals;
    }

    public void setLegals(List<LegalEntity> legals) {
        this.legals = legals;
    }

    public DisclaimerEntity getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(DisclaimerEntity disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
}
