package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FondosAgendamientoResponseEntity {
	
	@JsonProperty("Datos")
	private List<DatosFondosAgendEntity> datos;
	
	@JsonProperty("Codigo")
	private int codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;

	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	
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
	 * @return the datos
	 */
	public List<DatosFondosAgendEntity> getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(List<DatosFondosAgendEntity> datos) {
		this.datos = datos;
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
	
	
}
