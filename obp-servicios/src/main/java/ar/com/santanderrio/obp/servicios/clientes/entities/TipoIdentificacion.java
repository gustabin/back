/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Enum TipoIdentificacion.
 */
public enum TipoIdentificacion {

	/** The dni. */
	DNI("N", "DNI"),

	/** The libreta civica. */
	LIBRETA_CIVICA("C", "LC"),

	/** The libreta de enrolamiento. */
	LIBRETA_DE_ENROLAMIENTO("E", "LE"),

	/** The cedula de identidad. */
	CEDULA_DE_IDENTIDAD("I", "Cedula de identidad"),

	/** The cedula militar. */
	CEDULA_MILITAR("M", "Cedula militar"),

	/** The pasaporte. */
	PASAPORTE("P", "Pasaporte"),

	/** The cuit. */
	CUIT("T", "CUIT"),

	/** The cuil. */
	CUIL("L", "CUIL"),

	/** The cdi. */
	CDI("D", "CDI"),

	/** The dni extranjero. */
	DNI_EXTRANJERO("X", "DX"),

	/** The certificado internacional. */
	CERTIFICADO_INTERNACIONAL("F", "Certificado internacional");

	/** The key. */
	private String key;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo identificacion.
	 *
	 * @param key
	 *            the key
	 * @param descripcion
	 *            the descripcion
	 */
	TipoIdentificacion(String key, String descripcion) {
		this.key = key;
		this.descripcion = descripcion;
	}

	/**
	 * From key.
	 *
	 * @param key
	 *            the key
	 * @return the tipo identificacion
	 */
	public static TipoIdentificacion fromKey(String key) {
		TipoIdentificacion[] values = TipoIdentificacion.values();
		for (TipoIdentificacion tipoIdentificacion : values) {
			if (key.trim().equalsIgnoreCase(tipoIdentificacion.getKey())) {
				return tipoIdentificacion;
			}
		}
		return null;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
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
