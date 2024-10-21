package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosAccesoDirectoRequestEntity {
	
	@JsonProperty("Nup")
	private String nup;

	@JsonProperty("IP")
	private String ip;

	@JsonProperty("Usuario")
	private String usuario;
	
	public void setNup(String nup) {
		this.nup = nup;
	}
	
	public String getNup(){
		return nup;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getIp(){
		return ip;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario(){
		return usuario;
	}
}
