package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class DetalleFondosRimpEntity {
	
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
	private List<DatosRespuestaFondosRimp> datos;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Object getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(Object mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public List<DatosRespuestaFondosRimp> getDatos() {
		return datos;
	}

	public void setDatos(List<DatosRespuestaFondosRimp> datos) {
		this.datos = datos;
	}
	
	

}
