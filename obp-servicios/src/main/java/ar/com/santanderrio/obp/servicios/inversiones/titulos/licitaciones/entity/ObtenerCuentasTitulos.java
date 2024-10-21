/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ObtenerCuentasTitulos.
 */
public class ObtenerCuentasTitulos extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2202845328374532999L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosObtenerCuentasTitulos datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosObtenerCuentasTitulos getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosObtenerCuentasTitulos datos) {
		this.datos = datos;
	}

}
