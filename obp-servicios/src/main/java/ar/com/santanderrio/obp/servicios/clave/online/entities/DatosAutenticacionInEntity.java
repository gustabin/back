/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.Date;

/**
 * The Class DatosAutenticacionInEntity.
 */
public class DatosAutenticacionInEntity {

	/** The nup. */
	private String nup;

	/** The dni. */
	private String dni;

	/** The ip. */
	private String ip;

	/** The fecha. */
	private Date fecha;

	/** The sesion. */
	private String sesion;

	/** The ciclo. */
	private String ciclo;

	/** The letra A. */
	private String letraA;

	/** The tipo aut. */
	private String tipoAut;

	/** The numero. */
	private String numero;

	/** The clave. */
	private String clave;

	/** The funcion enum. */
	private FuncionEnum funcionEnum;
	
	private String empresaCelularElegida;

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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	 * Gets the ciclo.
	 *
	 * @return the ciclo
	 */
	public String getCiclo() {
		return ciclo;
	}

	/**
	 * Sets the ciclo.
	 *
	 * @param ciclo
	 *            the new ciclo
	 */
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * Gets the letra A.
	 *
	 * @return the letra A
	 */
	public String getLetraA() {
		return letraA;
	}

	/**
	 * Sets the letra A.
	 *
	 * @param letraA
	 *            the new letra A
	 */
	public void setLetraA(String letraA) {
		this.letraA = letraA;
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

	public String getEmpresaCelularElegida() {
		return empresaCelularElegida;
	}

	public void setEmpresaCelularElegida(String empresaCelularElegida) {
		this.empresaCelularElegida = empresaCelularElegida;
	}

}
