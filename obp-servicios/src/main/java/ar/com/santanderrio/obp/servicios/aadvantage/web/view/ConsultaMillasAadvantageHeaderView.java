/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.web.view;

import java.util.List;

/**
 * The Class ConsultaMillasAadvantageHeaderView.
 */
public class ConsultaMillasAadvantageHeaderView {

	/** The millas. */
	private String millas;
	
	/** The miembro. */
	private String miembro;
	
	/** The tooltip. */
	private String tooltip;
	
	/** The mensajes. */
	private List<MensajeView> mensajes;

	/**
	 * Gets the millas.
	 *
	 * @return the millas
	 */
	public String getMillas() {
		return millas;
	}

	/**
	 * Sets the millas.
	 *
	 * @param millas
	 *            the new millas
	 */
	public void setMillas(String millas) {
		this.millas = millas;
	}

	/**
	 * Gets the miembro.
	 *
	 * @return the miembro
	 */
	public String getMiembro() {
		return miembro;
	}

	/**
	 * Sets the miembro.
	 *
	 * @param miembro
	 *            the new miembro
	 */
	public void setMiembro(String miembro) {
		this.miembro = miembro;
	}

	/**
	 * Gets the tooltip.
	 *
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Sets the tooltip.
	 *
	 * @param tooltip
	 *            the new tooltip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Gets the mensajes.
	 *
	 * @return the mensajes
	 */
	public List<MensajeView> getMensajes() {
		return mensajes;
	}

	/**
	 * Sets the mensajes.
	 *
	 * @param mensajes
	 *            the new mensajes
	 */
	public void setMensajes(List<MensajeView> mensajes) {
		this.mensajes = mensajes;
	}
	
}
