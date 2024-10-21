package ar.com.santanderrio.obp.servicios.api.calendar.mapper;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.dto.FechaHabilitadaDTO;

import java.util.*;

public class CalendarApiConverter {

    private CalendarApiConverter() {
    }

    public static List<FechaHabilitadaDTO> calendarApiDTOMapperFechaHabilitadaDTO(List<CalendarApiDateDTO> dates){
        List<FechaHabilitadaDTO> listaFechasDTO = new ArrayList<FechaHabilitadaDTO>();

        for(CalendarApiDateDTO calendarApiDateDTO: dates){
            String str = calendarApiDateDTO.getDate();
            String[] parts = str.split("-");
            listaFechasDTO.add(new FechaHabilitadaDTO(Integer.parseInt(parts[2]),Integer.parseInt(parts[1]),Integer.parseInt(parts[0])));
        }

        Collections.sort(listaFechasDTO, new FechaHabilitadaComparator());

        return listaFechasDTO;

    }
}
