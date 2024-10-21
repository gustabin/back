package ar.com.santanderrio.obp.servicios.debinapi.dto;

import java.util.List;

public class DetailErrorDTO {
    private List<ErrorDTO> errors;

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}
