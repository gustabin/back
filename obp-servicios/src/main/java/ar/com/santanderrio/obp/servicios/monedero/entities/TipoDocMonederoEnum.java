/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Enum TipoDocMonederoEnum.
 */
public enum TipoDocMonederoEnum {

	/** The n. */
	N("N", "09"),

	/** The c. */
	C("C", "07"),

	/** The e. */
	E("E", "06"),

	/** The i. */
	I("I", "01"),

	/** The p. */
	P("P", "08"),

	/** The t. */
	T("T", "03"),

	/** The l. */
	L("L", "04"),

	/** The d. */
	D("D", "05");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Instantiates a new tipo doc monedero enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoDocMonederoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Obtener tipo doc monedero.
	 *
	 * @param campo
	 *            the campo
	 * @return the tipo doc monedero enum
	 */
	public static TipoDocMonederoEnum obtenerTipoDocMonedero(String campo) {
		TipoDocMonederoEnum[] values = TipoDocMonederoEnum.values();
		for (TipoDocMonederoEnum tipoDocMonedero : values) {
			if (campo.trim().equalsIgnoreCase(tipoDocMonedero.getCampo())) {
				return tipoDocMonedero;
			}
		}
		return null;
	}
}
