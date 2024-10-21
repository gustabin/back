/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.common;

/**
 * The Enum TiposDocumentoComexEnum.
 */
public enum TiposDocumentoComexEnum {

	/** The DNI. */
	DNI("N","DNI"),

	/** The LC. */
	LC("C","LC"),

	/** The LE. */
	LE("E","LE"),

	/** The CI. */
	I("I","CI"),

	/** The P. */
	P("P","PA"),//FIX PL 07-12-18

	/** The M. */
	M("M","CM"),

	/** The X. */
	X("X","DNE"),

	/** The T. */
	T("T","CUIT"),

	/** The L. */
	L("L","CUIL"),

	/** The D. */
	D("D","CDI"),

	/** The F. */
	F("F","CINT");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipos documento comex enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	TiposDocumentoComexEnum(String codigo, String descripcion) {
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

	/**
	 * Obtener descripcion por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 */
	public static String obtenerDescripcionPorCodigo(String codigo) {
		String retorno = null;
		for (TiposDocumentoComexEnum item : TiposDocumentoComexEnum.values()) {
			if (codigo.equals(item.getCodigo())) {
				retorno = item.getDescripcion();
				break;
			}
		}
		return retorno;
	}

}
