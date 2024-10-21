package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseEntityBase {

	@JsonProperty("Codigo")
	protected int codigo;
	
	@JsonProperty("Mensaje")
	protected String mensaje;
	
	@JsonProperty("MensajeTecnico")
	@NotNull
	protected String mensajeTecnico;

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
