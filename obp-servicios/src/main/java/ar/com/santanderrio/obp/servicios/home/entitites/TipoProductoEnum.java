/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.entitites;


/**
 * The Enum TipoCuenta.
 */
public enum TipoProductoEnum {

	/** The Tarjeta Visa. */
	VISA(7, "VI"),

	/** The Tarjeta AMEX. */
	AMEX(42, "AMX"),
	
	/** The Tarjeta MASTERCARD. */
	MASTERCARD(6,"MC"),

	/** The Tarjeta RECARGABLE. */
	RECARGABLE(77,"VR");
	
	/** The codigo. */
	private Integer codigo;
	
	/** The tipoProducto. */
	private String tipoProducto;

	/**
	 * Instantiates a new tipo producto
	 *
	 * @param tipoProducto
	 *            the tipoProducto
	 * @return 
	 */
	TipoProductoEnum(Integer codigo, String tipoProducto) {
		this.codigo = codigo;
		this.tipoProducto = tipoProducto;
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
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo cuenta
	 */
	public static TipoProductoEnum fromCodigo(Integer codigo) {
		TipoProductoEnum[] values = TipoProductoEnum.values();

		TipoProductoEnum response = null;

		for (TipoProductoEnum tipoProducto : values) {
			if (tipoProducto.getCodigo().equals(codigo)) {
				response = tipoProducto;
			}
		}
		return response;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

}