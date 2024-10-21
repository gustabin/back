/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dto;

import java.io.Serializable;
import java.util.List;

/**
 * The Class CredencialesMya.
 */
public class CredencialesMya implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3490484833956461672L;

	/** The email. */
	private String email;

	/** The email secundario. */
	private String emailSecundario;

	/** The celular. */
	private String celular;

	/** The celular secundario. */
	private String celularSecundario;

	/** The codigo area. */
	private String codigoArea;

	/** The codigo area secundario. */
	private String codigoAreaSecundario;

	/** The compania seleccionada. */
	private String companiaSeleccionada;

	/** The compania seleccionada secundaria. */
	private String companiaSeleccionadaSecundaria;

	/** The cliente estado. */
	private String clienteEstado;

	/** The aceptacion contrato. */
	private Long aceptacionContrato;

	/** The companias. */
	private List<String> companias;

	/** The celular id. */
	private String celularId;

	/** The email id. */
	private String emailId;
	
	/** The celular secundario id. */
	private String celularSecundarioId;
	
	/** The email secundario id. */
	private String emailSecundarioId;

	/** The sinonimo. */
	private Boolean sinonimo;
	
	private Boolean mostrarStackActualizarDatos = Boolean.FALSE;

	private String mensajePrincipal;
	
	private String mensajeSecundario;
	
	private Boolean mostrarBotonConfirmacion = Boolean.FALSE;
	
	
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
	public String getCompaniaSeleccionada() {
		return companiaSeleccionada;
	}

	/**
	 * Sets the compania seleccionada.
	 *
	 * @param companiaSeleccionada
	 *            the new compania seleccionada
	 */
	public void setCompaniaSeleccionada(String companiaSeleccionada) {
		this.companiaSeleccionada = companiaSeleccionada;
	}

	/**
	 * Gets the cliente estado.
	 *
	 * @return the cliente estado
	 */
	public String getClienteEstado() {
		return clienteEstado;
	}

	/**
	 * Sets the cliente estado.
	 *
	 * @param clienteEstado
	 *            the new cliente estado
	 */
	public void setClienteEstado(String clienteEstado) {
		this.clienteEstado = clienteEstado;
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
	 * Gets the aceptacion contrato.
	 *
	 * @return the aceptacion contrato
	 */
	public Long getAceptacionContrato() {
		return aceptacionContrato;
	}

	/**
	 * Sets the aceptacion contrato.
	 *
	 * @param aceptacionContrato
	 *            the new aceptacion contrato
	 */
	public void setAceptacionContrato(Long aceptacionContrato) {
		this.aceptacionContrato = aceptacionContrato;
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
	 * Gets the sinonimo.
	 *
	 * @return the sinonimo
	 */
	public Boolean getSinonimo() {
		return sinonimo;
	}

	/**
	 * Sets the sinonimo.
	 *
	 * @param sinonimo
	 *            the new sinonimo
	 */
	public void setSinonimo(Boolean sinonimo) {
		this.sinonimo = sinonimo;
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
	 * @param emailSecundario
	 *            the new email secundario
	 */
	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
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
	 * Gets the compania seleccionada secundaria.
	 *
	 * @return the compania seleccionada secundaria
	 */
	public String getCompaniaSeleccionadaSecundaria() {
		return companiaSeleccionadaSecundaria;
	}

	/**
	 * Sets the compania seleccionada secundaria.
	 *
	 * @param companiaSeleccionadaSecundaria
	 *            the new compania seleccionada secundaria
	 */
	public void setCompaniaSeleccionadaSecundaria(String companiaSeleccionadaSecundaria) {
		this.companiaSeleccionadaSecundaria = companiaSeleccionadaSecundaria;
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

	public Boolean getMostrarStackActualizarDatos() {
		return mostrarStackActualizarDatos;
	}

	public void setMostrarStackActualizarDatos(Boolean mostrarStackActualizarDatos) {
		this.mostrarStackActualizarDatos = mostrarStackActualizarDatos;
	}

	public String getMensajePrincipal() {
		return mensajePrincipal;
	}

	public void setMensajePrincipal(String mensajePrincipal) {
		this.mensajePrincipal = mensajePrincipal;
	}

	public String getMensajeSecundario() {
		return mensajeSecundario;
	}

	public void setMensajeSecundario(String mensajeSecundario) {
		this.mensajeSecundario = mensajeSecundario;
	}

	public Boolean getMostrarBotonConfirmacion() {
		return mostrarBotonConfirmacion;
	}

	public void setMostrarBotonConfirmacion(Boolean mostrarBotonConfirmacion) {
		this.mostrarBotonConfirmacion = mostrarBotonConfirmacion;
	}
		
}
