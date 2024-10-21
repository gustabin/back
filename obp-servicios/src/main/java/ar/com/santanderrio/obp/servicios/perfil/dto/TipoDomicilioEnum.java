/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * TipoDomicilioEnum.
 */
public enum TipoDomicilioEnum {

	/** PRINCIPAL. */
	TIPO_DOMICILIO_PRINCIPAL("PRI", "Domicilio Principal"),

	/** LABORAL. */
	TIPO_DOMICLIO_LABORAL("LAB", "Domicilio Laboral"),

	/** PRE. */
	TIPO_DOMICLIO_PRE("PRE", "Domicilio Préstamo Prendario"),

	/** ELE. */
	TIPO_SIN_DOMICLIO("ELE", "Sin Domicilio"),
	
	/** TIPO_ALT. */
	TIPO_ALTERNATIVO("ALT","Domicilio Secundario"),
	
	/** BPR. */
	TIPO_BANCA_PRIVADA("BPR","Domicilio Banca Privada"),

	/** VACIO. */
	TIPO_DOMICILIO_VACIO("EMPTY", "");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new tipo domicilio enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoDomicilioEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

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
	 * Gets the tipo domicilio.
	 *
	 * @param campo
	 *            the campo
	 * @return the tipo domicilio
	 */
	public static TipoDomicilioEnum getTipoDomicilio(String campo) {
		TipoDomicilioEnum[] values = TipoDomicilioEnum.values();
		for (TipoDomicilioEnum tipoDomicilio : values) {
			if (StringUtils.equalsIgnoreCase(tipoDomicilio.getCampo(), StringUtils.trim(campo))) {
				return tipoDomicilio;
			}
		}
		return TIPO_DOMICILIO_VACIO;
	}

}
