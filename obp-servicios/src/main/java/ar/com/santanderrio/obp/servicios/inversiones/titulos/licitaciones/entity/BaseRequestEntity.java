/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Entidad base para los llamados al ws de Licitaciones.
 *
 * @author marcelo.ruiz
 */
public class BaseRequestEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7542860754923567723L;

	/** The TIPO_HASH_B64. */
	private static String TIPO_HASH_B64 = "0";

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
	@JsonProperty("DatosFirmado")
	private String datosFirmado = "0";

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
	public String getDatosFirmado() {
		return datosFirmado;
	}

	/**
	 * Sets the datos firmado.
	 *
	 * @param datosFirmado
	 *            the new datos firmado
	 */
	public void setDatosFirmado(String datosFirmado) {
		this.datosFirmado = datosFirmado;
	}

}
