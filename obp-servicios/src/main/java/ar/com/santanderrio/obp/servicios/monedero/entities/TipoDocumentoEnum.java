/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Enum TipoDocumentoEnum.
 */
public enum TipoDocumentoEnum {

	/** The dni. */
	DNI("DNI", "N "),

	/** The lc. */
	LC("LC", "C "),

	/** The le. */
	LE("LE", "E "),

	/** The cuit. */
	CUIT("CUIT", "T "),

	/** The ext. */
	EXT("EXT", "X "),

	/** The pas. */
	PAS("PAS", "P "),

	/** The ci. */
	CI("CI", "I ");

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
	 * Instantiates a new tipo documento enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoDocumentoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Obtener tipo documento.
	 *
	 * @param campo
	 *            the campo
	 * @return the tipo documento enum
	 */
	public static TipoDocumentoEnum obtenerTipoDocumento(String campo) {
		TipoDocumentoEnum[] values = TipoDocumentoEnum.values();
		for (TipoDocumentoEnum tipoDocumento : values) {
			if (campo.trim().equalsIgnoreCase(tipoDocumento.getCampo())) {
				return tipoDocumento;
			}
		}
		return null;
	}
}
