package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoginCommand {

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("password")
	private String password;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
