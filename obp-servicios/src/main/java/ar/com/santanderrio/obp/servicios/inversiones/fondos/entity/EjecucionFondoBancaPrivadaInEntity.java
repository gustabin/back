/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

import java.math.BigDecimal;

/**
 * The Class EjecucionFondoInEntity.
 */
public class EjecucionFondoBancaPrivadaInEntity {

	/** The cliente */
	private Cliente cliente;

	/** The tipo. */
	private String tipo;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The especie. */
	private String especie;

	/** The moneda. */
	private String moneda;

	/** The cuotas partes. */
	private String cuotasPartes;

	/** The captial. */
	private BigDecimal capital;

	/** The especie destino. */
	private String especieDestino = null;

	/** The is perfil inversion. */
	private String isPerfilInversion;

	/** The usuario racf. */
	private String usuarioRacf;

	/** The password racf. */
	private String passwordRacf;

	/** the nombre fondo. Utilizado para los mensajes de error */
	private String nombreFondo;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the cuotas partes.
	 *
	 * @return the cuotas partes
	 */
	public String getCuotasPartes() {
		return cuotasPartes;
	}

	/**
	 * Sets the cuotas partes.
	 *
	 * @param cuotasPartes
	 *            the new cuotas partes
	 */
	public void setCuotasPartes(String cuotasPartes) {
		this.cuotasPartes = cuotasPartes;
	}

	/**
	 * Gets the especie destino.
	 *
	 * @return the especie destino
	 */
	public String getEspecieDestino() {
		return especieDestino;
	}

	/**
	 * Sets the especie destino.
	 *
	 * @param especieDestino
	 *            the new especie destino
	 */
	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	/**
	 * Gets the checks if is perfil inversion.
	 *
	 * @return the checks if is perfil inversion
	 */
	public String getIsPerfilInversion() {
		return isPerfilInversion;
	}

	/**
	 * Sets the checks if is perfil inversion.
	 *
	 * @param isPerfilInversion
	 *            the new checks if is perfil inversion
	 */
	public void setIsPerfilInversion(String isPerfilInversion) {
		this.isPerfilInversion = isPerfilInversion;
	}

	/**
	 * Gets the usuario racf.
	 *
	 * @return the usuario racf
	 */
	public String getUsuarioRacf() {
		return usuarioRacf;
	}

	/**
	 * Sets the usuario racf.
	 *
	 * @param usuarioRacf
	 *            the new usuario racf
	 */
	public void setUsuarioRacf(String usuarioRacf) {
		this.usuarioRacf = usuarioRacf;
	}

	/**
	 * Gets the password racf.
	 *
	 * @return the password racf
	 */
	public String getPasswordRacf() {
		return passwordRacf;
	}

	/**
	 * Sets the password racf.
	 *
	 * @param passwordRacf
	 *            the new password racf
	 */
	public void setPasswordRacf(String passwordRacf) {
		this.passwordRacf = passwordRacf;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

}
