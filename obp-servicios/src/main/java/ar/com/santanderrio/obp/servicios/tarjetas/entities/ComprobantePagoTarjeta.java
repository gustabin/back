/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

/**
 * The Class ComprobantePagoTarjeta.
 */
public class ComprobantePagoTarjeta {

	/** The mensaje. */
	private String mensaje;

	/** The nro comprobante. */
	private String nroComprobante;

	/** The nro comprobante stop debit. */
	private String nroComprobanteStopDebit;

	/** The fecha hora. */
	private String fechaHora;

	/** The legales SEO. */
	private String legalesSEO;

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

	/**
	 * Gets the legales SEO.
	 *
	 * @return the legales SEO
	 */
	public String getLegalesSEO() {
		return legalesSEO;
	}

	/**
	 * Sets the legales SEO.
	 *
	 * @param legalesSEO
	 *            the new legales SEO
	 */
	public void setLegalesSEO(String legalesSEO) {
		this.legalesSEO = legalesSEO;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the nro comprobante stop debit.
	 *
	 * @return the nro comprobante stop debit
	 */
	public String getNroComprobanteStopDebit() {
		return nroComprobanteStopDebit;
	}

	/**
	 * Sets the nro comprobante stop debit.
	 *
	 * @param nroComprobanteStopDebit
	 *            the new nro comprobante stop debit
	 */
	public void setNroComprobanteStopDebit(String nroComprobanteStopDebit) {
		this.nroComprobanteStopDebit = nroComprobanteStopDebit;
	}
}
