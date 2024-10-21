/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.view;

/**
 * The Enum TipoOperacionBajaProductoEnum.
 */
public enum TipoOperacionBajaProductoEnum {

	BAJA_PAQUETES_CUENTA("1", "Cuenta"),

	BAJA_TARJETA("2", "Tarjetas"),

	OTROS_MEDIO_PAGO("3", "Otros medios de pago"),
	
	ARREPENTIMIENTO("4", "producto");

	/** The codigo. */
	private String codigo;

	/** The nombre. */
	private String nombre;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Instantiates a new tipo operacion baja producto enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param nombre
	 *            the nombre
	 */
	TipoOperacionBajaProductoEnum(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Gets the by codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the by codigo
	 */
	public static TipoOperacionBajaProductoEnum getByCodigo(String codigo) {
		for (TipoOperacionBajaProductoEnum ennum : TipoOperacionBajaProductoEnum.values()) {
			if (ennum.getCodigo().equalsIgnoreCase(codigo)) {
				return ennum;
			}
		}
		return null;
	}
}
