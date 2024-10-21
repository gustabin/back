/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoOperacionMovimimiento.
 */
public enum TipoOperacionMovimimiento {

	/** The deposito de efectivo. */
	DEPOSITO_DE_EFECTIVO("Deposito de efectivo"),

	/** The pago de tarjeta. */
	PAGO_DE_TARJETA("Pago de tarjeta"),

	/** The deposito de cheque. */
	DEPOSITO_DE_CHEQUE("Deposito de cheque"),

	/** The deposito de cupones. */
	DEPOSITO_DE_CUPONES("Deposito de cupones");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo operacion movimimiento.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	private TipoOperacionMovimimiento(String descripcion) {
		this.descripcion = descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.descripcion;
	}

}
