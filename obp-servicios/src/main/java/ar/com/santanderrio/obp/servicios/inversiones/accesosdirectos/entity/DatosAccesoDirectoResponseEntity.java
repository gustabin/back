package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class DatosAccesoDirectoResponseEntity{


	@JsonProperty("AccesoDirecto")
	private String accesoDirecto;
	
	/**
	 * @return the datos
	 */
	public String getAccesoDirecto() {
		return accesoDirecto;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setAccesoDirecto(String accesoDirecto) {
		this.accesoDirecto = accesoDirecto;
	}
	

}
