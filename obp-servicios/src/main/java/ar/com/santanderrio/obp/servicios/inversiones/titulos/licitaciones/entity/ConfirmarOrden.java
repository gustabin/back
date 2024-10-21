/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ConfirmarOrden.
 */
public class ConfirmarOrden extends BaseRequestEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7241597938207401677L;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosConfirmarOrden datos;

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConfirmarOrden getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConfirmarOrden datos) {
		this.datos = datos;
	}

}
