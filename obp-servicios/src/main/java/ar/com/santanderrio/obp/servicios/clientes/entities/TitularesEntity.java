package ar.com.santanderrio.obp.servicios.clientes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class TitularesEntity {
	
	@JsonProperty("Nombre")
	private String nombre;
	
	@JsonProperty("Apellido")
	private String apellido;
	
	@JsonProperty("Participacion")
	private String participacion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getParticipacion() {
		return participacion;
	}

	public void setParticipacion(String participacion) {
		this.participacion = participacion;
	}
	
	 

}