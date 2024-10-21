/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * The Enum TipoInstrumentoInfoMercadoEnum.
 */
public enum TipoInstrumentoInfoMercadoEnum {

	/** The titulos publicos. */
	TITULOS_PUBLICOS("PUB", "Títulos públicos", 1), /** The titulos privados. */
 TITULOS_PRIVADOS("PRV", "Títulos privados", 2), /** The acciones. */
 ACCIONES("ACC",
			"Acciones", 3), 
 /** The cedears. */
 CEDEARS("CDR", "Cedears", 4);

	/** The codigo. */
	private String codigo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The orden. */
	private Integer orden;

	/**
	 * Instantiates a new tipo instrumento info mercado enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 * @param orden
	 *            the orden
	 */
	private TipoInstrumentoInfoMercadoEnum(String codigo, String descripcion, Integer orden) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.orden = orden;
	}

	/**
	 * Obtener tipo por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo instrumento info mercado enum
	 */
	public static TipoInstrumentoInfoMercadoEnum obtenerTipoPorCodigo(String codigo) {
		TipoInstrumentoInfoMercadoEnum[] values = TipoInstrumentoInfoMercadoEnum.values();
		for (TipoInstrumentoInfoMercadoEnum tipo : values) {
			if (StringUtils.equals(codigo, tipo.getCodigo())) {
				return tipo;
			}
		}
		return null;
	}

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
