/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto;

/**
 * The Class ParametroInfoMercadoDTO.
 */
public class ParametroInfoMercadoDTO {

	/** The agrupamiento id. */
	private String agrupamientoId;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The legal. */
	private String legal;
	
	/** The defecto. */
	private Boolean defecto;
	
	/** The orden. */
	private Integer orden;

	/**
	 * Gets the agrupamiento id.
	 *
	 * @return the agrupamientoId
	 */
	public String getAgrupamientoId() {
		return agrupamientoId;
	}

	/**
	 * Sets the agrupamiento id.
	 *
	 * @param agrupamientoId
	 *            the agrupamientoId to set
	 */
	public void setAgrupamientoId(String agrupamientoId) {
		this.agrupamientoId = agrupamientoId;
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
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the defecto.
	 *
	 * @return the defecto
	 */
	public Boolean getDefecto() {
		return defecto;
	}

	/**
	 * Sets the defecto.
	 *
	 * @param defecto
	 *            the defecto to set
	 */
	public void setDefecto(Boolean defecto) {
		this.defecto = defecto;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

}
