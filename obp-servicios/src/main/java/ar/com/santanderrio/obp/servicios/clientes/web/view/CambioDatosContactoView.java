package ar.com.santanderrio.obp.servicios.clientes.web.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ProvinciaView;

/**
 * The Class CambioDatosContactoView.
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "cambioDatosContactoView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CambioDatosContactoView extends RsaDTOParaDesafio {

	/** The email primario. */
	private String emailPrimario;

	/** The email secundario. */
	private String emailSecundario;

	/** The codigo area. */
	private String codigoArea;

	/** The posicion celular. */
	private String posicionCelular;

	/** The celular. */
	private String celularIn;

	/** The compania celular. */
	private String companiaCelular;

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

	/** The lista domicilios. */
	private List<CambioDomicilioView> listaDomicilios;

	/** The companias. */
	private List<String> companias;
	
	private String emailPrimarioPrevio;
	
	private String emailSecundarioPrevio;
	
	private String celularPrevio;

	/**
	 * Instantiates a new cambio datos contacto view.
	 */
	public CambioDatosContactoView() {
		super(null);
	}

	/**
	 * Gets the email primario.
	 *
	 * @return the email primario
	 */
	public String getEmailPrimario() {
		return emailPrimario;
	}

	/**
	 * Sets the email primario.
	 *
	 * @param emailPrimario the new email primario
	 */
	public void setEmailPrimario(String emailPrimario) {
		this.emailPrimario = emailPrimario;
	}

	/**
	 * Gets the email secundario.
	 *
	 * @return the email secundario
	 */
	public String getEmailSecundario() {
		return emailSecundario;
	}

	/**
	 * Sets the email secundario.
	 *
	 * @param emailSecundario the new email secundario
	 */
	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigo area
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Sets the codigo area.
	 *
	 * @param codigoArea the new codigo area
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Gets the celular in.
	 *
	 * @return the celular in
	 */
	public String getCelularIn() {
		return celularIn;
	}

	/**
	 * Sets the celular in.
	 *
	 * @param celularIn the new celular in
	 */
	public void setCelularIn(String celularIn) {
		this.celularIn = celularIn;
	}

	/**
	 * Gets the compania celular.
	 *
	 * @return the compania celular
	 */
	public String getCompaniaCelular() {
		return companiaCelular;
	}

	/**
	 * Sets the compania celular.
	 *
	 * @param companiaCelular the new compania celular
	 */
	public void setCompaniaCelular(String companiaCelular) {
		this.companiaCelular = companiaCelular;
	}

	/**
	 * Gets the posicion celular.
	 *
	 * @return the posicion celular
	 */
	public String getPosicionCelular() {
		return posicionCelular;
	}

	/**
	 * Sets the posicion celular.
	 *
	 * @param posicionCelular the new posicion celular
	 */
	public void setPosicionCelular(String posicionCelular) {
		this.posicionCelular = posicionCelular;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celulares
	 */
	public CelularResponse[] getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celulares the new celular
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
	 * @param email the new email
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
	 * @param mensajeFeedback the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
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
	 * @param estadoCliente the new estado cliente
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
	 * @param provincias the new provincias
	 */
	public void setProvincias(List<ProvinciaView> provincias) {
		this.provincias = provincias;
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
	 * @param listaDomicilios the new lista domicilios
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
	 * @param companias the new companias
	 */
	public void setCompanias(List<String> companias) {
		this.companias = companias;
	}

	public String getEmailPrimarioPrevio() {
		return emailPrimarioPrevio;
	}

	public void setEmailPrimarioPrevio(String emailPrimarioPrevio) {
		this.emailPrimarioPrevio = emailPrimarioPrevio;
	}

	public String getEmailSecundarioPrevio() {
		return emailSecundarioPrevio;
	}

	public void setEmailSecundarioPrevio(String emailSecundarioPrevio) {
		this.emailSecundarioPrevio = emailSecundarioPrevio;
	}

	public String getCelularPrevio() {
		return celularPrevio;
	}

	public void setCelularPrevio(String celularPrevio) {
		this.celularPrevio = celularPrevio;
	}
	
}
