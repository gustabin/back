/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Enum TipoDocumentoDescripcionEnum.
 */
public enum TipoDocumentoDescripcionEnum {

	/** The n. */
	N("N", "DNI"),

	/** The t. */
	T("T", "CUIT"),

	/** The x. */
	X("X", "EXT"),

	/** The p. */
	P("P", "PAS"),

	/** The i. */
	I("I", "CI"),

	/** The c. */
	C("C", "LC"),

	/** The e. */
	E("E", "LE");

	/** The tipo documento. */
	String campo;

	/** Descripcion. */
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
	 * Instantiates a new tipo documento descripcion enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoDocumentoDescripcionEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Obtener tipo documento descripcion.
	 *
	 * @param campo
	 *            the campo
	 * @return the tipo documento descripcion enum
	 */
	public static TipoDocumentoDescripcionEnum obtenerTipoDocumentoDescripcion(String campo) {
		TipoDocumentoDescripcionEnum[] values = TipoDocumentoDescripcionEnum.values();
		for (TipoDocumentoDescripcionEnum tipoDocumento : values) {
			if (campo.trim().equalsIgnoreCase(tipoDocumento.getCampo())) {
				return tipoDocumento;
			}
		}
		return null;
	}
}
