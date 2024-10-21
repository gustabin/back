package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

import java.util.List;

public class ProcessResultResponseDto {
    List<Process> results;

    public ProcessResultResponseDto(List<Process> results) {
        this.results = results;
    }

    public ProcessResultResponseDto() {
    }

    public List<Process> getResults() {
        return results;
    }

    public void setResults(List<Process> results) {
        this.results = results;
    }
}
