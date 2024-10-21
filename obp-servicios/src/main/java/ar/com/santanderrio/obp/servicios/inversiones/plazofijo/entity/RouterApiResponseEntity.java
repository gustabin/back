package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.codehaus.jackson.annotate.JsonProperty;


public class RouterApiResponseEntity{

	/** The Datos*/
	@JsonProperty("result")
	private String result;
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
