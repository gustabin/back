/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.util;

/**
 * The Enum IndicadorTraspasoEnum.
 */
public enum IndicadorTraspasoEnum {

	/** The indicador activo traspaso. */
	INDICADOR_ACTIVO("A", "activo"),

	/** The indicador inactivo traspaso. */
	INDICADOR_INACTIVO("N", "inactivo");

	/**
	 * Instantiates a indicador traspaso enum.
	 *
	 * @param indicador
	 *            the indicador
	 * @param descripcion
	 *            the descripcion
	 */
	IndicadorTraspasoEnum(String indicador, String descripcion) {
		this.indicador = indicador;
		this.descripcion = descripcion;
	}

	/** The indicador. */
	private String indicador;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Gets the indicador.
	 *
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
