/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.view;

/**
 * The Class ComprobanteTransferenciaAgendadaView.
 */
public class ComprobanteTransferenciaAgendadaView extends TransferenciaAgendadaDetalleView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The fecha hora comprobante. */
	private String fechaHoraComprobante;

	/** The texto legales. */
	private String textoLegales;

	/** The numero comprobante. */
	private String numeroComprobante;

	/**
	 * Gets the fecha hora comprobante.
	 *
	 * @return the fechaHoraComprobante
	 */
	public String getFechaHoraComprobante() {
		return fechaHoraComprobante;
	}

	/**
	 * Sets the fecha hora comprobante.
	 *
	 * @param fechaHoraComprobante
	 *            the fechaHoraComprobante to set
	 */
	public void setFechaHoraComprobante(String fechaHoraComprobante) {
		this.fechaHoraComprobante = fechaHoraComprobante;
	}

	/**
	 * Gets the texto legales.
	 *
	 * @return the textoLegales
	 */
	public String getTextoLegales() {
		return textoLegales;
	}

	/**
	 * Sets the texto legales.
	 *
	 * @param textoLegales
	 *            the textoLegales to set
	 */
	public void setTextoLegales(String textoLegales) {
		this.textoLegales = textoLegales;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

}
