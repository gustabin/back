/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun;

/**
 * The Enum CodigoSistemaLoadOrdenesEnum.
 */
public enum CodigoSistemaLoadOrdenesEnum {

	/** The peso. */
	PLAZO_FIJO("PF", 30),

	/** The dolar. */
	FONDO("FC", 59);

	/** The moneda. */
	private String codigoSistema;

	/** The simbolo. */
	private int diasAtrasPorDefecto;
	
	/**
	 * Instantiates a new codigo sistema load ordenes enum.
	 *
	 * @param codigoSistema
	 *            the codigo sistema
	 * @param diasAtrasPorDefecto
	 *            the dias atras por defecto
	 */
	CodigoSistemaLoadOrdenesEnum(String codigoSistema, int diasAtrasPorDefecto) {
		this.codigoSistema = codigoSistema;
		this.diasAtrasPorDefecto = diasAtrasPorDefecto;
	}
	
	/**
	 * From codigo sistema.
	 *
	 * @param codigoSistema
	 *            the codigo sistema
	 * @return the codigo sistema load ordenes enum
	 */
	public static CodigoSistemaLoadOrdenesEnum fromCodigoSistema(String codigoSistema) {
		CodigoSistemaLoadOrdenesEnum[] values = CodigoSistemaLoadOrdenesEnum.values();

		CodigoSistemaLoadOrdenesEnum response = null;

		for (CodigoSistemaLoadOrdenesEnum codigoSis : values) {
			if (codigoSis.getCodigoSistema().equals(codigoSistema)) {
				response = codigoSis;
			}
		}
		return response;
	}

	/**
	 * Gets the codigo sistema.
	 *
	 * @return the codigo sistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * Sets the codigo sistema.
	 *
	 * @param codigoSistema
	 *            the new codigo sistema
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * Gets the dias atras por defecto.
	 *
	 * @return the dias atras por defecto
	 */
	public int getDiasAtrasPorDefecto() {
		return diasAtrasPorDefecto;
	}

	/**
	 * Sets the dias atras por defecto.
	 *
	 * @param diasAtrasPorDefecto
	 *            the new dias atras por defecto
	 */
	public void setDiasAtrasPorDefecto(int diasAtrasPorDefecto) {
		this.diasAtrasPorDefecto = diasAtrasPorDefecto;
	}
	
}
