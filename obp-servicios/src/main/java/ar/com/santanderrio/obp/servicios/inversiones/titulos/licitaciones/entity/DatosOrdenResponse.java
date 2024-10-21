/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosOrdenResponse.
 */
public class DatosOrdenResponse {

	/** The num comprobante. */
	@JsonProperty("NroComprobante")
	private int numComprobante;

	/** The codigo. */
	@JsonProperty("Codigo")
	private int codigo;

	/** The descripcion. */
	@JsonProperty("Descripcion")
	private String descripcion;

	/**
	 * Gets the num comprobante.
	 *
	 * @return the num comprobante
	 */
	public int getNumComprobante() {
		return numComprobante;
	}

	/**
	 * Sets the num comprobante.
	 *
	 * @param numComprobante
	 *            the new num comprobante
	 */
	public void setNumComprobante(int numComprobante) {
		this.numComprobante = numComprobante;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
