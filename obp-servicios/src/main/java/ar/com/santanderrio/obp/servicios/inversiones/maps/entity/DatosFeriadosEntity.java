package ar.com.santanderrio.obp.servicios.inversiones.maps.entity;

import java.util.Date;

public class DatosFeriadosEntity {
	
	private String usuario;
	private String ip;
	private String filtroPais;
	private Date fechaDesde;
	private Date fechaHasta;
	
	
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
	 * @return the filtroPais
	 */
	public String getFiltroPais() {
		return filtroPais;
	}
	/**
	 * @param filtroPais the filtroPais to set
	 */
	public void setFiltroPais(String filtroPais) {
		this.filtroPais = filtroPais;
	}
	/**
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		return fechaDesde;
	}
	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	/**
	 * @return the fechaHasta
	 */
	public Date getFechaHasta() {
		return fechaHasta;
	}
	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	

}
