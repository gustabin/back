package ar.com.santanderrio.obp.servicios.prestamos.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ValidateTokenSmsResponseDTO {

	private Boolean valid;

	public ValidateTokenSmsResponseDTO() {
		super();
	}

	public ValidateTokenSmsResponseDTO(Boolean valid) {
		this();
		this.valid = valid;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
}