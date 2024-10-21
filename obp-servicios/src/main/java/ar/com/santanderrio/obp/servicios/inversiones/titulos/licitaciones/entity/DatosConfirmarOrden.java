/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConfirmarOrden.
 */
public class DatosConfirmarOrden {

	/** The monto A deb. */
	@JsonProperty("MontoADeb")
	private double montoADeb;

	/** The num orden. */
	@JsonProperty("NumOrden")
	private long numOrden;

	/** The usuario racf. */
	@JsonProperty("UsuarioRacf")
	private String usuarioRacf;

	/** The password racf. */
	@JsonProperty("PasswordRacf")
	private String passwordRacf;

	/** The cuenta oper. */
	@JsonProperty("CuentaOper")
	private long cuentaOper;

	/** The tipo cuenta oper. */
	@JsonProperty("TipoCuentaOper")
	private String tipoCuentaOper;

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
	@JsonProperty("ip")
	private String ip;

	@JsonProperty("tipoEjecucion")
	private String tipoEjecucion;

	/**
	 * Gets the monto A deb.
	 *
	 * @return the montoADeb
	 */
	public double getMontoADeb() {
		return montoADeb;
	}

	/**
	 * Sets the monto A deb.
	 *
	 * @param montoADeb the montoADeb to set
	 */
	public void setMontoADeb(double montoADeb) {
		this.montoADeb = montoADeb;
	}

	/**
	 * Gets the num orden.
	 *
	 * @return the numOrden
	 */
	public long getNumOrden() {
		return numOrden;
	}

	/**
	 * Sets the num orden.
	 *
	 * @param numOrden the numOrden to set
	 */
	public void setNumOrden(long numOrden) {
		this.numOrden = numOrden;
	}

	/**
	 * Gets the usuario racf.
	 *
	 * @return the usuarioRacf
	 */
	public String getUsuarioRacf() {
		return usuarioRacf;
	}

	/**
	 * Sets the usuario racf.
	 *
	 * @param usuarioRacf the usuarioRacf to set
	 */
	public void setUsuarioRacf(String usuarioRacf) {
		this.usuarioRacf = usuarioRacf;
	}

	/**
	 * Gets the password racf.
	 *
	 * @return the passwordRacf
	 */
	public String getPasswordRacf() {
		return passwordRacf;
	}

	/**
	 * Sets the password racf.
	 *
	 * @param passwordRacf the passwordRacf to set
	 */
	public void setPasswordRacf(String passwordRacf) {
		this.passwordRacf = passwordRacf;
	}

	/**
	 * Gets the cuenta oper.
	 *
	 * @return the cuentaOper
	 */
	public long getCuentaOper() {
		return cuentaOper;
	}

	/**
	 * Sets the cuenta oper.
	 *
	 * @param cuentaOper the cuentaOper to set
	 */
	public void setCuentaOper(long cuentaOper) {
		this.cuentaOper = cuentaOper;
	}

	/**
	 * Gets the tipo cuenta oper.
	 *
	 * @return the tipoCuentaOper
	 */
	public String getTipoCuentaOper() {
		return tipoCuentaOper;
	}

	/**
	 * Sets the tipo cuenta oper.
	 *
	 * @param tipoCuentaOper the tipoCuentaOper to set
	 */
	public void setTipoCuentaOper(String tipoCuentaOper) {
		this.tipoCuentaOper = tipoCuentaOper;
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
	 * @param canal the canal to set
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
	 * @param subcanal the subcanal to set
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
	 * @param usuario the usuario to set
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
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}

}
