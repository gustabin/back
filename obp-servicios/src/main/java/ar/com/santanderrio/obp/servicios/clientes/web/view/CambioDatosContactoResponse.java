/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ProvinciaView;

/**
 * The Class MarcaAPNHResponse.
 */
public class CambioDatosContactoResponse {

	/** The celular. */
	private CelularResponse[] celular;

	/** The email. */
	private EmailResponse[] email;

	/** The mensaje feedback. */
	private String mensajeFeedback;

	/** The estado cliente. */
	private String estadoCliente;

	/** The provincias. */
	private List<ProvinciaView> provincias;

	/** THe List CambioDomicilioView. */
	private List<CambioDomicilioView> listaDomicilios;

	/** The companias. */
	private List<String> companias;

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public CelularResponse[] getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the new celular
	 */
	public void setCelular(CelularResponse[] celular) {
		this.celular = celular;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public EmailResponse[] getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(EmailResponse[] email) {
		this.email = email;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the lista domicilios.
	 *
	 * @return the lista domicilios
	 */
	public List<CambioDomicilioView> getListaDomicilios() {
		return listaDomicilios;
	}

	/**
	 * Sets the lista domicilios.
	 *
	 * @param listaDomicilios
	 *            the new lista domicilios
	 */
	public void setListaDomicilios(List<CambioDomicilioView> listaDomicilios) {
		this.listaDomicilios = listaDomicilios;
	}

	/**
	 * Gets the companias.
	 *
	 * @return the companias
	 */
	public List<String> getCompanias() {
		return companias;
	}

	/**
	 * Sets the companias.
	 *
	 * @param companias
	 *            the new companias
	 */
	public void setCompanias(List<String> companias) {
		this.companias = companias;
	}

	/**
	 * Gets the estado cliente.
	 *
	 * @return the estado cliente
	 */
	public String getEstadoCliente() {
		return estadoCliente;
	}

	/**
	 * Sets the estado cliente.
	 *
	 * @param estadoCliente
	 *            the new estado cliente
	 */
	public void setEstadoCliente(String estadoCliente) {
		this.estadoCliente = estadoCliente;
	}

	/**
	 * Gets the provincias.
	 *
	 * @return the provincias
	 */
	public List<ProvinciaView> getProvincias() {
		return provincias;
	}

	/**
	 * Sets the provincias.
	 *
	 * @param provincias
	 *            the new provincias
	 */
	public void setProvincias(List<ProvinciaView> provincias) {
		this.provincias = provincias;
	}

}
