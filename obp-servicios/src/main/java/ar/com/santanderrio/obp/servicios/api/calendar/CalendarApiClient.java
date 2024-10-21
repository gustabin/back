package ar.com.santanderrio.obp.servicios.api.calendar;

import ar.com.santanderrio.obp.servicios.api.calendar.dto.CalendarApiDateDTO;
import ar.com.santanderrio.obp.servicios.api.calendar.exception.CalendarApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class CalendarApiClient implements CalendarApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarApiClient.class);
    @Value("${SOLAUMENTOLIMTRANSF.LIMITE.DIAS.OPERACION}")
    private int limiteDiaOperacion;

    @Value("${CALENDAR-API.URL}")
    private String calendarApiUrl;
    private final RestTemplate restTemplate;

    private static final String FORMAT_DATE= "yyyy-MM-dd";

    @Autowired
    public CalendarApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CalendarApiDateDTO> getDates() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        final Calendar today = Calendar.getInstance();
        final String todayNow = dateFormat.format(today.getTime());
        today.add(Calendar.DATE,limiteDiaOperacion);
        String plusDias = dateFormat.format(today.getTime());

        try {
            LOGGER.info("API: Obteniendo listado de dias habiles.-");
            URI requestUriWithQueryParams = UriComponentsBuilder
                    .fromUriString(calendarApiUrl + "?dayFrom={dayFrom}&dayTo={dayTo}&enabled={enabled}")
                    .replaceQueryParam("dayFrom", todayNow)
                    .replaceQueryParam("dayTo", plusDias)
                    .replaceQueryParam("enabled", "true")
                    .build().toUri();

            return Arrays.asList(restTemplate.getForObject(requestUriWithQueryParams, CalendarApiDateDTO[].class));

        }catch (HttpClientErrorException e) {

            throw new CalendarApiException(e.getResponseBodyAsString(), e);

        } catch (Exception e) {

            throw new CalendarApiException(e);

        }

    }

}
