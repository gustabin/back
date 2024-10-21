package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ParticipantesResponseEntity {
	
	@JsonProperty("Codigo")
	private String codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnica;
	
	@JsonProperty("Datos")
	private List<DatosParticipantesEntity> datos;

	/**
	 * @return the datos
	 */
	public List<DatosParticipantesEntity> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<DatosParticipantesEntity> datos) {
		this.datos = datos;
	}
	
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
	 * @return the mensajeTecnica
	 */
	public String getMensajeTecnica() {
		return mensajeTecnica;
	}

	/**
	 * @param mensajeTecnica the mensajeTecnica to set
	 */
	public void setMensajeTecnica(String mensajeTecnica) {
		this.mensajeTecnica = mensajeTecnica;
	}

}
