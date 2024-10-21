/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum TipoBancaEnum.
 */
public enum TipoBancaEnum {

	/** Banca personal (o retail). */
	BANCA_PERSONAL("BR"),

	/** Banca privada. */
	BANCA_PRIVADA("BP"),

	/** Ambas bancas. */
	AMBAS_BANCAS("RP");

	/** The codigo. */
	private final String codigo;

	/**
	 * Instantiates a new tipo banca enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	TipoBancaEnum(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * BR para banca personal, BP para banca privada, BR para ambas bancas.
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
	public static TipoBancaEnum fromCodigoString(String tipoBanca) {
		TipoBancaEnum[] valores = TipoBancaEnum.values();

		TipoBancaEnum response = null;

		for (TipoBancaEnum valor : valores) {
			if (valor.getCodigo().equalsIgnoreCase(tipoBanca)) {
				response = valor;
			}
		}
		return response;
	}
}
