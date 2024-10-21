/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

/**
 * The Enum TipoFondoEnum.
 */
public enum TipoFondoEnum {

	/** Fondos comunes de inversion. */
	MONEY_MARKET("1", "1"),

	/** Perfil agresivo. */
	RENTA_FIJA("2", "10"),

	/** Tenencia Valuada. */
	RENTA_VARIABLE("3", "11"),

	/** Renta Mixta. */
	RENTA_MIXTA("4", "4"),

	/** Otros. */
	OTROS("5", "12");

	/** The codigo pesos. */
	private String codigoPesos;

	/** The codigo dolares. */
	private String codigoDolares;

	/**
	 * Instantiates a new tipo fondo enum.
	 *
	 * @param codigoPesos
	 *            the codigo pesos
	 * @param codigoDolares
	 *            the codigo dolares
	 */
	TipoFondoEnum(String codigoPesos, String codigoDolares) {
		this.codigoPesos = codigoPesos;
		this.codigoDolares = codigoDolares;
	}

	/**
	 * Gets the codigo pesos.
	 *
	 * @return the codigo pesos
	 */
	public String getCodigoPesos() {
		return codigoPesos;
	}

	/**
	 * Gets the codigo dolares.
	 *
	 * @return the codigo dolares
	 */
	public String getCodigoDolares() {
		return codigoDolares;
	}

	/**
	 * From codigo producto pesos.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo fondo enum
	 */
	public static TipoFondoEnum fromCodigoProductoPesos(String codigo) {
		TipoFondoEnum[] values = TipoFondoEnum.values();

		TipoFondoEnum response = null;

		for (TipoFondoEnum codigoOperacion : values) {
			if (codigoOperacion.getCodigoPesos().equals(codigo)) {
				response = codigoOperacion;
			}
		}
		return response;
	}

	/**
	 * From codigo producto dolares.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo fondo enum
	 */
	public static TipoFondoEnum fromCodigoProductoDolares(String codigo) {
		TipoFondoEnum[] values = TipoFondoEnum.values();

		TipoFondoEnum response = null;

		for (TipoFondoEnum codigoOperacion : values) {
			if (codigoOperacion.getCodigoDolares().equals(codigo)) {
				response = codigoOperacion;
			}
		}
		return response;
	}

}
