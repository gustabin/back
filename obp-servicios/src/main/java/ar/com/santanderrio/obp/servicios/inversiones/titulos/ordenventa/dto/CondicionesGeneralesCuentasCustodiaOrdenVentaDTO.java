/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.custodia.view.CuentaCustodiaView;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CondicionesGeneralesCuentasCustodiaOrdenVentaDTO.
 */
public class CondicionesGeneralesCuentasCustodiaOrdenVentaDTO {

	/** The cuentas firmadas. */
	private List<Cuenta> cuentasFirmadas;
	
	/** The cuentas sin firmar. */
	private List<Cuenta> cuentasSinFirmar;
	
	/** The cuentas firmadas sin validacion. */
	private List<CuentaCustodiaView> cuentasFirmadasSinValidacion;
	
	/** The cuentas sin firmar sin validacion. */
	private List<CuentaCustodiaView> cuentasSinFirmarSinValidacion;
	
	/** The intro. */
	private String intro;
	
	/** The condiciones. */
	private String condiciones;
	
	/** The legal. */
	private String legal;
	
	/** The legal mail. */
	private String legalMail;
	
	/** The mail. */
	private String mail;
	
	/** The condiciones aceptadas. */
	private Boolean condicionesAceptadas;
	
	/** The comprobantes disponibles. */
	private Boolean comprobantesDisponibles;

	/**
	 * Gets the condiciones aceptadas.
	 *
	 * @return the condicionesAceptadas
	 */
	public Boolean getCondicionesAceptadas() {
		return condicionesAceptadas;
	}

	/**
	 * Sets the condiciones aceptadas.
	 *
	 * @param condicionesAceptadas
	 *            the condicionesAceptadas to set
	 */
	public void setCondicionesAceptadas(Boolean condicionesAceptadas) {
		this.condicionesAceptadas = condicionesAceptadas;
	}

	/**
	 * Gets the comprobantes disponibles.
	 *
	 * @return the comprobantesDisponibles
	 */
	public Boolean getComprobantesDisponibles() {
		return comprobantesDisponibles;
	}

	/**
	 * Sets the comprobantes disponibles.
	 *
	 * @param comprobantesDisponibles
	 *            the comprobantesDisponibles to set
	 */
	public void setComprobantesDisponibles(Boolean comprobantesDisponibles) {
		this.comprobantesDisponibles = comprobantesDisponibles;
	}

	/**
	 * Gets the intro.
	 *
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * Sets the intro.
	 *
	 * @param intro
	 *            the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * Gets the condiciones.
	 *
	 * @return the condiciones
	 */
	public String getCondiciones() {
		return condiciones;
	}

	/**
	 * Sets the condiciones.
	 *
	 * @param condiciones
	 *            the condiciones to set
	 */
	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
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
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the legal mail.
	 *
	 * @return the legalMail
	 */
	public String getLegalMail() {
		return legalMail;
	}

	/**
	 * Sets the legal mail.
	 *
	 * @param legalMail
	 *            the legalMail to set
	 */
	public void setLegalMail(String legalMail) {
		this.legalMail = legalMail;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<Cuenta> getCuentasFirmadas() {
		return cuentasFirmadas;
	}

	/**
	 * Sets the cuentas firmadas.
	 *
	 * @param cuentasFirmadas
	 *            the cuentasFirmadas to set
	 */
	public void setCuentasFirmadas(List<Cuenta> cuentasFirmadas) {
		this.cuentasFirmadas = cuentasFirmadas;
	}

	/**
	 * Gets the cuentas sin firmar.
	 *
	 * @return the cuentasSinFirmar
	 */
	public List<Cuenta> getCuentasSinFirmar() {
		return cuentasSinFirmar;
	}

	/**
	 * Sets the cuentas sin firmar.
	 *
	 * @param cuentasSinFirmar
	 *            the cuentasSinFirmar to set
	 */
	public void setCuentasSinFirmar(List<Cuenta> cuentasSinFirmar) {
		this.cuentasSinFirmar = cuentasSinFirmar;
	}
	
	/**
	 * Gets the cuentas firmadas sin validacion.
	 *
	 * @return the cuentasFirmadasSinValidacion
	 */
	public List<CuentaCustodiaView> getCuentasFirmadasSinValidacion() {
		return cuentasFirmadasSinValidacion;
	}

	/**
	 * Sets the cuentas firmadas sin validacion.
	 *
	 * @param cuentasFirmadasSinValidacion
	 *            the cuentasFirmadasSinValidacion to set
	 */
	public void setCuentasFirmadasSinValidacion(List<CuentaCustodiaView> cuentasFirmadasSinValidacion) {
		this.cuentasFirmadasSinValidacion = cuentasFirmadasSinValidacion;
	}

	/**
	 * Gets the cuentas sin firmar sin validacion.
	 *
	 * @return the cuentasSinFirmarSinValidacion
	 */
	public List<CuentaCustodiaView> getCuentasSinFirmarSinValidacion() {
		return cuentasSinFirmarSinValidacion;
	}

	/**
	 * Sets the cuentas sin firmar sin validacion.
	 *
	 * @param cuentasSinFirmarSinValidacion
	 *            the cuentasSinFirmar to set
	 */
	public void setCuentasSinFirmarSinValidacion(List<CuentaCustodiaView> cuentasSinFirmar) {
		this.cuentasSinFirmarSinValidacion = cuentasSinFirmar;
	}

}
