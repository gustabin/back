/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.math.BigDecimal;

/**
 * DTO de entrada el BO para suscripcion de fondo.
 *
 * @author marcelo.ruiz
 */
public class SuscripcionInDTO {

	/** The nro cuenta banca privada. */
	private String nroCuentaBancaPrivada;

	/** codigo del fondo a suscribir. */
	private String codigoFondo;

	/** Importe a suscribir. */
	private BigDecimal importe;

	/** Valor retornado por el servicio de simulacion. */
	private String dentroDelPerfil;

	/** The nombre fondo. */
	private String nombreFondo;

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

}
