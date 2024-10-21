/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum TipoProductoEnum.
 */
public enum TipoProductoBPrivEnum {

	/** Plazo Fijo. */
	PLAZO_FIJO("PF"),

	/** Fondos comunes de inversion. */
	FONDOS("FCI"),

	/** Titulos Valores. */
	TITULOS_VALORES("TV"),
	
	/** Liquidez. */
	LIQUIDEZ("LIQ"),

	/** The custodia. */
	/* Custodia */
	CUSTODIA("CUS");

	/** The codigo. */
	private final String codigo;

	/**
	 * Instantiates a new tipo banca enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	TipoProductoBPrivEnum(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * PF para lazo fijo, FCI para fondos comunes de inversion, TV para tenencia
	 * consolidada.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * From codigo string.
	 *
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the tipo banca enum
	 */
	public static TipoProductoBPrivEnum fromCodigoString(String tipoBanca) {
		TipoProductoBPrivEnum[] valores = TipoProductoBPrivEnum.values();

		TipoProductoBPrivEnum response = null;

		for (TipoProductoBPrivEnum valor : valores) {
			if (valor.getCodigo().equalsIgnoreCase(tipoBanca)) {
				response = valor;
			}
		}
		return response;
	}
}
