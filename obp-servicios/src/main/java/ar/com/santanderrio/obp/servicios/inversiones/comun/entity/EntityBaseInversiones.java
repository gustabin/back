package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.CabeceraOrdenesTitulosEntity;

/**
 * 
 * The Class EntityBaseInversiones 
 * @author B048053
 *
 */

public class EntityBaseInversiones {
	
	/**  The Usuario */
	@JsonProperty("Usuario")
	private String usuario;
	
	/** The Ip*/
	@JsonProperty("Ip")
	private String ip;
	
	/** The Cabecera */
	@JsonProperty("Encabezado")
	private CabeceraOrdenesTitulosEntity encabezado = new CabeceraOrdenesTitulosEntity();

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public CabeceraOrdenesTitulosEntity getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(CabeceraOrdenesTitulosEntity encabezado) {
		this.encabezado = encabezado;
	} 

	
}
