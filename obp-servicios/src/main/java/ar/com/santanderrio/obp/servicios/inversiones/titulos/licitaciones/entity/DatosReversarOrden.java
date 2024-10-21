/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosReversarOrden.
 */
public class DatosReversarOrden {

	/** The num orden. */
	@JsonProperty("NumOrden")
	private long numOrden;

	/** The usuario racf. */
	@JsonProperty("UsuarioRacf")
	private String usuarioRacf;

	/** The password racf. */
	@JsonProperty("PasswordRacf")
	private String passwordRacf;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal;

	/** The subcanal. */
	@JsonProperty("Subcanal")
	private String subcanal;

	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;

	/** The ip. */
	@JsonProperty("Ip")
	private String ip;

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("TipoEjecucion")
	private String tipoEjecucion;

	/**
	 * Gets the num orden.
	 *
	 * @return the num orden
	 */
	public long getNumOrden() {
		return numOrden;
	}

	/**
	 * Sets the num orden.
	 *
	 * @param numOrden
	 *            the new num orden
	 */
	public void setNumOrden(long numOrden) {
		this.numOrden = numOrden;
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
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the new canal
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the subcanal.
	 *
	 * @return the subcanal
	 */
	public String getSubcanal() {
		return subcanal;
	}

	/**
	 * Sets the subcanal.
	 *
	 * @param subcanal
	 *            the new subcanal
	 */
	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
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

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}

}
