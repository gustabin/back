/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class RespuestaDatosOrdenResponse.
 */
public class RespuestaDatosOrdenResponse {
	
	/** The datos orden response. */
	@JsonProperty("Datos")
	private DatosOrdenResponse datosOrdenResponse;
	
	/** The codigo. */
	@JsonProperty("Codigo")
	private int codigo;
	
	/** The mensaje. */
	@JsonProperty("Mensaje")
	private String mensaje;
	
	/** The mensaje tecnico. */
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	/**
	 * Gets the datos orden response.
	 *
	 * @return the datos orden response
	 */
	public DatosOrdenResponse getDatosOrdenResponse() {
		return datosOrdenResponse;
	}

	/**
	 * Sets the datos orden response.
	 *
	 * @param datosOrdenResponse
	 *            the new datos orden response
	 */
	public void setDatosOrdenResponse(DatosOrdenResponse datosOrdenResponse) {
		this.datosOrdenResponse = datosOrdenResponse;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

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
	

}
