/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosFinalizarReversarOrden.
 */
public class DatosFinalizarReversarOrden {

	/** The usuario racf. */
	@JsonProperty("UsuarioRacf")
	private String usuarioRacf;

	/** The password racf. */
	@JsonProperty("PasswordRacf")
	private String passwordRacf;
	
	/** The fecha ingreso. */
	@JsonProperty("Fecha")
	private String fechaIngreso;

	/** The numero orden. */
	@JsonProperty("NumeroOrden")
	private String numeroOrden;

	/** The cantidad. */
	@JsonProperty("Cantidad")
	private String cantidad;
	
	/** The motivo reversa. */
	@JsonProperty("MotivoReversa")
	private String motivoReversa;
		
	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;
	
	/** The canal. */
	@JsonProperty("Canal")
	private String canal;
	
	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal;

	/** The cabecera. */
	@JsonProperty("Cabecera")
	private CabeceraOrdenesTitulosEntity cabecera;
	
	

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
	 * Gets the fecha ingreso.
	 *
	 * @return the fecha ingreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * Sets the fecha ingreso.
	 *
	 * @param fechaIngreso
	 *            the new fecha ingreso
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	
	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the motivo reversa.
	 *
	 * @return the motivo reversa
	 */
	public String getMotivoReversa() {
		return motivoReversa;
	}

	/**
	 * Sets the motivo reversa.
	 *
	 * @param motivoReversa
	 *            the new motivo reversa
	 */
	public void setMotivoReversa(String motivoReversa) {
		this.motivoReversa = motivoReversa;
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
	 * Gets the sub canal.
	 *
	 * @return the sub canal
	 */
	public String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the new sub canal
	 */
	public void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public CabeceraOrdenesTitulosEntity getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(CabeceraOrdenesTitulosEntity cabecera) {
		this.cabecera = cabecera;
	}
	
	

}
