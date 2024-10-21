	package ar.com.santanderrio.obp.servicios.clientes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosConsultaCtasEntity extends EntityBase{
	
	@JsonProperty("Nup")
	private String nup;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}
	
	

}
