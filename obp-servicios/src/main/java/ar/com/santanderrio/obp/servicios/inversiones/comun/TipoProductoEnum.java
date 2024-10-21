/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum TipoProductoEnum.
 */
public enum TipoProductoEnum {

	/** Plazo Fijo. */
	PLAZO_FIJO("PF"),

	/** Fondos comunes de inversion. */
	FONDOS("FCI"),

	/** Titulos Valores. */
	TITULOS_VALORES("TV"),

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
	TipoProductoEnum(String codigo) {
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
	public static TipoProductoEnum fromCodigoString(String tipoBanca) {
		TipoProductoEnum[] valores = TipoProductoEnum.values();

		TipoProductoEnum response = null;

		for (TipoProductoEnum valor : valores) {
			if (valor.getCodigo().equalsIgnoreCase(tipoBanca)) {
				response = valor;
			}
		}
		return response;
	}
}
