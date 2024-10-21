/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Detalle de la Cuenta Producto Online.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuentaProductoOnlineEntity {

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
	private DatosRespuestaCuentaProductoOnline datos;

	/**
	 * Instantiates a new detalle tenencia valuada entity.
	 */
	public CuentaProductoOnlineEntity() {
		datos = new DatosRespuestaCuentaProductoOnline();
		codigo = -1;
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
	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * Sets the mensaje tecnico.
	 *
	 * @param mensajeTecnico
	 *            the new mensaje tecnico
	 */
	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosRespuestaCuentaProductoOnline getDatos() {
		return datos;
	}

	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosRespuestaCuentaProductoOnline datos) {
		this.datos = datos;
	}

}
