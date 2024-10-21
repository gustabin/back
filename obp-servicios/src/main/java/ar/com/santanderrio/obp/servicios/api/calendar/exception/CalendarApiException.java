package ar.com.santanderrio.obp.servicios.api.calendar.exception;

public class CalendarApiException extends RuntimeException {

    public CalendarApiException(String message , Throwable e) {

        super(message, e);

    }

    public CalendarApiException(Throwable e) {

        super(e);

    }
}
