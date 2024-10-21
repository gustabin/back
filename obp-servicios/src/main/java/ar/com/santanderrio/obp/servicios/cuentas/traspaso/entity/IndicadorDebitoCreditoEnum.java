/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity;

/**
 * The Enum IndicadorDebitoCreditoEnum.
 */
public enum IndicadorDebitoCreditoEnum {

	/** The indicador debito. */
	INDICADOR_DEBITO("D"),

	/** The indicador credito. */
	INDICADOR_CREDITO("C");

	/**
	 * Instantiates a new indicador debito credito enum.
	 *
	 * @param indicador
	 *            the indicador
	 */
	IndicadorDebitoCreditoEnum(String indicador) {
		this.indicador = indicador;
	}

	/** The indicador. */
	private String indicador;

	/**
	 * Gets the indicador.
	 *
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}
}
