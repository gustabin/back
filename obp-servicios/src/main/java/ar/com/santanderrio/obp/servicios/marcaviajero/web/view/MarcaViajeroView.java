/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.view;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MarcaViajeroView.
 */
public class MarcaViajeroView {

	/** The mensaje ayuda. */
	private String mensajeAyuda;

	/** The mensaje black. */
	private String mensajeBlack;

	/** The viajes habilitados. */
	private List<ViajeHabilitadoView> viajesHabilitados;

	/**
	 * Gets the mensaje ayuda.
	 *
	 * @return the mensaje ayuda
	 */
	public String getMensajeAyuda() {
		return mensajeAyuda; 
	}

	/**
	 * Sets the mensaje ayuda.
	 *
	 * @param mensajeAyuda
	 *            the new mensaje ayuda
	 */
	public void setMensajeAyuda(String mensajeAyuda) {
		this.mensajeAyuda = mensajeAyuda;
	}

	/**
	 * Gets the mensaje black.
	 *
	 * @return the mensaje black
	 */
	public String getMensajeBlack() {
		return mensajeBlack;
	}

	/**
	 * Sets the mensaje black.
	 *
	 * @param mensajeBlack
	 *            the new mensaje black
	 */
	public void setMensajeBlack(String mensajeBlack) {
		this.mensajeBlack = mensajeBlack;
	}

	/**
	 * Gets the viajes habilitados.
	 *
	 * @return the viajes habilitados
	 */
	public List<ViajeHabilitadoView> getViajesHabilitados() {
		return viajesHabilitados;
	}

	/**
	 * Sets the viajes habilitados.
	 *
	 * @param viajesHabilitados
	 *            the new viajes habilitados
	 */
	public void setViajesHabilitados(List<ViajeHabilitadoView> viajesHabilitados) {
		this.viajesHabilitados = viajesHabilitados; 
	}

}
