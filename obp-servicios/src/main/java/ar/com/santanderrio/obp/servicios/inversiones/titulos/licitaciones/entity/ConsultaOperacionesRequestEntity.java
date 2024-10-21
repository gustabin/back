/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ConsultaOperacionesRequestEntity.
 */
public class ConsultaOperacionesRequestEntity extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2030148699810940364L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaOperaciones datos;
	
	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaOperaciones getDatos() {
		return datos;
	}
	
	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultaOperaciones datos) {
		this.datos = datos;
	}
}
