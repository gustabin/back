package ar.com.santanderrio.obp.servicios.prestamos.dto;

public class ValidateTokenSmsRequestDTO {

	private String nup;
	private String smsToken;

	public ValidateTokenSmsRequestDTO() {
		super();
	}

	public ValidateTokenSmsRequestDTO(String nup, String smsToken) {
		this();
		this.nup = nup;
		this.smsToken = smsToken;
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getSmsToken() {
		return smsToken;
	}

	public void setSmsToken(String smsToken) {
		this.smsToken = smsToken;
	}

}
