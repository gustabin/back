package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

public class DatosTitulosRequestEntity extends EntityBase {
	
	@JsonProperty("Anio")
	private String anio;
	
	@JsonProperty("Nup")
	private String nup;
	
	@JsonProperty("Ip")
	private String ip;
	
	@JsonProperty("TipoEspecie")
	private String espeTipo;
	
	@JsonProperty("Usuario")
	private String usuario;

	/**
	 * @return the espeTipo
	 */
	public String getEspeTipo() {
		return espeTipo;
	}

	/**
	 * @param espeTipo the espeTipo to set
	 */
	public void setEspeTipo(String espeTipo) {
		this.espeTipo = espeTipo;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * @param nup the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

}
