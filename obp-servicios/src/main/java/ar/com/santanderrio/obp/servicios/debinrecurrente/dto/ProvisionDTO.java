package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ProvisionDTO {

	@JsonProperty("min")
	private Integer minimo;
	
	@JsonProperty("max")
	private Integer maximo;
	
	@JsonProperty("name")
	private String nombre;
	
	@JsonProperty("reference_tooltip")
	private String tooltipReferencia;


	public ProvisionDTO() {
	}

	public ProvisionDTO(Integer minimo, Integer maximo, String nombre, String tooltipReferencia) {
		this.minimo = minimo;
		this.maximo = maximo;
		this.nombre = nombre;
		this.tooltipReferencia = tooltipReferencia;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTooltipReferencia() {
		return tooltipReferencia;
	}

	public void setTooltipReferencia(String tooltipReferencia) {
		this.tooltipReferencia = tooltipReferencia;
	}
		
}
