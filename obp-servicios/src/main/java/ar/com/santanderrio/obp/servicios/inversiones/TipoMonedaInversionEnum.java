/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones;

/**
 * The Enum TipoMonedaInversionEnum.
 * 
 * @author pablo.d.gargaglione
 *
 */
public enum TipoMonedaInversionEnum {

	/** The peso. */
	ARG("peso", "$", "ARG", "pesos", "00", "ARS", "P"),

	/** The dolar. */
	USD("dolar", "u$s", "USD", "dolares", "02", "USD", "D"),

	/** The ambas monedas. */
	AMBAS("ambas", "u$s,$", "USD,ARG", "ambas", "01", "USD,ARS", "A");

	/** The moneda. */
	private String moneda;

	/** The simbolo. */
	private String simbolo;

	/** The codigo. */
	private String codigo;

	/** The monedas. */
	private String monedas;

	/** The monedas. */
	private String codigoNumerico;

	/** The codigo 2. */
	private String codigo2;
	
	/** The letra inicial. */
	private String letraInicial;

	/**
	 * Instantiates a new Tipo Moneda Inversion Enum.
	 *
	 * @param moneda
	 *            the moneda
	 * @param simbolo
	 *            the simbolo
	 * @param codigo
	 *            the codigo
	 * @param monedas
	 *            the monedas
	 * @param codigoNumerico
	 *            the codigo numerico
	 * @param codigo2
	 *            the codigo 2
	 * @param letraInicial
	 *            the letra inicial
	 */
	TipoMonedaInversionEnum(String moneda, String simbolo, String codigo, String monedas, String codigoNumerico,
			String codigo2, String letraInicial) {
		this.moneda = moneda;
		this.simbolo = simbolo;
		this.codigo = codigo;
		this.monedas = monedas;
		this.codigoNumerico = codigoNumerico;
		this.codigo2 = codigo2;
		this.letraInicial = letraInicial;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Gets the simbolo.
	 *
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
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
	 * Gets the monedas.
	 *
	 * @return the monedas
	 */
	public String getMonedas() {
		return monedas;
	}

	/**
	 * Gets the codigo numerico.
	 *
	 * @return the codigo numerico
	 */
	public String getCodigoNumerico() {
		return codigoNumerico;
	}

	/**
	 * Gets the codigo 2.
	 *
	 * @return the codigo 2
	 */
	public String getCodigo2() {
		return codigo2;
	}
	
	/**
	 * Gets the letra inicial.
	 *
	 * @return the letra inicial
	 */
	public String getLetraInicial() {
		return letraInicial;
	}
	
	/**
	 * Sets the letra inicial.
	 *
	 * @param letraInicial
	 *            the new letra inicial
	 */
	public void setLetraInicial(String letraInicial) {
		this.letraInicial = letraInicial;
	}

	/**
	 * From moneda string.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the tipomonedaInversion enum
	 */
	public static TipoMonedaInversionEnum fromMonedaString(String moneda) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getMoneda().equals(moneda)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From monedas string.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the tipo moneda inversion enum
	 */
	public static TipoMonedaInversionEnum fromMonedasString(String moneda) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getMonedas().equalsIgnoreCase(moneda)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From codigo string.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipomonedaInversion enum
	 */
	public static TipoMonedaInversionEnum fromCodigoString(String codigo) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getCodigo().equals(codigo)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From simbolo string.
	 *
	 * @param codigoNumerico
	 *            the codigo numerico
	 * @return the tipomonedaInversion enum
	 */
	public static TipoMonedaInversionEnum fromCodigoNumericoString(String codigoNumerico) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getCodigoNumerico().equals(codigoNumerico)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From simbolo string.
	 *
	 * @param simbolo
	 *            the simbolo
	 * @return the tipomonedaInversion enum
	 */
	public static TipoMonedaInversionEnum fromSimboloString(String simbolo) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getSimbolo().equals(simbolo)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From codigo 2 string.
	 *
	 * @param codigo2
	 *            the codigo 2
	 * @return the tipo moneda inversion enum
	 */
	public static TipoMonedaInversionEnum fromCodigo2String(String codigo2) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getCodigo2().equals(codigo2)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}

	/**
	 * From letra inicial string.
	 *
	 * @param letraInicial
	 *            the letra inicial
	 * @return the tipo moneda inversion enum
	 */
	public static TipoMonedaInversionEnum fromLetraInicialString(String letraInicial) {
		TipoMonedaInversionEnum[] values = TipoMonedaInversionEnum.values();

		TipoMonedaInversionEnum response = null;

		for (TipoMonedaInversionEnum tipomonedaInversion : values) {
			if (tipomonedaInversion.getLetraInicial().equalsIgnoreCase(letraInicial)) {
				response = tipomonedaInversion;
			}
		}
		return response;
	}
	
}
