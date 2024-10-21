package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import org.codehaus.jackson.annotate.JsonProperty;

public class PermissionDto {

	@JsonProperty("action")
	private String action;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("needsAuthorizationFrom")
	private String needsAuthorizationFrom;

	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeedsAuthorizationFrom() {
		return needsAuthorizationFrom;
	}

	public void setNeedsAuthorizationFrom(String needsAuthorizationFrom) {
		this.needsAuthorizationFrom = needsAuthorizationFrom;
	}
	
}
