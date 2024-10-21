
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosDetalleFondoRequestEntity;

public class DatosResumenFondosRimptEntity extends  DatosDetalleFondoRequestEntity {

	/** 
	 * The anio
	 * 
	 */
	@JsonProperty("Año")
	private String año;

	/** Gets the año
	 * @return the año
	 */ 
	public String getAño() {
		return año;
	}

	/**
	 * Sets the año
	 * @param año
	 */
	public void setAño(String año) {
		this.año = año;
	}
	
	
}
