/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.common;

/**
 * The Enum TipoChequeraENUM.
 */
public enum TipoChequeraENUM {

	/** The tipo chequera comun. */
	TIPO_CHEQUERA_COMUN("00", "TIPO_CHEQUERA_COMUN"),

	/** The tipo chequera pago diferido. */
	TIPO_CHEQUERA_PAGO_DIFERIDO("01", "TIPO_CHEQUERA_PAGO_DIFERIDO");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo chequera ENUM.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoChequeraENUM(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
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
