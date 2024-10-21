package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class Process {
    private Long id;
    private Long offerId;
    private Integer term;
    private String nup;
    private LocalDate firstDueDate;
    private String accountNumber;
    private String accountType;
    private String processState;
    private BigDecimal amountRequested;
    private Boolean isMonoproduct;

    public Process() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public LocalDate getFirstDueDate() {
        return firstDueDate;
    }

    public void setFirstDueDate(LocalDate firstDueDate) {
        this.firstDueDate = firstDueDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public BigDecimal getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(BigDecimal amountRequested) {
        this.amountRequested = amountRequested;
    }

    public Boolean getMonoproduct() {
        return isMonoproduct;
    }

    public void setMonoproduct(Boolean monoproduct) {
        isMonoproduct = monoproduct;
    }
}
