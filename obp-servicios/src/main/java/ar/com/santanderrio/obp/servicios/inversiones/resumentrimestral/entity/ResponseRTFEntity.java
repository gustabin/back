package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseRTFEntity {
	
	@JsonProperty("Datos")
	private DatosRTFEntity datos;
	
	@JsonProperty("Codigo")
	private int codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;
	  

	public DatosRTFEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosRTFEntity datos) {
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
