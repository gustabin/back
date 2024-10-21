package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ItemCuentaTitulos extends ItemGenericoMaps{

	@NotNull
	@JsonProperty("NumeroCtaTitulo")
	private String numeroCtaTitulo;
	@JsonProperty("Titulares")
	private List<String> titulares;
	
	public String getNumeroCtaTitulo() {
		return numeroCtaTitulo;
	}
	public void setNumeroCtaTitulo(String numeroCtaTitulo) {
		this.numeroCtaTitulo = numeroCtaTitulo;
	}
	public List<String> getTitulares() {
		return titulares;
	}
	public void setTitulares(List<String> titulares) {
		this.titulares = titulares;
	}
	public void validate() throws ControlMapValidationException {
		super.validate();
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}
}
