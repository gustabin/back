package ar.com.santanderrio.obp.servicios.solicitudes.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestTokenView {
	
	@JsonProperty("Canal")
	private String canal;
	
	@JsonProperty("Certificado")
	private String firma;

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	

}
