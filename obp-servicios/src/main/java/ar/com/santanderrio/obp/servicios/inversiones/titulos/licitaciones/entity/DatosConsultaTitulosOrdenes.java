/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaTitulosOrdenes.
 */
public class DatosConsultaTitulosOrdenes {
	
	/** The fecha desde. */
	@JsonProperty("FechaDesde")
	private String fechaDesde;
	
	/** The fecha hasta. */
	@JsonProperty("FechaHasta")
	private String fechaHasta;
		
	/** Se envia null por que es un campo no requerido.*/
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos = null;
		
	/** The usuario. */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The ip. */
	@JsonProperty("Ip")
	private String ip;
	
	/** The canal. */
	@JsonProperty("Canal")
	private String canal;
	
	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal;
	
	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The numero orden. */
	@JsonProperty("NumeroOrden")
	private String numeroOrden;
	
	/** The estado. */
	@JsonProperty("Estado")
	private String estado;
	
	/** The cabecera. */
	@JsonProperty("Cabecera")
	private CabeceraOrdenesTitulosEntity cabecera;

	
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
	 * Gets the cuenta titulos.
	 *
	 * @return the cuenta titulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulos
	 */
	public void setCuentaTitulos(String cuentaTitulo) {
		this.cuentaTitulos = cuentaTitulo;
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
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
