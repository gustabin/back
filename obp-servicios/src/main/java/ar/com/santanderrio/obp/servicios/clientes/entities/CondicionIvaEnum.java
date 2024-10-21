/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Enum CondicionIvaEnum.
 */
public enum CondicionIvaEnum {

	/** The consumidor final. */
	CONSUMIDOR_FINAL("CONS", "Consumidor Final"),

	/** The activo. */
	ACTIVO("ACT", "Activo"),

	/** The exento. */
	EXENTO("EXE ", "Exento"),

	/** The monotributo. */
	MONOTRIBUTO("MONO", "Monotributo"),

	/** The no alcanzado. */
	NO_ALCANZADO("NOAL", "No alcanzado"),

	/** The no inscripto. */
	NO_INSCRIPTO("NOIN", "No Inscripto"),

	/** The null. */
	NULL("", "");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the condicion IVA enum
	 */
	public static CondicionIvaEnum fromCodigo(String codigo) {
		CondicionIvaEnum[] values = CondicionIvaEnum.values();

		CondicionIvaEnum response = NULL;

		for (CondicionIvaEnum tipo : values) {
			if (tipo.getCodigo().equals(codigo)) {
				response = tipo;
			}
		}
		return response;
	}

	/**
	 * From descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the tipo compania enum
	 */
	public static CondicionIvaEnum fromDescripcion(String descripcion) {
		CondicionIvaEnum[] values = CondicionIvaEnum.values();

		CondicionIvaEnum response = null;

		for (CondicionIvaEnum tipo : values) {
			if (tipo.getDescripcion().equals(descripcion)) {
				response = tipo;
			}
		}
		return response;
	}

	/**
	 * Instantiates a new tipo compania enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	CondicionIvaEnum(String codigo, String descripcion) {
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
