package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.refinancing;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasFinanciacionErrorEntity;

import java.util.List;

public class OfferResponseDto {

    private List<OfferDto> results;

    private List<OfertasFinanciacionErrorEntity> errors;

    public List<OfferDto> getResults() {
        return results;
    }

    public void setResults(List<OfferDto> results) {
        this.results = results;
    }

    public List<OfertasFinanciacionErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<OfertasFinanciacionErrorEntity> errors) {
        this.errors = errors;
    }
}
