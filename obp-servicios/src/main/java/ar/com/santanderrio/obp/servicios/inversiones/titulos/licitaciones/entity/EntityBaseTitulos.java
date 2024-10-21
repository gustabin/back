/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class EntityBaseTitulos.
 */
public class EntityBaseTitulos {
	
	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;
	
	/** The cabecera. */
	@JsonProperty("Cabecera")
	private CabeceraOrdenesTitulosEntity cabecera = new CabeceraOrdenesTitulosEntity();

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
	 *            the usuario to set
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
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
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
	 *            the cabecera to set
	 */
	public void setCabecera(CabeceraOrdenesTitulosEntity cabecera) {
		this.cabecera = cabecera;
	}
	
	

}
