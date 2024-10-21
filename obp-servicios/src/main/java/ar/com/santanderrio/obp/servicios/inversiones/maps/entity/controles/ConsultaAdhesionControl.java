package ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.comun.utils.ValidationEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.RequestMap;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;

public class ConsultaAdhesionControl extends ControlMaps implements RequestMap {

	@NotNull
	@JsonProperty("Activas")
	private List<FormularioControl> activas;
	@NotNull
	@JsonProperty("Inactivas")
	private List<FormularioControl> inactivas;

	public List<FormularioControl> getActivas() {
		return activas;
	}

	public void setActivas(List<FormularioControl> activas) {
		this.activas = activas;
	}

	public List<FormularioControl> getInactivas() {
		return inactivas;
	}

	public void setInactivas(List<FormularioControl> inactivas) {
		this.inactivas = inactivas;
	}

	@Override
	public String getSegmento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdServicio() {
		// TODO Auto-generated method stub
		return null;
	}

	public void validate() throws ControlMapValidationException {
		super.validate();
		if (!ValidationEntity.validate(this)) {
			throw new ControlMapValidationException("Validation constraints");
		}
	}
}
