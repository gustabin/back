/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.Date;

/**
 * The Class AltaSGIClaveInEntity.
 */
public class AltaSGIClaveInEntity {

	/** The prefijo. */
	private String prefijo;

	/** The sufijo. */
	private String sufijo;

	/** The dni. */
	private String dni;

	/** The fecha nacimiento. */
	private Date fechaNacimiento;

	/** The nup. */
	private String nup;

	/** The ip. */
	private String ip;

	/** The id sesion. */
	private String idSesion;

	/** The clave encriptado. */
	private String claveEncriptado;

	/** The usuario encriptado. */
	private String usuarioEncriptado;

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the clave encriptado.
	 *
	 * @return the clave encriptado
	 */
	public String getClaveEncriptado() {
		return claveEncriptado;
	}

	/**
	 * Sets the clave encriptado.
	 *
	 * @param claveEncriptado
	 *            the new clave encriptado
	 */
	public void setClaveEncriptado(String claveEncriptado) {
		this.claveEncriptado = claveEncriptado;
	}

	/**
	 * Gets the usuario encriptado.
	 *
	 * @return the usuario encriptado
	 */
	public String getUsuarioEncriptado() {
		return usuarioEncriptado;
	}

	/**
	 * Sets the usuario encriptado.
	 *
	 * @param usuarioEncriptado
	 *            the new usuario encriptado
	 */
	public void setUsuarioEncriptado(String usuarioEncriptado) {
		this.usuarioEncriptado = usuarioEncriptado;
	}

	/**
	 * Gets the id sesion.
	 *
	 * @return the id sesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * Sets the id sesion.
	 *
	 * @param idSesion
	 *            the new id sesion
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * Gets the prefijo.
	 *
	 * @return the prefijo
	 */
	public String getPrefijo() {
		return prefijo;
	}

	/**
	 * Sets the prefijo.
	 *
	 * @param prefijo
	 *            the new prefijo
	 */
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	/**
	 * Gets the sufijo.
	 *
	 * @return the sufijo
	 */
	public String getSufijo() {
		return sufijo;
	}

	/**
	 * Sets the sufijo.
	 *
	 * @param sufijo
	 *            the new sufijo
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
