/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum DivisaEnum.
 */
public enum DivisaEnum {

	/** The peso. */
	PESO("peso", "$", "ARS"),

	/** The dolar. */
	DOLAR("dolar", "u$s", "USD");

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
	DivisaEnum(String moneda, String simbolo, String codigo) {
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
	public static DivisaEnum fromMonedaString(String moneda) {
		DivisaEnum[] values = DivisaEnum.values();

		DivisaEnum response = null;

		for (DivisaEnum divisa : values) {
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
	public static DivisaEnum fromCodigoString(String codigo) {
		DivisaEnum[] values = DivisaEnum.values();

		DivisaEnum response = null;

		for (DivisaEnum divisa : values) {
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
	public static DivisaEnum fromSimboloString(String simbolo) {
		DivisaEnum[] values = DivisaEnum.values();

		DivisaEnum response = null;

		for (DivisaEnum divisa : values) {
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
