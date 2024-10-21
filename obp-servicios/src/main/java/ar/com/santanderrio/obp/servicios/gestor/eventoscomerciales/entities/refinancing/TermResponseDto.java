package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing;

import java.math.BigDecimal;

public class TermResponseDto  {

    private Integer term;
    private BigDecimal interest;
    private BigDecimal firstQuotaValue;
    private BigDecimal insurance;
    private BigDecimal iva;
    private BigDecimal tna;
    private BigDecimal totalDueAmount;
    private BigDecimal pureQuotaValue;
    private BigDecimal otherCharges;
    private BigDecimal cft;

    private BigDecimal tea;

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getFirstQuotaValue() {
        return firstQuotaValue;
    }

    public void setFirstQuotaValue(BigDecimal firstQuotaValue) {
        this.firstQuotaValue = firstQuotaValue;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTna() {
        return tna;
    }

    public void setTna(BigDecimal tna) {
        this.tna = tna;
    }

    public BigDecimal getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(BigDecimal totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public BigDecimal getPureQuotaValue() {
        return pureQuotaValue;
    }

    public void setPureQuotaValue(BigDecimal pureQuotaValue) {
        this.pureQuotaValue = pureQuotaValue;
    }

    public BigDecimal getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public BigDecimal getCft() {
        return cft;
    }

    public void setCft(BigDecimal cft) {
        this.cft = cft;
    }

    public BigDecimal getTea() {
        return tea;
    }

    public void setTea(BigDecimal tea) {
        this.tea = tea;
    }
}
