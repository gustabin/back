package ar.com.santanderrio.obp.base.reporte.entities;

// TODO: Auto-generated Javadoc
/**
 * The Enum TipoArchivoEnum.<br>
 * El mime type para excel deberia ser application/vnd.ms-excel pero apple no lo
 * interpreta por eso se toma la solucion del siguiente site: <br>
 * http://davidsson.co/errormessagemain-ios-download/
 * 
 */
public enum TipoArchivoEnum {

	/** The pdf. */
	PDF("application/pdf"),
	/** The zip. */
	ZIP("application/zip"),
	/** The excel. */
	EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

	/** The excel for Mac. */
	EXCELMAC("application/vnd.ms-excel");

	/** The mime tipe. */
	private String mimeTipe;

	/**
	 * Instantiates a new tipo archivo enum.
	 *
	 * @param mimeTipe
	 *            the mime tipe
	 */
	TipoArchivoEnum(String mimeTipe) {
		this.mimeTipe = mimeTipe;
	}

	/**
	 * Gets the mime tipe.
	 *
	 * @return the mime tipe
	 */
	public String getMimeTipe() {
		return mimeTipe;
	}

}
