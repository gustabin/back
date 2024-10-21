/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

/**
 * The Enum IndicadorFuncion.
 *
 * @author B015167
 */
public enum IndicadorFuncion {

	/** The simulacion. */
	SIMULACION("S"),
	/** The liquidacion. */
	LIQUIDACION("L");

	/** The valor trfcci. */
	private String valorTrfcci;

	/**
	 * Instantiates a new indicador funcion.
	 *
	 * @param valorTrfcci
	 *            the valor trfcci
	 */
	private IndicadorFuncion(String valorTrfcci) {
		this.valorTrfcci = valorTrfcci;
	}

	/**
	 * Gets the valor trfcci.
	 *
	 * @return the valor trfcci
	 */
	public String getValorTrfcci() {
		return valorTrfcci;
	}
}
