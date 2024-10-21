package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class OfertasFinanciacionErrorEntity.
 */
public class OfertasFinanciacionErrorEntity {
	
	@JsonProperty("code")
	private String code;

	@JsonProperty("message")
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
