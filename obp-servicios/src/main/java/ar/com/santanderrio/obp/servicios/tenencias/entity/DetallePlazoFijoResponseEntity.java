package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DetallePlazoFijoResponseEntity {

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
	private List<DatosDetallesPlazoFijoResponseEntity> datos;

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the mensajeTecnico
	 */
	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * @param mensajeTecnico the mensajeTecnico to set
	 */
	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * @return the datos
	 */
	public List<DatosDetallesPlazoFijoResponseEntity> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<DatosDetallesPlazoFijoResponseEntity> datos) {
		this.datos = datos;
	}

}
