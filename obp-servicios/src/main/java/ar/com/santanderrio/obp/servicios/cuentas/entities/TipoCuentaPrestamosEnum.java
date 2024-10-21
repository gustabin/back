/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoCuentaPrestamosEnum.
 */
public enum TipoCuentaPrestamosEnum {

	/** The tipocta prestamo30. */
	TIPOCTA_PRESTAMO30("30", "Prestamo"),
	/** The tipocta prestamo31. */
	TIPOCTA_PRESTAMO31("31", "Prestamo Prendario");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo cuenta prestamos enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoCuentaPrestamosEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo cuenta prestamos enum
	 */
	public static TipoCuentaPrestamosEnum fromCodigo(String codigo) {
		TipoCuentaPrestamosEnum[] values = TipoCuentaPrestamosEnum.values();

		TipoCuentaPrestamosEnum response = null;

		for (TipoCuentaPrestamosEnum tipoCuenta : values) {
			if (tipoCuenta.getCodigo().equalsIgnoreCase(codigo)) {
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
