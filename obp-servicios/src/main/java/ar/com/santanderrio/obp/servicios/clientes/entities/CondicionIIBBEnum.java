/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Enum CondicionIIBBEnum.
 */
public enum CondicionIIBBEnum {

	/** The regimen simplificado. */
	REGIMEN_SIMPLIFICADO("RESI", "Régimen Simplificado"),

	/** The exento. */
	EXENTO("EXE", "Exento"),

	/** The contribuyente local. */
	CONTRIBUYENTE_LOCAL("CLOC ", "Contribuyente Local"),

	/** The convenio multilateral. */
	CONVENIO_MULTILATERAL("CONM", "Convenio Multilateral"),

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
	 * @return the condicion IIBB enum
	 */
	public static CondicionIIBBEnum fromCodigo(String codigo) {
		CondicionIIBBEnum[] values = CondicionIIBBEnum.values();

		CondicionIIBBEnum response = NULL;

		for (CondicionIIBBEnum tipo : values) {
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
	public static CondicionIIBBEnum fromDescripcion(String descripcion) {
		CondicionIIBBEnum[] values = CondicionIIBBEnum.values();

		CondicionIIBBEnum response = null;

		for (CondicionIIBBEnum tipo : values) {
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
	CondicionIIBBEnum(String codigo, String descripcion) {
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
