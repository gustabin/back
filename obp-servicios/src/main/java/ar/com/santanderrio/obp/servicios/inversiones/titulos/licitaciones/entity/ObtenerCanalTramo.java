/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ObtenerCanalTramo.
 *
 * @author juan.pablo.picate
 */
public class ObtenerCanalTramo extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -211828630129221936L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosObtenerCanalTramo datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosObtenerCanalTramo getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosObtenerCanalTramo datos) {
		this.datos = datos;
	}

}
