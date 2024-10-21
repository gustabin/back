package ar.com.santanderrio.obp.servicios.api.calendar.mapper;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.FechaHabilitadaDTO;

import java.util.Comparator;

public class FechaHabilitadaComparator implements Comparator<FechaHabilitadaDTO> {

    @Override
    public int compare(FechaHabilitadaDTO f1, FechaHabilitadaDTO f2) {
        int temp = f1.getAnio() - f2.getAnio();
        if (temp == 0) {
            temp = f1.getMes() - f2.getMes();
            if (temp == 0) {
                return f1.getDia() - f2.getDia();
            }
        }
        return temp;
    }

}
