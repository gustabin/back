/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.List;

/**
 * The Class ObtenerCuentasTitulosResponse.
 */
public class ObtenerCuentasTitulosResponse {

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/** The datos. */
	private List<DatosObtenerCuentasTitulosResponse> datos;

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
	 *            the codigo to set
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
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public List<DatosObtenerCuentasTitulosResponse> getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(List<DatosObtenerCuentasTitulosResponse> datos) {
		this.datos = datos;
	}

}
