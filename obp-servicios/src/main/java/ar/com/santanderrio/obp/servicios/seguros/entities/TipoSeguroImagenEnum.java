/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguros.entities;

/**
 * Enum TipoSeguroImagenEnum.
 */
public enum TipoSeguroImagenEnum {

	/** The accidentes personales. */
	ACCIDENTES_PERSONALES(1, "ACCIDENTES PERSONALES", "1_accidentes_personales_imagen"),

	/** The vida. */
	VIDA(18, "VIDA", "18_vida_imagen"),

	/** The robo cajeros. */
	ROBO_CAJEROS(19, "ROBO CAJEROS", "19_cajeros_en_cajeros_imagen"),

	/** The desempleo. */
	DESEMPLEO(20, "DESEMPLEO", "20_desempleo_imagen"),

	/** The vivienda. */
	VIVIENDA(21, "VIVIENDA", "21_vivienda_imagen"),

	/** The proteccion salud. */
	PROTECCION_SALUD(24, "PROTECCION SALUD", "24_proteccion_salud_imagen"),

	/** The proteccion cartera 25. */
	PROTECCION_CARTERA_25(25, "PROTECCION CARTERA", "25_proteccion_cartera_imagen"),

	/** The compra protegida 26. */
	COMPRA_PROTEGIDA_26(26, "COMPRA PROTEGIDA", "26_compra_protegida_imagen"),

	/** The auto 30. */
	AUTO_30(30, "AUTO", "30_auto_imagen"),

	/** The auto 32. */
	AUTO_32(32, "AUTO", "32_auto_imagen"),

	/** The auto 33. */
	AUTO_33(33, "AUTO", "33_auto_imagen"),

	/** The auto 35. */
	AUTO_35(35, "AUTO", "35_auto_imagen"),

	/** The auto 36. */
	AUTO_36(36, "AUTO", "36_auto_imagen"),

	/** The auto 39. */
	AUTO_39(39, "AUTO", "39_auto_imagen"),

	/** The proteccion cartera 52. */
	PROTECCION_CARTERA_52(52, "PROTECCION CARTERA", "52_proteccion_cartera_imagen"),

	/** The compra protegida 53. */
	COMPRA_PROTEGIDA_53(53, "COMPRA PROTEGIDA", "53_compra_protegida_imagen"),

	/** The proteccion de pagos. */
	PROTECCION_DE_PAGOS(58, "PROTECCION DE PAGOS", "58_proteccion_de_pagos_imagen"),

	/** The proteccion. */
	PROTECCION(58, "PROTECCION", "58_proteccion_imagen"),

	/** The seguros home imagen. */
	SEGUROS_HOME_IMAGEN(-1, "SEGUROS HOME IMAGEN", "seguros_home_imagen");

	/** The codigo ramo. */
	private Integer codigoRamo;

	/** The descripcion. */
	private String descripcion;

	/** The nombre imagen. */
	private String nombreImagen;

	/**
	 * Instantiates a new tipo seguro imagen enum.
	 *
	 * @param codigoRamo
	 *            the codigo ramo
	 * @param descripcion
	 *            the descripcion
	 * @param nombreImagen
	 *            the nombre imagen
	 */
	TipoSeguroImagenEnum(Integer codigoRamo, String descripcion, String nombreImagen) {
		this.codigoRamo = codigoRamo;
		this.descripcion = descripcion;
		this.nombreImagen = nombreImagen;
	}

	/**
	 * Gets the tipo imagen from codigo.
	 *
	 * @param codigoStr
	 *            the codigo str
	 * @return the tipo imagen from codigo
	 */
	public static TipoSeguroImagenEnum getTipoImagenFromCodigo(String codigoStr) {
		Integer codigo = Integer.parseInt(codigoStr);
		TipoSeguroImagenEnum[] values = TipoSeguroImagenEnum.values();
		TipoSeguroImagenEnum response = null;
		for (TipoSeguroImagenEnum tipoSeguroImagenEnum : values) {
			if (tipoSeguroImagenEnum.getCodigoRamo().equals(codigo)) {
				response = tipoSeguroImagenEnum;
			}
		}

		return response;
	}

	/**
	 * Gets the codigo ramo.
	 *
	 * @return the codigo ramo
	 */
	public Integer getCodigoRamo() {
		return codigoRamo;
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
	 * Gets the nombre imagen.
	 *
	 * @return the nombre imagen
	 */
	public String getNombreImagen() {
		return nombreImagen;
	}
}
