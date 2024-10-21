package ar.com.santanderrio.obp.servicios.debinws.dto;

import java.util.Date;

public class SolicitarContracargoDebinOutDTO {

	private String idContracargo;
	
	private Date fechaOperacion;

	public String getIdContracargo() {
		return idContracargo;
	}

	public void setIdContracargo(String idContracargo) {
		this.idContracargo = idContracargo;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
}
