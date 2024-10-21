package ar.com.santanderrio.obp.servicios.referidos.dto;

public class ReferidosTokenInputDTO {

	private String sec;
	
	private String nup;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String referencia) {
		this.sec = referencia;
	}
	
}
