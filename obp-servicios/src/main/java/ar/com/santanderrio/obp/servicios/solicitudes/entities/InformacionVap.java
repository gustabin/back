package ar.com.santanderrio.obp.servicios.solicitudes.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class InformacionVap {
	
	@JsonProperty("Personas")
	private List<PersonasVap> personas;

	public List<PersonasVap> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonasVap> personas) {
		this.personas = personas;
	}
	
	

}
