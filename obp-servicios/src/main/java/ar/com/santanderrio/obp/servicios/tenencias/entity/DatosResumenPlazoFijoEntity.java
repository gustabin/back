package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosResumenPlazoFijoEntity extends EntityBase {
	
	@JsonProperty("AñoDesde")
	private String añoDesde;
	
	@JsonProperty("AñoHasta")
	private String añoHasta;
	
	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	/**
	 * @return the añoDesde
	 */
	public String getAñoDesde() {
		return añoDesde;
	}

	/**
	 * @param añoDesde the añoDesde to set
	 */
	public void setAñoDesde(String añoDesde) {
		this.añoDesde = añoDesde;
	}

	/**
	 * @return the añoHasta
	 */
	public String getAñoHasta() {
		return añoHasta;
	}

	/**
	 * @param añoHasta the añoHasta to set
	 */
	public void setAñoHasta(String añoHasta) {
		this.añoHasta = añoHasta;
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

}
