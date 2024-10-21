/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoCajitaCuentaEnum.
 */
public enum TipoCajitaCuentaEnum {

	/** The cuenta unica pesos. */
	CUENTA_UNICA_PESOS("Cuenta Unica Pesos"),

	/** The cuenta unica dolares. */
	CUENTA_UNICA_DOLARES("Cuenta Unica Dolares"),

	/** The otra cuenta dolares. */
	OTRA_CUENTA_DOLARES("Otra Cuenta Dolares"),

	/** The otra cuenta pesos. */
	OTRA_CUENTA_PESOS("Otra Cuenta Pesos");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo cajita cuenta enum.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	TipoCajitaCuentaEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
