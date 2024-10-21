package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class GroupDto {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("permissions")
	private List<PermissionDto> permissions;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}
		
}
