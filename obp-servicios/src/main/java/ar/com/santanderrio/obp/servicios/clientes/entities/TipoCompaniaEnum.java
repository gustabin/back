/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

import org.apache.commons.lang3.StringUtils;

/**
 * The Enum TipoCompaniaEnum.
 */
public enum TipoCompaniaEnum {

	/** The movistar. */
	MOVISTAR("MOVI", "Movistar"),

	/** The personal. */
	PERSONAL("PERS", "Personal"),

	/** The claro. */
	CLARO("CTI ", "Claro"),

	/** The nextel. */
	NEXTEL("NEXT", "Nextel"),

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
	 * @return the tipo compania enum
	 */
	public static TipoCompaniaEnum fromCodigo(String codigo) {
		TipoCompaniaEnum[] values = TipoCompaniaEnum.values();

		TipoCompaniaEnum response = NULL;

		for (TipoCompaniaEnum tipo : values) {
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
	public static TipoCompaniaEnum fromDescripcion(String descripcion) {
		if (StringUtils.containsIgnoreCase(descripcion, "tuenti")) {
			return TipoCompaniaEnum.MOVISTAR;
		}
		TipoCompaniaEnum[] values = TipoCompaniaEnum.values();

		TipoCompaniaEnum response = null;

		for (TipoCompaniaEnum tipo : values) {
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
	TipoCompaniaEnum(String codigo, String descripcion) {
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
