/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

import ar.com.santanderrio.obp.servicios.clave.online.entities.FuncionEnum;

/**
 * The Class DatosAutenticacion.
 */
public class DatosAutenticacion {

	/** The funcion enum. */
	private FuncionEnum funcionEnum;

	/** The numero. */
	private String numero;
	
	/** The numero. */
	private String numeroAEnviarEnSegSgiaut ;

	/** The clave. */
	private String clave;

	/** The ciclo. */
	private Integer ciclo;

	/** The sesion. */
	private String sesion;

	/** The tipo aut. */
	private String tipoAut;
	
	private String empresaCelular;
	

	/** The dni. */
	private String dni;

	/** The fecha de nacimiento. */
	private String fechaDeNacimiento;

	/** The ip. */
	private String ip;

	/** The nup. */
	private String nup;

	
	


	/**
	 * @return the numeroAEnviarEnSegSgiaut
	 */
	public String getNumeroAEnviarEnSegSgiaut() {
		return numeroAEnviarEnSegSgiaut;
	}

	/**
	 * @param numeroAEnviarEnSegSgiaut the numeroAEnviarEnSegSgiaut to set
	 */
	public void setNumeroAEnviarEnSegSgiaut(String numeroAEnviarEnSegSgiaut) {
		this.numeroAEnviarEnSegSgiaut = numeroAEnviarEnSegSgiaut;
	}

	/**
	 * Gets the funcion enum.
	 *
	 * @return the funcion enum
	 */
	public FuncionEnum getFuncionEnum() {
		return funcionEnum;
	}

	/**
	 * Sets the funcion enum.
	 *
	 * @param funcionEnum
	 *            the new funcion enum
	 */
	public void setFuncionEnum(FuncionEnum funcionEnum) {
		this.funcionEnum = funcionEnum;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public Integer getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo
	 *            the new ciclo
	 */
	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the sesion.
	 *
	 * @return the sesion
	 */
	public String getSesion() {
		return sesion;
	}

	/**
	 * Sets the sesion.
	 *
	 * @param sesion
	 *            the new sesion
	 */
	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	/**
	 * Gets the tipo aut.
	 *
	 * @return the tipo aut
	 */
	public String getTipoAut() {
		return tipoAut;
	}

	/**
	 * Sets the tipo aut.
	 *
	 * @param tipoAut
	 *            the new tipo aut
	 */
	public void setTipoAut(String tipoAut) {
		this.tipoAut = tipoAut;
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
	 * Gets the fecha de nacimiento.
	 *
	 * @return the fecha de nacimiento
	 */
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	/**
	 * Sets the fecha de nacimiento.
	 *
	 * @param fechaDeNacimiento
	 *            the new fecha de nacimiento
	 */
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

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

	public String getEmpresaCelular() {
		return empresaCelular;
	}

	public void setEmpresaCelular(String empresaCelular) {
		this.empresaCelular = empresaCelular;
	}
}
