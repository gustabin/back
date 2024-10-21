package ar.com.santanderrio.obp.servicios.api.funds.exceptions;

public class BouncerAccessException extends RuntimeException {
    public BouncerAccessException() {
    }

    public BouncerAccessException(String message) {
        super(message);
    }
}
