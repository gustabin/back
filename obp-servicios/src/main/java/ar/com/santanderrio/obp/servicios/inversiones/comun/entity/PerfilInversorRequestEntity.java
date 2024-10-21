package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

public class PerfilInversorRequestEntity extends RequestBaseFirmado {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3937014551724841058L;
	
	/** The Datos*/
	@JsonProperty("Datos")
	private DatosPerfilInversorEntity datos = new DatosPerfilInversorEntity();

	/** 
	 * Gets the datos
	 * 
	 * @return
	 */
	public DatosPerfilInversorEntity getDatos() {
		return datos;
	}
	
	/**
	 * Sets de Datos
	 * 
	 * @param datos
	 */
	public void setDatos(DatosPerfilInversorEntity datos) {
		this.datos = datos;
	}
	

}
