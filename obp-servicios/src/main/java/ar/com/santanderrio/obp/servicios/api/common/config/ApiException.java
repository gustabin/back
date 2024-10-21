package ar.com.santanderrio.obp.servicios.api.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final HttpHeaders httpHeaders;
    private final ErrorResponse errorResponse;

    public ApiException(ErrorResponse errorResponse, Throwable ex) {
        this(errorResponse, null, null, ex);
    }

    public ApiException(ErrorResponse errorResponse) {
        this(errorResponse, null);
    }

    public ApiException(ErrorResponse errorResponse, HttpHeaders httpHeaders, HttpStatus httpStatus, Throwable ex) {
        super(ex);
        this.errorResponse = errorResponse;
        this.httpHeaders = httpHeaders;
        this.httpStatus = httpStatus;
    }

    public ApiException(ErrorResponse errorResponse, HttpHeaders httpHeaders, HttpStatus httpStatus) {
        this(errorResponse, httpHeaders, httpStatus, null);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

}
