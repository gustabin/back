package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosEnri {
	
	@JsonProperty("Habilitado")
	private boolean habilitado;

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	

}
