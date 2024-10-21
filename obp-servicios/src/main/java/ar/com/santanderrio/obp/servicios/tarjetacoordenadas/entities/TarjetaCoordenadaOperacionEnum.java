/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities;

/**
 * Enum que detalla las operaciones para solicitar la tarjeta de coordenadas.
 *
 * @author marcelo.ruiz
 */
public enum TarjetaCoordenadaOperacionEnum {

	/** The transferencia. */
	TRANSFERENCIA("T"),
	/** The pago. */
	PAGO("P"),
	/** The validacion. */
	VALIDACION("V"),
	/** The otro. */
	OTRO("O");

	/** The valor. */
	private final String valor;

	/**
	 * Instantiates a new tarjeta coordenada operacion enum.
	 *
	 * @param valor
	 *            the valor
	 */
	private TarjetaCoordenadaOperacionEnum(String valor) {
		this.valor = valor;
	}

	/**
	 * Gets the valor.
	 *
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}

}
