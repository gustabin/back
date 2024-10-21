/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class MetodoAutenticacionView.
 */
public class MetodoAutenticacionView {

	/** The mensaje. */
	private String mensaje;

	/** The accion. */
	private String accion;

	/** The legal. */
	private String legal;
	
	private String mensajePinBanelco;
	
	private AutentificacionDTO desafio;
	
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
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	 *            the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getMensajePinBanelco() {
		return mensajePinBanelco;
	}

	public void setMensajePinBanelco(String mensajePinBanelco) {
		this.mensajePinBanelco = mensajePinBanelco;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}
		
}
