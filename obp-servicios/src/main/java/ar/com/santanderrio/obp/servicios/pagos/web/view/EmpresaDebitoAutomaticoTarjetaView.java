/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.util.List;

/**
 * The Class EmpresaDebitoAutomaticoTarjetaView.
 */
public class EmpresaDebitoAutomaticoTarjetaView {

	/** The empresas. */
	private List<DebitoAutomaticoEnTarjetaView> empresas;

	/** The mensaje error. */
	private String mensajeError;

	/**
	 * Gets the empresas.
	 *
	 * @return the empresas
	 */
	public List<DebitoAutomaticoEnTarjetaView> getEmpresas() {
		return empresas;
	}

	/**
	 * Sets the empresas.
	 *
	 * @param empresas
	 *            the new empresas
	 */
	public void setEmpresas(List<DebitoAutomaticoEnTarjetaView> empresas) {
		this.empresas = empresas;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}
