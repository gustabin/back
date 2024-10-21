/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

/**
 * The Enum PlazoFijoEnum.
 */
public enum PlazoFijoEnum {

	/** Plazo fijo tradicional. */
	TRADICIONAL("3"),

	/** Plazo fijo precancelable. */
	PRECANCELABLE("16"),

	/** Plazo fijo interesante. */
	INTERESANTE_TASA_FIJA("17"),

	/** Plazo fijo DIVA. */
	DIVA("20"),

	/** Plazo fijo ajustable por CER. */
	AJUSTABLE_POR_CER("30"),

	/** Plazo fijo UVA. */
	UVA("60"),
	
	/** Plazo fijo UVA PRECANCELABLE. */
	UVA_PRECANCELABLE("61"),

	/** Plazo fijo dolar ahorro. */
	DOLAR_AHORRO("92"),
	
	TRADICIONAL_REPATRIACION("94");

	/** Codigo de plazo fijo. */
	private String codigo;

	/**
	 * Instantiates a new plazo fijo enum.
	 *
	 * @param codigoPlazoFijo
	 *            the codigo plazo fijo
	 */
	PlazoFijoEnum(String codigoPlazoFijo) {
		this.codigo = codigoPlazoFijo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

}
