package ar.com.santanderrio.obp.servicios.seguros.dto;

import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;

import java.util.List;

public class PolizaDTO {

    private List<Poliza> poliza;

    public List<Poliza> getPoliza() {
        return poliza;
    }

    public void setPoliza(List<Poliza> poliza) {
        this.poliza = poliza;
    }
}
