/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Entidad base para los request a los servicios de PLTenenciaValuadaServiceRest
 * contiene los atributos errores, ip y usuario no se si se usan en realidad ya
 * que mandandolos siempre en null los servicios funcionan igual.
 *
 * @author marcelo.ruiz
 */
public abstract class EntityBase {

	/** The errores. */
	@JsonProperty("Errores")
	private String errores = null;

	/** The ip. */
	@JsonProperty("Ip")
	private String ip = null;

	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario = null;

	/**
	 * Gets the errores.
	 *
	 * @return the errores
	 */
	public String getErrores() {
		return errores;
	}

	/**
	 * Sets the errores.
	 *
	 * @param errores
	 *            the new errores
	 */
	public void setErrores(String errores) {
		this.errores = errores;
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
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
