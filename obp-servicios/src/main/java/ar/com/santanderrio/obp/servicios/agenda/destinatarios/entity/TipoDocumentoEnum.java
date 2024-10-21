/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * The Enum TipoDocumentoEnum.
 *
 * @author florencia.n.martinez
 */
public enum TipoDocumentoEnum {

	/** The cuil. */
	CUIL("L", "CUIL"),
	/** The cuit. */
	CUIT("T", "CUIT"),
	/** The dni. */
	DNI("N", "DNI"),
	/** The libreta civica. */
	LIBRETA_CIVICA("C", "LC"),
	/** The libreta de enrolamiento. */
	LIBRETA_DE_ENROLAMIENTO("E", "LE"),
	/** The cedula de identidad. */
	CEDULA_DE_IDENTIDAD("I", "CI"),
	/** The cedula militar. */
	CEDULA_MILITAR("M", "CEDULA MILITAR"),
	/** The pasaporte. */
	PASAPORTE("P", "Pasaporte"),
	/** The cdi. */
	CDI("D", "CDI"),
	/** The dni extranjero. */
	DNI_EXTRANJERO("X", "DNI EXTRANJERO"),
	/** The certificado internacional. */
	CERTIFICADO_INTERNACIONAL("F", "CERTIFICADO INTERNACIONAL");

	/** The tipo documento. */
	String campo;

	/** Descripcion. */
	String descripcion;

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
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
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
	 * Retorna el TipoDocumentoEnum correspondiente al campo ingresado.
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