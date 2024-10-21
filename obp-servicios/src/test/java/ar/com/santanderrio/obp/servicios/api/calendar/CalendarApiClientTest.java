package ar.com.santanderrio.obp.servicios.api.calendar;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.dto.FechaHabilitadaDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.exception.CalendarApiException;
import ar.com.santanderrio.obp.servicios.api.calendar.mapper.CalendarApiConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CalendarApiClientTest {

    @Mock
    private RestTemplate restTemplate;


    private CalendarApiClient calendarApiClient;

    private final String calendarApiUrl = "https://calendar-api-dev-calendar-api.apps.ocp.ar.bsch/v1/api/dates";

    private final int limiteDiaOperacion =30;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        calendarApiClient = new CalendarApiClient(restTemplate);
        ReflectionTestUtils.setField(calendarApiClient, "calendarApiUrl", calendarApiUrl);
        ReflectionTestUtils.setField(calendarApiClient, "limiteDiaOperacion", limiteDiaOperacion);
    }

    @Test
    public void whenGetCalendar_thenGetCalendarOk() {

        Mockito.when(restTemplate.getForObject(Matchers.any(URI.class), Matchers.eq(CalendarApiDateDTO[].class)))
                .thenReturn(getCalendarArray());

        List<CalendarApiDateDTO> listPrueba = calendarApiClient.getDates();

        assertNotNull(listPrueba);
        assertEquals(2, listPrueba.size());
        assertEquals("2023-10-19", listPrueba.get(0).getDate());
        assertEquals("2023-10-20", listPrueba.get(1).getDate());
    }

    public CalendarApiDateDTO[] getCalendarArray() {
        CalendarApiDateDTO[] calendarApiDateDTOS = new CalendarApiDateDTO[2];
        calendarApiDateDTOS[0] = new CalendarApiDateDTO("2023-10-19", true);
        calendarApiDateDTOS[1] = new CalendarApiDateDTO("2023-10-20", true);

        return calendarApiDateDTOS;
    }

    @Test(expected = CalendarApiException.class)
    public void whenGetCalendar_thenThrowsCalendarApiException() throws SQLException, UnsupportedEncodingException {
        Mockito.when(restTemplate.getForObject(Matchers.any(URI.class), Matchers.eq(CalendarApiDateDTO[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        calendarApiClient.getDates();
    }


    @Test
    public void calendarApiDTOMapperFechaHabilitadaDTOOk() throws ParseException {
        List<CalendarApiDateDTO> listCalendarApiDTO = Arrays.asList(
                new CalendarApiDateDTO("2023-12-19", true),
                new CalendarApiDateDTO("2023-12-20", true),
                new CalendarApiDateDTO("2024-1-3", true)
        );

        List<FechaHabilitadaDTO> listaFechas = CalendarApiConverter.calendarApiDTOMapperFechaHabilitadaDTO(listCalendarApiDTO);

        String diaGetCero=listaFechas.get(0).getAnio()+"-"+listaFechas.get(0).getMes()+"-"+listaFechas.get(0).getDia();
        String diaGetUno=listaFechas.get(1).getAnio()+"-"+listaFechas.get(1).getMes()+"-"+listaFechas.get(1).getDia();
        String diaGetDos=listaFechas.get(2).getAnio()+"-"+listaFechas.get(2).getMes()+"-"+listaFechas.get(2).getDia();

        assertEquals(3, listaFechas.size());
        assertEquals("2023-12-19", diaGetCero);
        assertEquals("2023-12-20", diaGetUno);
        assertEquals("2024-1-3", diaGetDos);
    }

}
