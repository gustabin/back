/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.entity;

/**
 * The Enum que define el tipo de contrato.
 */
public enum TipoContratoEnum {

	/** The activo. */
	TRANSFERENCIA("Contrato transferencia");

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new operaciones RSA enum.
	 *
	 * @param descripcion
	 *            the descripcion
	 */
	TipoContratoEnum(String descripcion) {
		this.descripcion = descripcion;
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
	 * Gets the by descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the by descripcion
	 */
	public static TipoContratoEnum getByDescripcion(String descripcion) {
		for (TipoContratoEnum tipo : TipoContratoEnum.values()) {
			if (tipo.getDescripcion().equalsIgnoreCase(descripcion)) {
				return tipo;
			}
		}
		return null;
	}
}
