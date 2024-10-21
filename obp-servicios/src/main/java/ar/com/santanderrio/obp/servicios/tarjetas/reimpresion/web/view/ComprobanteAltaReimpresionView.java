/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view;

import java.util.List;

/**
 * The Class ComprobanteAltaReimpresionView.
 */
public class ComprobanteAltaReimpresionView {

	/** The domicilios. */
	private List<DomicilioView> domicilios;

	/** The tarjetas solicitadas. */
	private List<TarjetaSolicitadaView> tarjetasSolicitadas;

	/** The fecha hora. */
	private String fechaHora;

	/** The legal. */
	private String legal;

	/**
	 * Gets the tarjetas solicitadas.
	 *
	 * @return the tarjetasAdicionalesSolicitadas
	 */
	public List<TarjetaSolicitadaView> getTarjetasSolicitadas() {
		return tarjetasSolicitadas;
	}

	/**
	 * Sets the tarjetas solicitadas.
	 *
	 * @param tarjetasSolicitadas
	 *            the new tarjetas solicitadas
	 */
	public void setTarjetasSolicitadas(List<TarjetaSolicitadaView> tarjetasSolicitadas) {
		this.tarjetasSolicitadas = tarjetasSolicitadas;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fechaHora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the fechaHora to set
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the domicilios.
	 *
	 * @return the domicilios
	 */
	public List<DomicilioView> getDomicilios() {
		return domicilios;
	}

	/**
	 * Sets the domicilios.
	 *
	 * @param domicilios
	 *            the domicilios to set
	 */
	public void setDomicilios(List<DomicilioView> domicilios) {
		this.domicilios = domicilios;
	}

}
