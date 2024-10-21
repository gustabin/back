/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Objeto que modela un error en la lista de errores del servicio P&L.
 *
 * @author marcelo.ruiz
 */
public class ItemError {

	/** The codigo. */
	@JsonProperty("Codigo")
	private String codigo;

	/** The fechaError. */
	@JsonProperty("FechaError")
	private String fechaError;

	/** The guid. */
	@JsonProperty("GUID")
	private String guid;

	/** The mensaje tecnico. */
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	/**
	 * Gets the mensaje tecnico.
	 *
	 * @return the mensaje tecnico
	 */
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * Sets the mensaje tecnico.
	 *
	 * @param mensajeTecnico
	 *            the new mensaje tecnico
	 */
	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the fecha error.
	 *
	 * @return the fecha error
	 */
	public String getFechaError() {
		return fechaError;
	}

	/**
	 * Sets the fecha error.
	 *
	 * @param fechaError
	 *            the new fecha error
	 */
	public void setFechaError(String fechaError) {
		this.fechaError = fechaError;
	}

	/**
	 * Gets the guid.
	 *
	 * @return the guid
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 *
	 * @param guid
	 *            the new guid
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

}
