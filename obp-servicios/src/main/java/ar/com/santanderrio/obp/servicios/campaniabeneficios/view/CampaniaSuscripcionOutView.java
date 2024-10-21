package ar.com.santanderrio.obp.servicios.campaniabeneficios.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

// TODO: Auto-generated Javadoc
/**
 * The Class CampaniaSuscripcionView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)

public class CampaniaSuscripcionOutView {

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The url qr. */
	private String urlQr;

	/** The url todo pago. */
	private String urlTodoPago;

	/**
	 * Instantiates a new campania suscripcion view.
	 */
	public CampaniaSuscripcionOutView() {
		super();
	}

	/**
	 * Instantiates a new campania suscripcion view.
	 *
	 * @param mensajeFeedback the mensaje feedback
	 * @param urlQr           the url qr
	 * @param urlTodoPago     the url todo pago
	 */
	public CampaniaSuscripcionOutView(String mensajeFeedback, String urlQr, String urlTodoPago) {
		super();
		this.mensajeFeedback = mensajeFeedback;
		this.urlQr = urlQr;
		this.urlTodoPago = urlTodoPago;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
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

}
