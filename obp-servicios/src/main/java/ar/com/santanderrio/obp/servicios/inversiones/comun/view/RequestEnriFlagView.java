package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class RequestEnriFlagView {
	
	@JsonProperty("Firma")
	private String firma;
	
	@JsonProperty("Dato")
	private String dato;
	
	@JsonProperty("Canal")
	private String canal;
	
	@JsonProperty("SubCanal")
	private String subCanal;

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getSubCanal() {
		return subCanal;
	}

	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}
	
	

}
