package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosDetallesParticipantesRequestEntity extends EntityBase {
	
	@JsonProperty("Anio")
	private String anio;
	
	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	
	/**
	 * @return the datosServicios
	 */
	public DatosServiciosEntity getDatosServicios() {
		return datosServicios;
	}

	/**
	 * @param datosServicios the datosServicios to set
	 */
	public void setDatosServicios(DatosServiciosEntity datosServicios) {
		this.datosServicios = datosServicios;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * @param nup the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}
	
	

}
