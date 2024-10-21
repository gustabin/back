/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * DomicilioEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class DomicilioEntity {

	/** The secuencia domicilio. */
	@Field
	private String secuenciaDomicilio;

	/** The tipo domicilio. */
	@Field
	private String tipoDomicilio;

	/** The calle. */
	@Field
	private String calle;

	/** The observaciones. */
	@Field
	private String observaciones;

	/** The sucursal. */
	@Field
	private String sucursal;

	/** The apt. */
	@Field
	private String apt;

	/** The localidad. */
	@Field
	private String localidad;

	/** The comuna. */
	@Field
	private String comuna;

	/** The codigo postal. */
	@Field
	private String codigoPostal;

	/** The provincia. */
	@Field
	private String provincia;

	/** The pais. */
	@Field
	private String pais;

	/** The marca dom erroneo. */
	@Field
	private String marcaDomErroneo;

	/** The telefono. */
	@Field
	private String telefono;

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the tipo domicilio.
	 *
	 * @return the tipo domicilio
	 */
	public String getTipoDomicilio() {
		return tipoDomicilio;
	}

	/**
	 * Sets the tipo domicilio.
	 *
	 * @param tipoDomicilio
	 *            the new tipo domicilio
	 */
	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}

	/**
	 * Gets the calle.
	 *
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Sets the calle.
	 *
	 * @param calle
	 *            the new calle
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones
	 *            the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the apt.
	 *
	 * @return the apt
	 */
	public String getApt() {
		return apt;
	}

	/**
	 * Sets the apt.
	 *
	 * @param apt
	 *            the new apt
	 */
	public void setApt(String apt) {
		this.apt = apt;
	}

	/**
	 * Gets the localidad.
	 *
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * Sets the localidad.
	 *
	 * @param localidad
	 *            the new localidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	/**
	 * Gets the comuna.
	 *
	 * @return the comuna
	 */
	public String getComuna() {
		return comuna;
	}

	/**
	 * Sets the comuna.
	 *
	 * @param comuna
	 *            the new comuna
	 */
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	/**
	 * Gets the codigo postal.
	 *
	 * @return the codigo postal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Sets the codigo postal.
	 *
	 * @param codigoPostal
	 *            the new codigo postal
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Gets the provincia.
	 *
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}

	/**
	 * Sets the provincia.
	 *
	 * @param provincia
	 *            the new provincia
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais
	 *            the new pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Gets the marca dom erroneo.
	 *
	 * @return the marca dom erroneo
	 */
	public String getMarcaDomErroneo() {
		return marcaDomErroneo;
	}

	/**
	 * Sets the marca dom erroneo.
	 *
	 * @param marcaDomErroneo
	 *            the new marca dom erroneo
	 */
	public void setMarcaDomErroneo(String marcaDomErroneo) {
		this.marcaDomErroneo = marcaDomErroneo;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the new telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}