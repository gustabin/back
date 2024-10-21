/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Enum TipoTarjeta.
 */
public enum TipoTarjeta {

	/** The amex platinum. */
	AMEX_PLATINUM("AMEX Platinum"),
	/** The amex internacional. */
	AMEX_INTERNACIONAL("AMEX Internacional"),
	/** The amex gold. */
	AMEX_GOLD("AMEX Gold"),
	/** The amex black. */
	AMEX_BLACK("AMEX Black"),
	/** The visa platinum. */
	VISA_PLATINUM("VISA Platinum"),
	/** The visa internacional. */
	VISA_INTERNACIONAL("VISA Internacional"),
	/** The visa gold. */
	VISA_GOLD("VISA Gold"),
	/** The visa black. */
	VISA_BLACK("VISA Black");

	/** The tipo. */
	private String tipo;

	/**
	 * Instantiates a new tipo tarjeta.
	 *
	 * @param tipo
	 *            the tipo
	 */
	private TipoTarjeta(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

}
