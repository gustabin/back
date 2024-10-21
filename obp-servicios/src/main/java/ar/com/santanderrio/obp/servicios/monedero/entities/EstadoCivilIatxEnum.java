/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Enum EstadoCivilIatxEnum.
 */
public enum EstadoCivilIatxEnum {

	/** The s. */
	S("S", "Soltero", "Soltera"),

	/** The c. */
	C("C", "Casado", "Casada"),

	/** The d. */
	D("D", "Divorciado", "Divorciada"),

	/** The v. */
	V("V", "Viudo", "Viuda"),

	/** The o. */
	O("O", "Otros", "Otras");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcionM;
	
	/** The descripcion. */
	String descripcionF;

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	

	public String getDescripcionM() {
		return descripcionM;
	}



	public void setDescripcionM(String descripcionM) {
		this.descripcionM = descripcionM;
	}



	public String getDescripcionF() {
		return descripcionF;
	}



	public void setDescripcionF(String descripcionF) {
		this.descripcionF = descripcionF;
	}



	/**
	 * Instantiates a new estado civil iatx enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	EstadoCivilIatxEnum(String campo, String descripcionM, String descripcionF) {
		this.campo = campo;
		this.descripcionM = descripcionM;
		this.descripcionF = descripcionF;
	}

	/**
	 * Obtener estado civil iatx descripcion.
	 *
	 * @param campo
	 *            the campo
	 * @return the estado civil iatx enum
	 */
	public static EstadoCivilIatxEnum obtenerEstadoCivilIatxDescripcion(String campo) {
		EstadoCivilIatxEnum[] values = EstadoCivilIatxEnum.values();
		for (EstadoCivilIatxEnum tipoEstadoCivil : values) {
			if (campo.trim().equalsIgnoreCase(tipoEstadoCivil.getCampo())) {
				return tipoEstadoCivil;
			}
		}
		return null;
	}
}
