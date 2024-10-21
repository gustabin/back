/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Enum TipoMonedaPagoTCEnum.
 */
public enum TipoMonedaPagoTCEnum {

	/** The pesos. */
	PESOS("0"),
	/** The dolares. */
	DOLARES("1"),
	/** The ambos. */
	AMBOS("2");

	/** The value. */
	private String value;

	/**
	 * Instantiates a new tipo moneda pago TC enum.
	 *
	 * @param value
	 *            the value
	 */
	TipoMonedaPagoTCEnum(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
