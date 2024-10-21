/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultarOrden.
 */
public class DatosConsultarOrden implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2751353511847349318L;

	/** The TipoPliego. */
	@JsonProperty("TipoPliego")
	private String tipoPliego;

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

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;
	
	/** The liquidadas. */
	@JsonProperty("Liquidadas")
	private String liquidadas;

	/**
	 * Gets the tipo pliego.
	 *
	 * @return the tipo pliego
	 */
	public String getTipoPliego() {
		return tipoPliego;
	}

	/**
	 * Sets the tipo pliego.
	 *
	 * @param tipoPliego
	 *            the new tipo pliego
	 */
	public void setTipoPliego(String tipoPliego) {
		this.tipoPliego = tipoPliego;
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
	 * Gets the liquidadas.
	 *
	 * @return the liquidadas
	 */
    public String getLiquidadas() {
        return liquidadas;
    }

    /**
	 * Sets the liquidadas.
	 *
	 * @param liquidadas
	 *            the new liquidadas
	 */
    public void setLiquidadas(String liquidadas) {
        this.liquidadas = liquidadas;
    }
	
	// public static long getSerialversionuid() {
	// return serialVersionUID;
	// }

}
