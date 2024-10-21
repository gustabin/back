package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.items;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ItemTooltipFondo {

	@NotNull
	@JsonProperty("Titulo")
	private String titulo;
	@NotNull
	@JsonProperty("Contenido")
	private String contenido;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void validate() throws ControlMapValidationException {
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}
}
