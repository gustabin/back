/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Entidad base de la q deben extender los request al servicio de tenenca
 * valuada que deben ser firmados de esa forma.
 *
 * @author marcelo.ruiz
 */
public abstract class RequestBaseFirmado implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The TIPO_HASH_B64. */
	private static String TIPO_HASH_B64 = "0";

	/** The TIPO_HASH_PEM. */
	private static String TIPO_HASH_PEM = "1";

	/** The tipo hash. */
	@JsonProperty("TipoHash")
	private String tipoHash = TIPO_HASH_B64;

	/** The firma. */
	@JsonProperty("Firma")
	private String firma;

	/** The dato. */
	@JsonProperty("Dato")
	private String dato;

	/** The datos firmado. */
	@JsonProperty("DatosFirmados")
	private int datosFirmados = 0;

	/** The canal. */
	@JsonProperty("Canal")
	private String canal = "04";

	/** The sub canal. */
	@JsonProperty("SubCanal")
	private String subCanal = "0099";

	/**
	 * Gets the tipo hash.
	 *
	 * @return the tipo hash
	 */
	public String getTipoHash() {
		return tipoHash;
	}

	/**
	 * Sets the tipo hash.
	 *
	 * @param tipoHash
	 *            the new tipo hash
	 */
	public void setTipoHash(String tipoHash) {
		this.tipoHash = tipoHash;
	}

	/**
	 * Gets the firma.
	 *
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * Sets the firma.
	 *
	 * @param firma
	 *            the new firma
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	/**
	 * Gets the dato.
	 *
	 * @return the dato
	 */
	public String getDato() {
		return dato;
	}

	/**
	 * Sets the dato.
	 *
	 * @param dato
	 *            the new dato
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}

	/**
	 * Gets the datos firmado.
	 *
	 * @return the datos firmado
	 */
	public int getDatosFirmados() {
		return datosFirmados;
	}

	/**
	 * Sets the datos firmado.
	 *
	 * @param datosFirmados
	 *            the new datos firmados
	 */
	public void setDatosFirmados(int datosFirmados) {
		this.datosFirmados = datosFirmados;
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

}
