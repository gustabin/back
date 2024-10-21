/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dto;

import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;

/**
 * The Class CredencialesMyaIn.
 */
public class CredencialesMyaIn {

	/** The email. */
	private String email;

	/** The tipo operacion email. */
	private MyaCodOperacionEnum tipoOperacionEmail;

	/** The codigo area. */
	private String codigoArea;

	/** The celular. */
	private String celular;

	/** The tipo operacion celular. */
	private MyaCodOperacionEnum tipoOperacionCelular;

	/** The compania seleccionada. */
	private TipoCompaniaEnum companiaSeleccionada;

	/** The celular id. */
	private String celularId;

	/** The email id. */
	private String emailId;

	/** The email secundario. */
	private String emailSecundario;

	/** The email secundario id. */
	private String emailSecundarioId;

	/** The codigo area secundario. */
	private String codigoAreaSecundario;

	/** The celular secundario. */
	private String celularSecundario;

	/** The celular secundario id. */
	private String celularSecundarioId;

	/** The compania seleccionada secundaria. */
	private TipoCompaniaEnum companiaSeleccionadaSecundaria;

	/** The tipo operacion celular. */
	private MyaCodOperacionEnum tipoOperacionCelularSecundaria;

	/** The tipo operacion email. */
	private MyaCodOperacionEnum tipoOperacionEmailSecundaria;

	/** The solo primarios. */
	private boolean soloPrimarios;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular
	 *            the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the compania seleccionada.
	 *
	 * @return the compania seleccionada
	 */
	public TipoCompaniaEnum getCompaniaSeleccionada() {
		return companiaSeleccionada;
	}

	/**
	 * Datos de contacto.
	 *
	 * @return the email secundario
	 */

	public String getEmailSecundario() {
		return emailSecundario;
	}

	/**
	 * Sets the email secundario.
	 *
	 * @param emailSecundario
	 *            the new email secundario
	 */
	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	/**
	 * Gets the email secundario id.
	 *
	 * @return the email secundario id
	 */
	public String getEmailSecundarioId() {
		return emailSecundarioId;
	}

	/**
	 * Sets the email secundario id.
	 *
	 * @param emailSecundarioId
	 *            the new email secundario id
	 */
	public void setEmailSecundarioId(String emailSecundarioId) {
		this.emailSecundarioId = emailSecundarioId;
	}

	/**
	 * Gets the codigo area secundario.
	 *
	 * @return the codigo area secundario
	 */
	public String getCodigoAreaSecundario() {
		return codigoAreaSecundario;
	}

	/**
	 * Sets the codigo area secundario.
	 *
	 * @param codigoAreaSecundario
	 *            the new codigo area secundario
	 */
	public void setCodigoAreaSecundario(String codigoAreaSecundario) {
		this.codigoAreaSecundario = codigoAreaSecundario;
	}

	/**
	 * Gets the celular secundario.
	 *
	 * @return the celular secundario
	 */
	public String getCelularSecundario() {
		return celularSecundario;
	}

	/**
	 * Sets the celular secundario.
	 *
	 * @param celularSecundario
	 *            the new celular secundario
	 */
	public void setCelularSecundario(String celularSecundario) {
		this.celularSecundario = celularSecundario;
	}

	/**
	 * Gets the celular secundario id.
	 *
	 * @return the celular secundario id
	 */
	public String getCelularSecundarioId() {
		return celularSecundarioId;
	}

	/**
	 * Sets the celular secundario id.
	 *
	 * @param celularSecundarioId
	 *            the new celular secundario id
	 */
	public void setCelularSecundarioId(String celularSecundarioId) {
		this.celularSecundarioId = celularSecundarioId;
	}

	/**
	 * Gets the compania seleccionada secundaria.
	 *
	 * @return the compania seleccionada secundaria
	 */
	public TipoCompaniaEnum getCompaniaSeleccionadaSecundaria() {
		return companiaSeleccionadaSecundaria;
	}

