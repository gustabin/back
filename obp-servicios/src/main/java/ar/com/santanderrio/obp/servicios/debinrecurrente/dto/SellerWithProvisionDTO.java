package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SellerWithProvisionDTO {

	@JsonProperty("cuit")
	private String cuit;
	
	@JsonProperty("fancyName")
	private String nombreFantasia;
	
	@JsonProperty("provisions")
	private List<ProvisionDTO> servicios;

	public SellerWithProvisionDTO() {
	}

	public SellerWithProvisionDTO(String cuit, String nombreFantasia, List<ProvisionDTO> servicios) {
		this.cuit = cuit;
		this.nombreFantasia = nombreFantasia;
		this.servicios = servicios;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public List<ProvisionDTO> getServicios() {
		return servicios;
	}

	public void setServicios(List<ProvisionDTO> servicios) {
		this.servicios = servicios;
	}
	
	
}
