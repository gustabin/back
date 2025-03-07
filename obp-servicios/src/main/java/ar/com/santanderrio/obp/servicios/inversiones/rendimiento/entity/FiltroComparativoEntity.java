/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class FiltroComparativoEntity.
 */
public class FiltroComparativoEntity {
	
	/** The codigo. */
	@JsonProperty("Codigo")
	private int codigo;

	/** The mensaje. */
	@JsonProperty("Mensaje")
	private String mensaje;

	/** The mensaje tecnico. */
	@JsonProperty("MensajeTecnico")
	private Object mensajeTecnico;

	/** The datos. */
	@JsonProperty("Datos")
	private DatosRespuestaFiltroComparativo datos;

	/**
	 * Gets the codigo.
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje.
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the mensaje tecnico.
	 * @return the mensaje tecnico
	 */
	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * Sets the mensaje tecnico.
	 * @param mensajeTecnico
	 *            the new mensaje tecnico
	 */
	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * Gets the datos.
	 * @return the datos
	 */
	public DatosRespuestaFiltroComparativo getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosRespuestaFiltroComparativo datos) {
		this.datos = datos;
	}

}
