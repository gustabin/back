/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity;

/**
 * The Enum DivisaEnum.
 */
public enum DivisaEnumCartera {

	/** The peso. */
	PESO("peso", "$", "ARS"),

	/** The dolar. */
	DOLAR("dolar", "u$s", "USD"),
	
	/** The reexp peso. */
	REEXP_PESO("Reexpresado pesos", "$", "RARS"),
	
	/** The reexp dolar. */
	REEXP_DOLAR("Reexpresado dolar","u$s", "RUSD");

	/** The moneda. */
	private String moneda;

	/** The simbolo. */
	private String simbolo;

	/** The codigo. */
	private String codigo;

	/**
	 * Instantiates a new divisa enum.
	 *
	 * @param moneda
	 *            the moneda
	 * @param simbolo
	 *            the simbolo
	 * @param codigo
	 *            the codigo
	 */
	DivisaEnumCartera(String moneda, String simbolo, String codigo) {
		this.moneda = moneda;
		this.simbolo = simbolo;
		this.codigo = codigo;
	}

	/**
	 * From moneda string.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the divisa enum
	 */
	public static DivisaEnumCartera fromMonedaString(String moneda) {
		DivisaEnumCartera[] values = DivisaEnumCartera.values();

		DivisaEnumCartera response = null;

		for (DivisaEnumCartera divisa : values) {
			if (divisa.getMoneda().equals(moneda)) {
				response = divisa;
			}
		}
		return response;
	}

	/**
	 * From codigo string.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the divisa enum
	 */
	public static DivisaEnumCartera fromCodigoString(String codigo) {
		DivisaEnumCartera[] values = DivisaEnumCartera.values();

		DivisaEnumCartera response = null;

		for (DivisaEnumCartera divisa : values) {
			if (divisa.getCodigo().equals(codigo)) {
				response = divisa;
			}
		}
		return response;
	}

	/**
	 * From simbolo string.
	 *
	 * @param simbolo
	 *            the simbolo
	 * @return the divisa enum
	 */
	public static DivisaEnumCartera fromSimboloString(String simbolo) {
		DivisaEnumCartera[] values = DivisaEnumCartera.values();

		DivisaEnumCartera response = null;

		for (DivisaEnumCartera divisa : values) {
			if (divisa.getSimbolo().equals(simbolo)) {
				response = divisa;
			}
		}
		return response;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Gets the simbolo.
	 *
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

}
