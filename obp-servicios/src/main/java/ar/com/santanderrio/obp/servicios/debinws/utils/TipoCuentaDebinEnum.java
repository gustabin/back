package ar.com.santanderrio.obp.servicios.debinws.utils;

/**
 * The Enum TipoCuentaDebinEnum.
 */
public enum TipoCuentaDebinEnum {

	/** The tipo cuenta corriente pesos. */
	TIPO_CC_PESOS("01"),
	/** The tipo cuenta corriente dolares. */
	TIPO_CC_DOLAR("02"),
	/** The tipo caja de ahorros pesos. */
	TIPO_CA_PESOS("11"),
	/** The tipo caja de ahorros dolares. */
	TIPO_CA_DOLARES("12"),
	/** The tipo cuenta unica pesos. */
	TIPO_CU_PESOS("11"),
	/** The tipo cuenta unicas dolares. */
	TIPO_CU_DOLARES("12");

	/** The codigo. */
	private String codigo;

	/**
	 * Instantiates a new tipo cuenta debin enum.
	 *
	 * @param codigo
	 *            the codigo
	 */
	TipoCuentaDebinEnum(String codigo) {
		this.codigo = codigo;
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
