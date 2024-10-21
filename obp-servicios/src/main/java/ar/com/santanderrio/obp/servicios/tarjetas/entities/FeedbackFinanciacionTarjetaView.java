/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.base.web.view.View;

/**
 * View del feedback de la confirmacion de la financiacion de tarjeta.
 * 
 * @author Manuel.Vargas B041299
 *
 */
public class FeedbackFinanciacionTarjetaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new feedback financiacion tarjeta view.
	 */
	public FeedbackFinanciacionTarjetaView() {
		super();
	}

	/** The comprobante. */
	private String comprobante;

	/** The tarjeta santander rio visa. */
	private String tarjetaSantanderRioVisa;

	/** The moneda. */
	private String moneda;

	/** The monto A financiar. */
	private String montoAFinanciar;

	/** The monto cuota. */
	private String montoCuota;

	/** The cantidad cuotas. */
	private String cantidadCuotas;

	/** The numero de comprobante. */
	private String numeroDeComprobante;

	/** The leyenda. */
	private String leyenda;

	/** The Mensaje feedback. */
	private String mensajeFeedback;

	/** el TNA. */
	private String TNA;

	/** el CFT. */
	private String CFT;

	/** The fecha hora. */
	private String fechaHora;

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the tarjeta santander rio visa.
	 *
	 * @return the tarjetaSantanderRioVisa
	 */
	public String getTarjetaSantanderRioVisa() {
		return tarjetaSantanderRioVisa;
	}

	/**
	 * Sets the tarjeta santander rio visa.
	 *
	 * @param tarjetaSantanderRioVisa
	 *            the tarjetaSantanderRioVisa to set
	 */
	public void setTarjetaSantanderRioVisa(String tarjetaSantanderRioVisa) {
		this.tarjetaSantanderRioVisa = tarjetaSantanderRioVisa;
	}

	/**
	 * Gets the monto A financiar.
	 *
	 * @return the montoAFinanciar
	 */
	public String getMontoAFinanciar() {
		return montoAFinanciar;
	}

	/**
	 * Sets the monto A financiar.
	 *
	 * @param montoAFinanciar
	 *            the montoAFinanciar to set
	 */
	public void setMontoAFinanciar(String montoAFinanciar) {
		this.montoAFinanciar = montoAFinanciar;
	}

	/**
	 * Gets the monto cuota.
	 *
	 * @return the montoCuota
	 */
	public String getMontoCuota() {
		return montoCuota;
	}

	/**
	 * Sets the monto cuota.
	 *
	 * @param montoCuota
	 *            the montoCuota to set
	 */
	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	/**
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidadCuotas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the cantidadCuotas to set
	 */
	public void setCantidadCuotas(String cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the numero de comprobante.
	 *
	 * @return the numeroDeComprobante
	 */
	public String getNumeroDeComprobante() {
		return numeroDeComprobante;
	}

	/**
	 * Sets the numero de comprobante.
	 *
	 * @param numeroDeComprobante
	 *            the numeroDeComprobante to set
	 */
	public void setNumeroDeComprobante(String numeroDeComprobante) {
		this.numeroDeComprobante = numeroDeComprobante;
	}

	/**
	 * Gets the leyenda.
	 *
	 * @return the leyenda
	 */
	public String getLeyenda() {
		return leyenda;
	}

	/**
	 * Sets the leyenda.
	 *
	 * @param leyenda
	 *            the leyenda to set
	 */
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 * Gets the mensaje feedback.
	 *
	 * @return the mensajeFeedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the mensajeFeedback to set
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tNA
	 */
	public String getTNA() {
		return TNA;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tNA
	 *            the tNA to set
	 */
	public void setTNA(String tNA) {
		TNA = tNA;
	}

	/**
	 * Gets the cft.
	 *
	 * @return the cFT
	 */
	public String getCFT() {
		return CFT;
	}

	/**
	 * Sets the cft.
	 *
	 * @param cFT
	 *            the cFT to set
	 */
	public void setCFT(String cFT) {
		CFT = cFT;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}
