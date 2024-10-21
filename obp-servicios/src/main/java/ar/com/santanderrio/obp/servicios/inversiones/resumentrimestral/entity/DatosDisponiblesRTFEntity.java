package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosDisponiblesRTFEntity extends EntityBase{
	
	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("Id")
	private String id;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
