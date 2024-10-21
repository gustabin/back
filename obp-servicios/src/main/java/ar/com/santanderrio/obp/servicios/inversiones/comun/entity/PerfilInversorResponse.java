package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import org.codehaus.jackson.annotate.JsonProperty;



public class PerfilInversorResponse {
	
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
	private DatosPerfilInversorResponse datos;

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

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public DatosPerfilInversorResponse getDatos() {
		return datos;
	}

	public void setDatos(DatosPerfilInversorResponse datos) {
		this.datos = datos;
	}

	

}
