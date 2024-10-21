/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * The Class SuscripcionInView.
 */
public class SuscripcionInView {

	/** The cuenta titulo. */
	@Pattern(regexp = "7([0-9]{9})")
	private String nroCuentaBancaPrivada;

	/** codigo del fondo a suscribir. */
	@Pattern(regexp = "[0-9]{3}")
	private String codigoFondo;

	/** Importe a suscribir. */
	@NotNull
	private BigDecimal importe;

	/** Valor retornado por el servicio de simulacion. */
	@NotNull
	private String dentroDelPerfil;

	/** The nombre fondo. */
	@NotNull
	private String nombreFondo;
	
	/** The cuenta titulos. */
	private String cuentaTitulos;
	
	/** The numero cuenta debito. */
	private String numeroCuentaDebito;
	
	/** The sucursal cuenta debito. */
	private String sucursalCuentaDebito;

	/**
	 * Gets the nro cuenta banca privada.
	 *
	 * @return the nro cuenta banca privada
	 */
	public String getNroCuentaBancaPrivada() {
		return nroCuentaBancaPrivada;
	}

	/**
	 * Sets the nro cuenta banca privada.
	 *
	 * @param nroCuentaBancaPrivada
	 *            the new nro cuenta banca privada
	 */
	public void setNroCuentaBancaPrivada(String nroCuentaBancaPrivada) {
		this.nroCuentaBancaPrivada = nroCuentaBancaPrivada;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the dentro del perfil.
	 *
	 * @return the dentro del perfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the new dentro del perfil
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
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

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the cuentaTitulos to set
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numeroCuentaDebito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the numeroCuentaDebito to set
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursalCuentaDebito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the sucursalCuentaDebito to set
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}
}
