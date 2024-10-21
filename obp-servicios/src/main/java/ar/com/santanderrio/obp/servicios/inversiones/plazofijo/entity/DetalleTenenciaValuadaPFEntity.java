/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosRespuestaPF;

/**
 * The Class DetalleTenenciaValuadaPFEntity.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleTenenciaValuadaPFEntity {

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
	private DatosRespuestaPF datos;

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
	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * Sets the mensaje tecnico.
	 *
	 * @param mensajeTecnico
	 *            the mensajeTecnico to set
	 */
	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosRespuestaPF getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the datos to set
	 */
	public void setDatos(DatosRespuestaPF datos) {
		this.datos = datos;
	}

}