	/**
	 * Sets the compania seleccionada secundaria.
	 *
	 * @param companiaSeleccionadaSecundaria
	 *            the new compania seleccionada secundaria
	 */
	public void setCompaniaSeleccionadaSecundaria(TipoCompaniaEnum companiaSeleccionadaSecundaria) {
		this.companiaSeleccionadaSecundaria = companiaSeleccionadaSecundaria;
	}

	/**
	 * Sets the compania seleccionada.
	 *
	 * @param companiaSeleccionada
	 *            the new compania seleccionada
	 */
	public void setCompaniaSeleccionada(TipoCompaniaEnum companiaSeleccionada) {
		this.companiaSeleccionada = companiaSeleccionada;
	}

	/**
	 * Gets the tipo operacion email.
	 *
	 * @return the tipo operacion email
	 */
	public MyaCodOperacionEnum getTipoOperacionEmail() {
		return tipoOperacionEmail;
	}

	/**
	 * Sets the tipo operacion email.
	 *
	 * @param tipoOperacionEmail
	 *            the new tipo operacion email
	 */
	public void setTipoOperacionEmail(MyaCodOperacionEnum tipoOperacionEmail) {
		this.tipoOperacionEmail = tipoOperacionEmail;
	}

	/**
	 * Gets the tipo operacion celular.
	 *
	 * @return the tipo operacion celular
	 */
	public MyaCodOperacionEnum getTipoOperacionCelular() {
		return tipoOperacionCelular;
	}

	/**
	 * Sets the tipo operacion celular.
	 *
	 * @param tipoOperacionCelular
	 *            the new tipo operacion celular
	 */
	public void setTipoOperacionCelular(MyaCodOperacionEnum tipoOperacionCelular) {
		this.tipoOperacionCelular = tipoOperacionCelular;
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
	 * @param codigoArea
	 *            the new codigo area
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Gets the celular id.
	 *
	 * @return the celular id
	 */
	public String getCelularId() {
		return celularId;
	}

	/**
	 * Sets the celular id.
	 *
	 * @param celularId
	 *            the new celular id
	 */
	public void setCelularId(String celularId) {
		this.celularId = celularId;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the tipo operacion celular secundaria.
	 *
	 * @return the tipo operacion celular secundaria
	 */
	public MyaCodOperacionEnum getTipoOperacionCelularSecundaria() {
		return tipoOperacionCelularSecundaria;
	}

	/**
	 * Sets the tipo operacion celular secundaria.
	 *
	 * @param tipoOperacionCelularSecundaria
	 *            the new tipo operacion celular secundaria
	 */
	public void setTipoOperacionCelularSecundaria(MyaCodOperacionEnum tipoOperacionCelularSecundaria) {
		this.tipoOperacionCelularSecundaria = tipoOperacionCelularSecundaria;
	}

	/**
	 * Gets the tipo operacion email secundaria.
	 *
	 * @return the tipo operacion email secundaria
	 */
	public MyaCodOperacionEnum getTipoOperacionEmailSecundaria() {
		return tipoOperacionEmailSecundaria;
	}

	/**
	 * Sets the tipo operacion email secundaria.
	 *
	 * @param tipoOperacionEmailSecundaria
	 *            the new tipo operacion email secundaria
	 */
	public void setTipoOperacionEmailSecundaria(MyaCodOperacionEnum tipoOperacionEmailSecundaria) {
		this.tipoOperacionEmailSecundaria = tipoOperacionEmailSecundaria;
	}

	/**
	 * Checks if is solo primarios.
	 *
	 * @return true, if is solo primarios
	 */
	public boolean isSoloPrimarios() {
		return soloPrimarios;
	}

	/**
	 * Sets the solo primarios.
	 *
	 * @param soloPrimarios
	 *            the new solo primarios
	 */
	public void setSoloPrimarios(boolean soloPrimarios) {
		this.soloPrimarios = soloPrimarios;
	}

}
