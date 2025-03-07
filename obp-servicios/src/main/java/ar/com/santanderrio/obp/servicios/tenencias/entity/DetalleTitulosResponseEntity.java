package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DetalleTitulosResponseEntity {
	
	@JsonProperty("Codigo")
	private String codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;
	
	@JsonProperty("Datos")
	private List<DatosDetallesRespuestaTituloEntity> datos;

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
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
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	/**
	 * @param mensajeTecnico the mensajeTecnico to set
	 */
	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	/**
	 * @return the datos
	 */
	public List<DatosDetallesRespuestaTituloEntity> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<DatosDetallesRespuestaTituloEntity> datos) {
		this.datos = datos;
	}
	
}
