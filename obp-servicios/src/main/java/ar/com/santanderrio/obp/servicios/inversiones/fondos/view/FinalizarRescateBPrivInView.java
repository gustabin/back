/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.validation.constraints.NotNull;

/**
 * The Class FinalizarRescateBPrivInView.
 *
 * @author marcelo.ruiz
 */
public class FinalizarRescateBPrivInView implements FinalizarRescateHash{

	/** Nombre del fondo. */
	private String nombreFondo;

	/** Codigo del fondo. */
	@NotNull
	private String codigoFondo;

	/** The numero cta cred. */
	@NotNull
	private String numeroCtaCred;

	/** Tipo de Moneda $ o u$s. */
	@NotNull
	private String moneda;

	/** Importe a suscribir. */
	@NotNull
	private String importe;

	/** The cuota partes. */
	private String cuotaPartes;

	/** The dentro perfil. */
	@NotNull
	private String dentroDelPerfil;
	
	/** The cuenta titulo. */
	private String cuentaTitulo;
	
	/** The sucursal cta cred. */
	private String sucursalCtaCred;
	
	private boolean esEspecie;

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombreFondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the nombreFondo to set
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigoFondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the codigoFondo to set
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the numero cta cred.
	 *
	 * @return the numeroCtaCred
	 */
	public String getNumeroCtaCred() {
		return numeroCtaCred;
	}

	/**
	 * Sets the numero cta cred.
	 *
	 * @param numeroCtaCred
	 *            the numeroCtaCred to set
	 */
	public void setNumeroCtaCred(String numeroCtaCred) {
		this.numeroCtaCred = numeroCtaCred;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the cuota partes.
	 *
	 * @return the cuotaPartes
	 */
	public String getCuotaPartes() {
		return cuotaPartes;
	}

	/**
	 * Sets the cuota partes.
	 *
	 * @param cuotaPartes
	 *            the cuotaPartes to set
	 */
	public void setCuotaPartes(String cuotaPartes) {
		this.cuotaPartes = cuotaPartes;
	}

	/**
	 * Gets the dentro del perfil.
	 *
	 * @return the dentroDelPerfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the dentroDelPerfil to set
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the sucursal cta cred.
	 *
	 * @return the sucursalCtaCred
	 */
	public String getSucursalCtaCred() {
		return sucursalCtaCred;
	}

	/**
	 * Sets the sucursal cta cred.
	 *
	 * @param sucursalCtaCred
	 *            the sucursalCtaCred to set
	 */
	public void setSucursalCtaCred(String sucursalCtaCred) {
		this.sucursalCtaCred = sucursalCtaCred;
	}

	public boolean isEsEspecie() {
		return esEspecie;
	}

	public void setEsEspecie(boolean esEspecie) {
		this.esEspecie = esEspecie;
	}
}