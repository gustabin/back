/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum EstadoOperacionMovimimiento.
 */
public enum EstadoOperacionMovimimiento {

	/** The a confirmar. */
	A_CONFIRMAR("Operacion a confirmar"),

	/** The confirmada sin diferencia. */
	CONFIRMADA_SIN_DIFERENCIA("Operacion confirmada sin diferencia"),

	/** The confirmada con diferencia. */
	CONFIRMADA_CON_DIFERENCIA("Operacion confirmada con diferencia"),

	/** The a confirmar con cpc. */
	A_CONFIRMAR_CON_CPC("Operacion a confirmar con CPC"),

	/** The sin informar. */
	SIN_INFORMAR("Estado sin poder informar");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new estado operacion movimimiento.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	EstadoOperacionMovimimiento(String descripcion) {
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
