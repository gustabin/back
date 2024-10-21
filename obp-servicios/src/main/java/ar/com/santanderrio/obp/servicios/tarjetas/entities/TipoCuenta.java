/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Enum TipoCuenta.
 */
public enum TipoCuenta {

	/** The Tarjeta Visa. */
	VISA(7, "VISA"),

	/** The Tarjeta AMEX. */
	AMEX(42, "AMEX"),
	
	/** The Tarjeta MASTERCARD. */
	MASTERCARD(6, "MASTERCARD"),

	/** The Tarjeta RECARGABLE. */
	RECARGABLE(77, "Recargable");

	/** The codigo. */
	private Integer codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo cuenta.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoCuenta(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo cuenta
	 */
	public static TipoCuenta fromCodigo(String codigo) {
		Integer valor = Integer.parseInt(codigo);
		return TipoCuenta.fromCodigo(valor);
	}

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo cuenta
	 */
	public static TipoCuenta fromCodigo(Integer codigo) {
		TipoCuenta[] values = TipoCuenta.values();

		TipoCuenta response = null;

		for (TipoCuenta tipoCuenta : values) {
			if (tipoCuenta.getCodigo().equals(codigo)) {
				response = tipoCuenta;
			}
		}
		return response;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
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
