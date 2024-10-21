package ar.com.santanderrio.obp.servicios.api.common.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ar.com.santanderrio.obp.servicios.api.customers.model.Error;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

public class ErrorResponse {

    private List<Error> errors = null;
    private String message;
    private String code;

    private ResponseEntity<Object> body;

    public List<Error> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public ResponseEntity<Object> getBody() {
        return body;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBody(ResponseEntity<Object> body) {
        this.body = body;
    }

    public ErrorResponse errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse code(String code) {
        this.code = code;
        return this;
    }

    public static ErrorResponse emptyErrorResponse() {
        return new ErrorResponse().code("ERROR").message("NO RESPONSE BODY FOUND");
    }

    public ErrorResponse addErrorsItem(Error errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<Error>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" + "code='" + code + '\'' + ", message='" + message + '\'' + ", errors=" + errors +'}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(code)
                .append(message)
                .append(errors)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse)) return false;
        ErrorResponse that = (ErrorResponse) o;
        return new EqualsBuilder()
                .append(code, that.code)
                .append(message, that.message)
                .append(errors, that.errors)
                .isEquals();
    }
}
