/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ConsultaAperturaEspecieResponse.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaAperturaEspecieResponse {

	/** The codigo. */
	@JsonProperty("Codigo")
	private int codigo;

	/** The mensaje. */
	@JsonProperty("Mensaje")
	private String mensaje;

	/** The mensaje tecnico. */
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosAperturaEspecieResponse datos;

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
	 *            the codigo to set
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
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje tecnico.
	 *
	 * @return the mensajeTecnico
	 */
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * Sets the mensaje tecnico.
	 *
	 * @param mensajeTecnico
	 *            the mensajeTecnico to set
	 */
	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosAperturaEspecieResponse getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosAperturaEspecieResponse datos) {
		this.datos = datos;
	}

}