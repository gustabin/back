/*
 * 
 */
package ar.com.santanderrio.obp.servicios.general.entities;

/**
 * The Enum RangoFechaEnum.
 */
public enum RangoFechaEnum {

	/** The default. */
	DEFAULT("DEFAULT"),

	/** The AYER. */
	AYER("AYER"),

	/** The tres dias. */
	TRES_DIAS("TRES_DIAS"),

	/** The siete dias. */
	SIETE_DIAS("SIETE_DIAS"),

	/** The quince dias. */
	QUINCE_DIAS("QUINCE_DIAS"),

	/** The treinta dias. */
	TREINTA_DIAS("TREINTA_DIAS"),

	/** The sesenta dias. */
	SESENTA_DIAS("SESENTA_DIAS"),

	/** The personalizado. */
	PERSONALIZADO("CUSTOM"),
	
	/** The 53 dias Ver mas. */
	CINCUENTA_Y_TRES_DIAS("CINCUENTA_Y_TRES_DIAS");
	
	

	/** The rango. */
	private String rango;

	/**
	 * Gets the rango.
	 *
	 * @return the rango
	 */
	public String getRango() {
		return rango;
	}

	/**
	 * Instantiates a new rango fecha enum.
	 *
	 * @param rango
	 *            the rango
	 */

	RangoFechaEnum(String rango) {
		this.rango = rango;
	}

	/**
	 * From rango string.
	 *
	 * @param rango
	 *            the rango
	 * @return the rango fecha enum
	 */
	public static RangoFechaEnum fromRangoString(String rango) {
		RangoFechaEnum[] values = RangoFechaEnum.values();

		RangoFechaEnum response = null;

		for (RangoFechaEnum rangoFechaEnum : values) {
			if (rangoFechaEnum.getRango().equals(rango)) {
				response = rangoFechaEnum;
			}
		}
		return response;
	}

}
