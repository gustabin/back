/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.List;

/**
 * The Class ConsultarTenenciaRenovableResponse.
 */
public class ConsultarTenenciaRenovableResponse {

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/** The datos. */
	private List<DatosConsultarTenenciaRenovableResponse> datos;

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
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

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public List<DatosConsultarTenenciaRenovableResponse> getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(List<DatosConsultarTenenciaRenovableResponse> datos) {
		this.datos = datos;
	}

}
