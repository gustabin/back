package ar.com.santanderrio.obp.servicios.extraccionefectivo.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class Destination {

	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("identification_type")
	private String identificationType;
	
	@JsonProperty("identification_number")
	private String identificationNumber;
	
	@JsonProperty("email")
	private String email;
	
	public String getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(String tipoDocumento) {
		this.identificationType = tipoDocumento;
	}
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(String nroDocumento) {
		this.identificationNumber = nroDocumento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
