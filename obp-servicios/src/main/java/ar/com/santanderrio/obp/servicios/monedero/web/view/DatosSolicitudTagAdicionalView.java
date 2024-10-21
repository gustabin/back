/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

/**
 * The Class DatosSolicitudTagAdicionalView.
 */
public class DatosSolicitudTagAdicionalView {

	/** The tipos documento view. */
	private List<String> tipoDocumentoView;

	/** The mensaje. */
	private String mensaje = "";

	/**
	 * Gets the tipos documento view.
	 *
	 * @return the tipos documento view
	 */
	public List<String> getTipoDocumentoView() {
		return tipoDocumentoView;
	}

	/**
	 * Sets the tipos documento view.
	 *
	 * @param tipoDocumentoView
	 *            the new tipos documento view
	 */
	public void setTipoDocumentoView(List<String> tipoDocumentoView) {
		this.tipoDocumentoView = tipoDocumentoView;
	}

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

}
