package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import org.joda.time.LocalDate;

public class ProcessFilterRequestDto {
    String nup;
    String offerId;
    String state;
    LocalDate creationDate;

    public ProcessFilterRequestDto() {
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
