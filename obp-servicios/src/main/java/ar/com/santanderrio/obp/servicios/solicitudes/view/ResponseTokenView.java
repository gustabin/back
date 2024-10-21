package ar.com.santanderrio.obp.servicios.solicitudes.view;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseTokenView {
	
	@JsonProperty("Token")
	private String token;
	
	@JsonProperty("Message")
	private String message;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
