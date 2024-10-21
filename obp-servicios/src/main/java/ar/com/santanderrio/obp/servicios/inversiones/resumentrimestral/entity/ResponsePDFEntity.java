package ar.com.santanderrio.obp.servicios.inversiones.resumentrimestral.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponsePDFEntity {
	
	@JsonProperty("Datos")
	private List<InfoRTFEntity> datos;
	
	@JsonProperty("Codigo")
	private int codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	public List<InfoRTFEntity> getDatos() {
		return datos;
	}

	public void setDatos(List<InfoRTFEntity> datos) {
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
