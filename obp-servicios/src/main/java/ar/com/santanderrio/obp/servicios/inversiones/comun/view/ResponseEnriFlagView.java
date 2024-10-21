package ar.com.santanderrio.obp.servicios.inversiones.comun.view;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosEnri;

public class ResponseEnriFlagView {
	
	@JsonProperty("Datos")
	DatosEnri datos;
	
	@JsonProperty("Codigo")
	int codigo;
	
	@JsonProperty("Mensaje")
	String mensaje;
	
	@JsonProperty("MensajeTecnico")
	String mensajeTecnico;

	public DatosEnri getDatos() {
		return datos;
	}

	public void setDatos(DatosEnri datos) {
		this.datos = datos;
	}

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
	
	

}
