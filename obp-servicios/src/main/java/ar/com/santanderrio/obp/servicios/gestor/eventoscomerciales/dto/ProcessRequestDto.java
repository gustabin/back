package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class ProcessRequestDto {
    private Long offerId;
    private String nup;
    private Integer term;
    private LocalDate firstDueDate;
    private BigDecimal amountRequested;
    private BigDecimal declaredIncome;
    private String mail;

    public ProcessRequestDto(Long offerId, String nup, Integer term, LocalDate firstDueDate, BigDecimal amountRequested, BigDecimal declaredIncome, String mail) {
        this.offerId = offerId;
        this.nup = nup;
        this.term = term;
        this.firstDueDate = firstDueDate;
        this.amountRequested = amountRequested;
        this.declaredIncome = declaredIncome;
        this.mail = mail;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public LocalDate getFirstDueDate() {
        return firstDueDate;
    }

    public void setFirstDueDate(LocalDate firstDueDate) {
        this.firstDueDate = firstDueDate;
    }

    public BigDecimal getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(BigDecimal amountRequested) {
        this.amountRequested = amountRequested;
    }

    public BigDecimal getDeclaredIncome() {
        return declaredIncome;
    }

    public void setDeclaredIncome(BigDecimal declaredIncome) {
        this.declaredIncome = declaredIncome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
