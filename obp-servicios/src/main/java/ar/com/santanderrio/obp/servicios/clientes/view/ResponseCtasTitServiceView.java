package ar.com.santanderrio.obp.servicios.clientes.view;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.clientes.entities.CuentasCustodiaServiceEntity;

public class ResponseCtasTitServiceView {
	
	@JsonProperty("Datos")
	private CuentasCustodiaServiceEntity datos;
	
	@JsonProperty("Codigo")
	private int codigo;
	
	@JsonProperty("Mensaje")
	private String mensaje;
	
	@JsonProperty("MensajeTecnico")
	private String mensajeTecnico;

	public CuentasCustodiaServiceEntity getDatos() {
		return datos;
	}

	public void setDatos(CuentasCustodiaServiceEntity datos) {
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