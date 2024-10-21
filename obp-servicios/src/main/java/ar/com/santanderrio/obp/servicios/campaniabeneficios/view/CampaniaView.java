package ar.com.santanderrio.obp.servicios.campaniabeneficios.view;

/**
 * The Class Campania.
 */
public class CampaniaView {

	/** The key. */
	private String key;

	/** The cod descripcion campania. */
	private String codDescripcionCampania;

	/** The cod beneficios campania. */
	private String codBeneficiosCampania;
	
	/** The cod requisitos campania. */
	private String codRequisitosCampania;

	/** The cod legales campania. */
	private String codLegalesCampania;

	/** The url todo pago. */
	private String urlTodoPago;

	/** The url qr. */
	private String urlQr;

	/**
	 * Instantiates a new campania.
	 */
	public CampaniaView() {
		super();
	}

	/**
	 * Instantiates a new campania.
	 *
	 * @param key                    the key
	 * @param codDescripcionCampania the cod descripcion campania
	 * @param codBeneficiosCampania  the cod beneficios campania
	 * @param codRequisitosCampania  the cod requisitos campania
	 * @param codLegalesCampania    the cod legales campania
	 * @param urlTodoPago            the url todo pago
	 * @param urlQr                  the url qr
	 */
	public CampaniaView(String key, String codDescripcionCampania, String codBeneficiosCampania, String codRequisitosCampania, String codLegalesCampania,
			String urlTodoPago, String urlQr) {
		super();
		this.key = key;
		this.codDescripcionCampania = codDescripcionCampania;
		this.codBeneficiosCampania = codBeneficiosCampania;
		this.codRequisitosCampania = codRequisitosCampania;
		this.codLegalesCampania = codLegalesCampania;
		this.urlTodoPago = urlTodoPago;
		this.urlQr = urlQr;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the cod descripcion campania.
	 *
	 * @return the cod descripcion campania
	 */
	public String getCodDescripcionCampania() {
		return codDescripcionCampania;
	}

	/**
	 * Sets the cod descripcion campania.
	 *
	 * @param codDescripcionCampania the new cod descripcion campania
	 */
	public void setCodDescripcionCampania(String codDescripcionCampania) {
		this.codDescripcionCampania = codDescripcionCampania;
	}

	/**
	 * Gets the cod beneficios campania.
	 *
	 * @return the cod beneficios campania
	 */
	public String getCodBeneficiosCampania() {
		return codBeneficiosCampania;
	}

	/**
	 * Sets the cod beneficios campania.
	 *
	 * @param codBeneficiosCampania the new cod beneficios campania
	 */
	public void setCodBeneficiosCampania(String codBeneficiosCampania) {
		this.codBeneficiosCampania = codBeneficiosCampania;
	}
	
	/**
	 * Gets the cod requisitos campania.
	 *
	 * @return the cod requisitos campania
	 */
	public String getCodRequisitosCampania() {
		return codRequisitosCampania;
	}

	/**
	 * Sets the cod requisitos campania.
	 *
	 * @param codRequisitosCampania the new cod requisitos campania
	 */
	public void setCodRequisitosCampania(String codRequisitosCampania) {
		this.codRequisitosCampania = codRequisitosCampania;
	}

	/**
	 * Gets the cod legalesn campania.
	 *
	 * @return the cod legalesn campania
	 */
	public String getCodLegalesCampania() {
		return codLegalesCampania;
	}

	/**
	 * Sets the cod legalesn campania.
	 *
	 * @param codLegalesCampania the new cod legales campania
	 */
	public void setCodLegalesCampania(String codLegalesCampania) {
		this.codLegalesCampania = codLegalesCampania;
	}

	/**
	 * Gets the url todo pago.
	 *
	 * @return the url todo pago
	 */
	public String getUrlTodoPago() {
		return urlTodoPago;
	}

	/**
	 * Sets the url todo pago.
	 *
	 * @param urlTodoPago the new url todo pago
	 */
	public void setUrlTodoPago(String urlTodoPago) {
		this.urlTodoPago = urlTodoPago;
	}

	/**
	 * Gets the url qr.
	 *
	 * @return the url qr
	 */
	public String getUrlQr() {
		return urlQr;
	}

	/**
	 * Sets the url qr.
	 *
	 * @param urlQr the new url qr
	 */
	public void setUrlQr(String urlQr) {
		this.urlQr = urlQr;
	}

}
