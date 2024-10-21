package ar.com.santanderrio.obp.servicios.extraccionefectivo.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class Origin {

	@JsonProperty("account_number")
	private long accountNumber;
	
	@JsonProperty("account_type")
	private String accountType;
	
	@JsonProperty("branch_account")
	private String branch;
	
	@JsonProperty("identification_number")
	private String identificationNumber;
	
	@JsonProperty("nup")
	private String nup;
	
	@JsonProperty("identification_type")
	private String identificationType;
	
	@JsonProperty("first_name")
	private String nombreOrigen;
	
	@JsonProperty("last_name")
	private String apellidoOrigen;
	
	@JsonProperty("email")
	private String emailOrigen;

	@JsonProperty("birth_date")
	private String fechaNacimientoOrigen;
		
	
	public String getAccountType() {
		return accountType;
	}
	
	public void setAccountType(String tipoCuenta) {
		this.accountType = tipoCuenta;
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(long numeroCuenta) {
		this.accountNumber = numeroCuenta;
	}
	
	public String getIdentificationType() {
		return identificationType;
	}
	
	public void setIdentificationType(String tipoDocumento) {
		this.identificationType = tipoDocumento;
	}
	
	public String getNup() {
		return nup;
	}
	
	public void setNup(String nup) {
		this.nup = nup;
	}
	
	public String getBranch() {
		return branch;
	}
	
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getIdentificationNumber() {
		return identificationNumber;
	}
	
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public void setNombreOrigen(String nombreOrigen) {
		this.nombreOrigen = nombreOrigen;
	}

	public String getApellidoOrigen() {
		return apellidoOrigen;
	}

	public void setApellidoOrigen(String apellidoOrigen) {
		this.apellidoOrigen = apellidoOrigen;
	}

	public String getEmailOrigen() {
		return emailOrigen;
	}

	public void setEmailOrigen(String emailOrigen) {
		this.emailOrigen = emailOrigen;
	}

	public String getFechaNacimientoOrigen() {
		return fechaNacimientoOrigen;
	}

	public void setFechaNacimientoOrigen(String fechaNacimientoOrigen) {
		this.fechaNacimientoOrigen = fechaNacimientoOrigen;
	}
	
}
