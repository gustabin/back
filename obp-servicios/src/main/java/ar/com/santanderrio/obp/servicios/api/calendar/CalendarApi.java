package ar.com.santanderrio.obp.servicios.api.calendar;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;

import java.util.List;

public interface CalendarApi {

    List<CalendarApiDateDTO> getDates();
}
