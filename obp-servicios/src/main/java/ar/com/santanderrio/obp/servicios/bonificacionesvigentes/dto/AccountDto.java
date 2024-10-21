package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AccountDto {

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("sessionId")
	private String sessionId;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("office")
	private String office;
	
	@JsonProperty("groups")
	private List<GroupDto> groups;
	
	@JsonProperty("token")
	private String token;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public List<GroupDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
		
}
