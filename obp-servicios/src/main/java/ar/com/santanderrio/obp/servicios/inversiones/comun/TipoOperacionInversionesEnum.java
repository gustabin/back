/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum TipoOperacionInversionesEnum.
 */
public enum TipoOperacionInversionesEnum {

	/** Fondos comunes de inversion. */
	FONDO_COMUN_DE_INVERSION("FCI"),

	/** Perfil agresivo. */
	PLAZO_FIJO("PF"),

	/** titulo valores. */
	TITULO_VALORES("TV"),

	/** Tenencia Valuada. */
	TENENCIA_VALUADA("TOD"),
	
	/** The analisis cartera. */
	ANALISIS_CARTERA("AC"),
	
	TARJETAS_HOME("TAR");

	/** The codigo producto. */
	private String codigoProducto;

	/**
	 * Instantiates a new tipo operacion inversiones enum.
	 *
	 * @param codigoProducto
	 *            the codigo producto
	 */
	TipoOperacionInversionesEnum(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the codigo producto.
	 *
	 * @return the codigo producto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * From codigo producto.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo operacion inversiones enum
	 */
	public static TipoOperacionInversionesEnum fromCodigoProducto(String codigo) {
		TipoOperacionInversionesEnum[] values = TipoOperacionInversionesEnum.values();

		TipoOperacionInversionesEnum response = null;

		for (TipoOperacionInversionesEnum codigoOperacion : values) {
			if (codigoOperacion.getCodigoProducto().equals(codigo)) {
				response = codigoOperacion;
			}
		}
		return response;
	}

}
