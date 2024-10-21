/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum OrigenTransaccionEnum.
 */
public enum OrigenTransaccionEnum {

	/** The ban. */
	BAN("Banelco"),

	/** The rio. */
	RIO("RIO Self");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new origen transaccion enum.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	private OrigenTransaccionEnum(String descripcion) {
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
