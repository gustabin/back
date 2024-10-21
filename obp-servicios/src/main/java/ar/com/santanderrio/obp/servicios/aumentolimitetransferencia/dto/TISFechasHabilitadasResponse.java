package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.FechaHabilitadaDTO;

import java.util.List;

public class TISFechasHabilitadasResponse {

    private final String status;
    private final List<FechaHabilitadaDTO> fechasHabilitadas;

    public TISFechasHabilitadasResponse(String status, List<FechaHabilitadaDTO> listaFechasDTO) {
        this.status = status;
        this.fechasHabilitadas = listaFechasDTO;
    }

    public String getStatus() {return status;}

    public List<FechaHabilitadaDTO> getFechasHabilitadas() {
        return fechasHabilitadas;
    }

}
