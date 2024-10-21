/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaOperaciones.
 */
public class DatosConsultaOperaciones  {

	/** The cuentas titulo. - Se inicializa con "" porque se van a concatenar
	 * las cuentas titulos que se enviaran. Es decir un string pero funciona como vector. */
	@JsonProperty("CuentaTitulo")
	private String cuentaTitulo = "";
	
	/** The fecha desde. */
	@JsonProperty("FechaDesde")
	private String fechaDesde;
	
	/** The fecha hasta. */
	@JsonProperty("FechaHasta")
	private String fechaHasta;
	
	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;
	
	
	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	
	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}
	
	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
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
}
