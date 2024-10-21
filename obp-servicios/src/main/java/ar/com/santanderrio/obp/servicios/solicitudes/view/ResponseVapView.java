package ar.com.santanderrio.obp.servicios.solicitudes.view;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.solicitudes.entities.InformacionVap;

public class ResponseVapView {
	
	@JsonProperty("TOKEN_ENCUESTA")
	private String tokenEncuesta;
	
	@JsonProperty("Informacion")
	private InformacionVap informacion;
	
	@JsonProperty("ID_SOLICITUD")
	private String idSolicitud;
	
	@JsonProperty("ID_RESULTADO")
	private String idResultado;
	
	
	@JsonProperty("FECHA_VENCIMIENTO")
	private String fechaVencimiento;
	
	@JsonProperty("EsBloqueado")
	private boolean bloqueado;

	public String getTokenEncuesta() {
		return tokenEncuesta;
	}

	public void setTokenEncuesta(String tokenEncuesta) {
		this.tokenEncuesta = tokenEncuesta;
	}

	public InformacionVap getInformacion() {
		return informacion;
	}

	public void setInformacion(InformacionVap informacion) {
		this.informacion = informacion;
	}

	public String getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(String idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(String idResultado) {
		this.idResultado = idResultado;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	
	

}
