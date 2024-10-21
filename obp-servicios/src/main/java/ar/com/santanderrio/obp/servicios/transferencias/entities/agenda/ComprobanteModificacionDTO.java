/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

import ar.com.santanderrio.obp.servicios.comun.comprobante.entities.AbstractComprobante;

/**
 * Comprobante.
 */
public class ComprobanteModificacionDTO extends AbstractComprobante<String> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The mensaje. */
	private String mensaje;

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
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
