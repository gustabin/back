/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosObtenerCuentasTitulos.
 */
public class DatosObtenerCuentasTitulos implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6687114398319451875L;

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

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
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
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

}
